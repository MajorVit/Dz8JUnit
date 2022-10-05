package quru;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class DzWebTestCsvSource {
    @BeforeAll
    static void configure() {
        Configuration.holdBrowserOpen = true;
    }

        @CsvSource(value = {
                "Hello, Здравствуйте",
                "Bye, Пока"
        }
        )
        @ParameterizedTest(name = "При написании слова {0} выдаст перевод {1}")
        void translate(String testData, String exceptionData) {
            open("https://translate.yandex.ru");
            $("#fakeArea").setValue(testData);
            $("#translation").shouldHave(text(exceptionData));
        }
}