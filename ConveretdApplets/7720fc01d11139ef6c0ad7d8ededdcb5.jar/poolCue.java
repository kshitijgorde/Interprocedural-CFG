import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class poolCue
{
    xyVector m_prevAnchorPoint;
    xyVector m_prevControlPoint;
    xyVector m_anchorPoint;
    xyVector m_controlPoint;
    xyVector m_impact;
    private boolean m_wasActive;
    poolBall m_targetBall;
    private boolean m_isActive;
    final double m_cuePower;
    
    poolCue(final poolBall targetBall) {
        this.m_cuePower = 5.0;
        this.m_wasActive = false;
        this.m_isActive = false;
        this.m_anchorPoint = targetBall.m_position;
        this.m_prevAnchorPoint = new xyVector(targetBall.m_position);
        this.m_controlPoint = new xyVector(targetBall.m_position);
        this.m_prevControlPoint = new xyVector(targetBall.m_position);
        this.m_targetBall = targetBall;
    }
    
    void activate(final int n, final int n2) {
        this.m_anchorPoint = new xyVector(n, n2);
        this.m_prevAnchorPoint = new xyVector(n, n2);
        this.m_controlPoint = new xyVector(n, n2);
        this.m_prevControlPoint = new xyVector(n, n2);
        this.m_isActive = true;
    }
    
    void release(final int n, final int n2) {
        this.drag(n, n2);
        if (this.m_isActive && !this.m_anchorPoint.isEqualTo(this.m_controlPoint) && this.m_targetBall.testCollision(this.m_anchorPoint)) {
            final double magnitude = this.m_impact.magnitude();
            this.m_impact.scale(1.0 / Math.sqrt(magnitude));
            final xyVector xyVector = new xyVector(this.m_anchorPoint);
            xyVector.subtract(this.m_targetBall.m_position);
            xyVector.scale(magnitude / this.m_targetBall.radius());
            this.m_targetBall.impulse(this.m_impact, xyVector);
        }
        this.m_isActive = false;
    }
    
    void drag(final int n, final int n2) {
        this.m_controlPoint = new xyVector(n, n2);
        (this.m_impact = new xyVector(this.m_anchorPoint)).subtract(this.m_controlPoint);
        this.m_impact.scale(0.005);
        if (this.m_impact.magnitude() > 1.0) {
            this.m_impact = this.m_impact.unit();
            (this.m_controlPoint = new xyVector(this.m_impact)).scale(-200.0);
            this.m_controlPoint.add(this.m_anchorPoint);
        }
    }
    
    void unPaint(final Graphics graphics) {
        if (this.m_wasActive) {
            graphics.setColor(Color.green);
            graphics.drawLine((int)this.m_prevAnchorPoint.x(), (int)this.m_prevAnchorPoint.y(), (int)this.m_prevControlPoint.x(), (int)this.m_prevControlPoint.y());
            this.m_wasActive = this.m_isActive;
        }
    }
    
    void paint(final Graphics graphics) {
        this.m_prevAnchorPoint = new xyVector(this.m_anchorPoint);
        this.m_prevControlPoint = new xyVector(this.m_controlPoint);
        if (this.m_isActive) {
            graphics.setColor(Color.black);
            graphics.drawLine((int)this.m_anchorPoint.x(), (int)this.m_anchorPoint.y(), (int)this.m_controlPoint.x(), (int)this.m_controlPoint.y());
            this.m_wasActive = true;
        }
    }
}
