import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tinySlideShow extends Applet implements Runnable
{
    Thread runner;
    int delay;
    int minIndex;
    int maxIndex;
    int current;
    int bb_height;
    int ld_x;
    int ld_y;
    int numIntDig;
    String loadflag$;
    String loadstring$;
    String sound$;
    String fileLength$;
    String filePre$;
    String fileType$;
    String mode$;
    Image[] images;
    boolean runflag;
    boolean loading;
    boolean preload;
    Font outFont;
    Image offImage;
    Image curImage;
    Image button;
    Image b_on;
    Image b_off;
    Image backgr;
    Graphics offGrfx;
    AudioClip sound;
    Color background;
    Color fontColor;
    
    public void init() {
        final String parameter = this.getParameter("DELAY");
        this.mode$ = this.getParameter("MODE");
        this.fileLength$ = this.getParameter("FILELENGTH");
        this.filePre$ = this.getParameter("FILEPREFIX");
        this.fileType$ = this.getParameter("FILETYPE");
        final String parameter2 = this.getParameter("MININDEX");
        final String parameter3 = this.getParameter("MAXINDEX");
        final String parameter4 = this.getParameter("STARTINDEX");
        final String parameter5 = this.getParameter("BGRED");
        final String parameter6 = this.getParameter("BGGREEN");
        final String parameter7 = this.getParameter("BGBLUE");
        final String parameter8 = this.getParameter("FGRED");
        final String parameter9 = this.getParameter("FGGREEN");
        final String parameter10 = this.getParameter("FGBLUE");
        final String parameter11 = this.getParameter("FONTNAME");
        final String parameter12 = this.getParameter("FONTSIZE");
        final String parameter13 = this.getParameter("LOADINGX");
        final String parameter14 = this.getParameter("LOADINGY");
        this.loadflag$ = this.getParameter("LOADFLAG");
        this.loadstring$ = this.getParameter("LOADSTRING");
        final String parameter15 = this.getParameter("BUTTONBARON");
        final String parameter16 = this.getParameter("BUTTONBAROFF");
        final String parameter17 = this.getParameter("BUTTONBARHEIGHT");
        final String parameter18 = this.getParameter("BG");
        this.sound$ = this.getParameter("SOUND");
        if (this.sound$ != null) {
            this.sound = this.getAudioClip(this.getDocumentBase(), this.sound$);
        }
        if (this.fileLength$ != null) {
            this.numIntDig = Integer.parseInt(this.fileLength$) - this.filePre$.length();
        }
        if (parameter2 != null) {
            this.minIndex = Integer.parseInt(parameter2);
        }
        if (parameter3 != null) {
            this.maxIndex = Integer.parseInt(parameter3);
        }
        if (this.mode$ == null) {
            this.mode$ = new String("XXXXXXX");
        }
        if (this.mode$.equalsIgnoreCase("PRELOAD")) {
            for (int i = this.minIndex; i <= this.maxIndex; ++i) {
                this.images[i] = this.getImage(this.getDocumentBase(), String.valueOf(this.filePre$) + this.padder(this.numIntDig, Integer.toString(i)) + this.fileType$);
            }
        }
        this.outFont = this.getFont();
        if (parameter12 != null && parameter11 != null) {
            this.outFont = new Font(parameter11, 0, Integer.parseInt(parameter12));
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.outFont);
        if (this.loadstring$ == null) {
            this.loadstring$ = "Loading...";
        }
        if (parameter13 != null) {
            this.ld_x = Integer.parseInt(parameter13);
        }
        else {
            this.ld_x = (int)(0.5 * (this.size().width - fontMetrics.stringWidth(this.loadstring$)));
        }
        if (parameter14 != null) {
            this.ld_y = Integer.parseInt(parameter14);
        }
        else {
            this.ld_y = (int)(0.5 * (this.size().height - fontMetrics.getHeight()));
        }
        if (parameter18 != null) {
            this.backgr = this.getImage(this.getDocumentBase(), parameter18);
        }
        this.b_on = this.getImage(this.getDocumentBase(), parameter15);
        if (parameter16 != null) {
            this.b_off = this.getImage(this.getDocumentBase(), parameter16);
        }
        else {
            this.b_off = this.b_on;
        }
        this.button = this.b_on;
        if (parameter17 != null) {
            this.bb_height = Integer.parseInt(parameter17);
        }
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        int int4 = 255;
        int int5 = 255;
        int int6 = 255;
        if (parameter5 != null) {
            int1 = Integer.parseInt(parameter5);
        }
        if (parameter6 != null) {
            int2 = Integer.parseInt(parameter6);
        }
        if (parameter7 != null) {
            int3 = Integer.parseInt(parameter7);
        }
        if (parameter8 != null) {
            int4 = Integer.parseInt(parameter8);
        }
        if (parameter9 != null) {
            int5 = Integer.parseInt(parameter9);
        }
        if (parameter10 != null) {
            int6 = Integer.parseInt(parameter10);
        }
        this.fontColor = new Color(int4, int5, int6);
        this.setBackground(this.background = new Color(int1, int2, int3));
        if (parameter != null) {
            this.delay = Integer.parseInt(parameter);
        }
        if (this.mode$.equalsIgnoreCase("PRELOAD")) {
            this.preload = true;
        }
        if (parameter4 != null) {
            this.current = Integer.parseInt(parameter4) - 1;
        }
        else {
            this.current = this.minIndex - 1;
        }
        if (this.loadflag$ == null) {
            this.loadflag$ = new String("X");
        }
        this.curImage = null;
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
    }
    
    public String padder(final int n, final String s) {
        final char[] array = new char[n];
        String s2;
        if (this.fileLength$ != null) {
            final int length = s.length();
            final int n2 = n - length;
            for (int i = 0; i < n; ++i) {
                array[i] = '0';
            }
            for (int j = 0; j < length; ++j) {
                array[j + n2] = s.charAt(j);
            }
            s2 = new String(array);
        }
        else {
            s2 = s;
        }
        return s2;
    }
    
    public void drawLoadString() {
        if (this.loadflag$.equalsIgnoreCase("Y")) {
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this.fontColor);
            try {
                graphics.setFont(this.outFont);
            }
            catch (NullPointerException ex) {}
            graphics.drawString(this.loadstring$, this.ld_x, this.ld_y);
        }
    }
    
    public void getNextImage(final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (this.preload) {
            this.curImage = this.images[n];
        }
        else {
            this.drawLoadString();
            if (this.curImage != null) {
                this.curImage.flush();
            }
            mediaTracker.addImage(this.curImage = this.getImage(this.getDocumentBase(), String.valueOf(this.filePre$) + this.padder(this.numIntDig, Integer.toString(n)) + this.fileType$), n);
            mediaTracker.checkID(n, true);
            try {
                while (!mediaTracker.checkID(n, true)) {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException ex) {
                        mediaTracker.removeImage(this.curImage);
                        return;
                    }
                }
            }
            catch (NullPointerException ex2) {
                mediaTracker.removeImage(this.curImage);
                return;
            }
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.backgr != null) {
            this.offGrfx.drawImage(this.backgr, 0, 0, this);
        }
        if (this.curImage != null) {
            this.offGrfx.drawImage(this.curImage, (this.size().width - this.curImage.getWidth(null)) / 2, (this.size().height - this.bb_height - this.curImage.getHeight(null)) / 2, this);
        }
        this.offGrfx.drawImage(this.button, 0, this.size().height - this.bb_height, this);
        try {
            graphics.drawImage(this.offImage, 0, 0, this);
        }
        catch (NullPointerException ex) {}
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.b_on, 0);
        mediaTracker.addImage(this.b_off, 0);
        if (this.backgr != null) {
            mediaTracker.addImage(this.backgr, 0);
        }
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.drawLoadString();
        if (this.preload) {
            for (int i = this.minIndex; i <= this.maxIndex; ++i) {
                mediaTracker.addImage(this.images[i], 1);
            }
            try {
                mediaTracker.waitForID(1);
            }
            catch (InterruptedException ex2) {}
        }
        while (true) {
            ++this.current;
            if (this.current > this.maxIndex) {
                this.current = this.minIndex;
            }
            this.getNextImage(this.current);
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void click() {
        if (this.sound$ != null) {
            this.sound.play();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 > this.size().height - this.bb_height) {
            this.click();
            if (n < this.size().width / 5) {
                this.runflag = false;
                this.runner.suspend();
                this.button = this.b_off;
                this.getNextImage(this.current = this.minIndex);
            }
            else if (n < this.size().width / 5 * 2) {
                this.runflag = false;
                this.runner.suspend();
                this.button = this.b_off;
                if (this.current != this.minIndex) {
                    --this.current;
                }
                else {
                    this.current = this.maxIndex;
                }
                this.getNextImage(this.current);
            }
            else if (n < this.size().width / 5 * 3) {
                if (this.runflag) {
                    this.runflag = false;
                    this.runner.suspend();
                    this.button = this.b_off;
                    this.repaint();
                }
                else {
                    this.runflag = true;
                    this.runner.resume();
                    this.button = this.b_on;
                    this.repaint();
                }
            }
            else if (n < this.size().width / 5 * 4) {
                if (this.current < this.maxIndex) {
                    ++this.current;
                }
                else {
                    this.current = this.minIndex;
                }
                this.runflag = false;
                this.runner.suspend();
                this.button = this.b_off;
                this.getNextImage(this.current);
            }
            else {
                this.runflag = false;
                this.runner.suspend();
                this.button = this.b_off;
                this.getNextImage(this.current = this.maxIndex);
            }
        }
        return true;
    }
    
    public tinySlideShow() {
        this.delay = 5000;
        this.minIndex = 1;
        this.maxIndex = 1;
        this.bb_height = 20;
        this.images = new Image[5000];
        this.runflag = true;
        this.loading = false;
        this.preload = false;
    }
}
