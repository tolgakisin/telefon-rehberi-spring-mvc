package com.tolga.dao;

import com.tolga.model.Birim;
import com.tolga.model.Personel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class MainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = LoggerFactory.getLogger(MainDAO.class);

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        getCurrentSession().save(object);
        return true;
    }

    public boolean removeObject(Object object) {
        getCurrentSession().remove(object);
        return true;
    }

    // BİRİM
    public List<Birim> getBirimList() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Birim> criteriaQuery = criteriaBuilder.createQuery(Birim.class);
        Root<Birim> root = criteriaQuery.from(Birim.class);

        criteriaQuery.select(root);

        Query<Birim> query = getCurrentSession().createQuery(criteriaQuery);
        List<Birim> birimList = null;
        try {
            birimList = query.getResultList();
        } catch (Exception e) {
            logger.error("BIRIMLIST NULL GELDİ");
        }
        return birimList;
    }

    public Birim getBirimByBirimId(Long birimId) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Birim> criteriaQuery = criteriaBuilder.createQuery(Birim.class);
        Root<Birim> root = criteriaQuery.from(Birim.class);

        Predicate predicate = criteriaBuilder.equal(root.get("birimId"), birimId);
        criteriaQuery.select(root).where(predicate);

        Query<Birim> query = getCurrentSession().createQuery(criteriaQuery);
        Birim birim = null;
        try {
            birim = query.getSingleResult();
        } catch (Exception e) {
            logger.error("BİRİM NULL GELDİ");
        }
        return birim;
    }

    public Birim getBirimByBirimAdi(String birimAdi) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Birim> criteriaQuery = criteriaBuilder.createQuery(Birim.class);
        Root<Birim> root = criteriaQuery.from(Birim.class);

        Predicate predicate = criteriaBuilder.equal(root.get("birimAdi"), birimAdi);
        criteriaQuery.select(root).where(predicate);

        Query<Birim> query = getCurrentSession().createQuery(criteriaQuery);
        Birim birim = null;
        try {
            birim = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birim;
    }

    // PERSONEL

    public List<Personel> getPersonelList() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        criteriaQuery.select(root);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        List<Personel> personelList = null;
        try {
            personelList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personelList;
    }

    // ornek CriteriaBuilder kullanarak Id disinda alan sorgusu
    public Personel getPersonelByPersonelId(Long personelId) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        Predicate predicate = criteriaBuilder.equal(root.get("personelId"), personelId);
        criteriaQuery.select(root).where(predicate);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        Personel personel = null;
        try {
            personel = query.getSingleResult();
        } catch (Exception e) {
            logger.error("PERSONEL NULL GELDİ");
        }
        return personel;
    }

    public Personel getPersonelByPersonelTelefonNo(Long telefonNo) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        Predicate predicate = criteriaBuilder.equal(root.get("telefonNo"), telefonNo);
        criteriaQuery.select(root).where(predicate);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        Personel personel = null;
        try {
            personel = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personel;
    }

    public List<Personel> getPersonelByIsim(String adi) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        Predicate predicate = criteriaBuilder.equal(root.get("adi"), adi);
        criteriaQuery.select(root).where(predicate);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        List<Personel> personelList = null;
        try {
            personelList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personelList;
    }

    public List<Personel> getPersonelBySoyadi(String soyadi) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> root = criteriaQuery.from(Personel.class);

        Predicate predicate = criteriaBuilder.equal(root.get("soyadi"), soyadi);
        criteriaQuery.select(root).where(predicate);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        List<Personel> personelList = null;
        try {
            personelList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personelList;
    }

    public List<Personel> getPersonelByBirimAdi(String birimAdi) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Personel> criteriaQuery = criteriaBuilder.createQuery(Personel.class);
        Root<Personel> personelRoot = criteriaQuery.from(Personel.class);

        Predicate predicate = criteriaBuilder.equal(personelRoot.get("birim").get("birimAdi"), birimAdi);
        criteriaQuery.select(personelRoot).where(predicate);

        Query<Personel> query = getCurrentSession().createQuery(criteriaQuery);
        List<Personel> personelList = null;
        try {
            personelList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personelList;
    }
}
