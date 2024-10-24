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
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
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
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
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
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
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
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
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
