public class NameRepository implements Container {
    
    // Veriler burada saklanıyor (Veritabanı veya Liste olabilir)
    public String[] names = {"Altug", "Ayse", "Mehmet", "Fatma"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    // INNER CLASS (Dahili Sınıf)
    // Bu sınıf sadece NameRepository'ye hizmet eder.
    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            // Dizi sınırını aştık mı kontrol et
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            // Varsa veriyi dön ve indeksi bir artır
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}