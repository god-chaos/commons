package net.dongliu.commons;

import org.junit.Test;

import static net.dongliu.commons.Objects2.toStringHelper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Objects2Test {

    @Test
    public void elvis() {
        assertEquals("1", Objects2.elvis("1", "2"));
        assertEquals("2", Objects2.elvis(null, "2"));
        assertEquals("1", Objects2.elvis("1", () -> "2"));
        assertEquals("2", Objects2.elvis(null, () -> "2"));
    }

    @Test(expected = NullPointerException.class)
    public void elvisException() {
        Objects2.elvis(null, (Object) null);
    }

    @Test(expected = NullPointerException.class)
    public void elvisException2() {
        Objects2.elvis(null, () -> null);
    }

    @Test
    public void TestToString() {
        assertEquals("null", toStringHelper(Object.class).toString(null));
        assertEquals("1", toStringHelper(Integer.class).toString(1));
        assertEquals("[1]", toStringHelper(int[].class).toString(new int[]{1}));
        assertEquals("[1]", toStringHelper(char[].class).toString(new char[]{'1'}));
        assertEquals("[1]", toStringHelper(String[].class).toString(new String[]{"1"}));
        // Object class is special, it does has toString method...
        assertTrue(toStringHelper(Object.class).toString(new Object()).contains("Object"));
        Object v = new Object() {
            private int i = 1;
        };
        assertEquals("Objects2Test$1{i=1}", toStringHelper(v.getClass()).toString(v));

        // this unit test may be broken, fix it if necessary
        assertEquals("ToStrTest{i=1, str=abcd, l=10, chars=[c, d]}", toStringHelper(ToStrTest.class)
                .toString(new ToStrTest()));

        Object v2 = new ToStrTest() {
            private int i = 5;
        };
        assertEquals("Objects2Test$2{i=5, str=abcd, l=10, chars=[c, d]}", toStringHelper(v2.getClass()).toString(v2));
    }

    private static class ToStrTest {
        private int i = 1;
        private String str = "abcd";
        private long l = 10L;
        private char[] chars = {'c', 'd'};
    }
}