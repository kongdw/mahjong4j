package org.mahjong4j.yaku.yakuman;

import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;

import java.util.List;

import static org.mahjong4j.yaku.yakuman.Yakuman.CHINROTO;

/**
 * 清老頭判定クラス
 * 手牌全体が老頭牌（一九牌）だけの場合成立
 *
 * @author yu1ro
 */
public class ChinrohtohResolver implements YakumanResolver {
    private final Yakuman yakuman = CHINROTO;
    private final int totalKotsuKantsu;
    private final List<Triplet> tripletList;
    private final Pair janto;

    public ChinrohtohResolver(MeldDirectory comp) {
        totalKotsuKantsu = comp.getKotsuCount() + comp.getKantsuCount();
        tripletList = comp.getKotsuKantsu();
        janto = comp.getJanto();
    }

    public Yakuman getYakuman() {
        return yakuman;
    }

    /**
     * 違うものが見つかったらfalseを返す方針です
     *
     * @return 清老頭かどうか
     */
    public boolean isMatch() {
        if (totalKotsuKantsu != 4) {
            return false;
        }

        int tileNum = janto.getTile().getNumber();
        if (tileNum != 1 && tileNum != 9) {
            return false;
        }

        //刻子が全て一九牌か
        for (Triplet triplet : tripletList) {
            tileNum = triplet.getTile().getNumber();
            if (tileNum != 1 && tileNum != 9) {
                return false;
            }
        }

        //ここまできたら見つかっている
        return true;
    }
}
