package primitive;
import java.util.Scanner;
public class GaussElimination {  
    public static String driverGaussElimination(){
        System.out.println("Metode eliminasi Gauss");
        double[][] matrix = new double[0][0];
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/var.txt): ");
                String filename = scanner.nextLine();
                System.out.println("filename: " + filename);
                matrix = InputOutput.readMatrixFile(filename);
                break;
            } else if (choice == 'N' || choice == 'n'){
                matrix = BasicFunction.inputMatrix();
                break;
            } else if (choice == 'C' || choice == 'c'){
                return "0.267";
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
                System.out.println(e);
            }
        }
        String hasil = gaussElimination(matrix);
        if (hasil == null){return "0.267";}
        else {return hasil;}
    }
    public static String gaussElimination(double[][] matrix) {
        BasicFunction.printMatrix(matrix);
        int n = matrix.length;
        int m = matrix[0].length;
        boolean hasFreeVariable = false;
        int idxPivot = 0;
        
        for (int i = 0; i < n; i++) {
            while (idxPivot < m - 1 && Math.abs(matrix[i][idxPivot]) < 1e-9) {
                if (!switchRow(matrix, i)) {
                    hasFreeVariable = true; // Jika terdapat solusi banyak maka penukaran pivot akan dilewati dan ditandai solusi parametrik
                    idxPivot++;
                    if (idxPivot == m - 2) break;
                }
            }
            
            if (idxPivot == m - 2) break; // Mencegah akses indekx kolumn di luar batasan 
            
            // Eliminasi Gauss
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrix[j][idxPivot]) > 1e-9) {
                    double factor = matrix[j][idxPivot] / matrix[i][idxPivot];
                    for (int k = idxPivot; k < m; k++) {
                        matrix[j][k] -= factor * matrix[i][k];
                    } 
                }

            }
            idxPivot ++;
        }
        System.out.println("\nMatrix akhir:");
        BasicFunction.printMatrix(matrix);
        // Cek apakah ada baris nol dan augmented kolom juga nol (solusi banyak)
        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i]) && Math.abs(matrix[i][m-1]) < 1e-9) {
                hasFreeVariable = true;
            }
            // Cek apakah terdapat baris nol dan nilai di kolom augmented tidak nol (tidak ada solusi)
            if (isRowZero(matrix[i]) && Math.abs(matrix[i][m-1]) > 1e-9) {
                System.err.println("Tidak ditemukan solusi unik");
                return null;
            }
        }
        if (hasFreeVariable){
            System.err.println("Ditemukan solusi parametric");
            return parametricBackSubstitution(matrix);
        }        
        else{
            return printNormalBackSubstitution(normalBackSubstitution(matrix));
        }
        // Lakukan back substitution dan kembalikan array hasil
    }
    
    
    public static String readInput() throws Exception {
        StringBuilder inputBuilder = new StringBuilder();
        int character;
        while ((character = System.in.read()) != '\n') {
            inputBuilder.append((char) character);
        }
        return inputBuilder.toString();
    }
    
    private static boolean isRowZero(double[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Mengubah baris jika elemen diagonalnya nol
    public static boolean switchRow(double[][] matrix, int i) {
        for (int row = i + 1; row < matrix.length; row++) {
            if (matrix[row][i] != 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[row][j];
                    matrix[row][j] = temp;
                }
                return true; 
            }
        }
        return false;
    }

    public static double[] normalBackSubstitution(double[][] matrix) {
        // Konidisi tidak ada solusi dan solusi banyak sudah dihandle di fungsi utama
        int n = matrix.length; //Jumlah baris
        int m = matrix[0].length; //Jumlah kolom
        double[] x = new double[n]; // Array solusi
        
        int idxPivot = m-2; // Index dimulai dari
        for (int i = idxPivot; i >= 0; i--) {          
            x[i] = matrix[i][m-1]; // Array constant
            for (int j = i + 1; j < m-1; j++) {
                x[i] -= matrix[i][j] * x[j]; // s
            }

            x[i] /= matrix[i][i];
        }
        return x;
    }

    public static String printNormalBackSubstitution(double[] resultArray){
        StringBuilder result = new StringBuilder(); // Output untuk solusi
        int m = resultArray.length;
        for (int i = m-1; i >= 0; i--){
            result.append(String.format("x%d = %.2f\n",i + 1, resultArray[i]));

        }
        return result.toString();

    }
    

    // Substitusi balik untuk mendapatkan solusi
    public static String parametricBackSubstitution(double[][] matrix) {
        int n = matrix.length;         // Jumlah baris
        int m = matrix[0].length - 1;  // Jumlah variabel 
        StringBuilder[] expressions = new StringBuilder[m]; // Menampun ekspresi parametrik
        boolean[] isFreeVariable = new boolean[m]; // Menampung free variable
    
        // Inisialisasi free variable
        for (int i = 0; i < m; i++) {
            isFreeVariable[i] = true;
            expressions[i] = new StringBuilder(String.format("x%d", i + 1));
        }
    
        // Mulai back substitution
        for (int i = n - 1; i >= 0; i--) {
            int pivotCol = -1;
    
            // Mencari pivot (element bukan nol) pada baris 
            for (int j = 0; j < m; j++) {
                if (Math.abs(matrix[i][j]) > 1e-9) {
                    pivotCol = j;
                    break;
                }
            }
    
            // Kalau tidak terdapat pivot maka baris akan diskip
            if (pivotCol == -1) {
                continue;
            }
    
            // Kalau nilai pivot nol maka variable tersebut adalah parametrik
            if (Math.abs(matrix[i][pivotCol]) < 1e-9) {
                isFreeVariable[pivotCol] = true;
                continue;
            }
    
            // Membuat temporary ekspresi untuk menampung solusi 
            StringBuilder expression = new StringBuilder();
            double constantTerm = matrix[i][m];
    
            boolean hasParametric = false;
            
            // Mengoperasikan element setelah pivot 
            for (int j = pivotCol + 1; j < m; j++) {
                if (Math.abs(matrix[i][j]) > 1e-9) { // Hanya mengecek element yang lebih dari nol
                    hasParametric = true; // 
                    if (!isFreeVariable[j]) { // Jika bukan variabel bebas maka koefisien nya dihitung dengan value yang sudah ada sebelumnya
                        double coefficient = -matrix[i][j] / matrix[i][pivotCol];
                        String subExpression = expressions[j].toString();
                        if (coefficient != 1.0) {
                            subExpression = String.format("%.2f * (%s)", coefficient, subExpression);
                        }
                        expression.append(" + ").append(subExpression);
                    } else { // Jika variabel bebas langsung ditambahkan 
                        double coefficient = -matrix[i][j] / matrix[i][pivotCol];
                        expression.append(String.format(" + %.2fx%d", coefficient, j + 1));
                    }
                }
            }
    
            // Membagi constant dengan nilai pivot
            constantTerm /= matrix[i][pivotCol];
    
            if (!hasParametric) {
                // Jika tidak terdapat parametric maka nilai akan langsung dimasukkan
                expressions[pivotCol] = new StringBuilder(String.format("%.2f", constantTerm));
            } else {
                // Jika terdapat parametrik maka constant akan di insert 
                if (constantTerm != 0.0) {
                    expression.insert(0, String.format("%.2f", constantTerm));
                }
                expressions[pivotCol] = expression;
            }
    
            // Jika sudah dihandle maka bukan lagi free variable
            isFreeVariable[pivotCol] = false;
        }
    
        // Membangun hasil akhir
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (isFreeVariable[i]) {
                result.append(String.format("x%d = x%d\n", i + 1, i + 1)); 
            } else {
                result.append(String.format("x%d = %s\n", i + 1, expressions[i].toString().trim())); // Menggabungkan nilai jika bukan merupakan free variabel
            }
        }
    
        return result.toString();
    }
}