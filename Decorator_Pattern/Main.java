package Decorator_Pattern;

// Component
interface Character {
    String getAbilities();
}

// Concrete Component
class Mario implements Character {
    public String getAbilities() {
        return "Mario";
    }
}

// Decorator
abstract class PowerUp implements Character {

    protected Character character;

    PowerUp(Character character) {
        this.character = character;
    }
}

// Concrete Decorators
class Gun extends PowerUp {

    Gun(Character character) {
        super(character);
    }

    public String getAbilities() {
        return character.getAbilities() + " + Gun";
    }
}

class Star extends PowerUp {

    Star(Character character) {
        super(character);
    }

    public String getAbilities() {
        return character.getAbilities() + " + Star";
    }
}

// Main
public class Main {

    public static void main(String[] args) {

        Character mario = new Mario();
        System.out.println(mario.getAbilities());

        mario = new Gun(mario);
        System.out.println(mario.getAbilities());

        mario = new Star(mario);
        System.out.println(mario.getAbilities());
    }
}
