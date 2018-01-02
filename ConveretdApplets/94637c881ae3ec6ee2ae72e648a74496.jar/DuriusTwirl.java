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

public final class DuriusTwirl extends WrApp implements Runnable
{
    private Screen32 screenarea;
    private Screen32 texture;
    private Light white;
    private double[] sqrt;
    private int texturewidth;
    private int textureheight;
    private int light;
    private int ang;
    private double angle;
    private double xadd;
    private double yadd;
    private double counter2;
    private double counter3;
    private double counter;
    
    public DuriusTwirl() {
        this.light = 128;
        this.ang = 40;
    }
    
    public final void init() {
        this.intAppKey = 17565;
        super.init();
        (this.screenarea = new Screen32(this.intAppletWidth, this.intAppletHeight)).clear(this.intBackgroundColor);
        this.dcm = new DirectColorModel(32, 16711680, 65280, 255);
        this.mis = new MemoryImageSource(this.screenarea.getwidth(), this.screenarea.getheight(), this.dcm, this.screenarea.getdata(), 0, this.screenarea.getwidth());
        this.img = this.createImage(this.mis);
        this.s = this.getParameter("image");
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
        this.counter = 0.0;
        this.angle = 0.0;
        this.s = this.getParameter("angle");
        if (this.s != null) {
            this.ang = Integer.parseInt(this.s);
        }
        int int1 = 70;
        this.s = this.getParameter("lightsize");
        if (this.s != null) {
            int1 = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("light");
        if (this.s != null) {
            this.light = Integer.parseInt(this.s);
        }
        this.texture = new Screen32(this.texturewidth, this.textureheight);
        (this.white = new Light(int1, 15.0)).createPhongBall(204, 204, 204);
        this.texture.load(image);
        this.boolInitialized = true;
    }
    
    public final void render() {
        this.twirl();
        if (this.light != 0) {
            this.white.addFrame(this.screenarea.width, this.screenarea.height, 1.0);
            this.white.illuminate(this.screenarea, this.screenarea);
        }
        this.angle = Math.sin(this.counter) * this.ang;
        this.xadd = Math.sin(this.counter2) * (this.intAppletWidth >> 1);
        this.yadd = Math.cos(this.counter3) * (this.intAppletHeight >> 1);
        this.counter -= 0.054;
        this.counter2 += 0.038;
        this.counter3 += 0.041;
        this.img.flush();
        this.gfx.drawImage(this.img, 0, 0, null);
    }
    
    private final void twirl() {
        double n = this.intAppletWidth / 2;
        if (n > this.intAppletHeight / 2) {
            n = this.intAppletHeight / 2;
        }
        final int n2 = this.intAppletWidth / 2 + (int)this.xadd;
        final int n3 = this.intAppletHeight / 2 + (int)this.yadd;
        for (int i = 0; i < this.intAppletHeight; ++i) {
            for (int j = 0; j < this.intAppletWidth; ++j) {
                final double n4 = j - n2;
                final double n5 = i - n3;
                final double n6 = (this.angle - Math.sqrt(n4 * n4 + n5 * n5) * this.angle / n) * 3.141592654 / 180.0;
                final double sin = Math.sin(n6);
                final double cos = Math.cos(n6);
                final int n7 = (int)(n4 * cos - n5 * sin + n2);
                final int n8 = (int)(n4 * sin + n5 * cos + n3);
                if (n7 > 0 && n7 < this.intAppletWidth - 1 && n8 > 0 && n8 < this.intAppletHeight - 1) {
                    this.screenarea.data[this.screenarea.ytab[i] + j] = this.texture.data[this.texture.ytab[n8] + n7];
                }
                else {
                    this.screenarea.data[this.screenarea.ytab[i] + j] = this.intBackgroundColor;
                }
            }
        }
    }
}
