package Sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuTester {
    private SudokuSolver sudokuBoard;
    private int[][] sudokuArray;
    

    @BeforeEach
    public void setUp() throws Exception {
        sudokuArray = new int[9][9];
        sudokuBoard = new Sudoku(sudokuArray);
    }

    @AfterEach
    public void tearDown() throws Exception {
        sudokuBoard = null;
        sudokuArray = null;
    }

    @Test
    public final void testUnsolvable() {
        sudokuArray = new int[][] {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,3,0,0},
			{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3} };
            
        sudokuBoard = new Sudoku(sudokuArray);
        assertFalse(sudokuBoard.solve(), "Solve was not false");

        sudokuArray = new int[][] {
			{1,2,3,0,0,0,0,0,0},
			{4,5,6,0,0,0,0,0,0},
			{0,0,0,7,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0} };
            
        sudokuBoard = new Sudoku(sudokuArray);
        assertFalse(sudokuBoard.solve(), "Solve was not false");

    }

    @Test
    public final void testEmptySudoku() {
        sudokuArray = new int[][] {
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0} };
            
        sudokuBoard = new Sudoku(sudokuArray);
        assertTrue(sudokuBoard.solve(), "Solve was not true");
    }

    @Test
    public void testSudoku(){
        sudokuArray = new int[][] {
			{0,0,8,0,0,9,0,6,2},
			{0,0,0,0,0,0,0,0,5},
			{1,0,2,5,0,0,0,0,0},
			{0,0,0,2,1,0,0,9,0},
			{0,5,0,0,0,0,6,0,0},
			{6,0,0,0,0,0,0,2,8},
			{4,1,0,6,0,8,0,0,0},
			{8,6,0,0,3,0,1,0,0},
            {0,0,0,0,0,0,4,0,0} };

        assertTrue(sudokuBoard.solve(), "Solve was not true");
    }
}
