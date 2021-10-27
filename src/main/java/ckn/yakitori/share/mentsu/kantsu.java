package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;

import java.util.Objects;

/**
 * 槓子を扱うクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class kantsu extends mentsu {


    /**
     * 槓子であることが確定している場合のコンストラクタです。
     *
     * @param isOpen         trueなら明槓子
     * @param identifierTile 槓子になっている牌
     * @param redQuantity    面子の中にある赤ドラの数
     * @throws IllegalArgumentException 赤ドラ数がおかしい場合
     */
    public kantsu(boolean isOpen, tile identifierTile, int redQuantity) {
        if (identifierTile.isRed()) {
            //赤ドラなら赤ドラ数のとり得る値は1~4
            if (redQuantity < 1 || redQuantity > 4) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }
        } else {
            //赤ドラじゃないなら赤ドラ数のとり得る値は0~3
            if (redQuantity < 0 || redQuantity > 3) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }

        }
        this.redQuantity = redQuantity;
        this.identifierTile = identifierTile;
        this.isOpen = isOpen;
        this.isCheckPass = true;
    }

    /**
     * 槓子かどうかわからない場合のコンストラクタです。
     *
     * @param isOpen trueなら明槓子
     * @param tile1  1枚目の牌
     * @param tile2  2枚目の牌
     * @param tile3  3枚目の牌
     * @param tile4  4枚目の牌
     */
    public kantsu(boolean isOpen, tile tile1, tile tile2, tile tile3, tile tile4) {
        this.isOpen = isOpen;
        int red = 0;
        if (tile1.isRed()) {
            red++;
        }
        if (tile2.isRed()) {
            red++;
        }
        if (tile3.isRed()) {
            red++;
        }
        if (tile4.isRed()) {
            red++;
        }

        redQuantity = red;
        this.isCheckPass = check(tile1, tile2, tile3, tile4);
        if (this.isCheckPass) {
            this.identifierTile = tile2;
        }
    }

    /**
     * 槓子として成立しているかどうかチェックするメソッドです。
     *
     * @param tile1 チェックする1枚目の牌
     * @param tile2 チェックする2枚目の牌
     * @param tile3 チェックする3枚目の牌
     * @param tile4 チェックする4枚目の牌
     * @return 槓子として成立しているかどうか (Boolean)
     */
    private boolean check(tile tile1, tile tile2, tile tile3, tile tile4) {
        return Objects.equals(tile1.getFullName(), tile2.getFullName()) && Objects.equals(tile2.getFullName(), tile3.getFullName()) && Objects.equals(tile3.getFullName(), tile4.getFullName());
    }

    @Override
    public tile[] getTileFull() {
        tile tile1 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity >= 1);
        tile tile2 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity >= 2);
        tile tile3 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity >= 3);
        tile tile4 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity == 4);
        return new tile[]{tile1, tile2, tile3, tile4};
    }

    @Override
    public int getFu() {
        int Fu = 8;
        if (!isOpen) {
            Fu *= 2;
        }
        if (identifierTile.getYaochu()) {
            Fu *= 2;
        }
        return Fu;
    }
}
