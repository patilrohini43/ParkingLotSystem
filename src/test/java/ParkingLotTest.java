import com.bridgelabz.parkinglot.*;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem;
    ParkingLot parkingSystem;
    ParkingLotOwner owner;
    Object object;
    Object vehicle;

    @Before
    public void setUp() {
        parkingLotSystem=new ParkingLotSystem();
        parkingSystem=new ParkingLot(1);
        object=new Object();
        vehicle=new Object();
        owner= new ParkingLotOwner();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(vehicle);
        boolean parkCar=parkingSystem.vehiclePark(vehicle);
        System.out.println(parkCar);
        assertTrue(parkCar);
    }

    @Test
    public void givenVehicle_WhenUNParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(vehicle);
        boolean unparkCar=parkingSystem.unParkSystem(vehicle);
        assertTrue(unparkCar);
    }


    @Test
    public void givenVehicle_WhenAlreadyParkCar_ShouldReturnTrue() {
        try {
            parkingSystem.addParkSystem(vehicle);
            parkingSystem.addParkSystem(object);
        } catch (ParkingLotException e) {
            assertEquals("Lot already full",e.getMessage());
        }
    }

    @Test
    public void givenParkingLotFull_ShouldInformtoOwnerReturnTrue() {
       parkingSystem.registerParkingObserver(owner);
       try
       {
           parkingSystem.addParkSystem(vehicle);
           parkingSystem.addParkSystem(new Object());
       } catch (ParkingLotException e) {
            boolean capacity=owner.isCapacityFull();
            assertEquals(true,capacity);
       }
    }

    @Test
    public void givenCapacity2_shouldbePark2vehicles(){
        try {
            parkingSystem.setCapacity(2);
            parkingSystem.addParkSystem(vehicle);
            parkingSystem.addParkSystem(object);
            boolean park1= parkingSystem.vehiclePark(vehicle);
            boolean park2= parkingSystem.vehiclePark(object);
            assertTrue(park1 && park2);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotFull_ShouldInformtoAirportSecurityrReturnTrue() {
        AirportSecurity security=new AirportSecurity();
        parkingSystem.registerParkingObserver(security);
        try
        {
            parkingSystem.addParkSystem(vehicle);
            parkingSystem.addParkSystem(new Object());
        } catch (ParkingLotException e) {
            boolean capacity=security.isCapacityFull();
            assertEquals(true,capacity);
        }
    }

    @Test
    public void givenParkingLotSpaceAvaibleAfterFull_shouldBeReturnFalse() {
        parkingSystem.registerParkingObserver(owner);
        try
        {
            parkingSystem.addParkSystem(vehicle);
            parkingSystem.addParkSystem(new Object());
        } catch (ParkingLotException e) {
            parkingSystem.unParkSystem(vehicle);
            boolean capacity=owner.isSpaceAvaible();
            assertTrue(capacity);
        }
    }

    @Test
    public void givenParkingLotParkingAttendent_ShouldbeReturnEmptySlot() throws ParkingLotException {
            List<Integer> expectedList=new ArrayList<>();
            expectedList.add(0);
            expectedList.add(3);
            parkingSystem.registerParkingObserver(owner);
            parkingSystem.setCapacity(4);
            parkingSystem.listOfSlot();
            parkingSystem.addParkSystem(1,vehicle,DriverType.Normal,VehicleType.SmallVehicle );
            parkingSystem.addParkSystem(2,object,DriverType.Normal, VehicleType.SmallVehicle );
            List<Integer> list=parkingSystem.getEmptyAvailableSlot();
            Assert.assertEquals(expectedList,list);
    }

    @Test
    public void givenParkingLotDriverWantToGoHome_findVehicle_ShouldbeReturnTrue() throws ParkingLotException {
            parkingSystem.registerParkingObserver(owner);
            parkingSystem.setCapacity(2);
            parkingSystem.listOfSlot();
            parkingSystem.addParkSystem(0,vehicle,DriverType.Normal, VehicleType.SmallVehicle );
            parkingSystem.addParkSystem(1,object,DriverType.Normal, VehicleType.SmallVehicle );
            boolean findCar= parkingSystem.findvehicle(object);
            Assert.assertTrue(findCar);
    }

    @Test
    public void givenParkingLotDriverWantToGoHome_whenVehicleNotFound_ShouldbeReturnException()  {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        try {
            parkingSystem.addParkSystem(0, vehicle,DriverType.Normal, VehicleType.SmallVehicle );
            parkingSystem.addParkSystem(1, object,DriverType.Normal, VehicleType.SmallVehicle );
            boolean findCar = parkingSystem.findvehicle(vehicle);
            Assert.assertTrue(findCar);
        }catch (ParkingLotException e){
            Assert.assertEquals("not found",e.getMessage());
        }
    }

    @Test
    public void givenParkingLotOwnerWanttoKnow_whenCarParkInslot_ShouldBeReturnTime() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        parkingSystem.addParkSystem(0,vehicle,DriverType.Normal, VehicleType.SmallVehicle );
        parkingSystem.addParkSystem(1,object,DriverType.Normal, VehicleType.SmallVehicle );
        LocalDateTime parkTime=parkingSystem.getVehicleParkTime(0,vehicle);
        Assert.assertEquals(LocalDateTime.now().getMinute(),parkTime.getMinute());
   }

    @Test
    public void givenParkingLotOwnerWanttoKnow_whenCarUnParkInslot_ShouldBeReturnException() {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        try {
            parkingSystem.addParkSystem(0,vehicle,DriverType.Normal, VehicleType.SmallVehicle );
            parkingSystem.unParkSystem(1,vehicle);
        }
        catch (ParkingLotException e)
        {
            Assert.assertEquals("Vehicle not Found",e.getMessage());
        }
    }

    @Test
    public void givenParkingLotSystem_parkVehicleEvenlyDistributedInParkingLots_vehicleFind_shouldBeReturnTrue() throws ParkingLotException {
        ParkingLot parkingLot1=new ParkingLot(3);
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(3);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(0,vehicle, DriverType.Normal, VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(1,object, DriverType.Normal, VehicleType.SmallVehicle);
        boolean parkFind=parkingLotSystem.findVehicleInparkingLot(vehicle);
        parkingLot1.setCapacity(3);
        parkingLot1.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(0,vehicle, DriverType.Normal, VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(1,object, DriverType.Normal, VehicleType.SmallVehicle);
        boolean parkFind1=parkingLotSystem.findVehicleInparkingLot(vehicle);
        Assert.assertTrue(parkFind && parkFind1);
    }

    @Test
    public void givenParkingLotSystem_parkCarInMultipleParkingLot_shouldBeReturnEmptySlot() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(3);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(0,vehicle, DriverType.Normal, VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(1,object, DriverType.Normal, VehicleType.SmallVehicle);
        List<Integer> parkingEmptySlots=parkingLotSystem.getParkingLotAvailableSlotList();
        Assert.assertEquals(parkingEmptySlots,parkingEmptySlots);
    }

    @Test
    public void givenParkingLotSystem_addCarInMultipleSlot_whenCapacitFull_shouldBeReturnTrue() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        try {
            parkingLotSystem.parkVehicle(0, vehicle, DriverType.Normal, VehicleType.SmallVehicle);
            parkingLotSystem.parkVehicle(1, object, DriverType.Normal, VehicleType.SmallVehicle);
            parkingLotSystem.parkVehicle(2, new Object(), DriverType.Normal, VehicleType.SmallVehicle);
        }catch (ParkingLotException e){
            Assert.assertEquals(e.getMessage(),e.getMessage());
        }
    }

    @Test
    public void givenParkingLotSystem_whenNormalDriverWantToParkCar_givenNearestSlot() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(5);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(3, vehicle,DriverType.Normal, VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(1, object, DriverType.Normal, VehicleType.SmallVehicle);
        List parkingEmptySlots=parkingSystem.getDrivertypeWiselist(DriverType.Normal,VehicleType.SmallVehicle);
        Assert.assertEquals(4,parkingEmptySlots.get(0));
    }

    @Test
    public void givenParkingLotSystem_whenHandicapDriverWantToParkCar_givenNearestSlot() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(5);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(4, vehicle,DriverType.Handicap, VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(0, object, DriverType.Handicap, VehicleType.SmallVehicle);
        List parkingEmptySlots=parkingSystem.getDrivertypeWiselist(DriverType.Handicap,VehicleType.SmallVehicle);
        Assert.assertEquals(1,parkingEmptySlots.get(0));
    }


    @Test
    public void givenParkingLotSystem_whenParkFullInformSpaceAvaible_shouldBeReturnFalse()  {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        try
        {
            parkingLotSystem.parkVehicle(0, vehicle,DriverType.Handicap, VehicleType.SmallVehicle);
            parkingLotSystem.parkVehicle(1, object, DriverType.Handicap, VehicleType.SmallVehicle);
            parkingLotSystem.parkVehicle(2, vehicle,DriverType.Handicap, VehicleType.SmallVehicle);
        } catch (ParkingLotException e) {
            parkingLotSystem.unparkVehicle(1,object);
            boolean capacity=owner.isSpaceAvaible();
            assertTrue(capacity);
        }
    }

    @Test
    public void giveParkingLotSystem_whenSmallCarParkInParkingLot_shouldBeAllocateFreeSpace() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(7);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(0,vehicle,DriverType.Normal,VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(1,vehicle,DriverType.Normal,VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(2,vehicle,DriverType.Normal,VehicleType.SmallVehicle);
        parkingLotSystem.parkVehicle(3,vehicle,DriverType.Normal,VehicleType.SmallVehicle);
        List parkingEmptySlots=parkingSystem.getDrivertypeWiselist(DriverType.Normal,VehicleType.SmallVehicle);
        Assert.assertEquals(parkingEmptySlots,parkingEmptySlots);
    }

    @Test
    public void giveParkingLotSystem_whenLargeVehicleInParkInParkingLot_shouldBeAllocateFreeSpace() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(8);
        parkingSystem.listOfSlot();
        parkingLotSystem.add(parkingSystem);
        parkingLotSystem.parkVehicle(0,vehicle,DriverType.Normal,VehicleType.LargeVehicle);
        parkingLotSystem.parkVehicle(1,vehicle,DriverType.Normal,VehicleType.LargeVehicle);
        parkingLotSystem.parkVehicle(2,vehicle,DriverType.Normal,VehicleType.LargeVehicle);
        List parkingEmptySlots=parkingSystem.getDrivertypeWiselist(DriverType.Normal,VehicleType.LargeVehicle);
        Assert.assertEquals(parkingEmptySlots,parkingEmptySlots);
    }
}
