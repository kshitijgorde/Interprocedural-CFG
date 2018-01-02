// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.security.AccessControlException;

public class Serialization
{
    public static final int LATEST = 0;
    public static final int V1R0 = 10;
    public static int version;
    
    static {
        Serialization.version = 0;
        try {
            final String property = PropertyAccess.getProperty("jmx.serial.form");
            if (property != null && property.equals("1.0")) {
                Serialization.version = 10;
            }
        }
        catch (AccessControlException ex) {}
    }
}
