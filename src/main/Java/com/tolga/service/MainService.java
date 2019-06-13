package com.tolga.service;


import com.tolga.dao.MainDAO;
import com.tolga.model.Birim;
import com.tolga.model.Personel;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class MainService {

    @Autowired
    private MainDAO mainDAO;

    // BİRİM

    @Transactional
    public Boolean saveOrUpdateBirim(Long birimId, String birimAdi) {


        Birim birim = (Birim) mainDAO.getBirimByBirimId(birimId);

        if (birim == null) {
            birim = new Birim();
            birim.setBirimId(birimId);
        }
        birim.setBirimAdi(birimAdi);

        return mainDAO.saveOrUpdateObject(birim);
    }

    @Transactional
    public Boolean deleteBirim(Long birimId) {
        Birim birim = (Birim) mainDAO.loadObject(Birim.class, birimId);
        return mainDAO.removeObject(birim);
    }

    @Transactional
    public List<Birim> getBirimList() {
        return mainDAO.getBirimList();
    }

    @Transactional
    public Birim getBirimByBirimAdi(String birimAdi) {
        return mainDAO.getBirimByBirimAdi(birimAdi);
    }

    // PERSONEL

    @Transactional
    public Boolean saveOrUpdatePersonel(Long personelId, Long telefonNo, String adi, String soyadi, Long birimId) {
        Personel personel = mainDAO.getPersonelByPersonelId(personelId);
        if (personel == null) {
            personel = new Personel();
            personel.setPersonelId(personelId);
        }
        personel.setAdi(adi);
        personel.setSoyadi(soyadi);
        personel.setTelefonNo(telefonNo);

        Birim birim = (Birim) mainDAO.loadObject(Birim.class, birimId);
        personel.setBirim(birim);

        return mainDAO.saveOrUpdateObject(personel);
    }

    @Transactional
    public Boolean deletePersonel(Long personelId) {
        Personel personel = (Personel) mainDAO.loadObject(Personel.class, personelId);
        return mainDAO.removeObject(personel);
    }

    @Transactional
    public List<Personel> getPersonelList() {
        return mainDAO.getPersonelList();
    }

    @Transactional
    public Personel getPersonelByPersonelTelefonNo(Long telefonNo) {
        return mainDAO.getPersonelByPersonelTelefonNo(telefonNo);
    }

    @Transactional
    public List<Personel> getPersonelByIsim(String adi) {
        return mainDAO.getPersonelByIsim(adi);
    }

    @Transactional
    public List<Personel> getPersonelBySoyadi(String soyadi) {
        return mainDAO.getPersonelBySoyadi(soyadi);
    }

    @Transactional
    public List<Personel> getPersonelByBirimAdi(String birimAdi) {
        return mainDAO.getPersonelByBirimAdi(birimAdi);
    }


}
