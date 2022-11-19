package FirstModule.SecondTask.ParseFromAndToXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class XMLHandler extends DefaultHandler {
    private final ArrayList<Person> personArrayList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("person")) {
                String surname = attributes.getValue("surname");
                String nameFromXml = attributes.getValue("name");
                String name = nameFromXml.concat(" " + surname);
                String birthDate = attributes.getValue("birthDate");
                personArrayList.add(new Person(name, birthDate));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Node getPerson(Document doc, Person personFromList) {
        Element person = doc.createElement("person");
        person.setAttribute("name", personFromList.getName());
        person.setAttribute("birthDate", personFromList.getBirthDate());
        return person;
    }

    public static void writeInXml(ArrayList<Person> personArrayList) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            Element element = doc.createElement("persons");
            doc.appendChild(element);
            for (Person person: personArrayList) {
                element.appendChild(getPerson(doc,person));
            }
            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("src/main/resources/personsOutput.xml"));

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        try {
            parser.parse(new File("src/main/resources/persons.xml"), handler);
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Person person : handler.personArrayList)
            System.out.printf("Name: %s, birth day: %s%n", person.getName(), person.getBirthDate());

        writeInXml(handler.personArrayList);
    }
}
