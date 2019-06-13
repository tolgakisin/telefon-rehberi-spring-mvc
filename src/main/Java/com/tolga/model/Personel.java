package com.tolga.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "personel")
public class Personel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personel_id", nullable = false)
    private Long personelId;
    @Column(name = "telefon_no", nullable = false)
    private Long telefonNo;
    @Column(name = "adi", nullable = false, unique = true)
    private String adi;
    @Column(name = "soyadi", nullable = false)
    private String soyadi;
    @ManyToOne
    @JoinColumn(name = "birim_id", referencedColumnName = "birim_id", nullable = false)
    private Birim birim;

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public Long getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(Long telefonNo) {
        this.telefonNo = telefonNo;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Birim getBirim() {
        return birim;
    }

    public void setBirim(Birim birim) {
        this.birim = birim;
    }
}