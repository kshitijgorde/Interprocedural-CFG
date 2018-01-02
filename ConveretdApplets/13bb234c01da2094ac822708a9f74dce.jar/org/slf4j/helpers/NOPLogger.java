// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

public class NOPLogger extends MarkerIgnoringBase
{
    public static final NOPLogger b;
    
    public String a() {
        return "NOP";
    }
    
    public final void a(final String s) {
    }
    
    public final void b(final String s) {
    }
    
    public final void c(final String s) {
    }
    
    public final void d(final String s) {
    }
    
    public final void a(final String s, final Throwable t) {
    }
    
    static {
        b = new NOPLogger();
    }
}
