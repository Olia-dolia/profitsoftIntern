package FirstModule.SecondTask.ViolationOfTrafficRules;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViolationOfTrafficRules {

    public static void main(String[] args) throws IOException {
        List<Path> paths = getFileFromDirectory();
        List<Violation> violations = new ArrayList<>();
        for (Path p : paths) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader fileReader = new FileReader(p.toString())) {
                Object obj = jsonParser.parse(fileReader);
                JSONArray violationList = (JSONArray) obj;

                for (Object o : violationList) {
                    Violation vio = parseObjectFromJSON((JSONObject) o);
                    violations.add(vio);
                }
                writeInXml(getTypeAndAmount(violations));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Path> getFileFromDirectory() throws IOException {//get paths from directory
        try (Stream<Path> paths = Files.walk(
                Paths.get("src/main/java/FirstModule/SecondTask/ViolationOfTrafficRules/infoFiles"))) {
            return paths.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
    }

    private static Violation parseObjectFromJSON(JSONObject obj) {// parse objects to class
        String firstName = (String) obj.get("first_name");
        String lastName = (String) obj.get("last_name");
        Double amount = (Double) obj.get("fine_amount");
        String date = (String) obj.get("date_time");
        String type = (String) obj.get("type");

        return new Violation(date, firstName, lastName, type, amount);
    }

    private static Map<String, Double> getTypeAndAmount(List<Violation> violations) {//create desc map type:amount
        Map<String, Double> result = new HashMap<>();
        Function<String, Set<String>> typeOfVio = (vio) ->
                Arrays.stream(vio.split("(?:\")([^\"]+)(?:\")"))
                        .collect(Collectors.toSet());
        violations.forEach(vio -> {
            for (String type : typeOfVio.apply(vio.getTypeOfVio())) {
                result.merge(type, vio.getAmount(), Double::sum);
            }
        });
        return result.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (previousVal, newVal) -> previousVal,
                                LinkedHashMap::new));
    }

    private static Node setViolation(Document doc, String type, Double totalAmount) {//set node
        Element violation = doc.createElement("violation");
        violation.setAttribute("type", type);
        violation.setAttribute("totalAmount", totalAmount.toString());
        violation.setTextContent("\n");
        return violation;
    }

    private static void writeInXml(Map<String, Double> violations) { //write to xml
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element element = doc.createElement("violations");
            doc.appendChild(element).setTextContent("\n");
            violations.forEach((key, value)
                    -> element.appendChild(setViolation(doc, key, value)));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(
                    "src/main/java/FirstModule/SecondTask/ViolationOfTrafficRules/infoFiles/allInfoAboutViolations.xml"));
            transformer.transform(source, file);
            System.out.println("File has been successfully created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
