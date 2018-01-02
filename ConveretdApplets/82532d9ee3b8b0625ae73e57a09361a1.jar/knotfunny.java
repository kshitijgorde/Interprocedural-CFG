import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class knotfunny extends Applet implements Runnable
{
    Thread t;
    Image bi;
    Graphics bg;
    boolean sa;
    Random r;
    final Color whi;
    final Color bla;
    final Color red;
    final Color gra;
    int i;
    int j;
    int x;
    int y;
    int ei;
    int ej;
    int ex;
    int ey;
    int mod;
    int[][] kfpp;
    int vo;
    int btx;
    int bty;
    int bbx;
    int bby;
    int cx;
    int cy;
    int dkf;
    
    public void init() {
        this.t = new Thread(this);
        this.bi = this.createImage(245, 180);
        this.bg = this.bi.getGraphics();
        this.r = new Random();
        this.kfpp = new int[4][4];
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 4) {
                this.kfpp[this.i][this.j] = this.i + this.j * 4;
                ++this.j;
            }
            ++this.i;
        }
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                knotfunny.this.mod = mouseEvent.getModifiers();
                knotfunny.this.ex = mouseEvent.getX();
                knotfunny.this.ey = mouseEvent.getY();
                if (knotfunny.this.ex > 180 && knotfunny.this.ex < 200 && knotfunny.this.ey > 30 && knotfunny.this.ey < 50) {
                    knotfunny.this.moveClockwise();
                    return;
                }
                if (knotfunny.this.ex > 210 && knotfunny.this.ex < 230 && knotfunny.this.ey > 30 && knotfunny.this.ey < 50) {
                    knotfunny.this.moveAntiClockwise();
                    return;
                }
                if (knotfunny.this.ex > 180 && knotfunny.this.ex < 230 && knotfunny.this.ey > 80 && knotfunny.this.ey < 100) {
                    knotfunny.this.scramble();
                    return;
                }
                if (knotfunny.this.ex > 180 && knotfunny.this.ex < 230 && knotfunny.this.ey > 120 && knotfunny.this.ey < 140) {
                    knotfunny.this.resetBoard();
                    return;
                }
                knotfunny.this.ei = 0;
                while (knotfunny.this.ei < 4) {
                    knotfunny.this.ej = 0;
                    while (knotfunny.this.ej < 4) {
                        if (knotfunny.this.ex > 20 + knotfunny.this.ei * 40 && knotfunny.this.ex < 40 + knotfunny.this.ei * 40 && knotfunny.this.ey > 20 + knotfunny.this.ej * 40 && knotfunny.this.ey < 40 + knotfunny.this.ej * 40) {
                            if (knotfunny.this.mod == 16 && knotfunny.this.ei < knotfunny.this.bbx && knotfunny.this.ej < knotfunny.this.bby) {
                                knotfunny.this.btx = knotfunny.this.ei;
                                knotfunny.this.bty = knotfunny.this.ej;
                            }
                            if (knotfunny.this.mod == 4 && knotfunny.this.ei > knotfunny.this.btx && knotfunny.this.ej > knotfunny.this.bty) {
                                knotfunny.this.bbx = knotfunny.this.ei;
                                knotfunny.this.bby = knotfunny.this.ej;
                                break;
                            }
                            break;
                        }
                        else {
                            final knotfunny this$knotfunny = knotfunny.this;
                            ++this$knotfunny.ej;
                        }
                    }
                    final knotfunny this$knotfunny2 = knotfunny.this;
                    ++this$knotfunny2.ei;
                }
            }
        });
        this.btx = 0;
        this.bty = 0;
        this.bbx = 3;
        this.bby = 3;
    }
    
    public void moveAntiClockwise() {
        this.dkf = this.kfpp[this.btx][this.bty];
        this.cx = this.btx;
        this.cy = this.bty;
        while (this.cx++ < this.bbx) {
            this.kfpp[this.cx - 1][this.cy] = this.kfpp[this.cx][this.cy];
        }
        --this.cx;
        while (this.cy++ < this.bby) {
            this.kfpp[this.cx][this.cy - 1] = this.kfpp[this.cx][this.cy];
        }
        --this.cy;
        while (this.cx-- > this.btx) {
            this.kfpp[this.cx + 1][this.cy] = this.kfpp[this.cx][this.cy];
        }
        ++this.cx;
        while (this.cy-- > this.bty + 1) {
            this.kfpp[this.cx][this.cy + 1] = this.kfpp[this.cx][this.cy];
        }
        this.kfpp[this.cx][this.cy + 1] = this.dkf;
    }
    
    public void moveClockwise() {
        this.dkf = this.kfpp[this.btx][this.bty];
        this.cx = this.btx;
        this.cy = this.bty;
        while (this.cy++ < this.bby) {
            this.kfpp[this.cx][this.cy - 1] = this.kfpp[this.cx][this.cy];
        }
        --this.cy;
        while (this.cx++ < this.bbx) {
            this.kfpp[this.cx - 1][this.cy] = this.kfpp[this.cx][this.cy];
        }
        --this.cx;
        while (this.cy-- > this.bty) {
            this.kfpp[this.cx][this.cy + 1] = this.kfpp[this.cx][this.cy];
        }
        ++this.cy;
        while (this.cx-- > this.btx + 1) {
            this.kfpp[this.cx + 1][this.cy] = this.kfpp[this.cx][this.cy];
        }
        this.kfpp[this.cx + 1][this.cy] = this.dkf;
    }
    
    public void scramble() {
        this.i = 0;
        while (this.i < 500) {
            this.btx = Math.abs(this.r.nextInt() % 3);
            this.bty = Math.abs(this.r.nextInt() % 3);
            this.bbx = Math.abs(this.r.nextInt() % 3) + 1;
            this.bby = Math.abs(this.r.nextInt() % 3) + 1;
            while (this.btx >= this.bbx || this.bty >= this.bby) {
                this.btx = Math.abs(this.r.nextInt() % 3);
                this.bty = Math.abs(this.r.nextInt() % 3);
                this.bbx = Math.abs(this.r.nextInt() % 3) + 1;
                this.bby = Math.abs(this.r.nextInt() % 3) + 1;
            }
            if (this.r.nextInt() % 2 == 0) {
                this.moveClockwise();
            }
            else {
                this.moveAntiClockwise();
            }
            ++this.i;
        }
        this.btx = Math.abs(this.r.nextInt() % 3);
        this.bty = Math.abs(this.r.nextInt() % 3);
        this.bbx = Math.abs(this.r.nextInt() % 3) + 1;
        this.bby = Math.abs(this.r.nextInt() % 3) + 1;
        while (this.btx >= this.bbx || this.bty >= this.bby) {
            this.btx = Math.abs(this.r.nextInt() % 3);
            this.bty = Math.abs(this.r.nextInt() % 3);
            this.bbx = Math.abs(this.r.nextInt() % 3) + 1;
            this.bby = Math.abs(this.r.nextInt() % 3) + 1;
        }
    }
    
    public void resetBoard() {
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 4) {
                this.kfpp[this.i][this.j] = this.i + this.j * 4;
                ++this.j;
            }
            ++this.i;
        }
    }
    
    public void start() {
        this.sa = true;
        this.t.start();
    }
    
    public void stop() {
        this.sa = false;
        this.t.stop();
    }
    
    public void run() {
        while (this.sa) {
            this.repaint();
            try {
                Thread.sleep(30L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.bg.setColor(this.bla);
        this.bg.fillRect(0, 0, 245, 180);
        this.bg.setColor(this.whi);
        this.x = 0;
        while (this.x < 4) {
            this.y = 0;
            while (this.y < 4) {
                if (this.kfpp[this.x][this.y] > 9) {
                    this.vo = -3;
                }
                else {
                    this.vo = 0;
                }
                this.bg.drawRect(20 + this.x * 40, 20 + this.y * 40, 20, 20);
                this.bg.drawString(String.valueOf(this.kfpp[this.x][this.y]), 27 + this.vo + this.x * 40, 35 + this.y * 40);
                ++this.y;
            }
            ++this.x;
        }
        this.bg.setColor(this.red);
        this.bg.drawRect(15 + this.btx * 40, 15 + this.bty * 40, 30 + (this.bbx - this.btx) * 40, 30 + (this.bby - this.bty) * 40);
        this.bg.setColor(this.gra);
        this.bg.fillRect(180, 30, 20, 20);
        this.bg.fillRect(210, 30, 20, 20);
        this.bg.fillRect(180, 80, 50, 20);
        this.bg.fillRect(180, 120, 50, 20);
        this.bg.setColor(this.bla);
        this.bg.drawString("-->", 185, 45);
        this.bg.drawString("<--", 215, 45);
        this.bg.drawString("Scramble", 183, 95);
        this.bg.drawString("Reset", 190, 135);
        graphics.drawImage(this.bi, 0, 0, this);
    }
    
    public knotfunny() {
        this.whi = Color.white;
        this.bla = Color.black;
        this.red = Color.red;
        this.gra = Color.lightGray;
    }
}
