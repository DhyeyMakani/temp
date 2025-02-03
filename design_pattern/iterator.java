import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

// custom playlist class with an iterator
class Playlist implements Iterable<String> {
    private List<String> songs = new ArrayList<>();

    public void addSong(String song) {
        songs.add(song);
    }

    @Override
    public Iterator<String> iterator() {
        return songs.iterator();
    }
}

public class iterator {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.addSong("Song 3");

        for (String song : playlist) {
            System.out.println(song); // prints songs one by one
        }
    }
}