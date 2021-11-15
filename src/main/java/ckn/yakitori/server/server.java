package ckn.yakitori.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        int PORT = 25500;
        String henji = "焼鳥サーバーです。"; //各自変更
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("Local Host Name: " + addr.getHostName());
            System.out.println("IP Address     : " + addr.getHostAddress());
            ServerSocket mysvsoc = new ServerSocket(PORT);
            while (true) {
                //クライアントが接続してくるまで待ち続ける
                System.out.println("Server03 waiting...");
                Socket mysoc = mysvsoc.accept();
                //接続してきたホストの名前を表示
                String remotehost = mysoc.getInetAddress().getHostName();
                System.out.println(remotehost + " からメッセージです。:");
                // メッセージを受信
                InputStreamReader in
                        = new InputStreamReader(mysoc.getInputStream());
                BufferedReader inb = new BufferedReader(in);
                // 今回は1行のみ受信
                String line;
                line = inb.readLine();
                System.out.println(line);
                // メッセージに返信

                OutputStreamWriter out
                        = new OutputStreamWriter(mysoc.getOutputStream());
                BufferedWriter outb = new BufferedWriter(out);
                outb.write(henji);
                outb.newLine();

                outb.close();
                out.close();
                inb.close();
                in.close();
                mysoc.close();
                break;
            }
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }
}
