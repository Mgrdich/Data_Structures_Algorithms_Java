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
        return printNthMoveRec(n, from_rod, to_rod, aux_rod, goal);
    }

    static String printNthMoveRec(int n, String from_rod, String to_rod, String aux_rod, int goal) {
        double num = Math.pow(2, n) - 1;

        int halvedValue = (int) (num / 2);

        if (n == 1) {
            return from_rod + to_rod;
        }

        if (halvedValue >= goal) {
            // left recursion
            return printNthMoveRec(n - 1, from_rod, aux_rod, to_rod, goal);
        }

        if (goal - 1 == halvedValue) {
            return from_rod + to_rod;
        }

        return printNthMoveRec(n - 1, aux_rod, to_rod, from_rod, goal - (halvedValue + 1));
    }

    // Driver code
    public static void main(String[] args) {


        // A, B and C are names of rods


//        show(1, '0', '2', '1');
//        System.out.println();
//        show(2, '0', '2', '1');
//        System.out.println();
//        show(3, '0', '2', '1');
        System.out.println();
        show(5, "1", "0", "2");
        System.out.println();
        System.out.print(printNthMove(5, "1", "0", "2", 1));
        System.out.print(printNthMove(5, "1", "0", "2", 2));
        System.out.print(printNthMove(5, "1", "0", "2", 3));
        System.out.print(printNthMove(5, "1", "0", "2", 4));
        System.out.print(printNthMove(5, "1", "0", "2", 5));
        System.out.print(printNthMove(5, "1", "0", "2", 6));
        System.out.print(printNthMove(5, "1", "0", "2", 7));
        System.out.print(printNthMove(5, "1", "0", "2", 8));
        System.out.print(printNthMove(5, "1", "0", "2", 9));
        System.out.print(printNthMove(5, "1", "0", "2", 10));
        System.out.print(printNthMove(5, "1", "0", "2", 11));
        System.out.print(printNthMove(5, "1", "0", "2", 12));
        System.out.print(printNthMove(5, "1", "0", "2", 13));
        System.out.print(printNthMove(5, "1", "0", "2", 14));
        System.out.print(printNthMove(5, "1", "0", "2", 15));
        System.out.print(printNthMove(5, "1", "0", "2", 16));
        System.out.print(printNthMove(5, "1", "0", "2", 17));
        System.out.print(printNthMove(5, "1", "0", "2", 18));
        System.out.print(printNthMove(5, "1", "0", "2", 19));
        System.out.print(printNthMove(5, "1", "0", "2", 20));
        System.out.print(printNthMove(5, "1", "0", "2", 21));
        System.out.print(printNthMove(5, "1", "0", "2", 22));
        System.out.print(printNthMove(5, "1", "0", "2", 23));
        System.out.println();

//        show(4,'1','2','0');
//        System.out.println();
//        show(4,'2','0','1');

//        show(5, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(6, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(7, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(8, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(9, '0', '2', '1');
//        System.out.println();
//        show(8, '0', '2', '1');
        System.out.println();
    }
}
