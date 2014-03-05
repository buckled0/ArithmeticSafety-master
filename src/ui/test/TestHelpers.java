package ui.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TestHelpers {
    public static void assertInstanceOf(Object obj, Class expected) {
        assertThat(obj, instanceOf(expected));
    }
}
