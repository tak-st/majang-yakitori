package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;

public abstract class mentsu {
    protected tile identifierTile;
    protected boolean isOpen;
    protected boolean isCheckPass;
    protected int redQuantity;

    public tile getTile() {
        return getIdentifierTile();
    }

    public tile getIdentifierTile() {
        return identifierTile;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isCheckPass() {
        return isCheckPass;
    }

    public int getRedQuantity() {
        return redQuantity;
    }

    public abstract tile[] getTileFull();

    public abstract int getFu();
}
