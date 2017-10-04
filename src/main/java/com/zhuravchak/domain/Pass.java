package com.zhuravchak.domain;

import java.time.LocalDate;

/**
 * The Class Pass.
 */
public class Pass extends Entity implements Comparable<Pass>{

    /** The order id. */
    private long id;

    /** The user id. */
    private long tourId;

    /** The leaving date. */
    private LocalDate leavingDate;

    /** The quantity. */
    private int quantityAvailable;

    /** The price. */
    private double price;

    /** The hot. */
    private boolean hot;

    /** The discount for regular. */
    private int discountForRegular;

    /**
     * Instantiates a new user.
     */
    public Pass(){}

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the tour id.
     *
     * @return the tour id
     */
    public long getTourId() {
        return tourId;
    }

    /**
     * Sets the tour id.
     *
     * @param tourId the new tour id
     */
    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    /**
     * Gets the leaving date.
     *
     * @return the leaving date
     */
    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    /**
     * Sets the leaving date.
     *
     * @param leavingDate the new leaving date
     */
    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Sets the quantity.
     *
     * @param quantityAvailable the new quantity
     */
    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the  price.
     *
     * @param price the new total price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the hot.
     *
     * @return the hot
     */
    public boolean getHot() {
        return hot;
    }

    /**
     * Sets the hot.
     *
     * @param hot the new hot
     */
    public void setHot(boolean hot) {
        this.hot = hot;
    }

    /**
     * Gets the discount.
     *
     * @return the discount
     */
    public int getDiscountForRegular() {
        return discountForRegular;
    }

    /**
     * Sets the discount.
     *
     * @param discountForRegular the new discount
     */
    public void setDiscountForRegular(int discountForRegular) {
        this.discountForRegular = discountForRegular;
    }

    /* (non-Javadoc)
           * @see java.lang.Object
           */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pass pass = (Pass) o;

        if (id != pass.id) return false;
        if (tourId != pass.tourId) return false;
        if (quantityAvailable != pass.quantityAvailable) return false;
        if (Double.compare(pass.price, price) != 0) return false;
        if (hot != pass.hot) return false;
        if (discountForRegular != pass.discountForRegular) return false;
        return leavingDate.equals(pass.leavingDate);
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (tourId ^ (tourId >>> 32));
        result = 31 * result + leavingDate.hashCode();
        result = 31 * result + quantityAvailable;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hot ? 1 : 0);
        result = 31 * result + discountForRegular;
        return result;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public String toString() {
        return "Pass{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", leavingDate=" + leavingDate +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", hot=" + hot +
                ", discountForRegular=" + discountForRegular +
                "}\n";
    }

    /* (non-Javadoc)
       * @see java.lang.Comparable
       */
    @Override
    public int compareTo(Pass o) {
        return this.getLeavingDate().compareTo(o.getLeavingDate());
    }
}
