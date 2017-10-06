package com.zhuravchak.domain;

import java.util.List;

/**
 * The Class Country.
 */
public class Country extends Entity {

    /** The country ID. */
    private long id;

    /** The country name. */
    private String name;

    /** The country nameUA. */
    private String nameUA;

    /** The country's cities. */
    private List<City> cities;

    /**
     * Instantiates a new country.
     */
    public Country() {
    }

    /**
     * Gets the country id.
     *
     * @return the country ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the country id.
     *
     * @param id the new country id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the country name.
     *
     * @return the country name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the country name.
     *
     * @param name the new country name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country nameUA.
     *
     * @return the country nameUA
     */
    public String getNameUA() {
        return nameUA;
    }

    /**
     * Gets the country's cities.
     *
     * @return the country's cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Sets the country's cities.
     *
     * @param cities the new countr's cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Sets the country nameUA.
     *
     * @param nameUA the new country name
     */
    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        if (!name.equals(country.name)) return false;
        if (!nameUA.equals(country.nameUA)) return false;
        return cities != null ? cities.equals(country.cities) : country.cities == null;
    }

    /* (non-Javadoc)
           * @see java.lang.Object
           */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + nameUA.hashCode();
        result = 31 * result + cities.hashCode();
        return result;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", cities=" + cities +
                '}';
    }
}
