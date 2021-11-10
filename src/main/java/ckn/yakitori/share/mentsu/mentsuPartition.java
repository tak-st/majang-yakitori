package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ckn.yakitori.share.mentsu.WaitType.*;

/**
 *
 */
public class mentsuPartition {

    private final String NEWLINE_CODE = System.lineSeparator();
    private final StringBuffer logBuf = new StringBuffer();
    ArrayList<mentsu> TempleList = new ArrayList<>();
    private hand stockHand;
    private List<tile> stockTiles;
    private boolean canWin = false;
    ArrayList<ArrayList<kotsu>> kotsuList = new ArrayList<>();
    ArrayList<ArrayList<toitsu>> toitsuList = new ArrayList<>();
    ArrayList<ArrayList<shuntsu>> shuntsuList = new ArrayList<>();
    ArrayList<ArrayList<mentsu>> mentsuList = new ArrayList<>();
    ArrayList<ArrayList<kantsu>> kantsuList = new ArrayList<>();
    ArrayList<WaitType> WaitTypeList = new ArrayList<>();

    private WaitType waitType;

    public mentsuPartition(hand Hand, boolean isOpen) {
        DoPartition(Hand, isOpen);

    }

    private void DoPartition(hand Hand, boolean isOpen) {

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
            rollbackStock(Hand, (toitsu) toi);
            List<mentsu> S = findShuntsu();
            List<mentsu> K = findKotsu();

            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
                stockMentsu(K, S, new ArrayList<>(List.of(toi)));
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);
            }
            rollbackStock(Hand, (toitsu) toi);
            K = findKotsu();
            S = findShuntsu();
            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
                stockMentsu(K, S, new ArrayList<>(List.of(toi)));
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);

            }
            rollbackStock(Hand, (toitsu) toi);
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

    private void rollbackStock(hand Hand) {
        stockHand = Hand;
        waitType = null;
        stockTiles = new ArrayList<>(Arrays.asList(stockHand.getAll()));
    }

    private void rollbackStock(hand Hand, toitsu removeToitsu) {
        stockHand = Hand;
        waitType = null;
        stockTiles = new ArrayList<>(Arrays.asList(stockHand.getAll()));
        int find = 0;
        for (int i = 0; i < stockTiles.size(); i++) {
            mentsu Toitsu = new toitsu(stockTiles.get(i), removeToitsu.getIdentifierTile());
            if (Toitsu.isCheckPass()) {
                if (find <= 1) {
                    if (stockTiles.get(i).isWinTile()) {
                        waitType = calculateWaitType(Toitsu, stockTiles.get(i));
                    }
                    stockTiles.remove(i);
                    find++;
                    //logbuf.append(find + " : " + stockTiles.get(i).getFullName());
                    i--;
                }
            }
        }
    }

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

    private void newStock() {
        mentsuList.add(new ArrayList<>());
        shuntsuList.add(new ArrayList<>());
        kotsuList.add(new ArrayList<>());
        toitsuList.add(new ArrayList<>());
        kantsuList.add(new ArrayList<>());
    }

    public boolean isCanWin() {
        return canWin;
    }

    private boolean endCheck() {
        return stockTiles.size() == 0;
    }

    public ArrayList<ArrayList<kotsu>> getKotsuList() {
        return kotsuList;
    }

    public ArrayList<kotsu> getKotsuList(int Num) {
        return kotsuList.get(Num);
    }

    public ArrayList<ArrayList<toitsu>> getToitsuList() {
        return toitsuList;
    }

    public ArrayList<toitsu> getToitsuList(int Num) {
        return toitsuList.get(Num);
    }

    public ArrayList<ArrayList<shuntsu>> getShuntsuList() {
        return shuntsuList;
    }

    public ArrayList<shuntsu> getShuntsuList(int Num) {
        return shuntsuList.get(Num);
    }

    public ArrayList<ArrayList<mentsu>> getMentsuList() {
        return mentsuList;
    }

    public ArrayList<mentsu> getMentsuList(int Num) {
        return mentsuList.get(Num);
    }

    public ArrayList<ArrayList<kantsu>> getKantsuList() {
        return kantsuList;
    }

    public ArrayList<kantsu> getKantsuList(int Num) {
        return kantsuList.get(Num);
    }

    public ArrayList<WaitType> getWaitTypeList() {
        return WaitTypeList;
    }

    public WaitType getWaitTypeList(int Num) {
        return WaitTypeList.get(Num);
    }

    public String getLog() {
        return logBuf.toString();
    }
}
