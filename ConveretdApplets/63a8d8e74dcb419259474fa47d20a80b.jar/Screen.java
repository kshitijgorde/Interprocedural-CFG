import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Screen extends Canvas implements MouseListener
{
    int aaa;
    int bbb;
    int level;
    Image wrong;
    Image right;
    Image nlevel;
    Image paused;
    Image gamo;
    Image end;
    int xpo;
    int ypo;
    int offsetx;
    int offsety;
    int levels;
    int[] found;
    int[] xpos;
    int[] ypos;
    int klop;
    boolean succes;
    int[] fault;
    int faulttemp;
    URL url;
    boolean load;
    Image[] img;
    int pp;
    int kk;
    private Finditapplet fa;
    private Findit frame;
    
    public Screen(final Finditapplet fa, final Findit frame) {
        this.aaa = 1;
        this.bbb = 1;
        this.level = 1;
        this.offsetx = 50;
        this.offsety = 50;
        this.xpos = new int[5];
        this.ypos = new int[5];
        this.succes = false;
        this.fault = new int[10];
        this.faulttemp = 6;
        this.img = new Image[20];
        this.fa = fa;
        this.load = true;
        this.repaint();
        this.frame = frame;
        this.vervol(this.level);
        this.addMouseListener(this);
        this.levels = Integer.parseInt(fa.getParameter("numberoflevels"));
        this.url = fa.getDocumentBase();
        this.fetchimages();
        this.load = false;
    }
    
    public void fetchimages() {
        for (int i = 1; i < 2 * this.levels + 1; i += 2) {
            ++this.kk;
            final String string = "foto" + this.kk + "a";
            this.img[i] = ((this.fa.getParameter(string) == null) ? this.fa.getImage(this.url, "foto1a.jpg") : this.fa.getImage(this.url, this.fa.getParameter(string)));
        }
        for (int j = 2; j < 2 * this.levels + 1; j += 2) {
            ++this.pp;
            final String string2 = "foto" + this.pp + "b";
            this.img[j] = ((this.fa.getParameter(string2) == null) ? this.fa.getImage(this.url, "foto1a.jpg") : this.fa.getImage(this.url, this.fa.getParameter(string2)));
        }
        this.nlevel = this.fa.getImage(this.fa.getDocumentBase(), "nlevel.jpg");
        this.paused = this.fa.getImage(this.fa.getDocumentBase(), "paused.jpg");
        this.gamo = this.fa.getImage(this.fa.getDocumentBase(), "gamo.jpg");
        this.end = this.fa.getImage(this.fa.getDocumentBase(), "end.jpg");
        this.right = this.fa.getImage(this.fa.getDocumentBase(), "right.gif");
        this.wrong = this.fa.getImage(this.fa.getDocumentBase(), "wrong.gif");
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int k = 1; k < 2 * this.levels + 1; ++k) {
            mediaTracker.addImage(this.img[k], 0);
        }
        mediaTracker.addImage(this.nlevel, 0);
        mediaTracker.addImage(this.wrong, 0);
        mediaTracker.addImage(this.right, 0);
        mediaTracker.addImage(this.paused, 0);
        mediaTracker.addImage(this.gamo, 0);
        mediaTracker.addImage(this.end, 0);
        try {
            mediaTracker.waitForAll(0L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void herFill() {
        this.vervol(this.level);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.xpo = mouseEvent.getX();
        this.ypo = mouseEvent.getY();
        this.faulttemp = this.Calculeer(this.xpo, this.ypo);
        if (this.faulttemp != 6) {
            if (this.frame.sound) {
                if (this.bbb % 2 == 1) {
                    this.fa.play(this.fa.getDocumentBase(), "tiptoe.thru.the.tulips.au");
                }
                else {
                    this.fa.play(this.fa.getDocumentBase(), "cannot.be.completed.au");
                }
                ++this.bbb;
            }
            ++this.klop;
            Timeline.score += 50;
            this.fault[this.klop] = this.faulttemp;
        }
        if (this.faulttemp == 6) {
            Timeline.k -= 10;
            if (this.frame.sound) {
                if (this.aaa % 2 == 1) {
                    this.fa.play(this.fa.getDocumentBase(), "adapt-or-die.au");
                }
                else {
                    this.fa.play(this.fa.getDocumentBase(), "that.hurts.au");
                }
                ++this.aaa;
            }
        }
        this.repaint();
        if (this.klop == 5) {
            this.succes = true;
            Timeline.score += Timeline.k;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void paint(final Graphics graphics) {
        if (this.load) {
            graphics.setColor(Color.red);
            graphics.drawString("Loading Pictures", 50, 50);
            return;
        }
        this.levels = Integer.parseInt(this.fa.getParameter("numberoflevels"));
        if (!Timeline.timeup) {
            if (!Timeline.pause) {
                for (int i = 1; i < this.levels + 1; ++i) {
                    if (this.level == i) {
                        graphics.drawImage(this.img[this.level * 2], this.offsetx, this.offsety, null);
                        graphics.drawImage(this.img[this.level * 2 - 1], 310 + this.offsetx, this.offsety, null);
                    }
                }
                if (this.level == this.levels + 1) {
                    graphics.drawImage(this.end, this.offsetx, this.offsety, null);
                    graphics.drawImage(this.end, 310 + this.offsetx, this.offsety, null);
                    Timeline.messageThread.suspend();
                    graphics.setFont(new Font("Impact", 0, 12));
                    graphics.setColor(Color.green);
                    graphics.drawString("Brought to you by DaHazard, DubbleEagle and Luna-Tic", 170, 330);
                    this.frame.next.setLabel("New Game");
                }
                graphics.setFont(new Font("Impact", 0, 10));
                graphics.setColor(Color.red);
                if (this.faulttemp == 6) {
                    graphics.setColor(Color.red);
                    graphics.drawRect(this.xpo, this.ypo, 30, 30);
                }
                for (int j = 1; j < 6; ++j) {
                    for (int k = 0; k < 5; ++k) {
                        if (this.fault[j] == k) {
                            graphics.setColor(Color.green);
                            graphics.drawRect(this.xpos[k], this.ypos[k], 30, 30);
                            graphics.drawRect(this.xpos[k] + 310, this.ypos[k], 30, 30);
                        }
                    }
                }
            }
            else if (Timeline.pause) {
                graphics.setColor(Color.red);
                graphics.drawImage(this.paused, 150 + this.offsetx, 80 + this.offsety, null);
            }
        }
        if (Timeline.timeup) {
            graphics.setColor(Color.red);
            this.frame.next.setLabel("New Game");
            for (int l = 0; l < this.levels + 1; ++l) {
                if (this.level == l) {
                    graphics.drawImage(this.img[2 * l], this.offsetx, this.offsety, null);
                    graphics.drawImage(this.gamo, 350 + this.offsetx, 20 + this.offsety, null);
                }
            }
            for (int n = 0; n < 5; ++n) {
                graphics.setColor(Color.red);
                graphics.drawRect(this.xpos[n], this.ypos[n], 30, 30);
            }
        }
        if (this.succes) {
            graphics.drawImage(this.nlevel, this.offsetx, 80 + this.offsety, null);
            graphics.drawImage(this.nlevel, 310 + this.offsetx, 80 + this.offsety, null);
        }
    }
    
    public void vervol(final int n) {
        this.levels = Integer.parseInt(this.fa.getParameter("numberoflevels"));
        for (int i = 0; i < this.levels + 1; ++i) {
            if (i == n) {
                for (int j = 1; j < 6; ++j) {
                    this.xpos[j - 1] = Integer.parseInt(this.fa.getParameter("foto" + i + "xco" + j)) + this.offsetx;
                }
                for (int k = 1; k < 6; ++k) {
                    this.ypos[k - 1] = Integer.parseInt(this.fa.getParameter("foto" + i + "yco" + k)) + this.offsetx;
                }
            }
        }
    }
    
    public int Calculeer(final int n, final int n2) {
        final int[] array = new int[5];
        int n3 = 6;
        for (int i = 0; i < 5; ++i) {
            if ((n > this.xpos[i] && n < this.xpos[i] + 30 && n2 > this.ypos[i] && n2 < this.ypos[i] + 30 && array[i] == 0) || (n > this.xpos[i] + 310 && n < this.xpos[i] + 340 && n2 > this.ypos[i] && n2 < this.ypos[i] + 30 && array[i] == 0)) {
                n3 = i;
                array[i] = 1;
            }
        }
        return n3;
    }
}
