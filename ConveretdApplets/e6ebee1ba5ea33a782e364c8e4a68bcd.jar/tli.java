import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.util.Calendar;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class tli extends Panel implements MouseMotionListener, MouseListener
{
    static final int xMax = 90;
    static final String sR = "http://www.javaside.com/";
    private Vector vFile;
    private int nV;
    private String sEnc;
    private boolean AutoClose;
    private boolean bLoc;
    private boolean bReg;
    private char cSep;
    private int bOK;
    private Enumeration enu;
    private int iEnu;
    private int iPress;
    private int cPress;
    private int iDrag;
    private int xDrag;
    private String[] sV;
    private int mV;
    public Vector dList;
    public Vector iList;
    public int iT;
    private int iXX;
    private int iActif;
    private int iPos;
    private int yPos;
    private int xPos;
    private int iSk;
    private int iNext;
    private int iCur;
    private int iMax;
    private int iOpen;
    private Image buffer;
    private Graphics offScreen;
    private boolean onButt;
    private boolean onPres;
    private boolean onOpen;
    private URL clickDest;
    private Image[] img;
    private Image[] img2;
    private Image bgimg;
    private Image simg;
    private Image icoimg;
    private AudioClip[] song;
    private int iPas;
    private int iHom;
    private String sURL;
    private String sH;
    private String sDate;
    private String sFile;
    private int bForce;
    private Font[] wFont;
    private Color[] cFont;
    private int nbFont;
    private FontMetrics wMetrics;
    private int aWidth;
    private int aHeight;
    private int bgWidth;
    private int bgHeight;
    private int m_bgc;
    private String m_targ;
    private MediaTracker trk;
    private tli_rbl p;
    
    public tli(final tli_rbl p) {
        this.vFile = new Vector(5, 1);
        this.sEnc = "Cp1252";
        this.AutoClose = false;
        this.bLoc = false;
        this.bReg = false;
        this.cSep = ';';
        this.bOK = 1;
        this.iPress = 1;
        this.sV = new String[10];
        this.dList = new Vector(10, 10);
        this.iList = new Vector(10, 10);
        this.iXX = -1;
        this.iActif = -1;
        this.onButt = false;
        this.onPres = false;
        this.onOpen = false;
        this.sDate = "";
        this.bForce = -1;
        this.wFont = new Font[10];
        this.cFont = new Color[20];
        this.bgWidth = -1;
        this.bgHeight = -1;
        this.m_targ = "_self";
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setVisible(false);
        this.p = p;
        this.init();
    }
    
    public void init() {
        for (int n = 0; this.p.getParameter("v" + n) != null; ++n) {
            if (n < 10) {
                this.sV[n] = this.p.getParameter("v" + n);
                ++this.mV;
            }
        }
        final String parameter = this.p.getParameter("target");
        if (parameter != null) {
            this.m_targ = parameter;
        }
        final String parameter2 = this.p.getParameter("autoclose");
        if (parameter2 != null) {
            this.AutoClose = (parameter2.charAt(0) == 'Y');
        }
        this.m_bgc = this.p.readColor("bgcolor", this.m_bgc);
        this.cPress = this.p.readColor("accolor", this.cPress);
        final String parameter3 = this.p.getParameter("acpress");
        if (parameter3 != null) {
            this.iPress = Integer.parseInt(parameter3);
        }
        String s = this.p.getParameter("font" + this.nbFont);
        final rd_l rd_l = new rd_l();
        while (s != null) {
            if (rd_l.get(rd_l, s, "", ',')) {
                if (rd_l.sDesc[0] != null && rd_l.iN > -1 && rd_l.iN < 5 && rd_l.iE > 2) {
                    this.wFont[this.nbFont] = new Font(rd_l.sDesc[0], rd_l.iN, rd_l.iE);
                }
                else {
                    this.wFont[this.nbFont] = this.p.getFont();
                }
                this.cFont[this.nbFont * 2] = new Color(rd_l.i3);
                this.cFont[this.nbFont * 2 + 1] = new Color(rd_l.iF);
                ++this.nbFont;
            }
            s = this.p.getParameter("font" + this.nbFont);
        }
        if (this.nbFont < 2) {
            this.cFont[0] = new Color(0);
            this.cFont[1] = new Color(16777120);
            this.wFont[0] = this.p.getFont();
        }
        if (this.nbFont < 1) {
            this.cFont[2] = new Color(0);
            this.cFont[3] = new Color(0);
            this.wFont[1] = this.p.getFont();
        }
        final String parameter4 = this.p.getParameter("encoding");
        if (parameter4 != null) {
            this.sEnc = new String(parameter4);
        }
        final String parameter5 = this.p.getParameter("coef");
        int int1;
        if (parameter5 != null) {
            int1 = Integer.parseInt(parameter5);
        }
        else {
            int1 = 120;
        }
        this.wMetrics = this.p.getFontMetrics(this.wFont[1]);
        this.iT = this.wMetrics.getHeight() * int1 / 100;
        this.wMetrics = this.p.getFontMetrics(this.wFont[0]);
        final String parameter6 = this.p.getParameter("csep");
        if (parameter6 != null) {
            this.cSep = parameter6.charAt(0);
        }
        this.trk = new MediaTracker(this);
        final String parameter7 = this.p.getParameter("bgImg");
        if (parameter7 != null) {
            this.bgimg = this.p.getImage(this.p.getCodeBase(), parameter7);
            this.trk.addImage(this.bgimg, 90);
        }
        else {
            this.bgimg = null;
        }
        (this.song = new AudioClip[2])[0] = null;
        this.song[1] = null;
        final String parameter8 = this.p.getParameter("audio");
        if (parameter8 != null) {
            this.song[0] = this.p.getAudioClip(this.p.getCodeBase(), String.valueOf(parameter8) + "tree.au");
            this.song[1] = this.p.getAudioClip(this.p.getCodeBase(), String.valueOf(parameter8) + "link.au");
        }
        final String parameter9 = this.p.getParameter("bImg");
        if (parameter9 != null) {
            this.icoimg = this.p.getImage(this.p.getCodeBase(), parameter9);
            this.trk.addImage(this.icoimg, 91);
        }
        else {
            this.icoimg = null;
        }
        final String parameter10 = this.p.getParameter("local");
        this.bLoc = false;
        if (parameter10 != null) {
            this.bLoc = parameter10.equalsIgnoreCase("yes");
        }
        final Calendar instance = Calendar.getInstance();
        this.sDate = String.valueOf(instance.get(1));
        final int n2 = 1 + instance.get(2);
        if (n2 < 10) {
            this.sDate = String.valueOf(this.sDate) + "0";
        }
        this.sDate = String.valueOf(this.sDate) + "" + n2;
        final int value = instance.get(5);
        if (value < 10) {
            this.sDate = String.valueOf(this.sDate) + "0";
        }
        this.sDate = String.valueOf(this.sDate) + "" + value;
        this.rFile(this.p.getParameter("file"), 1);
    }
    
    private void loadIcon() {
        if (this.icoimg != null) {
            final int n = this.icoimg.getWidth(this) / 10;
            final int n2 = this.icoimg.getHeight(this) / 10;
            this.img2 = new Image[9];
            for (int i = 0; i < 9; ++i) {
                this.img2[i] = this.createImage(new FilteredImageSource(this.icoimg.getSource(), new CropImageFilter(i * n, 0, n, n2)));
            }
            this.img = new Image[90];
            for (int j = 0; j < 90; ++j) {
                final int n3 = j % 10;
                final int n4 = 1 + j / 10;
                if (j > 85) {
                    this.img[j] = this.createImage(new FilteredImageSource(this.icoimg.getSource(), new CropImageFilter(n3 * n, n4 * n2, 14, n2)));
                }
                else {
                    this.img[j] = this.createImage(new FilteredImageSource(this.icoimg.getSource(), new CropImageFilter(n3 * n, n4 * n2, n, n2)));
                }
            }
            return;
        }
        for (int k = 0; k < 90; ++k) {
            this.img[k] = null;
        }
        for (int l = 0; l < 9; ++l) {
            this.img2[l] = null;
        }
    }
    
    public void rFile(final String s, final int bok) {
        this.bOK = bok;
        this.sFile = new String(s);
        this.readFile();
    }
    
    public void setBgcolor(final int bgc) {
        this.m_bgc = bgc;
        this.repaint();
    }
    
    public void setPress(final int iPress, final int cPress) {
        this.iPress = iPress;
        this.cPress = cPress;
    }
    
    public void closeAll(final int bForce) {
        if (bForce == 0 || bForce == 1) {
            if ((this.bForce = bForce) == 0) {
                this.iCur = 0;
            }
            this.repaint();
        }
    }
    
    public void change(final String s, final int n) {
        rd_l rd_l = null;
        int n2 = 0;
        int n3 = -1;
        final Enumeration<rd_l> elements = (Enumeration<rd_l>)this.dList.elements();
        while (elements.hasMoreElements()) {
            rd_l = elements.nextElement();
            if (rd_l.sDesc[0].equals(s)) {
                rd_l.open_close();
                n3 = n2;
                break;
            }
            ++n2;
        }
        if (n3 > -1) {
            this.trtGet(n3, rd_l, n);
        }
    }
    
    public void search(final String s, final int n) {
        this.iEnu = 0;
        this.enu = this.dList.elements();
        this.next(s, n);
    }
    
    public void next(final String s, final int n) {
        rd_l rd_l = null;
        int n2 = -1;
        final String lowerCase = s.toLowerCase();
        if (this.enu == null) {
            this.enu = this.dList.elements();
            this.iEnu = 0;
        }
        while (this.enu.hasMoreElements()) {
            rd_l = this.enu.nextElement();
            if (rd_l.isKeys(lowerCase)) {
                rd_l.open_close();
                n2 = this.iEnu++;
                break;
            }
            ++this.iEnu;
        }
        if (n2 > -1) {
            this.trtGet(n2, rd_l, n);
            return;
        }
        this.iEnu = 0;
        this.enu = null;
    }
    
    void trtGet(final int iActif, final rd_l rd_l, final int n) {
        boolean b = rd_l.iN > 0;
        final int in = rd_l.iN;
        this.iActif = iActif;
        if (this.AutoClose) {
            this.forceClose();
        }
        for (int i = iActif - 1; i > 0; --i) {
            if (((rd_l)this.dList.elementAt(i)).iS == 0 && (((rd_l)this.dList.elementAt(i)).iN < in & b)) {
                ((rd_l)this.dList.elementAt(i)).iS = 1;
            }
            if (((rd_l)this.dList.elementAt(i)).iN == 0) {
                b = false;
            }
        }
        int n2 = 0;
        for (int j = 0; j <= iActif; ++j) {
            n2 += this.isEl(j, this.dList.elementAt(j));
        }
        this.iCur = n2 - 1;
        if (n == 0 || n == 2) {
            if (n == 2) {
                this.iXX = this.iActif;
            }
            this.repaint();
            return;
        }
        String surl = rd_l.sURL;
        if (rd_l.iE == 2) {
            surl = null;
        }
        Label_0296: {
            if (surl != null) {
                try {
                    if (surl.charAt(0) == '.') {
                        this.clickDest = new URL(this.p.getCodeBase(), surl);
                        break Label_0296;
                    }
                    this.clickDest = new URL(surl);
                    break Label_0296;
                }
                catch (Exception ex) {
                    return;
                }
            }
            this.clickDest = null;
        }
        this.goURL();
        this.iXX = this.iActif;
    }
    
    public void start() {
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.iNext + this.iCur > 0 && this.iDrag > 0) {
            if (mouseEvent.getX() < this.aWidth - 15) {
                this.iDrag = -1;
                this.xDrag = -1;
            }
            else {
                int n = 0;
                if (this.iHom > 0) {
                    n = 1;
                }
                final int n2 = (this.aHeight - 35 - this.iPas + n * this.iT) / (this.iCur + this.iMax + this.iNext);
                this.xDrag = mouseEvent.getY();
                final int n3 = (this.xDrag - this.iDrag) / n2;
                if (n3 != 0) {
                    if (n3 <= 0 || this.iNext != 0) {
                        this.iCur += n3;
                        if (this.iCur < 0) {
                            this.iCur = 0;
                        }
                    }
                    this.iDrag = this.xDrag;
                }
            }
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = (y - this.iPas) / this.iT;
        boolean b = false;
        if (this.bOK > 0) {
            return;
        }
        if (y > this.iPas) {
            if (y > this.aHeight - this.iT - 3 && this.iHom > 0) {
                final int iActif = x / (this.aWidth / this.iHom);
                this.onOpen = false;
                if (this.iActif != iActif) {
                    this.iActif = iActif;
                    if (((rd_l)this.dList.elementAt(iActif)).iE != 2) {
                        this.p.showStatus(this.dList.elementAt(this.iActif).sH2);
                    }
                    b = true;
                }
            }
            else if (n < this.iMax) {
                final int intValue = this.iList.elementAt(n);
                if (x > ((rd_l)this.dList.elementAt(intValue)).iN * this.iT * 2 / 3 + this.iT) {
                    if (this.onOpen) {
                        this.onOpen = false;
                        b = true;
                    }
                }
                else if (((rd_l)this.dList.elementAt(intValue)).iS != -1) {
                    if (!this.onOpen) {
                        this.onOpen = true;
                        b = true;
                    }
                }
                else {
                    this.onOpen = false;
                }
                if (this.iActif != intValue) {
                    this.iActif = intValue;
                    this.p.showStatus(this.dList.elementAt(this.iActif).sH2);
                    b = true;
                }
            }
            else {
                this.iActif = -1;
                this.p.showStatus("");
                b = true;
            }
        }
        else if (this.iActif != -99) {
            this.iActif = -99;
            this.p.showStatus(this.sURL);
            b = true;
        }
        if (b) {
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.bOK == 0 && this.onPres && this.onButt && !this.onOpen) {
            this.goURL();
        }
        final int n = -1;
        this.iDrag = n;
        this.xDrag = n;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = (y - this.iPas) / this.iT;
        if (this.bOK > 0) {
            return;
        }
        final int n2 = y;
        this.iDrag = n2;
        this.xDrag = n2;
        if (this.iNext + this.iCur > 0 && x > this.aWidth - 15) {
            int n3 = 0;
            if (this.iHom > 0) {
                n3 = -1;
            }
            if (this.iCur > 0 && y > this.iPas) {
                if (y - this.iPas < 13) {
                    --this.iCur;
                    this.iSk = -1;
                }
                else if (2 * (y - this.iPas) < this.aHeight) {
                    this.iCur -= 5;
                    this.iSk = -1;
                }
                if (this.iCur < 0) {
                    this.iCur = 0;
                }
            }
            if (this.iNext > 0) {
                if (y > this.aHeight - 13 + n3 * this.iT) {
                    ++this.iCur;
                    this.iSk = 1;
                }
                else if (2 * (y - this.iPas) >= this.aHeight) {
                    this.iCur += 5;
                    this.iSk = 1;
                }
            }
            this.repaint();
            return;
        }
        Label_0595: {
            if (n < this.iMax || (y > this.aHeight - this.iT - 3 && this.iHom > 0)) {
                this.onPres = true;
                if (y > this.aHeight - this.iT - 3 && this.iHom > 0) {
                    this.iActif = x / (this.aWidth / this.iHom);
                }
                else if (y > this.iPas) {
                    this.iActif = (int)this.iList.elementAt(n);
                }
                if ((this.onOpen && y > this.iPas) || (this.iActif > -1 && this.dList.elementAt(this.iActif).iE != -1 && this.dList.elementAt(this.iActif).sURL == null)) {
                    if (this.song[0] != null) {
                        this.song[0].play();
                    }
                    this.dList.elementAt(this.iActif).open_close();
                    if (this.AutoClose) {
                        this.forceClose();
                    }
                    this.clickDest = null;
                }
                else {
                    String s;
                    if (y > this.iPas) {
                        s = this.dList.elementAt(this.iActif).sURL;
                        if (this.dList.elementAt(this.iActif).iE == 2) {
                            s = null;
                        }
                    }
                    else {
                        s = this.sURL;
                    }
                    if (s != null) {
                        try {
                            if (s.charAt(0) == '.') {
                                this.clickDest = new URL(this.p.getCodeBase(), s);
                                break Label_0595;
                            }
                            this.clickDest = new URL(s);
                            break Label_0595;
                        }
                        catch (Exception ex) {
                            return;
                        }
                    }
                    this.clickDest = null;
                }
            }
        }
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.onButt = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.onPres = false;
        this.onButt = false;
        this.iActif = -1;
        this.p.showStatus("");
        this.repaint();
    }
    
    void goURL() {
        if (this.clickDest != null || this.dList.elementAt(this.iActif).sTarg.equals("_script") || (this.bLoc && this.dList.elementAt(this.iActif).sTarg.equals("_reload")) || (this.bLoc && this.dList.elementAt(this.iActif).sTarg.equals("_insert")) || this.dList.elementAt(this.iActif).sTarg.equals("_prev")) {
            this.iXX = -1;
            if (this.iActif > -1) {
                if (this.song[1] != null) {
                    this.song[1].play();
                }
                if (this.dList.elementAt(this.iActif).sTarg.equals("_insert")) {
                    this.rFile(this.dList.elementAt(this.iActif).sURL, 2);
                    this.dList.elementAt(this.iActif).i3 = 1;
                    this.iXX = this.iActif;
                }
                else if (this.dList.elementAt(this.iActif).sTarg.equals("_prev")) {
                    if (this.nV > 0) {
                        this.rFile(this.vFile.elementAt(0), 1);
                        this.vFile.removeElementAt(0);
                        --this.nV;
                    }
                }
                else if (this.dList.elementAt(this.iActif).sTarg.equals("_reload")) {
                    this.vFile.insertElementAt(this.sFile, 0);
                    this.rFile(this.dList.elementAt(this.iActif).sURL, 1);
                    ++this.nV;
                }
                else if (this.dList.elementAt(this.iActif).sTarg.equals("_script")) {
                    try {
                        JSObject.getWindow((Applet)this.p).eval(this.dList.elementAt(this.iActif).sURL);
                        this.iXX = this.iActif;
                    }
                    catch (Exception ex) {}
                }
                else {
                    if (this.dList.elementAt(this.iActif).iN != 98) {
                        this.dList.elementAt(this.iActif).i3 = 1;
                    }
                    this.iXX = this.iActif;
                    this.p.getAppletContext().showDocument(this.clickDest, this.dList.elementAt(this.iActif).sTarg);
                }
            }
            else {
                this.p.getAppletContext().showDocument(this.clickDest, this.m_targ);
            }
        }
        this.onPres = false;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public synchronized void update(final Graphics graphics) {
        this.p.validate();
        Label_0111: {
            if (this.aWidth >= 2 && this.aWidth == this.getSize().width) {
                if (this.aHeight == this.getSize().height) {
                    break Label_0111;
                }
            }
            try {
                this.aWidth = this.getSize().width;
                this.aHeight = this.getSize().height;
                this.buffer = this.createImage(this.aWidth, this.aHeight);
                this.offScreen = this.buffer.getGraphics();
            }
            catch (Exception ex) {
                this.offScreen = null;
            }
            this.bgWidth = -1;
            this.bgHeight = -1;
        }
        if (this.offScreen != null) {
            this.paintApplet(this.offScreen);
            graphics.drawImage(this.buffer, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        if (this.bOK > 0) {
            return;
        }
        graphics.setColor(new Color(this.m_bgc));
        graphics.fillRect(0, 0, this.aWidth, this.aHeight);
        int i = 0;
        if (this.bgimg != null) {
            if (this.bgWidth == -1 || this.bgHeight == -1) {
                this.bgWidth = this.bgimg.getWidth(this);
                this.bgHeight = this.bgimg.getHeight(this);
            }
            if (this.bgWidth != -1 && this.bgHeight != -1) {
                while (i < this.aHeight) {
                    for (int j = 0; j < this.aWidth; j += this.bgWidth) {
                        graphics.drawImage(this.bgimg, j, i, this);
                    }
                    i += this.bgHeight;
                }
            }
        }
        this.display(graphics);
    }
    
    private void readFile() {
        int iActif = this.iActif;
        int n = 0;
        if (this.bOK == 1) {
            this.iMax = 0;
            this.iActif = -1;
            this.iCur = 0;
            this.iPas = 0;
            this.iHom = 0;
            this.dList.removeAllElements();
        }
        else {
            n = this.dList.elementAt(iActif).iN + 1;
            ((rd_l)this.dList.elementAt(iActif)).iS = 1;
            ((rd_l)this.dList.elementAt(iActif)).sURL = null;
            ((rd_l)this.dList.elementAt(iActif)).sTarg = "";
        }
        BufferedReader bufferedReader = null;
        if (!this.bLoc) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.p.getCodeBase(), this.sFile).openStream(), this.sEnc));
            }
            catch (UnsupportedEncodingException ex) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.p.getCodeBase(), this.sFile).openStream()));
                }
                catch (Exception ex2) {
                    bufferedReader = null;
                    this.bLoc = true;
                }
            }
            catch (Exception ex3) {
                bufferedReader = null;
                this.bLoc = true;
            }
        }
        int n2 = 0;
        rd_l rd_l;
        while (true) {
            rd_l = new rd_l();
            String s;
            if (this.bLoc) {
                s = this.p.getParameter(String.valueOf(this.sFile) + n2);
                ++n2;
            }
            else {
                try {
                    s = bufferedReader.readLine();
                }
                catch (Exception ex4) {
                    break;
                }
            }
            if (s != null) {
                for (int i = 0; i < this.mV; ++i) {
                    final int index = s.indexOf("$" + i);
                    if (index > -1) {
                        s = new String(String.valueOf(s.substring(0, index)) + this.sV[i] + s.substring(index + 2));
                    }
                }
            }
            if (!rd_l.get(rd_l, s, this.m_targ, this.cSep)) {
                break;
            }
            if (rd_l.iN == 99) {
                this.simg = this.p.getImage(this.p.getCodeBase(), rd_l.sDesc[0]);
                this.trk.addImage(this.simg, 99);
                this.iPas = 1;
                this.sURL = rd_l.sURL;
                if (rd_l.sH != null) {
                    this.sH = rd_l.sH;
                }
                else {
                    this.sH = null;
                }
            }
            else {
                if (rd_l.iN == -1) {
                    continue;
                }
                if (rd_l.iN == 98) {
                    ++this.iHom;
                }
                else {
                    if (rd_l.i3 > 0 && rd_l.i3 < this.nbFont) {
                        rd_l.iF = rd_l.i3;
                    }
                    rd_l.i3 = 0;
                }
                if (rd_l.sVal.length() > 5 && rd_l.sVal.compareTo(this.sDate) > 0) {
                    rd_l.i3 = 2;
                }
                if (this.bOK == 1) {
                    this.dList.addElement(rd_l);
                }
                else {
                    rd_l.iN += n;
                    this.dList.insertElementAt(rd_l, ++iActif);
                }
            }
        }
        if (!this.bReg && this.bOK < 2) {
            rd_l.sDesc[0] = new String("About tListe");
            rd_l.sURL = new String("http://www.javaside.com/j_tliste.html");
            rd_l.sTarg = new String("_top");
            rd_l.iN = 0;
            rd_l.iS = -1;
            rd_l.iE = 5;
            this.dList.addElement(rd_l);
        }
        this.initBar();
        try {
            this.trk.waitForAll();
        }
        catch (InterruptedException ex5) {}
        this.loadIcon();
        if (this.iPas > 0) {
            this.iPas = this.simg.getHeight(this);
        }
        this.bOK = 0;
        this.setVisible(true);
        this.repaint();
    }
    
    private void forceClose() {
        int i = this.dList.size() - 1;
        int n = 0;
        int in = 0;
        while (i >= 0) {
            final rd_l rd_l = this.dList.elementAt(i);
            if (i > this.iActif) {
                if (rd_l.iS == 1) {
                    rd_l.iS = 0;
                }
            }
            else {
                if (i == this.iActif) {
                    in = rd_l.iN;
                }
                else if (rd_l.iS == 1 && (n != 0 || rd_l.iN == in)) {
                    rd_l.iS = 0;
                }
                if (rd_l.iN < 1) {
                    n = 1;
                }
            }
            --i;
        }
    }
    
    private void initBar() {
        int i = this.dList.size() - 1;
        int n = 0;
        int in = 0;
        int n2 = 99;
        while (i >= 0) {
            if (in == 0) {
                n2 = 99;
            }
            if (this.dList.elementAt(i).iN > in) {
                this.dList.elementAt(i).i4 = 1 + n + (1 << this.dList.elementAt(i).iN);
                n += 1 << this.dList.elementAt(i).iN;
                n2 = this.dList.elementAt(i).iN;
            }
            else if (this.dList.elementAt(i).iN == in) {
                this.dList.elementAt(i).i4 = n;
            }
            else {
                if (n2 != 99 && this.dList.elementAt(i).iS == -1) {
                    ((rd_l)this.dList.elementAt(i)).iS = (((rd_l)this.dList.elementAt(i)).iE & 0x1);
                    final rd_l rd_l = this.dList.elementAt(i);
                    rd_l.iE -= this.dList.elementAt(i).iS;
                }
                n -= 1 << in;
                this.dList.elementAt(i).i4 = n;
                if (this.dList.elementAt(i).iN != n2 && this.dList.elementAt(i).iN > 0 && (n & 1 << this.dList.elementAt(i).iN) != 1 << this.dList.elementAt(i).iN) {
                    n2 = this.dList.elementAt(i).iN;
                    n += 1 << in - 1;
                    this.dList.elementAt(i).i4 = 1 + n;
                }
            }
            in = this.dList.elementAt(i).iN;
            --i;
        }
    }
    
    public void display(final Graphics graphics) {
        int n = 0;
        final Enumeration<Object> elements = (Enumeration<Object>)this.dList.elements();
        this.iPos = 1;
        this.iList.removeAllElements();
        this.iMax = 0;
        if (this.iPas > 0 && this.simg != null) {
            graphics.drawImage(this.simg, (this.aWidth - this.simg.getWidth(this)) / 2, 1, this);
            if (this.iActif == -99) {
                graphics.setColor(new Color(12632319));
                this.yPos = 5;
                this.xPos = 20;
                graphics.draw3DRect((this.aWidth - this.simg.getWidth(this)) / 2, 1, this.simg.getWidth(this), this.iPas, !this.onPres);
                graphics.draw3DRect(1 + (this.aWidth - this.simg.getWidth(this)) / 2, 2, this.simg.getWidth(this) - 2, this.iPas - 2, !this.onPres);
            }
        }
        int n2 = 0;
        this.iNext = 0;
        if (this.iHom > 0) {
            n2 = -1;
        }
        while (elements.hasMoreElements()) {
            if ((this.iPos - this.iCur - n2) * this.iT <= this.aHeight - this.iPas) {
                this.drawEl(graphics, n++, elements.nextElement());
            }
            else {
                this.iNext += this.isEl(n++, elements.nextElement());
            }
        }
        this.bForce = -1;
        if (this.iNext + this.iCur > 0) {
            if (this.img[89] != null) {
                graphics.drawImage(this.img[89], this.aWidth - 14, 1 + this.iPas, 14, this.aHeight - 13 + n2 * this.iT - this.iPas, this);
            }
            if (this.img[87] != null) {
                graphics.drawImage(this.img[87], this.aWidth - 14, this.aHeight - 12 + n2 * this.iT, this);
            }
            if (this.img[88] != null) {
                graphics.drawImage(this.img[88], this.aWidth - 14, 1 + this.iPas, this);
            }
            final int n3 = (this.aHeight - 35 - this.iPas + n2 * this.iT) / (this.iCur + this.iMax + this.iNext);
            int n4 = (this.iMax + 1) * n3;
            if (this.iNext == 0) {
                n4 = this.aHeight + n2 * this.iT - (27 + this.iPas + this.iCur * n3);
            }
            if (this.img[86] != null) {
                graphics.drawImage(this.img[86], this.aWidth - 14, 14 + this.iPas + this.iCur * n3 + this.xDrag - this.iDrag, 14, n4, this);
            }
        }
        String s = null;
        if (this.iActif > -1 || this.iActif == -99) {
            if (this.iActif > -1 && this.dList.elementAt(this.iActif).sH != null) {
                s = new String(this.dList.elementAt(this.iActif).sH.substring(1).trim());
            }
            if (this.iActif == -99 && this.sH != null) {
                s = new String(this.sH.substring(1).trim());
            }
        }
        if (s != null) {
            final String[] array = new String[10];
            int n5 = 0;
            int n6 = 0;
            array[n5] = new String(s);
            int lastIndex = 0;
            while (true) {
                int i = this.wMetrics.stringWidth(array[n5]);
                if (i > this.aWidth - 25) {
                    while (i > this.aWidth - 25) {
                        lastIndex = array[n5].lastIndexOf(32);
                        array[n5] = new String(s.substring(0, lastIndex).trim());
                        i = this.wMetrics.stringWidth(array[n5]);
                        if (lastIndex >= 0) {
                            continue;
                        }
                        break;
                    }
                    if (n6 < i) {
                        n6 = i;
                    }
                    if (lastIndex < 0) {
                        break;
                    }
                    ++n5;
                    array[n5] = new String(s.substring(lastIndex).trim());
                    s = new String(s.substring(lastIndex).trim());
                }
                else {
                    if (n6 < i) {
                        n6 = i;
                        break;
                    }
                    break;
                }
            }
            final int n7 = this.wMetrics.getHeight() * (n5 + 1);
            graphics.setColor(this.cFont[1]);
            int n8;
            if (this.yPos + n7 + 10 > this.aHeight) {
                n8 = this.yPos - n7 - this.iT - 3;
            }
            else {
                n8 = this.yPos + 3;
            }
            if (this.xPos + n6 + 5 > this.aWidth) {
                this.xPos = this.aWidth - n6 - 5;
            }
            graphics.fillRect(this.xPos, n8, n6 + 3, n7 + 5);
            graphics.setColor(new Color(0));
            graphics.drawRect(this.xPos, n8, n6 + 3, n7 + 5);
            final int n9 = n8;
            graphics.setColor(this.cFont[0]);
            graphics.setFont(this.wFont[0]);
            for (int j = 0; j <= n5; ++j) {
                graphics.drawString(array[j], this.xPos + 2, n9 + this.wMetrics.getHeight() * (j + 1));
            }
        }
    }
    
    private void drawEl(final Graphics graphics, final int n, final Object o) {
        int n2 = this.iPos - this.iCur;
        final rd_l rd_l = (rd_l)o;
        if (this.bForce > -1 && rd_l.iS != -1) {
            rd_l.iS = this.bForce;
        }
        if (rd_l.iN < this.iOpen) {
            this.iOpen = rd_l.iN;
        }
        if (rd_l.iN == this.iOpen && rd_l.iS == 1) {
            ++this.iOpen;
        }
        if (rd_l.iN == 98) {
            if (this.img[rd_l.iE] != null && rd_l.iE != 2) {
                graphics.drawImage(this.img[rd_l.iE], this.aWidth / this.iHom * rd_l.i3 + (this.aWidth / this.iHom - this.img[rd_l.iE].getWidth(this)) / 2, this.aHeight - this.iT + 1, this);
                if (n == this.iActif) {
                    graphics.setColor(new Color(12632319));
                    graphics.draw3DRect(this.aWidth / this.iHom * rd_l.i3, this.aHeight - this.iT, this.aWidth / this.iHom - 2, this.iT - 2, !this.onPres);
                    this.yPos = this.aHeight - 5;
                    this.xPos = this.aWidth / this.iHom * rd_l.i3;
                }
            }
        }
        else if (this.iOpen >= rd_l.iN) {
            if (n2 > 0) {
                if (this.iSk != 0) {
                    if (n2 > 1) {
                        this.iCur += (n2 - 1) * this.iSk;
                        n2 = 1;
                    }
                    this.iSk = 0;
                }
                for (int i = 0; i < rd_l.iL; ++i) {
                    this.iList.addElement(new Integer(n));
                }
                this.iMax += rd_l.iL;
                if (n == this.iActif) {
                    graphics.setColor(new Color(12632319));
                    if (this.onOpen) {
                        graphics.draw3DRect(2 + this.iT * rd_l.iN * 2 / 3, 5 + this.iT * (n2 - 1) + this.iPas, this.iT, this.iT, true);
                    }
                    else if (rd_l.iE != 2 && this.iPress == 1) {
                        graphics.draw3DRect(3 + this.iT * (1 + 2 * (1 + rd_l.iN)) / 3, 5 + this.iT * (n2 - 1) + this.iPas, this.aWidth - this.iT * (1 + 2 * (1 + rd_l.iN)) / 3 - 6, this.iT * rd_l.iL, !this.onPres);
                    }
                    this.yPos = 5 + this.iT * n2 + this.iPas;
                    this.xPos = 20;
                }
                if (rd_l.i3 != 1) {
                    graphics.setColor(this.cFont[rd_l.iF * 2]);
                }
                else {
                    graphics.setColor(this.cFont[rd_l.iF * 2 + 1]);
                }
                if (n == this.iActif && !this.onOpen && (this.iPress == 2 || this.iPress == 4)) {
                    graphics.setColor(new Color(this.cPress));
                }
                final int n3 = this.iT * rd_l.iN * 2 / 3 + this.iT * 14 / 10;
                final int n4 = this.iT * n2 + 1 + this.iPas;
                graphics.setFont(this.wFont[rd_l.iF]);
                final FontMetrics fontMetrics = this.p.getFontMetrics(this.wFont[rd_l.iF]);
                for (int j = 0; j < rd_l.iL; ++j) {
                    graphics.drawString(rd_l.sDesc[j], n3, n4 + this.iT * j);
                    if (n == this.iActif && !this.onOpen && this.iPress > 2) {
                        graphics.drawLine(n3, n4 + 2, n3 + fontMetrics.stringWidth(rd_l.sDesc[j]), n4 + 2);
                    }
                }
                if (this.img2[1] != null && rd_l.iN > 0) {
                    int i2 = rd_l.i4;
                    int k = rd_l.iN;
                    int n5 = 2;
                    if (rd_l.iS == 0) {
                        n5 = 6;
                    }
                    if (rd_l.iS == 1) {
                        n5 = 4;
                    }
                    if ((i2 & 0x1) == 0x1) {
                        ++n5;
                        --i2;
                    }
                    int n6 = i2 - (1 << k);
                    --k;
                    if (this.img2[n5] != null) {
                        graphics.drawImage(this.img2[n5], 3 + this.iT * k * 2 / 3, 6 + this.iT * (n2 - 1) + this.iPas, this);
                    }
                    if ((n5 & 0x1) == 0x0) {
                        for (int l = 1; l < rd_l.iL; ++l) {
                            graphics.drawImage(this.img2[1], 3 + this.iT * k * 2 / 3, 6 + this.iT * (n2 - 1) + this.iPas + this.iT * l, this);
                        }
                    }
                    while (k > 0) {
                        if (n6 >= 1 << k) {
                            for (int n7 = 0; n7 < rd_l.iL; ++n7) {
                                graphics.drawImage(this.img2[1], 3 + this.iT * (k - 1) * 2 / 3, 6 + this.iT * (n2 - 1) + this.iPas + this.iT * n7, this);
                            }
                            n6 -= 1 << k;
                        }
                        --k;
                    }
                }
                int ie = rd_l.iE;
                if (rd_l.iS != -1) {
                    ie += rd_l.iS;
                }
                if (this.img[ie] != null) {
                    graphics.drawImage(this.img[ie], 3 + this.iT * rd_l.iN * 2 / 3, 6 + this.iT * (n2 - 1) + this.iPas, this);
                }
                if (rd_l.iS == 1 && rd_l.iL > 1) {
                    for (int n8 = 1; n8 < rd_l.iL; ++n8) {
                        graphics.drawImage(this.img2[1], 3 + this.iT * rd_l.iN * 2 / 3, 6 + this.iT * (n2 - 1) + this.iPas + this.iT * n8, this);
                    }
                }
                if (rd_l.i3 == 2) {
                    if (this.img2[0] != null) {
                        graphics.drawImage(this.img2[0], this.aWidth - 25, 7 + this.iT * (n2 - 1) + this.iPas, this);
                    }
                }
                else if (n == this.iXX && rd_l.iN != 98 && this.img2[8] != null) {
                    graphics.drawImage(this.img2[8], this.aWidth - 25, 7 + this.iT * (n2 - 1) + this.iPas, this);
                }
            }
            this.iPos += rd_l.iL;
        }
    }
    
    private int isEl(final int n, final Object o) {
        final rd_l rd_l = (rd_l)o;
        if (this.bForce > -1 && rd_l.iS != -1) {
            rd_l.iS = this.bForce;
        }
        if (rd_l.iN < this.iOpen) {
            this.iOpen = rd_l.iN;
        }
        if (rd_l.iN == this.iOpen && rd_l.iS == 1) {
            ++this.iOpen;
        }
        if (rd_l.iN != 98 && rd_l.iN != 99 && this.iOpen >= rd_l.iN) {
            return 1;
        }
        return 0;
    }
}
