import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.ResourceBundle;

// 
// Decompiled by Procyon v0.5.30
// 

public class XMLBundle extends ResourceBundle
{
    protected Hashtable get;
    protected Vector keys;
    protected static XMLBundle put;
    
    protected XMLBundle() {
        this.get = new Hashtable();
        this.keys = new Vector();
    }
    
    protected final Object handleGetObject(final String s) {
        return staticHandleGetObject(s);
    }
    
    public final Enumeration getKeys() {
        return staticGetKeys();
    }
    
    public static final synchronized Enumeration staticGetKeys() {
        return XMLBundle.put.get.keys();
    }
    
    public static final synchronized void setValue(final String s, final String s2) {
        XMLBundle.put.get.put(s, s2);
    }
    
    private static synchronized Object staticHandleGetObject(final String s) {
        return XMLBundle.put.get.get(s);
    }
    
    static {
        XMLBundle.put = new XMLBundle();
    }
}
