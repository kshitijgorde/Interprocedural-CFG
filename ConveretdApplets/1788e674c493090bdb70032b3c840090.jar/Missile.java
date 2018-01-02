import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Missile extends Flying
{
    Polygon shape;
    Explosion explosion;
    Color color;
    Ship ship;
    int middleX;
    int middleY;
    int reached;
    int maxReach;
    public Polygon sprite;
    public int zLevel;
    public boolean hitting;
    public boolean shown;
    
    public Missile(final Color color, final Ship ship) {
        this.color = color;
        this.shown = false;
        this.ship = ship;
        this.hitting = false;
        this.zLevel = 0;
        this.explosion = new Explosion();
        (this.shape = new Polygon()).addPoint(-1, -3);
        this.shape.addPoint(1, -3);
        this.shape.addPoint(3, -1);
        this.shape.addPoint(3, 1);
        this.shape.addPoint(1, 3);
        this.shape.addPoint(-1, 3);
        this.shape.addPoint(-3, 1);
        this.shape.addPoint(-3, -1);
    }
    
    public void shoot(final int middleX, final int middleY, final int zLevel) {
        if (!this.shown && !this.explosion.exploding) {
            this.middleX = middleX;
            this.middleY = middleY;
            this.reached = 0;
            this.maxReach = 70;
            this.shown = true;
            this.hitting = false;
            this.zLevel = zLevel;
        }
        this.render();
    }
    
    public void move() {
        if (this.shown) {
            if (this.middleX + 5 < this.ship.middleX && this.middleY + 5 < this.ship.middleY) {
                this.middleX += 5;
                this.middleY += 5;
            }
            else if (this.middleX + 5 < this.ship.middleX && this.middleY - 5 > this.ship.middleY) {
                this.middleX += 5;
                this.middleY -= 5;
            }
            else if (this.middleX - 5 > this.ship.middleX && this.middleY + 5 < this.ship.middleY) {
                this.middleX -= 5;
                this.middleY += 5;
            }
            else if (this.middleX - 5 > this.ship.middleX && this.middleY - 5 > this.ship.middleY) {
                this.middleX -= 5;
                this.middleY -= 5;
            }
            else {
                if (this.middleX < this.ship.middleX) {
                    this.middleX += 7;
                    if (this.middleX > this.ship.middleX) {
                        this.middleX = this.ship.middleX;
                        this.hitting = true;
                    }
                }
                else {
                    this.middleX -= 7;
                    if (this.middleX < this.ship.middleX) {
                        this.middleX = this.ship.middleX;
                        this.hitting = true;
                    }
                }
                if (this.middleY < this.ship.middleY) {
                    this.middleY += 7;
                    if (this.middleY > this.ship.middleY) {
                        this.middleY = this.ship.middleY;
                        this.hitting = true;
                    }
                }
                else if (this.middleY > this.ship.middleY) {
                    this.middleY -= 7;
                    if (this.middleY < this.ship.middleY) {
                        this.middleY = this.ship.middleY;
                        this.hitting = true;
                    }
                }
                else {
                    this.hitting = true;
                }
            }
            if (this.zLevel < 100) {
                ++this.zLevel;
            }
            ++this.reached;
            if (this.reached == this.maxReach) {
                this.crash();
            }
            else if (this.ship.sprite.contains(this.middleX, this.middleY)) {
                this.hitting = true;
            }
            this.render();
        }
        this.explosion.go();
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            graphics.setColor(this.color);
            graphics.fillPolygon(this.sprite);
            graphics.setColor(this.color.brighter());
            graphics.drawPolygon(this.sprite);
        }
        this.explosion.paint(graphics);
    }
    
    public void crash() {
        this.hitting = false;
        this.shown = false;
        this.explosion.explode(this.middleX, this.middleY, 14);
    }
    
    void render() {
        this.sprite = new Polygon();
        for (int i = 0; i < this.shape.npoints; ++i) {
            this.sprite.addPoint(this.shape.xpoints[i] + this.middleX, this.shape.ypoints[i] + this.middleY);
        }
    }
}
