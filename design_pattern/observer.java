import java.util.*;

// subscriber (observer) interface
interface Subscriber {
    void update(String videoTitle);
}

// youTube channel (subject)
class YouTubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>();

    void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    void uploadVideo(String videoTitle) {
        System.out.println("new video uploaded: " + videoTitle);
        for (Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }
}

// concrete subscriber
class User implements Subscriber {
    private String name;

    User(String name) {
        this.name = name;
    }

    public void update(String videoTitle) {
        System.out.println(name + " got notified: " + videoTitle);
    }
}

// testing the observer pattern
public class observer {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        // creating subscribers
        User alice = new User("alice");
        User bob = new User("bob");

        // users subscribe to the channel
        channel.subscribe(alice);
        channel.subscribe(bob);

        // upload a video (notifies all subscribers)
        channel.uploadVideo("java");

        // bob unsubscribes
        channel.unsubscribe(bob);

        // upload another video (only alice gets notified)
        channel.uploadVideo("dp");
    }
}
