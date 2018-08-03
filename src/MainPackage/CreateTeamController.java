package MainPackage;

import teams.BodyPart;
import teams.Fighter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.List;

import static MainPackage.Animation.*;

/**
 * Kontroler wykorzystany do operacji na kreatorze tworzenia fighterow.
 * Za pomoca funkcji pozwala na tworzenie wlasnego fightera oraz jego zapis do daleszej gry, a to wszystko dla dwoch graczy.
 */

final public class CreateTeamController {

    /**
     * Zapis gracza pierszego dla CombatMenu.
     */
    static Fighter playerone;
    /**
     * Zapis gracza drugiego dla CombatMenu.
     */
    static Fighter playertwo;
    /**
     * Zapis wyboru do dalszego zapisania na wybranego gracza.
     */
    private Fighter fighter;
    /**
     * Aktualny wybor head.
     */
    private int headchoice = 0;
    /**
     * Aktualny wybor torso.
     */
    private int torsochoice = 0;
    /**
     * Aktualny wybor arm.
     */
    private int armchoice = 0;
    /**
     * Aktualny wybor leg.
     */
    private int legchoice = 0;
    /**
     * Aktualny wybor name, zapisany w postaci String.
     */
    private String namechoice = "Fighter";
    @FXML
    Label comLabel;
    @FXML
    Label headSpeed;
    @FXML
    Label headAttack;
    @FXML
    Label headDefence;
    @FXML
    Label headHealth;
    @FXML
    Label torsoSpeed;
    @FXML
    Label torsoAttack;
    @FXML
    Label torsoDefence;
    @FXML
    Label torsoHealth;
    @FXML
    Label armsSpeed;
    @FXML
    Label armsAttack;
    @FXML
    Label armsDefence;
    @FXML
    Label armsHealth;
    @FXML
    Label legsSpeed;
    @FXML
    Label legsAttack;
    @FXML
    Label legsDefence;
    @FXML
    Label legsHealth;
    @FXML
    AnchorPane paneMoveShow;
    @FXML
    Label moveDamagePane;
    @FXML
    Label moveNamePane;
    @FXML
    Label moveHitChancePane;
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
    private void setStats() {
        headSpeed.setText("Speed: " + Integer.toString(fighter.head.getSpeed()));
        headAttack.setText("Attack: " + Integer.toString(fighter.head.getAttack()));
        headDefence.setText("Defence: " + Integer.toString(fighter.head.getDef()));
        headHealth.setText("Health: " + Integer.toString(fighter.head.getHealth()));

        torsoSpeed.setText("Speed: " + Integer.toString(fighter.torso.getSpeed()));
        torsoAttack.setText("Attack: " + Integer.toString(fighter.torso.getAttack()));
        torsoDefence.setText("Defence: " + Integer.toString(fighter.torso.getDef()));
        torsoHealth.setText("Health: " + Integer.toString(fighter.torso.getHealth()));

        armsSpeed.setText("Speed: " + Integer.toString(fighter.arm_f.getSpeed() + playerone.arm_b.getSpeed()));
        armsAttack.setText("Attack: " + Integer.toString(fighter.arm_f.getAttack() + playerone.arm_b.getAttack()));
        armsDefence.setText("Defence: " + Integer.toString(fighter.arm_f.getDef() + playerone.arm_b.getDef()));
        armsHealth.setText("Health: " + Integer.toString(fighter.arm_f.getHealth() + playerone.arm_b.getHealth()));


        legsSpeed.setText("Speed: " + Integer.toString(fighter.leg_f.getSpeed() + playerone.leg_b.getSpeed()));
        legsAttack.setText("Attack: " + Integer.toString(fighter.leg_f.getAttack() + playerone.leg_b.getAttack()));
        legsDefence.setText("Defence: " + Integer.toString(fighter.leg_f.getDef() + playerone.leg_b.getDef()));
        legsHealth.setText("Health: " + Integer.toString(fighter.leg_f.getHealth() + playerone.leg_b.getHealth()));
    }

    /**
     * Funkcja tworzy fightera do wyboru, oraz podstawe dla graczy z dosepnych danych iniciowanych w MainController.
     * Wykorzystuje też setdata do podgladu fightera do wyboru oraz zaposuje animacje.
     */


    public void initialize() {
        fighter = new Fighter(namechoice,
                MainController.heads.get(headchoice),
                MainController.torsos.get(torsochoice),
                MainController.legs_b.get(legchoice),
                MainController.legs_f.get(legchoice),
                MainController.arms_b.get(armchoice),
                MainController.arms_f.get(armchoice));

        try {
            playerone = (Fighter) fighter.clone();
            playertwo = (Fighter) fighter.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        setData(fighter);
        Idle(head, arm_b, arm_f);
    }

    /**
     * Resetuje panel wyswietlany przy najechaniu na dany przycisk bodypart.
     */
    private void resetPanel() {
        paneMoveShow.setVisible(false);
        paneMoveShow.setVisible(true);
    }

    /**
     * Ustawia fightera do podgladu. Wstawia obrazy na scene.
     *
     * @param Fighter pobiera informacje o danym fighter.
     */
    private void setData(Fighter Fighter) {
        fighter = Fighter;

        fightername.setText(this.namechoice);
        head.setImage(fighter.head.getLook());
        torso.setImage(fighter.torso.getLook());
        arm_b.setImage(fighter.arm_b.getLook());
        arm_f.setImage(fighter.arm_f.getLook());
        leg_b.setImage(fighter.leg_b.getLook());
        leg_f.setImage(fighter.leg_f.getLook());
        setStats();
    }

    private int nextValue(int tempChoseElement){
        tempChoseElement++;
        if (tempChoseElement == 3) {
            tempChoseElement = 0;
        }
        return tempChoseElement;
    }

    private BodyPart nextBodyPart(int tempChoseElement, List<BodyPart> tempBodyPartChoseListFromMain) {
        return tempBodyPartChoseListFromMain.get(tempChoseElement);
    }

    private void nextBodyPartShow(BodyPart tempBodyPart){
        setData(fighter);
        moveNamePane.setText(tempBodyPart.getMove().getName());
        moveDamagePane.setText(Integer.toString(tempBodyPart.getMove().getDamage()));
        moveHitChancePane.setText(Integer.toString(tempBodyPart.getMove().getHitchance()) + "%");
        resetPanel();
    }

    /**
     * Funkcja dla przycisku Head, wbiera kolejne head z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     */
    public void nextHead() {
        this.headchoice = nextValue(this.headchoice);
        fighter.head = nextBodyPart(this.headchoice, MainController.heads);
        nextBodyPartShow(fighter.head);
    }

    /**
     * Funkcja dla przycisku Torso, wbiera kolejne torso z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     */
    public void nextTorso() {
        this.torsochoice = nextValue(this.torsochoice);
        fighter.torso = nextBodyPart(this.torsochoice, MainController.torsos);
        nextBodyPartShow(fighter.torso);
    }

    /**
     * Funkcja dla przycisku Arms, wbiera kolejne arms z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     * Dane dla move wybiera tylko z arm_f.
     */
    public void nextArms() {
        this.armchoice = nextValue(this.armchoice);
        fighter.arm_b = nextBodyPart(this.armchoice, MainController.arms_b);
        fighter.arm_f = nextBodyPart(this.armchoice, MainController.arms_f);
        nextBodyPartShow(fighter.arm_f);
    }

    /**
     * Funkcja dla przycisku Legs, wbiera kolejne legs z listy w MainController.
     * Pokazuje bodypart na ekranie za pomoca setdata oraz zmienia dane dotyczace move dla wybranego bodypart ktore sa wyswietlane na panelu.
     * Dane dla move wybiera tylko z leg_f.
     */
    public void nextLegs() {
        this.legchoice = nextValue(this.legchoice);
        fighter.leg_b = nextBodyPart(this.legchoice, MainController.legs_b);
        fighter.leg_f = nextBodyPart(this.legchoice, MainController.legs_f);
        nextBodyPartShow(fighter.leg_f);
    }

    private void savePlayer(Fighter player, String text){
        player.name = fightername.getText();
        player.head = fighter.head;
        player.torso = fighter.torso;
        player.arm_b = fighter.arm_b;
        player.arm_f = fighter.arm_f;
        player.leg_b = fighter.leg_b;
        player.leg_f = fighter.leg_f;
        comLabel.setText(text);
        comLabel.setVisible(true);
    }

    /**
     * Zapisuje gracza pierwszego do fighter playerone.
     * To zostaje pozniej wykorzystane w CombatMenu.
     * Dodatkowo wyswietla komunikat o zapisie.
     */

    public void savePlayerOne() {
        savePlayer(playerone, "Player one saved!");
    }

    /**
     * Zapisuje gracza drugiego do fighter playertwo.
     * To zostaje pozniej wykorzystane w CombatMenu.
     * Dodatkowo wyswietla komunikat o zapisie.
     */

    public void savePlayerTwo() {
        savePlayer(playertwo, "Player two saved!");
    }

    /**
     * Zmienia plik FXML na CombatMenu.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     *
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void fightBegin() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/CombatMenu.fxml"));
        Main.stage.getScene().setRoot(loader);
    }

    /**
     * Zmienia plik FXML na BeginGame - czyli wraca na poprzedni.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     *
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void exitTeamButtonAction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/MainMenu.Fxml"));
        Main.stage.getScene().setRoot(loader);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Arms.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */
    private void nextBodyPartMove(BodyPart tempBodyPart, MouseEvent mouseEvent){
        paneMoveShow.setVisible(true);
        moveNamePane.setText(tempBodyPart.getMove().getName());
        moveDamagePane.setText(Integer.toString(tempBodyPart.getMove().getDamage()));
        moveHitChancePane.setText(Integer.toString(tempBodyPart.getMove().getHitchance()) + "%");
        paneMoveShow.setLayoutX(mouseEvent.getSceneX() - 215);
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 15);
    }

    public void nextArmsMove(MouseEvent mouseEvent) {
        nextBodyPartMove(fighter.arm_f, mouseEvent);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Torso.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextTorsoMove(MouseEvent mouseEvent) {
        nextBodyPartMove(fighter.torso, mouseEvent);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Head.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextHeadMove(MouseEvent mouseEvent) {
        nextBodyPartMove(fighter.head, mouseEvent);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk Legs.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void nextLegsMove(MouseEvent mouseEvent) {
        nextBodyPartMove(fighter.leg_f, mouseEvent);
    }

    /**
     * Funkcja do zamykania panelu po wyjsciu kursora z zakresu przycisku.
     * Funkcja jest taka sama dla kazdego przycisku.
     */

    public void moveEnd() {
        paneMoveShow.setVisible(false);
    }

}