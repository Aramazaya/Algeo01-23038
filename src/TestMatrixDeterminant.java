public class TestMatrixDeterminant {
    public static void main(String[] args) {
        // Test Case 1: 2x2 matrix
        double[][] matrix1 = {
            {1, 2},
            {3, 4}
        };
        double expectedDet1 = -2;  // The expected determinant
        testDeterminant(matrix1, expectedDet1);

        // Test Case 2: 3x3 matrix
        double[][] matrix2 = {
            {6, 1, 1},
            {4, -2, 5},
            {2, 8, 7}
        };
        double expectedDet2 = -306;
        testDeterminant(matrix2, expectedDet2);

        // Test Case 3: Singular matrix (determinant should be 0)
        double[][] matrix3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double expectedDet3 = 0;
        testDeterminant(matrix3, expectedDet3);

        // Test Case 4: Identity matrix (determinant should be 1)
        double[][] matrix4 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        double expectedDet4 = 1;
        testDeterminant(matrix4, expectedDet4);
    }

    // Helper function to test and print result
    private static void testDeterminant(double[][] matrix, double expectedDet) {
        double calculatedDet = Determinant.rowReductionDeterminant(matrix);
        System.out.println("Calculated Determinant: " + calculatedDet);
        System.out.println("Expected Determinant: " + expectedDet);
        if (calculatedDet == expectedDet) {
            System.out.println("Test Passed!\n");
        } else {
            System.out.println("Test Failed.\n");
        }
    }
}
