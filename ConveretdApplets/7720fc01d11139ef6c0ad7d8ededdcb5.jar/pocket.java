import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class pocket
{
    xyVector m_position;
    int m_diameter;
    
    int radius() {
        return this.m_diameter / 2;
    }
    
    pocket(final int n, final int n2, final int n3) {
        this.m_position = new xyVector(n, n2);
        this.m_diameter = n3 * 2;
    }
    
    boolean testPot(final poolBall poolBall) {
        final xyVector xyVector = new xyVector(poolBall.m_position);
        xyVector.subtract(this.m_position);
        return xyVector.magnitude() < this.radius();
    }
    
    void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillOval((int)this.m_position.x() - this.radius(), (int)this.m_position.y() - this.radius(), this.m_diameter, this.m_diameter);
    }
}
