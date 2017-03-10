package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;
import org.mahjong4j.tile.Tile;

import java.util.List;

import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.RYUISO;

/**
 * 緑一色判定クラス
 * 全ての牌が緑色の場合成立
 * すなわち索子の23468 發のみの場合成立
 *
 * @author yu1ro
 */
public class RyuisoResolver implements YakumanResolver {
    private final Yakuman yakuman = RYUISO;
    private final List<Pair> pairList;
    private final List<Sequence> sequenceList;
    private final List<Triplet> tripletList;

    public RyuisoResolver(MeldDirectory hands) {
        pairList = hands.getPairList();
        sequenceList = hands.getSequenceList();
        tripletList = hands.getKotsuKantsu();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        for (Pair pair : pairList) {
            if (!isGreen(pair.getTile())) {
                return false;
            }
        }
        for (Triplet triplet : tripletList) {
            if (!isGreen(triplet.getTile())) {
                return false;
            }
        }

        for (Sequence sequence : sequenceList) {
            if (sequence.getTile() != T3) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param tile 評価する牌
     * @return 緑の牌かどうか
     */
    private boolean isGreen(Tile tile) {
        return tile == GREEN
            || tile == T2
            || tile == T3
            || tile == T4
            || tile == T6
            || tile == T8;
    }
}
