package ckn.yakitori.client;

import org.junit.jupiter.api.Test;

class playerTest {

    @Test
    void changeScore() {

        player PlayScore = new player();
        PlayScore.setPlayer_score(25000);
        PlayScore.changeScore(-1000);
    }

    @Test
    void setPlayer_score() {
        player PlayScore = new player();
        PlayScore.setPlayer_score(25000);
    }

    @Test
    void getPlayer_score() {
        player PlayScore = new player();
        PlayScore.getPlayer_score();
    }
}