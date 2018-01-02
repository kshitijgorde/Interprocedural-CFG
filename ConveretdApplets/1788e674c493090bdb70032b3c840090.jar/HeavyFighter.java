import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class HeavyFighter extends Flying
{
    Polygon shape;
    Explosion[] explosions;
    Ship ship;
    int aWidth;
    int aHeight;
    double angle;
    double genSpeedX;
    double genSpeedY;
    double middleX;
    double middleY;
    double speedX;
    double speedY;
    int crashStadium;
    int shotToShoot;
    boolean[] missilesFired;
    public Missile[] missiles;
    public LaserShot[] shots;
    public Polygon sprite;
    public int shield;
    public int zLevel;
    public boolean shown;
    public boolean dangerous;
    public boolean crashing;
    
    public HeavyFighter(final int aWidth, final int aHeight, final Ship ship) {
        this.zLevel = 0;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shown = false;
        this.crashing = false;
        this.dangerous = false;
        this.shield = 0;
        this.ship = ship;
        this.shots = new LaserShot[5];
        this.missiles = new Missile[1];
        this.missilesFired = new boolean[1];
        this.explosions = new Explosion[3];
        for (int i = 0; i < 5; ++i) {
            this.shots[i] = new LaserShot(new Color(43520), false);
        }
        for (int j = 0; j < 3; ++j) {
            this.explosions[j] = new Explosion();
        }
        this.missiles[0] = new Missile(new Color(43520), ship);
        this.missilesFired[0] = false;
    }
    
    public void start() {
        if (!this.shown && !this.crashing) {
            (this.shape = new Polygon()).addPoint(0, -5);
            this.shape.addPoint(5, -10);
            this.shape.addPoint(20, -5);
            this.shape.addPoint(30, -15);
            this.shape.addPoint(25, 0);
            this.shape.addPoint(15, 6);
            this.shape.addPoint(8, 8);
            this.shape.addPoint(0, 10);
            this.shape.addPoint(-8, 8);
            this.shape.addPoint(-15, 6);
            this.shape.addPoint(-25, 0);
            this.shape.addPoint(-30, -15);
            this.shape.addPoint(-20, -5);
            this.shape.addPoint(-5, -10);
            this.zLevel = 1;
            this.shown = true;
            this.crashing = false;
            this.dangerous = false;
            this.angle = 0.0;
            this.shield = 16;
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.genSpeedX = 0.0;
            this.genSpeedY = 0.0;
            while (this.genSpeedX == 0.0 && this.genSpeedY == 0.0) {
                this.genSpeedX = (Math.random() - 0.5) * 1.5;
                this.genSpeedY = (Math.random() - 0.5) * 1.5;
            }
            this.shotToShoot = 0;
            this.middleX = this.aWidth / 2;
            this.middleY = this.aHeight / 2;
            this.render();
        }
    }
    
    public void move() {
        if (this.shown || this.crashing) {
            ++this.zLevel;
            this.middleX += this.speedX + this.genSpeedX;
            this.middleY += this.speedY + this.genSpeedY;
            this.genSpeedX *= 1.014;
            this.genSpeedY *= 1.014;
            if (!this.shots[4].shooting && !this.crashing && this.zLevel > 50 && this.ship.alive && this.middleX > 10.0 && this.middleX < this.aWidth - 10 && this.middleY > 10.0 && this.middleY < this.aHeight - 10) {
                if (Math.pow(this.ship.middleX - (int)this.middleX, 2.0) + Math.pow(this.ship.middleY - (int)this.middleY, 2.0) <= 525 * this.zLevel) {
                    switch (this.shotToShoot) {
                        case 0: {
                            this.shots[0].shoot(this.sprite.xpoints[7], this.sprite.ypoints[7], this.ship.middleX, this.ship.middleY);
                            Sounds.play(Sounds.laser);
                            break;
                        }
                        case 1: {
                            this.shots[1].shoot(this.sprite.xpoints[8], this.sprite.ypoints[8], this.ship.middleX - 20, this.ship.middleY + 15);
                            Sounds.play(Sounds.laser);
                        }
                        case 2: {
                            this.shots[2].shoot(this.sprite.xpoints[6], this.sprite.ypoints[6], this.ship.middleX + 20, this.ship.middleY + 15);
                            Sounds.play(Sounds.laser);
                            break;
                        }
                        case 3: {
                            this.shots[3].shoot(this.sprite.xpoints[9], this.sprite.ypoints[9], this.ship.middleX - 20, this.ship.middleY - 15);
                            Sounds.play(Sounds.laser);
                            break;
                        }
                        case 4: {
                            this.shots[4].shoot(this.sprite.xpoints[5], this.sprite.ypoints[5], this.ship.middleX + 20, this.ship.middleY - 15);
                            Sounds.play(Sounds.laser);
                            break;
                        }
                        case 5: {
                            this.shotToShoot = -1;
                            break;
                        }
                    }
                    ++this.shotToShoot;
                }
                else if (!this.missilesFired[0] && this.zLevel > 75) {
                    this.missiles[0].shoot((int)this.middleX, (int)this.middleY, this.zLevel + 1);
                    this.missilesFired[0] = true;
                }
            }
            if (this.crashing) {
                if (Math.random() < 0.7) {
                    this.angle += 0.1;
                }
                else {
                    this.angle -= 0.1;
                }
                if (this.explosions[0].exploding) {
                    this.explosions[0].middleX = this.sprite.xpoints[12] + (int)this.genSpeedX;
                    this.explosions[0].middleY = this.sprite.ypoints[12] + (int)this.genSpeedY;
                    this.explosions[1].middleX = (int)this.middleX + (int)this.genSpeedX;
                    this.explosions[1].middleY = (int)this.middleY + (int)this.genSpeedY;
                    this.explosions[2].middleX = this.sprite.xpoints[2] + (int)this.genSpeedX;
                    this.explosions[2].middleY = this.sprite.ypoints[2] + (int)this.genSpeedY;
                }
                if (this.shown && this.explosions[0].stadium > 35) {
                    this.shown = false;
                }
            }
            else {
                this.speedX *= 0.93;
                this.speedY *= 0.93;
            }
            if (this.middleX < -40.0 || this.middleX > this.aWidth + 40 || this.middleY < -40.0 || this.middleY > this.aHeight + 40 || this.explosions[0].stadium > 77) {
                this.shown = false;
                this.crashing = false;
            }
            this.render();
        }
        for (int i = 0; i < 5; ++i) {
            this.shots[i].move();
        }
        for (int j = 0; j < 3; ++j) {
            this.explosions[j].go();
        }
        this.missiles[0].move();
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            if (this.crashing) {
                graphics.setColor(new Color(2439717));
            }
            else {
                graphics.setColor(new Color(3362867));
            }
            graphics.fillPolygon(this.sprite);
            graphics.setColor(new Color(11184810));
            graphics.drawPolygon(this.sprite);
        }
        for (int i = 0; i < 5; ++i) {
            this.shots[i].paint(graphics);
        }
        for (int j = 0; j < 3; ++j) {
            this.explosions[j].paint(graphics);
        }
        this.missiles[0].paint(graphics);
    }
    
    public void paintBar(final Graphics graphics) {
        if (this.shown && this.shield != 0 && this.zLevel > 25) {
            graphics.setColor(Color.black);
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 20, 10, 3);
            if (this.shield >= 8) {
                graphics.setColor(new Color(43520));
            }
            else if (this.shield >= 5) {
                graphics.setColor(new Color(12303104));
            }
            else {
                graphics.setColor(new Color(12255232));
            }
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 20, this.shield * 10 / 16, 3);
            if (!this.crashing && this.zLevel >= 100) {
                graphics.setColor(new Color(13482888));
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.drawRect((int)this.middleX - 5, (int)this.middleY - 20, 10, 3);
        }
    }
    
    public void crash() {
        if (this.shown && !this.crashing) {
            Sounds.play(Sounds.enemy);
            this.crashing = true;
            this.genSpeedX *= 1.5;
            this.genSpeedY *= 1.5;
            this.speedX /= 4.0;
            this.speedY /= 4.0;
            this.shield = 0;
            this.missiles[0].crash();
            this.explosions[0].explode(this.sprite.xpoints[12], this.sprite.ypoints[12], 40);
            this.explosions[1].explode((int)this.middleX, (int)this.middleY, 40);
            this.explosions[2].explode(this.sprite.xpoints[2], this.sprite.ypoints[2], 40);
        }
    }
    
    public boolean hit(final int n) {
        if (this.shown) {
            if (Math.random() < 0.5) {
                this.speedX = (this.genSpeedX + this.speedX) * -1.4;
            }
            else {
                this.speedY = (this.genSpeedY + this.speedY) * -1.4;
            }
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
            return;
        }
        for (int j = 0; j < this.shape.npoints; ++j) {
            this.sprite.addPoint((int)(Math.round(this.shape.xpoints[j] * cos + this.shape.ypoints[j] * sin) + this.middleX), (int)(Math.round(this.shape.ypoints[j] * cos - this.shape.xpoints[j] * sin) + this.middleY));
        }
    }
}
