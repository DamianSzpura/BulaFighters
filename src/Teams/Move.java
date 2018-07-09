package Teams;

import java.io.Serializable;

/**
 * Klasa niezbedna do tworzenia move.
 */

public class Move implements Serializable {
    /**
     * Nazwa danego move.
     */
    private String name;
    /**
     * Obrazenia dla danego move.
     */
    private int damage;
    /**
     * Szansa trafienia dla danego move.
     */
    private int hitchance;

    /**
     * Konstruktor do tworzenia obiektow move.
     * @param name nazwa danego move
     * @param damage obrazenia move
     * @param hitchance szansa trafienia move
     */
    public Move(String name,
            int damage,
            int hitchance
            )
    {
        this.name = name;
        this.damage = damage;
        this.hitchance = hitchance;
    }

    /**
     * Metoda ta zwraca nazwe move.
     * @return nazwe
     */

    public String getName() {
        return name;
    }

    /**
     * Metoda zwraca obrazenia dla danego move.
     * @return obrazenia
     */

    public int getDamage() {
        return damage;
    }

    /**
     * Metoda zwraca szanse trafenia danego move.
     * @return szanse trafienia
     */

    public int getHitchance() {
        return hitchance;
    }
}
