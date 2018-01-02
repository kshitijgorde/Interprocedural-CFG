// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Frame;
import com.diginet.digichat.util.filetransfer.cb;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.util.p;

public class a7 extends ad implements p
{
    public String a(final Object o) {
        if (o == super.a) {
            return LanguageSupport.translate("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public final boolean g() {
        return super.f.j && cb.c;
    }
    
    public final void f() {
        cb.a(super.f, super.j);
    }
    
    public a7(final Frame frame, final h h, final int n) {
        super(frame, h, n, new a8(h, false));
    }
}
