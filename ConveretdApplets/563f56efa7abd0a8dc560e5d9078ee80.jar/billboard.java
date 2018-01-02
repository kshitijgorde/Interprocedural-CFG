import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class billboard extends Applet implements Runnable
{
    Thread runner;
    String bgcolor;
    String target_frame;
    String infostr;
    String image_align;
    String image_valign;
    String effects;
    String custom_effects;
    String regcode;
    String errstr;
    Image[] img;
    Image offimg;
    int bgci;
    int durum;
    int genislik;
    int yukseklik;
    int image_number;
    int don;
    int currimg;
    int hiz;
    int i;
    int j;
    int xx;
    int yy;
    int curreff;
    int jmp;
    int bstep;
    int effnum;
    int effsay;
    int step_period;
    Color bgc;
    MediaTracker tracker;
    Graphics offScreeng;
    URL u;
    String[] links;
    int[] X;
    int[] Y;
    int[] imgw;
    int[] imgh;
    int[] eff;
    int[] cusi;
    boolean devam;
    boolean inf;
    boolean rg;
    
    public void init() {
        this.xx = 0;
        this.yy = 0;
        this.bgcolor = this.getParameter("bgcolor");
        try {
            this.bgci = Integer.parseInt(this.bgcolor, 16);
        }
        catch (Exception ex) {
            this.bgci = 0;
        }
        try {
            this.image_number = Integer.parseInt("" + this.getParameter("image_number"), 10);
        }
        catch (Exception ex2) {
            this.image_number = 0;
        }
        try {
            this.hiz = Integer.parseInt("" + this.getParameter("delay_period"), 10);
        }
        catch (Exception ex3) {
            this.hiz = 2000;
        }
        try {
            this.step_period = Integer.parseInt("" + this.getParameter("step_period"), 10);
        }
        catch (Exception ex4) {
            this.step_period = 40;
        }
        try {
            this.jmp = Integer.parseInt("" + this.getParameter("step"), 10);
        }
        catch (Exception ex5) {
            this.jmp = 4;
        }
        this.setBackground(this.bgc = new Color(this.bgci));
        this.show();
        this.cusi = new int[100];
        this.image_align = this.getParameter("image_align");
        this.effects = this.getParameter("effects");
        if (this.effects == null) {
            this.effects = "random";
        }
        if (this.effects.equals("custom")) {
            this.custom_effects = this.getParameter("custom_effects");
            if (this.custom_effects == null) {
                this.custom_effects = "5,6,7,8";
            }
            this.i = 1;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.custom_effects, ",");
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    this.cusi[this.i] = Integer.valueOf(stringTokenizer.nextToken());
                }
                catch (Exception ex6) {
                    this.cusi[this.i] = 5;
                }
                ++this.i;
            }
            this.effnum = this.i - 1;
        }
        this.image_valign = this.getParameter("image_valign");
        this.img = new Image[this.image_number + 1];
        this.X = new int[this.image_number + 1];
        this.Y = new int[this.image_number + 1];
        this.links = new String[this.image_number + 1];
        this.imgw = new int[this.image_number + 1];
        this.imgh = new int[this.image_number + 1];
        this.eff = new int[9];
        this.i = 0;
        while (this.i <= 8) {
            this.eff[this.i] = this.i;
            ++this.i;
        }
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null) {
            if (this.regcode.equals("bb4556")) {
                this.rg = true;
            }
            else {
                this.rg = false;
            }
        }
        else {
            this.rg = false;
        }
        this.infostr = this.getParameter("info");
        this.target_frame = this.getParameter("target_frame");
        this.genislik = this.size().width;
        this.yukseklik = this.size().height;
        this.offimg = this.createImage(this.genislik, this.yukseklik);
        (this.offScreeng = this.offimg.getGraphics()).setColor(this.bgc);
        this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        this.tracker = new MediaTracker(this);
        this.don = 1;
        while (this.don <= this.image_number) {
            this.img[this.don] = this.getImage(this.getCodeBase(), this.getParameter("image" + this.don));
            this.tracker.addImage(this.img[this.don], this.don % 6);
            this.links[this.don] = this.getParameter("link" + this.don);
            ++this.don;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.rg) {
            this.showStatus("" + this.links[this.currimg]);
        }
        else {
            this.showStatus("  Unregistered version of billboard Java applet. URL: japplets.tripod.com");
        }
        this.repaint();
        ((Frame)this.getParent()).setCursor(12);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        ((Frame)this.getParent()).setCursor(0);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            this.u = new URL("" + this.links[this.currimg]);
            this.getAppletContext().showDocument(this.u, this.target_frame);
        }
        catch (Exception ex) {
            return true;
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        if (this.durum == 0) {
            this.offScreeng.setColor(Color.red);
            this.offScreeng.drawString("Loading...", 10, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 1) {
            this.offScreeng.setColor(Color.red);
            this.offScreeng.drawString("" + this.errstr, 10, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 2) {
            if (this.currimg >= 2) {
                this.offScreeng.drawImage(this.img[this.currimg - 1], this.X[this.currimg - 1], this.Y[this.currimg - 1], this);
            }
            else if (this.currimg == 1) {
                this.offScreeng.drawImage(this.img[this.image_number], this.X[this.image_number], this.Y[this.image_number], this);
            }
            this.offScreeng.drawImage(this.img[this.currimg], this.xx, this.yy, this);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimg, 0, 0, this);
    }
    
    public void start() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
        }
        this.runner.start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void destroy() {
        this.runner = null;
    }
    
    public int bigstp(final int n, final int n2) {
        final int min = Math.min(n, n2);
        return Math.max(n, n2) / ((min - min % this.jmp) / this.jmp);
    }
    
    public void run() {
        this.xx = 0;
        this.yy = 0;
        this.currimg = 1;
        this.effsay = 0;
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.tracker.isErrorAny()) {
            this.durum = 1;
            this.errstr = "Image Error !";
        }
        else {
            this.durum = 2;
            this.i = 1;
            while (this.i <= this.image_number) {
                this.imgw[this.i] = this.img[this.i].getWidth(this);
                this.imgh[this.i] = this.img[this.i].getHeight(this);
                ++this.i;
            }
            this.i = 1;
            while (this.i <= this.image_number) {
                if (this.image_align != null) {
                    if (this.imgw[this.i] < this.genislik) {
                        this.X[this.i] = 0;
                        if (this.image_align.equals("center")) {
                            this.X[this.i] = (this.genislik - this.imgw[this.i]) / 2;
                        }
                        if (this.image_align.equals("right")) {
                            this.X[this.i] = this.genislik - this.imgw[this.i];
                        }
                    }
                    else {
                        this.X[this.i] = 0;
                        if (this.image_align.equals("center")) {
                            this.X[this.i] = -1 * ((this.imgw[this.i] - this.genislik) / 2);
                        }
                        if (this.image_align.equals("right")) {
                            this.X[this.i] = this.genislik - this.imgw[this.i];
                        }
                    }
                }
                if (this.image_valign != null && this.imgh[this.i] < this.yukseklik) {
                    if (this.image_valign.equals("center")) {
                        this.Y[this.i] = (this.yukseklik - this.imgh[this.i]) / 2;
                    }
                    if (this.image_valign.equals("bottom")) {
                        this.Y[this.i] = this.yukseklik - this.imgh[this.i];
                    }
                }
                ++this.i;
            }
        }
        if (this.infostr != null) {
            if (!this.infostr.equals("Applet by Gokhan Dagli,japplets.tripod.com")) {
                this.durum = 1;
                this.errstr = "info parameter error!";
            }
        }
        else {
            this.durum = 1;
            this.errstr = "info parameter error!";
        }
        this.xx = this.X[this.currimg];
        this.yy = this.Y[this.currimg];
        this.repaint();
        this.curreff = 0;
        while (true) {
            try {
                Thread.sleep(this.hiz);
            }
            catch (InterruptedException ex2) {}
            if (this.effects.equals("respectively")) {
                ++this.curreff;
                if (this.curreff > 8) {
                    this.curreff = 1;
                }
            }
            if (this.effects.equals("random")) {
                this.curreff = (int)(8.0 * Math.random()) + 1;
                System.out.println("curreff: " + this.curreff);
                if (this.curreff > 8) {
                    this.curreff = 8;
                }
            }
            if (this.effects.equals("custom")) {
                ++this.effsay;
                if (this.effsay > this.effnum) {
                    this.effsay = 1;
                }
                this.curreff = this.cusi[this.effsay];
            }
            ++this.currimg;
            if (this.currimg > this.image_number) {
                this.currimg = 1;
            }
            switch (this.curreff) {
                case 1: {
                    this.xx = this.X[this.currimg] - this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg];
                    break;
                }
                case 2: {
                    this.xx = this.X[this.currimg] + this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg];
                    break;
                }
                case 3: {
                    this.xx = this.X[this.currimg];
                    this.yy = this.Y[this.currimg] - this.imgh[this.currimg];
                    break;
                }
                case 4: {
                    this.xx = this.X[this.currimg];
                    this.yy = this.Y[this.currimg] + this.imgh[this.currimg];
                    break;
                }
                case 5: {
                    this.xx = this.X[this.currimg] - this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg] - this.imgh[this.currimg];
                    break;
                }
                case 6: {
                    this.xx = this.X[this.currimg] + this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg] - this.imgh[this.currimg];
                    break;
                }
                case 7: {
                    this.xx = this.X[this.currimg] - this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg] + this.imgh[this.currimg];
                    break;
                }
                case 8: {
                    this.xx = this.X[this.currimg] + this.imgw[this.currimg];
                    this.yy = this.Y[this.currimg] + this.imgh[this.currimg];
                    break;
                }
            }
            this.bstep = this.bigstp(this.imgw[this.currimg], this.imgh[this.currimg]);
            this.devam = true;
            do {
                try {
                    Thread.sleep(this.step_period);
                }
                catch (InterruptedException ex3) {}
                switch (this.curreff) {
                    case 1: {
                        this.xx += this.jmp;
                        if (this.xx >= this.X[this.currimg]) {
                            this.xx = this.X[this.currimg];
                            this.devam = false;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.xx -= this.jmp;
                        if (this.xx <= this.X[this.currimg]) {
                            this.xx = this.X[this.currimg];
                            this.devam = false;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.yy += this.jmp;
                        if (this.yy >= this.Y[this.currimg]) {
                            this.yy = this.Y[this.currimg];
                            this.devam = false;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        this.yy -= this.jmp;
                        if (this.yy <= this.Y[this.currimg]) {
                            this.yy = this.Y[this.currimg];
                            this.devam = false;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.imgw[this.currimg] <= this.imgh[this.currimg]) {
                            this.xx += this.jmp;
                            this.yy += this.bstep;
                            if (this.xx >= this.X[this.currimg]) {
                                this.xx = this.X[this.currimg];
                                this.yy = this.Y[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        else {
                            this.xx += this.bstep;
                            this.yy += this.jmp;
                            if (this.yy >= this.Y[this.currimg]) {
                                this.yy = this.Y[this.currimg];
                                this.xx = this.X[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.imgw[this.currimg] <= this.imgh[this.currimg]) {
                            this.xx -= this.jmp;
                            this.yy += this.bstep;
                            if (this.xx <= this.X[this.currimg]) {
                                this.xx = this.X[this.currimg];
                                this.yy = this.Y[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        else {
                            this.xx -= this.bstep;
                            this.yy += this.jmp;
                            if (this.yy >= this.Y[this.currimg]) {
                                this.yy = this.Y[this.currimg];
                                this.xx = this.X[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case 7: {
                        if (this.imgw[this.currimg] <= this.imgh[this.currimg]) {
                            this.xx += this.jmp;
                            this.yy -= this.bstep;
                            if (this.xx >= this.X[this.currimg]) {
                                this.xx = this.X[this.currimg];
                                this.yy = this.Y[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        else {
                            this.xx += this.bstep;
                            this.yy -= this.jmp;
                            if (this.yy <= this.Y[this.currimg]) {
                                this.yy = this.Y[this.currimg];
                                this.xx = this.X[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if (this.imgw[this.currimg] <= this.imgh[this.currimg]) {
                            this.xx -= this.jmp;
                            this.yy -= this.bstep;
                            if (this.xx <= this.X[this.currimg]) {
                                this.xx = this.X[this.currimg];
                                this.yy = this.Y[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        else {
                            this.xx -= this.bstep;
                            this.yy -= this.jmp;
                            if (this.yy <= this.Y[this.currimg]) {
                                this.yy = this.Y[this.currimg];
                                this.xx = this.X[this.currimg];
                                this.devam = false;
                                break;
                            }
                            break;
                        }
                        break;
                    }
                }
                this.repaint();
            } while (this.devam);
        }
    }
    
    public billboard() {
        this.bgcolor = "";
        this.target_frame = "";
        this.infostr = "";
        this.image_align = "left";
        this.image_valign = "left";
        this.effects = "random";
        this.custom_effects = "5,6,7,8";
        this.regcode = "";
        this.errstr = "";
        this.currimg = 1;
        this.curreff = 3;
        this.jmp = 4;
        this.bstep = 4;
        this.effsay = 1;
        this.devam = false;
        this.inf = false;
        this.rg = false;
    }
}
