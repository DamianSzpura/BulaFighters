package MainPackage;

import teams.BodyPart;
import teams.Move;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static MainPackage.Main.*;

/**
 * Kontroler wykorzystany do operacji na menu glownym. Dodatkowo tez tworzy elementy niezbedne do dzialania gry.
 */

public class MainController {

    /**
     *  Tworzenie generatora losowych wartosci.
     */
    private Random rand = new Random();

    /**
     *  Zapis wszystkich Head w postaci listy.
     */
    static List<BodyPart> heads = new ArrayList<>();
    /**
     *  Zapis wszystkich Torso w postaci listy.
     */
    static List<BodyPart> torsos = new ArrayList<>();
    /**
     *  Zapis wszystkich Leg Back w postaci listy.
     */
    static List<BodyPart> legs_b = new ArrayList<>();
    /**
     *  Zapis wszystkich Leg Front w postaci listy.
     */
    static List<BodyPart> legs_f = new ArrayList<>();
    /**
     *  Zapis wszystkich Arm Back w postaci listy.
     */
    static List<BodyPart> arms_b = new ArrayList<>();
    /**
     *  Zapis wszystkich Arm Front w postaci listy.
     */
    static List<BodyPart> arms_f = new ArrayList<>();

    /**
     *  Zapis wszystkich move w postaci listy, wykorzystywane przy tworzeniu bodypart.
     */
    private static List<Move> moves = new ArrayList<>();

    /**
     * Losuje wartosci od 0 do 8 do generowania losowych move dla poszczegolnych bodypart.
     * @return losowa wartosc od 0 do 8.
     */
    private int random()
    {
        return rand.nextInt(moves.size());
    }

    /**
     * Inicializacja danych, czyli tworzenie wszystkich bodypartow i move w listach.
     * Statyski dla każdego move są dosc losowe i nie optymalizowane pod katem rozgrywki.
     * Dodatkowo tez kazdy move jest losow przypisany do bodypart, przez co ciezko jest znalesc idealna kombinacje.
     *
     */
    public void initialize(){

        moves.add(new Move( "Blaze Kick", 140, 90, 0,0, " ", 2,0, false));
        moves.add(new Move( "Shadow punch", 120, 95, 0, 0, " ", 2, 0,false));
        moves.add(new Move( "Sunsteel Strike", 200, 70, 0, 0, " ",2,0,false));
        moves.add(new Move( "High Horsepower", 180, 85, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Hydro Vortex", 160,80, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Draining Kiss", 100, 100, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Fusion Bolt", 200, 95, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Drill Run", 130, 90, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Brick Break", 150, 70, 0, 0, " ", 2,0,false));
        moves.add(new Move( "Bulk up", 0, 100, 0, 2, "attack up", 3,2,true));
        moves.add(new Move( "Eat buła", 100, 100, 0, 2, "all stats up", 6,30,true));


        heads.add(new BodyPart( 200, 30, 30, 30, "/MainPackage/character/Yellow/head.png", moves.get(random())));
        heads.add(new BodyPart( 150, 10, 10, 70, "/MainPackage/character/Gray/head.png", moves.get(random())));
        heads.add(new BodyPart( 300, 30, 50, 10, "/MainPackage/character/Pink/head.png", moves.get(random())));

        torsos.add(new BodyPart( 300, 25, 25, 40, "/MainPackage/character/Yellow/torso.png", moves.get(random())));
        torsos.add(new BodyPart( 150, 10, 20, 70, "/MainPackage/character/Gray/torso.png", moves.get(random())));
        torsos.add(new BodyPart( 500, 30, 1, 10, "/MainPackage/character/Pink/torso.png", moves.get(random())));

        legs_b.add(new BodyPart( 100, 20, 40, 30, "/MainPackage/character/Yellow/leg_b.png"));
        legs_b.add(new BodyPart( 50, 50, 10, 20, "/MainPackage/character/Gray/leg_b.png"));
        legs_b.add(new BodyPart( 150, 10, 70, 10, "/MainPackage/character/Pink/leg_b.png"));

        legs_f.add(new BodyPart( 100, 20, 40, 30, "/MainPackage/character/Yellow/leg_f.png", moves.get(random())));
        legs_f.add(new BodyPart( 50, 50, 10, 20, "/MainPackage/character/Gray/leg_f.png", moves.get(random())));
        legs_f.add(new BodyPart( 150, 10, 70, 10, "/MainPackage/character/Pink/leg_f.png", moves.get(random())));

        arms_b.add(new BodyPart( 100, 20, 50, 20, "/MainPackage/character/Yellow/arm_b.png"));
        arms_b.add(new BodyPart(50, 40, 10, 30, "/MainPackage/character/Gray/arm_b.png"));
        arms_b.add(new BodyPart(150, 10, 70, 10, "/MainPackage/character/Pink/arm_b.png"));

        arms_f.add(new BodyPart( 100, 20, 50, 20, "/MainPackage/character/Yellow/arm_f.png", moves.get(random())));
        arms_f.add(new BodyPart( 50, 40, 10, 30, "/MainPackage/character/Gray/arm_f.png", moves.get(random())));
        arms_f.add(new BodyPart( 150, 10, 70, 10, "/MainPackage/character/Pink/arm_f.png", moves.get(random())));

    }

    /**
     * Przycisk do wylaczenia calej aplikacji.
     */
    public void exitButtonAction() {
        Platform.exit();
    }

    /**
     * Zmienia plik FXML na BeginGame.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */
    public void startButtonAction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/CreateTeamMenu.fxml"));
        stage.getScene().setRoot(loader);
    }
}
