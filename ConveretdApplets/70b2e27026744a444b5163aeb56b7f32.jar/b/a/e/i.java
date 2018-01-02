// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import b.a.d.g;
import netscape.javascript.JSObject;
import b.a.d.d;
import java.applet.Applet;
import b.a.b.a;
import java.util.Vector;
import javax.swing.JApplet;

public class i extends JApplet
{
    private boolean a;
    protected boolean b;
    protected String c;
    protected String d;
    protected String e;
    protected boolean f;
    protected boolean g;
    protected static Vector h;
    
    public i() {
        this.a = false;
        this.b = false;
        this.d = "cookieframe";
        this.e = "cookiewriter.php?";
        this.f = false;
        this.g = false;
    }
    
    public synchronized void init() {
        this.b = true;
        i.h.addElement(this);
        try {
            this.getCodeBase().openStream().close();
        }
        catch (Throwable t) {}
        if (this.getParameter("COOKIE_DOMAIN") != null) {
            this.c = this.getParameter("COOKIE_DOMAIN");
        }
        else if (this.getDocumentBase() != null) {
            this.c = this.getDocumentBase().getHost();
        }
        else if (this.getCodeBase() != null) {
            this.c = this.getCodeBase().getHost();
        }
        if (this.getParameter("COOKIE_WRITER") != null) {
            this.e = this.getParameter("COOKIE_WRITER");
            if (this.e.indexOf(63) >= 0) {
                this.e += "&";
            }
            else {
                this.e += "?";
            }
        }
        if (this.getParameter("COOKIE_FRAME") != null) {
            this.d = this.getParameter("COOKIE_FRAME");
        }
        if (i.h.size() == 1) {
            b.a.b.a.a(this);
            this.f = true;
        }
        if (b.a.d.d.b((Object)this.getParameter("DISABLE_JAVASCRIPT"))) {
            this.g = false;
            return;
        }
        try {
            JSObject.getWindow((Applet)this);
            this.g = true;
        }
        catch (Throwable t2) {
            this.g = false;
        }
    }
    
    public synchronized void destroy() {
        if (this.f) {
            b.a.b.a.a((Applet)null);
        }
        super.destroy();
        this.a = true;
    }
    
    public String a() {
        if (!this.g) {
            return null;
        }
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            if (window == null) {
                return null;
            }
            final JSObject jsObject = (JSObject)window.getMember("document");
            if (jsObject == null) {
                return null;
            }
            final String s = (String)jsObject.getMember("cookie");
            if (s == null || s.length() > 0) {
                return s;
            }
        }
        catch (Throwable t) {
            System.err.println("KApplet.getCookie: " + t.getMessage());
            return null;
        }
        return null;
    }
    
    public String a(final String s) {
        return this.a(s, false);
    }
    
    public String a(final String s, final boolean b) {
        final String a = this.a();
        if (a == null) {
            return null;
        }
        final String string = s + "=";
        final int index = a.indexOf(string);
        if (index < 0) {
            return null;
        }
        final int n = index + string.length();
        int n2 = a.indexOf(";", n);
        if (n2 < 0) {
            n2 = a.length();
        }
        final String substring = a.substring(n, n2);
        if (b) {
            return substring;
        }
        return b.a.d.g.b(substring);
    }
    
    public boolean a(final String s, final String s2, final Date date) {
        return this.a(s, s2, null, date);
    }
    
    public boolean a(final String s, final String s2, final String s3, final Date date) {
        if (!this.g) {
            return this.b(s, s2, s3, date);
        }
        String s4 = s + "=" + b.a.d.g.a(s2);
        if (s3 != null) {
            s4 = s4 + "; path=" + s3;
        }
        if (date != null) {
            s4 = s4 + "; expires=" + b.a.d.g.a(date);
        }
        if (this.c != null) {
            s4 = s4 + "; domain=" + this.c;
        }
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            if (window == null) {
                return false;
            }
            final JSObject jsObject = (JSObject)window.getMember("document");
            if (jsObject == null) {
                return false;
            }
            jsObject.setMember("cookie", (Object)s4);
            if (jsObject.getMember("cookie") != null) {
                return true;
            }
            this.g = false;
            return this.b(s, s2, s3, date);
        }
        catch (Throwable t) {
            System.err.println("KApplet.setCookie: " + t.getMessage());
            return false;
        }
    }
    
    protected boolean b(final String s, final String s2, final String s3, final Date date) {
        String s4 = "$/" + this.e + "name=" + s + "&value=" + b.a.d.g.a(s2);
        if (s3 != null) {
            s4 = s4 + "&path=" + b.a.d.g.a(s3);
        }
        if (date != null) {
            s4 = s4 + "&expires=" + b.a.d.g.a(b.a.d.g.a(date));
        }
        if (this.c != null) {
            s4 = s4 + "&domain=" + b.a.d.g.a(this.c);
        }
        return this.a(s4, this.d);
    }
    
    public boolean a(String substring, final String s) {
        String s2 = "";
        if (substring.startsWith("$/")) {
            s2 += this.getDocumentBase();
            substring = substring.substring(2);
        }
        final int lastIndex = s2.lastIndexOf(47);
        if (lastIndex >= 0) {
            s2 = s2.substring(0, lastIndex + 1);
        }
        try {
            this.getAppletContext().showDocument(new URL(s2 + substring), s);
            return true;
        }
        catch (MalformedURLException ex) {}
        catch (NullPointerException ex2) {}
        return false;
    }
    
    static {
        i.h = new Vector();
    }
}
