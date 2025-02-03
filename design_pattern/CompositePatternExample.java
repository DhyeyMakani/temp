import java.util.ArrayList;
import java.util.List;

// component - common interface for files and folders
interface FileSystem {
    void showDetails();
}

// leaf - represents individual files
class File implements FileSystem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// composite - represents folders containing files or subfolders
class Folder implements FileSystem {
    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystem component) {
        children.add(component);
    }

    public void showDetails() {
        System.out.println("fol: " + name);
        for (FileSystem component : children) {
            component.showDetails();
        }
    }
}

public class CompositePatternExample {
    public static void main(String[] args) {
        File file1 = new File("doc.txt");
        File file2 = new File("img.png");

        Folder mainFolder = new Folder("mnfolder");
        Folder subFolder = new Folder("subFolder");

        // adding files to the subfolder
        subFolder.add(file1);

        // adding subfolder and file to the main folder
        mainFolder.add(subFolder);
        mainFolder.add(file2);

        // display structure
        mainFolder.showDetails();
    }
}