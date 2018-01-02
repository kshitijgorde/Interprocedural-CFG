import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Interceptor extends Flying
{
    Polygon shape;
    Ship ship;
    Explosion[] explosions;
    int aWidth;
    int aHeight;
    double angle;
    double genSpeedX;
    double genSpeedY;
    double middleX;
    double middleY;
    double speedX;
    double speedY;
    int speedZ;
    int cannon1X;
    int cannon1Y;
    int cannon2X;
    int cannon2Y;
    int crashStadium;
    public LaserShot[] shots;
    public Polygon sprite;
    public int shield;
    public int zLevel;
    public boolean shown;
    public boolean dangerous;
    public boolean crashing;
    
    public Interceptor(final int aWidth, final int aHeight, final Ship ship) {
        this.zLevel = 0;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shown = false;
        this.crashing = false;
        this.dangerous = false;
        this.shield = 0;
        this.ship = ship;
        this.explosions = new Explosion[2];
        this.shots = new LaserShot[2];
        for (int i = 0; i < 2; ++i) {
            this.shots[i] = new LaserShot(Color.green, false);
            this.explosions[i] = new Explosion();
        }
    }
    
    public void start() {
        if (!this.shown && !this.crashing) {
            (this.shape = new Polygon()).addPoint(0, -3);
            this.shape.addPoint(3, -2);
            this.shape.addPoint(6, -6);
            this.shape.addPoint(12, -9);
            this.shape.addPoint(18, -6);
            this.shape.addPoint(21, -2);
            this.shape.addPoint(20, -6);
            this.shape.addPoint(17, -12);
            this.shape.addPoint(12, -14);
            this.shape.addPoint(14, -16);
            this.shape.addPoint(21, -12);
            this.shape.addPoint(22, -9);
            this.shape.addPoint(24, 0);
            this.shape.addPoint(22, 9);
            this.shape.addPoint(21, 12);
            this.shape.addPoint(14, 16);
            this.shape.addPoint(12, 14);
            this.shape.addPoint(17, 12);
            this.shape.addPoint(20, 6);
            this.shape.addPoint(21, 2);
            this.shape.addPoint(18, 6);
            this.shape.addPoint(12, 9);
            this.shape.addPoint(6, 6);
            this.shape.addPoint(3, 2);
            this.shape.addPoint(0, 3);
            this.shape.addPoint(-3, 2);
            this.shape.addPoint(-6, 6);
            this.shape.addPoint(-12, 9);
            this.shape.addPoint(-18, 6);
            this.shape.addPoint(-21, 2);
            this.shape.addPoint(-20, 6);
            this.shape.addPoint(-17, 12);
            this.shape.addPoint(-12, 14);
            this.shape.addPoint(-14, 16);
            this.shape.addPoint(-21, 12);
            this.shape.addPoint(-22, 9);
            this.shape.addPoint(-24, 0);
            this.shape.addPoint(-22, -9);
            this.shape.addPoint(-21, -12);
            this.shape.addPoint(-14, -16);
            this.shape.addPoint(-12, -14);
            this.shape.addPoint(-17, -12);
            this.shape.addPoint(-20, -6);
            this.shape.addPoint(-21, -2);
            this.shape.addPoint(-18, -6);
            this.shape.addPoint(-12, -9);
            this.shape.addPoint(-6, -6);
            this.shape.addPoint(-3, -2);
            this.speedZ = 1;
            this.zLevel = 1;
            this.shown = true;
            this.crashing = false;
            this.dangerous = false;
            this.angle = 0.0;
            this.shield = 1;
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.genSpeedX = 0.0;
            this.genSpeedY = 0.0;
            while (this.genSpeedX == 0.0 && this.genSpeedY == 0.0) {
                this.genSpeedX = (Math.random() - 0.5) * 1.5;
                this.genSpeedY = (Math.random() - 0.5) * 1.5;
            }
            this.middleX = this.aWidth / 2;
            this.middleY = this.aHeight / 2;
            this.render();
        }
    }
    
    public void move() {
        for (int i = 0; i < 2; ++i) {
            this.explosions[i].go();
            this.shots[i].move();
        }
        if (this.shown || this.crashing) {
            ++this.zLevel;
            this.middleX += this.speedX + this.genSpeedX;
            this.middleY += this.speedY + this.genSpeedY;
            this.genSpeedX *= 1.014;
            this.genSpeedY *= 1.014;
            if (this.crashing) {
                this.angle += 0.03;
                if (this.explosions[0].exploding) {
                    this.explosions[0].middleX = this.cannon1X + (int)this.genSpeedX;
                    this.explosions[0].middleY = this.cannon1Y + (int)this.genSpeedY;
                    this.explosions[1].middleX = this.cannon2X + (int)this.genSpeedX;
                    this.explosions[1].middleY = this.cannon2Y + (int)this.genSpeedY;
                }
                if (this.shown && this.explosions[0].stadium > 35) {
                    this.shown = false;
                }
            }
            else {
                if (Math.random() < 0.01) {
                    this.speedX = 2.5;
                    this.speedY = 0.0;
                }
                if (Math.random() < 0.01) {
                    this.speedX = -2.5;
                    this.speedY = 0.0;
                }
                if (Math.random() < 0.01) {
                    this.speedY = 2.5;
                    this.speedX = 0.0;
                }
                if (Math.random() < 0.01) {
                    this.speedY = -2.5;
                    this.speedX = 0.0;
                }
                if (this.speedX == 2.5) {
                    this.angle = 0.39269908169872414;
                }
                else if (this.speedX == -2.5) {
                    this.angle = -0.39269908169872414;
                }
                else {
                    this.angle = 0.0;
                }
            }
            if (!this.crashing && this.zLevel > 50 && this.ship.alive && !this.shots[0].shooting && Math.pow(this.ship.middleX - (int)this.middleX, 2.0) + Math.pow(this.ship.middleY - (int)this.middleY, 2.0) <= 900 * this.zLevel) {
                Sounds.play(Sounds.laser);
                this.shots[0].shoot(this.cannon1X, this.cannon1Y, this.ship.middleX + (int)(Math.random() * -10.0), this.ship.middleY);
                this.shots[1].shoot(this.cannon2X, this.cannon2Y, this.ship.middleX + (int)(Math.random() * 10.0), this.ship.middleY);
            }
            if (this.middleX < -20.0 || this.middleX > this.aWidth + 20 || this.middleY < -20.0 || this.middleY > this.aHeight + 20 || this.explosions[0].stadium > 72) {
                this.shown = false;
                this.crashing = false;
            }
            this.render();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            if (this.crashing) {
                graphics.setColor(new Color(3158080));
            }
            else {
                graphics.setColor(new Color(3158144));
            }
            graphics.fillPolygon(this.sprite);
            graphics.setColor(new Color(10066329));
            graphics.drawPolygon(this.sprite);
        }
        for (int i = 0; i < 2; ++i) {
            this.shots[i].paint(graphics);
            this.explosions[i].paint(graphics);
        }
    }
    
    public void paintBar(final Graphics graphics) {
        if (this.shown && this.shield != 0 && this.zLevel > 25) {
            graphics.setColor(Color.black);
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 12, 10, 3);
            graphics.setColor(new Color(43520));
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 12, this.shield * 10, 3);
            if (!this.crashing && this.zLevel >= 100) {
                graphics.setColor(new Color(13482888));
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.drawRect((int)this.middleX - 5, (int)this.middleY - 12, 10, 3);
        }
    }
    
    public void crash() {
        if (this.shown && !this.crashing) {
            Sounds.play(Sounds.enemy);
            this.crashing = true;
            this.genSpeedX *= 1.5;
            this.genSpeedY *= 1.5;
            this.speedX /= 2.0;
            this.speedY /= 2.0;
            this.shield = 0;
            this.explosions[0].explode(this.cannon1X, this.cannon1Y, 38);
            this.explosions[1].explode(this.cannon2X, this.cannon2Y, 38);
        }
    }
    
    public boolean hit(final int n) {
        if (this.shown) {
            this.shield -= n;
            if (this.shield <= 0) {
                this.shield = 0;
                this.crash();
                return true;
            }
        }
        return false;
    }
    
    public boolean inside(final int n, final int n2) {
        return this.shown && !this.crashing && this.zLevel > 25 && this.sprite.contains(n, n2);
    }
    
    void render() {
        this.sprite = new Polygon();
        final double sin = Math.sin(this.angle);
        final double cos = Math.cos(this.angle);
        if (this.zLevel < 100) {
            for (int i = 0; i < this.shape.npoints; ++i) {
                this.sprite.addPoint((int)(Math.round(this.shape.xpoints[i] * cos + this.shape.ypoints[i] * sin) * this.zLevel / 100L + this.middleX), (int)(Math.round(this.shape.ypoints[i] * cos - this.shape.xpoints[i] * sin) * this.zLevel / 100L + this.middleY));
            }
            this.cannon1X = (int)(-12.0 * cos * this.zLevel / 100.0 + this.middleX);
            this.cannon1Y = (int)(12.0 * sin * this.zLevel / 100.0 + this.middleY);
            this.cannon2X = (int)(12.0 * cos * this.zLevel / 100.0 + this.middleX);
            this.cannon2Y = (int)(-12.0 * sin * this.zLevel / 100.0 + this.middleY);
            return;
        }
        for (int j = 0; j < this.shape.npoints; ++j) {
            this.sprite.addPoint((int)(Math.round(this.shape.xpoints[j] * cos + this.shape.ypoints[j] * sin) + this.middleX), (int)(Math.round(this.shape.ypoints[j] * cos - this.shape.xpoints[j] * sin) + this.middleY));
        }
        this.cannon1X = (int)(-12.0 * cos + this.middleX);
        this.cannon1Y = (int)(12.0 * sin + this.middleY);
        this.cannon2X = (int)(12.0 * cos + this.middleX);
        this.cannon2Y = (int)(-12.0 * sin + this.middleY);
    }
}
