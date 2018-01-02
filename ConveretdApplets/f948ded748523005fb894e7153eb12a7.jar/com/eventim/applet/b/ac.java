// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import javax.swing.plaf.ComponentUI;
import com.eventim.applet.EventimApplet;
import javax.swing.JToolTip;

public final class ac extends JToolTip
{
    public ac(final EventimApplet eventimApplet) {
        this.setUI(new b(eventimApplet));
    }
}
