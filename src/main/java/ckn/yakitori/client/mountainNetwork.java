package ckn.yakitori.client;

import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.tile;

import java.io.IOException;

public class mountainNetwork implements mountain {
    network Network;

    mountainNetwork(network Network) {
        this.Network = Network;
    }

    @Override
    public int getRemaingTile() {
        try {
            Network.SendString("getRemaingTile");
            return Integer.parseInt(Network.getReceive());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public tile pickTile() {
        try {
            Network.SendString("doPickTile");
            return Network.getReceiveTile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
