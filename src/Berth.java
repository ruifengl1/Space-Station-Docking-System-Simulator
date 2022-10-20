/**
 * cargo ship are docking, undocking and unloading at Berth
 *
 * @author RUIFENG LUO
 */

import java.util.ArrayList;

public class Berth {

    private int maxNumberShips;
    private ArrayList<Ship> ships;
    private Boolean isActivatedShield;
    private String berth;

    /**
     * Constructor method
     *
     * @param berth name of berth
     */
    Berth(String berth) {
        this.maxNumberShips = Params.MAX_NUM_SHIPS_BERTH;
        this.isActivatedShield = false;
        this.ships = new ArrayList<Ship>();
        this.berth = berth;

    }

    /**
     * docking cargo ship at berth
     *
     * @param ship ship object
     */
    public synchronized void docking(Ship ship) {

        while (this.ships.size() >= maxNumberShips || isActivatedShield) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ships.add(ship);
        System.out.println(ship + " docks at " + berth + ".");
        //notify();
    }


    /**
     * undocking cargo ship at berth
     */
    public synchronized void undocking() {

        while (this.ships.isEmpty() || this.isActivatedShield) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Ship ship = ships.remove(0);
        System.out.println(ship + " undocks from " + berth + ".");
        notify();

    }

    /**
     * set flag of shield to true
     */
    public synchronized void activeShield() {
        this.isActivatedShield = true;
        notifyAll();

    }

    /**
     * set flag of shield to false
     */
    public synchronized void deactivateShield() {
        this.isActivatedShield = false;
        notifyAll();

    }


}
