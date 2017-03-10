package org.mahjong4j.yaku.normals;

import org.mahjong4j.GeneralSituation;
import org.mahjong4j.PersonalSituation;
import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.MeldDirectory;

/**
 * @author yu1ro
 */
public class JikazeResolver extends SituationResolver implements NormalYakuResolver {
    private final MeldDirectory comp;

    public JikazeResolver(MeldDirectory comp, GeneralSituation generalSituation, PersonalSituation personalSituation) {
        super(generalSituation, personalSituation);
        this.comp = comp;
    }

    @Override
    public NormalYaku getNormalYaku() {
        return NormalYaku.JIKAZE;
    }

    @Override
    public boolean isMatch() {
        if (isSituationsNull()) {
            return false;
        }
        for (Triplet triplet : comp.getKotsuKantsu()) {
            if (triplet.getTile() == personalSituation.getJikaze()) {
                return true;
            }
        }
        return false;
    }
}
