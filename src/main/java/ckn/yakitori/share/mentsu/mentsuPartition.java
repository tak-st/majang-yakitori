package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public mentsuPartition(hand Hand, boolean isOpen) {
        DoPartition(Hand, isOpen);

    }

    private void DoPartition(hand Hand, boolean isOpen) {

        Hand.sortTile();

        logBuf.append(Hand).append("の 面子解析").append(NEWLINE_CODE);
        //国士
        rollbackStock(Hand);
        mentsu Kokushi = new kokushi(Hand);
        if (Kokushi.isCheckPass()) {
            logBuf.append("\u001b[00;32m国士無双です。\u001b[00m").append(NEWLINE_CODE);
            TempleList.add(Kokushi);
            canWin = true;
            return;
        }

        rollbackStock(Hand);
        List<toitsu> T = findAllToitsu();
        logBuf.append("雀頭候補数 : ").append(T.size()).append(NEWLINE_CODE);
        if (T.size() == 7) {
            logBuf.append("\u001b[00;32m七対子です。\u001b[00m").append(NEWLINE_CODE);
            TempleList.addAll(T);
            canWin = true;
            return;
        }
        for (toitsu toi : T) {
            rollbackStock(Hand, toi);
            List<shuntsu> S = findShuntsu();
            List<kotsu> K = findKotsu();

            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(順子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);
            }
            rollbackStock(Hand, toi);
            K = findKotsu();
            S = findShuntsu();
            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(刻子優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);

            }
            rollbackStock(Hand, toi);
            S = findShuntsuR();
            K = findKotsu();

            if (endCheck()) {
                logBuf.append("\u001b[00;32m").append(toi.getIdentifierTile().getFullName()).append("(逆順優先) : ○ : ").append(S).append(K).append("\u001b[00m").append(NEWLINE_CODE);
                canWin = true;
            } else {
                logBuf.append("\u001b[00;31m").append(toi.getIdentifierTile().getFullName()).append("(逆順優先) : × : ").append(S).append(K).append(" が取り出せましたが、").append(stockTiles).append(" が余りました。\u001b[00m").append(NEWLINE_CODE);
            }
        }
    }

    public List<toitsu> findAllToitsu() {
        List<toitsu> find = new ArrayList<>();
        ArrayList<String> maeToitsu = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            for (int j = i + 1; j < stockTiles.size(); j++) {
                toitsu Toitsu = new toitsu(stockTiles.get(i), stockTiles.get(j));
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

    public void rollbackStock(hand Hand) {
        stockHand = Hand;
        stockTiles = new ArrayList<>(Arrays.asList(stockHand.getAll()));
    }

    public void rollbackStock(hand Hand, toitsu removeToitsu) {
        stockHand = Hand;
        stockTiles = new ArrayList<>(Arrays.asList(stockHand.getAll()));
        int find = 0;
        for (int i = 0; i < stockTiles.size(); i++) {
            if (Objects.equals(stockTiles.get(i).getFullName(), removeToitsu.getIdentifierTile().getFullName())) {
                if (find <= 1) {
                    stockTiles.remove(i);
                    find++;
                    //logbuf.append(find + " : " + stockTiles.get(i).getFullName());
                    i--;
                }
            }
        }
    }

    public List<shuntsu> findShuntsu() {
        List<shuntsu> find = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            j:
            for (int j = i + 1; j < stockTiles.size(); j++) {
                for (int k = j + 1; k < stockTiles.size(); k++) {
                    shuntsu Shuntsu = new shuntsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    if (Shuntsu.isCheckPass()) {
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

    private List<shuntsu> findShuntsuR() {
        List<shuntsu> find = new ArrayList<>();
        for (int i = stockTiles.size() - 1; i >= 2; i--) {
            j:
            for (int j = i - 1; j >= 1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    shuntsu Shuntsu = new shuntsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    //logbuf.append(i + " - " + j + " - " + k + " : " + stockTiles.get(i).toString() + stockTiles.get(j).toString() + stockTiles.get(k).toString() + " : " + Shuntsu.isCheckPass + "");
                    if (Shuntsu.isCheckPass()) {
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

    private List<kotsu> findKotsu() {
        List<kotsu> find = new ArrayList<>();
        for (int i = 0; i < stockTiles.size(); i++) {
            j:
            for (int j = i + 1; j < stockTiles.size(); j++) {
                for (int k = j + 1; k < stockTiles.size(); k++) {
                    kotsu Kotsu = new kotsu(false, stockTiles.get(i), stockTiles.get(j), stockTiles.get(k));
                    if (Kotsu.isCheckPass()) {
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

    public boolean isCanWin() {
        return canWin;
    }

    private boolean endCheck() {
        return stockTiles.size() == 0;
    }

    public String getLog() {
        return logBuf.toString();
    }
}
