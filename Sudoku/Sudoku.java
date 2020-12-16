package Sudoku;

public class Sudoku implements SudokuSolver {

    private int[][] SudokuBoard;

    /**
     * Creates an empty Sudoku
     */
    public Sudoku() {
        this.SudokuBoard = new int[9][9];
    }

    /**
     * Creates a Sudoku with the values of sudokuBoard
     * @param sudokuBoard values of the sudokuBoard
     */
    public Sudoku(int[][] sudokuBoard) {
        this.SudokuBoard = sudokuBoard;
    }
    
    /**
     * Tries to solve the current sudoku.
     * @return true if there is one or more solutions, false if no solution could be found.
     */
    @Override
    public boolean solve() {
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if(SudokuBoard[row][col] != 0) {
                    if(!checkRules(row, col, SudokuBoard[row][col])) {
                        return false;
                    }
                }
            }
        }
        return recursiveSolve(0, 0);
    }

    /**
     * Tries to solve the current sudoku.
     * @return true if there is one or more solutions, false if no solution could be found.
     */
    private boolean recursiveSolve(int row, int col) {
        if (col == 9) {
            col = 0;
            row++;
        }

        if (row == 9) {
            return true;
        }

        if(SudokuBoard[row][col] != 0) {
            return recursiveSolve(row, col+1);
        }

        for(int i = 1; i <= 9; i++) {
            if(checkRules(row, col, i)) {
                SudokuBoard[row][col] = i;

                if(recursiveSolve(row, col+1)) {
                    return true;
                }
            }
            SudokuBoard[row][col] = 0;
        }
        return false;
    }

    /**
     * Check if inserting number on position x, y would be allowed according to the rules
     * @param x the x value of the position to check
     * @param y the y value of the position to check
     * @param number the number we are trying to insert
     * @return true if the number can be placed at x, y, otherwise returns false
     */
    private boolean checkRules(int x, int y, int number) {
        for(int i = 0; i < 9; i++) {
            if((y != i && SudokuBoard[x][i] == number) || (x != i && SudokuBoard[i][y] == number)) {
                return false;
            }
        }

        int row = x - x % 3;
        int col = y - y % 3;

        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if((SudokuBoard[i][j] == number) && ((i != x) && (j != y))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * @param row must be between 0-8 since the sudokuboard is 9x9
     * @param col must be between 0-8 since the sudokuboard is 9x9
     * @param val must be between 0-8 (following the rules of sudoku) or 0 if cell should be marked as unsolved.
     * @throws IllegalArgumentException if any of the parameters are out of range
     *         or the if val can't be placed at the current cell.
     */
    @Override
    public void setCell(int row, int col, int val) throws IllegalArgumentException {
        if(row < 0 || row > 8 || col < 0 || col > 8 || val < 0 || val > 9) {
            throw new IllegalArgumentException();
        }
        SudokuBoard[row][col] = val;
    }
    
    /**
     * @param row must be between 0-8 since the sudokuboard is 9x9
     * @param col must be between 0-8 since the sudokuboard is 9x9
     * @throws IllegalArgumentException if any of the parameters are out of range
     */
    @Override
    public int getCell(int row, int col) throws IllegalArgumentException {
        if(row < 0 || row > 8 || col < 0 || col > 8) {
            throw new IllegalArgumentException();
        }
        return SudokuBoard[row][col];
    }
    
}