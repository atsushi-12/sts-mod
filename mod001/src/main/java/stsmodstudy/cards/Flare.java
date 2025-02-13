package stsmodstudy.cards;

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

// �J�[�h��CustomCard�N���X���p�����Ē�`����
public class Flare extends CustomCard {
    public static final String ID = "stsmodstudy:Flare";
    // getCardStrings�� Main�N���X�ɂēǂݍ��� cards-JPN.json ���̕���������擾����
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
        this.damage = this.baseDamage = ATTACK_DMG;

        this.setBackgroundTexture("assets/img/relics/BursterCore.png", "assets/img/relics/BursterCore.png");
        this.setBannerTexture("assets/img/relics/BursterCore.png", "assets/img/relics/BursterCore.png");
    }

    // �J�[�h�g�p���ɔ����������
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // �_���[�W��^����
        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.DamageAction(
                        m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL) // ��ʌ���
        );
        // �Ǝ㉻��������
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction( // �o�t/�f�o�t�͂��ׂē����I�ɂ̓p���[����
                        m,
                        p,
                        new VulnerablePower(m, this.magicNumber, false), // �Ǝ��m(=�G)�ɂ�����
                        this.magicNumber,
                        true,
                        AbstractGameAction.AttackEffect.NONE) // ��ʌ���
        );
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flare();
    }

    // �J�[�h�A�b�v�O���[�h���̏���
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
        }
    }
}