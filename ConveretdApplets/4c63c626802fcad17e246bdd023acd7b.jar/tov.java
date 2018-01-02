import java.awt.Event;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Button;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tov extends Applet implements Runnable
{
    Graphics og;
    Image oi;
    Image photo;
    Thread t;
    int width;
    int height;
    int delay;
    String tovdata;
    Button butx;
    Button butxn;
    Button buty;
    Button butyn;
    Button butz;
    Button butzn;
    Button butStop;
    Button butReset;
    tovButton showWireframe;
    tovButton showShade;
    int picwidth;
    int picheight;
    tovcanvas rp;
    int displaymode;
    tovdata ri;
    int tovpcount;
    int tovlcount;
    int tovtcount;
    float[][] tovp;
    int[][] tovl;
    int[][] tovt;
    float xmin;
    float xmax;
    float ymin;
    float ymax;
    float zmin;
    float zmax;
    float dmax;
    float scale;
    float x2;
    float y2;
    float z2;
    int axis;
    int anglestep;
    double m11;
    double m12;
    double m13;
    double m21;
    double m22;
    double m23;
    double m31;
    double m32;
    double m33;
    int canvasw;
    int canvash;
    int bgR;
    int bgG;
    int bgB;
    int faceR;
    int faceG;
    int faceB;
    int wfR;
    int wfG;
    int wfB;
    String target;
    int closedSolid;
    boolean registered;
    
    public tov() {
        this.t = null;
        this.delay = 200;
        this.butx = new Button(" +X ");
        this.butxn = new Button(" -X ");
        this.buty = new Button(" +Y ");
        this.butyn = new Button(" -Y ");
        this.butz = new Button(" +Z ");
        this.butzn = new Button(" -Z ");
        this.butStop = new Button(" Stop ");
        this.butReset = new Button(" Reset ");
        this.displaymode = 0;
        this.tovpcount = 0;
        this.tovlcount = 0;
        this.tovtcount = 0;
        this.dmax = 0.0f;
        this.scale = 0.7f;
        this.x2 = 0.0f;
        this.y2 = 0.0f;
        this.z2 = 0.0f;
        this.axis = 0;
        this.anglestep = 5;
        this.m11 = 1.0;
        this.m12 = 0.0;
        this.m13 = 0.0;
        this.m21 = 0.0;
        this.m22 = 1.0;
        this.m23 = 0.0;
        this.m31 = 0.0;
        this.m32 = 0.0;
        this.m33 = 1.0;
        this.bgR = 0;
        this.bgG = 0;
        this.bgB = 0;
        this.faceR = 255;
        this.faceG = 255;
        this.faceB = 255;
        this.wfR = 0;
        this.wfG = 0;
        this.wfB = 0;
        this.closedSolid = 1;
        this.registered = false;
    }
    
    public void init() {
        super.init();
        this.width = this.size().width;
        this.height = this.size().height;
        this.canvasw = this.width - 20;
        this.canvash = this.height - 120;
        final String parameter = this.getParameter("regcode");
        parameter.trim();
        parameter.toUpperCase();
        int n = parameter.charAt(0) - '0';
        if (n > 9) {
            n -= 7;
        }
        final char c = (char)(parameter.charAt(parameter.length() - 1) - '0');
        if (n + 3 < parameter.length() && new Integer(parameter.substring(n, n + 3)) == 163 && c == n * 163 % 10) {
            this.registered = true;
        }
        this.tovdata = this.getParameter("tovdata");
        this.anglestep = new Integer(this.getParameter("anglestep"));
        this.scale = new Float(this.getParameter("scale"));
        if (this.scale > 1.0f) {
            this.scale = 1.0f;
        }
        if (this.scale < 0.1f) {
            this.scale = 0.1f;
        }
        final int[] int1 = this.parseInt(this.getParameter("objcolor"));
        this.faceR = int1[0];
        this.faceG = int1[1];
        this.faceB = int1[2];
        final int[] int2 = this.parseInt(this.getParameter("wfcolor"));
        this.wfR = int2[0];
        this.wfG = int2[1];
        this.wfB = int2[2];
        final int[] int3 = this.parseInt(this.getParameter("bgcolor"));
        this.bgR = int3[0];
        this.bgG = int3[1];
        this.bgB = int3[2];
        this.delay = new Integer(this.getParameter("delay"));
        DataInputStream dataInputStream = null;
        final String tovdata = this.tovdata;
        this.showStatus("Reading TOV data file " + this.tovdata + "...");
        try {
            URL url;
            if (this.tovdata.toUpperCase().startsWith("HTTP")) {
                url = new URL(tovdata);
            }
            else {
                url = new URL(this.getDocumentBase(), this.tovdata);
            }
            dataInputStream = new DataInputStream(url.openStream());
        }
        catch (Exception ex) {
            this.showStatus("[Error]: Exception: " + ex + " File = " + tovdata);
            this.stop();
        }
        try {
            this.target = dataInputStream.readLine();
            final int[] int4 = this.parseInt(dataInputStream.readLine());
            this.tovpcount = int4[0];
            this.tovlcount = int4[1];
            this.tovtcount = int4[2];
            if (this.tovpcount > 0) {
                this.tovp = new float[this.tovpcount][3];
                for (int i = 0; i < this.tovpcount; ++i) {
                    final float[] float1 = this.parseFloat(dataInputStream.readLine());
                    this.tovp[i][0] = float1[1];
                    this.tovp[i][1] = float1[2];
                    this.tovp[i][2] = float1[3];
                }
            }
            if (this.tovlcount > 0) {
                this.tovl = new int[this.tovlcount][2];
                for (int j = 0; j < this.tovlcount; ++j) {
                    final int[] int5 = this.parseInt(dataInputStream.readLine());
                    this.tovl[j][0] = int5[1];
                    this.tovl[j][1] = int5[2];
                }
            }
            if (this.tovtcount > 0) {
                this.tovt = new int[this.tovtcount][3];
                for (int k = 0; k < this.tovtcount; ++k) {
                    final int[] int6 = this.parseInt(dataInputStream.readLine());
                    this.tovt[k][0] = int6[1];
                    this.tovt[k][1] = int6[2];
                    this.tovt[k][2] = int6[3];
                }
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {
            this.showStatus("[Error]: Exception: " + ex2 + " File: " + tovdata);
        }
        this.rp = new tovcanvas(this.canvasw, this.canvash);
        this.setbox();
        this.rp.sketch = true;
        this.rp.allocateSpace();
        this.rp.angle = 0.26179916666666664;
        this.rp.axis = 0;
        this.rp.rotMatrix();
        this.rp.rotMatrix();
        this.rp.axis = 1;
        this.rp.rotMatrix();
        this.rp.rotMatrix();
        this.rp.angle = this.anglestep * 3.14159 / 180.0;
        this.rp.displaymode = 1;
        this.rp.registered = this.registered;
        this.rp.rotateObj = true;
        this.rp.repaint();
        this.readPhoto("tovwf.gif");
        this.showWireframe = new tovButton(new tovImage(this.photo, this.picwidth, this.picheight), 50, 6, "wireframe", 1, 15);
        this.readPhoto("tovshade.gif");
        this.showShade = new tovButton(new tovImage(this.photo, this.picwidth, this.picheight), 50, 6, "shaded", 1, 15);
        this.setBackground(new Color(192, 192, 192));
        this.setForeground(Color.black);
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        final Panel panel = new Panel();
        this.ri = new tovdata(this.canvasw, 30);
        this.ri.target = this.target;
        panel.add(this.ri);
        final Panel panel2 = new Panel();
        panel2.add(this.rp);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add(this.showWireframe);
        panel3.add(this.showShade);
        final Panel panel4 = new Panel();
        panel4.setLayout(new GridLayout(2, 4));
        panel4.add(this.butx);
        panel4.add(this.buty);
        panel4.add(this.butz);
        panel4.add(this.butStop);
        panel4.add(this.butxn);
        panel4.add(this.butyn);
        panel4.add(this.butzn);
        panel4.add(this.butReset);
        panel4.repaint();
        panel3.add(panel4);
        layout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        layout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        layout.setConstraints(panel3, gridBagConstraints);
        this.add(panel3);
        this.resize(this.width, this.height);
        if (this.registered) {
            this.showStatus("");
        }
        else {
            this.showStatus("tov (C) 2001 The J Maker");
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
        this.showStatus("Image " + s + " (" + this.picwidth + "x" + this.picheight + ") ready");
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            final char char1 = nextToken.charAt(0);
            new Character(char1);
            if (!Character.isLetter(char1)) {
                array[i] = Integer.parseInt(nextToken);
            }
        }
        return array;
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
        System.gc();
        int n = 0;
        while (Thread.currentThread() == this.t) {
            if (n++ > 1000) {
                n = 0;
                System.gc();
            }
            if (this.rp.rotateObj) {
                this.rp.repaint();
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
    }
    
    float[] parseFloat(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final float[] array = new float[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            final char char1 = nextToken.charAt(0);
            new Character(char1);
            if (!Character.isLetter(char1)) {
                array[i] = new Float(nextToken);
            }
        }
        return array;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(new Color(128, 128, 128));
        for (int i = 0; i < 4; ++i) {
            if (i < 2) {
                graphics.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, true);
            }
            else {
                graphics.draw3DRect(i, i, this.width - 1 - i * 2, this.height - 1 - i * 2, false);
            }
        }
    }
    
    void setbox() {
        final float xmin = 100000.0f;
        this.zmin = xmin;
        this.ymin = xmin;
        this.xmin = xmin;
        final float xmax = -100000.0f;
        this.zmax = xmax;
        this.ymax = xmax;
        this.xmax = xmax;
        for (int i = 0; i < this.tovpcount; ++i) {
            if (this.tovp[i][0] < this.xmin) {
                this.xmin = this.tovp[i][0];
            }
            if (this.tovp[i][0] > this.xmax) {
                this.xmax = this.tovp[i][0];
            }
            if (this.tovp[i][1] < this.ymin) {
                this.ymin = this.tovp[i][1];
            }
            if (this.tovp[i][1] > this.ymax) {
                this.ymax = this.tovp[i][1];
            }
            if (this.tovp[i][2] < this.zmin) {
                this.zmin = this.tovp[i][2];
            }
            if (this.tovp[i][2] > this.zmax) {
                this.zmax = this.tovp[i][2];
            }
        }
        this.dmax = this.xmax - this.xmin;
        if (this.ymax - this.ymin > this.dmax) {
            this.dmax = this.ymax - this.ymin;
        }
        if (this.zmax - this.zmin > this.dmax) {
            this.dmax = this.zmax - this.zmin;
        }
        this.scale = this.scale * this.canvasw / this.dmax;
        this.rp.xmin = this.xmin;
        this.rp.ymin = this.ymin;
        this.rp.zmin = this.zmin;
        this.rp.xmax = this.xmax;
        this.rp.ymax = this.ymax;
        this.rp.zmax = this.zmax;
        this.rp.dmax = this.dmax;
        this.rp.scale = this.scale;
        this.rp.tovpcount = this.tovpcount;
        this.rp.tovp = this.tovp;
        this.rp.tovlcount = this.tovlcount;
        this.rp.tovl = this.tovl;
        this.rp.tovtcount = this.tovtcount;
        this.rp.tovt = this.tovt;
        this.rp.xmid = (this.xmax + this.xmin) / 2.0f;
        this.rp.ymid = (this.ymax + this.ymin) / 2.0f;
        this.rp.zmid = (this.zmax + this.zmin) / 2.0f;
        this.rp.xoff = this.canvasw / 2;
        this.rp.yoff = this.canvash / 2;
        this.rp.scale = this.scale;
        this.rp.faceR = this.faceR;
        this.rp.faceG = this.faceG;
        this.rp.faceB = this.faceB;
        this.rp.wfR = this.wfR;
        this.rp.wfG = this.wfG;
        this.rp.wfB = this.wfB;
        this.rp.bgR = this.bgR;
        this.rp.bgG = this.bgG;
        this.rp.bgB = this.bgB;
        this.rp.closedSolid = this.closedSolid;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public boolean action(final Event event, final Object o) {
        this.repaint();
        if (event.target == this.butx) {
            this.rp.axis = 0;
            this.rp.direct = 1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.butxn) {
            this.rp.axis = 0;
            this.rp.direct = -1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.buty) {
            this.rp.axis = 1;
            this.rp.direct = 1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.butyn) {
            this.rp.axis = 1;
            this.rp.direct = -1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.butz) {
            this.rp.axis = 2;
            this.rp.direct = -1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.butzn) {
            this.rp.axis = 2;
            this.rp.direct = 1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.butStop) {
            this.rp.rotateObj = false;
            return true;
        }
        if (event.target == this.butReset) {
            this.rp.resetMatrix();
            this.rp.angle = 0.26179916666666664;
            this.rp.axis = 0;
            this.rp.rotMatrix();
            this.rp.rotMatrix();
            this.rp.axis = 1;
            this.rp.rotMatrix();
            this.rp.rotMatrix();
            this.rp.angle = this.anglestep * 3.14159 / 180.0;
            this.rp.rotateObj = false;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.showWireframe) {
            this.rp.displaymode = 0;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        if (event.target == this.showShade) {
            this.rp.displaymode = 1;
            this.rp.rotateObj = true;
            this.rp.repaint();
            return true;
        }
        return false;
    }
}
