import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.AudioClip;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Point;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnakePit extends Applet implements Runnable
{
    Thread loopThread;
    Thread loadThread;
    static final int MAX_DELAY = 75;
    static final int MIN_DELAY = 50;
    static final int DELAY_INCR = 5;
    static final int GRID_WIDTH = 39;
    static final int GRID_HEIGHT = 25;
    static final int GRID_SIZE = 16;
    static final int NUM_LEVELS = 5;
    static final int NUM_MICE = 6;
    static final int NUM_LIVES = 3;
    static final int INIT = 1;
    static final int PLAY = 2;
    static final int LEVEL = 3;
    static final int END = 4;
    static final int OVER = 5;
    static final int END_COUNT = 30;
    static final int LEVEL_COUNT = 40;
    static final int TYPE_MASK = 16711680;
    static final int EMPTY = 0;
    static final int BLOCK = 65536;
    static final int SNAKE = 131072;
    static final int MOUSE = 196608;
    static final int KEY = 262144;
    static final int DIR_MASK = 65280;
    static final int NONE = 0;
    static final int LEFT = 256;
    static final int RIGHT = 512;
    static final int UP = 768;
    static final int DOWN = 1024;
    static final int SHAPE_MASK = 255;
    static final int SQUARE = 0;
    static final int SNAKEHEAD = 1;
    static final int SNAKEBODY = 2;
    static final int SNAKEELB1 = 3;
    static final int SNAKEELB2 = 4;
    static final int SNAKETAIL = 5;
    static final int MOUSEBODY = 6;
    static final int KEYSHAPE = 7;
    static final int MOUSE_POINTS = 10;
    static final int LEVEL_POINTS = 200;
    static final int EXTRA_LIFE = 500;
    int width;
    int height;
    int score;
    int highScore;
    int lives;
    int extraLife;
    int level;
    int levelTotal;
    int miceNeeded;
    int miceEaten;
    int delay;
    int[][] grid;
    Point[] snake;
    int headPtr;
    int tailPtr;
    int direction;
    int lastDirection;
    boolean lockKeys;
    Point[] mouse;
    Point key;
    boolean keyActive;
    int gameState;
    int levelCounter;
    int endCounter;
    boolean paused;
    Color bgColor;
    Color fgColor;
    Color blockColor;
    Color fieldColor;
    Color snakeColor;
    Color mouseColor;
    Color keyColor;
    Polygon snakeHead;
    Polygon snakeBody;
    Polygon snakeTail;
    Polygon snakeElb1;
    Polygon snakeElb2;
    Polygon mouseBody;
    Polygon keyShape;
    boolean loaded;
    boolean sound;
    AudioClip bonkSound;
    AudioClip munchSound;
    AudioClip squeakSound;
    AudioClip chimeSound;
    AudioClip advanceSound;
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    Font font;
    FontMetrics fm;
    int fontWidth;
    int fontHeight;
    
    public String getAppletInfo() {
        return "Snake Pit, Copyright 1998 by Mike Hall.";
    }
    
    public void init() {
        System.out.println("Snake Pit, Copyright 1998 by Mike Hall.");
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.fontWidth = this.fm.getMaxAdvance();
        this.fontHeight = this.fm.getHeight();
        this.grid = new int[39][25];
        (this.snakeHead = new Polygon()).addPoint(4, 15);
        this.snakeHead.addPoint(3, 14);
        this.snakeHead.addPoint(1, 12);
        this.snakeHead.addPoint(1, 8);
        this.snakeHead.addPoint(3, 6);
        this.snakeHead.addPoint(3, 4);
        this.snakeHead.addPoint(6, 1);
        this.snakeHead.addPoint(9, 1);
        this.snakeHead.addPoint(12, 4);
        this.snakeHead.addPoint(12, 6);
        this.snakeHead.addPoint(14, 8);
        this.snakeHead.addPoint(14, 12);
        this.snakeHead.addPoint(12, 14);
        this.snakeHead.addPoint(11, 15);
        (this.snakeBody = new Polygon()).addPoint(11, 0);
        this.snakeBody.addPoint(11, 15);
        this.snakeBody.addPoint(4, 15);
        this.snakeBody.addPoint(4, 0);
        (this.snakeElb1 = new Polygon()).addPoint(11, 0);
        this.snakeElb1.addPoint(11, 4);
        this.snakeElb1.addPoint(15, 4);
        this.snakeElb1.addPoint(15, 11);
        this.snakeElb1.addPoint(7, 11);
        this.snakeElb1.addPoint(4, 8);
        this.snakeElb1.addPoint(4, 0);
        this.snakeElb2 = this.mirror(this.snakeElb1);
        (this.snakeTail = new Polygon()).addPoint(11, 0);
        this.snakeTail.addPoint(8, 15);
        this.snakeTail.addPoint(7, 15);
        this.snakeTail.addPoint(4, 0);
        (this.mouseBody = new Polygon()).addPoint(8, 1);
        this.mouseBody.addPoint(12, 5);
        this.mouseBody.addPoint(12, 6);
        this.mouseBody.addPoint(10, 7);
        this.mouseBody.addPoint(12, 9);
        this.mouseBody.addPoint(12, 12);
        this.mouseBody.addPoint(10, 14);
        this.mouseBody.addPoint(5, 14);
        this.mouseBody.addPoint(3, 12);
        this.mouseBody.addPoint(3, 9);
        this.mouseBody.addPoint(5, 7);
        this.mouseBody.addPoint(3, 6);
        this.mouseBody.addPoint(3, 5);
        this.mouseBody.addPoint(7, 1);
        (this.keyShape = new Polygon()).addPoint(1, 6);
        this.keyShape.addPoint(7, 6);
        this.keyShape.addPoint(9, 4);
        this.keyShape.addPoint(13, 4);
        this.keyShape.addPoint(15, 6);
        this.keyShape.addPoint(15, 10);
        this.keyShape.addPoint(13, 12);
        this.keyShape.addPoint(9, 12);
        this.keyShape.addPoint(7, 10);
        this.keyShape.addPoint(3, 10);
        this.keyShape.addPoint(3, 11);
        this.keyShape.addPoint(0, 11);
        this.keyShape.addPoint(0, 7);
        this.highScore = 0;
        this.sound = true;
        this.snake = new Point[975];
        for (int i = 0; i < this.snake.length; ++i) {
            this.snake[i] = new Point(-1, -1);
        }
        this.mouse = new Point[6];
        for (int j = 0; j < 6; ++j) {
            this.mouse[j] = new Point(-1, -1);
        }
        this.key = new Point(-1, -1);
        this.lockKeys = false;
        this.initGame();
        this.endGame();
        for (int k = 0; k < 6; ++k) {
            this.killMouse(k);
        }
        this.gameState = 1;
    }
    
    public void initGame() {
        this.score = 0;
        this.lives = 3;
        this.level = 0;
        this.levelTotal = 0;
        this.extraLife = 500;
        this.delay = 75;
        this.paused = false;
        this.initLevel();
    }
    
    public void endGame() {
        this.gameState = 5;
    }
    
    public void initLevel() {
        ++this.level;
        if (this.level > 5) {
            this.level = 1;
            if (this.delay > 50) {
                this.delay -= 5;
            }
        }
        ++this.levelTotal;
        this.initBlocks();
        this.initSnake();
        for (int i = 0; i < 6; ++i) {
            this.initMouse(i);
        }
        this.miceEaten = 0;
        this.miceNeeded = 3 * (11 - this.level);
        this.keyActive = false;
        this.gameState = 2;
    }
    
    public void endLevel() {
        this.gameState = 3;
        this.levelCounter = 40;
    }
    
    public void initLife() {
        this.killSnake();
        this.initSnake();
        this.gameState = 2;
    }
    
    public void endLife() {
        this.gameState = 4;
        this.endCounter = 30;
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
                if (this.gameState == 2) {
                    this.moveSnake();
                    for (int i = 0; i < 6; ++i) {
                        this.moveMouse(i);
                    }
                }
                if (this.score > this.highScore) {
                    this.highScore = this.score;
                }
                if (this.score >= this.extraLife) {
                    ++this.lives;
                    this.extraLife += 500;
                }
                if (this.gameState == 4 && --this.endCounter < 0) {
                    if (--this.lives == 0) {
                        this.endGame();
                    }
                    else {
                        this.initLife();
                    }
                }
                if (this.gameState == 2 && this.miceEaten == this.miceNeeded && !this.keyActive) {
                    if (this.loaded && this.sound) {
                        this.chimeSound.play();
                    }
                    this.initKey();
                }
                if (this.gameState == 3 && --this.levelCounter < 0) {
                    this.initLevel();
                }
            }
            this.repaint();
            try {
                if (this.gameState == 2) {
                    currentTimeMillis += this.delay;
                }
                else {
                    currentTimeMillis += 50L;
                }
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void loadSounds() {
        try {
            this.bonkSound = this.getAudioClip(new URL(this.getDocumentBase(), "bonk.au"));
            this.munchSound = this.getAudioClip(new URL(this.getDocumentBase(), "munch.au"));
            this.squeakSound = this.getAudioClip(new URL(this.getDocumentBase(), "squeak.au"));
            this.chimeSound = this.getAudioClip(new URL(this.getDocumentBase(), "chime.au"));
            this.advanceSound = this.getAudioClip(new URL(this.getDocumentBase(), "advance.au"));
        }
        catch (MalformedURLException ex) {}
        this.bonkSound.play();
        this.bonkSound.stop();
        this.munchSound.play();
        this.munchSound.stop();
        this.squeakSound.play();
        this.squeakSound.stop();
        this.chimeSound.play();
        this.chimeSound.stop();
        this.advanceSound.play();
        this.advanceSound.stop();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.paused && !this.lockKeys) {
            if (n == 1006 && this.lastDirection != 512) {
                this.direction = 256;
            }
            else if (n == 1007 && this.lastDirection != 256 && this.lastDirection != 0) {
                this.direction = 512;
            }
            else if (n == 1004 && this.lastDirection != 1024) {
                this.direction = 768;
            }
            else if (n == 1005 && this.lastDirection != 768) {
                this.direction = 1024;
            }
        }
        if (n == 109) {
            if (this.sound) {
                this.bonkSound.stop();
                this.munchSound.stop();
                this.squeakSound.stop();
                this.chimeSound.stop();
                this.advanceSound.stop();
            }
            this.sound = !this.sound;
        }
        if (n == 112) {
            this.paused = !this.paused;
        }
        if (this.loaded && n == 115 && (this.gameState == 1 || this.gameState == 5)) {
            this.initGame();
        }
        return true;
    }
    
    public void initBlocks() {
        for (int i = 0; i < 39; ++i) {
            for (int j = 0; j < 25; ++j) {
                this.grid[i][j] = 0;
            }
        }
        for (int k = 0; k < 39; ++k) {
            this.grid[k][0] = 65536;
            this.grid[k][24] = 65536;
        }
        for (int l = 1; l < 24; ++l) {
            this.grid[0][l] = 65536;
            this.grid[38][l] = 65536;
        }
        if (this.level == 2 || this.level == 4) {
            final int n = 9;
            for (int n2 = 6; n2 <= 9; ++n2) {
                this.grid[n][n2] = 65536;
                this.grid[38 - n][n2] = 65536;
                this.grid[n][24 - n2] = 65536;
                this.grid[38 - n][24 - n2] = 65536;
            }
            final int n3 = 6;
            for (int n4 = 9; n4 <= 16; ++n4) {
                this.grid[n4][n3] = 65536;
                this.grid[38 - n4][n3] = 65536;
                this.grid[n4][24 - n3] = 65536;
                this.grid[38 - n4][24 - n3] = 65536;
            }
        }
        if (this.level == 3) {
            final int n5 = 19;
            for (int n6 = 0; n6 <= 3; ++n6) {
                this.grid[n5][n6] = 65536;
                this.grid[n5][24 - n6] = 65536;
            }
        }
        if (this.level == 3 || this.level == 5) {
            final int n7 = 6;
            for (int n8 = 5; n8 <= 9; ++n8) {
                this.grid[n8][n7] = 65536;
                this.grid[38 - n8][n7] = 65536;
                this.grid[n8][24 - n7] = 65536;
                this.grid[38 - n8][24 - n7] = 65536;
            }
            final int n9 = 9;
            for (int n10 = 6; n10 <= 12; ++n10) {
                this.grid[n9][n10] = 65536;
                this.grid[38 - n9][n10] = 65536;
                this.grid[n9][24 - n10] = 65536;
                this.grid[38 - n9][24 - n10] = 65536;
            }
            final int n11 = 12;
            for (int n12 = 0; n12 <= 4; ++n12) {
                this.grid[n12][n11] = 65536;
                this.grid[38 - n12][n11] = 65536;
            }
        }
        if (this.level == 4) {
            final int n13 = 4;
            for (int n14 = 10; n14 <= 12; ++n14) {
                this.grid[n13][n14] = 65536;
                this.grid[38 - n13][n14] = 65536;
                this.grid[n13][24 - n14] = 65536;
                this.grid[38 - n13][24 - n14] = 65536;
            }
            final int n15 = 19;
            for (int n16 = 0; n16 <= 2; ++n16) {
                this.grid[n15][n16] = 65536;
                this.grid[n15][24 - n16] = 65536;
            }
        }
        if (this.level == 5) {
            final int n17 = 14;
            for (int n18 = 4; n18 <= 7; ++n18) {
                this.grid[n17][n18] = 65536;
                this.grid[38 - n17][n18] = 65536;
                this.grid[n17][24 - n18] = 65536;
                this.grid[38 - n17][24 - n18] = 65536;
            }
            final int n19 = 7;
            for (int n20 = 14; n20 <= 19; ++n20) {
                this.grid[n20][n19] = 65536;
                this.grid[38 - n20][n19] = 65536;
                this.grid[n20][24 - n19] = 65536;
                this.grid[38 - n20][24 - n19] = 65536;
            }
        }
    }
    
    public void initSnake() {
        final int x = 19;
        final int y = 12;
        this.snake[4].x = x - 2;
        this.snake[4].y = y;
        this.snake[3].x = x - 1;
        this.snake[3].y = y;
        this.snake[2].x = x;
        this.snake[2].y = y;
        this.snake[1].x = x + 1;
        this.snake[1].y = y;
        this.snake[0].x = x + 2;
        this.snake[0].y = y;
        this.headPtr = 4;
        this.tailPtr = 0;
        this.grid[x - 2][y] = 131329;
        this.grid[x - 1][y] = 131330;
        this.grid[x][y] = 131330;
        this.grid[x + 1][y] = 131330;
        this.grid[x + 2][y] = 131333;
        this.direction = 0;
        this.lastDirection = 0;
        for (int i = 0; i < 6; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (this.mouse[i].x == x + j && this.mouse[i].y == y) {
                    this.initMouse(i);
                }
            }
        }
        if (this.keyActive) {
            for (int k = -2; k <= 2; ++k) {
                if (this.key.x == x + k && this.key.y == y) {
                    this.initKey();
                }
            }
        }
    }
    
    public void moveSnake() {
        this.lockKeys = true;
        final Point point = this.snake[this.headPtr];
        int x = point.x;
        int y = point.y;
        switch (this.direction) {
            case 256: {
                --x;
                break;
            }
            case 512: {
                ++x;
                break;
            }
            case 768: {
                --y;
                break;
            }
            case 1024: {
                ++y;
                break;
            }
            default: {
                this.lockKeys = false;
                return;
            }
        }
        this.lockKeys = false;
        this.lastDirection = this.direction;
        if (this.direction == 0) {
            return;
        }
        final int n = this.grid[x][y] & 0xFF0000;
        if (n == 65536 || n == 131072) {
            if (this.loaded && this.sound) {
                this.bonkSound.play();
            }
            this.endLife();
            return;
        }
        final int n2 = this.grid[point.x][point.y] & 0xFF00;
        if (n2 == this.direction) {
            this.grid[point.x][point.y] = (0x20000 | n2 | 0x2);
        }
        else if (n2 == 256 && this.direction == 768) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x3);
        }
        else if (n2 == 768 && this.direction == 512) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x3);
        }
        else if (n2 == 512 && this.direction == 1024) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x3);
        }
        else if (n2 == 1024 && this.direction == 256) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x3);
        }
        else if (n2 == 768 && this.direction == 256) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x4);
        }
        else if (n2 == 256 && this.direction == 1024) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x4);
        }
        else if (n2 == 1024 && this.direction == 512) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x4);
        }
        else if (n2 == 512 && this.direction == 768) {
            this.grid[point.x][point.y] = (0x20000 | this.direction | 0x4);
        }
        this.grid[x][y] = (0x20000 | this.direction | 0x1);
        if (++this.headPtr >= this.snake.length) {
            this.headPtr = 0;
        }
        this.snake[this.headPtr].x = x;
        this.snake[this.headPtr].y = y;
        if (n == 196608) {
            this.score += 10;
            if (this.loaded && this.sound) {
                this.munchSound.play();
            }
            for (int i = 0; i < 6; ++i) {
                if (this.mouse[i].x == x && this.mouse[i].y == y) {
                    this.initMouse(i);
                }
            }
            ++this.miceEaten;
        }
        else {
            final Point point2 = this.snake[this.tailPtr];
            this.grid[point2.x][point2.y] = 0;
            if (++this.tailPtr >= this.snake.length) {
                this.tailPtr = 0;
            }
            final Point point3 = this.snake[this.tailPtr];
            this.grid[point3.x][point3.y] = (0x20000 | (this.grid[point3.x][point3.y] & 0xFF00) | 0x5);
        }
        if (n == 262144) {
            this.score += 200;
            if (this.loaded && this.sound) {
                this.advanceSound.play();
            }
            this.endLevel();
        }
    }
    
    public void killSnake() {
        int n = this.headPtr + 1;
        if (n >= this.snake.length) {
            n = 0;
        }
        while (this.tailPtr != n) {
            final Point point = this.snake[this.tailPtr];
            this.grid[point.x][point.y] = 0;
            if (++this.tailPtr >= this.snake.length) {
                this.tailPtr = 0;
            }
        }
    }
    
    public void initMouse(final int n) {
        int x;
        int y;
        do {
            x = (int)(Math.random() * 39.0);
            y = (int)(Math.random() * 25.0);
        } while ((this.grid[x][y] & 0xFF0000) != 0x0);
        final int n2 = (int)(Math.random() * 4.0) + 1 << 8;
        this.mouse[n].x = x;
        this.mouse[n].y = y;
        this.grid[x][y] = (0x30000 | n2 | 0x6);
    }
    
    public void moveMouse(final int n) {
        if (Math.random() > 0.25) {
            return;
        }
        if (this.mouse[n].x == -1 || this.mouse[n].y == -1) {
            return;
        }
        if (this.loaded && this.sound && Math.random() > 0.975) {
            this.squeakSound.play();
        }
        final int n2 = (int)(Math.random() * 5.0) << 8;
        final int n3 = this.grid[this.mouse[n].x][this.mouse[n].y] & 0xFF00;
        if ((n3 == 256 && n2 == 512) || (n3 == 512 && n2 == 256) || (n3 == 768 && n2 == 1024) || (n3 == 1024 && n2 == 768)) {
            return;
        }
        int x = this.mouse[n].x;
        int y = this.mouse[n].y;
        switch (n2) {
            case 256: {
                --x;
                break;
            }
            case 512: {
                ++x;
                break;
            }
            case 768: {
                --y;
                break;
            }
            case 1024: {
                ++y;
                break;
            }
            default: {
                return;
            }
        }
        if ((this.grid[x][y] & 0xFF0000) != 0x0) {
            return;
        }
        this.grid[this.mouse[n].x][this.mouse[n].y] = 0;
        this.mouse[n].x = x;
        this.mouse[n].y = y;
        this.grid[x][y] = (0x30000 | n2 | 0x6);
    }
    
    public void killMouse(final int n) {
        this.grid[this.mouse[n].x][this.mouse[n].y] = 0;
        this.mouse[n].x = -1;
        this.mouse[n].y = -1;
    }
    
    public void initKey() {
        int x;
        int y;
        do {
            x = (int)(Math.random() * 39.0);
            y = (int)(Math.random() * 25.0);
        } while ((this.grid[x][y] & 0xFF0000) != 0x0);
        this.key.x = x;
        this.key.y = y;
        this.grid[x][y] = 262151;
        this.keyActive = true;
    }
    
    public Polygon translate(final Polygon polygon, final int n, final int n2) {
        final Polygon polygon2 = new Polygon();
        for (int i = 0; i < polygon.npoints; ++i) {
            polygon2.addPoint(polygon.xpoints[i] + n, polygon.ypoints[i] + n2);
        }
        return polygon2;
    }
    
    public Polygon rotate(final Polygon polygon, final int n) {
        final Polygon polygon2 = new Polygon();
        for (int i = 0; i < polygon.npoints; ++i) {
            switch (n) {
                case 256: {
                    polygon2.addPoint(polygon.ypoints[i], 15 - polygon.xpoints[i]);
                    break;
                }
                case 512: {
                    polygon2.addPoint(15 - polygon.ypoints[i], polygon.xpoints[i]);
                    break;
                }
                case 1024: {
                    polygon2.addPoint(15 - polygon.xpoints[i], 15 - polygon.ypoints[i]);
                    break;
                }
                default: {
                    polygon2.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
                    break;
                }
            }
        }
        return polygon2;
    }
    
    public Polygon mirror(final Polygon polygon) {
        final Polygon polygon2 = new Polygon();
        for (int i = 0; i < polygon.npoints; ++i) {
            polygon2.addPoint(15 - polygon.xpoints[i], polygon.ypoints[i]);
        }
        return polygon2;
    }
    
    public Color fade(final Color color, final Color color2, final double n) {
        if (n < 0.0) {
            return color2;
        }
        if (n > 1.0) {
            return color;
        }
        return new Color(color2.getRed() + (int)Math.round(n * (color.getRed() - color2.getRed())), color2.getGreen() + (int)Math.round(n * (color.getGreen() - color2.getGreen())), color2.getBlue() + (int)Math.round(n * (color.getBlue() - color2.getBlue())));
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
        this.offGraphics.setColor(this.bgColor);
        this.offGraphics.fillRect(0, 0, size.width, size.height);
        final int n = 624;
        final int n2 = 400;
        final int n3 = (size.width - n) / 2;
        final int n4 = (size.height - (n2 + 2 * this.fontHeight)) / 2;
        this.offGraphics.translate(n3, n4);
        if (this.gameState == 3) {
            this.offGraphics.setColor(this.fade(this.fieldColor, this.bgColor, this.levelCounter / 40.0));
        }
        else {
            this.offGraphics.setColor(this.fieldColor);
        }
        this.offGraphics.fillRect(0, 0, 624, 400);
        for (int i = 0; i < 39; ++i) {
            for (int j = 0; j < 25; ++j) {
                switch (this.grid[i][j] & 0xFF0000) {
                    case 65536: {
                        if (this.gameState == 3) {
                            this.offGraphics.setColor(this.fade(this.blockColor, this.bgColor, this.levelCounter / 40.0));
                        }
                        else {
                            this.offGraphics.setColor(this.blockColor);
                        }
                        this.offGraphics.fillRect(i * 16, j * 16, 16, 16);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.drawRect(i * 16, j * 16, 16, 16);
                        break;
                    }
                    case 131072: {
                        final int n5 = this.grid[i][j] & 0xFF;
                        Polygon polygon = null;
                        switch (n5) {
                            case 1: {
                                polygon = this.snakeHead;
                                break;
                            }
                            case 2: {
                                polygon = this.snakeBody;
                                break;
                            }
                            case 3: {
                                polygon = this.snakeElb1;
                                break;
                            }
                            case 4: {
                                polygon = this.snakeElb2;
                                break;
                            }
                            case 5: {
                                polygon = this.snakeTail;
                                break;
                            }
                            default: {
                                polygon = this.snakeHead;
                                break;
                            }
                        }
                        final Polygon translate = this.translate(this.rotate(polygon, this.grid[i][j] & 0xFF00), i * 16, j * 16);
                        if (this.gameState == 3) {
                            this.offGraphics.setColor(this.fade(this.snakeColor, this.bgColor, this.levelCounter / 40.0));
                        }
                        else if (this.gameState == 4 || this.gameState == 5) {
                            this.offGraphics.setColor(this.fade(this.snakeColor, this.fieldColor, this.endCounter / 30.0));
                        }
                        else {
                            this.offGraphics.setColor(this.snakeColor);
                        }
                        this.offGraphics.fillPolygon(translate);
                        this.offGraphics.drawPolygon(translate);
                        this.offGraphics.drawLine(translate.xpoints[translate.npoints - 1], translate.ypoints[translate.npoints - 1], translate.xpoints[0], translate.ypoints[0]);
                        if (this.gameState == 4 || this.gameState == 5) {
                            this.offGraphics.setColor(this.fade(this.bgColor, this.fieldColor, this.endCounter / 30.0));
                        }
                        else {
                            this.offGraphics.setColor(this.bgColor);
                        }
                        if (n5 == 1 || n5 == 5) {
                            for (int k = 0; k < translate.npoints - 1; ++k) {
                                this.offGraphics.drawLine(translate.xpoints[k], translate.ypoints[k], translate.xpoints[k + 1], translate.ypoints[k + 1]);
                            }
                        }
                        if (n5 == 2) {
                            this.offGraphics.drawLine(translate.xpoints[0], translate.ypoints[0], translate.xpoints[1], translate.ypoints[1]);
                            this.offGraphics.drawLine(translate.xpoints[2], translate.ypoints[2], translate.xpoints[3], translate.ypoints[3]);
                        }
                        if (n5 == 3 || n5 == 4) {
                            this.offGraphics.drawLine(translate.xpoints[0], translate.ypoints[0], translate.xpoints[1], translate.ypoints[1]);
                            this.offGraphics.drawLine(translate.xpoints[1], translate.ypoints[1], translate.xpoints[2], translate.ypoints[2]);
                            this.offGraphics.drawLine(translate.xpoints[3], translate.ypoints[3], translate.xpoints[4], translate.ypoints[4]);
                            this.offGraphics.drawLine(translate.xpoints[4], translate.ypoints[4], translate.xpoints[5], translate.ypoints[5]);
                            this.offGraphics.drawLine(translate.xpoints[5], translate.ypoints[5], translate.xpoints[6], translate.ypoints[6]);
                            break;
                        }
                        break;
                    }
                    case 196608: {
                        if (this.gameState == 3) {
                            this.offGraphics.setColor(this.fade(this.mouseColor, this.bgColor, this.levelCounter / 40.0));
                        }
                        else {
                            this.offGraphics.setColor(this.mouseColor);
                        }
                        final Polygon translate2 = this.translate(this.rotate(this.mouseBody, this.grid[i][j] & 0xFF00), i * 16, j * 16);
                        this.offGraphics.fillPolygon(translate2);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.drawPolygon(translate2);
                        this.offGraphics.drawLine(translate2.xpoints[translate2.npoints - 1], translate2.ypoints[translate2.npoints - 1], translate2.xpoints[0], translate2.ypoints[0]);
                        break;
                    }
                    case 262144: {
                        final Polygon translate3 = this.translate(this.rotate(this.keyShape, this.grid[i][j] & 0xFF00), i * 16, j * 16);
                        this.offGraphics.setColor(this.keyColor);
                        this.offGraphics.fillPolygon(translate3);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.drawPolygon(translate3);
                        this.offGraphics.drawLine(translate3.xpoints[translate3.npoints - 1], translate3.ypoints[translate3.npoints - 1], translate3.xpoints[0], translate3.ypoints[0]);
                        break;
                    }
                }
            }
        }
        this.offGraphics.setColor(this.fieldColor);
        this.offGraphics.drawRect(0, 0, 624, 399);
        this.offGraphics.setFont(this.font);
        this.offGraphics.setColor(this.fieldColor);
        final int n6 = n2 - 1;
        final int n7 = n2 + 3 * this.fontHeight / 2;
        this.offGraphics.drawRect(0, n6, n, 2 * this.fontHeight);
        this.offGraphics.setColor(this.fgColor);
        this.offGraphics.drawString("Score: " + this.score, this.fontWidth, n7);
        final String string = "Level: " + this.levelTotal;
        this.offGraphics.drawString(string, (n - this.fm.stringWidth(string)) / 4, n7);
        final String string2 = "Lives: " + this.lives;
        this.offGraphics.drawString(string2, 3 * (n - this.fm.stringWidth(string2)) / 4, n7);
        final String string3 = "High: " + this.highScore;
        this.offGraphics.drawString(string3, n - (this.fontWidth + this.fm.stringWidth(string3)), n7);
        if (this.paused) {
            final String s = "Paused";
            this.offGraphics.drawString(s, (n - this.fm.stringWidth(s)) / 2, n7);
        }
        else if (!this.sound) {
            final String s2 = "Muted";
            this.offGraphics.drawString(s2, (n - this.fm.stringWidth(s2)) / 2, n7);
        }
        if (this.gameState == 1 || this.gameState == 5) {
            this.offGraphics.setColor(this.bgColor);
            final String s3 = "Snake Pit";
            this.offGraphics.drawString(s3, (n - this.fm.stringWidth(s3)) / 2 + 1, n2 / 3 + this.fontHeight + 1);
            final String s4 = "Copyright 1998 by Mike Hall";
            this.offGraphics.drawString(s4, (n - this.fm.stringWidth(s4)) / 2 + 1, n2 / 3 + 2 * this.fontHeight + 1);
            this.offGraphics.setColor(this.fgColor);
            final String s5 = "Snake Pit";
            this.offGraphics.drawString(s5, (n - this.fm.stringWidth(s5)) / 2, n2 / 3 + this.fontHeight);
            final String s6 = "Copyright 1998 by Mike Hall";
            this.offGraphics.drawString(s6, (n - this.fm.stringWidth(s6)) / 2, n2 / 3 + 2 * this.fontHeight);
            this.offGraphics.setColor(this.fgColor);
            String s7;
            if (!this.loaded) {
                s7 = "Loading sounds...";
            }
            else {
                s7 = "Game Over - 'S' to Start";
            }
            this.offGraphics.setColor(this.bgColor);
            this.offGraphics.drawString(s7, (n - this.fm.stringWidth(s7)) / 2 + 1, 2 * n2 / 3 + 1);
            this.offGraphics.setColor(this.fgColor);
            this.offGraphics.drawString(s7, (n - this.fm.stringWidth(s7)) / 2, 2 * n2 / 3);
        }
        if (this.gameState == 3) {
            final String string4 = "Advancing to Level " + (this.levelTotal + 1);
            this.offGraphics.setColor(this.bgColor);
            this.offGraphics.drawString(string4, (n - this.fm.stringWidth(string4)) / 2 + 1, n2 / 2 + 1);
            this.offGraphics.setColor(this.fgColor);
            this.offGraphics.drawString(string4, (n - this.fm.stringWidth(string4)) / 2, n2 / 2);
        }
        this.offGraphics.translate(-n3, -n4);
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public SnakePit() {
        this.bgColor = Color.black;
        this.fgColor = Color.white;
        this.blockColor = new Color(0, 0, 153);
        this.fieldColor = new Color(204, 153, 102);
        this.snakeColor = new Color(0, 153, 0);
        this.mouseColor = Color.gray;
        this.keyColor = new Color(204, 102, 102);
        this.loaded = false;
        this.font = new Font("Helvetica", 1, 12);
    }
}
