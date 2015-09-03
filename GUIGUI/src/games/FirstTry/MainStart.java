package games.FirstTry;

/**
 * Created by Daniel_Gonik on 05.08.15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainStart extends JFrame {
    public static int count = 0;
    public MainStart() {
        initUI();
    }

    private void initUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Ты счастлив, что ты здесь?");
        JButton btn = new JButton("Да");
        JButton btn1 = new JButton("Нет");
        JLabel fibLabel = new JLabel("Числа Фибоначи");
        final JLabel fibLabelNum = new JLabel();
        JButton btnFibCount = new JButton("Count next!");
        final JTextField text = new JTextField("Your text is here");
        text.setHorizontalAlignment(JTextField.CENTER);
        JButton SHA = new JButton("Encrypt!");
        JButton SHA2 = new JButton("Descrypt!");

        SHA2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 'Descrypt!' was pressed");
                String textContent = text.getText();
                String encText = EncryptUtils.base64decode(textContent);
                text.setText(encText);
            }

        });

        SHA.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 'Encrypt!' was pressed");
                String textContent = text.getText();
                String encText = EncryptUtils.base64encode(textContent);
                text.setText(encText);
            }

        });

        btnFibCount.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 'Count next!' was pressed");
                String nextNum = "" + fibonacciRecursion(count);
                count++;
                fibLabelNum.setText(nextNum);
            }

        });

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 'Да' was pressed");
                JOptionPane.showMessageDialog(null, "Привет, мой юный друг!\nЯ рад, что ты познаешь Java так быстро!");
            }

        });

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 'Нет' was pressed");
                System.exit(0);
            }

        });


        createLayout(pane, label, btn, btn1, fibLabel, fibLabelNum, btnFibCount, text, SHA, SHA2);

        frame.pack();
        frame.setSize(560, 380);
        pane.setBackground(Color.CYAN);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createLayout(Container box, JComponent... arg) {
        for (int i = 0; i < arg.length; i++) {
            arg[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(arg[i]);
        }

    }

    // for Fibonacci number using recursion.
    public static int fibonacciRecursion(int number){
        if(number == 0) return 0;
        if(number == 1 || number == 2){
            return 1;
        } else {
            return fibonacciRecursion(number-1) + fibonacciRecursion(number -2); //tail recursion
        }
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainStart ex = new MainStart();
//            	ex.setSize(760, 180);
//                ex.setVisible(true);
            }
        });
    }
}
