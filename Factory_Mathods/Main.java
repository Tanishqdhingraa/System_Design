package Factory_Mathods;


// Product
interface Burger {
    void prepare();
}

// Concrete Products
class BasicBurger implements Burger {
    public void prepare() {
        System.out.println("Basic Burger");
    }
}

class WheatBurger implements Burger {
    public void prepare() {
        System.out.println("Wheat Burger");
    }
}

// Factory
interface BurgerFactory {
    Burger createBurger();
}

// Concrete Factories
class NormalFactory implements BurgerFactory {
    public Burger createBurger() {
        return new BasicBurger();
    }
}

class WheatFactory implements BurgerFactory {
    public Burger createBurger() {
        return new WheatBurger();
    }
}

// Main
public class Main {
    public static void main(String[] args) {

        BurgerFactory factory = new NormalFactory();

        Burger burger = factory.createBurger();

        burger.prepare();
    }
}
