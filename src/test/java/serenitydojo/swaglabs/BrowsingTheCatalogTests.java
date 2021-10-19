package serenitydojo.swaglabs;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.SoftlyEnsure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.Image;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.ui.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import serenitydojo.swaglabs.actions.Login;

import java.util.Collection;

@DisplayName("When browsing the catalog")
class BrowsingTheCatalogTests {

    @CastMember
    Actor trudy;

    @BeforeEach
    void openTheBrowser() {
        trudy.attemptsTo(
                Open.url("https://www.saucedemo.com/"),
                Login.as("standard_user", "secret_sauce")
        );
    }

    @Nested
    @DisplayName("The products displayed")
    class TheListOfProducts {

        @Test
        @DisplayName("Should include the most popular product")
        public void shouldIncludeOurMostPopularProduct() {
            trudy.attemptsTo(
                    Ensure.that(PageElement.called("inventory_item").containingText("Sauce Labs Backpack")).isDisplayed()
            );
        }

        @Test
        @DisplayName("Should show six products")
        public void shouldIncludeSixProducts() {
            trudy.attemptsTo(
                    Ensure.that(Text.ofEach(PageElement.called("inventory_item"))).hasSize(6)
            );
        }

        private final Target inventoryItemCalled(String productName) {
            return PageElement.called("inventory_item").containingText(productName);
        }

        @Test
        @DisplayName("Should show the price, description and image of each product")
        public void shouldShowPricesForEachProduct() {
            Collection<String> displayedProducts = trudy.asksFor(Text.ofEach(PageElement.called("inventory_item_name")));

            trudy.attemptsTo(SoftlyEnsure.start());
            displayedProducts.forEach(
                    displayedProduct ->
                            trudy.attemptsTo(
                                    Ensure.that(Text.ofEach(PageElement.called("inventory_item_price").inside(inventoryItemCalled(displayedProduct)))).isNotEmpty(),
                                    Ensure.that(Text.ofEach(PageElement.called("inventory_item_desc ").inside(inventoryItemCalled(displayedProduct)))).isNotEmpty(),
                                    Ensure.that(Image.withAltText(displayedProduct)).isDisplayed()
                            )
            );
            trudy.attemptsTo(SoftlyEnsure.finish());
        }
    }

    @Nested
    @DisplayName("The products can be filtered")
    class ProductsCanBeFiltered {

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
                    Select.option(sortOrder).from(Dropdown.called("product_sort_container")),
                    Ensure.that(Text.of(PageElement.called("inventory_item_name"))).isEqualTo(expectedFirstItem)
            );
        }
    }
}