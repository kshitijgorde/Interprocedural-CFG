import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LED extends Applet implements Runnable
{
    int WIDTH;
    int HEIGHT;
    Color highlight;
    Script com;
    FuncInfo fi;
    Letters let;
    int ledsize;
    Color[] colors;
    LEDMessage msg;
    Color bhilite;
    Color bcolor;
    Color bshadow;
    Thread led;
    String scrpt;
    String endspace;
    String fnt;
    String text;
    String currurl;
    URL currURL;
    String target;
    int place;
    int border;
    int offset;
    int w;
    int h;
    int swidth;
    boolean beginning;
    boolean init;
    boolean inapplet;
    boolean done;
    Image pixmapimg;
    Image offimg;
    Image tmpimg;
    Graphics pixmap;
    Graphics offmap;
    Graphics tmpmap;
    Pixelize[] pix;
    
    private int getAttrs() {
        if (this.getParameter("script") == null) {
            System.out.println("LED Sign Error: No script specified in HTML.");
            this.currurl = new String("LED Sign Error: No script specified in HTML.");
            return -1;
        }
        this.scrpt = new String(this.getParameter("script"));
        if (this.getParameter("font") != null) {
            this.fnt = new String(this.getParameter("font"));
            if (this.getParameter("spacewidth") != null) {
                this.swidth = new Integer(new String(this.getParameter("spacewidth")));
            }
            else {
                this.swidth = 3;
            }
            if (this.getParameter("ledsize") != null) {
                this.ledsize = new Integer(new String(this.getParameter("ledsize")));
                if (this.ledsize < 1) {
                    this.ledsize = 1;
                }
                else if (this.ledsize > 4) {
                    this.ledsize = 4;
                }
                ++this.ledsize;
            }
            else {
                this.ledsize = 4;
            }
            if (this.getParameter("ht") != null) {
                this.HEIGHT = this.ledsize * new Integer(new String(this.getParameter("ht")));
                this.h = this.HEIGHT / this.ledsize;
            }
            else {
                System.out.println("LED Sign Warning: parameter \"ht\" (height in LED's) not specified");
                this.HEIGHT = this.ledsize * 9;
                this.h = 9;
            }
            if (this.getParameter("wth") != null) {
                this.WIDTH = this.ledsize * new Integer(new String(this.getParameter("wth")));
                if (this.WIDTH / this.ledsize % 2 == 1) {
                    this.WIDTH += this.ledsize;
                }
                this.w = this.WIDTH / this.ledsize;
            }
            else {
                System.out.println("LED Sign Warning: parameter \"wth\" (width in LED's) not specified");
                this.WIDTH = 60 * this.ledsize;
                this.w = 60;
            }
            if (this.getParameter("border") != null) {
                this.border = new Integer(new String(this.getParameter("border")));
            }
            else {
                this.border = 0;
            }
            if (this.getParameter("bordercolor") != null) {
                final String trim = new String(this.getParameter("bordercolor")).trim();
                final int intValue = new Integer(trim.substring(0, trim.indexOf(",")));
                final String substring = trim.substring(trim.indexOf(",") + 1);
                final int intValue2 = new Integer(substring.substring(0, substring.indexOf(",")));
                final int intValue3 = new Integer(substring.substring(substring.indexOf(",") + 1));
                this.bhilite = new Color((intValue + 40 < 256) ? (intValue + 40) : 255, (intValue2 + 40 < 256) ? (intValue2 + 40) : 255, (intValue3 + 40 < 256) ? (intValue3 + 40) : 255);
                this.bcolor = new Color(intValue, intValue2, intValue3);
                this.bshadow = new Color((intValue - 40 >= 0) ? (intValue - 40) : 0, (intValue2 - 40 >= 0) ? (intValue2 - 40) : 0, (intValue3 - 40 >= 0) ? (intValue3 - 40) : 0);
            }
            else {
                this.bhilite = Color.white;
                this.bcolor = Color.lightGray;
                this.bshadow = Color.darkGray;
            }
            return 1;
        }
        System.out.println("LED Sign Error: No font specified in HTML.");
        this.currurl = new String("LED Sign Error: No font specified in HTML.");
        return -1;
    }
    
    public void init() {
        this.highlight = new Color(100, 100, 100);
        (this.colors = new Color[9])[0] = new Color(80, 80, 80);
        this.colors[1] = new Color(255, 0, 0);
        this.colors[2] = new Color(130, 255, 0);
        this.colors[3] = new Color(0, 130, 255);
        this.colors[4] = new Color(255, 255, 0);
        this.colors[5] = new Color(255, 160, 0);
        this.colors[6] = new Color(255, 0, 255);
        this.colors[7] = new Color(255, 255, 255);
        this.colors[8] = new Color(0, 255, 255);
        if (this.getAttrs() == -1) {
            this.stop();
            return;
        }
        this.offset = 3 * this.border;
        this.beginning = true;
        this.init = true;
    }
    
    public void getinfo() {
        this.pix = new Pixelize[1];
        this.let = new Letters(this.getDocumentBase(), this.fnt, this.swidth);
        if (this.let.w == -1) {
            System.out.println("LED Sign Error - Bad font path in HTML:");
            System.out.println("   File not found:  \"" + this.fnt + "\"");
            this.currurl = new String("LED Sign Error - Bad font path in HTML.");
            this.stop();
        }
        else {
            if (this.HEIGHT != this.let.height() * this.ledsize) {
                System.out.println("LED Sign Warning: parameter \"ht\" should be set to " + this.let.height() * this.ledsize + ".");
            }
            this.msg = new LEDMessage(this.h, this.w, this.let);
            this.com = new Script(this.getDocumentBase(), this.scrpt);
            if (this.com.ok == -1) {
                System.out.println("LED Sign Error - Bad script path in HTML:");
                System.out.println("   File not found:  \"" + this.scrpt + "\"");
                this.currurl = new String("LED Sign Error: Bad script path in HTML.");
                this.stop();
            }
            else {
                this.fi = new FuncInfo();
                this.nextFunc();
                this.resize(this.WIDTH + 2 * this.offset, this.HEIGHT + 2 * this.offset);
            }
        }
        this.init = false;
    }
    
    public void start() {
        if (this.led == null) {
            (this.led = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.led != null) {
            this.led.stop();
            this.led = null;
        }
    }
    
    public void run() {
        if (this.init) {
            this.getinfo();
        }
        while (this.led != null) {
            this.repaint();
            try {
                Thread.sleep(this.fi.delay);
            }
            catch (InterruptedException ex) {}
            if (this.done) {
                this.nextFunc();
                if (this.fi == null) {
                    System.out.println("LED Sign Error - Bad script path in HTML:");
                    System.out.println("   File not found: " + this.scrpt);
                    this.currurl = new String("LED Sign Error: Bad script path in HTML.");
                    this.stop();
                }
                this.done = false;
            }
        }
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "script     ", "URL        ", "LED script to use (Required)" }, { "font       ", "URL        ", "Font to use (Required)" }, { "spacewidth ", "int        ", "Width of space in columns, default = 3 )" }, { "wth        ", "int        ", "Width of live display (cols, default=60)" }, { "ht         ", "int        ", "Height of live display (rows, default=9)" }, { "border     ", "int        ", "Width of display border (pix, default=0)" }, { "bordercolor", "int,int,int", "Color of border (n,n,n default=lightGray)" }, { "ledsize    ", "int        ", "Diameter of LEDs pixels (1-4), default=3)" } };
    }
    
    public String getAppletInfo() {
        return "LED Sign V2.7 by Darrick Brown. 3-22-96";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.currURL != null) {
            if (this.target.length() > 0) {
                this.getAppletContext().showDocument(this.currURL, this.target);
            }
            else {
                this.getAppletContext().showDocument(this.currURL);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.inapplet = true;
        this.showStatus(this.currurl);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inapplet = false;
        this.showStatus(" ");
        return true;
    }
    
    void nextFunc() {
        this.fi = this.com.nextFunc();
        this.fi = this.com.parseLine(this.fi);
        this.msg.setmsg(this.fi);
        switch (this.fi.func) {
            case 0: {
                this.place = 0;
                break;
            }
            case 1: {
                this.place = 0;
                break;
            }
            case 2: {
                this.place = 0;
                break;
            }
            case 3: {
                this.place = this.msg.length() - 1;
                break;
            }
            case 4: {
                this.place = 0;
                break;
            }
            case 5: {
                this.place = this.h - 1;
                break;
            }
            case 6: {
                this.place = 0;
                this.pix = new Pixelize[this.w * this.h];
                for (int i = 0; i < this.w; ++i) {
                    for (int j = 0; j < this.h; ++j) {
                        this.pix[this.h * i + j] = new Pixelize();
                        this.pix[this.h * i + j].x = i;
                        this.pix[this.h * i + j].y = j;
                    }
                }
                for (int k = 0; k < this.WIDTH / this.ledsize * this.h; ++k) {
                    final int n = (int)(Math.random() * (this.WIDTH / this.ledsize) * this.h);
                    final Pixelize pixelize = this.pix[k];
                    this.pix[k] = this.pix[n];
                    this.pix[n] = pixelize;
                }
                break;
            }
            case 7: {
                this.place = this.fi.times * 2;
                break;
            }
            case 8: {
                this.place = 0;
                break;
            }
            case 9: {
                this.place = 0;
                break;
            }
            case 10: {
                this.place = 0;
                break;
            }
            case 11: {
                this.place = this.w;
                break;
            }
            case 12: {
                this.place = this.h - 1;
                break;
            }
            case 13: {
                this.place = 0;
                break;
            }
        }
        if (this.fi.url != null) {
            this.currurl = this.fi.url.toString();
            this.currURL = this.fi.url;
            this.target = this.fi.target;
        }
        else {
            this.currurl = new String("LED Sign V2.7");
            this.currURL = null;
            this.target = new String("");
        }
        if (this.inapplet) {
            this.showStatus(this.currurl);
        }
    }
    
    private void drawLED(final int n, final int n2, final boolean b, final int n3, final Graphics graphics) {
        if (b) {
            graphics.setColor(this.colors[n3]);
        }
        else {
            graphics.setColor(this.colors[0]);
        }
        switch (this.ledsize) {
            case 2: {
                graphics.drawLine(n, n2, n, n2);
                break;
            }
            case 3: {
                graphics.fillRect(n, n2, 2, 2);
                break;
            }
            case 4: {
                graphics.drawLine(n, n2 + 1, n + 2, n2 + 1);
                graphics.drawLine(n + 1, n2, n + 1, n2 + 2);
                break;
            }
            case 5: {
                graphics.fillRect(n + 1, n2, 2, 4);
                graphics.fillRect(n, n2 + 1, 4, 2);
                break;
            }
        }
        if (this.ledsize == 5 && !b) {
            graphics.setColor(this.highlight);
            graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
        }
    }
    
    void draw3DRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        for (int i = 0; i < n5; ++i) {
            if (b) {
                graphics.setColor(this.bhilite);
            }
            else {
                graphics.setColor(this.bshadow);
            }
            graphics.drawLine(n + i, n2 + i, n3 - i, n2 + i);
            graphics.drawLine(n + i, n2 + i, n + i, n4 - i);
            if (b) {
                graphics.setColor(this.bshadow);
            }
            else {
                graphics.setColor(this.bhilite);
            }
            graphics.drawLine(n3 - i, n2 + i, n3 - i, n4 - i);
            graphics.drawLine(n + i, n4 - i, n3 - i, n4 - i);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.led != null) {
            if (this.border > 0) {
                this.draw3DRect(graphics, 0, 0, this.WIDTH + 2 * this.offset - 1, this.HEIGHT + 2 * this.offset - 1, this.border, true);
                graphics.setColor(this.bcolor);
                graphics.fillRect(this.border, this.border, this.WIDTH + 4 * this.border, this.HEIGHT + 4 * this.border);
                this.draw3DRect(graphics, 2 * this.border, 2 * this.border, this.WIDTH + 4 * this.border - 1, this.HEIGHT + 4 * this.border - 1, this.border, false);
            }
            if (this.beginning) {
                this.offimg = this.createImage(this.WIDTH, this.HEIGHT);
                (this.offmap = this.offimg.getGraphics()).setColor(Color.black);
                this.offmap.fillRect(0, 0, this.WIDTH, this.HEIGHT);
                for (int i = 0; i < this.HEIGHT; i += this.ledsize) {
                    for (int j = 0; j < this.WIDTH; j += this.ledsize) {
                        this.drawLED(j, i, false, 1, this.offmap);
                    }
                }
                graphics.drawImage(this.offimg, this.offset, this.offset, this);
                this.pixmapimg = this.createImage(this.WIDTH, this.HEIGHT);
                this.tmpimg = this.createImage(this.WIDTH, this.HEIGHT);
                this.pixmap = this.pixmapimg.getGraphics();
                this.tmpmap = this.tmpimg.getGraphics();
                this.pixmap.setColor(Color.black);
                this.pixmap.fillRect(0, 0, this.WIDTH, this.HEIGHT);
                for (int k = 0; k < this.HEIGHT; k += this.ledsize) {
                    for (int l = 0; l < this.WIDTH; l += this.ledsize) {
                        this.drawLED(l, k, false, 1, this.pixmap);
                    }
                }
                this.beginning = false;
                return;
            }
            graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.done) {
            return;
        }
        if (this.led != null && this.pixmap != null && this.offmap != null && this.tmpmap != null) {
            switch (this.fi.func) {
                case 0: {
                    if (this.fi.text == null) {
                        graphics.drawImage(this.offimg, this.offset, this.offset, this);
                    }
                    else {
                        for (int i = 0; i < this.w; ++i) {
                            for (int j = 0; j < this.h; ++j) {
                                this.drawLED(i * this.ledsize, j * this.ledsize, this.msg.getLED(i, j), this.msg.getColor(i), this.pixmap);
                            }
                        }
                        graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    }
                    this.done = true;
                }
                case 1: {
                    this.done = true;
                }
                case 2: {
                    this.pixmap.copyArea(this.ledsize, 0, this.WIDTH - this.ledsize, this.HEIGHT, -this.ledsize, 0);
                    for (int k = 0; k < this.HEIGHT; k += this.ledsize) {
                        this.drawLED(this.WIDTH - this.ledsize, k, this.msg.getLED(this.place, k / this.ledsize), this.msg.getColor(this.place), this.pixmap);
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (!this.msg.inRange(this.place)) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 3: {
                    this.pixmap.copyArea(0, 0, this.WIDTH - this.ledsize, this.HEIGHT, this.ledsize, 0);
                    for (int l = 0; l < this.HEIGHT; l += this.ledsize) {
                        this.drawLED(0, l, this.msg.getLED(this.place, l / this.ledsize), this.msg.getColor(this.place), this.pixmap);
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    --this.place;
                    if (this.place < 0) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 4: {
                    this.pixmap.copyArea(0, this.ledsize, this.WIDTH, this.HEIGHT - this.ledsize, 0, -this.ledsize);
                    for (int n = 0; n < this.WIDTH; n += this.ledsize) {
                        if (this.msg.inRange(n / this.ledsize)) {
                            this.drawLED(n, this.HEIGHT - this.ledsize, this.msg.getLED(n / this.ledsize, this.place), this.msg.getColor(n / this.ledsize), this.pixmap);
                        }
                        else {
                            this.drawLED(n, this.HEIGHT - this.ledsize, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (this.place >= this.h) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 5: {
                    this.pixmap.copyArea(0, 0, this.WIDTH, this.HEIGHT - this.ledsize, 0, this.ledsize);
                    for (int n2 = 0; n2 < this.WIDTH; n2 += this.ledsize) {
                        if (this.msg.inRange(n2 / this.ledsize)) {
                            this.drawLED(n2, 0, this.msg.getLED(n2 / this.ledsize, this.place), this.msg.getColor(n2 / this.ledsize), this.pixmap);
                        }
                        else {
                            this.drawLED(n2, 0, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    --this.place;
                    if (this.place < 0) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 6: {
                    final int n3 = this.place + this.fi.times;
                    while (this.place < this.WIDTH / this.ledsize * this.h && this.place < n3) {
                        if (this.msg.inRange(this.pix[this.place].x)) {
                            this.drawLED(this.pix[this.place].x * this.ledsize, this.pix[this.place].y * this.ledsize, this.msg.getLED(this.pix[this.place].x, this.pix[this.place].y), this.msg.getColor(this.pix[this.place].x), this.pixmap);
                        }
                        else {
                            this.drawLED(this.pix[this.place].x * this.ledsize, this.pix[this.place].y * this.ledsize, false, 1, this.pixmap);
                        }
                        ++this.place;
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    if (this.place >= this.w * this.h) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 7: {
                    if (this.place % 2 == 0) {
                        graphics.drawImage(this.offimg, this.offset, this.offset, this);
                    }
                    else {
                        graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    }
                    --this.place;
                    if (this.place == 0) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 8: {
                    if (this.msg.inRange(this.place)) {
                        for (int n4 = 0; n4 < this.h; ++n4) {
                            this.drawLED(this.place * this.ledsize, n4 * this.ledsize, this.msg.getLED(this.place, n4), this.msg.getColor(this.place), this.pixmap);
                        }
                    }
                    else {
                        for (int n5 = 0; n5 < this.h; ++n5) {
                            this.drawLED(this.place * this.ledsize, n5 * this.ledsize, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (this.place >= this.w) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 9: {
                    if (this.w >= this.place * 2) {
                        this.pixmap.copyArea(this.WIDTH / 2, 0, this.WIDTH / 2 - this.ledsize, this.HEIGHT, this.ledsize, 0);
                        for (int n6 = 0; n6 < this.h; ++n6) {
                            if (this.msg.inRange(this.w - this.place)) {
                                this.drawLED(this.WIDTH / 2, n6 * this.ledsize, this.msg.getLED(this.w - this.place, n6), this.msg.getColor(this.w - this.place), this.pixmap);
                            }
                            else {
                                this.drawLED(this.WIDTH / 2, n6 * this.ledsize, false, 1, this.pixmap);
                            }
                        }
                    }
                    if (this.place < this.w / 2) {
                        this.pixmap.copyArea(this.ledsize, 0, this.WIDTH / 2 - this.ledsize, this.HEIGHT, -this.ledsize, 0);
                        for (int n7 = 0; n7 < this.h; ++n7) {
                            if (this.msg.inRange(this.place)) {
                                this.drawLED(this.WIDTH / 2 - this.ledsize, n7 * this.ledsize, this.msg.getLED(this.place, n7), this.msg.getColor(this.place), this.pixmap);
                            }
                            else {
                                this.drawLED(this.WIDTH / 2 - this.ledsize, n7 * this.ledsize, false, 1, this.pixmap);
                            }
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (this.place >= this.w / 2 && this.place * 2 > this.w) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 10: {
                    if (this.w >= this.place + this.w / 2) {
                        for (int n8 = 0; n8 < this.h; ++n8) {
                            if (this.msg.inRange(this.w / 2 + this.place + 1)) {
                                this.drawLED(this.WIDTH / 2 + this.place * this.ledsize + this.ledsize, n8 * this.ledsize, this.msg.getLED(this.w / 2 + this.place + 1, n8), this.msg.getColor(this.w / 2 + this.place + 1), this.pixmap);
                            }
                            else {
                                this.drawLED(this.WIDTH / 2 + this.place * this.ledsize + this.ledsize, n8 * this.ledsize, false, 1, this.pixmap);
                            }
                        }
                    }
                    if (this.place < this.w / 2) {
                        for (int n9 = 0; n9 < this.h; ++n9) {
                            if (this.msg.inRange(this.w / 2 - this.place)) {
                                this.drawLED(this.WIDTH / 2 - this.place * this.ledsize, n9 * this.ledsize, this.msg.getLED(this.w / 2 - this.place, n9), this.msg.getColor(this.w / 2 - this.place), this.pixmap);
                            }
                            else {
                                this.drawLED(this.WIDTH / 2 - this.place * this.ledsize, n9 * this.ledsize, false, 1, this.pixmap);
                            }
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (this.w < this.w / 2 + this.place && this.place >= this.w / 2) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 11: {
                    if (this.msg.inRange(this.place)) {
                        for (int n10 = 0; n10 < this.h; ++n10) {
                            this.drawLED(this.place * this.ledsize, n10 * this.ledsize, this.msg.getLED(this.place, n10), this.msg.getColor(this.place), this.pixmap);
                        }
                    }
                    else {
                        for (int n11 = 0; n11 < this.h; ++n11) {
                            this.drawLED(this.place * this.ledsize, n11 * this.ledsize, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    --this.place;
                    if (this.place == 0) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 12: {
                    for (int n12 = 0; n12 < this.w; ++n12) {
                        if (this.msg.inRange(n12)) {
                            this.drawLED(n12 * this.ledsize, this.place * this.ledsize, this.msg.getLED(n12, this.place), this.msg.getColor(n12), this.pixmap);
                        }
                        else {
                            this.drawLED(n12 * this.ledsize, this.place * this.ledsize, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    --this.place;
                    if (this.place < 0) {
                        this.done = true;
                        return;
                    }
                    break;
                }
                case 13: {
                    for (int n13 = 0; n13 < this.w; ++n13) {
                        if (this.msg.inRange(n13)) {
                            this.drawLED(n13 * this.ledsize, this.place * this.ledsize, this.msg.getLED(n13, this.place), this.msg.getColor(n13), this.pixmap);
                        }
                        else {
                            this.drawLED(n13 * this.ledsize, this.place * this.ledsize, false, 1, this.pixmap);
                        }
                    }
                    graphics.drawImage(this.pixmapimg, this.offset, this.offset, this);
                    ++this.place;
                    if (this.place >= this.h) {
                        this.done = true;
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public LED() {
        this.WIDTH = 400;
        this.HEIGHT = 30;
        this.currurl = "LED Sign V2.7";
        this.target = new String("");
        this.beginning = false;
        this.init = false;
        this.done = false;
    }
}
