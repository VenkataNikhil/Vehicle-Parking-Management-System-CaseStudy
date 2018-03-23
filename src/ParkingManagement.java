import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParkingManagement implements VehicleParking{

    static Vehicle vehicle;
    static ParkingManagement p;
    static Scanner s = new Scanner(System.in);
    static ArrayList<Vehicle> car_list,truck_list,twoWh_list, ret_list;
    static int[] twoWh,car,truck;
    static int c1,c2,c3,owncount;

    @Override
    public boolean addVehicleToSlot(Vehicle vehicle) {

        return false;
    }

    @Override
    public int countUnassignedVehicles() {
        return owncount;
    }

    @Override
    public ArrayList<Vehicle> getAllVehicles(String s) {
//        s = s.toLowerCase();
        ret_list = new ArrayList<Vehicle>();
        if (s.contains("two"))
            ret_list = twoWh_list;
        else if (s.contains("car"))
            ret_list = car_list;
        else if (s.contains("truck"))
            ret_list = truck_list;

        VehicleComparator vehicleComparator = new VehicleComparator();

        System.out.println(ret_list.size());

        for(int i=0;i<ret_list.size();i++)
            System.out.println(ret_list.get(i).slotNo+" ");

        for (int i = 0; i < ret_list.size()-1; i++) {
            for (int j = 0; j < ret_list.size()-i-1; j++) {
                if(vehicleComparator.comparator(ret_list.get(j),ret_list.get(j+1))==1){
                    int temp = ret_list.get(i).getSlotNo();
                    ret_list.get(i).setSlotNo(ret_list.get(i+1).getSlotNo());
                    ret_list.get(i+1).setSlotNo(temp);
                }
            }
        }
        return ret_list;
    }

    @Override
    public HashMap<String, Integer> getCountAllVehicles() {
        HashMap<String,Integer> hashmap = new HashMap<String,Integer>();
        hashmap.put("two",twoWh_list.size());
        hashmap.put("truck",truck_list.size());
        hashmap.put("car",car_list.size());
        return hashmap;
    }

    @Override
    public boolean updateAllottedTime(int i, String s) {
        vehicle.setParkingDuration(i);
        return false;
    }

    public static void main(String[] args){
        p = new ParkingManagement();

        twoWh = new int[40];
        car = new int[40];
        truck = new int[40];

        car_list = new ArrayList<Vehicle>();
        twoWh_list = new ArrayList<Vehicle>();
        truck_list = new ArrayList<Vehicle>();

        boolean j=true;

        while(j) {
            System.out.println("Enter your choice\n");
            System.out.println("1.Add Vehicle");
            System.out.println("2. Update Allotted time ");
            System.out.println("3. get Count of all vehicles");
            System.out.println("4. get all vehicles of a certain category: ");
            System.out.println("5. Count unassigned vehicles ");
            System.out.println("6.EXIT");
            int choice = s.nextInt();

            switch(choice) {
                case 1: addVehicle();
                        break;

                case 2: vehicleDetails();
                        break;

                case 3:
                        System.out.println(p.getCountAllVehicles());
                        break;

                case 4:
                        System.out.println("Enter the category you want ..\n1.car\n2.truck\n3.two wheelers");
                        int ch = s.nextInt();
                        String cate = (ch==1?"car":(ch==2)?"truck":"two");
                        ArrayList<Vehicle> a = p.getAllVehicles(cate);
                        for(int i=0;i<a.size();i++)
                            System.out.println(a.get(i).getParkingDuration() +" "+a.get(i).getVehicleType()+" "+a.get(i).getSlotNo()+" "+a.get(i).getVehicleNo());
                        break;

                case 5:
                        System.out.println(p.countUnassignedVehicles());
                        break;

                case 6: System.exit(0);
                        break;

                default: System.exit(0);
            }
        }
    }

    private static void vehicleDetails() {
        System.out.println("Enter the type of the vehicle to Filter ");
        String text = s.next();
        System.out.println("The current vehicle parking allotted time is "+vehicle.getParkingDuration()+"\nEnter the required time ");
        int i = s.nextInt();
        p.updateAllottedTime(i,text);
    }

    private static void addVehicle() {

        vehicle = new Vehicle();

        System.out.println("Enter parking duration time: ");
        vehicle.setParkingDuration(s.nextInt());

        System.out.println("Enter vehicle no: ");
        vehicle.setVehicleNo(s.next());

        System.out.println("Enter vehicle type ");
        vehicle.setVehicleType(s.next());

        System.out.println("Do you want to enter the owner details: ");
        String check = s.next();
        if(check.contains("Y")||check.contains("y")) {
            Owner owner = new Owner();
            System.out.println("Enter the name of the Owner ");
            owner.setOwnerName(s.next());

            System.out.println("Enter the address of the owner ");
            owner.setOwnerAddress(s.next());

            System.out.println("Enter the email of the owner ");
            owner.setOwnerEmail(s.next());

            System.out.println("Enter the mobile no. of the owner ");
            owner.setMobileNo(s.nextLong());

            vehicle.setOwner(owner);
        }

        else {
            owncount++;
        }

        System.out.println("Enter slot no: ");
        int slot = s.nextInt();
        checkForException(slot);
        enterSlot(vehicle,vehicle.getVehicleType().toLowerCase(),slot);


        System.out.println(c1+" "+c2+" "+c3);
        //System.out.println(vehicle.getParkingDuration()+" "+vehicle.getSlotNo());
    }

    private static void checkForException(int slot) {
        if(slot>=40){
            try {
                throw new InvalidSlotException("Slot already allotted");
            }
            catch (InvalidSlotException e){
                e.printStackTrace();
            }
        }
        else
            vehicle.setSlotNo(slot);
    }

    private static void enterSlot(Vehicle vehicle,String vehicleType,int slot) {

        if(vehicleType.contains("two")) {
            if (twoWh[slot] != 1) {
                twoWh_list.add(vehicle);
                twoWh[slot] = 1;
                c1++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allotted!");
                }
                catch (SlotNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        else if(vehicleType.contains("car")) {
            if (car[slot] != 1) {
                car_list.add(vehicle);
                car[slot] = 1;
                c2++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allotted!");
                }
                catch (SlotNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        else if(vehicleType.contains("truck")) {
            if (truck[slot] != 1) {
                truck_list.add(vehicle);
                truck[slot] = 1;
                c3++;
            }
            else {
                try {
                    throw new SlotNotFoundException("No slot allotted!");
                }
                catch (SlotNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void sortVehicle(String s) {

    }
}
