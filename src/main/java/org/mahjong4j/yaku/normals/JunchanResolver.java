package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.JUNCHAN;

/**
 * 純チャン判定クラス
 * 123の順子と789の順子、および111、999といった老頭牌の刻子もしくは槓子
 * のみの場合に成立
 *
 * @author yu1ro
 */
public class JunchanResolver implements NormalYakuResolver {
    private final Pair janto;
    private final List<Sequence> sequenceList;
    private final List<Triplet> tripletList;
    private NormalYaku yakuEnum = JUNCHAN;

    public JunchanResolver(MeldDirectory comp) {
        janto = comp.getJanto();
        sequenceList = comp.getSequenceList();
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (janto == null) {
            return false;
        }
        for (Sequence sequence : sequenceList) {
            int num = sequence.getTile().getNumber();
            if (num != 2 && num != 8) {
                return false;
            }
        }

        for (Triplet triplet : tripletList) {
            int num = triplet.getTile().getNumber();
            if (num != 1 && num != 9) {
                return false;
            }
        }

        return true;
    }
}
