/**
 * cargo ship comes to depart wait zone waiting for departure
 *
 * @author RUIFENG LUO
 */

public class DepartureWaitZone extends WaitZone {
    /**
     * Constructor method
     *
     * @param waitZone name of wait zone
     */
    DepartureWaitZone(String waitZone) {
        //pass parameters to WaitZone class
        super(Params.MAX_NUM_SHIPS_DEPARTURE, waitZone);
    }


}
