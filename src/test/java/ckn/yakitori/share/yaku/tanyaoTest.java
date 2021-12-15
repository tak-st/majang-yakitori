package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static ckn.yakitori.share.yaku.yakuInfo.TANYAO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class tanyaoTest {


    @Test
    void getYakuInfo() {
        assertEquals(TANYAO, new tanyao(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "false, true, false, 1m , 1112222333444m5m",
            "true, true, false, 1m , 2333344455668m8m",
            "false, true, false, 1m , 1112223334m111z4m",
            "false, true, false, 1m , 2223334445559m9m",
            "false, false, false, 1m , 1112222333444m5m",
            "false, true, true, 1m , 1122333444555m2mr",
            "false, false, true, 2p, 2223334445556p6p",
            "true, true, true, 2p, 2223334445556p6p",
            "true, true, true, 3s, 3334445556667s7s",
            "true, true, true, 4s, 22334m33355p444s4m",
            "false, true, false,  , 1m9m1s9s1p9p1234567z7z",
            "false, true, false, , 1m9m1s9s1p9p1234667z7z",
    })

    @DisplayName("断么をチェック")
    void isCheckPass(boolean expected, boolean expectedKuitan, boolean expectedIsopen, tile Tile, String Hand) {

        hand content = new hand(Hand);

        //面子保存用のリスト
        ArrayList<ArrayList<mentsu>> mentsuList = new ArrayList<>();
        //面子リスト仮保存用
        ArrayList<mentsu> TempleArraylist = new ArrayList<>();
        ArrayList<mentsu> test = new ArrayList<>();
        //check結果の判定
        boolean check_bool = false;

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);

        for (int i = 0; i < M.getMentsuList().size(); i++) {
            TempleArraylist.addAll(M.getMentsuList(i));
            mentsuList.add(TempleArraylist);
            TempleArraylist = new ArrayList<>();
        }

        System.out.println("\u001b[00;32m2次元ArrayList\u001b[00m");
        System.out.println(mentsuList);
        System.out.println();

        //断么のアガりパターンがあるかチェック
        for (int i = 0; i < mentsuList.size(); i++) {
            //鳴きの処理
            if (expectedIsopen) {
                for (ArrayList<mentsu> mtList : mentsuList) {
                    int count = 0;
                    for (mentsu mt : mtList)
                        for (int k = 0; k < mtList.size(); k++) {
                            for (tile tl : mt.getTileFull()) {
                                if (Tile.getFullName().equals(tl.getFullName())) {
                                    count++;
                                }
                                if (count == 2) {
                                    mtList.set(k, new kotsu(true, Tile, Tile, Tile));
                                }
                            }
                        }
                }
            }
            sg.setCanKuitan(expectedKuitan);
            sg.setMentsuList(mentsuList.get(i));
            sg.setIsopen(mentsuList.get(i));
            tanyao Tanyao = new tanyao(sg);
            if (Tanyao.isCheckPass()) {
                check_bool = true;
                System.out.println("断么" + (i + 1) + "組目");
                System.out.println(mentsuList.get(i));
                System.out.println();
                System.out.println();
            }
        }

        //面子が存在しなかったときのテスト用
        if (mentsuList.size() == 0) {
            sg.setCanKuitan(expectedKuitan);
            sg.setMentsuList(TempleArraylist);
            sg.setIsopen(TempleArraylist);
            tanyao Tanyao = new tanyao(sg);
            check_bool = Tanyao.isCheckPass();
        }

        //断么ではなかった時のアラート
        if (!check_bool) {
            System.out.println("断么ではありません");
            System.out.println();
            System.out.println();
        }

        assertEquals(expected, check_bool, "チェック結果がおかしい");

    }

}