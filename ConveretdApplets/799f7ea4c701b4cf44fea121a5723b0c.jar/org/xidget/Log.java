// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class Log
{
    private static Log instance;
    private Set<String> debugs;
    
    public Log() {
        this.debugs = new HashSet<String>();
        final Iterator<Object> iterator = ((Hashtable<Object, V>)System.getProperties()).keySet().iterator();
        while (iterator.hasNext()) {
            final String string = iterator.next().toString();
            if (string.startsWith("log.")) {
                this.debugs.add(string.substring(4));
            }
        }
    }
    
    public void logf(final String s, final String s2, final Object... array) {
        if (this.debugs.contains(s)) {
            String[] split;
            for (int length = (split = String.format(s2, array).split("\n")).length, i = 0; i < length; ++i) {
                System.out.printf("[%s] %s\n", s.toUpperCase(), split[i]);
            }
        }
    }
    
    public void log(final String s, final String s2) {
        throw new InternalError("Badly shrinked");
    }
    
    public void log(final String s, final Exception ex) {
        throw new InternalError("Badly shrinked");
    }
    
    public static void printf(final String s, final String s2, final Object... array) {
        getInstance().logf(s, s2, array);
    }
    
    public static void println(final String s, final String s2) {
        throw new InternalError("Badly shrinked");
    }
    
    public static void exception(final String s, final Exception ex) {
        throw new InternalError("Badly shrinked");
    }
    
    public static Log getInstance() {
        if (Log.instance == null) {
            Log.instance = new Log();
        }
        return Log.instance;
    }
}
