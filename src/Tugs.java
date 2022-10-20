/**
 * Tug controller, it is to coordinate requests from pilots
 * to acquire or release the services of multiple tugs.
 *
 * @author RUIFENG LUO
 */

public class Tugs {
    private int availableTugs;

    /**
     * Constructor method
     *
     * @param totalTugs total number of tugs at the beginning
     */
    Tugs(int totalTugs) {
        this.availableTugs = totalTugs;
    }

    /**
     * allocate number of tugs requested from pilots
     *
     * @param numberTugs number of tugs required
     * @param pilot      the number of pilot
     */
    public synchronized void tugsRequest(int numberTugs, int pilot) {
        while (numberTugs > availableTugs) {
            try {
                wait();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }


        }
        availableTugs = availableTugs - numberTugs;
        System.out.println("Pilot " + pilot + " acquires " + numberTugs + " tugs " + "(" + availableTugs + " available).");
        notifyAll();


    }

    /**
     * release number of tugs request from pilots
     *
     * @param numReturnTugs number of tugs return
     * @param pilot         the number of pilot
     */
    public synchronized void returnTugs(int numReturnTugs, int pilot) {
        availableTugs = numReturnTugs + availableTugs;
        System.out.println("Pilot " + pilot + " releases " + numReturnTugs + " tugs " + "(" + availableTugs + " available).");
        notifyAll();

    }


}
