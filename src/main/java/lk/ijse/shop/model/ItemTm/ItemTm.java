package lk.ijse.shop.model.ItemTm;

import lombok.*;


public class ItemTm {
    private String id;
    private String itemName;
    private String qtyOnHand;
    private String details;

    public ItemTm(String id, String itemName, String qtyOnHand, String details) {
        this.id = id;
        this.itemName = itemName;
        this.qtyOnHand = qtyOnHand;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
