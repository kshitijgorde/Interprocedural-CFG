// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.id;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class SerialVersion
{
    public static final int LEGACY = 0;
    public static final int JBOSS_402 = 1;
    public static int version;
    
    static {
        SerialVersion.version = 1;
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            public Object run() {
                try {
                    if (System.getProperty("org.jboss.j2ee.LegacySerialization") != null) {
                        SerialVersion.version = 0;
                    }
                }
                catch (Throwable t) {}
                return null;
            }
        });
    }
}
