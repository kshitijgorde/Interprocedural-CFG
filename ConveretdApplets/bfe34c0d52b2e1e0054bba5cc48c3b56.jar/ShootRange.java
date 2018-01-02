import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ShootRange extends Canvas implements MouseListener, Runnable
{
    int mousex;
    int mousey;
    int Xsize;
    int Ysize;
    int birdmax;
    int score;
    int level;
    int bullets;
    int waittime;
    double blast;
    double[] birdx;
    double[] birdy;
    double[] birdvx;
    double[] birdvy;
    int[] birdstart;
    int[] birddirn;
    boolean wait;
    int fire;
    int shot;
    boolean gameover;
    Cursor cursor;
    Image Bird;
    Image Blast;
    Image Hit;
    Thread thisthread;
    AudioClip Shot_AC;
    AudioClip Hit_AC;
    AudioClip Empty_AC;
    
    ShootRange(final Image bird, final Image blast, final Image hit, final AudioClip shot_AC, final AudioClip hit_AC, final AudioClip empty_AC, final int xsize, final int ysize) {
        this.birdmax = 10;
        this.blast = 15.0;
        this.birdx = new double[this.birdmax];
        this.birdy = new double[this.birdmax];
        this.birdvx = new double[this.birdmax];
        this.birdvy = new double[this.birdmax];
        this.birdstart = new int[this.birdmax];
        this.birddirn = new int[this.birdmax];
        this.wait = false;
        this.fire = -1;
        this.shot = -1;
        this.gameover = true;
        this.Xsize = xsize;
        this.Ysize = ysize;
        this.addMouseListener(this);
        this.setSize(this.Xsize, this.Ysize);
        this.setBackground(Color.cyan);
        this.setCursor(Cursor.getPredefinedCursor(1));
        this.mousex = 0;
        this.mousey = 0;
        this.Bird = bird;
        this.Blast = blast;
        this.Hit = hit;
        this.Shot_AC = shot_AC;
        this.Hit_AC = hit_AC;
        this.Empty_AC = empty_AC;
        this.startpositions();
        (this.thisthread = new Thread(this)).start();
    }
    
    void startpositions() {
        this.waittime = 40;
        this.score = 0;
        this.bullets = 12;
        this.level = 0;
    }
    
    boolean checkhit(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (Math.sqrt(Math.pow(n - n3, 2.0) + Math.pow(n2 - n4, 2.0)) <= this.blast) {
            this.birdx[n5] = -1.0;
            this.birdy[n5] = -1.0;
            this.birdvx[n5] = -1.0;
            this.birdvy[n5] = -1.0;
            this.birdstart[n5] = 500;
            return true;
        }
        return false;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        graphics2.setColor(this.getForeground());
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
        image.flush();
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.birdmax; ++i) {
            if (this.birdx[i] != -1.0) {
                graphics.drawImage(this.Bird, (int)this.birdx[i] - 5, (int)this.birdy[i] - 5, this);
            }
        }
        if (this.wait) {
            graphics.drawString("Level " + this.level, 90, 40);
        }
        if (this.gameover) {
            graphics.drawString("Game Over. Click to play", 90, 40);
        }
        if (this.fire >= 0) {
            graphics.drawImage(this.Blast, this.mousex - 15, this.mousey - 15, this);
        }
        if (this.shot >= 0) {
            graphics.drawImage(this.Hit, this.mousex - 15, this.mousey - 15, this);
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 60, this.Ysize);
        graphics.setColor(new Color(189, 198, 0));
        graphics.drawString(String.valueOf(this.score), 10, 20);
        graphics.drawString(String.valueOf(this.level), 10, 40);
        graphics.drawString(String.valueOf(this.bullets), 10, 60);
        graphics.drawString(String.valueOf(8 * this.level), 10, 80);
        graphics.setColor(Color.red);
        graphics.drawString("Score", 10, 10);
        graphics.drawString("Level", 10, 30);
        graphics.drawString("Shells", 10, 50);
        graphics.drawString("Target", 10, 70);
    }
    
    public void run() {
        final double n = 4.5;
        final double n2 = 1.7;
        while (true) {
            if (!this.gameover) {
                for (int i = 0; i < 350; ++i) {
                    if (this.fire >= 0) {
                        --this.fire;
                    }
                    if (this.shot >= 0) {
                        --this.shot;
                    }
                    if (i == 1) {
                        for (int j = 0; j < this.birdmax; ++j) {
                            if (Math.random() > 0.5) {
                                this.birddirn[j] = 1;
                            }
                            else {
                                this.birddirn[j] = -1;
                            }
                            this.birdvx[j] = this.birddirn[j] * (n + 2.0 * Math.random());
                            this.birdvy[j] = n2 + 2.0 * Math.random();
                            this.birdx[j] = (this.Xsize - 60) / 2 - this.birddirn[j] * ((this.Xsize - 60) / 2 + 10) + 60;
                            this.birdy[j] = 70.0;
                            this.birdstart[j] = (int)(300.0 * Math.random());
                        }
                        this.bullets = 12;
                        ++this.level;
                        this.wait = true;
                    }
                    for (int k = 0; k < this.birdmax; ++k) {
                        if (i >= this.birdstart[k]) {
                            if (this.birdy[k] >= this.Ysize) {
                                this.birdstart[k] = 500;
                                this.birdy[k] = this.Ysize + 20;
                            }
                            this.birdvy[k] -= 0.1;
                            this.birdx[k] += this.birdvx[k];
                            this.birdy[k] -= this.birdvy[k];
                        }
                    }
                    if (i == 10) {
                        this.wait = false;
                    }
                    if (i >= 348 && this.score >= 8 * this.level) {
                        i = 0;
                        this.waittime *= (int)0.92;
                    }
                    if (i >= 349) {
                        System.out.println("Game Over");
                        this.gameover = true;
                    }
                    try {
                        Thread.sleep(this.waittime);
                    }
                    catch (Exception ex) {
                        System.out.println("Error on sleep");
                    }
                    this.repaint();
                }
            }
            else {
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex2) {
                    System.out.println("Error on sleep");
                }
                this.repaint();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.gameover) {
            this.gameover = false;
            this.startpositions();
        }
        else {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.mousex = x;
            this.mousey = y;
            if (this.bullets > 0) {
                this.fire = 2;
                this.Shot_AC.play();
                --this.bullets;
                for (int i = 0; i < this.birdmax; ++i) {
                    if (this.checkhit(x, y, (int)this.birdx[i], (int)this.birdy[i], i)) {
                        this.Hit_AC.play();
                        this.shot = 4;
                        ++this.score;
                    }
                }
            }
            else {
                this.Empty_AC.play();
            }
        }
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
