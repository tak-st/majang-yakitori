package ckn.yakitori.share.mentsu;

/**
 * 槓子の種類を定義する列挙型です。
 *
 * @author Shintani
 * @version 1.0
 */
public enum kantsuType {
    /**
     * 暗カン
     */
    DARK("D"),
    /**
     * 明カン
     */
    LIGHT("L"),
    /**
     * 加カン
     */
    ADD("+");
    private final String string;

    kantsuType(final String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
