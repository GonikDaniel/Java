package games.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel_Gonik on 04.08.15.
 */
public class TicTacToeLogic {
    private boolean win = false;
    private static int move = 0;
    private static ArrayList<String> xCells = new ArrayList();
    private static ArrayList<String> oCells = new ArrayList();
    private static String winnerLists[][] = {
            {"00", "10", "20"},
            {"00", "01", "02"},
            {"02", "12", "22"},
            {"20", "21", "22"},
            {"00", "11", "22"},
            {"02", "11", "02"},
            {"01", "11", "21"},
            {"10", "11", "12"}
    };


    public static void markField(String cell, char sign) {
        move++;
        //marks the cell to true if it's already has zero or x
        TicTacToe.fields.replace(cell, true);

        //and adding some info to our arrays to determine a winner
        if (sign == 'x') xCells.add(cell);
        else oCells.add(cell);

        //here is some logging
        System.out.println();
        System.out.println("Marked x-cells: ");
        for (int i = 0; i < xCells.size(); i++) {
            System.out.print(xCells.get(i) + ", ");
        }
        System.out.println();
        System.out.println("Marked o-cells: ");
        for (int i = 0; i < oCells.size(); i++) {
            System.out.print(oCells.get(i) + ", ");
        }
        System.out.println();

        //checking whether we have a winner or not
        //X-es won
        if (checkWinner(winnerLists, xCells)) {
            // TODO - продумать логику для передачи координат выигранной линии!
            JPanel panel = TicTacToe.getPanel();
            Graphics g = panel.getGraphics();
//            g.drawLine(0, 0, 600, 600);
//            waitForIt(1000);

            panel.setBackground(Color.orange);
            System.out.println("Winner are here! X-es won!!!"); //we got the winner
            JOptionPane.showMessageDialog(null, "Aaand the winner is here! X's won!!!");
            waitForIt(1000);
            System.exit(0);
            // O-s won
        } else if (checkWinner(winnerLists, oCells)) {
            JPanel panel = TicTacToe.getPanel();
            panel.setBackground(Color.CYAN);
            System.out.println("Winner are here! O-s won!!!"); //we got the winner
            JOptionPane.showMessageDialog(null, "Aaand the winner is here! O's won!!!");
            waitForIt(1000);
            System.exit(0);
        } else if (move == 9) {
            JOptionPane.showMessageDialog(null, "There is no winner, unfortunately!");
            waitForIt(1000);
            System.exit(0);
        }
    }

    //TODO не работает если у нас в массиве checkingList 4 элемента
    public static boolean checkWinner(String[][] winnerList, java.util.List<String> checkingList){
        for (int i = 0; i < winnerList.length; i++) {
            ArrayList<String> list = new ArrayList<String>();
            for (int j = 0; j < winnerList[i].length; j++) {
                list.add(winnerList[i][j]);
            }
            if (list.containsAll(checkingList) && checkingList.containsAll(list)) return true;
        }
        return false;
    }

    public static void waitForIt(int Ms) {
        try {
            Thread.sleep(Ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
