import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01f extends Canvas
{
    private tabnav01 z2;
    private Image z9;
    private Rectangle r;
    private boolean z5;
    private boolean z4;
    private boolean z3;
    private int z10;
    private int z11;
    private Event z8;
    private static final int z6 = 0;
    private static final int z7 = 1;
    
    tabnav01f(final tabnav01 z2, final int n, final int n2, final int z3) {
        this.z5 = false;
        this.z4 = false;
        this.z3 = false;
        this.z2 = z2;
        this.setBackground(z2.q16[2]);
        this.resize(n, n2);
        this.z10 = z3;
        this.r = new Rectangle(0, 0, n - 1, n2 - 2);
        this.z8 = new Event(this, z3, "TN");
    }
    
    private void z0(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.r.width, this.r.height);
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.r.width, this.r.height);
        if (this.z4) {
            graphics.draw3DRect(0, 0, this.r.width, this.r.height, false);
        }
        else {
            graphics.draw3DRect(1, 1, this.r.width - 2, this.r.height - 2, true);
        }
        graphics.setColor(this.z5 ? Color.black : Color.gray);
        int n = 6;
        int n2 = (this.r.height - n) / 2;
        int n3 = (this.z10 == 0) ? 8 : (this.r.width - 8);
        if (this.z5 && this.z4) {
            ++n2;
        }
        for (int i = 0; i < 4; ++i) {
            graphics.drawLine(n3, n2, n3, n2 + n);
            n3 += ((this.z10 == 0) ? -1 : 1);
            ++n2;
            n -= 2;
        }
        graphics.setColor(this.z2.q16[1]);
        graphics.drawLine(0, this.r.height + 1, this.r.width, this.r.height + 1);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        if (this.z9 == null) {
            this.z9 = this.createImage(this.r.width + 1, this.r.height + 2);
        }
        this.z0(this.z9.getGraphics());
        graphics.drawImage(this.z9, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void q28() {
        if (this.z3 && this.z4 && this.z11++ > 0) {
            this.postEvent(this.z8);
        }
    }
    
    boolean q60(final boolean z5) {
        if (this.z5 != z5) {
            this.z5 = z5;
            this.repaint();
        }
        return this.z5;
    }
    
    private void q74(final boolean z4) {
        if (this.z4 != z4) {
            this.z4 = z4;
            this.repaint();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.z5) {
            this.z3 = true;
            if (!this.z4) {
                this.z4 = true;
                this.repaint();
            }
            if (this.r.inside(n, n2)) {
                this.postEvent(this.z8);
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.z3) {
            this.z3 = false;
            if (this.z4) {
                this.z4 = false;
                this.repaint();
            }
        }
        this.z11 = 0;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.z3) {
            if (this.r.inside(n, n2)) {
                if (!this.z4) {
                    this.z4 = true;
                    this.repaint();
                }
            }
            else if (this.z4) {
                this.z4 = false;
                this.repaint();
            }
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
