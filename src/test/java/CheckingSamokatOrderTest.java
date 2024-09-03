import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import yandex.MainPageObject;
import yandex.OrderPageObject;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)

public class CheckingSamokatOrderTest   {

    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int station;
    private final String phoneNum;
    private final String rentalDate;
    private final int rentalTime;
    private final String color;
    private final String comment;

    public  CheckingSamokatOrderTest (String firstName, String lastName, String address, int station, String phoneNum, String rentalDate, int rentalTime, String color, String comment) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phoneNum = phoneNum;
        this.rentalDate = rentalDate;
        this.rentalTime = rentalTime;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters

    public static Object[][] checkingOrderButton () {
        return new Object[][] {
                {"Перс","Прайс","ул. Воздвиженка, 3/5с1, Москва","Библиотека имени Ленина ", "79315369414","13.09.2024", "1", "Grey","Перевозка на жемчужном самокате"},
                {"Мерс","Мрейс","улица Волхонка, 15, Москва", "Кропоткинская", "79117789515", "12.09.2024", "3", "Black","Перевозка на жемчужном самокате"},
        };
    }
    // Текст "заказ оформлен" через шапку
    @Test
    public void checkheaderOrderButton() {

        MainPageObject mainPage = new MainPageObject(driver);
        mainPage.setcookieButton();
        mainPage.headerOrderButton();

        OrderPageObject orderPage = new OrderPageObject(driver);
        orderPage.checkingOrderButton(firstName, lastName, address, station, phoneNum, rentalDate, rentalTime, color, comment);
        orderPage.checkingOrderStatus("Заказ оформлен");
    }
    // Тест "заказ оформлен" через середину сайта
    @Test
    public void checkmiddleOrderButton() {

        MainPageObject mainPage = new MainPageObject(driver);
        mainPage.setcookieButton();
        mainPage.middleOrderButton();

        OrderPageObject orderPage = new OrderPageObject(driver);
        orderPage.checkingOrderButton(firstName, lastName, address, station, phoneNum, rentalDate, rentalTime, color, comment);
        orderPage.checkingOrderStatus("Заказ оформлен");
    }
}
