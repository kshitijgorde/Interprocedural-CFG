// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.image.ImageObserver;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;

public class Slideshow extends NuApplet implements Runnable
{
    Image[] images;
    String[] desc;
    MediaTracker mediaTracker;
    ImageDescCanvas imageDesc;
    FadePictureCanvas imageCanvas;
    int frameSleep;
    boolean animate;
    String fatalError;
    Thread thread;
    boolean isEval;
    int items;
    int curIndex;
    
    public String appletName() {
        return "Slide Show " + Version.getShortVersion();
    }
    
    public void destroy() {
        this.println("SlideShow destroy");
        try {
            if (this.images != null) {
                for (int x = 0; x < this.items; ++x) {
                    if (this.images[x] != null) {
                        this.images[x].flush();
                    }
                    this.images[x] = null;
                }
                this.images = null;
            }
            this.mediaTracker = null;
            this.desc = null;
        }
        catch (Exception e) {
            this.fatalError(e, "destroy");
        }
        System.gc();
    }
    
    void fatalError(final Exception e, final String where) {
        Debug.report(e, where);
        this.println("Exception:" + e + " in " + where);
        this.fatalError = String.valueOf(where) + " error: " + e.toString();
        if (this.imageDesc != null) {
            this.imageDesc.setStatus(this.fatalError);
        }
        this.quit();
    }
    
    public void start() {
        this.println("start " + this.appletName());
        try {
            final String lic = this.getParameter("license");
            if ("EVAL".equals(lic)) {
                this.isEval = true;
                this.imageCanvas.loadMsg = "SiteProxy Evaluation";
                this.imageCanvas.repaint();
            }
            super.expires = this.getParameter("expires");
            final String urlBase = this.getCodeBaseServer();
            for (int x = 0; x < this.items; ++x) {
                final String name = this.getParameter("img" + x);
                if (name == null) {
                    break;
                }
                String urlStr = String.valueOf(urlBase) + (name.startsWith("/") ? name.substring(1, name.length()) : name);
                if (x == 0) {
                    this.println(urlStr);
                }
                if (super.expires != null) {
                    urlStr = String.valueOf(urlStr) + "&expires=" + super.expires;
                }
                final URL url = new URL(urlStr);
                this.images[x] = this.getImage(url);
                Debug.println("x=" + x + " url=" + url.toString());
                if (this.images[x] == null) {
                    throw new Exception("unable to get image: " + url.toString());
                }
                this.mediaTracker.addImage(this.images[x], x);
                this.desc[x] = this.getParameter("desc" + x);
                if (this.desc[x] == null) {
                    final String u = url.toString();
                    this.desc[x] = "image " + (x + 1) + " " + u.substring(u.lastIndexOf("/"), u.length());
                }
            }
            int waitUntil = (int)(this.items / 5.0);
            if (this.items < 5) {
                waitUntil = this.items;
            }
            Debug.println("loading first " + waitUntil + " images of " + this.items);
            for (int x2 = 0; x2 < waitUntil; ++x2) {
                this.mediaTracker.waitForID(x2);
                if (this.mediaTracker.isErrorID(x2)) {
                    Debug.println("Error loading image: " + this.desc[x2] + " " + this.images[x2].toString());
                }
            }
            (this.thread = new Thread(this, "Slideshow")).start();
        }
        catch (Exception e) {
            Debug.report(e);
            this.fatalError(e, "loading");
        }
    }
    
    synchronized void setIndex(final int index) {
        try {
            if (index != this.curIndex && this.images != null) {
                if (this.images[index] != null) {
                    try {
                        if (!this.mediaTracker.checkID(index)) {
                            this.mediaTracker.waitForID(index);
                        }
                        this.imageCanvas.setImage(this.images[index]);
                        this.imageDesc.setStatus(this.desc[index]);
                    }
                    catch (Exception e) {
                        if (this.desc != null) {
                            final String temp = this.desc[index];
                            this.desc[index] = "Unable to load " + temp + " " + e.toString();
                        }
                        this.fatalError(e, "getting archive image");
                    }
                }
                this.curIndex = index;
            }
        }
        catch (Exception e) {
            this.report(e);
        }
    }
    
    public void init() {
        super.init();
        try {
            this.items = Integer.parseInt(this.getParameter("images"));
            if (this.items <= 0) {
                return;
            }
            this.frameSleep = this.getIntParam("delay", 2500);
            final int imageWidth = this.getIntParam("width", 320);
            final int height = this.getBounds().height;
            final int imageHeight;
            final int hh = imageHeight = height - 16;
            this.mediaTracker = new MediaTracker(this);
            this.imageCanvas = new FadePictureCanvas(imageWidth, imageHeight);
            this.imageCanvas.fadeTime = this.getIntParam("fadeTime", 400);
            this.println("SlideShow by NuSpectra. Delay:" + this.frameSleep + " fadeTime:" + this.imageCanvas.fadeTime);
            this.imageDesc = new ImageDescCanvas(imageWidth, 16);
            this.imageCanvas.invert_images = this.getBoolParam("invert_all", false);
            Debug.println("invert=" + this.imageCanvas.invert_images);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            this.setLayout(gridBagLayout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 11;
            gridBagLayout.setConstraints(this.imageDesc, gridBagConstraints);
            this.add(this.imageDesc);
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = 3;
            gridBagConstraints.anchor = 10;
            gridBagLayout.setConstraints(this.imageCanvas, gridBagConstraints);
            this.add(this.imageCanvas);
            this.requestFocus();
            this.images = new Image[this.items];
            this.desc = new String[this.items];
            this.imageDesc.setStatus("Loading " + this.items + " images. " + Version.getShortVersion());
        }
        catch (Exception e) {
            this.fatalError(e, "initialize");
        }
    }
    
    public Slideshow() {
        this.images = null;
        this.desc = null;
        this.mediaTracker = null;
        this.frameSleep = 2000;
        this.animate = true;
        this.fatalError = "";
        this.isEval = false;
        this.items = 0;
        this.curIndex = -1;
    }
    
    public void run() {
        this.println("Run");
        try {
            this.setIndex(0);
            while (!this.wasQuit()) {
                try {
                    Thread.sleep(this.frameSleep);
                }
                catch (InterruptedException ex) {}
                if (this.wasQuit()) {
                    break;
                }
                if (!this.animate || this.items <= 1) {
                    continue;
                }
                int next = this.curIndex + 1;
                if (next >= this.items) {
                    next = 0;
                }
                if (this.mediaTracker != null) {
                    this.mediaTracker.waitForID(next);
                }
                if (this.images[next].getWidth(this) == 0) {
                    throw new Exception("Error getting image " + next + " width: " + this.images[next].toString());
                }
                this.setIndex(next);
            }
        }
        catch (Exception e) {
            this.fatalError(e, "run");
        }
        this.thread = null;
    }
    
    public void stop() {
        Debug.println("Stop");
        this.quit();
        while (true) {
            if (this.imageCanvas != null) {
                this.imageCanvas.quit();
                try {
                    while (this.thread != null) {
                        Thread.sleep(10L);
                    }
                }
                catch (Exception e) {
                    Debug.report(e);
                }
                super.stop();
                Debug.println("Calling destroy");
                this.destroy();
                return;
            }
            continue;
        }
    }
}
