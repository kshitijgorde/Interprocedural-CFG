import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Asteroids extends Applet implements Runnable
{
    Gamehandler game;
    AudioClip[] sounds;
    Graphics gbuf;
    Image im;
    Image bkg;
    Thread thread;
    MediaTracker tracker;
    URL codebase;
    URL docbase;
    URL test;
    String[] choicetexts;
    Rectangle[] area;
    int width;
    int height;
    boolean fire;
    boolean cw;
    boolean ccw;
    boolean thrust;
    boolean[] choice;
    boolean doublebuff;
    boolean loadfail;
    
    public void stop() {
        this.choice[3] = false;
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public Asteroids() {
        this.choicetexts = new String[] { "Backgrounds", "Sounds", "Double buffering", "Run Game", "New Game", "Solid polygons" };
        this.area = new Rectangle[this.choicetexts.length];
        this.choice = new boolean[this.choicetexts.length];
        this.doublebuff = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.choice[3]) {
            if (this.game.nextlevel && this.choice[0] && !this.loadfail) {
                this.loadimage(graphics, this.game.level);
            }
            if (this.choice[2]) {
                if (!this.loadfail && this.choice[0]) {
                    this.gbuf.drawImage(this.bkg, 0, 0, this);
                }
                else {
                    this.gbuf.setColor(new Color(0, 0, 0));
                    this.gbuf.fillRect(0, 0, this.width, this.height);
                }
                this.game.paint(this.gbuf);
                if (this.game.gameover) {
                    this.gbuf.setColor(new Color(255, 255, 255));
                    this.gbuf.setFont(new Font("Arial", 1, 36));
                    this.gbuf.drawString("GAME OVER", this.width / 2 - 120, this.height / 2);
                }
                graphics.drawImage(this.im, 0, 0, this);
                return;
            }
            if (!this.loadfail && this.choice[0]) {
                graphics.drawImage(this.bkg, 0, 0, this);
            }
            else {
                graphics.setColor(new Color(0, 0, 0));
                graphics.fillRect(0, 0, this.width, this.height);
            }
            this.game.paint(graphics);
            if (this.game.gameover) {
                graphics.setColor(new Color(255, 255, 255));
                graphics.setFont(new Font("Arial", 1, 36));
                graphics.drawString("GAME OVER", this.width / 2 - 150, this.height / 2);
            }
        }
        else {
            this.drawChoices(graphics);
        }
    }
    
    public void drawChoices(final Graphics graphics) {
        this.gbuf.setColor(new Color(192, 192, 192));
        this.gbuf.fillRect(0, 0, this.width, this.height);
        this.gbuf.setColor(new Color(64, 64, 64));
        this.gbuf.fillRoundRect(105, 105, 440, 280, 50, 50);
        this.gbuf.setColor(new Color(50, 100, 255));
        this.gbuf.fillRoundRect(100, 100, 440, 280, 50, 50);
        this.gbuf.setFont(new Font("Arial", 1, 12));
        for (int i = 0; i < this.choice.length; ++i) {
            if (this.choice[i]) {
                this.gbuf.setColor(new Color(64, 64, 100));
                this.gbuf.fillRoundRect(this.area[i].x + 4, this.area[i].y + 4, 160, this.area[i].height, 10, 10);
                this.gbuf.setColor(new Color(162, 162, 255));
                this.gbuf.fillRoundRect(this.area[i].x + 2, this.area[i].y + 2, 160, this.area[i].height, 10, 10);
                this.gbuf.setColor(new Color(54, 54, 90));
                this.gbuf.drawString(this.choicetexts[i], this.area[i].x + 22, this.area[i].y + 14);
            }
            else {
                this.gbuf.setColor(new Color(64, 64, 100));
                this.gbuf.fillRoundRect(this.area[i].x + 4, this.area[i].y + 4, 160, this.area[i].height, 10, 10);
                this.gbuf.setColor(new Color(192, 192, 255));
                this.gbuf.fillRoundRect(this.area[i].x, this.area[i].y, 160, this.area[i].height, 10, 10);
                this.gbuf.setColor(new Color(64, 64, 100));
                this.gbuf.drawString(this.choicetexts[i], this.area[i].x + 20, this.area[i].y + 12);
            }
        }
        this.gbuf.setColor(new Color(64, 64, 100));
        this.gbuf.fillRoundRect(105, 25, 440, 70, 50, 50);
        this.gbuf.setColor(new Color(50, 100, 255));
        this.gbuf.fillRoundRect(100, 20, 440, 70, 50, 50);
        this.gbuf.setFont(new Font("Arial", 1, 36));
        this.gbuf.setColor(new Color(64, 64, 64));
        this.gbuf.drawString("T R E K K I E R O I D S", 139, 70);
        this.gbuf.setColor(new Color(255, 255, 255));
        this.gbuf.drawString("T R E K K I E R O I D S", 135, 66);
        graphics.drawImage(this.im, 0, 0, this);
    }
    
    public void loadimage(final Graphics graphics, final int n) {
        this.choice[3] = false;
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setFont(new Font("Arial", 1, 24));
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString("V\u00e4nta... laddar " + this.game.ld[n], this.width / 2 - 200, this.height / 2);
        graphics.setFont(new Font("Arial", 1, 14));
        this.bkg = this.getImage(this.codebase, "level" + n + ".jpg");
        if (this.bkg == null) {
            this.bkg = this.getImage(this.codebase, "level" + n + ".gif");
        }
        if (this.bkg == null) {
            graphics.drawString("Kunde ej ladda bild.", 20, 20);
            graphics.drawString(this.codebase.toString() + "level" + n + ".jpg", 20, 40);
            graphics.drawString(this.codebase.toString() + "level" + n + ".gif", 20, 55);
            this.loadfail = true;
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
        else {
            (this.tracker = new MediaTracker(this)).addImage(this.bkg, n);
            try {
                this.tracker.waitForID(n);
            }
            catch (InterruptedException ex2) {}
            if (!this.tracker.isErrorID(n)) {
                this.gbuf.drawImage(this.bkg, 0, 0, this);
            }
            else {
                this.bkg = null;
                graphics.drawString("Kunde ej ladda bild.", 20, 20);
                graphics.drawString(this.codebase.toString() + "level" + n + ".jpg", 20, 40);
                graphics.drawString(this.codebase.toString() + "level" + n + ".gif", 20, 55);
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex3) {}
            }
        }
        this.game.nextlevel = false;
        this.choice[3] = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean keyUp(final Event event, final int n) {
        switch (n) {
            case 32: {
                this.fire = false;
                break;
            }
            case 108: {
                this.cw = false;
                break;
            }
            case 106: {
                this.ccw = false;
                break;
            }
            case 107: {
                this.thrust = false;
                break;
            }
        }
        return true;
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
            this.thread.setPriority(10);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.choice[3]) {
            this.choice[3] = false;
        }
        else {
            for (int i = 0; i < this.choice.length; ++i) {
                if (this.area[i].inside(n, n2)) {
                    if (!this.choice[i]) {
                        this.choice[i] = true;
                    }
                    else {
                        this.choice[i] = false;
                    }
                }
            }
            if (this.choice[4]) {
                this.game = new Gamehandler(this.width, this.height, 0, this.sounds);
                this.choice[4] = false;
                this.choice[3] = true;
            }
            this.game.solid(this.choice[5]);
        }
        if (this.game.gameover) {
            this.game = new Gamehandler(this.width, this.height, 0, this.sounds);
            this.choice[4] = false;
            this.choice[3] = false;
        }
        this.game.soundControl(this.choice[1]);
        this.repaint();
        return true;
    }
    
    public void run() {
        this.repaint();
        while (true) {
            Thread.yield();
            if (this.choice[3] && !this.game.gameover) {
                this.repaint();
                this.game.play(this.fire, this.cw, this.ccw, this.thrust);
                try {
                    Thread.sleep(40L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void init() {
        this.width = Integer.valueOf(this.getParameter("wid"));
        this.height = Integer.valueOf(this.getParameter("hei"));
        this.im = this.createImage(this.width, this.height);
        this.codebase = this.getCodeBase();
        this.docbase = this.getDocumentBase();
        this.gbuf = this.im.getGraphics();
        this.sounds = new AudioClip[4];
        for (int i = 0; i < this.sounds.length; ++i) {
            this.sounds[i] = this.getAudioClip(this.codebase, "sound" + i + ".au");
        }
        for (int j = 0; j < this.choice.length; ++j) {
            this.area[j] = new Rectangle(240, 150 + 20 * j, 160, 15);
        }
        this.game = new Gamehandler(this.width, this.height, 0, this.sounds);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 32: {
                this.fire = true;
                break;
            }
            case 108: {
                this.cw = true;
                break;
            }
            case 106: {
                this.ccw = true;
                break;
            }
            case 107: {
                this.thrust = true;
                break;
            }
        }
        return true;
    }
}
