public class NameRepository implements Container {

    // Data is stored here (Could be a Database or List)
    public String[] names = { "Altug", "Daryl", "Negan", "Rick" };

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    // INNER CLASS
    // This class only serves NameRepository.
    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            // Check if we exceeded the array bounds
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            // If exists, return the data and increment the index
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}