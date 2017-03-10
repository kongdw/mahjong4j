package org.mahjong4j.tile;

import static org.mahjong4j.tile.TileType.*;

/**
 * @author yu1ro
 */
public enum Tile {
    W1(0, CHARACTER, 1),
    W2(1, CHARACTER, 2),
    W3(2, CHARACTER, 3),
    W4(3, CHARACTER, 4),
    W5(4, CHARACTER, 5),
    W6(5, CHARACTER, 6),
    W7(6, CHARACTER, 7),
    W8(7, CHARACTER, 8),
    W9(8, CHARACTER, 9),

    D1(9, DOT, 1),
    D2(10, DOT, 2),
    D3(11, DOT, 3),
    D4(12, DOT, 4),
    D5(13, DOT, 5),
    D6(14, DOT, 6),
    D7(15, DOT, 7),
    D8(16, DOT, 8),
    D9(17, DOT, 9),

    T1(18, BAMBOO, 1),
    T2(19, BAMBOO, 2),
    T3(20, BAMBOO, 3),
    T4(21, BAMBOO, 4),
    T5(22, BAMBOO, 5),
    T6(23, BAMBOO, 6),
    T7(24, BAMBOO, 7),
    T8(25, BAMBOO, 8),
    T9(26, BAMBOO, 9),

    EAST(27, WIND, 0),//東
    SOUTH(28, WIND, 0),//南
    WEST(29, WIND, 0),//西
    NORTH(30, WIND, 0),//北

    WHITE(31, DRAGON, 0),//白
    GREEN(32, DRAGON, 0),//発
    RED(33, DRAGON, 0);//中

    private int code;
    private TileType type;
    private int number;

    Tile(int code, TileType type, int number) {
        this.code = code;
        this.type = type;
        this.number = number;
    }

    public static Tile valueOf(int c) {
        return Tile.values()[c];
    }

    public int getCode() {
        return code;
    }

    public TileType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public boolean isYaochu() {
        return number == 0 || number == 1 || number == 9;
    }
}
