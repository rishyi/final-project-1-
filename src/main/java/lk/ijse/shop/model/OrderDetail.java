package lk.ijse.shop.model;

public class OrderDetail {

    private String o_id;
    private String i_id;
    private String detials;

    public OrderDetail(String o_id, String i_id, String detials) {
        this.o_id = o_id;
        this.i_id = i_id;
        this.detials = detials;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getDetials() {
        return detials;
    }

    public void setDetials(String detials) {
        this.detials = detials;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "o_id='" + o_id + '\'' +
                ", i_id='" + i_id + '\'' +
                ", detials='" + detials + '\'' +
                '}';
    }
}
