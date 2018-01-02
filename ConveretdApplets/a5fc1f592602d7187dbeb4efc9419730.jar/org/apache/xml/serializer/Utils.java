// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.Hashtable;

class Utils
{
    static Class ClassForName(final String classname) throws ClassNotFoundException {
        final Object o = CacheHolder.cache.get(classname);
        Class c;
        if (o == null) {
            c = Class.forName(classname);
            CacheHolder.cache.put(classname, c);
        }
        else {
            c = (Class)o;
        }
        return c;
    }
    
    private static class CacheHolder
    {
        static final Hashtable cache;
        
        static {
            cache = new Hashtable();
        }
    }
}
