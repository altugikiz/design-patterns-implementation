public class Main {
    public static void main(String[] args) {
        System.out.println("=== Composite Pattern Demo (File System) ===\n");

        // 1. Create individual files (Leaf)
        FileSystemComponent file1 = new File("Notes.txt");
        FileSystemComponent file2 = new File("Image.jpg");
        FileSystemComponent file3 = new File("Homework.docx");

        // 2. Create folders (Composite)
        Folder mainFolder = new Folder("MyDocuments");
        Folder subFolder = new Folder("LectureNotes");

        // 3. Build the tree structure
        // Add files to the subfolder
        subFolder.addComponent(file1);
        subFolder.addComponent(file3);

        // Add both a file and another folder to the main folder
        mainFolder.addComponent(file2); // Added a file
        mainFolder.addComponent(subFolder); // Added a folder inside a folder!

        // 4. Print the entire structure
        // We only call "show" on the top-level folder, it handles the rest.
        mainFolder.showDetails();
    }
}