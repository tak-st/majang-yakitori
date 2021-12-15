package ckn.yakitori.share.score;

import ckn.yakitori.share.mentsu.*;

import java.util.ArrayList;

/**
 * 役判定に必要な情報をまとめるクラスです。
 *
 * @author Shintani
 * @version 1.1
 */
public class statusGroup {
    /**
     * 自風 (1234:東・南・西・北)
     */
    int jikaze = 1;
    /**
     * 場風 (1234:東・南・西・北)
     */
    int bakaze = 1;
    /**
     * ツモ上がりかロン上がりか
     */
    boolean isTsumo = true;
    /**
     * リーチしていたかどうか
     */
    boolean isRiichi = false;
    /**
     * 鳴いてタンヤオを作ることが許されているか
     */
    boolean canKuitan = true;
    /**
     * 天和か
     */
    boolean isTenho = false;
    /**
     * 地和か
     */
    boolean isChiho = false;
    /**
     * 人和か
     */
    boolean isRenho = false;
    /**
     * 海底・河底か
     */
    boolean isHaitei = false;
    /**
     * 槍槓か
     */
    boolean iscyankan = false;
    /**
     * 嶺上で上がったか
     */
    boolean isRinsyan = false;
    private ArrayList<mentsu> mentsuList = new ArrayList<>();
    /**
     * 刻子を保存するリスト
     */
    private ArrayList<kotsu> kotsuList = new ArrayList<>();
    /**
     * 順子を保存するリスト
     */
    private ArrayList<shuntsu> shuntsuList = new ArrayList<>();
    /**
     * 対子を保存するリスト
     */
    private ArrayList<toitsu> toitsuList = new ArrayList<>();
    /**
     * 槓子を保存するリスト
     */
    private ArrayList<kantsu> kantsuList = new ArrayList<>();
    /**
     * 待ち形を保存するリスト
     */
    private WaitType WaitTypeList;

    /**
     * 面子分割の結果を５種のリストに一括でセットします。
     *
     * @param mentsuPartition 結果をセットしたい面子分割クラス
     * @param index           何番目の分割結果をセットするか
     */
    public statusGroup setMentsuList(mentsuPartition mentsuPartition, int index) {
        setMentsuList(mentsuPartition.getMentsuList(index));
        setKotsuList(mentsuPartition.getKotsuList(index));
        setShuntsuList(mentsuPartition.getShuntsuList(index));
        setToitsuList(mentsuPartition.getToitsuList(index));
        setWaitTypeList(mentsuPartition.getWaitTypeList(index));
        return this;
    }

    /**
     * mentsuListを取得します。
     *
     * @return mentsuList
     */
    public ArrayList<mentsu> getMentsuList() {
        return mentsuList;
    }

    /**
     * mentsuListに値をセットします。
     *
     * @param mentsuList mentsuListにセットする値
     */
    public statusGroup setMentsuList(ArrayList<mentsu> mentsuList) {
        this.mentsuList = mentsuList;
        return this;
    }

    /**
     * kotsuListを取得します。
     *
     * @return kotsuList
     */
    public ArrayList<kotsu> getKotsuList() {
        return kotsuList;
    }

    /**
     * kotsuListに値をセットします。
     *
     * @param kotsuList kotsuListにセットする値
     */
    public statusGroup setKotsuList(ArrayList<kotsu> kotsuList) {
        this.kotsuList = kotsuList;
        return this;
    }

    /**
     * shuntsuListを取得します。
     *
     * @return shuntsuList
     */
    public ArrayList<shuntsu> getShuntsuList() {
        return shuntsuList;
    }

    /**
     * shuntsuListに値をセットします。
     *
     * @param shuntsuList shuntsuListにセットする値
     */
    public statusGroup setShuntsuList(ArrayList<shuntsu> shuntsuList) {
        this.shuntsuList = shuntsuList;
        return this;
    }

    /**
     * toitsuListを取得します。
     *
     * @return toitsuList
     */
    public ArrayList<toitsu> getToitsuList() {
        return toitsuList;
    }

    /**
     * toitsuListに値をセットします。
     *
     * @param toitsuList toitsuListにセットする値
     */
    public statusGroup setToitsuList(ArrayList<toitsu> toitsuList) {
        this.toitsuList = toitsuList;
        return this;
    }

    /**
     * kantsuListを取得します。
     *
     * @return kantsuList
     */
    public ArrayList<kantsu> getKantsuList() {
        return kantsuList;
    }

    /**
     * kantsuListに値をセットします。
     *
     * @param kantsuList kantsuListにセットする値
     */
    public statusGroup setKantsuList(ArrayList<kantsu> kantsuList) {
        this.kantsuList = kantsuList;
        return this;
    }

    /**
     * 刻子と槓子両方とも同時に取得します。
     *
     * @return 刻子と槓子が両方含まれたArrayList
     * @since 1.1
     */
    public ArrayList<mentsu> getKotsuKantsuBoth() {
        ArrayList<mentsu> result = new ArrayList<>();
        for (mentsu Mentsu : getMentsuList()) {
            if (Mentsu instanceof kotsu) {
                result.add(Mentsu);
            }
            if (Mentsu instanceof kantsu) {
                result.add(Mentsu);
            }
        }
        return result;
    }

    /**
     * WaitTypeListを取得します。
     *
     * @return WaitTypeList
     */
    public WaitType getWaitTypeList() {
        return WaitTypeList;
    }

    /**
     * WaitTypeListに値をセットします。
     *
     * @param waitTypeList WaitTypeListにセットする値
     */
    public statusGroup setWaitTypeList(WaitType waitTypeList) {
        WaitTypeList = waitTypeList;
        return this;
    }

    /**
     * jikazeを取得します。
     *
     * @return jikaze
     */
    public int getJikaze() {
        return jikaze;
    }

    /**
     * jikazeに値をセットします。
     *
     * @param jikaze jikazeにセットする値
     */
    public statusGroup setJikaze(int jikaze) {
        this.jikaze = jikaze;
        return this;
    }

    /**
     * bakazeを取得します。
     *
     * @return bakaze
     */
    public int getBakaze() {
        return bakaze;
    }

    /**
     * bakazeに値をセットします。
     *
     * @param bakaze bakazeにセットする値
     */
    public statusGroup setBakaze(int bakaze) {
        this.bakaze = bakaze;
        return this;
    }

    /**
     * isTsumoを取得します。
     *
     * @return isTsumo
     */
    public boolean isTsumo() {
        return isTsumo;
    }

    /**
     * isTsumoに値をセットします。
     *
     * @param tsumo isTsumoにセットする値
     */
    public statusGroup setTsumo(boolean tsumo) {
        isTsumo = tsumo;
        return this;
    }

    /**
     * isRiichiを取得します。
     *
     * @return isRiichi
     */
    public boolean isRiichi() {
        return isRiichi;
    }

    /**
     * isRiichiに値をセットします。
     *
     * @param riichi isRiichiにセットする値
     */
    public statusGroup setRiichi(boolean riichi) {
        isRiichi = riichi;
        return this;
    }

    /**
     * isOpenを取得します。
     * 戻り値:isOpen
     */

    public boolean isOpen() {
        for (mentsu Mentsu : mentsuList) {
            if (Mentsu.isOpen()) {
                return true;
            }
        }
        return false;
    }

    /**
     * canKuitanを取得します。
     *
     * @return canKuitan
     */
    public boolean isCanKuitan() {
        return canKuitan;
    }

    /**
     * canKuitanに値をセットします。
     *
     * @param canKuitan canKuitanにセットする値
     */
    public statusGroup setCanKuitan(boolean canKuitan) {
        this.canKuitan = canKuitan;
        return this;
    }

    /**
     * isTenhoを取得します。
     *
     * @return isTenho
     */
    public boolean isTenho() {
        return isTenho;
    }

    /**
     * isTenhoに値をセットします。
     *
     * @param tenho isTenhoにセットする値
     */
    public statusGroup setTenho(boolean tenho) {
        isTenho = tenho;
        return this;
    }

    /**
     * isChihoを取得します。
     *
     * @return isChiho
     */
    public boolean isChiho() {
        return isChiho;
    }

    /**
     * isChihoに値をセットします。
     *
     * @param chiho isChihoにセットする値
     */
    public statusGroup setChiho(boolean chiho) {
        isChiho = chiho;
        return this;
    }

    /**
     * isRenhoを取得します。
     *
     * @return isRenho
     */
    public boolean isRenho() {
        return isRenho;
    }

    /**
     * isRenhoに値をセットします。
     *
     * @param renho isRenhoにセットする値
     */
    public statusGroup setRenho(boolean renho) {
        isRenho = renho;
        return this;
    }

    /**
     * isHaiteiを取得します。
     *
     * @return isHaitei
     */
    public boolean isHaitei() {
        return isHaitei;
    }

    /**
     * isHaiteiに値をセットします。
     *
     * @param haitei isHaiteiにセットする値
     */
    public statusGroup setHaitei(boolean haitei) {
        isHaitei = haitei;
        return this;
    }

    /**
     * iscyankanを取得します。
     *
     * @return iscyankan
     */
    public boolean isIscyankan() {
        return iscyankan;
    }

    /**
     * iscyankanに値をセットします。
     *
     * @param iscyankan iscyankanにセットする値
     */
    public statusGroup setIscyankan(boolean iscyankan) {
        this.iscyankan = iscyankan;
        return this;
    }

    /**
     * isRinsyanを取得します。
     *
     * @return isRinsyan
     */
    public boolean isRinsyan() {
        return isRinsyan;
    }

    /**
     * isRinsyanに値をセットします。
     *
     * @param rinsyan isRinsyanにセットする値
     */
    public statusGroup setRinsyan(boolean rinsyan) {
        isRinsyan = rinsyan;
        return this;
    }
}
