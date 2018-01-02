// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$XG;

import java.io.StringWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.Hashtable;

public class $AI extends Hashtable
{
    String name;
    
    public String $DI() {
        return this.name.toLowerCase();
    }
    
    public static String $FI(final Object o, final Object o2) {
        return " " + o.toString().toLowerCase() + "=\"" + o2.toString() + "\"";
    }
    
    public void $GG(final Dictionary dictionary) {
        if (dictionary != null) {
            final Enumeration<Object> keys = dictionary.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                this.put(nextElement, dictionary.get(nextElement));
            }
        }
    }
    
    public $AI(final String s) {
        this(s, null);
    }
    
    public $AI(final String name, final Dictionary dictionary) {
        this.name = name;
        this.$GG(dictionary);
    }
    
    public Object get(final Object o) {
        return super.get(o.toString().toLowerCase());
    }
    
    public void output(final Writer writer) throws IOException {
        output(writer, this.name, this);
    }
    
    public static void output(final Writer writer, final String s, final Dictionary dictionary) throws IOException {
        writer.write(60);
        writer.write(s);
        if (dictionary != null) {
            final Enumeration<Object> keys = dictionary.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                writer.write($FI(nextElement, dictionary.get(nextElement)));
            }
        }
        writer.write(62);
    }
    
    public Object put(final Object o, final int n) {
        return this.put(o, String.valueOf(n));
    }
    
    public Object put(final Object o, final Object o2) {
        if (o2 == null) {
            return this.remove(o);
        }
        return super.put(o.toString().toLowerCase(), o2.toString());
    }
    
    public Object remove(final Object o) {
        return super.remove(o.toString().toLowerCase());
    }
    
    public String toString() {
        try {
            final StringWriter stringWriter = new StringWriter();
            this.output(stringWriter);
            return stringWriter.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
