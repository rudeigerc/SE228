package bookstore.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by rudeigerc on 2017/6/11.
 */
@Entity
public class OrderItem {
    @Expose
    private int id;
    @Expose
    private int orderId;
    @Expose
    private String isbn;
    @Expose
    private int quantity;
    @Expose
    private String price;
    private Order orderByOrderId;

    public OrderItem() { }
    public OrderItem(int orderId, String isbn, int quantity, String price) {
        this.orderId = orderId;
        this.isbn = isbn;
        this.quantity = quantity;
        this.price = price;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (orderId != orderItem.orderId) return false;
        if (quantity != orderItem.quantity) return false;
        if (isbn != null ? !isbn.equals(orderItem.isbn) : orderItem.isbn != null) return false;
        if (price != null ? !price.equals(orderItem.price) : orderItem.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

}
