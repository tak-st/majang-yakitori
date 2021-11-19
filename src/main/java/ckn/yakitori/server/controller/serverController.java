package ckn.yakitori.server.controller;

import ckn.yakitori.server.mountainEntity;
import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.tile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

import static ckn.yakitori.server.mountainType.SEQUENCE;
import static ckn.yakitori.server.mountainType.YONMA;

public class serverController {

    public Label ipLabel;
    public TextField command;
    public TextArea logArea;
    public Label statusLabel;
    ServerSocket svSocket = null;
    int connectCount = 0;
    int nextid = 1;
    ChkThread chkth;
    boolean runflag = true;
    ArrayList<SrvThread> SrvThreadList = new ArrayList<>();
    ArrayList<String> statusList = new ArrayList<>();
    mountain Mountain = null;

    @FXML
    public void initialize() {
        logArea.appendText("""
                焼鳥サーバー v0.1-SNAPSHOT
                サーバーを起動しました。
                """);
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ipLabel.setText("IP : " + Objects.requireNonNull(addr).getHostAddress());
        chkth = new ChkThread(); //待受けもスレッドで行う
        chkth.start();
    }

    @FXML
    public void ipLabel_Clicked(MouseEvent actionEvent) {
        new Thread(this::ipLabel_Copy).start();
    }

    private void ipLabel_Copy() {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(addr.getHostAddress());
            clipboard.setContents(selection, null);
            Platform.runLater(
                    () -> ipLabel.setText("Copyed!"));
            Thread.sleep(2000);
            InetAddress finalAddr = addr;
            Platform.runLater(
                    () -> ipLabel.setText("IP : " + finalAddr.getHostAddress()));
        } catch (UnknownHostException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void halt() {
        try {
            //accept()を回すために自分自身に接続
            Socket socket = new Socket("127.0.0.1", 21000);
            //すぐに閉じる
            socket.close();
            connectCount--;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private void commandProcess(String string) {
        logArea.appendText(string + "\n");
        switch (string) {
            case "list" -> {
                for (String s : statusList) {
                    if (!Objects.equals(s, "")) {
                        logArea.appendText(s + "\n");
                    }
                }
            }
            case "status" -> logArea.appendText("山情報 - 残り" + Mountain.getRemaingTile() + "牌\n");
            case "statusall" -> logArea.appendText(Mountain + "\n");
            case "resume" -> {
                if (!runflag) {
                    nextid = 1;
                    connectCount = 0;
                    Platform.runLater(
                            () -> statusLabel.setText("接続待ち中"));
                    runflag = true;

                    chkth = new ChkThread(); //待受けもスレッドで行う
                    chkth.start();
                } else {
                    logArea.appendText("すでに待機中のようです。" + "\n");
                }
            }
            case "stop" -> {
                if (runflag) {
                    nextid = 1;
                    runflag = false;
                    halt();
                    chkth = null;
                    statusList.clear();
                    SrvThreadList.clear();
                } else {
                    logArea.appendText("すでに停止しているようです。" + "\n");
                }
            }
            case "quit" -> {
                runflag = false;
                halt();
                chkth = null;
                logArea.appendText("終了します…" + "\n");
                try {
                    Thread.sleep(1000);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            default -> logArea.appendText("コマンドが存在しません。" + "\n");
        }
        command.setText("");
    }

    public void command_Enter(ActionEvent actionEvent) {
        new Thread(() ->
                commandProcess(command.getText())).start();

    }

//クライアントからの接続待ちを行うために、ServerSocketクラスの
//accept()を使いますが、このメソッドは現在のスレッドをブロック
//する(現在のスレッドの処理を停止状態にする)ため、メインスレッド
//内で使うと、GUIのボタン操作などができなくなってしまいます。
//そこでスレッドを新たに作って、その中で
//accept()による待受けを行います。こうすることで待受け中でも、ボタン
//クリックに対応するなどの処理が行えます。

    class ChkThread extends Thread { //待受け用スレッド
        public void run() {
            try {
                svSocket = new ServerSocket(21000);
                logArea.appendText("接続を待っています…" + "\n");
                Platform.runLater(
                        () -> statusLabel.setText("接続待ち中"));
                while (runflag) { //接続待ち受けループ
                    Socket socket;
                    socket = svSocket.accept();
                    //accept()は、クライアントからの接続要求があるまで処理を
                    //ブロックする。
                    //接続があれば次の命令に移る。
                    //ここでは新たにスレッドを起動し、クライアントと通信する。
                    connectCount++;
                    Platform.runLater(
                            () -> statusLabel.setText(connectCount + "人が接続中"));
                    //接続先との通信はスレッドを起動して行う
                    SrvThreadList.add(nextid - 1, new SrvThread(socket, nextid));
                    statusList.add(nextid - 1, "");
                    SrvThreadList.get(nextid - 1).start();
                    nextid++;
                }
                //runflagがfalseの場合、待受けループを終了、ソケットを閉じる
                //runflagをメインスレッドなどでfalseに変化させることで、
                //待受けループを終了させる。
                //ただし、whileループを回すためには、まず実行中のaccept()の
                //ブロックを解除する必要がある。accept()は接続が行われたときに
                //ブロックが解除されるので、接続待ちを終了するためには自分自身
                //に接続することでaccept()の次の処理に進ませることができる。
                svSocket.close();
            } catch (IOException e) {
                System.out.println("ChkThread:" + e);
            }
            logArea.appendText("接続待機を停止しました。resumeで再開できます。" + "\n");
            Platform.runLater(
                    () -> statusLabel.setText("停止中"));
        } //End of run()

    } //End of class ChkThread

    class SrvThread extends Thread {
        //GUIserverの内部クラスとして定義
        Socket soc;
        String response;
        PrintStream outStream;
        BufferedReader inStream = null;
        InputStream inpStream = null;
        InputStreamReader inprStream = null;
        ObjectOutputStream obOutStream = null;
        int num;
        int timeoutCount = 0;

        public SrvThread(Socket sct, int n) {
            soc = sct;
            num = n;
            logArea.appendText("[" + num + "] Connect to "
                    + soc.getInetAddress() + "\n");
        }

        public int getTimeoutCount() {
            return timeoutCount;
        }

        public void run() {
            try {
                boolean loopflag = true;
                int timeoutTime = 120;
                String name;
                outStream = new PrintStream(soc.getOutputStream());
                inpStream = soc.getInputStream();
                inprStream = new InputStreamReader(inpStream);
                inStream = new BufferedReader(inprStream);
                obOutStream = new ObjectOutputStream(soc.getOutputStream());

                response = inStream.readLine();
                if (!Objects.equals(response, "Let's Go Boys Yakitori")) {
                    logArea.appendText("[" + num + "] " +
                            "合言葉が誤っています。" + "\n");
                } else {
                    outStream.println("サーバーへの接続に成功したようですね。");
                    outStream.flush();
                    logArea.appendText("->[" + num + "] " +
                            "Welcomeメッセージを送信" + "\n");
                    response = inStream.readLine();
                    name = response;
                    // クライアントからのメッセージを待ち、
                    // 受け取ったメッセージをそのまま返す
                    while (loopflag) {
                        String status = "[" + num + "] " + name + " < " + timeoutCount / 5 + "/" + timeoutTime + " > ";
                        timeoutCount++;
                        while (inpStream.available() != 0) {
                            timeoutCount = 0;
                            response = inStream.readLine();
                            logArea.appendText("<-[" + num + "] " + response + "\n");
                            switch (response) {
                                case "doPickTile" -> {
                                    if (Objects.isNull(Mountain)) {
                                        Mountain = new mountainEntity(YONMA);
                                    }
                                    tile T = Mountain.pickTile();
                                    obOutStream.writeObject(T);
                                    logArea.appendText("->[" + num + "] " + T + "\n");
                                }
                                case "getRemaingTile" -> {
                                    if (Objects.isNull(Mountain)) {
                                        Mountain = new mountainEntity(YONMA);
                                    }
                                    outStream.println(Mountain.getRemaingTile());
                                    outStream.flush();
                                    logArea.appendText("->[" + num + "] " + Mountain.getRemaingTile() + "\n");
                                }
                                case "createSequenceMountain" -> {
                                    if (Objects.isNull(Mountain)) {
                                        Mountain = new mountainEntity(SEQUENCE);
                                        outStream.println("作成しました。");
                                        outStream.flush();
                                        logArea.appendText("山をSequenceタイプで作成しました。" + "\n");
                                    } else {
                                        outStream.println("すでに山があります。" + Mountain);
                                        outStream.flush();
                                    }
                                }
                                case "mountainClear" -> {
                                    Mountain = null;
                                    outStream.println("山を初期化しました。");
                                    outStream.flush();
                                    logArea.appendText("山を初期化しました。" + "\n");
                                }
                                case "alive" -> {

                                }
                                case "quit" -> loopflag = false;
                                default -> {
                                    outStream.println(response);
                                    outStream.flush();
                                    logArea.appendText("->[" + num + "] " + response + "\n");
                                }
                            }
                        }
                        if (!status.equals(statusList.get(num - 1))) {
                            statusList.set(num - 1, status);
                        }
                        if (timeoutCount >= timeoutTime * 5) {
                            logArea.appendText("[" + num + "] 2分間通信がないため切断します。 " + "\n");
                            loopflag = false;
                        }
                        Thread.sleep(200); // CPUの負荷を軽減するためのsleep200ミリ秒
                    }
                }
                inStream.close();
                inprStream.close();
                inpStream.close();
                outStream.close();
                obOutStream.close();
                soc.close();
                logArea.appendText("[" + num + "] 通信スレッド停止. " + "\n");
                connectCount--;
                if (connectCount == 0) {
                    logArea.appendText("接続者がいなくなったのでリセットします。" + "\n");
                    nextid = 1;
                    Platform.runLater(
                            () -> statusLabel.setText("接続待ち中"));
                    statusList.clear();
                    SrvThreadList.clear();
                } else {
                    statusList.set(num - 1, "");
                    Platform.runLater(
                            () -> statusLabel.setText(connectCount + "人が接続中"));
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
            //End of Deffinition of SrvThread (Inner class of GUIserver class.)
        }
    }
}
