// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;

public abstract class AccessibleBundle
{
    protected String key;
    
    public AccessibleBundle() {
        this.key = null;
    }
    
    public String toDisplayString() {
        return this.toDisplayString(Locale.getDefault());
    }
    
    protected String toDisplayString(final String s, final Locale locale) {
        String string = null;
        try {
            string = ResourceBundle.getBundle(s, locale).getString(this.key);
        }
        catch (MissingResourceException ex) {
            System.err.println(String.valueOf(String.valueOf(ex)) + ":  " + s + " not found");
        }
        if (string != null) {
            return string;
        }
        return this.key;
    }
    
    public String toDisplayString(final Locale locale) {
        return this.toDisplayString("javax.accessibility.AccessibleResourceBundle", locale);
    }
    
    public String toString() {
        return this.toDisplayString();
    }
}
