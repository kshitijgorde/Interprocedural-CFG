import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Actor extends Sprite
{
    Point[] wayPoints;
    Point[] speeds;
    int speed;
    int curWayPoint;
    int dx;
    int dy;
    boolean active;
    boolean upDown;
    boolean looping;
    int animSplit;
    int maxFrame;
    
    public Actor(final Image img) {
        super(img);
        this.speed = 1;
        this.dx = this.speed;
        this.dy = this.speed;
        this.active = true;
        this.upDown = false;
        this.looping = true;
        this.maxFrame = this.getFrameCount();
        this.animSplit = this.maxFrame;
    }
    
    public void setAnimationDirection(final boolean upDown, final int frame) {
        this.upDown = upDown;
        this.maxFrame = frame;
        this.animSplit = frame;
    }
    
    public void setWayPoints(final Point[] wayPoints) {
        this.wayPoints = wayPoints;
        if (wayPoints != null) {
            this.setPos(wayPoints[0].x, wayPoints[0].y);
        }
    }
    
    public Point[] getWayPoints() {
        return this.wayPoints;
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
        if (this.speeds == null) {
            this.dx = speed;
            this.dy = speed;
        }
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setSpeeds(final Point[] speeds) {
        this.speeds = speeds;
        this.dx = speeds[this.curWayPoint].x;
        this.dy = speeds[this.curWayPoint].y;
    }
    
    public Point[] getSpeeds() {
        return this.speeds;
    }
    
    public void setActive(final boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void setLooping(final boolean looping) {
        this.looping = looping;
    }
    
    public boolean isLooping() {
        return this.looping;
    }
    
    public void ping() {
        if (!this.active || !super.moving) {
            return;
        }
        boolean xOk = false;
        boolean yOk = false;
        ++super.curFrame;
        if (super.curFrame == this.maxFrame) {
            if (super.curFrame == this.animSplit) {
                super.curFrame = 0;
            }
            else {
                super.curFrame = this.animSplit;
            }
        }
        if (this.wayPoints != null) {
            if (super.bounds.x > this.wayPoints[this.curWayPoint].x) {
                final Rectangle bounds = super.bounds;
                bounds.x -= this.dx;
                final Rectangle bounds_ = super.bounds_;
                bounds_.x -= this.dx;
                if (super.bounds.x < this.wayPoints[this.curWayPoint].x) {
                    xOk = true;
                }
            }
            else if (super.bounds.x < this.wayPoints[this.curWayPoint].x) {
                final Rectangle bounds2 = super.bounds;
                bounds2.x += this.dx;
                final Rectangle bounds_2 = super.bounds_;
                bounds_2.x += this.dx;
                if (super.bounds.x > this.wayPoints[this.curWayPoint].x) {
                    xOk = true;
                }
            }
            else {
                xOk = true;
            }
            if (super.bounds.y > this.wayPoints[this.curWayPoint].y) {
                final Rectangle bounds3 = super.bounds;
                bounds3.y -= this.dy;
                final Rectangle bounds_3 = super.bounds_;
                bounds_3.y -= this.dy;
                if (super.bounds.y < this.wayPoints[this.curWayPoint].y) {
                    yOk = true;
                }
            }
            else if (super.bounds.y < this.wayPoints[this.curWayPoint].y) {
                final Rectangle bounds4 = super.bounds;
                bounds4.y += this.dy;
                final Rectangle bounds_4 = super.bounds_;
                bounds_4.y += this.dy;
                if (super.bounds.y > this.wayPoints[this.curWayPoint].y) {
                    yOk = true;
                }
            }
            else {
                yOk = true;
            }
            if (xOk && yOk) {
                ++this.curWayPoint;
                if (this.curWayPoint >= this.wayPoints.length) {
                    this.curWayPoint = 0;
                    if (!this.isLooping()) {
                        this.setAlive(false);
                    }
                }
                if (this.speeds != null) {
                    this.dx = this.speeds[this.curWayPoint].x;
                    this.dy = this.speeds[this.curWayPoint].y;
                }
                if (this.upDown) {
                    if (super.bounds.y > this.wayPoints[this.curWayPoint].y) {
                        this.maxFrame = this.animSplit;
                        super.curFrame = 0;
                    }
                    else if (super.bounds.y < this.wayPoints[this.curWayPoint].y) {
                        this.maxFrame = super.frameCount;
                        super.curFrame = this.animSplit;
                    }
                }
                else if (super.bounds.x > this.wayPoints[this.curWayPoint].x) {
                    this.maxFrame = super.frameCount;
                    super.curFrame = this.animSplit;
                }
                else if (super.bounds.x < this.wayPoints[this.curWayPoint].x) {
                    this.maxFrame = this.animSplit;
                    super.curFrame = 0;
                }
            }
        }
    }
}
