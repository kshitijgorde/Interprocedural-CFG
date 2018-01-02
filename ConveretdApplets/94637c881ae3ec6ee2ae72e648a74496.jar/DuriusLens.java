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

public final class DuriusLens extends WrApp implements Runnable
{
    private Screen32 screenarea;
    private Screen32 texture;
    private Lens lens;
    private int texturewidth;
    private int textureheight;
    private int mousex;
    private int mousey;
    private double xadd;
    private double yadd;
    private double span;
    private boolean boolMouse;
    private boolean boolNoise;
    private boolean boolMouseActive;
    private boolean boolMouseWasActive;
    
    public DuriusLens() {
        this.span = 1.0;
        this.boolMouse = true;
        this.boolNoise = true;
        this.boolMouseActive = false;
        this.boolMouseWasActive = false;
    }
    
    public final void init() {
        this.intAppKey = 13214;
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
            this.boolInitialized = true;
            return;
        }
        this.texturewidth = image.getWidth(this);
        this.textureheight = image.getHeight(this);
        int int1 = 100;
        int int2 = 100;
        int int3 = 19;
        int int4 = 29;
        this.s = this.getParameter("lenssizex");
        if (this.s != null) {
            int1 = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("lenssizey");
        if (this.s != null) {
            int2 = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("xadd");
        if (this.s != null) {
            int3 = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("yadd");
        if (this.s != null) {
            int4 = Integer.parseInt(this.s);
        }
        this.xadd = int3;
        this.yadd = int4;
        this.xadd /= 1000.0;
        this.yadd /= 1000.0;
        this.s = this.getParameter("span");
        if (this.s != null) {
            this.span = Integer.parseInt(this.s) / 100.0;
        }
        if (this.span < 0.05) {
            this.span = 0.05;
        }
        this.s = this.getParameter("mouse");
        if (this.s != null && Integer.parseInt(this.s) == 0) {
            this.boolMouse = false;
        }
        this.s = this.getParameter("noise");
        if (this.s != null) {
            if (Integer.parseInt(this.s) != 0) {
                this.boolNoise = true;
            }
            else {
                this.boolNoise = false;
            }
        }
        this.texture = new Screen32(this.texturewidth, this.textureheight);
        this.lens = new Lens(int1, int2, this.xadd, this.yadd, this.intBackgroundColor);
        this.s = this.getParameter("depth");
        if (this.s != null) {
            this.lens.depth = Integer.parseInt(this.s);
        }
        this.lens.createPhongDot();
        this.texture.load(image);
        this.screenarea.copy(this.texture);
        this.boolInitialized = true;
        this.showStatus(" ");
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        super.mouseEntered(mouseEvent);
        if (this.boolMouse) {
            this.boolMouseActive = true;
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        super.mouseExited(mouseEvent);
        if (this.boolMouse) {
            this.boolMouseActive = false;
        }
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        super.mouseMoved(mouseEvent);
        if (this.boolMouse) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.mousex = x;
            this.mousey = y;
        }
    }
    
    public final void render() {
        if (!this.boolImageError) {
            if (!this.boolMouseActive) {
                if (this.boolNoise) {
                    this.lens.addFrame(this.screenarea.width, this.screenarea.height, this.span);
                }
                else {
                    this.lens.setpos(1000, 1000, true);
                }
            }
            else {
                this.lens.setpos(this.mousex, this.mousey, true);
            }
            this.lens.restoreOld2(this.texture, this.screenarea);
            if (this.boolNoise || this.boolMouseActive) {
                this.lens.calcPersp(this.screenarea, this.texture);
            }
        }
        this.img.flush();
        this.gfx.drawImage(this.img, 0, 0, null);
    }
}
