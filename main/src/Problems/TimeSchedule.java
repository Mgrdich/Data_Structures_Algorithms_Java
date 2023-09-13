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

        @Override
        public String toString() {
            return String.valueOf(this.index);
        }

    }

    static class OptimalSchedule {
        private final List<Task> tasks;
        private List<Task> scheduled;
        private List<Task> canonicalForm;
        private List<Task> missed;
        private int penalty;


        OptimalSchedule(List<Task> tasks) {
            this.tasks = tasks;
            init();
        }

        public int getPenalty() {
            return this.penalty;
        }

        public List<Task> getCanonicalForm() {
            return canonicalForm;
        }

        public List<Task> getMissed() {
            return missed;
        }

        public List<Task> getScheduled() {
            return scheduled;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        private void init() {
            List<Task> tasks = new ArrayList<>(this.tasks);
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
                    schedule.add(this.tasks.get(slot - 1));
                }
            }

            // missed construction
            for (Task orgTask : this.tasks) {
                if (!schedule.contains(orgTask)) {
                    missed.add(orgTask);
                }
            }


            this.scheduled = schedule;
            this.missed = missed;

            this.penalty = 0;
            for (Task missedT : this.missed) {
                penalty += missedT.weight;
            }

            this.canonicalForm = new ArrayList<>(this.scheduled);
            this.canonicalForm.addAll(this.missed);
        }

        public void displayResults() {
            System.out.println("Canonical=" + this.getCanonicalForm());
            System.out.println("Scheduled=" + this.getScheduled());
            System.out.println("Missed=" + this.getMissed());
            System.out.println("Penalty=" + this.getPenalty());
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

        OptimalSchedule optim = new OptimalSchedule(arr);
        optim.displayResults();

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

        OptimalSchedule optim1 = new OptimalSchedule(arr1);
        optim1.displayResults();

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

        OptimalSchedule optim2 = new OptimalSchedule(arr2);
        optim2.displayResults();
   }

}
