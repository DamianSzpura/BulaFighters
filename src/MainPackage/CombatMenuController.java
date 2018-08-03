package MainPackage;

import teams.Fighter;
import teams.Move;
import teams.Player;
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

import static MainPackage.Animation.*;

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
     * Tworzenie generatora losowych wartosci.
     */

    public Random rand = new Random();

    /**
     * Licznik tur, wykorzystywany przy wyborze kolejnosci tur.
     */

    public int turnCounter;

    @FXML
    Label healthOne;
    @FXML
    Label attackOne;
    @FXML
    Label defenceOne;
    @FXML
    Label speedOne;

    public Player playerOne;

    @FXML
    Label healthTwo;
    @FXML
    Label attackTwo;
    @FXML
    Label defenceTwo;
    @FXML
    Label speedTwo;

    public Player playerTwo;

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
    Button endGameButton;
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
    Label endGame;

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
    AnchorPane paneMoveShow;
    @FXML
    Label moveDamagePane;
    @FXML
    Label moveNamePane;
    @FXML
    Label moveHitChancePane;

    /**
     * Ustawia obrazy oraz nazwy dla obu graczy.
     *
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

        playerOne = new Player(CreateTeamController.playerone);

        playerOneButton1.setText(playerOne.move1Fighter.getName());
        playerOneButton2.setText(playerOne.move2Fighter.getName());
        playerOneButton3.setText(playerOne.move3Fighter.getName());
        playerOneButton4.setText(playerOne.move4Fighter.getName());

        playerTwo = new Player(CreateTeamController.playertwo);

        playerTwoButton1.setText(playerTwo.move1Fighter.getName());
        playerTwoButton2.setText(playerTwo.move2Fighter.getName());
        playerTwoButton3.setText(playerTwo.move3Fighter.getName());
        playerTwoButton4.setText(playerTwo.move4Fighter.getName());
    }

    /**
     * Inicializowanie danych poczatkowych dla walki.
     * CZyli ustawienie animacji, tury, statystyk i obrazow graczy, oraz komunikatow zadanych obrazen.
     */

    public void initialize() {
        setIdle();
        turnCounter = -1;
        setData(CreateTeamController.playerone, CreateTeamController.playertwo);
        changeTurn();
        damageOne.setVisible(false);
        damageTwo.setVisible(false);
    }

    /**
     * Funkcja do zmiany kolejki jak i tury dla graczy.
     * Zawsze zmienia kolejke na nastepna
     * Dodaje kolejna ture do turncounter oraz co 2 tury sprawdza checkTurn.
     * Na tej podstawie wybiera nastepnego gracza pierwszego.
     * Wykonuje to wszystko o ile gra sie jeszcze nie zakonczyla.
     */

    public void changeTurn() {
        if (turnCounter >= 0)
            turn = !turn;
        updateStatsForThisTurn();

        if (!endGame.isVisible()) {
            turnCounter++;

            if (turnCounter % 2 == 0)
                checkTurn();

            checkPlayer();
        }
    }


    public void updateStatsForThisTurn() {
        changeStatsForPlayer(playerOne, CreateTeamController.playerone, healthOne, healthBarOne, attackOne, defenceOne, speedOne);
        changeStatsForPlayer(playerTwo, CreateTeamController.playertwo, healthTwo, healthBarTwo, attackTwo, defenceTwo, speedTwo);

        checkEndGamePlayer(playerOne, CreateTeamController.playerone, "PLAYER 2 WINS", healthOne, healthBarOne);
        checkEndGamePlayer(playerTwo, CreateTeamController.playerone, "PLAYER 1 WINS", healthTwo, healthBarTwo);
    }

        /**
         * Zmienia statystyki graczy na nowe.
         * Wykorzystywane przy wykonaniu odpowiedniego move w kolejce.
         * W grze co prawda nie ma move ktore zmieniaja statystyki attack, defence czy speed, jednak zostalo to stworzone z mysla rowniez i o takich move.
         * Pozniej sprawdza czy nie nastapil koniec gry.
         */

        void changeStatsForPlayer(Player tempPlayer, Fighter tempFighter, Label Health, ProgressBar Bar, Label Attack, Label Defence, Label Speed) {
            Health.setText("Health: " + Integer.toString(tempPlayer.healthint) + "/" + Integer.toString(tempFighter.getFullHealth()));
            Bar.setProgress((float) tempPlayer.healthint / tempFighter.getFullHealth());
            Attack.setText("Attack: " + Integer.toString((int) tempPlayer.attackint) + "/" + Integer.toString(tempFighter.getFullAttack()));
            Defence.setText("Defense: " + Integer.toString((int) tempPlayer.defenceint) + "/" + Integer.toString(tempFighter.getFullDef()));
            Speed.setText("Speed: " + Integer.toString((int)tempPlayer.speedint) + "/" + Integer.toString(tempFighter.getFullSpeed()));
        }

        /**
         * Sprawdza czy Health ktoregos z graczy po wykonaniu move nie jest mniejsze od 0.
         * Jeżeli jest to konczy gre i wyswietla odpowiedni komunikat.
         * Wylacza rowniez wszystkie przyciski na scenie i dodaje nowy - zakonczenie gry.
         * Funkcja wykorzystywana w changeStats.
         */

        void checkEndGamePlayer(Player tempPlayer, Fighter tempFighter, String win, Label playerHealth, ProgressBar playerBar) {
            if (tempPlayer.healthint <= 0) {
                tempPlayer.healthint = 0;
                playerHealth.setText("Health: " + Integer.toString(tempPlayer.healthint) + "/" + Integer.toString(tempFighter.getFullHealth()));
                playerBar.setProgress((float) tempPlayer.healthint / tempFighter.getFullHealth());
                endGame.setText(win);
                endGame.setVisible(true);
                playerTwoMenu.setDisable(true);
                playerOneMenu.setDisable(true);
                fighterHeaderTwo.setVisible(false);
                fighterHeaderOne.setVisible(false);
                endGameButton.setVisible(true);
            }
        }

    /**
     * Funkcja ma za zadnie sprawdzic kto bedzie pierwszy w kolejnej turze.
     * Po wykonaniu kolejki gracza x i y, losuje kto bedzie nastepny w koljnej turze.
     * Wartosc od 0 do 100 dodaje do wartosci speed fightera x i porownuje je z wartosciami uzyskanymi w ten sam sposob dla fightera y.
     * Kto ma wieksza wartosc zaczyna jako pierwszy kolejna ture.
     */

    public void checkTurn() {
        int randomTurnPlayerOne = random();
        int randomTurnPlayerTwo = random();
        turn = (randomTurnPlayerOne + playerOne.speedint) <= (randomTurnPlayerTwo + playerTwo.speedint);
    }

    /**
     * Odpowiednio zmienia mozliwosc klikania przyciskow oraz headery dla postaci w zaleznosci od kolejki.
     * Jest tu aby w kolejce gracza pierwszego, gracz drugi nie mogl nic robic.
     * Funkcja wykorzystana w changeturn.
     */

    public void checkPlayer() {
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

    Player chooseTarget(Player user, Player opponent, Move move){
        Player target = opponent;
        if(move.getTarget() == true)
        target = user;
        return target;
    }

    void attackPlayer(Label damageLabel, Player playerattack, Player playerdef, Fighter tempFighter, Move move) {
        int Hit = rand.nextInt(100) + 1;
        StringBuilder updateText = new StringBuilder();
        if (Hit > move.getHitchance()) {
            damageLabel.setVisible(true);
            damageLabel.setText("MISS");
            if (!turn) animationPlayerOne(false);
            else animationPlayerTwo(false);
            damageAnimate(damageLabel);
        } else {
            if (move.getType() % 2 == 0) {
                int damage = changeHealth( playerattack, playerdef, move);
                updateText.append(damage + " DMG ");
            }
            if (move.getType() % 3 == 0) {
                double effect = changeStats( playerdef, tempFighter, move);
                updateText.append(effect + " " + move.getDescription());
            }
           // else if (move.getType() % 5 == 0)
            //    turnEffects(damageLabel, playerattack, playerdef, move);
            //else if(move.getType()%7 == 0)
            damageLabel.setVisible(true);
            damageLabel.setText(updateText.toString());
            if (!turn) animationPlayerOne(true);
            else animationPlayerTwo(true);
            damageAnimate(damageLabel);
        }
    }

    int changeHealth( Player playerattack, Player playerdef, Move move){
        double MoveDamage = move.getDamage();
        double DAMAGE = (playerattack.attackint / playerdef.defenceint) * MoveDamage;
        playerdef.healthint -= (int)DAMAGE;
        return (int)DAMAGE;
    }

    double changeStats( Player playerdef, Fighter tempFighter, Move move){
        if (move.getSpecialType()%2 == 0 && playerdef.attackint < tempFighter.getFullAttack()*4 && playerdef.attackint > tempFighter.getFullAttack()/4)
            playerdef.attackint = (int)(playerdef.attackint * move.getEffect());
        if(move.getSpecialType()%3 == 0 && playerdef.defenceint < tempFighter.getFullDef()*4 && playerdef.defenceint > tempFighter.getFullDef()/4)
            playerdef.defenceint = (int)(playerdef.defenceint * move.getEffect());
        if (move.getSpecialType()%5 == 0 && playerdef.speedint < tempFighter.getFullSpeed()*4 && playerdef.speedint > tempFighter.getFullSpeed()/4)
            playerdef.speedint = (int)(playerdef.speedint * move.getEffect());
        return move.getEffect();
    }

    /*
     * Funkcja wykonuje atak gracza pierwszego na gracza drugiego.
     * Na poczatku oblicza trafienie move - generuje wartosc od 1 do 100 i porownuje z wartoscia hitchance danego move.
     * Jezeli jest obliczona wartosc jest w przedziale od 1 do hitmove, wtedy atak trafia.
     * W razie nie trafienia wyswietla odpowiednie komunikaty i animacje uniku gracza przeciwnego.
     * Jezeli trafi to zadaje obrazenia.
     * Obrazenia obliczane sa za pomoca wzoru (AttackOneint / DefenceTwoint) * MoveDamage.
     * Liczba zapisana w obrazeniach nastepnie jest odejmowana od puli zdrowia przeciwnika i nastepuje odpowiednia animacja i komunikat.
     * @param move za jego pomoca pobierane sa informacie o danym move.
     */

    /**
     * Wykonuje atak przy pomocy move 1 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */

    public void action1PlayerOne() {
        attackPlayer(damageTwo, playerOne, chooseTarget(playerOne, playerTwo, playerOne.move1Fighter),chooseTarget(playerOne, playerTwo, playerOne.move1Fighter).playerFighter, playerOne.move1Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 2 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */

    public void action2PlayerOne() {
        attackPlayer(damageTwo, playerOne, chooseTarget(playerOne, playerTwo, playerOne.move2Fighter),chooseTarget(playerOne, playerTwo, playerOne.move2Fighter).playerFighter, playerOne.move2Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 3 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */

    public void action3PlayerOne() {
        attackPlayer(damageTwo, playerOne, chooseTarget(playerOne, playerTwo, playerOne.move3Fighter),chooseTarget(playerOne, playerTwo, playerOne.move3Fighter).playerFighter, playerOne.move3Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 4 gracza pierwszego.
     * Funkcja przycisku gracza pierwszego.
     */

    public void action4PlayerOne() {
        attackPlayer(damageTwo, playerOne, chooseTarget(playerOne, playerTwo, playerOne.move4Fighter),chooseTarget(playerOne, playerTwo, playerOne.move4Fighter).playerFighter, playerOne.move4Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 1 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */

    public void action1PlayerTwo() {
        attackPlayer(damageOne, playerTwo, chooseTarget(playerTwo, playerOne, playerOne.move1Fighter),chooseTarget(playerTwo, playerOne, playerOne.move1Fighter).playerFighter, playerTwo.move1Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 2 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */

    public void action2PlayerTwo() {
        attackPlayer(damageOne, playerTwo, chooseTarget(playerTwo, playerOne, playerTwo.move2Fighter),chooseTarget(playerTwo, playerOne, playerTwo.move2Fighter).playerFighter, playerTwo.move2Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 3 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */

    public void action3PlayerTwo() {
        attackPlayer(damageOne, playerTwo, chooseTarget(playerTwo, playerOne, playerTwo.move3Fighter),chooseTarget(playerTwo, playerOne, playerTwo.move3Fighter).playerFighter, playerTwo.move3Fighter);
    }

    /**
     * Wykonuje atak przy pomocy move 4 gracza drugiego.
     * Funkcja przycisku gracza drugiego.
     */

    public void action4PlayerTwo() {
        attackPlayer(damageOne, playerTwo, chooseTarget(playerTwo, playerOne, playerTwo.move4Fighter),chooseTarget(playerTwo, playerOne, playerTwo.move4Fighter).playerFighter, playerTwo.move4Fighter);
    }

    /**
     * Zmienia plik FXML na MainMenu.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * Pojawia się dopiero po zakonczeniu gry.
     *
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void endGameButtonAction() throws IOException {
        Parent loader;
        loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/MainMenu.fxml"));

        Main.stage.getScene().setRoot(loader);
    }

    void actionForPlayer(Move tempPlayerMove) {
        paneMoveShow.setVisible(true);
        moveNamePane.setText(tempPlayerMove.getName());
        moveDamagePane.setText(Integer.toString(tempPlayerMove.getDamage()));
        moveHitChancePane.setText(Integer.toString(tempPlayerMove.getHitchance()) + "%");
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 1 gracza pierwszego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action1PlayerOneMove(MouseEvent mouseEvent) {
        actionForPlayer(playerOne.move1Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX());
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 2 gracza pierwszego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action2PlayerOneMove(MouseEvent mouseEvent) {
        actionForPlayer(playerOne.move2Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX());
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);

    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 3 gracza pierwszego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action3PlayerOneMove(MouseEvent mouseEvent) {
        actionForPlayer(playerOne.move3Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX());
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 4 gracza pierwszego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action4PlayerOneMove(MouseEvent mouseEvent) {
        actionForPlayer(playerOne.move4Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX());
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 1 gracza drugiego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action1PlayerTwoMove(MouseEvent mouseEvent) {
        actionForPlayer(playerTwo.move1Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 2 gracza drugiego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action2PlayerTwoMove(MouseEvent mouseEvent) {
        actionForPlayer(playerTwo.move2Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 3 gracza drugiego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action3PlayerTwoMove(MouseEvent mouseEvent) {
        actionForPlayer(playerTwo.move3Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do wyswietlania panelu po najechaniu myszą na przycisk move 4 gracza drugiego.
     * Panel zawiera informacje o move.
     *
     * @param mouseEvent wykorzystywany do zdobycia pozycji myszy.
     */

    public void action4PlayerTwoMove(MouseEvent mouseEvent) {
        actionForPlayer(playerTwo.move4Fighter);
        paneMoveShow.setLayoutX(mouseEvent.getSceneX() - 200);
        paneMoveShow.setLayoutY(mouseEvent.getSceneY() - 215);
    }

    /**
     * Funkcja do zamykania panelu po wyjsciu kursora z zakresu przycisku.
     * Funkcja jest taka sama dla kazdego przycisku.
     */

    public void actionPlayerOut() {
        paneMoveShow.setVisible(false);
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
     *
     * @param ifhit parametr niezbiedny do wyswietlenia odpowiedniej animacji.
     */

    public void animationPlayerOne(boolean ifhit) {

        playerTwoMenu.setDisable(true);
        playerOneMenu.setDisable(true);

        if (ifhit) {
            headbuttOne(p1group, p2group, background, event -> changeTurn());
        } else {
            headbuttOneMiss(p1group, p2group, event -> changeTurn());
        }
    }

    /**
     * Funkcja wyswietla animacje w zaleznosci od trafienia oraz na czas trwania animacji wylacza wszystkie przyciski graczy.
     *
     * @param ifhit parametr niezbiedny do wyswietlenia odpowiedniej animacji.
     */

    public void animationPlayerTwo(boolean ifhit) {

        playerTwoMenu.setDisable(true);
        playerOneMenu.setDisable(true);

        if (ifhit) {
            headbuttTwo(p2group, p1group, background, event -> changeTurn());
        } else {
            headbuttTwoMiss(p2group, p1group, event -> changeTurn());
        }
    }

    /**
     * Losuje wartosci od 0 do 100 do generowania kolejnosci w kolejnej ruchu turze.
     *
     * @return losowa wartosc od 0 do 100.
     */

    public int random() {
        return rand.nextInt(100);
    }
}

