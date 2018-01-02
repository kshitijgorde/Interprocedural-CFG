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
    
    public final String getString(final int key) throws MissingResourceException {
        return (String)this.getObject(key);
    }
    
    public final String[] getStringArray(final int key) throws MissingResourceException {
        return (String[])this.getObject(key);
    }
    
    protected abstract Object[] getContents();
    
    protected Object handleGetObject(final String key) {
        return this.getObject(Integer.parseInt(key));
    }
    
    public Object getObject(final int index) {
        if (this.contents == null) {
            this.contents = this.getContents();
        }
        try {
            return this.contents[index];
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public Enumeration getKeys() {
        if (this.contents == null) {
            this.contents = this.getContents();
        }
        final Vector result = new Vector(this.contents.length);
        for (int i = 0; i < this.contents.length; ++i) {
            result.addElement(String.valueOf(i));
        }
        return result.elements();
    }
}
