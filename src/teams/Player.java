package teams;

public class Player {
    public int healthint;
    public double attackint;
    public double defenceint;
    public double speedint;


    public Move move1Fighter;
    public Move move2Fighter;
    public Move move3Fighter;
    public Move move4Fighter;

    public Fighter playerFighter;

    public Player(Fighter tempFighter){
        this.healthint = tempFighter.getFullHealth();
        this.attackint = tempFighter.getFullAttack();
        this.defenceint = tempFighter.getFullDef();
        this.speedint = tempFighter.getFullSpeed();

        this.move1Fighter = tempFighter.head.getMove();
        this.move2Fighter = tempFighter.torso.getMove();
        this.move3Fighter = tempFighter.leg_f.getMove();
        this.move4Fighter = tempFighter.arm_f.getMove();

        this.playerFighter = tempFighter;
    }
}
