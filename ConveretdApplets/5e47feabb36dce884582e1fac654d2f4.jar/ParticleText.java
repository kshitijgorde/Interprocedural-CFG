import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ParticleText extends Applet implements Runnable
{
    private static int imgW;
    private static int imgH;
    private static int targetFPS;
    private static int morphDelay;
    private static int fontSize;
    private static int fontBold;
    private static int fontItalic;
    private static boolean useBlur;
    private static boolean useSineShift;
    private float periodFactor;
    private float scaleFactor;
    private static Color backColor;
    private static Color fontColor;
    private static float waveShiftSpeed;
    private static String fontName;
    String[] credSplit;
    private Thread myThread;
    private int[] pix;
    private int[] posX;
    private int[] posY;
    private int[] destX;
    private int[] destY;
    private int[] pointCol;
    private byte[] finPoint;
    private MemoryImageSource memSrc;
    private Image bb;
    private Image imgText;
    private ColorModel colModel;
    private Graphics txtG;
    private Graphics scrG;
    private Font myFont;
    private PixelGrabber pg;
    private int[] imgPix;
    private int[] myShift;
    private boolean destroy;
    private boolean stop;
    private float waveShift;
    
    public ParticleText() {
        this.periodFactor = 0.02f;
        this.scaleFactor = 15.0f;
        this.credSplit = new String[] { new String(""), new String("Three Rings -"), new String("for the Elven-kings -"), new String("under the sky,"), new String("Seven for the Dwarf-lords -"), new String("in their halls of stone,"), new String("Nine for Mortal Men -"), new String("doomed to die,"), new String("One for the Dark Lord -"), new String("on his dark throne"), new String("In the Land of Mordor -"), new String("where the Shadows lie."), new String(""), new String("One Ring to rule them all,"), new String("One Ring to find them,"), new String("One Ring to bring them all"), new String("and in the Darkness -"), new String("bind them"), new String("In the Land of Mordor"), new String("where the Shadows lie."), new String(""), new String("Ash nazg durbatul\u00fbk,"), new String("ash nazg gimbatul,"), new String("ash nazg thrakatul\u00fbk"), new String("agh burzum-ishi krimpatul."), new String(""), new String(""), new String("Quote from"), new String("The Lord Of The Rings"), new String("by J.R.R Tolkien"), new String("as you should know :)"), new String(""), new String("If you've watched"), new String("this long,"), new String("you should probably"), new String("find something -"), new String("better to do ^_^"), new String("..."), new String(""), new String("-- T H E  E N D --"), new String(""), new String(""), new String("") };
        this.destroy = false;
        this.stop = false;
        this.waveShift = 0.0f;
    }
    
    public void init() {
    }
    
    public void start() {
        this.getParams();
        this.initPix();
        this.initPoints(2000, ParticleText.fontColor.getRGB());
        this.scrG = this.getGraphics();
        this.imgText = this.createImage(ParticleText.imgW, ParticleText.imgH);
        this.colModel = new DirectColorModel(32, 16711680, 65280, 255);
        (this.memSrc = new MemoryImageSource(ParticleText.imgW, ParticleText.imgH, this.colModel, this.pix, 0, ParticleText.imgW)).setAnimated(true);
        this.bb = this.createImage(this.memSrc);
        this.txtG = this.imgText.getGraphics();
        this.myFont = new Font(ParticleText.fontName, ParticleText.fontBold | ParticleText.fontItalic, ParticleText.fontSize);
        this.txtG.setFont(this.myFont);
        this.newCred(this.credSplit[0]);
        this.stop = false;
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.stop = true;
        this.destroy = false;
        this.destroy();
    }
    
    public void destroy() {
        this.stop = true;
        this.destroy = true;
    }
    
    private void newCred(final String s) {
        final int length = this.posX.length;
        int n = 0;
        final int n2 = ParticleText.backColor.getRGB() & 0xFFFFFF;
        this.txtG.setColor(ParticleText.backColor);
        this.txtG.fillRect(0, 0, ParticleText.imgW, ParticleText.imgH);
        final FontMetrics fontMetrics = this.txtG.getFontMetrics();
        final int n3 = (ParticleText.imgW - fontMetrics.stringWidth(s)) / 2;
        final int n4 = (ParticleText.imgH + fontMetrics.getHeight()) / 2 - fontMetrics.getDescent() - fontMetrics.getLeading();
        this.txtG.setColor(ParticleText.fontColor);
        this.txtG.setFont(this.myFont);
        this.txtG.drawString(s, n3, n4);
        this.pg = new PixelGrabber(this.imgText, 0, 0, ParticleText.imgW, ParticleText.imgH, this.imgPix, 0, ParticleText.imgW);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        final int length2 = this.imgPix.length;
        for (int i = 0; i < length2; ++i) {
            if ((this.imgPix[i] & 0xFFFFFF) != n2) {
                ++n;
            }
        }
        final int n5 = n;
        if (n5 == 0) {
            this.initPoints(2000, ParticleText.fontColor.getRGB());
            return;
        }
        final int[] posX = new int[n5];
        final int[] posY = new int[n5];
        final int[] destX = new int[n5];
        final int[] destY = new int[n5];
        final int[] pointCol = new int[n5];
        final byte[] finPoint = new byte[n5];
        int j = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        while (j < length2) {
            if ((this.imgPix[j] & 0xFFFFFF) != n2) {
                destX[n6] = n7;
                destY[n6] = n8;
                pointCol[n6] = this.imgPix[j];
                finPoint[n6] = 0;
                if (n6 < length) {
                    posX[n6] = this.posX[n6];
                    posY[n6] = this.posY[n6];
                }
                else {
                    final int n9 = (int)(Math.random() * length);
                    posX[n6] = this.posX[n9];
                    posY[n6] = this.posY[n9];
                }
                ++n6;
            }
            ++j;
            if (++n7 == ParticleText.imgW) {
                n7 = 0;
                ++n8;
            }
        }
        this.posX = posX;
        this.posY = posY;
        this.destX = destX;
        this.destY = destY;
        this.pointCol = pointCol;
        this.finPoint = finPoint;
        this.swapDests(n5 >> 1);
    }
    
    private void initPix() {
        this.pix = new int[ParticleText.imgW * ParticleText.imgH];
        this.imgPix = new int[ParticleText.imgW * ParticleText.imgH];
        this.myShift = new int[ParticleText.imgW];
        for (int i = 0; i < ParticleText.imgH; ++i) {
            for (int j = 0; j < ParticleText.imgW; ++j) {
                this.pix[i * ParticleText.imgW + j] = 0;
            }
        }
    }
    
    private void initPoints(final int n, final int n2) {
        this.posX = new int[n];
        this.posY = new int[n];
        this.destX = new int[n];
        this.destY = new int[n];
        this.pointCol = new int[n];
        this.finPoint = new byte[n];
        for (int i = 0; i < n; ++i) {
            final int n3 = (int)(Math.random() * ParticleText.imgW);
            final int n4 = (int)(Math.random() * ParticleText.imgH);
            this.posX[i] = n3;
            this.posY[i] = n4;
            final int n5 = (int)(Math.random() * ParticleText.imgW);
            final int n6 = (int)(Math.random() * ParticleText.imgH);
            this.destX[i] = n5;
            this.destY[i] = n6;
            this.pointCol[i] = n2;
        }
    }
    
    private void swapDests(final int n) {
        final int n2 = this.posX.length - 1;
        for (int i = 0; i < n; ++i) {
            final int n3 = (int)(Math.random() * n2);
            final int n4 = (int)(Math.random() * n2);
            final int n5 = this.destX[n3];
            this.destX[n3] = this.destX[n4];
            this.destX[n4] = n5;
            final int n6 = this.destY[n3];
            this.destY[n3] = this.destY[n4];
            this.destY[n4] = n6;
            final int n7 = this.pointCol[n3];
            this.pointCol[n3] = this.pointCol[n4];
            this.pointCol[n4] = n7;
        }
    }
    
    private boolean movePoints() {
        int n = 0;
        final int length = this.posX.length;
        for (int i = 0; i < length; ++i) {
            if (this.finPoint[i] == 0) {
                int n2 = this.destX[i] - this.posX[i] >> 4;
                int n3 = this.destY[i] - this.posY[i] >> 4;
                boolean b = false;
                boolean b2 = false;
                if (n2 == 0) {
                    n2 = this.destX[i] - this.posX[i];
                    b = true;
                }
                if (n3 == 0) {
                    n3 = this.destY[i] - this.posY[i];
                    b2 = true;
                }
                final int[] posX = this.posX;
                final int n4 = i;
                posX[n4] += n2;
                final int[] posY = this.posY;
                final int n5 = i;
                posY[n5] += n3;
                if (b && b2) {
                    this.finPoint[i] = 1;
                    ++n;
                }
            }
            else {
                ++n;
            }
        }
        return n == length;
    }
    
    public void drawPoints() {
        final int length = this.posX.length;
        final int rgb = ParticleText.backColor.getRGB();
        int i = 0;
        final int n = rgb & 0xFF;
        final int n2 = (rgb & 0xFF00) >> 8;
        final int n3 = (rgb & 0xFF0000) >> 16;
        final int length2 = this.pix.length;
        if (ParticleText.useBlur) {
            while (i < length2) {
                final int n4 = this.pix[i];
                final int n5 = n4 & 0xFF;
                final int n6 = n4 >> 8 & 0xFF;
                final int n7 = n4 >> 16 & 0xFF;
                this.pix[i] = ((n5 << 3) - n5 + n >> 3 | (n6 << 3) - n6 + n2 >> 3 << 8 | (n7 << 3) - n7 + n3 >> 3 << 16 | 0xFF000000);
                ++i;
            }
        }
        else {
            while (i < length2) {
                this.pix[i] = rgb;
                ++i;
            }
        }
        if (ParticleText.useSineShift) {
            for (int j = 0; j < ParticleText.imgW; ++j) {
                this.myShift[j] = (int)(this.scaleFactor * Math.sin(j * this.periodFactor + this.waveShift));
            }
        }
        if (ParticleText.useSineShift) {
            for (int k = 0; k < length; ++k) {
                final int n8 = (this.posY[k] + this.myShift[this.posY[k]]) * ParticleText.imgW + (this.posX[k] + this.myShift[this.posX[k]]);
                if (n8 >= 0 && n8 < length2) {
                    this.pix[n8] = this.pointCol[k];
                }
            }
        }
        else {
            for (int l = 0; l < length; ++l) {
                final int n9 = this.posY[l] * ParticleText.imgW + this.posX[l];
                if (n9 >= 0 && n9 < length2) {
                    this.pix[n9] = this.pointCol[l];
                }
            }
        }
        if (ParticleText.useBlur) {
            for (int n10 = ParticleText.imgW + 1, n11 = n10 - ParticleText.imgW, n12 = n10 - 1, n13 = n10 + 1, n14 = n10 + ParticleText.imgW; n10 < this.pix.length - ParticleText.imgW; ++n10, ++n11, ++n12, ++n13, ++n14) {
                this.pix[n10] = ((this.pix[n11] & 0xFF) + (this.pix[n12] & 0xFF) + (this.pix[n13] & 0xFF) + (this.pix[n14] & 0xFF) >> 2 | (this.pix[n11] >> 8 & 0xFF) + (this.pix[n12] >> 8 & 0xFF) + (this.pix[n13] >> 8 & 0xFF) + (this.pix[n14] >> 8 & 0xFF) >> 2 << 8 | (this.pix[n11] >> 16 & 0xFF) + (this.pix[n12] >> 16 & 0xFF) + (this.pix[n13] >> 16 & 0xFF) + (this.pix[n14] >> 16 & 0xFF) >> 2 << 16 | 0xFF000000);
            }
        }
        this.waveShift = (this.waveShift + ParticleText.waveShiftSpeed) % 6.2832f;
    }
    
    public void update(final Graphics graphics) {
        this.scrG.drawImage(this.bb, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void run() {
        final int length = this.credSplit.length;
        int n = 0;
        final int n2 = 1000 / ParticleText.targetFPS;
        while (!this.stop) {
            final long currentTimeMillis = System.currentTimeMillis();
            final boolean movePoints = this.movePoints();
            this.drawPoints();
            this.memSrc.newPixels(0, 0, ParticleText.imgW, ParticleText.imgH);
            if (movePoints) {
                if (n > length - 2) {
                    n = -1;
                }
                long currentTimeMillis2 = 0L;
                while (currentTimeMillis2 - System.currentTimeMillis() < ParticleText.morphDelay) {
                    currentTimeMillis2 = System.currentTimeMillis();
                    if (ParticleText.useBlur || ParticleText.useSineShift) {
                        this.drawPoints();
                        this.memSrc.newPixels(0, 0, ParticleText.imgW, ParticleText.imgH);
                    }
                    this.scrG.drawImage(this.bb, 0, 0, null);
                    final long currentTimeMillis3 = System.currentTimeMillis();
                    long n3;
                    if (currentTimeMillis3 - currentTimeMillis2 < n2) {
                        n3 = n2 - (currentTimeMillis3 - currentTimeMillis2);
                    }
                    else {
                        n3 = 1L;
                    }
                    try {
                        Thread.sleep(n3);
                    }
                    catch (InterruptedException ex) {}
                }
                ++n;
                this.newCred(this.credSplit[n]);
            }
            this.scrG.drawImage(this.bb, 0, 0, null);
            final long currentTimeMillis4 = System.currentTimeMillis();
            int n4;
            if (currentTimeMillis4 - currentTimeMillis < n2) {
                n4 = n2 - (int)(currentTimeMillis4 - currentTimeMillis);
            }
            else {
                n4 = 1;
            }
            try {
                Thread.sleep(n4);
            }
            catch (InterruptedException ex2) {}
        }
        if (this.destroy) {
            this.posX = null;
            this.posY = null;
            this.destX = null;
            this.destY = null;
            this.finPoint = null;
            this.pointCol = null;
            this.pix = null;
            this.bb = null;
            this.imgText = null;
            this.memSrc = null;
            System.gc();
        }
    }
    
    public void getParams() {
        final String s = new String("");
        final Integer n = new Integer(0);
        final Float n2 = new Float(0.0f);
        final Integer intParam = this.getIntParam("width");
        if (intParam != null) {
            final int intValue = intParam;
            if (intValue > 5 && intValue < 2000) {
                ParticleText.imgW = intValue;
            }
        }
        final Integer intParam2 = this.getIntParam("height");
        if (intParam2 != null) {
            final int intValue2 = intParam2;
            if (intValue2 > 5 && intValue2 < 1000) {
                ParticleText.imgH = intValue2;
            }
        }
        final Integer intParam3 = this.getIntParam("targetFPS");
        if (intParam3 != null) {
            final int intValue3 = intParam3;
            if (intValue3 > 1 && intValue3 < 500) {
                ParticleText.targetFPS = intValue3;
            }
        }
        final Integer intParam4 = this.getIntParam("morphDelayMs");
        if (intParam4 != null) {
            final int intValue4 = intParam4;
            if (intValue4 >= 0 && intValue4 < 60000) {
                ParticleText.morphDelay = intValue4;
            }
        }
        final Integer hexParam = this.getHexParam("backColor");
        if (hexParam != null) {
            final int intValue5 = hexParam;
            if (intValue5 >= 0 && intValue5 < 16777216) {
                ParticleText.backColor = new Color(intValue5);
            }
        }
        final Integer hexParam2 = this.getHexParam("textColor");
        if (hexParam2 != null) {
            final int intValue6 = hexParam2;
            if (intValue6 >= 0 && intValue6 < 16777216) {
                ParticleText.fontColor = new Color(intValue6);
            }
        }
        final Integer intParam5 = this.getIntParam("fontSize");
        if (intParam5 != null) {
            final int intValue7 = intParam5;
            if (intValue7 > 3 && intValue7 < 200) {
                ParticleText.fontSize = intValue7;
            }
        }
        final Float floatParam = this.getFloatParam("sineShiftSpeed");
        if (floatParam != null) {
            final float floatValue = floatParam;
            if (Math.abs(floatValue) < 1000.0f) {
                ParticleText.waveShiftSpeed = floatValue;
            }
        }
        final Float floatParam2 = this.getFloatParam("sinePeriodFactor");
        if (floatParam2 != null) {
            final float floatValue2 = floatParam2;
            if (floatValue2 >= 0.0f && floatValue2 < 100.0f) {
                this.periodFactor = floatValue2;
            }
        }
        final Float floatParam3 = this.getFloatParam("sineScaleFactor");
        if (floatParam3 != null) {
            final float floatValue3 = floatParam3;
            if (floatValue3 >= 0.0f && floatValue3 < 100.0f) {
                this.scaleFactor = floatValue3;
            }
        }
        final String strParam = this.getStrParam("useBlur");
        if (strParam != null) {
            ParticleText.useBlur = strParam.toLowerCase().equals("true");
        }
        final String strParam2 = this.getStrParam("useSineShift");
        if (strParam2 != null) {
            ParticleText.useSineShift = strParam2.toLowerCase().equals("true");
        }
        final String strParam3 = this.getStrParam("fontName");
        if (strParam3 != null) {
            ParticleText.fontName = strParam3;
        }
        final String strParam4 = this.getStrParam("fontBold");
        if (strParam4 != null) {
            if (strParam4.toLowerCase().equals("true")) {
                ParticleText.fontBold = 1;
            }
            else {
                ParticleText.fontBold = 0;
            }
        }
        final String strParam5 = this.getStrParam("fontItalic");
        if (strParam5 != null) {
            if (strParam5.toLowerCase().equals("true")) {
                ParticleText.fontItalic = 2;
            }
            else {
                ParticleText.fontItalic = 0;
            }
        }
        final String strParam6 = this.getStrParam("displayText");
        if (strParam6 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(strParam6, "|");
            this.credSplit = new String[stringTokenizer.countTokens()];
            int n3 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                this.credSplit[n3] = stringTokenizer.nextToken();
                ++n3;
            }
        }
    }
    
    public Integer getIntParam(final String s) {
        final Integer n = new Integer(0);
        Integer n2;
        try {
            n2 = new Integer(Integer.parseInt(this.getParameter(s)));
        }
        catch (Exception ex) {
            return null;
        }
        return n2;
    }
    
    public Float getFloatParam(final String s) {
        final Float n = new Float(0.0f);
        Float n2;
        try {
            n2 = new Float(Integer.parseInt(this.getParameter(s)) / 1000.0f);
            System.out.println("Float param " + s + ": " + n2);
        }
        catch (Exception ex) {
            return null;
        }
        return n2;
    }
    
    public Integer getHexParam(final String s) {
        final Integer n = new Integer(0);
        Integer n2;
        try {
            n2 = new Integer(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {
            return null;
        }
        return n2;
    }
    
    public String getStrParam(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null || parameter == "") {
            return null;
        }
        return parameter;
    }
    
    static {
        ParticleText.imgW = 640;
        ParticleText.imgH = 120;
        ParticleText.targetFPS = 40;
        ParticleText.morphDelay = 1500;
        ParticleText.fontSize = 36;
        ParticleText.fontBold = 1;
        ParticleText.fontItalic = 0;
        ParticleText.useBlur = true;
        ParticleText.useSineShift = true;
        ParticleText.backColor = Color.black;
        ParticleText.fontColor = new Color(255, 245, 170);
        ParticleText.waveShiftSpeed = 0.1f;
        ParticleText.fontName = "Arial";
    }
}
