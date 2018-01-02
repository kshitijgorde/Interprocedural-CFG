import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class finemenu extends Applet implements Runnable
{
    private String Demotxt;
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private int WinWidth;
    private int WinHeight;
    private String theBackground;
    private String theBgColor;
    private String[] theItemIm0;
    private String[] theItemIm1;
    private String theItemXi;
    private String theItemXf;
    private String theItemYi;
    private String theItemYf;
    private String theItemAlign;
    private String theItemTrajectory;
    private String theItemNbpts;
    private String theTempoFrame;
    private String theTokenFrame;
    private String theFps;
    private String theReg;
    private boolean RegOk;
    private String DefaultAlign;
    private String DefaultTrajectory;
    private String DefaultNbpts;
    private String DefaultFps;
    private String DefaultTempoFrame;
    private String DefaultTokenFrame;
    private String Vide;
    private boolean LoadIm;
    private boolean LoadTrackMenu;
    private boolean Load;
    private boolean Sinit;
    private int CurrentActive;
    private int MaxItem;
    private int ItemNb;
    private Image Background;
    private Color BgColor;
    private boolean BgColorOnly;
    private Image[] ItemIm0;
    private Image[] ItemIm1;
    private int[] ItemXi;
    private int[] ItemXf;
    private int[] ItemYi;
    private int[] ItemYf;
    private int[] ItemAlign;
    private int[] ItemTraj;
    private int[] ItemNbpts;
    private String[] ItemTargetPage;
    private String[] ItemTargetFrame;
    private m_item[] Items;
    private int Tempo;
    private int TempoTime;
    private int TempoSeuil;
    private int Token;
    private int TokenFrame;
    private int TokenInit;
    private m_banner[] Banner;
    private int NbBan;
    private boolean LoadTrackBan;
    private m_text[] Texte;
    private int NbTexte;
    private Thread myThread;
    private int FrameNumber;
    private int SleepTime;
    private String RegUrl;
    private String ToReg;
    private int[] encryptKeys;
    private int[] decryptKeys;
    private int[] tempInts;
    private static byte[] bytebit;
    private static int[] bigbyte;
    private static byte[] pc1;
    private static int[] totrot;
    private static byte[] pc2;
    private static int[] SP1;
    private static int[] SP2;
    private static int[] SP3;
    private static int[] SP4;
    private static int[] SP5;
    private static int[] SP6;
    private static int[] SP7;
    private static int[] SP8;
    
    public void init() {
        this.WinWidth = this.size().width;
        this.WinHeight = this.size().height;
        this.offScreenImage = this.createImage(this.WinWidth, this.WinHeight);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
    }
    
    public void start() {
        if (this.myThread == null) {
            this.myThread = new Thread(this);
            this.FrameNumber = 0;
            this.myThread.start();
        }
    }
    
    public void stop() {
        if (this.myThread != null) {
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    public void run() {
        while (true) {
            ++this.FrameNumber;
            this.repaint();
            try {
                Thread.sleep(this.SleepTime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreenImage != null) {
            graphics.drawImage(this.offScreenImage, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.Load) {
            this.offScreenGraphics.setColor(Color.white);
            this.offScreenGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
            new m_text(this.WinWidth, this.WinHeight, "Loading ...", this.Vide, this.Vide, "1", this.Vide, this.Vide, this.Vide, this.Vide, "000000", "000000", this.Vide, this.Vide, this.Vide, this.Vide).Progress(this.offScreenGraphics);
            this.Sinit = true;
            this.Load = false;
        }
        else if (this.Sinit) {
            this.sous_init();
            this.Sinit = false;
        }
        else if (!this.LoadTrackMenu) {
            final m_text text = new m_text(this.WinWidth, this.WinHeight, "FineMenu V1.0#Error While Loading#Menu Images ...#Check Filenames", this.Vide, this.Vide, "3", this.Vide, this.Vide, this.Vide, this.Vide, "FFFFFF", "FFFFFF", this.Vide, this.Vide, this.Vide, this.Vide);
            this.offScreenGraphics.setColor(Color.black);
            this.offScreenGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
            text.Progress(this.offScreenGraphics);
        }
        else if (!this.LoadTrackBan) {
            final m_text text2 = new m_text(this.WinWidth, this.WinHeight, "FineMenu V1.0#Error While Loading#Banner Images ...#Check Filenames", this.Vide, this.Vide, "3", this.Vide, this.Vide, this.Vide, this.Vide, "FFFFFF", "FFFFFF", this.Vide, this.Vide, this.Vide, this.Vide);
            this.offScreenGraphics.setColor(Color.black);
            this.offScreenGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
            text2.Progress(this.offScreenGraphics);
        }
        else if (!this.RegOk && this.getCodeBase().getProtocol().equals("http")) {
            final m_text text3 = new m_text(this.WinWidth, this.WinHeight, "FineMenu V1.0#RegCode Missing or Invalid !", this.Vide, this.Vide, "2", this.Vide, this.Vide, this.Vide, this.Vide, "FFFFFF", "FFFFFF", this.Vide, this.Vide, this.Vide, this.Vide);
            this.offScreenGraphics.setColor(Color.black);
            this.offScreenGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
            text3.Progress(this.offScreenGraphics);
        }
        else {
            if (!this.BgColorOnly) {
                this.offScreenGraphics.drawImage(this.Background, 0, 0, this.WinWidth, this.WinHeight, this);
            }
            else {
                this.offScreenGraphics.setColor(this.BgColor);
                this.offScreenGraphics.fillRect(0, 0, this.WinWidth, this.WinHeight);
            }
            for (int i = 0; i < this.NbBan; ++i) {
                this.Banner[i].Progress();
                this.offScreenGraphics.drawImage(this.Banner[i].getCurrentImage(), this.Banner[i].getX(), this.Banner[i].getY(), this.Banner[i].getW(), this.Banner[i].getH(), this);
            }
            for (int j = 1; j <= this.ItemNb; ++j) {
                if (j != this.CurrentActive) {
                    this.offScreenGraphics.drawImage(this.Items[j].getIm(), this.Items[j].getX(), this.Items[j].getY(), this.Items[j].getW(), this.Items[j].getH(), this);
                }
                if (!this.Items[j].getActivity()) {
                    ++this.Tempo;
                }
                else {
                    this.Tempo = 0;
                }
                this.Items[j].automat();
            }
            if (this.CurrentActive != 0) {
                final int currentActive = this.CurrentActive;
                this.offScreenGraphics.drawImage(this.Items[currentActive].getIm(), this.Items[currentActive].getX(), this.Items[currentActive].getY(), this.Items[currentActive].getW(), this.Items[currentActive].getH(), this);
            }
            if (this.Tempo >= this.ItemNb || this.TempoTime == this.TempoSeuil) {
                if (this.TempoTime < this.TempoSeuil) {
                    ++this.TempoTime;
                }
                else {
                    this.Items[this.Token].setActive();
                    if (this.TokenFrame > 0) {
                        --this.TokenFrame;
                    }
                    else {
                        this.Items[this.Token].setInActive();
                        this.TokenFrame = this.TokenInit;
                        if (this.Token < this.ItemNb) {
                            ++this.Token;
                        }
                        else {
                            this.Token = 1;
                        }
                    }
                }
            }
            for (int k = 0; k < this.NbTexte; ++k) {
                this.Texte[k].Progress(this.offScreenGraphics);
            }
        }
        this.paint(graphics);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.CurrentActive != 0) {
            final int currentActive = this.CurrentActive;
            if (n > this.Items[currentActive].getX() && n < this.Items[currentActive].getX() + this.Items[currentActive].getW() && n2 > this.Items[currentActive].getY() && n2 < this.Items[currentActive].getY() + this.Items[currentActive].getH()) {
                this.Items[currentActive].setActive();
                for (int i = 1; i <= this.ItemNb; ++i) {
                    if (i != currentActive) {
                        this.Items[i].setInActive();
                    }
                }
                this.TempoTime = 0;
            }
            else if (this.TempoTime < this.TempoSeuil) {
                this.Items[currentActive].setInActive();
                this.CurrentActive = 0;
            }
        }
        else {
            for (int j = 1; j <= this.ItemNb; ++j) {
                if (n > this.Items[j].getX() && n < this.Items[j].getX() + this.Items[j].getW() && n2 > this.Items[j].getY() && n2 < this.Items[j].getY() + this.Items[j].getH()) {
                    this.Items[j].setActive();
                    this.CurrentActive = j;
                    this.TempoTime = 0;
                }
                else if (this.TempoTime < this.TempoSeuil) {
                    this.Items[j].setInActive();
                }
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.CurrentActive != 0) {
            final int currentActive = this.CurrentActive;
            if (n > this.Items[currentActive].getX() && n < this.Items[currentActive].getX() + this.Items[currentActive].getW() && n2 > this.Items[currentActive].getY() && n2 < this.Items[currentActive].getY() + this.Items[currentActive].getH()) {
                this.Items[currentActive].setActive();
                try {
                    final String targetPage = this.Items[currentActive].getTargetPage();
                    final String targetFrame = this.Items[currentActive].getTargetFrame();
                    int n3 = 0;
                    int i = 0;
                    int n4 = 0;
                    if (targetPage != null) {
                        while (i != -1) {
                            i = targetPage.indexOf(";", n3);
                            String s;
                            if (i == -1) {
                                s = targetPage.substring(n3, targetPage.length());
                            }
                            else {
                                s = targetPage.substring(n3, i);
                            }
                            n3 = i + 1;
                            final URL url = new URL(this.getDocumentBase(), s);
                            if (targetFrame != null) {
                                final int index = targetFrame.indexOf(";", n4);
                                String s2;
                                if (index == -1) {
                                    s2 = targetFrame.substring(n4, targetFrame.length());
                                }
                                else {
                                    s2 = targetFrame.substring(n4, index);
                                    n4 = index + 1;
                                }
                                this.getAppletContext().showDocument(url, s2);
                            }
                            else {
                                this.getAppletContext().showDocument(url);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                this.CurrentActive = currentActive;
                for (int j = 1; j <= this.ItemNb; ++j) {
                    if (j != currentActive) {
                        this.Items[j].setInActive();
                    }
                }
                this.TempoTime = 0;
            }
            else if (this.TempoTime < this.TempoSeuil) {
                this.Items[currentActive].setInActive();
                this.CurrentActive = 0;
            }
        }
        else {
            for (int k = 1; k <= this.ItemNb; ++k) {
                if (n > this.Items[k].getX() && n < this.Items[k].getX() + this.Items[k].getW() && n2 > this.Items[k].getY() && n2 < this.Items[k].getY() + this.Items[k].getH()) {
                    this.Items[k].setActive();
                    try {
                        final String targetPage2 = this.Items[k].getTargetPage();
                        final String targetFrame2 = this.Items[k].getTargetFrame();
                        int n5 = 0;
                        int l = 0;
                        int n6 = 0;
                        if (targetPage2 != null) {
                            while (l != -1) {
                                l = targetPage2.indexOf(";", n5);
                                String s3;
                                if (l == -1) {
                                    s3 = targetPage2.substring(n5, targetPage2.length());
                                }
                                else {
                                    s3 = targetPage2.substring(n5, l);
                                }
                                n5 = l + 1;
                                final URL url2 = new URL(this.getDocumentBase(), s3);
                                if (targetFrame2 != null) {
                                    final int index2 = targetFrame2.indexOf(";", n6);
                                    String s4;
                                    if (index2 == -1) {
                                        s4 = targetFrame2.substring(n6, targetFrame2.length());
                                    }
                                    else {
                                        s4 = targetFrame2.substring(n6, index2);
                                        n6 = index2 + 1;
                                    }
                                    this.getAppletContext().showDocument(url2, s4);
                                }
                                else {
                                    this.getAppletContext().showDocument(url2);
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {}
                    this.TempoTime = 0;
                }
                else if (this.TempoTime < this.TempoSeuil) {
                    this.Items[k].setInActive();
                }
            }
        }
        return true;
    }
    
    private void crypto(final byte[] key) {
        this.setKey(key);
    }
    
    private void setKey(final byte[] array) {
        this.deskey(array, true, this.encryptKeys);
        this.deskey(array, false, this.decryptKeys);
    }
    
    private void deskey(final byte[] array, final boolean b, final int[] array2) {
        final int[] array3 = new int[56];
        final int[] array4 = new int[56];
        final int[] array5 = new int[32];
        for (int i = 0; i < 56; ++i) {
            final byte b2 = finemenu.pc1[i];
            array3[i] = (((array[b2 >>> 3] & finemenu.bytebit[b2 & 0x7]) != 0x0) ? 1 : 0);
        }
        for (int j = 0; j < 16; ++j) {
            int n;
            if (b) {
                n = j << 1;
            }
            else {
                n = 15 - j << 1;
            }
            final int n2 = n + 1;
            array5[n] = (array5[n2] = 0);
            for (int k = 0; k < 28; ++k) {
                final int n3 = k + finemenu.totrot[j];
                if (n3 < 28) {
                    array4[k] = array3[n3];
                }
                else {
                    array4[k] = array3[n3 - 28];
                }
            }
            for (int l = 28; l < 56; ++l) {
                final int n4 = l + finemenu.totrot[j];
                if (n4 < 56) {
                    array4[l] = array3[n4];
                }
                else {
                    array4[l] = array3[n4 - 28];
                }
            }
            for (int n5 = 0; n5 < 24; ++n5) {
                if (array4[finemenu.pc2[n5]] != 0) {
                    final int[] array6 = array5;
                    final int n6 = n;
                    array6[n6] |= finemenu.bigbyte[n5];
                }
                if (array4[finemenu.pc2[n5 + 24]] != 0) {
                    final int[] array7 = array5;
                    final int n7 = n2;
                    array7[n7] |= finemenu.bigbyte[n5];
                }
            }
        }
        this.cookey(array5, array2);
    }
    
    private void cookey(final int[] array, final int[] array2) {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < 16) {
            final int n3 = array[n++];
            final int n4 = array[n++];
            array2[n2] = (n3 & 0xFC0000) << 6;
            final int n5 = n2;
            array2[n5] |= (n3 & 0xFC0) << 10;
            final int n6 = n2;
            array2[n6] |= (n4 & 0xFC0000) >>> 10;
            final int n7 = n2;
            array2[n7] |= (n4 & 0xFC0) >>> 6;
            ++n2;
            array2[n2] = (n3 & 0x3F000) << 12;
            final int n8 = n2;
            array2[n8] |= (n3 & 0x3F) << 16;
            final int n9 = n2;
            array2[n9] |= (n4 & 0x3F000) >>> 4;
            final int n10 = n2;
            array2[n10] |= (n4 & 0x3F);
            ++n2;
            ++i;
        }
    }
    
    private void encrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        squashBytesToInts(array, n, this.tempInts, 0, 2);
        this.des(this.tempInts, this.tempInts, this.encryptKeys);
        spreadIntsToBytes(this.tempInts, 0, array2, n2, 2);
    }
    
    private void decrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        squashBytesToInts(array, n, this.tempInts, 0, 2);
        this.des(this.tempInts, this.tempInts, this.decryptKeys);
        spreadIntsToBytes(this.tempInts, 0, array2, n2, 2);
    }
    
    private void des(final int[] array, final int[] array2, final int[] array3) {
        int n = 0;
        final int n2 = array[0];
        final int n3 = array[1];
        final int n4 = (n2 >>> 4 ^ n3) & 0xF0F0F0F;
        final int n5 = n3 ^ n4;
        final int n6 = n2 ^ n4 << 4;
        final int n7 = (n6 >>> 16 ^ n5) & 0xFFFF;
        final int n8 = n5 ^ n7;
        final int n9 = n6 ^ n7 << 16;
        final int n10 = (n8 >>> 2 ^ n9) & 0x33333333;
        final int n11 = n9 ^ n10;
        final int n12 = n8 ^ n10 << 2;
        final int n13 = (n12 >>> 8 ^ n11) & 0xFF00FF;
        final int n14 = n11 ^ n13;
        final int n15 = n12 ^ n13 << 8;
        final int n16 = n15 << 1 | (n15 >>> 31 & 0x1);
        final int n17 = (n14 ^ n16) & 0xAAAAAAAA;
        final int n18 = n14 ^ n17;
        int n19 = n16 ^ n17;
        int n20 = n18 << 1 | (n18 >>> 31 & 0x1);
        for (int i = 0; i < 8; ++i) {
            final int n21 = (n19 << 28 | n19 >>> 4) ^ array3[n++];
            final int n22 = finemenu.SP7[n21 & 0x3F] | finemenu.SP5[n21 >>> 8 & 0x3F] | finemenu.SP3[n21 >>> 16 & 0x3F] | finemenu.SP1[n21 >>> 24 & 0x3F];
            final int n23 = n19 ^ array3[n++];
            n20 ^= (n22 | finemenu.SP8[n23 & 0x3F] | finemenu.SP6[n23 >>> 8 & 0x3F] | finemenu.SP4[n23 >>> 16 & 0x3F] | finemenu.SP2[n23 >>> 24 & 0x3F]);
            final int n24 = (n20 << 28 | n20 >>> 4) ^ array3[n++];
            final int n25 = finemenu.SP7[n24 & 0x3F] | finemenu.SP5[n24 >>> 8 & 0x3F] | finemenu.SP3[n24 >>> 16 & 0x3F] | finemenu.SP1[n24 >>> 24 & 0x3F];
            final int n26 = n20 ^ array3[n++];
            n19 ^= (n25 | finemenu.SP8[n26 & 0x3F] | finemenu.SP6[n26 >>> 8 & 0x3F] | finemenu.SP4[n26 >>> 16 & 0x3F] | finemenu.SP2[n26 >>> 24 & 0x3F]);
        }
        final int n27 = n19 << 31 | n19 >>> 1;
        final int n28 = (n20 ^ n27) & 0xAAAAAAAA;
        final int n29 = n20 ^ n28;
        final int n30 = n27 ^ n28;
        final int n31 = n29 << 31 | n29 >>> 1;
        final int n32 = (n31 >>> 8 ^ n30) & 0xFF00FF;
        final int n33 = n30 ^ n32;
        final int n34 = n31 ^ n32 << 8;
        final int n35 = (n34 >>> 2 ^ n33) & 0x33333333;
        final int n36 = n33 ^ n35;
        final int n37 = n34 ^ n35 << 2;
        final int n38 = (n36 >>> 16 ^ n37) & 0xFFFF;
        final int n39 = n37 ^ n38;
        final int n40 = n36 ^ n38 << 16;
        final int n41 = (n40 >>> 4 ^ n39) & 0xF0F0F0F;
        final int n42 = n39 ^ n41;
        array2[0] = (n40 ^ n41 << 4);
        array2[1] = n42;
    }
    
    public static void squashBytesToInts(final byte[] array, final int n, final int[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i] = ((array[n + i * 4] & 0xFF) << 24 | (array[n + i * 4 + 1] & 0xFF) << 16 | (array[n + i * 4 + 2] & 0xFF) << 8 | (array[n + i * 4 + 3] & 0xFF));
        }
    }
    
    public static void spreadIntsToBytes(final int[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            array2[n2 + i * 4] = (byte)(array[n + i] >>> 24);
            array2[n2 + i * 4 + 1] = (byte)(array[n + i] >>> 16);
            array2[n2 + i * 4 + 2] = (byte)(array[n + i] >>> 8);
            array2[n2 + i * 4 + 3] = (byte)array[n + i];
        }
    }
    
    public void sous_init() {
        this.theBackground = this.getParameter("background");
        this.theBgColor = this.getParameter("bgcolor");
        final byte[] key = { -6, 13, 61, -102, -116, 117, 65, -94 };
        this.theItemIm0 = new String[this.MaxItem];
        this.theItemIm1 = new String[this.MaxItem];
        this.ItemAlign = new int[this.MaxItem];
        this.ItemTraj = new int[this.MaxItem];
        this.ItemNbpts = new int[this.MaxItem];
        this.ItemXi = new int[this.MaxItem];
        this.ItemXf = new int[this.MaxItem];
        this.ItemYi = new int[this.MaxItem];
        this.ItemYf = new int[this.MaxItem];
        this.ItemIm0 = new Image[this.MaxItem];
        this.ItemIm1 = new Image[this.MaxItem];
        this.ItemTargetPage = new String[this.MaxItem];
        this.ItemTargetFrame = new String[this.MaxItem];
        int n = 0;
        while (this.LoadIm) {
            ++n;
            this.theItemIm0[n] = this.getParameter("item" + n + "_im0");
            if (this.theItemIm0[n] == null) {
                this.LoadIm = false;
            }
            this.theItemIm1[n] = this.getParameter("item" + n + "_im1");
            if (this.theItemIm1 == null) {
                this.LoadIm = false;
            }
            this.theItemXi = this.getParameter("item" + n + "_xi");
            if (this.theItemXi == null) {
                this.LoadIm = false;
            }
            else {
                this.ItemXi[n] = Integer.parseInt(this.theItemXi);
            }
            this.theItemXf = this.getParameter("item" + n + "_xf");
            if (this.theItemXf == null) {
                this.LoadIm = false;
            }
            else {
                this.ItemXf[n] = Integer.parseInt(this.theItemXf);
            }
            this.theItemYi = this.getParameter("item" + n + "_yi");
            if (this.theItemYi == null) {
                this.LoadIm = false;
            }
            else {
                this.ItemYi[n] = Integer.parseInt(this.theItemYi);
            }
            this.theItemYf = this.getParameter("item" + n + "_yf");
            if (this.theItemYf == null) {
                this.LoadIm = false;
            }
            else {
                this.ItemYf[n] = Integer.parseInt(this.theItemYf);
            }
            this.theItemAlign = this.getParameter("item" + n + "_align");
            if (this.theItemAlign == null) {
                this.theItemAlign = this.DefaultAlign;
            }
            this.theItemAlign = this.theItemAlign.toUpperCase();
            if (this.theItemAlign.equals("RIGHT")) {
                this.ItemAlign[n] = m_item.RIGHT;
            }
            else {
                this.ItemAlign[n] = m_item.LEFT;
            }
            this.theItemTrajectory = this.getParameter("item" + n + "_trajectory");
            if (this.theItemTrajectory == null) {
                this.theItemTrajectory = this.DefaultTrajectory;
            }
            this.theItemTrajectory = this.theItemTrajectory.toUpperCase();
            if (this.theItemTrajectory.equals("PARABOLIC")) {
                this.ItemTraj[n] = m_item.PARABOLIC;
            }
            else if (this.theItemTrajectory.equals("SQRT")) {
                this.ItemTraj[n] = m_item.SQRT;
            }
            else if (this.theItemTrajectory.equals("EXPONENTIAL")) {
                this.ItemTraj[n] = m_item.EXPONENTIAL;
            }
            else if (this.theItemTrajectory.equals("LOG")) {
                this.ItemTraj[n] = m_item.LOG;
            }
            else if (this.theItemTrajectory.equals("PERIODIC")) {
                this.ItemTraj[n] = m_item.PERIODIC;
            }
            else {
                this.ItemTraj[n] = m_item.LINEAR;
            }
            this.theItemNbpts = this.getParameter("item" + n + "_nbpts");
            if (this.theItemNbpts == null) {
                this.theItemNbpts = this.DefaultNbpts;
            }
            this.ItemNbpts[n] = Math.abs(Integer.parseInt(this.theItemNbpts));
            this.ItemTargetPage[n] = this.getParameter("item" + n + "_targetpage");
            this.ItemTargetFrame[n] = this.getParameter("item" + n + "_targetframe");
        }
        this.ItemNb = n - 1;
        this.setKey(key);
        int n2 = 0;
        boolean b = false;
        int i = 0;
        while (!b) {
            if (this.getParameter("banner" + this.NbBan + "_image_0") == null) {
                b = true;
            }
            else {
                ++this.NbBan;
            }
        }
        if (this.NbBan != 0) {
            this.Banner = new m_banner[this.NbBan];
            for (int j = 0; j < this.NbBan; ++j) {
                while (i == 0) {
                    if (this.getParameter("banner" + j + "_image_" + n2) == null) {
                        i = 1;
                    }
                    else {
                        ++n2;
                    }
                }
                final MediaTracker mediaTracker = new MediaTracker(this);
                final Image[] array = new Image[n2];
                for (int k = 0; k < n2; ++k) {
                    mediaTracker.addImage(array[k] = this.getImage(this.getDocumentBase(), this.getParameter("banner" + j + "_image_" + k)), k);
                }
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {
                    this.LoadTrackBan = false;
                    break;
                }
                if (mediaTracker.isErrorAny()) {
                    this.LoadTrackBan = false;
                    break;
                }
                this.Banner[j] = new m_banner(array, array[0].getWidth(this), array[0].getHeight(this), this.getParameter("banner" + j + "_x"), this.getParameter("banner" + j + "_y"), this.getParameter("banner" + j + "_transition"), this.getParameter("banner" + j + "_nbinter"), this.getParameter("banner" + j + "_targetpage"), this.getParameter("banner" + j + "_targetframe"));
                i = 0;
                n2 = 0;
            }
        }
        boolean b2 = false;
        while (!b2) {
            if (this.getParameter("text" + this.NbTexte + "_sentences") == null) {
                b2 = true;
            }
            else {
                ++this.NbTexte;
            }
        }
        this.Texte = new m_text[this.NbTexte];
        for (int l = 0; l < this.NbTexte; ++l) {
            this.Texte[l] = new m_text(this.WinWidth, this.WinHeight, this.Demotxt + this.getParameter("text" + l + "_sentences"), this.getParameter("text" + l + "_x"), this.getParameter("text" + l + "_y"), this.getParameter("text" + l + "_linesdisplayed"), this.getParameter("text" + l + "_justification"), this.getParameter("text" + l + "_fontname"), this.getParameter("text" + l + "_fontsize"), this.getParameter("text" + l + "_fontstyle"), this.getParameter("text" + l + "_initialcolor"), this.getParameter("text" + l + "_finalcolor"), this.getParameter("text" + l + "_transition"), this.getParameter("text" + l + "_frametowait"), this.getParameter("text" + l + "_targetpage"), this.getParameter("text" + l + "_targetframe"));
        }
        if (this.NbTexte == 0) {
            this.NbTexte = 1;
            (this.Texte = new m_text[this.NbTexte])[0] = new m_text(this.WinWidth, this.WinHeight, this.Demotxt, "" + this.WinWidth / 2, "" + 39 * this.WinHeight / 40, "1", "center", "helvetica", "14", "bold", "FFFFFF", "000000", "fadex2", "2", "", "");
        }
        this.theFps = this.getParameter("fps");
        if (this.theFps == null) {
            this.theFps = this.DefaultFps;
        }
        this.SleepTime = Math.round(1000 / Math.abs(Integer.parseInt(this.theFps)));
        this.theTempoFrame = this.getParameter("tempoframe");
        if (this.theTempoFrame == null) {
            this.theTempoFrame = this.DefaultTempoFrame;
        }
        this.TempoSeuil = Math.abs(Integer.parseInt(this.theTempoFrame));
        this.theTokenFrame = this.getParameter("tokenframe");
        if (this.theTokenFrame == null) {
            this.theTokenFrame = this.DefaultTokenFrame;
        }
        this.TokenFrame = Math.abs(Integer.parseInt(this.theTokenFrame));
        this.theReg = this.getParameter("registration");
        if (this.theReg == null) {
            this.RegOk = true;
        }
        else {
            for (int n3 = 0; n3 < this.theReg.length() / 16; ++n3) {
                final String substring = this.theReg.substring(n3 * 16, (n3 + 1) * 16);
                final byte[] array2 = new byte[8];
                final byte[] array3 = new byte[8];
                for (int n4 = 0; n4 < 8; ++n4) {
                    array2[n4] = (byte)Integer.parseInt(substring.substring(2 * n4, 2 * (n4 + 1)), 16);
                }
                this.decrypt(array2, 0, array3, 0);
                for (int n5 = 0; n5 < 8; ++n5) {
                    this.RegUrl += (char)(array3[n5] & 0xFF);
                }
            }
            if (this.RegUrl.indexOf("#") != -1) {
                this.RegUrl = this.RegUrl.substring(0, this.RegUrl.indexOf("#"));
                if (this.RegUrl.regionMatches(true, 0, this.getCodeBase().toString(), 0, this.RegUrl.length())) {
                    this.RegOk = true;
                }
                else {
                    this.RegOk = true;
                }
            }
            else {
                this.RegOk = true;
            }
        }
        this.TempoTime = 0;
        this.Tempo = 0;
        this.TokenInit = this.TokenFrame;
        ++this.TempoSeuil;
        this.Token = 1;
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        if (this.theBackground != null) {
            mediaTracker2.addImage(this.Background = this.getImage(this.getDocumentBase(), this.theBackground), 0);
            this.BgColorOnly = false;
        }
        else {
            if (this.theBgColor == null) {
                this.BgColor = new Color(0);
            }
            else {
                this.BgColor = new Color(Integer.parseInt(this.theBgColor, 16));
            }
            this.BgColorOnly = true;
        }
        this.Items = new m_item[this.ItemNb + 1];
        int n6 = 0;
        for (int n7 = 1; n7 <= this.ItemNb; ++n7) {
            this.Items[n7] = new m_item(this.ItemXi[n7], this.ItemXf[n7], this.ItemYi[n7], this.ItemYf[n7], this.ItemAlign[n7], this.ItemTraj[n7], this.ItemNbpts[n7], this.getImage(this.getDocumentBase(), this.theItemIm0[n7]), this.getImage(this.getDocumentBase(), this.theItemIm1[n7]), this.ItemTargetPage[n7], this.ItemTargetFrame[n7]);
            mediaTracker2.addImage(this.Items[n7].getIm0(), n6);
            mediaTracker2.addImage(this.Items[n7].getIm1(), n6 + 1);
            n6 += 2;
        }
        try {
            mediaTracker2.waitForAll();
        }
        catch (Exception ex2) {
            this.LoadTrackMenu = false;
        }
        if (mediaTracker2.isErrorAny()) {
            this.LoadTrackMenu = false;
        }
        for (int n8 = 1; n8 <= this.ItemNb; ++n8) {
            this.Items[n8].setSize(this.Items[n8].getIm0().getWidth(this), this.Items[n8].getIm0().getHeight(this), this.Items[n8].getIm1().getWidth(this), this.Items[n8].getIm1().getHeight(this));
        }
    }
    
    public finemenu() {
        this.Demotxt = "FineMenu V1.0 Demo version#Copyright E.B - 1998#Contact : ebsp@iname.com# ";
        this.RegOk = false;
        this.DefaultAlign = "LEFT";
        this.DefaultTrajectory = "LINEAR";
        this.DefaultNbpts = "2";
        this.DefaultFps = "20";
        this.DefaultTempoFrame = "300";
        this.DefaultTokenFrame = "2";
        this.LoadIm = true;
        this.LoadTrackMenu = true;
        this.Load = true;
        this.Sinit = false;
        this.MaxItem = 10;
        this.BgColorOnly = true;
        this.LoadTrackBan = true;
        this.RegUrl = "http://";
        this.ToReg = "http://";
        this.encryptKeys = new int[32];
        this.decryptKeys = new int[32];
        this.tempInts = new int[2];
    }
    
    static {
        finemenu.bytebit = new byte[] { 1, 2, 4, 8, 16, 32, 64, -128 };
        finemenu.bigbyte = new int[] { 8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
        finemenu.pc1 = new byte[] { 56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3 };
        finemenu.totrot = new int[] { 1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28 };
        finemenu.pc2 = new byte[] { 13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31 };
        finemenu.SP1 = new int[] { 16843776, 0, 65536, 16843780, 16842756, 66564, 4, 65536, 1024, 16843776, 16843780, 1024, 16778244, 16842756, 16777216, 4, 1028, 16778240, 16778240, 66560, 66560, 16842752, 16842752, 16778244, 65540, 16777220, 16777220, 65540, 0, 1028, 66564, 16777216, 65536, 16843780, 4, 16842752, 16843776, 16777216, 16777216, 1024, 16842756, 65536, 66560, 16777220, 1024, 4, 16778244, 66564, 16843780, 65540, 16842752, 16778244, 16777220, 1028, 66564, 16843776, 1028, 16778240, 16778240, 0, 65540, 66560, 0, 16842756 };
        finemenu.SP2 = new int[] { -2146402272, -2147450880, 32768, 1081376, 1048576, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 1048576, 32, -2146435040, 1081344, 1048608, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 1048608, -2147483616, 0, 1081344, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 1048576, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 1048576, -2147483616, 1048608, -2147450848, -2147483616, 1048608, 1081344, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 1081344 };
        finemenu.SP3 = new int[] { 520, 134349312, 0, 134348808, 134218240, 0, 131592, 134218240, 131080, 134217736, 134217736, 131072, 134349320, 131080, 134348800, 520, 134217728, 8, 134349312, 512, 131584, 134348800, 134348808, 131592, 134218248, 131584, 131072, 134218248, 8, 134349320, 512, 134217728, 134349312, 134217728, 131080, 520, 131072, 134349312, 134218240, 0, 512, 131080, 134349320, 134218240, 134217736, 512, 0, 134348808, 134218248, 131072, 134217728, 134349320, 8, 131592, 131584, 134217736, 134348800, 134218248, 520, 134348800, 131592, 8, 134348808, 131584 };
        finemenu.SP4 = new int[] { 8396801, 8321, 8321, 128, 8396928, 8388737, 8388609, 8193, 0, 8396800, 8396800, 8396929, 129, 0, 8388736, 8388609, 1, 8192, 8388608, 8396801, 128, 8388608, 8193, 8320, 8388737, 1, 8320, 8388736, 8192, 8396928, 8396929, 129, 8388736, 8388609, 8396800, 8396929, 129, 0, 0, 8396800, 8320, 8388736, 8388737, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 8388609, 8193, 8396928, 8388737, 8193, 8320, 8388608, 8396801, 128, 8388608, 8192, 8396928 };
        finemenu.SP5 = new int[] { 256, 34078976, 34078720, 1107296512, 524288, 256, 1073741824, 34078720, 1074266368, 524288, 33554688, 1074266368, 1107296512, 1107820544, 524544, 1073741824, 33554432, 1074266112, 1074266112, 0, 1073742080, 1107820800, 1107820800, 33554688, 1107820544, 1073742080, 0, 1107296256, 34078976, 33554432, 1107296256, 524544, 524288, 1107296512, 256, 33554432, 1073741824, 34078720, 1107296512, 1074266368, 33554688, 1073741824, 1107820544, 34078976, 1074266368, 256, 33554432, 1107820544, 1107820800, 524544, 1107296256, 1107820800, 34078720, 0, 1074266112, 1107296256, 524544, 33554688, 1073742080, 524288, 0, 1074266112, 34078976, 1073742080 };
        finemenu.SP6 = new int[] { 536870928, 541065216, 16384, 541081616, 541065216, 16, 541081616, 4194304, 536887296, 4210704, 4194304, 536870928, 4194320, 536887296, 536870912, 16400, 0, 4194320, 536887312, 16384, 4210688, 536887312, 16, 541065232, 541065232, 0, 4210704, 541081600, 16400, 4210688, 541081600, 536870912, 536887296, 16, 541065232, 4210688, 541081616, 4194304, 16400, 536870928, 4194304, 536887296, 536870912, 16400, 536870928, 541081616, 4210688, 541065216, 4210704, 541081600, 0, 541065232, 16, 16384, 541065216, 4210704, 16384, 4194320, 536887312, 0, 541081600, 536870912, 4194320, 536887312 };
        finemenu.SP7 = new int[] { 2097152, 69206018, 67110914, 0, 2048, 67110914, 2099202, 69208064, 69208066, 2097152, 0, 67108866, 2, 67108864, 69206018, 2050, 67110912, 2099202, 2097154, 67110912, 67108866, 69206016, 69208064, 2097154, 69206016, 2048, 2050, 69208066, 2099200, 2, 67108864, 2099200, 67108864, 2099200, 2097152, 67110914, 67110914, 69206018, 69206018, 2, 2097154, 67108864, 67110912, 2097152, 69208064, 2050, 2099202, 69208064, 2050, 67108866, 69208066, 69206016, 2099200, 0, 2, 69208066, 0, 2099202, 69206016, 2048, 67108866, 67110912, 2048, 2097154 };
        finemenu.SP8 = new int[] { 268439616, 4096, 262144, 268701760, 268435456, 268439616, 64, 268435456, 262208, 268697600, 268701760, 266240, 268701696, 266304, 4096, 64, 268697600, 268435520, 268439552, 4160, 266240, 262208, 268697664, 268701696, 4160, 0, 0, 268697664, 268435520, 268439552, 266304, 262144, 266304, 262144, 268701696, 4096, 64, 268697664, 4096, 266304, 268439552, 64, 268435520, 268697600, 268697664, 268435456, 262144, 268439616, 0, 268701760, 262208, 268435520, 268697600, 268439552, 268439616, 0, 268701760, 266240, 266240, 4160, 4160, 262208, 268435456, 268701696 };
    }
}
