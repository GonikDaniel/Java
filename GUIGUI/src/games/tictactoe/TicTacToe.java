package games.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

class Surface extends JPanel {

    public static void main(String[] args)
    {
        new TicTacToe();

    }

    private boolean win = false;
    private boolean tictactoe = false;
    private boolean leftToRight = false;
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(Color.orange);
        g2d.drawLine(0, 0, getWidth(), getHeight());
        repaint();
    }

    public Surface(boolean win) {
        this.win = win;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        if (win) {
            if (win) {
                g.setColor(Color.black);
                g.drawLine(0, 0, getWidth(), getHeight());
                repaint();
            }
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        if (tictactoe) {
            if (leftToRight) {
                g.drawLine(0, (int) (getHeight() / 2), getWidth(), (int) (getHeight() / 2));
            }
        }
    }
}

public class TicTacToe extends JFrame implements ActionListener {
    private static JPanel panel;
    private JButton[][] buttons;
    private boolean toggle = true;


    public static JPanel getPanel() {
        return panel;
    }

    private final int SIZE = 3;
    private final int cellSize = 150;
    private GridLayout experimentLayout;

    public static HashMap<String, Boolean> fields = new HashMap<>();

    public TicTacToe()
    {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(cellSize * 3,cellSize * 3 + 22); //+22 - to adjust sizes with a frame of app
        setResizable(false);
        setLocationRelativeTo(null);

        //init the field list
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fields.put("" + i + j, false); //mark each cell with unique string key (00, 01, etc) and false flag
            }
        }


        experimentLayout =  new GridLayout(SIZE,SIZE);

        panel = new JPanel();
        panel.setLayout(experimentLayout);


        buttons = new JButton[SIZE][SIZE];
        addButtons(panel);


        //add(panel2);
        add(panel);
        setVisible(true);
    }



    public void addButtons(JPanel panel) {
        for (int k = 0; k < SIZE; k++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[k][j] = new JButton();
                buttons[k][j].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[k][j].setForeground(Color.blue);
                buttons[k][j].addActionListener((ActionListener) this);
                panel.add(buttons[k][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        int x = button.getX();
        int y = button.getY();
        String cell = "" + x/cellSize + y/cellSize;
        if (toggle) {
            if (button.getText() == "") button.setText("X");
            TicTacToeLogic.markField(cell, 'x');
        } else {
            if (button.getText() == "") button.setText("O");
            TicTacToeLogic.markField(cell, 'o');
        }
        toggle = !toggle;
    }
}