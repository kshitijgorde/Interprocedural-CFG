// 
// Decompiled by Procyon v0.5.30
// 

package ji.net.cookie;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Date;
import java.net.URL;

public class be
{
    String a;
    String b;
    URL c;
    String d;
    Date e;
    String f;
    private static DateFormat g;
    private static DateFormat h;
    
    public be(final URL c, final String s) {
        final String[] split = s.split(";");
        final String trim = split[0].trim();
        this.c = c;
        this.a = trim.substring(0, trim.indexOf(61));
        this.b = trim.substring(trim.indexOf(61) + 1);
        this.f = "/";
        this.d = c.getHost();
        for (int i = 1; i < split.length; ++i) {
            final String trim2 = split[i].trim();
            final int index = trim2.indexOf(61);
            if (index != -1) {
                final String substring = trim2.substring(0, index);
                String f = trim2.substring(index + 1);
                if (substring.equalsIgnoreCase("domain")) {
                    final String host = c.getHost();
                    if (host.equals(f)) {
                        this.d = f;
                    }
                    else {
                        if (!f.startsWith(".")) {
                            f = ".".concat(String.valueOf(String.valueOf(f)));
                        }
                        if (!host.substring(host.indexOf(46)).equals(f)) {
                            throw new IllegalArgumentException("Trying to set foreign cookie");
                        }
                        this.d = f;
                    }
                }
                else if (substring.equalsIgnoreCase("path")) {
                    this.f = f;
                }
                else if (substring.equalsIgnoreCase("expires")) {
                    try {
                        this.e = be.g.parse(f);
                    }
                    catch (ParseException ex) {
                        try {
                            this.e = be.h.parse(f);
                        }
                        catch (ParseException ex2) {
                            throw new IllegalArgumentException("Bad date format in header: ".concat(String.valueOf(String.valueOf(f))));
                        }
                    }
                }
            }
        }
    }
    
    public boolean a() {
        return this.e != null && new Date().after(this.e);
    }
    
    public String b() {
        return this.a;
    }
    
    public URL c() {
        return this.c;
    }
    
    public boolean a(final URL url) {
        if (this.a()) {
            return false;
        }
        boolean startsWith = false;
        final String host = url.getHost();
        if (this.d != null && host != null && host.equalsIgnoreCase(host)) {
            startsWith = true;
        }
        if (startsWith) {
            String path = url.getPath();
            if (path == null) {
                path = "/";
            }
            startsWith = path.startsWith(this.f);
        }
        return startsWith;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.a);
        sb.append("=");
        sb.append(this.b);
        return sb.toString();
    }
    
    static {
        be.g = new SimpleDateFormat("E, dd MMM yyyy k:m:s 'GMT'", Locale.US);
        be.h = new SimpleDateFormat("E, dd-MMM-yyyy k:m:s 'GMT'", Locale.US);
    }
}
