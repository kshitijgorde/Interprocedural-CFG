import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.Random;
import java.awt.Graphics;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Alien extends Applet implements KeyListener, Runnable, MouseListener
{
    boolean pause;
    boolean pausepress;
    boolean isStoped;
    Color background;
    Cursor c;
    Image building;
    Image monster;
    Image redmonster;
    MediaTracker mt;
    URL base;
    int[] buildx;
    int[] buildy;
    int h;
    int w;
    private Image dbImage;
    private Graphics dbg;
    Thread th;
    int prob;
    int kills;
    int aliens;
    int aliens2;
    int shots;
    int accuracy;
    int score;
    int topscore;
    int play;
    String rank;
    Random generator;
    Monster[] myMonster;
    
    public Alien() {
        this.pause = false;
        this.pausepress = false;
        this.isStoped = true;
        this.prob = 100;
        this.kills = 0;
        this.aliens = 0;
        this.aliens2 = 0;
        this.shots = 0;
        this.accuracy = 100;
        this.score = 0;
        this.topscore = 0;
        this.play = 1;
        this.generator = new Random();
    }
    
    public void init() {
        this.h = this.getSize().height;
        this.w = this.getSize().width;
        this.setBackground(this.background = new Color(0, 0, 0));
        this.kills = 0;
        this.aliens = 0;
        this.aliens2 = 0;
        this.shots = 0;
        this.accuracy = 100;
        this.score = 0;
        this.prob = 100;
        this.buildx = new int[7];
        this.buildy = new int[7];
        for (int i = 0; i < 7; ++i) {
            this.buildx[i] = 64 * i;
            this.buildy[i] = this.generator.nextInt(160) + 100;
        }
        this.myMonster = new Monster[7];
        for (int j = 0; j < 7; ++j) {
            this.myMonster[j] = new Monster(j * 64 + 9, this.buildy[j] + 4);
            this.myMonster[j].aliens = 0;
        }
        this.setCursor(this.c = new Cursor(1));
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.mt = new MediaTracker(this);
        try {
            this.base = this.getDocumentBase();
        }
        catch (Exception ex) {}
        this.building = this.getImage(this.base, "building.jpg");
        this.monster = this.getImage(this.base, "monster1.gif");
        this.redmonster = this.getImage(this.base, "alien2.jpg");
        this.mt.addImage(this.building, 1);
        this.mt.addImage(this.monster, 2);
        this.mt.addImage(this.redmonster, 2);
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex2) {}
    }
    
    public void start() {
        if (this.th == null) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            if (!this.pause) {
                this.repaint();
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        if (this.th != null) {
            this.th = null;
        }
    }
    
    public void destroy() {
    }
    
    public void updateBackground() {
        for (int i = 0; i < 7; ++i) {
            this.dbg.drawImage(this.building, this.buildx[i], this.buildy[i], this);
            this.myMonster[i].paint(this.dbg);
        }
    }
    
    public void updatePlayer() {
        this.aliens = 0;
        for (int i = 0; i < 7; ++i) {
            if (this.myMonster[i].alive && !this.myMonster[i].shot) {
                this.dbg.drawImage(this.monster, this.myMonster[i].monsterx, this.myMonster[i].monstery, this);
            }
            if (this.myMonster[i].alive && this.myMonster[i].shot) {
                this.dbg.drawImage(this.redmonster, this.myMonster[i].shotx, this.myMonster[i].shoty, this);
            }
            if (!this.pause && this.myMonster[i].alive) {
                this.myMonster[i].move();
            }
            if (!this.myMonster[i].alive && this.generator.nextInt(this.prob) == 1) {
                this.myMonster[i].alive = true;
                this.myMonster[i].shot = false;
            }
            this.aliens += this.myMonster[i].aliens;
        }
        this.aliens += this.aliens2;
        if (this.aliens > 10) {
            this.prob = 45;
        }
        if (this.aliens > 20) {
            this.prob = 40;
        }
        if (this.aliens > 30) {
            this.prob = 35;
        }
        if (this.aliens > 40) {
            this.prob = 30;
        }
        if (this.aliens > 50) {
            this.prob = 20;
        }
        if (this.aliens > 60) {
            this.prob = 10;
        }
        if (this.aliens > 80) {
            this.prob = 5;
        }
        if (this.aliens > 100) {
            this.isStoped = true;
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.w, this.h);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.w, this.h);
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isStoped) {
            graphics.setColor(Color.white);
            if (this.score > 0) {
                graphics.drawString("Score: " + this.score, 180, 120);
                if (this.score > 0) {
                    this.rank = "Civilian";
                }
                if (this.score > 1000) {
                    this.rank = "Cadet";
                }
                if (this.score > 2000) {
                    this.rank = "Corporal";
                }
                if (this.score > 3000) {
                    this.rank = "Sergeant";
                }
                if (this.score > 4000) {
                    this.rank = "Lieutenant";
                }
                if (this.score > 5000) {
                    this.rank = "Major";
                }
                if (this.score > 6000) {
                    this.rank = "General";
                }
                if (this.score > 7000) {
                    this.rank = "Field Marshal";
                }
                if (this.score > 8000) {
                    this.rank = "Elite";
                }
                if (this.score > 9000) {
                    this.rank = "Hero";
                }
                graphics.drawString("Rank: " + this.rank, 160, 100);
            }
            if (this.score > this.topscore) {
                this.topscore = this.score;
            }
            graphics.drawString("TopScore: " + this.topscore, 170, 140);
            graphics.drawString("Doubleclick to start!", 160, 160);
            graphics.drawString("Copyright 2003 Daniel Andrews", 130, 200);
        }
        else {
            this.dbg.setColor(Color.black);
            this.dbg.fillRect(0, 0, this.w, this.h);
            this.updatePlayer();
            this.updateBackground();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.w, 20);
            graphics.fillRect(0, this.h - 20, this.w, 20);
            this.dbg.setColor(Color.black);
            graphics.drawString("Kills: " + this.kills, 40, 13);
            if (this.shots - this.play + 2 > 0) {
                this.accuracy = this.kills * this.play * 100 / (this.shots - this.play + 2);
            }
            graphics.drawString("Accuracy: " + this.accuracy + "%", 300, 13);
            graphics.drawString("www.nomoreboredom.com", 150, this.h - 7);
            this.score = this.accuracy * this.kills;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'p') {
            this.pausepress = true;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'p' && this.pausepress && !this.pause) {
            this.pause = true;
            this.pausepress = false;
        }
        else if (keyEvent.getKeyChar() == 'p' && this.pausepress && this.pause) {
            this.pause = false;
            this.pausepress = false;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.isStoped && !this.pause) {
            ++this.shots;
            for (int i = 0; i < 7; ++i) {
                if (this.myMonster[i].hit(mouseEvent.getX(), mouseEvent.getY()) && mouseEvent.getY() < this.buildy[i]) {
                    this.myMonster[i].speed = -8;
                    ++this.kills;
                }
            }
        }
        else if (this.isStoped && mouseEvent.getClickCount() == 2) {
            this.isStoped = false;
            this.init();
            ++this.play;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
