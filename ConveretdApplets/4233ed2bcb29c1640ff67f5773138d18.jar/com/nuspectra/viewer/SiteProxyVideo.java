// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.net.URL;
import java.io.DataInputStream;

public class SiteProxyVideo extends MJPEGStream
{
    byte[] header;
    String baseURL;
    ControlSession session;
    
    SiteProxyVideo(final NuApplet inApplet, final VideoCanvas vc, final ControlSession cs) throws Exception {
        super(inApplet, vc);
        this.header = new byte[20];
        this.session = cs;
        this.baseURL = String.valueOf(super.applet.getCodeBaseServer()) + "vid.cgi?";
        this.baseURL = String.valueOf(this.baseURL) + "id=" + this.session.idStr + "&doc=" + this.session.doc;
        super.useFast = cs.mjpeg;
        if (this.session.trial) {
            vc.caption = "SiteProxy Trial--Please Register.";
        }
    }
    
    private void fastStream(final DataInputStream s) throws Exception {
        while (!super.quit) {
            final int avail = s.available();
            if (super.images == 0L) {
                Debug.println("reading header. avail=" + avail);
            }
            s.readFully(this.header);
            final int len = this.session.takeHeader(this.header);
            if (len <= 0) {
                this.setErrorMessage("Video stream ended");
                break;
            }
            final byte[] curImage = new byte[len];
            s.readFully(curImage);
            this.takeImageBytes(curImage);
        }
    }
    
    void fastStream() throws Exception {
        final URL url = this.getRequestURL();
        final boolean authorized = super.socket.connect(url);
        if (!authorized) {
            throw new Exception("unauthorized");
        }
        final DataInputStream s = super.socket.getDataInputStream();
        this.fastStream(s);
    }
    
    protected void takeImageBytes(final byte[] curImage) throws Exception {
        super.takeImageBytes(curImage);
        this.session.imagesReceived = super.images;
    }
    
    void slowStream() throws Exception {
        while (!super.quit) {
            final URL url = this.getRequestURL();
            final boolean authorized = super.socket.connect(url);
            if (!authorized) {
                this.setErrorMessage("Unauthorized");
                super.quit = true;
                return;
            }
            final DataInputStream s = super.socket.getDataInputStream();
            s.readFully(this.header);
            final int len = this.session.takeHeader(this.header);
            if (len > 0) {
                final byte[] curImage = new byte[len];
                s.readFully(curImage);
                this.takeImageBytes(curImage);
            }
            else {
                this.setErrorMessage("Session closed");
                Debug.println("Video stream stopped " + url);
            }
            super.socket.close();
        }
    }
    
    protected URL getRequestURL() throws Exception {
        if (this.useFastStream()) {
            return new URL(String.valueOf(this.baseURL) + "&v=1");
        }
        return new URL(String.valueOf(this.baseURL) + "&nc=" + super.images);
    }
    
    public void run() {
        final long startTime = System.currentTimeMillis();
        int attempts = 0;
        super.running = true;
        super.applet.println("SiteProxyVideo run start.");
        if (super.videoCanvas != null && !super.videoCanvas.isShowing()) {
            super.applet.println("Waiting for video canvas.");
            for (int x = 0; x < 100; ++x) {
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex) {}
                if (super.quit) {
                    break;
                }
                if (super.videoCanvas.isShowing()) {
                    break;
                }
            }
            if (!super.videoCanvas.isShowing()) {
                Debug.println("Timeout waiting for video canvas to appear.");
            }
        }
        try {
            if (super.fDebug) {
                Debug.println("Starting applet video " + (this.useFastStream() ? "Fast" : "Slow"));
            }
            Label_0203: {
                if (this.useFastStream()) {
                    try {
                        ++attempts;
                        this.fastStream();
                        break Label_0203;
                    }
                    catch (Exception e) {
                        Debug.report(e);
                        super.socket.close();
                        if (super.images == 0L) {
                            this.slowStream();
                            break Label_0203;
                        }
                        throw e;
                    }
                }
                this.slowStream();
                try {
                    if (super.socket != null) {
                        super.socket.close();
                    }
                }
                catch (Exception ex2) {}
            }
            super.socket = null;
        }
        catch (Exception ve) {
            if (!super.quit) {
                super.applet.report(ve, "Video stopped!");
                if (!this.hasErrorMessage()) {
                    if (super.images == 0L) {
                        this.setErrorMessage("Unable to receive video.");
                    }
                    else {
                        this.setErrorMessage("Received error getting video.");
                    }
                }
            }
        }
        final long delta = System.currentTimeMillis() - startTime;
        Debug.println("SiteProxyVideo run exit: " + delta + " ms ");
        if (this.getErrorMessage() != null) {
            Debug.println("SiteProxyVideo: Error: " + this.getErrorMessage());
        }
        this.header = null;
        super.running = false;
    }
}
