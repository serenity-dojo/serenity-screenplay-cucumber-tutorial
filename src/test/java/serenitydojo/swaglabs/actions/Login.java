package serenitydojo.swaglabs.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;

public class Login {
    public static Performable as(String username, String password) {
        return Task.where("{0} logs in as " + username,
                Open.url("https://www.saucedemo.com/"),
                Enter.theValue(username).into(InputField.called("Username")),
                Enter.theValue(password).into(InputField.called("Password")),
                Click.on(Button.called("Login"))
        );
    }
}
