import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wowwords extends Applet implements Runnable
{
    Thread th;
    private Image dbImage;
    private Graphics dbg;
    int h;
    int w;
    Color background;
    Color text;
    Font font1;
    Font font2;
    Font font3;
    Font font4;
    String word;
    int length;
    String[] chars;
    int[] chary;
    int[] charx;
    String[] evilchars;
    int textx;
    int draw;
    int status;
    Random generator;
    boolean regroup;
    int finish;
    int font;
    boolean updown;
    boolean updown2;
    int strength;
    boolean isStoped;
    
    public Wowwords() {
        this.status = 0;
        this.generator = new Random();
        this.regroup = false;
        this.finish = 0;
        this.font = 0;
        this.updown = true;
        this.updown2 = true;
        this.isStoped = false;
    }
    
    public void init() {
        this.h = this.getSize().height;
        this.w = this.getSize().width;
        this.regroup = false;
        this.isStoped = false;
        this.strength = Integer.parseInt(this.getParameter("wind_strength"), 10);
        this.font1 = new Font("Arial", 0, 14 - this.strength * 1);
        this.font2 = new Font("Arial", 0, 14 + this.strength * 1);
        this.font3 = new Font("Arial", 0, 14 + this.strength * 2);
        this.font4 = new Font("Arial", 0, 14 + this.strength * 3);
        this.word = this.getParameter("word");
        this.length = this.word.length();
        this.textx = 125 - 5 * this.length;
        this.chars = new String[this.length];
        this.chary = new int[this.length];
        this.charx = new int[this.length];
        for (int i = 0; i < this.length; ++i) {
            this.chars[i] = this.word.substring(i, i + 1);
        }
        final char[] array = { '\u0006', '\u0002', '\u0002', '\u0002', '\u000e', '\u0005', '\u0006', '\u000e' };
        for (int j = 0; j < this.length; ++j) {
            this.chary[j] = this.h - 20;
        }
        for (int k = 0; k < this.length; ++k) {
            this.charx[k] = this.textx + this.draw;
            this.draw += 11;
            if (this.chars[k].equals("f") || this.chars[k].equals("r") || this.chars[k].equals("t")) {
                this.draw -= 3;
            }
            if (this.chars[k].equals("m") || this.chars[k].equals("w")) {
                this.draw += 5;
            }
            if (this.chars[k].equals("i") || this.chars[k].equals("j") || this.chars[k].equals("l")) {
                this.draw -= 7;
            }
        }
        final String parameter = this.getParameter("bg_color");
        this.background = new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4), 16));
        final String parameter2 = this.getParameter("fg_color");
        this.text = new Color(Integer.parseInt(parameter2.substring(0, 2), 16), Integer.parseInt(parameter2.substring(2, 4), 16), Integer.parseInt(parameter2.substring(4), 16));
        this.setBackground(this.background);
    }
    
    public void start() {
        if (this.th == null) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.th == Thread.currentThread()) {
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void stop() {
        this.isStoped = true;
        if (this.th != null) {
            this.th = null;
        }
    }
    
    public void destroy() {
        this.isStoped = true;
    }
    
    public void updateWorm() {
        if (this.updown2) {
            ++this.font;
        }
        if (!this.updown2) {
            --this.font;
        }
        if (this.font == 4) {
            this.updown2 = false;
            this.font = 2;
        }
        if (this.font == -1) {
            this.updown2 = true;
            this.font = 1;
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
            graphics.setColor(this.text);
            graphics.drawString("Error", 0, 0);
        }
        else {
            graphics.setColor(this.text);
            graphics.setFont(this.font1);
            int font = this.font;
            for (int i = 0; i < this.length; ++i) {
                if (font == 0) {
                    graphics.setFont(this.font1);
                }
                if (font == 1) {
                    graphics.setFont(this.font2);
                }
                if (font == 2) {
                    graphics.setFont(this.font3);
                }
                if (font == 3) {
                    graphics.setFont(this.font4);
                }
                graphics.drawString(this.chars[i], this.charx[i], this.chary[i]);
                if (this.updown) {
                    ++font;
                }
                if (!this.updown) {
                    --font;
                }
                if (font == 4) {
                    this.updown = false;
                    font = 2;
                }
                if (font == -1) {
                    this.updown = true;
                    font = 1;
                }
            }
            this.draw = 0;
            this.updateWorm();
        }
    }
}
