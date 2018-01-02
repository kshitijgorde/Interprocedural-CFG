import java.awt.Graphics;
import java.awt.Event;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MissileCommando extends Applet implements Runnable
{
    private final int SCORE_WIDTH = 70;
    private final int WORLD_WIDTH = 430;
    private final int WORLD_HEIGHT = 300;
    private final int BASES = 5;
    private final int BASE_SPACING = 30;
    private final int BASE_WIDTH = 50;
    private final int BASE_HEIGHT = 40;
    private final int POINTS_MISSILE = 100;
    private final int POINTS_EXTRA_SHOTS = 50;
    private final int POINTS_BASE = 100;
    private final int POINTS_NEW_BASE = 5000;
    private final int MAX_SHOTS = 10;
    private int shots;
    private AudioClip startSound;
    private AudioClip applauseSound;
    private AudioClip missileSound;
    private AudioClip shotExplosionSound;
    private AudioClip missileExplosionSound;
    private AudioClip baseExplosionSound;
    private AudioClip music;
    private boolean playing;
    private boolean clearScreen;
    private boolean loadingSounds;
    private int score;
    private int level;
    private int speed;
    private int delay;
    private int missileCount;
    private int shotCount;
    private Semaphore missileSemaphore;
    private Semaphore messageSemaphore;
    private Font font;
    private FontMetrics fontMetrics;
    private String scoreString;
    private String levelString;
    private String shotString;
    private String welcomeString;
    private String loadingString;
    private Vector things;
    private Thread thread;
    
    public void init() {
        this.font = new Font("TimesRoman", 1, 24);
        this.fontMetrics = this.getFontMetrics(this.font);
        this.setFont(this.font);
        this.resize(500, 300);
    }
    
    public synchronized void newGame() {
        this.playing = true;
        this.clearScreen = true;
        this.stopThreads();
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        int n = 0;
        Thread.currentThread().setPriority(1);
        this.score = 0;
        this.shotCount = 0;
        this.shots = 0;
        this.things = new Vector(32);
        this.missileSemaphore = new Semaphore();
        this.messageSemaphore = new Semaphore();
        this.getSounds();
        this.createBases();
        if (this.music != null) {
            this.music.loop();
        }
        this.level = 1;
        while (true) {
            if (this.level == 1) {
                if (this.startSound != null) {
                    this.startSound.play();
                }
            }
            else if (this.applauseSound != null) {
                this.applauseSound.play();
            }
            this.messageSemaphore.take();
            this.createMessage("Level " + this.level);
            this.messageSemaphore.take();
            this.messageSemaphore.give();
            this.speed = 5 + (this.level - 1);
            if (this.speed > 30) {
                this.speed = 30;
            }
            this.delay = 2000 - (this.level - 1) * 200;
            if (this.delay < 500) {
                this.delay = 500;
            }
            this.missileCount = 5 + (this.level - 1) * 5;
            this.shotCount = this.missileCount * 2;
            this.missileSemaphore.take();
            while (this.missileCount > 0) {
                try {
                    Thread.sleep(this.delay);
                }
                catch (InterruptedException ex) {}
                this.createMissile(this.speed);
                --this.missileCount;
            }
            this.missileSemaphore.take();
            this.missileSemaphore.give();
            for (int n2 = this.score - n * 5000; this.countBases() < 5 && n2 >= 5000; n2 -= 5000, ++n) {
                this.createBase();
            }
            final int countBases = this.countBases();
            if (countBases == 0) {
                break;
            }
            this.score += countBases * 100;
            ++this.level;
        }
        this.playing = false;
        if (this.music != null) {
            this.music.stop();
        }
        for (int i = 0; i < 5; ++i) {
            this.things.addElement(new Explosion(this, Color.red, (int)(Math.random() * 430.0), (int)(Math.random() * 300.0), 500));
        }
        this.messageSemaphore.take();
        this.createMessage("GAME OVER");
        this.messageSemaphore.take();
        this.messageSemaphore.give();
    }
    
    public void stopThreads() {
        if (this.things != null) {
            final Enumeration<Thing> elements = this.things.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().explode();
            }
        }
    }
    
    public void stop() {
        if (this.music != null) {
            this.music.stop();
        }
        this.stopThreads();
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    void getSounds() {
        this.loadingSounds = true;
        this.repaint();
        this.startSound = this.getAudioClip(this.getCodeBase(), "sounds/sub_dive_horn.au");
        this.applauseSound = this.getAudioClip(this.getCodeBase(), "sounds/applause.au");
        this.missileSound = this.getAudioClip(this.getCodeBase(), "sounds/missile.au");
        this.shotExplosionSound = this.getAudioClip(this.getCodeBase(), "sounds/shot.au");
        this.missileExplosionSound = this.getAudioClip(this.getCodeBase(), "sounds/beep_multi.au");
        this.baseExplosionSound = this.getAudioClip(this.getCodeBase(), "sounds/bzzzt.au");
        this.music = null;
        this.loadingSounds = false;
    }
    
    void createBases() {
        for (int i = 0; i < 5; ++i) {
            this.createBase();
        }
    }
    
    void createBase() {
        for (int i = 0, n = 30; i < 5; ++i, n += 80) {
            boolean b = false;
            final Enumeration<Thing> elements = (Enumeration<Thing>)this.things.elements();
            while (elements.hasMoreElements()) {
                final Thing thing = elements.nextElement();
                if (thing instanceof Base && thing.X == n) {
                    b = true;
                }
            }
            if (!b) {
                this.things.addElement(new Base(this, Color.blue, n, 259, 50, 40));
                return;
            }
        }
    }
    
    void createShotExplosion(final int n, final int n2) {
        if (this.shots > 10) {
            return;
        }
        this.things.addElement(new ShotExplosion(this, n, n2));
        if (this.shotExplosionSound != null) {
            this.shotExplosionSound.play();
        }
        ++this.shots;
    }
    
    void createBaseExplosion(final int n, final int n2) {
        this.things.addElement(new BaseExplosion(this, n, n2));
        if (this.baseExplosionSound != null) {
            this.baseExplosionSound.play();
        }
    }
    
    void createMissile(final int n, final int n2, final int n3) {
        final Color red = Color.red;
        int n4 = (int)(Math.random() * 430.0);
        if (n4 == 0) {
            n4 = 1;
        }
        this.things.addElement(new Missile(this, red, n, n2, n4, 298, n3));
        if (this.missileSound != null) {
            this.missileSound.play();
        }
    }
    
    void createMissile(final int n) {
        int n2 = (int)(Math.random() * 430.0);
        if (n2 == 0) {
            n2 = 1;
        }
        this.createMissile(n2, 2, n);
    }
    
    void createMessage(final String s) {
        this.things.addElement(new Message(this, s, 215 - this.fontMetrics.stringWidth(s) / 2, 150));
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501) {
            if (this.playing && this.shotCount > 0 && event.x >= 0 && event.x <= 430 && event.y >= 0 && event.y <= 300) {
                this.createShotExplosion(event.x, event.y);
                --this.shotCount;
            }
            else if (!this.playing) {
                this.newGame();
            }
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void checkCollision(final Missile missile) {
        int n = 0;
        int n2 = 0;
        final Enumeration<Thing> elements = this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing != missile && thing.isAlive() && thing.hit(missile)) {
                thing.explode();
                if (thing instanceof Base) {
                    ++n;
                }
                else {
                    if (!(thing instanceof ShotExplosion)) {
                        continue;
                    }
                    ++n2;
                    this.score += 100;
                    if (this.missileExplosionSound == null) {
                        continue;
                    }
                    this.missileExplosionSound.play();
                }
            }
        }
        if (n > 0) {
            this.createBaseExplosion(missile.X, missile.Y);
        }
        if (n > 0 || n2 > 0) {
            missile.explode();
        }
    }
    
    public synchronized void updateThings(final Graphics graphics) {
        final Enumeration<Thing> elements = this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing.isAlive()) {
                thing.paint(graphics);
                if (!(thing instanceof Missile)) {
                    continue;
                }
                this.checkCollision((Missile)thing);
            }
            else {
                thing.erase(graphics);
                this.things.removeElement(thing);
                if (thing instanceof Message) {
                    this.messageSemaphore.give();
                }
                else {
                    if (!(thing instanceof ShotExplosion)) {
                        continue;
                    }
                    --this.shots;
                }
            }
        }
    }
    
    public int countMissiles() {
        int n = 0;
        final Enumeration<Thing> elements = (Enumeration<Thing>)this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing instanceof Missile && thing.isAlive()) {
                ++n;
            }
        }
        return n;
    }
    
    public int countBases() {
        int n = 0;
        final Enumeration<Thing> elements = (Enumeration<Thing>)this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing instanceof Base && thing.isAlive()) {
                ++n;
            }
        }
        return n;
    }
    
    public void updateBorder(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, 499, 299);
        graphics.drawLine(430, 0, 430, 300);
    }
    
    String numberToZeroPaddedString(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(Integer.toString(n));
        while (sb.length() < n2) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
    
    public synchronized void updateScore(final Graphics graphics) {
        final int stringWidth = this.fontMetrics.stringWidth("00000");
        final int height = this.fontMetrics.getHeight();
        final int n = 435;
        graphics.setColor(Color.black);
        final int n2 = height;
        graphics.drawString(this.scoreString, n, n2);
        final int n3 = n2 + height;
        graphics.clearRect(n, n3 - height, stringWidth, height);
        graphics.drawString(this.numberToZeroPaddedString(this.score, 5), n, n3);
        final int n4 = n3 + 2 * height;
        graphics.drawString(this.shotString, n, n4);
        final int n5 = n4 + height;
        graphics.clearRect(n, n5 - height, stringWidth, height);
        graphics.drawString(this.numberToZeroPaddedString(this.shotCount, 5), n, n5);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.clearScreen) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, 500, 300);
            this.clearScreen = false;
        }
        if (this.loadingSounds) {
            final int n = 215 - this.fontMetrics.stringWidth(this.loadingString) / 2;
            final int n2 = 150;
            graphics.setColor(Color.black);
            graphics.drawString(this.loadingString, n, n2);
            this.clearScreen = true;
        }
        else if (!this.playing && (this.things == null || this.things.size() == 0)) {
            final int n3 = 215 - this.fontMetrics.stringWidth(this.welcomeString) / 2;
            final int n4 = 150;
            graphics.setColor(Color.black);
            graphics.drawString(this.welcomeString, n3, n4);
        }
        else if (this.things != null) {
            this.updateThings(graphics.create(0, 0, 430, 300));
            if (this.missileSemaphore != null && this.countMissiles() == 0 && this.missileCount == 0) {
                this.missileSemaphore.give();
            }
        }
        this.updateBorder(graphics);
        this.updateScore(graphics);
    }
    
    public MissileCommando() {
        this.playing = false;
        this.clearScreen = false;
        this.loadingSounds = false;
        this.scoreString = "Score";
        this.levelString = "Level";
        this.shotString = "Shots";
        this.welcomeString = "Click to start";
        this.loadingString = "Loading sounds...";
    }
}
