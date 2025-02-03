// prototype interface
interface Prototype {
    Prototype clone();
}

// concrete prototype class
class GameCharacter implements Prototype {
    private String name;
    private int health;
    private int power;

    public GameCharacter(String name, int health, int power) {
        this.name = name;
        this.health = health;
        this.power = power;
    }

    public void showDetails() {
        System.out.println("nm: " + name + ", life: " + health + ", pwr: " + power);
    }

    @Override
    public Prototype clone() {
        return new GameCharacter(name, health, power);
    }
}

// test the prototype pattern
public class prototype {
    public static void main(String[] args) {
        // main character
        GameCharacter character1 = new GameCharacter("a", 10, 10);
        character1.showDetails();

        // clon the character and modify it
        GameCharacter character2 = (GameCharacter) character1.clone();
        character2.showDetails(); // same as char1
        
        GameCharacter character3 = (GameCharacter) character1.clone();
        character3.showDetails(); // Same as char1
    }
}