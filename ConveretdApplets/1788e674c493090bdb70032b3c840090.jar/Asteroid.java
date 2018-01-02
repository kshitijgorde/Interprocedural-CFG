import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Asteroid extends Flying
{
    Polygon shape;
    int aWidth;
    int aHeight;
    double angle;
    double middleX;
    double middleY;
    double speedX;
    double speedY;
    int speedZ;
    int crashStadium;
    public Polygon sprite;
    public int shield;
    public int zLevel;
    public boolean shown;
    public boolean dangerous;
    public boolean crashing;
    
    public Asteroid(final int aWidth, final int aHeight) {
        this.zLevel = 0;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shown = false;
        this.crashing = false;
        this.dangerous = false;
        this.shield = 0;
    }
    
    public void start(final int speedZ) {
        if (!this.shown) {
            (this.shape = new Polygon()).addPoint((int)((Math.random() - 0.5) * 10.0), (int)((Math.random() - 0.5) * 10.0) - 24);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 8, (int)((Math.random() - 0.5) * 10.0) - 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 16, (int)((Math.random() - 0.5) * 10.0) - 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 16, (int)((Math.random() - 0.5) * 10.0) - 8);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 24, (int)((Math.random() - 0.5) * 10.0));
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 16, (int)((Math.random() - 0.5) * 10.0) + 8);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 16, (int)((Math.random() - 0.5) * 10.0) + 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) + 8, (int)((Math.random() - 0.5) * 10.0) + 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0), (int)((Math.random() - 0.5) * 10.0) + 24);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 8, (int)((Math.random() - 0.5) * 10.0) + 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 16, (int)((Math.random() - 0.5) * 10.0) + 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 16, (int)((Math.random() - 0.5) * 10.0) + 8);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 24, (int)((Math.random() - 0.5) * 10.0));
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 16, (int)((Math.random() - 0.5) * 10.0) - 8);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 16, (int)((Math.random() - 0.5) * 10.0) - 16);
            this.shape.addPoint((int)((Math.random() - 0.5) * 10.0) - 8, (int)((Math.random() - 0.5) * 10.0) - 16);
            this.angle = 0.0;
            this.middleX = this.aWidth / 2;
            this.middleY = this.aHeight / 2;
            this.speedZ = speedZ;
            this.zLevel = 1;
            this.shown = true;
            this.dangerous = false;
            this.crashing = false;
            this.crashStadium = 0;
            this.shield = 10;
            this.speedX = 0.0;
            this.speedY = 0.0;
            while (this.speedX == 0.0 && this.speedY == 0.0) {
                this.speedX = (Math.random() - 0.5) * speedZ * 2.2 * speedZ;
                this.speedY = (Math.random() - 0.5) * speedZ * 2.2 * speedZ;
            }
            this.render();
        }
    }
    
    public void move() {
        if (this.shown) {
            this.angle += 0.02;
            this.middleX += this.speedX;
            this.middleY += this.speedY;
            this.zLevel += this.speedZ;
            this.speedX *= 1.016;
            this.speedY *= 1.016;
            if (this.middleX < -50.0 || this.middleX > this.aWidth + 50 || this.middleY < -50.0 || this.middleY > this.aHeight + 50) {
                this.shown = false;
            }
            if (this.zLevel >= 100) {
                this.dangerous = true;
            }
            if (this.crashing && this.crashStadium < 40) {
                ++this.crashStadium;
            }
            this.render();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            if (this.crashing) {
                graphics.setColor(new Color(4144959));
                graphics.fillPolygon(this.sprite);
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.sprite);
                this.middleX += this.crashStadium + 10;
                this.render();
                graphics.setColor(new Color(4144959));
                graphics.fillPolygon(this.sprite);
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.sprite);
                this.middleY += this.crashStadium + 10;
                this.render();
                graphics.setColor(new Color(4144959));
                graphics.fillPolygon(this.sprite);
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.sprite);
                this.middleX -= this.crashStadium * 2 + 20;
                this.render();
                graphics.setColor(new Color(4144959));
                graphics.fillPolygon(this.sprite);
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.sprite);
                this.middleY -= this.crashStadium * 2 + 20;
                this.render();
                graphics.setColor(new Color(4144959));
                graphics.fillPolygon(this.sprite);
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.sprite);
                this.middleX += this.crashStadium + 10;
                this.middleY += this.crashStadium + 10;
                this.render();
                return;
            }
            if (this.dangerous) {
                graphics.setColor(new Color(4276545));
            }
            else if (this.zLevel > 50) {
                graphics.setColor(new Color(3815994));
            }
            else {
                graphics.setColor(new Color(3355443));
            }
            graphics.fillPolygon(this.sprite);
            graphics.setColor(Color.gray);
            graphics.drawPolygon(this.sprite);
        }
    }
    
    public void paintBar(final Graphics graphics) {
        if (this.shown && this.shield != 0 && this.zLevel > 25) {
            graphics.setColor(Color.black);
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 11, 10, 3);
            if (this.shield >= 5) {
                graphics.setColor(new Color(43520));
            }
            else {
                graphics.setColor(new Color(12255232));
            }
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 11, this.shield, 3);
            if (this.dangerous && !this.crashing) {
                graphics.setColor(new Color(13482888));
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.drawRect((int)this.middleX - 5, (int)this.middleY - 11, 10, 3);
        }
    }
    
    public void crash() {
        if (this.shown && !this.crashing) {
            Sounds.play(Sounds.asteroid);
            this.dangerous = false;
            this.crashing = true;
            this.crashStadium = 1;
            this.shield = 0;
            while (this.speedX < 0.75 && this.speedX > -0.75 && this.speedY < 0.75 && this.speedY > -0.75) {
                this.speedX *= 1.5;
                this.speedY *= 1.5;
            }
        }
    }
    
    public boolean hit(final int n) {
        this.shield -= n;
        if (this.shield <= 0) {
            this.shield = 0;
            this.crash();
            return true;
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
        if (this.crashing) {
            if (this.zLevel < 75) {
                for (int i = 0; i < this.shape.npoints; ++i) {
                    this.sprite.addPoint((int)(Math.round(this.shape.xpoints[i] * cos + this.shape.ypoints[i] * sin) * this.zLevel / 500L + this.middleX), (int)(Math.round(this.shape.ypoints[i] * cos - this.shape.xpoints[i] * sin) * this.zLevel / 500L + this.middleY));
                }
                return;
            }
            for (int j = 0; j < this.shape.npoints; ++j) {
                this.sprite.addPoint((int)(Math.round(this.shape.xpoints[j] * cos + this.shape.ypoints[j] * sin) / 6L + this.middleX), (int)(Math.round(this.shape.ypoints[j] * cos - this.shape.xpoints[j] * sin) / 6L + this.middleY));
            }
        }
        else {
            if (this.zLevel < 100) {
                for (int k = 0; k < this.shape.npoints; ++k) {
                    this.sprite.addPoint((int)(Math.round(this.shape.xpoints[k] * cos * this.zLevel / 100.0 + this.shape.ypoints[k] * sin * this.zLevel / 100.0) + this.middleX), (int)(Math.round(this.shape.ypoints[k] * cos * this.zLevel / 100.0 - this.shape.xpoints[k] * sin * this.zLevel / 100.0) + this.middleY));
                }
                return;
            }
            for (int l = 0; l < this.shape.npoints; ++l) {
                this.sprite.addPoint((int)(Math.round(this.shape.xpoints[l] * cos + this.shape.ypoints[l] * sin) + this.middleX), (int)(Math.round(this.shape.ypoints[l] * cos - this.shape.xpoints[l] * sin) + this.middleY));
            }
        }
    }
}
