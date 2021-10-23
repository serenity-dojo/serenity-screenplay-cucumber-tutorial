package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityBDD;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.ui.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import serenitydojo.swaglabs.actions.Login;

@DisplayName("The products can be filtered")
@ExtendWith(SerenityJUnit5Extension.class)
class FilteringTests {

    @CastMember
    Actor trudy;

    @ParameterizedTest(name = "Filter displayed products in {0}")
    @CsvSource(textBlock = """
            alphabetical order         | Name (A to Z)       | Sauce Labs Backpack
            reverse alphabetical order | Name (Z to A)       | Test.allTheThings() T-Shirt (Red)
            ascending price            | Price (low to high) | Sauce Labs Onesie
            descending price           | Price (high to low) | Sauce Labs Fleece Jacket
            """, delimiterString = "|"
    )
    public void inASpecifiedOrder(String sortDescription, String sortOrder, String expectedFirstItem) {
        trudy.attemptsTo(
                Login.as("standard_user", "secret_sauce"),
                Select.option(sortOrder).from(Dropdown.called("product_sort_container")),
                Ensure.that(Text.of(PageElement.called("inventory_item_name"))).isEqualTo(expectedFirstItem)
        );
    }
}
