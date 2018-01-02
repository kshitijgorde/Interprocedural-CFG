import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class poolBall
{
    private int m_diameter;
    xyVector m_position;
    xyVector m_velocity;
    xyVector m_spin;
    private Color m_colour;
    private xyVector m_prevPosition;
    static final double m_friction = 2.0E-4;
    
    public int radius() {
        return this.m_diameter / 2;
    }
    
    void impulse(final xyVector xyVector) {
        this.m_velocity.add(xyVector);
    }
    
    void impulse(final xyVector xyVector, final xyVector xyVector2) {
        this.m_velocity.add(xyVector);
        this.m_spin.add(xyVector2);
    }
    
    poolBall(final xyVector xyVector, final int n, final Color colour) {
        this.m_position = new xyVector(xyVector);
        this.m_diameter = n * 2;
        this.m_colour = colour;
        this.m_prevPosition = null;
        this.m_velocity = new xyVector(0.0, 0.0);
        this.m_spin = new xyVector(0.0, 0.0);
    }
    
    poolBall(final poolBall poolBall) {
        this.m_position = new xyVector(poolBall.m_position);
        this.m_diameter = poolBall.m_diameter;
        this.m_colour = poolBall.m_colour;
        this.m_prevPosition = poolBall.m_prevPosition;
        this.m_velocity = new xyVector(poolBall.m_velocity);
        this.m_spin = new xyVector(poolBall.m_spin);
    }
    
    void reset(final poolBall poolBall) {
        this.m_position = new xyVector(poolBall.m_position);
        this.m_diameter = poolBall.m_diameter;
        this.m_colour = poolBall.m_colour;
        this.m_prevPosition = poolBall.m_prevPosition;
        this.m_velocity = new xyVector(poolBall.m_velocity);
        this.m_spin = new xyVector(poolBall.m_spin);
    }
    
    boolean Move() {
        final boolean b = this.m_velocity.magnitude() > 0.0;
        this.m_position.add(this.m_velocity);
        final xyVector xyVector = new xyVector(this.m_velocity);
        xyVector.subtract(this.m_spin);
        if (xyVector.magnitude() > 2.0E-4) {
            final xyVector unit = xyVector.unit();
            unit.scale(2.0E-4);
            this.m_velocity.subtract(unit);
            this.m_spin.add(unit);
        }
        else {
            this.m_velocity = this.m_spin;
        }
        if (this.m_spin.magnitude() < 2.0E-4) {
            this.m_spin = new xyVector(0.0, 0.0);
        }
        else {
            final xyVector unit2 = this.m_spin.unit();
            unit2.scale(1.0E-4);
            this.m_spin.subtract(unit2);
        }
        if (this.m_velocity.magnitude() < 2.0E-4) {
            this.m_velocity = new xyVector(0.0, 0.0);
        }
        else {
            final xyVector unit3 = this.m_velocity.unit();
            unit3.scale(1.0E-4);
            this.m_velocity.subtract(unit3);
        }
        return b;
    }
    
    boolean testCollision(final xyVector xyVector) {
        final double n = xyVector.x() - this.m_position.x();
        final double n2 = xyVector.y() - this.m_position.y();
        final double n3 = n * n + n2 * n2;
        final double n4 = this.m_diameter / 2;
        return n3 < n4 * n4;
    }
    
    boolean testCollision(final poolBall poolBall) {
        final double n = poolBall.m_position.x() - this.m_position.x();
        final double n2 = poolBall.m_position.y() - this.m_position.y();
        final double n3 = n * n + n2 * n2;
        final double n4 = (poolBall.m_diameter + this.m_diameter) / 2;
        if (n3 < n4 * n4) {
            final xyVector xyVector = new xyVector(this.m_position);
            xyVector.subtract(poolBall.m_position);
            final xyVector unit = xyVector.unit();
            unit.scale(this.radius() + poolBall.radius());
            unit.add(poolBall.m_position);
            this.m_position = unit;
            return true;
        }
        return false;
    }
    
    void Collide(final poolBall poolBall) {
        final xyVector xyVector = new xyVector(this.m_position);
        xyVector.subtract(poolBall.m_position);
        final xyVector unit = xyVector.unit();
        final double angle = unit.angle();
        final double magnitude = this.m_velocity.magnitude();
        final double magnitude2 = poolBall.m_velocity.magnitude();
        final double n = this.m_velocity.angle() - angle;
        final double n2 = poolBall.m_velocity.angle() + 3.141592653589793 - angle;
        final double n3 = magnitude * Math.cos(n);
        final double n4 = magnitude2 * Math.cos(n2);
        unit.scale(-n3);
        this.m_velocity.add(unit);
        poolBall.m_velocity.subtract(unit);
        final xyVector unit2 = unit.unit();
        unit2.scale(-n4);
        this.m_velocity.add(unit2);
        poolBall.m_velocity.subtract(unit2);
    }
    
    boolean bounce(final xyVector xyVector) {
        final xyVector xyVector2 = new xyVector(xyVector);
        xyVector2.subtract(this.m_position);
        if (xyVector2.magnitude() > this.radius()) {
            return false;
        }
        final xyVector unit = xyVector2.unit();
        final double n = this.m_velocity.magnitude() * Math.cos(unit.angle() - this.m_velocity.angle());
        if (n < 0.0) {
            return true;
        }
        final xyVector xyVector3 = new xyVector(unit);
        xyVector3.rotate90();
        final xyVector resolve = this.m_spin.resolve(xyVector3);
        this.m_velocity.add(resolve);
        this.m_spin.subtract(resolve);
        unit.scale(n * 2.0);
        this.m_velocity.subtract(unit);
        return true;
    }
    
    void paint(final Graphics graphics) {
        if (this.m_prevPosition == null) {
            this.m_prevPosition = new xyVector(this.m_position);
        }
        else if ((int)this.m_prevPosition.x() != (int)this.m_position.x() || (int)this.m_prevPosition.y() != (int)this.m_position.y()) {
            if (this.m_prevPosition != null) {
                graphics.setColor(Color.green);
                graphics.fillOval((int)this.m_prevPosition.x() - this.m_diameter / 2, (int)this.m_prevPosition.y() - this.m_diameter / 2, this.m_diameter, this.m_diameter);
            }
            this.m_prevPosition = new xyVector(this.m_position);
        }
        graphics.setColor(this.m_colour);
        graphics.fillOval((int)this.m_position.x() - this.m_diameter / 2, (int)this.m_position.y() - this.m_diameter / 2, this.m_diameter, this.m_diameter);
    }
}
