package com.example.btvn_buoi12.repository;

import com.example.btvn_buoi12.config.HibernateConfig;
import com.example.btvn_buoi12.entity.Lop;
import com.example.btvn_buoi12.entity.SinhVien;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SinhVienRepository {

    public List<SinhVien> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        List<SinhVien> sinhVienList = session.createQuery("FROM SinhVien").getResultList();
        return sinhVienList;
    }

    public SinhVien getOne(Long id) {
        Session session = HibernateConfig.getFACTORY().openSession();
        SinhVien sinhVien = (SinhVien) session.createQuery("FROM SinhVien WHERE id=:id").setParameter("id", id).getSingleResult();
        return sinhVien;
    }

    public boolean add(SinhVien sinhVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.persist(sinhVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(SinhVien sinhVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.merge(sinhVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(SinhVien sinhVien) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(sinhVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SinhVien> getTimKiem(String value, Lop lop) {
        Session session = HibernateConfig.getFACTORY().openSession();
        List<SinhVien> sinhVienList = (List<SinhVien>) session.createQuery("FROM SinhVien sv WHERE sv.ten LIKE:value AND sv.lopId =:lop")
                .setParameter("value", "%" + value + "%")
                .setParameter("lop", lop)
                .getResultList();
        session.close();
        return sinhVienList;
    }

    public List<SinhVien> getTop3(String tenLop) {
        Session session = HibernateConfig.getFACTORY().openSession();
        List<SinhVien> sinhVienList = (List<SinhVien>) session.createQuery("FROM SinhVien sv WHERE sv.lopId.ten =:tenLop ORDER BY sv.tuoi ASC")
                .setParameter("tenLop", tenLop)
                .setMaxResults(1)
                .getResultList();
        session.close();
        return sinhVienList;
    }

}
