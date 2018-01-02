// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

public class Reply extends Hashtable
{
    byte[] replyBytes;
    int result;
    final int kUninitialized = -10000;
    
    void copyTo(final Hashtable c) {
        final Enumeration e = this.keys();
        while (e.hasMoreElements()) {
            final Object key = e.nextElement();
            final Object val = this.get(key);
            c.put(key, val);
        }
    }
    
    Reply(final InputStream in) {
        this(new DataInputStream(in));
    }
    
    Reply(final DataInputStream in) {
        super(15);
        this.result = 0;
        try {
            while (true) {
                final String aLine = in.readLine();
                if (aLine == null || aLine.length() == 0) {
                    break;
                }
                final int i = aLine.indexOf("=");
                if (i < 0) {
                    continue;
                }
                final String name = aLine.substring(0, i);
                final String value = aLine.substring(i + 1);
                this.put(name, value);
            }
            this.result = this.getValue("err", 0);
        }
        catch (IOException e) {
            final String v = "io exception";
            this.put("status", v);
            this.put("exception", e.toString());
        }
    }
    
    public int getValue(final String name, final int def) {
        try {
            final String nv = this.get(name);
            if (nv != null) {
                return Integer.parseInt(nv);
            }
        }
        catch (NumberFormatException ex) {}
        return def;
    }
    
    public int getValue(final String name) {
        return this.getValue(name, -10000);
    }
    
    public String getString(final String name) {
        return this.get(name);
    }
    
    public String getErrorString() {
        return this.getString("error_str");
    }
    
    public boolean getBoolean(final String name, final boolean def) {
        final String i = this.getString(name);
        if (i != null) {
            if (i.equals("1")) {
                return true;
            }
            if (i.equals("0")) {
                return false;
            }
            if (i.equals("true")) {
                return true;
            }
            if (i.equals("on")) {
                return true;
            }
            if (i.equals("false")) {
                return false;
            }
            if (i.equals("off")) {
                return false;
            }
        }
        return def;
    }
    
    public int getStatus() {
        return this.result;
    }
    
    public boolean success() {
        return this.getStatus() == 0;
    }
    
    public String toString() {
        final String out = "Reply:  status: " + this.getStatus();
        return out;
    }
    
    String inspect(final String sep) {
        String s = String.valueOf(this.toString()) + sep;
        final Enumeration e = this.keys();
        while (e.hasMoreElements()) {
            final Object key = e.nextElement();
            final Object val = this.get(key);
            s = String.valueOf(s) + (String)key + "=" + (String)val + sep;
        }
        return s;
    }
}
