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

        List<Task> canonical = getCanonicalForm(arr);
        for (Task can : canonical) {
            System.out.print(can.index + " ");
        }

        System.out.println("Penalty=" + getPenalty(arr));


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

        List<Task> canonical1 = getCanonicalForm(arr1);
        for (Task can : canonical1) {
            System.out.print(can.index + " ");
        }

        System.out.println("Penalty=" + getPenalty(arr1));

        System.out.println();
        System.out.println("----------------------");


        ArrayList<Task> arr2 = new ArrayList<>();
        arr2.add(new Task(1, 2, 15));
        arr2.add(new Task(2, 3, 12));
        arr2.add(new Task(3, 3, 10));
        arr2.add(new Task(4, 1, 8));
        arr2.add(new Task(5, 5, 7));
        arr2.add(new Task(6, 2, 5));
        arr2.add(new Task(7, 4, 4));
        arr2.add(new Task(8, 5, 1));

        List<Task> canonical2 = getCanonicalForm(arr2);
        for (Task can : canonical2) {
            System.out.print(can.index + " ");
        }

        System.out.println("Penalty=" + getPenalty(arr2));
    }

    public static int getPenalty(List<Task> tasks) {
        List<List<Task>> arr = optimalSchedule(tasks);
        List<Task> missedTasks = arr.get(1);
        int penalty = 0;
        for (Task missed : missedTasks) {
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

    public static List<List<Task>> optimalSchedule(List<Task> t) {
        List<Task> tasks = new ArrayList<>(t);
        List<Task> schedule = new ArrayList<>();
        List<Task> missed = new ArrayList<>();

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
            if (slot - 1 >= 0) {
                schedule.add(t.get(slot - 1));
            }
        }

        // missed construction
        for (Task orgTask:t) {
            if(!schedule.contains(orgTask)){
                missed.add(orgTask);
            }
        }


        List<List<Task>> returnValue = new ArrayList<>();
        returnValue.add(schedule);
        returnValue.add(missed);
        return returnValue;
    }
}
