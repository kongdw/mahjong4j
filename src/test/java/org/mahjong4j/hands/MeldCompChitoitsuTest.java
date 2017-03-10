package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class MeldCompChitoitsuTest {
    private MeldDirectory actual;

    @Before
    public void setUp() throws Exception {
        List<Meld> toitsuList = new ArrayList<>(7);
        toitsuList.add(new Pair(W1));
        toitsuList.add(new Pair(D2));
        toitsuList.add(new Pair(D4));
        toitsuList.add(new Pair(D9));
        toitsuList.add(new Pair(RED));
        toitsuList.add(new Pair(SOUTH));
        toitsuList.add(new Pair(NORTH));

        actual = new MeldDirectory(toitsuList, NORTH);
    }

    @Test
    public void testGetJanto() throws Exception {
        assertNull(actual.getJanto());
    }

    @Test
    public void testGetToitsuList() throws Exception {
        assertThat(actual.getPairList(), hasItems(
            new Pair(SOUTH),
            new Pair(D4),
            new Pair(W1),
            new Pair(D9),
            new Pair(D2),
            new Pair(NORTH),
            new Pair(RED)
        ));
    }

    @Test
    public void testGetToitsuCount() throws Exception {
        assertEquals(7, actual.getToitsuCount());
    }

    @Test
    public void testGetShuntsuCount() throws Exception {
        assertEquals(0, actual.getShuntsuCount());
    }

    @Test
    public void testGetKotsuCount() throws Exception {
        assertEquals(0, actual.getKantsuCount());
    }

    @Test
    public void testGetKantsuCount() throws Exception {
        assertEquals(0, actual.getKantsuCount());
    }

    @Test
    public void testGetAllMentsu() throws Exception {
        List<Meld> actualAllMeld = actual.getAllMentsu();
        assertThat(actualAllMeld, hasItems((Meld) new Pair(W1)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(RED)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(SOUTH)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(D9)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(D4)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(D2)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(NORTH)));
    }

    @Test
    public void testEquals() throws Exception {
        List<Meld> expectedList = new ArrayList<>(7);
        expectedList.add(new Pair(W1));
        expectedList.add(new Pair(SOUTH));
        expectedList.add(new Pair(D9));
        expectedList.add(new Pair(D4));
        expectedList.add(new Pair(NORTH));
        expectedList.add(new Pair(D2));
        expectedList.add(new Pair(RED));

        MeldDirectory expected = new MeldDirectory(expectedList, NORTH);
        assertTrue(actual.equals(expected));
        assertEquals(actual.hashCode(), expected.hashCode());
    }
}