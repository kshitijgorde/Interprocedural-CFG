// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.io.EOFException;
import java.io.DataInput;
import com.diginet.digichat.network.t;

public class au extends Thread
{
    private h a;
    boolean b;
    
    public final void run() {
        t t = null;
        try {
            while (!this.a.bb) {
                try {
                    t = new t(this.a.w);
                    this.a.ao(t);
                    t = null;
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
            if (!this.a.bb) {
                final t t2 = new t(66561, 1);
                String s;
                if (t == null) {
                    s = StringSubst.Substitute(LanguageSupport.translate("The connection to the %1 server was unexpectedly broken."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                }
                else {
                    s = StringSubst.Substitute(LanguageSupport.translate("The connection to the %1 server was unexpectedly broken while receiving message type %2."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, Integer.toHexString(t.b()) });
                }
                t2.a(0, 0, s);
                t2.a(0, 1, ex.toString());
                this.a.ao(t2);
                this.a.d();
            }
        }
    }
    
    public au(final h a) {
        super("ChatReader");
        this.b = false;
        this.a = a;
    }
}
