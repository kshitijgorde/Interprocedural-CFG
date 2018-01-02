// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.pchat.sc.WindowUtil;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class ClientUtil
{
    private static final String DISALLOWED = "\\/:*?|'\"!$%^&()[]{}<>;+`~,";
    
    public static String fixUser(String s, final Config config) {
        if (s == null) {
            return null;
        }
        String s2;
        if (false) {
            s2 = "\\/:*?|'\"!$%^&()[]{}<>;+`~,";
        }
        else {
            s2 = "\\/:*?|'\"!$%^&()[]{}<>;+`~, ";
        }
        final char c = '_';
        for (int length = s2.length(), i = 0; i < length; ++i) {
            s = s.replace(s2.charAt(i), c);
        }
        if (s.length() > 64) {
            s = s.substring(0, 63);
        }
        return s;
    }
    
    public static int getPort(final Config config) {
        return selectPort(config, "Net.Port", 8642);
    }
    
    public static int getSecPort(final Config config) {
        return selectPort(config, "Net.SecPort", -1);
    }
    
    private static int selectPort(final Config config, final String s, final int n) {
        final String value = config.get(s);
        if (value == null) {
            return n;
        }
        final Vector vector = new Vector<String>(8);
        final StringTokenizer stringTokenizer = new StringTokenizer(value, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim.length() == 0) {
                continue;
            }
            vector.addElement(trim);
        }
        final int size = vector.size();
        if (size == 0) {
            return n;
        }
        int nextInt = new Random(System.currentTimeMillis()).nextInt();
        if (nextInt < 0) {
            nextInt = 0 - nextInt;
        }
        return WindowUtil.parseInt(vector.elementAt(nextInt % size), n);
    }
    
    public static String getStamp(final Config config, String value) {
        final Date date = new Date();
        if (value == null) {
            value = "MMM dd'-'HH:mm";
            value = config.get("Val.Stamp.Format", value);
        }
        return "(" + new SimpleDateFormat(value).format(date) + ") ";
    }
    
    public static String encodeSearch(final String s, final String s2) {
        return s + " (" + s2 + ")";
    }
    
    public static String decodeUser(final String s) {
        final int index = s.indexOf(" (");
        if (index < 0) {
            return null;
        }
        return s.substring(0, index);
    }
    
    public static String decodeRoom(String substring) {
        final int index = substring.indexOf(" (");
        if (index < 0) {
            return null;
        }
        substring = substring.substring(index + 2);
        if (substring.length() < 2) {
            return null;
        }
        return substring.substring(0, substring.length() - 1);
    }
    
    public static void doze(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public static int randomNum() {
        int nextInt = new Random(System.currentTimeMillis()).nextInt();
        if (nextInt < 0) {
            nextInt = 0 - nextInt;
        }
        int n = nextInt % 10000;
        if (n == 0 || n == 1) {
            n += 2;
        }
        return n;
    }
}
