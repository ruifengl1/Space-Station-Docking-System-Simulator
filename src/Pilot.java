/**
 * Pilot acquires a newly arrived cargo ship,
 * acquires the required number of tugs to dock the ship,
 * acquires tugs for undocking.
 *
 * @author RUIFENG LUO
 */
public class Pilot extends Thread {
    private int pilot;
    private WaitZone arrivalZone;
    private WaitZone departureZone;
    private Berth berth;
    private Tugs tugs;
    private Ship ship;

    /**
     * Constructor method
     *
     * @param pilot         the number of pilot
     * @param arrivalZone   ArrivalWaitZone object
     * @param departureZone DepartureWaitZone object
     * @param tugs          Tugs object
     * @param berth         Berth object
     */
    public Pilot(int pilot, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
        this.pilot = pilot;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.berth = berth;
        this.tugs = tugs;
    }

    // depart cargo ship from departure wait zone
    public void setShipOff() {
        System.out.println("pilot " + this.pilot + " releases " + this.ship + ".");
        this.ship = null;
    }

    // pilot acquire ship, tugs, docking at berth, unloading at berth
    // and undocking at berth, send ship to departure, release ship
    public void run() {

        while (!isInterrupted()) {
            try {
                this.ship = arrivalZone.depart(); // allocate ship to the pilot
                System.out.println("Pilot " + this.pilot + " acquires " + this.ship + ".");

                // request tugs for docking
                tugs.tugsRequest(Params.DOCKING_TUGS, pilot);

                // Travelling between a wait zone and the vicinity of the berth
                try {
                    sleep(Params.TRAVEL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // docking at berth for DOCKING_TIME
                try {
                    sleep(Params.DOCKING_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                berth.docking(this.ship);


                // return tugs
                tugs.returnTugs(Params.DOCKING_TUGS, pilot);

                // cargo ship is being unloading for UNLOADING_TIME
                System.out.println(ship + " being unloaded.");
                try {
                    sleep(Params.UNLOADING_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //  request tugs for undocking
                tugs.tugsRequest(Params.UNDOCKING_TUGS, pilot);


                //undocking  cargo ship for UNDOCKING_TIME
                try {
                    sleep(Params.UNDOCKING_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                berth.undocking();


                // Travelling between a wait zone and the vicinity of the berth
                try {
                    sleep(Params.TRAVEL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // send ship to departure wait zone
                departureZone.arrive(this.ship);

                // return tugs
                tugs.returnTugs(Params.UNDOCKING_TUGS, pilot);

                setShipOff();

                // let some time pass before the pilot arrives at arrival wait zone
                sleep(Params.arrivalLapse());

            } catch (InterruptedException e) {
                this.interrupt();
            }
        }


    }
}
