package quru;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DzWebTestValueSource {
    @ValueSource(strings = {"Фильмы", "Сериалы"})
    @ParameterizedTest(name = "Убедиться, что есть в списке {0}")
    void selenideTest(String testData) {
        open("https://www.kinopoisk.ru/");
        $(".styles_sticky__mDnbt").shouldHave(text(testData));
    }
}


