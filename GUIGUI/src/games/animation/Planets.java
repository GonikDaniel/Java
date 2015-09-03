package games.animation;

/**
 * Created by Daniel_Gonik on 05.08.15.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Planets extends JComponent {
    private double angle;
    private double angle2;

    public Planets() {
        angle = 0;
        angle2 = 0;
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 0.02;
                repaint();
            }
        });
        Timer timer2 = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle2 -= 0.05;
                repaint();
            }
        });
        timer.start();
        timer2.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //width and height of frame
        int width = getWidth();
        int height = getHeight();

        //set background
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //adding star sky
        g2d.setPaint(Color.white);
        int w = getWidth();
        int h = getHeight();
        Random rand = new Random();
        for (int i = 0; i < 80; i++) {
            int x = Math.abs(rand.nextInt()) % w;
            int y = Math.abs(rand.nextInt()) % h;
            g2d.drawOval(x, y, 1, 1);
        }

        //setting parameters for planets circles
        double x = 0.5 * width;
        double y = 0.5 * height;
        double r = 0.75 * Math.min(x, y);
        double x2 = 0.45 * width;
        double y2 = 0.45 * height;
        double r2 = 0.75 * Math.min(x, y);
        x += r/2 * Math.cos(angle);
        y += r/2 * Math.sin(angle);
        r = Math.max(0.1 * r, 5);
        g2d.setColor(Color.orange);
        x2 += r2 * Math.cos(angle2);
        y2 += r2 * Math.sin(angle2);
        r2 = Math.max(0.15 * r2, 5);

        //color for 1st planet
        g.setColor(Color.orange);
        g2d.fill(circle(x, y, r));
        //color for 2nd planet
        g2d.setColor(Color.red);
        g2d.fill(circle(x2, y2, r2));

    }

    private Shape circle(double x, double y, double r) {
        return new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //adding frame for planets
                JFrame frame = new JFrame("Planets");
                frame.add(new Planets());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 600);    //size of frame
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);     //lets show beggins
            }
        });
    }
}