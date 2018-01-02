import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ButtonBar extends Applet implements Runnable
{
    static int[] msgnum;
    static int[] x1;
    static int[] x2;
    static int[] y1;
    static int[] y2;
    static int[] mapx1;
    static int[] mapx2;
    static int[] mapy1;
    static int[] mapy2;
    static String[][] msgtext;
    static String fontname;
    static int fontsize;
    static int buttnum;
    static boolean[] left;
    static String[][] textURL;
    String click;
    Color textColor;
    int globalX;
    int globalY;
    int curButt;
    int cnt;
    int[] maxtextWidth;
    int fheight;
    int[][] msgtextWidth;
    Image image;
    Image ring;
    Image offscreen;
    Graphics gc;
    Graphics g;
    MediaTracker mt;
    Thread juicer;
    boolean[] buttOn;
    boolean buttPushed;
    FontMetrics fm;
    Font font;
    
    public void init() {
        new ButtonBarParseArgs(this);
        this.offscreen = this.createImage(473, 260);
        this.gc = this.offscreen.getGraphics();
        this.mt = new MediaTracker(this);
        this.image = this.getImage(this.getCodeBase(), "images/sis_frontnav2.jpg");
        this.ring = this.getImage(this.getCodeBase(), "images/ring.gif");
        this.mt.addImage(this.image, 0);
        this.mt.addImage(this.ring, 0);
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("interrupted load of images");
        }
        this.font = new Font(ButtonBar.fontname, 1, ButtonBar.fontsize);
        this.fm = this.gc.getFontMetrics(this.font);
        this.fheight = this.fm.getMaxAscent() - this.fm.getMaxDescent() + 5;
        this.msgtextWidth = new int[ButtonBar.buttnum][10];
        this.maxtextWidth = new int[ButtonBar.buttnum];
        ButtonBar.x2 = new int[ButtonBar.buttnum];
        ButtonBar.y2 = new int[ButtonBar.buttnum];
        this.buttOn = new boolean[ButtonBar.buttnum];
        for (int i = 0; i < ButtonBar.buttnum; ++i) {
            for (int j = 0; j < ButtonBar.msgnum[i]; ++j) {
                this.msgtextWidth[i][j] = this.fm.stringWidth(ButtonBar.msgtext[i][j]);
                if (this.maxtextWidth[i] < this.msgtextWidth[i][j]) {
                    this.maxtextWidth[i] = this.msgtextWidth[i][j];
                }
            }
            ButtonBar.y2[i] = this.fheight * ButtonBar.msgnum[i];
            ButtonBar.x2[i] = ButtonBar.x1[i] + this.maxtextWidth[i];
        }
    }
    
    public void start() {
        if (this.juicer == null) {
            (this.juicer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.juicer != null && this.juicer.isAlive()) {
            this.juicer.stop();
            this.juicer = null;
        }
    }
    
    public void run() {
        this.gc.drawImage(this.image, 0, 0, this);
        this.gc.setFont(this.font);
        while (true) {
            if (this.cnt < 50) {
                final int cnt = this.cnt;
                if (ButtonBar.left[cnt]) {
                    ButtonBar.x1[cnt] = ButtonBar.mapx1[cnt] - this.maxtextWidth[cnt];
                }
                else {
                    ButtonBar.x1[cnt] = ButtonBar.mapx2[cnt];
                }
                ButtonBar.y1[cnt] = (ButtonBar.mapy1[cnt] + ButtonBar.mapy2[cnt]) / 2 - this.fheight * ButtonBar.msgnum[cnt];
                this.gc.drawImage(this.image, 0, 0, this);
                this.gc.setColor(Color.red);
                this.gc.setColor(Color.white);
                this.gc.drawImage(this.ring, ButtonBar.mapx1[cnt], ButtonBar.mapy1[cnt], 59, 59, this);
                this.gc.fillRect(ButtonBar.x1[cnt] - 10, ButtonBar.y1[cnt], ButtonBar.x2[cnt] + 15, ButtonBar.y2[cnt] + 5);
                this.gc.setColor(Color.black);
                this.gc.drawRect(ButtonBar.x1[cnt] - 10, ButtonBar.y1[cnt], ButtonBar.x2[cnt] + 14, ButtonBar.y2[cnt] + 4);
                for (int i = 0; i < ButtonBar.msgnum[cnt]; ++i) {
                    if (this.globalY < ButtonBar.y1[cnt] + this.fheight + this.fheight * i && this.globalY > ButtonBar.y1[cnt] + this.fheight * i) {
                        this.click = ButtonBar.textURL[cnt][i];
                        this.gc.setColor(Color.red);
                        this.gc.fillOval(ButtonBar.x1[cnt] - 8, ButtonBar.y1[cnt] + this.fheight / 2 + this.fheight * i, 4, 5);
                        this.gc.setColor(this.textColor);
                        this.showStatus(ButtonBar.textURL[cnt][i]);
                    }
                    this.gc.drawString(ButtonBar.msgtext[cnt][i], ButtonBar.x1[cnt] + 2, ButtonBar.y1[cnt] + this.fheight + this.fheight * i);
                    this.gc.setColor(Color.black);
                    this.gc.drawOval(ButtonBar.x1[cnt] - 8, ButtonBar.y1[cnt] + this.fheight / 2 + this.fheight * i, 4, 5);
                }
            }
            this.repaint();
            try {
                Thread.sleep(60L);
            }
            catch (InterruptedException ex) {}
            if (this.cnt == 100) {
                this.showStatus("Copyright 1997 Southern Internet");
                this.gc.drawImage(this.image, 0, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreen, 0, 0, this);
    }
    
    public boolean mouseMove(final Event event, final int globalX, final int globalY) {
        this.globalX = globalX;
        this.globalY = globalY;
        this.buttPushed = false;
        boolean b = false;
        for (int i = 0; i < ButtonBar.buttnum; ++i) {
            if (this.buttPushed) {
                this.buttOn[i] = false;
            }
            else if (globalX > ButtonBar.mapx1[i] && globalX < ButtonBar.mapx2[i] && globalY > ButtonBar.mapy1[i] && globalY < ButtonBar.mapy2[i]) {
                this.buttOn[i] = true;
                b = true;
                this.cnt = i;
            }
            else if (!ButtonBar.left[i] && this.buttOn[i] && globalX > ButtonBar.mapx2[i] - 15 && globalX < ButtonBar.mapx2[i] + this.maxtextWidth[i] + 5 && globalY > (ButtonBar.mapy2[i] + ButtonBar.mapy1[i]) / 2 - this.fheight * ButtonBar.msgnum[i] && globalY < (ButtonBar.mapy2[i] + ButtonBar.mapy1[i]) / 2 + 10) {
                for (int j = 0; j < ButtonBar.buttnum; ++j) {
                    this.buttOn[j] = false;
                }
                this.buttOn[i] = true;
                this.cnt = i;
                b = true;
                this.buttPushed = true;
            }
            else if (ButtonBar.left[i] && this.buttOn[i] && globalX > ButtonBar.mapx1[i] - this.maxtextWidth[i] - 10 && globalX < ButtonBar.mapx1[i] + 20 && globalY > (ButtonBar.mapy2[i] + ButtonBar.mapy1[i]) / 2 - this.fheight * ButtonBar.msgnum[i] && globalY < ButtonBar.mapy2[i]) {
                for (int k = 0; k < ButtonBar.buttnum; ++k) {
                    this.buttOn[k] = false;
                }
                this.buttOn[i] = true;
                this.cnt = i;
                b = true;
                this.buttPushed = true;
            }
            else {
                this.buttOn[i] = false;
            }
        }
        if (!b) {
            this.cnt = 100;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL(this.click));
        }
        catch (MalformedURLException ex) {
            System.out.println("hey I caught it!!!");
            System.out.println(this.click + " exception");
        }
        return true;
    }
    
    public ButtonBar() {
        this.textColor = Color.blue;
        this.cnt = 100;
        this.buttPushed = false;
    }
}
