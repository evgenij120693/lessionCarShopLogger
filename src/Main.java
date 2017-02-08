import CarShop.Store;
import models.Order;

import java.io.*;

/**
 * Created by sa on 08.02.17.
 */
public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.createCar(500000, "kia-rio",
                "B146AA");
        store.sellCar("kia-rio",
                "Jhon",
                "Konner" ,
                "+79126241898");

        store.save();
    }
}
