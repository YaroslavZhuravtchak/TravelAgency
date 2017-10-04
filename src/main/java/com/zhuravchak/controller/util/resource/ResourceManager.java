package com.zhuravchak.controller.util.resource;
import java.util.ResourceBundle;

/**
 * The Class ResourceManager.
 */
public class ResourceManager {

    /** The resource bundle. */
    private static ResourceBundle resourceBundle =  ResourceBundle.getBundle("text");;

    /**
     * Instantiates a new resource manager.
     */
    private ResourceManager() {}

    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}