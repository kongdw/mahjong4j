package org.mahjong4j.yaku.normals;


import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.HONROHTOH;

/**
 * 混老頭判定クラス
 * 么九牌のみで構成される場合成立
 *
 * @author yu1ro
 */
public class HonrohtohResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = HONROHTOH;

    private List<Sequence> sequenceList;
    private List<Pair> pairList;
    private List<Triplet> tripletList;

    public HonrohtohResolver(MeldDirectory comp) {
        sequenceList = comp.getSequenceList();
        pairList = comp.getPairList();
        tripletList = comp.getKotsuKantsu();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    /**
     * 么九牌以外を見つけたらfalseを返す
     *
     * @return 混老頭かどうか
     */
    public boolean isMatch() {
        if (sequenceList.size() > 0) {
            return false;
        }
        for (Pair pair : pairList) {
            int num = pair.getTile().getNumber();
            if (1 < num && num < 9) {
                return false;
            }
        }

        for (Triplet triplet : tripletList) {
            int num = triplet.getTile().getNumber();
            if (1 < num && num < 9) {
                return false;
            }
        }
        return true;
    }
}
