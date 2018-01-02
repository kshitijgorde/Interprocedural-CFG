import java.util.Enumeration;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class ag
{
    public static void a(final String s, final ah ah, final String s2) {
        InputStream openStream = null;
        try {
            openStream = new URL(s).openStream();
            final byte[] b = new aj(openStream).b();
            final Properties properties = new Properties();
            properties.load(new ByteArrayInputStream(b));
            if (n.b()) {
                n.b("loaded properties from: " + s);
            }
            final Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s3 = (String)propertyNames.nextElement();
                final String string = s2 + s3;
                final String property = properties.getProperty(s3);
                if (property != null) {
                    ah.put(string, property);
                }
            }
        }
        catch (Exception ex) {
            if (n.a()) {
                n.a("failed loading properties from: " + s, ex);
            }
        }
        finally {
            if (openStream != null) {
                try {
                    openStream.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public static ah a(String string, final u u, final boolean b, final boolean b2) {
        String string2;
        if (b) {
            string2 = string + ".";
        }
        else {
            string2 = "";
        }
        final ah ah = new ah(string);
        String a = (u != null) ? u.a("CODEBASE", u) : "";
        if (u != null && u.m("INSTANCE_NAME")) {
            a = ai.a(a, u.a("INSTANCE_NAME", u));
        }
        string += ".prop";
        a(ai.a(a, string), ah, string2);
        if (b2) {
            a(ai.a(ai.a(a, "/conf"), string), ah, string2);
            String s = u.a("Lang", u);
            if (s == null) {
                s = u.a("LANG", u);
            }
            if (s != null) {
                a(ai.a(ai.a(ai.a(a, "/conf"), s), string), ah, string2);
            }
        }
        return ah;
    }
    
    public static ah a(final String s, final u u, final boolean b) {
        return a(s, u, b, true);
    }
}
