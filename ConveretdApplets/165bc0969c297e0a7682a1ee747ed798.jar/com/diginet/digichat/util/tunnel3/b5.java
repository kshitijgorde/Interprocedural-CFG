// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.tunnel3;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class b5 extends OutputStream
{
    private boolean a;
    private ByteArrayOutputStream b;
    private final b3 c;
    
    private final void a(final boolean b) throws IOException {
        if (this.b.size() > 0 || b) {
            this.b.write(b ? -1 : 0);
            this.c.a.a(new dg(this.b.toByteArray()));
            this.b.reset();
        }
    }
    
    public synchronized void close() throws IOException {
        if (!this.a) {
            this.a(true);
            this.a = true;
        }
    }
    
    public synchronized void flush() throws IOException {
        if (!this.a) {
            this.a(false);
        }
        else if (b3.a(this.c)) {
            System.out.println("error: tried to flush a closed stream");
            Thread.dumpStack();
        }
    }
    
    public synchronized void write(final int n) throws IOException {
        if (!this.a) {
            this.b.write(n);
        }
        else if (b3.a(this.c)) {
            System.out.println("error: tried to write to a closed stream");
            Thread.dumpStack();
        }
    }
    
    public synchronized void write(final byte[] array) throws IOException {
        if (!this.a) {
            this.b.write(array, 0, array.length);
        }
        else if (b3.a(this.c)) {
            System.out.println("error: tried to write to a closed stream");
            Thread.dumpStack();
        }
    }
    
    public synchronized void write(final byte[] array, final int n, final int n2) throws IOException {
        if (!this.a) {
            this.b.write(array, n, n2);
        }
        else if (b3.a(this.c)) {
            System.out.println("error: tried to write to a closed stream");
            Thread.dumpStack();
        }
    }
    
    b5(final b3 c) {
        this.c = c;
        this.a = false;
        this.b = new ByteArrayOutputStream();
    }
}
