package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.SHOSANGEN;

/**
 * 小三元判定クラス
 * 三元牌のいずれかを雀頭とし、残り2つを刻子もしくは槓子にすることで成立
 *
 * @author yu1ro
 */
public class ShosangenResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SHOSANGEN;

    private final Pair janto;
    private final List<Triplet> tripletList;

    public ShosangenResolver(MeldDirectory comp) {
        janto = comp.getJanto();
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        //七対子の場合はありえないのでfalse
        if (janto == null) {
            return false;
        }

        if (janto.getTile().getType() != TileType.DRAGON) {
            return false;
        }
        int count = 0;
        for (Triplet triplet : tripletList) {
            if (triplet.getTile().getType() == TileType.DRAGON) {
                count++;
            }
            if (count == 2) {
                return true;
            }
        }

        return false;
    }
}
