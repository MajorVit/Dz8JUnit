package quru;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class WebTest {

    //@Disabled("")
    @ValueSource(strings = {"Selenide", "Allure"})
    //@DisplayName("���������� ������ �� ������ ��� ������� 'Selenide'")
    @ParameterizedTest(name = "���������� ������ �� ������ ��� ������� {0}")
    void selenideTest(String testData) {
        open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvSource(value = {
             "Selenide, '��� ��������� ��� ������������������� ������������ ���-����������",
            "Allure java, -framework ������� ����������� � ������ ��������������",
    })
    @ParameterizedTest(name = "���������� ������ �������� ����� \"{1}\" ��� ������� \"{0}\"")
    void commonComplexSearchTest(String testData, String expectedResult) {
        open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").filter(not(text("�������")))
                .first()
                .shouldHave(text(expectedResult));
    }

    static Stream<Arguments> forSelenideSiteMenuTest() {
       return Stream.of(
               Arguments.of("RU", List.of("� ���� ������?", "���", "����", "����", "Javadoc", "������������","������") ),
               Arguments.of("EN", List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
       );
    }

    @MethodSource("forSelenideSiteMenuTest")
    @ParameterizedTest(name = "��� ������ {0} ������������ ������ {1}")
    void selenideSiteMenuTest(String lang, List<String> expectedButtons) {
        open("https://selenide.org/");
        $$("#languages a").find(text(lang)).click();
        $$(".main-menu-pages a")
                .filter(visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));

    }
}
