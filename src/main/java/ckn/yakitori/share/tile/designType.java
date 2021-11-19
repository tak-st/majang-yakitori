package ckn.yakitori.share.tile;

/**
 * 標準でゲームに組み込まれている牌デザインを管理する列挙型クラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public enum designType {
    /**
     * <b>住牌</b>
     *
     *
     * <p>製作者：住田</p>
     * <p>コメント：指で書いた。</p>
     * <p>ライブチケット忘れてライブ行かれへんかった。</p>
     *
     * @since 1.0
     */
    SUMIDA("住牌", "住田", "指で書いた。\nライブチケット忘れてライブ行かれへんかった。", "sumida/normal", "sumida/red");


    private final String nameJP;
    private final String author;
    private final String comment;
    private final String urlNormal;
    private final String urlRed;

    designType(final String nameJP, final String author, final String comment, final String urlNormal, final String urlRed) {
        this.nameJP = nameJP;
        this.author = author;
        this.comment = comment;
        this.urlNormal = urlNormal;
        this.urlRed = urlRed;
    }

    /**
     * 牌デザインの日本語名を取得します。
     *
     * @return 牌デザインの日本語名
     * @since 1.0
     */
    public String getNameJP() {
        return nameJP;
    }

    /**
     * 牌デザインの作者を取得します。
     *
     * @return 牌デザインの作者
     * @since 1.0
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 牌デザインの作者のコメントを取得します。
     *
     * @return 牌デザインの作者のコメント
     * @since 1.0
     */
    public String getComment() {
        return comment;
    }

    /**
     * 通常牌のURLを取得します。
     *
     * @return 通常牌のURL
     * @since 1.0
     */
    public String getUrlNormal() {
        return urlNormal;
    }

    /**
     * 赤牌のURLを取得します。
     *
     * @return 赤牌のURL
     * @since 1.0
     */
    public String getUrlRed() {
        return urlRed;
    }
}
