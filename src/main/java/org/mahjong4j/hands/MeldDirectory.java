package org.mahjong4j.hands;

import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * 上がれる面子を整理したクラスです
 *
 * @author yu1ro
 */
public class MeldDirectory {

    private List<Pair> pairList = new ArrayList<>(7);
    private List<Sequence> sequenceList = new ArrayList<>(4);
    private List<Triplet> tripletList = new ArrayList<>(4);
    private List<Kong> kongList = new ArrayList<>(4);
    private Tile last;

    /**
     * @param meldList 上がりとなった面子のリスト
     * @param last
     * @throws IllegalMentsuSizeException 和了れる形になっていなければthrow
     */
    public MeldDirectory(List<Meld> meldList, Tile last) throws IllegalMentsuSizeException {
        this.last = last;
        for (Meld meld : meldList) {
            setMentsu(meld);
        }

        //整合性を確認する
        int checkSum = sequenceList.size() + tripletList.size() + kongList.size();
        boolean isNormal = checkSum == 4 && pairList.size() == 1;
        boolean isChitoitsu = pairList.size() == 7 && checkSum == 0;
        if (!isNormal && !isChitoitsu) {
            throw new IllegalMentsuSizeException(meldList);
        }
    }

    /**
     * どの面子が入っても対応可能なセッター
     *
     * @param meld 入力したい面子
     */
    private void setMentsu(Meld meld) {
        if (meld instanceof Pair) {
            pairList.add((Pair) meld);
        } else if (meld instanceof Sequence) {
            sequenceList.add((Sequence) meld);
        } else if (meld instanceof Triplet) {
            tripletList.add((Triplet) meld);
        } else if (meld instanceof Kong) {
            kongList.add((Kong) meld);
        }
    }

    /**
     * 七対子の場合はnullを返します
     *
     * @return 雀頭を返します
     */
    public Pair getJanto() {
        if (getToitsuCount() == 1) {
            return pairList.get(0);
        }
        return null;
    }

    public List<Pair> getPairList() {
        return pairList;
    }

    /**
     * 対子の数を返します
     * 1もしくは7以外を返すことはありません
     *
     * @return 通常の型の場合1 七対子の型の場合7
     */
    public int getToitsuCount() {
        return pairList.size();
    }

    public List<Sequence> getSequenceList() {
        return sequenceList;
    }

    /**
     * 順子の数を返します
     * 0~4のどれかです
     *
     * @return 順子の数
     */
    public int getShuntsuCount() {
        return sequenceList.size();
    }

    public List<Triplet> getTripletList() {
        return tripletList;
    }

    /**
     * 刻子でも槓子でも役の判定に影響しない場合に利用します
     * 刻子と槓子をまとめて返します。
     * TODO:good name
     *
     * @return 刻子と槓子をまとめたリスト
     */
    public List<Triplet> getKotsuKantsu() {
        List<Triplet> tripletList = new ArrayList<>(this.tripletList);
        for (Kong kong : kongList) {
            tripletList.add(new Triplet(kong.isOpen(), kong.getTile()));
        }
        return tripletList;
    }

    /**
     * 刻子の数を返します
     * 0~4のどれかです
     *
     * @return 刻子の数
     */
    public int getKotsuCount() {
        return tripletList.size();
    }

    public List<Kong> getKongList() {
        return kongList;
    }

    /**
     * 槓子の数を返します
     * 0~4のどれかです
     *
     * @return 槓子の数
     */
    public int getKantsuCount() {
        return kongList.size();
    }

    /**
     * 対子も含めて全ての面子をリストにして返します
     *
     * @return 構成する全ての面子のリスト
     */
    public List<Meld> getAllMentsu() {
        List<Meld> allMeld = new ArrayList<>(7);
        allMeld.addAll(getPairList());
        allMeld.addAll(getSequenceList());
        allMeld.addAll(getTripletList());
        allMeld.addAll(getKongList());

        return allMeld;
    }

    public Tile getLast() {
        return last;
    }

    public boolean isRyanmen(Tile last) {
        for (Sequence sequence : sequenceList) {
            if (sequence.isOpen()) {
                continue;
            }
            if (sequence.getTile().getType() != last.getType()) {
                continue;
            }

            int number = sequence.getTile().getNumber();
            if (number == 8 || number == 2) {
                continue;
            }

            if (number - 1 == last.getNumber() || number + 1 == last.getNumber()) {
                return true;
            }
        }

        return false;
    }

    public boolean isTanki(Tile last) {
        return getJanto().getTile() == last;
    }

    public boolean isKanchan(Tile last) {
        if (isRyanmen(last)) {
            return false;
        }
        for (Sequence sequence : sequenceList) {
            if (sequence.isOpen() || sequence.getTile().getType() != last.getType()) {
                continue;
            }
            if (sequence.getTile().getNumber() == last.getNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPenchan(Tile last) {
        if (isRyanmen(last)) {
            return false;
        }
        for (Sequence sequence : sequenceList) {
            if (sequence.isOpen() || sequence.getTile().getType() != last.getType()) {
                continue;
            }
            int number = sequence.getTile().getNumber();
            if (number == 8 && last.getNumber() == 7) {
                return true;
            }
            if (number == 2 && last.getNumber() == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * 各面子のリストの順番は関係ないので、
     * 面子の順番が違っていてもtrueになります
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeldDirectory)) return false;

        MeldDirectory that = (MeldDirectory) o;

        if (last != that.last) return false;
        if (pairList.size() != that.pairList.size()) return false;
        if (sequenceList.size() != that.sequenceList.size()) return false;
        if (tripletList.size() != that.tripletList.size()) return false;
        if (kongList.size() != that.kongList.size()) return false;
        for (Pair pair : pairList) {
            if (!that.pairList.contains(pair)) return false;
        }
        for (Sequence sequence : sequenceList) {
            if (!that.sequenceList.contains(sequence)) return false;
        }
        for (Triplet triplet : tripletList) {
            if (!that.tripletList.contains(triplet)) return false;
        }
        for (Kong kong : kongList) {
            if (!that.kongList.contains(kong)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        int result;

        result = last.hashCode();

        if (pairList != null) {
            for (Pair pair : pairList) {
                tmp += pair.hashCode();
            }
        }
        result = 31 * result + tmp;

        tmp = 0;
        if (sequenceList != null) {
            for (Sequence sequence : sequenceList) {
                tmp += sequence.hashCode();
            }
        }

        result = 31 * result + tmp;

        tmp = 0;
        if (tripletList != null) {
            for (Triplet triplet : tripletList) {
                tmp += triplet.hashCode();
            }
        }

        result = 31 * result + tmp;

        tmp = 0;
        if (kongList != null) {
            for (Kong kong : kongList) {
                tmp += kong.hashCode();
            }
        }

        return 31 * result + tmp;
    }
}
