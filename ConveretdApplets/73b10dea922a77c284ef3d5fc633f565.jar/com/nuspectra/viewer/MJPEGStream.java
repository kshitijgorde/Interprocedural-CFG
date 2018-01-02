// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.awt.Toolkit;

public class MJPEGStream extends Thread
{
    int interframeSleep;
    boolean fDebug;
    protected boolean running;
    boolean quit;
    public long images;
    private String stopError;
    Toolkit tk;
    NuApplet applet;
    AppletSocket socket;
    long firstFrame;
    String sessionID;
    private byte[] lastImageBytes;
    int lastImageSize;
    int maxImageByteSize;
    String fAuthorizationStr;
    VideoCanvas videoCanvas;
    boolean useFast;
    
    private MJPEGStream() {
        this.interframeSleep = 25;
        this.fDebug = false;
        this.running = false;
        this.quit = false;
        this.images = 0L;
        this.stopError = null;
        this.tk = Toolkit.getDefaultToolkit();
        this.firstFrame = 0L;
        this.sessionID = null;
        this.lastImageSize = 0;
        this.maxImageByteSize = 0;
        this.fAuthorizationStr = "";
        this.useFast = false;
    }
    
    protected MJPEGStream(final NuApplet inApplet, final VideoCanvas vc) throws Exception {
        this.interframeSleep = 25;
        this.fDebug = false;
        this.running = false;
        this.quit = false;
        this.images = 0L;
        this.stopError = null;
        this.tk = Toolkit.getDefaultToolkit();
        this.firstFrame = 0L;
        this.sessionID = null;
        this.lastImageSize = 0;
        this.maxImageByteSize = 0;
        this.fAuthorizationStr = "";
        this.useFast = false;
        this.setName("video");
        this.applet = inApplet;
        this.videoCanvas = vc;
        this.socket = new AppletSocket();
        if (this.applet.hasAuth()) {
            this.socket.setAuthorization(this.applet.getAuth());
        }
        if (Debug.max) {
            this.socket.debug = true;
        }
    }
    
    protected URL getRequestURL() throws Exception {
        String url = this.applet.getParameter("URL");
        if (url == null) {
            throw new Exception("video request not specified.");
        }
        if (url.indexOf("http") != 0) {
            url = String.valueOf(this.applet.getCodeBaseServer()) + url;
        }
        return new URL(url);
    }
    
    protected boolean useFastStream() {
        return this.useFast;
    }
    
    protected synchronized void quit() {
        if (this == Thread.currentThread()) {
            this.quit = true;
            return;
        }
        try {
            if (!this.quit) {
                this.quit = true;
                Debug.println("Video quit start");
                for (int x = 0; x < 5 && this.running; ++x) {
                    Thread.sleep(500L);
                }
                if (this.running) {
                    Debug.println("Video quit start");
                }
                if (this.socket != null) {
                    this.socket.close();
                }
            }
        }
        catch (Exception e) {
            Debug.report(e);
        }
        this.lastImageBytes = null;
        this.socket = null;
    }
    
    protected byte[] getLastImageBytes() {
        if (this.lastImageSize == 0) {
            return null;
        }
        synchronized (this.lastImageBytes) {
            final byte[] b = new byte[this.lastImageSize];
            System.arraycopy(this.lastImageBytes, 0, b, 0, this.lastImageSize);
            // monitorexit(this.lastImageBytes)
            return b;
        }
    }
    
    protected void setSleep(final int sleep) {
        this.interframeSleep = sleep;
    }
    
    public void run() {
        this.applet.setStatus("Invalid call");
    }
    
    protected void takeImageBytes(final byte[] curImage) throws Exception {
        if (this.quit) {
            return;
        }
        final Image img = this.tk.createImage(curImage);
        this.waitUntilLoaded(img);
        this.lastImageSize = 0;
        if (this.maxImageByteSize < curImage.length) {
            this.maxImageByteSize = curImage.length + 2048;
            this.lastImageBytes = new byte[this.maxImageByteSize];
        }
        System.arraycopy(curImage, 0, this.lastImageBytes, 0, curImage.length);
        this.lastImageSize = curImage.length;
        ++this.images;
        if (this.videoCanvas != null) {
            this.videoCanvas.takeImage(img);
        }
        try {
            if (this.images == 1L && Debug.max && this.socket != null) {
                this.socket.debug = false;
            }
            if (this.interframeSleep > 0) {
                Thread.sleep(this.interframeSleep);
            }
        }
        catch (Exception e) {
            Debug.report(e);
        }
    }
    
    protected void setUserPass(final String user, final String pass) {
        this.socket.setUserPass(user, pass);
    }
    
    protected void setAuthorization(final String auth) throws Exception {
        this.socket.setAuthorization(auth);
    }
    
    protected synchronized void waitUntilLoaded(final Image img) throws Exception {
        final MediaTracker mt = new MediaTracker(this.applet);
        mt.addImage(img, 1);
        mt.waitForAll(60000L);
    }
    
    protected void setErrorMessage(final String errMsg) {
        if (!this.quit) {
            Debug.println("SiteProxy video stream error: " + errMsg);
            this.stopError = new String(errMsg);
            this.applet.sendMessage(1015, this.stopError);
            this.quit = true;
        }
    }
    
    protected boolean hasErrorMessage() {
        return this.stopError != null;
    }
    
    protected String getErrorMessage() {
        return this.stopError;
    }
}
