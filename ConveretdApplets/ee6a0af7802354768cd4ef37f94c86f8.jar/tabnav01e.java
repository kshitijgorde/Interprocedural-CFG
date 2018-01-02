import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class tabnav01e extends Canvas
{
    private Image z8;
    private Image z9;
    private Event z7;
    private boolean z4;
    private boolean z3;
    private int z5;
    private int z6;
    private int z12;
    private int z13;
    private int z14;
    private int z15;
    private int z10;
    private Rectangle rt;
    static final int q59 = 0;
    static final int q58 = 1;
    private int z11;
    
    public tabnav01e(final int z12, final int n, final int n2, final int n3) {
        this.z4 = true;
        this.z3 = false;
        this.z5 = 15;
        this.z6 = 100;
        this.z12 = z12;
        this.rt = new Rectangle();
        this.z7 = new Event(this, 1, null);
        this.setBackground(Color.lightGray);
        this.z2(n, n2, n3);
    }
    
    public void reshape(final int n, final int n2, final int z5, final int z6) {
        this.z5 = z5;
        this.z6 = z6;
        if (this.z8 != null) {
            this.z8 = this.createImage(this.z5, this.z6);
        }
        super.reshape(n, n2, z5, z6);
    }
    
    private void z0() {
        this.z9 = this.createImage(this.z5, this.z6);
        final Graphics graphics = this.z9.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.z5, this.z6);
        graphics.setColor(this.getBackground().brighter());
        for (int i = 1; i < this.z9.getWidth(null); ++i) {
            for (int j = 1; j < this.z9.getHeight(null); ++j) {
                if (i % 2 == 0) {
                    if (j % 2 > 0) {
                        graphics.drawLine(i, j, i, j);
                    }
                }
                else if (j % 2 == 0) {
                    graphics.drawLine(i, j, i, j);
                }
            }
        }
        graphics.setColor(this.getBackground().darker());
        graphics.drawRect(-1, -1, this.z5, this.z6);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        if (this.z8 == null) {
            this.z8 = this.createImage(this.z5, this.z6);
        }
        if (this.z9 == null) {
            this.z0();
        }
        final Graphics graphics2 = this.z8.getGraphics();
        graphics2.drawImage(this.z9, 0, 0, null);
        graphics2.setColor(Color.black);
        graphics2.drawRect(this.rt.x, this.rt.y, this.rt.width, this.rt.height);
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(this.rt.x, this.rt.y, this.rt.width, this.rt.height);
        graphics2.draw3DRect(this.rt.x + 1, this.rt.y + 1, this.rt.width - 2, this.rt.height - 2, true);
        if (this.z4) {
            graphics2.setColor(this.z3 ? Color.white : Color.black);
        }
        else {
            graphics2.setColor(this.getBackground().darker());
        }
        if (this.z12 == 0) {
            int n = 7;
            int n2 = (this.z5 - n) / 2;
            int n3 = this.rt.y + 7;
            for (int i = 0; i < 4; ++i) {
                graphics2.drawLine(n2, n3, n2 + n, n3);
                ++n2;
                --n3;
                n -= 2;
            }
            int n4 = 7;
            int n5 = (this.z5 - n4) / 2;
            int n6 = this.rt.y + this.rt.height - 7;
            for (int j = 0; j < 4; ++j) {
                graphics2.drawLine(n5, n6, n5 + n4, n6);
                ++n5;
                ++n6;
                n4 -= 2;
            }
        }
        else {
            int n7 = 7;
            int n8 = this.rt.x + 7;
            int n9 = (this.z6 - n7) / 2;
            for (int k = 0; k < 4; ++k) {
                graphics2.drawLine(n8, n9, n8, n9 + n7);
                --n8;
                ++n9;
                n7 -= 2;
            }
            int n10 = 7;
            int n11 = this.rt.x + this.rt.width - 7;
            int n12 = (this.z6 - n10) / 2;
            for (int l = 0; l < 4; ++l) {
                graphics2.drawLine(n11, n12, n11, n12 + n10);
                ++n11;
                ++n12;
                n10 -= 2;
            }
        }
        graphics2.dispose();
        graphics.drawImage(this.z8, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void z2(final int n, final int n2, final int n3) {
        this.z15 = ((n2 < 1) ? 1 : n2);
        this.z10 = ((n3 - n2 < 1) ? 1 : (n3 - n2 + 1));
        this.z14 = ((n > this.z10) ? this.z10 : n);
        final int n4 = this.z10 + this.z15;
        this.z13 = 1;
        int n5 = (this.z12 == 0) ? (this.z6 - 1) : (this.z5 - 1);
        if (this.z12 == 0) {
            if (this.z10 > 1) {
                this.z13 = this.z6 / n4;
                n5 = (this.z15 + 1) * this.z13 + this.z6 % n4 - 1;
            }
            this.rt.reshape(0, this.z14 * this.z13, this.z5 - 1, n5);
        }
        else {
            if (this.z10 > 1) {
                this.z13 = this.z5 / n4;
                n5 = (this.z15 + 1) * this.z13 + this.z5 % n4 - 1;
            }
            this.rt.reshape(this.z14 * this.z13, 0, n5, this.z6 - 1);
        }
        this.z4 = ((this.z12 == 0) ? (this.rt.height < this.z6 - 1) : (this.rt.width < this.z5 - 1));
    }
    
    public void sbSetValues(final int n, final int n2, final int n3) {
        this.z2(n, n2, n3);
        this.repaint();
    }
    
    private void z1(final int z14) {
        this.z14 = z14;
        if (this.z14 < 0) {
            this.z14 = 0;
        }
        else if (this.z14 >= this.z10) {
            this.z14 = this.z10 - 1;
        }
        if (this.z12 == 0) {
            this.rt.move(0, this.z14 * this.z13);
        }
        else {
            this.rt.move(this.z14 * this.z13, 0);
        }
        final Graphics graphics = this.getGraphics();
        this.paint(graphics);
        graphics.dispose();
        this.postEvent(this.z7);
    }
    
    public int sbGetValue() {
        return this.z14;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.z4) {
            if (this.rt.inside(n, n2)) {
                this.z3 = true;
                this.z11 = ((this.z12 == 0) ? (n2 - this.rt.y) : (n - this.rt.x));
                this.repaint();
            }
            else {
                int n3;
                if (this.z12 == 0) {
                    n3 = ((n2 < this.rt.y) ? (this.z14 - 1) : (this.z14 + 1));
                }
                else {
                    n3 = ((n < this.rt.x) ? (this.z14 - 1) : (this.z14 + 1));
                }
                this.z1(n3);
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.z3) {
            this.z3 = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.z3) {
            this.z1((this.z12 == 0) ? ((n2 - this.z11) / this.z13) : ((n - this.z11) / this.z13));
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return true;
    }
}
