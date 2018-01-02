// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

public class b3 extends b2
{
    long a;
    long b;
    private cg c;
    private Thread d;
    
    public final void a(final long a) {
        if (this.c != null) {
            this.c.a();
        }
        this.a = a;
        this.b = System.currentTimeMillis();
        if (this == null) {
            throw null;
        }
        this.c = new Runnable() {
            private boolean a = false;
            
            public final void a() {
                this.a = true;
            }
            
            public final void run() {
                this.a = false;
                while (b3.this.a + b3.this.b >= System.currentTimeMillis() && !this.a) {
                    b3.this.setText(c(b3.this.a + b3.this.b - System.currentTimeMillis()));
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
            }
        };
        (this.d = new Thread(this.c)).start();
    }
    
    public final void a(final String text) {
        if (this.c != null) {
            this.c.a();
        }
        if (text != null) {
            this.setText(text);
        }
        this.d = null;
        this.c = null;
    }
    
    private static final String c(final long n) {
        final int n2 = (int)(n / 1000L);
        final int n3 = n2 / 60;
        final int n4 = n3 / 60;
        final int n5 = n3 - n4 * 60;
        final int n6 = n2 - (n4 * 3600 + n5 * 60);
        return ((n4 < 10) ? ("0" + n4) : ("" + n4)) + ":" + ((n5 < 10) ? ("0" + n5) : ("" + n5)) + ":" + ((n6 < 10) ? ("0" + n6) : ("" + n6));
    }
    
    public b3(final String[] array) {
        super(array);
    }
}
