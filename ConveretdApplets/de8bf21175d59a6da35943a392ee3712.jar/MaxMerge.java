import java.awt.Event;
import java.awt.Font;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.util.Date;
import java.net.URL;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MaxMerge extends Applet implements Runnable
{
    Image bgimg;
    Thread t;
    int width;
    int height;
    int delay;
    int gridwidth;
    int gridheight;
    Color bg;
    Color fg;
    Color butbg;
    Color butfg;
    Color framec;
    int row;
    int col;
    AudioClip mergeaudio;
    AudioClip erroraudio;
    AudioClip frameaudio;
    String mergefile;
    String errorfile;
    String framefile;
    String bgfile;
    String titfile;
    int gameover;
    String linkto;
    String linkframe;
    String NewGameText;
    String OverText;
    Image oi;
    Graphics og;
    int butid;
    Rectangle[] box;
    String[] caption;
    boolean showCopyright;
    Image titimg;
    Image frameimg;
    int w;
    int h;
    int total;
    int[][] board;
    byte[][] flag;
    int gamex;
    int gamey;
    int titx;
    int tity;
    int npics;
    String[] picfile;
    Image[] photo;
    int pathlen;
    Vector vPath;
    int hiscore;
    int score;
    int scorex;
    int scorey;
    int nBlock;
    MediaTracker tracker;
    int loading;
    boolean loaded;
    boolean registered;
    
    public MaxMerge() {
        this.t = null;
        this.delay = 100;
        this.row = 4;
        this.col = 4;
        this.mergeaudio = null;
        this.erroraudio = null;
        this.frameaudio = null;
        this.mergefile = null;
        this.errorfile = null;
        this.framefile = null;
        this.gameover = 0;
        this.linkto = "";
        this.linkframe = "";
        this.NewGameText = null;
        this.OverText = null;
        this.og = null;
        this.butid = -1;
        this.box = new Rectangle[1];
        this.caption = new String[] { "New" };
        this.showCopyright = false;
        this.titimg = null;
        this.frameimg = null;
        this.gamex = 0;
        this.gamey = 0;
        this.titx = 0;
        this.tity = 0;
        this.npics = 0;
        this.pathlen = 0;
        this.vPath = new Vector();
        this.hiscore = 0;
        this.score = 0;
        this.scorex = 0;
        this.scorey = 0;
        this.nBlock = 0;
        this.loading = 1;
        this.loaded = false;
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
            if (intValue == '\u03e0' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.bgfile = this.getParameter("bgimage");
        this.titfile = this.getParameter("titleimage");
        if (this.getParameter("mergefile") != null) {
            this.mergefile = this.getParameter("mergefile");
        }
        if (this.getParameter("errorfile") != null) {
            this.errorfile = this.getParameter("errorfile");
        }
        if (this.getParameter("framefile") != null) {
            this.framefile = this.getParameter("framefile");
        }
        this.npics = new Integer(this.getParameter("total"));
        this.picfile = new String[this.npics];
        for (int j = 0; j < this.npics; ++j) {
            this.picfile[j] = this.getParameter("obj" + j);
        }
        this.photo = new Image[this.npics];
        final int[] int1 = this.parseInt(this.getParameter("panelbg"));
        this.bg = new Color(int1[0], int1[1], int1[2]);
        final int[] int2 = this.parseInt(this.getParameter("panelfg"));
        this.fg = new Color(int2[0], int2[1], int2[2]);
        final int[] int3 = this.parseInt(this.getParameter("buttonbg"));
        this.butbg = new Color(int3[0], int3[1], int3[2]);
        final int[] int4 = this.parseInt(this.getParameter("buttonfg"));
        this.butfg = new Color(int4[0], int4[1], int4[2]);
        this.gridwidth = new Integer(this.getParameter("gridwidth"));
        this.gridheight = new Integer(this.getParameter("gridheight"));
        final int[] int5 = this.parseInt(this.getParameter("framecolor"));
        this.framec = new Color(int5[0], int5[1], int5[2]);
        final int n2 = 0x40000000 | (int5[0] & 0xFF) << 16 | (int5[1] & 0xFF) << 8 | (int5[2] & 0xFF);
        final int[] array = new int[this.gridwidth * this.gridheight];
        for (int k = 0; k < this.gridwidth * this.gridheight; ++k) {
            array[k] = n2;
        }
        this.frameimg = this.createImage(new MemoryImageSource(this.gridwidth, this.gridheight, array, 0, this.gridwidth));
        for (int width = -1, height = -1; width < 0 || height < 0; width = this.frameimg.getWidth(this), height = this.frameimg.getHeight(this)) {}
        final int[] int6 = this.parseInt(this.getParameter("newpos"));
        this.box[0] = new Rectangle(int6[0], int6[1], 50, 20);
        final int[] int7 = this.parseInt(this.getParameter("titlepos"));
        this.titx = int7[0];
        this.tity = int7[1];
        final int[] int8 = this.parseInt(this.getParameter("gamepos"));
        this.gamex = int8[0];
        this.gamey = int8[1];
        final int[] int9 = this.parseInt(this.getParameter("scorepos"));
        this.scorex = int9[0];
        this.scorey = int9[1];
        final String[] parse = this.parse(this.getParameter("overlink").trim());
        this.linkto = parse[0];
        this.linkframe = parse[1];
        this.row = new Integer(this.getParameter("row"));
        this.col = new Integer(this.getParameter("col"));
        this.NewGameText = this.getParameter("NewGameText");
        this.caption[0] = this.NewGameText;
        this.OverText = this.getParameter("OverText");
        if (this.mergefile != null) {
            this.mergeaudio = this.getAudioClip(this.getDocumentBase(), this.mergefile);
        }
        if (this.errorfile != null) {
            this.erroraudio = this.getAudioClip(this.getDocumentBase(), this.errorfile);
        }
        if (this.framefile != null) {
            this.frameaudio = this.getAudioClip(this.getDocumentBase(), this.framefile);
        }
        this.board = new int[this.row][this.col];
        this.flag = new byte[this.row][this.col];
        this.reset(-1);
        this.total = this.row * this.col;
        this.w = this.gridwidth;
        this.h = this.gridheight;
        this.setBackground(this.bg);
        this.setForeground(this.fg);
        this.resize(this.width, this.height);
    }
    
    private void waitForImage(final int n) {
        while (!this.tracker.checkID(n, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
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
    }
    
    public void run() {
        if (!this.loaded) {
            final Graphics graphics = this.getGraphics();
            this.tracker = new MediaTracker(this);
            for (int i = 0; i < this.npics; ++i) {
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
            if (this.bgfile.equalsIgnoreCase("none")) {
                this.bgimg = null;
            }
            else {
                if (this.loading == 1) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.setColor(Color.black);
                    graphics.drawString("Loading \"" + this.bgfile + "\"...", 4, 29);
                }
                this.bgimg = this.getImage(this.getDocumentBase(), this.bgfile);
                this.tracker.addImage(this.bgimg, 0);
                this.waitForImage(0);
            }
            if (this.titfile.equalsIgnoreCase("none")) {
                this.titimg = null;
            }
            else {
                if (this.loading == 1) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.setColor(Color.black);
                    graphics.drawString("Loading \"" + this.titfile + "\"...", 4, 29);
                }
                this.titimg = this.getImage(this.getDocumentBase(), this.titfile);
                this.tracker.addImage(this.titimg, 1);
                this.waitForImage(1);
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
                Thread.sleep(500L);
            }
            catch (InterruptedException ex2) {}
            if (this.gameover == 1 && !this.linkto.equalsIgnoreCase("none")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.linkto), this.linkframe);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.gameover = 0;
            }
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 5 == 0 && date.getSeconds() < 20) {
                    this.nBlock = 20 - date.getSeconds();
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
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void CleanFlag() {
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.col; ++j) {
                this.flag[i][j] = 0;
            }
        }
    }
    
    public void reset(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.col; ++j) {
                this.board[i][j] = n2;
                n2 = (n2 + 1) % this.npics;
            }
        }
        for (int k = 0; k < this.row; ++k) {
            for (int l = 0; l < this.col; ++l) {
                final int n3 = (int)(Math.random() * this.row);
                final int n4 = (int)(Math.random() * this.col);
                final int n5 = this.board[k][l];
                this.board[k][l] = this.board[n3][n4];
                this.board[n3][n4] = n5;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.og == null) {
            this.oi = this.createImage(this.width, this.height);
            this.og = this.oi.getGraphics();
            return;
        }
        if (!this.loaded) {
            return;
        }
        if (this.nBlock > 0) {
            this.og.setColor(Color.blue);
            this.og.fillRect(0, 0, this.width, this.height);
            this.og.setColor(Color.white);
            final String[] array = { "\"MaxMerge\"", "by", "www.thejmaker.com", "Click to register: " + this.nBlock };
            int n = this.height / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.og.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.og.setFont(new Font("Helvetica", 1, 11));
                }
                this.og.drawString(array[i], (this.width - this.og.getFontMetrics().stringWidth(array[i])) / 2, n);
                if (i == 0) {
                    n += 16;
                }
                else if (i == 2) {
                    this.og.setColor(Color.yellow);
                    n += 20;
                }
                else {
                    n += 13;
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
        this.og.setColor(this.fg);
        this.og.setFont(new Font("Helvetica", 1, 11));
        this.og.drawString("HISCORE: " + this.hiscore, this.scorex, this.scorey);
        this.og.drawString("SCORE: " + this.score, this.scorex, this.scorey + 12);
        this.og.setColor(this.butfg);
        for (int l = 0; l < 1; ++l) {
            this.og.setColor(this.butbg);
            this.og.fillRoundRect(this.box[l].x, this.box[l].y, this.box[l].width, this.box[l].height, 10, 10);
            if (l == this.butid) {
                this.og.setColor(Color.red);
            }
            else {
                this.og.setColor(this.butfg);
            }
            this.og.drawRoundRect(this.box[l].x, this.box[l].y, this.box[l].width, this.box[l].height, 10, 10);
            this.og.drawString(this.caption[l], this.box[l].x + (this.box[l].width - this.og.getFontMetrics().stringWidth(this.caption[l])) / 2, this.box[l].y + this.box[l].height / 2 + 5);
        }
        if (this.titimg != null) {
            this.og.drawImage(this.titimg, this.titx, this.tity, this);
        }
        for (int n2 = 0; n2 < this.row; ++n2) {
            for (int n3 = 0; n3 < this.col; ++n3) {
                final int n4 = n3 * this.w + this.gamex;
                final int n5 = n2 * this.h + this.gamey;
                if (this.board[n2][n3] >= 0) {
                    this.og.drawImage(this.photo[this.board[n2][n3]], n4 + (this.w - this.photo[this.board[n2][n3]].getWidth(this)) / 2, n5 + (this.h - this.photo[this.board[n2][n3]].getHeight(this)) / 2, this);
                }
            }
        }
        this.og.setColor(this.framec);
        int height = this.height;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        for (int n9 = 0; n9 < this.vPath.size(); ++n9) {
            final int intValue = this.vPath.elementAt(n9);
            final int n10 = this.gamex + intValue % this.col * this.w;
            final int n11 = this.gamey + intValue / this.col * this.h;
            if (n10 > n6) {
                n6 = n10;
                n8 = n11 + this.gridheight / 2;
            }
            if (n11 < height) {
                height = n11;
                n7 = n10 + this.gridwidth / 2;
            }
            this.og.drawImage(this.frameimg, n10, n11, this);
            this.og.drawRect(n10, n11, this.gridwidth, this.gridheight);
        }
        if (this.vPath.size() > 1) {
            final String string = "" + this.vPath.size() * (this.vPath.size() - 1);
            if (height <= this.gamey) {
                this.og.setColor(this.framec);
                final int n12 = n6 + this.w;
                this.og.drawString(string, n12 + 5, n8 - 1);
                this.og.drawString(string, n12 + 5, n8 + 1);
                this.og.drawString(string, n12 + 4, n8);
                this.og.drawString(string, n12 + 6, n8);
                this.og.setColor(this.bg);
                this.og.drawString(string, n12 + 5, n8);
            }
            else {
                this.og.setColor(this.framec);
                this.og.drawString(string, n7, height - 6);
                this.og.drawString(string, n7, height - 4);
                this.og.drawString(string, n7 - 1, height - 5);
                this.og.drawString(string, n7 + 1, height - 5);
                this.og.setColor(this.bg);
                this.og.drawString(string, n7, height - 5);
            }
        }
        this.og.setColor(this.fg);
        this.og.drawRect(0, 0, this.width - 1, this.height - 1);
        if (this.gameover > 0) {
            this.og.setFont(new Font("Helvetica", 1, 20));
            final int n13 = (this.width - this.og.getFontMetrics().stringWidth(this.OverText)) / 2;
            final int n14 = this.height / 2;
            this.og.setColor(Color.red);
            this.og.drawString(this.OverText, n13, n14 - 1);
            this.og.drawString(this.OverText, n13, n14 + 1);
            this.og.drawString(this.OverText, n13 - 1, n14);
            this.og.drawString(this.OverText, n13 + 1, n14);
            this.og.setColor(Color.white);
            this.og.drawString(this.OverText, n13, n14);
        }
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s = "MaxMerge (C) thejmaker.com 2003";
            final int n15 = this.width - this.og.getFontMetrics().stringWidth(s) - 2 - 2;
            final int n16 = 11;
            this.og.setColor(Color.blue);
            this.og.drawString(s, n15, n16 - 1);
            this.og.drawString(s, n15, n16 + 1);
            this.og.drawString(s, n15 - 1, n16);
            this.og.drawString(s, n15 + 1, n16);
            this.og.setColor(Color.white);
            this.og.drawString(s, n15, n16);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public void ComputePathLength(final int n, final int n2, final int n3) {
        if (this.flag[n2][n3] == 1 || this.board[n2][n3] != n) {
            return;
        }
        this.vPath.addElement(new Integer(n2 * this.col + n3));
        this.flag[n2][n3] = 1;
        if (n2 + 1 < this.row) {
            this.ComputePathLength(n, n2 + 1, n3);
        }
        if (n2 - 1 >= 0) {
            this.ComputePathLength(n, n2 - 1, n3);
        }
        if (n3 + 1 < this.col) {
            this.ComputePathLength(n, n2, n3 + 1);
        }
        if (n3 - 1 >= 0) {
            this.ComputePathLength(n, n2, n3 - 1);
        }
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
        int n3 = 0;
        while (n3 < this.total && this.gameover == 0) {
            final int n4 = n3 % this.col;
            final int n5 = n3 / this.col;
            final int n6 = n4 * this.w + this.gamex;
            final int n7 = n5 * this.h + this.gamey;
            if (n >= n6 && n < n6 + this.w && n2 >= n7 && n2 < n7 + this.h) {
                boolean b = false;
                if (this.vPath.size() > 1) {
                    for (int i = 0; i < this.vPath.size(); ++i) {
                        if (n3 == (int)this.vPath.elementAt(i)) {
                            b = true;
                            break;
                        }
                    }
                }
                if (b) {
                    for (int j = 0; j < this.vPath.size(); ++j) {
                        final int intValue = this.vPath.elementAt(j);
                        this.board[intValue / this.col][intValue % this.col] = -1;
                    }
                    this.score += this.vPath.size() * (this.vPath.size() - 1);
                    if (this.score > this.hiscore) {
                        this.hiscore = this.score;
                    }
                    if (this.mergeaudio != null) {
                        this.mergeaudio.play();
                    }
                    for (int k = 0; k < this.col; ++k) {
                        for (int l = this.row - 1; l >= 0; --l) {
                            if (this.board[l][k] < 0) {
                                int n8 = 0;
                                for (int n9 = l - 1; n9 >= 0; --n9) {
                                    if (this.board[n9][k] >= 0) {
                                        n8 = l - n9;
                                        break;
                                    }
                                }
                                if (n8 > 0) {
                                    for (int n10 = l - n8; n10 >= 0; --n10) {
                                        this.board[n10 + n8][k] = this.board[n10][k];
                                    }
                                    for (int n11 = 0; n11 < n8; ++n11) {
                                        this.board[n11][k] = -1;
                                    }
                                }
                            }
                        }
                    }
                    for (int n12 = this.col - 1; n12 >= 0; --n12) {
                        if (this.board[this.row - 1][n12] < 0) {
                            int n13 = 0;
                            for (int n14 = n12 - 1; n14 >= 0; --n14) {
                                if (this.board[this.row - 1][n14] >= 0) {
                                    n13 = n12 - n14;
                                    break;
                                }
                            }
                            if (n13 > 0) {
                                for (int n15 = n12 - n13; n15 >= 0; --n15) {
                                    for (int n16 = 0; n16 < this.row; ++n16) {
                                        this.board[n16][n15 + n13] = this.board[n16][n15];
                                    }
                                }
                                for (int n17 = 0; n17 < n13; ++n17) {
                                    for (int n18 = 0; n18 < this.row; ++n18) {
                                        this.board[n18][n17] = -1;
                                    }
                                }
                            }
                        }
                    }
                    this.gameover = 1;
                    for (int n19 = 0; n19 < this.row; ++n19) {
                        for (int n20 = 0; n20 < this.col; ++n20) {
                            if (this.board[n19][n20] >= 0) {
                                this.vPath.removeAllElements();
                                this.CleanFlag();
                                this.ComputePathLength(this.board[n19][n20], n19, n20);
                                if (this.vPath.size() > 1) {
                                    this.gameover = 0;
                                    break;
                                }
                            }
                        }
                    }
                    this.vPath.removeAllElements();
                    this.CleanFlag();
                    if (this.gameover > 0) {
                        this.getAudioClip(this.getDocumentBase(), this.getParameter("overfile")).play();
                        break;
                    }
                    break;
                }
                else {
                    this.pathlen = 0;
                    this.vPath.removeAllElements();
                    this.CleanFlag();
                    if (this.board[n5][n4] >= 0) {
                        this.ComputePathLength(this.board[n5][n4], n5, n4);
                        this.pathlen = this.vPath.size();
                    }
                    if (this.pathlen <= 1) {
                        if (this.erroraudio != null) {
                            this.erroraudio.play();
                            break;
                        }
                        break;
                    }
                    else {
                        if (this.frameaudio != null) {
                            this.frameaudio.play();
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                ++n3;
            }
        }
        if (this.butid > -1) {
            this.score = 0;
            this.gameover = 0;
            this.reset(-1);
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showCopyright = false;
        if (!this.registered) {
            this.showStatus("MaxMerge (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showCopyright = false;
        if (!this.registered) {
            this.showStatus("MaxMerge (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.butid = -1;
        for (int i = 0; i < 1; ++i) {
            if (this.box[i].inside(n, n2)) {
                this.butid = i;
                break;
            }
        }
        this.showCopyright = true;
        if (this.butid > -1) {
            this.repaint();
        }
        return true;
    }
}
