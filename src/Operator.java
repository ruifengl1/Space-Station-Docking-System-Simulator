/**
 * Operator periodically activates the shield to protect the space
 * station from space debris.
 *
 * @author RUIFENG LUO
 */

public class Operator extends Thread {

    private Berth berth;

    /**
     * Constructor method
     *
     * @param berth name of berth
     */
    Operator(Berth berth) {
        this.berth = berth;
    }

    // shield are activated and deactivated for a certain time interval
    public void run() {
        while (!isInterrupted()) {
            try {

                // let some time pass before the next debris
                sleep(Params.debrisLapse());
                // active shield
                System.out.println("Shield is activated.");
                this.berth.activeShield();

                sleep(Params.DEBRIS_TIME);

                this.berth.deactivateShield();
                System.out.println("Shield is deactivated.");


            } catch (InterruptedException e) {
                this.interrupt();
            }
        }


    }
}
