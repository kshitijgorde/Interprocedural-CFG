import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import netscape.javascript.JSObject;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JS_Subscriber extends Applet
{
    private static transient String f;
    private static transient String e;
    private String c;
    protected h a;
    private Hashtable b;
    private JSObject d;
    
    public JS_Subscriber() {
        this.b = new Hashtable();
    }
    
    public void start() {
        this.setLayout(null);
        this.c = this.getCodeBase().getHost();
        if (this.c == null || this.c.length() == 0) {
            this.c = "zaphod.dev.sportsline.com";
        }
        this.a = new h(this);
        this.d = JSObject.getWindow((Applet)this);
        try {
            this.d.call("live_initialized", (Object[])new String[] { "classVersion=" + System.getProperty("java.class.version") + "," + "version=" + System.getProperty("java.version") + "," + "vendor=" + System.getProperty("java.vendor") + "," + "osArch=" + System.getProperty("os.arch") + "," + "osName=" + System.getProperty("os.name") + "," + "osVersion=" + System.getProperty("os.version") });
        }
        catch (Exception ex) {
            h.a("", ex);
        }
    }
    
    public void setName(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "(,)", false);
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("live_subscribe")) {
                this.a(stringTokenizer);
                this.b(JS_Subscriber.f, JS_Subscriber.e);
            }
            else if (nextToken.equals("live_unsubscribe")) {
                this.a(stringTokenizer);
                this.a(JS_Subscriber.f, JS_Subscriber.e);
            }
            else if (nextToken.equals("live_stop")) {
                this.stop();
            }
            else if (s.equalsIgnoreCase("debug")) {
                this.a.b();
            }
        }
        catch (Throwable t) {
            h.a(t);
        }
    }
    
    private void a(final StringTokenizer stringTokenizer) {
        final Vector vector = new Vector();
        JS_Subscriber.f = null;
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens - 1; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            if (i == 0) {
                JS_Subscriber.f = nextToken;
            }
            else {
                JS_Subscriber.f = JS_Subscriber.f + ',' + nextToken;
            }
        }
        JS_Subscriber.e = stringTokenizer.nextToken();
    }
    
    public void stop() {
        if (this.a != null) {
            this.a.c();
            this.a = null;
        }
    }
    
    private void b(final String s, final String s2) {
        try {
            this.d.call("live_callable", (Object[])new String[0]);
        }
        catch (Exception ex) {}
        a a = this.b.get(s2);
        if (a == null) {
            a = new a(s2, this.d);
            this.b.put(s2, a);
        }
        this.a.a(s, a);
    }
    
    private void a(final String s, final String s2) {
        final a a = this.b.get(s2);
        if (a != null) {
            this.a.b(s, a);
        }
    }
}
