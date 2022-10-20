/**
 * cargo ship wait at arrival wait zone before allocated to pilot and
 * cargo ship return to departure wait zone
 *
 * @author RUIFENG LUO
 */

import java.util.ArrayList;

public abstract class WaitZone {

    private int maxNumberShips;
    private ArrayList<Ship> ships;
    private String waitZone;

    /**
     * Constructor method
     *
     * @param maxNumberShips maximum number of ships allowed in the wait zone
     * @param waitZone       name of wait zone
     */
    public WaitZone(int maxNumberShips, String waitZone) {
        this.ships = new ArrayList<Ship>();
        this.maxNumberShips = maxNumberShips;
        this.waitZone = waitZone;
    }

    /**
     * cargo ship arrive at wait zone
     *
     * @param ship Ship object
     */
    public synchronized void arrive(Ship ship) {
        while (this.ships.size() >= this.maxNumberShips) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ships.add(ship);
        System.out.println(ship + " arrives at " + waitZone + " zone.");
        notify();

    }

    // cargo ships depart from wait zone
    public synchronized Ship depart() {
        while (this.ships.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Ship ship = ships.remove(0);
        notify();
        System.out.println(ship + " departs at " + waitZone + " zone.");
        return ship;

    }

}
