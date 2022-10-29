package Problems;

public class HanoiTower {
    static void show(int n, char from_rod, char to_rod, char aux_rod) {
        if (n == 0) {
            return;
        }
        show(n - 1, from_rod, aux_rod, to_rod);
        System.out.print(from_rod);
        System.out.print(to_rod);
        System.out.print('-');
        show(n - 1, aux_rod, to_rod, from_rod);
    }

    // Driver code
    public static void main(String[] args) {


        // A, B and C are names of rods


//        towerOfHanoi(1, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(2, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(3, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(4, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(5, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(6, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(7, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(8, '0', '2', '1');
//        System.out.println();
//        towerOfHanoi(9, '0', '2', '1');
//        System.out.println();
        show(8, '0', '2', '1');
        System.out.println();
    }
}
