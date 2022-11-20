package FirstModule.SecondTask.ParseFromAndToXml;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomXMLParser {

    public static void main(String[] args) {
        /*String inputFileName = "persons.xml";
        try {
            Scanner scanner = new Scanner(getFileFromResource(inputFileName));
            while (scanner.hasNextLine()){
                Pattern p = Pattern.compile("(name|surname)");
                Matcher match = p.matcher(scanner.nextLine());
                if (match.find()) {
                    concatFullName(scanner.nextLine());
                }
            }
            scanner.close();
        } catch (Exception e){
            e.printStackTrace();
        }*/
        String[] str = new String[]{
                " <persons>", " <person name=\"Іван\" surname=\"Котляревський\" birthDate=\"09.09.1769\" />",
                "<person surname=\"Шевченко\" name=\"Тарас\" birthDate=\"09.03.1814\" />", "    <person name=\"Леся\"\n" +
                "            surname=\"Українка\"\n" +
                "            birthDate=\"13.02.1871\" />"};
        for (String s: str) {
            concatFullName(s);
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


    private static void concatFullName(String input){
        String fullName = "";
        String name = "";
        Pattern p = Pattern.compile("(?<=(name=))(?:\")([^\"]+)(?:\")|(?<=(name = ))(?:\")([^\"]+)(?:\")");
        Matcher matcher = p.matcher(input);
        while (matcher.find()){
                name += matcher.group(2).concat(" ");
        }
        System.out.println(name.trim());
    }
}
