import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Go extends Applet implements Runnable
{
    public int[] xx;
    public int[] yy;
    public int[] xw;
    public int[] yw;
    public int[] xb;
    public int[] yb;
    public int play;
    public int st_num;
    public int end_num;
    public int number;
    public int count;
    public int num;
    int sleep_time;
    Thread ban;
    boolean draw_flag;
    Graphics gg;
    Graphics buffering;
    Image buffer;
    Dimension d;
    
    public Go() {
        this.xx = new int[] { 5, 3, 6, 2, 4, 2, 4, 3, 4, 4, 5, 4, 5, 3, 4, 4, 5, 3, 5, 4, 5, 3, 5, 3, 4, 3 };
        this.yy = new int[] { 3, 6, 6, 4, 2, 2, 7, 7, 6, 5, 5, 4, 4, 1, 1, 0, 0, 0, 1, 8, 8, 8, 7, 2, 3, 3 };
        this.xw = new int[] { 0, 1, 2, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 3, 0, 1, 2, 3, 0, 1, 2, 0, 1, 2, 0, 1, 2 };
        this.yw = new int[] { 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8 };
        this.xb = new int[] { 6, 7, 8, 6, 7, 8, 5, 6, 7, 8, 6, 7, 8, 6, 7, 8, 6, 7, 8, 5, 7, 8, 6, 7, 8, 6, 7, 8 };
        this.yb = new int[] { 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8 };
        this.play = 0;
        this.number = 50;
        this.count = 50;
        this.num = 0;
        this.sleep_time = 500;
        this.ban = null;
        this.draw_flag = false;
    }
    
    public void init() {
        this.resize(190, 190);
        this.d = this.getSize();
        this.buffer = this.createImage(this.d.width, this.d.height);
    }
    
    public void start() {
        if (this.ban == null) {
            (this.ban = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.ban != null) {
            if (this.draw_flag) {
                this.sleep_time = 2000;
            }
            else {
                this.sleep_time = 500;
            }
            this.draw_flag = false;
            this.repaint();
            try {
                Thread.sleep(this.sleep_time);
            }
            catch (InterruptedException e) {
                break;
            }
            ++this.num;
            if (this.num == 1) {
                this.play = 1;
            }
            if (this.num == 6) {
                this.draw_flag = true;
            }
            if (this.num == 7) {
                this.play = 2;
            }
            if (this.num == 9) {
                this.draw_flag = true;
            }
            if (this.num == 10) {
                this.play = 3;
            }
            if (this.num == 15) {
                this.draw_flag = true;
            }
            if (this.num == 16) {
                this.play = 4;
            }
            if (this.num == 23) {
                this.draw_flag = true;
            }
            if (this.num == 24) {
                this.play = 5;
            }
            if (this.num == 27) {
                this.play = 6;
                this.draw_flag = true;
            }
            if (this.num > 27) {
                this.play = 0;
            }
        }
    }
    
    public void stop() {
        if (this.ban != null) {
            this.ban.stop();
            this.ban = null;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        if (this.buffer == null) {
            this.init();
        }
        if (this.buffering == null) {
            this.buffering = this.buffer.getGraphics();
        }
        this.gg = this.buffering;
        this.setBackground(Color.white);
        Goban2(this.gg);
        this.Play(this.gg);
        g.drawImage(this.buffer, 0, 0, this);
    }
    
    public void Play(final Graphics gt) {
        final Color col = new Color(255, 180, 0);
        final int s1 = 0;
        final int s2 = 4;
        final int r1 = 20;
        final int r2 = 19;
        final int r3 = 18;
        int xx1 = 0;
        int yy1 = 0;
        switch (this.play) {
            case 0: {
                this.num = 0;
                this.st_num = 0;
                this.end_num = -1;
                break;
            }
            case 1: {
                this.st_num = 0;
                this.end_num = this.num;
                break;
            }
            case 2: {
                this.st_num = 6;
                this.end_num = this.num;
                xx1 = 5;
                yy1 = 7;
                break;
            }
            case 3: {
                this.st_num = 9;
                this.end_num = this.num;
                xx1 = 4;
                yy1 = 0;
                break;
            }
            case 4: {
                this.st_num = 15;
                this.end_num = this.num;
                break;
            }
            case 5: {
                this.st_num = 23;
                this.end_num = this.num;
                break;
            }
            case 6: {
                this.st_num = 0;
                this.end_num = -1;
                gt.setColor(col);
                gt.fillRect(0, 0, 180, 180);
                gt.setColor(Color.black);
                gt.drawLine(0, 0, 180, 0);
                gt.drawLine(0, 0, 0, 180);
                gt.drawLine(180, 0, 180, 180);
                gt.drawLine(0, 180, 180, 180);
                break;
            }
        }
        for (int i = 0; i < this.num && i != 26; ++i) {
            if (i % 2 == 0) {
                gt.setColor(Color.black);
                gt.fillOval(this.xx[i] * 20 + s1, this.yy[i] * 20 + s1, r1, r1);
            }
            else {
                gt.setColor(Color.white);
                gt.fillOval(this.xx[i] * 20 + s1, this.yy[i] * 20 + s1, r2, r2);
                gt.setColor(Color.black);
                gt.drawOval(this.xx[i] * 20 + s1, this.yy[i] * 20 + s1, r2, r2);
            }
        }
        for (int i = this.st_num; i < this.end_num && i != 26; ++i) {
            final Font font = new Font("Dialog", 0, 12);
            if (i % 2 == 0) {
                gt.setColor(Color.white);
                gt.setFont(font);
                if (i > 8) {
                    gt.drawString(String.valueOf(" ").concat(String.valueOf(i + 1)), this.xx[i] * 20, this.yy[i] * 20 + 14);
                }
                else {
                    gt.drawString(String.valueOf(" ").concat(String.valueOf(i + 1)), this.xx[i] * 20 + 4, this.yy[i] * 20 + 14);
                }
            }
            else {
                gt.setColor(Color.black);
                gt.setFont(font);
                if (i > 8) {
                    gt.drawString(String.valueOf(" ").concat(String.valueOf(i + 1)), this.xx[i] * 20, this.yy[i] * 20 + 14);
                }
                else {
                    gt.drawString(String.valueOf(" ").concat(String.valueOf(i + 1)), this.xx[i] * 20 + 4, this.yy[i] * 20 + 14);
                }
            }
        }
        if (this.play == 6) {
            gt.setColor(Color.black);
            final Font font = new Font("TimeRoman", 0, 18);
            gt.setFont(font);
            for (int i = 0; i < 27; ++i) {
                gt.drawString("w", this.xw[i] * 20 + 6, this.yw[i] * 20 + 14);
            }
            for (int i = 0; i < 28; ++i) {
                gt.drawString("b", this.xb[i] * 20 + 6, this.yb[i] * 20 + 14);
            }
        }
    }
    
    public static void Goban2(final Graphics g) {
        final int sx = 10;
        final int sy = 10;
        final int we = 180;
        final int he = 180;
        final Color col = new Color(255, 180, 0);
        g.setColor(col);
        g.fillRect(sx - 10, sy - 10, we, he);
        g.setColor(Color.black);
        g.drawLine(sx - 10, sy - 10, we, sy - 10);
        g.drawLine(sx - 10, sy - 10, sx - 10, he);
        g.drawLine(sx + we - 10, sy - 10, sx + we - 10, sy + he - 10);
        g.drawLine(sx - 10, sy + he - 10, sx + we - 10, sy + he - 10);
        for (int i = 0; i < 9; ++i) {
            g.drawLine(sx, sy + i * 20, sx + we - 20, sy + i * 20);
            g.drawLine(sx + i * 20, sy, sx + i * 20, sy + he - 20);
        }
    }
}
