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
}