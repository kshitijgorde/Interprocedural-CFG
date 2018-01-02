// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.Vector;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class ArrayResourceBundle extends ResourceBundle
{
    private Object[] contents;
    
    public final String getString(final int n) throws MissingResourceException {
        return (String)this.getObject(n);
    }
    
    public final String[] getStringArray(final int n) throws MissingResourceException {
        return (String[])this.getObject(n);
    }
    
    protected abstract Object[] getContents();
    
    protected Object handleGetObject(final String s) {
        return this.getObject(Integer.parseInt(s));
    }
    
    public Object getObject(final int n) {
        if (this.contents == null) {
            this.contents = this.getContents();
        }
        try {
            return this.contents[n];
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public Enumeration getKeys() {
        if (this.contents == null) {
            this.contents = this.getContents();
        }
        final Vector<String> vector = new Vector<String>(this.contents.length);
        for (int i = 0; i < this.contents.length; ++i) {
            vector.addElement(String.valueOf(i));
        }
        return vector.elements();
    }
}
