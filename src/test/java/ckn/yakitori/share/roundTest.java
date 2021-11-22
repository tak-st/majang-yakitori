package ckn.yakitori.share;

import org.junit.jupiter.api.Test;

class roundTest {

    @Test
    void roundStart() {
        round Round = new round();
        rule Rule = new rule();
        Rule.setSanma(true);
        Round.roundStart(Rule);

        Rule.setSanma(false);
        Round.roundStart(Rule);
    }

    //三麻用テスト
    @Test
    void sanma_getPlayer() {
        round Round = new round();
        rule Rule = new rule();

        for (int i = 0; i < 3; i++) {
            Rule.setSanma(true);
            Round.roundStart(Rule);
            Round.getPlayer(Rule);
        }
    }

    //四麻用テスト
    @Test
    void yonma_getPlayer() {
        round Round = new round();
        rule Rule = new rule();

        for (int i = 0; i < 4; i++) {
            Rule.setSanma(false);
            Round.roundStart(Rule);
            Round.getPlayer(Rule);
        }
    }
}