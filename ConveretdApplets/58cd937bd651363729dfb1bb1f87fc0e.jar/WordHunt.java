import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.util.Date;
import java.awt.Component;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Button;
import java.awt.List;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WordHunt extends Applet implements Runnable
{
    Image bgimg;
    Image titimg;
    Thread t;
    int width;
    int height;
    int delay;
    Color bg;
    Color fg;
    Image oi;
    Graphics og;
    boolean showCopyright;
    int nBlock;
    MediaTracker tracker;
    int loading;
    boolean loaded;
    String WordHuntFile;
    String bgfile;
    String titfile;
    int graphw;
    int graphh;
    int graphx;
    int graphy;
    int keyw;
    int keyh;
    int keyx;
    int keyy;
    Color gridc;
    int mousex;
    int mousey;
    char[][] board;
    int row;
    int col;
    Font font;
    Color fontc;
    Color crossc;
    int nkeys;
    String[] keys;
    boolean[] flag;
    int[] headr;
    int[] headc;
    int[] tailr;
    int[] tailc;
    List keylist;
    int cellw;
    int cellh;
    int titx;
    int tity;
    Button butnew;
    Button butshow;
    int newx;
    int newy;
    int showx;
    int showy;
    int neww;
    int newh;
    int showw;
    int showh;
    int drag_sx;
    int drag_sy;
    int drag_ex;
    int drag_ey;
    int drag_sr;
    int drag_sc;
    int drag_er;
    int drag_ec;
    long maxtime;
    String timer;
    int timerx;
    int timery;
    long tstart;
    long tend;
    long tdelta;
    int gameover;
    String linkto;
    String linkframe;
    String linkto2;
    String linkframe2;
    String overtext;
    String overtext2;
    boolean registered;
    
    public WordHunt() {
        this.bgimg = null;
        this.titimg = null;
        this.t = null;
        this.delay = 100;
        this.oi = null;
        this.og = null;
        this.showCopyright = false;
        this.nBlock = 0;
        this.loading = 1;
        this.loaded = false;
        this.WordHuntFile = "";
        this.bgfile = "";
        this.titfile = "";
        this.graphw = 0;
        this.graphh = 0;
        this.graphx = 0;
        this.graphy = 0;
        this.keyw = 0;
        this.keyh = 0;
        this.keyx = 0;
        this.keyy = 0;
        this.gridc = Color.yellow;
        this.mousex = 0;
        this.mousey = 0;
        this.row = 0;
        this.col = 0;
        this.crossc = Color.red;
        this.keylist = new List(10, false);
        this.cellw = 10;
        this.cellh = 10;
        this.titx = 0;
        this.tity = 0;
        this.butnew = new Button("New");
        this.butshow = new Button("Show");
        this.newx = 30;
        this.newy = 100;
        this.showx = 60;
        this.showy = 50;
        this.neww = 60;
        this.newh = 25;
        this.showw = 60;
        this.showh = 25;
        this.drag_sx = -1;
        this.drag_sy = -1;
        this.drag_ex = -1;
        this.drag_ey = -1;
        this.drag_sr = -1;
        this.drag_sc = -1;
        this.drag_er = -1;
        this.drag_ec = -1;
        this.maxtime = -1L;
        this.timer = "00:00:00";
        this.timerx = 0;
        this.timery = 0;
        this.tstart = 0L;
        this.tend = 0L;
        this.tdelta = 0L;
        this.gameover = 0;
        this.linkto = "";
        this.linkframe = "";
        this.linkto2 = "";
        this.linkframe2 = "";
        this.overtext = "";
        this.overtext2 = "";
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
            if (intValue == '\u015b' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.WordHuntFile = this.getParameter("WordHuntFile").trim();
        DataInputStream dataInputStream = null;
        final String wordHuntFile = this.WordHuntFile;
        this.showStatus("Reading WordHunt datafile " + this.WordHuntFile + "...");
        try {
            URL url;
            if (wordHuntFile.toUpperCase().startsWith("HTTP")) {
                url = new URL(wordHuntFile);
            }
            else {
                url = new URL(this.getDocumentBase(), wordHuntFile);
            }
            dataInputStream = new DataInputStream(url.openStream());
        }
        catch (Exception ex) {
            this.showStatus("[Error]: Exception: " + ex + " File = " + wordHuntFile);
            this.stop();
            System.exit(-1);
        }
        try {
            final String[] parse = this.parse(dataInputStream.readLine());
            final int[] int1 = this.parseInt(parse[0]);
            this.bg = new Color(int1[0], int1[1], int1[2]);
            final int[] int2 = this.parseInt(parse[1]);
            this.fg = new Color(int2[0], int2[1], int2[2]);
            this.bgfile = this.parse(dataInputStream.readLine())[0].trim();
            final String[] parse2 = this.parse(dataInputStream.readLine());
            this.titfile = parse2[0].trim();
            final int[] int3 = this.parseInt(parse2[1]);
            this.titx = int3[0];
            this.tity = int3[1];
            final String[] parse3 = this.parse(dataInputStream.readLine());
            final int[] int4 = this.parseInt(parse3[0]);
            this.graphw = int4[0];
            this.graphh = int4[1];
            final int[] int5 = this.parseInt(parse3[1]);
            this.graphx = int5[0];
            this.graphy = int5[1];
            final String[] parse4 = this.parse(dataInputStream.readLine());
            final int[] int6 = this.parseInt(parse4[0]);
            this.keyw = int6[0];
            this.keyh = int6[1];
            final int[] int7 = this.parseInt(parse4[1]);
            this.keyx = int7[0];
            this.keyy = int7[1];
            final String[] parse5 = this.parse(dataInputStream.readLine());
            final int[] int8 = this.parseInt(parse5[0]);
            this.neww = int8[0];
            this.newh = int8[1];
            final int[] int9 = this.parseInt(parse5[1]);
            this.newx = int9[0];
            this.newy = int9[1];
            final String[] parse6 = this.parse(dataInputStream.readLine());
            final int[] int10 = this.parseInt(parse6[0]);
            this.showw = int10[0];
            this.showh = int10[1];
            final int[] int11 = this.parseInt(parse6[1]);
            this.showx = int11[0];
            this.showy = int11[1];
            final String[] parse7 = this.parse(dataInputStream.readLine());
            final int[] int12 = this.parseInt(parse7[0]);
            this.gridc = new Color(int12[0], int12[1], int12[2]);
            final int[] int13 = this.parseInt(parse7[1]);
            this.row = int13[0];
            this.col = int13[1];
            this.board = new char[this.row][this.col];
            this.cellw = this.graphw / this.col;
            this.cellh = this.graphh / this.row;
            final int[] int14 = this.parseInt(dataInputStream.readLine());
            this.crossc = new Color(int14[0], int14[1], int14[2]);
            final String[] parse8 = this.parse(dataInputStream.readLine());
            final int[] int15 = this.parseInt(parse8[0]);
            this.fontc = new Color(int15[0], int15[1], int15[2]);
            this.font = new Font(parse8[1], this.parseInt(parse8[3])[0], this.parseInt(parse8[2])[0]);
            final String[] parse9 = this.parse(dataInputStream.readLine());
            final int[] int16 = this.parseInt(parse9[0]);
            this.timerx = int16[0];
            this.timery = int16[1];
            this.maxtime = this.parseInt(parse9[1])[0];
            if (this.maxtime <= 0L) {
                this.maxtime = 36000L;
            }
            else {
                this.maxtime *= 60L;
            }
            final String[] parse10 = this.parse(dataInputStream.readLine());
            this.overtext = parse10[0].trim();
            this.linkto = parse10[1].trim();
            this.linkframe = parse10[2].trim();
            final String[] parse11 = this.parse(dataInputStream.readLine());
            this.overtext2 = parse11[0].trim();
            this.linkto2 = parse11[1].trim();
            this.linkframe2 = parse11[2].trim();
            this.nkeys = this.parseInt(dataInputStream.readLine())[0];
            this.keys = new String[this.nkeys];
            this.flag = new boolean[this.nkeys];
            this.headr = new int[this.nkeys];
            this.headc = new int[this.nkeys];
            this.tailr = new int[this.nkeys];
            this.tailc = new int[this.nkeys];
            for (int j = 0; j < this.nkeys; ++j) {
                this.keys[j] = this.parse(dataInputStream.readLine())[0].trim().toUpperCase();
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {
            this.showStatus("[Error]: Exception: " + ex2 + " File: " + wordHuntFile);
        }
        this.showStatus("");
        this.createNewBoard();
        this.setLayout(null);
        this.add(this.keylist);
        this.keylist.setSize(this.keyw, this.keyh);
        this.keylist.setLocation(this.keyx, this.keyy);
        this.add(this.butnew);
        this.butnew.setSize(this.neww, this.newh);
        this.butnew.setLocation(this.newx, this.newy);
        this.add(this.butshow);
        this.butshow.setSize(this.showw, this.showh);
        this.butshow.setLocation(this.showx, this.showy);
        this.tstart = System.currentTimeMillis();
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
        this.repaint();
        this.resize(this.width, this.height);
    }
    
    private void createNewBoard() {
        this.keylist.removeAll();
        for (int i = 0; i < this.nkeys; ++i) {
            this.keylist.add(this.keys[i]);
        }
        for (int j = 0; j < this.nkeys; ++j) {
            this.flag[j] = false;
        }
        for (int k = 0; k < this.row; ++k) {
            for (int l = 0; l < this.col; ++l) {
                this.board[k][l] = '*';
            }
        }
        for (int n = 0; n < this.nkeys; ++n) {
            boolean b = false;
            while (!b) {
                int length = (int)(Math.random() * this.row);
                int n2 = (int)(Math.random() * this.col);
                final int n3 = (int)(Math.random() * 4.0);
                int n4 = 0;
                int n5 = 0;
                switch (n3) {
                    case 0: {
                        if (this.row - length < this.keys[n].length()) {
                            length = this.row - this.keys[n].length();
                        }
                        n4 = 1;
                        n5 = 0;
                        break;
                    }
                    case 1: {
                        if (length < this.keys[n].length()) {
                            length = this.keys[n].length();
                        }
                        if (this.col - n2 < this.keys[n].length()) {
                            n2 = this.col - this.keys[n].length();
                        }
                        n4 = -1;
                        n5 = 1;
                        break;
                    }
                    case 2: {
                        if (this.col - n2 < this.keys[n].length()) {
                            n2 = this.col - this.keys[n].length();
                        }
                        n4 = 0;
                        n5 = 1;
                        break;
                    }
                    case 3: {
                        if (this.row - length < this.keys[n].length()) {
                            length = this.row - this.keys[n].length();
                        }
                        if (this.col - n2 < this.keys[n].length()) {
                            n2 = this.col - this.keys[n].length();
                        }
                        n4 = 1;
                        n5 = 1;
                        break;
                    }
                }
                boolean b2 = false;
                int n6 = length;
                int n7 = n2;
                for (int n8 = 0; n8 < this.keys[n].length() && !b2; ++n8) {
                    if (this.board[n6][n7] != '*' && this.board[n6][n7] != this.keys[n].charAt(n8)) {
                        b2 = true;
                    }
                    n6 += n4;
                    n7 += n5;
                }
                if (!b2) {
                    b = true;
                    int n9 = length;
                    int n10 = n2;
                    this.headr[n] = n9;
                    this.headc[n] = n10;
                    for (int n11 = 0; n11 < this.keys[n].length(); ++n11) {
                        this.board[n9][n10] = this.keys[n].charAt(n11);
                        if (n11 == this.keys[n].length() - 1) {
                            this.tailr[n] = n9;
                            this.tailc[n] = n10;
                        }
                        n9 += n4;
                        n10 += n5;
                    }
                }
            }
        }
        for (int n12 = 0; n12 < this.row; ++n12) {
            for (int n13 = 0; n13 < this.col; ++n13) {
                if (this.board[n12][n13] == '*') {
                    this.board[n12][n13] = (char)(65 + (int)(Math.random() * 26.0));
                }
            }
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
            if (!this.bgfile.trim().toUpperCase().startsWith("NONE")) {
                if (this.loading == 1) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.setColor(Color.black);
                    graphics.drawString("Loading background \"" + this.bgfile + "\"...", 4, 29);
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
                if (date.getMinutes() % 8 == 0 && date.getSeconds() < 15) {
                    this.nBlock = 15 - date.getSeconds();
                }
                else {
                    this.nBlock = 0;
                }
            }
        }
    }
    
    int[] parseInt(String substring) {
        final int index = substring.indexOf("#");
        if (index > -1) {
            substring = substring.substring(0, index);
        }
        substring.trim();
        final StringTokenizer stringTokenizer = new StringTokenizer(substring, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        return array;
    }
    
    String[] parse(String substring) {
        final int index = substring.indexOf("#");
        if (index > -1) {
            substring = substring.substring(0, index);
        }
        substring.trim();
        final StringTokenizer stringTokenizer = new StringTokenizer(substring, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.butnew) {
            this.tstart = System.currentTimeMillis();
            this.createNewBoard();
            this.gameover = 0;
            this.repaint();
        }
        else if (event.target == this.butshow) {
            this.tstart = 0L;
            for (int i = 0; i < this.nkeys; ++i) {
                this.flag[i] = true;
            }
            this.repaint();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.og == null) {
            this.oi = this.createImage(this.width, this.height);
            this.og = this.oi.getGraphics();
        }
        if (!this.loaded) {
            return;
        }
        if (this.nBlock > 0) {
            this.og.setColor(Color.black);
            this.og.fillRect(0, 0, this.width, this.height);
            this.og.setColor(Color.white);
            final String[] array = { "\"WordHunt\"", "by", "www.thejmaker.com", "Click to register: " + this.nBlock };
            int n = this.height / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.og.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.og.setFont(new Font("Helvetica", 1, 11));
                }
                this.og.drawString(array[i], (this.graphw - this.og.getFontMetrics().stringWidth(array[i])) / 2, n);
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
        if (this.titimg != null) {
            this.og.drawImage(this.titimg, this.titx, this.tity, this);
        }
        this.og.setColor(this.gridc);
        for (int l = 0; l <= this.row; ++l) {
            this.og.drawLine(this.graphx, this.graphy + l * this.cellh, this.graphx + this.graphw, this.graphy + l * this.cellh);
        }
        for (int n2 = 0; n2 <= this.col; ++n2) {
            this.og.drawLine(this.graphx + n2 * this.cellw, this.graphy, this.graphx + n2 * this.cellw, this.graphy + this.graphh);
        }
        this.og.setFont(this.font);
        this.og.setColor(this.fontc);
        for (int n3 = 0; n3 < this.row; ++n3) {
            for (int n4 = 0; n4 < this.col; ++n4) {
                this.og.drawString(this.board[n3][n4] + "", this.graphx + this.cellw * n4 + (this.cellw - this.og.getFontMetrics().stringWidth(this.board[n3][n4] + "")) / 2 + 1, this.graphy + this.cellh * (n3 + 1) - (this.cellh - this.og.getFontMetrics().getHeight() + this.og.getFontMetrics().getDescent()) / 2);
            }
        }
        final int n5 = this.cellw / 2;
        final int n6 = this.cellh / 2;
        this.og.setColor(this.crossc);
        for (int n7 = 0; n7 < this.nkeys; ++n7) {
            if (this.flag[n7]) {
                this.og.drawLine(this.graphx + this.headc[n7] * this.cellw + n5, this.graphy + this.headr[n7] * this.cellh + n6, this.graphx + this.tailc[n7] * this.cellw + n5, this.graphy + this.tailr[n7] * this.cellh + n6);
            }
        }
        if (this.drag_sx > -1 && this.drag_sy > -1 && this.drag_ex > -1 && this.drag_ey > -1) {
            this.og.drawLine(this.drag_sx, this.drag_sy, this.drag_ex, this.drag_ey);
        }
        this.og.setColor(this.fg);
        this.og.setFont(new Font("Helvetica", 1, 12));
        this.og.drawString(this.timer, this.timerx, this.timery);
        this.og.drawRect(0, 0, this.width - 1, this.height - 1);
        if (this.gameover > 0) {
            this.og.setFont(new Font("Helvetica", 1, 25));
            String s = this.overtext;
            if (this.gameover == 2) {
                s = this.overtext2;
            }
            final int n8 = this.graphy + this.height / 2 - 30;
            final int n9 = this.graphx + (this.graphw - this.og.getFontMetrics().stringWidth(s)) / 2;
            this.og.setColor(Color.red);
            this.og.drawString(s, n9, n8 - 1);
            this.og.drawString(s, n9, n8 + 1);
            this.og.drawString(s, n9 - 1, n8);
            this.og.drawString(s, n9 + 1, n8);
            this.og.setColor(Color.pink);
            this.og.drawString(s, n9, n8);
        }
        if (!this.registered && this.showCopyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s2 = "WordHunt (C) thejmaker.com 2003";
            final int n10 = 5;
            final int n11 = 10;
            this.og.setColor(Color.blue);
            this.og.drawString(s2, n10, n11 - 1);
            this.og.drawString(s2, n10, n11 + 1);
            this.og.drawString(s2, n10 - 1, n11);
            this.og.drawString(s2, n10 + 1, n11);
            this.og.setColor(Color.cyan);
            this.og.drawString(s2, n10, n11);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showCopyright = true;
        if (this.registered) {
            this.showStatus("");
        }
        else {
            this.showStatus("WordHunt (C) thejmaker.com 2003");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int drag_ex, final int drag_ey) {
        this.drag_ex = drag_ex;
        this.drag_ey = drag_ey;
        final int n = -1;
        this.drag_ec = n;
        this.drag_er = n;
        if (drag_ey >= this.graphy && drag_ey <= this.graphy + this.graphh) {
            this.drag_er = (drag_ey - this.graphy) / this.cellh;
        }
        if (drag_ex >= this.graphx && drag_ex <= this.graphx + this.graphw) {
            this.drag_ec = (drag_ex - this.graphx) / this.cellw;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.drag_sr > -1 && this.drag_sc > -1 && this.drag_er > -1 && this.drag_ec > -1) {
            if (this.drag_sc == this.drag_ec && this.drag_sr == this.drag_er) {
                return true;
            }
            if (this.drag_ec < this.drag_sc) {
                final int drag_sc = this.drag_sc;
                this.drag_sc = this.drag_ec;
                this.drag_ec = drag_sc;
                final int drag_sr = this.drag_sr;
                this.drag_sr = this.drag_er;
                this.drag_er = drag_sr;
            }
            int drag_sr2 = this.drag_sr;
            int drag_sc2 = this.drag_sc;
            int n3 = 1;
            int n4 = 1;
            if (this.drag_sc == this.drag_ec) {
                n4 = 0;
            }
            if (this.drag_sr == this.drag_er) {
                n3 = 0;
            }
            else if (this.drag_sr > this.drag_er) {
                n3 = -1;
            }
            String string = "";
            while ((drag_sr2 <= this.drag_er || n3 == -1) && drag_sc2 <= this.drag_ec) {
                string = string + this.board[drag_sr2][drag_sc2] + "";
                drag_sr2 += n3;
                drag_sc2 += n4;
            }
            final int n5 = -1;
            this.drag_ey = n5;
            this.drag_ex = n5;
            this.drag_sy = n5;
            this.drag_sx = n5;
            for (int i = 0; i < this.nkeys; ++i) {
                if (this.keys[i].equalsIgnoreCase(string)) {
                    this.flag[i] = true;
                    this.keylist.remove(string);
                    if (this.keylist.getItemCount() == 0) {
                        this.tstart = 0L;
                        this.gameover = 1;
                    }
                    this.repaint();
                    return true;
                }
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int drag_sx, final int drag_sy) {
        if (this.nBlock > 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/pricing.html"), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.drag_sx = drag_sx;
        this.drag_sy = drag_sy;
        final int n = -1;
        this.drag_sc = n;
        this.drag_sr = n;
        if (drag_sy >= this.graphy && drag_sy <= this.graphy + this.graphh) {
            this.drag_sr = (drag_sy - this.graphy) / this.cellh;
        }
        if (drag_sx >= this.graphx && drag_sx <= this.graphx + this.graphw) {
            this.drag_sc = (drag_sx - this.graphx) / this.cellw;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        final int n3 = -1;
        this.drag_ey = n3;
        this.drag_ex = n3;
        this.drag_sy = n3;
        this.drag_sx = n3;
        this.showCopyright = false;
        if (!this.registered) {
            this.showStatus("WordHunt (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showCopyright = true;
        if (!this.registered) {
            this.showStatus("WordHunt (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
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
