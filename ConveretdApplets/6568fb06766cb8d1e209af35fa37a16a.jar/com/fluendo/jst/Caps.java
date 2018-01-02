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
    
    public synchronized void setMime(final String mime) {
        this.mime = mime;
    }
    
    public Caps(final String s) {
        this.fields = new Hashtable();
        final int length = s.length();
        int i = s.indexOf(59);
        if (i == -1) {
            i = length;
        }
        this.mime = s.substring(0, i);
        while (i < length) {
            final int n = i + 1;
            final int index = s.indexOf(61, n);
            int index2 = s.indexOf(59, index);
            if (index2 == -1) {
                index2 = length;
            }
            this.setField(s.substring(n, index), s.substring(index + 1, index2));
            i = index2 + 1;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Caps: ");
        sb.append(this.mime);
        sb.append("\n");
        final Enumeration<String> keys = this.fields.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(" \"").append(s).append("\": \"").append(this.fields.get(s)).append("\"\n");
        }
        return sb.toString();
    }
    
    public void setField(final String s, final Object o) {
        this.fields.put(s, o);
    }
    
    public void setFieldInt(final String s, final int n) {
        this.fields.put(s, new Integer(n));
    }
    
    public Object getField(final String s) {
        return this.fields.get(s);
    }
    
    public int getFieldInt(final String s, final int n) {
        final Integer n2 = this.fields.get(s);
        if (n2 == null) {
            return n;
        }
        return n2;
    }
    
    public String getFieldString(final String s, final String s2) {
        final String s3 = this.fields.get(s);
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
}
