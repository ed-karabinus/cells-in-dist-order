package com.karabinus.cells_in_dist_order;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] cellsInDistOrder = new int[R * C][2];
        boolean[][] visited = new boolean[R][C];
        int distOrderIndex = 0;
        Deque<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.offerLast(new int[] {r0, c0});
        while (bfsQueue.peekFirst() != null) {
            int[] coordinates = bfsQueue.pollFirst();
            int r = coordinates[0];
            int c = coordinates[1];
            if (r < 0 || c < 0 || r >= R || c >= C || visited[r][c]) {
                continue;
            }
            cellsInDistOrder[distOrderIndex] = coordinates;
            visited[r][c] = true;
            distOrderIndex++;
            bfsQueue.offerLast(new int[] {r + 1, c});
            bfsQueue.offerLast(new int[] {r - 1, c});
            bfsQueue.offerLast(new int[] {r, c + 1});
            bfsQueue.offerLast(new int[] {r, c - 1});
        }
        return cellsInDistOrder;
    }
}