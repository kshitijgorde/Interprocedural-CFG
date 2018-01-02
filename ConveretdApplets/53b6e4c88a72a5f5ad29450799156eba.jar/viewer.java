import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.Reader;
import java.io.InputStreamReader;
import java.awt.Component;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class viewer extends Applet implements Runnable
{
    Image photo;
    Image panel;
    Image photo2;
    MediaTracker MT;
    int height;
    int width;
    int lwin;
    int hwin;
    int hsizep;
    int lsizep;
    int lsize;
    int hsize;
    int cornerval;
    float imgratio;
    int a;
    int b;
    int c;
    int d;
    int i;
    int j;
    char t;
    double aa;
    double bb;
    double cc;
    double dd;
    double oldaa;
    Thread thread1;
    int trucpasnul;
    long oldtime;
    long newtime;
    int animofs;
    int animcol;
    boolean affpanel;
    boolean affphoto;
    boolean mousein;
    boolean review;
    int mousex;
    int mousey;
    int mouseclick;
    String size1;
    URL url;
    InputStream in;
    BufferedReader buf;
    int index;
    int index2;
    int index3;
    int index4;
    int ofstex;
    int ofstex2;
    int valpix;
    String txtcredits;
    boolean pauseslide;
    boolean newphoto;
    boolean newphotoloaded;
    boolean newphotoloaded2;
    boolean playmode;
    int nbphotos;
    int tempint1;
    int tempint2;
    int tempint3;
    int tempint4;
    int timerslide;
    int showpanel;
    int showinfo;
    int strlen;
    int regisok;
    String domain;
    String stest;
    String stest2;
    String regkey;
    String listfile;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String regcode;
    int photoindex;
    int currentphotoindex;
    int colorback;
    String[] photolist;
    char[] regtable;
    char[] regtabl2;
    
    public viewer() {
        this.trucpasnul = 1;
        this.affpanel = true;
        this.affphoto = false;
        this.mousein = false;
        this.review = true;
        this.index4 = 0;
        this.txtcredits = "applet by Mandrixx @ http://www.mandrixx.net";
        this.pauseslide = false;
        this.newphoto = false;
        this.newphotoloaded = false;
        this.newphotoloaded2 = false;
        this.playmode = true;
        this.colorback = 0;
        this.photolist = new String[2500];
        this.regtable = new char[] { '/', '.', ':', '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        this.regtabl2 = new char[] { 'u', 'x', 'v', '/', '8', 'd', 'y', 'u', '4', 'p', 'r', 'a', 'e', '3', 'c', 'b', 'o', '2', '1', 'i', 'f', 'h', 'g', '5', '0', 'n', 'z', '6', '#', '&', 'l', '7', 'q', 'k', '9', 's', 'w', 'm', 'j', '*' };
    }
    
    public void init() {
        this.setBackground(Color.black);
        this.enableEvents(16L);
        this.enableEvents(32L);
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.listfile = this.getParameter("list");
        this.regcode = this.getParameter("regkey");
        this.opt1 = this.getParameter("timer");
        this.timerslide = new Integer(this.opt1);
        this.opt2 = this.getParameter("showpanel");
        this.showpanel = new Integer(this.opt2);
        this.opt3 = this.getParameter("showinfo");
        this.showinfo = new Integer(this.opt3);
        this.opt4 = this.getParameter("backcolor");
        this.colorback = new Integer(this.opt4) - 16777216;
        if (this.timerslide < 300) {
            this.timerslide = 300;
        }
        this.MT = new MediaTracker(this);
        this.panel = this.getImage(this.getDocumentBase(), "panel.gif");
        this.MT.addImage(this.panel, 0);
        this.MT.checkAll(true);
        this.i = 0;
        try {
            this.url = new URL(this.getDocumentBase(), this.listfile);
        }
        catch (Exception ex) {}
        this.regkey = "";
        for (int i = 0; i < 7; ++i) {
            this.t = this.regcode.charAt(i);
            for (int j = 0; j < this.regtable.length; ++j) {
                if (this.regtabl2[j] == this.t) {
                    this.regkey += this.regtable[j];
                }
            }
        }
        this.domain = this.getDocumentBase().toString();
        this.strlen = this.domain.length();
        if (this.strlen < 8) {
            this.strlen = 8;
        }
        this.stest = this.domain.substring(0, 4);
        this.regisok = 0;
        if (this.stest.equalsIgnoreCase("http")) {
            for (int k = 0; k < this.strlen - 7; ++k) {
                this.stest2 = this.domain.substring(0 + k, 7 + k);
                if (this.stest2.equalsIgnoreCase(this.regkey)) {
                    this.regisok = 1;
                }
            }
        }
        else {
            this.regisok = 1;
        }
        try {
            this.in = this.url.openStream();
            this.buf = new BufferedReader(new InputStreamReader(this.in));
            String line;
            do {
                final String[] photolist = this.photolist;
                final int n = this.i++;
                line = this.buf.readLine();
                photolist[n] = line;
            } while (line != null && this.i <= 2480);
            this.buf.close();
        }
        catch (Exception ex2) {}
        this.nbphotos = this.i - 1;
        if (this.regisok == 0) {
            this.nbphotos = 5;
        }
        this.setBackground(Color.black);
        this.getphoto();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.affphoto = false;
        if (this.newphotoloaded && this.MT.checkID(0) && !this.MT.isErrorID(0)) {
            this.photo = this.photo2;
            this.newphotoloaded2 = true;
            this.newphotoloaded = false;
            this.affphoto = true;
        }
        if (this.affpanel) {
            this.affpanel = false;
            this.affphoto = true;
        }
        if (this.affphoto) {
            graphics.setColor(new Color(this.colorback));
            graphics.fillRect(0, 0, this.width, this.height);
            this.hsize = this.photo.getWidth(null);
            this.hsizep = this.hsize;
            this.i = this.hsize;
            this.lsize = this.photo.getHeight(null);
            this.lsizep = this.lsize;
            if (this.lsize > this.hsize) {
                this.j = this.hsize;
                this.hsize = this.lsize;
                this.lsize = this.j;
            }
            this.lwin = this.height * this.lsize / this.hsize;
            this.hwin = this.width * this.lsize / this.hsize;
            if (this.i == this.hsize) {
                this.cornerval = this.height - this.hwin >> 1;
                graphics.drawImage(this.photo, 0, this.cornerval, this.width, this.hwin, this);
            }
            else {
                this.cornerval = this.width - this.lwin >> 1;
                graphics.drawImage(this.photo, this.cornerval, 0, this.lwin, this.height, this);
            }
            if (this.mousein && this.showpanel != 0) {
                graphics.drawImage(this.panel, this.width - this.panel.getWidth(null), this.height - this.panel.getHeight(null), this.panel.getWidth(null), this.panel.getHeight(null), this);
            }
        }
        if (!this.playmode) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.width, 26);
            graphics.setColor(Color.white);
            graphics.drawString("SLIDESHOW IS PAUSED - PRESS PLAY TO CONTINUE", (this.width >> 1) - 125, 16);
            if (this.showpanel != 0) {
                graphics.drawImage(this.panel, this.width - this.panel.getWidth(null), this.height - this.panel.getHeight(null), this.panel.getWidth(null), this.panel.getHeight(null), this);
            }
        }
        if (this.regisok == 0 && this.playmode) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.width, 26);
            graphics.setColor(Color.white);
            graphics.drawString("MJS v1.0 - unregistered version - http://mandrixx.net", (this.width >> 1) - 132, 16);
        }
        if (this.showinfo != 0 && this.mousein && this.playmode) {
            graphics.setColor(Color.white);
            graphics.drawString("MJS v1.0 - http://mandrixx.net", 10, this.height - 48);
            graphics.drawString("Image : " + this.currentphotoindex + "/" + (this.nbphotos - 1) + "    Size  X:" + this.hsizep + "  Y:" + this.lsizep, 10, this.height - 32);
            graphics.drawString("File : " + this.photolist[this.currentphotoindex] + "    timer : " + this.timerslide / 1000 + " secs", 10, this.height - 16);
        }
    }
    
    public void start() {
        if (this.thread1 == null) {
            (this.thread1 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.thread1 = null;
    }
    
    public void run() {
        this.repaint();
        this.oldtime = System.currentTimeMillis();
        while (Thread.currentThread() == this.thread1) {
            this.repaint();
            if (this.mouseclick == 1 && this.showpanel != 0 && this.mousey > this.height - 50) {
                if (this.mousex >= this.width - 51 && this.newphotoloaded2) {
                    this.playmode = true;
                    this.oldtime = System.currentTimeMillis() - (this.timerslide - 250);
                }
                if (this.mousex >= this.width - 95 && this.mousex < this.width - 51 && this.newphotoloaded2) {
                    this.playmode = false;
                }
                if (this.mousex >= this.width - 155 && this.mousex < this.width - 95 && this.newphotoloaded2 && this.review) {
                    this.review = false;
                    this.photoindex -= 2;
                    if (this.photoindex < 0) {
                        this.photoindex += this.nbphotos;
                    }
                    this.newphotoloaded2 = false;
                    this.oldtime = this.newtime;
                    this.newphoto = true;
                    this.playmode = false;
                }
            }
            this.newtime = System.currentTimeMillis();
            if (this.newtime - this.oldtime > this.timerslide && this.newphotoloaded2 && this.playmode) {
                this.newphotoloaded2 = false;
                this.oldtime = this.newtime;
                this.newphoto = true;
            }
            if (this.newphoto) {
                this.getphoto();
            }
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
        while (this.trucpasnul == 1) {}
    }
    
    public void getphoto() {
        this.MT = new MediaTracker(this);
        this.currentphotoindex = this.photoindex;
        this.photo2 = this.getImage(this.getDocumentBase(), this.photolist[this.photoindex++]);
        if (this.photoindex >= this.nbphotos) {
            this.photoindex = 0;
        }
        this.MT.addImage(this.photo2, 0);
        this.MT.checkAll(true);
        this.newphotoloaded = true;
        this.newphotoloaded2 = false;
        this.newphoto = false;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 504: {
                this.mousein = true;
                this.affpanel = true;
            }
            case 505: {
                this.mousein = false;
                this.affpanel = true;
            }
            case 502: {
                this.review = true;
                this.mouseclick = 0;
            }
            case 501: {
                this.mouseclick = 1;
                break;
            }
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 503) {
            this.mousex = mouseEvent.getX();
            this.mousey = mouseEvent.getY();
        }
    }
}
