package yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.MatcherAssert;
import static org.junit.Assert.*;
import java.util.List;
import java.time.Duration;

public class OrderPageObject {

    private final WebDriver driver;
    private final WebDriverWait wait;
    // Локатор поля "Имя"
    private final By fieldFirstName = By.xpath(".//input[@placeholder='Имя']");

    // Локатор поля "Фамилия"
    private final By fieldLastName = By.xpath(".//input[@placeholder='Фамилия']");

    // Локатор поля "Адрес: куда привезти заказ"
    private final By addressField = By.xpath(".//input[@placeholder='Адрес: куда привезти заказ']");

    // Локатор поля "Станция метро"
    private final By stationM = By.xpath("//input[@placeholder='Станция метро']");

    // Локатор поля "Телефон: на него позвонит курьер"
    private final By phoneNumber = By.xpath(".//input[@placeholder='Телефон: на него позвонит курьер']");

    // Локатор кнопки "Далее"
    private final By buttonNext = By.xpath(".//*[@id='root']//button[text()='Далее']");

    // Локатор выбора Даты
    private final By rentalDateField = By.xpath(".//input[@placeholder='Когда привезти самокат']");

    // Локатор для списка сроков аренды
    private final By rentaltime = By.className("Dropdown-arrow");

    // Локатор цвета самоката "Чёрный жемчуг"
    private final By colorBlackCheckbox = By.xpath(".//input[@id='black']");

    // Локатор цвета самоката "Серая безысходность"
    private final By colorGreyCheckbox = By.xpath(".//input[@id='grey']");

    // Локатор поля "Комментарий"
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Локатор кнопки "Заказать"
    private final By orderButton = By.xpath(".//*[@id='root']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор кнопки "ДА" в окне "Хотите оформить заказ?"
    private final By orderButtonYes = By.xpath(".//button[text()='Да']");


    //Модальное окно заказа
    private final By orderModalW= By.className("Order_Modal__YZ-d3");
    //Заказ оформлен
    private final By orderModalheader = By.className("Order_ModalHeader__3FDaJ");

    public OrderPageObject (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    // Методы для работы с элементами страницы заказа
    // Ввод имени клиента
    public void sendFirstName(String firstName) {
        assertTrue(driver.findElement(fieldFirstName).isEnabled());
        driver.findElement(fieldFirstName).clear();
        driver.findElement(fieldFirstName).sendKeys(firstName);
    }
    // Ввод Фамилии
    public void sendLastName(String lastName) {
        assertTrue(driver.findElement(fieldLastName).isEnabled());
        driver.findElement(fieldLastName).clear();
        driver.findElement(fieldLastName).sendKeys(lastName);
    }

    // Ввод адреса доставки
    public void  sendAddress(String address) {
        assertTrue(driver.findElement(addressField).isEnabled());
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    // Выбор станции метро
    public void sendStation (int station) {
        driver.findElement(stationM).click();
        List<WebElement> elements = driver.findElements(By.className("select-search__row"));
        elements.get(station).click();
    }

    // Ввод телефона
    public void enterPhoneNumber (String phoneNum) {
        assertTrue(driver.findElement(phoneNumber).isEnabled());
        driver.findElement(phoneNumber).clear();
        driver.findElement(phoneNumber).sendKeys(phoneNum);
    }

    // Клик на кнопку далее
    public void clickButtonNext () {
        driver.findElement(buttonNext).click();
    }

    // Выбор даты доставки
    public void sendRentalDate(String date) {
        assertTrue(driver.findElement(rentalDateField).isEnabled());
        driver.findElement(rentalDateField).clear();
        driver.findElement(rentalDateField).sendKeys(date);
    }

    // Выбор срока аренды самоката
    public void sendRentalTime(int rentalTime) {
        driver.findElement(rentaltime).click();
        List<WebElement> elements = driver.findElements(By.className("Dropdown-option"));
        elements.get(rentalTime).click();
    }
    // Выбор цвета
    public void sendColor (String color) {
        driver.findElement(By.xpath(".//*[@id='root']//label[@for = '" + color + "']")).click();
    }

    // Заполнение поля "Комментарий для курьера"
    public void sendComment (String comment) {
        assertTrue(driver.findElement(commentField).isEnabled());
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }
    // Клик по кнопке "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    // Клик на кнопку "ДА" в окне "Хотите оформить заказ?"
    public void clickOrderButtonYes() {
        driver.findElement(orderButtonYes).click();
    }

    // Метод оформления заказа - далее через метод тесты на "ЗАКАЗ ОФОРМЛЕН"
    public void checkingOrderButton (String firstName,
                                     String lastName,
                                     String address,
                                     int station,
                                     String phoneNum,
                                     String rentalDate,
                                     int rentalTime,
                                     String color,
                                     String comment) {

        sendFirstName(firstName);
        sendLastName(lastName);
        sendAddress(address);
        sendStation(station);
        enterPhoneNumber(phoneNum);
        clickButtonNext();
        sendRentalDate(rentalDate);
        sendRentalTime(rentalTime);
        sendColor(color);
        sendComment(comment);
        clickOrderButton();
        clickOrderButtonYes();
    }

    // Метод загрузки "Заказа"
    public void orderModal() {
        new WebDriverWait(driver,Duration.ofSeconds(7))
                .until(driver -> (driver.findElement(orderModalW)));
    }
    // Метод проверки Статуса "Заказа"
    public void checkingOrderStatus (String expectedStatus) {
        orderModal();
        MatcherAssert.assertThat(driver.findElement(orderModalheader).getText(),containsString(expectedStatus));
    }
}

