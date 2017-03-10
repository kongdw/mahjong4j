package org.mahjong4j.hands;

import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * 对字
 */
public class Pair extends Meld {

    /**
     * 対子であることがわかっている場合に使います
     *
     * @param identifierTile 対子の種類
     */
    public Pair(Tile identifierTile) {
        this.identifierTile = identifierTile;
        this.isMeld = true;
    }

    /**
     * 対子であるかのチェックを伴います
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     */
    public Pair(Tile tile1, Tile tile2) {
        if (this.isMeld = Pair.check(tile1, tile2)) {
            this.identifierTile = tile1;
        }
    }

    /**
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @return 2枚が一致すればtrue
     */
    public static boolean check(Tile tile1, Tile tile2) {
        return tile1 == tile2;
    }

    /**
     * 対子になりうる牌をリストにして返す
     *
     * @param tiles 手牌
     * @return 雀頭候補の対子リスト
     */
    public static List<Pair> findJantoCandidate(int[] tiles) throws MahjongTileOverFlowException {
        List<Pair> result = new ArrayList<>(7);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] > 4) {
                throw new MahjongTileOverFlowException(i, tiles[i]);
            }
            if (tiles[i] >= 2) {
                result.add(new Pair(Tile.valueOf(i)));
            }
        }
        return result;
    }

    @Override
    public int getFu() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (isMeld != pair.isMeld) return false;
        return identifierTile == pair.identifierTile;

    }

    @Override
    public int hashCode() {
        int result = identifierTile != null ? identifierTile.hashCode() : 0;
        result = 31 * result + (isMeld ? 1 : 0);
        return result;
    }
}
