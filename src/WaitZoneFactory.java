/**
 * Factory design pattern used for wait zone
 * It returns new wait zone object
 *
 * @author RUIFENG LUO
 */

public class WaitZoneFactory {

    /**
     * returns new object of different wait zone
     *
     * @param waitZone name of wait zone
     * @return return object of different wait zone or null
     */
    public static WaitZone getWaitZone(String waitZone) {

        if (waitZone.equals("arrival")) {

            return new ArrivalWaitZone("arrival");

        } else if (waitZone.equals("departure")) {

            return new DepartureWaitZone("departure");
        } else {

            return null;

        }


    }
}
