import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        int[][] grid = new int[9][9];
        if (args.length == 1) {
            String filename = args[0];
            File f = new File(filename);
            if (f.exists() && !f.isDirectory()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filename));
                    String line;
                    int i = 0;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.trim().split("\\s+");
                        for (int j = 0; j < values.length; j++) {
                            grid[i][j] = Integer.parseInt(values[j]);
                        }
                        i++;
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Saisissez la grille manuellement :");
            for (int i = 0; i < 9; i++) {
                System.out.println("Ligne " + (i + 1) + " (9 chiffres séparés par des espaces) :");
                String line = scanner.nextLine();
                String[] values = line.trim().split("\\s+");

                if (values.length != 9) {
                    System.out.println("Erreur : Nombre incorrect de valeurs dans la ligne.");
                    return;
                }

                for (int j = 0; j < 9; j++) {
                    try {
                        grid[i][j] = Integer.parseInt(values[j]);
                        if (grid[i][j] < 0 || grid[i][j] > 9) {
                            System.out.println("Erreur : Les valeurs doivent être comprises entre 0 et 9.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur : Valeurs non numériques détectées.");
                        return;
                    }
                }
               // scanner.close();
            }
        }

        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.loadGrid(grid);

        System.out.println("Grille initiale :");
        sudokuGrid.display2();

        SudokuSolver solver = new SudokuSolver(sudokuGrid);
        if (solver.solve()) {
            System.out.println("Grille résolue :");
            sudokuGrid.display2();
        } else {
            System.out.println("Impossible de résoudre la grille.");
        }
    }
}