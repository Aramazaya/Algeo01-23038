public class CramerSolverTest {
    public static void main(String[] args) {
        testCramerSolver();
    }

    public static void testCramerSolver() {
        // Test case 1: Unique solution
        double[][] matrix1 = {
            {2, 1, -1, 8},
            {-3, -1, 2, -11},
            {-2, 1, 2, -3}
        };

        System.out.println("Test Case 1: Unique Solution");
        double[] result1 = Cramer.CramerSolver(matrix1);
        printResult(result1); // Expected output: [2.0, 3.0, -1.0]

        // Test case 2: No unique solution (determinant is zero)
        double[][] matrix2 = {
            {1, 2, 3, 4},
            {2, 4, 6, 8},
            {3, 6, 9, 12}
        };

        System.out.println("Test Case 2: No Unique Solution");
        double[] result2 = Cramer.CramerSolver(matrix2);
        printResult(result2); // Expected output: "Matriks tersebut tidak memiliki solusi unik"

        // Test case 3: Unique solution (different coefficients)
        double[][] matrix3 = {
            {1, 2, 1, 5},
            {2, 3, 1, 8},
            {1, 1, 1, 4}
        };

        System.out.println("Test Case 3: Unique Solution (Different Coefficients)");
        double[] result3 = Cramer.CramerSolver(matrix3);
        printResult(result3); // Expected output: [2.0, 1.0, 1.0]

    }

    // Helper method to print the result
    private static void printResult(double[] result) {
        if (result == null) {
            System.out.println("Matriks tersebut tidak memiliki solusi unik");
        } else {
            System.out.print("Solutions: ");
            for (double v : result) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
