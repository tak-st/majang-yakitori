package ckn.yakitori.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

class serverTest {
    public static void main(String[] args) {
        /* 通信先のIPアドレス */
        String Address = "127.0.0.1";
        /* 通信に使用するポート番号 */
        int PORT = 25500;
        /* 送信する文字列 */
        String Message = "";
        try {
            System.out.println(Address + " にメッセージを送ります。");
            Socket mysoc = new Socket(Address, PORT);
            OutputStreamWriter out
                    = new OutputStreamWriter(mysoc.getOutputStream());
            BufferedWriter outb = new BufferedWriter(out);

            outb.write(Message);
            outb.newLine();
            outb.flush();

            InputStreamReader in
                    = new InputStreamReader(mysoc.getInputStream());
            BufferedReader inb = new BufferedReader(in);
            String line;
            line = inb.readLine();
            System.out.println(line + "<- サーバーからのメッセージ");

            inb.close();
            in.close();
            outb.close();
            out.close();
            mysoc.close();
        } catch (UnknownHostException e) {
            System.err.println("Host not found");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }
}