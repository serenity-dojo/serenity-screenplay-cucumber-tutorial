package serenitydojo.swaglabs.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.ui.PageElement;

public class PurchaseActions {

    public static Performable addItemToCart(String itemName) {
        return Task.where("{0} adds the " + itemName + " to the shopping cart",
                Click.on(Button.called("Add to cart").inside(PageElement.called("inventory_item").containingText(itemName))),
                Click.on(PageElement.called("shopping_cart_badge")),
                Click.on(Button.called("Checkout"))
        );
    }

    public static final Performable checkoutBasket = Task.called("{0} checks out the cart").whereTheActorAttemptsTo(
                    Click.on(PageElement.called("shopping_cart_badge")),
                    Click.on(Button.called("Checkout"))
            );


    public static final Performable enterAddress =
        Task.called("{0} enters her address details").whereTheActorAttemptsTo(
                Enter.theValue("Sharon").into(InputField.withPlaceholder("First Name")),
                Enter.theValue("Shopper").into(InputField.withPlaceholder("Last Name")),
                Enter.theValue("XZY-123").into(InputField.withPlaceholder("Zip/Postal Code")),
                Click.on(Button.called("Continue"))
        );

    public static final Performable completePurchase = Task.called("{0} completes the purchase").whereTheActorAttemptsTo(Click.on(Button.called("Finish")));
}
