import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.applet.AudioClip;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Match extends Applet implements MouseListener, Runnable
{
    private Image dbImage;
    private Graphics dbg;
    Thread th;
    GameTimer timer;
    final int gamelength = 180;
    int timeleft;
    private Random rnd;
    final int maxpics = 20;
    Image[] matchimg;
    Block block;
    Block[][] screen;
    int row;
    int col;
    int count;
    int p;
    int size;
    int iwidth;
    int iheight;
    int selection;
    AudioClip yoohoo;
    AudioClip start;
    AudioClip gameover;
    AudioClip door;
    AudioClip aroooga;
    AudioClip bicycleHorn;
    boolean end_of_game;
    int sav_row;
    int sav_col;
    
    public Match() {
        this.rnd = new Random();
        this.matchimg = new Image[20];
        this.screen = new Block[10][10];
        this.size = 8;
        this.iwidth = 50;
        this.iheight = 50;
        this.selection = 0;
        this.end_of_game = false;
        this.sav_row = 0;
        this.sav_col = 0;
    }
    
    public void init() {
        this.timer = new GameTimer(180);
        this.door = this.getAudioClip(this.getCodeBase(), "door.wav");
        this.yoohoo = this.getAudioClip(this.getCodeBase(), "yoohoo.wav");
        this.gameover = this.getAudioClip(this.getCodeBase(), "gameover.wav");
        this.aroooga = this.getAudioClip(this.getCodeBase(), "aroooga.wav");
        this.bicycleHorn = this.getAudioClip(this.getCodeBase(), "bicycleHorn.wav");
        this.start = this.getAudioClip(this.getCodeBase(), "start.wav");
        final String[] array = { "1.gif", "2.gif", "3.gif", "4.gif", "5.gif", "6.gif", "7.gif", "8.gif", "9.gif", "10.gif", "11.gif", "12.gif", "13.gif", "14.gif", "15.gif", "16.gif", "17.gif", "18.gif", "19.gif", "20.gif" };
        for (int i = 0; i < this.matchimg.length; ++i) {
            this.matchimg[i] = this.getImage(this.getCodeBase(), array[i]);
        }
        while (this.count != this.size * this.size) {
            this.p = (int)(Math.random() * 20.0);
            do {
                this.row = (int)(Math.random() * this.size);
                this.col = (int)(Math.random() * this.size);
            } while (this.screen[this.row][this.col] != null);
            this.block = new Block(this.row * this.iwidth, this.col * this.iheight, this.iwidth, this.iheight, this.p, true);
            this.screen[this.row][this.col] = this.block;
            ++this.count;
            do {
                this.row = (int)(Math.random() * this.size);
                this.col = (int)(Math.random() * this.size);
            } while (this.screen[this.row][this.col] != null);
            this.block = new Block(this.row * this.iwidth, this.col * this.iheight, this.iwidth, this.iheight, this.p, true);
            this.screen[this.row][this.col] = this.block;
            ++this.count;
        }
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    public void stop() {
    }
    
    public void run() {
        this.start.play();
        this.addMouseListener(this);
        while (!this.end_of_game) {
            this.timeleft = this.timer.getTime();
            if (this.timeleft == 0) {
                this.end_of_game = true;
            }
            this.playSounds(this.timeleft);
            this.delay(200);
            this.repaint();
        }
        this.gameover.play();
        this.delay(2000);
        this.destroy();
    }
    
    public void destroy() {
        if (this.th != null) {
            this.th = null;
        }
        this.removeMouseListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final double n = mouseEvent.getX();
        final double n2 = mouseEvent.getY();
        this.row = 0;
        while (this.row < this.size) {
            this.col = 0;
            while (this.col < this.size) {
                if (this.screen[this.row][this.col].userHit(n, n2)) {
                    this.screen[this.row][this.col].killBlock();
                    this.repaint();
                    this.delay(200);
                    if (this.selection == 0) {
                        this.sav_row = this.row;
                        this.sav_col = this.col;
                        this.selection = 1;
                    }
                    else if (this.selection == 1) {
                        this.selection = 0;
                        if (this.screen[this.row][this.col].picture == this.screen[this.sav_row][this.sav_col].picture) {
                            this.yoohoo.play();
                        }
                        else {
                            this.door.play();
                            this.screen[this.row][this.col].alive = true;
                            this.screen[this.sav_row][this.sav_col].alive = true;
                        }
                    }
                }
                ++this.col;
            }
            ++this.row;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void delay(final int n) {
        try {
            final Thread th = this.th;
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void playSounds(final int n) {
        if (n == 10) {
            this.bicycleHorn.play();
        }
        if (n == 30) {
            this.aroooga.play();
        }
        if (n == 60) {
            this.bicycleHorn.play();
        }
        if (n == 90) {
            this.aroooga.play();
        }
        if (n == 150) {
            this.aroooga.play();
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.row = 0;
        while (this.row < this.size) {
            this.col = 0;
            while (this.col < this.size) {
                graphics.drawImage(this.matchimg[this.screen[this.row][this.col].picture], this.row * this.iwidth, this.col * this.iheight, this.iwidth, this.iheight, this);
                ++this.col;
            }
            ++this.row;
        }
        this.row = 0;
        while (this.row < this.size) {
            this.col = 0;
            while (this.col < this.size) {
                this.screen[this.row][this.col].drawBlock(graphics);
                ++this.col;
            }
            ++this.row;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 70, 15);
        graphics.setColor(Color.yellow);
        graphics.drawString("TIME: " + Integer.toString(this.timeleft), 10, 10);
        graphics.setColor(Color.white);
        graphics.drawString("Copyright (c) 2003 By Charles L. Wilson", 100, 390);
        graphics.drawString("http://www.getimage.com/match", 100, 10);
    }
}
