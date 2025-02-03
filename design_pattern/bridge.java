// step 1: create "color" interface(implementation)
interface Color {
    void fillColor();
}

// step 2: implement different colors
class RedColor implements Color {
    public void fillColor() {
        System.out.println("red color");
    }
}

class BlueColor implements Color {
    public void fillColor() {
        System.out.println("blue color");
    }
}

// step 3: create the "shape" abstract class(abstraction)
abstract class Shape {
    protected Color color;  // bridge to implementation

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();  // abstract method
}

// step 4: implement different shapes
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    public void draw() {
        System.out.print("draw circle ");
        color.fillColor();
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    public void draw() {
        System.out.print("draw Square ");
        color.fillColor();
    }
}

// step 5: use bridge pattern
public class bridge {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        redCircle.draw();  // op: Draw circle Red color

        Shape blueSquare = new Square(new BlueColor());
        blueSquare.draw(); // op: Draw Square Blue color
    }
}