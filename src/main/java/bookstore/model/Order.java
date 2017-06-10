package bookstore.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by rudeigerc on 2017/6/11.
 */
@Entity
public class Order {
    @Expose
    private int orderId;
    @Expose
    private String username;
    @Expose
    private String time;
    @Expose
    private String total;
    @Expose
    private int status;

    private Collection<OrderItem> orderItemsByOrderId;

    public Order() { }

    public Order(String username, String time, String total, int status) {
        this.username = username;
        this.time = time;
        this.total = total;
        this.status = status;
    }

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (status != order.status) return false;
        if (username != null ? !username.equals(order.username) : order.username != null) return false;
        if (time != null ? !time.equals(order.time) : order.time != null) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }


    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderItem> getOrderItemsByOrderId() {
        return orderItemsByOrderId;
    }

    public void setOrderItemsByOrderId(Collection<OrderItem> orderItemsByOrderId) {
        this.orderItemsByOrderId = orderItemsByOrderId;
    }
}
