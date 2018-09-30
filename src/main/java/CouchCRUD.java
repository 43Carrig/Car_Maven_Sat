import com.google.gson.JsonObject;
import org.lightcouch.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class CouchCRUD { // REST

    public static Response postToCouch(Car car) // post/create
    {
        CouchDbClient dbClient = new CouchDbClient();
        Response response = dbClient.save(car);
        dbClient.shutdown();
        return response;
    }

    public static Car getCarFromId(String id) // get/read
    {
        CouchDbClient dbClient = new CouchDbClient();
        try
        {
            Car foundCar = dbClient.find(Car.class,id);
            dbClient.shutdown();
            return foundCar;
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Unable to find car with id " + id);
        }
        return null;
    }

    public static void putCarDetails(String id, String rev,String registration, Boolean isSold) //put/update // CustomerSale
    {

        CouchDbClient dbClient = new CouchDbClient();
        //car = getCarFromId(id);
        Car car = new Car();

        car.set_id(id);
        car.set_rev(rev);
        car.setRegistration(registration);
        car.setSold(Boolean.valueOf(isSold));

        dbClient.update(car);
        dbClient.shutdown();

//        CouchDbClient dbClient = new CouchDbClient();
//        Car car = getCarFromId(tagId);
//
//        try {
//            List<PrivateSale> currentSales = car.getSales();
//            currentSales.add(sale);
//            car.setSales(currentSales);
//        }
//        catch (Exception e)
//        {
//            List<PrivateSale> newSales = new ArrayList<>();
//            newSales.add(sale);
//            car.setSales(newSales);
//        }
//
//        dbClient.update(car);
//        dbClient.shutdown();
    }

    public static void deleteCar(String tag)
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null,"delete all records for " + tag + ". Do you want to continue?","Warning Message",dialogButton);

        if(dialogResult == JOptionPane.YES_OPTION)
        {
            CouchDbClient dbClient = new CouchDbClient();
            Car car = getCarFromId(tag);

            if(car != null)
            {
                dbClient.remove(car);
                JOptionPane.showMessageDialog(null,"The animal with matching Tag " + tag + " has been deleted");
            }
        }
    }

    public static List<JsonObject> listAllDocs()
    {
        CouchDbClient dbClient = new CouchDbClient();

          List<JsonObject> allDocs = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);

          return allDocs;

    }




}
