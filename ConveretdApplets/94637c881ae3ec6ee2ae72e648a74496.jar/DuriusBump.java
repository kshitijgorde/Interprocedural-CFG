import java.awt.Image;
import java.util.StringTokenizer;
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

public final class DuriusBump extends WrApp implements Runnable
{
    private Screen32 screenarea;
    private Screen32 textureA;
    private BumpMap bump;
    private Light[] light;
    private int intBumpHeight;
    private int intLightCount;
    private int intAmbience;
    private double span;
    private boolean boolClearIndividual;
    
    public DuriusBump() {
        this.intBumpHeight = 200;
        this.intLightCount = 0;
        this.intAmbience = 0;
        this.span = 1.0;
        this.boolClearIndividual = false;
    }
    
    public final void init() {
        this.intAppKey = 16133;
        super.init();
        (this.screenarea = new Screen32(this.intAppletWidth, this.intAppletHeight)).clear(this.intBackgroundColor);
        this.dcm = new DirectColorModel(32, 16711680, 65280, 255);
        this.mis = new MemoryImageSource(this.screenarea.getwidth(), this.screenarea.getheight(), this.dcm, this.screenarea.getdata(), 0, this.screenarea.getwidth());
        this.img = this.createImage(this.mis);
        this.showStatus("Applet initializing.");
        this.intBumpHeight = 200;
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
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final Screen32 screen32 = new Screen32(width, height);
        this.textureA = new Screen32(width, height);
        screen32.load(image);
        while (this.getParameter("light" + String.valueOf(this.intLightCount + 1)) != null) {
            ++this.intLightCount;
        }
        this.light = new Light[this.intLightCount];
        for (int i = 0; i < this.intLightCount; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("light" + String.valueOf(i + 1)));
            int int1 = 120;
            int intValue = 16777215;
            int int2 = 15;
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                int2 = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (stringTokenizer.hasMoreTokens()) {
                intValue = Integer.valueOf(stringTokenizer.nextToken(), 16);
            }
            (this.light[i] = new Light(int1, (double)int2)).createPhongBall(intValue >> 16 & 0xFF, intValue >> 8 & 0xFF, intValue & 0xFF);
        }
        this.s = this.getParameter("bumpheight");
        if (this.s != null) {
            this.intBumpHeight = Integer.parseInt(this.s);
        }
        this.s = this.getParameter("ambience");
        if (this.s != null) {
            this.intAmbience = Integer.valueOf(this.s, 16);
        }
        this.s = this.getParameter("span");
        if (this.s != null) {
            this.span = Integer.parseInt(this.s) / 100.0;
        }
        if (this.span < 0.05) {
            this.span = 0.05;
        }
        this.light[0].illuminateAmbient(screen32, this.textureA, this.intAmbience >> 16 & 0xFF, this.intAmbience >> 8 & 0xFF, this.intAmbience & 0xFF);
        this.screenarea.copy(this.textureA);
        if (this.intBumpHeight != 0) {
            this.bump = new BumpMap(screen32, this.intBumpHeight);
        }
        int n = 0;
        for (int j = 0; j < this.intLightCount; ++j) {
            n += this.light[j].getwidthheight();
        }
        if (n * 100 / 70 < this.screenarea.getwidthheight()) {
            this.boolClearIndividual = true;
        }
        else {
            this.boolClearIndividual = false;
        }
        this.boolInitialized = true;
        this.showStatus("");
    }
    
    public final void render() {
        for (int i = 0; i < this.intLightCount; ++i) {
            this.light[i].addFrame(this.screenarea.width, this.screenarea.height, this.span);
        }
        if (this.boolClearIndividual) {
            for (int j = 0; j < this.intLightCount; ++j) {
                this.light[j].restoreOld(this.textureA, this.screenarea);
            }
        }
        else {
            this.screenarea.copy(this.textureA);
        }
        if (this.intBumpHeight != 0) {
            for (int k = 0; k < this.intLightCount; ++k) {
                this.light[k].illuminateBump(this.textureA, this.screenarea, this.bump);
            }
        }
        else {
            for (int l = 0; l < this.intLightCount; ++l) {
                this.light[l].illuminate(this.textureA, this.screenarea);
            }
        }
        this.img.flush();
        this.gfx.drawImage(this.img, 0, 0, null);
    }
}
