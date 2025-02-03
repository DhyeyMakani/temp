// singleton pattern

public class Sam {
    private static Sam sam; // single instance of class

    private Sam() {
        // private constructor to prevent instantiation
    }

    public static Sam getSam() {
        if (sam == null) {
            sam = new Sam(); // create instance only if it does not exist
        }
        return sam; // return the same instance
    }

    public static void main(String[] args) {
        Sam sam1 = Sam.getSam();
        System.out.println(sam1.hashCode());

        Sam sam2 = Sam.getSam();
        System.out.println(sam2.hashCode());
        
        // both hash codes will be the same, proving it's a singleton
    }
}