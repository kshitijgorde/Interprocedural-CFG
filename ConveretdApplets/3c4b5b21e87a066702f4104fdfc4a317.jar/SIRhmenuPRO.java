import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRhmenuPRO extends Applet implements MouseMotionListener, MouseListener
{
    boolean started;
    boolean NOBUTTONS;
    boolean OFF;
    int[] pixs;
    int index;
    String SC;
    int RED;
    int GREEN;
    int BLUE;
    int x;
    int i;
    int k;
    int k2;
    String TEMPSTR;
    StringTokenizer st;
    StringTokenizer st2;
    StringTokenizer st3;
    String FT;
    String Fonttype;
    String Fontstyle;
    int Fontsize;
    int SLY;
    int UPDOWN;
    int FONTOFFSET;
    int WIDTH;
    int HEIGHT;
    int TITLEHEIGHT;
    int TITLEWIDTH;
    int TITLEx;
    int TITLEy;
    Image BACKIMAGE;
    Image TITLEIMAGE;
    int BALIGN;
    Color SBGCOLOR;
    Color BGCOLOR;
    String IMAGES;
    String BGIMAGE;
    String TITLEIMG;
    String TITLEALIGN;
    String BUTTONALIGN;
    int BORDER;
    String[] BUTTONARRAY;
    int NO_BUTTONS;
    String TA;
    String BA;
    public Image buffer;
    Graphics pad;
    Image[][] BtnImg;
    Image Btnimage;
    int[][] BTNSIZE;
    String[][] BTNINFO;
    AudioClip[] BTNAUDIO;
    int[] STATE;
    Color SL_BGCOLOR;
    Color SL_HBGCOLOR;
    Color SL_TXTCOLOR;
    Color SL_HTXTCOLOR;
    Font SL_FONT;
    int[][] SL_NITEMS;
    String[][][] SL_ARRAY;
    int[][][] SL_COORDS;
    static AppletContext APPLETCONTEXT;
    
    public void init() {
        SIRhmenuPRO.APPLETCONTEXT = this.getAppletContext();
        new Evaluation();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setBackground(this.SBGCOLOR = this.parmgetcolor("STARTUPCOLOR", "255|255|255"));
    }
    
    public void startUp(final Graphics graphics) {
        this.started = true;
        final String parmgetstr = this.parmgetstr("LOADINGMESSAGE", "Loading Please Wait...");
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.setColor(this.parmgetcolor("STEXTCOLOR", "0|0|0"));
        graphics.drawString(parmgetstr, (this.size().width - fontMetrics.stringWidth(parmgetstr)) / 2, (this.size().height + fontMetrics.getHeight()) / 2);
        this.WIDTH = this.getSize().width;
        this.HEIGHT = this.getSize().height;
        this.BGCOLOR = new Color(255, 255, 255);
        this.SL_BGCOLOR = new Color(255, 255, 255);
        this.SL_HBGCOLOR = new Color(0, 0, 0);
        this.SL_TXTCOLOR = new Color(0, 0, 0);
        this.SL_HTXTCOLOR = new Color(255, 255, 255);
        this.IMAGES = this.parmgetstr("IMAGES", " ");
        this.BGIMAGE = this.parmgetstr("BGIMAGE", " ");
        this.TITLEIMG = this.parmgetstr("TITLEIMG", " ");
        this.TITLEALIGN = this.parmgetstr("TITLEALIGN", "center");
        this.BORDER = this.parmgetint("BORDER", 0, 0, this.WIDTH / 2);
        this.BUTTONALIGN = this.parmgetstr("BUTTONALIGN", "center");
        this.SC = this.parmgetstr("BGCOLOR", "255|255|255");
        if (this.SC == null) {
            this.SC = "255|255|255";
        }
        this.st = new StringTokenizer(this.SC, "|");
        if (this.st.hasMoreTokens()) {
            this.RED = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.RED = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.GREEN = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.GREEN = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.BLUE = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.BLUE = 0;
        }
        this.BGCOLOR = new Color(this.RED, this.GREEN, this.BLUE);
        this.SC = this.parmgetstr("MBGCOLOR", "255|255|255");
        if (this.SC == null) {
            this.SC = "255|255|255";
        }
        this.st = new StringTokenizer(this.SC, "|");
        if (this.st.hasMoreTokens()) {
            this.RED = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.RED = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.GREEN = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.GREEN = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.BLUE = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.BLUE = 0;
        }
        this.SL_BGCOLOR = new Color(this.RED, this.GREEN, this.BLUE);
        this.SC = this.parmgetstr("MHBGCOLOR", "0|0|0");
        if (this.SC == null) {
            this.SC = "255|255|255";
        }
        this.st = new StringTokenizer(this.SC, "|");
        if (this.st.hasMoreTokens()) {
            this.RED = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.RED = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.GREEN = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.GREEN = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.BLUE = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.BLUE = 0;
        }
        this.SL_HBGCOLOR = new Color(this.RED, this.GREEN, this.BLUE);
        this.SC = this.parmgetstr("MTXTCOLOR", "0|0|0");
        if (this.SC == null) {
            this.SC = "255|255|255";
        }
        this.st = new StringTokenizer(this.SC, "|");
        if (this.st.hasMoreTokens()) {
            this.RED = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.RED = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.GREEN = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.GREEN = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.BLUE = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.BLUE = 0;
        }
        this.SL_TXTCOLOR = new Color(this.RED, this.GREEN, this.BLUE);
        this.SC = this.parmgetstr("MHTXTCOLOR", "255|255|255");
        if (this.SC == null) {
            this.SC = "255|255|255";
        }
        this.st = new StringTokenizer(this.SC, "|");
        if (this.st.hasMoreTokens()) {
            this.RED = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.RED = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.GREEN = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.GREEN = 0;
        }
        if (this.st.hasMoreTokens()) {
            this.BLUE = Math.abs(Integer.parseInt(this.st.nextToken()));
        }
        else {
            this.BLUE = 0;
        }
        this.SL_HTXTCOLOR = new Color(this.RED, this.GREEN, this.BLUE);
        this.FT = this.parmgetstr("FONT", "Courier|N|12");
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
            this.Fontsize = 12;
        }
        if (this.Fontstyle.equals("B")) {
            this.SL_FONT = new Font(this.Fonttype, 1, this.Fontsize);
        }
        else if (this.Fontstyle.equals("I")) {
            this.SL_FONT = new Font(this.Fonttype, 2, this.Fontsize);
        }
        else if (this.Fontstyle.equals("BI") || this.Fontstyle.equals("IB")) {
            this.SL_FONT = new Font(this.Fonttype, 3, this.Fontsize);
        }
        else {
            this.SL_FONT = new Font(this.Fonttype, 0, this.Fontsize);
        }
        this.NO_BUTTONS = 0;
        this.BUTTONARRAY = new String[1000];
        this.TEMPSTR = this.getParameter("BUTTON" + (this.NO_BUTTONS + 1));
        do {
            this.BUTTONARRAY[this.NO_BUTTONS] = this.TEMPSTR;
            ++this.NO_BUTTONS;
            this.TEMPSTR = this.getParameter("BUTTON" + (this.NO_BUTTONS + 1));
        } while (this.TEMPSTR != null && this.NO_BUTTONS < 1000);
        this.BTNSIZE = new int[this.NO_BUTTONS][7];
        this.BTNINFO = new String[this.NO_BUTTONS][3];
        this.BTNAUDIO = new AudioClip[this.NO_BUTTONS];
        this.STATE = new int[this.NO_BUTTONS];
        this.BtnImg = new Image[this.NO_BUTTONS][3];
        this.SL_NITEMS = new int[this.NO_BUTTONS][2];
        this.SL_ARRAY = new String[this.NO_BUTTONS][100][3];
        this.SL_COORDS = new int[this.NO_BUTTONS][100][5];
        this.i = 0;
        while (this.i < this.NO_BUTTONS) {
            this.SL_NITEMS[this.i][0] = 0;
            this.SL_NITEMS[this.i][1] = 0;
            ++this.i;
        }
        this.x = 0;
        if (this.IMAGES.equals(" ")) {
            this.NOBUTTONS = true;
        }
        else {
            this.Btnimage = this.downloadImage(this.IMAGES);
        }
        if (!this.BGIMAGE.equals(" ")) {
            this.BACKIMAGE = this.downloadImage(this.BGIMAGE);
        }
        if (!this.TITLEIMG.equals(" ")) {
            this.TITLEIMAGE = this.downloadImage(this.TITLEIMG);
            this.TITLEHEIGHT = this.TITLEIMAGE.getHeight(this);
            this.TITLEWIDTH = this.TITLEIMAGE.getWidth(this);
        }
        this.TITLEx = this.WIDTH / 2 - this.TITLEWIDTH / 2;
        this.TITLEy = this.BORDER;
        this.TA = this.TITLEALIGN.substring(0, 1);
        if (this.TA.equals("l") || this.TA.equals("L")) {
            this.TITLEx = this.BORDER;
        }
        if (this.TA.equals("r") || this.TA.equals("R")) {
            this.TITLEx = this.WIDTH - this.BORDER - this.TITLEWIDTH;
        }
        this.BALIGN = 1;
        this.BA = this.BUTTONALIGN.substring(0, 1);
        if (this.BA.equals("t") || this.BA.equals("T")) {
            this.BALIGN = 0;
        }
        if (this.BA.equals("b") || this.BA.equals("B")) {
            this.BALIGN = 2;
        }
        final FontMetrics fontMetrics2 = this.getFontMetrics(this.SL_FONT);
        this.FONTOFFSET = fontMetrics2.getHeight() / 3;
        if (!this.NOBUTTONS) {
            this.i = 0;
            while (this.i < this.NO_BUTTONS) {
                this.st = new StringTokenizer(this.BUTTONARRAY[this.i], "|");
                if (this.st.hasMoreTokens()) {
                    this.BTNSIZE[this.i][0] = Math.abs(Integer.parseInt(this.st.nextToken()));
                }
                else {
                    this.BTNSIZE[this.i][0] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.BTNSIZE[this.i][1] = Math.abs(Integer.parseInt(this.st.nextToken()));
                }
                else {
                    this.BTNSIZE[this.i][0] = 0;
                }
                if (this.st.hasMoreTokens()) {
                    this.BTNINFO[this.i][0] = this.st.nextToken();
                }
                else {
                    this.BTNINFO[this.i][0] = " ";
                }
                if (this.st.hasMoreTokens()) {
                    this.BTNINFO[this.i][1] = this.st.nextToken();
                }
                else {
                    this.BTNINFO[this.i][1] = " ";
                }
                if (this.st.hasMoreTokens()) {
                    this.BTNAUDIO[this.i] = this.getAudioClip(this.getDocumentBase(), this.st.nextToken());
                }
                this.BtnImg[this.i][0] = this.createImage(new FilteredImageSource(this.Btnimage.getSource(), new CropImageFilter(this.x, 0, this.BTNSIZE[this.i][0], this.BTNSIZE[this.i][1])));
                this.BtnImg[this.i][1] = this.createImage(new FilteredImageSource(this.Btnimage.getSource(), new CropImageFilter(this.x, this.BTNSIZE[this.i][1], this.BTNSIZE[this.i][0], this.BTNSIZE[this.i][1])));
                this.BtnImg[this.i][2] = this.createImage(new FilteredImageSource(this.Btnimage.getSource(), new CropImageFilter(this.x, 2 * this.BTNSIZE[this.i][1], this.BTNSIZE[this.i][0], this.BTNSIZE[this.i][1])));
                this.BTNSIZE[this.i][2] = this.x + this.BORDER;
                if (this.BALIGN == 0) {
                    this.BTNSIZE[this.i][3] = this.BORDER + this.TITLEHEIGHT;
                }
                if (this.BALIGN == 1) {
                    this.BTNSIZE[this.i][3] = this.HEIGHT / 2 + this.TITLEHEIGHT / 2 - this.BtnImg[this.i][0].getHeight(this) / 2;
                }
                if (this.BALIGN == 2) {
                    this.BTNSIZE[this.i][3] = this.HEIGHT - this.BORDER - this.BtnImg[this.i][this.STATE[this.i]].getHeight(this);
                }
                this.BTNSIZE[this.i][4] = this.x + this.BORDER + this.BTNSIZE[this.i][0];
                this.BTNSIZE[this.i][5] = this.BTNSIZE[this.i][3] + this.BtnImg[this.i][0].getHeight(this);
                if (this.st.hasMoreTokens()) {
                    this.BTNINFO[this.i][2] = this.st.nextToken();
                }
                else {
                    this.BTNINFO[this.i][2] = " ";
                }
                if (this.st.hasMoreTokens()) {
                    this.BTNSIZE[this.i][6] = Math.abs(Integer.parseInt(this.st.nextToken()));
                }
                else {
                    this.BTNSIZE[this.i][6] = -1;
                }
                this.x += this.BTNSIZE[this.i][0];
                this.STATE[this.i] = 0;
                if (!this.BTNINFO[this.i][2].equals(" ")) {
                    if (this.BTNSIZE[this.i][6] == 0) {
                        this.SLY = this.BTNSIZE[this.i][3] - fontMetrics2.getHeight() - 4;
                        this.UPDOWN = -1;
                    }
                    else {
                        this.SLY = this.BTNSIZE[this.i][5];
                        this.UPDOWN = 1;
                    }
                    this.TEMPSTR = this.getParameter(String.valueOf(this.BTNINFO[this.i][2]) + (this.SL_NITEMS[this.i][0] + 1));
                    if (this.TEMPSTR != null) {
                        do {
                            this.st3 = new StringTokenizer(this.TEMPSTR, "|");
                            if (this.st3.hasMoreTokens()) {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][0] = this.st3.nextToken();
                            }
                            else {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][0] = " ";
                            }
                            if (this.st3.hasMoreTokens()) {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][1] = this.st3.nextToken();
                            }
                            else {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][0] = " ";
                            }
                            if (this.st3.hasMoreTokens()) {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][2] = this.st3.nextToken();
                            }
                            else {
                                this.SL_ARRAY[this.i][this.SL_NITEMS[this.i][0]][0] = " ";
                            }
                            this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][0] = this.BTNSIZE[this.i][2] + 5;
                            this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][1] = this.SLY;
                            this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][2] = this.BTNSIZE[this.i][0];
                            this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][3] = fontMetrics2.getHeight() + 4;
                            this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][4] = 0;
                            this.SLY += this.UPDOWN * this.SL_COORDS[this.i][this.SL_NITEMS[this.i][0]][3];
                            final int[] array = this.SL_NITEMS[this.i];
                            final int n = 0;
                            ++array[n];
                            this.TEMPSTR = this.getParameter(String.valueOf(this.BTNINFO[this.i][2]) + (this.SL_NITEMS[this.i][0] + 1));
                        } while (this.TEMPSTR != null && this.SL_NITEMS[this.i][0] < 100);
                    }
                }
                ++this.i;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.started) {
            this.startUp(graphics);
        }
        this.buffer = this.createImage(this.WIDTH, this.HEIGHT);
        this.pad = this.buffer.getGraphics();
        if (this.NOBUTTONS) {
            this.pad.setColor(this.BGCOLOR);
            this.pad.fillRect(0, 0, this.WIDTH, this.HEIGHT);
            this.pad.setColor(this.parmgetcolor("STEXTCOLOR", "0|0|0"));
            this.pad.drawString("ERROR", 10, 10);
            this.pad.drawString("Button Images not Found", 10, 25);
            this.pad.drawString("Menu Cannot Start.", 10, 40);
        }
        else {
            this.pad.setColor(this.BGCOLOR);
            this.pad.fillRect(0, 0, this.WIDTH, this.HEIGHT);
            if (!this.BGIMAGE.equals(" ")) {
                this.pad.drawImage(this.BACKIMAGE, 0, 0, this);
            }
            if (!this.TITLEIMG.equals(" ")) {
                this.pad.drawImage(this.TITLEIMAGE, this.TITLEx, this.TITLEy, this);
            }
            this.x = this.BORDER;
            this.i = 0;
            while (this.i < this.NO_BUTTONS) {
                this.pad.drawImage(this.BtnImg[this.i][this.STATE[this.i]], this.x, this.BTNSIZE[this.i][3], this);
                this.x += this.BTNSIZE[this.i][0];
                ++this.i;
            }
            this.i = 0;
            while (this.i < this.NO_BUTTONS) {
                if (this.SL_NITEMS[this.i][1] == 1 && this.SL_NITEMS[this.i][0] > 0) {
                    this.k = 0;
                    while (this.k < this.SL_NITEMS[this.i][0]) {
                        if (this.SL_COORDS[this.i][this.k][4] == 0) {
                            this.pad.setColor(this.SL_BGCOLOR);
                            this.pad.fillRect(this.SL_COORDS[this.i][this.k][0], this.SL_COORDS[this.i][this.k][1], this.SL_COORDS[this.i][this.k][2], this.SL_COORDS[this.i][this.k][3]);
                            this.pad.setFont(this.SL_FONT);
                            this.pad.setColor(this.SL_TXTCOLOR);
                            this.pad.drawString(this.SL_ARRAY[this.i][this.k][0], this.SL_COORDS[this.i][this.k][0] + 5, this.SL_COORDS[this.i][this.k][1] + this.SL_COORDS[this.i][this.k][3] - this.FONTOFFSET);
                        }
                        else {
                            this.pad.setColor(this.SL_HBGCOLOR);
                            this.pad.fillRect(this.SL_COORDS[this.i][this.k][0], this.SL_COORDS[this.i][this.k][1], this.SL_COORDS[this.i][this.k][2], this.SL_COORDS[this.i][this.k][3]);
                            this.pad.setFont(this.SL_FONT);
                            this.pad.setColor(this.SL_HTXTCOLOR);
                            this.pad.drawString(this.SL_ARRAY[this.i][this.k][0], this.SL_COORDS[this.i][this.k][0] + 5, this.SL_COORDS[this.i][this.k][1] + this.SL_COORDS[this.i][this.k][3] - this.FONTOFFSET);
                        }
                        ++this.k;
                    }
                }
                ++this.i;
            }
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public Color parmgetcolor(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.parmgetstr(s, s2), "|");
        return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
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
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean b = false;
        this.k2 = 0;
        do {
            if (x > this.BTNSIZE[this.k2][2] && x < this.BTNSIZE[this.k2][4] && y > this.BTNSIZE[this.k2][3] && y < this.BTNSIZE[this.k2][5]) {
                b = true;
                this.STATE[this.k2] = 2;
                this.paint(this.getGraphics());
                this.BTNAUDIO[this.k2].play();
                if (this.BTNINFO[this.k2][0] != null && !this.BTNINFO[this.k2][0].equals(" ")) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.BTNINFO[this.k2][0]), this.BTNINFO[this.k2][1]);
                    }
                    catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (this.SL_NITEMS[this.k2][1] == 1) {
                this.i = 0;
                while (this.i < this.SL_NITEMS[this.k2][0]) {
                    if (x > this.SL_COORDS[this.k2][this.i][0] && x < this.SL_COORDS[this.k2][this.i][2] + this.SL_COORDS[this.k2][this.i][0] && y >= this.SL_COORDS[this.k2][this.i][1] && y < this.SL_COORDS[this.k2][this.i][3] + this.SL_COORDS[this.k2][this.i][1]) {
                        this.BTNAUDIO[this.k2].play();
                        if (this.SL_ARRAY[this.k2][this.i][1] != null && !this.SL_ARRAY[this.k2][this.i][1].equals(" ")) {
                            try {
                                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.SL_ARRAY[this.k2][this.i][1]), this.SL_ARRAY[this.k2][this.i][2]);
                            }
                            catch (MalformedURLException ex2) {
                                ex2.printStackTrace();
                            }
                        }
                    }
                    ++this.i;
                }
            }
            ++this.k2;
        } while (!b && this.k2 < this.NO_BUTTONS);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.i = 0;
        while (this.i < this.NO_BUTTONS) {
            this.STATE[this.i] = 0;
            ++this.i;
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
        this.i = 0;
        while (this.i < this.NO_BUTTONS) {
            this.STATE[this.i] = 0;
            ++this.i;
        }
        this.paint(this.getGraphics());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
        this.i = 0;
        while (this.i < this.NO_BUTTONS) {
            this.STATE[this.i] = 0;
            ++this.i;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.k = 0;
        this.OFF = true;
        do {
            if (this.SL_NITEMS[this.k][1] == 1) {
                this.i = 0;
                while (this.i < this.SL_NITEMS[this.k][0]) {
                    if (x > this.SL_COORDS[this.k][this.i][0] && x < this.SL_COORDS[this.k][this.i][2] + this.SL_COORDS[this.k][this.i][0] && y >= this.SL_COORDS[this.k][this.i][1] && y < this.SL_COORDS[this.k][this.i][3] + this.SL_COORDS[this.k][this.i][1]) {
                        this.SL_COORDS[this.k][this.i][4] = 1;
                        this.OFF = false;
                        this.setCursor(new Cursor(12));
                    }
                    else {
                        this.SL_COORDS[this.k][this.i][4] = 0;
                    }
                    ++this.i;
                }
            }
            if (x > this.BTNSIZE[this.k][2] && x < this.BTNSIZE[this.k][4] && y > this.BTNSIZE[this.k][3] && y < this.BTNSIZE[this.k][5]) {
                this.SL_NITEMS[this.k][1] = 1;
                this.STATE[this.k] = 1;
                this.OFF = false;
                if (this.BTNINFO[this.k][0] != null && !this.BTNINFO[this.k][0].equals(" ")) {
                    this.setCursor(new Cursor(12));
                }
            }
            if (this.OFF) {
                this.SL_NITEMS[this.k][1] = 0;
            }
            ++this.k;
            this.OFF = true;
        } while (this.k < this.NO_BUTTONS);
        this.paint(this.getGraphics());
    }
    
    public Image downloadImage(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            this.showStatus("Loading Image...");
            mediaTracker.waitForID(0);
            this.showStatus("");
        }
        catch (InterruptedException ex) {
            return this.createImage(this.size().width, this.size().height);
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        this.pixs = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, this.pixs, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
            this.showStatus("Image loading error");
            return this.createImage(this.size().width, this.size().height);
        }
        final Image image2 = this.createImage(new MemoryImageSource(width, height, this.pixs, 0, width));
        this.index = 0;
        return image2;
    }
    
    public SIRhmenuPRO() {
        this.started = false;
        this.NOBUTTONS = false;
        this.OFF = true;
    }
}
