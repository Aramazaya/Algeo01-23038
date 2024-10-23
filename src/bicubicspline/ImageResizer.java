package bicubicspline;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import primitive.BasicFunction;
import primitive.Inverse;

import java.io.IOException;
public class ImageResizer {
    public static void main(String[] args) throws IOException {
        // Baca gambar
        BufferedImage inputImage = ImageIO.read(new File("tes.jpeg"));
        
        // Skala perbesaran/resizing (contoh: width 1.5x dan height 2x)
        double scaleX = 1.5;
        double scaleY = 2;

        // Dapatkan ukuran gambar asli
        int originalWidth = inputImage.getWidth();
        int originalHeight = inputImage.getHeight();

        // Tentukan ukuran baru gambar
        int newWidth = (int) (originalWidth * scaleX);
        int newHeight = (int) (originalHeight * scaleY);

        // Buat BufferedImage baru untuk gambar hasil resizing
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, inputImage.getType());

        // Lakukan interpolasi bicubic untuk setiap piksel
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Hitung koordinat pada gambar asli
                double originalX = x / scaleX;
                double originalY = y / scaleY;

                // Interpolasi RGB secara terpisah
                int newPixelColor = bicubicInterpolation(originalX, originalY, inputImage);
                
                // Set nilai piksel baru di gambar hasil resize
                outputImage.setRGB(x, y, newPixelColor);
            }
        }

        // Simpan gambar hasil
        ImageIO.write(outputImage, "png", new File("resized.png"));
    }

    // Fungsi untuk melakukan interpolasi bicubic dan mengembalikan nilai RGB baru
    public static int bicubicInterpolation(double x, double y, BufferedImage image) {
        // Ambil 4x4 grid piksel terdekat dari gambar asli berdasarkan x dan y
        int[][] pixelGridR = new int[4][4];
        int[][] pixelGridG = new int[4][4];
        int[][] pixelGridB = new int[4][4];
        
        int baseX = (int) x;
        int baseY = (int) y;

        // Ambil warna dari 16 piksel terdekat
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int imgX = Math.min(Math.max(baseX + i - 1, 0), image.getWidth() - 1);
                int imgY = Math.min(Math.max(baseY + j - 1, 0), image.getHeight() - 1);
                
                // Ambil nilai RGB dari piksel
                int rgb = image.getRGB(imgX, imgY);
                pixelGridR[i][j] = (rgb >> 16) & 0xFF; // Red
                pixelGridG[i][j] = (rgb >> 8) & 0xFF;  // Green
                pixelGridB[i][j] = rgb & 0xFF;         // Blue
            }
        }

        // Lakukan interpolasi bicubic untuk setiap komponen warna
        int r = (int) Math.round(performBicubicInterpolation(x - baseX, y - baseY, pixelGridR));
        int g = (int) Math.round(performBicubicInterpolation(x - baseX, y - baseY, pixelGridG));
        int b = (int) Math.round(performBicubicInterpolation(x - baseX, y - baseY, pixelGridB));

        // Pastikan nilai RGB tetap dalam rentang [0, 255]
        r = Math.min(Math.max(r, 0), 255);
        g = Math.min(Math.max(g, 0), 255);
        b = Math.min(Math.max(b, 0), 255);

        // Gabungkan kembali nilai RGB menjadi satu warna
        return (r << 16) | (g << 8) | b;
    }

    // Fungsi performBicubicInterpolation tetap sama seperti sebelumnya
    public static double performBicubicInterpolation(double x, double y, int[][] pixelGrid) {
        // Matriks input dari 4x4 piksel terdekat
        double[][] input = new double[4][4];
        
        // Mengisi matriks input dengan piksel dari grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                input[i][j] = pixelGrid[i][j];
            }
        }
    
        // Panggil fungsi yang mengubah matriks input menjadi bentuk linear (1D)
        matrixSingular(input);
    
        // Gunakan koefisien hasil inverse untuk menginterpolasi pada titik x, y
        double sum = 0;
        int index = 0;
        
        for (int l = 0; l < 16; l++) {
            int k = l / 4;
            int j = l % 4;
            double xTerm = Math.pow(x, j);
            double yTerm = Math.pow(y, k);
            sum += matriksCoefficient[index][0] * xTerm * yTerm;
            index++;
        }
    
        return sum;
    }

    static double[][] matriksInput = new double[16][1];
    static double[][] matriksInverse = new double[16][16];
    static double[][] matriksCoefficient = new double[16][1];
    
    public static void matrixSingular(double[][] input) {
        int index = 0;
        int i = 0, j = 0;
        while (i < 4) {
            while (j < 4) {
                matriksInput[index][0] = input[i][j];
                index++;
                j++;
            }
            j = 0;
            i++;
        }
        int count = 0;
        while (count < 16) {
            konstanta(count);
            count++;
        }
        matrixCoefficient();
    }

    public static void matrixCoefficient(){
        matriksInverse = Inverse.get_Inverse_Matriks_fromIdentity(matriksInverse);
        matriksCoefficient = BasicFunction.multiplication(matriksInverse, matriksInput);
    }
    
    public static void konstanta(int i) {
        int loop = i % 4;
        if (loop == 0) derivative(0, 0, i);
        else if (loop == 1) derivative(1, 0, i);
        else if (loop == 2) derivative( 0, 1, i);
        else derivative(1, 1, i);
    }

    public static void derivative(int x, int y, int i){
        double value;
        int idx = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                if (i < 4) {
                    value = (Math.pow(x,l) * Math.pow(y,k));
                } else if (i < 8) {
                    value = l * (Math.pow(x,l-1) * Math.pow(y,k));
                } else if (i < 12) {
                    value = k * (Math.pow(x,l) * Math.pow(y,k-1));
                } else {
                    value = k*l*(Math.pow(x,l-1) * Math.pow(y,k-1));
                }
                matriksInverse[i][idx] = (int) value;
                idx++;
            }
        }
    }
    
    public static String bicubiInterpolation(double x, double y) {
        StringBuilder sout = new StringBuilder();
        double sum = 0;
        int index = 0; 
            for (int l = 0; l < 16; l++) {
            int k = l / 4; 
            int j = l % 4; 
            double xTerm = Math.pow(x, j);
            double yTerm = Math.pow(y, k);
            sum += matriksCoefficient[index][0] * xTerm * yTerm;
            index++;
        }
        sout.append(String.format("f(%.2f,%.2f) = %f", x, y, sum));
        return sout.toString();
    }
}
