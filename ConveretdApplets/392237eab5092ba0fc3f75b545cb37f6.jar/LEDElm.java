import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class LEDElm extends DiodeElm
{
    double colorR;
    double colorG;
    double colorB;
    Point ledLead1;
    Point ledLead2;
    Point ledCenter;
    
    public LEDElm(final int n, final int n2) {
        super(n, n2);
        this.fwdrop = 2.1024259;
        this.setup();
        this.colorR = 1.0;
        final double n3 = 0.0;
        this.colorB = n3;
        this.colorG = n3;
    }
    
    public LEDElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        if ((n5 & 0x1) == 0x0) {
            this.fwdrop = 2.1024259;
        }
        this.setup();
        this.colorR = new Double(stringTokenizer.nextToken());
        this.colorG = new Double(stringTokenizer.nextToken());
        this.colorB = new Double(stringTokenizer.nextToken());
    }
    
    int getDumpType() {
        return 162;
    }
    
    String dump() {
        return super.dump() + " " + this.colorR + " " + this.colorG + " " + this.colorB;
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 12;
        this.ledLead1 = this.interpPoint(this.point1, this.point2, 0.5 - n / this.dn);
        this.ledLead2 = this.interpPoint(this.point1, this.point2, 0.5 + n / this.dn);
        this.ledCenter = this.interpPoint(this.point1, this.point2, 0.5);
    }
    
    void draw(final Graphics graphics) {
        if (this.needsHighlight() || this == LEDElm.sim.dragElm) {
            super.draw(graphics);
            return;
        }
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.ledLead1);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.ledLead2, this.point2);
        graphics.setColor(Color.gray);
        int n = 12;
        CircuitElm.drawThickCircle(graphics, this.ledCenter.x, this.ledCenter.y, n);
        n -= 4;
        double n2 = 255.0 * this.current / 0.01;
        if (n2 > 255.0) {
            n2 = 255.0;
        }
        graphics.setColor(new Color((int)(this.colorR * n2), (int)(this.colorG * n2), (int)(this.colorB * n2)));
        graphics.fillOval(this.ledCenter.x - n, this.ledCenter.y - n, n * 2, n * 2);
        this.setBbox(this.point1, this.point2, n);
        this.updateDotCount();
        this.drawDots(graphics, this.point1, this.ledLead1, this.curcount);
        this.drawDots(graphics, this.point2, this.ledLead2, -this.curcount);
        this.drawPosts(graphics);
    }
    
    void getInfo(final String[] array) {
        super.getInfo(array);
        array[0] = "LED";
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return super.getEditInfo(n);
        }
        if (n == 1) {
            return new EditInfo("Red Value (0-1)", this.colorR, 0.0, 1.0).setDimensionless();
        }
        if (n == 2) {
            return new EditInfo("Green Value (0-1)", this.colorG, 0.0, 1.0).setDimensionless();
        }
        if (n == 3) {
            return new EditInfo("Blue Value (0-1)", this.colorB, 0.0, 1.0).setDimensionless();
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            super.setEditValue(0, editInfo);
        }
        if (n == 1) {
            this.colorR = editInfo.value;
        }
        if (n == 2) {
            this.colorG = editInfo.value;
        }
        if (n == 3) {
            this.colorB = editInfo.value;
        }
    }
}
