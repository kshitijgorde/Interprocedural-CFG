import java.net.URL;
import java.util.Date;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Component;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.applet.AudioClip;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class recover extends Applet implements Runnable
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
    int row;
    int col;
    recoverButton buttonNew;
    recoverButton buttonSolve;
    TextField tfrow;
    TextField tfcol;
    recoverphoto rp;
    AudioClip winaudio;
    AudioClip moveaudio;
    String winfile;
    String movefile;
    boolean registered;
    boolean gameover;
    
    public recover() {
        this.t = null;
        this.delay = 200;
        this.bg = new Color(255, 204, 51);
        this.fg = new Color(0, 0, 255);
        this.butbg = new Color(255, 255, 120);
        this.butfg = new Color(0, 0, 255);
        this.row = 4;
        this.col = 4;
        this.registered = false;
        this.gameover = false;
    }
    
    public boolean action(final Event event, final Object o) {
        this.repaint();
        if (event.target == this.buttonNew) {
            this.row = new Integer(this.tfrow.getText());
            this.col = new Integer(this.tfcol.getText());
            this.rp.makePieces(this.row, this.col);
            this.rp.rescramble();
            this.rp.moves = 0;
            this.rp.gameover = false;
            return true;
        }
        if (event.target == this.buttonSolve) {
            this.rp.resetPositions();
            this.rp.moves = 0;
            this.rp.gameover = false;
            return true;
        }
        return false;
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        final int intValue = new Integer(this.getParameter("regcode"));
        final int n = intValue / 100000;
        final int n2 = intValue / 10 - n * 10000;
        final int n3 = intValue % 10;
        if (n == 808 && n3 == n * n2 % 10) {
            this.registered = true;
        }
        this.delay = new Integer(this.getParameter("delay"));
        this.picfile = this.getParameter("photo");
        this.winfile = this.getParameter("winfile");
        this.movefile = this.getParameter("movefile");
        this.row = new Integer(this.getParameter("row"));
        this.col = new Integer(this.getParameter("col"));
        this.tfrow = new TextField(String.valueOf(this.row));
        this.tfcol = new TextField(String.valueOf(this.col));
        this.winaudio = this.getAudioClip(this.getDocumentBase(), this.winfile);
        this.moveaudio = this.getAudioClip(this.getDocumentBase(), this.movefile);
        this.readPhoto(this.picfile);
        this.rp = new recoverphoto(this.photo, this.picwidth, this.picheight, this.row, this.col, this);
        this.rp.registered = this.registered;
        this.rp.bg = this.bg;
        this.rp.winaudio = this.winaudio;
        this.rp.moveaudio = this.moveaudio;
        this.rp.gameover = this.gameover;
        this.rp.repaint();
        this.setBackground(this.bg);
        this.setForeground(this.fg);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        final Panel panel = new Panel();
        (this.buttonNew = new recoverButton(50, 25, "New", "TimesRoman", 12, this.butbg, this.butfg)).setBGmain(this.bg);
        (this.buttonSolve = new recoverButton(50, 25, "Solve", "TimesRoman", 12, this.butbg, this.butfg)).setBGmain(this.bg);
        panel.add(this.buttonNew);
        panel.add(this.buttonSolve);
        panel.add(new Label("Row:", 2));
        panel.add(this.tfrow);
        panel.add(new Label("Column:", 2));
        panel.add(this.tfcol);
        gridBagLayout.setConstraints(this.rp, gridBagConstraints);
        this.add(this.rp);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        this.resize(this.width, this.height);
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bg);
        graphics.draw3DRect(0, 0, this.width - 1, this.height - 1, true);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, true);
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
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
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (true) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 5 != 0 || date.getSeconds() >= 2) {
                    continue;
                }
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
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
        this.photo = null;
        this.rp = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
