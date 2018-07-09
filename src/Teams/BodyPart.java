
package Teams;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * Klasa niezbedna do tworzenia elementow bodypart.
 * Wykorzystuje elemnty move.
 */

public class BodyPart implements Serializable {
    /**
     * Pole nazwy bodypart.
     */
    private String name;
    /**
     * Liczba zdrowia dla danego bodypart.
     */
    private int health;
    /**
     * Liczba ataku dla danego bodypart.
     */
    private int attack;
    /**
     * Liczba defensywy dla danego bodypart.
     */
    private int def;
    /**
     * Liczba szybkosci dla danego bodypart.
     */
    private int speed;
    /**
     * Wybrany move dla danego bodypart.
     */
    private Move move;
    /**
     * Scieżka do obrazu dla danego bodypart.
     */
    private String look;

    /**
     * Konstruktor do tworzenia bodypart bez wybranego move.
     * Wykorzystywany tylko przy leg back i arm back.
     * @param name nazwa bodypart
     * @param health zdrowie dla bodypart
     * @param attack atak dla bodypart
     * @param def defensywa dla bodypart
     * @param speed szybkosc dla bodypart
     * @param look sciezka do obrazu dla bodypart
     */

    public BodyPart(String name,
                    int health,
                    int attack,
                    int def,
                    int speed,
                    String look)
    {
        this.name = name;
        this.health =  health;
        this.attack = attack;
        this.def = def;
        this.speed = speed;
        this.look = look;
    }

    /**
     * Konstruktor do tworzenia bodypart z wybranym move.
     * Jest to standardowy konstruktor.
     * @param name nazwa bodypart
     * @param health zdrowie dla bodypart
     * @param attack atak dla bodypart
     * @param def defensywa dla bodypart
     * @param speed szybkosc dla bodypart
     * @param move wybrany move dla bodypart
     * @param look sciezka do obrazu dla bodypart
     */

    public BodyPart(String name,
                    int health,
                    int attack,
                    int def,
                    int speed,
                    String look,
                    Move move)
    {
        this.name = name;
        this.health =  health;
        this.attack = attack;
        this.def = def;
        this.speed = speed;
        this.look = look;
        this.move = move;
    }

    /**
     * Zwrocenie wartosci zdrowia z danego bodypart.
     * @return zdrowie
     */

    public int getHealth() {
        return health;
    }

    /**
     * Zwrocenie wartosci ataku z danego bodypart.
     * @return atak
     */

    public int getAttack() {
        return attack;
    }

    /**
     * Zwrocenie wartosci defensywy z danego bodypart.
     * @return defensywe
     */

    public int getDef() {
        return def;
    }

    /**
     * Zwrocenie obrazu z danego bodypart.
     * @return obraz bodyparta
     */

    public Image getLook() {
        return new Image(look);
    }

    /**
     * Zwrocenie wartosci szybkosci z danego bodypart.
     * @return szybkosc
     */

    public int getSpeed() {
        return speed;
    }

    /**
     * Zwrocenie move z danego bodypart.
     * @return wybrany ruch
     */

    public Move getMove() {
        return move;
    }
}