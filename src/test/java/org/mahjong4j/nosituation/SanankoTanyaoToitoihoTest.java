package org.mahjong4j.nosituation;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Triplet;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.normals.NormalYaku;
import org.mahjong4j.yaku.yakuman.Yakuman;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.mahjong4j.Score.SCORE0;
import static org.mahjong4j.tile.Tile.W4;
import static org.mahjong4j.tile.Tile.D7;
import static org.mahjong4j.yaku.normals.NormalYaku.*;

/**
 * @author yu1ro
 */
public class SanankoTanyaoToitoihoTest {
    private Player player;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 3, 0, 0, 0,
            0, 0, 3, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 3, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = W4;
        Hands hands = new Hands(tiles, last, new Triplet(true, D7));
        player = new Player(hands);
        player.calculate();
    }

    @Test
    public void testGetYakumanList() throws Exception {
        List<Yakuman> actual = player.getYakumanList();

        assertEquals(0, actual.size());
    }

    @Test
    public void testGetNormalYakuListSize() throws Exception {
        int actualSize = player.getNormalYakuList().size();

        assertEquals(3, actualSize);
    }

    @Test
    public void testGetNormalYakuListHasSanshokudohko() throws Exception {
        List<NormalYaku> actual = player.getNormalYakuList();

        assertThat(actual, hasItems(SANANKO, TANYAO, TOITOIHO));
    }

    @Test
    public void testGetFu() throws Exception {
        assertEquals(20, player.getFu());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(SCORE0, player.getScore());
    }
}