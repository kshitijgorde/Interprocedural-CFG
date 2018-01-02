// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.resources;

import javax.swing.ImageIcon;
import java.util.Enumeration;
import java.util.Properties;
import java.util.MissingResourceException;
import java.io.IOException;
import com.bullionvault.chart.c.h;
import java.util.PropertyResourceBundle;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class Resources
{
    private static Locale a;
    private static ResourceBundle b;
    private static NumberFormat c;
    private static NumberFormat d;
    private static Class e;
    
    public static synchronized void a(final String ex) {
        Resources.a = new Locale((String)ex, "");
        try {
            Resources.b = new PropertyResourceBundle(((Resources.e == null) ? (Resources.e = d("com.bullionvault.chart.resources.Resources")) : Resources.e).getResourceAsStream("messages_" + ((String)ex).substring(0, 2) + ".properties"));
        }
        catch (Exception ex) {
            try {
                Resources.b = new PropertyResourceBundle(((Resources.e == null) ? (Resources.e = d("com.bullionvault.chart.resources.Resources")) : Resources.e).getResourceAsStream("messages_en.properties"));
                h.e("Fallen back to default locale");
            }
            catch (IOException ex2) {
                throw new RuntimeException("Utter resource failure", ex);
            }
        }
        (Resources.c = NumberFormat.getInstance(Resources.a)).setMaximumFractionDigits(0);
        Resources.c.setMinimumFractionDigits(0);
        (Resources.d = NumberFormat.getInstance(Resources.a)).setMaximumFractionDigits(2);
        Resources.d.setMinimumFractionDigits(2);
        h.e("Loaded locale for: " + b("locale.name"));
    }
    
    public static synchronized Locale a() {
        return Resources.a;
    }
    
    public static synchronized String b(final String s) {
        try {
            return Resources.b.getString(s);
        }
        catch (MissingResourceException ex) {
            return null;
        }
    }
    
    public static synchronized String c(final String s) {
        final Properties properties = new Properties();
        try {
            properties.load(((Resources.e == null) ? (Resources.e = d("com.bullionvault.chart.resources.Resources")) : Resources.e).getResourceAsStream("messages_en.properties"));
        }
        catch (IOException ex) {
            throw new RuntimeException("Utter message properties failure", ex);
        }
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s2;
            final String b;
            if ((b = b(s2 = (String)propertyNames.nextElement())) != null && b.equals(s)) {
                return s2;
            }
        }
        return null;
    }
    
    public static synchronized NumberFormat b() {
        return Resources.c;
    }
    
    public static synchronized NumberFormat c() {
        return Resources.d;
    }
    
    public static ImageIcon d() {
        return new ImageIcon(((Resources.e == null) ? (Resources.e = d("com.bullionvault.chart.resources.Resources")) : Resources.e).getResource("icon.png"));
    }
    
    private static Class d(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
