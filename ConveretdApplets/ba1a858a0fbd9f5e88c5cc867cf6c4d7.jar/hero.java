import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Rectangle;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class hero extends sprite
{
    private final String rawImageName = "cliffies2.gif";
    private final int totalPics = 12;
    private final int picWidth = 32;
    private final int picHeight = 32;
    private Image[] pics;
    private Image rawImage;
    private int picnr;
    private boolean swingUp;
    private int moveStatus;
    private boolean resetMoves;
    private int move;
    private final int leap = 4;
    private Polygon moveLimit;
    private final int timeBetweenMoves = 50;
    private long timeSinceLastMove;
    private boolean isBlinking;
    private int blinks;
    private final int maxBlinks = 20;
    private boolean blinkSwitch;
    
    public hero(final Polygon moveLimit, final MediaTracker tracker, final int n, final Graphics offScrGC, final Applet parentApplet) {
        this.picnr = 4;
        this.swingUp = true;
        this.resetMoves = true;
        this.isBlinking = false;
        this.blinkSwitch = true;
        super.parentApplet = parentApplet;
        super.offScrGC = offScrGC;
        this.moveLimit = moveLimit;
        super.x_pos = 385;
        super.y_pos = 30;
        this.pics = new Image[12];
        super.tracker = tracker;
        super.collision = new Rectangle(6, 17, 19, 13);
        try {
            this.rawImage = super.parentApplet.getImage(super.parentApplet.getCodeBase(), "cliffies2.gif");
            super.tracker.addImage(this.rawImage, n);
            for (int i = 0; i < 12; ++i) {
                this.pics[i] = super.parentApplet.createImage(new FilteredImageSource(this.rawImage.getSource(), new CropImageFilter(i * 32, 0, 32, 32)));
                super.tracker.addImage(this.pics[i], n);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.timeSinceLastMove = System.currentTimeMillis();
    }
    
    private boolean insideLimits(final int n, final int n2) {
        return this.moveLimit.inside(n + super.collision.x, n2 + super.collision.y) && this.moveLimit.inside(n + super.collision.x, n2 + super.collision.y + super.collision.height) && this.moveLimit.inside(n + super.collision.x + super.collision.width, n2 + super.collision.y) && this.moveLimit.inside(n + super.collision.x + super.collision.width, n2 + super.collision.y + super.collision.height);
    }
    
    public void checkMoves(final Event event) {
        if (!this.insideLimits(super.x_pos, super.y_pos)) {
            switch (this.move) {
                case 1: {
                    do {
                        ++super.x_pos;
                    } while (!this.insideLimits(super.x_pos, super.y_pos));
                    break;
                }
                case 2: {
                    do {
                        --super.x_pos;
                    } while (!this.insideLimits(super.x_pos, super.y_pos));
                    break;
                }
                case 3: {
                    do {
                        ++super.y_pos;
                    } while (!this.insideLimits(super.x_pos, super.y_pos));
                    break;
                }
                case 4: {
                    do {
                        --super.y_pos;
                    } while (!this.insideLimits(super.x_pos, super.y_pos));
                    break;
                }
            }
        }
        this.move = 0;
        switch (event.key) {
            case 1006: {
                if (this.insideLimits(super.x_pos - 4 + 2, super.y_pos)) {
                    this.move = 1;
                    break;
                }
                break;
            }
            case 1007: {
                if (this.insideLimits(super.x_pos + 4 - 2, super.y_pos)) {
                    this.move = 2;
                    break;
                }
                break;
            }
            case 1004: {
                if (this.insideLimits(super.x_pos, super.y_pos + 2 - 4)) {
                    this.move = 3;
                    break;
                }
                break;
            }
            case 1005: {
                if (this.insideLimits(super.x_pos, super.y_pos - 2 + 4)) {
                    this.move = 4;
                    break;
                }
                break;
            }
        }
        if (this.moveStatus != this.move && !this.resetMoves) {
            this.resetMoves = true;
        }
        this.moveStatus = this.move;
    }
    
    public void walk() {
        if (this.moveStatus == 0 || System.currentTimeMillis() - this.timeSinceLastMove < 50L) {
            return;
        }
        this.timeSinceLastMove = System.currentTimeMillis();
        if (this.resetMoves) {
            switch (this.moveStatus) {
                case 1: {
                    this.picnr = 7;
                    break;
                }
                case 2: {
                    this.picnr = 1;
                    break;
                }
                case 3: {
                    this.picnr = 10;
                    break;
                }
                case 4: {
                    this.picnr = 4;
                    break;
                }
            }
            this.resetMoves = false;
        }
        if (this.swingUp) {
            ++this.picnr;
        }
        else {
            --this.picnr;
        }
        if (this.picnr % 3 == 0) {
            this.swingUp = true;
        }
        else if ((this.picnr + 1) % 3 == 0) {
            this.swingUp = false;
        }
        switch (this.moveStatus) {
            case 1: {
                super.x_pos -= 4;
            }
            case 2: {
                super.x_pos += 4;
            }
            case 3: {
                super.y_pos -= 4;
            }
            case 4: {
                super.y_pos += 4;
            }
            default: {}
        }
    }
    
    public void stop() {
        this.moveStatus = 0;
        this.resetMoves = true;
    }
    
    public void startBlinking() {
        this.isBlinking = true;
    }
    
    public void draw() {
        if (!this.isBlinking) {
            super.offScrGC.drawImage(this.pics[this.picnr], super.x_pos, super.y_pos, super.parentApplet);
            return;
        }
        final boolean blinkSwitch = this.blinkSwitch ^ true;
        this.blinkSwitch = blinkSwitch;
        if (blinkSwitch) {
            super.offScrGC.drawImage(this.pics[this.picnr], super.x_pos, super.y_pos, super.parentApplet);
        }
        if (++this.blinks == 20) {
            this.blinks = 0;
            this.isBlinking = false;
        }
    }
    
    public void resetPos() {
        this.move(385, 30);
        this.picnr = 4;
    }
}
