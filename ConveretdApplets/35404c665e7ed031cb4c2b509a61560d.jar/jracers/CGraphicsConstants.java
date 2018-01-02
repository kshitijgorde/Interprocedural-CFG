// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.awt.Component;
import java.net.URL;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Font;

public class CGraphicsConstants
{
    public static final Font FONT_MONO;
    public static final Font FONT_MONOBOLD;
    public static final Font FONT_BIGMONO;
    public static final Font FONT_BIGMONOBOLD;
    public static final Font FONT_BIGTIME;
    public static final Font FONT_BIGFONT;
    public static final int XOFFSET = 200;
    public static final int YOFFSET = 0;
    public static final int YBLINDS = 50;
    public static final int TRACKXSIZE = 400;
    public static final int TRACKYSIZE = 500;
    public static final int MAXYSCROLL = 20;
    public static final int SIZEX = 600;
    public static final int SIZEY = 450;
    public int frameNumber;
    public int numberImages;
    public int numberLoadedImages;
    protected MediaTracker tracker;
    public boolean playSound;
    public boolean soundsLoaded;
    public boolean imagesLoaded;
    public Image bikeImage;
    public Image carImage;
    public Image canisterImage;
    public Image tiresImage;
    public Image manholeImage;
    public Image clockImage;
    public Image boostImage;
    public Image[] trackTextureImage;
    public final int numberTrackTextures = 10;
    private Applet apl;
    public AudioClip introClip;
    public AudioClip carRam;
    public AudioClip tires;
    public AudioClip boost;
    public AudioClip refuel;
    public AudioClip defaultSound;
    public AudioClip won;
    public AudioClip manhole;
    public AudioClip border;
    public AudioClip engine;
    public AudioClip engine2;
    public AudioClip engine3;
    public AudioClip engine4;
    
    public CGraphicsConstants() {
        this.frameNumber = 0;
        this.soundsLoaded = false;
        this.imagesLoaded = false;
    }
    
    public void stopSounds() {
        this.introClip.stop();
        this.carRam.stop();
        this.tires.stop();
        this.boost.stop();
        this.refuel.stop();
        this.defaultSound.stop();
        this.won.stop();
        this.manhole.stop();
        this.border.stop();
        this.engine.stop();
        this.engine2.stop();
        this.engine3.stop();
        this.engine4.stop();
    }
    
    public void loadSounds() {
        this.playSound = true;
        final URL u = this.apl.getCodeBase();
        this.introClip = this.apl.getAudioClip(u, "intro.mid");
        this.engine = this.apl.getAudioClip(u, "engine3.wav");
        this.engine2 = this.apl.getAudioClip(u, "engine32.wav");
        this.engine3 = this.apl.getAudioClip(u, "engine33.wav");
        this.engine4 = this.apl.getAudioClip(u, "engine34.wav");
        this.won = this.apl.getAudioClip(u, "disco03.mid");
        this.border = this.apl.getAudioClip(u, "border.wav");
        this.carRam = this.apl.getAudioClip(u, "car_ram2.wav");
        this.manhole = this.apl.getAudioClip(u, "manhole.wav");
        this.tires = this.apl.getAudioClip(u, "tires4.wav");
        this.boost = this.apl.getAudioClip(u, "boost4.wav");
        this.refuel = this.apl.getAudioClip(u, "refuel2.wav");
        this.defaultSound = this.apl.getAudioClip(u, "clock.wav");
        this.introClip.play();
        this.introClip.stop();
        this.engine.play();
        this.engine.stop();
        this.engine2.play();
        this.engine2.stop();
        this.engine3.play();
        this.engine3.stop();
        this.engine4.play();
        this.engine4.stop();
        this.won.play();
        this.won.stop();
        this.border.play();
        this.border.stop();
        this.carRam.play();
        this.carRam.stop();
        this.manhole.play();
        this.manhole.stop();
        this.tires.play();
        this.tires.stop();
        this.boost.play();
        this.boost.stop();
        this.refuel.play();
        this.refuel.stop();
        this.defaultSound.play();
        this.defaultSound.stop();
        this.soundsLoaded = true;
    }
    
    public void init(final Applet ap) {
        this.playSound = true;
        this.apl = ap;
        this.numberImages = 100;
        this.numberLoadedImages = 0;
    }
    
    public void loadImages() {
        final URL u = this.apl.getCodeBase();
        this.numberImages = 0;
        this.numberLoadedImages = 0;
        this.trackTextureImage = new Image[10];
        this.tracker = new MediaTracker(this.apl);
        this.trackTextureImage[0] = this.apl.getImage(u, "grass2.jpg");
        this.tracker.addImage(this.trackTextureImage[0], this.numberImages++);
        this.trackTextureImage[1] = this.apl.getImage(u, "road4.jpg");
        this.tracker.addImage(this.trackTextureImage[1], this.numberImages++);
        this.trackTextureImage[2] = this.apl.getImage(u, "border_left.jpg");
        this.tracker.addImage(this.trackTextureImage[2], this.numberImages++);
        this.trackTextureImage[3] = this.apl.getImage(u, "stripe.jpg");
        this.tracker.addImage(this.trackTextureImage[3], this.numberImages++);
        this.trackTextureImage[4] = this.apl.getImage(u, "border_right.jpg");
        this.tracker.addImage(this.trackTextureImage[4], this.numberImages++);
        this.trackTextureImage[5] = this.apl.getImage(u, "road5.jpg");
        this.tracker.addImage(this.trackTextureImage[5], this.numberImages++);
        this.trackTextureImage[6] = this.apl.getImage(u, "checkered_flag.jpg");
        this.tracker.addImage(this.trackTextureImage[6], this.numberImages++);
        this.trackTextureImage[7] = this.apl.getImage(u, "ice.jpg");
        this.tracker.addImage(this.trackTextureImage[7], this.numberImages++);
        this.trackTextureImage[8] = this.apl.getImage(u, "iceRoad.jpg");
        this.tracker.addImage(this.trackTextureImage[8], this.numberImages++);
        this.trackTextureImage[9] = this.apl.getImage(u, "water.jpg");
        this.tracker.addImage(this.trackTextureImage[9], this.numberImages++);
        this.carImage = this.apl.getImage(u, "car.gif");
        this.tracker.addImage(this.carImage, this.numberImages++);
        this.bikeImage = this.apl.getImage(u, "bike.gif");
        this.tracker.addImage(this.bikeImage, this.numberImages++);
        this.canisterImage = this.apl.getImage(u, "fuel.gif");
        this.tracker.addImage(this.canisterImage, this.numberImages++);
        this.tiresImage = this.apl.getImage(u, "reifen.gif");
        this.tracker.addImage(this.tiresImage, this.numberImages++);
        this.manholeImage = this.apl.getImage(u, "manhole.gif");
        this.tracker.addImage(this.manholeImage, this.numberImages++);
        this.boostImage = this.apl.getImage(u, "boost.gif");
        this.tracker.addImage(this.boostImage, this.numberImages++);
        this.clockImage = this.apl.getImage(u, "uhr.gif");
        this.tracker.addImage(this.clockImage, this.numberImages++);
        try {
            this.tracker.waitForAll(1L);
        }
        catch (InterruptedException e) {
            return;
        }
        this.imagesLoaded = true;
    }
    
    static {
        FONT_MONO = new Font("Courier", 0, 12);
        FONT_MONOBOLD = new Font("Courier", 1, 12);
        FONT_BIGMONO = new Font("Courier", 0, 16);
        FONT_BIGMONOBOLD = new Font("Courier", 1, 20);
        FONT_BIGTIME = new Font("Arial", 1, 48);
        FONT_BIGFONT = new Font("Arial", 3, 48);
    }
}
