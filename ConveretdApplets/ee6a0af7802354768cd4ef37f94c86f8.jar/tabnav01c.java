import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01c extends Canvas
{
    private tabnav01 z6;
    private tabnav01b z9;
    private Image z14;
    private Image z35;
    private Image z36;
    private Vector z29;
    private int z10;
    private int z12;
    private int z18;
    private int z34;
    private int z20;
    private int z15;
    private int z13;
    private int z22;
    private int z16;
    private int z17;
    private int z19;
    private boolean z7;
    private boolean z8;
    private boolean z38;
    private Rectangle z26;
    private int z31;
    private int z21;
    private final int z30 = 8;
    private final int z28 = 3;
    private final int z27 = 6;
    private final int z11 = 18;
    private final String z25 = "menuindent";
    private final String z24 = "autoclose";
    private final String z37 = "menulines";
    
    tabnav01c(final tabnav01 z6, final tabnav01b z7, final int n, final int z8, final int z9) {
        this.z19 = -1;
        this.z8 = false;
        this.z6 = z6;
        this.z9 = z7;
        this.z10 = z8;
        this.z12 = z9;
        this.setBackground(z6.q16[5]);
        this.reshape(1, n, z8, z9);
        this.z29 = new Vector(16, 8);
        this.z18 = z6.q0(z6.q1("menuindent"), 0, 99, 4);
        this.z7 = (z6.q0(z6.q1("autoclose"), 0, 99, 0) > 0);
        this.z38 = (z6.q0(z6.q1("menulines"), 0, 99, 1) > 0);
        final int n2 = z7.q44[1];
        this.z13 = (n2 >> 8) + 1;
        if (z7.q38 > 0 && this.z13 < z7.q38) {
            this.z13 = z7.q38;
        }
        this.z13 += this.z13 % 2;
        this.z20 = (this.z13 - (n2 >> 8)) / 2 + (n2 & 0xFF);
        this.z15 = (this.z13 - z7.q38) / 2;
        this.z22 = (z9 - 6) / this.z13;
        this.z34 = ((z7.q36 > 18) ? z7.q36 : 18);
        if (this.z34 % 2 > 0) {
            --this.z34;
        }
        for (int i = 0; i < z7.q53.size(); ++i) {
            final Enumeration<tabnav01g> elements = z7.q53.elementAt(i).elements();
            int q72 = 1;
            while (elements.hasMoreElements()) {
                q72 = elements.nextElement().q72(z6, i, this.z7, this.z34, this.z18, q72);
            }
        }
        this.z26 = new Rectangle(0, 6, z8, 0);
    }
    
    private void z32(final Graphics graphics, final tabnav01g tabnav01g, final int n) {
        final int q68 = tabnav01g.q68();
        final int n2 = tabnav01g.q65() - this.z31 * 8;
        int n3;
        int n4;
        if (q68 > 0) {
            n3 = n2 - this.z34 / 2;
            n4 = n - this.z13 / 2;
            graphics.drawImage(this.z35, n3, n4, null);
        }
        else {
            n3 = n2 + this.z34 / 2;
            n4 = n;
        }
        for (int n5 = this.z29.indexOf(tabnav01g) - 1; n5 >= this.z21 && n5 > 0; --n5) {
            if (((tabnav01g)this.z29.elementAt(n5)).q68() <= q68) {
                return;
            }
            n4 -= this.z13;
            graphics.drawImage(this.z36, n3, n4, null);
        }
    }
    
    private void z1(final Graphics graphics, final tabnav01g tabnav01g, final int n, final boolean b) {
        final int n2 = tabnav01g.q65() - this.z31 * 8;
        graphics.setFont((tabnav01g.q70() == 2) ? this.z9.q46[1] : this.z9.q46[3]);
        graphics.setColor(this.z6.q16[5]);
        final int n3 = n2 + this.z9.q31(graphics, n2, n + this.z15, tabnav01g.q64(), 3);
        graphics.fillRect(n3 - 2, n, this.z10, this.z13);
        graphics.setColor(this.z6.q16[6]);
        if (b) {
            graphics.fillRect(n3 - 2, n, this.z31 * 8 + (this.z10 - n3 + 1), this.z13);
            graphics.setColor(this.z6.q16[5]);
        }
        graphics.drawString(tabnav01g.q61(), n3, n + this.z20);
    }
    
    private int z33(final Graphics graphics, final boolean b) {
        int n = 0;
        final int n2 = this.z21 + this.z16;
        int n3 = this.z13 + 6;
        int n4 = 0;
        final Enumeration<tabnav01g> elements = (Enumeration<tabnav01g>)this.z29.elements();
        while (elements.hasMoreElements()) {
            final tabnav01g tabnav01g = elements.nextElement();
            if (n >= this.z21 && n < n2) {
                if (b) {
                    this.z1(graphics, tabnav01g, n3, false);
                    ++n4;
                }
                else {
                    this.z32(graphics, tabnav01g, n3);
                }
                n3 += this.z13;
            }
            ++n;
        }
        return n4;
    }
    
    private void z2() {
        final Graphics graphics = this.z14.getGraphics();
        graphics.setColor(this.z6.q16[5]);
        graphics.fillRect(0, this.z13, this.z10, this.z12 + 6);
        if (this.z38) {
            this.z33(graphics, false);
        }
        this.z26.height = this.z33(graphics, true) * this.z13;
        graphics.setColor(this.z6.q16[5]);
        graphics.fillRect(0, this.z13, this.z10, 6);
        graphics.dispose();
    }
    
    private void z23() {
        if (this.z35 == null) {
            final int n = this.z34 / 2;
            this.z35 = this.createImage(n, this.z13);
            final Graphics graphics = this.z35.getGraphics();
            graphics.setColor(this.z6.q16[5]);
            graphics.fillRect(0, 0, n, this.z13);
            graphics.setColor(this.z6.q16[1]);
            for (int i = 1; i < this.z13; i += 2) {
                graphics.drawLine(0, i, 0, i);
            }
            for (int j = 2; j < n; j += 2) {
                graphics.drawLine(j, this.z13 - 1, j, this.z13 - 1);
            }
            graphics.dispose();
            this.z36 = this.createImage(1, this.z13);
            final Graphics graphics2 = this.z36.getGraphics();
            graphics2.setColor(this.z6.q16[5]);
            graphics2.fillRect(0, 0, 1, this.z13);
            graphics2.setColor(this.z6.q16[1]);
            for (int k = 1; k < this.z13; k += 2) {
                graphics2.drawLine(0, k, 0, k);
            }
            graphics2.dispose();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.z14 == null) {
            this.z14 = this.createImage(this.z10, this.z12 + this.z13 + 6);
            this.z23();
        }
        this.z2();
        if (this.z8) {
            if (this.z19 < this.z16) {
                final Graphics graphics2 = this.z14.getGraphics();
                this.z4(graphics2, this.z19, true);
                graphics2.dispose();
            }
            else {
                this.z19 = -1;
            }
            this.z8 = false;
        }
        graphics.drawImage(this.z14, 0, -this.z13, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private int z5(int i, final int n, final boolean b) {
        final int q75 = this.z9.q75();
        final Vector<tabnav01g> vector = this.z9.q53.elementAt(q75);
        while (i < vector.size()) {
            final tabnav01g tabnav01g = vector.elementAt(i++);
            if (tabnav01g.q62() == q75) {
                if (b && tabnav01g.q68() < n) {
                    return i - 1;
                }
                if (tabnav01g.q68() != n) {
                    continue;
                }
                this.z29.addElement(tabnav01g);
                final int z17 = tabnav01g.q65() + tabnav01g.q67() + this.z9.q36;
                if (z17 > this.z17) {
                    this.z17 = z17;
                }
                if (tabnav01g.q70() != 1 || tabnav01g.q69() != 1) {
                    continue;
                }
                i = this.z5(i, n + 1, true);
            }
        }
        return i;
    }
    
    private void z3() {
        this.z29.removeAllElements();
        this.z5(this.z17 = 0, 0, false);
        this.z16 = this.z29.size();
    }
    
    void q56(final boolean b) {
        if (b) {
            this.z21 = 0;
            this.z31 = 0;
        }
        this.z3();
        if (this.z21 > 0 && this.z16 - this.z21 < this.z22) {
            this.z21 = 0;
            this.z3();
        }
        final tabnav01e q32;
        if ((q32 = this.z9.q32(0)) != null) {
            q32.sbSetValues(this.z21, this.z22, this.z16);
        }
        final tabnav01e q33;
        if ((q33 = this.z9.q32(1)) != null) {
            q33.sbSetValues(0, this.z10 / 8, this.z17 / 8 + 1);
        }
    }
    
    void q55(final int z21, final int z22) {
        if (this.z21 != z21 || this.z31 != z22) {
            this.z21 = z21;
            this.z31 = z22;
            this.repaint();
        }
    }
    
    private tabnav01g z4(final Graphics graphics, final int z19, final boolean b) {
        final int n = this.z13 + (z19 - this.z21) * this.z13 + 6;
        if (b) {
            graphics.copyArea(0, n, this.z10, this.z13, 0, -n);
            this.z19 = z19;
        }
        final tabnav01g tabnav01g = this.z29.elementAt(z19);
        this.z1(graphics, tabnav01g, n, true);
        return tabnav01g;
    }
    
    private void z0(final Graphics graphics) {
        graphics.copyArea(0, 0, this.z10, this.z13, 0, this.z26.y + (this.z19 - this.z21 + 1) * this.z13);
    }
    
    private void z39() {
        final Graphics graphics = this.getGraphics();
        graphics.drawImage(this.z14, 0, -this.z13, null);
        graphics.dispose();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.z19 != -1) {
            final tabnav01g tabnav01g = this.z29.elementAt(this.z19);
            if (tabnav01g.q70() == 2) {
                this.z6.q2(2);
                tabnav01g.q74(1);
                final Graphics graphics = this.z14.getGraphics();
                this.z1(graphics, tabnav01g, 0, false);
                this.z4(graphics, this.z19, false);
                graphics.dispose();
                this.z39();
            }
            else {
                this.z8 = true;
                if (tabnav01g.q69() == 1) {
                    tabnav01g.q74(0);
                }
                else {
                    tabnav01g.q74(1);
                }
                this.z6.q2(1);
                if (this.z7 && tabnav01g.q68() == 0) {
                    final int q66 = tabnav01g.q66();
                    final Enumeration<tabnav01g> elements = this.z29.elements();
                    while (elements.hasMoreElements()) {
                        final tabnav01g tabnav01g2 = elements.nextElement();
                        if (tabnav01g2.q68() == 0 && tabnav01g2.q69() == 1 && tabnav01g2.q66() != q66) {
                            tabnav01g2.q74(0);
                        }
                    }
                }
                this.q56(false);
                this.repaint();
            }
            this.z9.q35(tabnav01g);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int z19, final int n) {
        boolean b = false;
        if (z19 == -1) {
            this.z19 = z19;
            this.repaint();
            this.z9.q30(this.z19, 0);
        }
        else if (!this.z8) {
            final Graphics graphics = this.z14.getGraphics();
            if (this.z26.inside(z19, n)) {
                final int n2 = this.z21 + (n - 6) / this.z13;
                if (n2 != this.z19) {
                    if (this.z19 != -1) {
                        this.z0(graphics);
                    }
                    final tabnav01g z20 = this.z4(graphics, n2, true);
                    this.z9.q30(z20.q66(), z20.q70());
                    b = true;
                }
            }
            else if (this.z19 != -1) {
                this.z0(graphics);
                this.z19 = -1;
                this.z9.q30(this.z19, 0);
                b = true;
            }
            graphics.dispose();
            if (b) {
                this.z39();
            }
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
