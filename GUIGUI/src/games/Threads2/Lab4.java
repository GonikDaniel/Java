package games.Threads2;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Daniel_Gonik on 06.08.15.
 */
public class Lab4 {
    public static int arr[] = new int[15000000];
    public static void fillArr() {
        for (int i = 0; i < 15000000; i++) {
            arr[i] = i + 1;
        }

    }

    public static void main(String[] args) {

        fillArr();  //filling the array with 15000000 nums
        Scanner sc = new Scanner(System.in);
        System.out.println("Print the key");
        int key = Integer.parseInt(sc.next());

        //count the time for whole program
        //timer start
        long start = System.currentTimeMillis();
        ExecutorService app = Executors.newCachedThreadPool();

        //starting our workers
        for (int i = 0; i < 5; i++) {
            app.execute(new Worker(key, arr));
        }


        app.shutdown();

        //timer end and print
        long end = System.currentTimeMillis();
        long time = ((end - start));
        System.out.println("Execution time " + time + " millisec");
    }
}
