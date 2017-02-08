package CarShop;

import com.sun.org.apache.xpath.internal.operations.Or;
import models.Car;
import models.Client;
import models.Order;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by sa on 08.02.17.
 */
public class Store {
    private HashMap<Order, Client> contractList = new HashMap<>(256);
    private HashSet<Car> cars = new HashSet<>(32);
    private HashSet<Client> clients = new HashSet<>(256);

    public void createCar(int price, String model,
                          String regNumber){
        Car car = new Car(price, model, regNumber);
        cars.add(car);
    }

    public void sellCar(String model,
                        String firstName,
                        String lastName,
                        String phoneNumber){
        Client client = new Client(firstName,
                lastName, phoneNumber);
        clients.add(client);

        Car tmpCar = null;
        for (Car car:
                cars) {
            if (car.getModel().equals(model)){
                tmpCar = car;
                break;
            }
        }
        if (tmpCar != null){
            Random random = new Random();
            Order order = new Order(tmpCar,
                    tmpCar.getPrice() * 2,
                    random.nextLong(), (short) 80
            );
            contractList.put(order, client);
        }
    }

    public void getOrders(){

    }

    public void getFreeCars(){

    }
}
