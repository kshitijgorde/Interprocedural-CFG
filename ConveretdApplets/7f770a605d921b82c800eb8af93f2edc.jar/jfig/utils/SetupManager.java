// 
// Decompiled by Procyon v0.5.30
// 

package jfig.utils;

import java.util.Hashtable;
import java.io.PrintStream;
import java.util.Enumeration;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.FileInputStream;
import java.awt.Color;
import java.util.Properties;

public class SetupManager
{
    static SetupManager _setupManager;
    static Properties properties;
    static Properties globals;
    static Properties userprops;
    static Properties locals;
    static boolean debug;
    private static /* synthetic */ Class class$Ljfig$utils$SetupManager;
    
    public static SetupManager getSetupManager() {
        if (SetupManager._setupManager == null) {
            SetupManager._setupManager = new SetupManager();
        }
        return SetupManager._setupManager;
    }
    
    public static Properties getProperties() {
        return SetupManager.properties;
    }
    
    public static String getProperty(final String s, final String s2) {
        return SetupManager.properties.getProperty(s, s2);
    }
    
    public static String getProperty(final String s) {
        return SetupManager.properties.getProperty(s);
    }
    
    public static int getInteger(final String s, final int n) {
        final String property = getProperty(s);
        if (property == null) {
            return n;
        }
        try {
            return Integer.parseInt(property);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static boolean getBoolean(final String s, final boolean b) {
        final String property = getProperty(s);
        if (property == null) {
            return b;
        }
        return property.trim().toLowerCase().equals("true") || (!property.trim().toLowerCase().equals("false") && b);
    }
    
    public static double getDouble(final String s, final double n) {
        final String property = getProperty(s);
        try {
            return Double.valueOf(property.trim());
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static Color getColor(final String s, final Color color) {
        final String property = getProperty(s);
        if (property == null) {
            return color;
        }
        try {
            return new Color(Integer.decode(property));
        }
        catch (Exception ex) {
            message("-E- Illegal Color String '" + property + "' for key: " + s);
            return color;
        }
    }
    
    public static void setProperty(final String s, final String s2) {
        if (s != null && s2 != null) {
            ((Hashtable<String, String>)SetupManager.properties).put(s, s2);
        }
    }
    
    public static void loadAllProperties(final String s) {
        loadGlobalProperties(s);
        loadUserProperties(s);
        loadLocalProperties(s);
    }
    
    public static void loadGlobalProperties(String string) {
        if (SetupManager.debug) {
            message("-I- loading global props: " + string);
        }
        getSetupManager();
        SetupManager.globals = new Properties();
        try {
            if (!string.startsWith("/")) {
                string = "/" + string;
            }
            SetupManager.globals.load(((SetupManager.class$Ljfig$utils$SetupManager != null) ? SetupManager.class$Ljfig$utils$SetupManager : (SetupManager.class$Ljfig$utils$SetupManager = class$("jfig.utils.SetupManager"))).getResourceAsStream(string));
        }
        catch (Exception ex) {
            ExceptionTracer.message("-W- Couldn't load global properties from '" + string + "'");
        }
        merge(SetupManager.properties, SetupManager.globals);
    }
    
    public static void loadUserProperties(final String s) {
        getSetupManager();
        SetupManager.userprops = new Properties();
        String string = null;
        try {
            string = SetupManager.properties.getProperty("user.home") + SetupManager.properties.getProperty("file.separator") + s;
            if (SetupManager.debug) {
                message("-I- loading user props: " + string);
            }
            final FileInputStream fileInputStream = new FileInputStream(string);
            SetupManager.userprops.load(fileInputStream);
            fileInputStream.close();
        }
        catch (Exception ex) {
            ExceptionTracer.message("-W- Couldn't load user properties from '" + string + "'");
        }
        merge(SetupManager.properties, SetupManager.userprops);
    }
    
    public static void loadLocalProperties(final String s) {
        getSetupManager();
        String string = null;
        try {
            string = SetupManager.properties.getProperty("user.dir") + SetupManager.properties.getProperty("file.separator") + s;
            if (SetupManager.debug) {
                message("-I- loading local props: " + string);
            }
            loadLocalProperties(new FileInputStream(string));
        }
        catch (Exception ex) {
            ExceptionTracer.message("-W- Couldn't load local properties from stream '" + string + "'");
        }
    }
    
    public static void loadLocalProperties(final InputStream inputStream) {
        SetupManager.locals = new Properties();
        try {
            SetupManager.locals.load(inputStream);
            inputStream.close();
        }
        catch (Exception ex) {
            ExceptionTracer.message("-W- Couldn't load local properties from stream '" + inputStream + "'");
        }
        merge(SetupManager.properties, SetupManager.locals);
    }
    
    public static void loadURLProperties(final String s) {
        getSetupManager();
        SetupManager.locals = new Properties();
        try {
            final URL url = new URL(s);
            url.openConnection();
            final InputStream openStream = url.openStream();
            SetupManager.locals.load(openStream);
            openStream.close();
        }
        catch (Exception ex) {
            ExceptionTracer.message("-W- got: " + ex);
            ExceptionTracer.message("-W- Couldn't load properties from '" + s + "'");
        }
        merge(SetupManager.properties, SetupManager.locals);
    }
    
    public static void save(final OutputStream outputStream, final String s) {
        try {
            SetupManager.properties.store(outputStream, s);
        }
        catch (Exception ex) {
            message("-E- Failed to save the properties: " + ex);
            ex.printStackTrace();
        }
    }
    
    public static void saveUserProperties(final String s, final String s2) {
        String s3 = null;
        try {
            s3 = s2;
            final FileOutputStream fileOutputStream = new FileOutputStream(s3);
            final Properties properties = new Properties();
            merge(properties, SetupManager.userprops);
            merge(properties, SetupManager.locals);
            properties.store(fileOutputStream, s);
            fileOutputStream.flush();
            fileOutputStream.close();
            message("-I- Saved setup to file '" + s3 + "'");
        }
        catch (Exception ex) {
            if (SetupManager.debug) {
                message("-W- Couldn't load local properties from '" + s3 + "'");
                message("-W- loadLocalProperites: " + ex);
            }
        }
    }
    
    public static void saveLocalProperties(final String s, final String s2) {
        String string = null;
        try {
            string = getProperty("user.dir") + getProperty("file.separator") + s2;
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final Properties properties = new Properties();
            merge(properties, SetupManager.userprops);
            merge(properties, SetupManager.locals);
            properties.store(fileOutputStream, s);
            fileOutputStream.flush();
            fileOutputStream.close();
            message("-I- Saved setup to file '" + string + "'");
        }
        catch (Exception ex) {
            message("-E- Couldn't load local properties from '" + string + "'");
            message("-E- loadLocalProperites: " + ex);
        }
    }
    
    public static void merge(final Properties properties, final Properties properties2) {
        final Enumeration<?> propertyNames = properties2.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            ((Hashtable<String, String>)properties).put(s, properties2.getProperty(s));
        }
    }
    
    public static void list(final PrintStream printStream) {
        printStream.println(getSetupManager().toString() + "[");
        final Enumeration<?> propertyNames = SetupManager.properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            printStream.println(s + " " + getProperty(s));
        }
        printStream.println("]");
    }
    
    public static void message(final String s) {
        System.out.println(s);
    }
    
    public String toString() {
        return "jfig.gui.SetupManager";
    }
    
    public static void main(final String[] array) {
        message("SetupManager selftest...");
        getSetupManager();
        loadAllProperties("jfig.cnf");
        list(System.out);
        message("Saving user Properties...");
        saveUserProperties("jfig setup variables", "hugo.conf");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private SetupManager() {
        try {
            final SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkPropertiesAccess();
            }
            SetupManager.properties = System.getProperties();
            if (SetupManager.debug) {
                message("-I- loaded system properties.");
            }
        }
        catch (SecurityException ex) {
            SetupManager.properties = new Properties();
            final String[] array = { "java.version", "java.vendor", "java.vendor.url", "java.class.version", "os.name", "os.arch", "or.version", "file.separator", "path.separator", "line.separator" };
            for (int i = 0; i < array.length; ++i) {
                try {
                    ((Hashtable<String, String>)SetupManager.properties).put(array[i], System.getProperty(array[i]));
                }
                catch (SecurityException ex2) {}
            }
            if (SetupManager.debug) {
                message("-I- created empty system properties.");
            }
        }
        catch (Throwable t) {
            SetupManager.properties = new Properties();
            if (SetupManager.debug) {
                message("-I- created empty system properties.");
            }
        }
    }
    
    static {
        SetupManager._setupManager = null;
        SetupManager.debug = false;
        getSetupManager();
    }
}
