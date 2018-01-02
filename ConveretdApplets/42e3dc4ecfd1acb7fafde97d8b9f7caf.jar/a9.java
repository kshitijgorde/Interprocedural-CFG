import java.util.Hashtable;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;
import au.com.rocketdog.project.awc.applet.Main;
import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class a9
{
    public String a() {
        final StringBuffer sb = new StringBuffer();
        final Enumeration<Object> keys = ((Hashtable<Object, V>)System.getProperties()).keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            if (string.startsWith("user")) {
                sb.append(System.getProperty(string) + "_");
            }
        }
        return sb.toString();
    }
    
    public boolean b() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/servlet/user?u=" + n.b().y() + "&a=" + URLEncoder.encode(this.c(), "UTF-8") + "&p=" + URLEncoder.encode(this.a(), "UTF-8")).openStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (String.valueOf(line).equals("1")) {
                    bufferedReader.close();
                    return false;
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
        return true;
    }
    
    public String c() {
        final StringBuffer sb = new StringBuffer();
        final String[] list = new File(System.getProperty("user.home")).list();
        for (int i = 0; i < list.length; ++i) {
            if (list[i].startsWith("awc_")) {
                sb.append(list[i].substring(4, list[i].length()) + ",");
            }
        }
        return sb.toString();
    }
}
