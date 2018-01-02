import java.util.Enumeration;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class af implements u
{
    private Applet a;
    private String b;
    private static Hashtable c;
    
    public af(final Applet a) {
        this.b = "AppletGetter";
        this.a = a;
    }
    
    public String a() {
        return this.b;
    }
    
    public String a(final String s, final v v) {
        if (s.equals("CODEBASE")) {
            return this.a.getCodeBase().toString();
        }
        if (s.equals("APPLET")) {
            return this.a.toString();
        }
        return this.a.getParameter(s);
    }
    
    public Object a(final String s, final String s2, final u u) {
        if (s.equals("APPLET") && s2.equals(this.a.toString())) {
            return this.a;
        }
        return null;
    }
    
    public String n(final String s) {
        return null;
    }
    
    public boolean m(final String s) {
        return af.c.containsKey(s) || this.a.getParameter(s) != null;
    }
    
    public Enumeration b() {
        return af.c.keys();
    }
    
    static {
        (af.c = new Hashtable()).put("APPLET", new Boolean(true));
        af.c.put("CODEBASE", new Boolean(true));
        af.c.put("MASTER_PROP_FILE", new Boolean(true));
    }
}
