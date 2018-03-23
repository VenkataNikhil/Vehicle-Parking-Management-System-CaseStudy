import java.util.ArrayList;
import java.util.HashMap;

public interface VehicleParking {

    boolean addVehicleToSlot(Vehicle vehicle);

    int  countUnassignedVehicles();

    ArrayList<Vehicle> getAllVehicles(String s);

    HashMap<String,Integer> getCountAllVehicles();

    boolean updateAllottedTime(int i,String s);
}
