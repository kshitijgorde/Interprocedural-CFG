import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class wermz extends Applet implements Runnable
{
    Image playarea;
    Image brick;
    int imgw;
    int imgh;
    PixelGrabber grabber;
    int ns;
    int width;
    int height;
    int[] x;
    int[] y;
    int[] z;
    int[] sp;
    int lens;
    int scrx;
    int scry;
    int dist;
    int widthmiddle;
    int heightmiddle;
    int fontsize;
    long hiscore;
    int pixel2;
    int pixel3;
    int linex;
    int liney;
    int[] universe;
    int keyz;
    int[] pixels;
    int grab;
    int speed;
    int point;
    int wormsize;
    int grow;
    int[][] worm;
    boolean playOn;
    public int horizontal;
    public int vertical;
    int number;
    int numberY;
    int numberX;
    int belowRow;
    int direction;
    int lastdirection;
    long score;
    int level;
    int xTemp;
    int yTemp;
    int rowShift;
    boolean initplayarea;
    boolean wormontop;
    boolean wormdied;
    boolean finished;
    boolean star;
    boolean loaded;
    int aboveRow;
    int row;
    int lives;
    int returned;
    int horizcolor;
    int vertcolor;
    int green;
    int green2;
    int green3;
    int dumb;
    Image workspace;
    Graphics offscreen;
    Thread runner;
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void randomplot() {
        boolean freespace = false;
        do {
            this.numberX = (int)(Math.random() * 430.0) + 20;
            this.numberY = (int)(Math.random() * 280.0) + 70;
            final int npixel = this.pixels[this.numberY * this.imgw + this.numberX];
            final int bpixel = this.pixels[(this.numberY + 10) * this.imgw + this.numberX];
            final int rpixel = this.pixels[this.numberY * this.imgw + (this.numberX + 15)];
            final int lpixel = this.pixels[this.numberY * this.imgw + (this.numberX - 1)];
            final int corner = this.pixels[(this.numberY + 15) * this.imgw + (this.numberX + 10)];
            final int red = npixel >> 16 & 0xFF;
            final int ngreen = npixel >> 8 & 0xFF;
            final int bgreen = bpixel >> 8 & 0xFF;
            final int rgreen = rpixel >> 8 & 0xFF;
            final int lgreen = lpixel >> 8 & 0xFF;
            final int cgreen = corner >> 8 & 0xFF;
            if (cgreen == 0 && ngreen == 0 && bgreen == 0 && rgreen == 0 && lgreen == 0) {
                freespace = true;
            }
            final int blue = npixel & 0xFF;
            if (ngreen > 0) {
                freespace = false;
            }
        } while (!freespace);
    }
    
    public void makerandom() {
        int dummy = 0;
        while (dummy < this.ns) {
            this.x[dummy] = (int)(Math.random() * this.width) - this.widthmiddle;
            this.y[dummy] = (int)(Math.random() * this.height) - this.heightmiddle;
            this.z[dummy] = (int)(Math.random() * 750.0) - 500;
            this.sp[dummy] = (int)(Math.random() * 6.0) + 1;
            ++dummy;
            this.showStatus("Getting random values...:" + dummy);
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void collidedetection() {
        final int numberoffsetX = this.numberX + 13;
        final int numberoffsetY = this.numberY + 12;
        if (this.numberX + 2 < this.horizcolor && numberoffsetX > this.horizcolor && this.numberY < this.vertcolor && numberoffsetY > this.vertcolor) {
            ++this.number;
            this.score += 500L;
            this.wormsize += 50;
            if (this.number != 10) {
                this.randomplot();
            }
        }
    }
    
    public boolean keyDown(final Event e, final int key) {
        switch (this.keyz = key) {
            case 1005: {
                if (this.lastdirection == 2) {
                    this.direction = this.lastdirection;
                    break;
                }
                this.direction = 1;
                break;
            }
            case 1004: {
                if (this.lastdirection == 1) {
                    this.direction = this.lastdirection;
                    break;
                }
                this.direction = 2;
                break;
            }
            case 1007: {
                if (this.lastdirection != 4) {
                    this.direction = 3;
                    break;
                }
                break;
            }
            case 1006: {
                if (this.lastdirection != 3) {
                    this.direction = 4;
                    break;
                }
                break;
            }
            case 1012: {
                this.playOn = false;
                this.level = 1;
                this.initplayarea = false;
                break;
            }
            case 1009: {
                this.playOn = true;
                this.grow = 200;
                this.star = false;
                this.finished = false;
                this.level = 1;
                this.initplayarea = false;
                this.init();
                break;
            }
            default: {
                this.direction = this.lastdirection;
                break;
            }
        }
        return false;
    }
    
    public wermz() {
        this.ns = 100;
        this.width = 480;
        this.height = 380;
        this.x = new int[1000];
        this.y = new int[1000];
        this.z = new int[1000];
        this.sp = new int[1000];
        this.linex = 45;
        this.liney = 25;
        this.universe = new int[] { 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.speed = 5;
        this.point = 10;
        this.wormsize = 300;
        this.grow = 200;
        this.worm = new int[2][9000];
        this.horizontal = 70;
        this.vertical = 70;
        this.lastdirection = 1;
        this.level = 1;
        this.star = true;
        this.lives = 3;
        this.horizcolor = 1;
        this.vertcolor = 1;
    }
    
    public void scanworm() {
        this.row = 1;
        while (this.row != this.grow) {
            if (this.direction == 1) {
                this.linex = this.worm[0][this.row];
                this.liney = this.worm[1][this.row];
            }
            if (this.direction == 2) {
                this.linex = this.worm[0][this.row];
                this.liney = this.worm[1][this.row] + 12;
            }
            if (this.direction == 3) {
                this.linex = this.worm[0][this.row];
                this.liney = this.worm[1][this.row];
            }
            if (this.direction == 4) {
                this.linex = this.worm[0][this.row] + 12;
                this.liney = this.worm[1][this.row];
            }
            if (this.linex == this.horizcolor && this.liney == this.vertcolor) {
                this.wormontop = true;
            }
            if (this.worm[0][this.row] == this.horizcolor && this.worm[1][this.row] == this.vertcolor) {
                this.wormontop = true;
            }
            ++this.row;
        }
    }
    
    public void shiftworm() {
        this.rowShift = this.grow - 1;
        while (this.rowShift != 0) {
            this.aboveRow = this.rowShift - 1;
            this.xTemp = this.worm[0][this.aboveRow];
            this.yTemp = this.worm[1][this.aboveRow];
            this.worm[0][this.rowShift] = this.xTemp;
            this.worm[1][this.rowShift] = this.yTemp;
            --this.rowShift;
        }
    }
    
    public void clearworm() {
        for (int row = 0; row != this.wormsize; ++row) {
            this.worm[0][row] = 0;
            this.worm[1][row] = 0;
        }
    }
    
    public void colordetection() {
        if (this.direction == 1) {
            this.horizcolor = 5 + this.horizontal;
            this.vertcolor = 13 + this.vertical;
            this.pixel2 = this.pixels[(this.vertcolor - 3) * this.imgw + this.horizcolor - 6];
            this.pixel3 = this.pixels[(this.vertcolor - 3) * this.imgw + this.horizcolor + 6];
            this.green2 = (this.pixel2 >> 8 & 0xFF);
            this.green3 = (this.pixel3 >> 8 & 0xFF);
        }
        if (this.direction == 2) {
            this.horizcolor = 5 + this.horizontal;
            this.vertcolor = -1 + this.vertical;
            this.pixel2 = this.pixels[(this.vertcolor + 3) * this.imgw + this.horizcolor - 6];
            this.pixel3 = this.pixels[(this.vertcolor + 3) * this.imgw + this.horizcolor + 6];
            this.green2 = (this.pixel2 >> 8 & 0xFF);
            this.green3 = (this.pixel3 >> 8 & 0xFF);
        }
        if (this.direction == 3) {
            this.horizcolor = 13 + this.horizontal;
            this.vertcolor = 7 + this.vertical;
            this.pixel2 = this.pixels[(this.vertcolor - 3) * this.imgw + this.horizcolor - 4];
            this.pixel3 = this.pixels[(this.vertcolor + 3) * this.imgw + this.horizcolor - 4];
            this.green2 = (this.pixel2 >> 8 & 0xFF);
            this.green3 = (this.pixel3 >> 8 & 0xFF);
        }
        if (this.direction == 4) {
            this.vertcolor = 7 + this.vertical;
            this.horizcolor = -1 + this.horizontal;
            this.pixel2 = this.pixels[(this.vertcolor - 3) * this.imgw + this.horizcolor + 4];
            this.pixel3 = this.pixels[(this.vertcolor + 3) * this.imgw + this.horizcolor + 4];
            this.green2 = (this.pixel2 >> 8 & 0xFF);
            this.green3 = (this.pixel3 >> 8 & 0xFF);
        }
        final int pixel = this.pixels[this.vertcolor * this.imgw + this.horizcolor];
        this.green = (pixel >> 8 & 0xFF);
    }
    
    public void run() {
        final float hue = 2.5f;
        final float saturation = 3.8f;
        float brightness = 0.0f;
        final Font dotFont = new Font("TimesRoman", 0, 9);
        final Font scoreFont = new Font("TimesRoman", 0, 14);
        while (true) {
            if (!this.playOn) {
                this.showStatus("Click on applet and hit F2 to START!");
            }
            while (!this.playOn) {
                this.showStatus("Click on applet and hit F2 to START!");
                try {
                    Thread.currentThread();
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex) {}
                this.offscreen.setColor(Color.black);
                this.offscreen.fillRect(0, 0, this.width, this.height);
                this.offscreen.setColor(Color.white);
                this.offscreen.setFont(dotFont);
                for (int d = 1; d < this.ns; ++d) {
                    final int[] z = this.z;
                    final int n = d;
                    z[n] += this.sp[d];
                    if (this.z[d] > 255) {
                        this.z[d] = -500;
                    }
                    this.dist = 256 - this.z[d];
                    this.scrx = this.lens * this.x[d] / this.dist + this.widthmiddle;
                    if (this.scrx < this.width) {
                        this.scry = this.heightmiddle - this.lens * this.y[d] / this.dist;
                        if (this.scry < this.height) {
                            this.offscreen.drawLine(this.scrx, this.scry, this.scrx, this.scry);
                        }
                    }
                }
                this.offscreen.setColor(Color.yellow);
                if (this.fontsize < 80) {
                    this.offscreen.setFont(dotFont);
                    this.offscreen.drawString("Adam Malinowski  v3 ", 0, 10);
                    this.offscreen.drawString("c.1998", 455, 10);
                    ++this.fontsize;
                    this.offscreen.setColor(Color.yellow);
                    final Font currentFont = new Font("TimesRoman", 0, this.fontsize);
                    this.offscreen.setFont(currentFont);
                    this.offscreen.drawString("WERMZ", 90, this.heightmiddle);
                }
                else {
                    final Font currentFont = new Font("TimesRoman", 0, this.fontsize);
                    this.offscreen.setFont(currentFont);
                    this.offscreen.drawString("WERMZ", 90, this.heightmiddle);
                    this.offscreen.setFont(dotFont);
                    if (this.hiscore > 0L) {
                        this.offscreen.setFont(scoreFont);
                        this.offscreen.drawString("Todays Highest Score: " + this.hiscore, 150, this.heightmiddle + 30);
                        if (this.finished) {
                            this.offscreen.drawString("Congratulations, you have finished Wermz! ", 120, this.heightmiddle - 70);
                        }
                        this.offscreen.setFont(dotFont);
                    }
                    else {
                        this.offscreen.setFont(scoreFont);
                        this.offscreen.setColor(Color.yellow);
                        this.offscreen.setColor(Color.getHSBColor(hue, saturation, brightness));
                        brightness += 0.05f;
                        if (brightness > 1.0f) {
                            brightness = 0.0f;
                        }
                        this.offscreen.drawString("Click here and hit F2 ", 190, this.heightmiddle + 30);
                    }
                }
                this.repaint();
            }
            if (this.playOn && this.loaded) {
                this.scanworm();
                if (this.score < 0L) {
                    this.score = 0L;
                }
                this.showStatus("YOUR SCORE: " + this.score);
                try {
                    Thread.currentThread();
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex2) {}
                if (!this.initplayarea) {
                    this.offscreen.setColor(Color.black);
                    this.drawlevel();
                    try {
                        Thread.currentThread();
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex3) {}
                    this.drawlevel();
                    final PixelGrabber grabber = new PixelGrabber(this.workspace, 0, 0, this.imgw, this.imgh, this.pixels, 0, this.imgw);
                    this.repaint();
                    try {
                        grabber.grabPixels();
                    }
                    catch (InterruptedException exception) {
                        this.offscreen.drawString("Error getting pixels", 200, 100);
                    }
                    this.randomplot();
                    this.initplayarea = true;
                }
                this.offscreen.setColor(Color.black);
                if (this.worm[0][this.wormsize] != 0 && this.worm[1][this.wormsize] != 0) {
                    this.offscreen.fillRect(this.worm[0][this.wormsize], this.worm[1][this.wormsize], 16, 13);
                }
                if (this.grow <= this.wormsize) {
                    ++this.grow;
                }
                this.offscreen.setColor(Color.blue);
                this.offscreen.fillOval(this.horizontal, this.vertical, 13, 12);
                this.offscreen.setColor(Color.cyan);
                this.offscreen.drawString("" + this.number, this.numberX + 5, this.numberY + 10);
                this.lastdirection = this.direction;
                this.shiftworm();
                --this.score;
                this.collidedetection();
                if (this.number == 10) {
                    this.number = 1;
                    this.clearworm();
                    this.wormsize = 300;
                    this.grow = 200;
                    this.horizontal = 70;
                    this.vertical = 70;
                    this.score += 1000L;
                    ++this.level;
                    if (this.level > 7) {
                        this.playOn = false;
                        this.finished = true;
                        this.level = 1;
                    }
                    this.randomplot();
                    this.initplayarea = false;
                }
                this.colordetection();
                if (this.wormontop || this.green > 45 || this.green2 > 45 || this.green3 > 45) {
                    this.green2 = 0;
                    this.green3 = 0;
                    this.grow = 1;
                    this.wormdied = true;
                    for (int row = 0; row != this.wormsize; ++row) {
                        this.worm[0][row] = 0;
                        this.worm[1][row] = 0;
                    }
                    this.direction = 3;
                    this.worm[0][0] = 70;
                    this.worm[1][0] = 70;
                    this.horizontal = 70;
                    this.vertical = 70;
                    this.wormsize = 300;
                    this.wormontop = false;
                }
                if (this.grow < this.wormsize - 40 && this.number == 1) {
                    this.direction = 3;
                }
                if (this.direction == 1) {
                    ++this.vertical;
                    if (this.vertical > 352) {
                        this.vertical -= 290;
                    }
                    this.worm[0][0] = this.horizontal;
                    this.worm[1][0] = this.vertical;
                    this.lastdirection = 1;
                }
                if (this.direction == 2) {
                    --this.vertical;
                    if (this.vertical < 65) {
                        this.vertical += 290;
                    }
                    this.worm[0][0] = this.horizontal;
                    this.worm[1][0] = this.vertical;
                    this.lastdirection = 2;
                }
                if (this.direction == 3) {
                    ++this.horizontal;
                    if (this.horizontal > 452) {
                        this.horizontal -= 437;
                    }
                    this.worm[0][0] = this.horizontal;
                    this.worm[1][0] = this.vertical;
                    this.lastdirection = 3;
                }
                if (this.direction == 4) {
                    --this.horizontal;
                    if (this.horizontal < 16) {
                        this.horizontal += 437;
                    }
                    this.worm[0][0] = this.horizontal;
                    this.worm[1][0] = this.vertical;
                    this.lastdirection = 4;
                }
                this.lastdirection = this.direction;
                this.repaint();
            }
        }
    }
    
    public void drawlevel() {
        int cell = 0;
        int sx = 0;
        int sy = 0;
        int i = 0;
        for (int marker = 10; marker != this.level + 10; marker = this.universe[i], ++i) {}
        this.offscreen.drawImage(this.playarea, 0, 0, this);
        sy = 0;
        do {
            sx = 0;
            do {
                cell = this.universe[i];
                ++i;
                if (cell == 1) {
                    this.offscreen.drawImage(this.brick, sx + 7, sy, this);
                }
                sx += 25;
            } while (sx <= 480);
            sy += 25;
        } while (sy <= 380);
    }
    
    public void destroy() {
    }
    
    public void init() {
        if (!this.loaded) {
            this.showStatus("Applet succesfully loaded! Please wait..this will not take long..");
            final MediaTracker t = new MediaTracker(this);
            this.repaint();
            this.playarea = this.getImage(this.getCodeBase(), "playarea.jpg");
            this.brick = this.getImage(this.getCodeBase(), "brick.jpg");
            t.addImage(this.playarea, 1);
            t.addImage(this.brick, 9);
            try {
                t.waitForID(1);
            }
            catch (InterruptedException ie) {
                return;
            }
            this.showStatus("1 of 2 images loaded...");
            try {
                t.waitForID(9);
            }
            catch (InterruptedException ie) {
                return;
            }
            this.showStatus("2 of 2 loaded... pre-calculating 3d starfield..");
            this.loaded = true;
        }
        this.initplayarea = false;
        this.workspace = this.createImage(this.size().width, this.size().height);
        this.offscreen = this.workspace.getGraphics();
        this.lens = 256;
        this.direction = this.lastdirection;
        this.number = 1;
        final Font normalFont = new Font("TimesRoman", 1, 9);
        this.imgw = 480;
        this.imgh = 380;
        this.pixels = new int[this.imgw * this.imgh];
        this.green = 0;
        this.widthmiddle = this.width / 2;
        this.heightmiddle = this.height / 2;
        this.makerandom();
    }
    
    public void paint(final Graphics screen) {
        screen.drawImage(this.workspace, 0, 0, this);
    }
    
    public void update(final Graphics screen) {
        if (!this.playOn || !this.initplayarea) {
            screen.drawImage(this.workspace, 0, 0, this);
            return;
        }
        if (this.wormdied) {
            this.offscreen.setColor(Color.yellow);
            final Font dieFont = new Font("TimesRoman", 0, 20);
            this.offscreen.setFont(dieFont);
            this.offscreen.drawString("werm died", 200, 250);
            --this.lives;
            this.playOn = false;
            screen.drawImage(this.workspace, 0, 0, this);
            try {
                Thread.currentThread();
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            this.initplayarea = false;
            this.wormdied = false;
            if (this.score > this.hiscore) {
                this.hiscore = this.score;
            }
            this.score = 0L;
        }
        screen.setColor(Color.black);
        if (this.worm[0][this.wormsize] != 0 && this.worm[1][this.wormsize] != 0) {
            screen.fillRect(this.worm[0][this.wormsize], this.worm[1][this.wormsize], 16, 13);
        }
        if (this.grow <= this.wormsize) {
            ++this.grow;
        }
        screen.setColor(Color.blue);
        screen.fillOval(this.horizontal, this.vertical, 13, 12);
        screen.setColor(Color.cyan);
        if (this.number != 10) {
            screen.drawString("" + this.number, this.numberX + 5, this.numberY + 10);
        }
    }
}
