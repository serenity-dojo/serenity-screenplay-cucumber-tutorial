package serenitydojo.swaglabs;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
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
 * Exercise 4
 */
@DisplayName("Making a purchase")
public class MakingAPurchaseTests {

    @Managed(driver = "chrome")//, options = "--headless -mode")
    WebDriver driver;

    Actor trudy = Actor.named("Trudy");

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Enter.theValue("standard_user").into(InputField.called("Username")),
                Enter.theValue("secret_sauce").into(InputField.called("Password")),
                Click.on(Link.called("Login"))
        );
    }

    @DisplayName("should be shown a confirmation screen after completing a purchase")
    @Test
    void completingAPurchase() {
        trudy.attemptsTo(
                Click.on(Button.called("Add to cart").inside(PageElement.called("inventory_item").containingText("Sauce Labs Backpack"))),
                Click.on(Link.called("shopping_cart_badge")),
                Click.on(Button.called("Checkout")),

                Enter.theValue("Sharon").into(InputField.withPlaceholder("First Name")),
                Enter.theValue("Shopper").into(InputField.withPlaceholder("Last Name")),
                Enter.theValue("XZY-123").into(InputField.withPlaceholder("Zip/Postal Code")),
                Click.on(Button.called("Continue")),

                Ensure.that(PageElement.called("cart_item").containingText("Sauce Labs Backpack")).isDisplayed(),

                Click.on(Button.called("Finish")),

                Ensure.that(PageElement.containingText("Thank you for your order")).isDisplayed()
        );
    }

    @DisplayName("should be shown a confirmation screen after completing a purchase 2")
    @Test
    void completingAPurchaseV2() {
        trudy.attemptsTo(
                addItemToCart("Sauce Labs Backpack"),
                checksOutBasket(),

                enterAddress(),

                Ensure.that(PageElement.called("cart_item").containingText("Sauce Labs Backpack")).isDisplayed(),

                completesPurchase(),

                Ensure.that(PageElement.containingText("Thank you for your order")).isDisplayed()
        );
    }

    Performable checksOutBasket() {
        return Task.where("{0} checks out the basket",
                Click.on(Link.called("shopping_cart_badge")),
                Click.on(Button.called("Checkout"))
        );
    }

    Performable completesPurchase() {
        return Task.where("{0} completes the purchase",
                Click.on(Button.called("Finish"))
        );
    }

    Performable addItemToCart(String itemName) {
        return Task.where("{0} adds the " + itemName + " to the shopping cart",
                Click.on(Button.called("Add to cart").inside(PageElement.called("inventory_item").containingText(itemName))),
                Click.on(Link.called("shopping_cart_badge")),
                Click.on(Button.called("Checkout"))
        );
    }

    Performable enterAddress() {
        return Task.where("{0} enters her address details",
                Enter.theValue("Sharon").into(InputField.withPlaceholder("First Name")),
                Enter.theValue("Shopper").into(InputField.withPlaceholder("Last Name")),
                Enter.theValue("XZY-123").into(InputField.withPlaceholder("Zip/Postal Code")),
                Click.on(Button.called("Continue"))
        );
    }
}
