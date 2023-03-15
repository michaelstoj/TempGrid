import java.util.concurrent.CyclicBarrier;

public class TempGrid {

    public static void main(String[] args) throws InterruptedException {

        // length & width of grid
        // numInnerGrid HAS TO BE DIVISABLE BY 8 AND 4 TO WORK

        int numInnerGrid = 160;

        int len = numInnerGrid + 2;

        // number of threads
        int threadnum = 4;

        int top = 90;       // top of grid
        int left = 10;      // left side of grid
        int bottom = 80;    // bottom of grid
        int right = 20;     // right side of grid

        double map[][] = new double[len][len];  // TempGrid map

        long startTime = System.currentTimeMillis();
        long endTime = 0;

        int[] iterations = new int[8];
        double[] totalAvg = new double[8];
        double[] totalError = new double[8];

        double avg = 0.0;

        // Create Original Map with values at top,left,right and bottom
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == 0 && j > 0 && j < len - 1) {
                    map[i][j] = top;
                }
                if (i == len - 1 && j > 0 && j < len - 1) {
                    map[i][j] = bottom;
                }
                if (j == 0 && i > 0 && i < len - 1) {
                    map[i][j] = (left);
                }
                if (j == len - 1 && i > 0 && i < len - 1) {
                    map[i][j] = (right);
                }
            }
        }


        if (threadnum == 1){
            CyclicBarrier barrier = new CyclicBarrier(threadnum); // create barrier for 1 thread
            Thread thread = new Thread(map, len, 0, threadnum, barrier, iterations, totalAvg, totalError);
            thread.start();


            try{
                thread.join();
            } catch (Exception e){
                System.out.println(e);
            }

            // print out results
            System.out.println("Map:");
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
//                    System.out.print(map[i][j] + " ");
                    avg = avg + map[i][j];
                }
//                System.out.println();
            }

            // print out results of calculations
            System.out.println("-----------------one thread---------------------");
            System.out.println("Iterations: " + iterations[0]);
            System.out.println("Total Average: " + (avg / (len * len)));
            System.out.println("Total Error: " + totalError[0]);
//            System.out.println("middle value: " + map[250][250]);

            // get total runtime of program
            endTime = System.currentTimeMillis();
            System.out.println("Time is " + (endTime - startTime) + " ms");

//            Thread.currentThread ().getThreadGroup ().interrupt ();

        } else if (threadnum == 8) {

            CyclicBarrier  barrier = new CyclicBarrier(threadnum);

            Thread a = new Thread(map, len, 0, threadnum, barrier, iterations, totalAvg, totalError);
            Thread b = new Thread(map, len, 1, threadnum, barrier, iterations, totalAvg, totalError);
            Thread c = new Thread(map, len, 2, threadnum, barrier, iterations, totalAvg, totalError);
            Thread d = new Thread(map, len, 3, threadnum, barrier, iterations, totalAvg, totalError);
            Thread e = new Thread(map, len, 4, threadnum, barrier, iterations, totalAvg, totalError);
            Thread f = new Thread(map, len, 5, threadnum, barrier, iterations, totalAvg, totalError);
            Thread g = new Thread(map, len, 6, threadnum, barrier, iterations, totalAvg, totalError);
            Thread h = new Thread(map, len, 7, threadnum, barrier, iterations, totalAvg, totalError);

            a.start();
            b.start();
            c.start();
            d.start();
            e.start();
            f.start();
            g.start();
            h.start();

            a.join();
            b.join();
            c.join();
            d.join();
            e.join();
            f.join();
            g.join();
            h.join();

            // print out results
            System.out.println("Map:");
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
//                    System.out.print(map[i][j] + " ");
                    avg = avg + map[i][j];
                }
//                System.out.println();
            }


            System.out.println("-----------------eight thread---------------------");
            System.out.println("Iterations (per thread): " + (iterations[0])) ;
            System.out.println("Total Average: " + (avg / (len * len)));
            System.out.println("Total Error: " + ((totalError[0] + totalError[1] + totalError[2] + totalError[3] + totalError[4] + totalError[5] + totalError[6] + totalError[7])));
//            System.out.println("middle value: " + map[250][250]);

            // get total runtime of program
            endTime = System.currentTimeMillis();

            System.out.println("Time: " + (endTime - startTime) + " ms");

        }
        else {
            CyclicBarrier barrier = new CyclicBarrier(threadnum); // create barrier for 4 threads

            Thread a = new Thread(map, len, 0, threadnum, barrier, iterations, totalAvg, totalError);;
            Thread b = new Thread(map, len, 1, threadnum, barrier, iterations, totalAvg, totalError);;
            Thread c = new Thread(map, len, 2, threadnum, barrier, iterations, totalAvg, totalError);;
            Thread d = new Thread(map, len, 3, threadnum, barrier, iterations, totalAvg, totalError);;

            a.start();
            b.start();
            c.start();
            d.start();
            a.join();
            b.join();
            c.join();
            d.join();

            // print out results
            System.out.println("Map:");
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
//                    System.out.print(map[i][j] + " ");
                    avg = avg + map[i][j];
                }
//                System.out.println();
            }


            // print out results of calculations
            System.out.println("-----------------four thread---------------------");
            System.out.println("Iterations (per thread): " + (iterations[0])) ;
            System.out.println("Total Average: " + (avg / (len * len)));
            System.out.println("Total Error: " + ((totalError[0] + totalError[1] + totalError[2] + totalError[3] )));
//            System.out.println("middle value: " + map[250][250]);

            // get total runtime of program
            endTime = System.currentTimeMillis();

            System.out.println("Time: " + (endTime - startTime) + " ms");

//            Thread.currentThread ().getThreadGroup ().interrupt ();
//            System.exit(0);

        }

    }
}
