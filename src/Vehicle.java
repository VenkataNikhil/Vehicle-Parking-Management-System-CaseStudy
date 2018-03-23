public class Vehicle {

    Owner ownerObj;
    int slotNo,parkingDuration;
    String vehicleNo,vehicleType;

    public Vehicle() {

    }

    public Vehicle(Owner owner,String s1,int i1,String s2,int i2) {

    }

    public Owner getOwner() {
        return ownerObj;
    }

    public void setOwner(Owner owner) {
        this.ownerObj = owner;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(int parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
