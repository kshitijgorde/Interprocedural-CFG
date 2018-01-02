// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

public class SafePropertyAccessor
{
    public static boolean getBoolean(final String property) {
        try {
            return Boolean.getBoolean(property);
        }
        catch (SecurityException se) {
            return false;
        }
    }
    
    public static boolean getBoolean(final String property, final boolean defValue) {
        try {
            if (System.getProperty(property) != null) {
                return Boolean.getBoolean(property);
            }
            return defValue;
        }
        catch (SecurityException se) {
            return defValue;
        }
    }
    
    public static String getProperty(final String property) {
        return getProperty(property, null);
    }
    
    public static String getProperty(final String property, final String defValue) {
        try {
            return System.getProperty(property, defValue);
        }
        catch (SecurityException se) {
            return defValue;
        }
    }
    
    public static int getInt(final String property) {
        return getInt(property, 0);
    }
    
    public static int getInt(final String property, final int defValue) {
        try {
            return Integer.parseInt(System.getProperty(property, String.valueOf(defValue)));
        }
        catch (SecurityException se) {
            return defValue;
        }
    }
    
    public static boolean isSecurityProtected(final String property) {
        try {
            System.getProperty(property);
            return false;
        }
        catch (SecurityException se) {
            return true;
        }
    }
}
