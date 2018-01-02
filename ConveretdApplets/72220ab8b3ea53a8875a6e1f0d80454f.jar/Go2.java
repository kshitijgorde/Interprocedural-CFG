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

public class Go2 extends Applet implements Runnable
{
    public int[] xx;
    public int[] yy;
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
    
    public Go2() {
        this.xx = new int[] { 5, 3, 6, 6, 6, 5, 4, 4, 3, 3, 4, 2, 5, 3, 6, 3, 4, 3, 4, 3, 4, 4, 5, 3, 5, 5, 7, 4, 5, 6, 7, 4, 3, 2, 0 };
        this.yy = new int[] { 3, 6, 6, 2, 3, 2, 2, 1, 1, 2, 3, 1, 1, 0, 1, 4, 5, 5, 7, 7, 6, 8, 8, 8, 7, 0, 2, 4, 4, 0, 0, 0, 3, 3, 0 };
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
            if (this.num == 10) {
                this.draw_flag = true;
            }
            if (this.num == 11) {
                this.play = 2;
            }
            if (this.num == 12) {
                this.draw_flag = true;
            }
            if (this.num == 13) {
                this.play = 3;
            }
            if (this.num == 16) {
                this.draw_flag = true;
            }
            if (this.num == 17) {
                this.play = 4;
            }
            if (this.num == 26) {
                this.draw_flag = true;
            }
            if (this.num == 27) {
                this.play = 5;
            }
            if (this.num == 34) {
                this.draw_flag = true;
            }
            if (this.num == 35) {
                this.play = 6;
            }
            if (this.num == 36) {
                this.draw_flag = true;
            }
            if (this.num == 37) {
                this.play = 7;
            }
            if (this.num == 38) {
                this.draw_flag = true;
            }
            if (this.num > 38) {
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
        final Color cl = new Color(255, 180, 0);
        final Color col = new Color(255, 180, 0);
        final int s1 = 0;
        final int s2 = 4;
        final int r1 = 20;
        final int r2 = 19;
        final int r3 = 18;
        final int xx1 = 0;
        final int yy1 = 0;
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
                this.st_num = 10;
                this.end_num = this.num;
                break;
            }
            case 3: {
                this.st_num = 12;
                this.end_num = this.num;
                break;
            }
            case 4: {
                this.st_num = 16;
                this.end_num = this.num;
                break;
            }
            case 5: {
                this.st_num = 26;
                this.end_num = this.num;
                break;
            }
            case 6: {
                this.st_num = 0;
                this.end_num = -1;
                break;
            }
            case 7: {
                this.st_num = 0;
                this.end_num = -1;
                break;
            }
        }
        for (int i = 0; i < this.num && i != 34; ++i) {
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
            if (this.num > 13) {
                gt.setColor(cl);
                gt.fillRect(60, 20 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(60, 30 + s1, 80, 30 + s1);
                gt.drawLine(70, 20 + s1, 70, 40 + s1);
            }
            if (i > 26) {
                gt.setColor(cl);
                gt.fillRect(100, 40 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(100, 50 + s1, 120, 50 + s1);
                gt.drawLine(110, 40 + s1, 110, 60 + s1);
                gt.setColor(cl);
                gt.fillRect(120, 40 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(120, 50 + s1, 140, 50 + s1);
                gt.drawLine(130, 40 + s1, 130, 60 + s1);
            }
        }
        for (int i = this.st_num; i < this.end_num && i != 34; ++i) {
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
            if (i == 13) {
                gt.setColor(cl);
                gt.fillRect(60, 20 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(60, 30 + s1, 80, 30 + s1);
                gt.drawLine(70, 20 + s1, 70, 40 + s1);
            }
            if (i == 26) {
                gt.setColor(cl);
                gt.fillRect(100, 40 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(100, 50 + s1, 120, 50 + s1);
                gt.drawLine(110, 40 + s1, 110, 60 + s1);
                gt.setColor(cl);
                gt.fillRect(120, 40 + s1, 20, 20);
                gt.setColor(Color.black);
                gt.drawLine(120, 50 + s1, 140, 50 + s1);
                gt.drawLine(130, 40 + s1, 130, 60 + s1);
            }
        }
        if (this.play == 6) {
            gt.setColor(Color.black);
            gt.fillOval(100 + s1, 40 + s1, r1, r1);
            gt.setColor(Color.white);
            final Font font = new Font("TimeRoman", 0, 14);
            gt.setFont(font);
            gt.drawString("\u0394", 100 + s1 + 3, 40 + s1 + 20 - 3);
            gt.setColor(Color.white);
            gt.fillOval(60 + s1, 20 + s1, r2, r2);
            gt.setColor(Color.black);
            gt.drawOval(60 + s1, 20 + s1, r2, r2);
            gt.drawString("\u0394", 60 + s1 + 3, 20 + s1 + 20 - 3);
            gt.setColor(Color.white);
            gt.fillOval(40 + s1, 40 + s1, r2, r2);
            gt.setColor(Color.black);
            gt.drawOval(40 + s1, 40 + s1, r2, r2);
            gt.drawString("\u0394", 40 + s1 + 3, 40 + s1 + 20 - 3);
        }
        if (this.play == 7) {
            gt.setColor(Color.black);
            gt.fillOval(100, 40, 20, 20);
            gt.setColor(Color.white);
            gt.fillOval(0, 60, 19, 19);
            gt.setColor(Color.black);
            gt.drawOval(0, 60, 19, 19);
            gt.setColor(Color.white);
            gt.fillOval(60, 20, 19, 19);
            gt.setColor(Color.black);
            gt.drawOval(60, 20, 19, 19);
            this.Change(gt, true, 7, 2, 6, 7);
            this.Change(gt, true, 6, 3, 5, 5);
            this.Change(gt, true, 6, 6, 5, 6);
            this.Change(gt, false, 2, 1, 1, 3);
            this.Change(gt, false, 2, 2, 2, 3);
            this.Change(gt, true, 6, 7, 7, 1);
            gt.setColor(Color.black);
            gt.drawOval(3, 63, 13, 13);
            gt.drawOval(23, 63, 13, 13);
            gt.setColor(Color.white);
            gt.drawOval(143, 23, 13, 13);
            gt.drawOval(103, 103, 13, 13);
            gt.drawOval(103, 123, 13, 13);
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
    
    void Change(final Graphics gt, final boolean tt, final int x1, final int y1, final int x2, final int y2) {
        final Color col = new Color(255, 180, 0);
        gt.setColor(col);
        gt.fillRect(x1 * 20, y1 * 20, 20, 20);
        gt.setColor(Color.black);
        gt.drawLine(x1 * 20, y1 * 20 + 10, x1 * 20 + 20, y1 * 20 + 10);
        gt.drawLine(x1 * 20 + 10, y1 * 20, x1 * 20 + 10, y1 * 20 + 20);
        if (tt) {
            gt.setColor(Color.black);
            gt.fillOval(x2 * 20, y2 * 20, 20, 20);
        }
        else {
            gt.setColor(Color.white);
            gt.fillOval(x2 * 20, y2 * 20, 19, 19);
            gt.setColor(Color.black);
            gt.drawOval(x2 * 20, y2 * 20, 19, 19);
        }
    }
}
