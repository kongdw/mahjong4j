package org.mahjong4j.hands;

import org.junit.Test;
import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.IllegalShuntsuIdentifierException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;

import static org.junit.Assert.assertEquals;
import static org.mahjong4j.tile.Tile.W1;
import static org.mahjong4j.tile.Tile.EAST;

/**
 * @author yu1ro
 */
public class HandsThrowsTest {

    @Test(expected = HandsOverFlowException.class)
    public void testTahai() throws Exception {
        int[] tiles = {
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 2, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        new Hands(tiles, W1);
    }

    @Test
    public void testCatchException() throws Exception {
        int[] tiles = {
            1, 5, 1, 1, 1, 1, 1, 0, 0,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };

        try {
            new Hands(tiles, Tile.W6);
        } catch (MahjongTileOverFlowException e) {//messageもテストするためにcatch
            assertEquals("麻雀の牌は4枚までしかありません", e.getMessage());
            assertEquals("W2(code = 1)が5枚見つかりました", e.getAdvice());
        }
    }

    @Test
    public void testIllegalShuntsu() throws Exception {
        String advice = "M1を識別牌として保存しようとしました\n"
            + "2番目の牌を順子識別牌とするため、1・9牌は識別牌になりえません";
        shuntsuCheck(W1, advice);
    }

    @Test
    public void testIllegalShuntsuJihai() throws Exception {
        String advice = "TONを識別牌として保存しようとしました\n"
            + "字牌は順子になりえません";
        shuntsuCheck(EAST, advice);
    }

    private void shuntsuCheck(Tile a, String expected) {
        try {
            new Sequence(true, a);
        } catch (IllegalShuntsuIdentifierException e) {
            assertEquals("順子識別牌としてありえない牌を検出しました", e.getMessage());

            assertEquals(expected, e.getAdvice());
        }
    }
}