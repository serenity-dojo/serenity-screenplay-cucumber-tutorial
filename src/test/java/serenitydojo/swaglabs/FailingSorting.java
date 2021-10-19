package serenitydojo.swaglabs;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.ui.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import serenitydojo.swaglabs.actions.Login;

@DisplayName("The products can be filtered with errors")
class FailingSorting {

    @CastMember
    Actor trudy;

    @Test
    public void shouldSortProducts() {
        trudy.attemptsTo(
                Login.as("standard_user", "secret_sauce"),
                Ensure.that(1).isEqualTo(2)
        );
    }

}
