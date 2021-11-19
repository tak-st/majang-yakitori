package ckn.yakitori.client;

import ckn.yakitori.share.tile.tile;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;

public class network {
    Socket mySoc;
    OutputStreamWriter out;
    BufferedWriter outb;
    InputStreamReader in;
    InputStream inp = null;
    BufferedReader inb;
    ObjectInputStream Obin;
    boolean loopFlag = true;
    Thread t = new Thread(this::alive);

    public network(String address) {
        connect(address);
    }

    public void connect(String address) {
        /* 通信に使用するポート番号 */
        int PORT = 21000;
        /* 送信する文字列 */
        String Name = "いたずらガチョウ";
        try {
            System.out.println(address + " に接続します。");
            mySoc = new Socket(address, PORT);
            out = new OutputStreamWriter(mySoc.getOutputStream());
            outb = new BufferedWriter(out);
            Obin = new ObjectInputStream(mySoc.getInputStream());
            in = new InputStreamReader(mySoc.getInputStream());
            inb = new BufferedReader(in);
            inp = mySoc.getInputStream();

            String line;
            SendString("Let's Go Boys Yakitori");
            line = inb.readLine();
            if (Objects.equals(line, "サーバーへの接続に成功したようですね。")) {
                SendString("Name : " + Name);
            }
            t.start();
        } catch (UnknownHostException e) {
            System.err.println("Host not found");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }

    public String getReceive() {
        String line;
        try {
            while (true) {
                if (inp.available() != 0) {
                    line = inb.readLine();
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()) + " - IO Error : " + e);
        }
        return null;
    }

    public tile getReceiveTile() {

        try {
            while (true) {
                if (inp.available() != 0) {
                    return (tile) Obin.readObject();
                }
            }
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()) + " - IO Error : " + e);
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("クラスが見つからないようです。");
            return null;
        }
    }

    public void SendString(String s) {
        try {
            outb.write(s);
            outb.newLine();
            outb.flush();
        } catch (IOException e) {
            System.err.println("IO Error : " + e);
        }
    }

    private void alive() {
        while (loopFlag) {
            try {
                SendString("alive");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                System.err.println("割り込みによる処理中断 : " + e);
            }
        }
    }

    public void quit() {
        try {
            SendString("quit");
            //t.stop();
            outb.newLine();
            outb.flush();
            inb.close();
            in.close();
            Obin.close();
            outb.close();
            out.close();
            mySoc.close();
        } catch (IOException e) {
            System.err.println("IO Error : " + e);
        }
    }
}
