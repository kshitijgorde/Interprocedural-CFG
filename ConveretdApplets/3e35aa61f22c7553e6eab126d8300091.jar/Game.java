import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Game extends Applet implements Animation, MouseListener, MouseMotionListener
{
    private String initString;
    private boolean in;
    private boolean intro;
    private boolean initial;
    private boolean squish;
    private boolean loading;
    private int h;
    private int w;
    private int mousex;
    private int mousey;
    private int squishX;
    private int squishY;
    private int squishRadius;
    private int squishTimer;
    private int squishTime;
    private int changeTime;
    private int bugTimer;
    private int redBugTimer;
    private int points;
    private int lives;
    private int levelI;
    private int level;
    private int pause;
    private int highScore;
    private int titleSpeed;
    private int z;
    private int zy;
    private AudioClip click;
    private AudioClip noclick;
    private AudioClip levelUp;
    private AudioClip bite;
    private Image bug;
    private Image bug2;
    private Image bugSquish;
    private Image redBug;
    private Image redBug2;
    private Image bg;
    Vector enemy;
    Image im;
    Graphics offscreen;
    AnimationTimer timer;
    
    public void destroy() {
        this.timer.stop();
    }
    
    public String getAppletInfo() {
        return "Name: REDBUGS\r\nAuthor: Jake W. Holmes\r\n";
    }
    
    public void init() {
        try {
            this.im = this.createImage(this.w, this.h);
            this.offscreen = this.im.getGraphics();
        }
        catch (Exception ex) {
            this.offscreen = null;
        }
        Thread.currentThread().setPriority(10);
        this.enemy.addElement(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)));
        this.enemy.addElement(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)));
        this.enemy.addElement(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)));
        for (int i = 3; i < 11; ++i) {
            int j;
            int num;
            do {
                j = this.getNum(1, 4);
                num = this.getNum(0, 3);
            } while (j <= num);
            this.enemy.addElement(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 8, num, j, this.getNum(10, 20) / 10.0f, this.getNum(10, 20) / 10.0f, this.getNum(20, this.h - 20)));
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.points = 0;
        this.lives = 100;
        this.levelI = 0;
        this.level = 1;
        this.z = this.h / 2;
        this.zy = this.h;
        this.mousex = 1000;
        this.mousey = 1000;
        this.highScore = 0;
        try {
            this.click = this.getAudioClip(this.getCodeBase(), "click.au");
            this.levelUp = this.getAudioClip(this.getCodeBase(), "levelUp.au");
            this.bite = this.getAudioClip(this.getCodeBase(), "bite.au");
            this.noclick = this.getAudioClip(this.getCodeBase(), "noclick.au");
            this.bug = this.getImage(this.getDocumentBase(), "bug.gif");
            this.bug2 = this.getImage(this.getDocumentBase(), "bug2.gif");
            this.bugSquish = this.getImage(this.getDocumentBase(), "bugsquish.gif");
            this.redBug = this.getImage(this.getDocumentBase(), "redbug.gif");
            this.redBug2 = this.getImage(this.getDocumentBase(), "redbug2.gif");
            this.bg = this.getImage(this.getDocumentBase(), "bg.gif");
        }
        catch (Exception ex2) {}
    }
    
    public void reInit() {
        if (this.points > this.highScore) {
            this.highScore = this.points;
        }
        this.enemy.setElementAt(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)), 0);
        this.enemy.setElementAt(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)), 1);
        this.enemy.setElementAt(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 15, 0, 1, 1.0f, 0.0f, this.getNum(20, this.h - 20)), 2);
        for (int i = 3; i < this.enemy.size(); ++i) {
            int j;
            int num;
            do {
                j = this.getNum(1, 4);
                num = this.getNum(0, 3);
            } while (j <= num);
            this.enemy.setElementAt(new Circle(this.getNum(20, this.h - 20), this.getNum(20, this.h - 20), 8, num, j, this.getNum(10, 20) / 10.0f, this.getNum(10, 20) / 10.0f, this.getNum(20, this.h - 20)), i);
        }
        this.squish = false;
        this.intro = true;
        this.z = this.h;
        this.zy = this.h;
        this.points = 0;
        this.lives = 100;
        this.levelI = 0;
        this.level = 1;
        this.mousex = 1000;
        this.mousey = 1000;
        this.timer.delay = this.titleSpeed;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        graphics.drawImage(this.bg, 0, 0, 640, 400, null);
        if (this.intro) {
            graphics.setColor(Color.blue);
            if (!this.loading) {
                graphics.drawString("RED BUGS", this.w / 2 - 30, this.z - 15);
            }
            graphics.drawString(this.initString, this.w / 2 - 80, this.z);
            return;
        }
        graphics.setColor(Color.blue);
        graphics.drawString("HIGH SCORE: " + this.highScore, 10, 20);
        graphics.drawString("SCORE: " + this.points, 10, 35);
        graphics.drawString("LEVEL: " + this.level, 10, 65);
        if (this.lives < 50 && this.lives > 25) {
            graphics.setColor(Color.yellow);
        }
        if (this.lives <= 25) {
            graphics.setColor(Color.red);
        }
        graphics.drawString("LIFE ENERGY: " + this.lives, 10, 50);
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0.0f) {
                if (this.bugTimer > 40) {
                    this.bugTimer = 0;
                }
                else {
                    ++this.bugTimer;
                }
                if (circle.squishing) {
                    graphics.drawImage(this.bugSquish, this.squishX - this.squishRadius, this.squishY - this.squishRadius, this.squishRadius * 2, this.squishRadius * 2, this);
                    if (this.squishTimer > this.squishTime) {
                        this.squishTimer = 0;
                        circle.squishing = false;
                    }
                    else {
                        ++this.squishTimer;
                        if (this.squishRadius > 1) {
                            --this.squishRadius;
                        }
                    }
                }
                else if (this.bugTimer <= 20) {
                    graphics.drawImage(this.bug, circle.x - circle.r, circle.y - circle.r, circle.r * 2, circle.r * 2, this);
                }
                else {
                    graphics.drawImage(this.bug2, circle.x - circle.r, circle.y - circle.r, circle.r * 2, circle.r * 2, this);
                }
            }
            else {
                if (this.redBugTimer > 100) {
                    this.redBugTimer = 0;
                }
                else {
                    ++this.redBugTimer;
                }
                if (this.redBugTimer <= 50) {
                    graphics.drawImage(this.redBug, circle.x - circle.r, circle.y - circle.r, circle.r * 2, circle.r * 2, this);
                }
                else {
                    graphics.drawImage(this.redBug2, circle.x - circle.r, circle.y - circle.r, circle.r * 2, circle.r * 2, this);
                }
            }
        }
    }
    
    public void animate() {
        if (this.intro) {
            if (this.loading) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.bug, 0);
                mediaTracker.addImage(this.bug2, 1);
                mediaTracker.addImage(this.bugSquish, 2);
                mediaTracker.addImage(this.redBug, 3);
                mediaTracker.addImage(this.redBug2, 4);
                mediaTracker.addImage(this.bg, 5);
                this.initString = "LOADING IMAGES..... PLEASE WAIT";
                this.repaint();
                try {
                    mediaTracker.waitForAll();
                    final boolean b = !mediaTracker.isErrorAny();
                }
                catch (InterruptedException ex) {}
                this.initString = "(Copyright 1999 by Jake Holmes)";
                this.z = this.h;
                this.loading = false;
            }
            if (this.z > this.h / 2) {
                --this.z;
                this.repaint();
                return;
            }
            ++this.pause;
            if (this.pause > this.h / 2) {
                this.pause = 0;
                this.intro = false;
                this.timer.delay = 17;
            }
        }
        else {
            for (int i = 0; i < this.enemy.size(); ++i) {
                final Circle circle = this.enemy.elementAt(i);
                final double n = absVal(this.mousex - circle.x);
                double n2 = absVal(this.mousey - circle.y);
                if (n < circle.attackRng && n2 < circle.attackRng && circle.skill > 0.0f) {
                    this.in = true;
                }
                else {
                    this.in = false;
                }
                if (this.in) {
                    final double n3 = this.mousey - circle.y;
                    final double n4 = this.mousex - circle.x;
                    if (n2 < 0.0) {
                        n2 -= n2 * 2.0;
                    }
                    double n5 = 0.0;
                    double n6 = 0.0;
                    if (n >= n2 & n != 0.0) {
                        n5 = n4 / n * circle.chaseSpeed;
                        n6 = n3 / n * circle.chaseSpeed;
                    }
                    if (n < n2 & n2 != 0.0) {
                        n5 = n4 / n2 * circle.skill;
                        n6 = n3 / n2 * circle.skill;
                    }
                    circle.dx = (int)Math.round(n5);
                    circle.dy = (int)Math.round(n6);
                }
                if (circle.skill > 0.0f && this.mousex < circle.x + circle.r && this.mousex > circle.x - circle.r && this.mousey < circle.y + circle.r && this.mousey > circle.y - circle.r) {
                    --this.lives;
                    this.bite.play();
                    if (this.lives < 0) {
                        this.reInit();
                    }
                }
                if (!this.in || circle.skill == 0.0f) {
                    if (this.changeTime > 50) {
                        this.changeTime = 1;
                    }
                    if (circle.changeTime == this.changeTime) {
                        final int ddx = circle.ddx;
                        final int ddy = circle.ddy;
                        int num = this.getNum(ddx, ddy);
                        int num2 = this.getNum(ddx, ddy);
                        final int num3 = this.getNum(1, 50);
                        if (this.getNum(1, 2) == 2) {
                            num = -num;
                        }
                        if (this.getNum(1, 2) == 2) {
                            num2 = -num2;
                        }
                        circle.dx = num;
                        circle.dy = num2;
                        circle.changeTime = num3;
                    }
                }
                if (circle.x - circle.r + circle.dx < 0 || circle.x + circle.r + circle.dx > this.bounds().width) {
                    circle.dx = -circle.dx;
                }
                if (circle.y - circle.r + circle.dy < 0 || circle.y + circle.r + circle.dy > this.bounds().height) {
                    circle.dy = -circle.dy;
                }
                final Circle circle2 = circle;
                circle2.x += circle.dx;
                final Circle circle3 = circle;
                circle3.y += circle.dy;
            }
            ++this.changeTime;
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.in = true;
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0.0f) {
                circle.dy = circle.ddy * (int)circle.chaseSpeed;
                circle.dx = circle.ddx * (int)circle.chaseSpeed;
            }
        }
    }
    
    private static int absVal(int n) {
        if (n < 0) {
            n -= n * 2;
        }
        return n;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mousex = 1000;
        this.mousey = 100000;
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            circle.dy = circle.ddy;
            circle.dx = circle.ddx;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mousex = mouseEvent.getX();
        this.mousey = mouseEvent.getY();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.noclick.play();
        this.setCursor(Cursor.getPredefinedCursor(1));
        for (int i = 0; i < this.enemy.size(); ++i) {
            final Circle circle = this.enemy.elementAt(i);
            if (circle.skill == 0.0f && this.mousex < circle.x + circle.r + 3 && this.mousex > circle.x - circle.r - 3 && this.mousey < circle.y + circle.r + 3 && this.mousey > circle.y - circle.r - 3) {
                if (circle.r > 10) {
                    --circle.r;
                }
                this.points += 100;
                ++this.levelI;
                this.squishX = circle.x;
                this.squishY = circle.y;
                this.squishRadius = circle.r;
                circle.x = this.getNum(25, 600);
                circle.y = this.getNum(25, 350);
                this.click.play();
                circle.squishing = true;
                if (this.levelI == 12) {
                    this.levelI = 0;
                    ++this.level;
                    this.increaseLevel();
                    this.levelUp.play();
                }
            }
        }
    }
    
    public int getNum(final int n, final int n2) {
        return new RandomIntGenerator(n, n2).draw();
    }
    
    private void increaseLevel() {
        switch (this.level) {
            case 2: {
                for (int i = 0; i < 3; ++i) {
                    final Circle circle = this.enemy.elementAt(i);
                    circle.r = 15;
                    circle.dy = 2;
                }
            }
            case 3: {
                for (int j = 0; j < 3; ++j) {
                    final Circle circle2 = this.enemy.elementAt(j);
                    circle2.r = 15;
                    circle2.dy = 3;
                }
            }
            case 4: {
                for (int k = 3; k < this.enemy.size(); ++k) {
                    final Circle circle3 = this.enemy.elementAt(k);
                    circle3.chaseSpeed += 0.2;
                }
            }
            case 5: {
                for (int l = 3; l < this.enemy.size(); ++l) {
                    final Circle circle4 = this.enemy.elementAt(l);
                    circle4.chaseSpeed += 0.3;
                }
            }
            case 6: {
                final Circle circle5 = this.enemy.elementAt(2);
                circle5.skill = 1.5f;
                circle5.r = 8;
            }
            case 7: {
                final Circle circle6 = this.enemy.elementAt(1);
                circle6.skill = 1.5f;
                circle6.r = 8;
            }
            default: {
                if (this.timer.delay > 1) {
                    final AnimationTimer timer = this.timer;
                    --timer.delay;
                }
                for (int n = 3; n < this.enemy.size(); ++n) {
                    final Circle circle7 = this.enemy.elementAt(n);
                    circle7.chaseSpeed += 0.3;
                }
            }
        }
    }
    
    public void start() {
        this.timer.start_animation();
    }
    
    public void stop() {
        this.timer.pause_animation();
    }
    
    public Game() {
        this.in = false;
        this.intro = true;
        this.initial = true;
        this.squish = false;
        this.loading = true;
        this.h = 400;
        this.w = 640;
        this.squishTime = 10;
        this.bugTimer = 10;
        this.redBugTimer = 20;
        this.titleSpeed = 5;
        this.enemy = new Vector(1, 11);
        this.timer = new AnimationTimer(this, this.titleSpeed);
    }
}
