// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Canvas;

public class ce extends Canvas
{
    private int a;
    Font b;
    private Image c;
    private Graphics d;
    
    public void a(final int a) {
        if (this.a == a) {
            return;
        }
        this.a = a;
        this.paint(this.getGraphics());
    }
    
    public Dimension preferredSize() {
        return new Dimension(200, Toolkit.getDefaultToolkit().getFontMetrics(this.getFont()).getHeight() + 4);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.c == null) {
            this.c = this.createImage(this.size().width, this.size().height);
            this.d = this.c.getGraphics();
        }
        final String string = this.a + "%";
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(string);
        final int height = fontMetrics.getHeight();
        final float n = this.a / 100.0f;
        final Color foreground = this.getForeground();
        final Color background = this.getBackground();
        final Dimension size = this.size();
        final int n2 = (int)(n * (size.width - 2));
        this.d.setColor(background);
        this.d.fillRect(1 + n2 + 1, 1, size.width - 2 - n2, size.height - 2);
        this.d.setColor(Color.black);
        this.d.drawRect(0, 0, size.width - 1, size.height - 1);
        this.d.setColor(foreground);
        this.d.fillRect(1, 1, n2, size.height - 2);
        this.d.setColor(foreground);
        this.d.drawString(string, (size.width - stringWidth) / 2, fontMetrics.getAscent() + (size.height - height) / 2);
        this.d.clipRect(0, 0, n2, this.size().height);
        this.d.setColor(background);
        this.d.drawString(string, (size.width - stringWidth) / 2, fontMetrics.getAscent() + (size.height - height) / 2);
        this.d = this.c.getGraphics();
        graphics.drawImage(this.c, 0, 0, null);
    }
    
    public ce() {
        this.a = 0;
        this.b = new Font("dialog", 1, 12);
        this.c = null;
        this.d = null;
        this.setFont(this.b);
    }
}
