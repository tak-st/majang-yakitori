package ckn.yakitori.share.tile;

/**
 * 牌の種類を定義しています。
 *
 * @author Shintani
 * @version 1.1
 */
public enum tileType {
    /**
     * 萬子
     *
     * @since 1.0
     */
    MANZU(9),
    /**
     * 筒子
     *
     * @since 1.0
     */
    PINZU(9),
    /**
     * 索子
     *
     * @since 1.0
     */
    SOHZU(9),
    /**
     * 風牌
     *
     * @since 1.0
     */
    FONPAI(4),
    /**
     * 三元牌
     *
     * @since 1.0
     */
    SANGEN(3),
    /**
     * 字牌
     *
     * @since 1.0
     */
    ZIPAI(7),
    /**
     * 牌がない状態。
     *
     * <p>鳴いた時の手牌に使用される。</p>
     *
     * @since 1.1
     */
    EMPTY(0);

    private final int maxNum;

    /**
     * maxNumで牌の最大番号が持ってこれるようにするコンストラクタです。
     *
     * @param maxNum 定数ごとに決められた最大番号
     * @since 1.1
     */
    tileType(final int maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * その種類が存在する最大番号を返します。
     *
     * @return 最大番号（int）
     * @since 1.1
     */
    public int getMaxNum() {
        return maxNum;
    }
}