import com.bridgelabz.parkinglot.ParkingSystem;
import com.bridgelabz.parkinglot.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem;

    @Before
    public void setUp() throws Exception {
        parkingSystem=new ParkingSystem();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Car car=new Car();
        boolean parkCar=parkingSystem.addParkSystem(car);
        System.out.println(parkCar);
        Assert.assertTrue(parkCar);
    }

    @Test
    public void givenVehicle_WhenUNParked_ShouldReturnTrue() {
        Car car=new Car();
        parkingSystem.addParkSystem(car);
        boolean unparkCar=parkingSystem.unParkSystem(car);
        Assert.assertTrue(unparkCar);
    }


    @Test
    public void givenVehicle_WhenAlreadyParkCar_ShouldReturnTrue() {
        Car car=new Car();
        parkingSystem.addParkSystem(car);
        boolean parkCar=parkingSystem.addParkSystem(car);
        Assert.assertFalse(parkCar);
    }
}
