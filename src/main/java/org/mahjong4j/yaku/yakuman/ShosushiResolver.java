package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;

import java.util.List;

import static org.mahjong4j.tile.TileType.WIND;
import static org.mahjong4j.yaku.yakuman.Yakuman.SHOSUSHI;

/**
 * 小四喜判定クラス
 * 4つの風牌東 南 西 北のうち3つを刻子1つを雀頭に含めて和了した時に成立
 * 4つのうち3つを刻子にし残る1つを雀頭にした場合
 *
 * @author yu1ro
 */
public class ShosushiResolver implements YakumanResolver {
    private final Yakuman yakuman = SHOSUSHI;
    private final Pair janto;
    private final List<Triplet> tripletList;
    private final int kotsuCount;

    public ShosushiResolver(MeldDirectory comp) {
        janto = comp.getJanto();
        tripletList = comp.getKotsuKantsu();
        kotsuCount = comp.getKotsuCount() + comp.getKantsuCount();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (janto == null) {
            return false;
        }

        if (janto.getTile().getType() != WIND) {
            return false;
        }
        if (kotsuCount < 3) {
            return false;
        }

        int shosushiCount = 0;
        for (Triplet triplet : tripletList) {
            if (triplet.getTile().getType() == WIND) {
                shosushiCount++;
            }
        }
        return shosushiCount == 3;
    }
}
