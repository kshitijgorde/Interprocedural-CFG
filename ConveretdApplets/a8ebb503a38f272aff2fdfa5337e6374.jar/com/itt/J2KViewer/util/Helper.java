// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Component;
import java.net.URL;
import javax.swing.ImageIcon;

public class Helper
{
    private static Log log;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$Helper;
    
    public static ImageIcon loadImage(final String s, final String s2) {
        ImageIcon imageIcon = null;
        if (s == null || s.length() == 0) {
            Helper.log.error("Image name is null or empty: " + s);
        }
        else {
            final URL urlAsResource = getURLAsResource(s);
            if (urlAsResource != null) {
                imageIcon = new ImageIcon(urlAsResource, s2);
            }
            else {
                Helper.log.error("Error loading image named: " + s);
            }
        }
        return imageIcon;
    }
    
    public static URL getURLAsResource(final String s) {
        return ((Helper.class$com$itt$J2KViewer$util$Helper == null) ? (Helper.class$com$itt$J2KViewer$util$Helper = class$("com.itt.J2KViewer.util.Helper")) : Helper.class$com$itt$J2KViewer$util$Helper).getResource("/" + s);
    }
    
    public static FileSelection showFileDialog(final boolean b, final Component component, final String s, final String[] array, final String[] array2) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (s != null && s.length() > 0) {
            fileChooser.setCurrentDirectory(new File(s));
        }
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; ++i) {
                fileChooser.addChoosableFileFilter(new FileChooserFilter(array[i].toLowerCase(), array2[i]));
            }
        }
        int n;
        if (b) {
            n = fileChooser.showSaveDialog(component);
        }
        else {
            n = fileChooser.showOpenDialog(component);
        }
        if (n == 0) {
            return new FileSelection(fileChooser.getSelectedFile(), fileChooser.getFileFilter());
        }
        return null;
    }
    
    public static Date parseDate(final String s, final String s2) {
        Date parse = null;
        if (s2 != null && s != null) {
            try {
                parse = new SimpleDateFormat(s2).parse(s);
            }
            catch (ParseException ex) {
                Helper.log.error("Error parsing date: " + ex.getMessage() + " for pattern '" + s2 + "'");
            }
        }
        else {
            Helper.log.warn("Cannot parse date if either date or pattern are null");
        }
        return parse;
    }
    
    public static boolean isJPIPProtocol(final String s) {
        if (s != null) {
            final String upperCase = s.toUpperCase();
            return upperCase.startsWith("JPIK") || upperCase.startsWith("JPIP");
        }
        return false;
    }
    
    public static String stripLastChars(final String s, final char c) {
        String value = null;
        boolean b = false;
        if (s != null) {
            final char[] charArray = s.toCharArray();
            int length = charArray.length;
            while (--length >= 0) {
                if (charArray[length] == c) {
                    b = true;
                    break;
                }
            }
            if (b) {
                value = String.valueOf(charArray, 0, charArray.length - (charArray.length - length - 1));
            }
            else {
                if (Helper.log.isInfoEnabled()) {
                    Helper.log.info("Could not find specified char in String: " + c);
                }
                value = s;
            }
        }
        return value;
    }
    
    public static List createSelectedNodeList(final String s) {
        ArrayList<String> list = null;
        if (!isJPIPProtocol(s)) {
            list = new ArrayList<String>();
            for (int i = s.indexOf("/"), n = 0; i > -1; n = ++i, i = s.indexOf("/", n)) {
                list.add(s.substring(n, i));
            }
        }
        return list;
    }
    
    public static void main(final String[] array) {
        createSelectedNodeList("JPEG2000/DGdefense/11bit_pan/ramenskoye.jp2");
    }
    
    public static boolean validateChar(final char c, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = true;
        if (b4) {
            if (b3) {
                b4 = (Character.isDigit(c) || c == '\b' || (!b && c == '-') || (!b2 && c == '.'));
            }
            else {
                b4 = ((!b3 && ((c >= 'A' && c <= 'Z') || Character.isDigit(c))) || c == '\b' || (!b && c == '-') || (!b2 && c == '.'));
            }
        }
        return b4;
    }
    
    public static int divideAndRoundUp(final long n, final long n2) {
        if (n2 == 0L) {
            throw new IllegalArgumentException("Divisor cannot be zero in divideAndRoundUp()");
        }
        return BigDecimal.valueOf(n).divide(BigDecimal.valueOf(n2), 0).intValue();
    }
    
    public static Properties getEnvVars() throws Throwable {
        final Properties properties = new Properties();
        final Runtime runtime = Runtime.getRuntime();
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        Process process;
        if (lowerCase.indexOf("windows 9") > -1) {
            process = runtime.exec("command.com /c set");
        }
        else if (lowerCase.indexOf("nt") > -1 || lowerCase.indexOf("windows 2000") > -1 || lowerCase.indexOf("windows xp") > -1) {
            process = runtime.exec("cmd.exe /c set");
        }
        else {
            process = runtime.exec("env");
        }
        String line;
        while ((line = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine()) != null) {
            final int index = line.indexOf(61);
            properties.setProperty(line.substring(0, index), line.substring(index + 1));
        }
        return properties;
    }
    
    public static void sleep(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            Helper.log.debug("Sleep failed: " + ex.getMessage());
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        Helper.log = new Log((Helper.class$com$itt$J2KViewer$util$Helper == null) ? (Helper.class$com$itt$J2KViewer$util$Helper = class$("com.itt.J2KViewer.util.Helper")) : Helper.class$com$itt$J2KViewer$util$Helper);
    }
}
