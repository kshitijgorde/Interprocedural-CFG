// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import java.util.Hashtable;

public class Caps
{
    protected String mime;
    protected Hashtable fields;
    
    public synchronized String getMime() {
        return this.mime;
    }
    
    public synchronized void setMime(final String newMime) {
        this.mime = newMime;
    }
    
    public Caps(final String mime) {
        this.fields = new Hashtable();
        final int len = mime.length();
        int sep1 = 0;
        int sep2 = mime.indexOf(59);
        if (sep2 == -1) {
            sep2 = len;
        }
        this.mime = mime.substring(0, sep2);
        while (sep2 < len) {
            sep1 = sep2 + 1;
            sep2 = mime.indexOf(61, sep1);
            int sep3 = mime.indexOf(59, sep2);
            if (sep3 == -1) {
                sep3 = len;
            }
            this.setField(mime.substring(sep1, sep2), mime.substring(sep2 + 1, sep3));
            sep2 = sep3 + 1;
        }
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("Caps: ");
        buf.append(this.mime);
        buf.append("\n");
        final Enumeration e = this.fields.keys();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            buf.append(" \"").append(key).append("\": \"").append(this.fields.get(key)).append("\"\n");
        }
        return buf.toString();
    }
    
    public void setField(final String key, final Object value) {
        this.fields.put(key, value);
    }
    
    public void setFieldInt(final String key, final int value) {
        this.fields.put(key, new Integer(value));
    }
    
    public Object getField(final String key) {
        return this.fields.get(key);
    }
    
    public int getFieldInt(final String key, final int def) {
        final Integer i = this.fields.get(key);
        if (i == null) {
            return def;
        }
        return i;
    }
    
    public String getFieldString(final String key, final String def) {
        final String s = this.fields.get(key);
        if (s == null) {
            return def;
        }
        return s;
    }
}
