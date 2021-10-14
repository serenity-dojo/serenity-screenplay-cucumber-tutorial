package serenitydojo.swaglabs;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.ui.PageElement;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

/**
 * Exercise 2
 */
@DisplayName("Browsing the Swaglabs catalog")
public class BrowsingTheCatalogTests {

    @Managed(driver = "chrome")//, options = "--headless -mode")
    WebDriver driver;

    Actor trudy = Actor.named("Trudy");

    @BeforeEach
    void openTheBrowser() {
        trudy.can(BrowseTheWeb.with(driver));
        trudy.attemptsTo(Open.url("https://www.saucedemo.com/"));
        trudy.attemptsTo(
                Enter.theValue("standard_user").into(InputField.called("Username")),
                Enter.theValue("secret_sauce").into(InputField.called("Password")),
                Click.on(Button.called("Login"))
        );
    }

    @DisplayName("The catalog should show our top product")
    @Test
    void shouldSeeOurTopProduct() {
        trudy.attemptsTo(
            Ensure.that(PageElement.called("inventory_item").containingText("Sauce Labs Backpack")).isDisplayed()
        );
    }

    @DisplayName("Users can view the product details")
    @Test
    void showProductDetails() {
        trudy.attemptsTo(
                Click.on(Link.called("Sauce Labs Backpack")),
                Ensure.that(PageElement.called("inventory_details_name")).text().isEqualToIgnoringCase("Sauce Labs Backpack"),
                Ensure.that(PageElement.called("inventory_details_price")).hasTextContent("$29.99")
        );
    }

}
