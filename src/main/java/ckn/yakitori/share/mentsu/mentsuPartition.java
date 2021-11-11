package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ckn.yakitori.share.mentsu.WaitType.*;

/**
 * 手牌から面子を抜き出し、分割するためのクラスです。
 * <p>
 * 手牌クラスを入れると、刻子、順子、対子を探しだし抜き出します。<br>
 * 待ちの形も計算して返します。<br>
 * {@link #getMentsuList()}、{@link #getKotsuList()}、{@link #getShuntsuList()}、{@link #getToitsuList()}、{@link #getWaitTypeList()} を使用してください。<br>
 * 複数パターンが存在する場合が存在するため、２次元配列で返します。<br>
 * ２次元配列を処理するのがめんどくさい場合は、
 * {@link #getMentsuList(int)}、{@link #getKotsuList(int)}、{@link #getShuntsuList(int)}、{@link #getToitsuList(int)}、{@link #getWaitTypeList(int)} を使用すると、int番目の牌グループの１次元配列を返します。<br>
 * </p>
 *
 * @author Matsushita
 * @author Shintani
 * @version 1.0
 */
public class mentsuPartition {

    /**
     * 見つけ出した面子を保存するリスト
     */
    private final ArrayList<ArrayList<mentsu>> mentsuList = new ArrayList<>();
    /**
     * 見つけ出した刻子を保存するリスト
     */
    private final ArrayList<ArrayList<kotsu>> kotsuList = new ArrayList<>();
    /**
     * 見つけ出した順子を保存するリスト
     */
    private final ArrayList<ArrayList<shuntsu>> shuntsuList = new ArrayList<>();
    /**
     * 見つけ出した対子を保存するリスト
     */
    private final ArrayList<ArrayList<toitsu>> toitsuList = new ArrayList<>();
    /**
     * 見つけ出した槓子を保存するリスト
     */
    private final ArrayList<ArrayList<kantsu>> kantsuList = new ArrayList<>();
    /**
     * 計算した待ち形を保存するリスト
     */
    private final ArrayList<WaitType> WaitTypeList = new ArrayList<>();
    /**
     * ログの文字列を保存する変数
     */
    private final StringBuffer logBuf = new StringBuffer();
    /**
     * 改行コードの定義（OSごとに変化する）
     */
    private final String NEWLINE_CODE = System.lineSeparator();
    /**
     * 見つけ出した面子を仮保存しておく変数
     */
    ArrayList<mentsu> TempleList = new ArrayList<>();
    /**
     * 入力された手牌が上がれるかどうか
     */
    private boolean canWin = false;
    /**
     * 計算した待ち形を仮保存しておく変数
     */
    private WaitType waitType;
    /**
     * 最初の手牌を保存しておく配列
     */
    private List<tile> stockTiles;


    /**
     * 手牌分割メソッドへHandを送るコンストラクタです。
     *
     * @param Hand 分割したい手牌
     * @since 1.0
     */
    public mentsuPartition(hand Hand) {
        DoPartition(Hand);

    }

    /**
     * 手牌分割を行い、List系に結果を入れるメソッドです。
     *
     * <p>判定方法をここに記しておきます。</p>
     * <ol>
     *     <li>国士無双かどうかチェックする。</li>
     *       <ul>
     *         <li>国士無双だった場合、十三面待ちか単騎待ちかのチェック。</li>
     *       </ul>
     *     <li>九蓮宝燈かどうかチェックする。</li>
     *       <ul>
     *         <li>九蓮宝燈だった場合、九面待ちか単騎待ちかのチェック。</li>
     *       </ul>
     *     <li>対子となりうる全てのパターンを調べる。</li>
     *     <li>対子となりうる牌の種類が7種類なら七対子とする。</li>
     *     <li>1つ目の対子となりうる牌を対子と仮定し、除外する。</li>
     *     <li>左から順子を探し、その後刻子を探して、すべての牌が抜き出せたかチェックする。</li>
     *     <li>刻子を探し、その後左から順子を探し、すべての牌が抜き出せたかチェックする。</li>
     *     <li>右から順子を探し、その後刻子を探して、すべての牌が抜き出せたかチェックする。</li>
     *     <li>抜き出せた牌をListに保存しておき、次の対子となりうる牌を抜き出す、そして6に戻る。</li>
     * </ol>
     * <p>{@link #getLog()}で詳細なログを取得することができます。</p>
     *
     * @param Hand 分割したい手牌
     * @since 1.0
     */
    private void DoPartition(hand Hand) {

        Hand.sortTile();
        Hand.getPickTile().setWinTile(true);
        logBuf.append(Hand).append("の 面子解析").append(NEWLINE_CODE);
        //国士
        rollbackStock(Hand);
        mentsu Kokushi = new kokushi(Hand);
        if (Kokushi.isCheckPass()) {
            if (Objects.equals(Kokushi.getIdentifierTile().getFullName(), Hand.getPickTile().getFullName())) {
                waitType = JYUSAN;
            } else {
                waitType = TANKI;
            }
            logBuf.append("\u001b[00;32m国士無双").append(waitType.getText()).append("です。\u001b[00m").append(NEWLINE_CODE);
            TempleList.add(Kokushi);
            stockMentsu(TempleList);
            canWin = true;
            return;
        }
        //九蓮宝燈
        rollbackStock(Hand);
        mentsu Cyuren = new cyuren(Hand);
        if (Cyuren.isCheckPass()) {
            if (Objects.equals(Cyuren.getIdentifierTile().getFullName(), Hand.getPickTile().getFullName())) {
                waitType = KYUMEN;
            } else {
                waitType = TANKI;
            }
            logBuf.append("\u001b[00;32m九蓮宝燈").append(waitType.getText()).append("です。\u001b[00m").append(NEWLINE_CODE);
            TempleList.add(Cyuren);
            stockMentsu(TempleList);
            canWin = true;
            return;
        }

        rollbackStock(Hand);
        List<mentsu> T = findAllToitsu();
        logBuf.append("雀頭候補数 : ").append(T.size()).append(NEWLINE_CODE);
        if (T.size() == 7) {
            logBuf.append("\u001b[00;32m七対子です。\u001b[00m").append(NEWLINE_CODE);
            waitType = TANKI;
            stockMentsu(T);
            canWin = true;
            return;
        }
        for (mentsu toi : T) {
            rollbackStock(Hand);
            removeToitsu((toitsu) toi);
            List<mentsu> S = findShuntsu();
            List<mentsu> K = findKotsu();

            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
                stockMentsu(K, S, new ArrayList<>(List.of(toi)));
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);
            }
            rollbackStock(Hand);
            removeToitsu((toitsu) toi);
            K = findKotsu();
            S = findShuntsu();
            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
                stockMentsu(K, S, new ArrayList<>(List.of(toi)));
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);

            }
            rollbackStock(Hand);
            removeToitsu((toitsu) toi);
            S = findShuntsuR();
            K = findKotsu();

            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(逆順優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
                stockMentsu(K, S, new ArrayList<>(List.of(toi)));
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(逆順優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);
            }
        }
    }

    /**
     * stockTilesに入ってある牌の中で、対子となりうる牌をすべて返すメソッドです。
     *
     * @return 対子となりうる牌（mentsu型(実体はtoitsu)のList）
     * @since 1.0
     */
    private List<mentsu> findAllToitsu() {
        List<mentsu> find = new ArrayList<>();
        ArrayList<String> maeToitsu = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            for (int j = i + 1; j < stockTiles.size(); j++) {
                mentsu Toitsu = new toitsu(stockTiles.get(i), stockTiles.get(j));
                if (Toitsu.isCheckPass() && !maeToitsu.contains(Toitsu.getTile().getFullName())) {
                    find.add(Toitsu);
                    maeToitsu.add(Toitsu.getTile().getFullName());
                    stockTiles.remove(i);
                    stockTiles.remove(j - 1);
                    i = -1;
                    break;
                }
            }
        }
        return find;
    }

    /**
     * 仮に収納しておく用の変数を初期化するメソッドです。
     *
     * <p>stockTiles、waitTypeを初期状態に戻します。</p>
     *
     * @param Hand stockHandにコピーする手牌
     * @since 1.0
     */
    private void rollbackStock(hand Hand) {
        stockTiles = new ArrayList<>(Arrays.asList(Hand.getAll()));
        waitType = null;
    }

    /**
     * stockTilesから入力された対子を抜き出し、除外するメソッドです。
     *
     * @param Toitsu stockTilesのどこにあるか検索し、除外したい対子
     * @since 1.0
     */
    private void removeToitsu(toitsu Toitsu) {
        int find = 0;
        for (int i = 0; i < stockTiles.size(); i++) {
            mentsu T = new toitsu(stockTiles.get(i), Toitsu.getIdentifierTile());
            if (T.isCheckPass()) {
                if (find <= 1) {
                    if (stockTiles.get(i).isWinTile()) {
                        waitType = calculateWaitType(T, stockTiles.get(i));
                    }
                    stockTiles.remove(i);
                    find++;
                    //logbuf.append(find + " : " + stockTiles.get(i).getFullName());
                    i--;
                }
            }
        }
    }

    /**
     * stockTilesの中から順子になる物を左から抜き出し、除外するメソッドです。
     *
     * @return 見つけ出した順子（mentsu型(実体はshuntsu)のList）
     * @since 1.0
     */
    private List<mentsu> findShuntsu() {
        List<mentsu> find = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            j:
            for (int j = i + 1; j < stockTiles.size(); j++) {
                for (int k = j + 1; k < stockTiles.size(); k++) {
                    mentsu Shuntsu = new shuntsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    if (Shuntsu.isCheckPass()) {

                        if (stockTiles.get(i).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(i));
                        }

                        if (stockTiles.get(j).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(j));
                        }

                        if (stockTiles.get(k).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(k));
                        }
                        find.add(Shuntsu);
                        stockTiles.remove(i);
                        stockTiles.remove(j - 1);
                        stockTiles.remove(k - 2);
                        i = -1;
                        break j;
                    }
                }
            }
        }
        return find;
    }

    /**
     * stockTilesの中から順子になる物を右から抜き出し、除外するメソッドです。
     *
     * @return 見つけ出した順子（mentsu型(実体はshuntsu)のList）
     * @since 1.0
     */
    private List<mentsu> findShuntsuR() {
        List<mentsu> find = new ArrayList<>();
        for (int i = stockTiles.size() - 1; i >= 2; i--) {
            j:
            for (int j = i - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    mentsu Shuntsu = new shuntsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    //logbuf.append(i + " - " + j + " - " + k + " : " + stockTiles.get(i).toString() + stockTiles.get(j).toString() + stockTiles.get(k).toString() + " : " + Shuntsu.isCheckPass + "");
                    if (Shuntsu.isCheckPass()) {
                        if (stockTiles.get(i).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(i));
                        }

                        if (stockTiles.get(j).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(j));
                        }

                        if (stockTiles.get(k).isWinTile()) {
                            waitType = calculateWaitType(Shuntsu, stockTiles.get(k));
                        }
                        find.add(Shuntsu);
                        stockTiles.remove(i);
                        stockTiles.remove(j);
                        stockTiles.remove(k);
                        i = stockTiles.size();
                        break j;
                    }
                }
            }
        }
        return find;
    }

    /**
     * stockTilesの中から刻子になる物を抜き出し、除外するメソッドです。
     *
     * @return 見つけ出した刻子（mentsu型(実体はkotsu)のList）
     * @since 1.0
     */
    private List<mentsu> findKotsu() {
        List<mentsu> find = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            j:
            for (int j = i + 1; j < stockTiles.size(); j++) {
                for (int k = j + 1; k < stockTiles.size(); k++) {
                    mentsu Kotsu = new kotsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    if (Kotsu.isCheckPass()) {
                        if (stockTiles.get(i).isWinTile()) {
                            waitType = calculateWaitType(Kotsu, stockTiles.get(i));
                        }

                        if (stockTiles.get(j).isWinTile()) {
                            waitType = calculateWaitType(Kotsu, stockTiles.get(j));
                        }

                        if (stockTiles.get(k).isWinTile()) {
                            waitType = calculateWaitType(Kotsu, stockTiles.get(k));
                        }
                        find.add(Kotsu);
                        stockTiles.remove(i);
                        stockTiles.remove(j - 1);
                        stockTiles.remove(k - 2);
                        i = -1;
                        break j;
                    }
                }
            }
        }
        return find;
    }

    /**
     * 上がり牌が含まれている面子と上がり牌の関係から待ち時の形を計算するメソッドです。
     *
     * @param Mentsu 上がり牌が含まれている面子
     * @param Tile   上がりに使用された牌
     * @return 待ち形（WaitType型）
     * @throws IllegalArgumentException 面子に上がり牌が含まれていないなど計算が行えない場合
     * @see WaitType
     * @since 1.0
     */
    private WaitType calculateWaitType(mentsu Mentsu, tile Tile) {
        if (Mentsu instanceof shuntsu) {
            if (Objects.equals(Mentsu.getIdentifierTile().getFullName(), Tile.getFullName())) {
                //真ん中の牌と上がり牌が同じなら間張待ち
                return KANCYAN;
            } else if (Mentsu.getTileFull()[0].getNumber() == 1 && Tile.getNumber() == 3) {
                //1牌目が1で上がり牌が3なら辺張待ち
                return PENCYAN;
            } else if (Mentsu.getTileFull()[2].getNumber() == 9 && Tile.getNumber() == 7) {
                //3牌目が9で上がり牌が7なら辺張待ち
                return PENCYAN;
            } else if (Objects.equals(Mentsu.getTileFull()[0].getFullName(), Tile.getFullName()) || Objects.equals(Mentsu.getTileFull()[2].getFullName(), Tile.getFullName())) {
                //1牌目、3牌目と上がり牌が一緒で辺張待ちの条件を満たしていないなら両面待ち
                return RYANMEN;
            } else {
                throw new IllegalArgumentException("引数が不正です。");
            }
        }
        if (Mentsu instanceof kotsu) {
            //上がり牌が刻子に含まれている場合、双碰待ち
            return SYANPON;
        }
        if (Mentsu instanceof toitsu) {
            //上がり牌が対子に含まれている場合、単騎待ち
            return TANKI;
        }
        throw new IllegalArgumentException("引数が不正です。");
    }

    /**
     * 面子を適切なリストに格納するメソッドです。
     *
     * <p>面子がなにであるかも判定し、適切なリストに格納します。</p>
     *
     * @param Mentsuss リストに追加する面子 mentsuのListである必要があります。(1つしかなくてもリストにする必要があります。) (何個でも引数を追加可能です。)
     * @since 1.0
     */
    @SafeVarargs
    private void stockMentsu(List<mentsu>... Mentsuss) {
        int i = mentsuList.size();
        newStock();
        for (List<mentsu> Mentsus : Mentsuss) {
            for (mentsu Mentsu : Mentsus) {
                mentsuList.get(i).add(Mentsu);
                if (Mentsu instanceof shuntsu) {
                    shuntsuList.get(i).add((shuntsu) Mentsu);
                }
                if (Mentsu instanceof kotsu) {
                    kotsuList.get(i).add((kotsu) Mentsu);
                }
                if (Mentsu instanceof toitsu) {
                    toitsuList.get(i).add((toitsu) Mentsu);
                }
                if (Mentsu instanceof kantsu) {
                    kantsuList.get(i).add((kantsu) Mentsu);
                }
            }
        }
        WaitTypeList.add(waitType);
    }

    /**
     * 新しい空のArrayListを5つのリストに追加するメソッドです。
     *
     * <p>複数パターンの抜き出し方がある時に区切りを付けるために使用します。</p>
     *
     * @since 1.0
     */
    private void newStock() {
        mentsuList.add(new ArrayList<>());
        shuntsuList.add(new ArrayList<>());
        kotsuList.add(new ArrayList<>());
        toitsuList.add(new ArrayList<>());
        kantsuList.add(new ArrayList<>());
    }

    /**
     * 手牌が上がれるかどうかを返します。
     *
     * @return 手牌が上がれるかどうか
     * @since 1.0
     */
    public boolean isCanWin() {
        return canWin;
    }

    /**
     * stockTilesの牌の数が0かどうかを返します。
     *
     * @return stockTilesの牌の数が0かどうか
     * @since 1.0
     */
    private boolean endCheck() {
        return stockTiles.size() == 0;
    }

    /**
     * 抜き出した刻子のリストを2次元ArrayListで返します。
     *
     * @return 抜き出した刻子のリスト（2次元ArrayList）
     * @since 1.0
     */
    public ArrayList<ArrayList<kotsu>> getKotsuList() {
        return kotsuList;
    }


    /**
     * 入力された組目の抜き出した刻子のリストをArrayListで返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 抜き出した刻子のリスト（ArrayList）
     * @since 1.0
     */
    public ArrayList<kotsu> getKotsuList(int Num) {
        return kotsuList.get(Num);
    }

    /**
     * 抜き出した対子のリストを2次元ArrayListで返します。
     *
     * @return 抜き出した対子のリスト（2次元ArrayList）
     * @since 1.0
     */
    public ArrayList<ArrayList<toitsu>> getToitsuList() {
        return toitsuList;
    }

    /**
     * 入力された組目の抜き出した対子のリストをArrayListで返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 抜き出した対子のリスト（ArrayList）
     * @since 1.0
     */
    public ArrayList<toitsu> getToitsuList(int Num) {
        return toitsuList.get(Num);
    }

    /**
     * 抜き出した順子のリストを2次元ArrayListで返します。
     *
     * @return 抜き出した順子のリスト（2次元ArrayList）
     * @since 1.0
     */
    public ArrayList<ArrayList<shuntsu>> getShuntsuList() {
        return shuntsuList;
    }

    /**
     * 入力された組目の抜き出した順子のリストをArrayListで返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 抜き出した順子のリスト（ArrayList）
     * @since 1.0
     */
    public ArrayList<shuntsu> getShuntsuList(int Num) {
        return shuntsuList.get(Num);
    }

    /**
     * 抜き出した面子のリストを2次元ArrayListで返します。
     *
     * @return 抜き出した面子のリスト（2次元ArrayList）
     * @since 1.0
     */
    public ArrayList<ArrayList<mentsu>> getMentsuList() {
        return mentsuList;
    }

    /**
     * 入力された組目の抜き出した面子のリストをArrayListで返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 抜き出した面子のリスト（ArrayList）
     * @since 1.0
     */
    public ArrayList<mentsu> getMentsuList(int Num) {
        return mentsuList.get(Num);
    }

    /**
     * 抜き出した槓子のリストを2次元ArrayListで返します。
     *
     * @return 抜き出した槓子のリスト（2次元ArrayList）
     * @since 1.0
     */
    public ArrayList<ArrayList<kantsu>> getKantsuList() {
        return kantsuList;
    }

    /**
     * 入力された組目の抜き出した槓子のリストをArrayListで返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 抜き出した槓子のリスト（ArrayList）
     * @since 1.0
     */
    public ArrayList<kantsu> getKantsuList(int Num) {
        return kantsuList.get(Num);
    }

    /**
     * 待ち形のリストをArrayListで返します。
     *
     * @return 待ちの形（WaitTypeのArrayList）
     * @since 1.0
     */
    public ArrayList<WaitType> getWaitTypeList() {
        return WaitTypeList;
    }

    /**
     * 入力された組目の待ちの形を返します。
     *
     * @param Num 何組目の抜き出し形の物を返すか
     * @return 待ちの形（WaitType）
     * @since 1.0
     */
    public WaitType getWaitTypeList(int Num) {
        return WaitTypeList.get(Num);
    }

    /**
     * 面子分解処理の詳細なログを返します。
     *
     * @return 面子分解処理の詳細なログ
     * @since 1.0
     */
    public String getLog() {
        return logBuf.toString();
    }
}
