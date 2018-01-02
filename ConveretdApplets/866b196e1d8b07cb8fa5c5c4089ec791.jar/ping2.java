import java.awt.Graphics;
import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ping2 extends Applet implements Runnable
{
    char currkey;
    int ypos;
    int y1pos;
    int flag;
    int xpos;
    int y;
    int k;
    Thread runner;
    int score1;
    int score2;
    
    public void init() {
        this.setBackground(Color.black);
        this.setFont(new Font("Helvetica", 1, 15));
    }
    
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    public void stop() {
        this.runner.stop();
        this.runner = null;
    }
    
    public void run() {
        while (true) {
            this.k += 10;
            this.s(20, 1);
            this.repeat();
        }
    }
    
    public void s(final int xpos, final int y) {
        this.xpos = xpos;
        this.y = y;
        while (this.xpos <= this.size().width - 5 || this.y <= this.size().height - 8) {
            if (this.y == this.size().height - 9 || this.y == this.size().height - 8) {
                this.s1(this.xpos, this.y);
            }
            if (this.xpos == this.size().width - 10 || this.xpos == this.size().width - 9) {
                this.s3(this.xpos, this.y);
            }
            if ((this.y == this.y1pos - 4 || this.y == this.y1pos - 2 || this.y == this.y1pos - 3 || this.y == this.y1pos - 1 || this.y == this.y1pos + 1 || this.y == this.y1pos + 2 || this.y == this.y1pos + 3 || this.y == this.y1pos + 4 || this.y == this.y1pos + 5 || this.y == this.y1pos + 6 || this.y == this.y1pos + 7 || this.y == this.y1pos + 8 || this.y == this.y1pos + 9 || this.y == this.y1pos + 10 || this.y == this.y1pos + 11 || this.y == this.y1pos + 12 || this.y == this.y1pos + 13 || this.y == this.y1pos + 14 || this.y == this.y1pos + 15 || this.y == this.y1pos + 16 || this.y == this.y1pos + 17 || this.y == this.y1pos + 18 || this.y == this.y1pos + 19 || this.y == this.y1pos + 20 || this.y == this.y1pos + 21 || this.y == this.y1pos + 22 || this.y == this.y1pos + 23 || this.y == this.y1pos + 24 || this.y == this.y1pos + 25 || this.y == this.y1pos + 26 || this.y == this.y1pos + 27 || this.y == this.y1pos + 28 || this.y == this.y1pos + 29 || this.y == this.y1pos + 30) && (this.xpos == 244 || this.xpos == 245 || this.xpos == 246 || this.xpos == 247 || this.xpos == 248 || this.xpos == 249 || this.xpos == 250)) {
                ++this.score2;
                this.s3(this.xpos, this.y);
            }
            this.repeat();
            this.y += 2;
            this.xpos += 2;
        }
    }
    
    public void s1(final int xpos, final int n) {
        this.xpos = xpos;
        this.y = this.y;
        while (this.xpos <= this.size().width - 5) {
            if (this.y == 5 || this.y == 4) {
                this.s(this.xpos, this.y);
            }
            if (this.xpos == this.size().width - 10 || this.xpos == this.size().width - 9) {
                this.s2(this.xpos, this.y);
            }
            if ((this.y == this.y1pos - 4 || this.y == this.y1pos - 2 || this.y == this.y1pos - 3 || this.y == this.y1pos - 1 || this.y == this.y1pos + 1 || this.y == this.y1pos + 2 || this.y == this.y1pos + 3 || this.y == this.y1pos + 4 || this.y == this.y1pos + 5 || this.y == this.y1pos + 6 || this.y == this.y1pos + 7 || this.y == this.y1pos + 8 || this.y == this.y1pos + 9 || this.y == this.y1pos + 10 || this.y == this.y1pos + 11 || this.y == this.y1pos + 12 || this.y == this.y1pos + 13 || this.y == this.y1pos + 14 || this.y == this.y1pos + 15 || this.y == this.y1pos + 16 || this.y == this.y1pos + 17 || this.y == this.y1pos + 18 || this.y == this.y1pos + 19 || this.y == this.y1pos + 20 || this.y == this.y1pos + 21 || this.y == this.y1pos + 22 || this.y == this.y1pos + 23 || this.y == this.y1pos + 24 || this.y == this.y1pos + 25 || this.y == this.y1pos + 26 || this.y == this.y1pos + 27 || this.y == this.y1pos + 28 || this.y == this.y1pos + 29 || this.y == this.y1pos + 30) && (this.xpos == 244 || this.xpos == 245 || this.xpos == 246 || this.xpos == 247 || this.xpos == 248 || this.xpos == 249 || this.xpos == 250)) {
                ++this.score2;
                this.s2(this.xpos, this.y);
            }
            this.repeat();
            this.xpos += 2;
            this.y -= 2;
        }
    }
    
    public void s2(final int xpos, final int y) {
        this.xpos = xpos;
        this.y = y;
        while (this.y >= 0) {
            if (this.y == 5 || this.y == 4) {
                this.s3(this.xpos, this.y);
            }
            if (this.xpos == 0 || this.xpos == 1) {
                this.s1(this.xpos, this.y);
            }
            if ((this.y == this.ypos - 4 || this.y == this.ypos - 2 || this.y == this.ypos - 3 || this.y == this.ypos - 1 || this.y == this.ypos + 1 || this.y == this.ypos + 2 || this.y == this.ypos + 3 || this.y == this.ypos + 4 || this.y == this.ypos + 5 || this.y == this.ypos + 6 || this.y == this.ypos + 7 || this.y == this.ypos + 8 || this.y == this.ypos + 9 || this.y == this.ypos + 10 || this.y == this.ypos + 11 || this.y == this.ypos + 12 || this.y == this.ypos + 13 || this.y == this.ypos + 14 || this.y == this.ypos + 15 || this.y == this.ypos + 16 || this.y == this.ypos + 17 || this.y == this.ypos + 18 || this.y == this.ypos + 19 || this.y == this.ypos + 20 || this.y == this.ypos + 21 || this.y == this.ypos + 22 || this.y == this.ypos + 23 || this.y == this.ypos + 24 || this.y == this.ypos + 25 || this.y == this.ypos + 26 || this.y == this.ypos + 27 || this.y == this.ypos + 28 || this.y == this.ypos + 29 || this.y == this.ypos + 30) && (this.xpos == 50 || this.xpos == 49 || this.xpos == 48 || this.xpos == 47 || this.xpos == 46 || this.xpos == 45)) {
                ++this.score1;
                this.s1(this.xpos, this.y);
            }
            this.repeat();
            this.y -= 2;
            this.xpos -= 2;
        }
    }
    
    public void s3(final int xpos, final int y) {
        this.xpos = xpos;
        this.y = y;
        while (this.xpos >= 0) {
            if (this.y == this.size().height - 9 || this.y == this.size().height - 8) {
                this.s4(this.xpos, this.y);
            }
            if (this.xpos == 0 || this.xpos == 1) {
                this.s(this.xpos, this.y);
            }
            if ((this.y == this.ypos - 4 || this.y == this.ypos - 2 || this.y == this.ypos - 3 || this.y == this.ypos - 1 || this.y == this.ypos + 1 || this.y == this.ypos + 2 || this.y == this.ypos + 3 || this.y == this.ypos + 4 || this.y == this.ypos + 5 || this.y == this.ypos + 6 || this.y == this.ypos + 7 || this.y == this.ypos + 8 || this.y == this.ypos + 9 || this.y == this.ypos + 10 || this.y == this.ypos + 11 || this.y == this.ypos + 12 || this.y == this.ypos + 13 || this.y == this.ypos + 14 || this.y == this.ypos + 15 || this.y == this.ypos + 16 || this.y == this.ypos + 17 || this.y == this.ypos + 18 || this.y == this.ypos + 19 || this.y == this.ypos + 20 || this.y == this.ypos + 21 || this.y == this.ypos + 22 || this.y == this.ypos + 23 || this.y == this.ypos + 24 || this.y == this.ypos + 25 || this.y == this.ypos + 26 || this.y == this.ypos + 27 || this.y == this.ypos + 28 || this.y == this.ypos + 29 || this.y == this.ypos + 30) && (this.xpos == 50 || this.xpos == 49 || this.xpos == 48 || this.xpos == 47 || this.xpos == 46 || this.xpos == 45)) {
                ++this.score1;
                this.s(this.xpos, this.y);
            }
            this.repeat();
            this.xpos -= 2;
            this.y += 2;
        }
    }
    
    public void s4(final int xpos, final int y) {
        this.xpos = xpos;
        this.y = y;
        while (this.xpos >= 0) {
            if (this.xpos == 5 || this.xpos == 4) {
                this.s5(this.xpos, this.y);
            }
            if (this.y == 5 || this.y == 4) {
                this.s3(this.xpos, this.y);
            }
            if ((this.y == this.ypos - 4 || this.y == this.ypos - 2 || this.y == this.ypos - 3 || this.y == this.ypos - 1 || this.y == this.ypos + 1 || this.y == this.ypos + 2 || this.y == this.ypos + 3 || this.y == this.ypos + 4 || this.y == this.ypos + 5 || this.y == this.ypos + 6 || this.y == this.ypos + 7 || this.y == this.ypos + 8 || this.y == this.ypos + 9 || this.y == this.ypos + 10 || this.y == this.ypos + 11 || this.y == this.ypos + 12 || this.y == this.ypos + 13 || this.y == this.ypos + 14 || this.y == this.ypos + 15 || this.y == this.ypos + 16 || this.y == this.ypos + 17 || this.y == this.ypos + 18 || this.y == this.ypos + 19 || this.y == this.ypos + 20 || this.y == this.ypos + 21 || this.y == this.ypos + 22 || this.y == this.ypos + 23 || this.y == this.ypos + 24 || this.y == this.ypos + 25 || this.y == this.ypos + 26 || this.y == this.ypos + 27 || this.y == this.ypos + 28 || this.y == this.ypos + 29 || this.y == this.ypos + 30) && (this.xpos == 50 || this.xpos == 49 || this.xpos == 48 || this.xpos == 47 || this.xpos == 46 || this.xpos == 45)) {
                ++this.score1;
                this.s1(this.xpos, this.y);
            }
            this.repeat();
            this.xpos -= 2;
            this.y -= 2;
        }
    }
    
    public void s5(final int xpos, final int y) {
        this.xpos = xpos;
        this.y = y;
        while (this.y >= 0) {
            if (this.y == 5 || this.y == 4) {
                this.k += 5;
                this.s(this.xpos, this.y);
            }
            if (this.xpos == this.size().width - 10 || this.xpos == this.size().width - 9) {
                this.s4(this.xpos, this.y);
            }
            if ((this.y == this.y1pos - 4 || this.y == this.y1pos - 2 || this.y == this.y1pos - 3 || this.y == this.y1pos - 1 || this.y == this.y1pos + 1 || this.y == this.y1pos + 2 || this.y == this.y1pos + 3 || this.y == this.y1pos + 4 || this.y == this.y1pos + 5 || this.y == this.y1pos + 6 || this.y == this.y1pos + 7 || this.y == this.y1pos + 8 || this.y == this.y1pos + 9 || this.y == this.y1pos + 10 || this.y == this.y1pos + 11 || this.y == this.y1pos + 12 || this.y == this.y1pos + 13 || this.y == this.y1pos + 15 || this.y == this.y1pos + 16 || this.y == this.y1pos + 17 || this.y == this.y1pos + 18 || this.y == this.y1pos + 19 || this.y == this.y1pos + 20 || this.y == this.y1pos + 21 || this.y == this.y1pos + 22 || this.y == this.y1pos + 23 || this.y == this.y1pos + 24 || this.y == this.y1pos + 25 || this.y == this.y1pos + 26 || this.y == this.y1pos + 27 || this.y == this.y1pos + 28 || this.y == this.y1pos + 29 || this.y == this.y1pos + 30) && (this.xpos == 244 || this.xpos == 245 || this.xpos == 246 || this.xpos == 247 || this.xpos == 248 || this.xpos == 249 || this.xpos == 250)) {
                ++this.score2;
                this.s2(this.xpos, this.y);
            }
            this.repeat();
            this.xpos += 2;
            this.y -= 2;
        }
    }
    
    public void repeat() {
        this.repaint();
        this.pause(20);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1005: {
                if (this.ypos >= this.size().height - 30) {
                    this.ypos = this.size().height - 30;
                    break;
                }
                this.ypos += 3;
                break;
            }
            case 1004: {
                if (this.ypos <= 0) {
                    this.ypos = 0;
                    break;
                }
                this.ypos -= 3;
                break;
            }
            case 1006: {
                if (this.y1pos >= this.size().height - 30) {
                    this.y1pos = this.size().height - 30;
                    break;
                }
                this.y1pos += 3;
                break;
            }
            case 1007: {
                if (this.y1pos <= 0) {
                    this.y1pos = 0;
                    break;
                }
                this.y1pos -= 3;
                break;
            }
            default: {
                if (n == 108) {
                    this.flag = 1;
                    break;
                }
                this.flag = 0;
                break;
            }
        }
        this.repaint();
        return true;
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(50, this.ypos, 5, 30);
        graphics.setColor(Color.blue);
        graphics.fillRect(250, this.y1pos, 5, 30);
        graphics.setColor(Color.yellow);
        graphics.fillOval(this.xpos, this.y, 10, 10);
        graphics.setColor(Color.white);
        graphics.drawString("PlayerA: " + this.score1, 10, 10);
        graphics.drawString("PlayerB: " + this.score2, 220, 10);
    }
    
    public ping2() {
        this.ypos = 100;
        this.y1pos = 100;
    }
}
