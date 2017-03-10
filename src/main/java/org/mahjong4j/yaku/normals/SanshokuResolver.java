package org.mahjong4j.yaku.normals;

import org.mahjong4j.tile.TileType;

import static org.mahjong4j.tile.TileType.*;

/**
 * @author yu1ro
 */
public abstract class SanshokuResolver implements NormalYakuResolver {
    protected boolean manzu = false;
    protected boolean pinzu = false;
    protected boolean sohzu = false;

    protected void detectType(TileType shuntsuType) {
        if (shuntsuType == CHARACTER) {
            manzu = true;
        } else if (shuntsuType == DOT) {
            pinzu = true;
        } else if (shuntsuType == BAMBOO) {
            sohzu = true;
        }
    }
}
