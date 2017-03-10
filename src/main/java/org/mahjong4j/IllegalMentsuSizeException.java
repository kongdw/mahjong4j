package org.mahjong4j;

import org.mahjong4j.hands.Meld;

import java.util.List;

/**
 * 面子の組が和了の形になっていない場合に投げられます
 *
 * @author yu1ro
 */
public class IllegalMentsuSizeException extends Mahjong4jException {
    private List<Meld> meldList;

    public IllegalMentsuSizeException(List<Meld> meldList) {
        super("面子の組が和了の形になっていません");
        this.meldList = meldList;
    }

    public String getAdvice() {
        return "面子の数は合計で5個もしくは七対子の場合のみ7個でなければなりませんが" + meldList.size() + "個の面子が見つかりました";
    }

    /**
     * @return 誤っていると判定されている面子の組を返します
     */
    public List<Meld> getMeldList() {
        return meldList;
    }
}
