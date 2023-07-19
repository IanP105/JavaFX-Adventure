public class Adventurer extends baseCreature{
    private int xp;
    private int level;
    private int numPotions;

    public Adventurer(int xp, int level, String name, int maxHp, int currentHp, int attack, int defense, int numPotions){
        super(name, maxHp, currentHp, attack, defense);
        this.xp = xp;
        this.level = level;
        this.numPotions = numPotions;
    }

    public int getXp() {return xp;}
    public int getLevel() {return level;}
    public int getNumPotions(){return numPotions;}

    public void setXp(int xp) {this.xp = xp;}
    public void setLevel(int level) {this.level = level;}
    public void setNumPotions(int numPotions) {this.numPotions = numPotions;}

    public boolean gameOver() {
        if (this.getCurrentHp() <= 0) {
            return true;
        } else {
            return false;
        }
    }
    public String healthCheck(){
        return "Current HP is: " + getCurrentHp();
    }

    public String stats(){
        return "Name: " + getName() + "\nLevel: " + getLevel() + "\nXP: " + getXp() + "\nMax HP: " + getMaxHp() +
                "\nCurrent HP: " + getCurrentHp() + "\nAttack: " + getAttack() +"\nDefense: " + getDefense() + "\nPotions: " + getNumPotions();
    }
    public void heal(int numPotions){
        if(numPotions > 0){
            setCurrentHp(getMaxHp());
        }
        else{
            System.out.println("You do not have any potions! ");
        }
    }
    public void xpGain(int xp){
        switch (xp){
            case 3:
                setLevel(2);
                break;
            case 6:
                setLevel(3);
                break;
            case 9:
                setLevel(4);
                break;
            case 12:
                setLevel(5);
                break;
        }

    }
    public void levelUp(){
        switch (level){
            case 2:
                setAttack(getAttack()+ 2);
                setDefense(getDefense() + 1);
                break;
            case 3:
                setAttack(getAttack() + 2);
                setDefense(getDefense() + 1);
                break;
            case 4:
                setAttack(getAttack() + 1);
                setDefense(getDefense() + 2);
                break;
            case 5:
                setAttack(getAttack() + 2);
                setDefense(getDefense() + 1);
                break;
        }
    }

}