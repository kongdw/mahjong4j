package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.tile.Tile;

import java.util.List;

/**
 * 中判定クラス
 * 中の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class ChunResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = NormalYaku.CHUN;
    private final List<Triplet> tripletList;

    public ChunResolver(MeldDirectory comp) {
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Triplet triplet : tripletList) {
            if (triplet.getTile() == Tile.RED) {
                return true;
            }
        }
        return false;
    }
}
