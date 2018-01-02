// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class I18n
{
    private static ArrayList<ResourceBundle> bundles;
    private static ArrayList<String> bundleNames;
    private static Locale locale;
    private static String defaultPropertiesFileName;
    
    public static void setLocale(final Locale locale) {
        setLocale(locale, true);
    }
    
    public static synchronized void setLocale(final Locale locale, final boolean b) {
        I18n.locale = locale;
        if (b) {
            I18n.bundles.clear();
            final ArrayList<String> bundleNames = I18n.bundleNames;
            I18n.bundleNames = new ArrayList<String>();
            for (int i = 0; i < bundleNames.size(); ++i) {
                load(bundleNames.get(i));
            }
        }
    }
    
    public static Locale getLocale() {
        if (I18n.locale == null) {
            return Locale.getDefault();
        }
        return I18n.locale;
    }
    
    public static synchronized boolean addFile(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < I18n.bundleNames.size(); ++i) {
            if (I18n.bundleNames.get(i).equals(s)) {
                return I18n.bundles.get(i) != null;
            }
        }
        if (I18n.bundles.size() == 0 && !s.equals(I18n.defaultPropertiesFileName)) {
            load(I18n.defaultPropertiesFileName);
        }
        return load(s) != null;
    }
    
    private static synchronized ResourceBundle load(final String s) {
        if (s == null) {
            return null;
        }
        ResourceBundle resourceBundle;
        try {
            if (I18n.locale == null) {
                resourceBundle = ResourceBundle.getBundle(s);
            }
            else {
                resourceBundle = ResourceBundle.getBundle(s, I18n.locale);
            }
        }
        catch (MissingResourceException ex) {
            resourceBundle = null;
        }
        I18n.bundles.add(resourceBundle);
        I18n.bundleNames.add(s);
        return resourceBundle;
    }
    
    private static String lookup(final String s) {
        if (s == null) {
            return null;
        }
        if (I18n.bundles.size() == 0) {
            load(I18n.defaultPropertiesFileName);
        }
        for (int i = I18n.bundles.size() - 1; i >= 0; --i) {
            final ResourceBundle resourceBundle = I18n.bundles.get(i);
            if (resourceBundle != null) {
                try {
                    return resourceBundle.getString(s);
                }
                catch (MissingResourceException ex) {}
            }
        }
        return null;
    }
    
    public static String tr(final String s, final Object... array) {
        String lookup = lookup(s);
        if (lookup == null) {
            lookup = s;
        }
        if (array == null || array.length == 0) {
            return lookup;
        }
        return MessageFormat.format(lookup, array);
    }
    
    public static String trIfFound(final String s, final Object... array) {
        final String lookup = lookup(s);
        if (lookup == null) {
            return null;
        }
        if (array == null || array.length == 0) {
            return lookup;
        }
        return MessageFormat.format(lookup, array);
    }
    
    static {
        I18n.bundles = new ArrayList<ResourceBundle>();
        I18n.bundleNames = new ArrayList<String>();
        I18n.defaultPropertiesFileName = "edu.hws.eck.umb.resources.strings";
    }
}
