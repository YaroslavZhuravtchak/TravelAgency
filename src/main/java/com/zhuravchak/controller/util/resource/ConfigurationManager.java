package com.zhuravchak.controller.util.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {

    private final static Locale locale = new Locale("en", "US");

    /** The resource bundle. */
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");

    /**
     * Instantiates a new configuration manager.
     */
    private ConfigurationManager() { }

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
