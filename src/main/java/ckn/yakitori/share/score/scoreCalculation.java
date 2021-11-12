package ckn.yakitori.share.score;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.yaku.riichi;

public class scoreCalculation {
    scoreCalculation(hand Hand) {
        doScoreCalculation(Hand);
    }

    private void doScoreCalculation(hand Hand) {
        mentsuPartition MentsuP = new mentsuPartition(Hand);
        for (int i = 0; i < MentsuP.getMentsuList().size(); i++) {
            statusGroup sg = new statusGroup();
            sg.setMentsuList(MentsuP.getMentsuList(i));
            sg.setKotsuList(MentsuP.getKotsuList(i));
            sg.setShuntsuList(MentsuP.getShuntsuList(i));
            sg.setToitsuList(MentsuP.getToitsuList(i));
            sg.setWaitTypeList(MentsuP.getWaitTypeList(i));

            int han = 0;
            boolean kuisagari = false;
            for (mentsu Mentsu : sg.getMentsuList()) {
                if (Mentsu.isOpen()) {
                    kuisagari = true;
                    break;
                }
            }

            // ここから役ごとの成立判定
            riichi riichi = new riichi(sg);
            if (riichi.isCheckPass()) {
                han = han + (!kuisagari ? riichi.getYakuInfo().getHan() : riichi.getYakuInfo().getKuisagari());
            }
        }
    }
}
