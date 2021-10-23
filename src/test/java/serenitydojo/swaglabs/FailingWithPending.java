package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityBDD;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Pending;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import serenitydojo.swaglabs.actions.Login;

@ExtendWith(SerenityJUnit5Extension.class)
class FailingWithPending {

    @CastMember
    Actor trudy;

    @Test
    @Pending
    public void shouldBePending() {
        trudy.attemptsTo(
                Login.as("standard_user", "secret_sauce"),
                Ensure.that(1).isEqualTo(2)
        );
    }

}
