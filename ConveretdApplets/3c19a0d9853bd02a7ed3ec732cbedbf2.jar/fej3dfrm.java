import javax.media.j3d.J3DGraphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Canvas3D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Canvas;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsEnvironment;
import javax.media.j3d.GraphicsConfigTemplate3D;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.event.KeyEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ComponentEvent;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Scrollbar;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Button;
import java.awt.Image;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class fej3dfrm extends Frame implements Runnable, ComponentListener, WindowListener, ActionListener, MouseListener, MouseMotionListener, KeyListener, ItemListener, AdjustmentListener
{
    String wmsg;
    String wmsg1;
    String wmsg2;
    Image img;
    Image new_img3;
    Image new_img4;
    Image img4;
    Image img5;
    Button bt0;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button bt7;
    Button bt8;
    Button bt9;
    Button bt10;
    Button bt11;
    Button bt12;
    Button bt13;
    Button bt14;
    fej3dmain applet;
    Thread trd;
    int w;
    int wm;
    int h;
    int hm;
    int sttype;
    int swp;
    int zoom;
    int zoompv;
    int nfit;
    int zmax;
    int apx;
    int apy;
    int apx1;
    int apx2;
    int apx3;
    int apx4;
    int apy1;
    int apy2;
    int apy3;
    int apy4;
    int sx;
    int sy;
    int dx;
    int dy;
    int dispx;
    int dispy;
    int centx;
    int centy;
    int dltx;
    int dlty;
    int dxl;
    int dxr;
    int dyl;
    int dyr;
    int nstart;
    int nfirst;
    int nSS;
    int nSF;
    int nST;
    int nFT;
    int nFB;
    int nFL;
    int nFR;
    int nshift;
    int gl4;
    int nlg;
    int nclear;
    int nSimagecnt;
    int[] pix;
    int[] new_pix3;
    int[] new_pix4;
    MemoryImageSource mimg3;
    MemoryImageSource mimg4;
    Graphics gm;
    Scrollbar sr;
    Dimension d;
    String[] strfil;
    public static final int TYPE_PARALLEL = 0;
    public static final int TYPE_CROSSED = 1;
    public static final int TYPE_ABOVEBELOW = 2;
    public static final int TYPE_ANABWREDCYAN = 3;
    public static final int TYPE_ANABWREDBLUE = 4;
    public static final int TYPE_ANABWREDGREEN = 5;
    public static final int TYPE_ANARCCOLOR = 6;
    public static final int TYPE_ANADUBOIS_RC = 7;
    public static final int TYPE_ANARCHALFCOLOR = 8;
    public static final int TYPE_ANABW_YB = 9;
    public static final int TYPE_ANA_COLORYB = 10;
    public static final int TYPE_ANA_HCOLORYB = 11;
    public static final int TYPE_RINTERLACED = 12;
    public static final int TYPE_CINTERLACED = 13;
    public static final int TYPE_SHARP3D = 14;
    public static final int TYPE_3DHDTV = 15;
    public static final int TYPE_MIRRORL = 16;
    public static final int TYPE_MIRRORR = 17;
    public static final int TYPE_MIRRORBOTH = 18;
    public static final int TYPE_HOLOBLADE = 19;
    public static final int TYPE_SINGLEIMAGE = 20;
    public static final int TYPE_PAGEFLIP = 21;
    public static final int NB_TYPES = 22;
    static final double[][] Dubois;
    static final double[][] DuboisYB;
    Choice c;
    Choice c1;
    Label lb;
    Label lb1;
    Label lb2;
    String[] stfile;
    String[] stfile1;
    String[] stname;
    String[] stmess;
    int stmax;
    int stimg;
    String stt;
    String ver;
    int nWF;
    int nwhl;
    canvascont Cancont;
    canvascontwh Cancontwh;
    canvascontnw Cancontnw;
    SimpleUniverse universe;
    int nFull;
    String fnm;
    int stfrm;
    int nSmooth;
    int nSmoothimg;
    int nSmoothnew;
    int nSpace;
    String charset;
    
    public fej3dfrm(final fej3dmain applet, final String stt, final String ver, final int stfrm) {
        this.wmsg = "Loading images...";
        this.wmsg1 = "Preparing the image display...";
        this.wmsg2 = "The image size is too large! Can not show the stereo image.";
        this.img = null;
        this.new_img3 = null;
        this.new_img4 = null;
        this.img4 = null;
        this.img5 = null;
        this.trd = null;
        this.w = 0;
        this.h = 0;
        this.sttype = 1;
        this.swp = 0;
        this.zoom = 100;
        this.zoompv = 100;
        this.nfit = 0;
        this.zmax = 200;
        this.sx = 0;
        this.sy = 0;
        this.dx = 0;
        this.dy = 0;
        this.dltx = 0;
        this.dlty = 0;
        this.dxl = 0;
        this.dxr = 0;
        this.dyl = 0;
        this.dyr = 0;
        this.nstart = 0;
        this.nfirst = 1;
        this.nSS = 3;
        this.nSF = 0;
        this.nST = 3;
        this.nshift = 0;
        this.gl4 = 0;
        this.nlg = 0;
        this.nclear = 0;
        this.nSimagecnt = 0;
        this.sr = new Scrollbar(0, 1, 10, 1, 210);
        this.strfil = new String[] { "Parallel-eyed", "Cross-eyed", "Above/Below", "Red/Cyan", "Red/Blue", "Red/Green", "Color Anaglyph", "Dubois Anaglyph", "Half color", "Yellow/Blue(Y/B)", "Color(Y/B)", "Half color(Y/B)", "Row Interlaced", "Column Interlaced", "Sharp 3D LCD.", "3D DLP TV", "Mirrored left", "Mirrored right", "Mirrored both", "Holoblade", "Single Image", "Page-flip" };
        this.stimg = 0;
        this.nWF = 0;
        this.nwhl = 0;
        this.Cancont = null;
        this.Cancontwh = null;
        this.Cancontnw = null;
        this.universe = null;
        this.nSmooth = 0;
        this.nSmoothimg = 0;
        this.nSmoothnew = 0;
        this.nSpace = 0;
        this.stt = stt;
        this.ver = ver;
        this.setSize(100, 100);
        this.setTitle(this.stt);
        this.applet = applet;
        this.stfrm = stfrm;
        this.wmsg = this.applet.getProperty("lg_Loading", this.wmsg);
        this.wmsg1 = this.applet.getProperty("lg_Preparing", this.wmsg1);
        this.wmsg2 = this.applet.getProperty("lg_PictureTooBig", this.wmsg2);
        for (int i = 0; i < 22; ++i) {
            String string = "lg_mode_";
            if (i < 10) {
                string += "0";
            }
            this.strfil[i] = this.applet.getProperty(string + i, this.strfil[i]);
        }
    }
    
    public void init(int n, int n2) {
        this.show();
        this.nfirst = 1;
        final Insets insets = this.getInsets();
        this.nFT = insets.top;
        this.nFT += 3;
        this.nFB = insets.bottom;
        this.nFL = insets.left;
        if (this.nFL == 0) {
            this.nFL = 4;
        }
        this.nFR = insets.right;
        if (this.nFR == 0) {
            this.nFR = 4;
        }
        n = n + this.nFL + this.nFR;
        n2 = n2 + this.nFT + this.nFB + 25;
        this.setSize(n, n2);
        this.setBackground(Color.lightGray);
        (this.gm = this.getGraphics()).clearRect(0, 0, n, n2);
        this.setLayout(null);
        this.add(this.bt1 = new Button("<"));
        this.add(this.bt2 = new Button(">"));
        this.readtxt(this.charset);
        if (this.stmax == 0) {
            this.stfile = new String[1];
            this.stname = new String[1];
            this.stmess = new String[1];
            this.stfile[0] = " ";
        }
        this.fnm = this.applet.getParam("imgfile");
        if (this.fnm != null && this.fnm.length() > 4) {
            this.stimg = -1;
            for (int i = 0; i < this.stmax; ++i) {
                if (this.stfile[i].equals(this.fnm)) {
                    this.stimg = i;
                }
            }
            if (this.stimg == -1) {
                this.stfile[this.stmax] = this.fnm;
                this.stname[this.stmax] = this.fnm;
                this.stmess[this.stmax] = null;
                this.stimg = this.stmax;
                ++this.stmax;
            }
        }
        if (this.stfrm > 0 && this.stfrm < this.stmax + 1) {
            this.stimg = this.stfrm - 1;
        }
        this.c1 = new Choice();
        for (int j = 0; j < this.stmax; ++j) {
            this.c1.addItem(this.stname[j]);
        }
        this.add(this.c1);
        this.add(this.c = new Choice());
        this.add(this.bt5 = new Button(this.applet.getProperty("lg_swap", "Swap")));
        this.add(this.sr);
        this.add(this.lb = new Label(""));
        this.add(this.bt6 = new Button("100%"));
        this.add(this.bt7 = new Button(this.applet.getProperty("lg_FIT", "FIT")));
        this.add(this.bt8 = new Button(this.applet.getProperty("lg_L", "L")));
        this.add(this.bt9 = new Button(this.applet.getProperty("lg_R", "R")));
        this.add(this.bt10 = new Button(this.applet.getProperty("lg_U", "U")));
        this.add(this.bt11 = new Button(this.applet.getProperty("lg_D", "D")));
        this.add(this.bt12 = new Button(this.applet.getProperty("lg_Reset", "Reset")));
        this.add(this.bt13 = new Button(this.applet.getProperty("lg_fast", "Fast")));
        this.add(this.bt14 = new Button(this.applet.getProperty("lg_SPC", "SPC")));
        this.add(this.bt0 = new Button(this.applet.getProperty("lg_SS", "SS")));
        this.add(this.bt3 = new Button("<"));
        this.add(this.bt4 = new Button(">"));
        this.add(this.lb1 = new Label("" + this.nSS + "s"));
        this.add(this.lb2 = new Label(this.ver));
        this.nFull = (this.applet.getParamint(1, "menu") + 1) % 2;
        this.SetButton();
        this.swp = this.applet.getParamint(0, "swap");
        if (this.swp == 0) {
            this.bt5.setForeground(Color.black);
        }
        else {
            this.swp = 1;
            this.bt5.setForeground(Color.blue);
        }
        this.bt5.setLabel(this.applet.getProperty("lg_swap", "Swap"));
        this.nSmooth = this.applet.getParamint(0, "fast");
        if (this.nSmooth == 0) {
            this.bt13.setForeground(Color.black);
        }
        else {
            this.nSmooth = 1;
            this.bt13.setForeground(Color.blue);
        }
        this.bt13.setLabel(this.applet.getProperty("lg_fast", "Fast"));
        this.nSpace = this.applet.getParamint(0, "spacing");
        if (this.nSpace == 0) {
            this.bt14.setForeground(Color.black);
        }
        else if (this.nSpace == 1) {
            this.bt14.setForeground(Color.blue);
        }
        else {
            this.nSpace = 2;
            this.bt14.setForeground(Color.red);
        }
        this.bt14.setLabel(this.applet.getProperty("lg_SPC", "SPC"));
        this.sttype = this.applet.getParamint(1, "type");
        if (this.sttype < 0) {
            this.sttype = 0;
        }
        if (this.sttype >= 22) {
            this.sttype = 21;
        }
        if (this.sttype == 21) {
            this.gl4 = 2;
            try {
                Class.forName("javax.media.j3d.Canvas3D");
            }
            catch (Exception ex) {
                this.gl4 = 1;
                this.sttype = 1;
            }
        }
        this.nwhl = 1;
        try {
            Class.forName("java.awt.event.MouseWheelEvent");
        }
        catch (Exception ex2) {
            this.nwhl = 0;
            this.gl4 = 1;
        }
        if (this.nwhl == 1) {
            this.Cancontwh = new canvascontwh(this);
        }
        else {
            this.Cancontnw = new canvascontnw(this);
        }
        if (this.gl4 == 1) {
            for (int k = 0; k < 21; ++k) {
                this.c.addItem(this.strfil[k]);
            }
        }
        else {
            for (int l = 0; l < 22; ++l) {
                this.c.addItem(this.strfil[l]);
            }
        }
        this.c.select(this.sttype);
        this.zoompv = this.zoom;
        this.zoom = this.applet.getParamint(0, "zoom");
        if (this.zoom == 0) {
            this.nfit = 1;
        }
        this.nSF = this.applet.getParamint(0, "slideshow");
        if (this.nSF == 0 || this.nSF > 99 || this.stmax < 2) {
            this.nSF = 0;
            this.nSS = 3;
            this.nST = 3;
        }
        else {
            int nsf = this.nSF;
            if (this.nSF < 0) {
                nsf = -this.nSF;
                this.nSF = 0;
            }
            else {
                this.nSF = 1;
            }
            this.nSS = nsf;
            this.nST = nsf;
            if (this.nSF != 0) {
                this.bt0.setForeground(Color.blue);
            }
            this.bt0.setLabel(this.applet.getProperty("lg_SS", "SS"));
            this.lb1.setText("" + this.nST + "s");
        }
        this.bt0.addActionListener(this);
        this.bt1.addActionListener(this);
        this.bt2.addActionListener(this);
        this.bt3.addActionListener(this);
        this.bt4.addActionListener(this);
        this.bt5.addActionListener(this);
        this.bt6.addActionListener(this);
        this.bt7.addActionListener(this);
        this.bt8.addActionListener(this);
        this.bt9.addActionListener(this);
        this.bt10.addActionListener(this);
        this.bt11.addActionListener(this);
        this.bt12.addActionListener(this);
        this.bt13.addActionListener(this);
        this.bt14.addActionListener(this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addWindowListener(this);
        this.addComponentListener(this);
        this.sr.addAdjustmentListener(this);
        this.c.addItemListener(this);
        this.c1.addItemListener(this);
        this.readImag(this.stimg);
        if (this.sttype != 21) {
            if (this.nwhl == 1) {
                this.Cancontwh.canstart();
            }
            else {
                this.Cancontnw.canstart();
            }
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
            if (this.nwhl == 1) {
                this.Cancontwh.canrepaint(1);
            }
            else {
                this.Cancontnw.canrepaint(1);
            }
        }
        this.nfirst = 0;
    }
    
    public void setCharset(final String charset) {
        this.charset = charset;
    }
    
    void SetButton() {
        if (this.nFull == 0) {
            if (this.stmax > 1) {
                this.bt1.setBounds(5, this.nFT, 20, 20);
                this.c1.setBounds(25, this.nFT, 140, 20);
                this.bt2.setBounds(165, this.nFT, 20, 20);
                this.c.setBounds(190, this.nFT, 120, 20);
                this.bt5.setBounds(315, this.nFT, 40, 20);
                this.bt13.setBounds(355, this.nFT, 40, 20);
                this.bt14.setBounds(395, this.nFT, 35, 20);
                this.sr.setBounds(435, this.nFT, 100, 20);
                this.lb.setBounds(535, this.nFT, 32, 20);
                this.bt6.setBounds(567, this.nFT, 40, 20);
                this.bt7.setBounds(607, this.nFT, 28, 20);
                this.bt8.setBounds(640, this.nFT, 20, 20);
                this.bt9.setBounds(660, this.nFT, 20, 20);
                this.bt10.setBounds(680, this.nFT, 20, 20);
                this.bt11.setBounds(700, this.nFT, 20, 20);
                this.bt12.setBounds(720, this.nFT, 43, 20);
                this.bt0.setBounds(768, this.nFT, 27, 20);
                this.bt3.setBounds(795, this.nFT, 20, 20);
                this.bt4.setBounds(815, this.nFT, 20, 20);
                this.lb1.setBounds(835, this.nFT, 50, 20);
                this.lb2.setBounds(885, this.nFT, 60, 20);
            }
            else {
                this.bt1.setBounds(10000, this.nFT, 0, 0);
                this.c1.setBounds(10000, this.nFT, 0, 0);
                this.bt2.setBounds(10000, this.nFT, 0, 0);
                this.bt0.setBounds(10000, this.nFT, 0, 0);
                this.bt3.setBounds(10000, this.nFT, 0, 0);
                this.bt4.setBounds(10000, this.nFT, 0, 0);
                this.lb1.setBounds(10000, this.nFT, 0, 0);
                this.c.setBounds(5, this.nFT, 150, 20);
                this.bt5.setBounds(160, this.nFT, 40, 20);
                this.bt13.setBounds(200, this.nFT, 40, 20);
                this.bt14.setBounds(240, this.nFT, 35, 20);
                this.sr.setBounds(280, this.nFT, 150, 20);
                this.lb.setBounds(435, this.nFT, 35, 20);
                this.bt6.setBounds(475, this.nFT, 40, 20);
                this.bt7.setBounds(515, this.nFT, 30, 20);
                this.bt8.setBounds(550, this.nFT, 20, 20);
                this.bt9.setBounds(570, this.nFT, 20, 20);
                this.bt10.setBounds(590, this.nFT, 20, 20);
                this.bt11.setBounds(610, this.nFT, 20, 20);
                this.bt12.setBounds(630, this.nFT, 45, 20);
                this.lb2.setBounds(675, this.nFT, 60, 20);
            }
        }
        else {
            this.bt1.setBounds(10000, this.nFT, 0, 0);
            this.c1.setBounds(10000, this.nFT, 0, 0);
            this.bt2.setBounds(10000, this.nFT, 0, 0);
            this.c.setBounds(10000, this.nFT, 0, 0);
            this.bt5.setBounds(10000, this.nFT, 0, 0);
            this.bt13.setBounds(10000, this.nFT, 0, 0);
            this.bt14.setBounds(10000, this.nFT, 0, 0);
            this.sr.setBounds(10000, this.nFT, 0, 0);
            this.lb.setBounds(10000, this.nFT, 0, 0);
            this.bt6.setBounds(10000, this.nFT, 0, 0);
            this.bt7.setBounds(10000, this.nFT, 0, 0);
            this.bt8.setBounds(10000, this.nFT, 0, 0);
            this.bt9.setBounds(10000, this.nFT, 0, 0);
            this.bt10.setBounds(10000, this.nFT, 0, 0);
            this.bt11.setBounds(10000, this.nFT, 0, 0);
            this.bt12.setBounds(10000, this.nFT, 0, 0);
            this.bt0.setBounds(10000, this.nFT, 0, 0);
            this.bt3.setBounds(10000, this.nFT, 0, 0);
            this.bt4.setBounds(10000, this.nFT, 0, 0);
            this.lb1.setBounds(10000, this.nFT, 0, 0);
            this.lb2.setBounds(10000, this.nFT, 0, 0);
        }
        this.d = this.getSize();
        this.apx = this.d.width;
        this.apy = this.d.height;
        this.apx1 = 0;
        this.apx4 = this.apx;
        this.apx2 = this.apx1 + (this.apx4 - 5) / 2;
        this.apx3 = this.apx4 - (this.apx4 - 5) / 2;
        this.apy1 = 0;
        if (this.nFull == 0) {
            this.apy4 = this.apy - (this.nFB + this.nFT + 24);
        }
        else {
            this.apy4 = this.apy - (this.nFB + this.nFT - 3);
        }
        this.apy2 = this.apy1 + (this.apy4 - 5) / 2;
        this.apy3 = this.apy4 - (this.apy4 - 5) / 2;
    }
    
    public void start() {
        if (this.trd == null) {
            (this.trd = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.trd = null;
    }
    
    public void run() {
        int n = 10;
        while (this.trd == Thread.currentThread()) {
            if (n == 0) {
                this.Timer();
                n = 10;
            }
            if (this.nSimagecnt > 0) {
                --this.nSimagecnt;
                if (this.nSimagecnt == 0) {
                    this.Timer2();
                }
            }
            --n;
            try {
                final Thread trd = this.trd;
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.img != null) {
            this.img.flush();
            this.img = null;
        }
        if (this.new_img3 != null) {
            this.new_img3.flush();
            this.new_img3 = null;
        }
        if (this.new_img4 != null) {
            this.new_img4.flush();
            this.new_img4 = null;
        }
        if (this.img4 != null) {
            this.img4.flush();
            this.img4 = null;
        }
        if (this.img5 != null) {
            this.img5.flush();
            this.img5 = null;
        }
    }
    
    public void Timer2() {
        this.readImag(this.stimg);
        this.nSmoothimg = 2;
        this.nSmoothnew = 2;
        this.setstpixnew(1);
    }
    
    public void Timer() {
        if (this.nSF == 0) {
            return;
        }
        --this.nST;
        this.lb1.setText("" + this.nST + " s");
        this.showfileinfo();
        if (this.nST == 0) {
            this.nSF = 2;
            this.nST = this.nSS;
            ++this.stimg;
            if (this.stimg > this.stmax - 1) {
                this.stimg = 0;
            }
            this.readImag(this.stimg);
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
            if (this.nwhl == 1) {
                this.Cancontwh.canrepaint(1);
            }
            else {
                this.Cancontnw.canrepaint(1);
            }
            this.nSF = 1;
        }
    }
    
    public void readtxt(final String s) {
        int n = 0;
        try {
            final InputStream inputstm = this.applet.inputstm();
            if (inputstm == null) {
                return;
            }
            InputStreamReader inputStreamReader;
            try {
                inputStreamReader = ((s == null) ? new InputStreamReader(inputstm) : new InputStreamReader(inputstm, s));
            }
            catch (UnsupportedEncodingException ex) {
                inputStreamReader = new InputStreamReader(inputstm);
            }
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 64000);
            bufferedReader.mark(64000);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",", false);
                if (stringTokenizer.hasMoreTokens() && stringTokenizer.nextToken().equals("L&R")) {
                    this.nWF = 1;
                }
                ++n;
            }
            this.stmax = n + 1;
            this.stfile = new String[this.stmax];
            if (this.nWF == 1) {
                this.stfile1 = new String[this.stmax];
            }
            this.stname = new String[this.stmax];
            this.stmess = new String[this.stmax];
            int stmax = 0;
            bufferedReader.reset();
            String line2;
            while ((line2 = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line2, ",", true);
                if (stringTokenizer2.hasMoreTokens()) {
                    this.stfile[stmax] = stringTokenizer2.nextToken();
                    if (this.stfile[stmax].equals("L&R")) {
                        continue;
                    }
                    if (this.stfile[stmax].equals("button")) {
                        continue;
                    }
                    final int length = this.stfile[stmax].length();
                    if (length < 5) {
                        continue;
                    }
                    final String lowerCase = this.stfile[stmax].substring(length - 4, length).toLowerCase();
                    if (!lowerCase.equals(".jpg") && !lowerCase.equals("jpeg") && !lowerCase.equals(".png") && !lowerCase.equals(".gif") && !lowerCase.equals(".jps")) {
                        continue;
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        stringTokenizer2.nextToken();
                    }
                    if (this.nWF == 1) {
                        if (stringTokenizer2.hasMoreTokens()) {
                            this.stfile1[stmax] = stringTokenizer2.nextToken();
                        }
                        else {
                            this.stfile1[stmax] = null;
                        }
                        if (stringTokenizer2.hasMoreTokens()) {
                            stringTokenizer2.nextToken();
                        }
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.stname[stmax] = stringTokenizer2.nextToken();
                    }
                    else {
                        this.stname[stmax] = " ";
                    }
                    if (this.stname[stmax].equals(",")) {
                        this.stname[stmax] = " ";
                    }
                    else if (stringTokenizer2.hasMoreTokens()) {
                        stringTokenizer2.nextToken();
                    }
                    if (this.stname[stmax].equals(" ")) {
                        this.stname[stmax] = this.stfile[stmax];
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.stmess[stmax] = stringTokenizer2.nextToken();
                    }
                    else {
                        this.stmess[stmax] = null;
                    }
                    ++stmax;
                }
            }
            this.stmax = stmax;
            inputStreamReader.close();
        }
        catch (Exception ex2) {}
    }
    
    public void ShowImag(final int n) {
        if (n > 0 && n < this.stmax + 1) {
            this.requestFocus();
            this.stimg = n - 1;
            this.nSimagecnt = 3;
        }
    }
    
    public void readImag(final int n) {
        this.setTitle(this.wmsg);
        if (this.img != null) {
            this.img.flush();
            this.img = null;
        }
        if (this.new_img3 != null) {
            this.new_img3.flush();
            this.new_img3 = null;
        }
        if (this.new_img4 != null) {
            this.new_img4.flush();
            this.new_img4 = null;
        }
        if (this.img4 != null) {
            this.img4.flush();
            this.img4 = null;
        }
        if (this.img5 != null) {
            this.img5.flush();
            this.img5 = null;
        }
        if (this.nlg == 1) {
            this.free();
        }
        if (this.nWF == 0) {
            this.img = this.applet.getI(this.stfile[n]);
        }
        else {
            final Image i = this.applet.getI(this.stfile[n]);
            final Image j = this.applet.getI(this.stfile1[n]);
            final int width = i.getWidth(this);
            this.img = this.createImage(width * 2, i.getHeight(this));
            final Graphics graphics = this.img.getGraphics();
            graphics.drawImage(i, 0, 0, this);
            graphics.drawImage(j, width, 0, this);
            i.flush();
            j.flush();
        }
        this.setTitle(this.wmsg1);
        if (n < this.c1.getItemCount()) {
            this.c1.select(n);
        }
        this.w = this.img.getWidth(this);
        this.h = this.img.getHeight(this);
        if (this.w < 10) {
            this.w = 100;
        }
        if (this.h < 10) {
            this.h = 50;
        }
        this.zmax = 25000000 / (this.w * 4);
        this.zmax = this.zmax * 100 / this.h;
        this.nlg = 0;
        if (this.zmax > 200) {
            this.zmax = 200;
        }
        if (this.zmax < 100) {
            this.zmax = 100;
            this.nlg = 1;
        }
        this.sr.setMaximum(this.zmax + 10);
        this.wm = this.w / 2 * this.zmax / 100;
        this.hm = this.h * this.zmax / 100;
        this.dispx = this.w / 2;
        this.dispy = this.h;
        this.centx = 0;
        this.centy = 0;
        this.pix = null;
        this.new_pix3 = null;
        this.new_pix4 = null;
        if (this.nlg == 1) {
            this.nstart = 1;
        }
        else {
            this.pix = new int[this.w * this.h];
            this.new_pix3 = new int[this.wm * this.hm];
            this.new_pix4 = new int[this.w * this.h];
            this.setPix1();
            if (this.zoom == 0 || this.nfit == 1) {
                this.nfit = 1;
                this.FitSize();
            }
            if (this.zoom > this.zmax) {
                this.zoom = this.zmax;
            }
            if (this.sttype >= 3 && this.sttype <= 15) {
                this.nstart = 1;
                this.setPix5();
                if (this.sttype == 3) {
                    this.setPix2(0);
                }
                if (this.sttype == 4) {
                    this.setPix2(1);
                }
                if (this.sttype == 5) {
                    this.setPix2(2);
                }
                if (this.sttype == 6) {
                    this.setPix3(0);
                }
                if (this.sttype == 7) {
                    this.setPix3(2);
                }
                if (this.sttype == 8) {
                    this.setPix3(1);
                }
                if (this.sttype == 9) {
                    this.setPix2(3);
                }
                if (this.sttype == 10) {
                    this.setPix3(3);
                }
                if (this.sttype == 11) {
                    this.setPix3(4);
                }
                if (this.sttype == 12) {
                    this.setPix4(0);
                }
                if (this.sttype == 13) {
                    this.setPix4(2);
                }
                if (this.sttype == 14) {
                    this.setPix4(1);
                }
                if (this.sttype == 15) {
                    this.setPix4(3);
                }
            }
        }
        if (this.sttype == 21) {
            if (this.nfirst == 1) {
                if (this.Cancont == null) {
                    this.Cancont = new canvascont(this);
                }
                this.Cancont.canvasstart();
            }
            else {
                this.Cancont.canvasmkimg();
                this.Cancont.canvasrepaint();
            }
        }
    }
    
    public void free() {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().runFinalization();
        Runtime.getRuntime().gc();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.wclose();
    }
    
    public void wclose() {
        final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        final Rectangle bounds = this.getBounds();
        this.applet.setst(this.sttype, this.swp, this.zoom, this.nFull, bounds.x, bounds.y, bounds.width, bounds.height, this.nSmooth, this.nSpace);
        if (this.sttype == 21) {
            this.Cancont.canvasstop();
        }
        else if (this.nwhl == 1) {
            this.Cancontwh.canstop();
        }
        else {
            this.Cancontnw.canstop();
        }
        this.pix = null;
        this.new_pix3 = null;
        this.new_pix4 = null;
        this.mimg3 = null;
        this.mimg4 = null;
        if (this.img != null) {
            this.img.flush();
            this.img = null;
        }
        if (this.new_img3 != null) {
            this.new_img3.flush();
            this.new_img3 = null;
        }
        if (this.new_img4 != null) {
            this.new_img4.flush();
            this.new_img4 = null;
        }
        if (this.img4 != null) {
            this.img4.flush();
            this.img4 = null;
        }
        if (this.img5 != null) {
            this.img5.flush();
            this.img5 = null;
        }
        this.free();
        this.applet.wclose();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        if (this.applet.bStandalone) {
            System.exit(0);
        }
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        if (this.nfirst == 1) {
            return;
        }
        this.d = this.getSize();
        this.apx = this.d.width;
        this.apy = this.d.height;
        this.apx1 = 0;
        this.apx4 = this.apx;
        this.apx2 = this.apx1 + (this.apx4 - 5) / 2;
        this.apx3 = this.apx4 - (this.apx4 - 5) / 2;
        this.apy1 = 0;
        if (this.nFull == 0) {
            this.apy4 = this.apy - (this.nFB + this.nFT + 24);
        }
        else {
            this.apy4 = this.apy - (this.nFB + this.nFT - 3);
        }
        this.apy2 = this.apy1 + (this.apy4 - 5) / 2;
        this.apy3 = this.apy4 - (this.apy4 - 5) / 2;
        if (this.nfit == 1) {
            this.FitSize();
        }
        if (this.sttype == 21) {
            this.Cancont.canresize();
        }
        else {
            if (this.nwhl == 1) {
                this.Cancontwh.canresize();
            }
            else {
                this.Cancontnw.canresize();
            }
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getAdjustable() == this.sr) {
            this.nfit = 0;
            this.zoompv = this.zoom;
            this.zoom = this.sr.getValue();
            this.lb.setText("" + this.zoom);
            if (this.nSmooth == 0) {
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
            }
            this.setstpix();
        }
        this.requestFocus();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.nSF == 2) {
            return;
        }
        switch (keyEvent.getKeyCode()) {
            case 16: {
                this.nshift = 1;
                break;
            }
            case 27: {
                this.wclose();
                break;
            }
            case 8: {
                if (this.stmax < 2) {
                    break;
                }
                --this.stimg;
                if (this.stimg < 0) {
                    this.stimg = this.stmax - 1;
                }
                this.readImag(this.stimg);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 32: {
                if (this.stmax < 2) {
                    break;
                }
                ++this.stimg;
                if (this.stimg > this.stmax - 1) {
                    this.stimg = 0;
                }
                this.readImag(this.stimg);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 82: {
                this.nSmooth = (this.nSmooth + 1) % 2;
                if (this.nSmooth == 0) {
                    this.bt13.setForeground(Color.black);
                }
                else {
                    this.bt13.setForeground(Color.blue);
                }
                this.bt13.setLabel(this.applet.getProperty("lg_fast", "Fast"));
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                if (this.sttype == 12 || this.sttype == 13 || this.sttype == 14 || this.sttype == 15) {
                    this.setstpixnew(1);
                    break;
                }
                if (this.nwhl == 1) {
                    this.Cancontwh.canrepaint(1);
                    break;
                }
                this.Cancontnw.canrepaint(1);
                break;
            }
            case 69: {
                this.nSpace = (this.nSpace + 1) % 3;
                if (this.nSpace == 0) {
                    this.bt14.setForeground(Color.black);
                }
                else if (this.nSpace == 1) {
                    this.bt14.setForeground(Color.blue);
                }
                else {
                    this.bt14.setForeground(Color.red);
                }
                this.bt14.setLabel(this.applet.getProperty("lg_SPC", "SPC"));
                if (this.nwhl == 1) {
                    this.Cancontwh.canrepaint(1);
                    break;
                }
                this.Cancontnw.canrepaint(1);
                break;
            }
            case 88: {
                this.swp = (this.swp + 1) % 2;
                if (this.swp == 0) {
                    this.bt5.setForeground(Color.black);
                }
                else {
                    this.bt5.setForeground(Color.blue);
                }
                this.bt5.setLabel(this.applet.getProperty("lg_swap", "Swap"));
                this.nSmoothimg = 1;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 33:
            case 114: {
                this.nfit = 0;
                this.zoompv = this.zoom;
                this.zoom += 4;
                if (this.zoom > 200) {
                    this.zoom = 200;
                }
                if (this.nSmooth == 0) {
                    this.nSmoothimg = 2;
                    this.nSmoothnew = 2;
                }
                this.setstpix();
                break;
            }
            case 34:
            case 113: {
                this.nfit = 0;
                this.zoompv = this.zoom;
                this.zoom -= 4;
                if (this.zoom < 1) {
                    this.zoom = 1;
                }
                if (this.nSmooth == 0) {
                    this.nSmoothimg = 2;
                    this.nSmoothnew = 2;
                }
                this.setstpix();
                break;
            }
            case 37: {
                this.dltx += 4;
                if (this.dltx < 0) {
                    this.dxl = -this.dltx;
                    this.dxr = 0;
                }
                else {
                    this.dxl = 0;
                    this.dxr = this.dltx;
                }
                this.setsift();
                break;
            }
            case 38: {
                this.dlty -= 4;
                if (this.dlty < 0) {
                    this.dyl = -this.dlty;
                    this.dyr = 0;
                }
                else {
                    this.dyl = 0;
                    this.dyr = this.dlty;
                }
                this.setsift();
                break;
            }
            case 39: {
                this.dltx -= 4;
                if (this.dltx < 0) {
                    this.dxl = -this.dltx;
                    this.dxr = 0;
                }
                else {
                    this.dxl = 0;
                    this.dxr = this.dltx;
                }
                this.setsift();
                break;
            }
            case 40: {
                this.dlty += 4;
                if (this.dlty < 0) {
                    this.dyl = -this.dlty;
                    this.dyr = 0;
                }
                else {
                    this.dyl = 0;
                    this.dyr = this.dlty;
                }
                this.setsift();
                break;
            }
            case 36: {
                this.dltx = 0;
                this.dlty = 0;
                this.dyl = 0;
                this.dyr = 0;
                this.dxl = 0;
                this.dxr = 0;
                this.nSmoothimg = 1;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 10: {
                this.setExtendedState((this.getExtendedState() == 0) ? 6 : 0);
                break;
            }
            case 65: {
                if (this.stmax < 2) {
                    break;
                }
                this.lb1.setText("" + this.nSS + "s");
                this.nSF = (this.nSF + 1) % 2;
                if (this.nSF == 0) {
                    this.bt0.setForeground(Color.black);
                }
                else {
                    this.bt0.setForeground(Color.blue);
                    this.nST = this.nSS;
                }
                this.bt0.setLabel(this.applet.getProperty("lg_SS", "SS"));
                this.showfileinfo();
                break;
            }
            case 83: {
                this.applet.filesave(this.stfile[this.stimg]);
                break;
            }
            case 70: {
                this.nfit = 1;
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpix();
                break;
            }
            case 72:
            case 112: {
                this.applet.showhelp();
                break;
            }
            case 71: {
                this.applet.showmypage();
                break;
            }
            case 74: {
                this.nfit = 0;
                this.zoompv = this.zoom;
                this.zoom = 100;
                this.nSmoothimg = 0;
                this.nSmoothnew = 0;
                this.setstpix();
                break;
            }
            case 77: {
                this.nFull = (this.nFull + 1) % 2;
                this.SetButton();
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.Cancont == null) {
                        this.Cancont = new canvascont(this);
                    }
                    this.Cancont.canvasstart();
                    break;
                }
                if (this.nwhl == 1) {
                    this.Cancontwh.canstop();
                }
                else {
                    this.Cancontnw.canstop();
                }
                if (this.nwhl == 1) {
                    this.Cancontwh.canstart();
                }
                else {
                    this.Cancontnw.canstart();
                }
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpix();
                break;
            }
            case 119: {
                if (this.gl4 == 1) {
                    break;
                }
                if (this.sttype == 21) {
                    break;
                }
                if (this.sttype != 21) {
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstop();
                    }
                    else {
                        this.Cancontnw.canstop();
                    }
                }
                this.sttype = 21;
                if (this.gl4 == 0) {
                    this.gl4 = 2;
                    try {
                        Class.forName("javax.media.j3d.Canvas3D");
                    }
                    catch (Exception ex) {
                        this.c.removeAll();
                        for (int i = 0; i < 21; ++i) {
                            this.c.addItem(this.strfil[i]);
                        }
                        this.gl4 = 1;
                        this.sttype = 1;
                    }
                }
                this.c.select(this.sttype);
                if (this.sttype == 1) {
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                    this.nSmoothimg = 2;
                    this.nSmoothnew = 2;
                    this.setstpixnew(1);
                    break;
                }
                if (this.Cancont == null) {
                    this.Cancont = new canvascont(this);
                }
                this.Cancont.canvasstart();
                break;
            }
            case 120: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    if (this.sttype == 1) {
                        this.sttype = 0;
                    }
                    else {
                        this.sttype = 1;
                    }
                }
                else {
                    this.sttype = 2;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 117: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    this.sttype = 3;
                }
                else {
                    this.sttype = 4;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 118: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    this.sttype = 6;
                }
                else {
                    this.sttype = 7;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 116: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    this.sttype = 20;
                }
                else {
                    this.sttype = 5;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 115: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    this.sttype = 12;
                }
                else {
                    this.sttype = 14;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
            case 123: {
                if (this.sttype == 21) {
                    this.Cancont.canvasstop();
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                }
                if (this.nshift == 0) {
                    this.sttype = 7;
                }
                else {
                    this.sttype = 7;
                }
                this.c.select(this.sttype);
                this.nSmoothimg = 2;
                this.nSmoothnew = 2;
                this.setstpixnew(1);
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 16: {
                this.nshift = 0;
                break;
            }
        }
    }
    
    public void showfileinfo() {
        final String string = " (" + this.ver + ") (" + this.applet.getProperty("lg_zoom", "Zoom") + " = " + this.zoom + ") (" + this.applet.getProperty("lg_align", "Align") + " x = " + this.dltx + " y = " + this.dlty + ") (" + this.applet.getProperty("lg_swap", "swp") + " = " + this.swp + ")";
        final String string2 = " " + this.applet.getProperty("lg_SS1", "(Slide Show ON") + " " + this.nST + "s)";
        final String string3 = " " + this.applet.getProperty("lg_SS0", "(Slide Show OFF)");
        final String string4 = " " + this.applet.getProperty("lg_FastMode", "(Fast mode)");
        String string5;
        if (this.nSmooth == 1) {
            string5 = string + string4;
        }
        else {
            string5 = string;
        }
        String s;
        if (this.stmax < 2) {
            s = string5;
        }
        else if (this.nSF == 0) {
            s = string5 + string3;
        }
        else {
            s = string5 + string2;
        }
        if (this.nlg == 1) {
            this.setTitle(this.wmsg2);
        }
        else if (this.stmess[this.stimg] == null) {
            this.setTitle(this.stname[this.stimg] + s);
        }
        else {
            this.setTitle(this.stmess[this.stimg] + s);
        }
    }
    
    void setsift() {
        this.nSmoothimg = 1;
        this.nSmoothnew = 2;
        this.setstpixnew(1);
    }
    
    void setPix1() {
        try {
            new PixelGrabber(this.img, 0, 0, this.w, this.h, this.pix, 0, this.w).grabPixels();
        }
        catch (Exception ex) {}
        this.mimg3 = null;
        (this.mimg3 = new MemoryImageSource(this.wm, this.hm, this.new_pix3, 0, this.wm)).setAnimated(true);
        this.new_img3 = this.createImage(this.mimg3);
        this.mimg4 = null;
        (this.mimg4 = new MemoryImageSource(this.w, this.h, this.new_pix4, 0, this.w)).setAnimated(true);
        this.new_img4 = this.createImage(this.mimg4);
    }
    
    void getDispSize(final int n) {
        int n2;
        if (this.dxl > this.dxr) {
            n2 = this.dxl;
        }
        else {
            n2 = this.dxr;
        }
        int n3;
        if (this.dyl > this.dyr) {
            n3 = this.dyl;
        }
        else {
            n3 = this.dyr;
        }
        this.dispx = (this.w / 2 - n2) * n / 100;
        this.dispy = (this.h - n3) * n / 100;
    }
    
    void setPix2(final int n) {
        this.getDispSize(100);
        for (int i = 0; i < this.dispy; ++i) {
            for (int j = 0; j < this.dispx; ++j) {
                final int n2 = this.pix[(i + this.dyl) * this.w + j + this.dxl];
                final int n3 = ((n2 >> 16 & 0xFF) * 299 + (n2 >> 8 & 0xFF) * 587 + (n2 >> 0 & 0xFF) * 114) / 1000;
                final int n4 = this.pix[(i + this.dyr) * this.w + this.w / 2 + j + this.dxr];
                final int n5 = ((n4 >> 16 & 0xFF) * 299 + (n4 >> 8 & 0xFF) * 587 + (n4 >> 0 & 0xFF) * 114) / 1000;
                int n6;
                if (n == 0) {
                    if (this.swp == 0) {
                        n6 = (0xFF000000 | n3 << 16 | n5 << 8 | n5 << 0);
                    }
                    else {
                        n6 = (0xFF000000 | n5 << 16 | n3 << 8 | n3 << 0);
                    }
                }
                else if (n == 2) {
                    if (this.swp == 0) {
                        n6 = (0xFF000000 | n3 << 16 | n5 << 8);
                    }
                    else {
                        n6 = (0xFF000000 | n5 << 16 | n3 << 8);
                    }
                }
                else if (n == 1) {
                    if (this.swp == 0) {
                        n6 = (0xFF000000 | n3 << 16 | n5 << 0);
                    }
                    else {
                        n6 = (0xFF000000 | n5 << 16 | n3 << 0);
                    }
                }
                else if (this.swp == 0) {
                    n6 = (0xFF000000 | n3 << 16 | n3 << 8 | n5 << 0);
                }
                else {
                    n6 = (0xFF000000 | n5 << 16 | n5 << 8 | n3 << 0);
                }
                this.new_pix3[i * this.wm + j] = n6;
            }
        }
        this.mimg3.newPixels(0, 0, this.dispx, this.dispy);
        this.getDispSize(this.zoom);
    }
    
    void setPix3(final int n) {
        int n2 = 0;
        this.getDispSize(100);
        for (int i = 0; i < this.dispy; ++i) {
            for (int j = 0; j < this.dispx; ++j) {
                final int n3 = this.pix[(i + this.dyl) * this.w + j + this.dxl];
                final int n4 = this.pix[(i + this.dyr) * this.w + this.w / 2 + j + this.dxr];
                int n5;
                int n6;
                int n7;
                int n8;
                int n9;
                int n10;
                if (this.swp == 0) {
                    n5 = (n3 >> 16 & 0xFF);
                    n6 = (n3 >> 8 & 0xFF);
                    n7 = (n3 >> 0 & 0xFF);
                    n8 = (n4 >> 16 & 0xFF);
                    n9 = (n4 >> 8 & 0xFF);
                    n10 = (n4 >> 0 & 0xFF);
                }
                else {
                    n8 = (n3 >> 16 & 0xFF);
                    n9 = (n3 >> 8 & 0xFF);
                    n10 = (n3 >> 0 & 0xFF);
                    n5 = (n4 >> 16 & 0xFF);
                    n6 = (n4 >> 8 & 0xFF);
                    n7 = (n4 >> 0 & 0xFF);
                }
                switch (n) {
                    case 0: {
                        n2 = (0xFF000000 | n5 << 16 | n9 << 8 | n10 << 0);
                        break;
                    }
                    case 1: {
                        n2 = (0xFF000000 | (n5 * 299 + n6 * 587 + n7 * 114) / 1000 << 16 | n9 << 8 | n10 << 0);
                        break;
                    }
                    case 2: {
                        final double[][] array = (n == 2) ? fej3dfrm.Dubois : fej3dfrm.DuboisYB;
                        double n11 = array[0][0] * n5 + array[0][1] * n6 + array[0][2] * n7 + array[0][3] * n8 + array[0][4] * n9 + array[0][5] * n10;
                        double n12 = array[1][0] * n5 + array[1][1] * n6 + array[1][2] * n7 + array[1][3] * n8 + array[1][4] * n9 + array[1][5] * n10;
                        double n13 = array[2][0] * n5 + array[2][1] * n6 + array[2][2] * n7 + array[2][3] * n8 + array[2][4] * n9 + array[2][5] * n10;
                        if (n11 > 255.0) {
                            n11 = 255.0;
                        }
                        else if (n11 < 0.0) {
                            n11 = 0.0;
                        }
                        if (n12 > 255.0) {
                            n12 = 255.0;
                        }
                        else if (n12 < 0.0) {
                            n12 = 0.0;
                        }
                        if (n13 > 255.0) {
                            n13 = 255.0;
                        }
                        else if (n13 < 0.0) {
                            n13 = 0.0;
                        }
                        n2 = (0xFF000000 | (int)n11 << 16 | (int)n12 << 8 | (int)n13 << 0);
                        break;
                    }
                    case 3: {
                        n2 = (0xFF000000 | n5 << 16 | n6 << 8 | n10 << 0);
                        break;
                    }
                    case 4: {
                        n2 = (0xFF000000 | n5 << 16 | n6 << 8 | (n8 * 299 + n9 * 587 + n10 * 114) / 1000 << 0);
                        break;
                    }
                }
                this.new_pix3[i * this.wm + j] = n2;
            }
        }
        this.mimg3.newPixels(0, 0, this.dispx, this.dispy);
        this.getDispSize(this.zoom);
    }
    
    void setZoomPix(final int n, final int n2) {
        for (int i = 0; i < this.h; ++i) {
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            for (int j = 0; j < this.w; ++j) {
                final int n8 = this.pix[i * this.w + j];
                n3 += (n8 >> 16 & 0xFF);
                n4 += (n8 >> 8 & 0xFF);
                n5 += (n8 >> 0 & 0xFF);
                ++n6;
                if (this.w * (n7 + 1) / n <= j + 1) {
                    this.new_pix4[i * this.w + n7] = (0xFF000000 | n3 / n6 << 16 | n4 / n6 << 8 | n5 / n6 << 0);
                    n3 = 0;
                    n4 = 0;
                    n5 = 0;
                    n6 = 0;
                    ++n7;
                }
            }
        }
        for (int k = 0; k < n; ++k) {
            int n9 = 0;
            int n10 = 0;
            int n11 = 0;
            int n12 = 0;
            int n13 = 0;
            for (int l = 0; l < this.h; ++l) {
                final int n14 = this.new_pix4[l * this.w + k];
                n9 += (n14 >> 16 & 0xFF);
                n10 += (n14 >> 8 & 0xFF);
                n11 += (n14 >> 0 & 0xFF);
                ++n12;
                if (this.h * (n13 + 1) / n2 <= l + 1) {
                    this.new_pix4[n13 * this.w + k] = (0xFF000000 | n9 / n12 << 16 | n10 / n12 << 8 | n11 / n12 << 0);
                    n9 = 0;
                    n10 = 0;
                    n11 = 0;
                    n12 = 0;
                    ++n13;
                }
            }
        }
        this.mimg4.newPixels(0, 0, n, n2);
    }
    
    void setZoomPix2(final int n, final int n2) {
        this.getDispSize(100);
        for (int i = 0; i < this.dispy; ++i) {
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            for (int j = 0; j < this.dispx; ++j) {
                final int n8 = this.new_pix3[i * this.wm + j];
                n3 += (n8 >> 16 & 0xFF);
                n4 += (n8 >> 8 & 0xFF);
                n5 += (n8 >> 0 & 0xFF);
                ++n6;
                if (this.dispx * (n7 + 1) / n <= j + 1) {
                    this.new_pix4[i * this.w + n7] = (0xFF000000 | n3 / n6 << 16 | n4 / n6 << 8 | n5 / n6 << 0);
                    n3 = 0;
                    n4 = 0;
                    n5 = 0;
                    n6 = 0;
                    ++n7;
                }
            }
        }
        for (int k = 0; k < n; ++k) {
            int n9 = 0;
            int n10 = 0;
            int n11 = 0;
            int n12 = 0;
            int n13 = 0;
            for (int l = 0; l < this.dispy; ++l) {
                final int n14 = this.new_pix4[l * this.w + k];
                n9 += (n14 >> 16 & 0xFF);
                n10 += (n14 >> 8 & 0xFF);
                n11 += (n14 >> 0 & 0xFF);
                ++n12;
                if (this.dispy * (n13 + 1) / n2 <= l + 1) {
                    this.new_pix4[n13 * this.w + k] = (0xFF000000 | n9 / n12 << 16 | n10 / n12 << 8 | n11 / n12 << 0);
                    n9 = 0;
                    n10 = 0;
                    n11 = 0;
                    n12 = 0;
                    ++n13;
                }
            }
        }
        this.mimg4.newPixels(0, 0, n, n2);
        this.getDispSize(this.zoom);
    }
    
    void setPix4(final int pix42) {
        if (this.zoom > this.zmax) {
            this.zoom = this.zmax;
        }
        if (this.nSmooth == 0 && (this.zoom <= 100 || pix42 == 3)) {
            this.setPix42(pix42);
            return;
        }
        if (this.nSmooth == 0 && this.zoom > 100 && this.zoom <= 200 && pix42 == 0) {
            this.setPix43();
            return;
        }
        this.getDispSize(this.zoom);
        if (pix42 == 0) {
            for (int i = 0; i < this.dispy; ++i) {
                for (int j = 0; j < this.dispx; ++j) {
                    if (i % 2 == this.swp) {
                        this.new_pix3[i * this.wm + j] = this.pix[(i * 100 / this.zoom + this.dyr) * this.w + this.w / 2 + j * 100 / this.zoom + this.dxr];
                    }
                    else {
                        this.new_pix3[i * this.wm + j] = this.pix[(i * 100 / this.zoom + this.dyl) * this.w + j * 100 / this.zoom + this.dxl];
                    }
                }
            }
        }
        else if (pix42 == 1) {
            for (int k = 0; k < this.dispy; ++k) {
                for (int l = 0; l < this.dispx; ++l) {
                    final int n = this.pix[(k * 100 / this.zoom + this.dyr) * this.w + this.w / 2 + l * 100 / this.zoom + this.dxr];
                    final int n2 = this.pix[(k * 100 / this.zoom + this.dyl) * this.w + l * 100 / this.zoom + this.dxl];
                    int n3;
                    int n4;
                    int n5;
                    if (l % 2 == this.swp) {
                        n3 = (n >> 16 & 0xFF);
                        n4 = (n2 >> 8 & 0xFF);
                        n5 = (n >> 0 & 0xFF);
                    }
                    else {
                        n3 = (n2 >> 16 & 0xFF);
                        n4 = (n >> 8 & 0xFF);
                        n5 = (n2 >> 0 & 0xFF);
                    }
                    this.new_pix3[k * this.wm + l] = (0xFF000000 | n3 << 16 | n4 << 8 | n5 << 0);
                }
            }
        }
        else if (pix42 == 2) {
            for (int n6 = 0; n6 < this.dispy; ++n6) {
                for (int n7 = 0; n7 < this.dispx; ++n7) {
                    if (n7 % 2 == this.swp) {
                        this.new_pix3[n6 * this.wm + n7] = this.pix[(n6 * 100 / this.zoom + this.dyr) * this.w + this.w / 2 + n7 * 100 / this.zoom + this.dxr];
                    }
                    else {
                        this.new_pix3[n6 * this.wm + n7] = this.pix[(n6 * 100 / this.zoom + this.dyl) * this.w + n7 * 100 / this.zoom + this.dxl];
                    }
                }
            }
        }
        else {
            for (int n8 = 0; n8 < this.dispy; ++n8) {
                for (int n9 = 0; n9 < this.dispx; ++n9) {
                    if ((n8 % 2 + n9 % 2) % 2 == this.swp) {
                        this.new_pix3[n8 * this.wm + n9] = this.pix[(n8 * 100 / this.zoom + this.dyr) * this.w + this.w / 2 + n9 * 100 / this.zoom + this.dxr];
                    }
                    else {
                        this.new_pix3[n8 * this.wm + n9] = this.pix[(n8 * 100 / this.zoom + this.dyl) * this.w + n9 * 100 / this.zoom + this.dxl];
                    }
                }
            }
        }
        this.mimg3.newPixels(0, 0, this.dispx, this.dispy);
    }
    
    void setPix43() {
        this.setZoomPix(this.w, this.h * this.zoom / 200);
        this.getDispSize(this.zoom);
        final int n = this.dyr * this.zoom / 100;
        final int n2 = this.dyl * this.zoom / 100;
        for (int i = 0; i < this.dispy; ++i) {
            for (int j = 0; j < this.dispx; ++j) {
                if (i % 2 == this.swp) {
                    this.new_pix3[i * this.wm + j] = this.new_pix4[(i + n) / 2 * this.w + this.w / 2 + j * 100 / this.zoom + this.dxr];
                }
                else {
                    this.new_pix3[i * this.wm + j] = this.new_pix4[(i + n2) / 2 * this.w + j * 100 / this.zoom + this.dxl];
                }
            }
        }
        this.mimg3.newPixels(0, 0, this.dispx, this.dispy);
    }
    
    void setPix42(final int n) {
        if (n == 0) {
            this.setZoomPix(this.w * this.zoom / 100, this.h * this.zoom / 200);
        }
        else if (n == 2) {
            this.setZoomPix(this.w * this.zoom / 200, this.h * this.zoom / 100);
        }
        else if (n == 3) {
            this.setZoomPix(this.w * this.zoom / 200, this.h * this.zoom / 200);
        }
        else {
            this.setZoomPix(this.w * this.zoom / 100, this.h * this.zoom / 100);
        }
        this.getDispSize(this.zoom);
        final int n2 = this.dxr * this.zoom / 100;
        final int n3 = this.dyr * this.zoom / 100;
        final int n4 = this.dxl * this.zoom / 100;
        final int n5 = this.dyl * this.zoom / 100;
        if (n == 0) {
            for (int i = 0; i < this.dispy; ++i) {
                for (int j = 0; j < this.dispx; ++j) {
                    if (i % 2 == this.swp) {
                        this.new_pix3[i * this.wm + j] = this.new_pix4[(i + n3) / 2 * this.w + this.w * this.zoom / 200 + j + n2];
                    }
                    else {
                        this.new_pix3[i * this.wm + j] = this.new_pix4[(i + n5) / 2 * this.w + j + n4];
                    }
                }
            }
        }
        else if (n == 1) {
            final int[] array = new int[this.dispx];
            for (int k = 0; k < this.dispy; ++k) {
                for (int l = 0; l < this.dispx; ++l) {
                    if (l % 2 == this.swp) {
                        array[l] = this.new_pix4[(k + n3) * this.w + this.w * this.zoom / 200 + l + n2];
                    }
                    else {
                        array[l] = this.new_pix4[(k + n5) * this.w + l + n4];
                    }
                }
                int n6 = 0;
                for (int n7 = 0; n7 < this.dispx; ++n7) {
                    this.new_pix3[k * this.wm + n7] = (0xFF000000 | (array[n7] >> 16 & 0xFF) << 16 | n6 << 8 | (array[n7] >> 0 & 0xFF) << 0);
                    n6 = (array[n7] >> 8 & 0xFF);
                }
            }
        }
        else if (n == 2) {
            for (int n8 = 0; n8 < this.dispy; ++n8) {
                for (int n9 = 0; n9 < this.dispx; ++n9) {
                    if (n9 % 2 == this.swp) {
                        this.new_pix3[n8 * this.wm + n9] = this.new_pix4[(n8 + n3) * this.w + this.w * this.zoom / 400 + (n9 + n2) / 2];
                    }
                    else {
                        this.new_pix3[n8 * this.wm + n9] = this.new_pix4[(n8 + n3) * this.w + (n9 + n4) / 2];
                    }
                }
            }
        }
        else {
            for (int n10 = 0; n10 < this.dispy; ++n10) {
                for (int n11 = 0; n11 < this.dispx; ++n11) {
                    if ((n10 % 2 + n11 % 2) % 2 == this.swp) {
                        this.new_pix3[n10 * this.wm + n11] = this.new_pix4[(n10 + n3) / 2 * this.w + this.w * this.zoom / 400 + (n11 + n2) / 2];
                    }
                    else {
                        this.new_pix3[n10 * this.wm + n11] = this.new_pix4[(n10 + n5) / 2 * this.w + (n11 + n4) / 2];
                    }
                }
            }
        }
        this.mimg3.newPixels(0, 0, this.dispx, this.dispy);
    }
    
    void setPix5() {
        for (int i = 0; i < this.hm; ++i) {
            for (int j = 0; j < this.wm; ++j) {
                this.new_pix3[i * this.wm + j] = this.pix[0];
            }
        }
        this.mimg3.newPixels(0, 0, this.wm, this.hm);
    }
    
    void setZoom() {
        int n = 0;
        int n2 = 0;
        this.centx = 0;
        this.centy = 0;
        this.getDispSize(this.zoom);
        int n3;
        if (this.sttype == 0 || this.sttype == 1 || this.sttype == 16 || this.sttype == 17 || this.sttype == 18) {
            n3 = this.apx2 - this.apx1;
        }
        else {
            n3 = this.apx4 - this.apx1;
        }
        int n4;
        if (this.sttype == 2 || this.sttype == 19) {
            n4 = this.apy2 - this.apy1;
        }
        else {
            n4 = this.apy4 - this.apy1;
        }
        if (n3 > this.dispx) {
            this.sx = 0;
            this.centx = (n3 - this.dispx) / 2;
        }
        else {
            if (this.zoompv > 0) {
                n2 = n3 * 100 / this.zoompv;
            }
            if (this.zoom > 0) {
                n = n3 * 100 / this.zoom;
            }
            if (this.zoompv > 0 && this.zoom > 0) {
                this.sx += (n2 - n) / 2;
            }
            this.setTitle("prev=" + n2 + "   new=" + n + "   sxpv=" + (this.sx - (n2 - n) / 2) + "   sx=" + this.sx + "   zoomprev=" + this.zoompv + "   zoom=" + this.zoom);
        }
        if (n4 > this.dispy) {
            this.sy = 0;
            this.centy = (n4 - this.dispy) / 2;
        }
        else {
            if (this.zoompv > 0) {
                n2 = n4 * 100 / this.zoompv;
            }
            if (this.zoom > 0) {
                n = n4 * 100 / this.zoom;
            }
            if (this.zoompv > 0 && this.zoom > 0) {
                this.sy += (n2 - n) / 2;
            }
        }
        this.checkscroll();
    }
    
    void setstpixnew(final int n) {
        if (this.nfit == 1) {
            this.FitSize();
        }
        this.sr.setValue(this.zoom);
        if (this.nfit == 0) {
            this.lb.setText("" + this.zoom);
        }
        else {
            this.lb.setText("" + this.zoom + "F");
        }
        if (this.sttype == 21) {
            this.Cancont.canvasrepaint();
            return;
        }
        if (this.sttype == 3) {
            this.setPix2(0);
        }
        if (this.sttype == 4) {
            this.setPix2(1);
        }
        if (this.sttype == 5) {
            this.setPix2(2);
        }
        if (this.sttype == 6) {
            this.setPix3(0);
        }
        if (this.sttype == 7) {
            this.setPix3(2);
        }
        if (this.sttype == 8) {
            this.setPix3(1);
        }
        if (this.sttype == 9) {
            this.setPix2(3);
        }
        if (this.sttype == 10) {
            this.setPix3(3);
        }
        if (this.sttype == 11) {
            this.setPix3(4);
        }
        if (this.sttype == 12) {
            this.setPix4(0);
        }
        if (this.sttype == 13) {
            this.setPix4(2);
        }
        if (this.sttype == 14) {
            this.setPix4(1);
        }
        if (this.sttype == 15) {
            this.setPix4(3);
        }
        this.setZoom();
        if (this.nwhl == 1) {
            this.Cancontwh.canrepaint(n);
        }
        else {
            this.Cancontnw.canrepaint(n);
        }
    }
    
    void setstpix() {
        if (this.nfit == 1) {
            this.FitSize();
        }
        this.sr.setValue(this.zoom);
        if (this.nfit == 0) {
            this.lb.setText("" + this.zoom);
        }
        else {
            this.lb.setText("" + this.zoom + "F");
        }
        if (this.sttype == 21) {
            this.Cancont.canvasrepaint();
            return;
        }
        if (this.sttype == 12) {
            this.setPix4(0);
        }
        if (this.sttype == 13) {
            this.setPix4(2);
        }
        if (this.sttype == 14) {
            this.setPix4(1);
        }
        if (this.sttype == 15) {
            this.setPix4(3);
        }
        this.setZoom();
        if (this.nwhl == 1) {
            this.Cancontwh.canrepaint(1);
        }
        else {
            this.Cancontnw.canrepaint(1);
        }
    }
    
    void FitSize() {
        this.sx = 0;
        this.sy = 0;
        int n;
        if (this.sttype == 0 || this.sttype == 1 || this.sttype == 16 || this.sttype == 17 || this.sttype == 18) {
            n = this.apx2 - this.apx1;
        }
        else {
            n = this.apx4 - this.apx1;
        }
        int n2;
        if (this.sttype == 2 || this.sttype == 19) {
            n2 = this.apy2 - this.apy1;
        }
        else {
            n2 = this.apy4 - this.apy1;
        }
        final int zoom = n * 200 / this.w;
        final int zoom2 = n2 * 100 / this.h;
        if (zoom < zoom2) {
            this.zoom = zoom;
        }
        else {
            this.zoom = zoom2;
        }
        if (this.zoom > this.zmax) {
            this.zoom = this.zmax;
        }
        this.sr.setValue(this.zoom);
        this.lb.setText("" + this.zoom + "F");
    }
    
    void checkscroll() {
        int n;
        if (this.dxl > this.dxr) {
            n = this.dxl;
        }
        else {
            n = this.dxr;
        }
        int n2;
        if (this.dyl > this.dyr) {
            n2 = this.dyl;
        }
        else {
            n2 = this.dyr;
        }
        if (this.sttype == 0 || this.sttype == 1 || this.sttype == 16 || this.sttype == 17 || this.sttype == 18) {
            if (this.sx > this.w / 2 - n - (this.apx2 - this.apx1) * 100 / this.zoom) {
                this.sx = this.w / 2 - n - (this.apx2 - this.apx1) * 100 / this.zoom;
            }
            if (this.apx2 - this.apx1 > this.dispx || this.sx < 0) {
                this.sx = 0;
            }
            if (this.sy > this.h - n2 - (this.apy4 - this.apy1) * 100 / this.zoom) {
                this.sy = this.h - n2 - (this.apy4 - this.apy1) * 100 / this.zoom;
            }
            if (this.apy4 - this.apy1 > this.dispy || this.sy < 0) {
                this.sy = 0;
            }
        }
        else if (this.sttype == 2 || this.sttype == 19) {
            if (this.sx > this.w / 2 - n - (this.apx4 - this.apx1) * 100 / this.zoom) {
                this.sx = this.w / 2 - n - (this.apx4 - this.apx1) * 100 / this.zoom;
            }
            if (this.apx4 - this.apx1 > this.dispx || this.sx < 0) {
                this.sx = 0;
            }
            if (this.sy > this.h - n2 - (this.apy2 - this.apy1) * 100 / this.zoom) {
                this.sy = this.h - n2 - (this.apy2 - this.apy1) * 100 / this.zoom;
            }
            if (this.apy2 - this.apy1 > this.dispy || this.sy < 0) {
                this.sy = 0;
            }
        }
        else if (this.sttype == 12 || this.sttype == 13 || this.sttype == 14 || this.sttype == 15) {
            if (this.sx > this.dispx - (this.apx4 - this.apx1)) {
                this.sx = this.dispx - (this.apx4 - this.apx1);
            }
            if (this.apx4 - this.apx1 > this.dispx || this.sx < 0) {
                this.sx = 0;
            }
            if (this.sy > this.dispy - (this.apy4 - this.apy1)) {
                this.sy = this.dispy - (this.apy4 - this.apy1);
            }
            if (this.apy4 - this.apy1 > this.dispy || this.sy < 0) {
                this.sy = 0;
            }
        }
        else {
            if (this.sx > this.w / 2 - n - (this.apx4 - this.apx1) * 100 / this.zoom) {
                this.sx = this.w / 2 - n - (this.apx4 - this.apx1) * 100 / this.zoom;
            }
            if (this.apx4 - this.apx1 > this.dispx || this.sx < 0) {
                this.sx = 0;
            }
            if (this.sy > this.h - n2 - (this.apy4 - this.apy1) * 100 / this.zoom) {
                this.sy = this.h - n2 - (this.apy4 - this.apy1) * 100 / this.zoom;
            }
            if (this.apy4 - this.apy1 > this.dispy || this.sy < 0) {
                this.sy = 0;
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (this.nfirst == 1) {
            return;
        }
        if (this.nSF == 2) {
            return;
        }
        if (itemEvent.getItemSelectable() == this.c) {
            final int sttype = this.sttype;
            this.sttype = 0;
            for (int i = 0; i < 22; ++i) {
                if (this.strfil[i].equals(itemEvent.getItem())) {
                    this.sttype = i;
                }
            }
            if (this.sttype == sttype) {
                return;
            }
            if (sttype == 21) {
                this.Cancont.canvasstop();
                if (this.nwhl == 1) {
                    this.Cancontwh.canstart();
                }
                else {
                    this.Cancontnw.canstart();
                }
            }
            if (this.sttype != 21) {
                this.nSmoothimg = 2;
                this.setstpixnew(this.nSmoothnew = 2);
            }
            else {
                if (this.nwhl == 1) {
                    this.Cancontwh.canstop();
                }
                else {
                    this.Cancontnw.canstop();
                }
                if (this.gl4 != 2) {
                    this.gl4 = 2;
                    try {
                        Class.forName("javax.media.j3d.Canvas3D");
                    }
                    catch (Exception ex) {
                        this.c.removeAll();
                        for (int j = 0; j < 21; ++j) {
                            this.c.addItem(this.strfil[j]);
                        }
                        this.gl4 = 1;
                        this.sttype = 1;
                        this.c.select(this.sttype);
                    }
                }
                if (this.sttype == 1) {
                    if (this.nwhl == 1) {
                        this.Cancontwh.canstart();
                    }
                    else {
                        this.Cancontnw.canstart();
                    }
                    this.nSmoothimg = 2;
                    this.setstpixnew(this.nSmoothnew = 2);
                }
                else {
                    if (this.Cancont == null) {
                        this.Cancont = new canvascont(this);
                    }
                    this.Cancont.canvasstart();
                }
            }
        }
        if (itemEvent.getItemSelectable() == this.c1) {
            int stimg = -1;
            for (int k = 0; k < this.stmax; ++k) {
                if (this.stname[k].equals(itemEvent.getItem())) {
                    stimg = k;
                }
            }
            if (stimg < 0) {
                return;
            }
            if (this.stimg == stimg) {
                return;
            }
            this.readImag(this.stimg = stimg);
            this.nSmoothimg = 2;
            this.setstpixnew(this.nSmoothnew = 2);
        }
        this.requestFocus();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.nSF == 2) {
            return;
        }
        if (actionEvent.getSource() == this.bt0) {
            this.lb1.setText("" + this.nSS + "s");
            this.nSF = (this.nSF + 1) % 2;
            if (this.nSF == 0) {
                this.bt0.setForeground(Color.black);
            }
            else {
                this.bt0.setForeground(Color.blue);
                this.nST = this.nSS;
            }
            this.bt0.setLabel(this.applet.getProperty("lg_SS", "SS"));
            this.showfileinfo();
        }
        if (actionEvent.getSource() == this.bt3) {
            --this.nSS;
            if (this.nSS < 1) {
                this.nSS = 1;
            }
            this.lb1.setText("" + this.nSS + "s");
        }
        if (actionEvent.getSource() == this.bt4) {
            ++this.nSS;
            if (this.nSS > 99) {
                this.nSS = 99;
            }
            this.lb1.setText("" + this.nSS + "s");
        }
        if (actionEvent.getSource() == this.bt6) {
            this.nfit = 0;
            this.zoompv = this.zoom;
            this.zoom = 100;
            this.nSmoothimg = 0;
            this.nSmoothnew = 0;
            this.setstpix();
        }
        if (actionEvent.getSource() == this.bt7) {
            this.nfit = 1;
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpix();
        }
        if (actionEvent.getSource() == this.bt1) {
            --this.stimg;
            if (this.stimg < 0) {
                this.stimg = this.stmax - 1;
            }
            this.readImag(this.stimg);
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
        }
        if (actionEvent.getSource() == this.bt2) {
            ++this.stimg;
            if (this.stimg > this.stmax - 1) {
                this.stimg = 0;
            }
            this.readImag(this.stimg);
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
        }
        if (actionEvent.getSource() == this.bt5) {
            this.swp = (this.swp + 1) % 2;
            if (this.swp == 0) {
                this.bt5.setForeground(Color.black);
            }
            else {
                this.bt5.setForeground(Color.blue);
            }
            this.bt5.setLabel(this.applet.getProperty("lg_swap", "Swap"));
            this.nSmoothimg = 1;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
        }
        if (actionEvent.getSource() == this.bt13) {
            this.nSmooth = (this.nSmooth + 1) % 2;
            if (this.nSmooth == 0) {
                this.bt13.setForeground(Color.black);
            }
            else {
                this.bt13.setForeground(Color.blue);
            }
            this.bt13.setLabel(this.applet.getProperty("lg_fast", "Fast"));
            this.nSmoothimg = 2;
            this.nSmoothnew = 2;
            if (this.sttype == 12 || this.sttype == 13 || this.sttype == 14 || this.sttype == 15) {
                this.setstpixnew(1);
            }
            else if (this.nwhl == 1) {
                this.Cancontwh.canrepaint(1);
            }
            else {
                this.Cancontnw.canrepaint(1);
            }
        }
        if (actionEvent.getSource() == this.bt14) {
            this.nSpace = (this.nSpace + 1) % 3;
            if (this.nSpace == 0) {
                this.bt14.setForeground(Color.black);
            }
            else if (this.nSpace == 1) {
                this.bt14.setForeground(Color.blue);
            }
            else {
                this.bt14.setForeground(Color.red);
            }
            this.bt14.setLabel(this.applet.getProperty("lg_SPC", "SPC"));
            if (this.nwhl == 1) {
                this.Cancontwh.canrepaint(1);
            }
            else {
                this.Cancontnw.canrepaint(1);
            }
        }
        if (actionEvent.getSource() == this.bt8) {
            this.dltx += 4;
            if (this.dltx < 0) {
                this.dxl = -this.dltx;
                this.dxr = 0;
            }
            else {
                this.dxl = 0;
                this.dxr = this.dltx;
            }
            this.setsift();
        }
        if (actionEvent.getSource() == this.bt9) {
            this.dltx -= 4;
            if (this.dltx < 0) {
                this.dxl = -this.dltx;
                this.dxr = 0;
            }
            else {
                this.dxl = 0;
                this.dxr = this.dltx;
            }
            this.setsift();
        }
        if (actionEvent.getSource() == this.bt10) {
            this.dlty -= 4;
            if (this.dlty < 0) {
                this.dyl = -this.dlty;
                this.dyr = 0;
            }
            else {
                this.dyl = 0;
                this.dyr = this.dlty;
            }
            this.setsift();
        }
        if (actionEvent.getSource() == this.bt11) {
            this.dlty += 4;
            if (this.dlty < 0) {
                this.dyl = -this.dlty;
                this.dyr = 0;
            }
            else {
                this.dyl = 0;
                this.dyr = this.dlty;
            }
            this.setsift();
        }
        if (actionEvent.getSource() == this.bt12) {
            this.dltx = 0;
            this.dlty = 0;
            this.dyl = 0;
            this.dyr = 0;
            this.dxl = 0;
            this.dxr = 0;
            this.nSmoothimg = 1;
            this.nSmoothnew = 2;
            this.setstpixnew(1);
        }
        this.requestFocus();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dx = mouseEvent.getX();
        this.dy = mouseEvent.getY();
        this.requestFocus();
        if (mouseEvent.getClickCount() >= 2) {
            this.setExtendedState((this.getExtendedState() == 0) ? 6 : 0);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.sttype == 21) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.sttype == 0 || this.sttype == 1 || this.sttype == 16 || this.sttype == 17 || this.sttype == 18) {
            if (this.apx2 - this.apx1 > this.dispx - 2 && this.apy4 - this.apy1 > this.dispy - 2) {
                return;
            }
            if (this.apx2 - this.apx1 < this.dispx) {
                this.sx += (this.dx - x) * 100 / this.zoom;
            }
            if (this.apy4 - this.apy1 < this.dispy) {
                this.sy += (this.dy - y) * 100 / this.zoom;
            }
        }
        else if (this.sttype == 2 || this.sttype == 19) {
            if (this.apx4 - this.apx1 > this.dispx - 2 && this.apy2 - this.apy1 > this.dispy - 2) {
                return;
            }
            if (this.apx4 - this.apx1 < this.dispx) {
                this.sx += (this.dx - x) * 100 / this.zoom;
            }
            if (this.apy2 - this.apy1 < this.dispy) {
                this.sy += (this.dy - y) * 100 / this.zoom;
            }
        }
        else if (this.sttype == 12 || this.sttype == 13 || this.sttype == 14 || this.sttype == 15) {
            if (this.apx4 - this.apx1 > this.dispx - 2 && this.apy4 - this.apy1 > this.dispy - 2) {
                return;
            }
            if (this.apx4 - this.apx1 < this.dispx) {
                this.sx += this.dx - x;
            }
            if (this.apy4 - this.apy1 < this.dispy) {
                this.sy += this.dy - y;
            }
        }
        else {
            if (this.apx4 - this.apx1 > this.dispx - 2 && this.apy4 - this.apy1 > this.dispy - 2) {
                return;
            }
            if (this.apx4 - this.apx1 < this.dispx) {
                this.sx += (this.dx - x) * 100 / this.zoom;
            }
            if (this.apy4 - this.apy1 < this.dispy) {
                this.sy += (this.dy - y) * 100 / this.zoom;
            }
        }
        this.checkscroll();
        this.dx = x;
        this.dy = y;
        if (this.nwhl == 1) {
            this.Cancontwh.canrepaint(0);
        }
        else {
            this.Cancontnw.canrepaint(0);
        }
    }
    
    public void canvaspaint(final Graphics graphics) {
        this.showfileinfo();
        int n = this.apx1 + this.centx;
        int n2 = this.apy1 + this.centy;
        int n3 = this.apx2 - this.centx;
        int n4 = this.apy4 - this.centy;
        final int n5 = (this.sttype == 14 || this.sttype == 13) ? (this.sx & 0xFFFFFFFE) : this.sx;
        final int n6 = (this.sttype == 12) ? (this.sy & 0xFFFFFFFE) : this.sy;
        int n7 = n5 + (n3 - n) * 100 / this.zoom;
        int n8 = n6 + (n4 - n2) * 100 / this.zoom;
        int apx3 = this.apx3 + this.centx;
        int apx4 = this.apx4 - this.centx;
        int n9;
        if (this.dxl > this.dxr) {
            n9 = this.dxl;
        }
        else {
            n9 = this.dxr;
        }
        int n10;
        if (this.dyl > this.dyr) {
            n10 = this.dyl;
        }
        else {
            n10 = this.dyr;
        }
        final int n11 = n5 * this.zoom / 100;
        final int n12 = n6 * this.zoom / 100;
        final int n13 = this.dxr * this.zoom / 100;
        final int n14 = this.dyr * this.zoom / 100;
        final int n15 = this.dxl * this.zoom / 100;
        final int n16 = this.dyl * this.zoom / 100;
        if (this.sttype == 0 || this.sttype == 1 || this.sttype == 16 || this.sttype == 17 || this.sttype == 18) {
            if (this.centx > 0) {
                if (this.nSpace == 0) {
                    n = this.apx1 + this.centx;
                    n3 = n + this.dispx;
                    apx3 = this.apx4 - this.centx - this.dispx;
                    apx4 = apx3 + this.dispx;
                }
                else if (this.nSpace == 1) {
                    n = this.apx2 - this.dispx;
                    n3 = this.apx2;
                    apx3 = this.apx3;
                    apx4 = this.apx3 + this.dispx;
                }
                else {
                    n = 0;
                    n3 = this.dispx;
                    apx3 = this.apx4 - this.dispx;
                    apx4 = this.apx4;
                }
                n7 = this.w / 2 - n9;
            }
            if (this.centy > 0) {
                n2 = this.apy1 + this.centy;
                n4 = n2 + this.dispy;
                n8 = this.h - n10;
            }
            if (this.nSmooth == 1 || this.zoom >= 100) {
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    if (this.sttype == 1) {
                        graphics.drawImage(this.img, n, n2, n3, n4, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n7 + this.w / 2 + this.dxr, n8 + this.dyr, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 17) {
                        graphics.drawImage(this.img, n, n2, n3, n4, n5 + this.dxr, n6 + this.dyr, n7 + this.dxr, n8 + this.dyr, this);
                    }
                    else {
                        graphics.drawImage(this.img, n3, n2, n, n4, n5 + this.dxr, n6 + this.dyr, n7 + this.dxr, n8 + this.dyr, this);
                    }
                    graphics.drawImage(this.img, apx3 + 1, n2, apx3, n2 + 1, 0, 0, 1, 1, this);
                    if (this.sttype == 1) {
                        graphics.drawImage(this.img, apx3, n2, apx4, n4, n5 + this.dxl, n6 + this.dyl, n7 + this.dxl, n8 + this.dyl, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 16) {
                        graphics.drawImage(this.img, apx3, n2, apx4, n4, n5 + this.w / 2 + this.dxl, n6 + this.dyl, n7 + this.w / 2 + this.dxl, n8 + this.dyl, this);
                    }
                    else {
                        graphics.drawImage(this.img, apx4, n2, apx3, n4, n5 + this.w / 2 + this.dxl, n6 + this.dyl, n7 + this.w / 2 + this.dxl, n8 + this.dyl, this);
                    }
                }
                else {
                    if (this.sttype == 1) {
                        graphics.drawImage(this.img, n, n2, n3, n4, n5 + this.dxr, n6 + this.dyr, n7 + this.dxr, n8 + this.dyr, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 17) {
                        graphics.drawImage(this.img, n, n2, n3, n4, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n7 + this.w / 2 + this.dxr, n8 + this.dyr, this);
                    }
                    else {
                        graphics.drawImage(this.img, n3, n2, n, n4, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n7 + this.w / 2 + this.dxr, n8 + this.dyr, this);
                    }
                    graphics.drawImage(this.img, apx3 + 1, n2, apx3, n2 + 1, 0, 0, 1, 1, this);
                    if (this.sttype == 1) {
                        graphics.drawImage(this.img, apx3, n2, apx4, n4, n5 + this.w / 2 + this.dxl, n6 + this.dyl, n7 + this.w / 2 + this.dxl, n8 + this.dyl, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 16) {
                        graphics.drawImage(this.img, apx3, n2, apx4, n4, n5 + this.dxl, n6 + this.dyl, n7 + this.dxl, n8 + this.dyl, this);
                    }
                    else {
                        graphics.drawImage(this.img, apx4, n2, apx3, n4, n5 + this.dxl, n6 + this.dyl, n7 + this.dxl, n8 + this.dyl, this);
                    }
                }
            }
            if (this.nstart == 0) {
                this.nstart = 1;
                this.setPix5();
                graphics.drawImage(this.new_img3, 0, 0, 1, 1, this);
            }
            if (this.nSmooth == 0 && this.zoom < 100) {
                final int n17 = this.w * this.zoom / 100;
                final int n18 = this.h * this.zoom / 100;
                if (this.nSmoothimg == 2) {
                    this.setZoomPix(n17, n18);
                    this.nSmoothimg = 1;
                    this.nSmoothnew = 1;
                }
                int dispx = n11 + (n3 - n);
                int dispy = n12 + (n4 - n2);
                if (this.centx > 0) {
                    dispx = this.dispx;
                }
                if (this.centy > 0) {
                    dispy = this.dispy;
                }
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    if (this.sttype == 1) {
                        graphics.drawImage(this.new_img4, n, n2, n3, n4, n11 + n17 / 2 + n13, n12 + n14, dispx + n17 / 2 + n13, dispy + n14, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 17) {
                        graphics.drawImage(this.new_img4, n, n2, n3, n4, n11 + n13, n12 + n14, dispx + n13, dispy + n14, this);
                    }
                    else {
                        graphics.drawImage(this.new_img4, n3, n2, n, n4, n11 + n13, n12 + n14, dispx + n13, dispy + n14, this);
                    }
                    graphics.drawImage(this.new_img4, apx3 + 1, n2, apx3, n2 + 1, 0, 0, 1, 1, this);
                    if (this.sttype == 1) {
                        graphics.drawImage(this.new_img4, apx3, n2, apx4, n4, n11 + n15, n12 + n16, dispx + n15, dispy + n16, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 16) {
                        graphics.drawImage(this.new_img4, apx3, n2, apx4, n4, n11 + n17 / 2 + n15, n12 + n16, dispx + n17 / 2 + n15, dispy + n16, this);
                    }
                    else {
                        graphics.drawImage(this.new_img4, apx4, n2, apx3, n4, n11 + n17 / 2 + n15, n12 + n16, dispx + n17 / 2 + n15, dispy + n16, this);
                    }
                }
                else {
                    if (this.sttype == 1) {
                        graphics.drawImage(this.new_img4, n, n2, n3, n4, n11 + n13, n12 + n14, dispx + n13, dispy + n14, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 17) {
                        graphics.drawImage(this.new_img4, n, n2, n3, n4, n11 + n17 / 2 + n13, n12 + n14, dispx + n17 / 2 + n13, dispy + n14, this);
                    }
                    else {
                        graphics.drawImage(this.new_img4, n3, n2, n, n4, n11 + n17 / 2 + n13, n12 + n14, dispx + n17 / 2 + n13, dispy + n14, this);
                    }
                    graphics.drawImage(this.new_img4, apx3 + 1, n2, apx3, n2 + 1, 0, 0, 1, 1, this);
                    if (this.sttype == 1) {
                        graphics.drawImage(this.new_img4, apx3, n2, apx4, n4, n11 + n17 / 2 + n15, n12 + n16, dispx + n17 / 2 + n15, dispy + n16, this);
                    }
                    else if (this.sttype == 0 || this.sttype == 16) {
                        graphics.drawImage(this.new_img4, apx3, n2, apx4, n4, n11 + n15, n12 + n16, dispx + n15, dispy + n16, this);
                    }
                    else {
                        graphics.drawImage(this.new_img4, apx4, n2, apx3, n4, n11 + n15, n12 + n16, dispx + n15, dispy + n16, this);
                    }
                }
            }
        }
        else if (this.sttype == 2 || this.sttype == 19) {
            final int n19 = this.apx1 + this.centx;
            int n20 = this.apy1 + this.centy;
            final int n21 = n19 + this.dispx;
            int n22 = this.apy2 - this.centy;
            int n23 = n5 + (n21 - n19) * 100 / this.zoom;
            int n24 = n6 + (n22 - n20) * 100 / this.zoom;
            int apy3 = this.apy3 + this.centy;
            int apy4 = this.apy4 - this.centy;
            if (this.centy > 0) {
                if (this.nSpace == 0) {
                    n20 = this.apy1 + this.centy;
                    n22 = n20 + this.dispy;
                    apy3 = this.apy4 - this.centy - this.dispy;
                    apy4 = apy3 + this.dispy;
                }
                else if (this.nSpace == 1) {
                    n20 = this.apy2 - this.dispy;
                    n22 = this.apy2;
                    apy3 = this.apy3;
                    apy4 = apy3 + this.dispy;
                }
                else {
                    n20 = 0;
                    n22 = this.dispy;
                    apy3 = this.apy4 - this.dispy;
                    apy4 = this.apy4;
                }
                n24 = this.h - n10;
            }
            if (this.centx > 0) {
                n23 = this.w / 2 - n9;
            }
            if (this.nSmooth == 1 || this.zoom >= 100) {
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    if (this.sttype == 2) {
                        graphics.drawImage(this.img, n19, n20, n21, n22, n5 + this.dxr, n6 + this.dyr, n23 + this.dxr, n24 + this.dyr, this);
                        graphics.drawImage(this.img, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                        graphics.drawImage(this.img, n19, apy3, n21, apy4, n5 + this.w / 2 + this.dxl, n6 + this.dyl, n23 + this.w / 2 + this.dxl, n24 + this.dyl, this);
                    }
                    else {
                        graphics.drawImage(this.img, n19, n22, n21, n20, n5 + this.dxr, n6 + this.dyr, n23 + this.dxr, n24 + this.dyr, this);
                        graphics.drawImage(this.img, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                        graphics.drawImage(this.img, n19, apy4, n21, apy3, n5 + this.w / 2 + this.dxl, n6 + this.dyl, n23 + this.w / 2 + this.dxl, n24 + this.dyl, this);
                    }
                }
                else if (this.sttype == 2) {
                    graphics.drawImage(this.img, n19, n20, n21, n22, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n23 + this.w / 2 + this.dxr, n24 + this.dyr, this);
                    graphics.drawImage(this.img, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                    graphics.drawImage(this.img, n19, apy3, n21, apy4, n5 + this.dxl, n6 + this.dyl, n23 + this.dxl, n24 + this.dyl, this);
                }
                else {
                    graphics.drawImage(this.img, n19, n22, n21, n20, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n23 + this.w / 2 + this.dxr, n24 + this.dyr, this);
                    graphics.drawImage(this.img, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                    graphics.drawImage(this.img, n19, apy4, n21, apy3, n5 + this.dxl, n6 + this.dyl, n23 + this.dxl, n24 + this.dyl, this);
                }
            }
            if (this.nSmooth == 0 && this.zoom < 100) {
                final int n25 = this.w * this.zoom / 100;
                final int n26 = this.h * this.zoom / 100;
                if (this.nSmoothimg == 2) {
                    this.setZoomPix(n25, n26);
                    this.nSmoothimg = 1;
                    this.nSmoothnew = 1;
                }
                int dispx2 = n11 + (n21 - n19);
                int dispy2 = n12 + (n22 - n20);
                if (this.centy > 0) {
                    dispy2 = this.dispy;
                }
                if (this.centx > 0) {
                    dispx2 = this.dispx;
                }
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    if (this.sttype == 2) {
                        graphics.drawImage(this.new_img4, n19, n20, n21, n22, n11 + n13, n12 + n14, dispx2 + n13, dispy2 + n14, this);
                        graphics.drawImage(this.new_img4, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                        graphics.drawImage(this.new_img4, n19, apy3, n21, apy4, n11 + n25 / 2 + n15, n12 + n16, dispx2 + n25 / 2 + n15, dispy2 + n16, this);
                    }
                    else {
                        graphics.drawImage(this.new_img4, n19, n22, n21, n20, n11 + n13, n12 + n14, dispx2 + n13, dispy2 + n14, this);
                        graphics.drawImage(this.new_img4, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                        graphics.drawImage(this.new_img4, n19, apy4, n21, apy3, n11 + n25 / 2 + n15, n12 + n16, dispx2 + n25 / 2 + n15, dispy2 + n16, this);
                    }
                }
                else if (this.sttype == 2) {
                    graphics.drawImage(this.new_img4, n19, n20, n21, n22, n11 + n25 / 2 + n13, n12 + n14, dispx2 + n25 / 2 + n13, dispy2 + n14, this);
                    graphics.drawImage(this.new_img4, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                    graphics.drawImage(this.new_img4, n19, apy3, n21, apy4, n11 + n15, n12 + n16, dispx2 + n15, dispy2 + n16, this);
                }
                else {
                    graphics.drawImage(this.new_img4, n19, n22, n21, n20, n11 + n25 / 2 + n13, n12 + n14, dispx2 + n25 / 2 + n13, dispy2 + n14, this);
                    graphics.drawImage(this.new_img4, n19 + 1, apy3, n19, apy3 + 1, 0, 0, 1, 1, this);
                    graphics.drawImage(this.new_img4, n19, apy4, n21, apy3, n11 + n15, n12 + n16, dispx2 + n15, dispy2 + n16, this);
                }
            }
        }
        else if (this.sttype == 12 || this.sttype == 13 || this.sttype == 14 || this.sttype == 15) {
            if (this.nclear > 0) {
                graphics.clearRect(0, 0, this.apx, this.apy);
                --this.nclear;
            }
            int n27 = n;
            int n28 = apx4;
            if (this.sttype == 14 || this.sttype == 13) {
                n27 = (n & 0xFFFFFFFE);
                if (n != n27) {
                    --n28;
                }
            }
            int n29 = n2;
            int n30 = n4;
            if (this.sttype == 12) {
                n29 = (n2 & 0xFFFFFFFE);
                if (n2 != n29) {
                    --n30;
                }
            }
            graphics.drawImage(this.new_img3, n27, n29, n28 - 1, n30 - 1, n5, n6, n5 + (n28 - n27) - 1, n6 + (n30 - n29) - 1, this);
        }
        else if (this.sttype == 20) {
            int n31 = this.apx4 - this.centx;
            int n32 = n5 + (n31 - n) * 100 / this.zoom;
            if (this.centx > 0) {
                n = this.apx1 + this.centx;
                n31 = n + this.dispx;
                n32 = this.w / 2 - n9;
            }
            if (this.centy > 0) {
                n2 = this.apy1 + this.centy;
                n4 = n2 + this.dispy;
                n8 = this.h - n10;
            }
            if (this.nSmooth == 1 || this.zoom >= 100) {
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    graphics.drawImage(this.img, n, n2, n31, n4, n5 + this.dxr, n6 + this.dyr, n32 + this.dxr, n8 + this.dyr, this);
                }
                else {
                    graphics.drawImage(this.img, n, n2, n31, n4, n5 + this.w / 2 + this.dxr, n6 + this.dyr, n32 + this.w / 2 + this.dxr, n8 + this.dyr, this);
                }
            }
            if (this.nSmooth == 0 && this.zoom < 100) {
                final int n33 = this.w * this.zoom / 100;
                final int n34 = this.h * this.zoom / 100;
                if (this.nSmoothimg == 2) {
                    this.setZoomPix(n33, n34);
                    this.nSmoothimg = 1;
                    this.nSmoothnew = 1;
                }
                int dispx3 = n11 + (n31 - n);
                int dispy3 = n12 + (n4 - n2);
                if (this.centy > 0) {
                    dispy3 = this.dispy;
                }
                if (this.centx > 0) {
                    dispx3 = this.dispx;
                }
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                if (this.swp == 0) {
                    graphics.drawImage(this.new_img4, n, n2, n31, n4, n11 + n13, n12 + n14, dispx3 + n13, dispy3 + n14, this);
                }
                else {
                    graphics.drawImage(this.new_img4, n, n2, n31, n4, n11 + n33 / 2 + n13, n12 + n14, dispx3 + n33 / 2 + n13, dispy3 + n14, this);
                }
            }
        }
        else {
            final int n35 = this.apx4 - this.centx;
            int n36 = n5 + (n35 - n) * 100 / this.zoom;
            if (this.centx > 0) {
                n36 = this.w / 2 - n9;
            }
            if (this.centy > 0) {
                n8 = this.h - n10;
            }
            if (this.nSmooth == 1 || this.zoom >= 100) {
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                graphics.drawImage(this.new_img3, n, n2, n35, n4, n5, n6, n36, n8, this);
            }
            if (this.nSmooth == 0 && this.zoom < 100) {
                final int n37 = this.new_img3.getWidth(this) * this.zoom / 100;
                final int n38 = this.new_img3.getHeight(this) * this.zoom / 100;
                final int n39 = this.w * this.zoom / 200;
                final int n40 = this.h * this.zoom / 100;
                if (this.nSmoothnew == 2) {
                    this.setZoomPix2(n39 - n9 * this.zoom / 100, n40 - n10 * this.zoom / 100);
                    this.nSmoothnew = 1;
                    this.nSmoothimg = 1;
                }
                final int n41 = this.apx4 - this.centx;
                int n42 = n11 + (n41 - n);
                int n43 = n12 + (n4 - n2);
                if (this.centx > 0) {
                    n42 = n39 - n9 * this.zoom / 100;
                }
                if (this.centy > 0) {
                    n43 = n40 - n10 * this.zoom / 100;
                }
                if (this.nclear > 0) {
                    graphics.clearRect(0, 0, this.apx, this.apy);
                    --this.nclear;
                }
                graphics.drawImage(this.new_img4, n, n2, n41 - 1, n4 - 1, n11, n12, n42 - 1, n43 - 1, this);
            }
        }
    }
    
    static {
        Dubois = new double[][] { { 0.4561, 0.500484, 0.176381, -0.0434706, -0.0879388, -0.00155529 }, { -0.0400822, -0.0378246, -0.0157589, 0.378476, 0.73364, -0.0184503 }, { -0.0152161, -0.0205971, -0.00546856, -0.0721527, -0.112961, 1.2264 } };
        DuboisYB = new double[][] { { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.9, 0.1, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.1, 0.3, 0.59, 0.4 } };
    }
    
    private class canvascontwh
    {
        fej3dfrm applet3d;
        wheelcanvas whcanvas;
        
        public canvascontwh(final fej3dfrm applet3d) {
            this.whcanvas = null;
            this.applet3d = applet3d;
        }
        
        public void canrepaint(final int nclear) {
            if (fej3dfrm.this.sttype == 21) {
                this.applet3d.showfileinfo();
                return;
            }
            fej3dfrm.this.nclear = nclear;
            if (this.whcanvas != null) {
                this.whcanvas.repaint();
            }
        }
        
        public void canstart() {
            this.whcanvas = new wheelcanvas(this.applet3d);
        }
        
        public void canstop() {
            if (this.whcanvas != null) {
                this.whcanvas.canremove();
            }
        }
        
        public void canresize() {
            if (this.whcanvas != null) {
                this.whcanvas.canresize();
            }
        }
    }
    
    private class canvascontnw
    {
        fej3dfrm applet3d;
        nowheelcanvas nowhcanvas;
        
        public canvascontnw(final fej3dfrm applet3d) {
            this.nowhcanvas = null;
            this.applet3d = applet3d;
        }
        
        public void canrepaint(final int nclear) {
            if (fej3dfrm.this.sttype == 21) {
                this.applet3d.showfileinfo();
                return;
            }
            fej3dfrm.this.nclear = nclear;
            if (this.nowhcanvas != null) {
                this.nowhcanvas.repaint();
            }
        }
        
        public void canstart() {
            this.nowhcanvas = new nowheelcanvas(this.applet3d);
        }
        
        public void canstop() {
            if (this.nowhcanvas != null) {
                this.nowhcanvas.canremove();
            }
        }
        
        public void canresize() {
            if (this.nowhcanvas != null) {
                this.nowhcanvas.canresize();
            }
        }
    }
    
    private class canvascont
    {
        fej3dfrm applet3d;
        stereocanvas myCanvas3D;
        
        public canvascont(final fej3dfrm applet3d) {
            this.myCanvas3D = null;
            this.applet3d = applet3d;
        }
        
        public void canvasstart() {
            final GraphicsConfigTemplate3D graphicsConfigTemplate3D = new GraphicsConfigTemplate3D();
            graphicsConfigTemplate3D.setStereo(1);
            this.myCanvas3D = new stereocanvas(this.applet3d, GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration((GraphicsConfigTemplate)graphicsConfigTemplate3D));
            if (fej3dfrm.this.nfit == 1) {
                fej3dfrm.this.FitSize();
            }
            ((Component)this.myCanvas3D).repaint();
        }
        
        public void canvasstop() {
            this.myCanvas3D.bufclear();
            fej3dfrm.this.universe.cleanup();
            this.myCanvas3D.canremove();
            this.myCanvas3D = null;
            fej3dfrm.this.universe = null;
        }
        
        public void canvasmkimg() {
            this.myCanvas3D.makebufimage();
        }
        
        public void canresize() {
            if (fej3dfrm.this.nFull == 0) {
                ((Component)this.myCanvas3D).setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                ((Component)this.myCanvas3D).setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
            ((Component)this.myCanvas3D).repaint();
        }
        
        public void canvasrepaint() {
            ((Component)this.myCanvas3D).repaint();
        }
    }
    
    private class wheelcanvas extends Canvas implements KeyListener, MouseWheelListener, MouseListener, MouseMotionListener
    {
        fej3dfrm applet3d;
        
        public wheelcanvas(final fej3dfrm applet3d) {
            (this.applet3d = applet3d).setLayout(null);
            this.applet3d.add("Center", this);
            if (fej3dfrm.this.nFull == 0) {
                this.setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                this.setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
            this.requestFocus();
            this.addKeyListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addMouseWheelListener(this);
            this.setBackground(Color.black);
        }
        
        public void canremove() {
            this.applet3d.remove(this);
        }
        
        public void canresize() {
            if (fej3dfrm.this.nFull == 0) {
                this.setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                this.setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void paint(final Graphics graphics) {
            if (fej3dfrm.this.sttype == 21) {
                return;
            }
            this.applet3d.canvaspaint(graphics);
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            this.applet3d.keyPressed(keyEvent);
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.applet3d.keyReleased(keyEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {}
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.applet3d.mousePressed(mouseEvent);
            this.requestFocus();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.applet3d.mouseDragged(mouseEvent);
        }
        
        public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            if (fej3dfrm.this.sttype == 21) {
                return;
            }
            fej3dfrm.this.nfit = 0;
            fej3dfrm.this.zoompv = fej3dfrm.this.zoom;
            if (mouseWheelEvent.getWheelRotation() == 1) {
                final fej3dfrm this$0 = fej3dfrm.this;
                this$0.zoom -= 4;
                if (fej3dfrm.this.zoom < 4) {
                    fej3dfrm.this.zoom = 4;
                }
            }
            else {
                final fej3dfrm this$2 = fej3dfrm.this;
                this$2.zoom += 4;
            }
            if (fej3dfrm.this.nSmooth == 0) {
                fej3dfrm.this.nSmoothimg = 2;
                fej3dfrm.this.nSmoothnew = 2;
            }
            this.applet3d.setstpix();
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {
                this.requestFocus();
            }
        }
    }
    
    private class nowheelcanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener
    {
        fej3dfrm applet3d;
        
        public nowheelcanvas(final fej3dfrm applet3d) {
            (this.applet3d = applet3d).setLayout(null);
            this.applet3d.add("Center", this);
            if (fej3dfrm.this.nFull == 0) {
                this.setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                this.setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
            this.requestFocus();
            this.addKeyListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.setBackground(Color.black);
        }
        
        public void canremove() {
            this.applet3d.remove(this);
        }
        
        public void canresize() {
            if (fej3dfrm.this.nFull == 0) {
                this.setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                this.setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void paint(final Graphics graphics) {
            if (fej3dfrm.this.sttype == 21) {
                return;
            }
            this.applet3d.canvaspaint(graphics);
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            this.applet3d.keyPressed(keyEvent);
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.applet3d.keyReleased(keyEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {}
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.applet3d.mousePressed(mouseEvent);
            this.requestFocus();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.applet3d.mouseDragged(mouseEvent);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {
                this.requestFocus();
            }
        }
    }
    
    private class stereocanvas extends Canvas3D implements KeyListener, MouseWheelListener, MouseListener, MouseMotionListener
    {
        private GraphicsContext3D _gc3D;
        private BufferedImage _imageLeft;
        private BufferedImage _imageRight;
        int nImgwidth;
        int nImgheight;
        int nScrheight;
        int nScrwidth;
        int xCurrentScroll;
        int yCurrentScroll;
        fej3dfrm applet3d;
        
        public stereocanvas(final fej3dfrm applet3d, final GraphicsConfiguration graphicsConfiguration) {
            super(graphicsConfiguration);
            this.xCurrentScroll = 0;
            this.yCurrentScroll = 0;
            this.applet3d = applet3d;
            ((Component)this).addKeyListener(this);
            ((Component)this).addMouseListener(this);
            ((Component)this).addMouseMotionListener(this);
            ((Component)this).addMouseWheelListener(this);
            this.makebufimage();
            this.setStereoEnable(true);
            this.stopRenderer();
            this.applet3d.setLayout(null);
            this.applet3d.add("Center", (Component)this);
            if (fej3dfrm.this.nFull == 0) {
                ((Component)this).setBounds(0, fej3dfrm.this.nFT + 24, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFT + fej3dfrm.this.nFB + 24));
            }
            else {
                ((Component)this).setBounds(0, fej3dfrm.this.nFT - 3, this.applet3d.getSize().width, this.applet3d.getSize().height - (fej3dfrm.this.nFB + fej3dfrm.this.nFT - 3));
            }
            fej3dfrm.this.universe = new SimpleUniverse((Canvas3D)this);
            fej3dfrm.this.universe.getViewingPlatform().setNominalViewingTransform();
        }
        
        public void canremove() {
            this.applet3d.remove((Component)this);
        }
        
        public void bufclear() {
            this._gc3D.flush(true);
            this._imageLeft.flush();
            this._imageRight.flush();
            this._imageLeft = null;
            this._imageRight = null;
            this._gc3D = null;
        }
        
        public void makebufimage() {
            fej3dfrm.this.w = fej3dfrm.this.img.getWidth((ImageObserver)this);
            fej3dfrm.this.h = fej3dfrm.this.img.getHeight((ImageObserver)this);
            this.nImgwidth = fej3dfrm.this.w / 2;
            this.nImgheight = fej3dfrm.this.h;
            this._imageLeft = new BufferedImage(this.nImgwidth, this.nImgheight, 5);
            final Graphics graphics = this._imageLeft.getGraphics();
            graphics.drawImage(fej3dfrm.this.img, 0, 0, null);
            graphics.dispose();
            this._imageRight = new BufferedImage(this.nImgwidth, this.nImgheight, 5);
            final Graphics graphics2 = this._imageRight.getGraphics();
            graphics2.drawImage(fej3dfrm.this.img, 0, 0, this.nImgwidth, this.nImgheight, this.nImgwidth, 0, this.nImgwidth * 2, this.nImgheight, null);
            graphics2.dispose();
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
            this.applet3d.showfileinfo();
            final Dimension size = this.getSize();
            this.nScrheight = size.height;
            this.nScrwidth = size.width;
            final int n = this.nImgwidth * fej3dfrm.this.zoom / 100;
            final int n2 = this.nImgheight * fej3dfrm.this.zoom / 100;
            int xCurrentScroll;
            if (this.nScrwidth - n > 0) {
                xCurrentScroll = (this.nScrwidth - n) / 2;
                this.xCurrentScroll = 0;
            }
            else {
                if (this.xCurrentScroll < this.nScrwidth - n) {
                    this.xCurrentScroll = this.nScrwidth - n;
                }
                if (this.xCurrentScroll > 0) {
                    this.xCurrentScroll = 0;
                }
                xCurrentScroll = this.xCurrentScroll;
            }
            int yCurrentScroll;
            if (this.nScrheight - n2 > 0) {
                yCurrentScroll = (this.nScrheight - n2) / 2;
                this.yCurrentScroll = 0;
            }
            else {
                if (this.yCurrentScroll < this.nScrheight - n2) {
                    this.yCurrentScroll = this.nScrheight - n2;
                }
                if (this.yCurrentScroll > 0) {
                    this.yCurrentScroll = 0;
                }
                yCurrentScroll = this.yCurrentScroll;
            }
            BufferedImage bufferedImage;
            BufferedImage bufferedImage2;
            int n3;
            int n4;
            if (fej3dfrm.this.swp == 0) {
                bufferedImage = this._imageLeft;
                bufferedImage2 = this._imageRight;
                n3 = fej3dfrm.this.dltx * fej3dfrm.this.zoom / 200;
                n4 = fej3dfrm.this.dlty * fej3dfrm.this.zoom / 200;
            }
            else {
                bufferedImage2 = this._imageLeft;
                bufferedImage = this._imageRight;
                n3 = -fej3dfrm.this.dltx * fej3dfrm.this.zoom / 200;
                n4 = -fej3dfrm.this.dlty * fej3dfrm.this.zoom / 200;
            }
            final J3DGraphics2D graphics2D = this.getGraphics2D();
            (this._gc3D = this.getGraphicsContext3D()).setBufferOverride(true);
            this._gc3D.setStereoMode(0);
            this._gc3D.clear();
            if (n > 0 && n2 > 0) {
                ((Graphics)graphics2D).drawImage(bufferedImage, xCurrentScroll + n3, yCurrentScroll + n4, n, n2, (ImageObserver)this);
            }
            graphics2D.flush(true);
            this.swap();
            this._gc3D.setStereoMode(1);
            this._gc3D.clear();
            if (n > 0 && n2 > 0) {
                ((Graphics)graphics2D).drawImage(bufferedImage2, xCurrentScroll - n3, yCurrentScroll - n4, n, n2, (ImageObserver)this);
            }
            graphics2D.flush(true);
            this.swap();
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            this.applet3d.keyPressed(keyEvent);
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.applet3d.keyReleased(keyEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {}
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            fej3dfrm.this.dx = mouseEvent.getX();
            fej3dfrm.this.dy = mouseEvent.getY();
            ((Component)this).requestFocus();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.xCurrentScroll += x - fej3dfrm.this.dx;
            this.yCurrentScroll += y - fej3dfrm.this.dy;
            ((Component)this).repaint();
            fej3dfrm.this.dx = x;
            fej3dfrm.this.dy = y;
        }
        
        public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            fej3dfrm.this.nfit = 0;
            fej3dfrm.this.zoompv = fej3dfrm.this.zoom;
            if (mouseWheelEvent.getWheelRotation() == 1) {
                final fej3dfrm this$0 = fej3dfrm.this;
                this$0.zoom -= 4;
                if (fej3dfrm.this.zoom < 4) {
                    fej3dfrm.this.zoom = 4;
                }
            }
            else {
                final fej3dfrm this$2 = fej3dfrm.this;
                this$2.zoom += 4;
            }
            ((Component)this).repaint();
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (mouseEvent.getComponent().equals(this)) {
                ((Component)this).requestFocus();
            }
        }
    }
}
