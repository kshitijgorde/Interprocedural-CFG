// 
// Decompiled by Procyon v0.5.30
// 

package com.is_teledata.log;

import java.io.PrintStream;

public class ConsoleAppender extends ad
{
    private PrintStream a;
    
    public ConsoleAppender() {
        this.a = System.out;
    }
    
    public ConsoleAppender(final String s) {
        this();
        super.b(s);
        if (f.e.i()) {
            f.e.g("ctor ConsoleAppender " + s);
        }
        if (super.b != null) {
            if (super.b.equals("err")) {
                this.a = System.err;
            }
            else {
                this.a = System.out;
            }
            this.a(super.c);
        }
    }
    
    public ConsoleAppender(final aa aa) {
        this();
        this.a(aa);
    }
    
    public synchronized void append(final f f, final h h, final String s, final Throwable t) {
        this.a.print(this.a().a(f, h, s, t));
    }
    
    public String toString() {
        return "ConsoleAppender@" + this.hashCode() + "[" + super.a + "]->" + ((this.a == System.out) ? "STDOUT" : "STDERR");
    }
    
    public void setTarget(final PrintStream a) {
        if (a != null && (a == System.out || a == System.err)) {
            this.a = a;
        }
    }
}
