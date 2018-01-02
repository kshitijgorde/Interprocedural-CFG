import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Cursor;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class coolbanner extends Applet implements Runnable
{
    int iw;
    int ih;
    Image Buf;
    Graphics Bufg;
    int curNum;
    int nextNum;
    Thread t;
    Image i;
    int speed;
    int delay;
    int curDelay;
    final int FINISHED = 1;
    final int LOADING = 2;
    final int CHANGING = 3;
    final int CHANGE_AMOUNT = 256;
    final int WAIT_TIME = 50;
    int stage;
    int rate;
    int growLines;
    MediaTracker tracker;
    boolean over;
    boolean overarea;
    static final int maskR = 16711680;
    static final int maskG = 65280;
    static final int maskB = 255;
    static final int black = -16777216;
    static final int white = -1;
    static final int blue = -16777080;
    static final int green = -16742400;
    static final int cyan = -16742264;
    static final int red = -7864320;
    static final int magenta = -7864184;
    static final int brown = -7829504;
    static final int lightGray = -4342083;
    static final int darkGray = -14408668;
    static final int brightBlue = -16776961;
    static final int brightGreen = -16711936;
    static final int brightCyan = -16711681;
    static final int brightRed = -65536;
    static final int pink = -65281;
    static final int yellow = -256;
    Vector imgVector;
    int[] effectsArray;
    int effectCount;
    int[][] convArray;
    int[][] data;
    int[] transdata;
    int[] effectsArray1;
    int[] effectsArray2;
    int[] effectsArray3;
    int[] effectsArray4;
    int[] effectsArray5;
    int[] effectsArray6;
    int[] effectsArray7;
    int[] effectsArray8;
    final int FADE1 = 0;
    final int SCROLL1 = 1;
    final int SCROLL2 = 2;
    final int SCROLL3 = 3;
    final int SCROLL4 = 4;
    final int SCROLL5 = 5;
    static final int totalEffects = 6;
    int effect;
    static final int NOT_USED = 0;
    static final int USED = 1;
    boolean useBorder;
    int borderColor;
    int borderColorRed;
    int borderColorGreen;
    int borderColorBlue;
    public static int wipeBits;
    public static int wipeFactor;
    boolean noEffect;
    
    static {
        coolbanner.wipeBits = 5;
        coolbanner.wipeFactor = 32;
    }
    
    public coolbanner() {
        this.iw = 468;
        this.ih = 60;
        this.curNum = 0;
        this.nextNum = 0;
        this.speed = 1;
        this.delay = 50;
        this.curDelay = this.delay;
        this.stage = 0;
        this.rate = 0;
        this.growLines = 1;
        this.over = false;
        this.overarea = false;
        this.effectsArray = null;
        this.effectCount = 0;
        this.convArray = null;
        this.data = null;
        this.transdata = null;
        this.effectsArray1 = null;
        this.effectsArray2 = null;
        this.effectsArray3 = null;
        this.effectsArray4 = null;
        this.effectsArray5 = null;
        this.effectsArray6 = null;
        this.effectsArray7 = null;
        this.effectsArray8 = null;
        this.effect = 0;
        this.useBorder = true;
        this.borderColor = -16777216;
        this.noEffect = true;
    }
    
    public void getFiles() {
        int n = 1;
        this.stage = 2;
        String parameter;
        do {
            parameter = this.getParameter("img" + n);
            final String parameter2 = this.getParameter("link" + n);
            if (parameter != null) {
                this.imgVector.addElement(new imageInfo(this.loadImage(parameter, n - 1), parameter2));
            }
            ++n;
        } while (parameter != null);
    }
    
    public void getNewEffect() {
        if (this.effectCount >= 6) {
            for (int i = 0; i < 6; ++i) {
                this.effectsArray[i] = 0;
            }
            this.effectCount = 0;
        }
        do {
            this.effect = (int)(Math.random() * 6.0);
            if (this.effectsArray[this.effect] == 0) {
                this.effectsArray[this.effect] = 1;
                ++this.effectCount;
            }
        } while (this.effectCount == this.effectCount);
    }
    
    public void getParameters() {
        final String parameter = this.getParameter("speed");
        if (parameter != null && parameter.compareTo("") != 0) {
            this.speed = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("delay");
        if (parameter2 != null && parameter2.compareTo("") != 0) {
            this.delay = Integer.parseInt(parameter2) * 15;
        }
        final String parameter3 = this.getParameter("useborder");
        if (parameter3 != null && parameter3.compareTo("") != 0) {
            if (parameter3.compareTo("Y") == 0) {
                this.useBorder = true;
            }
            else if (parameter3.compareTo("N") == 0) {
                this.useBorder = false;
            }
        }
        final String parameter4 = this.getParameter("bordercolor");
        if (parameter4 != null && parameter4.compareTo("") != 0) {
            if (parameter4.compareTo("0") == 0) {
                this.borderColor = -1;
            }
            if (parameter4.compareTo("1") == 0) {
                this.borderColor = -16777216;
            }
            if (parameter4.compareTo("2") == 0) {
                this.borderColor = -16777080;
            }
            if (parameter4.compareTo("3") == 0) {
                this.borderColor = -16742400;
            }
            if (parameter4.compareTo("4") == 0) {
                this.borderColor = -16742264;
            }
            if (parameter4.compareTo("5") == 0) {
                this.borderColor = -7864320;
            }
            if (parameter4.compareTo("6") == 0) {
                this.borderColor = -7864184;
            }
            if (parameter4.compareTo("7") == 0) {
                this.borderColor = -7829504;
            }
            if (parameter4.compareTo("8") == 0) {
                this.borderColor = -4342083;
            }
            if (parameter4.compareTo("9") == 0) {
                this.borderColor = -14408668;
            }
            if (parameter4.compareTo("10") == 0) {
                this.borderColor = -16776961;
            }
            if (parameter4.compareTo("11") == 0) {
                this.borderColor = -16711936;
            }
            if (parameter4.compareTo("12") == 0) {
                this.borderColor = -16711681;
            }
            if (parameter4.compareTo("13") == 0) {
                this.borderColor = -65536;
            }
            if (parameter4.compareTo("14") == 0) {
                this.borderColor = -65281;
            }
            if (parameter4.compareTo("15") == 0) {
                this.borderColor = -256;
            }
        }
        else {
            final String parameter5 = this.getParameter("bordercolorred");
            if (parameter5 != null && parameter5.compareTo("") != 0) {
                this.borderColorRed = Integer.parseInt(parameter5);
            }
            final String parameter6 = this.getParameter("bordercolorgreen");
            if (parameter6 != null && parameter6.compareTo("") != 0) {
                this.borderColorGreen = Integer.parseInt(parameter6);
            }
            final String parameter7 = this.getParameter("bordercolorblue");
            if (parameter7 != null && parameter7.compareTo("") != 0) {
                this.borderColorBlue = Integer.parseInt(parameter7);
            }
            final Color color = new Color(this.borderColorRed, this.borderColorGreen, this.borderColorBlue);
            this.borderColor = (0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue());
        }
    }
    
    public void grabImagePixels() {
        this.nextNum = (this.curNum + 1) % this.imgVector.size();
        final Image img = this.imgVector.elementAt(this.curNum).Img;
        final Image img2 = this.imgVector.elementAt(this.nextNum).Img;
        try {
            new PixelGrabber(img, 0, 0, this.iw, this.ih, this.convArray[this.curNum], 0, this.iw).grabPixels();
            new PixelGrabber(img2, 0, 0, this.iw, this.ih, this.convArray[this.nextNum], 0, this.iw).grabPixels();
        }
        catch (Exception ex) {}
    }
    
    public void init() {
        this.Buf = this.createImage(this.iw, this.ih);
        this.Bufg = this.Buf.getGraphics();
        final String parameter = this.getParameter("img1");
        final String parameter2 = this.getParameter("link1");
        if (parameter != null) {
            this.Bufg.drawImage(new imageInfo(this.loadImage(parameter, 0), parameter2).Img, 0, 0, this);
        }
        this.imgVector = new Vector();
        this.effectsArray = new int[6];
        coolbanner.wipeFactor = 1 << coolbanner.wipeBits;
        coolbanner.wipeBits = 8 - coolbanner.wipeBits;
        this.data = new int[256][];
        for (int i = 0; i < 256; ++i) {
            this.data[i] = null;
        }
        this.transdata = new int[this.iw * this.ih];
        this.getParameters();
        final int delay = this.delay;
        this.getFiles();
        this.convArray = new int[this.imgVector.size() + 1][this.iw * this.ih];
        for (int j = 0; j < 6; ++j) {
            this.effectsArray[j] = 0;
        }
        this.effectCount = 0;
        this.getNewEffect();
    }
    
    public Image loadImage(final String s, final int n) {
        final Image image = this.getImage(this.getCodeBase(), s);
        (this.tracker = new MediaTracker(this)).addImage(image, n);
        try {
            this.tracker.waitForID(n);
        }
        catch (InterruptedException ex) {}
        return image;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.stage == 3) {
            return true;
        }
        if (this.overarea) {
            URL url;
            try {
                url = new URL(this.getCodeBase(), "http://www.reystar.com");
            }
            catch (Exception ex) {
                url = null;
            }
            if (url == null) {
                return true;
            }
            this.getAppletContext().showDocument(url, "_blank");
        }
        else {
            final String imgUrl = this.imgVector.elementAt(this.curNum).ImgUrl;
            if (!imgUrl.equals("null")) {
                URL url2;
                try {
                    url2 = new URL(this.getCodeBase(), imgUrl);
                }
                catch (Exception ex2) {
                    url2 = null;
                }
                if (url2 == null) {
                    return true;
                }
                this.getAppletContext().showDocument(url2, "_blank");
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.over = false;
        this.overarea = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n2 > 0 && n2 < this.ih) {
            this.over = true;
            this.overarea = false;
            if (n2 > this.ih - 16 && n < 190) {
                this.overarea = true;
            }
            return true;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.setCursor(new Cursor(3));
        if (this.stage == 2) {
            graphics.drawString("Loading Images", 2, 8);
        }
        if (this.stage == 3) {
            if (this.effect == 0) {
                final int rate = this.rate;
                final int n = 256 - rate;
                final int n2 = rate;
                for (int i = 0; i < this.iw * this.ih; ++i) {
                    this.convArray[this.imgVector.size()][i] = (0xFF000000 | ((this.convArray[this.curNum][i] & 0xFF0000) >> 16) * n + ((this.convArray[this.nextNum][i] & 0xFF0000) >> 16) * n2 >> 8 << 16 | ((this.convArray[this.curNum][i] & 0xFF00) >> 8) * n + ((this.convArray[this.nextNum][i] & 0xFF00) >> 8) * n2 >> 8 << 8 | (this.convArray[this.curNum][i] & 0xFF) * n + (this.convArray[this.nextNum][i] & 0xFF) * n2 >> 8);
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, this.convArray[this.imgVector.size()], 0, this.iw)), 0, 0, this);
                this.rate += this.speed;
                if (this.rate >= 256) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
            else if (this.effect == 1) {
                final int rate2 = this.rate;
                final int n3 = this.iw - rate2;
                final int n4 = rate2 / 3;
                final int n5 = 256 - n4;
                final int n6 = n4;
                final int[] array = new int[this.iw * this.ih];
                for (int j = 0; j < this.ih; ++j) {
                    for (int k = 0; k < n3; ++k) {
                        array[j * this.iw + k] = this.convArray[this.curNum][j * this.iw + k];
                    }
                }
                for (int l = 0; l < this.ih; ++l) {
                    for (int n7 = n3; n7 < this.iw; ++n7) {
                        array[l * this.iw + n7] = this.convArray[this.nextNum][l * this.iw + n7];
                    }
                }
                for (int n8 = 0; n8 < this.iw * this.ih; ++n8) {
                    array[n8] = (0xFF000000 | ((array[n8] & 0xFF0000) >> 16) * n5 + ((this.convArray[this.nextNum][n8] & 0xFF0000) >> 16) * n6 >> 8 << 16 | ((array[n8] & 0xFF00) >> 8) * n5 + ((this.convArray[this.nextNum][n8] & 0xFF00) >> 8) * n6 >> 8 << 8 | (array[n8] & 0xFF) * n5 + (this.convArray[this.nextNum][n8] & 0xFF) * n6 >> 8);
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, array, 0, this.iw)), 0, 0, this);
                this.rate += 2 * this.speed;
                if (this.rate >= this.iw) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
            else if (this.effect == 2) {
                final int n9 = this.ih - this.rate;
                final int[] array2 = new int[this.iw * this.ih];
                for (int n10 = 0; n10 < n9; ++n10) {
                    for (int n11 = 0; n11 < this.iw; ++n11) {
                        array2[n10 * this.iw + n11] = this.convArray[this.curNum][n10 * this.iw + n11];
                    }
                }
                for (int n12 = n9; n12 < this.ih; ++n12) {
                    for (int n13 = 0; n13 < this.iw; ++n13) {
                        array2[n12 * this.iw + n13] = this.convArray[this.nextNum][n12 * this.iw + n13];
                    }
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, array2, 0, this.iw)), 0, 0, this);
                ++this.rate;
                if (this.rate >= this.ih) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
            else if (this.effect == 3) {
                final int n14 = this.iw - this.rate;
                final int n15 = this.ih / 2;
                final int[] array3 = new int[this.iw * this.ih];
                for (int n16 = 0; n16 < this.ih; ++n16) {
                    for (int n17 = 0; n17 < this.iw; ++n17) {
                        array3[n16 * this.iw + n17] = this.convArray[this.curNum][n16 * this.iw + n17];
                    }
                }
                for (int n18 = 0; n18 < n15; ++n18) {
                    for (int n19 = 2 * n18; n19 < 2 * n18 + 1; ++n19) {
                        for (int n20 = 0; n20 < this.iw - n14; ++n20) {
                            array3[n19 * this.iw + n20] = this.convArray[this.nextNum][n19 * this.iw + n20];
                        }
                    }
                }
                for (int n21 = 0; n21 < n15; ++n21) {
                    for (int n22 = 2 * n21 + 1; n22 < 2 * n21 + 2; ++n22) {
                        for (int n23 = n14; n23 < this.iw; ++n23) {
                            array3[n22 * this.iw + n23] = this.convArray[this.nextNum][n22 * this.iw + n23];
                        }
                    }
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, array3, 0, this.iw)), 0, 0, this);
                this.rate += 3 * this.speed;
                if (this.rate >= this.iw) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
            else if (this.effect == 4) {
                final int n24 = this.ih - this.rate;
                final int[] array4 = new int[this.ih * this.iw];
                final int[] array5 = new int[this.ih * this.iw];
                final int[][] array6 = new int[this.iw][this.iw];
                final int[] array7 = new int[this.ih * this.iw];
                for (int n25 = 0; n25 < this.ih; ++n25) {
                    for (int n26 = 0; n26 < this.iw; ++n26) {
                        array6[n25][n26] = this.convArray[this.curNum][n25 * this.iw + n26];
                    }
                }
                for (int n27 = this.ih - 1; n27 >= 0; --n27) {
                    for (int n28 = 0; n28 < this.iw; ++n28) {
                        array4[n28 * this.ih + (this.ih - n27 - 1)] = array6[n27][n28];
                    }
                }
                for (int n29 = 0; n29 < this.ih; ++n29) {
                    for (int n30 = 0; n30 < this.iw; ++n30) {
                        array6[n29][n30] = this.convArray[this.nextNum][n29 * this.iw + n30];
                    }
                }
                for (int n31 = this.ih - 1; n31 >= 0; --n31) {
                    for (int n32 = 0; n32 < this.iw; ++n32) {
                        array5[n32 * this.ih + (this.ih - n31 - 1)] = array6[n31][n32];
                    }
                }
                final int iw = this.iw;
                final int ih = this.ih;
                final int n33 = iw / 2;
                final int[] array8 = new int[this.iw * this.ih];
                for (int n34 = 0; n34 < iw; ++n34) {
                    for (int n35 = 0; n35 < ih; ++n35) {
                        array8[n34 * ih + n35] = array4[n34 * ih + n35];
                    }
                }
                for (int n36 = 0; n36 < n33; ++n36) {
                    for (int n37 = 2 * n36; n37 < 2 * n36 + 1; ++n37) {
                        for (int n38 = 0; n38 < ih - n24; ++n38) {
                            array8[n37 * ih + n38] = array5[n37 * ih + n38];
                        }
                    }
                }
                for (int n39 = 0; n39 < n33; ++n39) {
                    for (int n40 = 2 * n39 + 1; n40 < 2 * n39 + 2; ++n40) {
                        for (int n41 = n24; n41 < ih; ++n41) {
                            array8[n40 * ih + n41] = array5[n40 * ih + n41];
                        }
                    }
                }
                for (int n42 = 0; n42 < this.iw; ++n42) {
                    for (int n43 = 0; n43 < this.ih; ++n43) {
                        array6[n42][n43] = array8[n42 * this.ih + n43];
                    }
                }
                for (int n44 = this.ih - 1; n44 >= 0; --n44) {
                    for (int n45 = 0; n45 < this.iw; ++n45) {
                        array7[(this.ih - n44 - 1) * this.iw + n45] = array6[n45][n44];
                    }
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, array7, 0, this.iw)), 0, 0, this);
                this.rate += 2 * this.speed;
                if (this.rate >= this.ih) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
            else if (this.effect == 5) {
                final int n46 = this.iw - this.rate;
                final int[] array9 = new int[this.iw * this.ih];
                for (int n47 = 0; n47 < this.ih; ++n47) {
                    for (int n48 = 0; n48 < this.iw; ++n48) {
                        array9[n47 * this.iw + n48] = this.convArray[this.curNum][n47 * this.iw + n48];
                    }
                }
                for (int n49 = 0; n49 < 30; ++n49) {
                    for (int n50 = 0; n50 < this.iw - n46; ++n50) {
                        array9[n49 * this.iw + n50] = this.convArray[this.nextNum][n49 * this.iw + n50];
                    }
                }
                for (int n51 = 30; n51 < 60; ++n51) {
                    for (int n52 = n46; n52 < this.iw; ++n52) {
                        array9[n51 * this.iw + n52] = this.convArray[this.nextNum][n51 * this.iw + n52];
                    }
                }
                graphics.drawImage(this.createImage(new MemoryImageSource(this.iw, this.ih, array9, 0, this.iw)), 0, 0, this);
                this.rate += 3 * this.speed;
                if (this.rate >= this.iw) {
                    this.curNum = this.nextNum;
                    this.stage = 1;
                }
            }
        }
        if (this.stage == 1) {
            if (this.over) {
                this.setCursor(new Cursor(12));
            }
            if (this.imgVector.size() == 0) {
                graphics.drawString("No images exist", 2, 8);
            }
            else {
                graphics.drawImage(this.imgVector.elementAt(this.curNum).Img, 0, 0, this);
                if (this.over && this.useBorder) {
                    graphics.setColor(new Color(this.borderColor));
                    graphics.drawRect(0, 0, this.iw - 1, this.ih - 1);
                }
            }
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            if (this.stage == 2 && this.tracker.checkAll()) {
                this.stage = 3;
            }
            if (this.stage == 1) {
                --this.curDelay;
                if (this.curDelay <= 0) {
                    this.curDelay = this.delay;
                    this.grabImagePixels();
                    this.rate = 0;
                    this.stage = 3;
                    this.getNewEffect();
                }
                for (int i = 0; i < 256; ++i) {
                    this.data[i] = null;
                }
            }
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void start() {
        (this.t = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
            this.t = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(this.Bufg);
        graphics.drawImage(this.Buf, 0, 0, this);
    }
    
    class imageInfo
    {
        Image Img;
        String ImgUrl;
        
        public imageInfo(final Image img, final String imgUrl) {
            this.Img = img;
            this.ImgUrl = imgUrl;
        }
    }
}
