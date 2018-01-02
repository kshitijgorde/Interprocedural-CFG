// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.networkcam;

import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.net.URL;
import java.net.URLConnection;
import com.mindprod.common11.StoppableThread;
import com.mindprod.common11.ResizingImageViewer;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.applet.Applet;

public final class NetworkCam extends Applet implements Runnable
{
    private static final boolean DEBUGGING = true;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2002-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2007-04-19";
    public static final String VERSION_STRING = "1.6";
    private static final String[] frogSounds;
    private static final Random wheel;
    private static int cameraNumber;
    private static int uniqueGenerator;
    private AffineTransform transform;
    private AudioClip clickSound;
    private AudioClip frogSound;
    private Image camImage;
    private ResizingImageViewer camViewer;
    private StoppableThread ticker;
    private String cameraName;
    private URLConnection urlConn;
    private URL camURL;
    private volatile boolean clicked;
    private boolean flip;
    private boolean mirror;
    private boolean quiet;
    private int rotateDegrees;
    private long refreshFast;
    private long refreshSlow;
    private long refreshTimeout;
    static /* synthetic */ Class class$com$mindprod$networkcam$NetworkCam;
    
    public void addNotify() {
        super.addNotify();
        if (this.camImage == null && this.camURL != null) {
            this.camImage = this.getUncachedImage(this.camURL);
        }
    }
    
    public void destroy() {
        this.cameraName = null;
        this.camImage = null;
        this.camURL = null;
        this.camViewer = null;
        this.clickSound = null;
        this.frogSound = null;
        this.ticker = null;
        this.transform = null;
        this.urlConn = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 2, 0, this)) {
            return;
        }
        NetworkCam.cameraNumber = getCameraNumber();
        this.cameraName = this.getCameraNameParameter();
        this.quiet = this.getBooleanParameter("quiet", true);
        this.clickSound = this.getAudioClip(((NetworkCam.class$com$mindprod$networkcam$NetworkCam == null) ? (NetworkCam.class$com$mindprod$networkcam$NetworkCam = class$("com.mindprod.networkcam.NetworkCam")) : NetworkCam.class$com$mindprod$networkcam$NetworkCam).getResource("cameraclick.au"));
        if (!this.quiet) {
            this.frogSound = this.getFrogSound();
        }
        this.refreshFast = this.getNumericParameter("RefreshFast", 1, 10, 3600) * 1000L;
        this.refreshSlow = this.getNumericParameter("RefreshSlow", 1, 120, 3600) * 1000L;
        this.refreshTimeout = this.getNumericParameter("RefreshTimeout", 1, 30, 3600) * 1000L;
        this.camURL = this.getURLParameter("Image");
        this.flip = this.getBooleanParameter("Flip", false);
        this.mirror = this.getBooleanParameter("Mirror", false);
        this.rotateDegrees = this.getNumericParameter("Rotate", -360, 0, 360);
        if (this.camImage == null && this.camURL != null) {
            this.camImage = this.getUncachedImage(this.camURL);
        }
        System.out.println("size: " + this.getWidth() + "x" + this.getHeight());
        if (this.flip || this.mirror || this.rotateDegrees != 0) {
            (this.transform = new AffineTransform()).translate(this.getWidth() / 2.0, this.getHeight() / 2.0);
            if (this.flip) {
                this.transform.scale(1.0, -1.0);
            }
            if (this.mirror) {
                this.transform.scale(-1.0, 1.0);
            }
            if (this.rotateDegrees != 0) {
                if (this.rotateDegrees % 90 == 0) {
                    this.transform.quadrantRotate(this.rotateDegrees / 90);
                }
                else {
                    this.transform.rotate(Math.toRadians(this.rotateDegrees));
                }
            }
            this.transform.translate(-this.getWidth() / 2.0, -this.getHeight() / 2.0);
        }
        else {
            this.transform = null;
        }
        this.setLayout(null);
        this.setBackground(Color.black);
        (this.camViewer = new ResizingImageViewer(this.transform)).setBackground(Color.black);
        this.camViewer.setSize(this.getSize());
        this.camViewer.setLocation(0, 0);
        this.camViewer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent m) {
                System.out.println("Click: " + NetworkCam.this.cameraName);
                NetworkCam.this.clickSound.play();
                NetworkCam.this.clicked = true;
                if (NetworkCam.this.ticker != null) {
                    try {
                        NetworkCam.this.ticker.interrupt();
                    }
                    catch (SecurityException e) {
                        System.err.println("Security problem. Click ignored. " + e.getMessage());
                    }
                }
            }
        });
        this.add(this.camViewer);
        this.validate();
        this.setVisible(true);
    }
    
    public void run() {
    Label_0000:
        while (!this.ticker.stopping()) {
            if (this.wasRecentClick()) {
                continue;
            }
            System.out.println("Restarting : " + this.cameraName);
            this.refreshCamImage();
            if (this.ticker.stopping()) {
                return;
            }
            if (this.wasRecentClick()) {
                continue;
            }
            int fastCycles = (int)((this.refreshTimeout + this.refreshFast / 2L) / this.refreshFast);
            if (this.isMultipleInstance() && fastCycles > 0) {
                final int random;
                synchronized (NetworkCam.wheel) {
                    random = NetworkCam.wheel.nextInt((int)this.refreshFast);
                }
                System.out.println("random cycle: " + random + " milliseconds " + this.cameraName);
                this.oneCycle(random);
                if (this.ticker.stopping()) {
                    return;
                }
                if (this.wasRecentClick()) {
                    continue;
                }
                --fastCycles;
            }
            while (fastCycles > 0) {
                this.oneCycle(this.refreshFast);
                if (this.ticker.stopping()) {
                    return;
                }
                if (this.wasRecentClick()) {
                    continue Label_0000;
                }
                --fastCycles;
            }
            do {
                this.oneCycle(this.refreshSlow);
                if (this.ticker.stopping()) {
                    return;
                }
            } while (!this.wasRecentClick());
        }
    }
    
    public void start() {
        if (this.ticker == null) {
            (this.ticker = new StoppableThread(this)).start();
        }
    }
    
    public void stop() {
        if (this.ticker != null) {
            this.ticker.gentleStop(true, 0L);
            this.ticker = null;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private static int getCameraNumber() {
        return ++NetworkCam.uniqueGenerator;
    }
    
    private boolean getBooleanParameter(final String paramName, final boolean defaultValue) {
        final String boolString = this.getParameter(paramName);
        if (boolString == null) {
            return defaultValue;
        }
        if (boolString.equalsIgnoreCase("true") || boolString.equalsIgnoreCase("yes") || boolString.equalsIgnoreCase("t") || boolString.equalsIgnoreCase("y")) {
            return true;
        }
        if (boolString.equalsIgnoreCase("false") || boolString.equalsIgnoreCase("no") || boolString.equalsIgnoreCase("yes") || boolString.equalsIgnoreCase("f") || boolString.equalsIgnoreCase("n")) {
            return false;
        }
        throw new IllegalArgumentException("NetworkCam: " + paramName + " param: " + boolString + " should be true or false.");
    }
    
    private String getCameraNameParameter() {
        String cameraName = this.getParameter("cameraname");
        if (cameraName == null) {
            cameraName = "camera-" + NetworkCam.cameraNumber;
        }
        return cameraName;
    }
    
    private AudioClip getFrogSound() {
        final int frogNumber = NetworkCam.wheel.nextInt(NetworkCam.frogSounds.length);
        final URL u = ((NetworkCam.class$com$mindprod$networkcam$NetworkCam == null) ? (NetworkCam.class$com$mindprod$networkcam$NetworkCam = class$("com.mindprod.networkcam.NetworkCam")) : NetworkCam.class$com$mindprod$networkcam$NetworkCam).getResource(NetworkCam.frogSounds[frogNumber]);
        if (u == null) {
            throw new IllegalArgumentException("com/mindprod/networkcam/" + NetworkCam.frogSounds[frogNumber] + ".au is missing from networkcam.jar");
        }
        return this.getAudioClip(u);
    }
    
    private int getNumericParameter(final String paramName, final int low, final int defaultValue, final int high) {
        String paramValue = null;
        try {
            paramValue = this.getParameter(paramName);
            if (paramValue == null) {
                return defaultValue;
            }
            final int result = Integer.parseInt(paramValue);
            if (result < low) {
                System.err.println("NetworkCam: value for " + paramName + " param: " + paramValue + " cannot be below " + low);
                return defaultValue;
            }
            if (result > high) {
                System.err.println("NetworkCam: value for " + paramName + " param: " + paramValue + " cannot be above " + high);
                return defaultValue;
            }
            return result;
        }
        catch (NumberFormatException e) {
            System.err.println("NetworkCam: invalid value for " + paramName + " param: " + paramValue);
            return defaultValue;
        }
    }
    
    private URL getURLParameter(final String paramName) {
        String urlString = null;
        try {
            urlString = this.getParameter(paramName);
            if (urlString == null) {
                System.err.println("NetworkCam: missing value for " + paramName + " param");
                return null;
            }
            return new URL(this.getDocumentBase(), urlString);
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException("NetworkCam: invalid value for " + paramName + " param: " + urlString);
        }
    }
    
    private Image getUncachedImage(final URL url) {
        try {
            this.urlConn = url.openConnection();
            if (this.urlConn == null) {
                throw new IOException("Unable to make a connection to the image source");
            }
            this.urlConn.setUseCaches(false);
            this.urlConn.connect();
            final long lastModified = this.urlConn.getLastModified();
            if (lastModified != 0L) {
                System.out.println("  age of incoming image: " + (System.currentTimeMillis() - lastModified) + " millis : " + this.cameraName);
            }
            else {
                System.out.println("  no age on image : " + this.cameraName);
            }
            return this.createImage((ImageProducer)this.urlConn.getContent());
        }
        catch (IOException e) {
            System.err.println("NetworkCam: server not responding at : " + url);
            return null;
        }
    }
    
    private boolean isMultipleInstance() {
        final Enumeration other = this.getAppletContext().getApplets();
        while (other.hasMoreElements()) {
            final Object otherApplet = other.nextElement();
            if (otherApplet != this && otherApplet instanceof NetworkCam) {
                return true;
            }
        }
        return false;
    }
    
    private void oneCycle(final long delay) {
        if (this.clicked || this.ticker.stopping()) {
            return;
        }
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException ex) {}
        if (this.clicked || this.ticker.stopping()) {
            return;
        }
        System.out.println("Time for a refresh: " + this.cameraName);
        this.refreshCamImage();
    }
    
    private void refreshCamImage() {
        System.out.println("  starting refresh: " + this.cameraName);
        if (!this.quiet) {
            this.frogSound.play();
        }
        if (this.camImage != null) {
            this.camImage.flush();
        }
        final long lastModified = this.urlConn.getLastModified();
        if (lastModified != 0L) {
            System.out.println("  age of incoming image: " + (System.currentTimeMillis() - lastModified) + " millis : " + this.cameraName);
        }
        else {
            System.out.println("  no age on image : " + this.cameraName);
        }
        final long length = this.urlConn.getContentLength();
        System.out.println("  length of image: " + length + " bytes : " + this.cameraName);
        this.camViewer.setImage(this.camImage);
        System.out.println("   finishing refresh: " + this.cameraName);
    }
    
    private boolean wasRecentClick() {
        if (this.clicked) {
            this.clicked = false;
            return true;
        }
        return false;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        frogSounds = new String[] { "braak.au", "bullfrog.au", "bullfrog.au", "eeak.au", "greentoad.au", "greentreefrog.au", "peeper.au", "squee.au", "sunfrog.au", "tiny.au" };
        wheel = new Random();
        NetworkCam.uniqueGenerator = 0;
    }
}
