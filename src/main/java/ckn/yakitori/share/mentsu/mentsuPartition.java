package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class mentsuPartition {

    ArrayList<mentsu> MentsuList = new ArrayList<>();
    //雀頭候補のリスト
    ArrayList<tile> ToitsuList = new ArrayList<tile>();
    //刻子候補のリスト
    ArrayList<tile> KotsuList = new ArrayList<tile>();
    //順子候補のリスト
    ArrayList<tile> ShuntsuList = new ArrayList<tile>();
    //完成形保存用の2次元リスト
    ArrayList<ArrayList<mentsu>> TempleList = new ArrayList<ArrayList<mentsu>>();
    private boolean canWin = false;

    public mentsuPartition(hand Hand, boolean isOpen) {
        Hand.sortTile();
        mentsu Kokushi = new kokushi(Hand);
        if (Kokushi.isCheckPass()) {
            MentsuList.add(Kokushi);
        } else {
            //七対子判定
            if (true) {

            } else {
                //雀頭抜き出し
                findToitsu(Hand);
                for (int i = 0; i < ToitsuList.size(); i++) {
                    tile remain_tile[] = removeToitsu(Hand, ToitsuList.get(i));
                    //刻子->順子パターン
                    findKotsu(remain_tile, isOpen);
                    switch (KotsuList.size()) {
                        case 0:
                            findShuntsu(remain_tile, isOpen);
                            for (int s = 0; s < ShuntsuList.size)

                    }
                    for (int j = 0; j < KotsuList.size(); j++) {
                        remain_tile = removeKotsu(remain_tile, KotsuList.get(j));
                        findKotsu(remain_tile, isOpen);
                        findShuntsu(remain_tile, isOpen);
                        for (int p = 0; p < ShuntsuList.size(); p++) {
                            remain_tile = removeShuntsu(remain_tile, ShuntsuList.get(p));
                            if (endcheck()) {
                                mentsu Toitsu = new toitsu(ToitsuList.get(i), ToitsuList.get(i));
//                                mentsu Kotsu = new kotsu(KotsuList.get(j), KotsuList.get(j), KotsuList.get(j));
//                                MentsuList.addAll(Toitsu);
                            }
                        }
                    }
                    //リセット

                    MentsuList.clear();
                    KotsuList.clear();

                    //順子->刻子パターン
//                    findShuntsu(remain_tile,);


                }
            }
        }

    }

    public ArrayList<mentsu> getMentsuList() {
        return MentsuList;
    }

    private void findToitsu(hand Hand) {

        for (tile Tile : Hand.getContents()) {
            for (int i = 1; i < 13; i++) { //提案：forが２重ガア
                tile content[] = Hand.getContents();
                if (Tile.getFullName() == content[i].getFullName()) {
                    if (!ToitsuList.contains(Tile)) {
                        ToitsuList.add(Tile);
                    }
                }
            }
        }
    }

    private void findShuntsu(tile remain_tile[], boolean isOpen) {
        for (tile Tile : remain_tile) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                }
                mentsu Shuntsu = new shuntsu(isOpen, remain_tile[i], remain_tile[i + 1], remain_tile[i + 2]);
                if (Shuntsu.isCheckPass()) {
                    if (!ShuntsuList.contains(Tile)) {
                        ShuntsuList.add(Tile);
                    }
                }
            }
        }
    }

    private void findKotsu(tile remain_tile[], boolean isOpen) {
        for (tile Tile : remain_tile) {
            for (int i = 0; i < 10; i++) {
                mentsu Kotsu = new kotsu(isOpen, remain_tile[i], remain_tile[i + 1], remain_tile[i + 2]);
                if (Kotsu.isCheckPass()) {
                    if (!KotsuList.contains(Tile)) {
                        KotsuList.add(Tile);
                    }
                }
            }
        }
    }

    private tile[] removeToitsu(hand Hand, tile Atama) {
        tile remain_hand[] = Hand.getContents();
        for (int i = 0; i < 2; i++) {
            Arrays.asList(remain_hand).remove(Atama);
        }
        return remain_hand;
    }

    private tile[] removeShuntsu(tile remain_tile[], tile Shuntsu) {
        for (int i = 0; i < 3; i++) {
            Arrays.asList(remain_tile).remove(Shuntsu);
        }
        return remain_tile;
    }

    private tile[] removeKotsu(tile remain_tile[], tile Kotsu) {
        for (int i = 0; i < 3; i++) {
            Arrays.asList(remain_tile).remove(Kotsu);
        }
        return remain_tile;
    }

    private boolean endcheck() {
        return true;
    }

}
