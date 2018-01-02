// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components.smileys;

import java.util.StringTokenizer;
import java.awt.Component;
import irc.managers.Resources;
import javax.swing.ImageIcon;
import java.io.InputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Locale;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.MediaTracker;

public class SmileyLoader
{
    private static Object obj;
    private static Class classloader;
    private static MediaTracker tracker;
    private static Hashtable smileys;
    private static String smileylist;
    public static Vector keyS;
    
    public static void destroy() {
        SmileyLoader.obj = null;
        SmileyLoader.classloader = null;
        SmileyLoader.tracker = null;
    }
    
    public static void free() {
        SmileyLoader.obj = null;
        SmileyLoader.classloader = null;
        SmileyLoader.tracker = null;
        SmileyLoader.smileys.clear();
        SmileyLoader.smileylist = null;
        SmileyLoader.keyS.removeAllElements();
    }
    
    private static Properties getBundle(final String s, final Locale locale) throws MissingResourceException {
        final Properties properties = new Properties();
        final String string = s + "_" + locale.getLanguage() + ".properties";
        try {
            final InputStream resourceAsStream = SmileyLoader.classloader.getResourceAsStream(string);
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                resourceAsStream.close();
            }
            if (properties == null) {
                throw new MissingResourceException("Cannot load properties file", "Resource", string);
            }
        }
        catch (IOException ex) {
            throw new MissingResourceException("Cannot load properties file", "Resource", string);
        }
        return properties;
    }
    
    public static ImageIcon getSmiley(final String s) {
        return new ImageIcon(Resources.getSmileyImage(SmileyLoader.smileys.get(s)));
    }
    
    public static void init(final Object obj) {
        SmileyLoader.obj = obj;
        SmileyLoader.classloader = ((obj instanceof Class) ? ((Class)obj) : obj.getClass());
        SmileyLoader.tracker = new MediaTracker((Component)obj);
        SmileyLoader.smileylist = Resources.smileys_Ressources.get("symbol.liste");
        final StringTokenizer stringTokenizer = new StringTokenizer(SmileyLoader.smileylist, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(Resources.smileys_Ressources.get("symbol." + nextToken + ".emoticons"));
                while (stringTokenizer2.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    SmileyLoader.smileys.put(nextToken2, Resources.smileys_Ressources.get("symbol." + nextToken + ".char"));
                    SmileyLoader.keyS.addElement(nextToken2);
                }
            }
            catch (Exception ex) {
                System.out.println(nextToken);
            }
        }
    }
    
    public static Hashtable Smileykeys() {
        return SmileyLoader.smileys;
    }
    
    static {
        SmileyLoader.smileys = new Hashtable();
        SmileyLoader.keyS = new Vector();
    }
}
