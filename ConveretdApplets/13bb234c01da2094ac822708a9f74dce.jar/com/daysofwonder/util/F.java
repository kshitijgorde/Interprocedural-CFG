// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public class F
{
    public static final String[] a;
    protected I b;
    protected int c;
    
    public F() {
        this(new H());
    }
    
    public F(final int n) {
        this(new H());
        this.a(n);
    }
    
    public F(final I b) {
        this.c = 2;
        this.b = b;
    }
    
    public boolean a(final Properties properties) {
        return this.b.a(properties);
    }
    
    public void a(final int c) {
        if (c > 6) {
            this.c = 6;
        }
        else if (c < 1) {
            this.c = 1;
        }
        else {
            this.c = c;
        }
    }
    
    public void a(final int n, final String s) {
        if (n < this.c) {
            return;
        }
        this.b.a(n, s);
    }
    
    public void a(final int n, final String s, final String s2) {
        if (n < this.c) {
            return;
        }
        this.b.a(n, s + ": " + s2);
    }
    
    public void a(final int n, final byte[] array, final int n2, final int n3) {
        this.a(n, o.a(array, n2, n3));
    }
    
    public void a(final int n, final Throwable t) {
        if (n < this.c) {
            return;
        }
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        this.b.a(n, stringWriter.getBuffer().toString());
    }
    
    static {
        a = new String[] { "", "DEBUG", "INFO", "WARNING", "ERROR  ", "CRITICAL", "EMERGENCY" };
    }
}
