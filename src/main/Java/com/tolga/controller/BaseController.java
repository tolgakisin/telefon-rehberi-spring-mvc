package com.tolga.controller;

import com.tolga.dao.MainDAO;
import com.tolga.model.Birim;
import com.tolga.model.Personel;
import com.tolga.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Controller
@RequestMapping("/*")
public class BaseController {

    @Autowired
    private MainService mainService;

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    //Login - Logout

    @PostMapping(value = "/login.ajax")
    public @ResponseBody
    String Login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (username.equals("admin") && password.equals("1234")) {
            session.setAttribute("username", username);
            return "Başarılı";
        } else
            return "Kullanıcı adı veya parola yanlış";
    }

    @PostMapping(value = "/logout.ajax")
    public @ResponseBody
    String Logout(HttpSession session) {
        session.removeAttribute("username");
        return "Çıkış yapıldı";
    }

    // BİRİM

    @DeleteMapping(value = "/birimsil.ajax")
    public @ResponseBody
    String BirimSil(@RequestParam Long id) {

        Boolean success = mainService.deleteBirim(id);
        logger.info("Birim silme işlemi --- " + "Birim id = " + id + " success = " + success.toString()
                + "Geri Dönüş değeri = " + success.toString());
        return success.toString();
    }

    @PostMapping(value = "/birimekle.ajax")
    public @ResponseBody
    String BirimEkle(@RequestParam Long id, @RequestParam String birim_adi) {

        Boolean success;
        for (Birim birim : mainService.getBirimList()) {
            if (birim.getBirimAdi().equals(birim_adi)) {
                success = false;
                logger.error(success.toString() + " - aynı isimden birim adı var" + "Geri Dönüş değeri = " + success.toString());
                return success.toString();
            }
        }
        success = mainService.saveOrUpdateBirim(id, birim_adi);

        logger.info("Birim ekleme veya güncelleme işlemi --- " + "Birim id = " + id +
                " Birim adi = " + birim_adi + " success = " + success.toString() + "Geri Dönüş değeri = " + success.toString());

        return success.toString();
    }

    @GetMapping(value = "/birimlistele.ajax")
    public @ResponseBody
    String BirimListele() {

        List<Birim> birimList = mainService.getBirimList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Birim birim : birimList)
            stringBuilder.append("<p>" + birim.getBirimAdi() + "</p>");
        logger.info("Birim listeleme işlemi " + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

    @GetMapping(value = "/birimsorgula.ajax")
    public @ResponseBody
    String BirimSorgula(@RequestParam String birim_adi) {
        Boolean success = false;

        if (mainService.getBirimByBirimAdi(birim_adi) != null)
            success = true;

        logger.info("Birim sorgulama işlemi --- " + "Birim adi = " + birim_adi + "Geri Dönüş değeri = " + success.toString());

        return success.toString();
    }


    // PERSONEL

    @PostMapping(value = "/personelekle.ajax")
    public @ResponseBody
    String PersonelEkle(@RequestParam Long id, @RequestParam String adi,
                        @RequestParam String soyadi, @RequestParam Long telefonNo, @RequestParam Long birimId) {

        Boolean success;

        for (Personel personel : mainService.getPersonelList()) {
            if (personel.getTelefonNo().equals(telefonNo)) {
                success = false;
                logger.error(success.toString() + " - aynı telefon numarası sistemde mevcut. EKLENEMEDİ" + "Geri Dönüş değeri = " + success.toString());
                return success.toString();
            }
        }

        success = mainService.saveOrUpdatePersonel(id, telefonNo, adi, soyadi, birimId);

        logger.info("Personel ekleme işlemi --- " + "Personel id = " + id +
                " Personel Adi = " + adi + " Personel Soyadi = " + soyadi + " Telefon Numarası = " + telefonNo + " Birim Id = " +
                birimId + " success = " + success.toString() + "Geri Dönüş değeri = " + success.toString());

        return success.toString();
    }

    @DeleteMapping(value = "/personelsil.ajax")
    public @ResponseBody
    String PersonelSil(@RequestParam Long id) {

        Boolean success;

        try {
            mainService.deletePersonel(id);
            success = true;
        } catch (Exception e) {
            success = false;
            logger.error("Personel silerken bir hata oluştu" + "Geri Dönüş değeri = " + success.toString());
        }

        logger.info("Personel silme işlemi --- " + "Personel id = " + id + " success = " + success.toString() + "Geri Dönüş değeri = " + success.toString());

        return success.toString();
    }

    @GetMapping(value = "/personellistele.ajax")
    public @ResponseBody
    String PersonelListele() {

        List<Personel> personelList = mainService.getPersonelList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Personel personel : personelList)
            stringBuilder.append("<p>" + personel.getAdi() + " " + personel.getSoyadi() + " " +
                    personel.getTelefonNo() + " " + personel.getBirim().getBirimAdi() + "</p>");
        logger.info("Personel listeleme işlemi" + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

    @GetMapping(value = "/personellistelebytelefonno.ajax")
    public @ResponseBody
    String PersonelListeleByTelefonNo(@RequestParam Long telefonNo) {

        Personel personel = mainService.getPersonelByPersonelTelefonNo(telefonNo);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(personel.getAdi() + " " + personel.getSoyadi() + " " +
                personel.getTelefonNo() + " " + personel.getBirim().getBirimAdi());

        logger.info("Telefon numarasına göre personel listeleme işlemi --- " + "telefon numarası = " + telefonNo + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

    @GetMapping(value = "/personellistelebyadi.ajax")
    public @ResponseBody
    String PersonelListeleByAdi(@RequestParam String adi) {

        List<Personel> personelList = mainService.getPersonelByIsim(adi);
        StringBuilder stringBuilder = new StringBuilder();
        for (Personel personel : personelList) {

            stringBuilder.append("<p>" + personel.getAdi() + " " + personel.getSoyadi() + " " +
                    personel.getTelefonNo() + " " + personel.getBirim().getBirimAdi() + "</p>");

        }

        logger.info("Personel adına göre personel listeleme işlemi --- " + " Personel Adı = " + adi + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

    @GetMapping(value = "/personellistelebysoyadi.ajax")
    public @ResponseBody
    String PersonelListeleBySoyadi(@RequestParam String soyadi) {

        List<Personel> personelList = mainService.getPersonelBySoyadi(soyadi);
        StringBuilder stringBuilder = new StringBuilder();
        for (Personel personel : personelList) {

            stringBuilder.append("<p>" + personel.getAdi() + " " + personel.getSoyadi() + " " +
                    personel.getTelefonNo() + " " + personel.getBirim().getBirimAdi() + "</p>");

        }
        logger.info("Personel soyadına göre personel listeleme işlemi --- " + " Personel Soyadı = " + soyadi + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

    @GetMapping(value = "/personellistelebybirimadi.ajax")
    public @ResponseBody
    String PersonelListeleByBirimAdi(@RequestParam String birimAdi) {

        List<Personel> personelList = mainService.getPersonelByBirimAdi(birimAdi);

        StringBuilder stringBuilder = new StringBuilder();
        for (Personel personel : personelList) {

            stringBuilder.append("<p>" + personel.getAdi() + " " + personel.getSoyadi() + " " +
                    personel.getTelefonNo() + " " + personel.getBirim().getBirimAdi() + "</p>");
        }

        logger.info("Birim adına göre personel listeleme işlemi --- " + " Birim Adı = " + birimAdi + "Geri Dönüş değeri = " + stringBuilder.toString());

        return stringBuilder.toString();
    }

}
