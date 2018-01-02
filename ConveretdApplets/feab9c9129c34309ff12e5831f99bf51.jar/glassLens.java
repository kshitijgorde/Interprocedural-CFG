import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class glassLens extends Applet implements Runnable
{
    private Thread m_backg;
    int xPos;
    int yPos;
    int ymov;
    int xmov;
    int[] xCirc;
    int m_size;
    private final int CIRCLE = 0;
    private final int SQUARE = 1;
    private final int DIAMOND = 2;
    private final int PLUS = 3;
    private final int TESTGEOMETRY = 4;
    private int arrayType;
    final int SMALL = 0;
    final int LARGE = 1;
    private boolean loadedSmall;
    private boolean loadedLarge;
    private Image m_Image;
    private Image m_Image2;
    int im_x;
    int im_y;
    Image buf;
    Graphics gbuf;
    int screen_W;
    int screen_H;
    int messB;
    boolean xonly;
    boolean yonly;
    int move;
    int pmag;
    int precision;
    int origprecision;
    boolean needsRepaint;
    private Graphics m_Graphics;
    private boolean m_fStandAlone;
    private String m_img;
    private String m_img0;
    private int m_radius;
    private int mag;
    private int smallFac;
    private final String PARAM_img = "img";
    private final String PARAM_img0 = "thumb";
    private final String PARAM_size = "diameter";
    private final String PARAM_power = "power";
    private final String PARAM_lopower = "lopower";
    private final String PARAM_shape = "shape";
    private static final String[] text;
    
    public boolean mouseMove(final Event event, final int xPos, final int yPos) {
        this.needsRepaint = true;
        this.xPos = xPos;
        this.yPos = yPos;
        return true;
    }
    
    public void stop() {
        if (this.m_backg != null) {
            this.m_backg.stop();
            this.m_backg = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        ((Frame)this.getParent()).setCursor(1);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        ((Frame)this.getParent()).setCursor(0);
        return true;
    }
    
    void drawBackground() {
        this.gbuf.clearRect(0, 0, this.screen_W, this.screen_H);
        this.gbuf.setColor(Color.black);
        this.gbuf.setFont(new Font(this.getToolkit().getFontList()[2], 0, 60));
        this.gbuf.setColor(new Color(120, 0, 0));
        for (int i = 60; i < this.screen_H; i += 40) {
            this.gbuf.drawLine(0, i, this.screen_W, i);
        }
        this.gbuf.drawLine(80, 0, 80, this.screen_H);
        this.gbuf.setColor(new Color(0, 0, 100));
        this.gbuf.drawString("Please wait...", 80, 60);
        this.gbuf.setFont(new Font(this.getToolkit().getFontList()[2], 2, 20));
        int n = 0;
        do {
            this.gbuf.drawString(glassLens.text[n], 80, 100 + n * 40);
        } while (++n < 9);
    }
    
    void GetParameters(final String[] array) {
        final String getParameter = this.GetParameter("img", array);
        if (getParameter != null) {
            this.m_img = getParameter;
        }
        final String getParameter2 = this.GetParameter("thumb", array);
        if (getParameter2 != null) {
            this.m_img0 = getParameter2;
        }
        if (!this.m_fStandAlone) {
            final String parameter = this.getParameter("diameter");
            if (parameter != null) {
                this.m_radius = Integer.parseInt(parameter) / 2;
            }
            final String parameter2 = this.getParameter("power");
            if (parameter2 != null) {
                final int int1 = Integer.parseInt(parameter2);
                this.mag = int1;
                this.pmag = int1;
            }
            if (this.pmag == 0) {
                final boolean b = true;
                this.mag = (b ? 1 : 0);
                this.pmag = (b ? 1 : 0);
            }
            if (this.pmag < 0) {
                this.pmag = -this.pmag;
            }
            if (this.pmag > 2) {
                this.smallFac = 1;
            }
            else {
                this.smallFac = 9;
            }
            final String parameter3 = this.getParameter("lopower");
            if (parameter3 != null) {
                this.smallFac = 9 - Integer.parseInt(parameter3);
            }
            final String parameter4 = this.getParameter("shape");
            if (parameter4 != null) {
                this.arrayType = Integer.parseInt(parameter4);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.drawImage();
        if (this.pmag > 3) {
            this.drawSlowVert();
            this.drawSlowHorz();
        }
        else if (this.pmag > 1) {
            this.drawglassLensVert();
            this.drawglassLensHorz();
        }
        else {
            this.drawFineVert();
            this.drawFineHorz();
        }
        graphics.drawImage(this.buf, 0, 0, this);
        this.needsRepaint = false;
    }
    
    void drawglassLensHorz() {
        final int pmag = this.pmag;
        for (int i = this.m_size; i > 0; --i) {
            this.gbuf.copyArea(this.xPos - i, this.yPos - this.xCirc[i] * pmag, this.pmag, ((this.xCirc[i] << 1) + 1) * this.pmag, i * (1 - this.mag), 0);
            this.gbuf.copyArea(this.xPos + i, this.yPos - this.xCirc[i] * pmag, this.pmag, ((this.xCirc[i] << 1) + 1) * this.pmag, i * (this.mag - 1), 0);
        }
    }
    
    void drawFineHorz() {
        final int n = this.m_size / (this.smallFac + 1);
        final int pmag = this.pmag;
        for (int n2 = n, i = this.m_size - n2; i > 0; i -= this.smallFac, --n2) {
            this.gbuf.copyArea(this.xPos - i, this.yPos - this.xCirc[i + n2] * pmag, this.smallFac + 1, ((this.xCirc[i + n2] << 1) + 1) * this.pmag, -n2, 0);
            this.gbuf.copyArea(this.xPos + i, this.yPos - this.xCirc[i + n2] * pmag, this.smallFac + 1, ((this.xCirc[i + n2] << 1) + 1) * this.pmag, n2, 0);
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.needsRepaint = true;
        final boolean b = false;
        this.yonly = b;
        this.xonly = b;
        ((Frame)this.getParent()).setCursor(1);
        return true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "img", "String", "Main image" }, { "thumb", "String", "Thumbnail preview image" }, { "diameter", "int", "lens diameter" }, { "power", "int", "lens magnification" }, { "lopower", "int", "lens fine magnification" }, { "shape", "int", "lens shape 0-4" } };
    }
    
    public void destroy() {
    }
    
    void drawSlowVert() {
        int n;
        int n2;
        for (n = 128, n2 = 7; (n & this.pmag) == 0x0; n >>= 1, --n2) {}
        final int n3 = this.pmag - n;
        for (int i = this.m_size; i >= 0; --i) {
            this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos - i, (this.xCirc[i] << 1) + this.pmag, 1, 0, i * (1 - this.mag));
            this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos + i, (this.xCirc[i] << 1) + this.pmag, 1, 0, i * (this.mag - 1));
            for (int j = 0; j < n2; ++j) {
                this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos - i * this.mag, (this.xCirc[i] << 1) + this.pmag, 1 << j, 0, 1 << j);
                this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos + i * this.mag, (this.xCirc[i] << 1) + this.pmag, 1 << j, 0, 1 << j);
            }
            if (n3 > 0) {
                this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos - i * this.mag, (this.xCirc[i] << 1) + this.pmag, n3, 0, 1 << n2);
                this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos + i * this.mag, (this.xCirc[i] << 1) + this.pmag, n3, 0, 1 << n2);
            }
        }
    }
    
    String GetParameter(final String s, final String[] array) {
        if (array == null) {
            return this.getParameter(s);
        }
        final String string = s + "=";
        String s2 = null;
        final int length = string.length();
        try {
            int i = 0;
            while (i < array.length) {
                if (string.equalsIgnoreCase(array[i].substring(0, length))) {
                    s2 = array[i].substring(length);
                    if (!s2.startsWith("\"")) {
                        break;
                    }
                    s2 = s2.substring(1);
                    if (s2.endsWith("\"")) {
                        s2 = s2.substring(0, s2.length() - 1);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public glassLens() {
        this.xPos = 100;
        this.yPos = 100;
        this.im_x = -1;
        this.im_y = -1;
        this.move = 50;
        this.pmag = 2;
        this.precision = 10;
        this.origprecision = 10;
        this.needsRepaint = true;
        this.m_img = "test.jpg";
        this.m_img0 = "small.gif";
        this.m_radius = 150;
        this.mag = 2;
        this.smallFac = 9;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        text = new String[] { "Downloading main image", "", "Instructions:", "1. Move mouse to move lens", "2. Click and drag Horizontally to change lens size", "3. Click and drag Vertically to change magnification", "4. Right-Click to change lens shape", "", "Creator: Xavier Potier, 1999" };
    }
    
    public void start() {
        if (this.m_backg == null) {
            (this.m_backg = new Thread(this)).start();
        }
    }
    
    void bitBlt(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.gbuf.copyArea(n, n2, n3, n4, n5 - n, n6 - n2);
    }
    
    public String getAppletInfo() {
        return "Name: glass lens\r\n" + "Author: Xavier Potier\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.metaDown()) {
            if (++this.arrayType > 4) {
                this.arrayType = 0;
            }
            this.makeCircleArray();
        }
        return true;
    }
    
    void drawImage() {
        this.gbuf.clearRect(0, 0, this.screen_W, this.screen_H);
        if (this.loadedLarge) {
            this.gbuf.drawImage(this.m_Image2, 0, 0, null);
            return;
        }
        this.drawBackground();
        if (this.loadedSmall) {
            this.gbuf.drawImage(this.m_Image, 0, this.move++ % this.screen_H, null);
        }
    }
    
    void makeCircleArray() {
        this.xCirc = new int[this.m_size + 1];
        switch (this.arrayType) {
            case 0: {
                for (int i = 0; i < this.m_size; ++i) {
                    this.xCirc[i] = (int)Math.sqrt(this.m_size * this.m_size - i * i);
                }
            }
            case 1: {
                for (int j = 0; j < this.m_size; ++j) {
                    this.xCirc[j] = this.m_size;
                }
            }
            case 2: {
                for (int k = 0; k < this.m_size; ++k) {
                    this.xCirc[this.m_size - 1 - k] = k;
                }
            }
            case 3: {
                for (int l = 0; l < this.m_size; ++l) {
                    this.xCirc[this.m_size - 1 - l] = this.m_size - (int)Math.sqrt(this.m_size * this.m_size - l * l);
                }
            }
            case 4: {
                for (int n = 0; n < this.m_size; ++n) {
                    this.xCirc[n] = (int)Math.sqrt(this.m_size * this.m_size - n * n);
                    if (n % 50 < 25) {
                        this.xCirc[n] = 0;
                    }
                }
            }
            default: {}
        }
    }
    
    public void run() {
        ((Frame)this.getParent()).setCursor(1);
        final MediaTracker mediaTracker = new MediaTracker(this);
    Label_0191:
        while (true) {
            if (this.loadedSmall && this.loadedLarge) {
                break Label_0191;
            }
            this.m_size = this.m_radius / this.mag;
            if (this.m_fStandAlone) {
                this.m_Image = Toolkit.getDefaultToolkit().getImage(this.m_img0);
                this.m_Image2 = Toolkit.getDefaultToolkit().getImage(this.m_img);
            }
            else {
                this.m_Image = this.getImage(this.getDocumentBase(), this.m_img0);
                this.m_Image2 = this.getImage(this.getDocumentBase(), this.m_img);
            }
            mediaTracker.addImage(this.m_Image, 0);
            mediaTracker.addImage(this.m_Image2, 1);
            try {
                mediaTracker.waitForID(0);
                this.loadedSmall = !mediaTracker.isErrorID(0);
                if (mediaTracker.checkID(1, true)) {
                    this.loadedLarge = !mediaTracker.isErrorID(1);
                }
            }
            catch (InterruptedException ex) {}
            this.makeCircleArray();
            while (true) {
                try {
                    while (true) {
                        if (!this.loadedSmall && mediaTracker.checkID(0) && (this.loadedSmall = !mediaTracker.isErrorID(0))) {
                            this.im_x = this.m_Image.getWidth(this);
                            this.im_y = this.m_Image.getHeight(this);
                        }
                        if (!this.loadedLarge) {
                            this.needsRepaint = true;
                            if (mediaTracker.checkID(1)) {
                                this.loadedLarge = !mediaTracker.isErrorID(1);
                            }
                        }
                        if (this.needsRepaint) {
                            this.repaint();
                        }
                        Thread.sleep(50L);
                    }
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue;
                }
                continue Label_0191;
            }
            break;
        }
    }
    
    public void init() {
        if (!this.m_fStandAlone) {
            this.GetParameters(null);
        }
        this.resize(800, 530);
        final int mag = this.mag;
        this.precision = mag;
        this.origprecision = mag;
        final int width = this.size().width;
        this.screen_W = width;
        final int height = this.size().height;
        this.screen_H = height;
        this.buf = this.createImage(width, height);
        this.gbuf = this.buf.getGraphics();
    }
    
    void drawSlowHorz() {
        int n;
        int n2;
        for (n = 128, n2 = 7; (n & this.pmag) == 0x0; n >>= 1, --n2) {}
        final int n3 = this.pmag - n;
        for (int i = this.m_size; i >= 0; --i) {
            this.gbuf.copyArea(this.xPos - i, this.yPos - this.xCirc[i] * this.pmag, 1, ((this.xCirc[i] << 1) + 1) * this.pmag, i * (1 - this.mag), 0);
            this.gbuf.copyArea(this.xPos + i, this.yPos - this.xCirc[i] * this.pmag, 1, ((this.xCirc[i] << 1) + 1) * this.pmag, i * (this.mag - 1), 0);
            for (int j = 0; j < n2; ++j) {
                this.gbuf.copyArea(this.xPos - i * this.mag, this.yPos - this.xCirc[i] * this.pmag, 1 << j, ((this.xCirc[i] << 1) + 1) * this.pmag, 1 << j, 0);
                this.gbuf.copyArea(this.xPos + i * this.mag, this.yPos - this.xCirc[i] * this.pmag, 1 << j, ((this.xCirc[i] << 1) + 1) * this.pmag, 1 << j, 0);
            }
            if (n3 > 0) {
                this.gbuf.copyArea(this.xPos - i * this.mag, this.yPos - this.xCirc[i] * this.pmag, n3, ((this.xCirc[i] << 1) + 1) * this.pmag, 1 << n2, 0);
                this.gbuf.copyArea(this.xPos + i * this.mag, this.yPos - this.xCirc[i] * this.pmag, n3, ((this.xCirc[i] << 1) + 1) * this.pmag, 1 << n2, 0);
            }
        }
    }
    
    public boolean mouseDrag(final Event event, final int xPos, final int yPos) {
        this.needsRepaint = true;
        if (!this.xonly) {
            if (yPos - this.yPos > 0) {
                ++this.ymov;
            }
            else if (yPos - this.yPos < 0) {
                --this.ymov;
            }
            if (this.ymov >= 4 || this.ymov <= -4) {
                this.precision = this.origprecision;
                this.yonly = true;
                ((Frame)this.getParent()).setCursor(8);
                if (this.pmag == 1) {
                    this.smallFac += this.ymov >> 2;
                    if (this.smallFac == 0 || this.smallFac == 11) {
                        this.smallFac -= this.ymov >> 2;
                        this.mag += -this.ymov >> 2;
                    }
                }
                else {
                    this.mag += -this.ymov >> 2;
                }
                if (this.mag > 255) {
                    this.mag &= 0xFF;
                }
                if (this.mag == 0) {
                    this.mag += -this.ymov >> 2;
                }
                this.pmag = Math.abs(this.mag);
                this.ymov = 0;
                this.m_size = this.m_radius / this.pmag;
                if (this.precision > this.pmag) {
                    this.origprecision = this.precision;
                    this.precision = this.pmag;
                }
                this.makeCircleArray();
            }
        }
        if (!this.yonly) {
            if (xPos - this.xPos > 0) {
                ++this.xmov;
            }
            else if (xPos - this.xPos < 0) {
                --this.xmov;
            }
            if (this.xmov >= 4 || this.xmov <= -4) {
                this.xonly = true;
                ((Frame)this.getParent()).setCursor(11);
                this.m_radius += this.xmov << 1;
                if (this.m_radius < 0) {
                    this.m_radius = 0;
                }
                this.xmov = 0;
                this.m_size = this.m_radius / this.pmag;
                this.makeCircleArray();
            }
        }
        this.xPos = xPos;
        this.yPos = yPos;
        return true;
    }
    
    void drawglassLensVert() {
        for (int i = this.m_size; i > 0; --i) {
            this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos - i, (this.xCirc[i] << 1) + this.pmag, this.pmag, 0, i * (1 - this.mag));
            this.gbuf.copyArea(this.xPos - this.xCirc[i], this.yPos + i, (this.xCirc[i] << 1) + this.pmag, this.pmag, 0, i * (this.mag - 1));
        }
    }
    
    void drawFineVert() {
        for (int n = this.m_size / (this.smallFac + 1), i = this.m_size - n; i > 0; i -= this.smallFac, --n) {
            this.gbuf.copyArea(this.xPos - this.xCirc[i + n], this.yPos - i, (this.xCirc[i + n] << 1) + this.pmag, this.smallFac + 1, 0, -n);
            this.gbuf.copyArea(this.xPos - this.xCirc[i + n], this.yPos + i, (this.xCirc[i + n] << 1) + this.pmag, this.smallFac + 1, 0, n);
        }
    }
}
