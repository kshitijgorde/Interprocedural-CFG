// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Component;

public abstract class av extends Component implements aq
{
    ChartManager parent;
    private DataSource bN;
    private Insets bP;
    private Insets bS;
    private Image bO;
    private Graphics bM;
    boolean bL;
    Point bR;
    af bD;
    private boolean bG;
    float bJ;
    float bI;
    float bC;
    float bF;
    boolean bE;
    bi bK;
    private int bQ;
    private static final int bH = 5;
    
    protected av(final ChartManager parent, final DataSource bn, final Insets insets) {
        this.bP = new Insets(30, 20, 30, 20);
        this.bS = new Insets(5, 0, 5, 0);
        this.bL = false;
        this.bR = new Point(0, 0);
        this.bG = false;
        this.bE = false;
        this.bQ = -1;
        this.parent = parent;
        (this.bN = bn).if(this);
        this.bP = (Insets)insets.clone();
        this.bD = new af(this, this.case().right - 10, this.char());
    }
    
    public void dataSourceChanged(final a2 a2) {
        this.a(true);
        this.repaint();
    }
    
    public void a(final bi bi) {
        this.bK = bi;
        this.bD.try = bi;
        this.setBackground(bi.gY);
    }
    
    public synchronized void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(this.bK.gY);
        graphics.fillRect(0, 0, this.case().left - 1, this.getSize().height - this.case().bottom);
        graphics.fillRect(this.bD.for, 0, this.bD.new, this.bD.a);
        graphics.fillRect(this.bD.for, this.bD.a + this.bD.if, this.bD.new, this.getSize().height - (this.bD.a + this.bD.if) + 1);
        this.a(graphics);
    }
    
    protected synchronized void a(final Graphics graphics) {
        if (this.null().D() != 0) {
            this.d();
            graphics.setColor(this.bK.gP);
            graphics.drawRect(this.case().left - 1, this.case().top - 1, this.b() + 1, this.char() + 1);
            graphics.drawImage(this.long(), this.case().left, this.case().top, this);
            this.bD.a(graphics);
            if (this.goto()) {
                this.a(graphics, this.bR.x, this.bR.y);
            }
            this.if(graphics);
        }
    }
    
    protected abstract void d();
    
    protected void a(final Graphics graphics, int n, final int n2) {
        if (n > this.case().left && n < this.case().left + this.b()) {
            if (this.parent.eS.d8 == 8) {
                if (this.parent.eS.dI == -1) {
                    return;
                }
                n = this.parent.eS.case().left + this.parent.eS.g(this.parent.eS.dI - this.null().u());
            }
            graphics.drawLine(n, this.case().top + 1, n, this.case().top + this.char() - 1);
            if (n2 > this.case().top && n2 < this.case().top + this.char() && this.parent.eS.d8 != 8) {
                graphics.drawLine(this.case().left + 1, n2, this.case().left + this.b() - 1, n2);
                this.bD.a(graphics, n2);
            }
        }
    }
    
    public void int(final int bq) {
        this.bQ = bq;
    }
    
    public void if(final Graphics graphics) {
        if (this.bQ > this.case().top && this.bQ > -1) {
            final int n = this.case().left + this.b() - 1;
            final int n2 = 5;
            graphics.setColor(this.getBackground());
            final Color brighter = this.getBackground().brighter().brighter();
            final Color darker = this.getBackground().darker().darker();
            graphics.setColor(brighter);
            graphics.drawLine(this.case().left, this.bQ + 1, n - 3, this.bQ + 1);
            graphics.drawLine(this.case().left + 1, this.bQ, this.case().left + 1, this.bQ + n2 - 2);
            graphics.setColor(darker);
            graphics.drawLine(this.case().left + 2, this.bQ + n2 - 2, n - 1, this.bQ + n2 - 2);
            graphics.drawLine(n - 2, this.bQ + 1, n - 2, this.bQ + n2 - 1);
        }
    }
    
    protected boolean goto() {
        return (this.parent.e1 && this.parent.eS.d8 == 1) || this.parent.eS.d8 == 8;
    }
    
    public float void() {
        return 1.0f;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        super.setBounds(n, n2, n3, n4);
        this.a(width != this.getSize().width, height != this.getSize().height);
    }
    
    public void setBounds(final Rectangle bounds) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        super.setBounds(bounds);
        this.a(width != this.getSize().width, height != this.getSize().height);
    }
    
    protected void a(final boolean b, final boolean b2) {
        this.bD.a(this.case().right - 10, this.char());
        if (b || b2) {
            this.a(true);
            this.c();
        }
    }
    
    protected void a(final Graphics graphics, final int n, final int n2, final int n3, final Color color) {
        final int n4 = 10;
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        for (int i = n; i < n2; i += n4) {
            graphics.drawLine(i, n3, i + n4 / 2, n3);
        }
        graphics.setColor(color2);
    }
    
    protected void a(final Graphics graphics, final Rectangle rectangle) {
        Color color;
        if (this instanceof be) {
            color = this.bK.gN;
        }
        else {
            color = this.bK.gG;
        }
        if (this.bK.g8 == null) {
            graphics.setColor(this.bK.gC);
            graphics.setXORMode(color);
            graphics.drawLine(rectangle.x + 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
            graphics.drawLine(rectangle.x, 1, rectangle.x, this.char() - 1);
            graphics.drawLine(rectangle.x + rectangle.width, 1, rectangle.x + rectangle.width, this.char() - 1);
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + 5, rectangle.y - 2);
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + 5, rectangle.y + 2);
            graphics.drawLine(rectangle.x + rectangle.width - 5, rectangle.y - 2, rectangle.x + rectangle.width, rectangle.y);
            graphics.drawLine(rectangle.x + rectangle.width - 5, rectangle.y + 2, rectangle.x + rectangle.width, rectangle.y);
            graphics.setPaintMode();
            return;
        }
        final int n = (rectangle.width < 0) ? 1 : 0;
        final byte b = (byte)(1 - n);
        final int abs = Math.abs(rectangle.width);
        graphics.setColor(this.bK.g8[n]);
        graphics.setXORMode(color);
        graphics.drawLine(rectangle.x, 1, rectangle.x, this.char() - 1);
        graphics.setColor(this.bK.g8[b]);
        graphics.setXORMode(color);
        graphics.drawLine(rectangle.x + abs, 1, rectangle.x + abs, this.char() - 1);
        graphics.setColor(this.bK.g8[2]);
        graphics.setXORMode(color);
        graphics.fillRect(rectangle.x + 1, 1, abs - 1, this.char() - 1);
        graphics.setPaintMode();
    }
    
    protected Image long() {
        if (this.bO == null) {
            final int b = this.b();
            final int char1 = this.char();
            this.bO = this.createImage((b < 1) ? 1 : b, (char1 < 1) ? 1 : char1);
        }
        return this.bO;
    }
    
    protected Graphics else() {
        if (this.bM == null) {
            this.bM = this.long().getGraphics();
        }
        return this.bM;
    }
    
    protected void c() {
        this.bO = null;
        if (this.bM != null) {
            this.bM.dispose();
            this.bM = null;
        }
    }
    
    public int b() {
        final int n = this.getSize().width - (this.case().left + this.case().right);
        return (n < 0) ? 0 : n;
    }
    
    public int char() {
        final int n = this.getSize().height - (this.case().top + this.case().bottom);
        return (n < 0) ? 0 : n;
    }
    
    public void a(final Insets bp) {
        this.bP = bp;
    }
    
    public Insets case() {
        return this.bP;
    }
    
    public void if(final Insets bs) {
        this.bS = bs;
    }
    
    public Insets byte() {
        return this.bS;
    }
    
    public void if(final DataSource bn) {
        if (this.bN != null) {
            this.bN.a(this);
        }
        this.bN = bn;
        if (this.bN != null) {
            this.bN.if(this);
        }
    }
    
    public DataSource null() {
        return this.bN;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(1 + this.case().left + this.case().right + this.byte().left + this.byte().right, 1 + this.case().top + this.case().bottom + this.byte().top + this.byte().bottom);
    }
    
    public void a(final boolean bg) {
        this.bG = bg;
    }
    
    public boolean try() {
        return this.bG;
    }
}
