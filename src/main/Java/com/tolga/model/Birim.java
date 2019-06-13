package com.tolga.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component
@Entity
@Table(name = "birim")
public class Birim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birim_id", nullable = false)
    private Long birimId;
    @Column(name = "birim_adi", nullable = false)
    private String birimAdi;

    @OneToMany(mappedBy = "birim", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personel> personelList;

    public Long getBirimId() {
        return birimId;
    }

    public void setBirimId(Long birimId) {
        this.birimId = birimId;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

    public List<Personel> getPersonelList() {
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }
}