package MainPackage;

import Teams.BodyPart;
import Teams.Move;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Kontroler wykorzystany do operacji na menu glownym. Dodatkowo tez tworzy elementy niezbedne do dzialania gry.
 */

public class MainController {

    /**
     *  Tworzenie generatora losowych wartosci.
     */
    public Random rand = new Random();

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
    static List<Move> moves = new ArrayList<>();

    /**
     * Losuje wartosci od 0 do 8 do generowania losowych move dla poszczegolnych bodypart.
     * @return losowa wartosc od 0 do 8.
     */
    public int Random()
    {
        return rand.nextInt(8);
    }

    /**
     * Inicializacja danych, czyli tworzenie wszystkich bodypartow i move w listach.
     * Statyski dla każdego move są dosc losowe i nie optymalizowane pod katem rozgrywki.
     * Dodatkowo tez kazdy move jest losow przypisany do bodypart, przez co ciezko jest znalesc idealna kombinacje.
     *
     */
    public void initialize(){


        moves.add(new Move( "Blaze Kick", 140, 90));
        moves.add(new Move( "Shadow punch", 120, 95));
        moves.add(new Move( "Sunsteel Strike", 200, 70));
        moves.add(new Move( "High Horsepower", 180, 85));
        moves.add(new Move( "Hydro Vortex", 160,80));
        moves.add(new Move( "Draining Kiss", 100, 100));
        moves.add(new Move( "Fusion Bolt", 200, 95));
        moves.add(new Move( "Drill Run", 130, 90));
        moves.add(new Move( "Brick Break", 150, 70));


        heads.add(new BodyPart("Yhead", 200, 30, 30, 30, "/MainPackage/Character/Yellow/head.png", moves.get(Random())));
        heads.add(new BodyPart("Ghead", 150, 10, 10, 70, "/MainPackage/Character/Gray/head.png", moves.get(Random())));
        heads.add(new BodyPart("Phead", 300, 30, 50, 10, "/MainPackage/Character/Pink/head.png", moves.get(Random())));

        torsos.add(new BodyPart("Ytorso", 300, 25, 25, 40, "/MainPackage/Character/Yellow/torso.png", moves.get(Random())));
        torsos.add(new BodyPart("Gtorso", 150, 10, 20, 70, "/MainPackage/Character/Gray/torso.png", moves.get(Random())));
        torsos.add(new BodyPart("Ptorso", 500, 30, 1, 10, "/MainPackage/Character/Pink/torso.png", moves.get(Random())));

        legs_b.add(new BodyPart("Ylegb", 100, 20, 40, 30, "/MainPackage/Character/Yellow/leg_b.png"));
        legs_b.add(new BodyPart("Glegb", 50, 50, 10, 20, "/MainPackage/Character/Gray/leg_b.png"));
        legs_b.add(new BodyPart("Plegb", 150, 10, 70, 10, "/MainPackage/Character/Pink/leg_b.png"));

        legs_f.add(new BodyPart("Ylegf", 100, 20, 40, 30, "/MainPackage/Character/Yellow/leg_f.png", moves.get(Random())));
        legs_f.add(new BodyPart("Glegf", 50, 50, 10, 20, "/MainPackage/Character/Gray/leg_f.png", moves.get(Random())));
        legs_f.add(new BodyPart("Plegf", 150, 10, 70, 10, "/MainPackage/Character/Pink/leg_f.png", moves.get(Random())));

        arms_b.add(new BodyPart("Yarmb", 100, 20, 50, 20, "/MainPackage/Character/Yellow/arm_b.png"));
        arms_b.add(new BodyPart("Garmb", 50, 40, 10, 30, "/MainPackage/Character/Gray/arm_b.png"));
        arms_b.add(new BodyPart("Parmb", 150, 10, 70, 10, "/MainPackage/Character/Pink/arm_b.png"));

        arms_f.add(new BodyPart("Yarmf", 100, 20, 50, 20, "/MainPackage/Character/Yellow/arm_f.png", moves.get(Random())));
        arms_f.add(new BodyPart("Garmf", 50, 40, 10, 30, "/MainPackage/Character/Gray/arm_f.png", moves.get(Random())));
        arms_f.add(new BodyPart("Parmf", 150, 10, 70, 10, "/MainPackage/Character/Pink/arm_f.png", moves.get(Random())));

    }

    /**
     * Przycisk do wylaczenia calej aplikacji.
     */
    public void ExitButtonAction() {
        Platform.exit();
    }

    /**
     * Zmienia plik FXML na BeginGame.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */
    public void StartButtonAction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/BeginGameMenu.fxml"));

        Main.stage.getScene().setRoot(loader);
    }
}
