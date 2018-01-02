// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import logging.LogHolder;
import logging.LogType;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

public final class JAPMessages
{
    private static ResourceBundle ms_resourceBundle;
    private static ResourceBundle ms_defaultResourceBundle;
    private static Locale ms_locale;
    private static final Locale SYSTEM_LOCALE;
    private static Hashtable ms_cachedMessages;
    private static final Object SYNC;
    static /* synthetic */ Class class$java$util$PropertyResourceBundle;
    
    public static Locale getSystemLocale() {
        return JAPMessages.SYSTEM_LOCALE;
    }
    
    public static boolean init(final String s) {
        return init(Locale.getDefault(), s);
    }
    
    private static String getBundleLocalisedFilename(final String s, Locale default1) {
        final String s2 = "_";
        if (s == null) {
            return null;
        }
        if (default1 == null) {
            default1 = Locale.getDefault();
        }
        String s3;
        if (default1 == null || default1.getLanguage().trim().length() == 0) {
            s3 = s2 + "en";
        }
        else {
            s3 = s2 + default1.getLanguage();
        }
        return s + (s3 + ".properties");
    }
    
    public static synchronized boolean init(Locale default1, final String s) {
        InputStream loadResourceAsStream = null;
        Exception ex = null;
        if (JAPMessages.ms_locale != null) {
            Locale.setDefault(Locale.ENGLISH);
        }
        try {
            if (JAPMessages.ms_defaultResourceBundle == null) {
                JAPMessages.ms_defaultResourceBundle = ResourceBundle.getBundle(s, Locale.ENGLISH);
            }
        }
        catch (Exception ex2) {
            LogHolder.log(0, LogType.GUI, ex2);
            return false;
        }
        JAPMessages.ms_resourceBundle = JAPMessages.ms_defaultResourceBundle;
        try {
            loadResourceAsStream = ResourceLoader.loadResourceAsStream(getBundleLocalisedFilename(s, default1), true);
            if (loadResourceAsStream != null) {
                JAPMessages.ms_resourceBundle = new PropertyResourceBundle(loadResourceAsStream);
            }
        }
        catch (Exception ex3) {
            ex = ex3;
        }
        Util.closeStream(loadResourceAsStream);
        Label_0160: {
            if (loadResourceAsStream == null) {
                try {
                    JAPMessages.ms_resourceBundle = ResourceBundle.getBundle(s, default1);
                }
                catch (Exception ex4) {
                    try {
                        if (default1 == null || !default1.equals(Locale.getDefault())) {
                            default1 = Locale.getDefault();
                            JAPMessages.ms_resourceBundle = ResourceBundle.getBundle(s, default1);
                            break Label_0160;
                        }
                        if (ex != null) {
                            throw ex;
                        }
                        throw ex4;
                    }
                    catch (Exception ex5) {
                        LogHolder.log(3, LogType.FILE, ex5);
                    }
                }
            }
        }
        JAPMessages.ms_cachedMessages = new Hashtable();
        JAPMessages.ms_locale = default1;
        return true;
    }
    
    public static boolean isInitialised() {
        return JAPMessages.ms_locale != null;
    }
    
    public static Locale getLocale() {
        if (JAPMessages.ms_locale == null) {
            return Locale.getDefault();
        }
        return JAPMessages.ms_locale;
    }
    
    public static void setLocale(final Locale ms_locale) {
        if (ms_locale != null) {
            JAPMessages.ms_locale = ms_locale;
        }
    }
    
    public static String getString(final String s) {
        if (JAPMessages.ms_cachedMessages == null) {
            return s;
        }
        String s2 = JAPMessages.ms_cachedMessages.get(s);
        if (s2 != null) {
            return s2;
        }
        try {
            s2 = JAPMessages.ms_resourceBundle.getString(s);
            if (s2 == null || s2.trim().length() == 0) {
                throw new MissingResourceException("Resource is empty", ((JAPMessages.class$java$util$PropertyResourceBundle == null) ? (JAPMessages.class$java$util$PropertyResourceBundle = class$("java.util.PropertyResourceBundle")) : JAPMessages.class$java$util$PropertyResourceBundle).getName(), s);
            }
        }
        catch (Exception ex) {
            try {
                if (JAPMessages.ms_resourceBundle != JAPMessages.ms_defaultResourceBundle) {
                    s2 = JAPMessages.ms_defaultResourceBundle.getString(s);
                    LogHolder.log(7, LogType.GUI, "Could not load messsage string '" + s + "' for the locale '" + JAPMessages.ms_locale.getLanguage() + "'. Using default resource bundle.", true);
                }
            }
            catch (Exception ex2) {
                s2 = null;
            }
            if (s2 == null || s2.trim().length() == 0) {
                LogHolder.log(7, LogType.GUI, "Could not load messsage string: " + s, true);
                s2 = s;
            }
        }
        JAPMessages.ms_cachedMessages.put(s, s2);
        return s2;
    }
    
    public static String getString(final String s, final Object[] array) {
        return MessageFormat.format(getString(s), array);
    }
    
    public static String getString(final String s, final Object o) {
        return getString(s, Util.toArray(o));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        JAPMessages.ms_resourceBundle = null;
        JAPMessages.ms_defaultResourceBundle = null;
        SYNC = new Object();
        SYSTEM_LOCALE = Locale.getDefault();
    }
}
