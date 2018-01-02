// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import matt.web.MattApplet;
import java.util.Properties;

public class MattProperties extends Properties
{
    private static MattProperties _instance;
    private static PropertiesLoader propertiesLoader;
    
    public static float getFloat(final String key) {
        return Float.parseFloat(getString(key));
    }
    
    public static boolean getBoolean(final String key) {
        return Boolean.parseBoolean(getString(key));
    }
    
    public static String getString(final String key) {
        return "" + ((Hashtable<K, Object>)instance()).get(key);
    }
    
    public static void setString(final String key, final String value) {
        instance().setProperty(key, value);
        instance();
        save();
    }
    
    public static MattProperties instance() {
        if (MattProperties._instance == null) {
            MattProperties._instance = new MattProperties();
            (MattProperties.propertiesLoader = new PropertiesLoader()).load();
            MattProperties.propertiesLoader.start();
        }
        return MattProperties._instance;
    }
    
    public static MattProperties instance(final boolean applet) {
        if (MattProperties._instance == null) {
            MattProperties._instance = new MattProperties();
            if (!applet) {
                (MattProperties.propertiesLoader = new PropertiesLoader()).start();
            }
            else {
                try {
                    System.out.println("Loading...");
                    String url = "" + MattApplet._instance.getDocumentBase();
                    url = url.substring(0, url.lastIndexOf("/")) + "/";
                    url += "matt.properties";
                    System.out.println("URL: " + url);
                    MattProperties._instance.load(new URL(url).openStream());
                    System.out.println("Loaded...");
                }
                catch (Exception ex) {
                    Logger.log(ex.toString());
                }
            }
        }
        return MattProperties._instance;
    }
    
    public static void save() {
        if (!getBoolean("applet")) {
            try {
                MattProperties._instance.store(new FileOutputStream("matt.properties"), null);
            }
            catch (Exception e) {
                Logger.log("Problem storing properties");
                e.printStackTrace();
            }
        }
    }
    
    static {
        MattProperties._instance = null;
        MattProperties.propertiesLoader = null;
    }
}
