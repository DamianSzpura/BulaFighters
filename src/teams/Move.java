package teams;

import java.io.Serializable;

/**
 * Klasa niezbedna do tworzenia move.
 */

public class Move implements Serializable {

    private String name;
    private int damage;
    private int duration;
    private double effect;
    private int hitchance;
    private String description;
    private int type;
    private int specialType;
    private boolean target;

    public Move(String name,
                int damage,
                int hitchance,
                int duration,
                double effect,
                String description,
                int type,
                int specialType,
                boolean target)
    {
        this.name = name;
        this.damage = damage;
        this.description = description;
        this.duration = duration;
        this.effect = effect;
        this.hitchance = hitchance;
        this.type = type;
        this.specialType = specialType;
        this.target = target;
    }

    /**
     * Metoda ta zwraca nazwe move.
     * @return nazwe
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Metoda zwraca obrazenia dla danego move.
     * @return obrazenia
     */

    public int getDamage() {
        return damage;
    }

    public int getDuration() {
        return duration;
    }

    public double getEffect() {
        return effect;
    }
    /**
     * Metoda zwraca szanse trafenia danego move.
     * @return szanse trafienia
     */

    public int getHitchance() {
        return hitchance;
    }

    public int getType() {
        return type;
    }

    public int getSpecialType() {
        return specialType;
    }

    public boolean getTarget() {
        return target;
    }
}
