import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    protected boolean ib;
    protected boolean jb;
    private Rectangle kb;
    private Rectangle E;
    private String lb;
    private int mb;
    private int nb;
    private int ob;
    private int pb;
    private Image qb;
    private p rb;
    
    public k(final Rectangle e, final Image qb, final String lb, final p rb) {
        this.ob = 11;
        this.pb = 20;
        this.E = e;
        this.qb = qb;
        this.lb = lb;
        this.kb = new Rectangle(e.x + (e.width - this.ob) / 2, e.y + (e.height - this.pb) / 2, this.ob, this.pb);
        this.rb = rb;
    }
    
    public void a(final Graphics graphics) {
        if (this.ib) {
            graphics.drawImage(this.qb, this.E.x - 19, this.E.y, null);
            graphics.setColor(Color.orange);
            graphics.fillRect(this.kb.x, this.kb.y, this.kb.width, this.kb.height);
            graphics.setColor(Color.white);
            graphics.drawLine(this.kb.x + this.kb.width / 2, this.kb.y + 2, this.kb.x + this.kb.width / 2, this.kb.y + this.kb.height - 3);
            graphics.drawString(this.lb, p.b(this.lb, true, this.E, graphics), this.E.y);
        }
    }
    
    public void _(final int mb) {
        this.mb = mb;
    }
    
    public int a(final int n) {
        if (this.ib) {
            final Rectangle kb = this.kb;
            kb.x -= this.mb - n;
            final int n2 = this.kb.x + 5;
            if (n2 < this.E.x + 20) {
                this.kb.x = this.E.x + 15;
            }
            else if (n2 > this.E.x + this.E.width - 20) {
                this.kb.x = this.E.x + this.E.width - 25;
            }
            this._(n);
        }
        return 0;
    }
    
    public int b() {
        return 35 - (this.kb.x + 5 - (this.E.x + 20) - 50) / 2;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.E.contains(n, n2);
    }
    
    public boolean a(final int n, final int n2) {
        return this.kb.contains(n, n2);
    }
}
