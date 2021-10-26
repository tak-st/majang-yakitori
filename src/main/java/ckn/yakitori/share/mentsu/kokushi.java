package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 国士無双型を扱うクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class kokushi extends mentsu {
    /**
     * 国士無双型であることが確定している場合のコンストラクタです。
     *
     * @param identifierTile 2牌持っている牌
     * @param redQuantity    面子の中にある赤ドラの数
     * @throws IllegalArgumentException 赤ドラ数がおかしい場合
     */
    public kokushi(tile identifierTile, int redQuantity) {
        if (identifierTile.isRed()) {
            //赤ドラなら赤ドラ数のとり得る値は1~14
            if (redQuantity < 1 || redQuantity > 14) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }
        } else {
            //赤ドラじゃないなら赤ドラ数のとり得る値は0~13
            if (redQuantity < 0 || redQuantity > 13) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }

        }
        this.redQuantity = redQuantity;
        setIdentifierTile(identifierTile);
        this.isCheckPass = true;
    }


    /**
     * 国士無双型かどうかわからない場合のコンストラクタです。
     *
     * @param Hand 手牌を入力
     */
    public kokushi(hand Hand) {
        int red = 0;
        Hand.sortTile();
        String beforeTileName = "";
        this.isCheckPass = check(Hand);
        if (this.isCheckPass) {
            for (tile Tile : Hand.getContents()) {
                if (Tile.isRed()) {
                    red++;
                }
                if (Objects.equals(beforeTileName, Tile.getFullName()) || Objects.equals(Tile.getFullName(), Hand.getPickTile().getFullName())) {
                    this.identifierTile = Tile;
                } else {
                    beforeTileName = Tile.getFullName();
                }
            }
            if (Hand.getPickTile().isRed()) {
                red++;
            }
        }
        redQuantity = red;
    }

    /**
     * 対子として成立しているかどうかチェックするメソッドです。
     *
     * @param Hand チェックする手牌
     * @return 対子として成立しているかどうか (Boolean)
     */
    private boolean check(hand Hand) {
        boolean x2flag = false;
        final int[][] kokushiData = {
                {1, 9, 9, 9, 9, 9, 9, 9, 1},
                {1, 9, 9, 9, 9, 9, 9, 9, 1},
                {1, 9, 9, 9, 9, 9, 9, 9, 1},
                {1, 1, 1, 1},
                {1, 1, 1}
        };
        Hand.sortTile();
        for (tile Tile : Hand.getContents()) {
            int c;
            int n = Tile.getNumber() - 1;
            switch (Tile.getCategory()) {
                case MANZU -> c = 0;
                case SOHZU -> c = 1;
                case PINZU -> c = 2;
                case FONPAI -> c = 3;
                default -> c = 4;
            }
            if (kokushiData[c][n] == 1) {
                kokushiData[c][n]--;
            } else if (kokushiData[c][n] == 0) {
                if (x2flag) {
                    return false;
                } else {
                    x2flag = true;
                }
            } else {
                return false;
            }
        }
        tile Tile = Hand.getPickTile();
        int c;
        int n = Tile.getNumber() - 1;
        switch (Tile.getCategory()) {
            case MANZU -> c = 0;
            case SOHZU -> c = 1;
            case PINZU -> c = 2;
            case FONPAI -> c = 3;
            default -> c = 4;
        }
        if (kokushiData[c][n] == 1) {
            return x2flag;
        } else if (kokushiData[c][n] == 0) {
            return !x2flag;
        } else return false;

    }

    @Override
    public tile[] getTileFull() {
        int redQuantity = this.redQuantity;
        if (identifierTile.isRed()) {
            redQuantity--;
        }
        tile tile1 = new tile("1m" + (redQuantity >= 1 ? "r" : ""));
        tile tile2 = new tile("9m" + (redQuantity >= 2 ? "r" : ""));
        tile tile3 = new tile("1p" + (redQuantity >= 3 ? "r" : ""));
        tile tile4 = new tile("9p" + (redQuantity >= 4 ? "r" : ""));
        tile tile5 = new tile("1s" + (redQuantity >= 5 ? "r" : ""));
        tile tile6 = new tile("9s" + (redQuantity >= 6 ? "r" : ""));
        tile tile7 = new tile("1z" + (redQuantity >= 7 ? "r" : ""));
        tile tile8 = new tile("2z" + (redQuantity >= 8 ? "r" : ""));
        tile tile9 = new tile("3z" + (redQuantity >= 9 ? "r" : ""));
        tile tile10 = new tile("4z" + (redQuantity >= 10 ? "r" : ""));
        tile tile11 = new tile("5z" + (redQuantity >= 11 ? "r" : ""));
        tile tile12 = new tile("6z" + (redQuantity >= 12 ? "r" : ""));
        tile tile13 = new tile("7z" + (redQuantity >= 13 ? "r" : ""));
        tile tile14 = identifierTile;
        tile[] result = new tile[]{tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13, tile14};
        Arrays.sort(result, Comparator.comparingInt(tile::getSortID));
        return result;
    }

    @Override
    public int getFu() {
        return 0;
    }

    private void setIdentifierTile(tile identifierTile) {
        if (!identifierTile.getYaochu()) {
            throw new IllegalArgumentException("２～８の牌は識別牌になりません。");
        } else {
            this.identifierTile = identifierTile;
        }
    }
}
