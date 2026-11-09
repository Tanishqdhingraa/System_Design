package Adapter_Design_Pattern;

// Target interface
interface IReports {
    String getJsonData(String data);
}


//! Existing class (Adaptee)
class XmlDataProvider {

    String getXmlData(String data) {
        String[] parts = data.split(":");

        String name = parts[0];
        String id = parts[1];

        return "<user>"
                + "<name>" + name + "</name>"
                + "<id>" + id + "</id>"
                + "</user>";
    }
}


// Adapter
class XmlToJsonAdapter implements IReports {

    private XmlDataProvider xmlProvider;

    XmlToJsonAdapter(XmlDataProvider xmlProvider) {
        this.xmlProvider = xmlProvider;
    }

    @Override
    public String getJsonData(String data) {

        String xml = xmlProvider.getXmlData(data);

        // Convert XML to JSON
        String name = xml.split("<name>")[1].split("</name>")[0];
        String id = xml.split("<id>")[1].split("</id>")[0];

        return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
    }
}


// Client
class Client {

    void getReport(IReports report, String data) {
        System.out.println(report.getJsonData(data));
    }
}


// Main
public class AdapterPattern {

    public static void main(String[] args) {

        XmlDataProvider xmlProvider = new XmlDataProvider();

        IReports adapter =
                new XmlToJsonAdapter(xmlProvider);

        Client client = new Client();

        client.getReport(adapter, "Alice:42");
    }
}