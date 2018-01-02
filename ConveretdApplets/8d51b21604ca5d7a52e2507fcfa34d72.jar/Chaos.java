import java.awt.image.ImageObserver;
import java.awt.Event;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Chaos extends Applet implements Runnable
{
    int movingDots;
    int frameNumber;
    int objectCount;
    int delay;
    int currentKey;
    int Score;
    int Lives;
    Thread animatorThread;
    boolean frozen;
    int[] cosi;
    int[] sini;
    int[] raumy;
    int[] raumx;
    int exp;
    int exp2;
    shoot[] schuss;
    optik[] backp;
    int curs;
    int curback;
    int[] raumdx;
    int[] raumdy;
    int px;
    int py;
    int vx;
    int vy;
    int rot;
    Image offImage;
    Color eineF;
    Graphics offGraphics;
    einObjekt[] testoval;
    
    public void init() {
        if (this.offGraphics == null) {
            this.offImage = this.createImage(320, 256);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.delay = 60;
        this.testoval[0] = new einObjekt();
        for (int i = 0; i < 360; ++i) {
            this.cosi[i] = (int)(Math.cos(i * 3.1415 / 180.0) * 1024.0);
            this.sini[i] = (int)(Math.sin(i * 3.1415 / 180.0) * 1024.0);
        }
        for (int j = 0; j < 4; ++j) {
            this.schuss[j] = new shoot();
            this.schuss[j].live = 0;
        }
        for (int k = 0; k <= this.movingDots; ++k) {
            this.backp[k] = new optik();
            this.backp[k].x = 0;
            this.backp[k].y = 0;
            this.backp[k].ix = 0;
            this.backp[k].iy = 0;
            this.backp[k].flimer = 0;
        }
        this.level();
        this.Score = 0;
        this.Lives = 0;
    }
    
    public void start() {
        (this.animatorThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.animatorThread = null;
    }
    
    public void readHigh() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL("http://www.lrz-muenchen.de/~a2823ae/www/test.java").openStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                System.out.println(line);
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        }
        catch (IOException ex2) {
            System.out.println("IOException: " + ex2);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.Lives == 0) {
            if (n == 32) {
                this.Lives = 3;
                this.Score = 0;
                this.objectCount = 0;
                this.level();
            }
        }
        else {
            if (n == 120) {
                this.currentKey |= 0x1;
            }
            if (n == 99) {
                this.currentKey |= 0x2;
            }
            if (n == 103) {
                this.currentKey |= 0x4;
            }
            if (n == 118 && this.schuss[this.curs].live == 0) {
                this.schuss[this.curs].x = this.px;
                this.schuss[this.curs].y = this.py;
                this.schuss[this.curs].ix = this.cosi[this.rot] << 1;
                this.schuss[this.curs].iy = this.sini[this.rot] << 1;
                this.schuss[this.curs].live = 30;
                if (++this.curs > 3) {
                    this.curs = 0;
                }
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (n == 120) {
            this.currentKey &= 0x6;
        }
        if (n == 99) {
            this.currentKey &= 0x5;
        }
        if (n == 103) {
            this.currentKey &= 0x3;
        }
        return true;
    }
    
    public void level() {
        for (int i = 0; i < 10; ++i) {
            this.neuer(this.rnd(320), this.rnd(10), 24);
        }
        this.px = 40960;
        this.py = 40960;
        this.vx = 0;
        this.vy = 0;
    }
    
    public void neuer(final int x, final int y, final int n) {
        int n2;
        for (n2 = 0; n2 < this.objectCount && this.testoval[n2].x >= -100; ++n2) {}
        this.testoval[n2] = new einObjekt();
        this.testoval[n2].x = x;
        this.testoval[n2].y = y;
        this.testoval[n2].ix = this.rnd(6) - 3;
        this.testoval[n2].iy = this.rnd(6) - 3;
        this.testoval[n2].farbe = new Color(this.rnd(255), this.rnd(255), this.rnd(255));
        this.testoval[n2].s = this.rnd(n) + 10;
        if (n2 == this.objectCount) {
            this.objectCount = n2 + 1;
        }
    }
    
    public boolean collision(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n4 - n;
        final int n8 = n5 - n2;
        return Math.sqrt(n7 * n7 + n8 * n8) < n3 + n6;
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.animatorThread) {
            ++this.frameNumber;
            boolean b = true;
            for (int i = 0; i < this.objectCount; ++i) {
                if (this.testoval[i].x > -100) {
                    b = false;
                    final einObjekt einObjekt = this.testoval[i];
                    einObjekt.x += this.testoval[i].ix;
                    final einObjekt einObjekt2 = this.testoval[i];
                    einObjekt2.y += this.testoval[i].iy;
                    if (this.testoval[i].x > 320 || this.testoval[i].x < 0) {
                        this.testoval[i].ix = -this.testoval[i].ix;
                    }
                    if (this.testoval[i].y > 256 || this.testoval[i].y < 0) {
                        this.testoval[i].iy = -this.testoval[i].iy;
                    }
                    final int x = this.testoval[i].x;
                    final int y = this.testoval[i].y;
                    final int n = this.testoval[i].s >> 1;
                    if (this.collision(x, y, n, this.px >> 8, this.py >> 8, 8)) {
                        if (this.Lives > 0) {
                            --this.Lives;
                        }
                        int n2 = 0;
                        for (int j = 0; j < 72; ++j) {
                            this.backp[this.curback].x = this.px;
                            this.backp[this.curback].y = this.py;
                            this.backp[this.curback].ix = -this.cosi[n2] + this.rnd(300) - 150;
                            this.backp[this.curback].iy = -this.sini[n2] + this.rnd(300) - 150;
                            this.backp[this.curback].farbe = new Color(this.rnd(255), this.rnd(255), this.rnd(255));
                            n2 += 5;
                            this.curback = ((this.curback >= this.movingDots) ? 0 : (this.curback + 1));
                        }
                        this.objectCount = 0;
                        this.level();
                    }
                    for (int k = 0; k < 4; ++k) {
                        if (this.schuss[k].live != 0 && this.collision(x, y, this.testoval[i].s >> 1, this.schuss[k].x >> 8, this.schuss[k].y >> 8, 1)) {
                            if (this.testoval[i].s >= 20) {
                                for (int l = 0; l < n; l += 8) {
                                    this.neuer(this.testoval[i].x, this.testoval[i].y, 8);
                                }
                                final einObjekt einObjekt3 = this.testoval[i];
                                einObjekt3.s -= 8;
                            }
                            else {
                                this.testoval[i].x = -900;
                            }
                            int n3 = 0;
                            ++this.exp;
                            if (this.rnd(2) == 1) {
                                ++this.exp2;
                            }
                            int n4 = 1;
                            if (this.rnd(7) == 1) {
                                n4 = 3;
                            }
                            this.eineF = new Color(this.rnd(255), this.rnd(255), this.rnd(255));
                            for (int n5 = 0; n5 < 72; ++n5) {
                                this.backp[this.curback].x = (x << 8) - (this.cosi[n3] >> 1);
                                this.backp[this.curback].y = (y << 8) - (this.sini[n3] >> 1);
                                this.backp[this.curback].ix = -this.cosi[n3] + (this.sini[n3 * this.exp % 360] >> 1) + (this.cosi[n3 * this.exp2 % 360] >> 2);
                                this.backp[this.curback].iy = -this.sini[n3] + (this.cosi[n3 * this.exp % 360] >> 1) + (this.sini[n3 * this.exp2 % 360] >> 2);
                                this.backp[this.curback].farbe = this.eineF;
                                this.backp[this.curback].flimer = this.rnd(n4) - 1;
                                n3 += 5;
                                this.curback = ((this.curback >= this.movingDots) ? 0 : (this.curback + 1));
                            }
                            this.schuss[k].live = 0;
                            this.Score += 7;
                        }
                    }
                }
            }
            for (int n6 = 0; n6 < 4; ++n6) {
                if (this.schuss[n6].live != 0) {
                    final shoot shoot = this.schuss[n6];
                    --shoot.live;
                    final shoot shoot2 = this.schuss[n6];
                    shoot2.x += this.schuss[n6].ix;
                    final shoot shoot3 = this.schuss[n6];
                    shoot3.y += this.schuss[n6].iy;
                    if (this.schuss[n6].x > 81920 || this.schuss[n6].x < 0) {
                        this.schuss[n6].ix = -this.schuss[n6].ix;
                    }
                    if (this.schuss[n6].y > 65536 || this.schuss[n6].y < 0) {
                        this.schuss[n6].iy = -this.schuss[n6].iy;
                    }
                }
            }
            if ((this.currentKey & 0x1) == 0x1) {
                this.rot -= 16;
            }
            if ((this.currentKey & 0x2) == 0x2) {
                this.rot += 16;
            }
            if (this.rot > 359) {
                this.rot -= 360;
            }
            if (this.rot < 0) {
                this.rot += 360;
            }
            if ((this.currentKey & 0x4) == 0x4) {
                this.vx += this.cosi[this.rot] >> 3;
                this.vy += this.sini[this.rot] >> 3;
                for (int n7 = 0; n7 < 5; ++n7) {
                    this.backp[this.curback].x = this.px - (this.cosi[this.rot] >> 1);
                    this.backp[this.curback].y = this.py - (this.sini[this.rot] >> 1);
                    this.backp[this.curback].ix = -this.cosi[this.rot] + this.rnd(300) - 150;
                    this.backp[this.curback].iy = -this.sini[this.rot] + this.rnd(300) - 150;
                    this.backp[this.curback].farbe = new Color(this.rnd(200) + 50, 0, 0);
                    this.backp[this.curback].flimer = 1;
                    this.curback = ((this.curback >= this.movingDots) ? 0 : (this.curback + 1));
                }
            }
            this.transform();
            this.repaint();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                return;
            }
            if (b) {
                this.level();
            }
        }
    }
    
    public int rnd(final int n) {
        return (int)(Math.random() * n) + 1;
    }
    
    public void transform() {
        this.px += this.vx;
        this.py += this.vy;
        if (this.px > 81920 || this.px < 0) {
            this.vx = -this.vx;
        }
        if (this.py > 65536 || this.py < 0) {
            this.vy = -this.vy;
        }
        for (int i = 0; i < 4; ++i) {
            this.raumdx[i] = (this.raumx[i] * this.cosi[this.rot] + this.raumy[i] * this.sini[this.rot] >> 10) + (this.px >> 8);
            this.raumdy[i] = (this.raumx[i] * this.sini[this.rot] - this.raumy[i] * this.cosi[this.rot] >> 10) + (this.py >> 8);
        }
    }
    
    public void update(final Graphics graphics) {
        this.offGraphics.setColor(Color.black);
        this.offGraphics.fillRect(0, 0, 320, 256);
        this.offGraphics.setColor(Color.white);
        this.offGraphics.drawString("Score: " + this.Score, 0, 10);
        this.offGraphics.drawString("Lives: " + this.Lives, 270, 10);
        this.offGraphics.setColor(Color.green.darker());
        for (int i = 0; i <= this.movingDots; ++i) {
            final optik optik = this.backp[i];
            optik.x += this.backp[i].ix;
            final optik optik2 = this.backp[i];
            optik2.y += this.backp[i].iy;
            if (this.backp[i].flimer > 0) {
                final optik optik3 = this.backp[i];
                ++optik3.flimer;
            }
            if ((this.backp[i].flimer & 0x1) == 0x1) {
                this.offGraphics.setColor(Color.white);
            }
            else {
                this.offGraphics.setColor(this.backp[i].farbe);
            }
            this.offGraphics.drawRect(this.backp[i].x >> 8, this.backp[i].y >> 8, 1, 1);
        }
        for (int j = 0; j < this.objectCount; ++j) {
            this.offGraphics.setColor(this.testoval[j].farbe);
            final int s = this.testoval[j].s;
            this.offGraphics.fillOval(this.testoval[j].x - (s >> 1), this.testoval[j].y - (s >> 1), s, s);
        }
        this.offGraphics.setColor(Color.white);
        for (int k = 0; k < 4; ++k) {
            if (this.schuss[k].live > 0) {
                this.offGraphics.drawRect(this.schuss[k].x >> 8, this.schuss[k].y >> 8, 1, 1);
            }
        }
        this.offGraphics.fillPolygon(this.raumdx, this.raumdy, 4);
        this.offGraphics.setColor(Color.blue);
        this.offGraphics.drawRect(this.px >> 8, this.py >> 8, 1, 1);
        this.offGraphics.setColor(Color.white);
        if (this.Lives == 0) {
            this.offGraphics.drawString("Game Over", 130, 120);
            this.offGraphics.drawString("Press 'Space' to Start", 100, 200);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public Chaos() {
        this.movingDots = 250;
        this.frameNumber = -1;
        this.frozen = false;
        this.cosi = new int[360];
        this.sini = new int[360];
        this.raumy = new int[] { -5, 0, 5, 0 };
        this.raumx = new int[] { -8, -5, -8, 8 };
        this.exp = 4;
        this.exp2 = 4;
        this.schuss = new shoot[5];
        this.backp = new optik[this.movingDots + 1];
        this.raumdx = new int[4];
        this.raumdy = new int[4];
        this.px = 50;
        this.py = 50;
        this.vx = 20;
        this.vy = 10;
        this.testoval = new einObjekt[30];
    }
}
