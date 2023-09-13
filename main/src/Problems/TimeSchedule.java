package Problems;

import java.util.*;

public class TimeSchedule {

    static class Task {
        public final int index;
        public final int weight;
        public final int deadline;

        Task(int index, int deadline, int weight) {
            this.index = index;
            this.weight = weight;
            this.deadline = deadline;
        }
    }


    public static void main(String[] args) {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new Task(1, 3, 10));
        arr.add(new Task(2, 1, 8));
        arr.add(new Task(3, 5, 8));
        arr.add(new Task(4, 1, 7));
        arr.add(new Task(5, 4, 6));
        arr.add(new Task(6, 5, 4));
        arr.add(new Task(7, 4, 4));
        arr.add(new Task(8, 7, 3));
        arr.add(new Task(9, 8, 2));
        arr.add(new Task(10, 4, 1));

//        List<Task> canonical = getCanonicalForm(arr);
//        for (Task can:canonical) {
//            System.out.print(can.index+ " ");
//        }
//
//        System.out.println("Penalty=" +  getPenalty(arr));

        List<Integer> num = optimalSchedule4(arr);
        for (int can:num) {
            System.out.print(can+ " ");
        }

        System.out.println();
        System.out.println("----------------------");

        ArrayList<Task> arr1 = new ArrayList<>();
        arr1.add(new Task(1, 4, 70));
        arr1.add(new Task(2, 2, 60));
        arr1.add(new Task(3, 4, 50));
        arr1.add(new Task(4, 3, 40));
        arr1.add(new Task(5, 1, 30));
        arr1.add(new Task(6, 4, 20));
        arr1.add(new Task(7, 6, 10));

//        List<Task> canonical1 = getCanonicalForm(arr1);
//        for (Task can:canonical1) {
//            System.out.print(can.index+ " ");
//        }
//
//        System.out.println("Penalty=" +  getPenalty(arr1));

          List<Integer> num2 = optimalSchedule4(arr1);
          for (int can:num2) {
            System.out.print(can+ " ");
          }

    }

    public static int getPenalty(List<Task> tasks) {
        List<List<Task>> arr = optimalSchedule(tasks);
        List<Task> missedTasks = arr.get(1);
        int penalty = 0;
        for (Task missed: missedTasks) {
            penalty += missed.weight;
        }
        return penalty;
    }

    public static List<Task> getCanonicalForm(List<Task> tasks) {
        List<List<Task>> arr = optimalSchedule(tasks);

        List<Task> all = new ArrayList<>(arr.get(0));
        all.addAll(arr.get(1));

        return all;
    }

    /**
     * it returns List consisting of two elements one is the accepted one and the other is
     * */
    private static List<List<Task>> optimalSchedule(List<Task> orgTasks) {
        List<Task> tasks = new ArrayList<>(orgTasks); // create a clone
        List<Task> schedule = new ArrayList<>();
        List<Task> missedTasks = new ArrayList<>();

        // Create a list of tasks to maintain the original order
        List<Task> originalOrderTasks = new ArrayList<>(tasks);

        // Sort tasks by their deadlines in ascending order if equal check the weight so that the bigger the penalty will get in the front
        Comparator<Task> comp = Comparator.comparingInt(task -> task.deadline);
        Comparator<Task> realOne = comp.thenComparing(task -> -1 * task.weight);

//        Comparator<Task> comp = Comparator.comparingInt(task -> -1 * task.weight);
//        Comparator<Task> realOne = comp.thenComparing(task -> task.deadline);
        tasks.sort(realOne);

        int currentTime = 0;
        int currentIndex = 0;

        for (Task task : tasks) {
            // Check if the task can be scheduled without missing the deadline
            if (currentTime < task.deadline) {
                schedule.add(task);
                currentTime++;
            } else {
                // If the task would miss the deadline, mark it with -1
                missedTasks.add(task);
            }

            // Update the current index in the original order
            while (currentIndex < originalOrderTasks.size() &&
                    originalOrderTasks.get(currentIndex).index != task.index) {
                currentIndex++;
            }
        }

        // Add placeholders for unscheduled tasks in the original order
        for (int i = currentIndex + 1; i < originalOrderTasks.size(); i++) {
            missedTasks.add(originalOrderTasks.get(i));
        }

        List<List<Task>> finalValue = new ArrayList<>();
        finalValue.add(schedule);
        finalValue.add(missedTasks);
        return finalValue;
    }

    public static List<Integer> optimalSchedule1(List<Task> t) {
        List<Task> tasks = new ArrayList<>(t);

        int n = tasks.size();

        // Sort tasks by their deadlines in ascending order
        tasks.sort(Comparator.comparingInt(task -> task.deadline));

        // Initialize dynamic programming table dp
        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                int cost = tasks.get(i - 1).weight;
                if (j > 0) {
                    cost += dp[j];
                }

                if (cost < dp[i] && i <= tasks.get(j).deadline) {
                    dp[i] = cost;
                    prev[i] = j;
                }
            }
        }

        // Reconstruct the optimal schedule
        List<Integer> schedule = new ArrayList<>();
        int currentTask = n;
        while (currentTask > 0) {
            schedule.add(tasks.get(currentTask - 1).index);
            currentTask = prev[currentTask];
        }
        Collections.reverse(schedule);

        return schedule;
    }

    public static List<Integer> optimalSchedule3(List<Task> t) {
        List<Task> tasks = new ArrayList<>(t);
        int n = tasks.size();

        // Sort tasks by their deadlines in ascending order
        tasks.sort(Comparator.comparingInt(task -> task.deadline));

        // Initialize dynamic programming table dp
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > 0 && j <= tasks.get(i - 1).deadline) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + tasks.get(i - 1).weight);
                }
            }
        }

        // Reconstruct the optimal schedule
        List<Integer> schedule = new ArrayList<>();
        int currentTask = n;
        int currentDeadline = n;

        while (currentTask > 0 && currentDeadline > 0) {
            if (dp[currentTask][currentDeadline] != dp[currentTask - 1][currentDeadline]) {
                schedule.add(tasks.get(currentTask - 1).index);
                currentTask--;
                currentDeadline--;
            } else {
                currentTask--;
            }
        }
        Collections.reverse(schedule);

        return schedule;
    }



    public static List<Integer> optimalSchedule4(List<Task> t) {
        List<Task> tasks = new ArrayList<>(t);
        List<Integer> schedule = new ArrayList<>();

        // Sort tasks by penalty in descending order
        tasks.sort(Comparator.comparingInt(task -> -task.weight));

        int maxDeadline = 0;

        for (Task task : tasks) {
            maxDeadline = Math.max(maxDeadline, task.deadline);
        }

        int[] slots = new int[maxDeadline];
        for (int i = 0; i < maxDeadline; i++) {
            slots[i] = -1;
        }

        for (Task task : tasks) {
            for (int i = task.deadline - 1; i >= 0; i--) {
                if (slots[i] == -1) {
                    slots[i] = task.index;
                    break;
                }
            }
        }

        for (int slot : slots) {
            schedule.add(slot);
        }

        return schedule;
    }
}
