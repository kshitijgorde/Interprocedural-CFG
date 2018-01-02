// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.applet.Applet;
import java.awt.Component;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.ActionListener;

public class ArchiveViewApplet extends NuApplet implements Runnable, ActionListener
{
    Image[] images;
    String[] desc;
    MediaTracker mediaTracker;
    ImageSlider slider;
    ImageDescCanvas imageDesc;
    PictureCanvas imageCanvas;
    ImageButtonCanvas startStopButton;
    Image pauseImage;
    Image startImage;
    boolean animate;
    String fatalError;
    Thread thread;
    boolean isEval;
    final String kStart = "start";
    final String kPause = "pause";
    int frameSleep;
    int items;
    int curIndex;
    
    public String appletName() {
        return "ArchiveView " + Version.getShortVersion();
    }
    
    public void destroy() {
        this.println("ArchiveView destroy");
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
        super.quit = true;
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
            else {
                this.isEval = false;
            }
            super.expires = this.getParameter("expires");
            final String urlBase = this.getCodeBaseServer();
            for (int x = 0; x < this.items; ++x) {
                String urlStr = this.getParameter("img" + x);
                if (urlStr == null) {
                    urlStr = String.valueOf(urlBase) + "item=" + x;
                }
                else {
                    urlStr = String.valueOf(urlBase) + this.getParameter("img" + x);
                }
                if (x == 0) {
                    this.println(urlStr);
                }
                if (super.expires != null) {
                    urlStr = String.valueOf(urlStr) + "&expires=" + super.expires;
                }
                final URL url = new URL(urlStr);
                this.images[x] = this.getImage(url);
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
            (this.thread = new Thread(this, "ArchiveAnimate")).start();
            this.startPlay();
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
                            this.imageCanvas.setImage(this.images[index]);
                            this.imageDesc.setStatus(this.desc[index]);
                            this.mediaTracker.waitForID(index);
                            this.repaint();
                        }
                        else {
                            this.imageDesc.setStatus(this.desc[index]);
                            this.imageCanvas.setImage(this.images[index]);
                        }
                    }
                    catch (Exception e) {
                        if (this.desc != null) {
                            final String temp = this.desc[index];
                            this.desc[index] = "Unable to load " + temp + " " + e.toString();
                        }
                        this.fatalError(e, "getting archive image");
                    }
                    this.slider.setIndex(index);
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
            final int seconds = (this.getParameter("seconds") != null) ? Integer.parseInt(this.getParameter("seconds")) : 15;
            this.frameSleep = seconds * 1000 / this.items;
            final int fps = (this.getParameter("fps") != null) ? Integer.parseInt(this.getParameter("fps")) : -1;
            if (fps >= 1 && fps <= 30) {
                this.frameSleep = (int)Math.round(33.333333333333336 * fps);
            }
            final int imageWidth = (this.getParameter("width") != null) ? Integer.parseInt(this.getParameter("width")) : 320;
            this.mediaTracker = new MediaTracker(this);
            this.imageCanvas = new PictureCanvas(imageWidth);
            this.imageDesc = new ImageDescCanvas(imageWidth, 16);
            this.slider = new ImageSlider(this, this.items);
            this.pauseImage = this.getImage(new URL(this.getCodeBase(), "archive_pause.gif"));
            this.startImage = this.getImage(new URL(this.getCodeBase(), "archive_start.gif"));
            this.imageCanvas.invert_images = this.getBoolParam("invert_all", false);
            Debug.println("invert=" + this.imageCanvas.invert_images);
            (this.startStopButton = new ImageButtonCanvas(this.startImage, this.pauseImage, "start", this)).resize(16, 16);
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
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 16;
            gridBagLayout.setConstraints(this.startStopButton, gridBagConstraints);
            this.add(this.startStopButton);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 14;
            gridBagLayout.setConstraints(this.slider, gridBagConstraints);
            this.add(this.slider);
            this.requestFocus();
            this.images = new Image[this.items];
            this.desc = new String[this.items];
            this.imageDesc.setStatus("Loading " + this.items + " images. v2.0");
        }
        catch (Exception e) {
            this.fatalError(e, "initialize");
        }
    }
    
    public ArchiveViewApplet() {
        this.images = null;
        this.desc = null;
        this.mediaTracker = null;
        this.animate = false;
        this.fatalError = "";
        this.isEval = false;
        this.frameSleep = 333;
        this.items = 0;
        this.curIndex = -1;
    }
    
    protected void startPlay() {
        this.startStopButton.changeFunction(this.pauseImage, null, "pause");
        this.animate = true;
    }
    
    protected void stopPlay() {
        this.animate = false;
        this.startStopButton.changeFunction(this.startImage, null, "start");
    }
    
    public void actionPerformed(final ActionEvent ae) {
        final Object o = ae.getSource();
        if (o instanceof ImageButtonCanvas) {
            final ImageButtonCanvas b = (ImageButtonCanvas)o;
            if ("mousePressed".equals(ae.getActionCommand())) {
                this.println("Command: " + b.getCommand());
                if ("start".equals(b.getCommand())) {
                    this.startPlay();
                }
                else if ("pause".equals(b.getCommand())) {
                    this.stopPlay();
                }
            }
            else {
                "mouseReleased".equals(ae.getActionCommand());
            }
        }
    }
    
    protected void sliderChange(final int index) {
        if (this.animate) {
            this.stopPlay();
        }
        this.setIndex(index);
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        boolean handled = super.handleMessage(id, arg);
        switch (id) {
            case 3010: {
                this.sliderChange(this.slider.getValue());
                handled = true;
                break;
            }
        }
        return handled;
    }
    
    public void run() {
        this.println("Run");
        try {
            this.setIndex(0);
            while (!super.quit) {
                int sleep = 500;
                if (this.animate) {
                    final long startTime = System.currentTimeMillis();
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
                    final long delay = System.currentTimeMillis() - startTime;
                    sleep = (int)(this.frameSleep - delay);
                    if (sleep < 1) {
                        sleep = 1;
                    }
                }
                try {
                    Thread.sleep(sleep);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception e) {
            this.fatalError(e, "run");
        }
        this.destroy();
    }
    
    public void stop() {
        super.quit = true;
        super.stop();
    }
}
