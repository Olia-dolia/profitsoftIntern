package FirstModule.SecondTask.ParseFromAndToXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


public class XMLHandler extends DefaultHandler {
    private final ArrayList<Person> personArrayList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("person")) {
                String surnameFromXml = attributes.getValue("surname");
                String nameFromXml = attributes.getValue("name");
                String name = nameFromXml.concat(" " + surnameFromXml);
                String birthDate = attributes.getValue("birthDate");
                personArrayList.add(new Person(name, birthDate));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static Node setPerson(Document doc, Person personFromList) {
        Element person = doc.createElement("person");
        person.setAttribute("name", personFromList.getName());
        person.setAttribute("birthDate", personFromList.getBirthDate());
        person.setTextContent("\n");
        return person;
    }

    private static void writeInXml(ArrayList<Person> personArrayList) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element element = doc.createElement("persons");
            doc.appendChild(element);
            for (Person person: personArrayList) {
                element.appendChild(setPerson(doc,person));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("src/main/resources/personsOutput.xml"));
            transformer.transform(source, file);
            System.out.println("File has been successfully created!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedInputStream getFileFromResource(String fileName) {
        ClassLoader classLoader = XMLHandler.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            System.out.println("Okay!");
            return (BufferedInputStream) inputStream;
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        try {
            parser.parse(getFileFromResource("persons.xml"),handler);
        } catch (Exception e) {
            System.out.println(e);
        }

        for (Person person : handler.personArrayList)
            System.out.printf("Name: %s, birth day: %s%n", person.getName(), person.getBirthDate());

        writeInXml(handler.personArrayList);
    }
}
