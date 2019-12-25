package com.karabinus.cells_in_dist_order;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest 
{
    private static App app;

    @BeforeClass
    public static void setup() throws Exception {
        app = new App();
    }

    @Test
    public void twoByOneMatrix() {
        int[][] result = app.allCellsDistOrder(1, 2, 0, 0);
        List<List<Integer>> resultAsNestedLists = Arrays.stream(result).map(row -> Arrays.stream(row).boxed().collect(Collectors.toList())).collect(Collectors.toList());
        assertTrue(resultAsNestedLists.get(0).equals(Arrays.asList(0, 0)));
        assertTrue(resultAsNestedLists.get(1).equals(Arrays.asList(0, 1)));
    }

    @Test(timeout = 25) 
    public void largeMatrixWithTimeout() {
        app.allCellsDistOrder(89, 90, 21, 65);
    }
}
