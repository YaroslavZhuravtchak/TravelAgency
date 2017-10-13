package com.zhuravchak.domain.enums;

/**
 * The Enum TransportType.
 */
public enum TransportType {
    
    /** The plane. */
    PLANE("Літак"),
    
    /** The train. */
    TRAIN("Поїзд"),
    
    /** The bus. */
    BUS("Автобус");

    /** The name. */
    private String name;
    
    /**
     * Instantiates a new transport.
     *
     * @param name the name
     */
    TransportType(String name){
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
