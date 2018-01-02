// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class b1 extends Canvas
{
    private String a;
    private Image b;
    private Color c;
    private boolean d;
    private boolean e;
    
    public final void a() {
        this.postEvent(new Event(this, 1001, null));
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.e) {
            switch (event.id) {
                case 504: {
                    this.d = true;
                    this.repaint();
                    return true;
                }
                case 505: {
                    this.d = false;
                    this.repaint();
                    return true;
                }
                case 502: {
                    this.a();
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public final void setEnabled(final boolean e) {
        this.e = e;
        this.repaint();
    }
    
    public final void a(final String a) {
        this.a = a;
        this.b();
        this.repaint();
    }
    
    protected final void b() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        this.resize(fontMetrics.stringWidth(this.a), fontMetrics.getAscent() + 3);
    }
    
    public final boolean isEnabled() {
        return this.e;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.b != null) {
            System.out.println("########## TextButton.paint(): bgImg needs to be handled in paint() ############");
        }
        final Color color = graphics.getColor();
        if (!this.e) {
            graphics.setColor(this.c);
        }
        graphics.setFont(this.getFont());
        final int ascent = graphics.getFontMetrics().getAscent();
        graphics.drawString(this.a, 0, ascent);
        if (this.d) {
            graphics.drawLine(0, ascent + 2, graphics.getFontMetrics().stringWidth(this.a), ascent + 2);
        }
        if (!this.e) {
            graphics.setColor(color);
        }
    }
    
    public b1(final String s, final Font font) {
        this(s, font, null, Color.gray);
    }
    
    public b1(final String a, final Font font, final Image b, final Color c) {
        this.d = false;
        this.e = true;
        this.a = a;
        this.b = b;
        this.c = c;
        this.setFont(font);
        this.b();
    }
}
