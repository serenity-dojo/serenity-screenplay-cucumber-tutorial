package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.PageElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static serenitydojo.swaglabs.actions.PurchaseActions.*;

/**
 * Exercise 4
 */
@DisplayName("Making a purchase")
@ExtendWith(SerenityJUnit5Extension.class)
public class MakingAPurchaseTests {

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

    @DisplayName("should be shown a confirmation screen after completing a purchase")
    @Test
    void completingAPurchase() {
        trudy.attemptsTo(
                addItemToCart("Sauce Labs Backpack"),
                checkoutBasket,
                enterAddress,

                Ensure.that(PageElement.called("cart_item").containingText("Sauce Labs Backpack")).isDisplayed(),

                completePurchase,

                Ensure.that(PageElement.containingText("THANK YOU FOR YOUR ORDER")).isDisplayed()
        );
    }

}
