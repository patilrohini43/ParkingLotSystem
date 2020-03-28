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
    Car car;

    @Before
    public void setUp() {
        parkingSystem=new ParkingSystem(1);
        object=new Object();
        car=new Car();
        owner= new ParkingOwner();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(car);
        boolean parkCar=parkingSystem.vehiclePark(car);
        System.out.println(parkCar);
        Assert.assertTrue(parkCar);
    }

    @Test
    public void givenVehicle_WhenUNParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.addParkSystem(car);
        boolean unparkCar=parkingSystem.unParkSystem(car);
        Assert.assertTrue(unparkCar);
    }


    @Test
    public void givenVehicle_WhenAlreadyParkCar_ShouldReturnTrue() {
        try {
            parkingSystem.addParkSystem(car);
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
           parkingSystem.addParkSystem(car);
           parkingSystem.addParkSystem(car);
       } catch (ParkingLotException e) {
            boolean capacity=owner.isCapacityFull();
            Assert.assertEquals(true,capacity);
       }
    }
}
