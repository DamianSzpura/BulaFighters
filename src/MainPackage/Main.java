package MainPackage;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Stworzenie sceny i rozpoczecie dzialania programu.
 */

public class Main extends Application {

    /**
     * Static do wykorzystania w pozostalych controllerach jako odwolywanie do sceny.
     */
    static Stage stage;


    /**
     * Funkcja start odpowiada za tworzenie pierwszej sceny na ktorej tez beda opierac sie pozostale sceny.
     * @param primaryStage jest wykorzystywany pod postacia stage, aby moc odwolywac sie do tej sceny w pozostalych controllerach.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Fxml/MainMenu.fxml"));

        stage.setTitle("Buła fighters");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();

    }

    /**
     * Funkcja main uruchamia aplikacje.
     */

    public static void main(String[] args) {
        launch(args);
    }
}
