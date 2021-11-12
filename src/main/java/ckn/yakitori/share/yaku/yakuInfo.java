package ckn.yakitori.share.yaku;

public enum yakuInfo {

    //一般役
    TANYAO(1, 1, "断么九"),
    TSUMO(1, 0, "門前清自摸和"),
    PINFU(1, 0, "平和"),
    IIPEIKO(1, 0, "一盃口"),
    HAKU(1, 1, "白"),
    HATSU(1, 1, "發"),
    CHUN(1, 1, "中"),
    JIKAZE(1, 1, "自風牌"),
    BAKAZE(1, 1, "場風牌"),
    IPPATSU(1, 0, "一発"),
    HOUTEI(1, 1, "河底撈魚"),
    HAITEI(1, 1, "海底摸月"),
    RIICHI(1, 0, "立直"),
    DORA(1, 1, "ドラ"),
    URADORA(1, 1, "裏ドラ"),
    RINSHAN(1, 1, "嶺上開花"),
    CYANKAN(1, 1, "槍槓"),
    DOUBLE_REACH(1, 0, "W立直"),
    CYANTA(2, 1, "混全帯么九"),
    HONROUTOU(2, 2, "混老頭"),
    SANSHOKUDOUJUN(2, 1, "三色同順"),
    IKKITSUKAN(2, 1, "一気通貫"),
    TOITOIHO(2, 2, "対々和"),
    SANSYOKUDOUKO(2, 2, "三色同刻"),
    SANANKO(2, 2, "三暗刻"),
    SANKANTSU(2, 2, "三槓子"),
    SYOSANGEN(2, 2, "小三元"),
    CHITOITSU(2, 0, "七対子"),
    RYANPEIKO(3, 0, "二盃口"),
    JUNCHAN(3, 2, "純全帯么九"),
    HONITSU(3, 2, "混一色"),
    CHINITSU(6, 5, "清一色"),

    //役満
    KOKUSHIMUSO(13, 0, "国士無双"),
    KOKUSHIMUSO_JYUSAN(26, 0, "国士無双十三面待ち"),
    SUANKO(13, 0, "四暗刻"),
    SUANKO_TANKI(26, 0, "四暗刻単騎"),
    CHURENPOUTO(13, 0, "九蓮宝燈"),
    CHURENPOUTO_KYUMEN(26, 0, "純正九蓮宝燈"),
    DAISANGEN(13, 13, "大三元"),
    TSUISO(13, 13, "字一色"),
    SHOSUSHI(13, 13, "小四喜"),
    DAISUSHI(26, 26, "大四喜"),
    RYUISO(13, 13, "緑一色"),
    CHINROTO(13, 13, "清老頭"),
    SUKANTSU(13, 13, "四槓子"),
    RENHO(13, 13, "人和"),
    CHIHO(13, 13, "地和"),
    TENHO(13, 13, "天和"),
    ;


    private final int han;
    private final int kuisagari;
    private final String nameJP;
    
    yakuInfo(int han, int kuisagari, String nameJP) {
        this.han = han;
        this.kuisagari = kuisagari;
        this.nameJP = nameJP;
    }

    public int getHan() {
        return han;
    }

    public int getKuisagari() {
        return kuisagari;
    }

    public String getnameJP() {
        return nameJP;
    }
}
