package lk.ijse.shop.model;

import java.sql.Date;

public class Order {
        private String id;
        private String details;
        private Date date;
        private String c_id;

        @Override
        public String toString() {
                return "Order{" +
                        "id='" + id + '\'' +
                        ", details='" + details + '\'' +
                        ", date=" + date +
                        ", c_id='" + c_id + '\'' +
                        '}';
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

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public String getC_id() {
                return c_id;
        }

        public void setC_id(String c_id) {
                this.c_id = c_id;
        }

        public Order(String id, String details, Date date, String c_id) {
                this.id = id;
                this.details = details;
                this.date = date;
                this.c_id = c_id;
        }
}
