package serenitydojo.todomvc;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.InputField;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

@DisplayName("Adding tasks to the list")
@ExtendWith(SerenityJUnit5Extension.class)
public class AddingNewTaskTests {

    private static final Target NEW_TODO = InputField.withPlaceholder("What needs to be done?");

    @CastMember
    Actor trudy = Actor.named("Trudy");

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(Open.url("https://todomvc.com/examples/angularjs/#/"));
    }

    @DisplayName("Adding a single item to the list")
    @Test
    void addASingleItem() {
        trudy.attemptsTo(
                Enter.theValue("Feed the cat", Keys.ENTER).into(NEW_TODO),

                Ensure.that(Text.ofEach(".todo-list li")).contains("Feed the cat")
        );
    }


    @DisplayName("Adding several items to the list")
    @Test
    void addSeveralItems() {
        trudy.attemptsTo(
                Enter.theValue("Feed the cat", Keys.ENTER).into(NEW_TODO),
                Enter.theValue("Walk the dog", Keys.ENTER).into(NEW_TODO),

                Ensure.that(Text.ofEach(".todo-list li")).contains("Feed the cat","Walk the dog")
        );
    }
}
