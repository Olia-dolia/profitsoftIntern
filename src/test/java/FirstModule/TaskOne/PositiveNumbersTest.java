package FirstModule.TaskOne;

import org.junit.Assert;
import org.junit.Test;


public class PositiveNumbersTest {

    @Test
    public void arrMoreThanZero_PositiveNumbers_ArrDesc() {
        try {
            int[] input = new int[]{-5, 8, 12, 33, -6, 5};
            Integer[] actual = PositiveNumbers.arrMoreThanZero(input);
            Integer[] expected = new Integer[]{33, 12, 8, 5};

            Assert.assertArrayEquals(expected, actual);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}