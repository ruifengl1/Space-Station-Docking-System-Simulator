/**
 * cargo ship comes to arrival wait zone waiting for pilot
 *
 * @author RUIFENG LUO
 */

public class ArrivalWaitZone extends WaitZone {
    /**
     * Constructor method
     *
     * @param waitZone name of wait zone
     */
    ArrivalWaitZone(String waitZone) {
        //pass parameters to WaitZone class
        super(Params.MAX_NUM_SHIPS_ARRIVAL, waitZone);

    }


}
