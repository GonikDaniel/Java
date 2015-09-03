package games.Threads2;

/**
 * Created by Daniel_Gonik on 06.08.15.
 */
public class Worker implements Runnable {
    private int key;
    private int arr[];
    private static int current = 0;
    private static boolean done = false;

    public Worker(int key, int arr[]) {
        this.key = key;
        this.arr = arr;
    }

    @Override
    public void run() {
        //if the work is not done yet - starting our new worker
        if (!done) {
            System.out.println("new thread runs");
            System.out.println("Thread " + this + " founds key " + key + " with index " + search());
            done = true;
        } else {
            System.out.println("The work is already done");
        }

    }

    private synchronized int search() {
        //searching key in the array
        for (; current < arr.length; current++) {
            if (arr[current] == key) return current;
        }
        return -1;
    }

}
