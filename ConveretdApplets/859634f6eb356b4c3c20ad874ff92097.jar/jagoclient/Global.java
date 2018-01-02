// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.util.Locale;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import rene.util.list.ListClass;
import jagoclient.igs.MessageFilter;
import java.awt.Frame;
import java.awt.Component;

public class Global extends rene.gui.Global
{
    public static Component C;
    public static String Dir;
    public static String Home;
    public static Frame F;
    public static MessageFilter MF;
    public static ListClass PartnerList;
    public static ListClass OpenPartnerList;
    public static boolean UseUrl;
    public static URL Url;
    public static boolean Busy;
    public static Color gray;
    public static Font SansSerif;
    public static Font Monospaced;
    public static Font BigMonospaced;
    public static Font BoardFont;
    public static Hashtable WindowList;
    public static String Version;
    public static int Silent;
    
    public static void loadmessagefilter() {
        Global.MF = new MessageFilter();
    }
    
    public static void setcomponent(final Component c) {
        Global.C = c;
    }
    
    public static Image createImage(final int n, final int n2) {
        return Global.C.createImage(n, n2);
    }
    
    public static InputStream getDataStream(final String s) {
        try {
            if (useurl()) {
                return new URL(url(), s).openStream();
            }
            return new FileInputStream(s);
        }
        catch (Exception ex) {
            return new Object().getClass().getResourceAsStream("/" + s);
        }
    }
    
    public static BufferedReader getStream(final String s) {
        return new BufferedReader(new InputStreamReader(getDataStream(s)));
    }
    
    public static void readparameter(final String s) {
        rene.gui.Global.loadProperties(getDataStream(s));
        String s2 = rene.gui.Global.getParameter("language", "");
        if (!s2.equals("")) {
            String substring = "";
            if (s2.length() > 3 && s2.charAt(2) == '_') {
                substring = s2.substring(3);
                s2 = s2.substring(0, 2);
            }
            Locale.setDefault(new Locale(s2, substring));
            rene.gui.Global.initBundle("JagoResource");
        }
        Global.gray = getColor("globalgray", new Color(220, 220, 220));
    }
    
    public static void writeparamter(final String s) {
        if (Global.UseUrl) {
            return;
        }
        if (Global.Home.equals("")) {
            rene.gui.Global.saveProperties("Jago Properties", "go.cfg");
        }
        else {
            rene.gui.Global.saveProperties("Jago Properties", String.valueOf(home()) + "go.cfg");
        }
        if (Global.MF != null) {
            Global.MF.save();
        }
    }
    
    public static String dir() {
        return Global.Dir;
    }
    
    public static void dir(final String s) {
        if (Go.isApplet) {
            Global.Dir = String.valueOf(s) + "\\";
            return;
        }
        Global.Dir = String.valueOf(s) + System.getProperty("file.separator");
    }
    
    public static String home() {
        return Global.Home;
    }
    
    public static void home(final String s) {
        if (Go.isApplet) {
            Global.Home = String.valueOf(s) + "\\";
            return;
        }
        Global.Home = String.valueOf(s) + System.getProperty("file.separator");
    }
    
    public static Color getColor(final String s, final int n, final int n2, final int n3) {
        return rene.gui.Global.getParameter(s, n, n2, n3);
    }
    
    public static Color getColor(final String s, final Color color) {
        return rene.gui.Global.getParameter(s, color);
    }
    
    public static void setColor(final String s, final Color color) {
        rene.gui.Global.setParameter(s, color);
    }
    
    public static void frame(final Frame f) {
        Global.F = f;
    }
    
    public static Frame frame() {
        if (Global.F == null) {
            Global.F = new Frame();
        }
        return Global.F;
    }
    
    public static int blocks(final String s) {
        return Global.MF.blocks(s);
    }
    
    public static boolean posfilter(final String s) {
        return Global.MF.posfilter(s);
    }
    
    public static String getFunctionKey(final int n) {
        int n2 = 0;
        switch (n) {
            case 112: {
                n2 = 1;
                break;
            }
            case 113: {
                n2 = 2;
                break;
            }
            case 114: {
                n2 = 3;
                break;
            }
            case 115: {
                n2 = 4;
                break;
            }
            case 116: {
                n2 = 5;
                break;
            }
            case 117: {
                n2 = 6;
                break;
            }
            case 118: {
                n2 = 7;
                break;
            }
            case 119: {
                n2 = 8;
                break;
            }
            case 120: {
                n2 = 9;
                break;
            }
            case 121: {
                n2 = 10;
                break;
            }
        }
        if (n2 == 0) {
            return "";
        }
        return rene.gui.Global.getParameter("f" + n2, "");
    }
    
    public static URL url() {
        return Global.Url;
    }
    
    public static void url(final URL url) {
        Global.Url = url;
        Global.UseUrl = true;
    }
    
    public static boolean useurl() {
        return Global.UseUrl;
    }
    
    public static void createfonts() {
        Global.SansSerif = createfont("sansserif", "SansSerif", "ssfontsize");
        Global.Monospaced = createfont("monospaced", "Monospaced", "msfontsize");
        Global.BigMonospaced = createfont("bigmonospaced", "BoldMonospaced", "bigmsfontsize");
        Global.BoardFont = createfont("boardfontname", "SansSerif", "boardfontsize");
    }
    
    static Font createfont(String parameter, final String s, final String s2) {
        parameter = rene.gui.Global.getParameter(parameter, s);
        if (parameter.startsWith("Bold")) {
            return new Font(parameter.substring(4), 1, rene.gui.Global.getParameter(s2, 11));
        }
        if (parameter.startsWith("Italic")) {
            return new Font(parameter.substring(5), 2, rene.gui.Global.getParameter(s2, 11));
        }
        return new Font(parameter, 0, rene.gui.Global.getParameter(s2, 11));
    }
    
    public static void setwindow(final Window window, final String s, int n, int n2) {
        int parameter = rene.gui.Global.getParameter(String.valueOf(s) + "xpos", 0);
        int parameter2 = rene.gui.Global.getParameter(String.valueOf(s) + "ypos", 0);
        n = rene.gui.Global.getParameter(String.valueOf(s) + "width", n);
        n2 = rene.gui.Global.getParameter(String.valueOf(s) + "height", n2);
        final Dimension screenSize = window.getToolkit().getScreenSize();
        if (n > screenSize.width) {
            n = screenSize.width;
        }
        if (n2 > screenSize.height) {
            n2 = screenSize.height;
        }
        if (parameter + n > screenSize.width) {
            parameter = screenSize.width - n;
        }
        if (parameter < 0) {
            parameter = 0;
        }
        if (parameter2 + n2 > screenSize.height) {
            parameter2 = screenSize.height - n2;
        }
        if (parameter2 < 0) {
            parameter2 = 0;
        }
        window.pack();
        window.setBounds(parameter, parameter2, n, n2);
    }
    
    public static void setpacked(final Window window, final String s, int n, int n2) {
        int parameter = rene.gui.Global.getParameter(String.valueOf(s) + "xpos", 0);
        int parameter2 = rene.gui.Global.getParameter(String.valueOf(s) + "ypos", 0);
        n = rene.gui.Global.getParameter(String.valueOf(s) + "width", n);
        n2 = rene.gui.Global.getParameter(String.valueOf(s) + "height", n2);
        final Dimension screenSize = window.getToolkit().getScreenSize();
        if (n > screenSize.width) {
            n = screenSize.width;
        }
        if (n2 > screenSize.height) {
            n2 = screenSize.height;
        }
        if (parameter + n > screenSize.width) {
            parameter = screenSize.width - n;
        }
        if (parameter < 0) {
            parameter = 0;
        }
        if (parameter2 + n2 > screenSize.height) {
            parameter2 = screenSize.height - n2;
        }
        if (parameter2 < 0) {
            parameter2 = 0;
        }
        if (rene.gui.Global.getParameter("pack", true)) {
            window.pack();
            window.setLocation(parameter, parameter2);
            return;
        }
        window.setBounds(parameter, parameter2, n, n2);
    }
    
    public static void setwindow(final Dialog dialog, final String s, int n, int n2, final Frame frame) {
        int n3 = frame.getLocation().x + frame.getSize().width / 2 - dialog.getSize().width / 2;
        int n4 = frame.getLocation().y + frame.getSize().height / 2 - dialog.getSize().height / 2;
        n = rene.gui.Global.getParameter(String.valueOf(s) + "width", n);
        n2 = rene.gui.Global.getParameter(String.valueOf(s) + "height", n2);
        final Dimension screenSize = dialog.getToolkit().getScreenSize();
        if (n > screenSize.width) {
            n = screenSize.width;
        }
        if (n2 > screenSize.height) {
            n2 = screenSize.height;
        }
        if (n3 + n > screenSize.width) {
            n3 = screenSize.width - n;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 + n2 > screenSize.height) {
            n4 = screenSize.height - n2;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        dialog.setBounds(n3, n4, n, n2);
    }
    
    public static void setpacked(final Dialog dialog, final String s, int n, int n2, final Frame frame) {
        int n3 = frame.getLocation().x + frame.getSize().width / 2 - dialog.getSize().width / 2;
        int n4 = frame.getLocation().y + frame.getSize().height / 2 - dialog.getSize().height / 2;
        n = rene.gui.Global.getParameter(String.valueOf(s) + "width", n);
        n2 = rene.gui.Global.getParameter(String.valueOf(s) + "height", n2);
        final Dimension screenSize = dialog.getToolkit().getScreenSize();
        if (n > screenSize.width) {
            n = screenSize.width;
        }
        if (n2 > screenSize.height) {
            n2 = screenSize.height;
        }
        if (n3 + n > screenSize.width) {
            n3 = screenSize.width - n;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 + n2 > screenSize.height) {
            n4 = screenSize.height - n2;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (rene.gui.Global.getParameter("pack", true)) {
            dialog.pack();
        }
        else {
            dialog.setSize(n, n2);
        }
        dialog.setLocation(n3, n4);
    }
    
    public static void setpacked(final Frame frame, final String s, int n, int n2, final Frame frame2) {
        int n3 = frame2.getLocation().x + frame2.getSize().width / 2 - frame.getSize().width / 2;
        int n4 = frame2.getLocation().y + frame2.getSize().height / 2 - frame.getSize().height / 2;
        n = rene.gui.Global.getParameter(String.valueOf(s) + "width", n);
        n2 = rene.gui.Global.getParameter(String.valueOf(s) + "height", n2);
        final Dimension screenSize = frame.getToolkit().getScreenSize();
        if (n > screenSize.width) {
            n = screenSize.width;
        }
        if (n2 > screenSize.height) {
            n2 = screenSize.height;
        }
        if (n3 + n > screenSize.width) {
            n3 = screenSize.width - n;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 + n2 > screenSize.height) {
            n4 = screenSize.height - n2;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (rene.gui.Global.getParameter("pack", true)) {
            frame.pack();
        }
        else {
            frame.setSize(n, n2);
        }
        frame.setLocation(n3, n4);
    }
    
    public static void notewindow(final Component component, final String s) {
        rene.gui.Global.setParameter(String.valueOf(s) + "width", component.getSize().width);
        rene.gui.Global.setParameter(String.valueOf(s) + "height", component.getSize().height);
        rene.gui.Global.setParameter(String.valueOf(s) + "xpos", component.getLocation().x);
        rene.gui.Global.setParameter(String.valueOf(s) + "ypos", component.getLocation().y);
    }
    
    public static String resourceString(String replace) {
        replace = replace.replace(' ', '_');
        String s = rene.gui.Global.name(replace, "???");
        if (s.equals("???")) {
            s = replace.replace('_', ' ');
            if (s.endsWith(" n")) {
                s = String.valueOf(s.substring(0, s.length() - 2)) + "\n";
            }
        }
        return s;
    }
    
    static {
        Global.MF = null;
        Global.PartnerList = null;
        Global.OpenPartnerList = null;
        Global.Busy = true;
        Global.gray = Color.gray;
        Global.Version = "Version 3.57";
        Global.WindowList = new Hashtable();
        Global.Dir = "";
        Global.Home = "";
        rene.gui.Global.B = null;
    }
}
