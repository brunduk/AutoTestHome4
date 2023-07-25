package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
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

    //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //String date = format.format(LocalDate.now().plusDays(3));
    public String date(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    String planningDate = date(3, "dd.MM.yyyy");

    @Test
    void OrderDeliveryCard() {

        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id='date'] input").doubleClick().press(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79211234567");
        $(".checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

 //   @Test
  //  void OrderDeliveryCardChangeCity() {

 //      $("[data-test-id='city'] input").setValue("Ка");
  //      $$(".menu-item__control").find(exactText("Калининград")).click();
  //      $("[data-test-id='date'] input").doubleClick().press(Keys.BACK_SPACE);
  //      $("[data-test-id='date'] input").setValue(planningDate);
  //      $("[data-test-id='name'] input").setValue("Иванов Иван");
  //      $("[data-test-id='phone'] input").setValue("+79211234567");
  //      $(".checkbox__box").click();
  //      $(".button").click();
  //      $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

  //  }

  //  @Test
  //  void OrderDeliveryCardChangeCalendar() {

 //       $("[data-test-id='city'] input").setValue("Уфа");
  //      $(".icon-button__text").click();
 //       $$(".calendar__arrow_direction_right").last().click();
 //       $(byText("3")).click();
 //       $("[data-test-id='name'] input").setValue("Иванов Иван");
 //       $("[data-test-id='phone'] input").setValue("+79211234567");
 //       $(".checkbox__box").click();
  //      $(".button").click();
 //       $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

  //  }
}
