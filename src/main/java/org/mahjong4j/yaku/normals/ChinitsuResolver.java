package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.tile.TileType.WIND;
import static org.mahjong4j.tile.TileType.DRAGON;
import static org.mahjong4j.yaku.normals.NormalYaku.CHINITSU;

/**
 * 清一色判定クラス
 * 萬子、索子、筒子のどれか一種の牌だけで構成された場合成立
 *
 * @author yu1ro
 */
public class ChinitsuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = CHINITSU;
    private final MeldDirectory comp;

    public ChinitsuResolver(MeldDirectory comp) {
        this.comp = comp;
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        List<Meld> allMeld = comp.getAllMentsu();
        TileType firstType = allMeld.get(0).getTile().getType();

        if (firstType == WIND || firstType == DRAGON) {
            return false;
        }

        for (Meld meld : allMeld) {
            TileType checkType = meld.getTile().getType();
            if (firstType != checkType) {
                return false;
            }
        }

        return true;
    }
}
