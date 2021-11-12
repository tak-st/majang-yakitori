package ckn.yakitori.share.score;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;

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

            // ここから役ごとの成立判定
        }
    }
}
