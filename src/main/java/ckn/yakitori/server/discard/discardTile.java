package ckn.yakitori.server.discard;

import ckn.yakitori.share.tile.tile;

/**
 * 捨て牌に何かアクションが起こせるかを調べます。
 *
 * @author mizu
 * @version 1.0
 */
public class discardTile {

    tile Tile;

    /**
     * この牌がリーチ宣言牌かどうか
     */
    boolean isCalledRiichi;


    /**
     * この牌がツモ切りされたかどうか
     */
    boolean isImmediately;


    /**
     * この牌が鳴かれて消えているかどうか
     */
    boolean isStealed;

    /**
     * コンストラクタ　値を初期化します。
     *
     * @param Tile           捨て牌の情報
     * @param isCalledRiichi 　この牌がリーチ宣言牌かどうか
     * @param isImmediatery  この牌がツモ切りされたかどうか
     * @param isStealed      　この牌が鳴かれて消えているかどうか
     */
    discardTile(tile Tile, boolean isCalledRiichi, boolean isImmediatery, boolean isStealed) {
        this.Tile = Tile;
        this.isCalledRiichi = isCalledRiichi;
        this.isImmediately = isImmediatery;
        this.isStealed = isStealed;
    }


    public boolean getCalledRiichi() {
        return this.isCalledRiichi;
    }

    public boolean isImmediately() {
        return isImmediately;
    }

    public boolean isStealed() {
        return isStealed;
    }
}
