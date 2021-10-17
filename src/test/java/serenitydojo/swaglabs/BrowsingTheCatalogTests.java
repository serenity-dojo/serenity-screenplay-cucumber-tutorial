package serenitydojo.swaglabs;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.ui.PageElement;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenitydojo.swaglabs.actions.Login;

/**
 * Exercise 2
 */
@RunWith(SerenityRunner.class)
public class BrowsingTheCatalogTests {

    @Managed(driver = "chrome")//, options = "--headless -mode")
    WebDriver driver;

    @CastMember
    Actor trudy;

    @Before
    public void openTheBrowser() {
        trudy.attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Login.as("standard_user","secret_sauce")
        );
//        trudy.attemptsTo(
//                Enter.theValue("standard_user").into(InputField.called("Username")),
//                Enter.theValue("secret_sauce").into(InputField.called("Password")),
//                Click.on(Button.called("Login"))
//        );
    }

    @Title("The catalog should show our top product")
    @Test
    public void shouldSeeOurTopProduct() {
        trudy.attemptsTo(
            Ensure.that(PageElement.called("inventory_item").containingText("Sauce Labs Backpack")).isDisplayed()
        );
    }

//    @DisplayName("Users can view the product details")
    @Test
    @Title("Users can view the product details")
    public void showProductDetails() {
        trudy.attemptsTo(
                Click.on(Link.called("Sauce Labs Backpack")),
                Ensure.that(PageElement.called("inventory_details_name")).text().isEqualToIgnoringCase("Sauce Labs Backpack"),
                Ensure.that(PageElement.called("inventory_details_price")).hasTextContent("$29.99")
        );
    }

}
