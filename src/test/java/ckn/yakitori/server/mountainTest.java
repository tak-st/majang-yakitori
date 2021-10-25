package ckn.yakitori.server;

import ckn.yakitori.share.mountain;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static ckn.yakitori.server.mountainType.*;
import static org.junit.jupiter.api.Assertions.*;

class mountainTest {

    @Test
    @DisplayName("山作成のテスト")
    void generationTest() {
        assertDoesNotThrow(() -> new mountainEntity(YONMA));
    }

    @Test
    @DisplayName("牌をツモれるかどうかのテスト")
    void TsumoTest() {
        assertDoesNotThrow(() -> new mountainEntity(YONMA).pickTile());
    }

    @Test
    @DisplayName("SEQUENCEモードのテスト／toStringのテスト")
    void SequenceTest() {
        assertEquals("mountain{tileList=[tile{1m}, tile{1m}, tile{1m}, tile{1m}, tile{2m}, tile{2m}, tile{2m}, tile{2m}, tile{3m}, tile{3m}, tile{3m}, tile{3m}, tile{4m}, tile{4m}, tile{4m}, tile{4m}, tile{5m}, tile{5m}, tile{5m}, tile{5mr}, tile{6m}, tile{6m}, tile{6m}, tile{6m}, tile{7m}, tile{7m}, tile{7m}, tile{7m}, tile{8m}, tile{8m}, tile{8m}, tile{8m}, tile{9m}, tile{9m}, tile{9m}, tile{9m}, tile{1s}, tile{1s}, tile{1s}, tile{1s}, tile{2s}, tile{2s}, tile{2s}, tile{2s}, tile{3s}, tile{3s}, tile{3s}, tile{3s}, tile{4s}, tile{4s}, tile{4s}, tile{4s}, tile{5s}, tile{5s}, tile{5s}, tile{5sr}, tile{6s}, tile{6s}, tile{6s}, tile{6s}, tile{7s}, tile{7s}, tile{7s}, tile{7s}, tile{8s}, tile{8s}, tile{8s}, tile{8s}, tile{9s}, tile{9s}, tile{9s}, tile{9s}, tile{1p}, tile{1p}, tile{1p}, tile{1p}, tile{2p}, tile{2p}, tile{2p}, tile{2p}, tile{3p}, tile{3p}, tile{3p}, tile{3p}, tile{4p}, tile{4p}, tile{4p}, tile{4p}, tile{5p}, tile{5p}, tile{5p}, tile{5pr}, tile{6p}, tile{6p}, tile{6p}, tile{6p}, tile{7p}, tile{7p}, tile{7p}, tile{7p}, tile{8p}, tile{8p}, tile{8p}, tile{8p}, tile{9p}, tile{9p}, tile{9p}, tile{9p}, tile{1z}, tile{1z}, tile{1z}, tile{1z}, tile{2z}, tile{2z}, tile{2z}, tile{2z}, tile{3z}, tile{3z}, tile{3z}, tile{3z}, tile{4z}, tile{4z}, tile{4z}, tile{4z}, tile{5z}, tile{5z}, tile{5z}, tile{5z}, tile{6z}, tile{6z}, tile{6z}, tile{6z}, tile{7z}, tile{7z}, tile{7z}, tile{7z}], pick_number=0, contents_length=136}", new mountainEntity(SEQUENCE).toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 50, 136})
    @DisplayName("ツモった後残り牌が減少するかどうかのテスト")
    void getRemaingTileTest(int picknum) {
        mountain Mountain = new mountainEntity(YONMA);
        int beforeNum = Mountain.getRemaingTile();
        for (int i = 0; i < picknum; i++) {
            Mountain.pickTile();
        }
        assertEquals(beforeNum - picknum, Mountain.getRemaingTile());
    }

    @Nested
    @DisplayName("山が正しく生成されているかのテスト")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class mountainCheckTest{

        mountain Mountain = new mountainEntity(YONMA);
        Map<String, Integer> tilemap = new HashMap<>();
        @BeforeAll
        void beforeAll() {
            while (Mountain.getRemaingTile() > 0) {
                String pickName = Mountain.pickTile().getFullName();
                tilemap.put(pickName, tilemap.getOrDefault(pickName, 0) + 1);
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"1m", "2m", "3m", "4m", "5m", "6m", "7m", "8m", "9m", "1p", "2p", "3p", "4p", "5p", "6p", "7p", "8p", "9p", "1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z" })
        @DisplayName("適切な枚数牌が生成されているかのテスト")
        void generationTileCheck(String tileType) {
            assertEquals(4, (int)tilemap.getOrDefault(tileType,0));
        }

    }

    @Nested
    @DisplayName("三麻のテスト")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class sanmaTest {
        @Test
        @DisplayName("108牌であるか")
        void getRemaingTileSanmaTest() {
            assertEquals(108, new mountainEntity(SANMA).getRemaingTile());
        }

        @Nested
        @DisplayName("山が正しく生成されているか")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class mountainCheckTest {
            mountain Mountain = new mountainEntity(SANMA);
            Map<String, Integer> tilemap = new HashMap<>();

            @BeforeAll
            void beforeAll() {
                while (Mountain.getRemaingTile() > 0) {
                    String pickName = Mountain.pickTile().getFullName();
                    tilemap.put(pickName, tilemap.getOrDefault(pickName, 0) + 1);
                }
            }

            @ParameterizedTest
            @ValueSource(strings = {"2m", "3m", "4m", "5m", "6m", "7m", "8m" })
            @DisplayName("生成されてはいけない牌が生成されていないか")
            void notGenerationTileCheck(String tileType) {
                assertNull(tilemap.get(tileType));
            }

            @ParameterizedTest
            @ValueSource(strings = {"1m", "9m", "1p", "2p", "3p", "4p", "5p", "6p", "7p", "8p", "9p", "1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z" })
            @DisplayName("適切な枚数牌が生成されているか")
            void generationTileCheck(String tileType) {
                assertEquals(4, (int)tilemap.getOrDefault(tileType,0));
            }
        }

    }
}