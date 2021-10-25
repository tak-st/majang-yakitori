package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;

/**
 * 面子のインターフェースです。
 *
 * @author Shintani
 * @version 1.0
 */
public abstract class mentsu {

    /**
     * 識別するための牌
     */
    protected tile identifierTile;

    /**
     * 明か暗か。つまり鳴いているかどうか。
     */
    protected boolean isOpen;

    /**
     * 中身が各フォーマットに適しているかのチェックを通過したかどうか。
     */
    protected boolean isCheckPass;

    /**
     * 面子の中に赤ドラが何枚あるか
     */
    protected int redQuantity;

    /**
     * 識別牌を取得するメソッドです。
     *
     * <p>
     * 順子：真ん中の牌を取得します。<br>
     * それ以外：牌の種類を取得します。
     * </p>
     * <p>
     * getIdentifierTile()と同じです。
     *
     * @return 識別するための牌（tile型)
     */
    public tile getTile() {
        return getIdentifierTile();
    }

    /**
     * 識別牌を取得するメソッドです。
     *
     * <p>
     * 順子：真ん中の牌を取得します。<br>
     * それ以外：牌の種類を取得します。
     * </p>
     * <p>
     * getTile()と同じです。
     *
     * @return 識別するための牌（tile型)
     */
    public tile getIdentifierTile() {
        return identifierTile;
    }

    /**
     * 明か暗かを取得するメソッドです。
     *
     * @return 明かどうかのboolean型
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * 中身が各フォーマットに適しているかのチェックを通過したかどうかを取得するメソッドです。
     *
     * @return チェックを通過したかどうかのboolean型
     */
    public boolean isCheckPass() {
        return isCheckPass;
    }

    /**
     * 面子の中に赤ドラが何枚あるかを取得するメソッドです。
     *
     * @return 赤ドラが何枚あるかのint型
     */
    public int getRedQuantity() {
        return redQuantity;
    }

    /**
     * 面子を構成する牌を全て取得するメソッドです。
     *
     * @return 面子を構成する牌、tile[]型
     */
    public abstract tile[] getTileFull();

    /**
     * 符計算の際に使用する符を取得するメソッドです。
     *
     * @return 面子から得られる符のint型
     */
    public abstract int getFu();
}
