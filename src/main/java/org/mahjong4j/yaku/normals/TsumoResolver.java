package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;

/**
 * @author yu1ro
 */
public class TsumoResolver implements NormalYakuResolver {
    private final MeldDirectory comp;
    private final GeneralSituation generalSituation;
    private final PersonalSituation personalSituation;

    public TsumoResolver(MeldDirectory comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        this.comp = comp;
        this.generalSituation = generalSituation;
        this.personalSituation = personalSituation;
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.TSUMO;
    }

    @Override
    public boolean isMatch() {
        if (generalSituation == null || personalSituation == null) {
            return false;
        }
        boolean isOpen = false;
        for (Meld meld : comp.getAllMentsu()) {
            if (meld.isOpen()) {
                isOpen = true;
            }
        }
        return personalSituation.isTsumo() && !isOpen;
    }
}
