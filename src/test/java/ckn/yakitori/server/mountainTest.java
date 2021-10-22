package ckn.yakitori.server;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static ckn.yakitori.server.mountainType.SANMA;
import static ckn.yakitori.server.mountainType.YONMA;
import static org.junit.jupiter.api.Assertions.*;

class mountainTest {

    @Test
    @DisplayName("山作成のテスト")
    void generationTest() {
        assertDoesNotThrow(() -> new mountain(YONMA));
    }

    @Test
    @DisplayName("牌をツモれるかどうかのテスト")
    void TsumoTest() {
        assertDoesNotThrow(() -> new mountain(YONMA).pickTile());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 50, 136})
    @DisplayName("ツモった後残り牌が減少するかどうかのテスト")
    void getRemaingTileTest(int picknum) {
        mountain Mountain = new mountain(YONMA);
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

        mountain Mountain = new mountain(YONMA);
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

        @Test
        void testToString() {
            System.out.println(new mountain(YONMA));
        }
    }

    @Nested
    @DisplayName("三麻のテスト")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class sanmaTest {
        @Test
        @DisplayName("108牌であるか")
        void getRemaingTileSanmaTest() {
            assertEquals(108, new mountain(SANMA).getRemaingTile());
        }

        @Nested
        @DisplayName("山が正しく生成されているか")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class mountainCheckTest {
            mountain Mountain = new mountain(SANMA);
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