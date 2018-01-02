// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Hashtable;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class ObjectNameFactory
{
    public static ObjectName create(final String name) {
        try {
            return new ObjectName(name);
        }
        catch (MalformedObjectNameException e) {
            throw new Error("Invalid ObjectName: " + name + "; " + e);
        }
    }
    
    public static ObjectName create(final String domain, final String key, final String value) {
        try {
            return new ObjectName(domain, key, value);
        }
        catch (MalformedObjectNameException e) {
            throw new Error("Invalid ObjectName: " + domain + "," + key + "," + value + "; " + e);
        }
    }
    
    public static ObjectName create(final String domain, final Hashtable table) {
        try {
            return new ObjectName(domain, table);
        }
        catch (MalformedObjectNameException e) {
            throw new Error("Invalid ObjectName: " + domain + "," + table + "; " + e);
        }
    }
}
