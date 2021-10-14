package serenitydojo.todomvc;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.ui.InputField;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

@DisplayName("Opening The Application")
public class NavigationTests {

    @Managed(driver = "chrome")
    WebDriver driver;

    Actor trudy = Actor.named("Trudy");

    @BeforeEach
    void openTheBrowser() {
        trudy.can(BrowseTheWeb.with(driver));
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
