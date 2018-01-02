// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;

class SystemResourceBundle
{
    static ResourceBundle getBundle(final String name, final Locale locale) throws MissingResourceException {
        return ResourceLoader.getBundle(null, name, locale);
    }
    
    static ResourceBundle getBundle(final String name) throws MissingResourceException {
        return getBundle(name, Locale.getDefault());
    }
}
