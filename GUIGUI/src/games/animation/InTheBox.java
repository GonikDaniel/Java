package games.animation;


/**
 * Created by Daniel_Gonik on 05.08.15.
 */
        import javax.swing.*;
import java.awt.*;

final public class InTheBox
{

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 15;
    private int oneY = 15;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;

    public static void main(String... args)
    {
        new InTheBox().go();
    }

    private void go()
    {
        frame = new JFrame("InTheBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        moveIt();
    }

    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
//            g.setColor(Color.BLUE);
//            g.fillRect(0, 0, this.getWidth(), this.getHeight());
//            g.setColor(Color.RED);
//            g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
//            g.setColor(Color.WHITE);
//            g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
            g.setColor(Color.BLACK);
//            g.fillRect(oneX, oneY, 6, 6);
            g.fillOval(oneX, oneY, 100, 100);

        }

    }

    private void moveIt()
    {
        while (true)
        {
            if (oneX >= 500)
            {
                right = false;
                left = true;
            }
            if (oneX <= 7)
            {
                right = true;
                left = false;
            }
            if (oneY >= 500)
            {
                up = true;
                down = false;
            }
            if (oneY <= 7)
            {
                up = false;
                down = true;
            }
            if (up) oneY--;
            if (down) oneY++;
            if (left) oneX--;
            if (right) oneX++;
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}
