// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.security.PrivilegedAction;
import java.security.AccessController;

public class PropertyAccess
{
    public static String getProperty(final String name) {
        return getProperty(name, null);
    }
    
    public static String getProperty(final String name, final String defaultValue) {
        final PrivilegedAction action = new PropertyReadAction(name, defaultValue);
        final String property = AccessController.doPrivileged((PrivilegedAction<String>)action);
        return property;
    }
    
    public static String setProperty(final String name, final String value) {
        final PrivilegedAction action = new PropertyWriteAction(name, value);
        final String property = AccessController.doPrivileged((PrivilegedAction<String>)action);
        return property;
    }
    
    static class PropertyReadAction implements PrivilegedAction
    {
        private String name;
        private String defaultValue;
        
        PropertyReadAction(final String name, final String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }
        
        public Object run() {
            return System.getProperty(this.name, this.defaultValue);
        }
    }
    
    static class PropertyWriteAction implements PrivilegedAction
    {
        private String name;
        private String value;
        
        PropertyWriteAction(final String name, final String value) {
            this.name = name;
            this.value = value;
        }
        
        public Object run() {
            return System.setProperty(this.name, this.value);
        }
    }
}
