import com.tolga.config.AppConfig;
import com.tolga.config.WebAppInitializer;
import com.tolga.config.WebConfig;
import com.tolga.dao.MainDAO;
import com.tolga.model.Birim;
import com.tolga.model.Personel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class,
        WebConfig.class})
@Transactional
@WebAppConfiguration
public class TestMainDAO {
    @Autowired
    private MainDAO mainDAO;

    @Test
    public void testGetCurrentSession() {
        if (mainDAO.getCurrentSession() == null)
            Assert.assertNull(mainDAO.getCurrentSession());
        else
            Assert.assertNotNull(mainDAO.getCurrentSession());
    }

    @Test
    public void testLoadObject() {
        Birim birim = (Birim) mainDAO.loadObject(Birim.class, 19L);
        if (birim == null) {
            Assert.assertNull(birim);
        } else {
            Assert.assertNotNull(birim);
        }
    }

    @Test
    public void testRemoveObject() {
        boolean success;
        Birim birim = (Birim) mainDAO.loadObject(Birim.class, 19L);
        if (birim == null) {

            Assert.assertNull(birim);
        } else {
            success = mainDAO.removeObject(birim);
            Assert.assertTrue(success);
        }
    }

    @Test
    @Rollback(value = true)
    public void testSaveOrUpdateObject() {
        Birim birim = new Birim();
        birim.setBirimAdi("TestBirimAdi");
        birim.setBirimId(454L);

        boolean success = mainDAO.saveOrUpdateObject(birim);
        Assert.assertTrue(success);
    }

    @Test
    public void testGetBirimList() {
        List<Birim> birimList = mainDAO.getBirimList();
        Assert.assertTrue(birimList.size() > 0);

    }

    // ?
    @Test
    public void testGetBirimByBirimId() {
        List<Birim> birimList = mainDAO.getBirimList();
        Birim birim = null;
        birim = mainDAO.getBirimByBirimId(birimList.get(0).getBirimId());
        Assert.assertTrue(birim != null);

    }

    @Test
    public void testGetBirimByBirimAdi() {
        List<Birim> birimList = mainDAO.getBirimList();
        Birim birim = null;
        birim = mainDAO.getBirimByBirimAdi(birimList.get(0).getBirimAdi());
        Assert.assertTrue(birim != null);
    }

    @Test
    public void testGetPersonelList() {
        List<Personel> personelList = mainDAO.getPersonelList();
        Assert.assertTrue(personelList.size() > 0);
    }

    @Test
    public void testGetPersonelByPersonelId() {
        List<Personel> personelList = mainDAO.getPersonelList();
        Personel personel = null;
        personel = mainDAO.getPersonelByPersonelId(personelList.get(0).getPersonelId());
        Assert.assertTrue(personel != null);
    }

    @Test
    public void testGetPersonelByPersonelTelefonNo() {
        List<Personel> personelList = mainDAO.getPersonelList();
        Personel personel = null;
        personel = mainDAO.getPersonelByPersonelTelefonNo(personelList.get(0).getTelefonNo());
        Assert.assertTrue(personel != null);
    }

    @Test
    public void testGetPersonelByPersonelIsim() {

        List<Personel> personelList = mainDAO.getPersonelList();
        List<Personel> personelListByIsim = null;
        personelListByIsim = mainDAO.getPersonelByIsim(personelList.get(0).getAdi());
        Assert.assertTrue(personelListByIsim.size() > 0);
    }

    @Test
    public void testGetPersonelByPersonelSoyadi() {

        List<Personel> personelList = mainDAO.getPersonelList();
        List<Personel> personelListBySoyadi = null;
        personelListBySoyadi = mainDAO.getPersonelBySoyadi(personelList.get(0).getSoyadi());
        Assert.assertTrue(personelListBySoyadi.size() > 0);
    }

    @Test
    public void testGetPersonelByBirimAdi() {

        List<Birim> birimList = mainDAO.getBirimList();
        List<Personel> personelListByBirimAdi = null;
        personelListByBirimAdi = mainDAO.getPersonelByBirimAdi(birimList.get(3).getBirimAdi());
        Assert.assertTrue(personelListByBirimAdi.size() > 0);
    }
}
