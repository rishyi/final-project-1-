package lk.ijse.shop.model;

public class Payement {

    private String id;
    private String details;
    private String date;
    private String price;
    private String o_id;

    public Payement(String id, String details, String date, String price, String o_id) {
        this.id = id;
        this.details = details;
        this.date = date;
        this.price = price;
        this.o_id = o_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    @Override
    public String toString() {
        return "Payement{" +
                "id='" + id + '\'' +
                ", details='" + details + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", o_id='" + o_id + '\'' +
                '}';
    }
}
