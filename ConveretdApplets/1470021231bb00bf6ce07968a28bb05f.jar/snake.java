import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class snake extends Applet implements Runnable
{
    Image[] dot;
    Image back;
    Image offI;
    Graphics offG;
    int[] x;
    int[] y;
    int rtemp;
    int game;
    int level;
    int z;
    int n;
    int count;
    int score;
    int add;
    Button b;
    Button i;
    Button p;
    Button X;
    String stemp;
    String s;
    String t;
    boolean[] go;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    boolean started;
    boolean me;
    Thread setTime;
    
    public snake() {
        this.dot = new Image[400];
        this.x = new int[400];
        this.y = new int[400];
        this.rtemp = 1;
        this.game = 1;
        this.count = 0;
        this.score = 0;
        this.add = 1;
        this.b = new Button("Beginner");
        this.i = new Button("Intermediate");
        this.p = new Button("Professional");
        this.X = new Button("Xtreamest");
        this.go = new boolean[400];
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.started = false;
        this.me = false;
    }
    
    public boolean action(final Event event, final Object o) {
        this.stemp = (String)o;
        if (this.stemp.equals("Beginner")) {
            this.add = 2;
            this.level = 100;
            (this.setTime = new Thread(this)).start();
            return true;
        }
        if (this.stemp.equals("Intermediate")) {
            this.add = 5;
            this.level = 70;
            (this.setTime = new Thread(this)).start();
            return true;
        }
        if (this.stemp.equals("Professional")) {
            this.add = 10;
            this.level = 40;
            (this.setTime = new Thread(this)).start();
            return true;
        }
        if (this.stemp.equals("Xtreamest")) {
            this.add = 20;
            this.level = 20;
            (this.setTime = new Thread(this)).start();
            return true;
        }
        return false;
    }
    
    public void init() {
        this.add(this.b);
        this.add(this.i);
        this.add(this.p);
        this.add(this.X);
        this.setBackground(Color.black);
        this.back = this.getImage(this.getCodeBase(), "screan.gif");
        this.z = 0;
        while (this.z < 400) {
            this.dot[this.z] = this.getImage(this.getCodeBase(), "dot.gif");
            ++this.z;
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1006 && !this.right) {
            this.left = true;
            this.up = false;
            this.down = false;
            if (!this.started) {
                this.started = true;
            }
        }
        if (n == 1007 && !this.left) {
            this.right = true;
            this.up = false;
            this.down = false;
            if (!this.started) {
                this.started = true;
            }
        }
        if (n == 1004 && !this.down) {
            this.up = true;
            this.right = false;
            this.left = false;
            if (!this.started) {
                this.started = true;
            }
        }
        if (n == 1005 && !this.up) {
            this.down = true;
            this.right = false;
            this.left = false;
            if (!this.started) {
                this.started = true;
            }
        }
        return true;
    }
    
    public void locateRandom(final int n) {
        this.rtemp = (int)(Math.random() * 20.0);
        this.x[n] = this.rtemp * 10 + 1;
        this.rtemp = (int)(Math.random() * 20.0);
        this.y[n] = this.rtemp * 10 + 1;
        ++this.n;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.back, 0, 0, this);
        graphics.setColor(Color.white);
        if (this.started) {
            graphics.setFont(new Font("Verdana", 1, 12));
            graphics.drawString(this.t = "Score " + this.score, 75, 220);
        }
        if (this.game == 1) {
            graphics.setFont(new Font("Verdana", 1, 13));
            graphics.drawString(this.s = "Select Mode", 65, 30);
            this.b.move(75, 50);
            this.i.move(68, 90);
            this.p.move(68, 130);
            this.X.move(73, 170);
        }
        if (this.game == 2 || this.game == 3) {
            if (!this.started) {
                graphics.setFont(new Font("Verdana", 1, 11));
                graphics.drawString(this.t = "Use the key board arrows to move!", 5, 215);
            }
            this.z = 0;
            while (this.z <= this.n) {
                graphics.drawImage(this.dot[this.z], this.x[this.z], this.y[this.z], this);
                ++this.z;
            }
            this.me = true;
        }
        if (!this.me) {
            graphics.setFont(new Font("Verdana", 1, 11));
            graphics.drawString(this.t = "by Omar Wally, http://crash.to/PLAY", 5, 215);
        }
        if (this.game == 3) {
            graphics.setFont(new Font("Verdana", 1, 13));
            graphics.drawString(this.s = "Game Over", 65, 60);
        }
    }
    
    public void run() {
        this.z = 4;
        while (this.z < 400) {
            this.go[this.z] = false;
            ++this.z;
        }
        this.z = 0;
        while (this.z < 4) {
            this.go[this.z] = true;
            this.x[this.z] = 91;
            this.y[this.z] = 91;
            ++this.z;
        }
        this.n = 3;
        this.game = 2;
        this.score = 0;
        this.b.move(70, -100);
        this.i.move(70, -100);
        this.p.move(70, -100);
        this.X.move(70, -100);
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.locateRandom(4);
        while (true) {
            if (this.game == 2) {
                if (this.x[0] == this.x[this.n] && this.y[0] == this.y[this.n]) {
                    this.go[this.n] = true;
                    this.locateRandom(this.n + 1);
                    this.score += this.add;
                }
                this.z = 399;
                while (this.z > 0) {
                    if (this.go[this.z]) {
                        this.x[this.z] = this.x[this.z - 1];
                        this.y[this.z] = this.y[this.z - 1];
                        if (this.z > 4 && this.x[0] == this.x[this.z] && this.y[0] == this.y[this.z]) {
                            this.game = 3;
                        }
                    }
                    --this.z;
                }
                if (this.left) {
                    final int[] x = this.x;
                    final int n = 0;
                    x[n] -= 10;
                }
                if (this.right) {
                    final int[] x2 = this.x;
                    final int n2 = 0;
                    x2[n2] += 10;
                }
                if (this.up) {
                    final int[] y = this.y;
                    final int n3 = 0;
                    y[n3] -= 10;
                }
                if (this.down) {
                    final int[] y2 = this.y;
                    final int n4 = 0;
                    y2[n4] += 10;
                }
            }
            if (this.y[0] > 191) {
                this.y[0] = 191;
                this.game = 3;
            }
            if (this.y[0] < 1) {
                this.y[0] = 1;
                this.game = 3;
            }
            if (this.x[0] > 191) {
                this.x[0] = 191;
                this.game = 3;
            }
            if (this.x[0] < 1) {
                this.x[0] = 1;
                this.game = 3;
            }
            if (this.game == 3) {
                if (this.count < 1500 / this.level) {
                    ++this.count;
                }
                else {
                    this.count = 0;
                    this.game = 1;
                    this.repaint();
                    this.setTime.stop();
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.level);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offI == null) {
            this.offI = this.createImage(size.width, size.height);
            this.offG = this.offI.getGraphics();
        }
        this.offG.clearRect(0, 0, size.width, size.height);
        this.paint(this.offG);
        graphics.drawImage(this.offI, 0, 0, null);
    }
}
