package com.zhuravchak.domain;

/**
 * The Class City.
 */
public class City extends Entity implements Comparable<City> {

    /** The city ID. */
    private long id;

    /** The city's country id. */
    private long countryId;

    /** The city name. */
    private String name;

    /** The city nameUA. */
    private String nameUA;

    /** The city's country. */
    private Country country;

    /**
     * Instantiates a new city.
     */
    public City() {}

    /**
     * Gets the city id.
     *
     * @return the city ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the city ID.
     *
     * @param id the new city ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the city name.
     *
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the city name.
     *
     * @param name the new city name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the countryId.
     *
     * @return the countryId
     */
    public long getCountryId() {
        return countryId;
    }

    /**
     * Sets the countryId.
     *
     * @param countryId the new city's countryId
     */
    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the nameUA.
     *
     * @return the nameUA
     */
    public String getNameUA() {
        return nameUA;
    }

    /**
     * Sets the nameUA.
     *
     * @param nameUA the new city's nameUA
     */
    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }


    /**
     * Gets the country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new city's country refer
     */
    public void setCountry(Country country) {
        this.country = country;
    }


    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (countryId != city.countryId) return false;
        if (!name.equals(city.name)) return false;
        if (!nameUA.equals(city.nameUA)) return false;
        return country.equals(city.country);
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (countryId ^ (countryId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + nameUA.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", name='" + name + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public int compareTo(City o) {
        return this.getName().compareTo(o.getName());
    }
}
