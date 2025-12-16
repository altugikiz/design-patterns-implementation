public interface Iterator {
    boolean hasNext(); // Is there anyone else in line?
    Object next();     // Bring the next person.
}