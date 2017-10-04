package com.zhuravchak.controller.util.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Class MessageManager.
 */
public class MessageManager {

    private final static Locale locale = new Locale("en", "US");

    /** The resource bundle. */
    private  static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("messages");

    /**
     * Instantiates a new message manager.
     */
    private MessageManager() { }

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
