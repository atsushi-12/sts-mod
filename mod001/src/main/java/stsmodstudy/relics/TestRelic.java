package stsmodstudy.relics; //ここは自動で入ると思います

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//能力によって入れる
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;

public class TestRelic extends CustomRelic {

  public static final String ID = "stsmodstudy:TestRelic";
  public static final String IMG = "assets/img/relics/BursterCore.png";
  public static final String OUTLINE_IMG = "assets/img/relics/BursterCore.png";

  public TestRelic() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(OUTLINE_IMG),
        RelicTier.STARTER, // レアリティ。ここではスターターレリック
        LandingSound.FLAT);
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  @Override
  public AbstractRelic makeCopy() {
    return new TestRelic();
  }

  // 戦闘開始時に5の再生を得る
  @Override
  public void onEquip() {
    AbstractDungeon.rareRelicPool.remove("Dead Branch");
  }

  public void atBattleStart() {
    AbstractDungeon.actionManager.addToBottom(
        new RelicAboveCreatureAction(AbstractDungeon.player, this));
    AbstractDungeon.actionManager.addToBottom(
        new ApplyPowerAction(
            AbstractDungeon.player,
            AbstractDungeon.player,
            new RegenPower(AbstractDungeon.player, 5),
            5));
  }
}
