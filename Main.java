import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        int[][] grid = new int[9][9];
        //Recuperation de la grille initiale a partir du tableau de parametre args
        if (args.length == 1) {
            String filename = args[0];
            File f = new File(filename);
            //Verification de l'existence et du type du fichier recupere (fichier simple ou repertoire)
            if (f.exists() && !f.isDirectory()) {
                try {
                    //Crée un BufferedReader pour lire le contenu du fichier spécifié par 'filename'.
                    BufferedReader br = new BufferedReader(new FileReader(filename));
                    String line;
                    int i = 0;
                    //Boucle permettant de recuperer le contenu de "Grille.txt" ligne par ligne
                    while ((line = br.readLine()) != null) {
                        String[] values = line.trim().split("\\s+");
                        for (int j = 0; j < values.length; j++) {
                            grid[i][j] = Integer.parseInt(values[j]);
                        }
                        i++;
                    }
                    br.close();
                } catch (Exception e) {
                    //Capture de l'exception et affichage du message d'erreur
                    e.printStackTrace();
                }
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Saisissez la grille manuellement :");
            //Saisie des valeurs de la grille initiale ligne par ligne avec comme delimitant le caractere "espace"
            for (int i = 0; i < 9; i++) {
                System.out.println("Ligne " + (i + 1) + " (9 chiffres séparés par des espaces) :");
                String line = scanner.nextLine();
                String[] values = line.trim().split("\\s+");

                //Controle du nombres de valeurs saisies par lignes
                if (values.length != 9) {
                    System.out.println("Erreur : Nombre incorrect de valeurs dans la ligne.");
                    scanner.close();
                    return;
                }

                for (int j = 0; j < 9; j++) {
                    try {
                        grid[i][j] = Integer.parseInt(values[j]);
                        //Controle des valeurs saisies (valeurs entieres comprises dans l'intervalle [0,9])
                        if (grid[i][j] < 0 || grid[i][j] > 9) {
                            System.out.println("Erreur : Les valeurs doivent être comprises entre 0 et 9.");
                            scanner.close();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur : Valeurs non numériques détectées.");
                        scanner.close();
                        return;
                    }
                }
            }
            scanner.close();
        }

        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.loadGrid(grid);

        System.out.println("Grille initiale :");
        sudokuGrid.display();

        SudokuSolver solver = new SudokuSolver(sudokuGrid);
        if (solver.solve()) {
            System.out.println("Grille résolue :");
            sudokuGrid.display1();
        } else {
            System.out.println("Impossible de résoudre la grille.");
        }
    }
}