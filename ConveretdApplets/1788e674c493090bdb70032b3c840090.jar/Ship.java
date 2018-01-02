import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ship extends Flying
{
    public Polygon sprite;
    public int shield;
    public int zLevel;
    public boolean shown;
    Iridium game;
    int aWidth;
    int aHeight;
    public Explosion explosion;
    public Explosion[] shotExplosions;
    public LaserShot[] shots;
    public Polygon shape;
    public int middleX;
    public int middleY;
    public int targetX;
    public int targetY;
    public int targetMoveX;
    public int targetMoveY;
    public int shieldCounter;
    public int autoDownSpeed;
    public int landingStadium;
    public double angle;
    public double goToAngle;
    public boolean usingShield;
    public boolean alive;
    public boolean wonTheGame;
    
    public Ship(final int aWidth, final int aHeight, final Iridium game) {
        this.wonTheGame = false;
        this.zLevel = 100;
        this.game = game;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shown = true;
        this.autoDownSpeed = 3;
        this.shots = new LaserShot[20];
        this.shotExplosions = new Explosion[20];
        for (int i = 0; i < 20; ++i) {
            this.shots[i] = new LaserShot(Color.red, true);
            this.shotExplosions[i] = new Explosion();
        }
        (this.shape = new Polygon()).addPoint(0, -14);
        this.shape.addPoint(10, -7);
        this.shape.addPoint(40, -15);
        this.shape.addPoint(10, 0);
        this.shape.addPoint(40, 15);
        this.shape.addPoint(10, 7);
        this.shape.addPoint(0, 14);
        this.shape.addPoint(-10, 7);
        this.shape.addPoint(-40, 15);
        this.shape.addPoint(-10, 0);
        this.shape.addPoint(-40, -15);
        this.shape.addPoint(-10, -7);
        this.middleX = this.aWidth / 2;
        this.middleY = -45;
        this.targetX = this.aWidth / 2;
        this.targetY = this.aHeight / 2;
        this.angle = 0.0;
        this.goToAngle = 0.0;
        this.shield = 100;
        this.alive = true;
        this.sprite = new Polygon();
        for (int j = 0; j < this.shape.npoints; ++j) {
            this.sprite.addPoint(this.shape.xpoints[j] + this.middleX, this.shape.ypoints[j] + this.middleY);
        }
        this.explosion = new Explosion();
        this.usingShield = false;
        this.shieldCounter = 0;
        this.landingStadium = 0;
    }
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            for (int i = 0; i < 20; ++i) {
                this.shots[i].paint(graphics);
                this.shotExplosions[i].paint(graphics);
            }
            if (this.alive && !this.wonTheGame) {
                graphics.setColor(Color.red);
                graphics.drawLine(this.targetX - 1, this.targetY, this.targetX - 2, this.targetY);
                graphics.drawLine(this.targetX + 1, this.targetY, this.targetX + 2, this.targetY);
                graphics.drawLine(this.targetX, this.targetY - 1, this.targetX, this.targetY - 2);
                graphics.drawLine(this.targetX, this.targetY + 1, this.targetX, this.targetY + 2);
            }
            if (this.usingShield) {
                graphics.setColor(new Color(8425600));
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.fillPolygon(this.sprite);
            if (this.usingShield) {
                graphics.setColor(Color.green);
            }
            else {
                graphics.setColor(Color.lightGray);
            }
            graphics.drawPolygon(this.sprite);
            if (this.game.levelWait == 0) {
                graphics.setColor(new Color(14527044));
            }
            else {
                graphics.setColor(new Color(13408563));
            }
            if (this.landingStadium == 0) {
                graphics.fillOval(this.middleX - 7, this.middleY - 7, 14, 14);
            }
            else if (this.landingStadium < 95) {
                graphics.fillOval(this.middleX - 7 * (101 - this.landingStadium) / 100, this.middleY - 7 * (101 - this.landingStadium) / 100, 14 * (101 - this.landingStadium) / 100, 14 * (101 - this.landingStadium) / 100);
            }
            this.explosion.paint(graphics);
        }
    }
    
    public boolean inside(final int n, final int n2) {
        return (n > this.sprite.xpoints[10] || n > this.sprite.xpoints[8]) && (n2 > this.sprite.ypoints[10] || n2 > this.sprite.ypoints[2]) && (n < this.sprite.xpoints[2] || n < this.sprite.xpoints[4]) && (n2 < this.sprite.ypoints[4] || n2 < this.sprite.ypoints[8]);
    }
}
