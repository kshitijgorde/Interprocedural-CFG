// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.EOFException;
import com.hw.client.util.a;
import java.io.InputStream;

final class j implements Runnable
{
    private boolean a;
    private int b;
    private InputStream c;
    private long d;
    private da e;
    private int f;
    private static long g;
    
    j(final da e, final int b, final InputStream c) {
        this.e = e;
        com.hw.client.util.a.b("DoorReader stream=" + b + ",in=" + c);
        this.c = c;
        this.b = b;
        this.a = false;
        this.d = 0L;
        if (b == 1) {
            this.f = 200000;
        }
        else {
            this.f = -1;
        }
        this.a = false;
        final Thread thread;
        (thread = new Thread(this, "HW-DoorReader-" + this.b)).setPriority(10);
        thread.start();
    }
    
    public final boolean a() {
        return this.a;
    }
    
    public final boolean b() {
        if (this.f <= 0) {
            return false;
        }
        if (this.d == 0L) {
            return false;
        }
        if ((j.g = System.currentTimeMillis() - this.d) > this.f) {
            com.hw.client.util.a.d("reader has timed-out, active_diff => " + j.g);
            return true;
        }
        return false;
    }
    
    public final void run() {
        this.d = System.currentTimeMillis();
        while (!this.a) {
            this.d = System.currentTimeMillis();
            try {
                if (this.b == 1) {
                    this.e.a(this.c);
                }
                else {
                    if (this.b != 2) {
                        continue;
                    }
                    this.e.b(this.c);
                }
            }
            catch (EOFException ex2) {
                this.a = true;
            }
            catch (IOException ex) {
                com.hw.client.util.a.d("DoorReader: caught " + ex + ", has_error => " + this.a);
                this.a = true;
            }
        }
    }
    
    public final void c() {
        this.a = true;
    }
}
