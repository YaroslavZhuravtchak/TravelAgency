package com.zhuravchak.domain;

import com.zhuravchak.domain.enums.CityFrom;
import com.zhuravchak.domain.enums.TourType;
import com.zhuravchak.domain.enums.TransportType;

import java.util.List;

/**
 * The Class Tour.
 */
public  class Tour extends Entity implements Comparable<Tour>{

    /** The id. */
    protected long id;

    /** The city from witch people go to the tour. */
    private CityFrom cityFrom;

    /** The tour type. */
    private TourType tourType;

    /** The transport type. */
    private TransportType transportType;

    /** The name. */
    private String name;

    /** The nameUA. */
    private String nameUA;

    /** The description. */
    private String description;

    /** The descriptionUA. */
    private String descriptionUA;

    /** The duration. */
    private int duration;

    /** The path image. */
    private String pathImage;

    /** The passes. */
    private List<Pass> passes;

    /** The cities. */
    private List<City> cities;

    /**
     * Instantiates a new tour.
     */
    public Tour() {}

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
     * Gets the cityFrom.
     *
     * @return the cityFrom
     */
    public CityFrom getCityFrom() {
        return cityFrom;
    }

    /**
     * Sets the cityFrom.
     *
     * @param cityFrom the new cityFrom
     */
    public void setCityFrom(CityFrom cityFrom) {
        this.cityFrom = cityFrom;
    }

    /**
     * Gets the tourType.
     *
     * @return the tourType
     */
    public TourType getTourType() {
        return tourType;
    }

    /**
     * Sets the tourType.
     *
     * @param tourType the new tourType
     */
    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    /**
     * Gets the transportType.
     *
     * @return the transportType
     */
    public TransportType getTransportType() {
        return transportType;
    }

    /**
     * Sets the transportType.
     *
     * @param transportType the new transportType
     */
    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets the name.
     *
     * @param nameUA the new nameUA
     */
    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the descriptionUA.
     *
     * @return the descriptionUA
     */
    public String getDescriptionUA() {
        return descriptionUA;
    }

    /**
     * Sets the descriptionUA.
     *
     * @param descriptionUA the new descriptionUA
     */
    public void setDescriptionUA(String descriptionUA) {
        this.descriptionUA = descriptionUA;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the new duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the pathImage.
     *
     * @return the pathImage
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Sets the pathImage.
     *
     * @param pathImage the new pathImage
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    /**
     * Gets the passes.
     *
     * @return the passes
     */
    public List<Pass> getPasses() {
        return passes;
    }

    /**
     * Sets the passes.
     *
     * @param passes the new passes
     */
    public void setPasses(List<Pass> passes) {
        this.passes = passes;
    }

    /**
     * Gets the cities.
     *
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Sets the cities.
     *
     * @param cities the new cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /* (non-Javadoc)
           * @see java.lang.Object
           */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (id != tour.id) return false;
        if (duration != tour.duration) return false;
        if (cityFrom != tour.cityFrom) return false;
        if (tourType != tour.tourType) return false;
        if (transportType != tour.transportType) return false;
        if (name != null ? !name.equals(tour.name) : tour.name != null) return false;
        if (nameUA != null ? !nameUA.equals(tour.nameUA) : tour.nameUA != null) return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null) return false;
        if (descriptionUA != null ? !descriptionUA.equals(tour.descriptionUA) : tour.descriptionUA != null)
            return false;
        if (pathImage != null ? !pathImage.equals(tour.pathImage) : tour.pathImage != null) return false;
        if (passes != null ? !passes.equals(tour.passes) : tour.passes != null) return false;
        return cities != null ? cities.equals(tour.cities) : tour.cities == null;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cityFrom != null ? cityFrom.hashCode() : 0);
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (transportType != null ? transportType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameUA != null ? nameUA.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (descriptionUA != null ? descriptionUA.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (pathImage != null ? pathImage.hashCode() : 0);
        result = 31 * result + (passes != null ? passes.hashCode() : 0);
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }

    /* (non-Javadoc)
       * @see java.lang.Object
       */
    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", cityFrom=" + cityFrom +
                ", tourType=" + tourType +
                ", transportType=" + transportType +
                ", name='" + name + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", description='" + description + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", duration=" + duration +
                ", pathImage='" + pathImage + '\'' +
                ", passes=" + passes +
                ", cities=" + cities +
                '}';
    }

    /* (non-Javadoc)
           * @see java.lang.Comparable
           */
    @Override
    public int compareTo(Tour o) {
        if(this.getPasses().get(0) != null && o.getPasses().get(0) != null) {
            return this.getPasses().get(0).compareTo(o.getPasses().get(0));
        } else {
            return 0;
        }
    }
}
