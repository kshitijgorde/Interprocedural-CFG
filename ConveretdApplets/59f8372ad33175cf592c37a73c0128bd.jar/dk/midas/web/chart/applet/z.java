// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import netscape.javascript.JSObject;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.Applet;

public class z
{
    private Applet a;
    private final String do = "HostCheck.asp?Site=";
    private String if;
    
    public z(final Applet a) {
        this.a = null;
        this.if = null;
        this.a = a;
    }
    
    public int int() {
        int i = 0;
        int n = 0;
        if (((AppletChart)this.a).gf) {
            return 2;
        }
        while (i < 3) {
            try {
                final URL a = this.a();
                if (a == null) {
                    return 0;
                }
                final String trim = a.getHost().trim();
                n = 0;
                ++n;
                final URL url = new URL(this.a.getCodeBase(), "HostCheck.asp?Site=" + trim);
                ++n;
                final InputStream openStream = url.openStream();
                ++n;
                if (openStream == null) {
                    System.out.println("Can't find HostCheck.asp?Site=");
                    return 0;
                }
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
                ++n;
                String s = bufferedReader.readLine();
                String trim2 = "0";
                while (s != null) {
                    trim2 = s.trim();
                    s = bufferedReader.readLine();
                }
                ++n;
                return Integer.parseInt(trim2);
            }
            catch (Exception ex) {
                System.out.println("Host exception " + n + " : " + ex);
                ++i;
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException ex2) {}
                continue;
            }
            break;
        }
        this.if = "Invalid document.";
        return 0;
    }
    
    public boolean do() {
        if (((AppletChart)this.a).gf) {
            return true;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.a.getCodeBase(), "HostCheck.asp?Site=").openStream()));
            final URL a = this.a();
            if (a == null) {
                return false;
            }
            final String trim = a.getHost().trim();
            final String string = "www." + trim;
            for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
                if (trim.equalsIgnoreCase(s.trim()) || string.equalsIgnoreCase(s.trim())) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Host exception " + ex);
        }
        this.if = "Invalid document.";
        return false;
    }
    
    public URL a() {
        try {
            if (((AppletChart)this.a).gf) {
                return new URL("http://www.reksoft.ru/index.html?a=12345&b=0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
            }
            return new URL(((String)((JSObject)((JSObject)JSObject.getWindow(this.a).getMember("document")).getMember("location")).getMember("href")).trim());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return this.a.getDocumentBase();
        }
    }
    
    public final String for() {
        return this.if;
    }
    
    public String if() {
        String s = this.a().toString();
        if (s.startsWith("http://")) {
            s = s.substring(7, s.length());
        }
        if (s.startsWith("https://")) {
            s = s.substring(8, s.length());
        }
        if (s.startsWith("www.")) {
            s = s.substring(4, s.length());
        }
        s.indexOf("/");
        final String a = this.a(s);
        final int lastIndex = a.lastIndexOf("/");
        String s2;
        if (lastIndex != -1) {
            s2 = a.substring(0, lastIndex + 1);
        }
        else {
            s2 = a;
        }
        while (s2.endsWith("/")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        int index;
        while ((index = s2.indexOf("./")) != -1) {
            s2 = s2.substring(0, index) + s2.substring(index + 1);
        }
        if (s2.endsWith(".")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        return s2.toLowerCase();
    }
    
    private String a(final String s) {
        String s2 = s;
        final int index;
        if ((index = s2.indexOf("#")) != -1) {
            s2 = s2.substring(0, index);
        }
        final int index2;
        if ((index2 = s2.indexOf("?")) != -1) {
            s2 = s2.substring(0, index2);
        }
        return s2;
    }
}
