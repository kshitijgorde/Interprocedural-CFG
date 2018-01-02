import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Zephi extends Flying
{
    Polygon shape1;
    Polygon shape2;
    Polygon backSprite;
    Explosion explosion;
    Ship ship;
    int aWidth;
    int aHeight;
    int counter;
    double speedX;
    double speedY;
    public double middleX;
    public double middleY;
    public Polygon sprite;
    public int shield;
    public int zLevel;
    public boolean shown;
    public boolean crashing;
    public boolean hitting;
    
    public Zephi(final int aWidth, final int aHeight, final Ship ship) {
        this.zLevel = 0;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shown = false;
        this.crashing = false;
        this.hitting = false;
        this.shield = 0;
        this.ship = ship;
        this.explosion = new Explosion();
    }
    
    public void start() {
        if (!this.shown && !this.explosion.exploding) {
            (this.shape1 = new Polygon()).addPoint(-12, 8);
            this.shape1.addPoint(-8, 14);
            this.shape1.addPoint(0, 16);
            this.shape1.addPoint(8, 14);
            this.shape1.addPoint(12, 8);
            this.shape1.addPoint(9, -11);
            this.shape1.addPoint(4, -16);
            this.shape1.addPoint(-4, -16);
            this.shape1.addPoint(-9, -11);
            (this.shape2 = new Polygon()).addPoint(-12, 8);
            this.shape2.addPoint(-8, 14);
            this.shape2.addPoint(0, 16);
            this.shape2.addPoint(8, 14);
            this.shape2.addPoint(12, 8);
            this.shape2.addPoint(8, 2);
            this.shape2.addPoint(0, 0);
            this.shape2.addPoint(-8, 2);
            this.zLevel = 85;
            this.shown = true;
            this.crashing = false;
            this.shield = 6;
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.counter = 0;
            switch ((int)(Math.random() * 4.0)) {
                case 0: {
                    this.middleX = -20.0;
                    this.middleY = Math.random() * this.aHeight;
                    break;
                }
                case 1: {
                    this.middleX = this.aWidth + 20;
                    this.middleY = Math.random() * this.aHeight;
                    break;
                }
                case 2: {
                    this.middleX = Math.random() * this.aWidth;
                    this.middleY = -20.0;
                    break;
                }
                default: {
                    this.middleX = Math.random() * this.aWidth;
                    this.middleY = this.aHeight + 20;
                    break;
                }
            }
            this.render();
        }
    }
    
    public void move() {
        if (this.shown) {
            if (!this.crashing) {
                if (this.counter == 4 && this.zLevel > 50) {
                    --this.zLevel;
                    this.counter = 0;
                }
                else {
                    ++this.counter;
                }
                this.middleX += (this.aWidth / 2 - this.middleX) / (this.zLevel - 49) / 4.0;
                this.middleY += (this.aHeight / 2 - this.middleY) / (this.zLevel - 49) / 4.0;
                if (!this.hitting && Math.pow((int)this.middleX - this.aWidth / 2, 2.0) + Math.pow((int)this.middleY - this.aHeight / 2, 2.0) <= 1600.0) {
                    this.hitting = true;
                }
                else if (this.hitting) {
                    this.crash();
                }
            }
            else {
                this.middleX += (this.aWidth / 2 - this.middleX) / (this.zLevel - 49) * 0.2;
                this.middleY += (this.aHeight / 2 - this.middleY) / (this.zLevel - 49) * 0.2;
                this.explosion.middleX = (int)this.middleX;
                this.explosion.middleY = (int)this.middleY;
                if (this.explosion.stadium >= 40) {
                    this.shown = false;
                }
            }
            this.render();
        }
        this.explosion.go();
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            graphics.setColor(new Color(14540253));
            graphics.fillPolygon(this.sprite);
            graphics.setColor(new Color(12303274));
            graphics.fillPolygon(this.backSprite);
            graphics.setColor(new Color(10066329));
            graphics.drawPolygon(this.sprite);
            graphics.drawPolygon(this.backSprite);
            graphics.drawLine(this.backSprite.xpoints[5], this.backSprite.ypoints[5], this.sprite.xpoints[6], this.sprite.ypoints[6]);
            graphics.drawLine(this.backSprite.xpoints[7], this.backSprite.ypoints[7], this.sprite.xpoints[7], this.sprite.ypoints[7]);
        }
        this.explosion.paint(graphics);
    }
    
    public void paintBar(final Graphics graphics) {
        if (this.shown && this.shield != 0 && this.zLevel > 25) {
            graphics.setColor(Color.black);
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 20, 10, 3);
            if (this.shield == 6) {
                graphics.setColor(new Color(43520));
            }
            else if (this.shield >= 3) {
                graphics.setColor(new Color(12303104));
            }
            else {
                graphics.setColor(new Color(12255232));
            }
            graphics.fillRect((int)this.middleX - 5, (int)this.middleY - 20, this.shield * 10 / 6, 3);
            graphics.setColor(Color.gray);
            graphics.drawRect((int)this.middleX - 5, (int)this.middleY - 20, 10, 3);
        }
    }
    
    public void crash() {
        if (this.shown && !this.crashing) {
            Sounds.play(Sounds.enemy);
            this.crashing = true;
            this.hitting = false;
            this.shield = 0;
            this.explosion.explode((int)this.middleX, (int)this.middleY, 40);
        }
    }
    
    public boolean hit(final int n) {
        if (this.shown) {
            this.shield -= n;
            if (this.shield <= 0) {
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
        if (this.middleY == this.aHeight / 2) {
            return;
        }
        this.sprite = new Polygon();
        this.backSprite = new Polygon();
        final double atan = Math.atan((this.aWidth / 2 - this.middleX) / (this.aHeight / 2 - this.middleY));
        double sin = Math.sin(atan);
        double cos = Math.cos(atan);
        if (this.middleY < this.aHeight / 2) {
            sin = -sin;
            cos = -cos;
        }
        for (int i = 0; i < this.shape1.npoints; ++i) {
            this.sprite.addPoint((int)(Math.round(this.shape1.xpoints[i] * cos + this.shape1.ypoints[i] * sin) * this.zLevel / 100L + this.middleX), (int)(Math.round(this.shape1.ypoints[i] * cos - this.shape1.xpoints[i] * sin) * this.zLevel / 100L + this.middleY));
        }
        for (int j = 0; j < this.shape2.npoints; ++j) {
            this.backSprite.addPoint((int)(Math.round(this.shape2.xpoints[j] * cos + this.shape2.ypoints[j] * sin) * this.zLevel / 100L + this.middleX), (int)(Math.round(this.shape2.ypoints[j] * cos - this.shape2.xpoints[j] * sin) * this.zLevel / 100L + this.middleY));
        }
    }
}
