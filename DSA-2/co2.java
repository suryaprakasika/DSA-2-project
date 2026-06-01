class FenwickTree {

    int[] bit;
    int n;

    // Constructor
    FenwickTree(int size) {
        n = size;
        bit = new int[n + 1];
    }

    // Point Update
    void update(int index, int value) {
        while (index <= n) {
            bit[index] += value;
            index += index & (-index);
        }
    }

    // Prefix Sum Query
    int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += bit[index];
            index -= index & (-index);
        }

        return sum;
    }

    // Range Sum Query
    int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    // Display BIT Array
    void displayBIT() {
        System.out.println("\nBIT Array:");
        for (int i = 1; i <= n; i++) {
            System.out.print(bit[i] + " ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {

        // Spend array (1 to 15)
        int[] spend = {
                0, 1200, 800, 0, 2400,
                1500, 600, 0, 0, 3500,
                0, 1100, 950, 700, 0
        };

        FenwickTree ft = new FenwickTree(15);

        // Build Fenwick Tree
        for (int i = 1; i <= 15; i++) {
            ft.update(i, spend[i]);
        }

        ft.displayBIT();

        // Query Jan 5 to Jan 12
        int total = ft.rangeSum(5, 12);

        System.out.println("\nTotal Spend from Jan 5 to Jan 12 = ₹" + total);
    }
}