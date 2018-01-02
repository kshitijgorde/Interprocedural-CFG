// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Level;
import java.util.Properties;

public class OptionConverter
{
    static String DELIM_START;
    static char DELIM_STOP;
    static int DELIM_START_LEN;
    static int DELIM_STOP_LEN;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$apache$log4j$Level;
    static /* synthetic */ Class class$org$apache$log4j$spi$Configurator;
    
    public static String[] concatanateArrays(final String[] array, final String[] array2) {
        final String[] array3 = new String[array.length + array2.length];
        System.arraycopy(array, 0, array3, 0, array.length);
        System.arraycopy(array2, 0, array3, array.length, array2.length);
        return array3;
    }
    
    public static String convertSpecialChars(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            char c = s.charAt(i++);
            if (c == '\\') {
                c = s.charAt(i++);
                if (c == 'n') {
                    c = '\n';
                }
                else if (c == 'r') {
                    c = '\r';
                }
                else if (c == 't') {
                    c = '\t';
                }
                else if (c == 'f') {
                    c = '\f';
                }
                else if (c == '\b') {
                    c = '\b';
                }
                else if (c == '\"') {
                    c = '\"';
                }
                else if (c == '\'') {
                    c = '\'';
                }
                else if (c == '\\') {
                    c = '\\';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String getSystemProperty(final String s, final String s2) {
        try {
            return System.getProperty(s, s2);
        }
        catch (Throwable t) {
            LogLog.debug("Was not allowed to read system property \"" + s + "\".");
            return s2;
        }
    }
    
    public static Object instantiateByKey(final Properties properties, final String s, final Class clazz, final Object o) {
        final String andSubst = findAndSubst(s, properties);
        if (andSubst == null) {
            LogLog.error("Could not find value for key " + s);
            return o;
        }
        return instantiateByClassName(andSubst.trim(), clazz, o);
    }
    
    public static boolean toBoolean(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        final String trim = s.trim();
        return "true".equalsIgnoreCase(trim) || (!"false".equalsIgnoreCase(trim) && b);
    }
    
    public static int toInt(final String s, final int n) {
        if (s != null) {
            final String trim = s.trim();
            try {
                return Integer.valueOf(trim);
            }
            catch (NumberFormatException ex) {
                LogLog.error("[" + trim + "] is not in proper int form.");
                ex.printStackTrace();
            }
        }
        return n;
    }
    
    public static Level toLevel(final String s, final Level level) {
        if (s == null) {
            return level;
        }
        final int index = s.indexOf(35);
        if (index == -1) {
            if ("NULL".equalsIgnoreCase(s)) {
                return null;
            }
            return Level.toLevel(s, level);
        }
        else {
            Level level2 = level;
            final String substring = s.substring(index + 1);
            final String substring2 = s.substring(0, index);
            if ("NULL".equalsIgnoreCase(substring2)) {
                return null;
            }
            LogLog.debug("toLevel:class=[" + substring + "]" + ":pri=[" + substring2 + "]");
            try {
                level2 = (Level)Loader.loadClass(substring).getMethod("toLevel", (OptionConverter.class$java$lang$String == null) ? (OptionConverter.class$java$lang$String = class$("java.lang.String")) : OptionConverter.class$java$lang$String, (OptionConverter.class$org$apache$log4j$Level == null) ? (OptionConverter.class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : OptionConverter.class$org$apache$log4j$Level).invoke(null, substring2, level);
            }
            catch (ClassNotFoundException ex6) {
                LogLog.warn("custom level class [" + substring + "] not found.");
            }
            catch (NoSuchMethodException ex) {
                LogLog.warn("custom level class [" + substring + "]" + " does not have a constructor which takes one string parameter", ex);
            }
            catch (InvocationTargetException ex2) {
                LogLog.warn("custom level class [" + substring + "]" + " could not be instantiated", ex2);
            }
            catch (ClassCastException ex3) {
                LogLog.warn("class [" + substring + "] is not a subclass of org.apache.log4j.Level", ex3);
            }
            catch (IllegalAccessException ex4) {
                LogLog.warn("class [" + substring + "] cannot be instantiated due to access restrictions", ex4);
            }
            catch (Exception ex5) {
                LogLog.warn("class [" + substring + "], level [" + substring2 + "] conversion failed.", ex5);
            }
            return level2;
        }
    }
    
    public static long toFileSize(final String s, final long n) {
        if (s == null) {
            return n;
        }
        String s2 = s.trim().toUpperCase();
        long n2 = 1L;
        final int index;
        if ((index = s2.indexOf("KB")) != -1) {
            n2 = 1024L;
            s2 = s2.substring(0, index);
        }
        else {
            final int index2;
            if ((index2 = s2.indexOf("MB")) != -1) {
                n2 = 1048576L;
                s2 = s2.substring(0, index2);
            }
            else {
                final int index3;
                if ((index3 = s2.indexOf("GB")) != -1) {
                    n2 = 1073741824L;
                    s2 = s2.substring(0, index3);
                }
            }
        }
        if (s2 != null) {
            try {
                return Long.valueOf(s2) * n2;
            }
            catch (NumberFormatException ex) {
                LogLog.error("[" + s2 + "] is not in proper int form.");
                LogLog.error("[" + s + "] not in expected format.", ex);
            }
        }
        return n;
    }
    
    public static String findAndSubst(final String s, final Properties properties) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return null;
        }
        try {
            return substVars(property, properties);
        }
        catch (IllegalArgumentException ex) {
            LogLog.error("Bad option value [" + property + "].", ex);
            return property;
        }
    }
    
    public static Object instantiateByClassName(final String s, final Class clazz, final Object o) {
        if (s != null) {
            try {
                final Class loadClass = Loader.loadClass(s);
                if (!clazz.isAssignableFrom(loadClass)) {
                    LogLog.error("A \"" + s + "\" object is not assignable to a \"" + clazz.getName() + "\" variable.");
                    LogLog.error("The class \"" + clazz.getName() + "\" was loaded by ");
                    LogLog.error("[" + clazz.getClassLoader() + "] whereas object of type ");
                    LogLog.error("\"" + loadClass.getName() + "\" was loaded by [" + loadClass.getClassLoader() + "].");
                    return o;
                }
                return loadClass.newInstance();
            }
            catch (Exception ex) {
                LogLog.error("Could not instantiate class [" + s + "].", ex);
            }
        }
        return o;
    }
    
    public static String substVars(final String s, final Properties properties) throws IllegalArgumentException {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        while (true) {
            final int index = s.indexOf(OptionConverter.DELIM_START, n);
            if (index == -1) {
                if (n == 0) {
                    return s;
                }
                sb.append(s.substring(n, s.length()));
                return sb.toString();
            }
            else {
                sb.append(s.substring(n, index));
                final int index2 = s.indexOf(OptionConverter.DELIM_STOP, index);
                if (index2 == -1) {
                    throw new IllegalArgumentException('\"' + s + "\" has no closing brace. Opening brace at position " + index + '.');
                }
                final String substring = s.substring(index + OptionConverter.DELIM_START_LEN, index2);
                String s2 = getSystemProperty(substring, null);
                if (s2 == null && properties != null) {
                    s2 = properties.getProperty(substring);
                }
                if (s2 != null) {
                    sb.append(substVars(s2, properties));
                }
                n = index2 + OptionConverter.DELIM_STOP_LEN;
            }
        }
    }
    
    public static void selectAndConfigure(final URL url, String s, final LoggerRepository loggerRepository) {
        final String file = url.getFile();
        if (s == null && file != null && file.endsWith(".xml")) {
            s = "org.apache.log4j.xml.DOMConfigurator";
        }
        Configurator configurator;
        if (s != null) {
            LogLog.debug("Preferred configurator class: " + s);
            configurator = (Configurator)instantiateByClassName(s, (OptionConverter.class$org$apache$log4j$spi$Configurator == null) ? (OptionConverter.class$org$apache$log4j$spi$Configurator = class$("org.apache.log4j.spi.Configurator")) : OptionConverter.class$org$apache$log4j$spi$Configurator, null);
            if (configurator == null) {
                LogLog.error("Could not instantiate configurator [" + s + "].");
                return;
            }
        }
        else {
            configurator = new PropertyConfigurator();
        }
        configurator.doConfigure(url, loggerRepository);
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
        OptionConverter.DELIM_START = "${";
        OptionConverter.DELIM_STOP = '}';
        OptionConverter.DELIM_START_LEN = 2;
        OptionConverter.DELIM_STOP_LEN = 1;
    }
}
