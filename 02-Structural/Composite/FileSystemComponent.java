public abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    // Common action for both files and folders
    public abstract void showDetails();
}