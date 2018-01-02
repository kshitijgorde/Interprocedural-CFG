import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Polygon;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.AudioClip;
import java.awt.Point;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Asteroids extends Applet implements Runnable
{
    Thread loadThread;
    Thread loopThread;
    static final int DELAY = 50;
    static final int MAX_SHIPS = 3;
    static final int MAX_SHOTS = 6;
    static final int MAX_ROCKS = 8;
    static final int MAX_SCRAP = 20;
    static final int SCRAP_COUNT = 30;
    static final int HYPER_COUNT = 60;
    static final int STORM_PAUSE = 30;
    static final int UFO_PASSES = 3;
    static final int MIN_ROCK_SIDES = 8;
    static final int MAX_ROCK_SIDES = 12;
    static final int MIN_ROCK_SIZE = 20;
    static final int MAX_ROCK_SIZE = 40;
    static final int MIN_ROCK_SPEED = 2;
    static final int MAX_ROCK_SPEED = 12;
    static final int BIG_POINTS = 25;
    static final int SMALL_POINTS = 50;
    static final int UFO_POINTS = 250;
    static final int MISSLE_POINTS = 500;
    static final int NEW_SHIP_POINTS = 5000;
    static final int NEW_UFO_POINTS = 2750;
    int numStars;
    Point[] stars;
    int score;
    int highScore;
    int newShipScore;
    int newUfoScore;
    boolean loaded;
    boolean paused;
    boolean playing;
    boolean sound;
    boolean detail;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    AsteroidsSprite ship;
    AsteroidsSprite ufo;
    AsteroidsSprite missle;
    AsteroidsSprite[] photons;
    AsteroidsSprite[] asteroids;
    AsteroidsSprite[] explosions;
    int shipsLeft;
    int shipCounter;
    int hyperCounter;
    int[] photonCounter;
    int photonIndex;
    int ufoPassesLeft;
    int ufoCounter;
    int missleCounter;
    boolean[] asteroidIsSmall;
    int asteroidsCounter;
    int asteroidsSpeed;
    int asteroidsLeft;
    int[] explosionCounter;
    int explosionIndex;
    AudioClip crashSound;
    AudioClip explosionSound;
    AudioClip fireSound;
    AudioClip missleSound;
    AudioClip saucerSound;
    AudioClip thrustersSound;
    AudioClip warpSound;
    boolean thrustersPlaying;
    boolean saucerPlaying;
    boolean misslePlaying;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    Font font;
    FontMetrics fm;
    int fontWidth;
    int fontHeight;
    
    public String getAppletInfo() {
        return "Asteroids, Copyright 1998 by Mike Hall.";
    }
    
    public void init() {
        System.out.println("Asteroids, Copyright 1998 by Mike Hall.");
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        AsteroidsSprite.width = size.width;
        AsteroidsSprite.height = size.height;
        this.numStars = AsteroidsSprite.width * AsteroidsSprite.height / 5000;
        this.stars = new Point[this.numStars];
        for (int i = 0; i < this.numStars; ++i) {
            this.stars[i] = new Point((int)(Math.random() * AsteroidsSprite.width), (int)(Math.random() * AsteroidsSprite.height));
        }
        this.ship = new AsteroidsSprite();
        this.ship.shape.addPoint(0, -10);
        this.ship.shape.addPoint(7, 10);
        this.ship.shape.addPoint(-7, 10);
        for (int j = 0; j < 6; ++j) {
            this.photons[j] = new AsteroidsSprite();
            this.photons[j].shape.addPoint(1, 1);
            this.photons[j].shape.addPoint(1, -1);
            this.photons[j].shape.addPoint(-1, 1);
            this.photons[j].shape.addPoint(-1, -1);
        }
        this.ufo = new AsteroidsSprite();
        this.ufo.shape.addPoint(-15, 0);
        this.ufo.shape.addPoint(-10, -5);
        this.ufo.shape.addPoint(-5, -5);
        this.ufo.shape.addPoint(-5, -9);
        this.ufo.shape.addPoint(5, -9);
        this.ufo.shape.addPoint(5, -5);
        this.ufo.shape.addPoint(10, -5);
        this.ufo.shape.addPoint(15, 0);
        this.ufo.shape.addPoint(10, 5);
        this.ufo.shape.addPoint(-10, 5);
        this.missle = new AsteroidsSprite();
        this.missle.shape.addPoint(0, -4);
        this.missle.shape.addPoint(1, -3);
        this.missle.shape.addPoint(1, 3);
        this.missle.shape.addPoint(2, 4);
        this.missle.shape.addPoint(-2, 4);
        this.missle.shape.addPoint(-1, 3);
        this.missle.shape.addPoint(-1, -3);
        for (int k = 0; k < 8; ++k) {
            this.asteroids[k] = new AsteroidsSprite();
        }
        for (int l = 0; l < 20; ++l) {
            this.explosions[l] = new AsteroidsSprite();
        }
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.fontWidth = this.fm.getMaxAdvance();
        this.fontHeight = this.fm.getHeight();
        this.highScore = 0;
        this.sound = true;
        this.detail = true;
        this.initGame();
        this.endGame();
    }
    
    public void initGame() {
        this.score = 0;
        this.shipsLeft = 3;
        this.asteroidsSpeed = 2;
        this.newShipScore = 5000;
        this.newUfoScore = 2750;
        this.initShip();
        this.initPhotons();
        this.stopUfo();
        this.stopMissle();
        this.initAsteroids();
        this.initExplosions();
        this.playing = true;
        this.paused = false;
    }
    
    public void endGame() {
        this.playing = false;
        this.stopShip();
        this.stopUfo();
        this.stopMissle();
    }
    
    public void start() {
        if (this.loopThread == null) {
            (this.loopThread = new Thread(this)).start();
        }
        if (!this.loaded && this.loadThread == null) {
            (this.loadThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loopThread != null) {
            this.loopThread.stop();
            this.loopThread = null;
        }
        if (this.loadThread != null) {
            this.loadThread.stop();
            this.loadThread = null;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.loaded && Thread.currentThread() == this.loadThread) {
            this.loadSounds();
            this.loaded = true;
            this.loadThread.stop();
        }
        while (Thread.currentThread() == this.loopThread) {
            if (!this.paused) {
                this.updateShip();
                this.updatePhotons();
                this.updateUfo();
                this.updateMissle();
                this.updateAsteroids();
                this.updateExplosions();
                if (this.score > this.highScore) {
                    this.highScore = this.score;
                }
                if (this.score > this.newShipScore) {
                    this.newShipScore += 5000;
                    ++this.shipsLeft;
                }
                if (this.playing && this.score > this.newUfoScore && !this.ufo.active) {
                    this.newUfoScore += 2750;
                    this.ufoPassesLeft = 3;
                    this.initUfo();
                }
                if (this.asteroidsLeft <= 0 && --this.asteroidsCounter <= 0) {
                    this.initAsteroids();
                }
            }
            this.repaint();
            try {
                currentTimeMillis += 50L;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void loadSounds() {
        try {
            this.crashSound = this.getAudioClip(new URL(this.getDocumentBase(), "crash.au"));
            this.explosionSound = this.getAudioClip(new URL(this.getDocumentBase(), "explosion.au"));
            this.fireSound = this.getAudioClip(new URL(this.getDocumentBase(), "fire.au"));
            this.missleSound = this.getAudioClip(new URL(this.getDocumentBase(), "missle.au"));
            this.saucerSound = this.getAudioClip(new URL(this.getDocumentBase(), "saucer.au"));
            this.thrustersSound = this.getAudioClip(new URL(this.getDocumentBase(), "thrusters.au"));
            this.warpSound = this.getAudioClip(new URL(this.getDocumentBase(), "warp.au"));
        }
        catch (MalformedURLException ex) {}
        this.crashSound.play();
        this.crashSound.stop();
        this.explosionSound.play();
        this.explosionSound.stop();
        this.fireSound.play();
        this.fireSound.stop();
        this.missleSound.play();
        this.missleSound.stop();
        this.saucerSound.play();
        this.saucerSound.stop();
        this.thrustersSound.play();
        this.thrustersSound.stop();
        this.warpSound.play();
        this.warpSound.stop();
    }
    
    public void initShip() {
        this.ship.active = true;
        this.ship.angle = 0.0;
        this.ship.deltaAngle = 0.0;
        this.ship.currentX = 0.0;
        this.ship.currentY = 0.0;
        this.ship.deltaX = 0.0;
        this.ship.deltaY = 0.0;
        this.ship.render();
        if (this.loaded) {
            this.thrustersSound.stop();
        }
        this.thrustersPlaying = false;
        this.hyperCounter = 0;
    }
    
    public void updateShip() {
        if (!this.playing) {
            return;
        }
        if (this.left) {
            final AsteroidsSprite ship = this.ship;
            ship.angle += 0.19634954084936207;
            if (this.ship.angle > 6.283185307179586) {
                final AsteroidsSprite ship2 = this.ship;
                ship2.angle -= 6.283185307179586;
            }
        }
        if (this.right) {
            final AsteroidsSprite ship3 = this.ship;
            ship3.angle -= 0.19634954084936207;
            if (this.ship.angle < 0.0) {
                final AsteroidsSprite ship4 = this.ship;
                ship4.angle += 6.283185307179586;
            }
        }
        final double n = -Math.sin(this.ship.angle);
        final double cos = Math.cos(this.ship.angle);
        final double n2 = 16.0;
        if (this.up) {
            if (this.ship.deltaX + n > -n2 && this.ship.deltaX + n < n2) {
                final AsteroidsSprite ship5 = this.ship;
                ship5.deltaX += n;
            }
            if (this.ship.deltaY + cos > -n2 && this.ship.deltaY + cos < n2) {
                final AsteroidsSprite ship6 = this.ship;
                ship6.deltaY += cos;
            }
        }
        if (this.down) {
            if (this.ship.deltaX - n > -n2 && this.ship.deltaX - n < n2) {
                final AsteroidsSprite ship7 = this.ship;
                ship7.deltaX -= n;
            }
            if (this.ship.deltaY - cos > -n2 && this.ship.deltaY - cos < n2) {
                final AsteroidsSprite ship8 = this.ship;
                ship8.deltaY -= cos;
            }
        }
        if (this.ship.active) {
            this.ship.advance();
            this.ship.render();
            if (this.hyperCounter > 0) {
                --this.hyperCounter;
            }
        }
        else if (--this.shipCounter <= 0) {
            if (this.shipsLeft > 0) {
                this.initShip();
                this.hyperCounter = 60;
                return;
            }
            this.endGame();
        }
    }
    
    public void stopShip() {
        this.ship.active = false;
        this.shipCounter = 30;
        if (this.shipsLeft > 0) {
            --this.shipsLeft;
        }
        if (this.loaded) {
            this.thrustersSound.stop();
        }
        this.thrustersPlaying = false;
    }
    
    public void initPhotons() {
        for (int i = 0; i < 6; ++i) {
            this.photons[i].active = false;
            this.photonCounter[i] = 0;
        }
        this.photonIndex = 0;
    }
    
    public void updatePhotons() {
        for (int i = 0; i < 6; ++i) {
            if (this.photons[i].active) {
                this.photons[i].advance();
                this.photons[i].render();
                final int[] photonCounter = this.photonCounter;
                final int n = i;
                if (--photonCounter[n] < 0) {
                    this.photons[i].active = false;
                }
            }
        }
    }
    
    public void initUfo() {
        this.ufo.active = true;
        this.ufo.currentX = -AsteroidsSprite.width / 2;
        this.ufo.currentY = Math.random() * AsteroidsSprite.height;
        this.ufo.deltaX = 2.0 + Math.random() * 10.0;
        if (Math.random() < 0.5) {
            this.ufo.deltaX = -this.ufo.deltaX;
            this.ufo.currentX = AsteroidsSprite.width / 2;
        }
        this.ufo.deltaY = 2.0 + Math.random() * 10.0;
        if (Math.random() < 0.5) {
            this.ufo.deltaY = -this.ufo.deltaY;
        }
        this.ufo.render();
        this.saucerPlaying = true;
        if (this.sound) {
            this.saucerSound.loop();
        }
        this.ufoCounter = (int)Math.floor(AsteroidsSprite.width / Math.abs(this.ufo.deltaX));
    }
    
    public void updateUfo() {
        if (this.ufo.active) {
            this.ufo.advance();
            this.ufo.render();
            if (--this.ufoCounter <= 0) {
                if (--this.ufoPassesLeft > 0) {
                    this.initUfo();
                    return;
                }
                this.stopUfo();
            }
            else {
                for (int i = 0; i < 6; ++i) {
                    if (this.photons[i].active && this.ufo.isColliding(this.photons[i])) {
                        if (this.sound) {
                            this.crashSound.play();
                        }
                        this.explode(this.ufo);
                        this.stopUfo();
                        this.score += 250;
                    }
                }
                final int n = (int)Math.max(Math.abs(this.ufo.currentX - this.ship.currentX), Math.abs(this.ufo.currentY - this.ship.currentY));
                if (this.ship.active && this.hyperCounter <= 0 && this.ufo.active && !this.missle.active && n > 160 && Math.random() < 0.03) {
                    this.initMissle();
                }
            }
        }
    }
    
    public void stopUfo() {
        this.ufo.active = false;
        this.ufoCounter = 0;
        this.ufoPassesLeft = 0;
        if (this.loaded) {
            this.saucerSound.stop();
        }
        this.saucerPlaying = false;
    }
    
    public void initMissle() {
        this.missle.active = true;
        this.missle.angle = 0.0;
        this.missle.deltaAngle = 0.0;
        this.missle.currentX = this.ufo.currentX;
        this.missle.currentY = this.ufo.currentY;
        this.missle.deltaX = 0.0;
        this.missle.deltaY = 0.0;
        this.missle.render();
        this.missleCounter = 3 * Math.max(AsteroidsSprite.width, AsteroidsSprite.height) / 20;
        if (this.sound) {
            this.missleSound.loop();
        }
        this.misslePlaying = true;
    }
    
    public void updateMissle() {
        if (this.missle.active) {
            if (--this.missleCounter <= 0) {
                this.stopMissle();
                return;
            }
            this.guideMissle();
            this.missle.advance();
            this.missle.render();
            for (int i = 0; i < 6; ++i) {
                if (this.photons[i].active && this.missle.isColliding(this.photons[i])) {
                    if (this.sound) {
                        this.crashSound.play();
                    }
                    this.explode(this.missle);
                    this.stopMissle();
                    this.score += 500;
                }
            }
            if (this.missle.active && this.ship.active && this.hyperCounter <= 0 && this.ship.isColliding(this.missle)) {
                if (this.sound) {
                    this.crashSound.play();
                }
                this.explode(this.ship);
                this.stopShip();
                this.stopUfo();
                this.stopMissle();
            }
        }
    }
    
    public void guideMissle() {
        if (!this.ship.active || this.hyperCounter > 0) {
            return;
        }
        final double n = this.ship.currentX - this.missle.currentX;
        final double n2 = this.ship.currentY - this.missle.currentY;
        if (n == 0.0 && n2 == 0.0) {}
        double atan;
        if (n == 0.0) {
            if (n2 < 0.0) {
                atan = -1.5707963267948966;
            }
            else {
                atan = 1.5707963267948966;
            }
        }
        else {
            atan = Math.atan(Math.abs(n2 / n));
            if (n2 > 0.0) {
                atan = -atan;
            }
            if (n < 0.0) {
                atan = 3.141592653589793 - atan;
            }
        }
        this.missle.angle = atan - 1.5707963267948966;
        this.missle.deltaX = 6.0 * -Math.sin(this.missle.angle);
        this.missle.deltaY = 6.0 * Math.cos(this.missle.angle);
    }
    
    public void stopMissle() {
        this.missle.active = false;
        this.missleCounter = 0;
        if (this.loaded) {
            this.missleSound.stop();
        }
        this.misslePlaying = false;
    }
    
    public void initAsteroids() {
        for (int i = 0; i < 8; ++i) {
            this.asteroids[i].shape = new Polygon();
            for (int n = 8 + (int)(Math.random() * 4.0), j = 0; j < n; ++j) {
                final double n2 = 6.283185307179586 / n * j;
                final double n3 = 20 + (int)(Math.random() * 20.0);
                this.asteroids[i].shape.addPoint((int)(-Math.round(n3 * Math.sin(n2))), (int)Math.round(n3 * Math.cos(n2)));
            }
            this.asteroids[i].active = true;
            this.asteroids[i].angle = 0.0;
            this.asteroids[i].deltaAngle = (Math.random() - 0.5) / 10.0;
            if (Math.random() < 0.5) {
                this.asteroids[i].currentX = -AsteroidsSprite.width / 2;
                if (Math.random() < 0.5) {
                    this.asteroids[i].currentX = AsteroidsSprite.width / 2;
                }
                this.asteroids[i].currentY = Math.random() * AsteroidsSprite.height;
            }
            else {
                this.asteroids[i].currentX = Math.random() * AsteroidsSprite.width;
                this.asteroids[i].currentY = -AsteroidsSprite.height / 2;
                if (Math.random() < 0.5) {
                    this.asteroids[i].currentY = AsteroidsSprite.height / 2;
                }
            }
            this.asteroids[i].deltaX = Math.random() * this.asteroidsSpeed;
            if (Math.random() < 0.5) {
                this.asteroids[i].deltaX = -this.asteroids[i].deltaX;
            }
            this.asteroids[i].deltaY = Math.random() * this.asteroidsSpeed;
            if (Math.random() < 0.5) {
                this.asteroids[i].deltaY = -this.asteroids[i].deltaY;
            }
            this.asteroids[i].render();
            this.asteroidIsSmall[i] = false;
        }
        this.asteroidsCounter = 30;
        this.asteroidsLeft = 8;
        if (this.asteroidsSpeed < 12) {
            ++this.asteroidsSpeed;
        }
    }
    
    public void initSmallAsteroids(final int n) {
        int n2 = 0;
        int n3 = 0;
        final double currentX = this.asteroids[n].currentX;
        final double currentY = this.asteroids[n].currentY;
        do {
            if (!this.asteroids[n3].active) {
                this.asteroids[n3].shape = new Polygon();
                for (int n4 = 8 + (int)(Math.random() * 4.0), i = 0; i < n4; ++i) {
                    final double n5 = 6.283185307179586 / n4 * i;
                    final double n6 = (20 + (int)(Math.random() * 20.0)) / 2;
                    this.asteroids[n3].shape.addPoint((int)(-Math.round(n6 * Math.sin(n5))), (int)Math.round(n6 * Math.cos(n5)));
                }
                this.asteroids[n3].active = true;
                this.asteroids[n3].angle = 0.0;
                this.asteroids[n3].deltaAngle = (Math.random() - 0.5) / 10.0;
                this.asteroids[n3].currentX = currentX;
                this.asteroids[n3].currentY = currentY;
                this.asteroids[n3].deltaX = Math.random() * 2.0 * this.asteroidsSpeed - this.asteroidsSpeed;
                this.asteroids[n3].deltaY = Math.random() * 2.0 * this.asteroidsSpeed - this.asteroidsSpeed;
                this.asteroids[n3].render();
                this.asteroidIsSmall[n3] = true;
                ++n2;
                ++this.asteroidsLeft;
            }
        } while (++n3 < 8 && n2 < 2);
    }
    
    public void updateAsteroids() {
        for (int i = 0; i < 8; ++i) {
            if (this.asteroids[i].active) {
                this.asteroids[i].advance();
                this.asteroids[i].render();
                for (int j = 0; j < 6; ++j) {
                    if (this.photons[j].active && this.asteroids[i].active && this.asteroids[i].isColliding(this.photons[j])) {
                        --this.asteroidsLeft;
                        this.asteroids[i].active = false;
                        this.photons[j].active = false;
                        if (this.sound) {
                            this.explosionSound.play();
                        }
                        this.explode(this.asteroids[i]);
                        if (!this.asteroidIsSmall[i]) {
                            this.score += 25;
                            this.initSmallAsteroids(i);
                        }
                        else {
                            this.score += 50;
                        }
                    }
                }
                if (this.ship.active && this.hyperCounter <= 0 && this.asteroids[i].active && this.asteroids[i].isColliding(this.ship)) {
                    if (this.sound) {
                        this.crashSound.play();
                    }
                    this.explode(this.ship);
                    this.stopShip();
                    this.stopUfo();
                    this.stopMissle();
                }
            }
        }
    }
    
    public void initExplosions() {
        for (int i = 0; i < 20; ++i) {
            this.explosions[i].shape = new Polygon();
            this.explosions[i].active = false;
            this.explosionCounter[i] = 0;
        }
        this.explosionIndex = 0;
    }
    
    public void explode(final AsteroidsSprite asteroidsSprite) {
        asteroidsSprite.render();
        int n = 2;
        if (this.detail || asteroidsSprite.sprite.npoints < 6) {
            n = 1;
        }
        for (int i = 0; i < asteroidsSprite.sprite.npoints; i += n) {
            ++this.explosionIndex;
            if (this.explosionIndex >= 20) {
                this.explosionIndex = 0;
            }
            this.explosions[this.explosionIndex].active = true;
            (this.explosions[this.explosionIndex].shape = new Polygon()).addPoint(asteroidsSprite.shape.xpoints[i], asteroidsSprite.shape.ypoints[i]);
            int n2 = i + 1;
            if (n2 >= asteroidsSprite.sprite.npoints) {
                n2 -= asteroidsSprite.sprite.npoints;
            }
            this.explosions[this.explosionIndex].shape.addPoint(asteroidsSprite.shape.xpoints[n2], asteroidsSprite.shape.ypoints[n2]);
            this.explosions[this.explosionIndex].angle = asteroidsSprite.angle;
            this.explosions[this.explosionIndex].deltaAngle = (Math.random() * 2.0 * 3.141592653589793 - 3.141592653589793) / 15.0;
            this.explosions[this.explosionIndex].currentX = asteroidsSprite.currentX;
            this.explosions[this.explosionIndex].currentY = asteroidsSprite.currentY;
            this.explosions[this.explosionIndex].deltaX = -asteroidsSprite.shape.xpoints[i] / 5;
            this.explosions[this.explosionIndex].deltaY = -asteroidsSprite.shape.ypoints[i] / 5;
            this.explosionCounter[this.explosionIndex] = 30;
        }
    }
    
    public void updateExplosions() {
        for (int i = 0; i < 20; ++i) {
            if (this.explosions[i].active) {
                this.explosions[i].advance();
                this.explosions[i].render();
                final int[] explosionCounter = this.explosionCounter;
                final int n = i;
                if (--explosionCounter[n] < 0) {
                    this.explosions[i].active = false;
                }
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1006) {
            this.left = true;
        }
        if (n == 1007) {
            this.right = true;
        }
        if (n == 1004) {
            this.up = true;
        }
        if (n == 1005) {
            this.down = true;
        }
        if ((this.up || this.down) && this.ship.active && !this.thrustersPlaying) {
            if (this.sound && !this.paused) {
                this.thrustersSound.loop();
            }
            this.thrustersPlaying = true;
        }
        if (n == 32 && this.ship.active) {
            if (this.sound & !this.paused) {
                this.fireSound.play();
            }
            ++this.photonIndex;
            if (this.photonIndex >= 6) {
                this.photonIndex = 0;
            }
            this.photons[this.photonIndex].active = true;
            this.photons[this.photonIndex].currentX = this.ship.currentX;
            this.photons[this.photonIndex].currentY = this.ship.currentY;
            this.photons[this.photonIndex].deltaX = 20.0 * -Math.sin(this.ship.angle);
            this.photons[this.photonIndex].deltaY = 20.0 * Math.cos(this.ship.angle);
            this.photonCounter[this.photonIndex] = Math.min(AsteroidsSprite.width, AsteroidsSprite.height) / 20;
        }
        if (n == 104 && this.ship.active && this.hyperCounter <= 0) {
            this.ship.currentX = Math.random() * AsteroidsSprite.width;
            this.ship.currentX = Math.random() * AsteroidsSprite.height;
            this.hyperCounter = 60;
            if (this.sound & !this.paused) {
                this.warpSound.play();
            }
        }
        if (n == 112) {
            if (this.paused) {
                if (this.sound && this.misslePlaying) {
                    this.missleSound.loop();
                }
                if (this.sound && this.saucerPlaying) {
                    this.saucerSound.loop();
                }
                if (this.sound && this.thrustersPlaying) {
                    this.thrustersSound.loop();
                }
            }
            else {
                if (this.misslePlaying) {
                    this.missleSound.stop();
                }
                if (this.saucerPlaying) {
                    this.saucerSound.stop();
                }
                if (this.thrustersPlaying) {
                    this.thrustersSound.stop();
                }
            }
            this.paused = !this.paused;
        }
        if (n == 109 && this.loaded) {
            if (this.sound) {
                this.crashSound.stop();
                this.explosionSound.stop();
                this.fireSound.stop();
                this.missleSound.stop();
                this.saucerSound.stop();
                this.thrustersSound.stop();
                this.warpSound.stop();
            }
            else {
                if (this.misslePlaying && !this.paused) {
                    this.missleSound.loop();
                }
                if (this.saucerPlaying && !this.paused) {
                    this.saucerSound.loop();
                }
                if (this.thrustersPlaying && !this.paused) {
                    this.thrustersSound.loop();
                }
            }
            this.sound = !this.sound;
        }
        if (n == 100) {
            this.detail = !this.detail;
        }
        if (n == 115 && this.loaded && !this.playing) {
            this.initGame();
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 1006) {
            this.left = false;
        }
        if (n == 1007) {
            this.right = false;
        }
        if (n == 1004) {
            this.up = false;
        }
        if (n == 1005) {
            this.down = false;
        }
        if (!this.up && !this.down && this.thrustersPlaying) {
            this.thrustersSound.stop();
            this.thrustersPlaying = false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, size.width, size.height);
        if (this.detail) {
            this.offGraphics.setColor(Color.white);
            for (int i = 0; i < this.numStars; ++i) {
                this.offGraphics.drawLine(this.stars[i].x, this.stars[i].y, this.stars[i].x, this.stars[i].y);
            }
        }
        this.offGraphics.setColor(Color.white);
        for (int j = 0; j < 6; ++j) {
            if (this.photons[j].active) {
                this.offGraphics.drawPolygon(this.photons[j].sprite);
            }
        }
        final int min = Math.min(this.missleCounter * 24, 255);
        this.offGraphics.setColor(new Color(min, min, min));
        if (this.missle.active) {
            this.offGraphics.drawPolygon(this.missle.sprite);
            this.offGraphics.drawLine(this.missle.sprite.xpoints[this.missle.sprite.npoints - 1], this.missle.sprite.ypoints[this.missle.sprite.npoints - 1], this.missle.sprite.xpoints[0], this.missle.sprite.ypoints[0]);
        }
        for (int k = 0; k < 8; ++k) {
            if (this.asteroids[k].active) {
                if (this.detail) {
                    this.offGraphics.setColor(Color.black);
                    this.offGraphics.fillPolygon(this.asteroids[k].sprite);
                }
                this.offGraphics.setColor(Color.white);
                this.offGraphics.drawPolygon(this.asteroids[k].sprite);
                this.offGraphics.drawLine(this.asteroids[k].sprite.xpoints[this.asteroids[k].sprite.npoints - 1], this.asteroids[k].sprite.ypoints[this.asteroids[k].sprite.npoints - 1], this.asteroids[k].sprite.xpoints[0], this.asteroids[k].sprite.ypoints[0]);
            }
        }
        if (this.ufo.active) {
            if (this.detail) {
                this.offGraphics.setColor(Color.black);
                this.offGraphics.fillPolygon(this.ufo.sprite);
            }
            this.offGraphics.setColor(Color.white);
            this.offGraphics.drawPolygon(this.ufo.sprite);
            this.offGraphics.drawLine(this.ufo.sprite.xpoints[this.ufo.sprite.npoints - 1], this.ufo.sprite.ypoints[this.ufo.sprite.npoints - 1], this.ufo.sprite.xpoints[0], this.ufo.sprite.ypoints[0]);
        }
        final int n = 255 - 4 * this.hyperCounter;
        if (this.ship.active) {
            if (this.detail && this.hyperCounter == 0) {
                this.offGraphics.setColor(Color.black);
                this.offGraphics.fillPolygon(this.ship.sprite);
            }
            this.offGraphics.setColor(new Color(n, n, n));
            this.offGraphics.drawPolygon(this.ship.sprite);
            this.offGraphics.drawLine(this.ship.sprite.xpoints[this.ship.sprite.npoints - 1], this.ship.sprite.ypoints[this.ship.sprite.npoints - 1], this.ship.sprite.xpoints[0], this.ship.sprite.ypoints[0]);
        }
        for (int l = 0; l < 20; ++l) {
            if (this.explosions[l].active) {
                final int n2 = 8 * this.explosionCounter[l];
                this.offGraphics.setColor(new Color(n2, n2, n2));
                this.offGraphics.drawPolygon(this.explosions[l].sprite);
            }
        }
        this.offGraphics.setFont(this.font);
        this.offGraphics.setColor(Color.white);
        this.offGraphics.drawString("Score: " + this.score, this.fontWidth, this.fontHeight);
        this.offGraphics.drawString("Ships: " + this.shipsLeft, this.fontWidth, size.height - this.fontHeight);
        final String string = "High: " + this.highScore;
        this.offGraphics.drawString(string, size.width - (this.fontWidth + this.fm.stringWidth(string)), this.fontHeight);
        if (!this.sound) {
            final String s = "Mute";
            this.offGraphics.drawString(s, size.width - (this.fontWidth + this.fm.stringWidth(s)), size.height - this.fontHeight);
        }
        if (!this.playing) {
            final String s2 = "A S T E R O I D S";
            this.offGraphics.drawString(s2, (size.width - this.fm.stringWidth(s2)) / 2, size.height / 2);
            final String s3 = "Copyright 1998 by Mike Hall";
            this.offGraphics.drawString(s3, (size.width - this.fm.stringWidth(s3)) / 2, size.height / 2 + this.fontHeight);
            if (!this.loaded) {
                final String s4 = "Loading sounds...";
                this.offGraphics.drawString(s4, (size.width - this.fm.stringWidth(s4)) / 2, size.height / 4);
            }
            else {
                final String s5 = "Game Over";
                this.offGraphics.drawString(s5, (size.width - this.fm.stringWidth(s5)) / 2, size.height / 4);
                final String s6 = "'S' to Start";
                this.offGraphics.drawString(s6, (size.width - this.fm.stringWidth(s6)) / 2, size.height / 4 + this.fontHeight);
            }
        }
        else if (this.paused) {
            final String s7 = "Game Paused";
            this.offGraphics.drawString(s7, (size.width - this.fm.stringWidth(s7)) / 2, size.height / 4);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public Asteroids() {
        this.loaded = false;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.photons = new AsteroidsSprite[6];
        this.asteroids = new AsteroidsSprite[8];
        this.explosions = new AsteroidsSprite[20];
        this.photonCounter = new int[6];
        this.asteroidIsSmall = new boolean[8];
        this.explosionCounter = new int[20];
        this.font = new Font("Helvetica", 1, 12);
    }
}
