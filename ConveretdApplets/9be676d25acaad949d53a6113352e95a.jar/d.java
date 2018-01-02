import java.util.Enumeration;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public static String a(final String s) throws Exception {
        return a(s, null);
    }
    
    public static String a(final String s, final Hashtable hashtable) throws Exception {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        if (hashtable != null) {
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                openConnection.setRequestProperty("Cookie", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("=").append(hashtable.get(s2)))));
            }
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        final StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        bufferedReader.close();
        return sb.toString();
    }
}
