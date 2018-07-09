package MainPackage;

import Teams.Fighter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Kontroler wykorzystany do operacji na kreatorze tworzenia fighterow.
 * Za pomoca funkcji pozwala na tworzenie wlasnego fightera oraz jego zapis do daleszej gry, a to wszystko dla dwoch graczy.
 */

public class CreateTeamController {

    /**
     * Zapis gracza pierszego dla CombatMenu.
     */
    static Fighter playerone;
    /**
     * Zapis gracza drugiego dla CombatMenu.
     */
    static Fighter playertwo;
    @FXML
    Label ComLabel;
    @FXML
    Label HeadSpeed;
    @FXML
    Label HeadAttack;
    @FXML
    Label HeadDefence;
    @FXML
    Label HeadHealth;
    @FXML
    Label TorsoSpeed;
    @FXML
    Label TorsoAttack;
    @FXML
    Label TorsoDefence;
    @FXML
    Label TorsoHealth;
    @FXML
    Label ArmsSpeed;
    @FXML
    Label ArmsAttack;
    @FXML
    Label ArmsDefence;
    @FXML
    Label ArmsHealth;
    @FXML
    Label LegsSpeed;
    @FXML
    Label LegsAttack;
    @FXML
    Label LegsDefence;
    @FXML
    Label LegsHealth;
    @FXML
    AnchorPane PaneMoveShow;
    @FXML
    Label MoveDamagePane;
    @FXML
    Label MoveNamePane;
    @FXML
    Label MoveHitChancePane;

    /**
     * Zapis wyboru do dalszego zapisania na wybranego gracza.
     */
    public Fighter fighter;
    /**
     * Aktualny wybor head.
     */
    public int headchoice = 0;
    /**
     * Aktualny wybor torso.
     */
    public int torsochoice = 0;
    /**
     * Aktualny wybor arm.
     */
    public int armchoice = 0;
    /**
     * Aktualny wybor leg.
     */
    public int legchoice = 0;
    /**
     * Aktualny wybor name, zapisany w postaci String.
     */
    public String namechoice = "Fighter";
    @FXML
    private ImageView head;
    @FXML
    private ImageView torso;
    @FXML
    private ImageView arm_b;
    @FXML
    private ImageView arm_f;
    @FXML
    private ImageView leg_b;
    @FXML
    private ImageView leg_f;
    @FXML
    private TextField fightername;

    /**
     * Wypisanie danych aktualnego wyboru fightera dla kazdego bodypart w odpowiednich label.
     * Funkcja ta wykorzystywana jest przy kazdej zmianie bodypart.
     */
    public void SetStats(){

         HeadSpeed.setText("Speed: " + Integer.toString(fighter.head.getSpeed()));
         HeadAttack.setText("Attack: " + Integer.toString(fighter.head.getAttack()));
         HeadDefence.setText("Defence: " + Integer.toString(fighter.head.getDef()));
         HeadHealth.setText("Health: " + Integer.toString(fighter.head.getHealth()));

         TorsoSpeed.setText("Speed: " + Integer.toString(fighter.torso.getSpeed()));
         TorsoAttack.setText("Attack: " + Integer.toString(fighter.torso.getAttack()));
         TorsoDefence.setText("Defence: " + Integer.toString(fighter.torso.getDef()));
         TorsoHealth.setText("Health: " + Integer.toString(fighter.torso.getHealth()));

         ArmsSpeed.setText("Speed: " + Integer.toString(fighter.arm_f.getSpeed()+playerone.arm_b.getSpeed()));
         ArmsAttack.setText("Attack: " + Integer.toString(fighter.arm_f.getAttack()+playerone.arm_b.getAttack()));
         ArmsDefence.setText("Defence: " + Integer.toString(fighter.arm_f.getDef()+playerone.arm_b.getDef()));
         ArmsHealth.setText("Health: " + Integer.toString(fighter.arm_f.getHealth()+playerone.arm_b.getHealth()));


         LegsSpeed.setText("Speed: " + Integer.toString(fighter.leg_f.getSpeed()+playerone.leg_b.getSpeed()));
         LegsAttack.setText("Attack: " + Integer.toString(fighter.leg_f.getAttack()+playerone.leg_b.getAttack()));
         LegsDefence.setText("Defence: " + Integer.toString(fighter.leg_f.getDef()+playerone.leg_b.getDef()));
         LegsHealth.setText("Health: " + Integer.toString(fighter.leg_f.getHealth()+playerone.leg_b.getHealth()));

    }

    /**
     * Funkcja tworzy fightera do wyboru, oraz podstawe dla graczy z dosepnych danych iniciowanych w MainController.
     * Wykorzystuje też setdata do podgladu fightera do wyboru oraz zaposuje animacje.
     */
    public void initialize(){

        fighter = new Fighter(namechoice,
                MainController.heads.get(headchoice),
                MainController.torsos.get(torsochoice),
                MainController.legs_b.get(legchoice),
                MainController.legs_f.get(legchoice),
                MainController.arms_b.get(armchoice),
                MainController.arms_f.get(armchoice));

        playerone = new Fighter(namechoice,
                MainController.heads.get(headchoice),
                MainController.torsos.get(torsochoice),
                MainController.legs_b.get(legchoice),
                MainController.legs_f.get(legchoice),
                MainController.arms_b.get(armchoice),
                MainController.arms_f.get(armchoice));

        playertwo = new Fighter(namechoice,
                MainController.heads.get(headchoice),
                MainController.torsos.get(torsochoice),
                MainController.legs_b.get(legchoice),
                MainController.legs_f.get(legchoice),
                MainController.arms_b.get(armchoice),
                MainController.arms_f.get(armchoice));

        setData(fighter);
        Animation.Idle(head, arm_b, arm_f);

    }

    /**
     * Resetuje panel wyswietlany przy najechaniu na dany przycisk bodypart.
     */
    public void ResetPanel(){

        PaneMoveShow.setVisible(false);
        PaneMoveShow.setVisible(true);

    }

    /**
     * Ustawia fightera do podgladu. Wstawia obrazy na scene.
     * @param Fighter pobiera informacje o danym fighter.
     */
    public void setData(Fighter Fighter){

        fighter = Fighter;

        fightername.setText(this.namechoice);
        head.setImage(fighter.head.getLook());
        torso.setImage(fighter.torso.getLook());
        arm_b.setImage(fighter.arm_b.getLook());
        arm_f.setImage(fighter.arm_f.getLook());
        leg_b.setImage(fighter.leg_b.getLook());
        leg_f.setImage(fighter.leg_f.getLook());
        SetStats();

    }

    /**
     * Ustawia imie dla danego wyboru fightera.
     */
    public void setName(){

        namechoice = fightername.getText();
        fighter.name = namechoice;

        setData(fighter);

    }

    /**
     * Funkcja dla przycisku Head, wbiera kolejne head z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     */
    public void nextHead(){

        this.headchoice++;
        if(this.headchoice == 3){ headchoice = 0; }

        fighter.head = MainController.heads.get(headchoice);

        setData(fighter);
        MoveNamePane.setText(fighter.head.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.head.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.head.getMove().getHitchance())+ "%");
        ResetPanel();

    }

    /**
     * Funkcja dla przycisku Torso, wbiera kolejne torso z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     */
    public void nextTorso(){

        this.torsochoice++;
        if(this.torsochoice == 3){ torsochoice = 0; }

        fighter.torso = MainController.torsos.get(torsochoice);

        setData(fighter);
        MoveNamePane.setText(fighter.torso.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.torso.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.torso.getMove().getHitchance())+ "%");
        ResetPanel();
    }

    /**
     * Funkcja dla przycisku Arms, wbiera kolejne arms z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     * Dane dla move wybiera tylko z arm_f.
     */
    public void nextArms(){

        this.armchoice++;
        if(this.armchoice == 3){ armchoice = 0; }

        fighter.arm_b = MainController.arms_b.get(armchoice);
        fighter.arm_f = MainController.arms_f.get(armchoice);

        setData(fighter);
        MoveNamePane.setText(fighter.arm_f.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.arm_f.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.arm_f.getMove().getHitchance())+ "%");
        ResetPanel();
    }

    /**
     * Funkcja dla przycisku Legs, wbiera kolejne legs z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     * Dane dla move wybiera tylko z leg_f.
     */
    public void nextLegs(){

        this.legchoice++;
        if(this.legchoice == 3){ legchoice = 0; }

        fighter.leg_b = MainController.legs_b.get(legchoice);
        fighter.leg_f = MainController.legs_f.get(legchoice);

        setData(fighter);
        MoveNamePane.setText(fighter.leg_f.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.leg_f.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.leg_f.getMove().getHitchance())+ "%");
        ResetPanel();
    }

    /**
     * Zapisuje gracza pierwszego do fighter playerone.
     * To zostaje pozniej wykorzystane w CombatMenu.
     * Dodatkowo wyswietla komunikat o zapisie.
     */

    public void savePlayerOne(){

        playerone.name = fighter.name;
        playerone.head = fighter.head;
        playerone.torso = fighter.torso;
        playerone.arm_b = fighter.arm_b;
        playerone.arm_f = fighter.arm_f;
        playerone.leg_b = fighter.leg_b;
        playerone.leg_f = fighter.leg_f;
        ComLabel.setText("Player one saved!");
        ComLabel.setVisible(true);
    }

    /**
     * Zapisuje gracza drugiego do fighter playertwo.
     * To zostaje pozniej wykorzystane w CombatMenu.
     * Dodatkowo wyswietla komunikat o zapisie.
     */

    public void savePlayerTwo(){

        playertwo.name = fighter.name;
        playertwo.head = fighter.head;
        playertwo.torso = fighter.torso;
        playertwo.arm_b = fighter.arm_b;
        playertwo.arm_f = fighter.arm_f;
        playertwo.leg_b = fighter.leg_b;
        playertwo.leg_f = fighter.leg_f;
        ComLabel.setText("Player two saved!");
        ComLabel.setVisible(true);
    }

    /**
     * Zmienia plik FXML na CombatMenu.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void fightBegin() throws IOException {

        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/CombatMenu.fxml"));
        Main.stage.getScene().setRoot(loader);

    }

    /**
     * Zmienia plik FXML na BeginGame - czyli wraca na poprzedni.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void ExitTeamButtonAction() throws IOException {

        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/BeginGameMenu.fxml"));
        Main.stage.getScene().setRoot(loader);

    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Arms.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextArmsMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(fighter.arm_f.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.arm_f.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.arm_f.getMove().getHitchance())+ "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX()-215);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY()-15);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Torso.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextTorsoMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(fighter.torso.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.torso.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.torso.getMove().getHitchance())+ "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX()-215);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY()-15);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Head.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextHeadMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(fighter.head.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.head.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.head.getMove().getHitchance())+ "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX()-215);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY()-15);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Legs.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextLegsMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(fighter.leg_f.getMove().getName());
        MoveDamagePane.setText(Integer.toString(fighter.leg_f.getMove().getDamage()));
        MoveHitChancePane.setText(Integer.toString(fighter.leg_f.getMove().getHitchance())+ "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX()-215);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY()-15);
    }

    /**
     * Funkcja do zamykania panelu po wyjsciu kursora z zakresu przycisku.
     * Funkcja jest taka sama dla kazdego przycisku.
     */

    public void MoveEnd() {
        PaneMoveShow.setVisible(false);
    }

}