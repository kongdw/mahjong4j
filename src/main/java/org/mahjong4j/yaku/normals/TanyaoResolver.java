package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.TANYAO;

/**
 * 断么九判定クラス
 * 么九牌（一九字牌）を一切使わず、中張牌（数牌の2〜8）のみを使って手牌を完成させた場合に成立
 *
 * @author yu1ro
 */
public class TanyaoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = TANYAO;
    private final List<Meld> allMeld;

    public TanyaoResolver(MeldDirectory comp) {
        allMeld = comp.getAllMentsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        for (Meld meld : allMeld) {
            int number = meld.getTile().getNumber();
            if (number == 0 || number == 1 || number == 9) {
                return false;
            }

            int shuntsuNum = meld.getTile().getNumber();
            boolean isEdgeShuntsu = (shuntsuNum == 2 || shuntsuNum == 8);
            if (meld instanceof Sequence && isEdgeShuntsu) {
                return false;
            }
        }

        return true;
    }
}
