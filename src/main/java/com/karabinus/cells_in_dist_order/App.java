package com.karabinus.cells_in_dist_order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static double numberOfMillisecondsPerSecond = 1000.0;

    public static void main(String[] args) {
        if (args.length < 4) {
            return;
        }
        long start = System.currentTimeMillis();
        int[][] result = new App().allCellsDistOrder(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        long finish = System.currentTimeMillis();
        for (int[] coordinates : result) {
            System.out.printf("[%d, %d]\n", coordinates[0], coordinates[1]);
        }
        System.out.printf("%.3f seconds elapsed.\n", (finish - start) / numberOfMillisecondsPerSecond);
    }

    private int r0;
    private int c0;

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        this.r0 = r0;
        this.c0 = c0;
        Map<Integer, List<List<Integer>>> cellsIndexedByDistance = new HashMap<>();
        for (int rIndex = 0; rIndex < R; rIndex++) {
            for (int cIndex = 0; cIndex < C; cIndex++) {
                List<Integer> coordinates = Arrays.asList(rIndex, cIndex);
                int manhattanDistance = getManhattanDistanceFromCell(coordinates);
                List<List<Integer>> existingCells = 
                    cellsIndexedByDistance.getOrDefault(manhattanDistance, new ArrayList<>());
                existingCells.add(coordinates);
                cellsIndexedByDistance.put(manhattanDistance, existingCells);
            }
        }
        List<List<Integer>> cellsInDistOrder = new ArrayList<>();
        for (int distanceIndex = 0; cellsIndexedByDistance.get(distanceIndex) != null; distanceIndex++) {
            for (List<Integer> cell : cellsIndexedByDistance.get(distanceIndex)) {
                cellsInDistOrder.add(cell);
            }
        }
        return cellsInDistOrder.stream()
            .map(coordinate -> coordinate.stream().mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }
    
    private int getManhattanDistanceFromCell(List<Integer> coordinates) {
        int r = coordinates.get(0);
        int c = coordinates.get(1);
        return Math.abs(r - r0) + Math.abs(c - c0);
    }
}