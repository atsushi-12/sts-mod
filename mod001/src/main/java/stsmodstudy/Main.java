import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;

@SpireInitializer
public class Main implements
  EditCardsSubscriber,   // カードを追加する場合にimplementする
  EditStringsSubscriber  // 言語ファイルを読み込む場合に implementする
{

    public Main(){
        BaseMod.subscribe(this);
    }

    // @SpireInitializerで修飾したクラスはこのメソッドを定義する必要がある
    public static void initialize() {
        Main main = new Main();
    }

    @Override
    public void receiveEditStrings() {
        // 独自定義した言語ファイルを追加
        // Settings.languageには日本語ならJPN, 英語ならENGが入っている
        BaseMod.loadCustomStringsFile(CardStrings.class, "assets/loc/cards-" + Settings.language + ".json");
    }

    @Override
    public void receiveEditCards() {
        // 独自定義したカードを追加
        BaseMod.addCard(new Flare());
    }
}
