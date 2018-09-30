import java.util.List;

public class Car {

    private String _id;
    private String _rev;
    private Boolean isSold;
    private String registration;

    public Car()
    {

    }

    public Car(String _id) {

        this._id = _id;
    }

    public Car(String _id, Boolean isSold,String registration)
    {
        this._id = _id;
        this._rev = _rev;
        this.isSold = isSold;
        this.registration = registration;
    }

    public Car(String _id, String _rev, Boolean isSold,String registration)
    {
        this._id = _id;
        this._rev = _rev;
        this.isSold = isSold;
        this.registration = registration;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return  "Car{" +
                ", Id: " + _id + '\'' +
                ", Rev: " + _rev + '\'' +
                ", Is Sold: " + isSold + '\'' +
                ", Registration: " + registration +
                '}';
    }
}
