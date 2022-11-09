package FirstModule.TaskOne;

import java.util.*;
import java.util.stream.Collectors;

public class GeomFiguresVolume {
    public static Map<String, Double> geomFiguresVolume(List<Figures> figuresList) {
        Map<String, Double> volumes = new HashMap<>();
        int listSize = 0;
        while (listSize < figuresList.size()) {
            volumes.put(figuresList.get(listSize).getName(), figuresList.get(listSize).volume());
            listSize++;
        }
        return volumes.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }

    public static void main(String[] args) {
        //System.out.println(geomFiguresVolume(figuresList));
    }
}
