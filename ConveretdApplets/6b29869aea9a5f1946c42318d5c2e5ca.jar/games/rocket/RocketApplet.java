// 
// Decompiled by Procyon v0.5.30
// 

package games.rocket;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class RocketApplet extends Applet implements Runnable, MouseMotionListener, MouseListener
{
    protected int sleepInt;
    protected boolean inGame;
    protected boolean newLevel;
    protected boolean gameOver;
    protected int level;
    protected int score;
    protected int highScore;
    protected Thread myThread;
    protected int width;
    protected int height;
    protected int roundIteration;
    protected Font font;
    protected int shipLife;
    protected int shipX;
    protected int shipY;
    protected Point shipFireFrom;
    protected Point[] shipRocket;
    protected Point[] shipRocketExplode;
    protected double[] shipRocketDistance;
    protected int[] shipRocketExplodeTime;
    protected int shipRockets;
    protected int shipRocketSpeed;
    protected int shipRocketExplodeDuration;
    protected int shipRocketExplodeSize;
    protected int shipRocketWidth;
    protected int shipRocketHeight;
    protected Polygon ship;
    protected boolean shipHit;
    protected int enemyRockets;
    protected int enemyRocketsMax;
    protected int enemyRocketsMaxSpeed;
    protected int enemyRocketsLeftToLaunch;
    protected double[] enemyRocketSpeed;
    protected double[] enemyRocketX;
    protected double[] enemyRocketY;
    protected int[] enemyRocketAngle;
    protected int[] enemyRocketDelay;
    protected boolean[] enemyRocketShowing;
    protected double shipRocketSpeedBonusTargetX;
    protected double shipRocketSpeedBonusTargetY;
    protected double shipRocketExplodeSizeBonusTargetX;
    protected double shipRocketExplodeSizeBonusTargetY;
    protected double pointsBonusTargetX;
    protected double pointsBonusTargetY;
    protected int shipRocketSpeedBonusStartTime;
    protected int shipRocketExplodeSizeBonusStartTime;
    protected int pointsBonusStartTime;
    protected int bonusTargetSize;
    protected int shipRocketSpeedBonus;
    protected int shipRocketExplodeSizeBonus;
    protected static final double BONUSDURATION = 700.0;
    protected double[] xDistance;
    protected double[] yDistance;
    protected Color[] explodeColor;
    
    public RocketApplet() {
        this.sleepInt = 50;
        this.inGame = false;
        this.newLevel = false;
        this.gameOver = false;
        this.level = 1;
        this.score = 0;
        this.highScore = 50;
        this.width = 0;
        this.height = 0;
        this.roundIteration = 0;
        this.font = new Font("helvetica", 1, 12);
        this.shipLife = 5;
        this.shipX = 200;
        this.shipY = 50;
        this.shipFireFrom = new Point();
        this.shipRockets = 5;
        this.shipRocketSpeed = 20;
        this.shipRocketExplodeDuration = 20;
        this.shipRocketExplodeSize = 25;
        this.shipRocketWidth = 8;
        this.shipRocketHeight = 8;
        this.shipHit = false;
        this.enemyRockets = 0;
        this.enemyRocketsMax = 10;
        this.enemyRocketsMaxSpeed = 5;
        this.shipRocketSpeedBonusStartTime = 0;
        this.shipRocketExplodeSizeBonusStartTime = 0;
        this.pointsBonusStartTime = 0;
        this.bonusTargetSize = 15;
        this.shipRocketSpeedBonus = 0;
        this.shipRocketExplodeSizeBonus = 0;
        this.xDistance = new double[360];
        this.yDistance = new double[360];
        this.explodeColor = new Color[] { new Color(105, 24, 20), new Color(112, 26, 22), new Color(120, 28, 23), new Color(128, 30, 25), new Color(136, 32, 26), new Color(144, 34, 28), new Color(151, 35, 29), new Color(159, 37, 31), new Color(167, 39, 33), new Color(175, 41, 34), new Color(183, 43, 35) };
    }
    
    public void init() {
        this.setCursor(new Cursor(1));
        this.setFont(this.font);
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.setBackground(Color.black);
        for (int i = 0; i < 90; ++i) {
            final double n = 0.01745329 * i;
            this.xDistance[i] = Math.sin(n);
            this.yDistance[i] = Math.cos(n) * -1.0;
        }
        for (int j = 90; j < 360; ++j) {
            this.xDistance[j] = this.yDistance[j - 90] * -1.0;
            this.yDistance[j] = this.xDistance[j - 90];
        }
        this.initEnemyRockets();
        this.initShip();
        this.initBonus();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    private void newGame() {
        this.enemyRocketsMax = 10;
        this.enemyRocketsMaxSpeed = 5;
        this.shipX = 200;
        this.shipY = 50;
        this.shipLife = 5;
        this.level = 1;
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
        this.score = 0;
        this.initEnemyRockets();
        this.initShip();
        this.initBonus();
        this.gameOver = false;
    }
    
    private void newLevel() {
        this.shipX *= (int)1.1;
        this.shipY *= (int)1.05;
        ++this.enemyRocketsMax;
        ++this.enemyRocketsMaxSpeed;
        this.initEnemyRockets();
        this.initShip();
        this.initBonus();
        ++this.level;
        this.newLevel = false;
    }
    
    private void initShip() {
        this.ship = new Polygon(new int[] { (int)(this.width / 2 - this.shipX / 2 * 0.8), (int)(this.width / 2 + this.shipX / 2 * 0.8), this.width / 2 + this.shipX / 2, (int)(this.width / 2 + this.shipX * 0.2), (int)(this.width / 2 + this.shipX * 0.2), (int)(this.width / 2 - this.shipX * 0.2), (int)(this.width / 2 - this.shipX * 0.2), this.width / 2 - this.shipX / 2 }, new int[] { this.height, this.height, (int)(this.height - this.shipY * 0.7), (int)(this.height - this.shipY * 0.7), this.height - this.shipY, this.height - this.shipY, (int)(this.height - this.shipY * 0.7), (int)(this.height - this.shipY * 0.7) }, 8);
        this.shipFireFrom.x = this.width / 2;
        this.shipFireFrom.y = this.height - this.shipY;
        this.shipRocket = new Point[this.shipRockets];
        this.shipRocketExplode = new Point[this.shipRockets];
        this.shipRocketExplodeTime = new int[this.shipRockets];
        this.shipRocketDistance = new double[this.shipRockets];
        for (int i = 0; i < this.shipRockets; ++i) {
            this.shipRocket[i] = new Point();
            this.shipRocketExplode[i] = new Point();
        }
    }
    
    private void initEnemyRockets() {
        this.enemyRockets = (int)(Math.random() * this.enemyRocketsMax) + 4;
        this.enemyRocketsLeftToLaunch = this.enemyRockets;
        this.enemyRocketSpeed = new double[this.enemyRockets];
        this.enemyRocketX = new double[this.enemyRockets];
        this.enemyRocketY = new double[this.enemyRockets];
        this.enemyRocketAngle = new int[this.enemyRockets];
        this.enemyRocketDelay = new int[this.enemyRockets];
        this.enemyRocketShowing = new boolean[this.enemyRockets];
        for (int i = 0; i < this.enemyRockets; ++i) {
            this.enemyRocketSpeed[i] = Math.random() * this.enemyRocketsMaxSpeed + 1.0;
            this.enemyRocketAngle[i] = (int)(Math.random() * 40.0) + 160;
            this.enemyRocketX[i] = Math.random() * (this.width * 0.25) + this.width * 0.5;
            this.enemyRocketY[i] = -10.0;
            this.enemyRocketDelay[i] = (int)(Math.random() * (10 * this.enemyRockets + 100)) + 10;
            this.enemyRocketShowing[i] = true;
        }
        this.inGame = false;
        this.roundIteration = 0;
    }
    
    private void initBonus() {
        this.shipRocketSpeedBonusTargetX = this.bonusTargetSize * -1;
        this.shipRocketSpeedBonusTargetY = (int)(Math.random() * 100.0) + 50;
        this.shipRocketExplodeSizeBonusTargetX = this.bonusTargetSize * -1;
        this.shipRocketExplodeSizeBonusTargetY = (int)(Math.random() * 100.0) + 50;
        this.pointsBonusTargetX = this.bonusTargetSize * -1;
        this.pointsBonusTargetY = (int)(Math.random() * 100.0) + 50;
        if (this.shipRocketSpeedBonus == 0) {
            this.shipRocketSpeedBonusStartTime = (int)(Math.random() * 1000.0) + 50;
        }
        if (this.shipRocketExplodeSizeBonus == 0) {
            this.shipRocketExplodeSizeBonusStartTime = (int)(Math.random() * 1000.0) + 50;
        }
        this.pointsBonusStartTime = (int)(Math.random() * 700.0) + 50;
    }
    
    public synchronized void run() {
        while (true) {
            if (this.inGame && !this.gameOver) {
                ++this.roundIteration;
                try {
                    Thread.sleep(this.sleepInt);
                    this.moveEnemyRockets();
                    this.moveShipRockets();
                    this.checkRocketsHitShip();
                    this.checkShipRocketsHitEnemyRocketsOrBonus();
                    this.moveBonusTarget();
                    this.checkForBonus();
                    this.repaint();
                    this.wait();
                }
                catch (InterruptedException ex) {
                    System.out.println("sleep failed");
                }
            }
            else {
                try {
                    this.repaint();
                    this.wait();
                    Thread.sleep(500L);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private void moveBonusTarget() {
        if (this.shipRocketSpeedBonusStartTime != 0 && this.shipRocketSpeedBonusStartTime < this.roundIteration) {
            this.shipRocketSpeedBonusTargetX += 1.7;
            this.shipRocketSpeedBonusTargetY += Math.sin(0.05 * this.shipRocketSpeedBonusTargetX);
        }
        if (this.shipRocketExplodeSizeBonusStartTime != 0 && this.shipRocketExplodeSizeBonusStartTime < this.roundIteration) {
            this.shipRocketExplodeSizeBonusTargetX += 1.4;
            this.shipRocketExplodeSizeBonusTargetY += Math.sin(0.03 * this.shipRocketExplodeSizeBonusTargetX);
        }
        if (this.pointsBonusStartTime != 0 && this.pointsBonusStartTime < this.roundIteration) {
            this.pointsBonusTargetX += 2.1;
            this.pointsBonusTargetY += Math.sin(0.025 * this.pointsBonusTargetX);
        }
    }
    
    private void checkForBonus() {
        if (this.shipRocketSpeedBonus > 0) {
            --this.shipRocketSpeedBonus;
            this.shipRocketSpeed = 50;
        }
        else {
            this.shipRocketSpeed = 20;
        }
        if (this.shipRocketExplodeSizeBonus > 0) {
            --this.shipRocketExplodeSizeBonus;
            this.shipRocketExplodeSize = 35;
        }
        else {
            this.shipRocketExplodeSize = 25;
        }
    }
    
    private void fireShip(final int x, final int y) {
        for (int i = 0; i < this.shipRockets; ++i) {
            if (this.shipRocketExplode[i].x == 0 && this.shipRocketExplode[i].y == 0) {
                this.shipRocketExplode[i].x = x;
                this.shipRocketExplode[i].y = y;
                this.shipRocket[i].x = this.shipFireFrom.x;
                this.shipRocket[i].y = this.shipFireFrom.y;
                final int n = this.shipRocketExplode[i].x - this.shipFireFrom.x;
                final int n2 = this.shipRocketExplode[i].y - this.shipFireFrom.y;
                this.shipRocketDistance[i] = Math.sqrt(n * n + n2 * n2);
                break;
            }
        }
    }
    
    private void checkRocketsHitShip() {
        for (int i = 0; i < this.enemyRockets; ++i) {
            if (this.enemyRocketShowing[i] && this.ship.inside((int)this.enemyRocketX[i], (int)this.enemyRocketY[i])) {
                this.enemyRocketShowing[i] = false;
                --this.shipLife;
                this.shipHit = true;
                if (this.shipLife < 0) {
                    this.gameOver = true;
                }
            }
        }
    }
    
    private void checkShipRocketsHitEnemyRocketsOrBonus() {
        for (int i = 0; i < this.enemyRockets; ++i) {
            for (int j = 0; j < this.shipRockets; ++j) {
                if (this.shipRocketExplodeTime[j] > 0) {
                    final double sqrt = Math.sqrt(Math.pow(this.shipRocketExplode[j].x - this.enemyRocketX[i], 2.0) + Math.pow(this.shipRocketExplode[j].y - this.enemyRocketY[i], 2.0));
                    if (this.enemyRocketShowing[i] && sqrt < this.shipRocketExplodeSize / 2) {
                        this.score += this.level;
                        this.enemyRocketShowing[i] = false;
                    }
                    if (Math.sqrt(Math.pow(this.shipRocketExplode[j].x - (this.shipRocketSpeedBonusTargetX + this.bonusTargetSize / 2), 2.0) + Math.pow(this.shipRocketExplode[j].y - (this.shipRocketSpeedBonusTargetY + this.bonusTargetSize / 2), 2.0)) < this.bonusTargetSize / 2 + this.shipRocketExplodeSize / 2) {
                        this.shipRocketSpeedBonus = 700;
                        this.shipRocketSpeedBonusTargetX = 0 - this.bonusTargetSize;
                        this.shipRocketSpeedBonusStartTime = 0;
                    }
                    if (Math.sqrt(Math.pow(this.shipRocketExplode[j].x - (this.shipRocketExplodeSizeBonusTargetX + this.bonusTargetSize / 2), 2.0) + Math.pow(this.shipRocketExplode[j].y - (this.shipRocketExplodeSizeBonusTargetY + this.bonusTargetSize / 2), 2.0)) < this.bonusTargetSize / 2 + this.shipRocketExplodeSize / 2) {
                        this.shipRocketExplodeSizeBonus = 700;
                        this.shipRocketExplodeSizeBonusTargetX = 0 - this.bonusTargetSize;
                        this.shipRocketExplodeSizeBonusStartTime = 0;
                    }
                    if (Math.sqrt(Math.pow(this.shipRocketExplode[j].x - (this.pointsBonusTargetX + this.bonusTargetSize / 2), 2.0) + Math.pow(this.shipRocketExplode[j].y - (this.pointsBonusTargetY + this.bonusTargetSize / 2), 2.0)) < this.bonusTargetSize / 2 + this.shipRocketExplodeSize / 2) {
                        this.score += 50;
                        this.pointsBonusTargetX = 0 - this.bonusTargetSize;
                        this.pointsBonusStartTime = 0;
                    }
                }
            }
        }
    }
    
    private void moveShipRockets() {
        for (int i = 0; i < this.shipRockets; ++i) {
            if (this.shipRocketExplode[i].x != 0 || this.shipRocketExplode[i].y != 0) {
                if (this.shipRocketExplodeTime[i] > 0) {
                    final int[] shipRocketExplodeTime = this.shipRocketExplodeTime;
                    final int n = i;
                    --shipRocketExplodeTime[n];
                    if (this.shipRocketExplodeTime[i] == 0) {
                        this.shipRocketExplode[i].x = 0;
                        this.shipRocketExplode[i].y = 0;
                    }
                }
                else {
                    final double n2 = (this.shipRocketDistance[i] - this.shipRocketSpeed) / this.shipRocketDistance[i];
                    this.shipRocketDistance[i] -= this.shipRocketSpeed;
                    this.shipRocket[i].x = (int)(this.shipRocketExplode[i].x - (this.shipRocketExplode[i].x - this.shipRocket[i].x) * n2);
                    this.shipRocket[i].y = (int)(this.shipRocketExplode[i].y - (this.shipRocketExplode[i].y - this.shipRocket[i].y) * n2);
                    if (this.shipRocketDistance[i] < 1.0) {
                        this.shipRocketExplodeTime[i] = this.shipRocketExplodeDuration;
                    }
                }
            }
        }
    }
    
    private void moveEnemyRockets() {
        int enemyRocketsLeftToLaunch = 0;
        int n = 0;
        for (int i = 0; i < this.enemyRockets; ++i) {
            if (this.enemyRocketX[i] < 0.0 || this.enemyRocketX[i] > this.width) {
                this.enemyRocketShowing[i] = false;
            }
            if (this.enemyRocketY[i] > this.height) {
                this.enemyRocketShowing[i] = false;
            }
            if (this.enemyRocketY[i] == -10.0) {
                ++enemyRocketsLeftToLaunch;
            }
            if (this.enemyRocketShowing[i] && this.enemyRocketDelay[i] < this.roundIteration) {
                ++n;
                this.enemyRocketX[i] += this.enemyRocketSpeed[i] * this.xDistance[this.enemyRocketAngle[i]];
                this.enemyRocketY[i] += this.enemyRocketSpeed[i] * this.yDistance[this.enemyRocketAngle[i]];
            }
        }
        this.enemyRocketsLeftToLaunch = enemyRocketsLeftToLaunch;
        if (this.enemyRocketsLeftToLaunch == 0 && n == 0) {
            this.newLevel = true;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.gameOver) {
            this.newGame();
        }
        else if (this.newLevel) {
            this.newLevel();
            this.inGame = true;
        }
        else if (this.inGame) {
            this.fireShip(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.inGame = true;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this, "Rocket")).setPriority(10);
            this.myThread.start();
        }
    }
    
    public void stop() {
        if (this.myThread != null) {
            this.myThread = null;
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.font);
        graphics.setColor(Color.white);
        if (this.shipHit) {
            graphics.setColor(Color.red);
            this.shipHit = false;
        }
        else {
            graphics.setColor(Color.darkGray);
        }
        graphics.fillPolygon(this.ship);
        graphics.setColor(Color.white);
        if (this.inGame) {
            graphics.setColor(Color.green);
            graphics.fillRect(0, this.height - (int)(50.0 * (this.shipRocketSpeedBonus / 700.0)), 20, (int)(50.0 * (this.shipRocketSpeedBonus / 700.0)));
            graphics.fillOval((int)this.shipRocketSpeedBonusTargetX, (int)this.shipRocketSpeedBonusTargetY, this.bonusTargetSize, this.bonusTargetSize);
            graphics.setColor(Color.blue);
            graphics.fillRect(20, this.height - (int)(50.0 * (this.shipRocketExplodeSizeBonus / 700.0)), 20, (int)(50.0 * (this.shipRocketExplodeSizeBonus / 700.0)));
            graphics.fillOval((int)this.shipRocketExplodeSizeBonusTargetX, (int)this.shipRocketExplodeSizeBonusTargetY, this.bonusTargetSize, this.bonusTargetSize);
            graphics.setColor(Color.yellow);
            graphics.fillOval((int)this.pointsBonusTargetX, (int)this.pointsBonusTargetY, this.bonusTargetSize, this.bonusTargetSize);
            graphics.setColor(Color.white);
            for (int i = 0; i < this.enemyRockets; ++i) {
                if (this.enemyRocketShowing[i]) {
                    graphics.fillPolygon(new int[] { (int)this.enemyRocketX[i], (int)this.enemyRocketX[i] - 3, (int)this.enemyRocketX[i] + 3 }, new int[] { (int)this.enemyRocketY[i], (int)this.enemyRocketY[i] - 8, (int)this.enemyRocketY[i] - 8 }, 3);
                }
            }
            graphics.drawString("Score:" + this.score, 2, 12);
            graphics.drawString("Level:" + this.level + "(" + this.enemyRocketsLeftToLaunch + ")", 2, 30);
            final String string = "High Score:" + this.highScore + " ";
            graphics.drawString(string, this.width - fontMetrics.stringWidth(string), 12);
            if (this.gameOver) {
                final String s = "Game Over";
                graphics.drawString(s, this.width / 2 - fontMetrics.stringWidth(s) / 2, this.height / 2);
            }
            else {
                graphics.drawString("Lives:" + this.shipLife, this.width / 2 - 20, this.height - 12);
                if (this.newLevel) {
                    final String s2 = "Click to start new level";
                    graphics.drawString(s2, this.width / 2 - fontMetrics.stringWidth(s2) / 2, this.height / 2);
                }
            }
            for (int j = 0; j < this.shipRockets; ++j) {
                if (this.shipRocketExplode[j].x != 0 || this.shipRocketExplode[j].y != 0) {
                    graphics.setColor(Color.lightGray);
                    if (this.shipRocketExplodeTime[j] == 0) {
                        graphics.fillOval(this.shipRocket[j].x - this.shipRocketWidth / 2, this.shipRocket[j].y - this.shipRocketHeight / 2, this.shipRocketWidth, this.shipRocketHeight);
                    }
                    else {
                        this.addExplosion(graphics, j);
                    }
                }
            }
        }
        else {
            graphics.drawString("Click to start", this.width / 2 - 40, this.height / 2);
        }
        this.notifyAll();
    }
    
    private void addExplosion(final Graphics graphics, final int n) {
        graphics.setColor(this.explodeColor[(int)(10.0 * (this.shipRocketExplodeTime[n] / this.shipRocketExplodeDuration))]);
        graphics.fillOval(this.shipRocketExplode[n].x - this.shipRocketExplodeSize / 2, this.shipRocketExplode[n].y - this.shipRocketExplodeSize / 2, this.shipRocketExplodeSize, this.shipRocketExplodeSize);
    }
}
