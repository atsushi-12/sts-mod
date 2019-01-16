import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;

@SpireInitializer
public class Main implements
  EditCardsSubscriber,   // �J�[�h��ǉ�����ꍇ��implement����
  EditStringsSubscriber  // ����t�@�C����ǂݍ��ޏꍇ�� implement����
{

    public Main(){
        BaseMod.subscribe(this);
    }

    // @SpireInitializer�ŏC�������N���X�͂��̃��\�b�h���`����K�v������
    public static void initialize() {
        Main main = new Main();
    }

    @Override
    public void receiveEditStrings() {
        // �Ǝ���`��������t�@�C����ǉ�
        // Settings.language�ɂ͓��{��Ȃ�JPN, �p��Ȃ�ENG�������Ă���
        BaseMod.loadCustomStringsFile(CardStrings.class, "assets/loc/cards-" + Settings.language + ".json");
    }

    @Override
    public void receiveEditCards() {
        // �Ǝ���`�����J�[�h��ǉ�
        BaseMod.addCard(new Flare());
    }
}
