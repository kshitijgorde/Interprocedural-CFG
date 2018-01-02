import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    public static en a;
    public static Hashtable b;
    
    public static final en a() {
        return r.a;
    }
    
    static {
        (r.a = new en("LHTMLProperties")).a("LHTML_CONFIG_FILE", new eo("LHTML_CONFIG_FILE", "LHTMLProperties", et.o, "String", 1));
        r.a.a("MDG_CONFIG_FILE", new eo("MDG_CONFIG_FILE", "OnvProperties", et.o, "String", 1));
        r.a.a("LOAD_CONFIG_FILES", new eo("LOAD_CONFIG_FILES", "true", et.p, "Boolean", 1));
        r.a.a("BACKGROUND", new eo("BACKGROUND", "ffffff", eu.b, "Color", 1));
        r.a.a("LHTML_APPENDER", new eo("LHTML_APPENDER", "", et.o, "String", 1));
        r.a.a("LHTML_OV_MUI", new eo("LHTML_OV_MUI", "", et.j, "Integer", 1));
        r.a.a("LHTML_DISABLE_SESSION", new eo("LHTML_DISABLE_SESSION", "false", et.p, "Boolean", 1));
        r.a.a("LHTML_ZOMBIE_TIMEOUT", new eo("LHTML_ZOMBIE_TIMEOUT", "20000", et.j, "Integer", 1));
        r.a.a("LHTML_ZOMBIE_CHECK_INTERVAL", new eo("LHTML_ZOMBIE_CHECK_INTERVAL", "30000", et.j, "Integer", 1));
        r.a.a("RELOAD_TIMEOUT", new eo("RELOAD_TIMEOUT", "0", et.k, "Long", 1));
        r.b = new Hashtable();
    }
}
