import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LaserShot
{
    Color color;
    double posX;
    double posY;
    double speedX;
    double speedY;
    int reached;
    int maxReach;
    public int targetX;
    public int targetY;
    public boolean hitting;
    public boolean shooting;
    public boolean showTarget;
    
    public LaserShot(final Color color, final boolean showTarget) {
        this.color = color;
        this.hitting = false;
        this.shooting = false;
        this.showTarget = showTarget;
        this.targetX = -500;
        this.targetY = -500;
    }
    
    public void shoot(final int n, final int n2, final int targetX, final int targetY) {
        if (!this.shooting) {
            this.posX = n;
            this.posY = n2;
            this.targetX = targetX;
            this.targetY = targetY;
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.reached = 0;
            this.maxReach = 10;
            this.shooting = true;
            this.hitting = false;
        }
    }
    
    public void move() {
        if (this.shooting) {
            this.speedX = (this.targetX - this.posX) / (this.maxReach - this.reached + 1);
            this.speedY = (this.targetY - this.posY) / (this.maxReach - this.reached + 1);
            this.posX += this.speedX;
            this.posY += this.speedY;
            ++this.reached;
            if (this.reached == this.maxReach) {
                this.hitting = true;
                return;
            }
            if (this.reached > this.maxReach) {
                this.shooting = false;
                this.hitting = false;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.shooting && this.reached > 1) {
            graphics.setColor(this.color);
            if (this.showTarget) {
                graphics.drawLine(this.targetX - 1, this.targetY - 1, this.targetX + 1, this.targetY + 1);
                graphics.drawLine(this.targetX + 1, this.targetY - 1, this.targetX - 1, this.targetY + 1);
            }
            graphics.drawLine((int)(this.posX - this.speedX * 2.0), (int)(this.posY - this.speedY * 2.0), (int)this.posX, (int)this.posY);
        }
    }
}
