// step 1: interface for pizza
interface Pizza {
    String getDescription();
    double getCost();
}

// step 2: basic pizza class
class PlainPizza implements Pizza {
    public String getDescription() {
        return "Plain Pizza";
    }
    public double getCost() {
        return 100;
    }
}

// step 3: abstract decorator class
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // composition: wrapping the existing pizza

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription();
    }

    public double getCost() {
        return pizza.getCost();
    }
}

// step 4: create concrete decorators (Extra Cheese, Mushrooms)
class ExtraCheese extends PizzaDecorator {
    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", cheese";
    }

    public double getCost() {
        return pizza.getCost() + 10;
    }
}

class Mushrooms extends PizzaDecorator {
    public Mushrooms(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + ", mushrooms";
    }

    public double getCost() {
        return pizza.getCost() + 5;
    }
}

// step 5: use the decorator pattern
public class DecoratorPatternExample {
    public static void main(String[] args) {
        // plain pizza
        Pizza pizza = new PlainPizza();
        System.out.println(pizza.getDescription() + " cst: " + pizza.getCost());

        // add cheese
        pizza = new ExtraCheese(pizza);
        System.out.println(pizza.getDescription() + " cst: " + pizza.getCost());

        // add mushrooms
        pizza = new Mushrooms(pizza);
        System.out.println(pizza.getDescription() + " cst: " + pizza.getCost());
    }
}