// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.io.EOFException;
import java.io.DataInput;
import com.diginet.digichat.network.v;

public class b8 extends Thread
{
    private i a;
    boolean b;
    
    public void run() {
        v v = null;
        final byte[] key = new byte[8];
        try {
            while (!this.a.ay) {
                try {
                    this.a.inpRandom.nextBytes(key);
                    this.a.inpCoded.setKey(key);
                    v = new v(this.a.r);
                    this.a.am(v);
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
            if (!this.a.ay) {
                final v v2 = new v(66561, 1);
                String s;
                if (v == null) {
                    s = a5.a(c.a("The connection to the %1 server was unexpectedly broken."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                }
                else {
                    s = a5.a(c.a("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, Integer.toHexString(v.b()) });
                }
                v2.a(0, 0, s);
                v2.a(0, 1, ex.toString());
                this.a.am(v2);
                this.a.a();
            }
        }
    }
    
    public b8(final i a) {
        super("ChatReader");
        this.b = false;
        this.a = a;
    }
}
