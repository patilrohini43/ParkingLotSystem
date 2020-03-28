import com.bridgelabz.parkinglot.ParkingOwner;
import com.bridgelabz.parkinglot.ParkingSystem;
import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem;
    ParkingOwner owner;
    Object object;
    Object vehicle;

    @Before
    public void setUp() {
        parkingSystem=new ParkingSystem(1);
        object=new Object();
        vehicle=new Object();
        owner= new ParkingOwner();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(vehicle);
        boolean parkCar=parkingSystem.vehiclePark(vehicle);
        System.out.println(parkCar);
        Assert.assertTrue(parkCar);
    }

    @Test
    public void givenVehicle_WhenUNParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(vehicle);
        boolean unparkCar=parkingSystem.unParkSystem(vehicle);
        Assert.assertTrue(unparkCar);
    }


    @Test
    public void givenVehicle_WhenAlreadyParkCar_ShouldReturnTrue() {
        try {
            parkingSystem.addParkSystem(vehicle);
            parkingSystem.addParkSystem(object);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Lot already full",e.getMessage());
        }
    }

    @Test
    public void givenParkingLotFull_ShouldReturnTrue() {
       parkingSystem.registerOwner(owner);
       try
       {
           parkingSystem.addParkSystem(vehicle);
           parkingSystem.addParkSystem(new Object());
       } catch (ParkingLotException e) {
            boolean capacity=owner.isCapacityFull();
            Assert.assertEquals(true,capacity);
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
            Assert.assertTrue(park1 && park2);

        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
