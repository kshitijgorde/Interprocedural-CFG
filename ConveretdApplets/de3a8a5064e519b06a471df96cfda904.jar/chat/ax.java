// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.EOFException;
import java.io.DataInput;

public final class ax extends Thread
{
    private bh a;
    private boolean a;
    
    public final void run() {
        m m = null;
        try {
            while (!this.a.h) {
                try {
                    m = new m(this.a.a);
                    this.a.l(m);
                    m = null;
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
            if (!this.a.h) {
                final m i = new m(66561, 1);
                String s;
                if (m == null) {
                    s = ak.a(ak.a(474), new String[] { this.a.a.a });
                }
                else {
                    s = ak.a(ak.a(475), new String[] { this.a.a.a, Integer.toHexString(m.a) });
                }
                i.a(0, s);
                i.a(1, ex.toString());
                this.a.l(i);
                this.a.a();
            }
        }
    }
    
    public ax(final bh a) {
        super("ChatReader");
        this.a = false;
        this.a = a;
    }
}
