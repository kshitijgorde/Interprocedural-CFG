// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.applet.Applet;
import java.net.URL;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.util.Date;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

public class ClipViewApplet extends NuApplet implements Runnable, ActionListener
{
    Toolkit tk;
    ImageSlider slider;
    ImageDescCanvas imageDesc;
    PictureCanvas imageCanvas;
    ImageButtonCanvas startStopButton;
    Image pauseImage;
    Image startImage;
    boolean animate;
    String urlStringBase;
    String fatalError;
    Thread thread;
    boolean isEval;
    final String kStart = "start";
    final String kPause = "pause";
    int items;
    int loaded;
    int paint;
    Date frameDate;
    PassFrame passFrame;
    MediaTracker mt;
    int curIndex;
    ClipVideoStream stream;
    
    public String appletName() {
        return "ClipView " + Version.getShortVersion();
    }
    
    public void destroy() {
        System.gc();
    }
    
    void fatalError(final Exception e, final String where) {
        Debug.report(e, where);
        this.println("Exception:" + e + " in " + where);
        this.setCaption(this.fatalError = String.valueOf(where) + " error: " + e.toString());
    }
    
    void setCaption(final String caption) {
        if (this.imageDesc != null) {
            this.imageDesc.setStatus(caption);
        }
    }
    
    public void paint(final Graphics g) {
        if (!super.quit) {
            super.paint(g);
        }
        ++this.paint;
    }
    
    public void start() {
        (this.thread = new Thread(this, "ArchiveAnimate")).start();
    }
    
    private boolean startStream() {
        try {
            this.setCaption("Start ClipVideoStream");
            this.stream = new ClipVideoStream((NuApplet)this);
            this.setCaption("Connecting...");
            final boolean ok = this.stream.connect();
            if (!ok) {
                return false;
            }
            this.imageCanvas.invert_images = this.stream.movie.getInvertVideo();
            this.items = this.stream.movie.getFrameCount();
            if (this.items > 0) {
                this.slider.setMaxItems(this.items);
                this.stream.start();
                this.setCaption("Connecting...");
                while (this.stream.connecting()) {
                    Thread.yield();
                }
                this.setCaption("Loading...");
                if (this.stream.err != null) {
                    while (!this.stream.done()) {
                        this.setCaption("Loading " + this.loaded + " of " + this.items);
                        Thread.sleep(200L);
                    }
                }
            }
            else {
                this.setCaption(this.stream.err);
            }
            this.startPlay();
        }
        catch (Exception e) {
            Debug.report(e);
            this.fatalError(e, "loading");
            return false;
        }
        return true;
    }
    
    Image getImage(final int index) throws Exception {
        final byte[] b = this.stream.getImage(index);
        final Image img = this.tk.createImage(b);
        this.mt.addImage(img, index);
        this.mt.waitForID(index);
        this.mt.removeImage(img);
        return img;
    }
    
    protected synchronized void setIndex(final int index, final boolean full) {
        try {
            if (index != this.curIndex) {
                final Image image = this.getImage(index);
                if (image != null) {
                    this.imageCanvas.setImage(image);
                    this.slider.setIndex(index);
                    this.curIndex = index;
                    this.frameDate.setTime(this.stream.movie.getTime() + this.stream.timestamp[index]);
                    this.setCaption(this.frameDate.toString());
                }
            }
        }
        catch (Exception e) {
            this.fatalError(e, "setIndex");
        }
    }
    
    public void init() {
        super.init();
        try {
            super.expires = this.getParameter("expires");
            this.urlStringBase = this.getParameter("url");
            if (this.urlStringBase != null) {
                if (this.urlStringBase.indexOf("?") != -1) {
                    this.urlStringBase = String.valueOf(this.urlStringBase) + "&";
                }
                else {
                    this.urlStringBase = String.valueOf(this.urlStringBase) + "?";
                }
            }
            else {
                this.urlStringBase = this.getCodeBase().toString();
                final int slash = this.urlStringBase.indexOf("/", 9);
                if (slash != -1) {
                    this.urlStringBase = this.urlStringBase.substring(0, slash);
                }
            }
            final int imageWidth = (this.getParameter("width") != null) ? Integer.parseInt(this.getParameter("width")) : 320;
            this.imageCanvas = new PictureCanvas(imageWidth);
            this.imageDesc = new ImageDescCanvas(imageWidth, 16);
            this.slider = new ImageSlider(this);
            this.pauseImage = this.getImage(new URL(this.getCodeBase(), "archive_pause.gif"));
            this.startImage = this.getImage(new URL(this.getCodeBase(), "archive_start.gif"));
            (this.startStopButton = new ImageButtonCanvas(this.startImage, null, "start", this)).resize(16, 16);
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
            this.setCaption("initialized");
        }
        catch (Exception e) {
            this.fatalError(e, "initialize");
        }
    }
    
    public ClipViewApplet() {
        this.tk = Toolkit.getDefaultToolkit();
        this.animate = false;
        this.fatalError = "";
        this.thread = null;
        this.isEval = true;
        this.items = 0;
        this.loaded = 0;
        this.paint = 0;
        this.frameDate = new Date();
        this.passFrame = null;
        this.curIndex = -1;
        this.mt = new MediaTracker(this);
    }
    
    protected void startPlay() {
        this.startStopButton.changeFunction(this.pauseImage, null, "pause");
        if (this.curIndex >= this.items - 1) {
            this.setIndex(0, true);
        }
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
        this.setIndex(index, true);
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        boolean handled = super.handleMessage(id, arg);
        switch (id) {
            case 3010: {
                this.sliderChange(this.slider.getValue());
                handled = true;
                break;
            }
            case 556: {
                ++this.loaded;
                break;
            }
            case 1012: {
                this.setCaption("Getting user/password...");
                this.showUserPassDialog();
                break;
            }
            case 1013: {
                this.setCaption("Reconnecting...");
                this.setUserPass(PassFrame.myUserName, PassFrame.myPassword);
                this.passFrame = null;
                this.start();
                Debug.println("restarted stream");
                this.repaint();
                break;
            }
            case 1014: {
                Debug.println("User/pass cancel");
                this.setCaption("Password required!");
                this.passFrame = null;
                super.fAuth = null;
                this.repaint();
                break;
            }
        }
        return handled;
    }
    
    protected void showUserPassDialog() {
        Debug.println("getting user/pass");
        try {
            if (this.passFrame == null) {
                this.passFrame = new PassFrame(this);
            }
            this.passFrame.show();
            this.passFrame.toFront();
            Thread.sleep(1000L);
        }
        catch (Exception e) {
            Debug.report(e);
        }
        if (this.passFrame != null) {
            this.passFrame.toFront();
        }
    }
    
    public void stop() {
        super.quit = true;
        super.stop();
        if (this.stream != null) {
            this.stream.quit();
        }
        this.stream = null;
    }
    
    public void run() {
        this.println("Run");
        this.setCaption("Run");
        try {
            for (int x = 0; x < 10 && this.paint == 0; ++x) {
                this.setCaption("Run " + x);
                this.repaint();
                Thread.sleep(1000L);
                if (super.quit) {
                    this.thread = null;
                    return;
                }
            }
            this.setCaption("UI Up");
            this.println("UI Up");
            if (this.startStream()) {
                this.setIndex(0, true);
                while (!super.quit) {
                    if (this.animate) {
                        final int next = this.curIndex + 1;
                        if (next >= this.items) {
                            this.stopPlay();
                        }
                        else {
                            final long t1 = System.currentTimeMillis();
                            this.setIndex(next, false);
                            final long t2 = System.currentTimeMillis();
                            long s = this.stream.getSleep(next);
                            s -= t2 - t1;
                            if (s <= 0L) {
                                continue;
                            }
                            Thread.sleep(s);
                        }
                    }
                    else {
                        Thread.sleep(500L);
                    }
                }
            }
        }
        catch (Exception e) {
            this.fatalError(e, "run");
        }
        this.thread = null;
    }
}
