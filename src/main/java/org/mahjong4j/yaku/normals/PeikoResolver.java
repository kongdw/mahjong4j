package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Sequence;

import java.util.List;

/**
 * @author yu1ro
 */
public abstract class PeikoResolver implements NormalYakuResolver {
    protected int peiko(List<Sequence> sequenceList) {
        if (sequenceList.size() < 2) {
            return 0;
        }

        Sequence stockOne = null;
        Sequence stockTwo = null;

        int peiko = 0;
        for (Sequence sequence : sequenceList) {
            //鳴いている場合はfalse
            if (sequence.isOpen()) {
                return 0;
            }

            if (stockOne == null) {
                stockOne = sequence;
                continue;
            }

            //１つ目の盃口が見つかった
            if (stockOne.equals(sequence) && peiko == 0) {
                peiko = 1;
                continue;
            }

            if (stockTwo == null) {
                stockTwo = sequence;
                continue;
            }

            if (stockTwo.equals(sequence)) {
                return 2;
            }
        }
        return peiko;
    }
}
