package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest {

    @Test
    public void shouldPlanningMeetAgain() {
        UserGenerator user = DataGenerator.generateUser(4);
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999/");

        SelenideElement form = $(".form");

        form.$("[data-test-id=city] input").val(user.getCity());
        form.$("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").val(user.getDate());
        form.$("[data-test-id=name] input").val(user.getName());
        form.$("[data-test-id=phone] input").val(user.getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$(byText("Запланировать")).click();
        $x("//div [@data-test-id=\"success-notification\"]").should(visible, Duration.ofSeconds(15));
        $x("//div [@data-test-id=\"success-notification\"]").should(text("Встреча успешно запланирована на " + user.getDate()));
        //Перепланируем дату
        $x("//input [@placeholder=\"Дата встречи\"]").doubleClick();
        $x("//input [@placeholder=\"Дата встречи\"]").sendKeys(Keys.BACK_SPACE);
        $x("//input [@placeholder=\"Дата встречи\"]").val(DataGenerator.generateDate(5));
        $x("//input [@placeholder=\"Дата встречи\"]").pressEnter();
        form.$(byText("Запланировать")).click();
        $x("//button[@class='button button_view_extra button_size_s button_theme_alfa-on-white']").click();
        $x("//div[@class = \"notification__content\"]").should(text(DataGenerator.generateDate(5)));
        $x(".//div [@data-test-id='success-notification']/button").click();
    }
}
