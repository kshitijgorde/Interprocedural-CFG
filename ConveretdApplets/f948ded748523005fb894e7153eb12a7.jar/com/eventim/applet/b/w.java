// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.applet.EventimApplet;
import java.awt.Paint;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public final class w extends JPanel
{
    private String a;
    private Color b;
    private String c;
    private Color d;
    
    public w(final String a, final String c, final Color b, final Color d) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.d = d;
        this.setOpaque(false);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D;
        (graphics2D = (Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final Font font = this.getFont();
        graphics2D.setFont(new Font(font.getName(), font.getStyle() | 0x1, font.getSize()));
        graphics2D.setPaint(new Color(0.5f, 0.5f, 0.5f, 0.5f));
        graphics2D.fillOval(3, 3, 10, 10);
        graphics2D.drawString(this.a, 19, 13);
        graphics2D.fillOval(3, 19, 10, 10);
        graphics2D.drawString(this.c, 19, 29);
        graphics2D.setPaint(this.b);
        graphics2D.fillOval(0, 0, 10, 10);
        graphics2D.setPaint(this.d);
        graphics2D.fillOval(0, 16, 10, 10);
        graphics2D.setPaint(Color.black);
        graphics2D.drawOval(0, 0, 10, 10);
        graphics2D.drawOval(0, 16, 10, 10);
        graphics2D.drawString(this.a, 15, 9);
        graphics2D.drawString(this.c, 15, 25);
        graphics2D.drawString(this.a, 16, 9);
        graphics2D.drawString(this.c, 16, 25);
        graphics2D.drawString(this.a, 17, 9);
        graphics2D.drawString(this.c, 17, 25);
        graphics2D.drawString(this.a, 15, 10);
        graphics2D.drawString(this.c, 15, 26);
        graphics2D.drawString(this.a, 16, 10);
        graphics2D.drawString(this.c, 16, 26);
        graphics2D.drawString(this.a, 17, 10);
        graphics2D.drawString(this.c, 17, 26);
        graphics2D.drawString(this.a, 15, 11);
        graphics2D.drawString(this.c, 15, 27);
        graphics2D.drawString(this.a, 16, 11);
        graphics2D.drawString(this.c, 16, 27);
        graphics2D.drawString(this.a, 17, 11);
        graphics2D.drawString(this.c, 17, 27);
        graphics2D.setPaint(Color.white);
        graphics2D.drawString(this.a, 16, 10);
        graphics2D.drawString(this.c, 16, 26);
        if (!EventimApplet.l()) {
            graphics2D.drawString(this.a, 16, 10);
            graphics2D.drawString(this.c, 16, 26);
        }
    }
}
