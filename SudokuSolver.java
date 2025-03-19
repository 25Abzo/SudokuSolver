public class SudokuSolver {
    private SudokuGrid grid;

    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        return grid.solve();
    }
}