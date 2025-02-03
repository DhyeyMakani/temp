// product class(pizza)
class Pizza {
    private String size;
    private String cheese;
    private boolean mushrooms;
    private boolean pepperoni;

    // private constructor(only Builder can create Pizza)
    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.mushrooms = builder.mushrooms;
        this.pepperoni = builder.pepperoni;
    }

    // display final pizza
    public void showPizza() {
        System.out.println("pizza: " + size + ", " + cheese + 
            (mushrooms ? ", mushrooms" : "") + 
            (pepperoni ? ", pepperoni" : ""));
    }

    // builder class
    public static class PizzaBuilder {
        private String size;
        private String cheese;
        private boolean mushrooms;
        private boolean pepperoni;

        public PizzaBuilder(String size, String cheese) {
            this.size = size;
            this.cheese = cheese;
        }

        public PizzaBuilder addMushrooms() {
            this.mushrooms = true;
            return this;
        }

        public PizzaBuilder addPepperoni() {
            this.pepperoni = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// testing builder pattern
public class builder {
    public static void main(String[] args) {
        // step-by-step pizza making
        Pizza myPizza = new Pizza.PizzaBuilder("large", "xyz")
                .addMushrooms()
                .addPepperoni()
                .build();

        myPizza.showPizza(); // o.p.: pizza: large, xyz, mushrooms, pepperoni
    }
}