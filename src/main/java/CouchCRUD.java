import com.google.gson.JsonObject;
import org.lightcouch.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    }

    public static void deleteCar(String id)
    {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null,"delete all records for " + id + ". Do you want to continue?","Warning Message",dialogButton);

        if(dialogResult == JOptionPane.YES_OPTION)
        {
            CouchDbClient dbClient = new CouchDbClient();
            Car car = getCarFromId(id);

            if(car != null)
            {
                dbClient.remove(car);
                JOptionPane.showMessageDialog(null,"The car with matching id " + id + " has been deleted");
            }
        }
    }

    public static List<JsonObject> listAllDocs()
    {
        CouchDbClient dbClient = new CouchDbClient();

          List<JsonObject> allDocs = dbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);

          return allDocs;

    }

    public static void simpleMapReduce(String dbName)
    {
        CouchDbClient dbClient = new CouchDbClient(dbName);

        /* MapReduce Design Document:
        {
          "_id": "_design/views",
          "_rev": "4-f74a20be55243a45bb3b4e9f2a7f2433",
          "views": {
            "byCarMake": {
              "map": "function(doc) {\r\n    emit(doc.carMake, 1);\r\n}",
              "reduce": "_count"
            },
            "byCarName": {
               "map": "function(doc)" {if('name' in doc) { emit (doc.name, doc._id); }}"
            }
          },
          "language": "javascript"
        }
        */

        List<JsonObject> allDocs = dbClient.view("views/byCarMake").reduce(true).group(true).query(JsonObject.class);
        List<JsonObject> stuff = dbClient.view("views/byCarMake").includeDocs(true).key("Ford Fiesta XXX").query(JsonObject.class);

    }

}
