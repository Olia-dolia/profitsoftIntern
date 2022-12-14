package FirstModule.TaskOne;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopFiveTags {
    public static void main(String[] args) {
        String[] input = {"#one bird was on the #tree in the forest",
                "#one or #two #birds sit on the #tree",
                "#one or #three birds sit on the #three #tree", "and here were a #three #men",
                "a #three man with #one ax", "#three stumps and #three #men remained", "#three birds left this forest"};
        System.out.println(printTopFiveTags(input));
    }

    public static Map<String, Integer> printTopFiveTags(String[] input) {
        Map<String, Integer> dict = new HashMap<>();

        Function<String, Set<String>> findTags = (str) ->
                Arrays.stream(str.split(" "))
                        .filter(in -> in.matches("#.+"))
                        .collect(Collectors.toSet());

        Arrays.stream(input).forEach(str -> {
            for (String tag : findTags.apply(str)) {
                dict.merge(tag, 1, Integer::sum);
            }
        });

        if (dict.size() < 5) {
            System.out.println("Your text has less than five tags!");
            return dict;
        }
        return dict.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (previousVal, newVal) -> previousVal,
                                LinkedHashMap::new));

    }
}
