package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.Yakuman.TSUISO;

/**
 * 字一色判定クラス
 * 字牌のみで構成された場合に成立
 *
 * @author yu1ro
 */
public class TsuisoResolver implements YakumanResolver {
    private final Yakuman yakuman = TSUISO;

    private final Pair janto;
    private final List<Sequence> sequenceList;
    private final List<Pair> pairList;
    private final List<Triplet> tripletList;

    public TsuisoResolver(MeldDirectory comp) {
        janto = comp.getJanto();
        sequenceList = comp.getSequenceList();
        pairList = comp.getPairList();
        tripletList = comp.getKotsuKantsu();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    public boolean isMatch() {
        if (sequenceList.size() > 0) {
            return false;
        }
        if (janto == null) {
            for (Pair pair : pairList) {
                if (pair.getTile().getNumber() != 0) {
                    return false;
                }
            }
            return true;
        }

        if (janto.getTile().getNumber() != 0) {
            return false;
        }

        for (Triplet triplet : tripletList) {
            if (triplet.getTile().getNumber() != 0) {
                return false;
            }
        }

        return true;
    }
}
