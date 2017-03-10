package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;

/**
 * @author yu1ro
 */
public class BakazeResolver extends SituationResolver implements NormalYakuResolver {
    private final MeldDirectory comp;

    public BakazeResolver(MeldDirectory comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
        this.comp = comp;
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.BAKAZE;
    }

    @Override
    public boolean isMatch() {
        if ((isSituationsNull())) {
            return false;
        }
        for (Triplet triplet : comp.getKotsuKantsu()) {
            if (triplet.getTile() == generalSituation.getBakaze()) {
                return true;
            }
        }
        return false;
    }
}
