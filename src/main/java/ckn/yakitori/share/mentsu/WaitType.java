package ckn.yakitori.share.mentsu;

/**
 * 待ちの種類と日本語名、符数を管理するクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public enum WaitType {
    /**
     * 単騎待ち
     */
    TANKI("単騎待ち", 2),

    /**
     * 両面待ち
     */
    RYANMEN("両面待ち", 0),
    /**
     * 間張待ち
     */
    KANCYAN("間張待ち", 2),
    /**
     * 辺張待ち
     */
    PENCYAN("辺張待ち", 2),
    /**
     * 双碰待ち
     */
    SYANPON("双碰待ち", 2),
    /**
     * 九面待ち
     */
    KYUMEN("九面待ち", 0),
    /**
     * 十三面待ち
     */
    JYUSAN("十三面待ち", 0);

    /**
     * 日本語名
     */
    private final String text;
    /**
     * 待ちによって獲得する符
     */
    private final int fu;

    WaitType(final String text, final int fu) {
        this.text = text;
        this.fu = fu;
    }

    /**
     * 日本語名を返します。
     *
     * @return String 日本語名
     */
    public String getText() {
        return text;
    }

    /**
     * 待ちによって獲得する符を返します。
     *
     * @return int 符数
     */
    public int getFu() {
        return fu;
    }

    @Override
    public String toString() {
        return text;
    }
}
