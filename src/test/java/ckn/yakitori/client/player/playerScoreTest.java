package ckn.yakitori.client.player;

import org.junit.jupiter.api.Test;

class playerScoreTest {


    @Test
    void changeScore() {

        playerScore PlayScore = new playerScore();
        PlayScore.setPlayer_score(25000);
        PlayScore.changeScore(1000);
    }

    @Test
    void setPlayer_score() {
        playerScore PlayScore = new playerScore();
        PlayScore.setPlayer_score(25000);
    }

    @Test
    void getPlayer_score() {
        playerScore PlayScore = new playerScore();
        PlayScore.getPlayer_score();
    }
}