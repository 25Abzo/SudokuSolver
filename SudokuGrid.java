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
        System.out.println("-------------------");
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0)
                    System.out.print(" ");
                else
                    System.out.print(grid[i][j]);
                if ((j + 1) % 3 == 0)
                    System.out.print("|");
                else
                    System.out.print(" ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0)
                System.out.println("-------------------");
        }
    }

    public void display2() {
        final String TOP_LINE    = "┏━━━┯━━━┯━━━┳━━━┯━━━┯━━━┳━━━┯━━━┯━━━┓";
        final String THICK_MID   = "┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫";
        final String THIN_MID    = "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨";
        final String BOTTOM_LINE = "┗━━━┷━━━┷━━━┻━━━┷━━━┷━━━┻━━━┷━━━┷━━━┛";
        System.out.println(TOP_LINE);
        for (int i = 0; i < 9; i++) {
            StringBuilder rowBuilder = new StringBuilder();
            rowBuilder.append("┃");
            for (int j = 0; j < 9; j++) {
                int val = grid[i][j];
                if (val == 0) {
                    rowBuilder.append("  "); 
                } else {
                    rowBuilder.append(" ").append(val).append(" ");
                }
                if (j == 2 || j == 5 || j == 8) {
                    rowBuilder.append("┃");
                } else {
                    rowBuilder.append("│");
                }
            }
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