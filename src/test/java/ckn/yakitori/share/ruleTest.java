package ckn.yakitori.share;

import org.junit.jupiter.api.Test;

class ruleTest {

    @Test
    void getMatchLongth() {
        rule Rule = new rule();
        Rule.getMatchLongth();
    }

    @Test
    void setMatchLongth() {
        rule Rule = new rule();
        Rule.setMatchLongth(8);
    }

    @Test
    void isSanma() {
        rule Rule = new rule();
        Rule.isSanma();
    }

    @Test
    void setSanma() {
        rule Rule = new rule();
        Rule.setSanma(false);
    }

    @Test
    void getStartScore() {
        rule Rule = new rule();
        Rule.getStartScore();
    }

    @Test
    void setStartScore() {
        rule Rule = new rule();
        Rule.setStartScore(25000);
    }

    @Test
    void getWinNeedScore() {
        rule Rule = new rule();
        Rule.getWinNeedScore();
    }

    @Test
    void setWinNeedScore() {
        rule Rule = new rule();
        Rule.setWinNeedScore(30000);
    }

    @Test
    void getRed() {
        rule Rule = new rule();
        Rule.getRed();
    }

    @Test
    void setRed() {
        rule Rule = new rule();
        Rule.setRed(3);
    }

    @Test
    void isCanKuitan() {
        rule Rule = new rule();
        Rule.isCanKuitan();
    }

    @Test
    void setCanKuitan() {
        rule Rule = new rule();
        Rule.setCanKuitan(true);
    }

    @Test
    void getNeedHan() {
        rule Rule = new rule();
        Rule.getNeedHan();
    }

    @Test
    void setNeedHan() {
        rule Rule = new rule();
        Rule.setNeedHan(1);
    }
}