// 
// Decompiled by Procyon v0.5.30
// 

package com.broadware.videoclient;

import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.net.URL;
import com.broadware.videoclient.video.GTSVideo;
import java.awt.Frame;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.applet.Applet;

public class VideoClient extends Applet
{
    AppletContext context;
    Dimension res;
    Frame mainFrame;
    GTSVideo gtsVideo;
    String defaultHeight;
    String defaultWidth;
    String message;
    String source;
    String logo;
    String brand;
    URL codeBase;
    boolean autoplay;
    boolean clickcontrol;
    boolean report;
    boolean httpConnection;
    boolean debug;
    float framerate;
    Cursor cursor;
    private String copyright;
    public String status;
    
    public VideoClient() {
        this.gtsVideo = null;
        this.defaultHeight = "240";
        this.defaultWidth = "320";
        this.message = null;
        this.source = null;
        this.logo = null;
        this.brand = null;
        this.codeBase = null;
        this.autoplay = true;
        this.clickcontrol = false;
        this.report = false;
        this.httpConnection = false;
        this.debug = false;
        this.framerate = 1.0f;
        this.cursor = new Cursor(0);
        this.copyright = "Broadware VideoClient Version #3.0\n Fri April 11 12:00:00 PDT 2002\n© 2002, Broadware Technologies";
    }
    
    public void init() {
        this.getEnv();
        this.parseParams();
    }
    
    private void getEnv() {
        this.context = this.getAppletContext();
        this.codeBase = this.getCodeBase();
        Object parent;
        for (parent = this.getParent(); !(parent instanceof Frame); parent = ((Component)parent).getParent()) {}
        this.mainFrame = (Frame)parent;
    }
    
    private void initGUI() {
        this.setLayout(new BorderLayout());
        (this.gtsVideo = new GTSVideo(this.res, this.httpConnection)).setDebug(this.debug);
        this.gtsVideo.setReport(this.report);
        if (this.logo != null) {
            try {
                final URL logoUrl = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this.codeBase.getHost()).append(this.logo))));
                this.gtsVideo.setLogo(logoUrl);
            }
            catch (Exception ex) {}
        }
        if (this.brand != null) {
            try {
                final URL brandUrl = new URL(String.valueOf(String.valueOf(new StringBuffer("http://").append(this.codeBase.getHost()).append(this.brand))));
                this.gtsVideo.setBrand(brandUrl);
            }
            catch (Exception ex2) {}
        }
        this.add("Center", this.gtsVideo.getCanvas());
        this.validate();
        this.gtsVideo.repaint();
    }
    
    private void parseParams() {
        String temp;
        if ((temp = this.getParameter("info")) != null) {
            this.print(temp);
        }
        else {
            this.print(this.copyright);
        }
        this.source = this.getParameter("video");
        String width;
        if ((width = this.getParameter("videowidth")) == null) {
            width = this.defaultWidth;
        }
        String height;
        if ((height = this.getParameter("videoheight")) == null) {
            height = this.defaultHeight;
        }
        try {
            this.res = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
        }
        catch (NumberFormatException nfe) {
            this.print("Video dimensions must be positive integers.\n".concat(String.valueOf(String.valueOf(nfe))));
        }
        if ((temp = this.getParameter("autoplay")) != null && temp.equalsIgnoreCase("false")) {
            this.autoplay = false;
            this.clickcontrol = true;
        }
        if ((temp = this.getParameter("clickcontrol")) != null) {
            if (temp.equalsIgnoreCase("true")) {
                this.clickcontrol = true;
            }
            else if (temp.equalsIgnoreCase("false")) {
                this.clickcontrol = false;
            }
        }
        if (this.clickcontrol) {
            this.cursor = new Cursor(12);
        }
        this.setCursor(this.cursor);
        if ((temp = this.getParameter("framerate")) != null) {
            this.framerate = Float.valueOf(temp);
            if (this.framerate > 30) {
                this.framerate = 30.0f;
            }
        }
        if ((temp = this.getParameter("report")) != null && temp.equalsIgnoreCase("true")) {
            this.report = true;
        }
        if ((this.message = this.getParameter("message")) != null) {
            this.setStatus(this.message);
        }
        if ((temp = this.getParameter("logo")) != null) {
            this.logo = temp;
        }
        if ((temp = this.getParameter("brand")) != null) {
            this.brand = temp;
        }
        if ((temp = this.getParameter("connecttype")) != null && temp.equalsIgnoreCase("HTTP")) {
            this.httpConnection = true;
        }
        if ((temp = this.getParameter("debug")) != null && temp.equalsIgnoreCase("true")) {
            this.debug = true;
        }
    }
    
    public void start() {
        this.initGUI();
        this.setStatus("Ready");
        if (this.source == null || !this.autoplay) {
            return;
        }
        this.startVideo();
    }
    
    public void startVideo() {
        this.startVideo(this.source, this.framerate);
    }
    
    public void startVideo(final String source) {
        this.startVideo(source, this.framerate);
    }
    
    public boolean startVideo(String source, final float framerate) {
        if (this.gtsVideo == null) {
            return false;
        }
        if (this.gtsVideo.isAlive()) {
            this.gtsVideo.stop();
        }
        this.setStatus("Initializing video...");
        this.setCursor(new Cursor(3));
        this.source = source;
        if (framerate != -1 && source != null) {
            this.updateFramerate(source, framerate);
            source = this.source;
        }
        URL url = null;
        try {
            url = new URL(this.codeBase, source);
        }
        catch (Exception e) {
            this.print(String.valueOf(String.valueOf(new StringBuffer("Problems connecting to video source: ").append(source).append("\n").append(e))));
            this.setStatus("Error");
            this.setCursor(this.cursor);
            return false;
        }
        if (this.gtsVideo.startVideo(url)) {
            this.setStatus("Video running");
            this.setCursor(this.cursor);
            return true;
        }
        this.setStatus("Error");
        this.setCursor(this.cursor);
        return false;
    }
    
    private void updateFramerate(String source, final float framerate) {
        final String framerateStr = "framerate=";
        final int i;
        if ((i = source.indexOf(framerateStr)) != -1) {
            final String begin = source.substring(0, i);
            String end = source.substring(i + framerateStr.length());
            end = ((end.indexOf("&") != -1) ? end.substring(end.indexOf("&")) : "");
            source = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(begin))).append(framerateStr).append(framerate).append(end)));
        }
        else {
            final String symbol = (source.indexOf("?") != -1) ? "&" : "?";
            source = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(source))).append(symbol).append(framerateStr).append(framerate)));
        }
        this.source = source;
        this.framerate = framerate;
    }
    
    public void setFramerate(final float newframerate) {
        if (0 < newframerate && newframerate <= 30) {
            this.framerate = newframerate;
            this.print("framerate changed to ".concat(String.valueOf(String.valueOf(this.framerate))));
            this.stopVideo();
            this.startVideo();
            return;
        }
        this.print("unable to change framerate to ".concat(String.valueOf(String.valueOf(newframerate))));
    }
    
    public void setSource(final String source) {
        this.source = source;
        this.stopVideo();
        this.startVideo();
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void stopVideo() {
        if (this.gtsVideo != null) {
            this.gtsVideo.stop();
            this.setStatus("Video stopped");
        }
    }
    
    public void stop() {
        if (this.source == null) {
            return;
        }
        this.stopVideo();
        this.setCursor(this.cursor);
        this.remove(this.gtsVideo.getCanvas());
        this.gtsVideo = null;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        this.context.showStatus(this.status);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int x, final int y) {
        this.context.showStatus("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if (this.gtsVideo != null && this.clickcontrol && this.gtsVideo.isAlive()) {
            this.stopVideo();
        }
        else if (this.clickcontrol) {
            this.startVideo();
        }
        return true;
    }
    
    private void print(final String s) {
        System.out.println(s);
    }
    
    private void Debug(final String statement) {
        if (this.debug) {
            System.err.println("VideoClient: ".concat(String.valueOf(String.valueOf(statement))));
        }
    }
    
    public void setStatus(final String s) {
        this.status = s;
        if (this.context != null) {
            this.context.showStatus(s);
        }
        System.out.println(s);
    }
    
    public String getAppletInfo() {
        return this.copyright;
    }
}
