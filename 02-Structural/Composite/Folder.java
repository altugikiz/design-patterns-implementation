import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemComponent {

    // A folder can contain files or other folders
    // Since our list type is 'FileSystemComponent', it covers both!
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);

        // RECURSION: Call the same method for each child component
        for (FileSystemComponent component : children) {
            component.showDetails();
        }
    }
}