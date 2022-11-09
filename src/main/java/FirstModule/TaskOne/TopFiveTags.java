package FirstModule.TaskOne;

import java.util.*;
import java.util.stream.Collectors;

public class TopFiveTags {
    public static void main(String[] args) {
            Map<String, Integer> dict = new HashMap<>();
            String[] input = {"#one bird was on the #tree in the forest",
                    "#one or #two #birds sits on the #tree",
                    "#one or #three birds sits on the #three #tree", "and here were a #three #men",
                    "a #three man with #one ax", "#three stumps and #three #men remained", "#three birds left this forest"};
            Arrays.stream(input).forEach(str -> {
                for (String tag : findTags(str)) {
                    dict.merge(tag, 1, Integer::sum);
                }
            });
            Map<String, Integer> sortedMapDesc = dict.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(5)
                    .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
            System.out.println(sortedMapDesc);
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
