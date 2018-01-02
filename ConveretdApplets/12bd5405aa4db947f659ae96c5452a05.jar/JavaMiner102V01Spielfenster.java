import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class JavaMiner102V01Spielfenster extends Frame implements Runnable
{
    public static final int SPIELFELD_X_RAND = 32;
    public static final int SPIELFELD_Y_RAND = 32;
    public static final int FIELD_SIZE = 32;
    public static final int MAP_WIDTH = 14;
    public static final int MAP_HEIGHT = 13;
    public static final int MAX_OBJECTS = 30;
    public static final int MAX_HOSTILES = 10;
    public static final int HIT_KEIN_GEGNER = 0;
    public static final int HIT_SIMPLE_HOSTILE = 1;
    public static final int HIT_GOLD = 2;
    public static final int GOLD_EINGESAMMELT = 0;
    public static final int GOLD_NICHT_EINGESAMMELT = 1;
    public static final int THREAD_MISC_TASK = 0;
    public static final int THREAD_GAME_TASK = 1;
    public static final int THREAD_SCORE_TASK = 2;
    public static final int LAST_LEVEL = 15;
    public static final Font STATUS_FONT;
    public static final int DIGGER_IMAGES = 8;
    public static final int STATIC_IMAGES = 10;
    public static final int SHOT_IMAGES = 4;
    public static final int HOSTILE_IMAGES = 1;
    public static final int GAME_SPEED = 50;
    public static final int START_BLINK = 8;
    public static final int START_BLINK_TIME = 150;
    Image[] allImages;
    Image[] diggerImages;
    Image[] staticImages;
    Image[] shotImages;
    Image[] hostileImages;
    Image[] goldImages;
    URL levelDirectory;
    Image bslogo;
    Thread thread;
    int threadTask;
    Image offScreen;
    int scoreCount;
    int mainScore;
    Color scoreCountColor;
    boolean credits;
    int creditScroll;
    int startblink;
    boolean spielPause;
    boolean introActive;
    boolean gameOver;
    boolean abortCredits;
    int currentLevel;
    int numberOfObjects;
    int numberOfHostiles;
    JavaMiner102V01Object[] objects;
    JavaMiner102V01Miner digger;
    JavaMiner102V01Level level;
    JavaMiner102V01MinerShot shot;
    JavaMiner102V01Intro intro;
    public static int activeSimpleHostiles;
    int nextHostile;
    
    public JavaMiner102V01Spielfenster(final Image[] allImages, final URL levelDirectory, final Image bslogo) {
        super("JavaMiner");
        this.setIconImage(allImages[0]);
        this.allImages = allImages;
        this.levelDirectory = levelDirectory;
        this.setBackground(Color.black);
        this.bslogo = bslogo;
        this.diggerImages = new Image[8];
        this.staticImages = new Image[10];
        this.shotImages = new Image[4];
        this.hostileImages = new Image[1];
        this.goldImages = new Image[4];
        for (int i = 0; i < 8; ++i) {
            this.diggerImages[i] = this.allImages[i];
        }
        for (int j = 8; j < 18; ++j) {
            this.staticImages[j - 8] = this.allImages[j];
        }
        for (int k = 18; k < 22; ++k) {
            this.shotImages[k - 8 - 10] = this.allImages[k];
        }
        for (int l = 22; l < 23; ++l) {
            this.hostileImages[l - 8 - 10 - 4] = this.allImages[l];
        }
        this.goldImages[0] = this.allImages[11];
        this.goldImages[1] = this.allImages[23];
        this.goldImages[2] = this.allImages[24];
        this.goldImages[3] = this.allImages[25];
        this.restartGame();
    }
    
    public static int getXValueFromKoordinate(final int n) {
        return 32 + n * 32;
    }
    
    public static int getYValueFromKoordinate(final int n) {
        return 32 + n * 32;
    }
    
    public static int getYKoordinateFromValue(final int n) {
        return (n - 32) / 32;
    }
    
    public static int getXKoordinateFromValue(final int n) {
        return (n - 32) / 32;
    }
    
    private void startLevel(final int n) {
        try {
            String string = "jm";
            if (n <= 9) {
                string += "0";
            }
            this.level.readLevelFile(new URL(this.levelDirectory, string + String.valueOf(n) + ".lvl").openStream());
        }
        catch (IOException ex) {
            System.out.println("Fehler beim \u00d6ffnen der Level-Datei!");
        }
        this.objects[0] = this.digger;
        this.numberOfObjects = 1;
        for (int i = 0; i < this.level.getStartGoldLocations().length; ++i) {
            this.objects[this.numberOfObjects++] = new JavaMiner102V01Gold(this.level.getStartGoldLocations()[i].x, this.level.getStartGoldLocations()[i].y, this.goldImages, this.level, this.objects);
        }
        this.restartLevel();
    }
    
    protected void restartLevel() {
        for (int i = 0; i < 30; ++i) {
            if (this.objects[i] != null && !this.objects[i].stayAtLevelRestart()) {
                this.objects[i].dispose();
            }
        }
        this.checkForDispose();
        JavaMiner102V01Spielfenster.activeSimpleHostiles = 0;
        this.nextHostile = 0;
        this.shot = null;
        this.digger.reset(this.level.getMinerStartX(), this.level.getMinerStartY(), this.level.getMinerStartRichtung(), this.level);
    }
    
    public void run() {
        while (true) {
            switch (this.threadTask) {
                case 1: {
                    this.gameTask();
                    continue;
                }
                case 2: {
                    this.scoreTask();
                    continue;
                }
                case 0: {
                    this.miscTask();
                    continue;
                }
            }
        }
    }
    
    private void createSimpleHostile() {
        this.objects[this.numberOfObjects] = new JavaMiner102V01SimpleHostile(this.hostileImages[0], this.level, this.digger, this.objects);
        ++this.numberOfObjects;
        ++JavaMiner102V01Spielfenster.activeSimpleHostiles;
    }
    
    private void gameTask() {
        this.startblink = 8;
        while (this.startblink > 0) {
            try {
                this.repaint();
                Thread.sleep(150L);
            }
            catch (InterruptedException ex) {}
            --this.startblink;
        }
        while (this.level.isAnyDiamondLeft() && !this.digger.destroyedByHostile()) {
            this.repaint();
            for (int i = 0; i < this.numberOfObjects; ++i) {
                this.objects[i].act();
            }
            this.checkForDispose();
            if (this.digger.getDoesShoot()) {
                this.createMinerShot();
            }
            this.checkHostiles();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex2) {}
        }
        if (!this.digger.destroyedByHostile()) {
            this.threadTask = 2;
            return;
        }
        if (this.digger.getLives() < 0) {
            this.threadTask = 2;
            return;
        }
        this.digger.decrLives();
        if (this.digger.getLives() >= 0) {
            this.restartLevel();
            return;
        }
        this.threadTask = 2;
    }
    
    private void createMinerShot() {
        this.objects[this.numberOfObjects] = new JavaMiner102V01MinerShot(this.shotImages, this.digger.getAusrichtung(), this.digger.getXPos(), this.digger.getYPos(), this.level, this.objects, this.digger);
        ++this.numberOfObjects;
        this.digger.shotDone();
    }
    
    private void checkHostiles() {
        if (this.nextHostile <= 0) {
            if (JavaMiner102V01Spielfenster.activeSimpleHostiles < this.level.getSimpleHostiles()) {
                this.createSimpleHostile();
                this.nextHostile = 40;
            }
        }
        else {
            --this.nextHostile;
        }
    }
    
    private void scoreTask() {
        int n = (this.mainScore / 10000 + 1) * 10000;
        this.scoreCountColor = Color.green;
        try {
            for (int i = 0; i <= this.digger.getScore(); i += 25) {
                if ((this.scoreCount = i) > 0) {
                    this.mainScore += 25;
                }
                this.repaint();
                Thread.sleep(15L);
                if (this.mainScore == n) {
                    for (int j = 0; j < 2; ++j) {
                        Thread.sleep(100L);
                        this.scoreCountColor = Color.red;
                        this.repaint();
                        Thread.sleep(100L);
                        this.scoreCountColor = Color.green;
                        this.repaint();
                    }
                    n += 10000;
                    if (this.digger.getLives() >= 0 && this.digger.getLives() < 3) {
                        this.digger.incrLives();
                    }
                }
            }
            this.repaint();
            Thread.sleep(1000L);
            this.digger.resetScore();
        }
        catch (InterruptedException ex) {}
        this.threadTask = 0;
        this.repaint();
    }
    
    private synchronized void checkForDispose() {
        for (int i = 0; i < this.numberOfObjects; ++i) {
            if (this.objects[i].getDispose()) {
                --this.numberOfObjects;
                this.objects[i] = null;
                if (i < this.numberOfObjects) {
                    for (int j = i; j < this.numberOfObjects; ++j) {
                        this.objects[j] = this.objects[j + 1];
                    }
                }
                --i;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.offScreen == null || this.offScreen.getWidth(this) != this.size().width || this.offScreen.getHeight(this) != this.size().height) {
            this.offScreen = this.createImage(this.size().width, this.size().height);
        }
        final Graphics graphics2 = this.offScreen.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.size().width, this.size().height);
        this.paint(graphics2);
        graphics2.dispose();
        graphics.drawImage(this.offScreen, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.introActive) {
            if (this.threadTask == 1) {
                graphics.setColor(Color.green);
                graphics.drawRect(480, 32, 128, 416);
                for (int i = 0; i < 14; ++i) {
                    for (int j = 0; j < 13; ++j) {
                        graphics.drawImage(this.level.getBackground(i, j), 32 + i * 32, 32 + j * 32, this);
                    }
                }
                if (this.startblink % 2 == 1) {
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(32 + this.level.getMinerStartX() * 32, 32 + this.level.getMinerStartY() * 32, 32, 32);
                    graphics.setColor(Color.red);
                    graphics.setFont(new Font("Times Roman", 2, 72));
                    if (this.startblink <= 7) {
                        graphics.drawString(String.valueOf(this.startblink / 2), 240, 260);
                    }
                }
                for (int k = 0; k < this.numberOfObjects; ++k) {
                    graphics.drawImage(this.objects[k].getImage(), this.objects[k].getXPos(), this.objects[k].getYPos(), this);
                }
                graphics.setColor(Color.yellow);
                graphics.setFont(JavaMiner102V01Spielfenster.STATUS_FONT);
                graphics.drawImage(this.bslogo, 494, 33, this);
                graphics.drawString("JavaMiner V 1.00", 490, 150);
                graphics.drawString("(c) 1999 - 2000", 490, 165);
                graphics.drawString("by Matthias Burg", 490, 180);
                graphics.drawString("www.burgsoft.de", 490, 195);
                graphics.drawString("Level:", 490, 250);
                graphics.drawString(String.valueOf(this.currentLevel), 520, 270);
                graphics.drawString("Level-Score:", 490, 310);
                graphics.drawString(String.valueOf(this.digger.getScore()), 520, 330);
                graphics.drawString("Lives:", 490, 370);
                if (this.digger.getLives() > 0) {
                    for (int l = 0; l < this.digger.getLives(); ++l) {
                        graphics.drawImage(this.diggerImages[0], 500 + l * 32, 390, this);
                    }
                }
                if (this.spielPause) {
                    graphics.setColor(Color.red);
                    graphics.setFont(new Font("TimesRoman", 2, 72));
                    graphics.drawString("Pause", 200, 260);
                }
            }
            else if (this.threadTask == 2) {
                graphics.setColor(this.scoreCountColor);
                graphics.setFont(new Font("TimesRoman", 1, 48));
                graphics.drawString("Level-Score:", 200, 150);
                graphics.drawString(String.valueOf(this.digger.getScore() - this.scoreCount), 250, 200);
                graphics.drawString("Your Score:", 300, 250);
                graphics.drawString(String.valueOf(this.mainScore), 350, 300);
                if (this.digger.getScore() == this.scoreCount) {
                    graphics.setFont(new Font("TimesRoman", 0, 16));
                    if (this.currentLevel != 15 && this.digger.getLives() >= 0) {
                        graphics.drawString("Prepare for next Level...", 200, 400);
                    }
                }
            }
            else if (this.threadTask == 0) {
                if (this.credits) {
                    graphics.setColor(Color.green);
                    final Font font = new Font("Helvetica", 1, 64);
                    final Font font2 = new Font("Helvetica", 2, 12);
                    final Font font3 = new Font("Dialog", 0, 16);
                    graphics.setFont(font);
                    graphics.drawString("JavaMiner", this.getCreditCentered(font, "JavaMiner"), this.creditScroll - 300);
                    graphics.setFont(font2);
                    graphics.drawString("Version 1.00", this.getCreditCentered(font2, "Version 1.00"), this.creditScroll - 270);
                    graphics.setFont(font3);
                    graphics.drawString("Copyright (c) 1999-2000 by Matthias Burg", this.getCreditCentered(font3, "Copyright (c) 1999-2000 by Matthias Burg"), this.creditScroll - 200);
                    graphics.drawString("Ich hoffe, das Spiel hat Ihnen gefallen!", this.getCreditCentered(font3, "Ich hoffe, das Spiel hat Ihnen gefallen!"), this.creditScroll - 100);
                    graphics.drawString("I hope you enjoyed the game!", this.getCreditCentered(font3, "I hope you enjoyed the game!"), this.creditScroll - 75);
                    graphics.drawString("Bitte schreiben Sie mir: Matthias@burgsoft.de", this.getCreditCentered(font3, "Bitte schreiben Sie mir: Matthias@burgsoft.de"), this.creditScroll - 25);
                    graphics.drawString("Please write me: Matthias@burgsoft.de", this.getCreditCentered(font3, "Please write me: Matthias@burgsoft.de"), this.creditScroll);
                    graphics.drawImage(this.diggerImages[0], 700 - this.creditScroll, this.creditScroll - 250, this);
                    graphics.drawImage(this.diggerImages[1], this.creditScroll, this.creditScroll - 150, this);
                    return;
                }
                if (this.currentLevel == 15 || this.digger.getLives() < 0) {
                    graphics.setColor(Color.green);
                    graphics.setFont(new Font("TimesRoman", 1, 48));
                    graphics.drawString("GameOver!", 50, 100);
                    graphics.drawString("Score: " + String.valueOf(this.mainScore), 100, 200);
                    return;
                }
                graphics.setColor(Color.green);
                graphics.setFont(new Font("TimesRoman", 2, 32));
                graphics.drawString("Loading next Level... (<1KB)", 50, 100);
                graphics.drawString("Current Score: " + String.valueOf(this.digger.getScore()), 100, 200);
            }
        }
    }
    
    protected int getCreditCentered(final Font font, final String s) {
        return (640 - this.getFontMetrics(font).stringWidth(s)) / 2;
    }
    
    void miscTask() {
        if (this.currentLevel == 15 || this.digger.getLives() < 0) {
            this.credits = true;
            this.abortCredits = false;
            this.creditScroll = 800;
            for (int creditScroll = 800; creditScroll > 0 && !this.abortCredits; --creditScroll) {
                this.creditScroll = creditScroll;
                this.repaint();
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex) {}
            }
            this.credits = false;
            this.repaint();
            this.gameOver = true;
            this.thread.stop();
            return;
        }
        this.startLevel(++this.currentLevel);
        this.threadTask = 1;
        this.repaint();
    }
    
    public void restartGame() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.objects = new JavaMiner102V01Object[30];
        this.digger = new JavaMiner102V01Miner(this.diggerImages, this.objects);
        this.level = new JavaMiner102V01Level(this.staticImages);
        this.spielPause = false;
        this.startLevel(this.currentLevel = 1);
        this.thread = new Thread(this);
        this.threadTask = 1;
        this.gameOver = false;
        this.intro = new JavaMiner102V01Intro(this.bslogo);
        this.setLayout(new GridLayout(1, 1));
        this.add(this.intro);
        this.intro.startIntro();
        this.introActive = true;
        this.intro.resize(640, 480);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.introActive) {
            this.remove(this.intro);
            this.intro.stopIntro();
            this.intro = null;
            this.thread.start();
            this.introActive = false;
            this.requestFocus();
            return true;
        }
        if (this.credits) {
            return this.abortCredits = true;
        }
        if (this.gameOver) {
            this.restartGame();
            return true;
        }
        return super.mouseUp(event, n, n2);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.introActive) {
            this.remove(this.intro);
            this.intro.stopIntro();
            this.intro = null;
            this.thread.start();
            this.introActive = false;
            this.requestFocus();
        }
        switch (event.key) {
            case 1006: {
                this.digger.setBewegung(2);
                break;
            }
            case 1007: {
                this.digger.setBewegung(1);
                break;
            }
            case 1004: {
                this.digger.setBewegung(4);
                break;
            }
            case 1005: {
                this.digger.setBewegung(8);
                break;
            }
            case 1009: {
                this.digger.setShoot(true);
                break;
            }
            case 112: {
                if (this.spielPause) {
                    if (this.thread != null) {
                        this.thread.resume();
                    }
                    this.spielPause = false;
                    this.repaint();
                    break;
                }
                if (this.thread != null) {
                    this.thread.suspend();
                }
                this.spielPause = true;
                this.repaint();
                break;
            }
        }
        return super.keyDown(event, n);
    }
    
    public boolean keyUp(final Event event, final int n) {
        switch (event.key) {
            case 1004:
            case 1005:
            case 1006:
            case 1007: {
                this.digger.setBewegung(16);
                break;
            }
            case 1009: {
                this.digger.setShoot(false);
                break;
            }
        }
        return super.keyUp(event, n);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            if (this.thread != null) {
                this.thread.stop();
                this.thread = null;
            }
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
    
    static {
        STATUS_FONT = new Font("Dialog", 0, 12);
    }
}
