import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Irue
{
    Iridium game;
    Image irueImg;
    Image spriteImg;
    Image scaledImg;
    Asteroid[] scrap;
    Zephi[] zephis;
    int aWidth;
    int aHeight;
    int shield;
    int cannonX;
    int cannonY;
    int upperImg;
    int leftImg;
    int scaledLeft;
    int scaledUpper;
    boolean imgLoaded;
    public int stadium;
    public Explosion nova;
    public boolean shown;
    public LaserShot shot;
    public boolean alive;
    
    public Irue(final int aWidth, final int aHeight, final Iridium game) {
        this.alive = false;
        this.stadium = 1;
        this.shown = false;
        this.game = game;
        this.aWidth = aWidth;
        this.aHeight = aHeight;
        this.shot = new LaserShot(new Color(15610845), false);
        this.nova = new Explosion();
        this.scrap = new Asteroid[25];
        for (int i = 0; i < 25; ++i) {
            this.scrap[i] = new Asteroid(this.aWidth, this.aHeight);
        }
        this.imgLoaded = false;
    }
    
    public void start(final Image irueImg, final Zephi[] zephis) {
        this.irueImg = irueImg;
        this.zephis = zephis;
        this.shown = true;
        this.shield = 200;
        this.load();
        this.render();
    }
    
    public void move() {
        if (this.shown) {
            if (this.stadium < 150) {
                if (this.imgLoaded) {
                    ++this.stadium;
                    if (this.stadium == 150) {
                        this.alive = true;
                    }
                }
                this.render();
            }
            else if (!this.shot.shooting && this.alive) {
                for (int i = 0; i < 15; ++i) {
                    if (this.zephis[i].shown) {
                        int n;
                        do {
                            n = (int)Math.round(Math.random() * (this.zephis.length - 1));
                        } while (!this.zephis[n].shown);
                        this.shot.shoot(this.cannonX, this.cannonY, this.zephis[n].sprite.xpoints[6], this.zephis[n].sprite.ypoints[6]);
                        Sounds.play(Sounds.laser);
                        break;
                    }
                }
            }
            if (this.nova.stadium > 300) {
                this.shown = false;
            }
        }
        this.shot.move();
        this.nova.go();
        for (int j = 0; j < 25; ++j) {
            this.scrap[j].move();
        }
    }
    
    public void crash() {
        if (this.shown && !this.nova.exploding) {
            Sounds.play(Sounds.die);
            this.alive = false;
            this.shield = 0;
            this.nova.explode(this.aWidth / 2, this.aHeight / 2, this.aWidth - 10);
            for (int i = 0; i < 25; ++i) {
                this.scrap[i].start(1);
                this.scrap[i].crash();
            }
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
    
    public void paint(final Graphics graphics) {
        if (this.shown) {
            if (this.stadium == 1) {
                graphics.setColor(Color.lightGray);
                graphics.setFont(new Font("Sans-Serif", 0, 10));
                graphics.drawString("Loading Irue.gif", this.aWidth / 2 - 33, this.aHeight / 2 - 4);
            }
            else if (!graphics.drawImage(this.spriteImg, this.leftImg, this.upperImg, this.game)) {
                graphics.drawImage(this.scaledImg, this.scaledLeft, this.scaledUpper, this.game);
            }
        }
        this.shot.paint(graphics);
        this.nova.paint(graphics);
        for (int i = 0; i < 25; ++i) {
            this.scrap[i].paint(graphics);
        }
    }
    
    public void paintBar(final Graphics graphics) {
        if (this.shown && this.shield != 0 && this.stadium > 100) {
            graphics.setColor(Color.black);
            graphics.fillRect(this.aWidth / 2 - 25, this.aHeight / 2 - 60, 50, 3);
            if (this.shield >= 125) {
                graphics.setColor(new Color(43520));
            }
            else if (this.shield >= 50) {
                graphics.setColor(new Color(12303104));
            }
            else {
                graphics.setColor(new Color(12255232));
            }
            graphics.fillRect(this.aWidth / 2 - 25, this.aHeight / 2 - 60, this.shield / 4, 3);
            graphics.setColor(Color.gray);
            graphics.drawRect(this.aWidth / 2 - 25, this.aHeight / 2 - 60, 50, 3);
        }
    }
    
    void render() {
        if (this.shown) {
            if (this.stadium == 1) {
                this.load();
            }
            else if (this.stadium < 100) {
                if (this.stadium % 5 == 2) {
                    if (this.stadium != 2) {
                        this.scaledUpper = this.upperImg;
                        this.scaledLeft = this.leftImg;
                        this.scaledImg = this.spriteImg;
                    }
                    this.spriteImg = this.irueImg.getScaledInstance(this.stadium, this.stadium, 0);
                    this.leftImg = (this.aWidth - this.stadium) / 2;
                    this.upperImg = (this.aHeight - this.stadium) / 2;
                    if (this.stadium == 2) {
                        this.scaledUpper = this.upperImg;
                        this.scaledLeft = this.leftImg;
                        this.scaledImg = this.spriteImg;
                    }
                }
            }
            else {
                this.spriteImg = this.irueImg;
                this.leftImg = this.aWidth / 2 - 50;
                this.upperImg = this.aHeight / 2 - 50;
            }
            this.cannonX = 65 + this.leftImg;
            this.cannonY = 32 + this.upperImg;
        }
    }
    
    public void load() {
        if (!this.imgLoaded) {
            this.imgLoaded = this.load(this.irueImg);
        }
    }
    
    boolean load(final Image image) {
        return this.game.createImage(1, 1).getGraphics().drawImage(image, 0, 0, this.game);
    }
}
