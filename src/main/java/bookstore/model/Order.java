package bookstore.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rudeigerc on 2017/5/21.
 */
@Entity
@Table(name = "order", schema = "bookstore")
public class Order {
    private String orderId;
    private String username;
    private Timestamp time;
    private String total;

    @Id
    @Column(name = "order_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "total")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
