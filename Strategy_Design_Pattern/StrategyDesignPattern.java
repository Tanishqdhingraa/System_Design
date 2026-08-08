package Strategy_Design_Pattern;


//! Strategy Design Pattern 

interface WalkBehavior {
    void walk();
}

// Different strategies
class NormalWalk implements WalkBehavior {
    public void walk() {
        System.out.println("Walking normally");
    }
}

class NoWalk implements WalkBehavior {
    public void walk() {
        System.out.println("Cannot walk");
    }
}

// Robot
class Robot {
    private WalkBehavior walkBehavior;

    Robot(WalkBehavior walkBehavior) {
        this.walkBehavior = walkBehavior;
    }

    void walk() {
        walkBehavior.walk();
    }
}

// Main
public class StrategyDesignPattern {
    public static void main(String[] args) {

        Robot robot1 = new Robot(new NormalWalk());
        robot1.walk();

        Robot robot2 = new Robot(new NoWalk());
        robot2.walk();
    }
}
