import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Line
{
    Point p1;
    Point p2;
    Point start;
    Point end;
    boolean isTrans;
    boolean outOfOrder;
    int lengthOfXLeg;
    int lineLength;
    int b;
    int inc;
    int y1;
    int y2;
    int transLength;
    int lOfTransLine;
    double m;
    double xvelocity;
    double angle;
    double x1;
    double x2;
    Color transColor;
    
    public Line(final Point _p1, final Point _p2) {
        this.isTrans = false;
        this.outOfOrder = false;
        this.lengthOfXLeg = 7;
        this.transLength = 20;
        this.transColor = null;
        if (_p1.equals(_p2)) {
            return;
        }
        this.p1 = _p1;
        this.p2 = _p2;
        this.lineLength = this.calculateLineLength(this.p1, this.p2);
        if (this.p1.y == this.p2.y) {
            this.m = 0.0;
        }
        else {
            this.m = (this.p1.y - this.p2.y) / (this.p1.x - this.p2.x);
        }
        this.b = (int)Math.round(-1.0 * this.m * this.p2.x + this.p2.y);
        if (this.p2.y != this.p1.y) {
            this.angle = Math.atan((this.p2.y - this.p1.y) / (this.p2.x - this.p1.x));
        }
        else if (this.p1.x < this.p2.x) {
            this.angle = 0.0;
        }
        else {
            this.angle = 3.141592653589793;
        }
        this.xvelocity = Math.cos(this.angle);
    }
    
    public boolean isTransmitting() {
        return this.isTrans;
    }
    
    public boolean isOutOfOrder() {
        return this.outOfOrder;
    }
    
    public void setOutOfOrder(final boolean b) {
        this.outOfOrder = b;
    }
    
    public void startTransmission(final Point _start, final Point _end, final Color c) {
        this.transColor = c;
        if (this.calculateLineLength(_start, this.p1) < this.calculateLineLength(_start, this.p2)) {
            this.start = this.p1;
            this.end = this.p2;
            if (this.start.x - this.end.x < 0) {
                this.inc = 1;
            }
            else {
                this.inc = -1;
            }
        }
        else {
            this.start = this.p2;
            this.end = this.p1;
            if (this.start.x - this.end.x < 0) {
                this.inc = 1;
            }
            else {
                this.inc = -1;
            }
        }
        this.x1 = this.start.x;
        this.x2 = this.start.x;
        this.y1 = this.start.y;
        this.y2 = this.start.y;
        this.lOfTransLine = 0;
        this.isTrans = true;
    }
    
    private int calculateLineLength(final Point p, final Point pp) {
        return (int)Math.sqrt(Math.pow(p.x - pp.x, 2.0) + Math.pow(p.y - pp.y, 2.0));
    }
    
    public int getLineLength() {
        return this.calculateLineLength(this.p1, this.p2);
    }
    
    public Point getMidPoint() {
        return new Point((this.p2.x + this.p1.x) / 2, (this.p2.y + this.p1.y) / 2);
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.drawLine(this.p1.x, this.p1.y, this.p2.x, this.p2.y);
        if (this.outOfOrder) {
            this.drawOutOfOrderX(g);
        }
        if (this.isTrans) {
            g.setColor(this.transColor);
            if (this.drawTransFrame(g)) {
                this.isTrans = false;
            }
        }
    }
    
    private void drawOutOfOrderX(final Graphics g) {
        g.setColor(Color.red);
        final Point p = this.getMidPoint();
        g.drawLine(p.x, p.y, p.x + this.lengthOfXLeg, p.y + this.lengthOfXLeg);
        g.drawLine(p.x, p.y, p.x - this.lengthOfXLeg, p.y + this.lengthOfXLeg);
        g.drawLine(p.x, p.y, p.x + this.lengthOfXLeg, p.y - this.lengthOfXLeg);
        g.drawLine(p.x, p.y, p.x - this.lengthOfXLeg, p.y - this.lengthOfXLeg);
        g.drawLine(p.x + 1, p.y, p.x + this.lengthOfXLeg, p.y + this.lengthOfXLeg);
        g.drawLine(p.x - 1, p.y, p.x - this.lengthOfXLeg, p.y + this.lengthOfXLeg);
        g.drawLine(p.x + 1, p.y, p.x + this.lengthOfXLeg, p.y - this.lengthOfXLeg);
        g.drawLine(p.x - 1, p.y, p.x - this.lengthOfXLeg, p.y - this.lengthOfXLeg);
    }
    
    private boolean drawTransFrame(final Graphics g) {
        if (Math.round(this.x1) != this.end.x) {
            this.x1 += this.inc * this.xvelocity;
        }
        if (this.lOfTransLine > this.transLength || Math.round(this.x1) == this.end.x) {
            this.x2 += this.inc * this.xvelocity;
        }
        this.y1 = (int)Math.round(this.x1 * this.m) + this.b;
        this.y2 = (int)Math.round(this.x2 * this.m) + this.b;
        this.lOfTransLine = this.calculateLineLength(new Point((int)this.x1, this.y1), new Point((int)this.x2, this.y2));
        if (this.x2 == this.x1) {
            return true;
        }
        g.drawLine((int)Math.round(this.x1), this.y1, (int)Math.round(this.x2), this.y2);
        return false;
    }
}
