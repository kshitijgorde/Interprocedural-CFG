// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

class SysPropertyActions
{
    public static String getProperty(final String name, final String defaultValue) {
        String prop;
        if (System.getSecurityManager() == null) {
            prop = SysProps.NON_PRIVILEDGED.getProperty(name, defaultValue);
        }
        else {
            prop = SysProps.PRIVILEDGED.getProperty(name, defaultValue);
        }
        return prop;
    }
    
    interface SysProps
    {
        public static final SysProps NON_PRIVILEDGED = new SysProps() {
            public String getProperty(final String name, final String defaultValue) {
                return System.getProperty(name, defaultValue);
            }
        };
        public static final SysProps PRIVILEDGED = new SysProps() {
            public String getProperty(final String name, final String defaultValue) {
                final PrivilegedAction action = new PrivilegedAction() {
                    public Object run() {
                        return System.getProperty(name, defaultValue);
                    }
                };
                return AccessController.doPrivileged((PrivilegedAction<String>)action);
            }
        };
        
        String getProperty(final String p0, final String p1);
    }
}
