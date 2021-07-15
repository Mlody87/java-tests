import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

class inputStringField {
    private String name;
    private String value;
    private String xPath;

    public inputStringField(String name, String ExpectedValue, String xPath) {
        this.name = name;
        this.value = ExpectedValue;
        this.xPath = xPath;
    }

    public String getName() {
        return this.name;
    }

    public String getExpectedValue() {
        return this.value;
    }

    public String getXPath() {
        return this.xPath;
    }
}

class checkboxField {
    private String name;
    private String value;
    private String xPath;
    private String iconXPath;

    public checkboxField(String name, String ExpectedValue, String xPath, String iconXPath) {
        this.name = name;
        this.value = ExpectedValue;
        this.xPath = xPath;
        this.iconXPath = iconXPath;
    }

    public String getName() {
        return this.name;
    }

    public String getExpectedValue() {
        return this.value;
    }

    public String getXPath() {
        return this.xPath;
    }

    public String iconXPath() {
        return this.iconXPath;
    }
}

class NewsObject {
    public ArrayList<inputStringField> stringFields = new ArrayList<inputStringField>();
    public ArrayList<checkboxField> checkboxFields = new ArrayList<checkboxField>();
    public List<WebElement> dataFields;

    public NewsObject(ChromeDriver driver) {

        stringFields.add(new inputStringField("title", "TestInformacja","//input[@name='title']"));
        stringFields.add(new inputStringField("supertitle", "TestNadtytul","//input[@name='supertitle']"));
        stringFields.add(new inputStringField("hpTitle", "TestHomePageTitle","//input[@name='hpTitle']"));
        stringFields.add(new inputStringField("infoTitle", "TestInfoTytul","//input[@name='infoTitle']"));
        stringFields.add(new inputStringField("seoTitle", "TestSeoTytul","//input[@name='seoTitle']"));
        stringFields.add(new inputStringField("editorId", "123","//input[@name='editorId']"));
        stringFields.add(new inputStringField("associatedObject", "123","//input[@name='associatedObject']"));

        checkboxFields.add(new checkboxField("onlyInTvp","true","//input[@name='onlyInTvp']","//input[@name='onlyInTvp']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("onlyWithUs","true","//input[@name='onlyWithUs']","//input[@name='onlyWithUs']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("pilne","true","//input[@name='pilne']","//input[@name='pilne']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("textLive","true","//input[@name='textLive']","//input[@name='textLive']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("transmision","true","//input[@name='transmision']","//input[@name='transmision']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("playOnHomePage","true","//input[@name='playOnHomePage']","//input[@name='playOnHomePage']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("transmissionNews","true","//input[@name='transmissionNews']","//input[@name='transmissionNews']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("newsBanner","true","//input[@name='newsBanner']","//input[@name='newsBanner']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("hideOnTheHP","true","//input[@name='hideOnTheHP']","//input[@name='hideOnTheHP']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("newsAdvert","true","//input[@name='newsAdvert']","//input[@name='newsAdvert']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("noAdv","true","//input[@name='noAdv']","//input[@name='noAdv']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("showVideoAsGallery","true","//input[@name='showVideoAsGallery']","//input[@name='showVideoAsGallery']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("noAdsDisplay","true","//input[@name='noAdsDisplay']","//input[@name='noAdsDisplay']/following-sibling::span/descendant::i"));
        checkboxFields.add(new checkboxField("doNotShowInApp","true","//input[@name='doNotShowInApp']","//input[@name='doNotShowInApp']/following-sibling::span/descendant::i"));

        dataFields = driver.findElements(By.xpath("//button[@title='Ustaw obecną date']"));

    }
}

public class SeleniumTest {
    public String baseUrl = "";
    String driverPath = "C:\\\\Users\\\\pawkab\\\\Desktop\\\\Selenium\\\\chromedriver\\\\chromedriver91.exe";
    public WebDriver driver;
    public int numer;
    public WebDriverWait wait;

    @BeforeSuite
    public void Przygotowanie() {
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();

        options.addArguments("headless");
        options.addArguments("window-size=1280,800");
        options.addArguments("allow-insecure-localhost");

        driver = new ChromeDriver(options);


        wait = new WebDriverWait(driver, 10);

        Random rand = new Random();
        numer = rand.nextInt(1000);

        driver.manage().window().maximize();
    }

    @AfterSuite
    public void Zakonczenie() {
        driver.close();
    }

    @Test(testName = "Logowanie", priority = 0)
    public void LogowanieTest() {
        driver.get("http://devcms.tvp.pl/");
        driver.navigate().to("https://devcms.tvp.pl/login?redirect=home");
        By byLogin = By.xpath("//input[@name='login']");
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(byLogin));
        login.sendKeys("pawkab");

        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.sendKeys("x");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        //WebElement button = driver.findElement(By.xpath("//span[contains(text(),'Zaloguj')]"));
        WebElement button = driver.findElement(By.name("button"));
        button.click();

        By loged = By.xpath("//*[contains(text(),'Konto wygasa:')]");
        WebElement zalogowany = (wait.until(ExpectedConditions.presenceOfElementLocated(loged)));

    }

    @Test(testName = "Przejscie do folderu testow", priority = 1)
    public void OtwarcieGlownegoFolderuTest() {
        By byKatalog = By.xpath("//span[contains(text(), 'AutoTests')]");
        WebElement katalog = wait.until(ExpectedConditions.presenceOfElementLocated(byKatalog));
        katalog.click();
    }

    @Test(testName = "Utworzenie wortalu", priority = 2)
    public void VortalCreateTest() {

        By xDodaj = By.xpath("//div[contains(@class, 'object-adder')]//button[@class='vs-con-dropdown parent-dropdown']");
        WebElement dodaj = wait.until(ExpectedConditions.presenceOfElementLocated(xDodaj));
        dodaj.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By xSzukaj = By.xpath("//div[@class='vs-con-input']//input[@type='text']");
        WebElement szukaj = wait.until(ExpectedConditions.presenceOfElementLocated(xSzukaj));
        szukaj.sendKeys("wortal");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By xPozycjaListy = By.xpath("//a[@class='vs-dropdown--item-link'][contains(text(), 'wortal')]");
        WebElement pozycjaListy = wait.until(ExpectedConditions.presenceOfElementLocated(xPozycjaListy));
        pozycjaListy.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By titleBy = By.name("title");
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(titleBy));
        titleElement.sendKeys("Testowy wortal");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        WebElement saveElement = driver.findElement(By.id("saveButton"));
        saveElement.click();

        By byCreatedCheck = By.xpath("//h2[@class='mb-1 title'][contains(text(), 'Testowy wortal')]");
        WebElement createdCheck = wait.until(ExpectedConditions.presenceOfElementLocated(byCreatedCheck));
    }

    @Test(testName = "Utworzenie katalogu", priority = 3)
    public void DirectoryCreateTest() {

        By xDodaj = By.xpath("//div[contains(@class, 'object-adder')]//button[@class='vs-con-dropdown parent-dropdown']");
        WebElement dodaj = wait.until(ExpectedConditions.presenceOfElementLocated(xDodaj));
        dodaj.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        //do poprawienia, musi znalezc tez w tym divie span=text Szukaj
        By xSzukaj = By.xpath("//div[@class='vs-con-input']//input[@type='text']");
        WebElement szukaj = wait.until(ExpectedConditions.presenceOfElementLocated(xSzukaj));
        szukaj.sendKeys("directory_standard");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By xPozycjaListy = By.xpath("//a[@class='vs-dropdown--item-link'][contains(text(), 'katalog')]");
        WebElement pozycjaListy = wait.until(ExpectedConditions.presenceOfElementLocated(xPozycjaListy));
        pozycjaListy.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By titleBy = By.name("title");
        WebElement titleElement = wait.until(ExpectedConditions.presenceOfElementLocated(titleBy));
        titleElement.sendKeys("Testowy katalog");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        WebElement saveElement = driver.findElement(By.id("saveButton"));
        saveElement.click();

        By byCreatedCheck = By.xpath("//h2[@class='mb-1 title'][contains(text(), 'Testowy katalog')]");
        WebElement createdCheck = wait.until(ExpectedConditions.presenceOfElementLocated(byCreatedCheck));


    }

    @Test(testName = "Dodanie informacji", priority = 4)
    public void AddNewsTest() {

        By xDodaj = By.xpath("//div[contains(@class, 'object-adder')]//button[@class='vs-con-dropdown parent-dropdown']");
        WebElement dodaj = wait.until(ExpectedConditions.presenceOfElementLocated(xDodaj));
        dodaj.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By xSzukaj = By.xpath("//div[@class='vs-con-input']//input[@type='text']");
        WebElement szukaj = wait.until(ExpectedConditions.presenceOfElementLocated(xSzukaj));
        szukaj.sendKeys("informacja");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        By xPozycjaListy = By.xpath("//a[@class='vs-dropdown--item-link'][contains(text(), 'informacja')]");
        WebElement pozycjaListy = wait.until(ExpectedConditions.presenceOfElementLocated(xPozycjaListy));
        pozycjaListy.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        }

        NewsObject news = new NewsObject((ChromeDriver) driver);

        for (int i = 0; i < news.stringFields.size(); i++) {
            WebElement addElement = driver.findElement(By.xpath(news.stringFields.get(i).getXPath()));
            addElement.sendKeys(news.stringFields.get(i).getExpectedValue());
        }

        for (int j = 0; j < news.checkboxFields.size(); j++) {
            WebElement addElement = driver.findElement(By.xpath(news.checkboxFields.get(j).getXPath()));
            addElement.click();
        }

        for (int l = 0; l < news.dataFields.size(); l++) {
            String disabled = news.dataFields.get(l).getAttribute("disabled");

            if (disabled == null) {
                news.dataFields.get(l).click();
            }
        }

        JavascriptExecutor js = (JavascriptExecutor)driver;

        WebElement opis = driver.findElement(By.xpath("//div[@name='mainPageDescription']//p"));
        js.executeScript("arguments[0].innerHTML ='Testowy opis';", opis);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        WebElement saveElement = driver.findElement(By.id("saveButton"));
        saveElement.click();

        By byCreatedCheck = By.xpath("//h2[@class='mb-1 title'][contains(text(), 'TestInformacja')]");
        WebElement createdCheck = wait.until(ExpectedConditions.presenceOfElementLocated(byCreatedCheck));
    }

    @Test(testName = "Sprawdzenie danych w informacji", priority = 5)
    public void AddNewsDataCheckTest() {

        /*
        driver.navigate().to("https://devcms.tvp.pl/listing/44529019");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        */

        NewsObject news = new NewsObject((ChromeDriver) driver);
        SoftAssert softassert = new SoftAssert();

        int i;
        for(i=0; i<news.stringFields.size(); i++){

            String inputValue = driver.findElement(By.xpath(news.stringFields.get(i).getXPath())).getAttribute("value");

            softassert.assertEquals(inputValue, news.stringFields.get(i).getExpectedValue());
        }

        int j;
        for(j=0; j<news.checkboxFields.size();j++){
            String checkboxValue = driver.findElement(By.xpath(news.checkboxFields.get(j).getXPath())).getAttribute("value");
            String checkboxIcon = driver.findElement(By.xpath(news.checkboxFields.get(j).iconXPath())).getText();

            softassert.assertEquals(checkboxValue, news.checkboxFields.get(j).getExpectedValue());
            softassert.assertEquals(checkboxIcon, "check");

        }

        softassert.assertAll();

    }

}
