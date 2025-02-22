package matrixmult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class App {
    public static int getInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int[][] readIntoMatrix(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            // Find width of our matrix, then reset reader to beginning of file again
            br.mark(64);
            int matrixLength = br.readLine().split("\\s+").length;
            br.reset();

            // System.out.println("matrixLength: " + matrixLength);
            int[][] newMatrix = new int[matrixLength][matrixLength];
            
            for (int i = 0; i < matrixLength; i++) {
                // get next line
                String[] row = br.readLine().split("\\s+");

                // Fill row j with this line's values
                for (int j = 0; j < matrixLength; j++) {
                    // write lines to our new matrix
                    newMatrix[j][i] = getInt(row[j]);
                }
            }
            br.close();
            return newMatrix;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return new int[1][1];
    }

    public static int[][] createMatrix(int length) {
        int[][] newMatrix = new int[length][length];

        Random rand = new Random();

        for (int j = 0; j < length; j++) {
            for (int i = 0; i < length; i++) {
                newMatrix[i][j] = rand.nextInt(10);
            }
        }

        return newMatrix;
    }

    public static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        try {

            int[][] result = new int[m1.length][m1.length];
            
            for (int j = 0; j < result.length; j++) {
                for (int i = 0; i < result[0].length; i++) {
                    result[i][j] = m1[i][j] * m2[i][j];
                }
            }
            
            return result;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error!! You probably didn't give a proper file");
            return new int[1][1];
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[0].length; i++) {
                System.out.print(matrix[i][j] + "\t");

            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] m1 = new int[0][];
        int[][] m2 = new int[0][];
        int[][] m3; // result
        switch (args.length) {
            // Assume int input
            case 1:
                int length = getInt(args[0]);

                m1 = createMatrix(length);
                m2 = createMatrix(length);

            break;
            // Assume filename input
            case 2:
                String m1Path = args[0];
                String m2Path = args[1];
                
                m1 = readIntoMatrix(m1Path);
                m2 = readIntoMatrix(m2Path);
            break;
            // Input is something we aren't expecting
            default:
                System.out.println("You did not give proper command-line input.");
            break;
        }

        if (m1.length > 0) {
            m3 = multiplyMatrix(m1, m2);
            System.out.println("\n");
            printMatrix(m3);
        }
    }
}
