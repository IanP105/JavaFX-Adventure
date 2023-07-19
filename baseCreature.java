public class baseCreature{
    private String name;
    private int maxHp;
    private int currentHp;
    private int attack;
    private int defense;

    public baseCreature(String name, int maxHp, int currentHp, int attack, int defense){
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.attack = attack;
        this.defense = defense;
    }
    public String getName() {return name;}
    public int getMaxHp() {return maxHp;}
    public int getCurrentHp() {return currentHp;}
    public int getAttack() {return attack;}
    public int getDefense() {return defense;}

    public void setName(String name) {this.name = name;}
    public void setMaxHp(int maxHp) {this.maxHp = maxHp;}
    public void setCurrentHp(int currentHp) {this.currentHp = currentHp;}
    public void setAttack(int attack) {this.attack = attack;}
    public void setDefense(int defense) {this.defense = defense;}

    public boolean isDead(){
        if(this.currentHp<= 0){
            return true;
        }
        else{
            return false;
        }
    }

}