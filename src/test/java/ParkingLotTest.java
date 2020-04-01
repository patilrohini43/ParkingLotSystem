import com.bridgelabz.parkinglot.AirportSecurity;
import com.bridgelabz.parkinglot.ParkingLotOwner;
import com.bridgelabz.parkinglot.ParkingLotSystem;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingLotTest {

    ParkingLotSystem parkingSystem;
    ParkingLotOwner owner;
    Object object;
    Object vehicle;

    @Before
    public void setUp() {
        parkingSystem=new ParkingLotSystem(1);
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
            assertFalse(capacity);
        }
    }

    @Test
    public void givenParkingLotParkingAttendent_ShouldbeReturnEmptySlot() {
            List<Integer> expectedList=new ArrayList<>();
            expectedList.add(0);
            expectedList.add(3);
            parkingSystem.registerParkingObserver(owner);
            parkingSystem.setCapacity(4);
            parkingSystem.listOfSlot();
            parkingSystem.addParkSystem(1,vehicle);
            parkingSystem.addParkSystem(2,object);
            List<Integer> list=parkingSystem.getEmptyAvailableSlot();
            Assert.assertEquals(expectedList,list);
    }

    @Test
    public void givenParkingLotDriverWantToGoHome_findVehicle_ShouldbeReturnTrue() {
            parkingSystem.registerParkingObserver(owner);
            parkingSystem.setCapacity(2);
            parkingSystem.listOfSlot();
            parkingSystem.addParkSystem(0,vehicle);
            parkingSystem.addParkSystem(1,object);
            boolean findCar= parkingSystem.findvehicle(vehicle);
            Assert.assertTrue(findCar);
    }

    @Test
    public void givenParkingLotOwnerWanttoKnow_whenCarParkInslot_ShouldBeReturnTime() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        parkingSystem.addParkSystem(0,vehicle);
        parkingSystem.addParkSystem(1,object);
        LocalDateTime parkTime=parkingSystem.getVehicleParkTime(0,vehicle);
        Assert.assertEquals(LocalDateTime.now().getMinute(),parkTime.getMinute());
   }

    @Test
    public void givenParkingLotOwnerWanttoKnow_whenCarUnParkInslot_ShouldBeReturnTime() throws ParkingLotException {
        parkingSystem.registerParkingObserver(owner);
        parkingSystem.setCapacity(2);
        parkingSystem.listOfSlot();
        try {
            parkingSystem.addParkSystem(0,vehicle);
            parkingSystem.unParkSystem(1,object);
        }
        catch (ParkingLotException e)
        {
            Assert.assertEquals("Vehicle not Found",e.getMessage());
        }
    }


}
