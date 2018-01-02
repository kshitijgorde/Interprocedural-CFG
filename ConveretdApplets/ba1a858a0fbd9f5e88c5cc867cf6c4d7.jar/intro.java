import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

public class intro
{
    private MediaTracker tracker;
    private Applet parentApplet;
    private Graphics offScrGC;
    private final String imageName = "introscreen.gif";
    private final String soundImageName = "sound.gif";
    private final int picWidth = 668;
    private final int picHeight = 400;
    private final int soundPosX = 71;
    private final int soundPosY = 194;
    private int offScrDrawY;
    private boolean music;
    private Image[] soundPics;
    private Image rawSoundImage;
    private final int numberOfButtons = 5;
    public final int BUTTON_hiscores = 0;
    public final int BUTTON_music = 1;
    public final int BUTTON_newgame = 2;
    public final int BUTTON_quotes = 3;
    public final int BUTTON_help = 4;
    private int transStatus;
    private int transX;
    private final int transSpeed = 20;
    private final int[][] buttons;
    private Image introPic;
    private boolean hasPaintedOnce;
    
    public intro(final int offScrDrawY, final MediaTracker tracker, final int n, final Graphics offScrGC, final Applet parentApplet) {
        this.music = true;
        this.soundPics = new Image[2];
        this.buttons = new int[][] { { 12, 156, 117, 29 }, { 12, 189, 93, 28 }, { 550, 140, 118, 29 }, { 573, 172, 96, 28 }, { 597, 204, 72, 29 } };
        this.hasPaintedOnce = false;
        this.parentApplet = parentApplet;
        this.offScrGC = offScrGC;
        this.tracker = tracker;
        this.offScrDrawY = offScrDrawY;
        try {
            this.rawSoundImage = this.parentApplet.getImage(this.parentApplet.getCodeBase(), "sound.gif");
            this.introPic = this.parentApplet.getImage(this.parentApplet.getCodeBase(), "introscreen.gif");
            this.tracker.addImage(this.rawSoundImage, n + 99);
            this.tracker.addImage(this.introPic, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.soundPics[0] = this.parentApplet.createImage(new FilteredImageSource(this.rawSoundImage.getSource(), new CropImageFilter(0, 0, 19, 18)));
        this.soundPics[1] = this.parentApplet.createImage(new FilteredImageSource(this.rawSoundImage.getSource(), new CropImageFilter(19, 0, 19, 18)));
        this.tracker.addImage(this.soundPics[0], n + 100);
        this.tracker.addImage(this.soundPics[1], n + 100);
    }
    
    public int checkButton(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < 5; ++i) {
            if (new Rectangle(this.buttons[i][0], this.buttons[i][1], this.buttons[i][2], this.buttons[i][3]).inside(n, n2)) {
                n3 = i;
                break;
            }
        }
        return n3;
    }
    
    public boolean musicEnabled() {
        return this.music;
    }
    
    public void changeMusic() {
        this.music ^= true;
        this.offScrGC.drawImage(this.soundPics[this.music], 71, this.offScrDrawY + 194, this.parentApplet);
    }
    
    public boolean inTransition() {
        return this.transStatus > 1;
    }
    
    public boolean inClosingTransition() {
        return this.transStatus == 3;
    }
    
    public boolean inOpeningTransition() {
        return this.transStatus == 2;
    }
    
    public boolean transitionOpen() {
        return this.transStatus == 1;
    }
    
    public boolean transitionClosed() {
        return this.transStatus == 0;
    }
    
    public void doTransition() {
        if (this.transStatus == 0) {
            this.transStatus = 2;
            return;
        }
        if (this.transStatus == 1) {
            this.transStatus = 3;
        }
    }
    
    public void draw() {
        if (this.hasPaintedOnce) {
            if (this.transStatus == 0) {
                this.offScrGC.copyArea(0, this.offScrDrawY, 668, 400, 0, -this.offScrDrawY - 1);
                return;
            }
            if (this.transStatus == 2) {
                this.transX += 20;
                if (this.transX >= 334) {
                    this.transStatus = 1;
                }
                this.offScrGC.copyArea(0, this.offScrDrawY, 334, 400, -this.transX, -this.offScrDrawY - 1);
                this.offScrGC.copyArea(334, this.offScrDrawY, 334, 400, this.transX, -this.offScrDrawY - 1);
                return;
            }
            if (this.transStatus == 3) {
                this.transX -= 20;
                if (this.transX <= 0) {
                    this.transStatus = 0;
                }
                this.offScrGC.copyArea(0, this.offScrDrawY, 334, 400, -this.transX, -this.offScrDrawY - 1);
                this.offScrGC.copyArea(334, this.offScrDrawY, 334, 400, this.transX, -this.offScrDrawY - 1);
            }
        }
        else {
            this.offScrGC.drawImage(this.introPic, 0, this.offScrDrawY, this.parentApplet);
            this.hasPaintedOnce = true;
        }
    }
}
