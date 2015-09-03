package games.Threads;

/**
 * Created by Daniel_Gonik on 05.08.15.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

final public class BallsInOneLine {

    //frame elements
    public static JFrame frame;
    DrawPanel drawPanel;
    Checkbox checkbox;

    //flag for state switching
    public static boolean checked = false;

    //ball parameters
    public static int oneX = 15;
    public static int oneY = 15;
    public static int ballRadius = 50;

    public static boolean left = false;
    public static boolean right = true;

    public static int height = 600;
    public static int width = 600;

    public static int rand = new Random().nextInt(width - ballRadius);

    private void go() {
        //key elements
        frame = new JFrame("Balls In One Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new DrawPanel();
        checkbox = new Checkbox("Check it out");

        //listener to checkbox state change & adding it drawpanel
        checkbox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checked) {
                    frame.repaint();
                } else {
                    rand = new Random().nextInt(width - ballRadius);
                }
                checked = !checked;
            }

        });
        drawPanel.add(checkbox);

        //adding drawpanel to main frame
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        //frame parameters && run the animation method
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // moveIt();
        //initiating new thread
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
    }


    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            g.setColor(Color.orange);
            g.fillOval(oneX, height / 2 - ballRadius, ballRadius, ballRadius);
            if (checked) {
                g.setColor(Color.green);
                g.fillOval(rand, height / 2 - ballRadius, ballRadius, ballRadius);
            }
        }

    }


    public static void main(String... args) {
        new BallsInOneLine().go();
    }
}
