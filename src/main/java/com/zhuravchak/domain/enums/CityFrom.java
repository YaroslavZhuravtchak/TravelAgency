package com.zhuravchak.domain.enums;

/**
 * The Enum CityFrom.
 */
public enum CityFrom {
    KYIV("Київ"),
    KHARKIV("Харків"),
    LVIV("Львів");

    /** The name. */
    private String name;

    /**
     * Instantiates a new transport.
     *
     * @param name the name
     */
    CityFrom(String name){
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
