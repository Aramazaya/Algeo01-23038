package primitive;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;


public class InputOutput {
    public static int getValidIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();  // Return the input if it's valid
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka.");
                scanner.next();  // Clear the invalid input
            }
        }
    }
    public static void readInputRegression(String filepath, double[][] matrix, double[] predictors, int n, int m) throws IOException{
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        matrix = new double[n][m];
        predictors = new double[m-1];
        if (scanner.hasNextLine()){
            for (int i = 0; i < n; i++) {
                String[] tokens = scanner.nextLine().split(" ");
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = Double.parseDouble(tokens[j]);
                }
            }
            if (scanner.hasNextLine()){
                String[] predTokens = scanner.nextLine().split(" ");
                for (int i = 0; i < predTokens.length; i++){
                    predictors[i] = Double.parseDouble(predTokens[i]);
                }
            }
        }
    }
    public static void readInputPolinomialInterpolation(String filepath, double[][] matrix, int n, double xEstimate) throws IOException {
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        
        double[] x = new double[1];

        for (int i = 0; i < n; i++) {
            if (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().trim().split("\\s+"); 
                matrix[i][0] = Double.parseDouble(tokens[0]); // x value
                matrix[i][1] = Double.parseDouble(tokens[1]); // y value
            }
        }
        
        if (scanner.hasNextLine()) {
            String tempX = scanner.nextLine();
            x[0] = Double.parseDouble(tempX.trim()); 
        }
        xEstimate = x[0];
        
    }
    
    public static void readInputBicubic(String filepath,double[][] matrix, double[] predictors, int x, int y ) throws IOException{
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        matrix = new double[4][4];
        predictors = new double[2];
        if (scanner.hasNextLine()){
            for (int i = 0; i < 4; i++){
                String[] tokens = scanner.nextLine().split(" ");
                for (int j = 0; j < 4; j++) {
                    matrix[i][j] = Double.parseDouble(tokens[j]);
                }
            }
        }

        if (scanner.hasNextLine()){
            String[] predTokens = scanner.nextLine().split(" ");
            for (int i = 0; i < predTokens.length; i++){
                predictors[i] = Double.parseDouble(predTokens[i]);
            }
        }
        scanner.close();
    }
    public static double[][] readMatrixFile(String filePath) throws FileNotFoundException{
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Menghitung jumlah baris dan kolom dalam file
        int rows = 0;
        int cols = 0;
        if(scanner.hasNextLine()){
            String[] firstLine = scanner.nextLine().split(" ");
            cols = firstLine.length;
            rows ++;
        }

        while (scanner.hasNextLine()){
            scanner.nextLine();
            rows++;
        }
        scanner.close();
        double[][] matrix = new double[rows][cols]; // Inisialisasi matrix dengan ukuran row x col

        // Menginput nilai dalam file ke matrix
        scanner = new Scanner(file);
        int rowIndex = 0;
        while (scanner.hasNextLine()){
            String[]line = scanner.nextLine().split(" ");
            for (int colIndex = 0; colIndex < line.length; colIndex++){
                matrix[rowIndex][colIndex] = Double.parseDouble(line[colIndex]);
            }
            rowIndex++;
        }

        scanner.close();
        return matrix;
    }

    public static void writeMatrixToFile(double[][] matrix, String outputPath) throws IOException {
        // Will overwrite if there's any file with the same name, implement with file path checking
        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                printWriter.print(matrix[i][j] + " "); 
            }
            printWriter.println();
        }

        printWriter.close(); 
    }

    public static void writeArrayToFile(double[] matrix, String outputPath) throws IOException {
        // Will overwrite if there's any file with the same name, implement with file path checking
        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < matrix.length; i++) {
            printWriter.print(matrix[i] + " "); 
        }
        printWriter.close(); 
    }
    
    public static void writeStringToFile(String content, String outputPath) throws IOException {
        // FileWriter to write the string to the specified output path
        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        // Write the content string to the file
        printWriter.print(content);
        
        // Close the PrintWriter to free up resources
        printWriter.close();
    }
    public static void writeDoubleToFile(double content, String outputPath) throws IOException {
        // FileWriter to write the string to the specified output path
        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        // Write the content string to the file
        printWriter.print(content);
        
        // Close the PrintWriter to free up resources
        printWriter.close();
    }
    // Function to check if file path exist
    public static boolean checkFilePath(String outputPath) throws IOException{
        File file = new File(outputPath);
        return file.exists();
    }

    public static void writeMatrixFile(double[][] matrix) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                String filename = scanner.nextLine();
                outputPath = filename;
                 
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeMatrixToFile(matrix, outputPath);
                        System.out.println("File berhasil di simpan pada" + outputPath);
                        break;
                    }
                    else if (overwrite == 'n' || overwrite == 'N') {
                        System.out.println("File tidak disimpan\n");
                        break;
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                        break;
                    }
                }
                else{
                    InputOutput.writeMatrixToFile(matrix, outputPath);
                    System.out.println("File berhasil di simpan pada " + outputPath);
                }

                break;
            }

            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                break;
            }
        }
    }

    public static void writeArrayFile(double[] array) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                String filename = scanner.nextLine();
                outputPath = filename;
                 
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeArrayToFile(array, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                        break;
                    }
                    else if (overwrite == 'n' || overwrite == 'N') {
                        System.out.println("File tidak disimpan\n");
                        break;
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                        break;
                    }
                }
                else{
                    InputOutput.writeArrayToFile(array, outputPath);
                    System.out.println("File berhasil di simpan pada" + outputPath);
                }
                
                break;
            }
            
            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                break;
            }
        }
    }

    public static void writeStringFile(String string) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                String filename = scanner.nextLine();
                outputPath = filename;
                
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeStringToFile(string, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                        break;
                    }
                    else if (overwrite == 'n' || overwrite == 'N') {
                        System.out.println("File tidak disimpan\n");
                        break;
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                        break;
                    }
                }
                else{
                    InputOutput.writeStringToFile(string, outputPath);
                    System.out.println("File berhasil di simpan pada" + outputPath);
                    }
                
                break;
            }
            
            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                break;
            }
        }
    }


    public static void writeDoubleFile(Double x) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                String filename = scanner.nextLine();
                outputPath = filename;
                
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeDoubleToFile(x, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                        break;
                    }
                    else if (overwrite == 'n' || overwrite == 'N') {
                        System.out.println("File tidak disimpan\n");
                        break;
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                        break;
                    }
                }
                else{
                    InputOutput.writeDoubleToFile(x, outputPath);
                    System.out.println("File berhasil di simpan pada" + outputPath);
                    }
                
                break;
            }
            
            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                break;
            }
        }
    }
}
