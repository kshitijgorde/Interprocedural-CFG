// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.ag;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.awt.Frame;
import java.io.IOException;
import com.diginet.digichat.awt.ad;

public class ac extends ad
{
    private h a;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
        }
        super.setVisible(visible);
    }
    
    public void b() {
        try {
            this.a.a();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public ac(final Frame frame, final h a) {
        super(frame, ap.a(com.esial.util.d.a("%1 Settings"), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), a);
        this.a = a;
        if (!a.b6) {
            this.a(new b0(a));
        }
        this.a(new b4(a));
        this.a(new b5(a));
        if (a.az != null) {
            this.a(new b9(a));
        }
        this.a(new ca(a));
    }
}
