package Problems;

import java.util.ArrayList;
import java.util.List;

class TSPSolver {
    private final int[][] distanceMatrix;
    private final int citiesCount;
    private List<Integer> bestPath;
    private int bestCost;

    public TSPSolver(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.citiesCount = distanceMatrix.length;
        this.bestPath = new ArrayList<>();
        this.bestCost = Integer.MAX_VALUE;
    }

    public void solve() {
        List<Integer> remainingCities = new ArrayList<>();
        for (int i = 1; i < citiesCount; i++) {
            remainingCities.add(i);
        }

        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0);  // Start from the first city
        findOptimalPath(0, remainingCities, 0, currentPath);

        System.out.println("Best Path: " + bestPath);
        System.out.println("Best Cost: " + bestCost);
    }

    private void findOptimalPath(int currentCity, List<Integer> remainingCities, int currentCost, List<Integer> currentPath) {
        if (remainingCities.isEmpty()) {
            int totalCost = currentCost + distanceMatrix[currentCity][0];
            if (totalCost < bestCost) {
                bestCost = totalCost;
                bestPath = new ArrayList<>(currentPath);
            }
            return;
        }

        for (int i = 0; i < remainingCities.size(); i++) {
            int nextCity = remainingCities.get(i);
            int newCost = currentCost + distanceMatrix[currentCity][nextCity];

            List<Integer> newRemainingCities = new ArrayList<>(remainingCities);
            newRemainingCities.remove(i);

            List<Integer> newPath = new ArrayList<>(currentPath);
            newPath.add(nextCity);

            findOptimalPath(nextCity, newRemainingCities, newCost, newPath);
        }
    }
}

public class TSPOptimal {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 8, 11, 7, 8, 9},
                {8, 0, 11, 7, 12, 6},
                {11, 11, 0, 6, 9, 10},
                {7, 7, 6, 0, 10, 8},
                {8, 12, 9, 10, 0, 12},
                {9, 6, 10, 8, 12, 0}
        };

        TSPSolver tspSolver = new TSPSolver(graph);
        tspSolver.solve();
    }
}