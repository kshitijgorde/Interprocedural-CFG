import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class type_writer extends Applet implements Runnable
{
    Thread runner;
    int dt;
    int bgcolor;
    int fgcolor;
    String bgcs;
    String fgcs;
    String infostr;
    Color bgrenk;
    Color fgrenk;
    FontMetrics fmetrics;
    Font nf;
    String fonttipi;
    String bosluk;
    int yaziboyu;
    int genislik;
    int yukseklik;
    int i;
    int j;
    int adet;
    int kul;
    int k;
    int basilanpixel;
    int pd;
    int ud;
    int rd;
    int x;
    int y;
    int l;
    int yboyu;
    int ara;
    int tyd;
    String[][] kelimeler;
    int[][] metrik;
    String[] paragraf;
    int[] paragraftakisatirsayisi;
    int bos;
    int link;
    int bk;
    int mdo;
    int ha;
    int fontstyle;
    int[] paragraftakikelimesayisi;
    char[][][] harfler;
    String[][] satir;
    int[][] satirgenisligi;
    Image img;
    Graphics offScreeng;
    Color c1;
    Color c2;
    Color c3;
    boolean ilk;
    
    public void init() {
        this.bgcs = this.getParameter("bgcolor");
        try {
            this.bgcolor = Integer.parseInt(this.bgcs, 16);
        }
        catch (Exception ex) {
            this.bgcolor = 0;
        }
        this.setBackground(this.bgrenk = new Color(this.bgcolor));
        this.show();
        this.showStatus("");
        this.dt = Integer.valueOf(this.getParameter("scroll_delay"));
        this.tyd = Integer.valueOf(this.getParameter("type_delay"));
        this.fgcs = this.getParameter("fgcolor");
        this.infostr = this.getParameter("info");
        try {
            this.fgcolor = Integer.parseInt(this.fgcs, 16);
        }
        catch (Exception ex2) {
            this.fgcolor = 16777215;
        }
        this.setForeground(this.fgrenk = new Color(this.fgcolor));
        this.adet = 0;
        this.x = 5;
        this.ara = Integer.valueOf(this.getParameter("line_height"));
        this.yaziboyu = Integer.valueOf(this.getParameter("font_size"));
        this.fonttipi = this.getParameter("font_type");
        try {
            this.fontstyle = Integer.parseInt(this.getParameter("font_style"), 10);
        }
        catch (Exception ex3) {
            this.fontstyle = 0;
        }
        this.nf = new Font(this.fonttipi, this.fontstyle, this.yaziboyu);
        this.fmetrics = this.getFontMetrics(this.nf);
        this.genislik = this.size().width;
        this.yukseklik = this.size().height;
        this.kul = this.genislik - 5;
        this.adet = Integer.valueOf(this.getParameter("paragraph_count"));
        this.paragraf = new String[this.adet + 1];
        this.satir = new String[this.adet + 1][50];
        this.kelimeler = new String[this.adet + 1][150];
        this.metrik = new int[this.adet + 1][50];
        this.satirgenisligi = new int[this.adet + 1][50];
        this.paragraftakikelimesayisi = new int[this.adet + 1];
        this.harfler = new char[this.adet + 1][50][50];
        this.paragraftakisatirsayisi = new int[this.adet + 1];
        this.i = 1;
        while (this.i <= this.adet) {
            if (this.infostr.equals("Applet by Gokhan Dagli,freeapplet.tripod.com")) {
                this.paragraf[this.i] = this.getParameter("parag" + this.i);
                if (this.paragraf[this.i] == null) {
                    this.paragraf[this.i] = "\t";
                }
            }
            else {
                this.paragraf[this.i] = "Warning ! info parameter error!";
            }
            ++this.i;
        }
        this.i = 1;
        while (this.i <= this.adet) {
            this.j = 1;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.paragraf[this.i], " ");
            while (stringTokenizer.hasMoreTokens()) {
                this.kelimeler[this.i][this.j] = stringTokenizer.nextToken();
                this.metrik[this.i][this.j] = this.fmetrics.stringWidth(this.kelimeler[this.i][this.j]);
                ++this.j;
            }
            --this.j;
            this.paragraftakikelimesayisi[this.i] = this.j;
            ++this.i;
        }
        this.bos = this.fmetrics.stringWidth(this.bosluk);
        this.i = 1;
        while (this.i <= this.adet) {
            this.k = 1;
            this.j = 1;
            this.satir[this.i][this.k] = this.kelimeler[this.i][this.j];
            this.satirgenisligi[this.i][this.k] += this.metrik[this.i][this.j];
            while (true) {
                if (this.satirgenisligi[this.i][this.k] + this.bos + this.metrik[this.i][this.j + 1] <= this.kul) {
                    ++this.j;
                    if (this.paragraftakikelimesayisi[this.i] < this.j) {
                        this.paragraftakisatirsayisi[this.i] = this.k;
                        break;
                    }
                    this.satir[this.i][this.k] = this.satir[this.i][this.k] + " " + this.kelimeler[this.i][this.j];
                    this.satirgenisligi[this.i][this.k] = this.satirgenisligi[this.i][this.k] + this.bos + this.metrik[this.i][this.j];
                }
                else {
                    ++this.k;
                    ++this.j;
                    if (this.paragraftakikelimesayisi[this.i] < this.j) {
                        this.paragraftakisatirsayisi[this.i] = this.k;
                        break;
                    }
                    this.satir[this.i][this.k] = this.kelimeler[this.i][this.j];
                    this.satirgenisligi[this.i][this.k] += this.metrik[this.i][this.j];
                }
            }
            ++this.i;
        }
        this.i = 1;
        while (this.i <= this.adet) {
            this.k = 1;
            while (this.k <= this.paragraftakisatirsayisi[this.i]) {
                this.harfler[this.i][this.k] = this.satir[this.i][this.k].toCharArray();
                ++this.k;
            }
            ++this.i;
        }
        this.basilanpixel = this.yukseklik - 20;
        this.y = this.basilanpixel;
        this.img = this.createImage(this.genislik, this.yukseklik);
        (this.offScreeng = this.img.getGraphics()).setFont(this.nf);
        this.setFont(this.nf);
        this.i = 1;
        this.j = 0;
        this.yboyu = this.fmetrics.getAscent() + this.fmetrics.getDescent();
        this.c2 = this.bgrenk;
        this.c1 = this.fgrenk;
        System.out.println("http://freeapplet.tripod.com");
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.img, 0, 0, this);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        this.ilk = false;
        this.i = 1;
        this.j = 0;
        while (true) {
            if (!this.ilk) {
                this.x = 5;
                this.offScreeng.setColor(this.c2);
                this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
                this.repaint();
                this.ilk = true;
            }
            ++this.j;
            if (this.j > this.paragraftakisatirsayisi[this.i]) {
                this.pd = 1;
                while (this.pd <= this.yboyu) {
                    this.repaint();
                    this.offScreeng.copyArea(0, 0, this.genislik, this.yukseklik, 0, -1);
                    try {
                        Thread.currentThread();
                        Thread.sleep(this.dt);
                    }
                    catch (InterruptedException ex) {}
                    ++this.pd;
                }
                this.j = 1;
                ++this.i;
                if (this.i > this.adet) {
                    this.i = 1;
                }
            }
            this.offScreeng.setFont(this.nf);
            this.offScreeng.setColor(this.c1);
            this.l = this.harfler[this.i][this.j].length;
            this.pd = 0;
            while (this.pd <= this.l) {
                this.offScreeng.drawChars(this.harfler[this.i][this.j], 0, this.pd, this.x, this.y);
                this.repaint();
                try {
                    Thread.currentThread();
                    Thread.sleep(this.tyd);
                }
                catch (InterruptedException ex2) {}
                ++this.pd;
            }
            this.pd = 1;
            while (this.pd <= this.ara) {
                this.offScreeng.copyArea(0, 0, this.genislik, this.yukseklik, 0, -1);
                this.repaint();
                try {
                    Thread.currentThread();
                    Thread.sleep(this.dt);
                }
                catch (InterruptedException ex3) {}
                ++this.pd;
            }
            this.repaint();
        }
    }
    
    public type_writer() {
        this.bgcs = "";
        this.fgcs = "";
        this.infostr = "";
        this.bosluk = " ";
        this.ilk = false;
    }
}
