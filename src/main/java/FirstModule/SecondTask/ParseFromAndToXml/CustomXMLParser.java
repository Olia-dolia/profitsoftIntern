package FirstModule.SecondTask.ParseFromAndToXml;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class CustomXMLParser {

    public static void main(String[] args) {
        String inputFileName = "persons.xml";
        try {
            Scanner scanner = new Scanner(getFileFromResource(inputFileName));
            while (scanner.hasNext()){
                //function for parse
                //function for write
            }
            scanner.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {

        URL fileFromResource = CustomXMLParser.class.getClassLoader().getResource(fileName);
        if (fileFromResource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            System.out.println("Its work");
            return new File(fileFromResource.toURI());
        }
    }

}
