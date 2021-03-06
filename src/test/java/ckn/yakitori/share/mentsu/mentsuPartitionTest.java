package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class mentsuPartitionTest {


 /*    @Disabled
    @ParameterizedTest
    @CsvSource({
            "2m3m4m,4m5m6m,4m5m6m,3m3m3m,8m8m,2333344455668m8m",
            "5m6m7m,7m8m9m,7m8m9m,6m6m6m,5m5m,5556667778899m6m",
    })
   void test_通常の面子仕分けテスト(String mentsu1, String mentsu2, String mentsu3, String mentsu4, String mentsu5, String string) {
        int match = 0;
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(mentsu1, mentsu2, mentsu3, mentsu4, mentsu5));
        ArrayList<mentsu> Mentsu = new mentsuPartition(new hand(string)).getMentsuList();
        StringBuilder discoverMentsu = new StringBuilder();
        for (String exp : expected) {
            for (int i = 0; i < Mentsu.size(); i++) {
                tile[] tiles = Mentsu.get(i).getTileFull();
                StringBuilder tilesText = new StringBuilder();
                for (tile tile : tiles) {
                    tilesText.append(tile.getFullName());
                }
                discoverMentsu.append(tilesText);
                if (Objects.equals(tilesText.toString(), exp)) {
                    Mentsu.remove(i);
                    match++;
                    break;
                }
            }
        }
        assertEquals(expected.size(), match, discoverMentsu.toString());
    }*/

    @ParameterizedTest
    @CsvSource({
            "2333344455668m8m",
            "2233345666778m8m",
            "2234444555667m7m",
            "1112333444557m7m",
            "2222333344455m6m",
            "1122234555667m7m",
            "2233334455566m7m",
            "1133344556667m8m",
            "1112222333445m5m",
            "2255556667778m8m",
            "2333344455556m6m",
            "2256666777889m9m",
            "2244456777889m9m",
            "2244445566677m8m",
            "1222233344559m9m",
            "2222334445568m8m",
            "1112333444555m5m",
            "3334455556667m8m",
            "4445566777899m9m",
            "1111223334456m6m",
            "1144455666777m8m",
            "1122556677889m9m",
            "4444555566677m8m",
            "1222233344558m8m",
            "2223455566777m7m",
            "3334566677888m8m",
            "1112233444566m6m",
            "2222333444558m8m",
            "3334566677778m8m",
            "1112233444567m7m",
            "2223334555667m7m",
            "1122334466779m9m",
            "1112344455556m6m",
            "4445556777889m9m",
            "3344445566677m8m",
            "2223444555666m6m",
            "1122233445556m7m",
            "4445666777888m8m",
            "1112334445568m8m",
            "1122233444555m6m",
            "1144456677788m9m",
            "1112333444558m8m",
            "1133334455566m7m",
            "2223455566667m7m",
            "1112233344456m6m",
            "4456666777889m9m",
            "5566667778889m9m",
            "3333445556679m9m",
            "1155566777888m9m",
            "1133345556667m7m",
            "2222334445569m9m",
            "3334444555667m7m",
            "3355556677788m9m",
            "2223344555679m9m",
            "1112233344458m8m",
            "1112222334445m6m",
            "2222333444559m9m",
            "3444455566667m7m",
            "4444556667789m9m",
            "5555666677788m9m",
            "2244455667778m9m",
            "1112233344459m9m",
            "1111222333449m9m",
            "1133445566779m9m",
            "3334556667789m9m",
            "1112334445566m6m",
            "3333444555669m9m",
            "3344456677788m9m",
            "1112223344455m6m",
            "1166667778889m9m",
            "3334455566678m8m",
            "2223333444555m6m",
            "1112333444556m6m",
            "1156666777889m9m",
            "1122446677889m9m",
            "1122445566779m9m",
            "2244455666777m8m",
            "1222233344445m5m",
            "2223444555667m7m",
            "3344445556667m7m",
            "2233334445556m6m",
            "1112333444559m9m",
            "3344455667778m9m",
            "1112233344457m7m",
            "1111222333446m6m",
            "1112222333444m5m",
            "2223445556666m7m",
            "2222333444557m7m",
            "1122335566889m9m",
            "1144445566677m8m",
            "1112344455668m8m",
            "4455566777888m9m",
            "1112233444569m9m",
            "1133345666778m8m",
            "1111222333448m8m",
            "5556666777888m9m",
            "1123333444556m6m",
            "1133445566889m9m",
            "3334555666779m9m",
            "2223334455566m7m",
            "3444455566779m9m",
            "1122334455779m9m",
            "1122445566778m8m",
            "2233344555666m7m",
            "2223444555668m8m",
            "2223444455566m7m",
            "1122445566889m9m",
            "2223455566779m9m",
            "1111223334459m9m",
            "3334444555666m7m",
            "4444556667788m8m",
            "1111222333447m7m",
            "5555667778899m9m",
            "1111223334458m8m",
            "1111222233344m5m",
            "2223445556679m9m",
            "1112344455669m9m",
            "3355566777888m9m",
            "2233344556667m8m",
            "3356666777889m9m",
            "1222233344557m7m",
            "2244456677788m9m",
            "2223344445556m7m",
            "4445566667778m9m",
            "2255567778889m9m",
            "4445555667778m9m",
            "4455556677788m9m",
            "2223344555678m8m",
            "1122445577889m9m",
            "4445556677788m9m",
            "3344456777889m9m",
            "1144456667778m8m",
            "4445666777889m9m",
            "2233556677889m9m",
            "3333444455566m7m",
            "1122334466889m9m",
            "2223445556677m7m",
            "4445677788889m9m",
            "3334455666789m9m",
            "4445555666777m8m",
            "1122334455778m8m",
            "3366667778889m9m",
            "1111223334455m5m",
            "5556777888999m9m",
            "1144445556667m7m",
            "1145555666778m8m",
            "3334555666778m8m",
            "3334445666778m8m",
            "2223344455569m9m",
            "4455556667778m8m",
            "1122223344455m6m",
            "1112233334445m6m",
            "1134444555667m7m",
            "1122234455566m7m",
            "4444555666779m9m",
            "3355567778889m9m",
            "2223344455567m7m",
            "1112334445569m9m",
            "1133344555666m7m",
            "4445667778888m9m",
            "2233345566677m8m",
            "3345555666778m8m",
            "1112334445555m6m",
            "1133446677889m9m",
            "3355556667778m8m",
            "1144455667778m9m",
            "4445667778899m9m",
            "1112223444556m6m",
            "2255566777888m9m",
            "2245555666778m8m",
            "3344455666777m8m",
            "2223344555677m7m",
            "3334444556667m8m",
            "1155556667778m8m",
            "3334455566679m9m",
            "2255556677788m9m",
            "3334556667777m8m",
            "3334555666777m7m",
            "3333445556678m8m",
            "2223333445556m7m",
            "2223444555669m9m",
            "3333444555668m8m",
            "1133334445556m6m",
            "4445555666778m8m",
            "3334556667788m8m",
            "1112333344455m6m",
            "3334455666788m8m",
            "1122234445556m6m",
            "3334555566677m8m",
            "2223445556678m8m",
            "2233345556667m7m",
            "4445566677789m9m",
            "2333344455669m9m",
            "1133345566677m8m",
            "1122334455889m9m",
            "1144456777889m9m",
            "1133556677889m9m",
            "3333445556677m7m",
            "4555566677778m8m",
            "4445666677788m9m",
            "1112334445567m7m",
            "3344456667778m8m",
            "1112344455666m6m",
            "2244445556667m7m",
            "2222334445566m6m",
            "2223344455568m8m",
            "1122223334445m5m",
            "4445677788999m9m",
            "4455567778889m9m",
            "2244456667778m8m",
            "2266667778889m9m",
            "2223333444556m6m",
            "4466667778889m9m",
            "1112233444568m8m",
            "2233445566889m9m",
            "3334445566677m8m",
            "5666677788889m9m",
            "1155556677788m9m",
            "5556666777889m9m",
            "1111223334457m7m",
            "2222334445567m7m",
            "1155567778889m9m",
            "1122334455889m9m",
            "1122334445556m6m",
            "19m19s19p1234567z1m",
            "19m19s11p1234567z9p",
            "1112345678999m3m",
            "1112335678999m4m",
            "1112223334445m5m",
            "1112334456689m7m",
            "3334445556677m7m",
            "1245566677899m3m",
            "2233446677889m9m"
    })
    @DisplayName("清一色での面子分割テスト")
    void PartitionTest(String Hand) {
        mentsuPartition M = new mentsuPartition(new hand(Hand));
        System.out.println(M.getLog());

        System.out.println("\u001b[00;32m面子パターンが" + M.getMentsuList().size() + "個見つかりました。\u001b[00m");
        System.out.println();
        for (int i = 0; i <= M.getMentsuList().size() - 1; i++) {
            System.out.println("\u001b[00;32m" + (i + 1) + "組目\u001b[00m");
            System.out.println(M.getMentsuList(i));
            System.out.println("\u001b[00;32m順子" + M.getShuntsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getShuntsuList(i));
            System.out.println("\u001b[00;32m刻子" + M.getKotsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getKotsuList(i));
            System.out.println("\u001b[00;32m槓子" + M.getKantsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getKantsuList(i));
            System.out.println("\u001b[00;32m対子" + M.getToitsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getToitsuList(i));
            System.out.println("\u001b[00;32m待ち\u001b[00m");
            System.out.println(M.getWaitTypeList(i));
            System.out.println();
        }
        System.out.println();
        System.out.println("\u001b[00;32m2次元ArrayList\u001b[00m");
        System.out.println(M.getMentsuList());
        System.out.println("\u001b[00;32m順子\u001b[00m");
        System.out.println(M.getShuntsuList());
        System.out.println("\u001b[00;32m刻子\u001b[00m");
        System.out.println(M.getKotsuList());
        System.out.println("\u001b[00;32m槓子\u001b[00m");
        System.out.println(M.getKantsuList());
        System.out.println("\u001b[00;32m対子\u001b[00m");
        System.out.println(M.getToitsuList());
        System.out.println("\u001b[00;32m待ち\u001b[00m");
        System.out.println(M.getWaitTypeList());
        assertTrue(M.isCanWin());
    }

    @Test
    void hadakaTankiTest() {
        hand Hand = new hand("1123555666677m1mr");
        Hand.doChii(new tile("4m"), 2, 3);
        Hand.doPon(new tile("1m"));
        Hand.doLightKan(new tile("5m"));
        Hand.doDarkKan(0);
        Hand.doAddKan(2);
        Hand.sortTile();
        Hand.setContents(Hand.getAll()[1], 2);
        Hand.setContents(new tile("e"), 1);
        Hand.sortTile();
        mentsuPartition M = new mentsuPartition(Hand);
        System.out.println(M.getLog());

        System.out.println("\u001b[00;32m面子パターンが" + M.getMentsuList().size() + "個見つかりました。\u001b[00m");
        System.out.println();
        for (int i = 0; i <= M.getMentsuList().size() - 1; i++) {
            System.out.println("\u001b[00;32m" + (i + 1) + "組目\u001b[00m");
            System.out.println(M.getMentsuList(i));
            System.out.println("\u001b[00;32m順子" + M.getShuntsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getShuntsuList(i));
            System.out.println("\u001b[00;32m刻子" + M.getKotsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getKotsuList(i));
            System.out.println("\u001b[00;32m槓子" + M.getKantsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getKantsuList(i));
            System.out.println("\u001b[00;32m対子" + M.getToitsuList(i).size() + "個\u001b[00m");
            System.out.println(M.getToitsuList(i));
            System.out.println("\u001b[00;32m待ち\u001b[00m");
            System.out.println(M.getWaitTypeList(i));
            System.out.println();
        }
        System.out.println();
        System.out.println("\u001b[00;32m2次元ArrayList\u001b[00m");
        System.out.println(M.getMentsuList());
        System.out.println("\u001b[00;32m順子\u001b[00m");
        System.out.println(M.getShuntsuList());
        System.out.println("\u001b[00;32m刻子\u001b[00m");
        System.out.println(M.getKotsuList());
        System.out.println("\u001b[00;32m槓子\u001b[00m");
        System.out.println(M.getKantsuList());
        System.out.println("\u001b[00;32m対子\u001b[00m");
        System.out.println(M.getToitsuList());
        System.out.println("\u001b[00;32m待ち\u001b[00m");
        System.out.println(M.getWaitTypeList());
        assertTrue(M.isCanWin());
    }
}