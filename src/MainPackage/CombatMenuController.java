package MainPackage;

import Teams.Fighter;
import Teams.Move;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

/**
 * Kontroler wykorzystany do operacji w oknie walki.
 * Za pomoca funkcji tworzy caly system walki. Jest to kluczowy element calej gry.
 */

public class CombatMenuController {

    /**
     * Zapis kolejki dla graczy.
     * False - kolejka gracza 1.
     * True - kolejka gracza 2.
     */
    public boolean turn;

    /**
     *  Tworzenie generatora losowych wartosci.
     */
    public Random rand = new Random();

    /**
     * Licznik tur, wykorzystywany przy wyborze kolejnosci tur.
     */
    public int TurnCounter;

    @FXML
    Label HealthOne;
    @FXML
    Label AttackOne;
    @FXML
    Label DefenceOne;
    @FXML
    Label SpeedOne;

    /**
     *  Zapis zdrowia gracza pierwszego.
     */
    public int HealthOneint;
    /**
     *  Zapis ataku gracza pierwszego.
     */
    public double AttackOneint;
    /**
     *  Zapis defensywy gracza pierwszego.
     */
    public double DefenceOneint;
    /**
     *  Zapis szybkosci gracza pierwszego.
     */
    public int SpeedOneint;

    /**
     *  Zapis ruchu pierwszego gracza pierwszego.
     */
    public Move Move1FighterOne;
    /**
     *  Zapis ruchu drugiego gracza pierwszego.
     */
    public Move Move2FighterOne;
    /**
     *  Zapis ruchu trzeciego gracza pierwszego.
     */
    public Move Move3FighterOne;
    /**
     *  Zapis ruchu czwartego gracza pierwszego.
     */
    public Move Move4FighterOne;

    @FXML
    Label HealthTwo;
    @FXML
    Label AttackTwo;
    @FXML
    Label DefenceTwo;
    @FXML
    Label SpeedTwo;

    /**
     *  Zapis zdrowia gracza drugiego.
     */
    public int HealthTwoint;
    /**
     *  Zapis ataku gracza drugiego.
     */
    public double AttackTwoint;
    /**
     *  Zapis defensywy gracza drugiego.
     */
    public double DefenceTwoint;
    /**
     *  Zapis szybkosci gracza drugiego.
     */
    public int SpeedTwoint;

    /**
     *  Zapis ruchu pierwszego gracza drugiego.
     */
    public Move Move1FighterTwo;
    /**
     *  Zapis ruchu drugiego gracza drugiego.
     */
    public Move Move2FighterTwo;
    /**
     *  Zapis ruchu trzeciego gracza drugiego.
     */
    public Move Move3FighterTwo;
    /**
     *  Zapis ruchu czwartego gracza drugiego.
     */
    public Move Move4FighterTwo;

    @FXML
    ImageView p1head;
    @FXML
    ImageView p1torso;
    @FXML
    ImageView p1arm_b;
    @FXML
    ImageView p1arm_f;
    @FXML
    ImageView p1leg_b;
    @FXML
    ImageView p1leg_f;
    @FXML
    Text p1name;

    @FXML
    ImageView p2head;
    @FXML
    ImageView p2torso;
    @FXML
    ImageView p2arm_b;
    @FXML
    ImageView p2arm_f;
    @FXML
    ImageView p2leg_b;
    @FXML
    ImageView p2leg_f;
    @FXML
    Text p2name;

    @FXML
    Button EndGameButton;
    @FXML
    AnchorPane playerOneMenu;
    @FXML
    AnchorPane playerTwoMenu;
    @FXML
    Group fighterHeaderOne;
    @FXML
    Group fighterHeaderTwo;

    @FXML
    AnchorPane p1group;
    @FXML
    AnchorPane p2group;

    @FXML
    Label EndGame;

    @FXML
    Button playerOneButton1;
    @FXML
    Button playerOneButton2;
    @FXML
    Button playerOneButton3;
    @FXML
    Button playerOneButton4;

    @FXML
    Button playerTwoButton1;
    @FXML
    Button playerTwoButton2;
    @FXML
    Button playerTwoButton3;
    @FXML
    Button playerTwoButton4;

    @FXML
    ProgressBar healthBarOne;
    @FXML
    ProgressBar healthBarTwo;

    @FXML
    Label damageOne;
    @FXML
    Label damageTwo;

    @FXML
    Rectangle background;
    @FXML
    AnchorPane PaneMoveShow;
    @FXML
    Label MoveDamagePane;
    @FXML
    Label MoveNamePane;
    @FXML
    Label MoveHitChancePane;

    /**
     * Losuje wartosci od 0 do 100 do generowania kolejnosci w kolejnej ruchu turze.
     * @return losowa wartosc od 0 do 100.
     */
    public int Random() {
        return rand.nextInt(100);
    }

    /**
     * Funkcja ma za zadnie sprawdzic kto bedzie pierwszy w kolejnej turze.
     * Po wykonaniu kolejki gracza x i y, losuje kto bedzie nastepny w koljnej turze.
     * Wartosc od 0 do 100 dodaje do wartosci speed fightera x i porownuje je z wartosciami uzyskanymi w ten sam sposob dla fightera y.
     * Kto ma wieksza wartosc zaczyna jako pierwszy kolejna ture.
     */

    public void CheckTurn() {
        int randomTurnPlayerOne = Random();
        int randomTurnPlayerTwo = Random();
        turn = (randomTurnPlayerOne + SpeedOneint) <= (randomTurnPlayerTwo + SpeedTwoint);
    }

    /**
     * Funkcja do zmiany kolejki jak i tury dla graczy.
     * Zawsze zmienia kolejke na nastepna
     * Dodaje kolejna ture do TurnCounter oraz co 2 tury sprawdza CheckTurn.
     * Na tej podstawie wybiera nastepnego gracza pierwszego.
     * Wykonuje to wszystko o ile gra sie jeszcze nie zakonczyla.
     */
    public void changeTurn() {
        turn = !turn;
        ChangeStats();
        if (!EndGame.isVisible()) {
            TurnCounter++;
            if (TurnCounter % 2 == 0)
                CheckTurn();
            CheckPlayer();
            if (turn)
                playerTwoMenu.setDisable(false);
            else
                playerOneMenu.setDisable(false);
        }
    }

    /**
     * Zmienia statystyki graczy na nowe.
     * Wykorzystywane przy wykonaniu odpowiedniego move w kolejce.
     * W grze co prawda nie ma move ktore zmieniaja statystyki attack, defence czy speed, jednak zostalo to stworzone z mysla rowniez i o takich move.
     * Pozniej sprawdza czy nie nastapil koniec gry.
     */

    public void ChangeStats() {
        HealthOne.setText("Health: " + Integer.toString(HealthOneint) + "/" + Integer.toString(CreateTeamController.playerone.getFullHealth()));
        healthBarOne.setProgress((float) HealthOneint / CreateTeamController.playerone.getFullHealth());
        AttackOne.setText("Attack: " + Integer.toString((int) AttackOneint));
        DefenceOne.setText("Defense: " + Integer.toString((int) DefenceOneint));
        SpeedOne.setText("Speed: " + Integer.toString(SpeedOneint));

        HealthTwo.setText("Health: " + Integer.toString(HealthTwoint) + "/" + Integer.toString(CreateTeamController.playerone.getFullHealth()));
        healthBarTwo.setProgress((float) HealthTwoint / CreateTeamController.playertwo.getFullHealth());
        AttackTwo.setText("Attack: " + Integer.toString((int) AttackTwoint));
        DefenceTwo.setText("Defence: " + Integer.toString((int) DefenceTwoint));
        SpeedTwo.setText("Speed: " + Integer.toString(SpeedTwoint));

        CheckEndGame();
    }

    /**
     * Sprawdza czy Health ktoregos z graczy po wykonaniu move nie jest mniejsze od 0.
     * Jeżeli jest to konczy gre i wyswietla odpowiedni komunikat.
     * Wylacza rowniez wszystkie przyciski na scenie i dodaje nowy - zakonczenie gry.
     * Funkcja wykorzystywana w ChangeStats.
     */

    public void CheckEndGame() {
        if (HealthOneint < 0) {
            HealthOneint = 0;
            HealthOne.setText("Health: " + Integer.toString(HealthOneint) + "/" + Integer.toString(CreateTeamController.playerone.getFullHealth()));
            healthBarOne.setProgress((float) HealthOneint / CreateTeamController.playerone.getFullHealth());
            EndGame.setText("PLAYER 2 WINS");
            EndGame.setVisible(true);
            playerTwoMenu.setDisable(true);
            playerOneMenu.setDisable(true);
            fighterHeaderTwo.setVisible(false);
            fighterHeaderOne.setVisible(false);
            EndGameButton.setVisible(true);
        }

        if (HealthTwoint < 0) {
            HealthTwoint = 0;
            HealthTwo.setText("Health: " + Integer.toString(HealthTwoint) + "/" + Integer.toString(CreateTeamController.playerone.getFullHealth()));
            healthBarTwo.setProgress((float) HealthTwoint / CreateTeamController.playertwo.getFullHealth());
            EndGame.setText("PLAYER 1 WINS");
            EndGame.setVisible(true);
            playerTwoMenu.setDisable(true);
            playerOneMenu.setDisable(true);
            fighterHeaderTwo.setVisible(false);
            fighterHeaderOne.setVisible(false);
            EndGameButton.setVisible(true);
        }

    }

    /**
     * Odpowiednio zmienia mozliwosc klikania przyciskow oraz headery dla postaci w zaleznosci od kolejki.
     * Jest tu aby w kolejce gracza pierwszego, gracz drugi nie mogl nic robic.
     * Funkcja wykorzystana w changeturn.
     */

    public void CheckPlayer() {
        if (!turn) {
            playerTwoMenu.setDisable(true);
            playerOneMenu.setDisable(false);
            fighterHeaderTwo.setVisible(false);
            fighterHeaderOne.setVisible(true);
        } else {
            playerTwoMenu.setDisable(false);
            playerOneMenu.setDisable(true);
            fighterHeaderTwo.setVisible(true);
            fighterHeaderOne.setVisible(false);
        }
    }

    /**
     * Funkcja wykonuje atak gracza pierwszego na gracza drugiego.
     * Na poczatku oblicza trafienie move - generuje wartosc od 1 do 100 i porownuje z wartoscia hitchance danego move.
     * Jezeli jest obliczona wartosc jest w przedziale od 1 do hitmove, wtedy atak trafia.
     * W razie nie trafienia wyswietla odpowiednie komunikaty i animacje uniku gracza przeciwnego.
     * Jezeli trafi to zadaje obrazenia.
     * Obrazenia obliczane sa za pomoca wzoru (AttackOneint / DefenceTwoint) * MoveDamage.
     * Liczba zapisana w obrazeniach nastepnie jest odejmowana od puli zdrowia przeciwnika i nastepuje odpowiednia animacja i komunikat.
     * @param move za jego pomoca pobierane sa informacie o danym move.
     */

    public void AttackTargetPlayerOne(Move move) {
        double DAMAGE;
        double MoveDamage = move.getDamage();
        int Hit = rand.nextInt(100) + 1;

        if (Hit > move.getHitchance()) {
            DAMAGE = 0;
            damageTwo.setVisible(true);
            damageTwo.setText("MISS");
            AnimationPlayerOne(false);
            Animation.DamageAnimate(damageTwo);
        } else {
            DAMAGE = (AttackOneint / DefenceTwoint) * MoveDamage;
            damageTwo.setVisible(true);
            damageTwo.setText((int) DAMAGE + " DMG");
            AnimationPlayerOne(true);
            Animation.DamageAnimate(damageTwo);
        }
        HealthTwoint = HealthTwoint - (int) DAMAGE;
    }

    /**
     * Funkcja wykonuje atak gracza drugiego na gracza pierwszego.
     * Na poczatku oblicza trafienie move - generuje wartosc od 1 do 100 i porownuje z wartoscia hitchance danego move.
     * Jezeli jest obliczona wartosc jest w przedziale od 1 do hitmove, wtedy atak trafia.
     * W razie nie trafienia wyswietla odpowiednie komunikaty i animacje uniku gracza przeciwnego.
     * Jezeli trafi to zadaje obrazenia.
     * Obrazenia obliczane sa za pomoca wzoru (AttackOneint / DefenceTwoint) * MoveDamage.
     * Liczba zapisana w obrazeniach nastepnie jest odejmowana od puli zdrowia przeciwnika i nastepuje odpowiednia animacja i komunikat.
     * @param move za jego pomoca pobierane sa informacie o danym move.
     */

    public void AttackTargetPlayerTwo(Move move) {
        double DAMAGE;
        double MoveDamage = move.getDamage();
        int Hit = rand.nextInt(100) + 1;

        if (Hit > move.getHitchance()) {
            DAMAGE = 0;
            damageOne.setVisible(true);
            damageOne.setText("MISS");
            AnimationPlayerTwo(false);
            Animation.DamageAnimate(damageOne);
        } else {
            DAMAGE = (AttackTwoint / DefenceOneint) * MoveDamage;
            damageOne.setVisible(true);
            damageOne.setText((int) DAMAGE + " DMG");
            AnimationPlayerTwo(true);
            Animation.DamageAnimate(damageOne);
        }
        HealthOneint = HealthOneint - (int) DAMAGE;
    }

    /**
     * Funkcja niezbedna do tworzenia animacji. Tworzy timeline oraz ustawia kolejnosc animacji.
     */

    public void setIdle() {

        final Timeline timelineIdle = new Timeline();
        timelineIdle.setCycleCount(Timeline.INDEFINITE);
        timelineIdle.setAutoReverse(true);

        final KeyValue kvhead1 = new KeyValue(p1head.yProperty(), p1head.getY() - 3);
        final KeyFrame kfhead1 = new KeyFrame(Duration.seconds(2), kvhead1);

        final KeyValue kvarm_b1 = new KeyValue(p1arm_b.yProperty(), p1arm_b.getY() - 3);
        final KeyFrame kfarm_b1 = new KeyFrame(Duration.seconds(2), kvarm_b1);

        final KeyValue kvarm_f1 = new KeyValue(p1arm_f.yProperty(), p1arm_f.getY() - 3);
        final KeyFrame kfarm_f1 = new KeyFrame(Duration.seconds(2), kvarm_f1);

        final KeyValue kvlabel1_1 = new KeyValue(fighterHeaderOne.translateYProperty(), -10);
        final KeyFrame kflabel1_1 = new KeyFrame(Duration.seconds(1), kvlabel1_1);

        final KeyValue kvlabel1_2 = new KeyValue(fighterHeaderOne.translateYProperty(), 10);
        final KeyFrame kflabel1_2 = new KeyFrame(Duration.seconds(2), kvlabel1_2);

        final KeyValue kvhead2 = new KeyValue(p2head.yProperty(), p2head.getY() - 3);
        final KeyFrame kfhead2 = new KeyFrame(Duration.seconds(2), kvhead2);

        final KeyValue kvarm_b2 = new KeyValue(p2arm_b.yProperty(), p2arm_b.getY() - 3);
        final KeyFrame kfarm_b2 = new KeyFrame(Duration.seconds(2), kvarm_b2);

        final KeyValue kvarm_f2 = new KeyValue(p2arm_f.yProperty(), p2arm_f.getY() - 3);
        final KeyFrame kfarm_f2 = new KeyFrame(Duration.seconds(2), kvarm_f2);

        final KeyValue kvlabel2_1 = new KeyValue(fighterHeaderTwo.translateYProperty(), -10);
        final KeyFrame kflabel2_1 = new KeyFrame(Duration.seconds(1), kvlabel2_1);

        final KeyValue kvlabel2_2 = new KeyValue(fighterHeaderTwo.translateYProperty(), 10);
        final KeyFrame kflabel2_2 = new KeyFrame(Duration.seconds(2), kvlabel2_2);

        timelineIdle.getKeyFrames().add(kfhead1);
        timelineIdle.getKeyFrames().add(kfarm_b1);
        timelineIdle.getKeyFrames().add(kfarm_f1);
        timelineIdle.getKeyFrames().add(kflabel1_1);
        timelineIdle.getKeyFrames().add(kflabel1_2);
        timelineIdle.getKeyFrames().add(kfhead2);
        timelineIdle.getKeyFrames().add(kfarm_b2);
        timelineIdle.getKeyFrames().add(kfarm_f2);
        timelineIdle.getKeyFrames().add(kflabel2_1);
        timelineIdle.getKeyFrames().add(kflabel2_2);
        timelineIdle.play();
    }

    /**
     * Funkcja wyswietla animacje w zaleznosci od trafienia oraz na czas trwania animacji wylacza wszystkie przyciski graczy.
     * @param ifhit parametr niezbiedny do wyswietlenia odpowiedniej animacji.
     */

    public void AnimationPlayerOne(boolean ifhit) {

        playerTwoMenu.setDisable(true);
        playerOneMenu.setDisable(true);

        if (ifhit) {
            Animation.HeadbuttOne(p1group, p2group, background, event -> changeTurn());
        } else {
            Animation.HeadbuttOneMiss(p1group, p2group, event -> changeTurn());
        }
    }

    /**
     * Funkcja wyswietla animacje w zaleznosci od trafienia oraz na czas trwania animacji wylacza wszystkie przyciski graczy.
     * @param ifhit parametr niezbiedny do wyswietlenia odpowiedniej animacji.
     */

    public void AnimationPlayerTwo(boolean ifhit) {

        playerTwoMenu.setDisable(true);
        playerOneMenu.setDisable(true);

        if (ifhit) {
            Animation.HeadbuttTwo(p2group, p1group, background, event -> changeTurn());
        } else {
            Animation.HeadbuttTwoMiss(p2group, p1group, event -> changeTurn());
        }
    }


    /**
     * Wykonuje atak przy pomocy move 1 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */
    public void action1PlayerOne() {
        AttackTargetPlayerOne(Move1FighterOne);
    }
    /**
     * Wykonuje atak przy pomocy move 2 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */
    public void action2PlayerOne() {
        AttackTargetPlayerOne(Move2FighterOne);
    }
    /**
     * Wykonuje atak przy pomocy move 3 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */
    public void action3PlayerOne() {
        AttackTargetPlayerOne(Move3FighterOne);
    }
    /**
     * Wykonuje atak przy pomocy move 4 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */
    public void action4PlayerOne() {
        AttackTargetPlayerOne(Move4FighterOne);
    }
    /**
     * Wykonuje atak przy pomocy move 1 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */
    public void action1PlayerTwo() {
        AttackTargetPlayerTwo(Move1FighterTwo);
    }
    /**
     * Wykonuje atak przy pomocy move 2 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */
    public void action2PlayerTwo() {
        AttackTargetPlayerTwo(Move2FighterTwo);
    }
    /**
     * Wykonuje atak przy pomocy move 3 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */
    public void action3PlayerTwo() {
        AttackTargetPlayerTwo(Move3FighterTwo);
    }
    /**
     * Wykonuje atak przy pomocy move 4 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */
    public void action4PlayerTwo() {
        AttackTargetPlayerTwo(Move4FighterTwo);
    }

    /**
     * Zmienia plik FXML na MainMenu.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * Pojawia się dopiero po zakonczeniu gry.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void EndGameButtonAction() throws IOException {
        Parent loader;
        loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/MainMenu.fxml"));

        Main.stage.getScene().setRoot(loader);
    }

    /**
     * Ustawia obrazy oraz nazwy dla obu graczy.
     * @param fighterone parametr wojownika gracza pierwszego, potrzebny do ustawienia obrazow.
     * @param fightertwo parametr wojownika gracza drugiego, potrzebny do ustawienia obrazow.
     */

    public void setData(Fighter fighterone, Fighter fightertwo) {

        p1name.setText(fighterone.name);
        p1head.setImage(fighterone.head.getLook());
        p1torso.setImage(fighterone.torso.getLook());
        p1arm_b.setImage(fighterone.arm_b.getLook());
        p1arm_f.setImage(fighterone.arm_f.getLook());
        p1leg_b.setImage(fighterone.leg_b.getLook());
        p1leg_f.setImage(fighterone.leg_f.getLook());

        p2name.setText(fightertwo.name);
        p2head.setImage(fightertwo.head.getLook());
        p2torso.setImage(fightertwo.torso.getLook());
        p2arm_b.setImage(fightertwo.arm_b.getLook());
        p2arm_f.setImage(fightertwo.arm_f.getLook());
        p2leg_b.setImage(fightertwo.leg_b.getLook());
        p2leg_f.setImage(fightertwo.leg_f.getLook());

    }

    /**
     * Ustawienie statystyk, move oraz nazw move gracza pierwszego w odpowiednie labele oraz buttony.
     * Pobiera je z CreateTeam
     */

    public void SetPlayerOne() {
        HealthOneint = CreateTeamController.playerone.getFullHealth();
        AttackOneint = CreateTeamController.playerone.getFullAttack();
        DefenceOneint = CreateTeamController.playerone.getFullDef();
        SpeedOneint = CreateTeamController.playerone.getFullSpeed();

        Move1FighterOne = CreateTeamController.playerone.head.getMove();
        Move2FighterOne = CreateTeamController.playerone.torso.getMove();
        Move3FighterOne = CreateTeamController.playerone.leg_f.getMove();
        Move4FighterOne = CreateTeamController.playerone.arm_f.getMove();

        playerOneButton1.setText(Move1FighterOne.getName());
        playerOneButton2.setText(Move2FighterOne.getName());
        playerOneButton3.setText(Move3FighterOne.getName());
        playerOneButton4.setText(Move4FighterOne.getName());
    }

    /**
     * Ustawienie statystyk, move oraz nazw move gracza drugiego w odpowiednie labele oraz buttony.
     * Pobiera je z CreateTeam
     */

    public void SetPlayerTwo() {
        HealthTwoint = CreateTeamController.playertwo.getFullHealth();
        AttackTwoint = CreateTeamController.playertwo.getFullAttack();
        DefenceTwoint = CreateTeamController.playertwo.getFullDef();
        SpeedTwoint = CreateTeamController.playertwo.getFullSpeed();

        Move1FighterTwo = CreateTeamController.playertwo.head.getMove();
        Move2FighterTwo = CreateTeamController.playertwo.torso.getMove();
        Move3FighterTwo = CreateTeamController.playertwo.leg_f.getMove();
        Move4FighterTwo = CreateTeamController.playertwo.arm_f.getMove();

        playerTwoButton1.setText(Move1FighterTwo.getName());
        playerTwoButton2.setText(Move2FighterTwo.getName());
        playerTwoButton3.setText(Move3FighterTwo.getName());
        playerTwoButton4.setText(Move4FighterTwo.getName());
    }

    /**
     * Inicializowanie danych poczatkowych dla walki.
     * CZyli ustawienie animacji, tury, statystyk i obrazow graczy, oraz komunikatow zadanych obrazen.
     */

    public void initialize() {
        setIdle();
        TurnCounter = 0;
        setData(CreateTeamController.playerone, CreateTeamController.playertwo);
        SetPlayerOne();
        SetPlayerTwo();
        ChangeStats();
        CheckTurn();
        CheckPlayer();
        damageOne.setVisible(false);
        damageTwo.setVisible(false);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 1 gracza pierwszego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action1PlayerOneMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move1FighterOne.getName());
        MoveDamagePane.setText(Integer.toString(Move1FighterOne.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move1FighterOne.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX());
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 2 gracza pierwszego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action2PlayerOneMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move2FighterOne.getName());
        MoveDamagePane.setText(Integer.toString(Move2FighterOne.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move2FighterOne.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX());
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);

    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 3 gracza pierwszego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action3PlayerOneMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move3FighterOne.getName());
        MoveDamagePane.setText(Integer.toString(Move3FighterOne.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move3FighterOne.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX());
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 4 gracza pierwszego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action4PlayerOneMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move4FighterOne.getName());
        MoveDamagePane.setText(Integer.toString(Move4FighterOne.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move4FighterOne.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX());
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 1 gracza drugiego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action1PlayerTwoMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move1FighterTwo.getName());
        MoveDamagePane.setText(Integer.toString(Move1FighterTwo.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move1FighterTwo.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 2 gracza drugiego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action2PlayerTwoMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move2FighterTwo.getName());
        MoveDamagePane.setText(Integer.toString(Move2FighterTwo.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move2FighterTwo.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 3 gracza drugiego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action3PlayerTwoMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move3FighterTwo.getName());
        MoveDamagePane.setText(Integer.toString(Move3FighterTwo.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move3FighterTwo.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 4 gracza drugiego.
     * Panel zawiera informacje o move.
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action4PlayerTwoMove(MouseEvent mouseEvent) {
        PaneMoveShow.setVisible(true);
        MoveNamePane.setText(Move4FighterTwo.getName());
        MoveDamagePane.setText(Integer.toString(Move4FighterTwo.getDamage()));
        MoveHitChancePane.setText(Integer.toString(Move4FighterTwo.getHitchance()) + "%");
        PaneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        PaneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do zamykania panelu po wyjsciu kursora z zakresu przycisku.
     * Funkcja jest taka sama dla kazdego przycisku.
     */

    public void actionPlayerOut() {
        PaneMoveShow.setVisible(false);
    }
}
