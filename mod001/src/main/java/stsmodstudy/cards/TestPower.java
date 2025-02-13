package stsmodstudy.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import stsmodstudy.patches.AbstractCardEnum;
import stsmodstudy.powers.*;//さっき作ったパワーのやつ。めんどいので*

import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

public class TestPower extends CustomCard {
  public static final String ID = "stsmodstudy:TestPower";
  private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "assets/img/cards/card.png";// カードのイメージ。とりあえず使いまわす
  private static final int COST = 0;// エネルギーコスト
  // カード独自の変数
  private static final int GETMONEY = 100;
  private static final int UPGRADE_GETMONEY = 10;

  public TestPower() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        CardType.POWER,
        AbstractCardEnum.EXAMPLE_COLOR, // 何度も言うけどカラー名
        CardRarity.COMMON, // レアリティ
        CardTarget.SELF// カードの対象。パワーはだいたいSELF
    );
    this.magicNumber = this.baseMagicNumber = GETMONEY;
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    // 戦闘終了時にお金をもらう能力です
    if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
      AbstractDungeon.getCurrRoom().addGoldToRewards(this.magicNumber);
      AbstractDungeon.actionManager.addToBottom(
          new ApplyPowerAction(
              p,
              p,
              new TestPowerGold(p, this.magicNumber), // さっきつくったパワーのやつ
              this.magicNumber));
    }
  }

  @Override
  public AbstractCard makeCopy() {
    return new TestPower();
  }

  // カードアップグレード時の処理
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      this.upgradeName();
      this.upgradeDamage(UPGRADE_GETMONEY);
    }
  }
}
