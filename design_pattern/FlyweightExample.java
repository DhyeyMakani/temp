import java.util.HashMap;

// step 1: create a common flyweight class
class Shape {
    private String color;
    
    public Shape(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println(color + " shape.");
    }
}

// step 2: create a factory to reuse objects
class ShapeFactory {
    private static final HashMap<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String color) {
        if (!shapeMap.containsKey(color)) {
            shapeMap.put(color, new Shape(color));
            System.out.println("new " + color + " shape.");
        }
        return shapeMap.get(color);
    }
}

// step 3: use the flyweight pattern
public class FlyweightExample {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.getShape("Red");
        Shape shape2 = ShapeFactory.getShape("Blue");
        Shape shape3 = ShapeFactory.getShape("Red"); // reuses existing "Red" shape

        shape1.draw();
        shape2.draw();
        shape3.draw(); // does not create a new object
    }
}