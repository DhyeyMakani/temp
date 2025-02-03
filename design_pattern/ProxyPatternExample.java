// step 1: create Interface
interface Image {
    void display();
}

// step 2: real img class (actual obj)
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("load " + fileName); // file load
    }

    public void display() {
        System.out.println("displaying " + fileName);
    }
}

// step 3: proxy class (controls access)
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        // load only if needed (lazy initialization)
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// step 4: test the proxy pattern
public class ProxyPatternExample {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // first time: loads and displays image
        image.display();
        
        System.out.println("\nSecond time:");

        // second time: uses cached image, doesn't load again
        image.display();
    }
}