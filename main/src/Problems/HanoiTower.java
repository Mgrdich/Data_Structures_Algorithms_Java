package Problems;

public class HanoiTower {
    static void show(int n, String from_rod, String to_rod, String aux_rod) {
        if (n == 0) {
            return;
        }
        show(n - 1, from_rod, aux_rod, to_rod);
        System.out.print(from_rod);
        System.out.print(to_rod);
//        System.out.print('-');
        show(n - 1, aux_rod, to_rod, from_rod);
    }

    static String printNthMove(int n, String from_rod, String to_rod, String aux_rod, int goal) {
        double num = Math.pow(2, n) - 1;

        int halvedValue = (int) (num / 2);

        if (n == 1) {
            return from_rod + to_rod;
        }

        if (halvedValue >= goal) {
            // left recursion
            return printNthMove(n - 1, from_rod, aux_rod, to_rod, goal);
        }

        if (goal - 1 == halvedValue) {
            return from_rod + to_rod;
        }

        return printNthMove(n - 1, aux_rod, to_rod, from_rod, goal - (halvedValue + 1));
    }
}
