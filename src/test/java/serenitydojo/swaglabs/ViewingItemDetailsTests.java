package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.ui.PageElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import serenitydojo.swaglabs.actions.Login;

@DisplayName("Viewing item details")
@ExtendWith(SerenityJUnit5Extension.class)
public class ViewingItemDetailsTests {

    @CastMember
    Actor trudy;

    @DisplayName("Users can view the details of each product")
    @ParameterizedTest(name = "Viewing product details for {0}")
    @CsvSource(textBlock = """
            Sauce Labs Backpack      | $29.99 | carry.allTheThings()                                                         |
            Sauce Labs Bike Light    | $9.99  | A red light isn't the desired state in testing but it sure helps when riding |
            Sauce Labs Bolt T-Shirt  | $15.99 | Get your testing superhero                                                   |
            Sauce Labs Fleece Jacket | $49.99 | a midweight quarter-zip fleece jacket                                        | 
            """,
            delimiterString = "|"
    )
    public void showProductDetails(String productName, String expectedPrice, String expectedDescription) {
        trudy.attemptsTo(
                Login.as("standard_user", "secret_sauce"),
                Click.on(Link.called(productName)),
                Ensure.that(PageElement.called("inventory_details_name")).text().isEqualToIgnoringCase(productName),
                Ensure.that(PageElement.called("inventory_details_price")).hasTextContent(expectedPrice),
                Ensure.that(PageElement.called("inventory_details_desc")).text().contains(expectedDescription)
        );
    }

}
