package com.zhuravchak.domain.enums;

/**
 * The Enum TourType.
 */
public enum TourType {
    VACATION("Відпочинок"),
    TRIP("Подорож"),
    SHOPPING("Шоппінг");

    /** The name. */
    private String name;

    /**
     * Instantiates a new transport.
     *
     * @param name the name
     */
    TourType(String name){
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