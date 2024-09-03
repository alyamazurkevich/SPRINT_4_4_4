import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import yandex.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;


import static urls.Urls.URL;

public class GeneralTest {

    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        MainPageObject mainPage = new  MainPageObject(driver);
        driver = new ChromeDriver();
        driver.get(URL);
    }

//
    //  WebDriverManager.firefoxdriver().setup();
    //  MainPageObject mainPage = new MainPageObject(driver);
    //  driver = new FireFoxdriver();
    //  driver.get(URL);
//

    //Закрытие браузера
    @After
    public void tearDown() {
        driver.quit();
    }
}
