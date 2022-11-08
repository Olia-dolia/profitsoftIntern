package FirstModule.TaskOne;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopFiveTags {
    public static void main(String[] args) {
            HashMap<String, Integer> dict = new HashMap<>();
            String[] input = {"#one blah",
                    "blah #one #two blah",
                    "#one blah #three #three"};
            Arrays.stream(input).forEach(str -> {
                for (String tag : findTags(str)) {
                    dict.merge(tag, 1, Integer::sum);
                }
            });
            for (Map.Entry<String, Integer> entry : dict.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        private static Set<String> findTags(String str) {
            HashSet<String> tags;
            String[] splitedInput = str.split(" ");
            tags = (HashSet<String>) Arrays.stream(splitedInput)
                    .filter(in -> in.matches("#.+"))
                    .collect(Collectors.toSet());
            return tags;
        }
}
