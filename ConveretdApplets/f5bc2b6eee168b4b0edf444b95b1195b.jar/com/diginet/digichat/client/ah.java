// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.al;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.Frame;
import java.io.IOException;
import com.diginet.digichat.awt.ai;

public class ah extends ai
{
    private g a;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public final void b() {
        try {
            this.a.a();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ah(final Frame frame, final g a) {
        super(frame, StringSubst.Substitute(LanguageSupport.translate("%1 Settings"), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), a);
        this.a = a;
        if (!a.cx) {
            this.a(new ci(a));
        }
        this.a(new cm(a));
        this.a(new cn(a));
        if (a.be != null) {
            this.a(new cp(a));
        }
        this.a(new cq(a));
    }
}
