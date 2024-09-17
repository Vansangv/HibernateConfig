package com.example.btvn_buoi12.repository;

import com.example.btvn_buoi12.config.HibernateConfig;
import com.example.btvn_buoi12.entity.Lop;
import org.hibernate.Session;

import java.util.List;

public class LopRepository {

    public List<Lop> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        List<Lop> lopList = session.createQuery("FROM Lop").getResultList();
        session.close();
        return lopList;
    }

    public Lop getOne(Long id) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Lop lop = (Lop) session.createQuery("FROM Lop WHERE id=:id").setParameter("id", id).getSingleResult();
        session.close();
        return lop;
    }

}
