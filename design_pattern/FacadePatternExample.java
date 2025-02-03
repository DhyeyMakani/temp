// step 1: create complex subsystems
class TV {
    void turnOn() { System.out.println("TV is turned ON"); }
    void setHDMI() { System.out.println("HDMI input set"); }
}

class SoundSystem {
    void turnOn() { System.out.println("sound is turned ON"); }
    void setVolume(int level) { System.out.println("volume=" + level); }
}

// step 2: create a facade class to simplify opes
class HomeTheaterFacade {
    private TV tv;
    private SoundSystem soundSystem;

    public HomeTheaterFacade(TV tv, SoundSystem soundSystem) {
        this.tv = tv;
        this.soundSystem = soundSystem;
    }

    public void watchMovie() {
        tv.turnOn();
        tv.setHDMI();
        soundSystem.turnOn();
        soundSystem.setVolume(10);
    }
}

// step 3: use the facade in main class
public class FacadePatternExample {
    public static void main(String[] args) {
        TV tv = new TV();
        SoundSystem soundSystem = new SoundSystem();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, soundSystem);
        
        // now, watching movie!
        homeTheater.watchMovie();
    }
}