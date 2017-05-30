package bookstore.model;

import javax.persistence.*;

/**
 * Created by rudeigerc on 2017/5/26.
 */
@Entity
@Table(name = "orderItem", schema = "bookstore")
public class OrderItem {
    private int id;
    private int orderId;
    private String isbn;
    private int quantity;
    private String price;

    public OrderItem() { }
    public OrderItem(int orderId, String isbn, int quantity, String price) {
        this.orderId = orderId;
        this.isbn = isbn;
        this.quantity = quantity;
        this.price = price;
    }

    @Id
    @Column(name="id")
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

        if (orderId != orderItem.orderId) return false;
        if (quantity != orderItem.quantity) return false;
        if (isbn != null ? !isbn.equals(orderItem.isbn) : orderItem.isbn != null) return false;
        if (price != null ? !price.equals(orderItem.price) : orderItem.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
