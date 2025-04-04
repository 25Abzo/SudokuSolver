public class SudokuGrid {
    private int[][] grid;

    public SudokuGrid() {
        this.grid = new int[9][9];
    }

    public void loadGrid(int[][] grid) {
        this.grid = grid;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num)
                return false;
        }
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num)
                return false;
        }
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (grid[i][j] == num)
                    return false;
            }
        }
        return true;
    }

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row, col, num)) {
                            grid[row][col] = num;
                            if (solve())
                                return true;
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        // Lignes de séparation
        final String TOP_LINE    = "┌─────────┬─────────┬─────────┐";
        final String MID_LINE   = "├─────────┼─────────┼─────────┤";
        final String BOTTOM_LINE = "└─────────┴─────────┴─────────┘";
        
        System.out.println(TOP_LINE);
        for (int i = 0; i < 9; i++) {
            System.out.print("│");
            for (int j = 0; j < 9; j++) {
                int val = grid[i][j];
                // Affiche un point pour les cases vides (0), sinon le chiffre
                System.out.print(" " + (val == 0 ? " " : val) + " ");
                
                // Ajoute une bordure verticale après chaque bloc de 3 colonnes
                if (j == 2 || j == 5 || j == 8) {
                    System.out.print("│");
                }
            }
            System.out.println();
            
            // Ajoute une ligne de séparation horizontale après chaque bloc de 3 lignes
            if (i == 2 || i == 5) {
                System.out.println(MID_LINE);
            }
        }
        System.out.println(BOTTOM_LINE);
    }
     

    public void display1() {
        //Definition des lignes de separation
        final String TOP_LINE    = "┏━━━┯━━━┯━━━┳━━━┯━━━┯━━━┳━━━┯━━━┯━━━┓"; 
        final String THICK_MID   = "┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫"; 
        final String THIN_MID    = "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨"; 
        final String BOTTOM_LINE = "┗━━━┷━━━┷━━━┻━━━┷━━━┷━━━┻━━━┷━━━┷━━━┛"; 
        System.out.println(TOP_LINE);
        //Boucle pour parcourir les lignes du tableau
        for (int i = 0; i < 9; i++) {
            //Utilisation du StringBuilder pour construire chaque ligne du tableau
            StringBuilder rowBuilder = new StringBuilder();
            rowBuilder.append("┃");
            //Boucle pour parcourir les colonnes de chaque ligne
            for (int j = 0; j < 9; j++) {
                int val = grid[i][j];
                if (val == 0) {
                    rowBuilder.append("  "); 
                } else {
                    rowBuilder.append(" ").append(val).append(" ");
                }
                //Apres chaque 3 colonnes (indices 2, 5, 8) on ajoute une bordure verticale ┃
                if (j == 2 || j == 5 || j == 8) {
                    rowBuilder.append("┃");
                } else {
                    //Sinon on ajoute une bordure fine │
                    rowBuilder.append("│");
                }
            }
            //Affiche la ligne construite
            System.out.println(rowBuilder.toString());
            if      (i == 2 || i == 5) System.out.println(THICK_MID);
            else if (i == 8)           System.out.println(BOTTOM_LINE);
            else                       System.out.println(THIN_MID);
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}