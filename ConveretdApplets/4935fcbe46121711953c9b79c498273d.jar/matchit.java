import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.util.Date;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class matchit extends Applet implements Runnable
{
    Image[] photo;
    String bgfile;
    Image bgimg;
    int npics;
    Thread t;
    int width;
    int height;
    int delay;
    String[] picfile;
    int picwidth;
    int picheight;
    Color bg;
    Color fg;
    Color butbg;
    Color butfg;
    int row;
    int col;
    int showbut;
    int gridwidth;
    int gridheight;
    AudioClip winaudio;
    AudioClip moveaudio;
    String winfile;
    String clickfile;
    String cardback;
    String linkto;
    String linkframe;
    String linkto2;
    String linkframe2;
    int maxmoves;
    long maxtime;
    String TitleText;
    String NewGameText;
    String SolvePuzzleText;
    Image oi;
    Graphics og;
    int butid;
    Rectangle[] box;
    String[] caption;
    boolean showCopyright;
    int gwidth;
    int gheight;
    int w;
    int h;
    int gameover;
    int[] order;
    int[] flip;
    int moves;
    int movex;
    int movey;
    int minmoves;
    int id1;
    int id2;
    boolean pause;
    String MovesCountText;
    String WinText;
    String LoseText;
    String RecordText;
    int nBlock;
    int gamex;
    int gamey;
    String timer;
    int timerx;
    int timery;
    long tstart;
    long tend;
    long tdelta;
    int frames;
    MediaTracker tracker;
    int loading;
    boolean loaded;
    int titx;
    int tity;
    Color bc;
    boolean registered;
    
    public matchit() {
        this.bgfile = "";
        this.bgimg = null;
        this.npics = 0;
        this.t = null;
        this.delay = 200;
        this.row = 4;
        this.col = 4;
        this.showbut = 1;
        this.linkto = "";
        this.linkframe = "";
        this.linkto2 = "";
        this.linkframe2 = "";
        this.maxmoves = -1;
        this.maxtime = -1L;
        this.oi = null;
        this.og = null;
        this.butid = -1;
        this.box = new Rectangle[2];
        this.caption = new String[] { "New", "Solve" };
        this.showCopyright = false;
        this.gameover = 0;
        this.moves = 0;
        this.movex = -1;
        this.movey = -1;
        this.minmoves = 999;
        this.id1 = -1;
        this.id2 = -1;
        this.pause = false;
        this.nBlock = 0;
        this.gamex = 0;
        this.gamey = 0;
        this.timer = "00:00:00";
        this.timerx = 0;
        this.timery = 0;
        this.tstart = 0L;
        this.tend = 0L;
        this.tdelta = 0L;
        this.frames = 10;
        this.loading = 1;
        this.loaded = false;
        this.titx = 0;
        this.tity = 0;
        this.bc = new Color(160, 160, 160);
        this.registered = false;
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        final String parameter = this.getParameter("regcode");
        parameter.trim();
        parameter.toUpperCase();
        final char c = (char)(parameter.charAt(parameter.length() - 1) - '0');
        if (c + '\u0004' < parameter.length()) {
            final int intValue = new Integer(parameter.substring(c, c + '\u0003'));
            int intValue2 = new Integer(parameter.substring(parameter.length() - 4, parameter.length() - 1));
            final String upperCase = this.getDocumentBase().getHost().toUpperCase();
            char c2 = '\0';
            int n = 0;
            if (upperCase.length() > 0) {
                for (int i = upperCase.length() - 1; i >= 0; --i) {
                    if (upperCase.charAt(i) == '.') {
                        if (++n > 1) {
                            i = -1;
                        }
                    }
                    else if (n < 2 && upperCase.charAt(i) >= 'A' && upperCase.charAt(i) <= 'Z') {
                        c2 += (char)(upperCase.charAt(i) - 'A' + '\n');
                    }
                    else if (n < 2 && upperCase.charAt(i) >= '0' && upperCase.charAt(i) <= '9') {
                        c2 += (char)(upperCase.charAt(i) - '0');
                    }
                }
            }
            else {
                intValue2 = 0;
            }
            if (intValue == '\u0220' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.delay = new Integer(this.getParameter("delay"));
        this.loading = Integer.parseInt(this.getParameter("loading"));
        this.cardback = this.getParameter("cardback");
        this.showbut = new Integer(this.getParameter("showbutton"));
        this.showbut = ((this.showbut > 0) ? 1 : 0);
        this.npics = new Integer(this.getParameter("total"));
        this.picfile = new String[this.npics + 1];
        for (int j = 0; j < this.npics; ++j) {
            this.picfile[j] = this.getParameter("pic" + j);
        }
        this.picfile[this.npics] = this.cardback.trim();
        this.winfile = this.getParameter("winfile");
        this.clickfile = this.getParameter("clickfile");
        final int[] int1 = this.parseInt(this.getParameter("startpos"));
        this.box[0] = new Rectangle(int1[0], int1[1], 50, 20);
        final int[] int2 = this.parseInt(this.getParameter("solvepos"));
        this.box[1] = new Rectangle(int2[0], int2[1], 50, 20);
        final int[] int3 = this.parseInt(this.getParameter("gamepos"));
        this.gamex = int3[0];
        this.gamey = int3[1];
        final int[] int4 = this.parseInt(this.getParameter("titlepos"));
        this.titx = int4[0];
        this.tity = int4[1];
        final int[] int5 = this.parseInt(this.getParameter("timerpos"));
        this.timerx = int5[0];
        this.timery = int5[1];
        this.bgfile = this.getParameter("bgimage").trim();
        final int[] int6 = this.parseInt(this.getParameter("panelbg"));
        this.bg = new Color(int6[0], int6[1], int6[2]);
        final int[] int7 = this.parseInt(this.getParameter("panelfg"));
        this.fg = new Color(int7[0], int7[1], int7[2]);
        final int[] int8 = this.parseInt(this.getParameter("buttonbg"));
        this.butbg = new Color(int8[0], int8[1], int8[2]);
        final int[] int9 = this.parseInt(this.getParameter("buttonfg"));
        this.butfg = new Color(int9[0], int9[1], int9[2]);
        final String[] parse = this.parse(this.getParameter("winlink").trim());
        this.linkto = parse[0];
        this.linkframe = parse[1];
        final String[] parse2 = this.parse(this.getParameter("loselink").trim());
        this.linkto2 = parse2[0];
        this.linkframe2 = parse2[1];
        this.row = new Integer(this.getParameter("row"));
        this.col = new Integer(this.getParameter("col"));
        this.gridwidth = new Integer(this.getParameter("gridwidth"));
        this.gridheight = new Integer(this.getParameter("gridheight"));
        this.TitleText = this.getParameter("TitleText");
        this.maxmoves = new Integer(this.getParameter("maxmoves"));
        if (this.maxmoves <= 0) {
            this.maxmoves = 16777216;
        }
        this.maxtime = new Integer(this.getParameter("maxtime"));
        if (this.maxtime <= 0L) {
            this.maxtime = 36000L;
        }
        else {
            this.maxtime *= 60L;
        }
        this.NewGameText = this.getParameter("NewGameText");
        this.SolvePuzzleText = this.getParameter("SolvePuzzleText");
        if (this.getParameter("MovesCountText") != null) {
            this.MovesCountText = this.getParameter("MovesCountText").trim();
        }
        else {
            this.MovesCountText = "";
        }
        if (this.getParameter("RecordText") != null) {
            this.RecordText = this.getParameter("RecordText").trim();
        }
        else {
            this.RecordText = "";
        }
        this.WinText = this.getParameter("WinText");
        this.LoseText = this.getParameter("LoseText");
        this.winaudio = this.getAudioClip(this.getDocumentBase(), this.winfile);
        this.moveaudio = this.getAudioClip(this.getDocumentBase(), this.clickfile);
        this.photo = new Image[this.npics + 1];
        this.initgame(this.col * this.gridwidth, this.row * this.gridheight, this.gridwidth, this.gridheight);
        this.repaint();
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
        this.resize(this.width, this.height);
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
        }
        this.t = null;
        this.photo = null;
    }
    
    public void run() {
        if (!this.loaded) {
            final Graphics graphics = this.getGraphics();
            this.tracker = new MediaTracker(this);
            for (int i = 0; i < this.npics + 1; ++i) {
                if (this.loading == 1) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.setColor(Color.black);
                    graphics.drawString("Loading \"" + this.picfile[i] + "\"...", 4, 29);
                }
                this.photo[i] = this.getImage(this.getDocumentBase(), this.picfile[i]);
                this.tracker.addImage(this.photo[i], i);
                this.waitForImage(i);
            }
            if (!this.bgfile.trim().toUpperCase().startsWith("NONE")) {
                if (this.loading == 1) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.setColor(Color.black);
                    graphics.drawString("Loading background \"" + this.bgfile + "\"...", 4, 29);
                }
                this.bgimg = this.getImage(this.getDocumentBase(), this.bgfile);
                this.tracker.addImage(this.bgimg, this.npics);
                this.waitForImage(this.npics);
            }
            this.resize(this.width, this.height);
            this.repaint();
            this.loaded = true;
        }
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        int n = 0;
        while (Thread.currentThread() == this.t) {
            if (n++ > 1000) {
                n = 0;
                System.gc();
            }
            this.paint(graphics2);
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex3) {}
            if (this.tstart > 0L) {
                this.tend = System.currentTimeMillis();
                this.tdelta = (this.tend - this.tstart) / 1000L;
                final int n2 = (int)(this.tdelta / 3600L);
                final int n3 = (int)(this.tdelta % 3600L / 60L);
                final int n4 = (int)(this.tdelta % 60L);
                final String s = "";
                String s2;
                if (n2 < 10) {
                    s2 = s + "0" + n2 + ":";
                }
                else {
                    s2 = s + n2 + ":";
                }
                String s3;
                if (n3 < 10) {
                    s3 = s2 + "0" + n3 + ":";
                }
                else {
                    s3 = s2 + n3 + ":";
                }
                String timer;
                if (n4 < 10) {
                    timer = s3 + "0" + n4;
                }
                else {
                    timer = s3 + n4 + "";
                }
                this.timer = timer;
                if (this.gameover == 0 && this.tdelta > this.maxtime) {
                    this.gameover = 2;
                    this.tstart = 0L;
                }
            }
            if (this.gameover == 1 && !this.linkto.equalsIgnoreCase("none")) {
                this.tstart = 0L;
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.linkto), this.linkframe);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.gameover = 0;
            }
            if (this.gameover == 2 && !this.linkto2.equalsIgnoreCase("none")) {
                this.tstart = 0L;
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.linkto2), this.linkframe2);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                this.gameover = 0;
            }
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 5 == 0 && date.getSeconds() < 15) {
                    this.nBlock = 15 - date.getSeconds();
                }
                else {
                    this.nBlock = 0;
                }
            }
        }
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void initgame(final int gwidth, final int gheight, final int w, final int h) {
        this.gwidth = gwidth;
        this.gheight = gheight;
        this.w = w;
        this.h = h;
        this.order = null;
        this.order = new int[this.row * this.col];
        for (int i = 0; i < this.row * this.col; ++i) {
            this.order[i] = i;
        }
        this.flip = null;
        this.flip = new int[this.row * this.col];
        this.flipall(1);
        this.rescramble();
    }
    
    public void flipall(final int n) {
        for (int i = 0; i < this.row * this.col; ++i) {
            this.flip[i] = n;
        }
    }
    
    public void rescramble() {
        for (int n = this.row * this.col, i = 0; i < n; ++i) {
            final int n2 = i;
            int n3 = (int)(Math.round(Math.random() * 10.0 * n * n) % n);
            if (n2 == n3) {
                n3 = (n2 + 1) % n;
            }
            final int n4 = this.order[n2];
            this.order[n2] = this.order[n3];
            this.order[n3] = n4;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.row * this.col;
        if (this.og == null) {
            this.oi = this.createImage(this.width, this.height);
            this.og = this.oi.getGraphics();
        }
        if (!this.loaded) {
            return;
        }
        if (this.nBlock > 0) {
            this.og.setColor(Color.blue);
            this.og.fillRect(0, 0, this.width, this.height);
            this.og.setColor(Color.white);
            final String[] array = { "\"matchit\"", "by", "www.thejmaker.com", "Click to register: " + this.nBlock };
            int n2 = this.height / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.og.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.og.setFont(new Font("Helvetica", 1, 11));
                }
                this.og.drawString(array[i], (this.width - this.og.getFontMetrics().stringWidth(array[i])) / 2, n2);
                if (i == 0) {
                    n2 += 16;
                }
                else if (i == 2) {
                    this.og.setColor(Color.yellow);
                    n2 += 20;
                }
                else {
                    n2 += 13;
                }
            }
            graphics.drawImage(this.oi, 0, 0, this);
            return;
        }
        if (this.bgimg != null) {
            for (int j = 0; j < this.height - 1; j += this.bgimg.getHeight(this)) {
                for (int k = 0; k < this.width - 1; k += this.bgimg.getWidth(this)) {
                    this.og.drawImage(this.bgimg, k, j, this);
                }
            }
        }
        else {
            this.og.setColor(this.bg);
            this.og.fillRect(0, 0, this.width, this.height);
        }
        this.og.setFont(new Font("Helvetica", 1, 20));
        this.og.setColor(this.fg);
        this.og.drawString(this.TitleText, this.titx, this.tity);
        for (int l = 0; l < n; ++l) {
            final int n3 = this.gamex + l % this.col * this.w;
            final int n4 = this.gamey + l / this.col * this.h;
            final int n5 = (this.w - this.photo[this.order[l] / 2 % this.npics].getWidth(this)) / 2;
            final int n6 = (this.h - this.photo[this.order[l] / 2 % this.npics].getHeight(this)) / 2;
            if (this.flip[l] == 1) {
                this.og.drawImage(this.photo[this.order[l] / 2 % this.npics], n3 + n5, n4 + n6, this);
            }
            else if (this.flip[l] == -1) {
                this.og.drawImage(this.photo[this.npics], n3, n4, this);
            }
            if (this.flip[l] != 0) {
                this.og.setColor(this.bc);
                this.og.draw3DRect(n3, n4, this.w - 1, this.h - 1, true);
                this.og.draw3DRect(n3 + 1, n4 + 1, this.w - 3, this.h - 3, true);
            }
        }
        if (this.pause) {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            this.pause = false;
        }
        if (this.id1 != -1 && this.id2 != -1) {
            if (this.order[this.id1] / 2 % this.npics == this.order[this.id2] / 2 % this.npics) {
                this.flip[this.id1] = (this.flip[this.id2] = 0);
            }
            else {
                this.flip[this.id1] = (this.flip[this.id2] = -1);
            }
            final int n7 = -1;
            this.id2 = n7;
            this.id1 = n7;
            this.pause = true;
        }
        if (this.movex > -1 && this.movey > -1) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String string = this.MovesCountText + " " + this.moves;
            this.og.setColor(Color.blue);
            final int n8 = this.gamex + 5;
            final int n9 = this.gamey + 24;
            this.og.drawString(string, n8, n9 - 13);
            this.og.drawString(string, n8, n9 - 11);
            this.og.drawString(string, n8 - 1, n9 - 12);
            this.og.drawString(string, n8 + 1, n9 - 12);
            this.og.setColor(Color.cyan);
            this.og.drawString(string, n8, n9 - 12);
            if (this.RecordText.length() > 0) {
                String string2 = this.RecordText + " " + this.minmoves;
                if (this.minmoves >= 999) {
                    string2 = "";
                }
                this.og.setColor(Color.red);
                this.og.drawString(string2, n8, n9 - 1);
                this.og.drawString(string2, n8, n9 + 1);
                this.og.drawString(string2, n8 - 1, n9);
                this.og.drawString(string2, n8 + 1, n9);
                this.og.setColor(Color.cyan);
                this.og.drawString(string2, n8, n9);
            }
        }
        if (this.moves > this.maxmoves) {
            this.tstart = 0L;
            this.gameover = 2;
        }
        this.og.setColor(this.butfg);
        this.og.setFont(new Font("Helvetica", 1, 11));
        for (int n10 = 0; n10 < 1 + this.showbut; ++n10) {
            this.og.setColor(this.butbg);
            this.og.fillRoundRect(this.box[n10].x, this.box[n10].y, this.box[n10].width, this.box[n10].height, 10, 10);
            if (n10 == this.butid) {
                this.og.setColor(Color.red);
            }
            else {
                this.og.setColor(this.butfg);
            }
            this.og.drawRoundRect(this.box[n10].x, this.box[n10].y, this.box[n10].width, this.box[n10].height, 10, 10);
            this.og.drawString(this.caption[n10], this.box[n10].x + (this.box[n10].width - this.og.getFontMetrics().stringWidth(this.caption[n10])) / 2, this.box[n10].y + this.box[n10].height / 2 + 5);
        }
        this.og.setColor(this.fg);
        this.og.setFont(new Font("Helvetica", 1, 12));
        this.og.drawString(this.timer, this.timerx, this.timery);
        if (this.gameover == 0 && this.moves > 0) {
            boolean b = true;
            for (int n11 = 0; n11 < n; ++n11) {
                if (this.flip[n11] != 0) {
                    b = false;
                    break;
                }
            }
            if (b) {
                this.winaudio.play();
                this.tstart = 0L;
                this.gameover = 1;
            }
        }
        if (this.gameover > 0) {
            this.tstart = 0L;
            if (this.gameover == 1 && this.moves < this.minmoves) {
                this.minmoves = this.moves;
            }
            this.og.setFont(new Font("Helvetica", 1, 20));
            String s = this.WinText;
            if (this.gameover == 2) {
                s = this.LoseText;
            }
            final int n12 = (this.width - this.og.getFontMetrics().stringWidth(s)) / 2;
            final int n13 = this.height / 2;
            this.og.setColor(Color.red);
            this.og.drawString(s, n12, n13 - 1);
            this.og.drawString(s, n12, n13 + 1);
            this.og.drawString(s, n12 - 1, n13);
            this.og.drawString(s, n12 + 1, n13);
            this.og.setColor(Color.white);
            this.og.drawString(s, n12, n13);
            for (int n14 = 0; n14 < n; ++n14) {
                this.flip[n14] = 1;
            }
        }
        this.og.setColor(this.bg);
        this.og.draw3DRect(0, 0, this.width - 1, this.height - 1, true);
        this.og.draw3DRect(1, 1, this.width - 3, this.height - 3, true);
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s2 = "matchit (C) thejmaker.com 2003";
            final int n15 = this.width - this.og.getFontMetrics().stringWidth(s2) - 4;
            final int n16 = this.height - 5;
            this.og.setColor(Color.blue);
            this.og.drawString(s2, n15, n16 - 1);
            this.og.drawString(s2, n15, n16 + 1);
            this.og.drawString(s2, n15 - 1, n16);
            this.og.drawString(s2, n15 + 1, n16);
            this.og.setColor(Color.white);
            this.og.drawString(s2, n15, n16);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.nBlock > 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
        final int n3 = this.row * this.col;
        int i = 0;
        while (i < n3) {
            final int n4 = this.gamex + i % this.col * this.w;
            final int n5 = this.gamey + i / this.col * this.h;
            if (n >= n4 && n < n4 + this.w && n2 >= n5 && n2 < n5 + this.h) {
                this.moveaudio.play();
                if (this.id1 == -1 && this.flip[i] == -1) {
                    this.flip[i] = 1;
                    this.id1 = i;
                    ++this.moves;
                    break;
                }
                if (i != this.id1 && this.id1 > -1 && this.flip[i] == -1) {
                    this.flip[i] = 1;
                    this.id2 = i;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (this.butid == 0) {
            this.flipall(this.butid = -1);
            this.id1 = -1;
            this.id2 = -1;
            this.moves = 0;
            this.gameover = 0;
            this.rescramble();
            this.tstart = System.currentTimeMillis();
        }
        else if (this.butid == 1) {
            this.butid = -1;
            this.tstart = 0L;
            this.flipall(1);
            this.id1 = -1;
            this.id2 = -1;
            this.moves = 0;
            this.gameover = 0;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showCopyright = true;
        if (this.registered) {
            this.showStatus("");
        }
        else {
            this.showStatus("matchit (C) thejmaker.com 2003");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showCopyright = false;
        if (!this.registered) {
            this.showStatus("matchit (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
        this.repaint();
        this.movex = -1;
        this.movey = -1;
        this.butid = -1;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int movex, final int movey) {
        this.movex = movex;
        this.movey = movey;
        this.butid = -1;
        for (int i = 0; i < 1 + this.showbut; ++i) {
            if (this.box[i].inside(movex, movey)) {
                this.butid = i;
                break;
            }
        }
        this.showCopyright = true;
        this.repaint();
        return true;
    }
    
    private void waitForImage(final int n) {
        while (!this.tracker.checkID(n, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
}
