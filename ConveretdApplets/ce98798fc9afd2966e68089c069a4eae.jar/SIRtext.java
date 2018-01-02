import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRtext extends Applet implements Runnable, MouseMotionListener, MouseListener
{
    int i;
    int j;
    int k;
    int x;
    int y;
    int NEXTPAGE;
    int FIRST;
    int FIRSTLINE;
    int CURRENTLINE;
    int YOFFSET;
    int SPEED;
    int HIGHLIGHT;
    String SC;
    String FT;
    String Fonttype;
    String Fontstyle;
    int Fontsize;
    StringTokenizer st;
    FontMetrics fontMetrics;
    int WIDTH;
    int HEIGHT;
    int TXTWIDTH;
    int TXTHEIGHT;
    String TITLE;
    int TITLEFONT;
    int TITLECOLOR;
    int BGCOLOR;
    int BORDERWIDTH;
    int BORDERCOLOR;
    int SCROLLSPEED;
    int DISPLAYTIME;
    int MAXLINES;
    Color[] COLORS;
    Font[] FONTS;
    String[] TEXTLINES;
    int[] TEXTCOLOR;
    int[] TEXTFONT;
    public Image buffer;
    public Image buffer2;
    public Image buffer3;
    Graphics pad;
    Graphics BGpad;
    Graphics TEXT;
    Thread mainthread;
    boolean threadflag;
    boolean waiting;
    
    public SIRtext() {
        this.threadflag = false;
        this.waiting = false;
    }
    
    public void CreateBG() {
        this.buffer2 = this.createImage(this.WIDTH, this.HEIGHT);
        (this.BGpad = this.buffer2.getGraphics()).setColor(this.COLORS[this.BORDERCOLOR]);
        this.BGpad.fillRect(0, 0, this.WIDTH, this.HEIGHT);
        if (this.TITLE.equals(" ")) {
            this.TITLE = " ";
        }
        else {
            this.fontMetrics = this.getFontMetrics(this.FONTS[this.TITLEFONT]);
            this.y = this.fontMetrics.getHeight();
            this.x = this.fontMetrics.stringWidth(this.TITLE);
            this.BGpad.setColor(this.COLORS[this.BGCOLOR]);
            this.BGpad.fillRect(this.BORDERWIDTH, this.BORDERWIDTH, this.WIDTH - 2 * this.BORDERWIDTH, this.BORDERWIDTH + this.y);
            this.BGpad.setColor(this.COLORS[this.TITLECOLOR]);
            this.BGpad.setFont(this.FONTS[this.TITLEFONT]);
            this.BGpad.drawString(this.TITLE, (this.WIDTH - this.x) / 2, this.BORDERWIDTH + this.y);
        }
    }
    
    public void CreateText() {
        this.buffer3 = this.createImage(this.TXTWIDTH, this.TXTHEIGHT);
        (this.TEXT = this.buffer3.getGraphics()).setColor(this.COLORS[this.BGCOLOR]);
        this.TEXT.fillRect(0, 0, this.TXTWIDTH, this.TXTHEIGHT);
        this.x = 5;
        this.y = -this.YOFFSET;
        this.CURRENTLINE = this.FIRSTLINE;
        do {
            this.fontMetrics = this.getFontMetrics(this.FONTS[this.TEXTFONT[this.CURRENTLINE]]);
            this.y += this.fontMetrics.getHeight();
            this.TEXT.setColor(this.COLORS[this.TEXTCOLOR[this.CURRENTLINE]]);
            this.TEXT.setFont(this.FONTS[this.TEXTFONT[this.CURRENTLINE]]);
            this.TEXT.drawString(this.TEXTLINES[this.CURRENTLINE], this.x, this.y);
            ++this.CURRENTLINE;
            if (this.CURRENTLINE >= this.MAXLINES) {
                this.CURRENTLINE = 0;
            }
        } while (this.y < this.TXTHEIGHT);
    }
    
    public void init() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.FIRST = 0;
        this.NEXTPAGE = 0;
        this.WIDTH = this.getSize().width;
        this.HEIGHT = this.getSize().height;
        this.FONTS = new Font[11];
        this.i = 1;
        while (this.i < 11) {
            this.FT = this.parmgetstr("FONT" + this.i, "Courier|N");
            this.st = new StringTokenizer(this.FT, "|");
            if (this.st.hasMoreTokens()) {
                this.Fonttype = this.st.nextToken();
            }
            else {
                this.Fonttype = "Courier";
            }
            if (this.st.hasMoreTokens()) {
                this.Fontstyle = this.st.nextToken();
            }
            else {
                this.Fontstyle = "N";
            }
            if (this.st.hasMoreTokens()) {
                this.Fontsize = Integer.parseInt(this.st.nextToken());
            }
            else {
                this.Fontsize = 10;
            }
            if (this.Fontstyle.equals("B")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 1, this.Fontsize);
            }
            else if (this.Fontstyle.equals("I")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 2, this.Fontsize);
            }
            else if (this.Fontstyle.equals("BI") || this.Fontstyle.equals("IB")) {
                this.FONTS[this.i] = new Font(this.Fonttype, 3, this.Fontsize);
            }
            else {
                this.FONTS[this.i] = new Font(this.Fonttype, 0, this.Fontsize);
            }
            ++this.i;
        }
        this.FONTS[0] = new Font("Helvetica", 0, 10);
        this.COLORS = new Color[13];
        this.i = 0;
        while (this.i < 10) {
            this.SC = this.getParameter("COLOR" + (this.i + 1));
            if (this.SC == null) {
                if (this.i == 0) {
                    this.SC = "0|0|0";
                }
                else {
                    this.SC = "255|255|255";
                }
            }
            this.st = new StringTokenizer(this.SC, "|");
            this.COLORS[this.i + 1] = new Color(Math.abs(Integer.parseInt(this.st.nextToken())), Math.abs(Integer.parseInt(this.st.nextToken())), Math.abs(Integer.parseInt(this.st.nextToken())));
            ++this.i;
        }
        this.COLORS[0] = new Color(255, 255, 255);
        this.COLORS[11] = new Color(255, 20, 50);
        this.COLORS[12] = new Color(0, 0, 0);
        this.TITLE = this.parmgetstr("TITLE", " ");
        this.TITLEFONT = this.parmgetint("TITLEFONT", 0, 0, 9);
        this.TITLECOLOR = this.parmgetint("TITLECOLOR", 12, 1, 10);
        this.BGCOLOR = this.parmgetint("BGCOLOR", 0, 1, 10);
        this.BORDERWIDTH = this.parmgetint("BORDERWIDTH", 0, 0, 20);
        this.BORDERCOLOR = this.parmgetint("BORDERCOLOR", 0, 1, 10);
        this.SCROLLSPEED = this.parmgetint("SCROLLSPEED", 97, 0, 100);
        this.DISPLAYTIME = this.parmgetint("DISPLAYTIME", 50, 0, 1000);
        this.MAXLINES = this.parmgetint("MAXLINES", 1, 1, 10000);
        this.SPEED = 105 - this.SCROLLSPEED;
        if (this.SPEED < 5) {
            this.SPEED = 5;
        }
        this.TEXTFONT = new int[this.MAXLINES];
        this.TEXTCOLOR = new int[this.MAXLINES];
        this.TEXTLINES = new String[this.MAXLINES];
        this.i = 0;
        while (this.i < this.MAXLINES) {
            this.SC = this.parmgetstr("TEXTLINE" + (this.i + 1), "1|1| ");
            if (this.SC == null) {
                this.SC = "1|1| ";
            }
            this.st = new StringTokenizer(this.SC, "|");
            this.TEXTFONT[this.i] = Math.abs(Integer.parseInt(this.st.nextToken()));
            this.TEXTCOLOR[this.i] = Math.abs(Integer.parseInt(this.st.nextToken()));
            if (this.st.hasMoreTokens()) {
                this.TEXTLINES[this.i] = this.st.nextToken();
            }
            else {
                this.TEXTLINES[this.i] = " ";
            }
            if (this.TEXTCOLOR[this.i] > 11) {
                this.TEXTCOLOR[this.i] = 12;
            }
            if (this.TEXTCOLOR[this.i] < 1) {
                this.TEXTCOLOR[this.i] = 0;
            }
            if (this.TEXTFONT[this.i] > 10 || this.TEXTFONT[this.i] < 1) {
                this.TEXTFONT[this.i] = 0;
            }
            ++this.i;
        }
        this.FIRSTLINE = 0;
        this.TXTWIDTH = this.WIDTH - 2 * this.BORDERWIDTH;
        if (this.TITLE.equals(" ")) {
            this.TXTHEIGHT = this.HEIGHT - 2 * this.BORDERWIDTH;
        }
        else {
            this.fontMetrics = this.getFontMetrics(this.FONTS[this.TITLEFONT]);
            this.TXTHEIGHT = this.HEIGHT - 2 * this.BORDERWIDTH - this.fontMetrics.getHeight() - 20;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getY() > this.HEIGHT - 20 - this.BORDERWIDTH) {
            this.HIGHLIGHT = 1;
        }
        else {
            this.HIGHLIGHT = 0;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getY() > this.HEIGHT - 20 - this.BORDERWIDTH) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.net800.co.uk/netstart/sirius/"), "SIRIUS");
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.FIRST < 1) {
            this.CreateBG();
            this.buffer = this.createImage(this.WIDTH, this.HEIGHT);
            this.pad = this.buffer.getGraphics();
            this.FIRST = 1;
            this.pad.drawImage(this.buffer2, 0, 0, this);
        }
        this.CreateText();
        this.pad.drawImage(this.buffer3, this.BORDERWIDTH, this.HEIGHT - this.TXTHEIGHT - this.BORDERWIDTH - 20, this);
        this.pad.setColor(this.COLORS[12]);
        this.pad.fillRect(this.BORDERWIDTH, this.HEIGHT - 20 - this.BORDERWIDTH, this.WIDTH - 2 * this.BORDERWIDTH, this.HEIGHT - this.BORDERWIDTH);
        if (this.HIGHLIGHT == 0) {
            this.pad.setColor(this.COLORS[0]);
        }
        else {
            this.pad.setColor(this.COLORS[11]);
        }
        this.pad.setFont(this.FONTS[0]);
        this.pad.drawString("More Applets by Sirius - Click Here", this.BORDERWIDTH + 5, this.HEIGHT - this.BORDERWIDTH - 5);
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public int parmgetint(final String s, final int n, final int n2, final int n3) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else {
            int1 = Integer.parseInt(parameter);
            if (int1 > n3) {
                int1 = n3;
            }
            if (int1 < n2) {
                int1 = n2;
            }
        }
        return int1;
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public void run() {
        while (this.threadflag) {
            this.fontMetrics = this.getFontMetrics(this.FONTS[this.TEXTFONT[this.FIRSTLINE]]);
            this.k = this.fontMetrics.getHeight();
            if (this.YOFFSET < this.k) {
                ++this.YOFFSET;
            }
            else {
                this.YOFFSET = 0;
                ++this.FIRSTLINE;
            }
            if (this.FIRSTLINE >= this.MAXLINES) {
                this.FIRSTLINE = 0;
            }
            this.repaint();
            if (this.FIRSTLINE == this.NEXTPAGE) {
                this.waiting = true;
                this.NEXTPAGE = this.CURRENTLINE - 1;
                if (this.NEXTPAGE < 0 || this.NEXTPAGE < this.FIRSTLINE) {
                    this.NEXTPAGE = 0;
                }
            }
            if (this.waiting) {
                try {
                    Thread.sleep(10 * this.DISPLAYTIME);
                }
                catch (InterruptedException ex) {
                    System.out.println("Error - The thread failed to sleep");
                }
                this.waiting = false;
            }
            else {
                try {
                    Thread.sleep(this.SPEED);
                }
                catch (InterruptedException ex2) {
                    System.out.println("Error - The thread failed to sleep");
                }
            }
        }
    }
    
    public void start() {
        this.mainthread = new Thread(this);
        this.threadflag = true;
        this.mainthread.start();
    }
    
    public void stop() {
        this.threadflag = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
