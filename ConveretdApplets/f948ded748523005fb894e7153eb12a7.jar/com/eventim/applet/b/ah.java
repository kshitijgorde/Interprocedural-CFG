// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Dimension;
import javax.swing.SwingUtilities;
import java.awt.Font;
import com.eventim.applet.a.i;
import com.eventim.applet.EventimApplet;
import java.awt.Color;
import javax.swing.JLabel;

public final class ah extends JLabel
{
    public ah(final String s, final Color color, final Color color2, final Color color3, final EventimApplet eventimApplet) {
        super(s, i.a(color, color2, color3), 0);
        this.setHorizontalTextPosition(10);
        this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), 9));
        final int n = SwingUtilities.computeStringWidth(eventimApplet.getGraphics().getFontMetrics(), s) + 15;
        this.setPreferredSize(new Dimension(n, 17));
        this.setMinimumSize(new Dimension(n, 17));
        this.setMaximumSize(new Dimension(n, 17));
    }
}
