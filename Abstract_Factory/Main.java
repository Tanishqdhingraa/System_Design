package Abstract_Factory;

// Product 1
interface Burger {
    void prepare();
}

class NormalBurger implements Burger {
    public void prepare() {
        System.out.println("Normal Burger");
    }
}

class WheatBurger implements Burger {
    public void prepare() {
        System.out.println("Wheat Burger");
    }
}

// Product 2
interface GarlicBread {
    void prepare();
}

class NormalBread implements GarlicBread {
    public void prepare() {
        System.out.println("Normal Garlic Bread");
    }
}

class WheatBread implements GarlicBread {
    public void prepare() {
        System.out.println("Wheat Garlic Bread");
    }
}

// Abstract Factory
interface MealFactory {
    Burger createBurger();

    GarlicBread createBread();
}

// Concrete Factory 1
class NormalFactory implements MealFactory {

    public Burger createBurger() {
        return new NormalBurger();
    }

    public GarlicBread createBread() {
        return new NormalBread();
    }
}

// Concrete Factory 2
class WheatFactory implements MealFactory {

    public Burger createBurger() {
        return new WheatBurger();
    }

    public GarlicBread createBread() {
        return new WheatBread();
    }
}

// Main
public class Main {
    public static void main(String[] args) {

        MealFactory factory = new NormalFactory();

        Burger burger = factory.createBurger();
        GarlicBread bread = factory.createBread();

        burger.prepare();
        bread.prepare();
    }
}
