package serenitydojo.swaglabs;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
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
 * Exercise 3
 */
@DisplayName("Managing the shopping cart")
public class ShoppingCartTests {

    @Managed(driver = "chrome")//, options = "--headless -mode")
    WebDriver driver;

    @CastMember
    Actor trudy;

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Enter.theValue("standard_user").into(InputField.called("Username")),
                Enter.theValue("secret_sauce").into(InputField.called("Password")),
                Click.on(Button.called("Login"))
        );
    }

    @DisplayName("Adding a single item to the cart")
    @Test
    void addingOneItemToTheCart() {
        trudy.attemptsTo(
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Backpack"))),

                Ensure.that(Text.of(PageElement.called("shopping_cart_badge")).asInteger()).isEqualTo(1)
        );
    }

    @DisplayName("Adding several items to the cart")
    @Test
    void addingSeveralItemsToTheCart() {
        trudy.attemptsTo(
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Backpack"))),
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Bike Light"))),

                Ensure.that(Text.of(PageElement.called("shopping_cart_badge")).asInteger()).isEqualTo(2)
        );
    }

    @DisplayName("Removing an item from the cart")
    @Test
    void removingItemsFromTheCart() {
        trudy.attemptsTo(
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Backpack"))),
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Bike Light"))),

                Click.on(Button.called("Remove")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Bike Light"))),

                Ensure.that(Text.of(PageElement.called("shopping_cart_badge")).asInteger()).isEqualTo(1)
        );
    }

    @DisplayName("Users can add a product to the cart from the product details")
    @Test
    void addProductToCartFromDetails() {
        trudy.attemptsTo(
                Click.on(Link.called("Sauce Labs Backpack")),
                Click.on(Button.called("Add to cart")),
                Ensure.that(Text.of(PageElement.called("shopping_cart_badge")).asInteger()).isEqualTo(1)
        );
    }

    @DisplayName("Viewing items in the cart")
    @Test
    void viewingItemsInTheCart() {
        trudy.attemptsTo(
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Backpack"))),
                Click.on(Button.called("Add to cart")
                        .inside(PageElement.called("inventory_item").containingText("Sauce Labs Bike Light"))),

                Click.on(PageElement.called("shopping_cart_badge")),

                // All the selected items should be displayed
                Ensure.that(Text.ofEach(PageElement.called("inventory_item_name")))
                                .contains("Sauce Labs Backpack", "Sauce Labs Bike Light")

        );
    }
}
