package ckn.yakitori.share;

import ckn.yakitori.share.tile.tile;

/**
 * 山クラスのインターフェースです。
 * <p>
 * 実際に山があってもなくても同じ処理をさせるためのものです。
 */
public interface mountain {
    int getRemaingTile();

    tile pickTile();

    String toString();
}
