package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.tile.TileType;

import java.util.List;

import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHJUN;

/**
 * 三色同順判定クラス
 * 萬子・索子・筒子それぞれの色で同じ並びの順子を作ったときに成立
 *
 * @author yu1ro
 */
public class SanshokudohjunResolver extends SanshokuResolver implements NormalYakuResolver {
    private final NormalYaku yakuEnum = SANSHOKUDOHJUN;
    private final int shuntsuCount;
    private final List<Sequence> sequenceList;

    public SanshokudohjunResolver(MeldDirectory comp) {
        shuntsuCount = comp.getShuntsuCount();
        sequenceList = comp.getSequenceList();
    }

    public NormalYaku getNormalYaku() {
        return yakuEnum;
    }

    public boolean isMatch() {
        if (shuntsuCount < 3) {
            return false;
        }

        Sequence candidate = null;

        for (Sequence sequence : sequenceList) {
            TileType shuntsuType = sequence.getTile().getType();
            int shuntsuNum = sequence.getTile().getNumber();

            if (candidate == null) {
                candidate = sequence;
                continue;
            }

            if (candidate.getTile().getNumber() == shuntsuNum) {
                detectType(shuntsuType);
                detectType(candidate.getTile().getType());
            } else {
                candidate = sequence;
            }
        }
        return manzu && pinzu && sohzu;
    }
}
