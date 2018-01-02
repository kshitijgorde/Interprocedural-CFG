import java.awt.Cursor;
import java.awt.AWTEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
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
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.a.Y, 0, this.a.bb));
        return new Dimension(fontMetrics.charWidth('a') * (this.i.length() + 2), fontMetrics.getHeight() + 4);
    }
    
    public Dimension getPreferredSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.a.Y, 0, this.a.bb));
        return new Dimension(fontMetrics.charWidth('a') * (this.i.length() + 2), fontMetrics.getHeight() + 4);
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        int b;
        final int n = b = (this.b ? 1 : 0);
        if (m == 0) {
            if (n != 0) {
                b = 1;
            }
            else {
                b = 0;
            }
        }
        final int n2 = b;
        final boolean c = this.c;
        Label_0222: {
            if (m == 0) {
                if (c) {
                    graphics.drawImage(this.a.x, 0, 0, 10, height, 30, 41, 40, 61, null);
                    graphics.drawImage(this.a.x, 10, 0, width - 10, height, 40, 41, 50, 61, null);
                    graphics.drawImage(this.a.x, width - 10, 0, width, height, 50, 41, 60, 61, null);
                    if (m == 0) {
                        break Label_0222;
                    }
                }
                graphics.drawImage(this.a.x, 0, 0, 10, height, 0, 41, 10, 61, null);
                graphics.drawImage(this.a.x, 10, 0, width - 10, height, 10, 41, 20, 61, null);
                graphics.drawImage(this.a.x, width - 10, 0, width, height, 20, 41, 30, 61, null);
            }
        }
        graphics.setColor(this.a.i);
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.drawString(this.i, width / 2 - fontMetrics.charWidth('a') * this.i.length() / 2, height / 2 + fontMetrics.getHeight() / 3 + n2);
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        final int m = fb.m;
        int n2;
        int id;
        final int n = id = (n2 = awtEvent.getID());
        int n5;
        int n4;
        final int n3 = n4 = (n5 = 501);
        Label_0125: {
            if (m == 0) {
                if (n == n3) {
                    this.b = true;
                    this.repaint();
                    if (m == 0) {
                        break Label_0125;
                    }
                }
                final int n6;
                id = (n6 = (n2 = awtEvent.getID()));
                final int n7;
                n4 = (n7 = (n5 = 502));
            }
            if (m == 0) {
                if (n == n3) {
                    this.b = false;
                    this.repaint();
                    this.a();
                    if (m == 0) {
                        break Label_0125;
                    }
                }
                n2 = (id = awtEvent.getID());
                n5 = (n4 = 504);
            }
            if (m == 0) {
                if (id == n4) {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    this.c = true;
                    this.repaint();
                    if (m == 0) {
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
