// 
// Decompiled by Procyon v0.5.30
// 

package count;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Properties;

public final class CounterConf
{
    private ChatCounter pCounter;
    private Properties confProps;
    private static final String CONFIG_DIR = "";
    private static final String CONFIG_FILE = "count.conf";
    
    public CounterConf(final ChatCounter pCounter) {
        this.pCounter = pCounter;
        this.initVariables();
        this.loadFile(pCounter.getCodeBase(), "count.conf", this.confProps);
    }
    
    private boolean loadFile(final URL url, final String s, final Properties properties) {
        try {
            final URLConnection openConnection = new URL(url, s).openConnection();
            openConnection.setDoInput(true);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
            properties.load(bufferedInputStream);
            bufferedInputStream.close();
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println("warning 7438, count," + url);
            return false;
        }
        return true;
    }
    
    public String get(final String s) {
        if (this.confProps == null) {
            return null;
        }
        String parameter;
        try {
            parameter = this.pCounter.getParameter(s);
        }
        catch (Exception ex) {
            System.out.println("Err #877," + ex);
            parameter = null;
        }
        if (parameter != null) {
            return parameter;
        }
        return this.confProps.getProperty(s);
    }
    
    public String get(final String s, final String s2) {
        final String value = this.get(s);
        if (value == null) {
            return s2;
        }
        return value;
    }
    
    public boolean getBool(final String s, final boolean b) {
        final String value = this.get(s);
        if (value == null) {
            return b;
        }
        final String trim = value.trim();
        return isTrue(trim) || (!isFalse(trim) && b);
    }
    
    public int getInt(final String s, final int n) {
        final String value = this.get(s);
        if (value == null) {
            return n;
        }
        final String trim = value.trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            System.out.println("error E652. int=" + trim);
            int1 = n;
        }
        return int1;
    }
    
    private void initVariables() {
        this.confProps = new Properties();
    }
    
    public static boolean isTrue(String trim) {
        if (trim == null) {
            return false;
        }
        trim = trim.trim();
        return trim.equalsIgnoreCase("true") || trim.equalsIgnoreCase("yes") || trim.equalsIgnoreCase("enabled") || trim.equalsIgnoreCase("on");
    }
    
    public static boolean isFalse(String trim) {
        if (trim == null) {
            return false;
        }
        trim = trim.trim();
        return trim.equalsIgnoreCase("false") || trim.equalsIgnoreCase("off") || trim.equalsIgnoreCase("disabled") || trim.equalsIgnoreCase("no");
    }
}
