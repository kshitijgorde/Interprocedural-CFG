// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.io.InputStream;
import java.net.URL;
import java.awt.Toolkit;
import java.io.DataInputStream;

class ClipVideoStream extends Thread
{
    NuApplet applet;
    MJPEGMovieReader movie;
    String err;
    volatile boolean quit;
    AppletSocket urlConnection;
    DataInputStream in;
    long memAllocated;
    int delIndex;
    long maxBytesBeforeDelete;
    static final int CONNECTING = 0;
    static final int LOADING = 1;
    static final int ERROR = 2;
    static final int DONE = 3;
    private byte[][] images;
    private int[] timestamp;
    int state;
    Toolkit tk;
    
    public long getTimestamp(final int frame) {
        return this.movie.getTime() + this.timestamp[frame];
    }
    
    protected boolean done() {
        return this.state == 2 || this.state == 3;
    }
    
    protected boolean connecting() {
        return this.state == 0;
    }
    
    protected ClipVideoStream(final NuApplet inApplet) {
        this.err = null;
        this.quit = false;
        this.memAllocated = 0L;
        this.delIndex = 0;
        this.maxBytesBeforeDelete = 614400L;
        this.images = null;
        this.timestamp = null;
        this.state = 0;
        this.tk = Toolkit.getDefaultToolkit();
        this.applet = inApplet;
    }
    
    protected URL getRequestURL() throws Exception {
        String url = this.applet.getParameter("URL");
        if (url != null && url.indexOf("http") != 0) {
            url = String.valueOf(this.applet.getCodeBaseServer()) + url;
        }
        Debug.println("url=" + url);
        return new URL(url);
    }
    
    protected boolean connect() throws Exception {
        if (this.movie == null) {
            final URL url = this.getRequestURL();
            this.urlConnection = this.applet.getConnection(url);
            if (this.urlConnection == null) {
                return false;
            }
            this.in = this.urlConnection.getDataInputStream();
            (this.movie = new MJPEGMovieReader(this.in)).readHeader();
            this.images = new byte[this.movie.getFrameCount()][];
            this.timestamp = new int[this.movie.getFrameCount()];
        }
        return true;
    }
    
    protected byte[] getImage(final int index) throws Exception {
        while (!this.quit && this.images[index] == null && index < this.delIndex) {
            Thread.sleep(30L);
        }
        return this.images[index];
    }
    
    protected int getSleep(final int index) throws Exception {
        if (index > 0) {
            final int diff = this.timestamp[index] - this.timestamp[index - 1];
            return diff;
        }
        return 0;
    }
    
    public void run() {
        try {
            final int frames = this.movie.getFrameCount();
            this.state = 1;
            for (int x = 0; x < frames && !this.quit; ++x) {
                this.images[x] = this.movie.getNextImage();
                this.memAllocated += this.images[x].length;
                if (this.memAllocated > this.maxBytesBeforeDelete) {
                    this.images[this.delIndex++] = null;
                }
                this.timestamp[x] = this.movie.getTimestamp();
                this.applet.sendMessage(556, this.images[x]);
            }
            this.state = 3;
        }
        catch (Throwable e) {
            Debug.report(e);
            this.state = 2;
            this.err = e.toString();
            for (int i = 0; i < this.images.length; ++i) {
                this.images[i] = null;
            }
        }
        try {
            if (this.in != null) {
                this.in.close();
            }
            this.in = null;
        }
        catch (Exception ex) {}
        this.urlConnection = null;
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        final boolean handled = false;
        return handled;
    }
    
    protected synchronized void quit() {
        this.quit = true;
        try {
            final int frames = this.movie.getFrameCount();
            for (int x = 0; x < frames; ++x) {
                if (this.images[x] != null) {
                    this.images[x] = null;
                }
            }
            this.delIndex = frames;
            this.images = null;
            System.gc();
        }
        catch (Throwable t) {}
    }
}
