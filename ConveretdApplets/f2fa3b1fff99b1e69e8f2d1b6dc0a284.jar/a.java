import netscape.javascript.JSObject;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    private Applet if;
    private boolean a;
    private final String for = "HostCheck.asp?site=";
    private String do;
    
    public a(final Applet if1) {
        this.if = null;
        this.a = false;
        this.do = null;
        this.if = if1;
    }
    
    public a(final Applet if1, final boolean a) {
        this.if = null;
        this.a = false;
        this.do = null;
        this.if = if1;
        this.a = a;
    }
    
    public boolean do() {
        int i = 0;
        int n = 0;
        while (i < 3) {
            try {
                final URL a = this.a();
                if (a == null) {
                    return false;
                }
                final String trim = a.getHost().trim();
                n = 0;
                ++n;
                final URL url = new URL(this.if.getCodeBase(), "HostCheck.asp?site=" + trim);
                ++n;
                final InputStream openStream = url.openStream();
                ++n;
                if (openStream == null) {
                    System.out.println("Can't find HostCheck.asp?site=");
                    return false;
                }
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
                ++n;
                for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine(), ++n) {
                    if (s.trim().equalsIgnoreCase("True")) {
                        return this.a = true;
                    }
                }
                return false;
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
        this.do = "Invalid document.";
        return false;
    }
    
    private String int() {
        try {
            return (String)((JSObject)((JSObject)JSObject.getWindow(this.if).getMember("document")).getMember("location")).getMember("href");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return this.if.getDocumentBase().toString();
        }
    }
    
    public URL a() {
        try {
            return new URL(((String)((JSObject)((JSObject)JSObject.getWindow(this.if).getMember("document")).getMember("location")).getMember("href")).trim());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return this.if.getDocumentBase();
        }
    }
    
    public final String for() {
        return this.do;
    }
    
    public String if() {
        this.a();
        String s = this.int();
        if (s.startsWith("http://")) {
            s = s.substring(7, s.length());
        }
        if (s.startsWith("https://")) {
            s = s.substring(8, s.length());
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
        int index;
        while ((index = s2.indexOf("./")) != -1) {
            s2 = s2.substring(0, index) + s2.substring(index + 1);
        }
        if (s2.endsWith(".")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        return s2;
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
    
    private String if(final String s) {
        String substring = s;
        final int lastIndex;
        if ((lastIndex = substring.lastIndexOf("@")) != -1) {
            substring = substring.substring(lastIndex + 1);
        }
        return substring;
    }
}
