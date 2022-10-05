package quru;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DzWebTestMethodSource {

    static Stream<Arguments> kinoTest() {
        return Stream.of(
                Arguments.of("Фильмы", List.of("Фильмы", "Онлайн-кинотеатр", "Жанры", "Страны", "Годы", "Критика", "Сериалы", "Сборы", "Премии", "Направления")),
                Arguments.of("Сериалы", List.of("Фильмы", "Онлайн-кинотеатр", "Жанры", "Страны", "Годы", "Критика", "Сериалы", "Сборы", "Премии", "Направления"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Punkt {0} Knopki {1}")
    void kinoTest(String menu, List<String> expectedButtons) {
        open("https://www.kinopoisk.ru/");
        $(".styles_sticky__mDnbt").shouldHave(text(menu)).click();
        $$(".data-tid = 9b0445d1").shouldHave(CollectionCondition.texts(expectedButtons));


    }
}