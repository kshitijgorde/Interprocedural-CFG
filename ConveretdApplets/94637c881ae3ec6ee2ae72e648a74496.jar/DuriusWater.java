import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class DuriusWater extends WrApp implements Runnable
{
    private Screen32 screenarea;
    private Screen32 texture;
    private Screen32 opentexture;
    private Water water;
    int texturewidth;
    int textureheight;
    int timeleft;
    private boolean noise;
    private boolean mouse;
    private boolean timer;
    private boolean usestexture;
    
    public DuriusWater() {
        this.usestexture = false;
    }
    
    public void init() {
        this.intAppKey = 11128;
        super.init();
        (this.screenarea = new Screen32(this.intAppletWidth, this.intAppletHeight)).clear(this.intBackgroundColor);
        this.dcm = new DirectColorModel(32, 16711680, 65280, 255);
        this.mis = new MemoryImageSource(this.screenarea.getwidth(), this.screenarea.getheight(), this.dcm, this.screenarea.getdata(), 0, this.screenarea.getwidth());
        this.img = this.createImage(this.mis);
        this.showStatus("Applet initializing.");
        this.s = this.getParameter("image");
        if (this.s != null) {
            this.usestexture = true;
            final Image image = this.getImage(this.getDocumentBase(), this.s);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            if (mediaTracker.isErrorAny()) {
                this.boolImageError = true;
                this.strBrokenImageName = this.s;
            }
            this.texturewidth = image.getWidth(this);
            this.textureheight = image.getHeight(this);
            (this.opentexture = new Screen32(this.texturewidth, this.textureheight)).load(image);
        }
        else {
            this.texturewidth = 3;
            this.textureheight = 3;
            this.opentexture = new Screen32(this.texturewidth, this.textureheight);
        }
        this.water = new Water(this.intAppletWidth, this.intAppletHeight);
        this.noise = false;
        this.mouse = true;
        this.timer = false;
        this.s = this.getParameter("mouse");
        if (this.s != null) {
            this.water.dotsize = Integer.parseInt(this.s);
            if (Integer.parseInt(this.s) == 0) {
                this.mouse = false;
            }
        }
        this.s = this.getParameter("noise");
        if (this.s != null) {
            this.water.ndotsize = Integer.parseInt(this.s);
            if (Integer.parseInt(this.s) != 0) {
                this.s = this.getParameter("timer");
                if (this.s != null && Integer.parseInt(this.s) >= 1) {
                    this.timer = true;
                    this.timeleft = Integer.parseInt(this.s);
                }
                this.noise = true;
            }
        }
        this.s = this.getParameter("dim");
        if (this.s != null) {
            this.water.dim = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("strength");
        if (this.s != null) {
            this.water.dotdepth = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("logoheight");
        if (this.s != null) {
            this.water.logoheight = 512 + Integer.parseInt(this.s);
            this.water.logoheight2 = 512 - Integer.parseInt(this.s);
        }
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        int intValue4 = 0;
        int intValue5 = 0;
        this.s = this.getParameter("col1");
        if (this.s != null) {
            intValue = Integer.valueOf(this.s, 16);
        }
        this.s = this.getParameter("col2");
        if (this.s != null) {
            intValue2 = Integer.valueOf(this.s, 16);
        }
        this.s = this.getParameter("col3");
        if (this.s != null) {
            intValue3 = Integer.valueOf(this.s, 16);
        }
        this.s = this.getParameter("col4");
        if (this.s != null) {
            intValue4 = Integer.valueOf(this.s, 16);
        }
        this.s = this.getParameter("col5");
        if (this.s != null) {
            intValue5 = Integer.valueOf(this.s, 16);
        }
        this.water.createPalette(intValue >> 16 & 0xFF, intValue >> 8 & 0xFF, intValue & 0xFF, intValue2 >> 16 & 0xFF, intValue2 >> 8 & 0xFF, intValue2 & 0xFF, intValue3 >> 16 & 0xFF, intValue3 >> 8 & 0xFF, intValue3 & 0xFF, intValue4 >> 16 & 0xFF, intValue4 >> 8 & 0xFF, intValue4 & 0xFF, intValue5 >> 16 & 0xFF, intValue5 >> 8 & 0xFF, intValue5 & 0xFF);
        this.texture = new Screen32(this.intAppletWidth, this.intAppletHeight);
        int n = (this.intAppletWidth >> 1) - (this.texturewidth >> 1) + ((this.intAppletHeight >> 1) - (this.textureheight >> 1)) * this.intAppletWidth;
        for (int i = 0; i < this.textureheight; ++i) {
            for (int j = 0; j < this.texturewidth; ++j) {
                this.texture.data[n] = this.opentexture.data[j + this.opentexture.ytab[i]];
                ++n;
            }
            n += this.intAppletWidth - this.texturewidth;
        }
        this.boolInitialized = true;
        this.showStatus("");
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        super.mouseMoved(mouseEvent);
        if (this.mouse) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            int n = x - this.water.dotsize / 2;
            int n2 = y - this.water.dotsize / 2;
            if (n > this.intAppletWidth - this.water.dotsize) {
                n = this.intAppletWidth - this.water.dotsize;
            }
            if (n2 > this.intAppletHeight - this.water.dotsize) {
                n2 = this.intAppletHeight - this.water.dotsize;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            this.water.setDot(n, n2, this.water.dotdepth, this.water.dotsize);
        }
    }
    
    public final void render() {
        this.water.flip();
        if (this.noise) {
            if (this.timer) {
                if (this.timeleft > 0) {
                    this.water.setDot((int)(Math.random() * (this.water.width >> 1) + (this.water.width >> 2) - (this.water.ndotsize >> 1)), (int)(Math.random() * (this.water.height >> 1) + (this.water.height >> 2) - (this.water.ndotsize >> 1)), this.water.dotdepth, this.water.ndotsize);
                    this.timeleft -= (int)DuriusWater.lngFPS;
                }
            }
            else {
                this.water.setDot((int)(Math.random() * (this.water.width >> 1) + (this.water.width >> 2) - (this.water.ndotsize >> 1)), (int)(Math.random() * (this.water.height >> 1) + (this.water.height >> 2) - (this.water.ndotsize >> 1)), this.water.dotdepth, this.water.ndotsize);
            }
        }
        if (this.usestexture) {
            this.water.calcOverlay(this.screenarea, this.texture);
        }
        else {
            this.water.calcWater(this.screenarea);
        }
        this.img.flush();
        this.gfx.drawImage(this.img, 0, 0, null);
    }
}
