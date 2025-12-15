public class Main {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Demo (Legacy Integration) ===\n");

        // SCENARIO: Our application wants to work with JSON
        String jsonData = "{ \"customer\": \"Altug\", \"amount\": 100 }";

        // 1. Create the old system (Adaptee)
        OldXmlReader oldSystem = new OldXmlReader();

        // 2. Bring in the adapter (We wrap the old system inside)
        IJsonParser adapter = new XmlToJsonAdapter(oldSystem);

        // 3. The application now uses the old system as if it were
        // a new JSON parser
        System.out.println("Client: Sending JSON data...");
        adapter.parseJson(jsonData);
    }
}