package games.Threads;

import static games.Threads.BallsInOneLine.*;

/**
 * Created by Daniel_Gonik on 05.08.15.
 */
public class Worker implements Runnable {
    public static boolean running = false;

    public Worker() {
        running = true;
    }

    @Override
    public void run() {
        System.out.println("Lets go!");

        while (true) {
            if ((checked && oneX >= width - ballRadius + 5) || (checked && oneX == rand - ballRadius) || (!checked && oneX >= width - ballRadius + 5)) {
                right = false;
                left = true;
            }
            if (oneX <= 5 || (checked && oneX == rand + ballRadius)) {
                right = true;
                left = false;
            }

            if (left) oneX--;
            if (right) oneX++;
            try {
                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            frame.repaint();
        }

    }
}
