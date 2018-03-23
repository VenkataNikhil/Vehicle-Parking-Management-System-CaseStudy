public class VehicleComparator {
    public int comparator(Vehicle v1,Vehicle v2){
        if(v1.getSlotNo()>v2.getSlotNo())
            return 1;
        else
            return 0;
    }
}
