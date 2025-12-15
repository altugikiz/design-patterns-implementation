public class XmlToJsonAdapter implements IJsonParser {

    private OldXmlReader xmlReader;

    public XmlToJsonAdapter(OldXmlReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    @Override
    public void parseJson(String jsonData) {
        // 1. Receive the incoming JSON data
        System.out.println("Adapter: JSON data received, converting to XML...");

        // 2. Convert it to XML format that the old system understands (Simulation)
        String convertedXml = "<xml>" + jsonData + "</xml>";

        // 3. Delegate the work to the old class that will actually do the job
        xmlReader.readXml(convertedXml);
    }
}