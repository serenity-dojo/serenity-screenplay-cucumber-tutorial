package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.ui.Select;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import serenitydojo.swaglabs.actions.Login;

@ExtendWith(SerenityJUnit5Extension.class)
class FailingFilteringUsingTestDataFromAFile {

    @CastMember
    Actor trudy;

    /**
     * The third row should fail
     */
    @ParameterizedTest(name = "Filter displayed products in order of {0}")
    @CsvFileSource(resources = "/test-data/filtering_products.csv", numLinesToSkip = 1)
    public void inASpecifiedOrder(String sortDescription, String sortOrder, String expectedFirstItem) {
        trudy.attemptsTo(
                Login.as("standard_user", "secret_sauce"),
                Select.option(sortOrder).from(Dropdown.called("product_sort_container")),
                Ensure.that(Text.of(PageElement.called("inventory_item_name"))).isEqualTo(expectedFirstItem)
        );
    }
}
