import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlaneElement extends Element
{
    PointElement A;
    PointElement B;
    PointElement C;
    PointElement S;
    PointElement T;
    PointElement U;
    boolean isScreen;
    PointElement pivot;
    PointElement D;
    
    PlaneElement() {
        this.isScreen = false;
        this.D = new PointElement();
        super.dimension = 2;
    }
    
    PlaneElement(final PointElement a, final PointElement b, final PointElement c) {
        this.isScreen = false;
        this.D = new PointElement();
        super.dimension = 2;
        this.A = a;
        this.B = b;
        this.C = c;
        this.S = new PointElement();
        this.T = new PointElement();
        this.U = new PointElement();
    }
    
    public String toString() {
        return "[" + super.name + ": " + this.A + " " + this.B + " " + this.C + "]";
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        this.update();
    }
    
    protected void update() {
        if (this.isScreen && (this.A.z != 0.0 || this.B.z != 0.0 || this.C.z != 0.0)) {
            this.isScreen = false;
        }
        this.S.to(this.B).minus(this.A);
        this.T.to(this.C).minus(this.A);
        this.S.times(1.0 / this.S.length());
        final double dot = PointElement.dot(this.T, this.S);
        final PointElement t = this.T;
        t.x -= dot * this.S.x;
        final PointElement t2 = this.T;
        t2.y -= dot * this.S.y;
        final PointElement t3 = this.T;
        t3.z -= dot * this.S.z;
        this.T.times(1.0 / this.T.length());
        this.U.toCross(this.S, this.T);
    }
    
    protected boolean defined() {
        return this.A.defined() && this.B.defined() && this.C.defined();
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
        if (super.nameColor != null && super.name != null && this.defined()) {
            graphics.setColor(super.nameColor);
            this.drawString((int)Math.round((this.B.x + this.C.x) / 2.0), (int)Math.round((this.B.y + this.C.y) / 2.0), graphics, dimension);
        }
    }
    
    protected void drawVertex(final Graphics graphics) {
        if (super.vertexColor != null && this.defined()) {
            this.A.drawVertex(graphics, super.vertexColor);
            this.B.drawVertex(graphics, super.vertexColor);
            this.C.drawVertex(graphics, super.vertexColor);
            this.D.x = this.B.x + this.C.x - this.A.x;
            this.D.y = this.B.y + this.C.y - this.A.y;
            this.D.z = this.B.z + this.C.z - this.A.z;
            this.D.drawVertex(graphics, super.vertexColor);
        }
    }
    
    protected void drawEdge(final Graphics graphics) {
        if (super.edgeColor != null && this.defined()) {
            this.D.x = this.B.x + this.C.x - this.A.x;
            this.D.y = this.B.y + this.C.y - this.A.y;
            this.D.z = this.B.z + this.C.z - this.A.z;
            LineElement.drawEdge(this.A, this.B, graphics, super.edgeColor);
            LineElement.drawEdge(this.B, this.D, graphics, super.edgeColor);
            LineElement.drawEdge(this.D, this.C, graphics, super.edgeColor);
            LineElement.drawEdge(this.C, this.A, graphics, super.edgeColor);
        }
    }
    
    protected void drawFace(final Graphics graphics) {
        if (super.faceColor != null && this.defined()) {
            graphics.setColor(super.faceColor);
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            array[0] = (int)Math.round(this.A.x);
            array2[0] = (int)Math.round(this.A.y);
            array[1] = (int)Math.round(this.B.x);
            array2[1] = (int)Math.round(this.B.y);
            array[2] = (int)Math.round(this.B.x + this.C.x - this.A.x);
            array2[2] = (int)Math.round(this.B.y + this.C.y - this.A.y);
            array[3] = (int)Math.round(this.C.x);
            array2[3] = (int)Math.round(this.C.y);
            graphics.fillPolygon(array, array2, 4);
        }
    }
}
