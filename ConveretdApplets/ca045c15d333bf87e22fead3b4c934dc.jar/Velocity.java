// 
// Decompiled by Procyon v0.5.30
// 

public class Velocity
{
    Ball ball;
    double vx;
    double vy;
    double ax;
    double ay;
    
    public Velocity(final Ball ball, final double ax, final double ay) {
        this.ball = ball;
        this.ax = ax;
        this.ay = ay;
    }
    
    public void setVelocity(final double vx, final double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    public void moveBall() {
        this.ball.setXY(this.ball.getX() + this.vx, this.ball.getY() + this.vy);
        this.vx += this.ax;
        this.vy += this.ay;
    }
    
    public double getVY() {
        return this.vy;
    }
    
    public double getVX() {
        return this.vx;
    }
}
