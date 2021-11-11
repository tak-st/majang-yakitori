package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;

import java.util.Objects;

/**
 * 対子を扱うクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class toitsu extends mentsu {


    /**
     * 対子であることが確定している場合のコンストラクタです。
     *
     * @param identifierTile 対子になっている牌
     * @param redQuantity    面子の中にある赤ドラの数
     * @throws IllegalArgumentException 赤ドラ数がおかしい場合
     */
    public toitsu(tile identifierTile, int redQuantity) {
        if (identifierTile.isRed()) {
            //赤ドラなら赤ドラ数のとり得る値は1~2
            if (redQuantity < 1 || redQuantity > 2) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }
        } else {
            //赤ドラじゃないなら赤ドラ数のとり得る値は0~1
            if (redQuantity < 0 || redQuantity > 1) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }

        }
        this.redQuantity = redQuantity;
        this.identifierTile = identifierTile;
        this.isCheckPass = true;
    }

    /**
     * 対子かどうかわからない場合のコンストラクタです。
     *
     * @param tile1 1枚目の牌
     * @param tile2 2枚目の牌
     */
    public toitsu(tile tile1, tile tile2) {
        int red = 0;
        if (tile1.isRed()) {
            red++;
        }
        if (tile2.isRed()) {
            red++;
        }

        redQuantity = red;
        this.isCheckPass = check(tile1, tile2);
        if (this.isCheckPass) {
            this.identifierTile = tile1;
        }
    }

    /**
     * 対子として成立しているかどうかチェックするメソッドです。
     *
     * @param tile1 チェックする1枚目の牌
     * @param tile2 チェックする2枚目の牌
     * @return 対子として成立しているかどうか (Boolean)
     */
    private boolean check(tile tile1, tile tile2) {
        return Objects.equals(tile1.getFullName(), tile2.getFullName());
    }

    @Override
    public tile[] getTileFull() {
        tile tile1 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity >= 1);
        tile tile2 = new tile(identifierTile.getCategory(), identifierTile.getNumber(), redQuantity == 2);
        return new tile[]{tile1, tile2};
    }

    /**
     * 符計算の際に使用する符を取得するメソッドです。
     *
     * <p>
     * アタマが役牌であるかの判定はここでは行いません。
     * </p>
     *
     * @return 面子から得られる符のint型
     */
    @Override
    public int getFu() {
        return 0;
    }


    @Override
    public String toString() {
        return "対{" +
                identifierTile.getFullName() + "*2" +
                (redQuantity != 0 ? " r" + redQuantity : "") +
                '}';
    }
}
