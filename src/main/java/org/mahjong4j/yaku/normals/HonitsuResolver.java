package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HONITSU;

/**
 * 混一色判定クラス
 * 萬子、索子、筒子のどれか一種と、字牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonitsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HONITSU;

    private List<Meld> allMeld;

    private boolean hasJihai = false;
    private TileType type = null;

    public HonitsuResolver(MeldDirectory comp) {
        allMeld = comp.getAllMentsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Meld meld : allMeld) {
            if (!hasOnlyOneType(meld)) {
                return false;
            }
        }

        return hasJihai;
    }

    private boolean hasOnlyOneType(Meld meld) {
        if (meld.getTile().getNumber() == 0) {
            hasJihai = true;
        } else if (type == null) {
            type = meld.getTile().getType();
        } else if (type != meld.getTile().getType()) {
            return false;
        }
        return true;
    }
}
