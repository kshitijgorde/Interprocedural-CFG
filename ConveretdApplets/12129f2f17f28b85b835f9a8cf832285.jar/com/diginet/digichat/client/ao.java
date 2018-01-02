// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.io.EOFException;
import java.io.DataInput;
import com.diginet.digichat.network.v;

public class ao extends Thread
{
    private i a;
    boolean b;
    
    public void run() {
        v v = null;
        try {
            while (!this.a.aw) {
                try {
                    v = new v(this.a.q);
                    this.a.ac(v);
                    v = null;
                }
                catch (EOFException ex2) {
                    if (this.b) {
                        throw new Exception();
                    }
                    try {
                        Thread.sleep(500L);
                        this.b = true;
                    }
                    catch (InterruptedException ex3) {}
                }
            }
        }
        catch (Exception ex) {
            if (!this.a.aw) {
                final v v2 = new v(66561, 1);
                String s;
                if (v == null) {
                    s = ap.a(d.a("The connection to the %1 server was unexpectedly broken."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                }
                else {
                    s = ap.a(d.a("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, Integer.toHexString(v.b()) });
                }
                v2.a(0, 0, s);
                v2.a(0, 1, ex.toString());
                this.a.ac(v2);
                this.a.d();
            }
        }
    }
    
    public ao(final i a) {
        super("ChatReader");
        this.b = false;
        this.a = a;
    }
}
