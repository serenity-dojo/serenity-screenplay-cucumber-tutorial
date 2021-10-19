package serenitydojo.swaglabs.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;

public class Navigate {
    public static Performable toTheProductCatalog() {
        return Open.url("https://www.saucedemo.com/inventory.html");
    }
}
