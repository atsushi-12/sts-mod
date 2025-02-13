import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import stsmodstudy.relics.*;
import stsmodstudy.cards.Flare;
import stsmodstudy.patches.*;
import stsmodstudy.patches.AbstractCardEnum;

@SpireInitializer
public class Main implements
        PostInitializeSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber {
    private static final Color EXAMPLE_COLOR_BG = CardHelper.getColor(100.0f, 50.0f, 50.0f);// カードリスト選択とかに出てくるときのバーの背景の色です。
    private static final String ATTACK_EXAMPLE = "assets/img/cards/bg_attack_512.png";// この辺は後で説明します
    private static final String SKILL_EXAMPLE = "assets/img/cards/bg_skill_512.png";// とりあえず変数名を書いてください
    private static final String POWER_EXAMPLE = "assets/img/cards/bg_power_512.png";
    private static final String ENERGY_ORB_EXAMPLE = "assets/img/cards/orb_512.png";
    private static final String ATTACK_PORT_EXAMPLE = "assets/img/cards/bg_attack_1024.png";
    private static final String SKILL_PORT_EXAMPLE = "assets/img/cards/bg_skill_1024.png";
    private static final String POWER_PORT_EXAMPLE = "assets/img/cards/bg_power_1024.png";
    private static final String ENERGY_ORB_PORT_EXAMPLE = "assets/img/cards/orb_1024.png";
    private static final String ENERGY_ORB_CARD_EXAMPLE = "assets/img/cards/orb_ui.png";

    public Main() {
        BaseMod.subscribe(this);
        BaseMod.addColor(
                // カラー追加部分です。上で設定した変数を入れていくだけ。
                AbstractCardEnum.EXAMPLE_COLOR // color
                , EXAMPLE_COLOR_BG // bgColor
                , EXAMPLE_COLOR_BG// backColor
                , EXAMPLE_COLOR_BG// frameColor
                , EXAMPLE_COLOR_BG// frameOutlineColor
                , EXAMPLE_COLOR_BG// descBoxColor
                , EXAMPLE_COLOR_BG // trailVfColor
                , EXAMPLE_COLOR_BG// glowColor
                , ATTACK_EXAMPLE// attackBg
                , SKILL_EXAMPLE// skillBg
                , POWER_EXAMPLE// powerBG
                , ENERGY_ORB_EXAMPLE// energyOrb
                , ATTACK_PORT_EXAMPLE// attackBgPortrait
                , SKILL_PORT_EXAMPLE// skillBgPortrait
                , POWER_PORT_EXAMPLE// powerBgPortrait
                , ENERGY_ORB_PORT_EXAMPLE// energyOrbPortrait
                , ENERGY_ORB_CARD_EXAMPLE// CardEnergyOrb
        );
    }

    // @SpireInitializer�ŏC�������N���X�͂��̃��\�b�h���`����K�v������
    public static void initialize() {
        Main main = new Main();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "assets/loc/cards-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "assets/loc/relics-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "assets/loc/powers-" + Settings.language + ".json");

    }

    @Override
    public void receiveEditCards() {
        // �Ǝ���`�����J�[�h��ǉ�
        BaseMod.addCard(new stsmodstudy.cards.Flare());
        BaseMod.addCard(new stsmodstudy.cards.TestAttack());
        BaseMod.addCard(new stsmodstudy.cards.TestPower());
    }

    @Override
    public void receiveEditRelics() {
        // レリックの追加
        BaseMod.addRelicToCustomPool(
                new TestRelic(),
                AbstractCardEnum.EXAMPLE_COLOR // カラーに対して追加する。共通レリックはまた別のメソッドになる
        );
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("PostInitialize処理が実行されました！");
    }
}
