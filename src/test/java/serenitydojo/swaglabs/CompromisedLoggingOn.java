package serenitydojo.swaglabs;

import net.serenitybdd.core.exceptions.TestCompromisedException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import net.serenitybdd.screenplay.ui.PageElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Both should fail due to elements not found
 */
@DisplayName("Logging on to the Swaglabs site with errors")
public class CompromisedLoggingOn {

    @CastMember
    Actor trudy;

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(Open.url("https://www.saucedemo.com/"));
    }

    @DisplayName("When a user enters correct credentials they should be taken to the product page")
    @Test
    void loggingOnWithValidCredentials() {
        trudy.attemptsTo(
                Enter.theValue("standard_user").into(InputField.called("Username")),
                Enter.theValue("secret_sauce").into(InputField.called("Password")),
                Click.on(Button.called("Login")),
                Ensure.that(PageElement.locatedBy(".title").containingText("Product")).isDisplayed().orElseThrow(new TestCompromisedException("Wrong page"))
        );
    }
}
