package Sudoku;
import java.awt.*;
import javax.swing.*; 

public class SudokuUI {
    JTextField[][] sudokuFields;
    SudokuSolver solver;

    public SudokuUI(Sudoku solver, String windowTitle) {
        this.solver = solver;
        sudokuFields = new JTextField[9][9];
        SwingUtilities.invokeLater(() -> createWindow(windowTitle, 400, 410));
    }

    public void createWindow(String title, int width, int height){
        JFrame frame = new JFrame(title);
        Container pane = frame.getContentPane();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.pack();
		frame.setVisible(true);

        pane.add(createFields(), BorderLayout.CENTER);
        pane.add(createButtons(frame), BorderLayout.SOUTH);
    }

    private JPanel createButtons(JFrame frame) {
        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        clearButton.addActionListener(e -> {
            clearBoard();
        });

        solveButton.addActionListener(e -> {
            solveBoard(frame);
        });

        return buttonPanel;
    }

    private JPanel createFields() {
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

        return fieldsPanel;
    }

    private void solveBoard(Frame frame) {
        boolean error = false;
        
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                String input = sudokuFields[x][y].getText();
                
                if(input.equals("")) {
                    solver.setCell(x, y, 0);
                } else {
                    try {
                        Integer.parseInt(input);

                        if(Integer.parseInt(input) > 9 || Integer.parseInt(input) < 1) {
                            error = true;
                            sudokuFields[x][y].setText("");
                        } else {
                            solver.setCell(x, y, Integer.parseInt(input));
                        }
                    } catch (NumberFormatException e) {
                        error = true;
                        sudokuFields[x][y].setText("");
                    }
                }     
            }
        }

        if(error) {
            JOptionPane.showMessageDialog(frame, "Only numbers between 1-9 can be entered. Invalid entries have been cleared.");
        } else {
            if(!solver.solve()) {
                JOptionPane.showMessageDialog(frame, "The entered Sudoku is unsolvable");
            } else {
                for(int x = 0; x < 9; x++) {
                    for(int y = 0; y < 9; y++) {
                        sudokuFields[x][y].setText(((Integer) (solver.getCell(x, y))).toString());
                    }
                }
            }
        }
    }

    private void clearBoard() {
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                sudokuFields[x][y].setText("");
            }
        }
        solver.clear();
    }
}