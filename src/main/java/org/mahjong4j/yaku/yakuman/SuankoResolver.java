package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.Yakuman.SUANKO;

/**
 * 四暗刻判定クラス
 * 暗刻を4つ作って和了した場合成立
 * 暗槓が含まれても良い
 *
 * @author yu1ro
 */
public class SuankoResolver implements YakumanResolver {
    private final int count;
    private final List<Triplet> tripletList;
    private final Yakuman yakuman = SUANKO;

    public SuankoResolver(MeldDirectory comp) {
        tripletList = comp.getKotsuKantsu();
        count = comp.getKotsuCount() + comp.getKantsuCount();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (count < 4) {
            return false;
        }
        for (Triplet triplet : tripletList) {
            if (triplet.isOpen()) {
                return false;
            }
        }

        return true;
    }
}
