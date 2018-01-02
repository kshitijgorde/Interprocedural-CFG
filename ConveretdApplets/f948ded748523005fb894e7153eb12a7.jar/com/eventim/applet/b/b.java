// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.util.Iterator;
import java.util.Map;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import javax.swing.SwingUtilities;
import com.eventim.applet.a.i;
import javax.swing.JToolTip;
import java.awt.Dimension;
import javax.swing.JComponent;
import com.eventim.applet.EventimApplet;
import de.muntjak.tinylookandfeel.TinyToolTipUI;

final class b extends TinyToolTipUI
{
    private EventimApplet a;
    
    public b(final EventimApplet a) {
        this.a = a;
    }
    
    public final Dimension getPreferredSize(final JComponent component) {
        final FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
        String tipText;
        if ((tipText = ((JToolTip)component).getTipText()) == null) {
            tipText = "";
        }
        final Image image = i.a(new Integer(0)).getImage();
        int n = SwingUtilities.computeStringWidth(fontMetrics, tipText);
        final int n2 = fontMetrics.getHeight() + image.getHeight(component);
        if (n < image.getWidth(component)) {
            n = image.getWidth(component);
        }
        return new Dimension(Math.max(n, this.a.j().keySet().size() * 44) + 10, n2 + 9);
    }
    
    public final void paint(final Graphics graphics, final JComponent component) {
        final Graphics2D graphics2D;
        (graphics2D = (Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setPaint(component.getForeground());
        graphics2D.drawString(((JToolTip)component).getTipText(), 5, 14);
        int n = 3;
        final Iterator<Map.Entry<Long, V>> iterator = this.a.j().entrySet().iterator();
        while (iterator.hasNext()) {
            final Object o;
            final Long n2 = ((Map.Entry<Long, Integer>)(o = iterator.next())).getKey();
            final Integer n3 = ((Map.Entry<K, Integer>)o).getValue();
            graphics2D.drawImage(i.a(EventimApplet.a((long)n2)).getImage(), n, 19, component);
            n += 16;
            graphics2D.drawString((n3 <= 50) ? String.valueOf((int)n3) : "50+", n, 28);
            n += 27;
        }
    }
}
