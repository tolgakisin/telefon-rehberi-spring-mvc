import com.tolga.config.AppConfig;
import com.tolga.config.WebAppInitializer;
import com.tolga.config.WebConfig;
import com.tolga.controller.BaseController;
import com.tolga.model.Birim;
import com.tolga.model.Personel;
import com.tolga.service.MainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class,
        WebConfig.class})
@Transactional
@WebAppConfiguration
public class TestBaseController {
    @Autowired
    private BaseController baseController;

    @Autowired
    private MainService mainService;

    @Test
    public void testBirimListele() {

        List<Birim> birimList = mainService.getBirimList();
        Assert.assertTrue(birimList.size() > 0);
    }

    @Test
    public void testBirimSorgula() {
        List<Birim> birimList = mainService.getBirimList();

        String parameter = birimList.get(0).getBirimAdi();
        Birim birim = mainService.getBirimByBirimAdi(parameter);
        Assert.assertNotNull(birim);
    }

    @Test
    public void testBirimEkle() {

        String birimAdi = "testmethodbirim";
        Long birimId = 23242L;

        String success = "";
        success = baseController.BirimEkle(birimId, birimAdi);

        Assert.assertEquals(success, "true");
    }

    @Test
    public void testBirimSil() {
        Birim birim = mainService.getBirimByBirimAdi("birimtest");

        String success = "";
        success = baseController.BirimSil(birim.getBirimId());

        Assert.assertEquals(success, "true");
    }

    @Test
    public void testPersonelListele() {
        List<Personel> personelList = mainService.getPersonelList();

        Assert.assertTrue(personelList.size() > 0);
    }

    @Test
    public void testPersonelEkle() {
        String adi = "testmethodadi";
        String soyadi = "testmethodsoyadi";
        Long telefonNo = 05555555550L;
        Long id = 23242L;
        Long birimId = 40L;

        String success = baseController.PersonelEkle(id, adi, soyadi, telefonNo, birimId);

        Assert.assertEquals(success, "true");
    }

    @Test
    public void testPersonelSil() {
        Long id = 36L;

        String success = baseController.PersonelSil(id);
        Assert.assertEquals(success, "true");
    }

    @Test
    public void testPersonelListeleByTelefonNo() {
        Long telefonNo = mainService.getPersonelList().get(0).getTelefonNo();

        Personel personel = mainService.getPersonelByPersonelTelefonNo(telefonNo);
        Assert.assertNotNull(personel);
    }

    @Test
    public void testPersonelListeleByAdi() {
        String adi = mainService.getPersonelList().get(0).getAdi();

        List<Personel> personelList = mainService.getPersonelByIsim(adi);

        Assert.assertTrue(personelList.size() > 0);
    }

    @Test
    public void testPersonelListeleBySoyadi() {
        String soyadi = mainService.getPersonelList().get(0).getSoyadi();

        List<Personel> personelList = mainService.getPersonelBySoyadi(soyadi);

        Assert.assertTrue(personelList.size() > 0);
    }

    @Test
    public void testPersonelListeleByBirimAdi() {
        String birimAdi = mainService.getBirimList().get(3).getBirimAdi();

        List<Personel> personelList = mainService.getPersonelByBirimAdi(birimAdi);

        Assert.assertTrue(personelList.size() > 0);
    }

    @Test
    public void testLogin() {
        String username = "admin";
        String password = "1234";
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        String msg = baseController.Login(username, password, session);

        Assert.assertEquals(msg, "Başarılı");
    }

    @Test
    public void testLogout() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        String msg = baseController.Logout(session);

        Assert.assertEquals(msg, "Çıkış yapıldı");
    }


}
