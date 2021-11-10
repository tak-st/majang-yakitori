package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;
import ckn.yakitori.share.tile.tileType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 九蓮宝燈型を扱うクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class cyuren extends mentsu {
    /**
     * 九蓮宝燈型であることが確定している場合のコンストラクタです。
     *
     * @param identifierTile 2牌持っている牌
     * @param redQuantity    面子の中にある赤ドラの数
     * @throws IllegalArgumentException 赤ドラ数がおかしい場合
     */
    public cyuren(tile identifierTile, int redQuantity) {
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
     * 九蓮宝燈型かどうかわからない場合のコンストラクタです。
     *
     * @param Hand 手牌を入力
     */
    public cyuren(hand Hand) {
        int red = 0;
        Hand.sortTile();
        String beforeTileName = "";
        this.isCheckPass = check(Hand);
        if (this.isCheckPass) {
            for (tile Tile : Hand.getContents()) {
                if (Tile.isRed()) {
                    red++;
                }
                if (Tile.getNumber() != 1 && Tile.getNumber() != 9 && Objects.equals(beforeTileName, Tile.getFullName()) || Objects.equals(Tile.getFullName(), Hand.getPickTile().getFullName())) {
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
     * 九蓮宝燈として成立しているかどうかチェックするメソッドです。
     *
     * @param Hand チェックする手牌
     * @return 九蓮宝燈として成立しているかどうか (Boolean)
     */
    private boolean check(hand Hand) {
        boolean x2flag = false;
        int[] tileCount = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] tileGoal = {3, 1, 1, 1, 1, 1, 1, 1, 3};
        tileType type = Hand.getContents()[0].getCategory();

        Hand.sortTile();
        for (tile Tile : Hand.getAll()) {
            int n = Tile.getNumber() - 1;
            if (type != Tile.getCategory()) {
                return false;
            }
            tileCount[Tile.getNumber() - 1]++;
        }
        int i = 0;
        for (int Count : tileCount) {
            if (tileGoal[i] == 3) {
                if (Count != 3) {
                    return false;
                }
            }
            if (tileGoal[i] == 1) {
                if (Count == 2) {
                    if (x2flag) {
                        return false;
                    } else {
                        x2flag = true;
                    }
                } else if (Count != 1) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    @Override
    public tile[] getTileFull() {
        int redQuantity = this.redQuantity;
        if (identifierTile.isRed()) {
            redQuantity--;
        }
        char c = identifierTile.getCategoryChar();
        tile tile1 = new tile("5" + c + (redQuantity >= 1 ? "r" : ""));
        tile tile2 = new tile("4" + c + (redQuantity >= 2 ? "r" : ""));
        tile tile3 = new tile("6" + c + (redQuantity >= 3 ? "r" : ""));
        tile tile4 = new tile("3" + c + (redQuantity >= 4 ? "r" : ""));
        tile tile5 = new tile("7" + c + (redQuantity >= 5 ? "r" : ""));
        tile tile6 = new tile("2" + c + (redQuantity >= 6 ? "r" : ""));
        tile tile7 = new tile("8" + c + (redQuantity >= 7 ? "r" : ""));
        tile tile8 = new tile("1" + c + (redQuantity >= 8 ? "r" : ""));
        tile tile9 = new tile("9" + c + (redQuantity >= 9 ? "r" : ""));
        tile tile10 = new tile("1" + c + (redQuantity >= 10 ? "r" : ""));
        tile tile11 = new tile("9" + c + (redQuantity >= 11 ? "r" : ""));
        tile tile12 = new tile("1" + c + (redQuantity >= 12 ? "r" : ""));
        tile tile13 = new tile("9" + c + (redQuantity >= 13 ? "r" : ""));
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
        if (identifierTile.getCategoryChar() == 'z') {
            throw new IllegalArgumentException("字牌は識別牌になりません。");
        } else {
            this.identifierTile = identifierTile;
        }
    }

    @Override
    public String toString() {
        return "九蓮{" +
                identifierTile.getFullName() + "*2+12牌" +
                (redQuantity != 0 ? " r" + redQuantity : "") +
                (isOpen ? " <op>" : "") +
                '}';
    }
}
