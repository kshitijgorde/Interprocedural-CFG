import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Insets;
import java.net.URL;
import java.util.Date;
import java.awt.Event;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class dia extends Applet implements Runnable
{
    Graphics og;
    Image oi;
    Image photo;
    Thread t;
    int width;
    int height;
    String diadata;
    String keys;
    String keyd;
    String head;
    TextField tfs;
    TextField tfstatus;
    Button buttons;
    Button buttonBack;
    Button buttonNext;
    Color bg;
    Color fg;
    Color infobg;
    Color infofg;
    Color photobg;
    Choice desc;
    diaButton contrastm;
    diaButton brightm;
    diaButton saturatem;
    diaButton tintm;
    diaButton zoomm;
    diaButton sharpenm;
    diaButton contrastp;
    diaButton brightp;
    diaButton saturatep;
    diaButton tintp;
    diaButton zoomp;
    diaButton sharpenp;
    diaButton showPhoto;
    diaButton reset;
    int ntargets;
    int matchd;
    int matchs;
    String[] target;
    String[] targetimage;
    int picwidth;
    int picheight;
    boolean nophoto;
    diaphoto rp;
    diadata ri;
    boolean registered;
    
    public dia() {
        this.t = null;
        this.tfs = new TextField(15);
        this.tfstatus = new TextField(70);
        this.buttons = new Button(" Search ");
        this.buttonBack = new Button(" < ");
        this.buttonNext = new Button(" > ");
        this.desc = new Choice();
        this.ntargets = 1;
        this.matchd = -1;
        this.matchs = -1;
        this.picwidth = 1;
        this.picheight = 1;
        this.nophoto = true;
        this.registered = false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (new Date().getMinutes() % 10 == 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/"));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (event.target == this.buttons) {
            this.keys = this.tfs.getText();
            this.matchs = -1;
            for (int i = 0; i < this.ntargets; ++i) {
                final String upperCase = this.keys.trim().toUpperCase();
                final String upperCase2 = this.target[i].trim().toUpperCase();
                final String upperCase3 = this.targetimage[i].trim().toUpperCase();
                if (upperCase2.indexOf(upperCase) >= 0 || upperCase3.indexOf(upperCase) >= 0) {
                    this.matchs = i;
                    break;
                }
            }
            this.displayinfo(this.matchs);
            return true;
        }
        if (event.target == this.reset) {
            this.displayinfo(this.matchs = this.desc.getSelectedIndex() - 1);
            return true;
        }
        if (event.target == this.desc) {
            this.displayinfo(this.matchs = this.desc.getSelectedIndex() - 1);
            this.tfs.setText("");
            return true;
        }
        if (event.target == this.buttonNext) {
            this.keys = this.tfs.getText();
            int n;
            if (this.matchs > -1 && this.matchs < this.ntargets - 1) {
                n = this.matchs + 1;
            }
            else {
                n = 0;
            }
            for (int j = n; j < this.ntargets; ++j) {
                final String upperCase4 = this.keys.trim().toUpperCase();
                final String upperCase5 = this.target[j].trim().toUpperCase();
                final String upperCase6 = this.targetimage[j].trim().toUpperCase();
                if (upperCase5.indexOf(upperCase4) >= 0 || upperCase6.indexOf(upperCase4) >= 0) {
                    this.matchs = j;
                    break;
                }
            }
            this.displayinfo(this.matchs);
            return true;
        }
        if (event.target == this.buttonBack) {
            this.keys = this.tfs.getText();
            int n2;
            if (this.matchs > 0) {
                n2 = this.matchs - 1;
            }
            else {
                n2 = this.ntargets - 1;
            }
            for (int k = n2; k >= 0; --k) {
                final String upperCase7 = this.keys.trim().toUpperCase();
                final String upperCase8 = this.target[k].trim().toUpperCase();
                final String upperCase9 = this.targetimage[k].trim().toUpperCase();
                if (upperCase8.indexOf(upperCase7) >= 0 || upperCase9.indexOf(upperCase7) >= 0) {
                    this.matchs = k;
                    break;
                }
            }
            this.displayinfo(this.matchs);
            return true;
        }
        if (event.target == this.contrastm) {
            this.rp.contrast(0.5f);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.contrastp) {
            this.rp.contrast(2.0f);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.sharpenm) {
            this.rp.sharpen(-1, -9);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.sharpenp) {
            this.rp.sharpen(12, 4);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.saturatem) {
            this.rp.saturate(80);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.saturatep) {
            this.rp.saturate(120);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.tintm) {
            this.rp.tint(-10);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.tintp) {
            this.rp.tint(10);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.brightm) {
            this.rp.brighten(-10);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.brightp) {
            this.rp.brighten(10);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.zoomm) {
            this.rp.scalePhoto(-0.1f);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.zoomp) {
            this.rp.scalePhoto(0.1f);
            this.rp.repaint();
            return true;
        }
        if (event.target == this.showPhoto) {
            this.readPhoto(this.targetimage[this.matchs]);
            new diaframe(this.target[this.matchs], new diaImage(this.photo, this.picwidth, this.picheight)).show();
            return true;
        }
        this.repaint();
        return false;
    }
    
    public void displayinfo(final int n) {
        this.nophoto = false;
        if (n < 0) {
            this.nophoto = true;
            this.desc.select(0);
        }
        else {
            if (this.targetimage[n].trim() == "none") {
                this.nophoto = true;
            }
            this.desc.select(n + 1);
        }
        if (this.nophoto) {
            this.photo = null;
        }
        else {
            this.readPhoto(this.targetimage[n].trim());
        }
        this.rp.newphoto(this.photo, this.picwidth, this.picheight);
        this.rp.repaint();
        if (n < 0) {
            this.tfstatus.setText("Photo not ready or found");
        }
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        final int intValue = new Integer(this.getParameter("regcode"));
        final int n = intValue / 100000;
        final int n2 = intValue / 10 - n * 10000;
        final int n3 = intValue % 10;
        if (n == 402 && n3 == n * n2 % 10) {
            this.registered = true;
        }
        this.diadata = this.getParameter("diadata");
        DataInputStream dataInputStream = null;
        final String diadata = this.diadata;
        this.tfstatus.setForeground(new Color(255, 0, 0));
        this.tfstatus.setBackground(this.bg);
        this.tfstatus.setText("Reading dia datafile " + this.diadata + "...");
        int n4 = 0;
        try {
            dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), diadata).openStream());
        }
        catch (Exception ex) {
            this.tfstatus.setText("[Error]: Exception: " + ex + " File = " + diadata);
            this.stop();
        }
        try {
            final int[] int1 = this.parseInt(dataInputStream.readLine());
            this.bg = new Color(int1[0], int1[1], int1[2]);
            final int[] int2 = this.parseInt(dataInputStream.readLine());
            this.fg = new Color(int2[0], int2[1], int2[2]);
            final int[] int3 = this.parseInt(dataInputStream.readLine());
            this.infobg = new Color(int3[0], int3[1], int3[2]);
            final int[] int4 = this.parseInt(dataInputStream.readLine());
            this.infofg = new Color(int4[0], int4[1], int4[2]);
            final int[] int5 = this.parseInt(dataInputStream.readLine());
            this.photobg = new Color(int5[0], int5[1], int5[2]);
            this.head = dataInputStream.readLine();
            this.ntargets = this.parseInt(dataInputStream.readLine())[0];
            this.target = new String[this.ntargets];
            this.targetimage = new String[this.ntargets];
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                final String[] parse = this.parse(line);
                this.target[n4] = parse[0];
                this.targetimage[n4] = parse[1];
                this.tfstatus.setText(String.valueOf(this.target[n4]) + " reading done! ");
                ++n4;
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {
            this.tfstatus.setText("[Error]: Exception: " + ex2 + " File: " + diadata);
        }
        this.tfstatus.setText("'" + this.head + "'" + " dia datafile reading done");
        this.photo = null;
        this.rp = new diaphoto(this.photo, this.width - 170, this.height - 140, this.picwidth, this.picheight, this.photobg);
        this.rp.registered = this.registered;
        this.rp.repaint();
        (this.ri = new diadata(this.width - 170, 40, this.bg, this.infobg, this.infofg)).newtext(this.head);
        this.ri.repaint();
        this.desc.addItem("Select a draggable photo here or search a keyword below:");
        for (int i = 0; i < this.ntargets; ++i) {
            this.desc.addItem(this.target[i]);
        }
        this.desc.setForeground(this.fg);
        this.desc.setBackground(this.bg);
        this.readPhoto("diabmbri.gif");
        this.brightm = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "darken", 10, 25, this.bg);
        this.readPhoto("diabmcon.gif");
        this.contrastm = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "contrast", 10, 25, this.bg);
        this.readPhoto("diabmsat.gif");
        this.saturatem = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "saturate", 10, 25, this.bg);
        this.readPhoto("diabmsha.gif");
        this.sharpenm = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "sharpen", 10, 25, this.bg);
        this.readPhoto("diabmtin.gif");
        this.tintm = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "tint", 10, 25, this.bg);
        this.readPhoto("diabmzoo.gif");
        this.zoomm = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "zoom", 10, 25, this.bg);
        this.readPhoto("diabpbri.gif");
        this.brightp = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "brighten", 10, 25, this.bg);
        this.readPhoto("diabpcon.gif");
        this.contrastp = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "contrast", 10, 25, this.bg);
        this.readPhoto("diabpsat.gif");
        this.saturatep = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "saturate", 10, 25, this.bg);
        this.readPhoto("diabpsha.gif");
        this.sharpenp = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "sharpen", 10, 25, this.bg);
        this.readPhoto("diabptin.gif");
        this.tintp = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "tint", 10, 25, this.bg);
        this.readPhoto("diabpzoo.gif");
        this.zoomp = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "zoom", 10, 25, this.bg);
        this.readPhoto("diabshow.gif");
        this.showPhoto = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "original", 10, 25, this.bg);
        this.readPhoto("diabr.gif");
        this.reset = new diaButton(new diaImage(this.photo, this.picwidth, this.picheight), 4, "reset", 10, 25, this.bg);
        this.setForeground(this.fg);
        this.setBackground(this.bg);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        this.setLayout(layout);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        this.buttons.setForeground(this.fg);
        this.buttons.setBackground(this.bg);
        this.tfs.setForeground(this.fg);
        this.tfs.setBackground(this.bg);
        this.buttonBack.setForeground(this.fg);
        this.buttonBack.setBackground(this.bg);
        this.buttonNext.setForeground(this.fg);
        this.buttonNext.setBackground(this.bg);
        panel.add(this.buttons);
        panel.add(this.tfs);
        panel.add(this.buttonBack);
        panel.add(this.buttonNext);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(7, 2));
        panel2.add(this.zoomm);
        panel2.add(this.zoomp);
        panel2.add(this.saturatem);
        panel2.add(this.saturatep);
        panel2.add(this.brightm);
        panel2.add(this.brightp);
        panel2.add(this.tintm);
        panel2.add(this.tintp);
        panel2.add(this.sharpenm);
        panel2.add(this.sharpenp);
        panel2.add(this.contrastm);
        panel2.add(this.contrastp);
        panel2.add(this.showPhoto);
        panel2.add(this.reset);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("Center", this.rp);
        panel3.add("South", this.desc);
        panel3.add("North", this.ri);
        final Panel panel4 = new Panel();
        panel4.setLayout(new FlowLayout(1));
        panel4.add(panel3);
        panel4.add(panel2);
        layout.setConstraints(panel4, gridBagConstraints);
        this.add(panel4);
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        layout.setConstraints(this.tfstatus, gridBagConstraints);
        this.add(this.tfstatus);
        this.tfstatus.setText("Ready!");
        this.resize(this.width, this.height);
        this.show();
        this.repaint();
    }
    
    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bg);
        for (int i = 0; i < 3; ++i) {
            graphics.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, true);
        }
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
        this.tfstatus.setText("Reading photo '" + s + "'...");
        this.photo = this.getImage(this.getDocumentBase(), s);
        final int n = -1;
        this.picheight = n;
        this.picwidth = n;
        while (this.picwidth < 0 || this.picheight < 0) {
            this.picwidth = this.photo.getWidth(this);
            this.picheight = this.photo.getHeight(this);
        }
        this.tfstatus.setText("Draggable photo '" + s + "' (" + this.picwidth + "x" + this.picheight + ") ready");
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
    Label_0007_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(1000L);
                    }
                }
                catch (InterruptedException ex) {
                    continue Label_0007_Outer;
                }
                continue;
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
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
