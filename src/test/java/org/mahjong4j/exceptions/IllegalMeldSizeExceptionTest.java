package org.mahjong4j.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class IllegalMeldSizeExceptionTest {
    private List<Meld> less;
    private List<Meld> more;

    @Before
    public void setUp() throws Exception {
        less = new ArrayList<>(4);
        less.add(new Pair(D7));
        less.add(new Sequence(false, T3));
        less.add(new Sequence(false, T3));
        less.add(new Triplet(false, SOUTH));

        more = new ArrayList<>(8);
        more.add(new Pair(D7));
        more.add(new Sequence(false, T3));
        more.add(new Sequence(false, T3));
        more.add(new Triplet(false, SOUTH));
        more.add(new Sequence(false, T3));
        more.add(new Sequence(false, T3));
        more.add(new Triplet(false, SOUTH));
    }

    @Test
    public void testSetLessMentsu() throws Exception {
        try {
            new MeldDirectory(less, T3);
        } catch (IllegalMentsuSizeException e) {
            assertEquals("面子の組が和了の形になっていません", e.getMessage());
            assertEquals("面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが4個の面子が見つかりました", e.getAdvice());
            assertThat(e.getMeldList(), hasItems(
                new Pair(D7),
                new Sequence(false, T3),
                new Triplet(false, SOUTH),
                new Sequence(false, T3)
            ));
        }
    }

    @Test
    public void testSetMoreMentsu() throws Exception {
        try {
            new MeldDirectory(more, T2);
        } catch (IllegalMentsuSizeException e) {
            assertEquals("面子の組が和了の形になっていません", e.getMessage());
            assertEquals("面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが7個の面子が見つかりました", e.getAdvice());
            assertThat(e.getMeldList(), hasItems(
                new Pair(D7),
                new Sequence(false, T3),
                new Triplet(false, SOUTH),
                new Sequence(false, T3),
                new Sequence(false, T3),
                new Triplet(false, SOUTH),
                new Sequence(false, T3)
            ));
        }
    }
}