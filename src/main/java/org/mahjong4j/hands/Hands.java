package org.mahjong4j.hands;

import org.mahjong4j.HandsOverFlowException;
import org.mahjong4j.IllegalMentsuSizeException;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;
import org.mahjong4j.yaku.yakuman.KokushimusoResolver;

import java.util.*;

/**
 * 手牌に関する操作全般を扱います
 * このクラスのインスタンスをMahjongクラスに投げると
 * 点数が返ってくるようにしたいと考えています
 * TODO: ツモって牌を捨てるオペレーションメソッド
 *
 * @author yu1ro
 */
public class Hands {
    // -------------------------確定系-----------------------

    //確定した上がりの形のリスト
    private Set<MeldDirectory> meldDirectorySet = new HashSet<>();

    //確定した各牌の数一覧
    private int[] handsComp = new int[34];

    //最後の牌
    private Tile last;

    //あがれるか
    private boolean canWin = false;

    //食い下がりかどうか
    private boolean isOpen = false;

    // ------------------------ストック系----------------------

    // コンストラクタで入力された面子リスト
    private List<Meld> inputtedMeldList = new ArrayList<>();

    // 操作する用のストック
    private int[] handStocks = new int[34];

    // コンストラクタで入力された各牌の数の配列
    private int[] inputtedTiles;
    private boolean isKokushimuso = false;

    /**
     * @param otherTiles
     * @param last
     * @param meldList
     * @throws MahjongTileOverFlowException
     */
    public Hands(int[] otherTiles, Tile last, List<Meld> meldList) throws MahjongTileOverFlowException, IllegalMentsuSizeException {
        inputtedTiles = otherTiles;
        this.last = last;
        inputtedMeldList = meldList;
        setHandsComp(otherTiles, meldList);
        findMentsu();
    }

    /**
     * @param otherTiles
     * @param last
     * @param meld
     * @throws MahjongTileOverFlowException
     */
    public Hands(int[] otherTiles, Tile last, Meld... meld) throws MahjongTileOverFlowException, IllegalMentsuSizeException {
        inputtedTiles = otherTiles;
        setHandsComp(otherTiles, Arrays.asList(meld));
        this.last = last;
        Collections.addAll(inputtedMeldList, meld);
        findMentsu();
    }

    /**
     * @param allTiles lastの牌も含めて下さい合計14になるはずです
     * @param last     この牌もotherTilesに含めてください
     */
    public Hands(int[] allTiles, Tile last) throws HandsOverFlowException, MahjongTileOverFlowException, IllegalMentsuSizeException {
        inputtedTiles = allTiles;
        this.last = last;

        //整合性をチェック
        checkTiles(allTiles);

        handsComp = allTiles;

        findMentsu();
    }

    /**
     * コンストラクタで面子を入力した場合に
     * 面子を各牌の数に変換します
     *
     * @param otherTiles 各牌の数
     * @param meldList 面子のリスト
     */
    private void setHandsComp(int[] otherTiles, List<Meld> meldList) {
        System.arraycopy(otherTiles, 0, handsComp, 0, otherTiles.length);
        for (Meld meld : meldList) {
            int code = meld.getTile().getCode();

            if (meld.isOpen()) {
                isOpen = true;
            }

            if (meld instanceof Sequence) {
                handsComp[code - 1] += 1;
                handsComp[code] += 1;
                handsComp[code + 1] += 1;
            } else if (meld instanceof Triplet) {
                handsComp[code] += 3;
            } else if (meld instanceof Kong) {
                handsComp[code] += 4;
            } else if (meld instanceof Pair) {
                handsComp[code] += 2;
            }
        }
    }

    public Set<MeldDirectory> getMeldDirectorySet() {
        return meldDirectorySet;
    }

    public boolean getCanWin() {
        return canWin;
    }

    public Tile getLast() {
        return last;
    }

    public int[] getHandsComp() {
        return handsComp;
    }

    public boolean getIsKokushimuso() {
        return isKokushimuso;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void checkTiles(int[] allTiles) throws HandsOverFlowException {
        int num = 0;
        for (int tileNum : allTiles) {
            num += tileNum;
            if (num > 14) {
                throw new HandsOverFlowException();
            }
        }
    }

    public void initStock() {
        System.arraycopy(inputtedTiles, 0, handStocks, 0, inputtedTiles.length);
    }

    /**
     * 槓子は見つけません
     */
    public void findMentsu() throws MahjongTileOverFlowException, IllegalMentsuSizeException {
        checkTileOverFlow();

        // 国士無双型の判定
        initStock();
        KokushimusoResolver kokushimuso = new KokushimusoResolver(handStocks);
        if (kokushimuso.isMatch()) {
            isKokushimuso = true;
            canWin = true;
            return;
        }

        // 雀頭の候補を探してストックしておく
        initStock();
        List<Pair> pairList = Pair.findJantoCandidate(handStocks);

        // 雀頭が一つも見つからなければfalse
        if (pairList.size() == 0) {
            canWin = false;
            return;
        }

        //七対子なら保存しておく
        if (pairList.size() == 7) {
            canWin = true;
            List<Meld> meldList = new ArrayList<>(7);
            meldList.addAll(pairList);
            MeldDirectory comp = new MeldDirectory(meldList, last);
            meldDirectorySet.add(comp);
        }

        // その他の判定
        //雀頭候補から探す
        List<Meld> winCandidate = new ArrayList<>(4);
        for (Pair pair : pairList) {
            // 操作変数を初期化
            init(winCandidate, pair);

            //刻子優先検索
            //検索
            winCandidate.addAll(findKotsuCandidate());
            winCandidate.addAll(findShuntsuCandidate());
            //全て0かチェック
            convertToMentsuComp(winCandidate);

            init(winCandidate, pair);
            //順子優先検索
            winCandidate.addAll(findShuntsuCandidate());
            winCandidate.addAll(findKotsuCandidate());
            convertToMentsuComp(winCandidate);
        }

    }

    /**
     * 同じ牌が5個以上はありえないので、Exception をthrow
     *
     * @throws MahjongTileOverFlowException
     */
    private void checkTileOverFlow() throws MahjongTileOverFlowException {
        //
        for (int i = 0; i < handsComp.length; i++) {
            int hand = handsComp[i];
            if (hand > 4) {
                canWin = false;
                throw new MahjongTileOverFlowException(i, hand);
            }
        }
    }

    /**
     * 操作変数・面子の候補を初期化し
     * 雀頭の分をストックから減らします
     *
     * @param winCandidate 面子の候補
     * @param pair       この検索サイクルの雀頭候補
     */
    private void init(List<Meld> winCandidate, Pair pair) {
        // 操作変数を初期化
        initStock();
        winCandidate.clear();
        //ストックから雀頭を減らす
        handStocks[pair.getTile().getCode()] -= 2;
        winCandidate.add(pair);
    }

    /**
     * handsStockが全て0の場合
     * winCandidateは完成しているので
     * mentsuCompに代入します
     *
     * @param winCandidate mentsuCompに代入するかもしれない
     */
    private void convertToMentsuComp(List<Meld> winCandidate) throws IllegalMentsuSizeException {
        //全て0かチェック
        if (isAllZero(handStocks)) {
            canWin = true;
            winCandidate.addAll(inputtedMeldList);
            MeldDirectory meldDirectory = new MeldDirectory(winCandidate, last);
            if (!meldDirectorySet.contains(meldDirectory)) {
                meldDirectorySet.add(meldDirectory);
            }
        }
    }

    /**
     * 入力の配列が全て0かを調べます
     *
     * @param stocks 調べたい配列
     * @return すべて0ならtrue ひとつでも0でなければfalse
     */
    private boolean isAllZero(int[] stocks) {
        for (int i : stocks) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private List<Meld> findShuntsuCandidate() {
        List<Meld> resultList = new ArrayList<>(4);
        //字牌などはチェックしないので26まで
        for (int j = 1; j < 26; j++) {
            // whileにしたのは一盃口などがあるから
            while (handStocks[j - 1] > 0 && handStocks[j] > 0 && handStocks[j + 1] > 0) {
                Sequence sequence = new Sequence(
                    false,
                    Tile.valueOf(j - 1),
                    Tile.valueOf(j),
                    Tile.valueOf(j + 1)
                );

                //3つ並んでいても順子であるとは限らないので調べる
                if (sequence.isMeld()) {
                    resultList.add(sequence);
                    handStocks[j - 1]--;
                    handStocks[j]--;
                    handStocks[j + 1]--;
                }
            }
        }
        return resultList;
    }

    private List<Meld> findKotsuCandidate() {
        List<Meld> resultList = new ArrayList<>(4);
        for (int i = 0; i < handStocks.length; i++) {
            if (handStocks[i] >= 3) {
                resultList.add(new Triplet(false, Tile.valueOf(i)));
                handStocks[i] -= 3;
            }
        }
        return resultList;
    }
}
