import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Star extends Applet implements Runnable
{
    Thread starThread;
    public int out;
    public boolean gameover;
    public int kills;
    public int health;
    public int deathWait;
    public int randomSeed;
    public int randomTime;
    public int timeInc;
    public int[] enemyX;
    public int[] enemyY;
    public int[] enemySpeed;
    public int[] enemyOpen;
    public int[] enemyType;
    public int[] enemyDeath;
    public int[] enemySlope;
    public int[] bX;
    public int[] bY;
    public int[] bOpen;
    public int[] bType;
    public int numBullets;
    public int numStars;
    public int bulletShot;
    public int[] starsX;
    public int[] starsY;
    public int xSize;
    public int ySize;
    public int speed;
    public int sleepTime;
    public int shipX;
    public int shipY;
    Image imageBuffer;
    Image ship;
    Image enemy1;
    Image enemy2;
    Image ex;
    Image b1;
    Image b2;
    
    public Star() {
        this.out = 0;
        this.gameover = false;
        this.kills = 0;
        this.health = 100;
        this.deathWait = 12;
        this.randomSeed = 50;
        this.randomTime = (int)(Math.random() * this.randomSeed + 1.0 + 50.0);
        this.timeInc = 0;
        this.numBullets = 20;
        this.numStars = 30;
        this.bulletShot = 0;
        this.xSize = 640;
        this.ySize = 480;
        this.speed = 6;
        this.sleepTime = 8;
        this.shipX = this.xSize / 2 - 17;
        this.shipY = this.ySize - 80;
    }
    
    public void collisions() {
        if (this.out == 0) {
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < this.numBullets; ++j) {
                    if (this.bOpen[j] == 1 && (this.bY[j] > this.shipY & this.bY[j] < this.shipY + 32) && (this.bX[j] > this.shipX & this.bX[j] < this.shipX + 32)) {
                        this.bOpen[j] = 0;
                        this.health -= 5;
                    }
                    if ((this.bOpen[j] == 1 & this.enemyOpen[i] == 1) && (this.bY[j] > this.enemyY[i] & this.bY[j] < this.enemyY[i] + 32) && (this.bX[j] > this.enemyX[i] & this.bX[j] < this.enemyX[i] + 32)) {
                        this.enemyDeath[i] = 0;
                        this.enemyOpen[i] = 2;
                        this.bOpen[j] = 0;
                        ++this.kills;
                    }
                }
                if (this.enemyOpen[i] == 1 && (this.shipX + 32 > this.enemyX[i] & this.shipX < this.enemyX[i] + 32) && (this.shipY + 32 > this.enemyY[i] & this.shipY < this.enemyY[i] + 36)) {
                    this.enemyDeath[i] = 0;
                    this.enemyOpen[i] = 2;
                    this.health -= 5;
                }
            }
        }
    }
    
    public void init() {
        this.setSize(this.xSize, this.ySize);
        this.enemyX = new int[5];
        this.enemyY = new int[5];
        this.enemySpeed = new int[5];
        this.enemyOpen = new int[5];
        this.enemyType = new int[5];
        this.enemyDeath = new int[5];
        this.enemySlope = new int[5];
        this.bX = new int[this.numBullets];
        this.bY = new int[this.numBullets];
        this.bOpen = new int[this.numBullets];
        this.bType = new int[this.numBullets];
        this.ship = this.getImage(this.getCodeBase(), "ship.gif");
        this.enemy1 = this.getImage(this.getCodeBase(), "enemy1.gif");
        this.enemy2 = this.getImage(this.getCodeBase(), "enemy2.gif");
        this.ex = this.getImage(this.getCodeBase(), "ex.gif");
        this.b1 = this.getImage(this.getCodeBase(), "b1.gif");
        this.b2 = this.getImage(this.getCodeBase(), "b2.gif");
        this.initStars();
        this.repaint();
        this.addMouseListener((MouseListener)new Star.LocalMouseListener(this, this));
        this.addMouseMotionListener((MouseMotionListener)new Star.LocalMouseMotionListener(this, this));
    }
    
    public void initStars() {
        this.imageBuffer = this.createImage(this.xSize, this.ySize);
        this.starsX = new int[this.numStars];
        this.starsY = new int[this.numStars];
        for (int i = 0; i < this.numStars; ++i) {
            this.starsX[i] = (int)(Math.random() * this.xSize - 1.0 + 1.0);
            this.starsY[i] = (int)(Math.random() * this.ySize - 1.0 + 1.0);
        }
    }
    
    public void moveBullets() {
        for (int i = 0; i < this.numBullets; ++i) {
            if (this.bOpen[i] == 1) {
                if (this.bY[i] < 5) {
                    this.bOpen[i] = 0;
                }
                else if (this.bType[i] == 1) {
                    final int[] by = this.bY;
                    final int n = i;
                    by[n] -= 2;
                }
                else {
                    final int[] by2 = this.bY;
                    final int n2 = i;
                    by2[n2] -= 4;
                }
            }
        }
    }
    
    public void moveEnemys() {
        for (int i = 0; i < 5; ++i) {
            if (this.enemyX[i] + this.enemySlope[i] < 0) {
                final int[] enemySlope = this.enemySlope;
                final int n = i;
                enemySlope[n] *= -1;
            }
            if (this.enemyX[i] + this.enemySlope[i] > this.xSize - 32) {
                final int[] enemySlope2 = this.enemySlope;
                final int n2 = i;
                enemySlope2[n2] *= -1;
            }
            final int[] enemyX = this.enemyX;
            final int n3 = i;
            enemyX[n3] += this.enemySlope[i];
            final int n4 = (int)(Math.random() * 150.0 + 1.0);
            if (n4 == 3) {
                final int[] enemySlope3 = this.enemySlope;
                final int n5 = i;
                enemySlope3[n5] *= -1;
            }
            if (n4 == 5 & Math.abs(this.enemySlope[i]) < 8) {
                final int[] enemySlope4 = this.enemySlope;
                final int n6 = i;
                enemySlope4[n6] *= -2;
            }
            if (n4 == 6 & Math.abs(this.enemySlope[i]) > 0) {
                final int[] enemySlope5 = this.enemySlope;
                final int n7 = i;
                enemySlope5[n7] /= -2;
            }
            if (this.enemyOpen[i] > 0) {
                if (this.enemyOpen[i] == 2) {
                    if (this.enemyDeath[i] == this.deathWait) {
                        this.enemyOpen[i] = 0;
                    }
                    else {
                        final int[] enemyDeath = this.enemyDeath;
                        final int n8 = i;
                        ++enemyDeath[n8];
                    }
                }
                if (this.enemyY[i] > this.ySize) {
                    this.enemyOpen[i] = 0;
                }
                else {
                    final int[] enemyY = this.enemyY;
                    final int n9 = i;
                    enemyY[n9] += this.enemySpeed[i] + (this.speed - 6);
                }
            }
        }
    }
    
    public void moveStars() {
        for (int i = 0; i < this.numStars; ++i) {
            if (this.starsY[i] + 1 > this.ySize - this.speed * 2) {
                this.starsY[i] = 0;
            }
            else {
                final int[] starsY = this.starsY;
                final int n = i;
                starsY[n] += this.speed;
            }
        }
    }
    
    public void newEnemy() {
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            if (n == 0 & this.enemyOpen[i] == 0) {
                this.enemyOpen[i] = 1;
                n = 1;
                this.enemySpeed[i] = (int)(Math.random() * 5.0 + 1.0);
                this.enemyType[i] = (int)(Math.random() * 2.0 + 1.0);
                this.enemySlope[i] = (int)(Math.random() * 3.0 + 1.0 - 1.0);
                if ((int)(Math.random() * 2.0 + 1.0) == 1) {
                    this.enemySlope[i] = -this.enemySlope[i];
                }
                this.enemyX[i] = (int)(Math.random() * (this.xSize - 32) + 1.0);
                this.enemyY[i] = -32;
                this.randomTime = (int)(Math.random() * this.randomSeed + 1.0 + 50.0);
                this.timeInc = 0;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.imageBuffer, 0, 0, this);
    }
    
    public void prepareImageBuffer() {
        final Graphics graphics = this.imageBuffer.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.xSize, this.ySize);
        graphics.setColor(Color.blue);
        for (int i = 0; i < this.numStars; ++i) {
            graphics.drawRect(this.starsX[i], this.starsY[i], 1, 1);
        }
        graphics.setColor(Color.yellow);
        graphics.drawString("Kills = " + this.kills, 5, 20);
        graphics.drawString("Health = " + this.health + "%", this.xSize - 80, 20);
        if (this.out == 0) {
            if (this.gameover) {
                graphics.drawString("Game Over", this.xSize / 2 - 15, this.ySize / 2);
            }
        }
        else {
            graphics.drawString("Game Paused", this.xSize / 2 - 23, this.ySize / 2);
        }
        graphics.setXORMode(Color.black);
        for (int j = 0; j < this.numBullets; ++j) {
            if (this.bOpen[j] == 1) {
                if (this.bType[j] == 1) {
                    graphics.drawImage(this.b1, this.bX[j] - this.b1.getWidth(this) / 2, this.bY[j] - this.b1.getHeight(this), this);
                }
                else {
                    graphics.drawImage(this.b2, this.bX[j] - this.b1.getWidth(this) / 2, this.bY[j] - this.b1.getHeight(this), this);
                }
            }
        }
        for (int k = 0; k < 5; ++k) {
            if (this.enemyOpen[k] == 2) {
                graphics.drawImage(this.ex, this.enemyX[k], this.enemyY[k], this);
            }
            if (this.enemyOpen[k] == 1) {
                if (this.enemyType[k] == 1) {
                    graphics.drawImage(this.enemy1, this.enemyX[k], this.enemyY[k], this);
                }
                else {
                    graphics.drawImage(this.enemy2, this.enemyX[k], this.enemyY[k], this);
                }
            }
        }
        graphics.drawImage(this.ship, this.shipX, this.shipY, this);
    }
    
    public void run() {
        while (true) {
            if (this.health == 0) {
                this.kills = 0;
                this.health = 100;
                this.gameover = true;
            }
            ++this.timeInc;
            if (this.timeInc == this.randomTime) {
                this.newEnemy();
            }
            this.moveEnemys();
            this.moveStars();
            this.moveBullets();
            this.collisions();
            this.prepareImageBuffer();
            this.repaint();
            try {
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.starThread == null) {
            (this.starThread = new Thread(this)).setPriority(5);
            this.starThread.start();
        }
    }
    
    public void stop() {
        if (this.starThread != null) {
            this.starThread.stop();
            this.starThread = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
