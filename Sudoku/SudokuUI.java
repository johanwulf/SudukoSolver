package Sudoku;
import java.awt.*;
import javax.swing.*; 

public class SudokuUI {
    JTextField[][] sudokuFields;
    Sudoku solver;

    public SudokuUI(Sudoku solver) {
        this.solver = solver;
        sudokuFields = new JTextField[9][9];
        SwingUtilities.invokeLater(() -> createWindow(solver, "Sudoku", 400, 410));
    }

    public void createWindow(Sudoku solver, String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(9, 9));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                sudokuFields[x][y] = new JTextField(1);
                sudokuFields[x][y].setText("");
                sudokuFields[x][y].setHorizontalAlignment(JTextField.CENTER);
                sudokuFields[x][y].setFont(font);
                sudokuFields[x][y].setForeground(Color.WHITE);

                if(((x < 3 || x > 5 ) && (y < 3 || y > 5)) || x > 2 && x < 6 && y > 2 && y < 6 ){
                    sudokuFields[x][y].setBackground(new Color(56, 140, 209));
                } else {
                    sudokuFields[x][y].setBackground(new Color(120, 120, 120));
                }
                fieldsPanel.add(sudokuFields[x][y], BorderLayout.NORTH);
                
            }
        }
        
        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        pane.add(fieldsPanel, BorderLayout.CENTER);
        pane.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
		frame.setVisible(true);
    }

    public void createBoard(Frame JFrame, Sudoku Sudoku){
        


    }
}