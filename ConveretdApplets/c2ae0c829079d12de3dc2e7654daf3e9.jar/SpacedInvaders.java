import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpacedInvaders extends Applet implements Runnable, KeyListener, MouseListener
{
    protected int width;
    protected int height;
    private int v;
    private int score;
    private int high;
    private int life;
    private int deadUFOCount;
    private int timer1;
    private int level;
    public boolean[] keystate;
    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int FIRE = 2;
    private final int REFRESH_RATE = 10;
    private final int SWARM_ROWS = 5;
    private final int SWARM_COLS = 11;
    final int INTRO = 0;
    final int START = 1;
    final int GAME_OVER = 2;
    final int NEW_LEVEL = 3;
    protected int GAME_STATE;
    int updatealiens;
    int aliencount;
    Graphics offscreen;
    Image image;
    MediaTracker t;
    GunManager gm;
    NewSwarm theSwarm;
    Shield theShield;
    Life thelives;
    Mystery theMysteryShip;
    Thread animation;
    Color color;
    Font mediumFont;
    Font bigFont;
    FontMetrics fm;
    String scoreString;
    String highString;
    
    public SpacedInvaders() {
        this.keystate = new boolean[3];
        this.LEFT = 0;
        this.RIGHT = 1;
        this.FIRE = 2;
        this.REFRESH_RATE = 10;
        this.SWARM_ROWS = 5;
        this.SWARM_COLS = 11;
        this.INTRO = 0;
        this.START = 1;
        this.GAME_OVER = 2;
        this.NEW_LEVEL = 3;
        this.mediumFont = new Font("Helvetica", 1, 14);
        this.bigFont = new Font("Helvetica", 1, 20);
        this.scoreString = "SCORE: ";
        this.highString = "HIGH SCORE: ";
    }
    
    public void init() {
        this.color = Color.red;
        this.GAME_STATE = 0;
        this.updatealiens = 0;
        this.aliencount = 5;
        this.timer1 = 0;
        this.level = 1;
        this.width = this.bounds().width;
        this.height = this.bounds().height;
        this.v = 240;
        this.score = 0;
        this.high = 0;
        this.life = 3;
        this.deadUFOCount = 0;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.image = this.createImage(this.width, this.height);
        (this.offscreen = this.image.getGraphics()).setFont(this.mediumFont);
        this.fm = this.getFontMetrics(this.mediumFont);
        this.t = new MediaTracker(this);
        final Image[] array = new Image[3];
        final Image[] array2 = new Image[3];
        final Image[] array3 = new Image[3];
        for (int i = 3; i < 6; ++i) {
            array[i - 3] = this.getImage(this.getCodeBase(), "Bitmaps/alien" + i + ".gif");
            this.t.addImage(array[i - 3], 5);
        }
        for (int j = 3; j < 6; ++j) {
            array3[j - 3] = this.getImage(this.getCodeBase(), "Bitmaps/saucer" + j + ".gif");
            this.t.addImage(array3[j - 3], 5);
        }
        final Image image = this.getImage(this.getCodeBase(), "Bitmaps/Ship.gif");
        this.t.addImage(image, 1);
        final Image image2 = this.getImage(this.getCodeBase(), "Bitmaps/shield.gif");
        this.t.addImage(image2, 0);
        for (int k = 0; k < 3; ++k) {
            array2[k] = this.getImage(this.getCodeBase(), "Bitmaps/life.gif");
            this.t.addImage(array2[k], 0);
        }
        final Image image3 = this.getImage(this.getCodeBase(), "Bitmaps/explosion.gif");
        this.t.addImage(image3, 0);
        final Image image4 = this.getImage(this.getCodeBase(), "Bitmaps/explosion2.gif");
        this.t.addImage(image3, 0);
        final Image image5 = this.getImage(this.getCodeBase(), "Bitmaps/mystery.gif");
        this.t.addImage(image5, 0);
        try {
            this.t.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.theMysteryShip = new Mystery(image5, image4, this);
        this.thelives = new Life(array2, this);
        this.theShield = new Shield(100, 100, image2, this);
        this.theSwarm = new NewSwarm(array, array3, image3, 0, this.width, 5, 11, this);
        this.gm = new GunManager(this.width, this.height, image, image4, (Intersect[][])this.theSwarm.getSwarm(), this, 5, 11, (Intersect[][])this.theShield.getShield(), (Intersect)this.theMysteryShip.getTarget());
        this.theSwarm.getTarget(this.gm, (Intersect[][])this.theShield.getShield());
    }
    
    public void start() {
        this.animation = new Thread(this);
        if (this.animation != null) {
            this.animation.start();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            this.gm.update();
            this.theMysteryShip.moveMystery();
            if (this.timer1 > 1000) {
                this.theMysteryShip.ms.reset();
                this.timer1 = 0;
            }
            if (this.keystate[0] && this.v >= 32) {
                this.v -= 5;
                this.gm.moveGun(this.v);
            }
            if (this.keystate[1] && this.v <= this.width - 32) {
                this.v += 5;
                this.gm.moveGun(this.v);
            }
            if (this.keystate[2]) {
                this.gm.fireMissile(this.v);
            }
            Thread.currentThread();
            Thread.yield();
            try {
                Thread.sleep(10L);
            }
            catch (Exception ex) {}
            ++this.updatealiens;
            ++this.timer1;
            if (this.GAME_STATE == 2) {
                if (this.color == Color.red) {
                    this.color = Color.yellow;
                }
                else if (this.color == Color.yellow) {
                    this.color = Color.red;
                }
                this.theShield.reset();
                this.gm.reset();
                this.theSwarm.suspend();
                this.theSwarm.setLevel();
                this.thelives.setLife(3);
                this.deadUFOCount = 0;
                if (this.score > this.high) {
                    this.high = this.score;
                }
                this.v = 240;
                this.life = 3;
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex2) {}
            }
            if (this.GAME_STATE == 3) {
                this.gm.reset();
                this.theSwarm.suspend();
                this.repaint();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.sleep(2000L);
                }
                catch (Exception ex3) {}
                this.theSwarm.setLevel();
                this.theShield.reset();
                this.theSwarm.gameOver();
                ++this.life;
                this.thelives.setLife(this.life);
                this.GAME_STATE = 1;
                this.theSwarm.resume();
                this.deadUFOCount = 0;
                this.v = 240;
            }
            if (this.GAME_STATE == 0) {
                if (this.color == Color.red) {
                    this.color = Color.yellow;
                }
                else if (this.color == Color.yellow) {
                    this.color = Color.red;
                }
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex4) {}
            }
        }
    }
    
    public void stop() {
        if (this.animation != null) {
            this.animation.stop();
            this.animation = null;
        }
    }
    
    public void updateSprites() {
        this.theSwarm.move();
        this.gm.update();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.GAME_STATE == 1) {
            this.offscreen.setColor(Color.black);
            this.offscreen.fillRect(0, 0, this.width, this.height);
            this.offscreen.setColor(Color.cyan);
            this.offscreen.drawString(this.scoreString + this.score, 10, 13);
            this.offscreen.drawString(this.highString + this.high, 180, 13);
            this.theSwarm.paint(this.offscreen);
            this.gm.paint(this.offscreen);
            this.theShield.paint(this.offscreen);
            this.thelives.paint(this.offscreen);
            this.theMysteryShip.ms.paint(this.offscreen);
            graphics.drawImage(this.image, 0, 0, this);
        }
        if (this.GAME_STATE == 0) {
            this.offscreen.setColor(Color.black);
            this.offscreen.fillRect(0, 0, this.width, this.height);
            this.offscreen.setColor(Color.yellow);
            this.offscreen.setFont(this.bigFont);
            this.fm = this.getFontMetrics(this.bigFont);
            this.offscreen.drawString("Super Java Invaders", 130, 150);
            this.offscreen.setFont(this.mediumFont);
            this.fm = this.getFontMetrics(this.mediumFont);
            this.offscreen.setColor(this.color);
            this.offscreen.drawString("CLICK TO START", 170, 250);
            this.offscreen.setColor(Color.red);
            this.offscreen.drawString("By Colin Edwards 2002", 150, 400);
            graphics.drawImage(this.image, 0, 0, this);
        }
        if (this.GAME_STATE == 2) {
            this.offscreen.setColor(Color.black);
            this.offscreen.fillRect(0, 0, this.width, this.height);
            this.offscreen.setColor(Color.cyan);
            this.offscreen.drawString(this.scoreString + this.score, 10, 13);
            this.offscreen.drawString(this.highString + this.high, 180, 13);
            this.offscreen.setFont(this.bigFont);
            this.fm = this.getFontMetrics(this.bigFont);
            this.offscreen.setColor(Color.red);
            this.offscreen.drawString("Game Over", 180, 200);
            this.offscreen.setColor(this.color);
            this.offscreen.drawString("PRESS SPACE TO PLAY AGAIN", 90, 350);
            graphics.drawImage(this.image, 0, 0, this);
            this.offscreen.setFont(this.mediumFont);
            this.fm = this.getFontMetrics(this.mediumFont);
        }
        if (this.GAME_STATE == 3) {
            this.offscreen.setColor(Color.black);
            this.offscreen.fillRect(0, 0, this.width, this.height);
            this.offscreen.setFont(this.bigFont);
            this.fm = this.getFontMetrics(this.bigFont);
            this.offscreen.setColor(Color.red);
            this.offscreen.drawString("LEVEL " + this.level, 175, 200);
            graphics.drawImage(this.image, 0, 0, this);
            this.offscreen.setFont(this.mediumFont);
            this.fm = this.getFontMetrics(this.mediumFont);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'p') {
            this.keystate[1] = true;
        }
        if (keyEvent.getKeyChar() == 'o') {
            this.keystate[0] = true;
        }
        if (keyEvent.getKeyChar() == ' ' && this.GAME_STATE == 1) {
            this.keystate[2] = true;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'p') {
            this.keystate[1] = false;
        }
        if (keyEvent.getKeyChar() == 'o') {
            this.keystate[0] = false;
        }
        if (keyEvent.getKeyChar() == ' ') {
            this.keystate[2] = false;
        }
        if (keyEvent.getKeyChar() == ' ' && this.GAME_STATE == 2) {
            for (int i = 0; i < 1000; ++i) {
                this.score = 0;
            }
            this.GAME_STATE = 1;
            this.theSwarm.gameOver();
            this.theSwarm.resume();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.theSwarm.alienManager.start();
        this.theMysteryShip.ms.reset();
        this.timer1 = 0;
        this.GAME_STATE = 1;
    }
    
    public void incScore(final int n) {
        this.score += n;
        this.doGamePlay();
    }
    
    public void deadUFOCount() {
        ++this.deadUFOCount;
        if (this.deadUFOCount == 55) {
            ++this.level;
            this.GAME_STATE = 3;
        }
    }
    
    public void doGamePlay() {
        boolean b = false;
        this.theSwarm.setSwarmDelay();
        if (this.deadUFOCount > 40) {
            b = true;
        }
        this.theSwarm.setFireFreq(4.0E-4, b);
        if (this.deadUFOCount > 50) {
            this.theSwarm.setFireFreq(0.05, false);
        }
    }
    
    public void shipIsDead() {
        this.thelives.setLife(--this.life);
        this.gm.doExplosion();
        this.theSwarm.suspend();
        Thread.currentThread();
        Thread.yield();
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex) {}
        ((Sprite)this.gm.gun).restore();
        this.theSwarm.resume();
        if (this.life == 0) {
            this.GAME_STATE = 2;
        }
    }
}
