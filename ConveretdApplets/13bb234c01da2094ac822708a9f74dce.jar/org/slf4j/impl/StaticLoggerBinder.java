// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.impl;

import org.slf4j.ILoggerFactory;

public class StaticLoggerBinder
{
    private static final StaticLoggerBinder c;
    public static String a;
    private static final String d;
    private final ILoggerFactory e;
    static /* synthetic */ Class b;
    
    public static final StaticLoggerBinder a() {
        return StaticLoggerBinder.c;
    }
    
    private StaticLoggerBinder() {
        this.e = new SimpleLoggerFactory();
    }
    
    public ILoggerFactory b() {
        return this.e;
    }
    
    public String c() {
        return StaticLoggerBinder.d;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        c = new StaticLoggerBinder();
        StaticLoggerBinder.a = "1.5.6";
        d = ((StaticLoggerBinder.b == null) ? (StaticLoggerBinder.b = a("org.slf4j.impl.SimpleLoggerFactory")) : StaticLoggerBinder.b).getName();
    }
}
