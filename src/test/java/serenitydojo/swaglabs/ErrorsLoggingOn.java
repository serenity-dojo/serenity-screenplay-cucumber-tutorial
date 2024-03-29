package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Both should fail due to elements not found
 */
@DisplayName("Logging on to the Swaglabs site with errors")
@ExtendWith(SerenityJUnit5Extension.class)
public class ErrorsLoggingOn {

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
                Click.on(Button.called("FailToLogin")),
                Ensure.that(PageElement.locatedBy(".title").containingText("Products")).isDisplayed()
        );
    }

    @DisplayName("When a user provides incorrect credentials they should be refused access")
    @ParameterizedTest(name = "Logging on with username {0} and password {1}")
//    @CsvFileSource(resources = "/test-data/invalid_credentials_with_commas.csv")
    @CsvSource(textBlock = """
            standard_user   | wrong_password | Username and password do not match any user in this service
            unknown_user    | secret_sauce   | Username and password do not match any user in this service
            unknown_user    | wrong_password | Username and password do not match any user in this service
            locked_out_user | secret_sauce   | Sorry, this user has been locked out.
            """,
            delimiterString="|"
    )
    void loggingOnWithInvalidCredentials(String username, String password, String errorMessage) {
        trudy.attemptsTo(
                Enter.theValue(username).into(InputField.called("username")),
                Enter.theValue(password).into(InputField.called("password"))
        );
        if (username.equals("unknown_user")) {
            trudy.attemptsTo(Click.on(Button.called("FailToLogin")));
        } else {
            trudy.attemptsTo(Click.on(Button.called("Login")));
        }
        trudy.attemptsTo(
                Ensure.that(Text.of(PageElement.called("error-message-container"))).contains(errorMessage)
        );
    }
}
