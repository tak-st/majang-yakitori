package ckn.yakitori.server;

/**
 * 山を作るときの種類を定義しています。
 */
public enum mountainType {
    /**
     * 四麻用の山を生成
     */
    YONMA,
    /**
     * 三麻用の山を生成
     *
     * <p>
     * 2m~8mを除外
     * </p>
     */
    SANMA,
    /**
     * 規則的な山を生成
     */
    SEQUENCE
}
