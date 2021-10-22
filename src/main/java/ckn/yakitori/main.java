package ckn.yakitori;

import ckn.yakitori.client.client;
import javafx.application.Application;

/**
 * アプリケーションを実行した時に処理されるクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class main {

    /**
     * アプリケーション実行時に処理されるメソッドです。
     *
     * @param args 起動時に渡される引数
     */
    public static void main(String[] args) {
        Application.launch(client.class);
    }
}