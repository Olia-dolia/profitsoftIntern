package FirstModule.TaskOne;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GeomFiguresVolumeTest {

    private List<Figures> figuresListExpected;

    @Before
    public void setup() throws Exception{
        Figures cube = new Cube(12);
        Figures prism = new Prism(12, 3);
        Figures pyramid = new Pyramid(13, 4);
        Figures cylinder = new Cylinder(0, 8, 4);
        Figures cone = new Cone(12, 6,3);
        Figures sphere = new Sphere(12);
        figuresListExpected = new ArrayList<>();
        figuresListExpected.add(cube);
        figuresListExpected.add(prism);
        figuresListExpected.add(pyramid);
        figuresListExpected.add(cylinder);
        figuresListExpected.add(cone);
        figuresListExpected.add(sphere);
    }

    @Test
    public void geomFiguresVolume(){
        List<Figures> figuresList = new ArrayList<>(figuresListExpected);

        Map<String, Double> actual = new HashMap<>();
        int listSize = 0;
        while (listSize < figuresList.size()){
            actual.put(figuresList.get(listSize).getName(), figuresList.get(listSize).volume());
            listSize++;
        }
         actual.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
        Assert.assertEquals(figuresListExpected, figuresList);
    }
}