import java.awt.Cursor;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends Canvas
{
    private final esChat a;
    boolean b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    ActionListener g;
    String h;
    private String i;
    
    c(final esChat a, final String s) {
        this.a = a;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.i = s;
        this.h = s;
        this.enableEvents(16L);
    }
    
    public void a(final ActionListener actionListener) {
        this.g = AWTEventMulticaster.add(this.g, actionListener);
    }
    
    private void a() {
        if (this.g != null) {
            this.g.actionPerformed(new ActionEvent(this, 1001, this.h));
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.getFontMetrics(new Font(this.a.eb, 0, this.a.hb)).stringWidth(this.i) + 20, 25);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.getFontMetrics(new Font(this.a.eb, 0, this.a.hb)).stringWidth(this.i) + 20, 25);
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final boolean b = this.b;
        if (!r && !b) {}
        final boolean b2 = this.b;
        Label_0317: {
            if (!r) {
                if (b2) {
                    graphics.drawImage(this.a.B, 0, 0, 10, height, 0, 40, 10, 60, this);
                    graphics.drawImage(this.a.B, 10, 0, width - 10, height, 10, 40, 20, 60, this);
                    graphics.drawImage(this.a.B, width - 10, 0, width, height, 20, 40, 30, 60, this);
                    if (!r) {
                        break Label_0317;
                    }
                }
                final boolean c = this.c;
            }
            if (!r) {
                if (b2) {
                    graphics.drawImage(this.a.B, 0, 0, 10, height, 0, 20, 10, 40, this);
                    graphics.drawImage(this.a.B, 10, 0, width - 10, height, 10, 20, 20, 40, this);
                    graphics.drawImage(this.a.B, width - 10, 0, width, height, 20, 20, 30, 40, this);
                    if (!r) {
                        break Label_0317;
                    }
                }
                graphics.drawImage(this.a.B, 0, 0, 10, height, 0, 0, 10, 20, this);
                graphics.drawImage(this.a.B, 10, 0, width - 10, height, 10, 0, 20, 20, this);
                graphics.drawImage(this.a.B, width - 10, 0, width, height, 20, 0, 30, 20, this);
            }
        }
        final int n = width / 2 - this.getFontMetrics(graphics.getFont()).stringWidth(this.i) / 2;
        graphics.setColor(Color.white);
        graphics.drawString(this.i, n + 1, 17);
        graphics.setColor(this.a.j);
        graphics.drawString(this.i, n, 16);
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        final boolean r = d.r;
        int n2;
        int id;
        final int n = id = (n2 = awtEvent.getID());
        int n5;
        int n4;
        final int n3 = n4 = (n5 = 501);
        Label_0125: {
            if (!r) {
                if (n == n3) {
                    this.b = true;
                    this.repaint();
                    if (!r) {
                        break Label_0125;
                    }
                }
                final int n6;
                id = (n6 = (n2 = awtEvent.getID()));
                final int n7;
                n4 = (n7 = (n5 = 502));
            }
            if (!r) {
                if (n == n3) {
                    this.repaint();
                    this.b = false;
                    this.a();
                    if (!r) {
                        break Label_0125;
                    }
                }
                n2 = (id = awtEvent.getID());
                n5 = (n4 = 504);
            }
            if (!r) {
                if (id == n4) {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    this.c = true;
                    this.repaint();
                    if (!r) {
                        break Label_0125;
                    }
                }
                n2 = awtEvent.getID();
                n5 = 505;
            }
            if (n2 == n5) {
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.c = false;
                this.repaint();
            }
        }
        super.processEvent(awtEvent);
    }
}
