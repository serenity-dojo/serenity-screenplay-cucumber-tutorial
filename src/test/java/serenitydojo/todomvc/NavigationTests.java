package serenitydojo.todomvc;

import net.serenitybdd.junit5.SerenityBDD;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.ui.InputField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SerenityBDD
@DisplayName("Opening The Application")
@ExtendWith(SerenityJUnit5Extension.class)
public class NavigationTests {

    @CastMember
    Actor trudy;

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(Open.url("https://todomvc.com/examples/angularjs/#/"));
    }


    @DisplayName("Should show the application title")
    @Test
    void showCorrectTitle() {
        trudy.attemptsTo(
                Ensure.that(TheWebPage.title()).contains("TodoMVC")
        );
    }

    @DisplayName("Should show a helpful prompt")
    @Test
    void showCorrectPrompt() {
        trudy.attemptsTo(
                Ensure.that(InputField.withPlaceholder("What needs to be done?")).isDisplayed()
        );
    }
}
