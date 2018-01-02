// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Frame;
import com.diginet.digichat.util.filetransfer.bw;
import com.esial.util.d;
import com.diginet.digichat.util.s;

public class a5 extends z implements s
{
    public String a(final Object o) {
        if (o == super.a) {
            return com.esial.util.d.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public boolean c() {
        return super.e.h && bw.c;
    }
    
    public void b() {
        bw.a(super.e, super.h);
    }
    
    public a5(final Frame frame, final i i, final int n) {
        super(frame, i, n, new a6(i, false));
    }
}
