package com.zhuravchak.domain;

import java.time.LocalDate;

/**
 * The Class Order.
 */
public class Order extends Entity {

    /** The id. */
    private long id;

    /** The user id. */
    private long userId;

    /** The pass id. */
    private long passId;

    /** The quantity. */
    private int quantity;

    /** The total price. */
    private double totalPrice;

    /** The order date. */
    private LocalDate orderDate;

    /**
     * Gets the order id.
     *
     * @return the order id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the order id.
     *
     * @param id the new order id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the pass id.
     *
     * @return the pass id
     */
    public long getPassId() {
        return passId;
    }

    /**
     * Sets the pass id.
     *
     * @param passId the new tour id
     */
    public void setPassId(long passId) {
        this.passId = passId;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price.
     *
     * @return the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice the new total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the order date.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     *
     * @param orderDate the new order date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /* (non-Javadoc)
           * @see java.lang.Object
           */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (passId != order.passId) return false;
        if (quantity != order.quantity) return false;
        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        return orderDate != null ? orderDate.equals(order.orderDate) : order.orderDate == null;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (passId ^ (passId >>> 32));
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", passId=" + passId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}


