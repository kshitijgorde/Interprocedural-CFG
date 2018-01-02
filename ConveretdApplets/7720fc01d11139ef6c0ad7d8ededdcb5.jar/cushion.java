import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class cushion
{
    boolean m_isHorizontal;
    int m_depthSign;
    int m_depth;
    xyVector m_end1;
    xyVector m_end2;
    xyVector m_corner1;
    xyVector m_corner2;
    pocket m_pocket1;
    pocket m_pocket2;
    
    cushion(final pocket pocket2, final pocket pocket3, final int depth) {
        if (depth > 0) {
            this.m_depthSign = 1;
            this.m_depth = depth;
        }
        else {
            this.m_depthSign = -1;
            this.m_depth = depth * -1;
        }
        this.m_end1 = new xyVector(pocket2.m_position);
        this.m_end2 = new xyVector(pocket3.m_position);
        this.m_pocket1 = pocket2;
        this.m_pocket2 = pocket3;
        this.m_isHorizontal = (this.m_end1.y() == this.m_end2.y());
        if (this.m_isHorizontal) {
            if (this.m_end1.x() > this.m_end2.x()) {
                this.m_end1 = new xyVector(pocket3.m_position);
                this.m_end2 = new xyVector(pocket2.m_position);
                this.m_pocket1 = pocket3;
                this.m_pocket2 = pocket2;
            }
            this.m_end1.add(this.m_pocket1.radius(), 0.0);
            this.m_end2.subtract(this.m_pocket2.radius(), 0.0);
        }
        else {
            if (this.m_end1.y() > this.m_end2.y()) {
                this.m_end1 = new xyVector(pocket3.m_position);
                this.m_end2 = new xyVector(pocket2.m_position);
                this.m_pocket1 = pocket3;
                this.m_pocket2 = pocket2;
            }
            this.m_end1.add(0.0, this.m_pocket1.radius());
            this.m_end2.subtract(0.0, this.m_pocket2.radius());
        }
        this.m_corner1 = new xyVector(this.m_end1);
        this.m_corner2 = new xyVector(this.m_end2);
        if (this.m_isHorizontal) {
            this.m_corner1.add(this.m_depth, depth);
            this.m_corner2.add(-this.m_depth, depth);
        }
        else {
            this.m_corner1.add(depth, this.m_depth);
            this.m_corner2.add(depth, -this.m_depth);
        }
    }
    
    void bounce(final poolBall poolBall) {
        final int radius = poolBall.radius();
        if (this.m_isHorizontal) {
            if ((poolBall.m_position.y() - this.m_corner1.y()) * this.m_depthSign > radius) {
                return;
            }
            final int n = (int)this.m_end1.x() - radius;
            if (poolBall.m_position.x() < n) {
                return;
            }
            final int n2 = (int)this.m_end2.x() + radius;
            if (poolBall.m_position.x() > n2) {
                return;
            }
            final int n3 = n + (this.m_depth + radius);
            final int n4 = n2 - (this.m_depth + radius);
            if (poolBall.m_position.x() > n3 && poolBall.m_position.x() < n4) {
                if (poolBall.m_velocity.y() * this.m_depthSign > 0.0) {
                    return;
                }
                final xyVector xyVector = new xyVector(poolBall.m_spin);
                xyVector.scale(1.0, 0.0);
                poolBall.m_velocity.add(xyVector);
                poolBall.m_spin.subtract(xyVector);
                poolBall.m_velocity.scale(1.0, -1.0);
            }
            else {
                final xyVector xyVector2 = new xyVector(poolBall.m_position);
                if (xyVector2.x() < (n3 + n4) / 2) {
                    if (poolBall.bounce(this.m_corner1)) {
                        return;
                    }
                    xyVector2.subtract(this.m_end1);
                    xyVector2.scale(1.0, this.m_depthSign);
                    xyVector2.rotate45();
                    if (xyVector2.y() > radius) {
                        return;
                    }
                    final xyVector xyVector3 = new xyVector(poolBall.m_velocity);
                    xyVector3.scale(1.0, this.m_depthSign);
                    xyVector3.rotate45();
                    if (xyVector3.y() > 0.0) {
                        return;
                    }
                    poolBall.m_velocity.mirror45();
                    poolBall.m_velocity.scale(this.m_depthSign, this.m_depthSign);
                    final xyVector xyVector4 = new xyVector(this.m_end1);
                    xyVector4.subtract(this.m_corner1);
                    final xyVector resolve = poolBall.m_spin.resolve(xyVector4);
                    poolBall.m_spin.subtract(resolve);
                    poolBall.m_velocity.add(resolve);
                }
                else {
                    if (poolBall.bounce(this.m_corner2)) {
                        return;
                    }
                    xyVector2.subtract(this.m_end2);
                    xyVector2.scale(-1.0, this.m_depthSign);
                    xyVector2.rotate45();
                    if (xyVector2.y() > radius) {
                        return;
                    }
                    final xyVector xyVector5 = new xyVector(poolBall.m_velocity);
                    xyVector5.scale(-1.0, this.m_depthSign);
                    xyVector5.rotate45();
                    if (xyVector5.y() > 0.0) {
                        return;
                    }
                    poolBall.m_velocity.mirror45();
                    poolBall.m_velocity.scale(-this.m_depthSign, -this.m_depthSign);
                    final xyVector xyVector6 = new xyVector(this.m_end2);
                    xyVector6.subtract(this.m_corner2);
                    final xyVector resolve2 = poolBall.m_spin.resolve(xyVector6);
                    poolBall.m_spin.subtract(resolve2);
                    poolBall.m_velocity.add(resolve2);
                }
            }
        }
        else {
            if ((poolBall.m_position.x() - this.m_corner1.x()) * this.m_depthSign > radius) {
                return;
            }
            final int n5 = (int)this.m_end1.y() - radius;
            if (poolBall.m_position.y() < n5) {
                return;
            }
            final int n6 = (int)this.m_end2.y() + radius;
            if (poolBall.m_position.y() > n6) {
                return;
            }
            final int n7 = n5 + (this.m_depth + radius);
            final int n8 = n6 - (this.m_depth + radius);
            if (poolBall.m_position.y() > n7 && poolBall.m_position.y() < n8) {
                if (poolBall.m_velocity.x() * this.m_depthSign > 0.0) {
                    return;
                }
                final xyVector xyVector7 = new xyVector(poolBall.m_spin);
                xyVector7.scale(0.0, 1.0);
                poolBall.m_velocity.add(xyVector7);
                poolBall.m_spin.subtract(xyVector7);
                poolBall.m_velocity.scale(-1.0, 1.0);
            }
            else {
                final xyVector xyVector8 = new xyVector(poolBall.m_position);
                if (xyVector8.y() < (n7 + n8) / 2) {
                    if (poolBall.bounce(this.m_corner1)) {
                        return;
                    }
                    xyVector8.subtract(this.m_end1);
                    xyVector8.scale(this.m_depthSign, -1.0);
                    xyVector8.rotate45();
                    if (xyVector8.x() > radius) {
                        return;
                    }
                    final xyVector xyVector9 = new xyVector(poolBall.m_velocity);
                    xyVector9.scale(-this.m_depthSign, -1.0);
                    xyVector9.rotate45();
                    if (xyVector9.y() > 0.0) {
                        return;
                    }
                    poolBall.m_velocity.mirror45();
                    poolBall.m_velocity.scale(this.m_depthSign, this.m_depthSign);
                    final xyVector xyVector10 = new xyVector(this.m_end1);
                    xyVector10.subtract(this.m_corner1);
                    final xyVector resolve3 = poolBall.m_spin.resolve(xyVector10);
                    poolBall.m_spin.subtract(resolve3);
                    poolBall.m_velocity.add(resolve3);
                }
                else {
                    if (poolBall.bounce(this.m_corner2)) {
                        return;
                    }
                    xyVector8.subtract(this.m_end2);
                    xyVector8.scale(this.m_depthSign, 1.0);
                    xyVector8.rotate45();
                    if (xyVector8.x() > radius) {
                        return;
                    }
                    final xyVector xyVector11 = new xyVector(poolBall.m_velocity);
                    xyVector11.scale(-this.m_depthSign, 1.0);
                    xyVector11.rotate45();
                    if (xyVector11.y() > 0.0) {
                        return;
                    }
                    poolBall.m_velocity.mirror45();
                    poolBall.m_velocity.scale(-this.m_depthSign, -this.m_depthSign);
                    final xyVector xyVector12 = new xyVector(this.m_end2);
                    xyVector12.subtract(this.m_corner2);
                    final xyVector resolve4 = poolBall.m_spin.resolve(xyVector12);
                    poolBall.m_spin.subtract(resolve4);
                    poolBall.m_velocity.add(resolve4);
                }
            }
        }
    }
    
    void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawLine((int)this.m_end1.x(), (int)this.m_end1.y(), (int)this.m_corner1.x(), (int)this.m_corner1.y());
        graphics.drawLine((int)this.m_corner1.x(), (int)this.m_corner1.y(), (int)this.m_corner2.x(), (int)this.m_corner2.y());
        graphics.drawLine((int)this.m_corner2.x(), (int)this.m_corner2.y(), (int)this.m_end2.x(), (int)this.m_end2.y());
    }
}
