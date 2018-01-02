import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Connector
{
    int L1x;
    int L1y;
    int L2x;
    int L2y;
    int x;
    int y;
    Line l1;
    Line l2;
    Line invisible;
    Line final1;
    boolean first;
    Color color;
    
    Connector(final int x, final int y, final Line l1, final Line l2) {
        this.first = false;
        this.color = new Color(255, 0, 0);
        this.x = x;
        this.y = y;
        this.l1 = l1;
        this.l2 = l2;
        l1.setColor(this.color);
        l2.setColor(this.color);
    }
    
    public double getAngle() {
        if (this.x == this.l1.getX1() && this.y == this.l1.getY1()) {
            this.L1x = this.l1.getX2();
            this.L1y = this.l1.getY2();
        }
        else {
            this.L1x = this.l1.getX1();
            this.L1y = this.l1.getY1();
        }
        if (this.x == this.l2.getX1() && this.y == this.l2.getY1()) {
            this.L2x = this.l2.getX2();
            this.L2y = this.l2.getY2();
        }
        else {
            this.L2x = this.l2.getX1();
            this.L2y = this.l2.getY1();
        }
        this.invisible = new Line(this.L1x, this.L1y, this.L2x, this.L2y);
        final double n = -1.0 * (1.0 / this.invisible.getSlope());
        final double n2 = this.getY() - n * this.getX();
        final double n3 = this.invisible.getY1() - this.invisible.getSlope() * this.invisible.getX1();
        final double n4 = (n2 - n3) / (this.invisible.getSlope() - n);
        this.final1 = new Line((int)n4, (int)(this.invisible.getSlope() * n4 + n3), this.x, this.y);
        final double acos = Math.acos(this.final1.getLength() / this.l1.getLength());
        final double acos2 = Math.acos(this.final1.getLength() / this.l2.getLength());
        System.out.println(String.valueOf(Math.min(Math.abs(this.l1.getLength()), Math.abs(this.l2.getLength()))) + " " + Math.min(Math.max(Math.abs(this.l1.getLength()), Math.abs(this.l2.getLength())), Math.abs(this.invisible.getLength())) + " " + Math.max(Math.max(Math.abs(this.l1.getLength()), Math.abs(this.l2.getLength())), Math.abs(this.invisible.getLength())));
        if (this.invisible.getSlope() > 0.0 || this.invisible.getSlope() < 0.0) {
            System.out.println("Real Number");
        }
        else {
            if (this.l1.getSlope() > 0.0 || this.l1.getSlope() < 0.0) {
                this.final1 = this.l1;
            }
            else {
                this.final1 = this.l2;
            }
            System.out.println("Transfer");
        }
        double n7;
        if ((this.final1.getX1() > this.L1x && this.final1.getX1() > this.L2x) || (this.final1.getY1() > this.L1y && this.final1.getY1() > this.L2y) || (this.final1.getX1() < this.L1x && this.final1.getX1() < this.L2x) || (this.final1.getY1() < this.L1y && this.final1.getY1() < this.L2y)) {
            System.out.println("Acute");
            final double n5 = this.l2.getLength() / this.final1.getLength();
            final double n6 = this.l1.getLength() / this.final1.getLength();
            final double acos3 = Math.acos(this.final1.getLength() / this.l2.getLength());
            final double acos4 = Math.acos(this.final1.getLength() / this.l1.getLength());
            if (this.l1.getLength() <= this.l2.getLength()) {
                n7 = acos3 - acos4;
            }
            else {
                n7 = acos4 - acos3;
            }
            System.out.println("#" + this.l2.getLength() / this.final1.getLength());
        }
        else {
            System.out.println("obtuse");
            n7 = Math.abs(acos) + Math.abs(acos2);
        }
        final double n8 = n7 * 57.29577951308232;
        System.out.println(n8);
        if (n8 == 0.0) {
            System.out.println("Error");
        }
        return n8 * 10.0;
    }
    
    public int getAX() {
        return ((this.x + Math.abs((this.invisible.getX1() + this.invisible.getX2()) / 2)) / 2 + this.x) / 2;
    }
    
    public int getAY() {
        return ((this.y + Math.abs((this.invisible.getY1() + this.invisible.getY2()) / 2)) / 2 + this.y) / 2;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Line getL1() {
        return this.l1;
    }
    
    public Line getL2() {
        return this.l2;
    }
}
