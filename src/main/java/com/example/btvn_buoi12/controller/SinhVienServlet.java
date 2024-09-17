package com.example.btvn_buoi12.controller;

import com.example.btvn_buoi12.entity.Lop;
import com.example.btvn_buoi12.entity.SinhVien;
import com.example.btvn_buoi12.repository.LopRepository;
import com.example.btvn_buoi12.repository.SinhVienRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "SinhVienServlet", value = {
        "/sinh-vien/hien-thi",
        "/sinh-vien/add",
        "/sinh-vien/detail",
        "/sinh-vien/delete",
        "/sinh-vien/update",
        "/sinh-vien/view-update",
        "/sinh-vien/search",
        "/sinh-vien/top3"
})
public class SinhVienServlet extends HttpServlet {
    private SinhVienRepository sinhVienRepository = new SinhVienRepository();
    private LopRepository lopRepository = new LopRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("hien-thi")) {
            hienThi(request, response);
        } else if (uri.contains("detail")) {
            detail(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else if (uri.contains("view-update")) {
            viewUpdate(request, response);
        } else if (uri.contains("search")) {
            search(request, response);
        } else if (uri.contains("top3")) {
            top3(request, response);
        } else {
            hienThi(request, response);
        }
    }

    private void top3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sinhVienList", sinhVienRepository.getTop3("SD18317"));
        request.setAttribute("lopList", lopRepository.getAll());
        request.getRequestDispatcher("/view/trang-chu.jsp").forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("value");
        Long idLop = Long.parseLong(request.getParameter("lopId"));
        Lop lop = lopRepository.getOne(idLop);
        request.setAttribute("sinhVienList", sinhVienRepository.getTimKiem(value, lop));
        request.setAttribute("lopList", lopRepository.getAll());
        request.getRequestDispatcher("/view/trang-chu.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("detail", sinhVienRepository.getOne(Long.parseLong(request.getParameter("id"))));
        request.setAttribute("lopList", lopRepository.getAll());
        request.getRequestDispatcher("/view/update.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sinhVienRepository.delete(sinhVienRepository.getOne(Long.parseLong(request.getParameter("id"))));
        response.sendRedirect("/sinh-vien/hien-thi");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("detail", sinhVienRepository.getOne(Long.parseLong(request.getParameter("id"))));
        request.setAttribute("sinhVienList", sinhVienRepository.getAll());
        request.setAttribute("lopList", lopRepository.getAll());
        request.getRequestDispatcher("/view/trang-chu.jsp").forward(request, response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sinhVienList", sinhVienRepository.getAll());
        request.setAttribute("lopList", lopRepository.getAll());
        request.getRequestDispatcher("/view/trang-chu.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();

        if (uri.contains("add")) {
            add(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SinhVien sinhVien = sinhVienRepository.getOne(Long.parseLong(request.getParameter("id")));
        sinhVien.setMa(request.getParameter("ma"));
        sinhVien.setTen(request.getParameter("ten"));
        sinhVien.setTuoi(Integer.parseInt(request.getParameter("tuoi")));
        sinhVien.setDiaChi(request.getParameter("diaChi"));
        sinhVien.setGioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));
        sinhVien.setLopId(lopRepository.getOne(Long.parseLong(request.getParameter("lopId"))));
        if (sinhVienRepository.update(sinhVien)) {
            response.sendRedirect("/sinh-vien/hien-thi");
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setMa(request.getParameter("ma"));
        sinhVien.setTen(request.getParameter("ten"));
        sinhVien.setTuoi(Integer.parseInt(request.getParameter("tuoi")));
        sinhVien.setDiaChi(request.getParameter("diaChi"));
        sinhVien.setGioiTinh(Integer.parseInt(request.getParameter("gioiTinh")));
        sinhVien.setLopId(lopRepository.getOne(Long.parseLong(request.getParameter("lopId"))));
        if (sinhVienRepository.add(sinhVien)) {
            response.sendRedirect("/sinh-vien/hien-thi");
        }
    }

}