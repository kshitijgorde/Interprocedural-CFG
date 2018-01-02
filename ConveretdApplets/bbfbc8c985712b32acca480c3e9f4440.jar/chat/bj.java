// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.EOFException;
import java.io.DataInput;

public final class bj extends Thread
{
    private cs a;
    private boolean a;
    
    public final void run() {
        r r = null;
        try {
            while (!this.a.l) {
                try {
                    r = new r(this.a.a);
                    this.a.n(r);
                    r = null;
                    continue;
                }
                catch (EOFException ex2) {
                    if (!this.a) {
                        try {
                            Thread.sleep(500L);
                            this.a = true;
                        }
                        catch (InterruptedException ex3) {}
                        continue;
                    }
                    throw new Exception();
                }
                break;
            }
        }
        catch (Exception ex) {
            if (!this.a.l) {
                final r r2 = new r(66561, 1);
                String s;
                if (r == null) {
                    s = bm.a(aS.a(474), new String[] { this.a.a.a });
                }
                else {
                    s = bm.a(aS.a(475), new String[] { this.a.a.a, Integer.toHexString(r.a) });
                }
                r2.a(0, 0, s);
                r2.a(0, 1, ex.toString());
                this.a.n(r2);
                this.a.c();
            }
        }
    }
    
    public bj(final cs a) {
        super("ChatReader");
        this.a = false;
        this.a = a;
    }
}
