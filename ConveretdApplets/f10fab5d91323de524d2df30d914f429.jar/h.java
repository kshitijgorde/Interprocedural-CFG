import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class h extends Canvas
{
    CHAT a;
    static Color a;
    boolean a;
    static String[] a;
    String a;
    public int b;
    boolean b;
    int a;
    
    public h(final CHAT a) {
        this.a = false;
        this.a = null;
        this.b = 0;
        this.a = 15;
        this.setSize(50, 5);
        this.a = a;
    }
    
    public h(final CHAT a, final String a2) {
        this.a = false;
        this.a = null;
        this.b = 0;
        this.a = 15;
        this.setSize(0, 20);
        this.a = a;
        this.a = a2;
    }
    
    public h(final Color color, final Color a, final String[] a2) {
        this.a = false;
        this.a = null;
        this.b = 0;
        this.a = 15;
        this.setSize(200, 100);
        this.setFont(new Font("SansSerif", 0, 11));
        this.a = true;
        this.setBackground(Color.lightGray);
        h.a = a;
        h.a = a2;
    }
    
    static int a(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawString(h.a[17], 28, 20);
        graphics.drawString(h.a[18], 20, 40);
        graphics.drawString(h.a[19], 20, 60);
        final String s = h.a[20];
        graphics.setColor(Color.blue);
        graphics.drawString(s, 50, 80);
        final int stringWidth = graphics.getFontMetrics().stringWidth(s);
        graphics.drawLine(50, 82, stringWidth + 50, 82);
        return stringWidth;
    }
    
    public void a() {
        if (this.b == this.a.a.a.equals(" ")) {
            return;
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final boolean a = d.a;
        h h = this;
        h h2 = this;
        if (!a) {
            if (this.a) {
                this.b = a(graphics);
                return;
            }
            h = this;
            h2 = this;
        }
        Label_0190: {
            if (!a) {
                if (h2.a == null) {
                    break Label_0190;
                }
                h = this;
            }
            boolean b2;
            final boolean b = b2 = this.a.a.a.equals(" ");
            h.b = b;
            Label_0185: {
                if (!a) {
                    if (b) {
                        graphics.setColor(Color.lightGray);
                        graphics.fillRect(3, 3, this.getBounds().width - 6, 15);
                        this.a.a[0].setText("");
                    }
                    graphics.setColor(Color.black);
                    if (a) {
                        break Label_0185;
                    }
                    b2 = this.a.h;
                }
                Label_0171: {
                    if (b2) {
                        h h3 = this;
                        if (!a) {
                            if (this.a != 15) {
                                break Label_0171;
                            }
                            h3 = this;
                        }
                        h3.a = this.getSize().width - graphics.getFontMetrics(this.getFont()).stringWidth(this.a) - 15;
                    }
                }
                graphics.drawString(this.a, this.a, 15);
            }
            if (!a) {
                return;
            }
        }
        final int n = 1 + this.getSize().width / this.a.o;
        int n2 = 0;
        while (true) {
            Label_0241: {
                if (!a) {
                    break Label_0241;
                }
                graphics.drawImage(this.a.a, this.a.o * n2, 0, null);
                ++n2;
            }
            if (n2 < n) {
                continue;
            }
            break;
        }
    }
}
