package tests.SELENİUM_TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ReusableMethods;
import utilities.TestBaseCross;

import java.util.List;

public class DERS4_CrossBrowser extends TestBaseCross {

    /*
          CrossBrowser testleri,
          uygulamamizin guncel tum browser'lar ile sorunsuz calistigini kontrol etmek icin
          sinirli sayida class ile yapilir

          bir test class'inin crossbrowser testlerde kullanilabilmesi icin
          .xml dosyasindan parametre olarak gonderilen browser'i kullanabilecegi
          ozel bir duzene ihtiyaci vardir

          Biz bu duzenlemeyi TestBaseCross ve DriverCross ile hazirladik

          Bir framework'de crossbrowser testleri ayarlamak icin
          bu duzenlemenin 1 kere yapilmasi yeterlidir
          ve bizim bu framework'de hazirladigimiz TestBaseCross ve DriverCross'u
          isyerindeki framework'e kopyalamamiz yeterli olur

          bu duzenlemeyi yaptiktan sonra
          crossbrowser calisacak class'lari bir package altinda olusturup
          TestBaseCross'a extends etmemiz,
          ve TestBaseCross'da olusturulan 'driver' objesini kullanmamiz yeterli olacaktir

          Dikkat edecegimiz son konu
          locate'leri yaptigimiz Page class'larinda
          Driver.getDriver() ile olusturulan webDriver tanimli oldugundan
          crossBrowser testlerde Page class'larini kullanmamamiz gerektigidir

          Locate'leri driver objesini kullanarak findElement()
          veya findElements() ile yapabiliriz.
       */





    @Test
    public void aramaTesti(){

        // testotomasyonu anasayfaya gidin

        driver.get(ConfigReader.getProperty("toUrl"));

        // arama kutusuna belirlenmis arama kelimesini yazip aratin

        WebElement aramaKutusu = driver.findElement(By.id("global-search"));

        aramaKutusu.sendKeys(ConfigReader.getProperty("toAranacakKelime") + Keys.ENTER);

        // arama sonucunda urun bulunabildigini test edin

        List<WebElement> bulunanSonucElementleriList =
                driver.findElements(By.xpath("//*[@class='product-box my-2  py-1']"));

        Assert.assertTrue(bulunanSonucElementleriList.size() > 0);

    }






    @Test
    public void pozitifLoginTesti(){

        // testotomasyonu anasayfaya gidin
        driver.get(ConfigReader.getProperty("toUrl"));

        // account linkine tiklayin
        driver.findElement(By.xpath("(//*[text()='Account'])[1]"))
                .click();

        // email ve password kutularina gecerli degerleri girin

        WebElement emailKutusu = driver.findElement(By.id("email"));
        WebElement passwordKutusu = driver.findElement(By.id("password"));

        emailKutusu.sendKeys(ConfigReader.getProperty("websiteValidEmail"));
        passwordKutusu.sendKeys(ConfigReader.getProperty("websiteValidPassword"));

        // login butonuna basarak login olun
        driver.findElement(By.id("submitlogin"))
                .click();


        // login oldugunuzu test etmek icin
        // logout butonun gorunur oldugunu test edin
        WebElement logoutButonu = driver.findElement(By.xpath("(//*[text()='Logout'])[2]"));

        Assert.assertTrue(logoutButonu.isDisplayed());
        ReusableMethods.bekle(1);


        // logout tusuna basin
        logoutButonu.click();

    }






}
