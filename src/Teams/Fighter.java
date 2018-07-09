package Teams;

import java.io.Serializable;
import java.util.*;

/**
 * Klasa niezbedna do tworzenia fighterow.
 * Wykorzystuje w sobie bodypart.
 */

public class Fighter implements Serializable {
    /**
     * Pole nazwy fighter.
     */
    public String name;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart head;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart torso;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart arm_b;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart arm_f;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart leg_b;
    /**
     * Element bodypart potrzebny do stworzenia fighter.
     */
    public BodyPart leg_f;

    /**
     * Konstruktor do tworzenia fighter na podstawie elementow bodypart.
     * @param name nazwa fighter
     * @param head element bodypart oznaczony jako glowa
     * @param torso element bodypart oznaczony jako tulow
     * @param leg_b element bodypart oznaczony jako nowa tylna
     * @param leg_f element bodypart oznaczony jako noga przednia
     * @param arm_b element bodypart oznaczony jako reka tylnia
     * @param arm_f element bodypart oznaczony jako reka przednia
     */

    public Fighter(String name,
                   BodyPart head,
                   BodyPart torso,
                   BodyPart leg_b,
                   BodyPart leg_f,
                   BodyPart arm_b,
                   BodyPart arm_f)
    {
        this.name = name;
        this.head = head;
        this.torso = torso;
        this.arm_b = arm_b;
        this.arm_f = arm_f;
        this.leg_b = leg_b;
        this.leg_f = leg_f;
    }

    /**
     * Metoda ta zwraca wartosc pelnego zdrowia danego figtera.
     * Sklada sie ona za elementow bodypart i laczy zdrowie kazdego z tych elementow.
     * @return calkowite zdrowie
     */

    public int getFullHealth() {
        int Health;
        Health = this.arm_f.getHealth() + this.arm_b.getHealth() + this.leg_f.getHealth() + this.leg_b.getHealth() + this.head.getHealth() + this.torso.getHealth();
        return Health;
    }

    /**
     * Metoda ta zwraca wartosc pelnego ataku danego figtera.
     * Sklada sie ona za elementow bodypart i laczy atak kazdego z tych elementow.
     * @return calkowity atak
     */

    public int getFullAttack() {
        int Attack;
        Attack = this.arm_f.getAttack() + this.arm_b.getAttack() + this.leg_f.getAttack() + this.leg_b.getAttack() + this.head.getAttack() + this.torso.getAttack();
        return Attack;
    }
    /**
     * Metoda ta zwraca wartosc pelnej defensywy danego figtera.
     * Sklada sie ona za elementow bodypart i laczy defensywe kazdego z tych elementow.
     * @return calkowita defensywa
     */

    public int getFullDef() {
        int Def;
        Def = this.arm_f.getDef() + this.arm_b.getDef() + this.leg_f.getDef() + this.leg_b.getDef() + this.head.getDef() + this.torso.getDef();
        return Def;
    }

    /**
     * Metoda ta zwraca wartosc pelnej szybkosci danego figtera.
     * Sklada sie ona za elementow bodypart i laczy szybkosc kazdego z tych elementow.
     * @return calkowita szybkosc
     */

    public int getFullSpeed() {
        int Speed;
        Speed = this.arm_f.getSpeed() + this.arm_b.getSpeed() + this.leg_f.getSpeed() + this.leg_b.getSpeed() + this.head.getSpeed() + this.torso.getSpeed();
        return Speed;
    }
}
