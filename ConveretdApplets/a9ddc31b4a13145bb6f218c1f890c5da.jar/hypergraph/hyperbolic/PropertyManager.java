// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.util.Hashtable;
import java.io.OutputStream;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Properties;

public class PropertyManager
{
    private Properties properties;
    private Properties defaultProperties;
    
    public PropertyManager() {
        this.properties = this.initProperties();
    }
    
    private Properties initProperties() {
        ((Hashtable<String, String>)(this.defaultProperties = new Properties())).put("model.name", "Poincare model");
        ((Hashtable<String, String>)this.defaultProperties).put("model.class", "hypergraph.hyperbolic.PoincareModel");
        ((Hashtable<String, String>)this.defaultProperties).put("projector.class", "hypergraph.hyperbolic.PoincareProjector");
        ((Hashtable<String, String>)this.defaultProperties).put("linerenderer.class", "hypergraph.hyperbolic.DefaultLineRenderer");
        ((Hashtable<String, String>)this.defaultProperties).put("textrenderer.class", "hypergraph.hyperbolic.DefaultTextRenderer");
        ((Hashtable<String, String>)this.defaultProperties).put("hypergraph.hyperbolic.background.color", "white");
        ((Hashtable<String, String>)this.defaultProperties).put("hypergraph.hyperbolic.text.color", "black");
        return new Properties(this.defaultProperties);
    }
    
    public Object getProperty(final String s) {
        Object o = ((Hashtable<K, Object>)this.properties).get(s);
        if (o == null) {
            o = this.properties.getProperty(s);
        }
        return o;
    }
    
    public Object setProperty(final String s, final Object o) {
        return ((Hashtable<String, Object>)this.properties).put(s, o);
    }
    
    public Class getClass(final String s) throws ClassNotFoundException {
        final String string = this.getString(s);
        try {
            final Class clazz = (Class)this.getProperty(string);
            if (clazz != null) {
                return clazz;
            }
        }
        catch (ClassCastException ex) {}
        final Class<?> forName = Class.forName(string);
        if (forName != null) {
            ((Hashtable<String, Class<?>>)this.properties).put(string, forName);
        }
        return forName;
    }
    
    public String getString(final String s) {
        final Object property = this.getProperty(s);
        return (property instanceof String) ? ((String)property) : null;
    }
    
    public Double getDouble(final String s, final Double n) {
        final Object property = this.getProperty(s);
        if (property instanceof Double) {
            return (Double)property;
        }
        try {
            if (property instanceof String) {
                return new Double((String)property);
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    public void list(final PrintStream printStream) {
        this.properties.list(printStream);
    }
    
    public void list(final PrintWriter printWriter) {
        this.properties.list(printWriter);
    }
    
    public synchronized void load(final InputStream inputStream) throws IOException {
        this.properties.load(inputStream);
    }
    
    public Enumeration propertyNames() {
        return this.properties.propertyNames();
    }
    
    public synchronized void store(final OutputStream outputStream, final String s) throws IOException {
        this.properties.store(outputStream, s);
    }
}
