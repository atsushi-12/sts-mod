import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import basemod.abstracts.CustomCard;

// カードはCustomCardクラスを継承して定義する
public class Flare extends CustomCard {
    public static final String ID = "stsmodstudy:Flare";
    // getCardStringsで Mainクラスにて読み込んだ cards-JPN.json 内の文字列情報を取得する
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "assets/img/relics/BursterCore.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int VULNERABLE_AMT = 1;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;

    public Flare() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                AbstractCard.CardType.ATTACK,
                AbstractCard.CardColor.RED,
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = VULNERABLE_AMT;
        this.damage=this.baseDamage = ATTACK_DMG;
        
        this.setBackgroundTexture("assets/img/relics/BursterCore.png", "assets/img/relics/BursterCore.png");
        this.setBannerTexture("assets/img/relics/BursterCore.png", "assets/img/relics/BursterCore.png");
    }

    // カード使用時に発動する効果
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // ダメージを与える
        AbstractDungeon.actionManager.addToBottom(
            new com.megacrit.cardcrawl.actions.common.DamageAction(
                m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL) // 画面効果
        );
        // 脆弱化をかける
        AbstractDungeon.actionManager.addToBottom(
            new ApplyPowerAction(                                // バフ/デバフはすべて内部的にはパワー扱い
                m,
                p,
                new VulnerablePower(m, this.magicNumber, false), // 脆弱をm(=敵)にかける
                this.magicNumber,
                true,
                AbstractGameAction.AttackEffect.NONE)            // 画面効果
        );
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flare();
    }

    // カードアップグレード時の処理
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
        }
    }
}