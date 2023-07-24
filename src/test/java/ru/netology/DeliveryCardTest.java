package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withDate;
import static com.codeborne.selenide.SetValueOptions.withText;

public class DeliveryCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String date = format.format(LocalDate.now().plusDays(3));

    @Test
    void OrderDeliveryCard() {

        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id='date'] input").doubleClick().press("backspace");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79211234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void OrderDeliveryCardChangeCity() {

        $("[data-test-id='city'] input").setValue("Ка");
        $$(".menu-item__control").find(exactText("Калининград")).click();
        $("[data-test-id='date'] input").doubleClick().press("backspace");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79211234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void OrderDeliveryCardChangeCalendar() {

        $("[data-test-id='city'] input").setValue("Уфа");
        $(".icon-button__text").click();
        $$(".calendar__arrow_direction_right").last().click();
        $(byText("3")).click();
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79211234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}
