package net.dongliu.commons.collection;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapsTest {

    @Test
    public void of() {
        Map<String, Integer> map = Maps.ofEntries(Pair.of("1", 1), Pair.of("2", 2));
        assertEquals(2, map.size());
        assertEquals(1, (int) map.get("1"));
    }
}