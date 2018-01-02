import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Rectangle;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.util.Random;
import java.applet.AudioClip;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class dog extends sprite
{
    private final String rawImageName = "doggy.gif";
    private final int totalPics = 12;
    private final int picWidth = 40;
    private final int picHeight = 40;
    private Image[] pics;
    private Image rawImage;
    private int picnr;
    private boolean swingUp;
    private int moveStatus;
    private boolean resetMoves;
    private AudioClip barkSound;
    private final int timeBetweenBarks = 800;
    private long timeSinceBark;
    private static Random random;
    private int stepsToWalk;
    private final int maxStepsToWalk = 100;
    private final int leap = 4;
    private Polygon moveLimit;
    private int timeBetweenMoves;
    private final int maxTimeBetweenMoves = 120;
    private long timeSinceLastMove;
    
    public dog(final Polygon moveLimit, final MediaTracker tracker, final int n, final Graphics offScrGC, final Applet parentApplet) {
        this.picnr = 4;
        this.swingUp = true;
        this.resetMoves = true;
        super.parentApplet = parentApplet;
        super.offScrGC = offScrGC;
        this.moveLimit = moveLimit;
        final Rectangle boundingBox = this.moveLimit.getBoundingBox();
        super.collision = new Rectangle(13, 13, 15, 15);
        do {
            super.x_pos = Math.abs(dog.random.nextInt()) % boundingBox.width + boundingBox.x;
            super.y_pos = Math.abs(dog.random.nextInt()) % boundingBox.height + boundingBox.y;
        } while (!this.insideLimits(super.x_pos, super.y_pos));
        this.pics = new Image[12];
        super.tracker = tracker;
        (this.barkSound = super.parentApplet.getAudioClip(super.parentApplet.getCodeBase(), "bark.au")).play();
        this.barkSound.stop();
        this.timeSinceBark = System.currentTimeMillis();
        try {
            this.rawImage = super.parentApplet.getImage(super.parentApplet.getCodeBase(), "doggy.gif");
            super.tracker.addImage(this.rawImage, n);
            for (int i = 0; i < 12; ++i) {
                this.pics[i] = super.parentApplet.createImage(new FilteredImageSource(this.rawImage.getSource(), new CropImageFilter(i * 40, 0, 40, 40)));
                super.tracker.addImage(this.pics[i], n);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.timeSinceLastMove = System.currentTimeMillis();
    }
    
    public void bark() {
        if (System.currentTimeMillis() - this.timeSinceBark > 800L) {
            this.timeSinceBark = System.currentTimeMillis();
            this.barkSound.play();
        }
    }
    
    private boolean insideLimits(final int n, final int n2) {
        return this.moveLimit.inside(n + super.collision.x, n2 + super.collision.y) && this.moveLimit.inside(n + super.collision.x, n2 + super.collision.y + super.collision.height) && this.moveLimit.inside(n + super.collision.x + super.collision.width, n2 + super.collision.y) && this.moveLimit.inside(n + super.collision.x + super.collision.width, n2 + super.collision.y + super.collision.height);
    }
    
    public void checkMoves() {
        if (!this.insideLimits(super.x_pos, super.y_pos)) {
            switch (this.moveStatus) {
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
        if (this.stepsToWalk-- == 0) {
            if (this.moveStatus == 0) {
                this.timeBetweenMoves = Math.abs(dog.random.nextInt()) % 120 + 1;
            }
            this.moveStatus = Math.abs(dog.random.nextInt()) % 5;
            if (this.moveStatus != 0) {
                this.stepsToWalk = Math.abs(dog.random.nextInt()) % 100 + 1;
            }
            else {
                this.stepsToWalk = Math.abs(dog.random.nextInt()) % 100 / 5 + 1;
            }
        }
        switch (this.moveStatus) {
            case 1: {
                if (!this.insideLimits(super.x_pos - 4 + 2, super.y_pos)) {
                    this.stepsToWalk = 0;
                    return;
                }
                break;
            }
            case 2: {
                if (!this.insideLimits(super.x_pos + 4 - 2, super.y_pos)) {
                    this.stepsToWalk = 0;
                    return;
                }
                break;
            }
            case 3: {
                if (!this.insideLimits(super.x_pos, super.y_pos + 2 - 4)) {
                    this.stepsToWalk = 0;
                    return;
                }
                break;
            }
            case 4: {
                if (!this.insideLimits(super.x_pos, super.y_pos - 2 + 4)) {
                    this.stepsToWalk = 0;
                    return;
                }
                break;
            }
        }
    }
    
    public void walk() {
        if (this.moveStatus == 0 || System.currentTimeMillis() - this.timeSinceLastMove < this.timeBetweenMoves) {
            return;
        }
        this.timeSinceLastMove = System.currentTimeMillis();
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
    
    public void changeCourse() {
        this.stepsToWalk = 0;
    }
    
    public boolean inBarkRange(final sprite sprite) {
        final Rectangle rectangle = new Rectangle(super.collision.x + super.x_pos, super.collision.y + super.y_pos, super.collision.width, super.collision.height);
        final Rectangle rectangle2 = new Rectangle(sprite.collision.x + sprite.x_pos, sprite.collision.y + sprite.y_pos, sprite.collision.width, sprite.collision.height);
        switch (this.moveStatus) {
            case 1: {
                rectangle.grow(0, 18);
                final Rectangle rectangle3 = rectangle;
                rectangle3.x -= 60;
                break;
            }
            case 2: {
                rectangle.grow(0, 18);
                final Rectangle rectangle4 = rectangle;
                rectangle4.x += 60;
                break;
            }
            case 3: {
                rectangle.grow(18, 0);
                final Rectangle rectangle5 = rectangle;
                rectangle5.y -= 60;
                break;
            }
            case 4: {
                rectangle.grow(18, 0);
                final Rectangle rectangle6 = rectangle;
                rectangle6.y += 60;
                break;
            }
        }
        return rectangle.intersects(rectangle2);
    }
    
    public void draw() {
        super.offScrGC.drawImage(this.pics[this.picnr], super.x_pos, super.y_pos, super.parentApplet);
    }
    
    static {
        dog.random = new Random();
    }
}
