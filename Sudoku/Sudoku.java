package Sudoku;

public class Sudoku implements SudokuSolver {

    private int[][] SudokuBoard;

    public Sudoku() {
        this.SudokuBoard = new int[9][9];
    }

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
        
        return recursiveSolve();
    }

    private boolean recursiveSolve() {
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if(SudokuBoard[row][col] == 0) {
                    for(int number = 1; number < 10; number++) {
                        if(checkRules(row, col, number)) {
                            SudokuBoard[row][col] = number;

                            if(recursiveSolve()) {
                                return true;
                            } else {
                                SudokuBoard[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRules(int x, int y, int number) {
        for(int i = 0; i < 9; i++) {
            if((y != i && SudokuBoard[x][i] == number) || (x != i && SudokuBoard[i][y] == number)) {
                return false;
            }
        }

        int row = x - x % 3;
        int col = y - y % 3;

        // This function is not working
        for(int i = row; i < row + 3; i++) {
            for(int j = col; j < col + 3; j++) {
                if((SudokuBoard[i][j] == number) && ((i != x) && (j != y))) {
                    return false;
                }
            }
        }

        return true;
    }
    
    @Override
    public void setCell(int row, int col, int val) throws IllegalArgumentException {
        SudokuBoard[row][col] = val;
    }

    @Override
    public int getCell(int row, int col) throws IllegalArgumentException {
        return SudokuBoard[row][col];
    }
    
}