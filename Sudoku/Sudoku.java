package Sudoku;

public class Sudoku implements SudokuSolver {

    private int[][] SudokuBoard;

    public Sudoku() {
        this.SudokuBoard = new int[9][9];
    }

    @Override
    public boolean solve() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        for (int i = 1; i <= 9; i++) {
            for (int k = 1; k <= 9; k++) {
                setCell(i, k, 0);
            }
        }
    }

    public void randomize() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setCell(int row, int col, int val) throws IllegalArgumentException {

    }

    @Override
    public int getCell(int row, int col) throws IllegalArgumentException {
        return 0;
    }
    
}