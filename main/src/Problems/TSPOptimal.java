package Problems;

import java.util.ArrayList;
import java.util.List;

class TSPSolver {
    private final int[][] distanceMatrix;
    private final int citiesCount;
    private List<Integer> optimalPath;
    private int optimalCost;

    public TSPSolver(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
        this.citiesCount = distanceMatrix.length;
        this.optimalPath = new ArrayList<>();
        this.optimalCost = Integer.MAX_VALUE;
    }

    public void solve() {
        List<Integer> remainingCities = new ArrayList<>();
        for (int i = 1; i < citiesCount; i++) {
            remainingCities.add(i);
        }

        List<Integer> path = new ArrayList<>();
        path.add(0);  // Start from the first city
        findOptimalPath(0, remainingCities, 0, path);

        System.out.println("The Best Path: " + optimalPath);
        System.out.println("The Best Cost: " + optimalCost);
    }

    private void findOptimalPath(int currentCity, List<Integer> remainingCities, int currentCost, List<Integer> path) {
        if (remainingCities.isEmpty()) {
            int totalCost = currentCost + distanceMatrix[currentCity][0];
            if (totalCost < optimalCost) {
                optimalCost = totalCost;
                optimalPath = new ArrayList<>(path);
            }
            return;
        }

        for (int i = 0; i < remainingCities.size(); i++) {
            int nextCity = remainingCities.get(i);
            int newCost = currentCost + distanceMatrix[currentCity][nextCity];

            List<Integer> newRemainingCities = new ArrayList<>(remainingCities);
            newRemainingCities.remove(i);

            List<Integer> newPath = new ArrayList<>(path);
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