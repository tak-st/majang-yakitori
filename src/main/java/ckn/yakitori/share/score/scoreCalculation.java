package ckn.yakitori.share.score;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.yaku.*;

public class scoreCalculation {

    int han = 0;
    statusGroup sg = new statusGroup();

    scoreCalculation(hand Hand) {
        doScoreCalculation(Hand);
    }

    private void doScoreCalculation(hand Hand) {
        mentsuPartition MentsuP = new mentsuPartition(Hand);
        for (int i = 0; i < MentsuP.getMentsuList().size(); i++) {
            sg.setMentsuList(MentsuP.getMentsuList(i));
            sg.setKotsuList(MentsuP.getKotsuList(i));
            sg.setShuntsuList(MentsuP.getShuntsuList(i));
            sg.setToitsuList(MentsuP.getToitsuList(i));
            sg.setWaitTypeList(MentsuP.getWaitTypeList(i));


            // ここから役ごとの成立判定

            //1翻役
            addHan(new riichi(sg));

            addHan(new chun(sg));
            addHan(new haku(sg));
            addHan(new hatsu(sg));

            addHan(new jikaze(sg));
            addHan(new bakaze(sg));

            addHan(new tsumo(sg));
            addHan(new pinfu(sg));
            addHan(new tanyao(sg));
        }
    }

    private void addHan(yaku yaku) {
        if (yaku.isCheckPass()) {
            han = han + (!sg.isOpen() ? yaku.getYakuInfo().getHan() : yaku.getYakuInfo().getKuisagari());
        }
    }

}
