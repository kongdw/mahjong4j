package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;

import java.util.List;

import static org.mahjong4j.tile.Tile.WHITE;
import static org.mahjong4j.yaku.normals.NormalYaku.HAKU;

/**
 * 白判定クラス
 * 白の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HakuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HAKU;
    private final List<Triplet> tripletList;

    public HakuResolver(MeldDirectory comp) {
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Triplet triplet : tripletList) {
            if (triplet.getTile() == WHITE) {
                return true;
            }
        }
        return false;
    }

}