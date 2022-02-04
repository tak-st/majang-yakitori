package ckn.yakitori.client;

import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.tile;

public class mountainNetwork implements mountain {
    network Network;

    mountainNetwork(network Network) {
        this.Network = Network;
    }

    @Override
    public int getRemaingTile() {
        Network.SendString("getRemaingTile");
        return Integer.parseInt(Network.getReceive());
    }

    @Override
    public tile pickTile() {
        Network.SendString("doPickTile");
        return Network.getReceiveTile();
    }
}
