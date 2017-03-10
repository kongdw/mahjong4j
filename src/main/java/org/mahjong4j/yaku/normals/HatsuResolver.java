package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.tile.Tile;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HATSU;

/**
 * 發判定クラス
 * 發の刻子もしくは槓子が含まれる場合成立
 *
 * @author yu1ro
 */
public class HatsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HATSU;
    private final List<Triplet> tripletList;

    public HatsuResolver(MeldDirectory comp) {
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Triplet triplet : tripletList) {
            if (triplet.getTile() == Tile.GREEN) {
                return true;
            }
        }

        return false;
    }
}
