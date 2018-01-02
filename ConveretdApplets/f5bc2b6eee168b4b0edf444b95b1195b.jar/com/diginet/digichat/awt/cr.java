// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Event;
import com.diginet.digichat.client.User2;
import com.diginet.digichat.client.g;
import com.diginet.ui.cs;

public class cr extends cs
{
    private g a;
    private User2 b;
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            this.a.d(this.b);
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public cr(final Frame frame, final Font font, final g a, final User2 b) {
        super(frame, font);
        this.a = a;
        this.b = b;
        this.setBackground(a.df.outerBackground);
    }
}
