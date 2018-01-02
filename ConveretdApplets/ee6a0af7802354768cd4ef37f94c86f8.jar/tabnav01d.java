import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01d extends Panel
{
    private tabnav01 z3;
    private tabnav01b z6;
    private tabnav01f z7;
    private tabnav01f z8;
    private boolean z4;
    private boolean z5;
    private int z11;
    private int z13;
    private int z12;
    private int z9;
    private int z17;
    private int z15;
    private int z19;
    private int z27;
    private int z28;
    private int z16;
    private Image z14;
    private int z18;
    private Vector z26;
    private static final int z20 = 2;
    private static final int z23 = 5;
    private static final int z21 = 3;
    private static final int z10 = 30;
    private static final int z24 = 9;
    private static final int z22 = 1;
    private static final int z25 = 2;
    
    tabnav01d(final tabnav01 z3, final tabnav01b z4, final int z5, final int z6, final int n) {
        this.z5 = false;
        this.z16 = -1;
        this.z4 = (z6 > 0);
        this.z3 = z3;
        this.z6 = z4;
        this.z11 = z5;
        this.z13 = z6;
        this.z15 = ((n > 0) ? 1 : 0);
        this.setLayout(null);
        this.setBackground(z3.q16[0]);
        this.reshape(0, 0, z5, this.z4 ? z6 : 1);
        this.z26 = new Vector(8, 4);
        final int n2 = z4.q44[0];
        this.z27 = z6 - ((n2 >> 8) - (n2 & 0xFF) + 3);
        this.z28 = 1;
        if (this.z4) {
            this.z9 = 2;
            for (int i = 0; i < z4.q54.size(); ++i) {
                final tabnav01g tabnav01g = z4.q54.elementAt(i);
                int n3 = tabnav01g.q67() + 10 - 1;
                if (tabnav01g.q64() > -1) {
                    n3 += z4.q36 + 3;
                }
                this.z26.addElement(new Rectangle(this.z9, this.z28, n3, z6 + 4));
                this.z9 += n3;
                if (i == 0) {
                    tabnav01g.q74(1);
                }
            }
            this.z9 += 2;
            if (this.z9 > (this.z12 = z5)) {
                this.z12 -= 30;
                final int n4 = z5 - 30;
                this.z7 = new tabnav01f(z3, 15, z6, 0);
                this.z8 = new tabnav01f(z3, 15, z6, 1);
                this.z7.move(n4, 0);
                this.z8.move(n4 + 15, 0);
                this.add(this.z7);
                this.add(this.z8);
            }
            else if (this.z9 < z5) {
                this.z9 = z5;
            }
            ++this.z9;
        }
    }
    
    private void z0(final Graphics graphics, final int n) {
        final boolean b = n == this.z18;
        final tabnav01g tabnav01g = this.z6.q54.elementAt(n);
        final Rectangle rectangle = this.z26.elementAt(n);
        int n2 = rectangle.x + 5;
        final int n3 = b ? (rectangle.y - 1) : rectangle.y;
        graphics.setColor(b ? this.z3.q16[5] : this.z3.q16[3]);
        graphics.fillRoundRect(rectangle.x, n3, rectangle.width, rectangle.height, 9, 9);
        final int q64;
        if ((q64 = tabnav01g.q64()) > -1) {
            final int n4 = this.z28 + (this.z13 - this.z28 - this.z6.q38) / 2;
            n2 += this.z6.q31(graphics, n2, b ? n4 : (n4 + 1), q64, 3);
        }
        graphics.setColor(this.z3.q16[1]);
        graphics.drawRoundRect(rectangle.x, n3, rectangle.width, rectangle.height, 9, 9);
        graphics.setColor(this.z3.q16[4]);
        graphics.drawString(tabnav01g.q61(), n2, b ? (this.z27 - 1) : this.z27);
    }
    
    private Rectangle z1(final int n) {
        for (int i = 0; i < this.z26.size(); ++i) {
            final Rectangle rectangle = this.z26.elementAt(i);
            if (rectangle.inside(n, this.z28 + 1)) {
                return rectangle;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        if (this.z4) {
            if (this.z14 == null) {
                this.z14 = this.createImage(this.z9, this.z13);
            }
            if (this.z17 == 1) {
                final Rectangle z1;
                if ((z1 = this.z1(this.z19 - 1)) != null) {
                    final int n = this.z19 - z1.x;
                    if (n > 0) {
                        this.z19 -= n + 2 + 4;
                    }
                    if (this.z19 < 0) {
                        this.z19 = 0;
                    }
                }
            }
            else {
                final Rectangle z2;
                if (this.z17 == 2 && (z2 = this.z1(this.z19 + this.z12 + 1)) != null) {
                    final int n2 = z2.x + z2.width - (this.z19 + this.z12) + 1;
                    if (n2 > 0) {
                        this.z19 += n2 + 2 + 3;
                    }
                    if (this.z19 + this.z12 > this.z9) {
                        this.z19 -= 3;
                    }
                }
            }
            this.z17 = 0;
            final Graphics graphics2 = this.z14.getGraphics();
            graphics2.setColor(this.z3.q16[0]);
            graphics2.fillRect(this.z19, 0, this.z12, this.z13);
            if (this.z15 == 0) {
                this.z3.q3(graphics2, this.z6.q47[1], new Rectangle(this.z19 - this.z3.q19, 0, this.z19 + this.z12, this.z13));
            }
            graphics2.setFont(this.z6.q46[0]);
            for (int i = 0; i < this.z26.size(); ++i) {
                if (i != this.z18) {
                    this.z0(graphics2, i);
                }
            }
            graphics2.setColor(this.z3.q16[1]);
            graphics2.drawLine(0, this.z13 - 1, this.z9, this.z13 - 1);
            this.z0(graphics2, this.z18);
            final Rectangle z3;
            if ((z3 = this.z1(this.z19)) != null && this.z19 > z3.x) {
                graphics2.setColor(this.z3.q16[1]);
                graphics2.drawLine(this.z19, this.z28, this.z19, this.z13);
            }
            graphics2.dispose();
            graphics.drawImage(this.z14, -this.z19, 0, null);
        }
        if (this.z7 != null) {
            this.z7.q60(this.z19 > 0);
            this.z8.q60(this.z19 + this.z12 < this.z9);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    int q57() {
        return this.z18;
    }
    
    void q28() {
        if (this.z7 != null) {
            this.z7.q28();
            this.z8.q28();
        }
    }
    
    private int z2(final int n, final int n2) {
        if (n >= 0 && n < this.z12) {
            for (int i = 0; i < this.z26.size(); ++i) {
                if (((Rectangle)this.z26.elementAt(i)).inside(n + this.z19, n2)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof tabnav01f && event.id < 2) {
            this.z17 = ((event.id == 0) ? 1 : 2);
            this.repaint();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int z2 = this.z2(n, n2);
        if (z2 != -1) {
            if (z2 != this.z18) {
                this.z6.q54.elementAt(this.z18).q74(0);
                final tabnav01g tabnav01g = this.z6.q54.elementAt(z2);
                tabnav01g.q74(1);
                this.z18 = z2;
                this.z3.q2(0);
                this.z6.q34();
                this.z6.q35(tabnav01g);
            }
            final Rectangle rectangle = this.z26.elementAt(z2);
            if (rectangle.x < this.z19) {
                this.z17 = 1;
            }
            else if (rectangle.x + rectangle.width > this.z19 + this.z12) {
                this.z17 = 2;
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int z2 = this.z2(n, n2);
        if (z2 > -1) {
            if (z2 != this.z16) {
                this.z16 = z2;
                this.z6.q30(this.z16, 0);
            }
        }
        else if (this.z16 != -1) {
            this.z16 = -1;
            this.z6.q30(this.z16, 0);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return this.mouseMove(event, -1, n2);
    }
}
