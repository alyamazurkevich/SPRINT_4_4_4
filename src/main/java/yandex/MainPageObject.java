package yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;


public class MainPageObject {

    private final WebDriver driver;
    // Локатор кнопки "Да все привыкли" в окне куки
    private final By cookieButton = By.id("rcc-confirm-button");
    // Локатор кнопки "Заказа" в шапке
    private final By headerOrderButton = By.className("Button_Button__ra12g");
    // Локатор кнопки "Заказа" расположенной на сайте и выровнена посередине
    private final By middleOrderButton = By.xpath(".//*[@id='root']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Текст в FAQ
    private final By textFieldimport = By.xpath(".//*[@class='accordion__panel' and not(@hidden)]/p");

    // Методы для работы с элементами с главной страницы
    public MainPageObject (WebDriver driver) {
        this.driver = driver;
    }
    // Подтверждение кук
    public void setcookieButton() {
        driver.findElement(cookieButton).click();
    }
    // Метод нажатие на заказ сверху страницы
    public void headerOrderButton() {
        driver.findElement(headerOrderButton).click();
    }
    // Метод нажатие на заказ середина стр
    public void middleOrderButton () {
        driver.findElement(middleOrderButton).click();
    }
    // Метод получения списка о важном
    public List<String> textFromList() {
        List<WebElement> elements = driver.findElements(By.className("accordion__button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elements.get(0));

        ArrayList <String> listText = new ArrayList <String>();
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elements.get(i));
            String textField = driver.findElement(textFieldimport).getText();
            listText.add(textField);
        }
        return listText;
    }
}