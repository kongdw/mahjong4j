package org.mahjong4j.exceptions;

import org.junit.Test;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.Player;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.mahjong4j.tile.Tile.W2;
import static org.mahjong4j.tile.Tile.D7;

/**
 * TODO : lastの牌が無い場合も
 *
 * @author yu1ro
 */
public class MahjongTileOverFlowExceptionTest {

    @Test(expected = MahjongTileOverFlowException.class)
    public void testManySameMentsu() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = W2;
        Hands hands = new Hands(tiles, last, new Triplet(true, D7), new Triplet(true, D7), new Triplet(true, D7), new Triplet(true, D7));
        new Player(hands);
    }

    @Test(expected = MahjongTileOverFlowException.class)
    public void testManySameMentsuList() throws Exception {
        int[] tiles = {
            0, 2, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Tile last = W2;
        List<Meld> meldList = new ArrayList<>(4);
        meldList.add(new Triplet(true, D7));
        meldList.add(new Triplet(true, D7));
        meldList.add(new Triplet(true, D7));
        meldList.add(new Triplet(true, D7));
        Hands hands = new Hands(tiles, last, meldList);
        new Player(hands);
    }
}