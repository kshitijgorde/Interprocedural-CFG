import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class car extends sprite
{
    private static final int totalPics = 2;
    private static final int picWidth = 61;
    private static final int picHeight = 24;
    private Image[] pics;
    private static Image carsUnpainted;
    private static final String rawImageName = "cars.gif";
    private static int[] carRawData;
    private static int[] carData;
    private static int[] colorTable;
    private static final int transparentColor = 8421504;
    private AudioClip honkSound;
    private final int timeBetweenHonks = 700;
    private long timeSinceHonk;
    private int picnr;
    private int moveStatus;
    private int speed;
    
    public car(final double n, final double n2, final double n3, final Graphics offScrGC, final Applet parentApplet) {
        this.pics = new Image[2];
        this.picnr = 1;
        this.speed = 6;
        super.parentApplet = parentApplet;
        super.offScrGC = offScrGC;
        this.pics = new Image[2];
        super.tracker = new MediaTracker(super.parentApplet);
        (this.honkSound = super.parentApplet.getAudioClip(super.parentApplet.getCodeBase(), "horn.au")).play();
        this.honkSound.stop();
        super.x_pos = -100;
        super.y_pos = -100;
        super.collision = new Rectangle(3, 6, 55, 16);
        if (car.carsUnpainted == null) {
            try {
                car.carsUnpainted = super.parentApplet.getImage(super.parentApplet.getCodeBase(), "cars.gif");
                super.tracker.addImage(car.carsUnpainted, 99);
                super.tracker.waitForID(99);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final PixelGrabber pixelGrabber = new PixelGrabber(car.carsUnpainted, 0, 0, 122, 24, car.carRawData, 0, 122);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {}
        }
        this.makeColorTable(n, n2, n3);
        this.prepareAndPaint();
    }
    
    public void honk() {
        if (System.currentTimeMillis() - this.timeSinceHonk > 700L) {
            this.timeSinceHonk = System.currentTimeMillis();
            this.honkSound.play();
        }
    }
    
    public boolean inHonkRange(final sprite sprite) {
        final Rectangle rectangle = new Rectangle(super.collision.x + super.x_pos, super.collision.y + super.y_pos, super.collision.width, super.collision.height);
        final Rectangle rectangle2 = new Rectangle(sprite.collision.x + sprite.x_pos, sprite.collision.y + sprite.y_pos, sprite.collision.width, sprite.collision.height);
        if (this.moveStatus == 1) {
            final Rectangle rectangle3 = rectangle;
            rectangle3.x += 20 * this.speed;
        }
        else if (this.moveStatus == 2) {
            final Rectangle rectangle4 = rectangle;
            rectangle4.x -= 20 * this.speed;
        }
        return rectangle.intersects(rectangle2);
    }
    
    public void initDrive(final int moveStatus, final int x_pos, final int y_pos, final int speed) {
        super.x_pos = x_pos;
        super.y_pos = y_pos;
        this.moveStatus = moveStatus;
        if (moveStatus == 1) {
            this.picnr = 1;
        }
        else {
            this.picnr = 0;
        }
        this.speed = speed;
    }
    
    public void drive() {
        if (this.moveStatus == 0) {
            return;
        }
        if (this.moveStatus == 1) {
            super.x_pos += this.speed;
            return;
        }
        if (this.moveStatus == 2) {
            super.x_pos -= this.speed;
        }
    }
    
    public int getStatus() {
        return this.moveStatus;
    }
    
    public void setStatus(final int moveStatus) {
        this.moveStatus = moveStatus;
    }
    
    public void stop() {
        this.moveStatus = 0;
    }
    
    public void prepareAndPaint() {
        for (int i = 0; i < 2; ++i) {
            this.pics[i] = super.parentApplet.createImage(new MemoryImageSource(61, 24, car.carData, i * 61, 122));
            super.tracker.addImage(this.pics[i], 2);
        }
        for (int j = 0; j < 2928; ++j) {
            if (car.carRawData[j] != 8421504) {
                car.carData[j] = car.colorTable[car.carRawData[j] & 0xFF];
            }
            else {
                car.carData[j] = car.carRawData[j];
            }
        }
        for (int k = 0; k < 2; ++k) {
            this.pics[k].flush();
        }
        try {
            super.tracker.waitForID(2);
        }
        catch (InterruptedException ex) {}
    }
    
    public void draw() {
        super.offScrGC.drawImage(this.pics[this.picnr], super.x_pos, super.y_pos, super.parentApplet);
    }
    
    public void makeColorTable(final double n, final double n2, final double n3) {
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 2.0 * n + 0.01;
        double n8 = 2.0 * n2 + 0.01;
        double n9 = 2.0 * n3 + 0.01;
        int n10 = 0;
        for (int i = 0; i < 4; ++i) {
            while (n4 < 255.4 && n5 < 255.4 && n6 < 255.4 && n10 < 256) {
                car.colorTable[n10] = (0xFF000000 | (int)n4 << 16 | (int)n5 << 8 | (int)n6);
                n4 += n7;
                n5 += n8;
                n6 += n9;
                ++n10;
            }
            if (n4 > 255.4) {
                n7 = 0.0;
                n4 = 255.1;
            }
            if (n5 > 255.4) {
                n8 = 0.0;
                n5 = 255.1;
            }
            if (n6 > 255.4) {
                n9 = 0.0;
                n6 = 255.1;
            }
            final double n11 = 2.0 * (n + n2 + n3) / (0.01 + n7 + n8 + n9);
            n7 *= n11;
            n8 *= n11;
            n9 *= n11;
        }
    }
    
    static {
        car.carsUnpainted = null;
        car.carRawData = new int[23424];
        car.carData = new int[23424];
        car.colorTable = new int[256];
    }
}
