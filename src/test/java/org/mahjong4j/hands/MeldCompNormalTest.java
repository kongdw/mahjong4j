package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class MeldCompNormalTest {
    private MeldDirectory actual;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(D5));
        meldList.add(new Sequence(false, D2));
        meldList.add(new Sequence(true, T3));
        meldList.add(new Triplet(false, SOUTH));
        meldList.add(new Kong(false, RED));

        actual = new MeldDirectory(meldList, SOUTH);
    }

    @Test
    public void testGetJanto() throws Exception {
        assertEquals(new Pair(D5, D5), actual.getJanto());
        assertNotEquals(new Pair(D5, T4), actual.getJanto());
    }

    @Test
    public void testGetToitsuList() throws Exception {
        assertThat(actual.getPairList(), hasItems(new Pair(D5)));
    }

    @Test
    public void testGetToitsuCount() throws Exception {
        assertEquals(1, actual.getToitsuCount());
    }

    @Test
    public void testGetShuntsuList() throws Exception {
        assertThat(actual.getSequenceList(), hasItems(new Sequence(true, T3), new Sequence(false, D2)));
    }

    @Test
    public void testGetShuntsuCount() throws Exception {
        assertEquals(2, actual.getShuntsuCount());
    }

    @Test
    public void testGetKotsuList() throws Exception {
        assertThat(actual.getTripletList(), hasItems(new Triplet(false, SOUTH)));
    }

    @Test
    public void testGetKotsuKantsu() throws Exception {
        assertThat(actual.getKotsuKantsu(), hasItems(new Triplet(false, SOUTH)));
        assertThat(actual.getKotsuKantsu(), hasItems(new Triplet(false, RED)));

        //刻子の数はそのまま
        assertEquals(1, actual.getKotsuCount());
    }

    @Test
    public void testGetAllMentsu() throws Exception {
        List<Meld> actualAllMeld = actual.getAllMentsu();
        assertThat(actualAllMeld, hasItems((Meld) new Triplet(false, SOUTH)));
        assertThat(actualAllMeld, hasItems((Meld) new Sequence(true, T3)));
        assertThat(actualAllMeld, hasItems((Meld) new Kong(false, RED)));
        assertThat(actualAllMeld, hasItems((Meld) new Sequence(false, D2)));
        assertThat(actualAllMeld, hasItems((Meld) new Pair(D5)));

    }

    @Test
    public void testGetKotsuCount() throws Exception {
        assertEquals(1, actual.getKotsuCount());
    }

    @Test
    public void testGetKantsuList() throws Exception {
        assertThat(actual.getKongList(), hasItems(new Kong(false, RED)));
    }

    @Test
    public void testGetKantsuCount() throws Exception {
        assertEquals(1, actual.getKantsuCount());
    }

    @Test
    public void testEquals() throws Exception {
        List<Meld> trList = new ArrayList<>(5);
        trList.add(new Pair(D5));
        trList.add(new Sequence(true, T3));
        trList.add(new Triplet(false, SOUTH));
        trList.add(new Kong(false, RED));
        trList.add(new Sequence(false, D2));


        MeldDirectory tr = new MeldDirectory(trList, SOUTH);
        assertTrue(actual.equals(tr));
        assertEquals(actual.hashCode(), tr.hashCode());
    }

    @Test
    public void testNotEquals() throws Exception {
        List<Meld> flsList = new ArrayList<>(5);
        flsList.add(new Pair(D5));
        flsList.add(new Triplet(false, SOUTH));
        flsList.add(new Sequence(false, D2));
        flsList.add(new Kong(true, RED));
        flsList.add(new Sequence(true, T3));

        MeldDirectory fls = new MeldDirectory(flsList, T4);

        assertFalse(actual.equals(fls));
        assertNotEquals(actual.hashCode(), fls.hashCode());
    }
}