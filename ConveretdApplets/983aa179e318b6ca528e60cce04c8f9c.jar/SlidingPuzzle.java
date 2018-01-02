import java.awt.Event;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Label;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlidingPuzzle extends Applet implements Runnable
{
    Image photo;
    Thread t;
    int width;
    int height;
    int delay;
    String picfile;
    int picwidth;
    int picheight;
    Color bg;
    Color fg;
    Color butbg;
    Color butfg;
    Color overc;
    int row;
    int col;
    int scramblestart;
    int solvebut;
    SlidingPuzzleButton buttonNew;
    SlidingPuzzleButton buttonSolve;
    SlidingPuzzlePhoto rp;
    AudioClip winaudio;
    AudioClip moveaudio;
    String winfile;
    String movefile;
    boolean registered;
    boolean gameover;
    String linkto;
    String linkframe;
    String TitleText;
    String NewGameText;
    String SolvePuzzleText;
    String RowText;
    String ColText;
    String MovesCountText;
    String FinishText;
    String BestText;
    Label timer;
    long tstart;
    long tend;
    long tdelta;
    
    public SlidingPuzzle() {
        this.t = null;
        this.delay = 200;
        this.row = 4;
        this.col = 4;
        this.scramblestart = 0;
        this.solvebut = 1;
        this.registered = false;
        this.gameover = false;
        this.linkto = "";
        this.linkframe = "";
        this.RowText = "Row:";
        this.ColText = "Col:";
        this.tstart = 0L;
        this.tend = 0L;
        this.tdelta = 0L;
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
            if (intValue == '\u00e7' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.delay = new Integer(this.getParameter("delay"));
        this.picfile = this.getParameter("photo");
        this.scramblestart = new Integer(this.getParameter("scramblestart"));
        this.solvebut = new Integer(this.getParameter("solvebutton"));
        final int intValue3 = new Integer(this.getParameter("frames"));
        this.winfile = this.getParameter("winfile");
        this.movefile = this.getParameter("movefile");
        final int[] int1 = this.parseInt(this.getParameter("panelbg"));
        this.bg = new Color(int1[0], int1[1], int1[2]);
        final int[] int2 = this.parseInt(this.getParameter("panelfg"));
        this.fg = new Color(int2[0], int2[1], int2[2]);
        final int[] int3 = this.parseInt(this.getParameter("buttonbg"));
        this.butbg = new Color(int3[0], int3[1], int3[2]);
        final int[] int4 = this.parseInt(this.getParameter("buttonfg"));
        this.butfg = new Color(int4[0], int4[1], int4[2]);
        final int[] int5 = this.parseInt(this.getParameter("mouseover"));
        this.overc = new Color(int5[0], int5[1], int5[2]);
        final String[] parse = this.parse(this.getParameter("linkto").trim());
        this.linkto = parse[0];
        this.linkframe = parse[1];
        this.row = new Integer(this.getParameter("row"));
        this.col = new Integer(this.getParameter("col"));
        this.TitleText = this.getParameter("TitleText");
        this.NewGameText = this.getParameter("NewGameText");
        this.SolvePuzzleText = this.getParameter("SolvePuzzleText");
        this.MovesCountText = this.getParameter("MovesCountText");
        this.BestText = this.getParameter("BestText");
        this.FinishText = this.getParameter("FinishText");
        this.winaudio = this.getAudioClip(this.getDocumentBase(), this.winfile);
        this.moveaudio = this.getAudioClip(this.getDocumentBase(), this.movefile);
        this.readPhoto(this.picfile);
        this.rp = new SlidingPuzzlePhoto(this.photo, this.picwidth, this.picheight, this.row, this.col, this);
        this.rp.maxtransit = intValue3;
        this.rp.delay = this.delay;
        this.rp.registered = this.registered;
        this.rp.bg = this.bg;
        this.rp.overc = this.overc;
        this.rp.winaudio = this.winaudio;
        this.rp.moveaudio = this.moveaudio;
        this.rp.gameover = this.gameover;
        this.rp.MovesCountText = this.MovesCountText;
        this.rp.FinishText = this.FinishText;
        this.rp.BestText = this.BestText;
        this.rp.repaint();
        final Panel panel = new Panel(new BorderLayout());
        panel.add("Center", this.rp);
        this.setBackground(this.bg);
        this.setForeground(this.fg);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        final Panel[] array = new Panel[5];
        for (int j = 0; j < 5; ++j) {
            array[j] = new Panel();
        }
        (this.buttonNew = new SlidingPuzzleButton(80, 20, this.NewGameText, "Helvetica", 11, this.butbg, this.butfg)).setBGmain(this.bg);
        array[0].add(this.buttonNew);
        (this.buttonSolve = new SlidingPuzzleButton(80, 20, this.SolvePuzzleText, "Helvetica", 11, this.butbg, this.butfg)).setBGmain(this.bg);
        if (this.solvebut > 0) {
            array[1].add(this.buttonSolve);
        }
        else {
            array[1].add(new Label("", 1));
        }
        (this.timer = new Label("00:00:00", 2)).setBackground(this.bg);
        this.timer.setForeground(this.fg);
        this.timer.setFont(new Font("Helvetica", 1, 12));
        array[2].add(this.timer);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        for (int k = 0; k < 3; ++k) {
            panel2.add(array[k]);
        }
        panel.add("South", panel2);
        final Label label = new Label(this.TitleText, 1);
        label.setBackground(this.bg);
        label.setForeground(this.fg);
        label.setFont(new Font("Helvetica", 1, 20));
        panel.add("North", label);
        this.add(panel);
        this.resize(this.width, this.height);
        this.show();
        this.repaint();
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
        this.rp = null;
    }
    
    public void run() {
        System.gc();
        int n = 0;
        if (this.scramblestart > 0) {
            this.rp.rescramble();
            this.rp.repaint();
        }
        while (Thread.currentThread() == this.t) {
            if (n++ > 1000) {
                n = 0;
                System.gc();
            }
            try {
                Thread.sleep(500L);
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
                String text;
                if (n4 < 10) {
                    text = s3 + "0" + n4;
                }
                else {
                    text = s3 + n4 + "";
                }
                this.timer.setText(text);
            }
            this.rp.repaint();
            if (this.rp.gameover && !this.linkto.equalsIgnoreCase("none")) {
                this.tstart = 0L;
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.linkto), this.linkframe);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.rp.gameover = false;
            }
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 5 != 0 || date.getSeconds() >= 2) {
                    continue;
                }
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"));
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public void readPhoto(final String s) {
        this.showStatus("Reading image " + s);
        this.photo = null;
        this.photo = this.getImage(this.getDocumentBase(), s);
        final int n = -1;
        this.picheight = n;
        this.picwidth = n;
        while (this.picwidth < 0 || this.picheight < 0) {
            this.picwidth = this.photo.getWidth(this);
            this.picheight = this.photo.getHeight(this);
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
        this.showStatus("Photo " + s + " (" + this.picwidth + "x" + this.picheight + ") ready");
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
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bg);
        graphics.draw3DRect(0, 0, this.width - 1, this.height - 1, true);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, true);
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.buttonNew) {
            this.rp.target = -1;
            this.rp.makePieces(this.row, this.col);
            this.rp.moves = 0;
            this.rp.gameover = false;
            this.rp.rescramble();
            this.tstart = System.currentTimeMillis();
            this.rp.repaint();
            return true;
        }
        if (event.target == this.buttonSolve) {
            this.tstart = 0L;
            this.rp.target = -1;
            this.rp.resetPositions();
            this.rp.moves = 0;
            this.rp.gameover = false;
            this.rp.repaint();
            return true;
        }
        return false;
    }
}
