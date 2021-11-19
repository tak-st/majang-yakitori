package ckn.yakitori.share.yaku;

/**
 * 役判定を行うクラスの為のインターフェースです。
 *
 * @author Shintani
 * @version 1.0
 */
public interface yaku {
    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    yakuInfo getYakuInfo();

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    boolean isCheckPass();
}
