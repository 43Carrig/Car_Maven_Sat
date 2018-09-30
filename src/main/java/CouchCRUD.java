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
        //Database db = dbSession.getDatabase(dbName);

        Document doc = new Document();


        doc.setId("_design/mapReduceView12");

        String str = "{\"get_\": {\"map\": \"function(doc) { if ('Hours' in doc && 'Car' in doc)" +
                "emit(doc.Car, doc.Car) } \"}}";

        //doc.put("views", str);
        //doc.put(str);

        MapReduce mapreduce = new MapReduce();
        mapreduce.

        //doc.addAttachment("views",str);




        //db.saveDocument(doc);
        dbClient.save(doc);
    }


    public static void simpleMapReduceTry(String dbName)
    {
        CouchDbClient dbClient = new CouchDbClient();

        DesignDocument designDocument = new DesignDocument();

        designDocument.setId("_design/mapReduceView003");

        String str = "{\"getCar\": {\"map\": \"function(doc) { if ('Cars' in doc && 'Car' in doc)" +
                "emit(doc.Car, doc.Car) } \"}}";

        Map<String, MapReduce> view = new HashMap<>();



        //view.put();
    }

    public static void simpleMapReduceTryAgain(String dbName)
    {

        CouchDbClient dbClient = new CouchDbClient();

        DesignDocument designDocument = new DesignDocument();
        designDocument.setId("_design/mydesign");
        //designDocument.setLanguage("javascript");

        MapReduce mapreduce = new MapReduce();


        mapreduce.set(
                "function(doc) { "
                        + "  emit(doc.seriesName, doc.season)"
                        + "}");

        mapreduce.setReduce(
                "function (key, values, rereduce) {"
                        + "return Math.max.apply({}, values)"
                        + "}");

        Map<String, MapReduce> view = new HashMap<>();
        view.put("get_numberOfSeasons", mapreduce);

        //designDocument.setViews(view);

        dbClient.design().synchronizeWithDb(designDocument);

        int count = dbClient.view("mydesign/get_numberOfSeasons").key("Arrow").queryForInt();
    }



}
