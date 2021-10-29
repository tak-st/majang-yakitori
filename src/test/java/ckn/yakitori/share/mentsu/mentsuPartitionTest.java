package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class mentsuPartitionTest {


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
    }
}