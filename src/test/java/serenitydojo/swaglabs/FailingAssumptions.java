package serenitydojo.swaglabs;

import net.serenitybdd.junit5.SerenityBDD;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.assertj.core.api.Assertions;
import org.junit.Assume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SerenityBDD
@ExtendWith(SerenityJUnit5Extension.class)
public class FailingAssumptions {

    @Test
    void withFailingAssumption() {
        Assume.assumeTrue(false);
    }

    @Test
    void withFailingAssertion() {
        Assertions.assertThat(true).isFalse();
    }

    @Test
    void withBrokenTest() {
        int i = 0;
        int j = i / i;
    }
}
