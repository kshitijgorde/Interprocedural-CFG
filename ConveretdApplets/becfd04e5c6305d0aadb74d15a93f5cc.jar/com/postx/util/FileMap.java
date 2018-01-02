// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util;

import java.util.Enumeration;
import java.util.Hashtable;

public class FileMap
{
    public static final String Ident = "$Id: FileMap.java,v 1.3 2009/08/03 03:36:01 blm Exp $";
    public static final int INTERNAL = 1;
    public static final int LOCALIZED = 2;
    public static final int SANITIZED = 4;
    public static final int PUBLIC = 6;
    public static final String MBAR_KEY = "mbar";
    public static final String MAIN_KEY = "main";
    private final Hashtable internal;
    private final Hashtable localized;
    private final Hashtable sanitized;
    
    private void merge(final Hashtable hashtable, final Hashtable hashtable2) {
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
    }
    
    public Object get(final String s, final int n) {
        final Object value;
        if ((n & 0x2) != 0x0 && (value = this.localized.get(s)) != null) {
            return value;
        }
        final Object value2;
        if ((n & 0x4) != 0x0 && (value2 = this.sanitized.get(s)) != null) {
            return value2;
        }
        final Object value3;
        if ((n & 0x1) != 0x0 && (value3 = this.internal.get(s)) != null) {
            return value3;
        }
        return null;
    }
    
    public void put(final String s, final int n, final Object o) {
        if ((n & 0x2) != 0x0) {
            this.localized.put(s, o);
        }
        if ((n & 0x4) != 0x0) {
            this.sanitized.put(s, o);
        }
        if ((n & 0x1) != 0x0) {
            this.internal.put(s, o);
        }
    }
    
    public String toString() {
        return "{internal:" + this.internal + "," + "localized:" + this.localized + "," + "sanitized:" + this.sanitized + "}";
    }
    
    public FileMap() {
        this.internal = new Hashtable();
        this.localized = new Hashtable();
        this.sanitized = new Hashtable();
    }
    
    public int size(final int n) {
        return this.merged(n).size();
    }
    
    public boolean containsKey(final String s, final int n) {
        return this.get(s, n) != null;
    }
    
    public Enumeration keys(final int n) {
        return this.merged(n).keys();
    }
    
    private Hashtable merged(final int n) {
        final Hashtable hashtable = new Hashtable();
        if ((n & 0x2) != 0x0) {
            this.merge(this.localized, hashtable);
        }
        if ((n & 0x4) != 0x0) {
            this.merge(this.sanitized, hashtable);
        }
        if ((n & 0x1) != 0x0) {
            this.merge(this.internal, hashtable);
        }
        return hashtable;
    }
    
    public void remove(final String s, final int n) {
        if ((n & 0x2) != 0x0) {
            this.localized.remove(s);
        }
        if ((n & 0x4) != 0x0) {
            this.sanitized.remove(s);
        }
        if ((n & 0x1) != 0x0) {
            this.internal.remove(s);
        }
    }
}
