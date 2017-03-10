package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.RYANPEIKO;

/**
 * 二盃口判定クラス
 * 一盃口が２つ含まれる場合に成立
 * 一盃口とは複合しない
 *
 * @author yu1ro
 */
public class RyanpeikoResolver extends PeikoResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = RYANPEIKO;

    private final List<Sequence> sequenceList;

    public RyanpeikoResolver(MeldDirectory comp) {
        sequenceList = comp.getSequenceList();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        return peiko(sequenceList) == 2;
    }
}
