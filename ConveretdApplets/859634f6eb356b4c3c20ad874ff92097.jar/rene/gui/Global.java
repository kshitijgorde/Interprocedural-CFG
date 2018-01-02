// 
// Decompiled by Procyon v0.5.30
// 

package rene.gui;

import java.util.Hashtable;
import rene.dialogs.Warning;
import rene.util.parser.StringParser;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.awt.SystemColor;
import java.awt.Frame;
import java.util.Properties;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.Font;

public class Global
{
    public static Font NormalFont;
    public static Font FixedFont;
    public static Font BoldFont;
    public static Color Background;
    public static Color ControlBackground;
    protected static ResourceBundle B;
    static Properties P;
    static String ConfigName;
    static Frame F;
    public static String AppletClipboard;
    public static boolean IsApplet;
    
    public static void makeFonts() {
        if (haveParameter("normalfont.name")) {
            Global.NormalFont = createfont("normalfont", "SansSerif", 12, false);
        }
        else {
            Global.NormalFont = null;
        }
        Global.FixedFont = createfont("fixedfont", "Monospaced", 12, false);
        Global.BoldFont = createfont("fixedfont", "Monospaced", 12, true);
    }
    
    public static Font createfont(final String s, final String s2, final int n, final boolean b) {
        final String parameter = getParameter(String.valueOf(s) + ".name", s2);
        final String parameter2 = getParameter(String.valueOf(s) + ".mode", "plain");
        if (b || parameter2.equals("bold")) {
            return new Font(parameter, 1, getParameter(String.valueOf(s) + ".size", n));
        }
        if (parameter2.equals("italic")) {
            return new Font(parameter, 2, getParameter(String.valueOf(s) + ".size", n));
        }
        return new Font(parameter, 0, getParameter(String.valueOf(s) + ".size", n));
    }
    
    public static void makeColors() {
        if (haveParameter("color.background")) {
            Global.Background = getParameter("color.background", Color.gray.brighter());
        }
        else {
            Global.Background = SystemColor.window;
        }
        if (haveParameter("color.control")) {
            Global.ControlBackground = getParameter("color.control", Color.gray.brighter());
            return;
        }
        Global.ControlBackground = SystemColor.control;
    }
    
    public static Enumeration names() {
        if (Global.B != null) {
            return Global.B.getKeys();
        }
        return null;
    }
    
    public static String name(final String s, final String s2) {
        if (Global.B == null) {
            return s2;
        }
        String string;
        try {
            string = Global.B.getString(s);
        }
        catch (Exception ex) {
            string = s2;
        }
        return string;
    }
    
    public static String name(final String s) {
        return name(s, s.substring(s.lastIndexOf(".") + 1));
    }
    
    public static void initBundle(final String s) {
        try {
            Global.B = ResourceBundle.getBundle(s);
        }
        catch (Exception ex) {
            Global.B = null;
        }
    }
    
    public static Enumeration properties() {
        return Global.P.keys();
    }
    
    public static void loadProperties(final InputStream inputStream) {
        try {
            (Global.P = new Properties()).load(inputStream);
            inputStream.close();
        }
        catch (Exception ex) {
            Global.P = new Properties();
        }
    }
    
    public static boolean loadPropertiesFromResource(final String configName) {
        try {
            final InputStream resourceAsStream = new Object().getClass().getResourceAsStream(configName);
            (Global.P = new Properties()).load(resourceAsStream);
            resourceAsStream.close();
        }
        catch (Exception ex) {
            Global.P = new Properties();
            return false;
        }
        Global.ConfigName = configName;
        return true;
    }
    
    public static boolean loadProperties(final String configName) {
        try {
            final FileInputStream fileInputStream = new FileInputStream(configName);
            (Global.P = new Properties()).load(fileInputStream);
            fileInputStream.close();
        }
        catch (Exception ex) {
            Global.P = new Properties();
            return false;
        }
        Global.ConfigName = configName;
        return true;
    }
    
    public static void loadPropertiesInHome(final String s) {
        try {
            final Properties properties = System.getProperties();
            loadProperties(String.valueOf(properties.getProperty("user.home")) + properties.getProperty("file.separator") + s);
        }
        catch (Exception ex) {}
    }
    
    public static void saveProperties(final String s) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(Global.ConfigName);
            Global.P.save(fileOutputStream, s);
            fileOutputStream.close();
        }
        catch (Exception ex) {}
    }
    
    public static void saveProperties(final String s, final String configName) {
        Global.ConfigName = configName;
        saveProperties(s);
    }
    
    public static void setParameter(final String s, final boolean b) {
        if (Global.P == null) {
            return;
        }
        if (b) {
            ((Hashtable<String, String>)Global.P).put(s, "true");
            return;
        }
        ((Hashtable<String, String>)Global.P).put(s, "false");
    }
    
    public static boolean getParameter(final String s, final boolean b) {
        try {
            final String property = Global.P.getProperty(s);
            return property.equals("true") || (!property.equals("false") && b);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public static String getParameter(final String s, final String s2) {
        String s3 = s2;
        try {
            s3 = Global.P.getProperty(s);
        }
        catch (Exception ex) {}
        if (s3 != null) {
            if (s3.startsWith("$")) {
                s3 = s3.substring(1);
            }
            return s3;
        }
        return s2;
    }
    
    public static void setParameter(final String s, String string) {
        if (Global.P == null) {
            return;
        }
        if (string.length() > 0 && Character.isSpaceChar(string.charAt(0))) {
            string = "$" + string;
        }
        ((Hashtable<String, String>)Global.P).put(s, string);
    }
    
    public static int getParameter(final String s, final int n) {
        try {
            return Integer.parseInt(getParameter(s, ""));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static void setParameter(final String s, final int n) {
        setParameter(s, String.valueOf(n));
    }
    
    public static Color getParameter(final String s, final Color color) {
        final String parameter = getParameter(s, "");
        if (parameter.equals("")) {
            return color;
        }
        final StringParser stringParser = new StringParser(parameter);
        stringParser.replace(',', ' ');
        return new Color(stringParser.parseint(), stringParser.parseint(), stringParser.parseint());
    }
    
    public static Color getParameter(final String s, int parseint, int parseint2, int parseint3) {
        final String parameter = getParameter(s, "");
        if (parameter.equals("")) {
            return new Color(parseint, parseint2, parseint3);
        }
        final StringParser stringParser = new StringParser(parameter);
        stringParser.replace(',', ' ');
        parseint = stringParser.parseint();
        parseint2 = stringParser.parseint();
        parseint3 = stringParser.parseint();
        return new Color(parseint, parseint2, parseint3);
    }
    
    public static void setParameter(final String s, final Color color) {
        setParameter(s, color.getRed() + "," + color.getGreen() + "," + color.getBlue());
    }
    
    public static void removeParameter(final String s) {
        Global.P.remove(s);
    }
    
    public static void removeAllParameters(final String s) {
        final Enumeration<String> keys = ((Hashtable<String, V>)Global.P).keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s2.startsWith(s)) {
                Global.P.remove(s2);
            }
        }
    }
    
    public static boolean haveParameter(final String s) {
        try {
            if (Global.P.getProperty(s) == null) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static void warning(final String s) {
        if (Global.F == null) {
            Global.F = new Frame();
        }
        final Warning warning = new Warning(Global.F, s, name("warning"), false);
        warning.center();
        warning.show();
    }
    
    public static void setApplet(final boolean isApplet) {
        Global.IsApplet = isApplet;
    }
    
    public static boolean isApplet() {
        return Global.IsApplet;
    }
    
    static {
        Global.NormalFont = null;
        Global.FixedFont = null;
        Global.BoldFont = null;
        makeFonts();
        Global.Background = null;
        Global.ControlBackground = null;
        makeColors();
        Global.P = new Properties();
        Global.F = null;
        Global.AppletClipboard = null;
    }
}
