package org.mahjong4j.hands;

import org.mahjong4j.tile.Tile;

/**
 * 槓子に関するクラスです
 * 暗槓と明槓の両方を扱います
 *
 * @author yu1ro
 */
public class Kong extends Meld {

    /**
     * 槓子が完成していることを前提にしているため
     * 槓子であるかのチェックは伴いません。
     *
     * @param isOpen         暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param identifierTile どの牌の槓子なのか
     */
    public Kong(boolean isOpen, Tile identifierTile) {
        this.isOpen = isOpen;
        this.identifierTile = identifierTile;
        this.isMeld = true;
    }

    /**
     * 槓子であるかのチェックも伴います
     * すべての牌(tile1~4)が同じ場合にisMentsuがtrueになります
     *
     * @param isOpen 暗槓の場合false, 明槓の場合はtrueを入れて下さい
     * @param tile1  1枚目
     * @param tile2  2枚目
     * @param tile3  3枚目
     * @param tile4  4枚目
     */
    public Kong(boolean isOpen, Tile tile1, Tile tile2, Tile tile3, Tile tile4) {
        this.isOpen = isOpen;
        if (this.isMeld = check(tile1, tile2, tile3, tile4)) {
            identifierTile = tile1;
        }
    }

    /**
     * tile1~4が同一の牌かを調べます
     *
     * @param tile1 1枚目
     * @param tile2 2枚目
     * @param tile3 3枚目
     * @param tile4 4枚目
     * @return 槓子の場合true 槓子でない場合false
     */
    public static boolean check(Tile tile1, Tile tile2, Tile tile3, Tile tile4) {
        return tile1 == tile2 && tile2 == tile3 && tile3 == tile4;
    }

    @Override
    public int getFu() {
        int mentsuFu = 8;
        if (!isOpen) {
            mentsuFu *= 2;
        }
        if (identifierTile.isYaochu()) {
            mentsuFu *= 2;
        }
        return mentsuFu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kong)) return false;

        Kong kong = (Kong) o;

        if (isMeld != kong.isMeld) return false;
        if (isOpen != kong.isOpen) return false;
        return identifierTile == kong.identifierTile;

    }

    @Override
    public int hashCode() {
        int result = identifierTile != null ? identifierTile.hashCode() : 0;
        result = 31 * result + (isMeld ? 1 : 0);
        result = 31 * result + (isOpen ? 1 : 0);
        return result;
    }
}
