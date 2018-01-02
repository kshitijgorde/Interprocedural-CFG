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

public final class DuriusWaterPic extends WrApp implements Runnable
{
    private Screen32 screenarea;
    private Screen32 texture;
    private Waterpic water;
    int texturewidth;
    int textureheight;
    int timeleft;
    private boolean noise;
    private boolean mouse;
    private boolean timer;
    private int scale;
    
    public DuriusWaterPic() {
        this.scale = 100;
    }
    
    public void init() {
        this.intAppKey = 15637;
        super.init();
        (this.screenarea = new Screen32(this.intAppletWidth, this.intAppletHeight)).clear(this.intBackgroundColor);
        this.dcm = new DirectColorModel(32, 16711680, 65280, 255);
        (this.mis = new MemoryImageSource(this.screenarea.getwidth(), this.screenarea.getheight(), this.dcm, this.screenarea.getdata(), 0, this.screenarea.getwidth())).setAnimated(true);
        this.mis.setFullBufferUpdates(true);
        this.img = this.createImage(this.mis);
        this.showStatus("Applet initializing.");
        this.s = this.getParameter("waterscale");
        if (this.s != null) {
            this.scale = Integer.parseInt(this.s);
        }
        this.texture = new Screen32(this.intAppletWidth, this.intAppletHeight);
        this.water = new Waterpic(this.intAppletWidth * this.scale / 100, this.intAppletHeight * this.scale / 100);
        this.noise = false;
        this.timer = false;
        this.timeleft = 0;
        this.mouse = true;
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
        this.s = this.getParameter("image");
        final Image image = this.getImage(this.getDocumentBase(), this.s);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            this.showStatus("InterruptedException");
        }
        if (mediaTracker.isErrorAny()) {
            this.boolImageError = true;
            this.strBrokenImageName = this.s;
            this.boolInitialized = true;
            return;
        }
        this.texturewidth = image.getWidth(this);
        this.textureheight = image.getHeight(this);
        this.texture.load(image);
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
            this.water.setDot(n, n2, this.water.dotdepth, this.water.dotsize, this.intAppletWidth, this.intAppletHeight);
        }
    }
    
    public final void render() {
        this.water.flip();
        if (this.noise) {
            if (this.timer) {
                if (this.timeleft > 0) {
                    this.water.setDot((int)(Math.random() * (this.water.width >> 1) + (this.water.width >> 2) - (this.water.ndotsize >> 1)), (int)(Math.random() * (this.water.height >> 1) + (this.water.height >> 2) - (this.water.ndotsize >> 1)), this.water.dotdepth / 2, this.water.ndotsize, this.water.width, this.water.height);
                    this.timeleft -= (int)DuriusWaterPic.lngFPS;
                }
            }
            else {
                this.water.setDot((int)(Math.random() * (this.water.width >> 1) + (this.water.width >> 2) - (this.water.ndotsize >> 1)), (int)(Math.random() * (this.water.height >> 1) + (this.water.height >> 2) - (this.water.ndotsize >> 1)), this.water.dotdepth / 2, this.water.ndotsize, this.water.width, this.water.height);
            }
        }
        this.water.render(this.screenarea, this.texture);
        this.mis.newPixels();
        this.gfx.drawImage(this.img, 0, 0, null);
    }
}
