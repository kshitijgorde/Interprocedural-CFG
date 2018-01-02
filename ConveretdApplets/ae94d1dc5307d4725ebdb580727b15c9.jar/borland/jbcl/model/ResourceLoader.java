// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.lang.reflect.Method;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;

class ResourceLoader
{
    static ResourceBundle getBundle(final ClassLoader loader, final String name, final Locale locale) throws MissingResourceException {
        try {
            if (loader == null) {
                if (Class.forName("borland.jbcl.model.ResourceLoader").getClassLoader() == null) {
                    return ResourceBundle.getBundle(name, locale);
                }
                final Class clazz = Class.forName("java.util.ResourceBundle");
                final Method getBundleMethod = clazz.getMethod("getBundle", Class.forName("java.lang.String"), Class.forName("java.util.Locale"));
                return (ResourceBundle)getBundleMethod.invoke(null, name, locale);
            }
            else {
                if (Class.forName("borland.jbcl.model.ResourceLoader").getClassLoader() == null) {
                    final Class clazz = Class.forName("java.util.ResourceBundle");
                    final Method getBundleMethod = clazz.getMethod("getBundle", Class.forName("java.lang.String"), Class.forName("java.util.Locale"));
                    return (ResourceBundle)getBundleMethod.invoke(null, name, locale);
                }
                final Class clazz = loader.loadClass("java.util.ResourceBundle");
                final Method getBundleMethod = clazz.getMethod("getBundle", Class.forName("java.lang.String"), Class.forName("java.util.Locale"));
                return (ResourceBundle)getBundleMethod.invoke(null, name, locale);
            }
        }
        catch (Exception e) {
            throw new MissingResourceException("can't find resource for ", name, "");
        }
    }
    
    static ResourceBundle getBundle(final ClassLoader loader, final String name) throws MissingResourceException {
        return getBundle(loader, name, Locale.getDefault());
    }
}
