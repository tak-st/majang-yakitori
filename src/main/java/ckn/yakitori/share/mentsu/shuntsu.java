package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 順子を扱うクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class shuntsu extends mentsu {

    /**
     * 面子を構成する牌 1つ目
     */
    private tile tile1;

    /**
     * 面子を構成する牌 3つ目
     */
    private tile tile3;

    /**
     * 順子であることが確定している場合のコンストラクタです。
     *
     * @param isOpen         trueなら明順子（チー）
     * @param identifierTile 順子を構成する真ん中の牌
     * @param redQuantity    面子の中にある赤ドラの数
     */
    public shuntsu(boolean isOpen, tile identifierTile, int redQuantity) {
        setIdentifierTile(identifierTile);
        boolean tile1red = false;
        boolean tile3red = false;
        // 2牌目が赤ドラか？
        if (identifierTile.isRed()) {
            //赤ドラなら赤ドラ数のとり得る値は1~3
            if (redQuantity < 1 || redQuantity > 3) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }
            if (redQuantity == 2) {
                // のこりどちらか片方が赤の場合、5に近い牌を赤ドラとする
                if (identifierTile.getNumber() <= 5) {
                    tile3red = true;
                } else {
                    tile1red = true;
                }
            } else if (redQuantity == 3) {
                // 両方とも赤
                tile1red = true;
                tile3red = true;
            }
        } else {
            //赤ドラじゃないなら赤ドラ数のとり得る値は0~2
            if (redQuantity < 0 || redQuantity > 2) {
                throw new IllegalArgumentException("赤ドラの数がおかしいです。");
            }
            if (redQuantity == 1) {
                // のこりどちらか片方が赤の場合、5に近い牌を赤ドラとする
                if (identifierTile.getNumber() <= 5) {
                    tile3red = true;
                } else {
                    tile1red = true;
                }
            } else if (redQuantity == 2) {
                // 両方とも赤
                tile1red = true;
                tile3red = true;
            }
        }

        tile1 = new tile(identifierTile.getCategory(), identifierTile.getNumber() - 1, tile1red);
        tile3 = new tile(identifierTile.getCategory(), identifierTile.getNumber() + 1, tile3red);
        this.isOpen = isOpen;
        this.isCheckPass = true;
        this.redQuantity = redQuantity;
    }

    /**
     * 順子かどうかわからない場合のコンストラクタです。
     *
     * @param isOpen trueなら明順子（チー）
     * @param tile1  1枚目の牌
     * @param tile2  2枚目の牌
     * @param tile3  3枚目の牌
     */
    public shuntsu(boolean isOpen, tile tile1, tile tile2, tile tile3) {
        this.isOpen = isOpen;
        tile[] tiles = this.sortTile(tile1, tile2, tile3);
        int red = 0;
        for (tile Tile : tiles) {
            if (Tile.isRed()) {
                red++;
            }
        }
        redQuantity = red;
        tile1 = tiles[0];
        tile2 = tiles[1];
        tile3 = tiles[2];
        this.isCheckPass = check(tile1, tile2, tile3);
        if (this.isCheckPass) {
            this.tile1 = tile1;
            setIdentifierTile(tile2);
            this.tile3 = tile3;
        }
    }

    /**
     * 順子として成立しているかどうかチェックするメソッドです。
     *
     * @param tile1 チェックする1枚目の牌
     * @param tile2 チェックする2枚目の牌
     * @param tile3 チェックする3枚目の牌
     * @return 順子として成立しているかどうか (Boolean)
     */
    private boolean check(tile tile1, tile tile2, tile tile3) {
        // 種類が違う場合、失格
        if (tile1.getCategory() != tile2.getCategory() || tile2.getCategory() != tile3.getCategory()) {
            return false;
        }
        // 字牌の場合、失格
        if (tile1.getCategoryChar() == 'z') {
            return false;
        }
        //ソートしてから判定する
        tile[] tiles = this.sortTile(tile1, tile2, tile3);
        tile1 = tiles[0];
        tile2 = tiles[1];
        tile3 = tiles[2];

        //tile1+2 == tile2+1 == tile3 の場合、合格
        return tile1.getNumber() + 1 == tile2.getNumber() && tile2.getNumber() + 1 == tile3.getNumber();
    }

    /**
     * ソートIDを使用して牌をソートします。
     *
     * @param tile1 1枚目の牌
     * @param tile2 2枚目の牌
     * @param tile3 3枚目の牌
     * @return ソートされた後のtile[]型
     */
    private tile[] sortTile(tile tile1, tile tile2, tile tile3) {
        tile[] tiles = {tile1, tile2, tile3};
        Arrays.sort(tiles, Comparator.comparingInt(tile::getSortID));
        return tiles;
    }

    /**
     * 識別牌をセットするメソッドです。
     *
     * @param identifierTile 順子の識別牌
     * @throws IllegalArgumentException 么九牌なら識別牌としてありえないので、例外を返します。
     */
    private void setIdentifierTile(tile identifierTile) throws IllegalArgumentException {
        if (identifierTile.getYaochu()) {
            throw new IllegalArgumentException("字牌・１・９の牌は識別牌になりません。");
        }
        this.identifierTile = identifierTile;
    }

    /**
     * 面子を構成する牌を全て取得するメソッドです。
     *
     * @return 面子を構成する牌、tile[]型
     */
    @Override
    public tile[] getTileFull() {
        return new tile[]{tile1, identifierTile, tile3};
    }

    /**
     * 符計算の際に使用する符を取得するメソッドです。
     *
     * @return 面子から得られる符のint型
     */
    @Override
    public int getFu() {
        return 0;
    }
}
