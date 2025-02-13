
package stsmodstudy.cards;//まあここは自動で入るはず

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stsmodstudy.patches.AbstractCardEnum;//これをimportしないとカードカラーが扱えない

public class TestAttack extends CustomCard {
  public static final String ID = "stsmodstudy:TestAttack";// 言語ファイルの参照IDなので間違えないように。
  private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "assets/img/cards/card.png";// カードの絵柄です。後で説明します。
  private static final int COST = 0;// エネルギーコスト
  private static final int ATTACK_DMG = 4;// ダメージ
  private static final int UPGRADE_PLUS_DMG = 3;// アップグレードしたときのダメージ

  public TestAttack() {
    super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        CardType.ATTACK, // カードの種類
        AbstractCardEnum.EXAMPLE_COLOR, // さっきから使ってるカードカラーの変数。間違えない
        CardRarity.COMMON, // カードのレアリティ
        CardTarget.ENEMY// ターゲットが誰なのか
    );
    this.damage = this.baseDamage = ATTACK_DMG;
  }

  @Override
  public void use(AbstractPlayer p, AbstractMonster m) {
    // ダメージを与える
    AbstractDungeon.actionManager.addToBottom(
        new com.megacrit.cardcrawl.actions.common.DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL) // 画面効果
    );
  }

  @Override
  public AbstractCard makeCopy() {
    return new TestAttack();
  }

  // カードアップグレード時の処理
  @Override
  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}
