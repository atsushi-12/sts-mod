import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class Main implements EditCardsSubscriber {

    public Main(){
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        // logger.info("Initializing Mod");
        Main main = new Main();
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new Flare());
    }
}
