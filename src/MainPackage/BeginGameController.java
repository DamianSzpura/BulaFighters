package MainPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Kontroler wykorzystany do operacji na menu dodatkowym gry.
 * Pozwala on upewnic sie ze chcemy rozpoczac gre.
 */

public class BeginGameController {

    /**
     * Zmienia plik FXML na CreateTeam.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void CreateButtonAction() throws IOException {

        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/CreateTeamMenu.fxml"));

        Main.stage.getScene().setRoot(loader);
    }

    /**
     * Zmienia plik FXML na MainMenu.
     * W teorii zmienia nam scene, jednak bez podmiany naszego Main.stage.
     * @throws IOException Na potrzebe braku sciezki do pliku FXML.
     */

    public void ReturnButtonAction() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("/MainPackage/Fxml/MainMenu.fxml"));

        Main.stage.getScene().setRoot(loader);
    }
}
