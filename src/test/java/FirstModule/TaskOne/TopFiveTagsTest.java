package FirstModule.TaskOne;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TopFiveTagsTest {

    private final String[] input = {"#one bird was on the #tree in the forest",
            "#one or #two #birds sit on the #tree",
            "#one or #three birds sit on the #three #tree", "and here were a #three #men",
            "a #three man with #one ax", "#three stumps and #three #men remained", "#three birds left this forest"};

    @Test
    public void printTopFiveTags_MapFiveTags_DescMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("#three", 5);
        expected.put("#one", 4);
        expected.put("#tree", 3);
        expected.put("#men", 2);
        expected.put("#birds", 1);

        Map<String, Integer> actual = TopFiveTags.printTopFiveTags(input);

        assertEquals(expected, actual);
    }

    @Test
    public void printTopFiveTags_LESS_THAN_FIVE() {
        Map<String, Integer> actual = TopFiveTags.printTopFiveTags(input);
        if (actual.size() < 5) {
            fail("Your text has less than five tags");
        }
    }
}