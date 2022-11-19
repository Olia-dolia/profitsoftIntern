package FirstModule.SecondTask.ParseFromAndToXml;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomXMLParser {

    public static void main(String[] args) {
        String inputFileName = "persons.xml";
        try {
            Scanner scanner = new Scanner(getFileFromResource(inputFileName));
            while (scanner.hasNextLine()){
                concatFullName(scanner.nextLine());
                /*function concatName(scanner)
                *   reg = [A-Z](for name)
                *   reg = [A-Z](for surname)
                *   if(scanner.findInLine("name"))
                *       String name = scanner.findInLine(regN).concat(" " + scanner.findInLine(regS));
                *       write(name);
                *    else
                *        write(scanner)
                * */
            }
            scanner.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static BufferedInputStream getFileFromResource(String fileName) {
        ClassLoader classLoader = CustomXMLParser.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            System.out.println("Okay!");
            return (BufferedInputStream) inputStream;
        }
    }

    private static String concatFullName(String input){
        Pattern p = Pattern.compile("(name|surname)");
        //Pattern p2 = Pattern.compile("(?:\")([^\"]+)(?:\")");
        //StringBuilder stringBuilder = new StringBuilder(input);
        Matcher match = p.matcher(input);
        String surname = "";
        String name = "";
        if (match.find()){
         name = Arrays.stream(input.split(" "))
                  .filter(in -> in.matches("(?<=(name=))(?:\")([^\"]+)(?:\")|(?<=(name = ))(?:\")([^\"]+)(?:\")"))
                  .toString();
         surname = Arrays.stream(input.split(" "))
                  .filter(in -> in.matches("(?<=(surname=))(?:\")([^\"]+)(?:\")|(?<=(surname = ))(?:\")([^\"]+)(?:\")"))
                  .toString();
            System.out.println(name+ "\n" + surname);
        }
        return input;
    }

}
