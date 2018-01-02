// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;

public class RecordStatus extends NuApplet implements Runnable
{
    RecordStatusCanvas imageDesc;
    ImageButtonCanvas startStopButton;
    Image stopImage;
    Image startImage;
    boolean recording;
    String urlStringBase;
    URL statusURL;
    String auth;
    private String fatalError;
    Thread thread;
    final String kStart = "start";
    final String kStop = "stop";
    int paint;
    String id;
    String clip_id;
    boolean cmdInProgress;
    
    public RecordStatus() {
        this.startStopButton = null;
        this.recording = false;
        this.auth = null;
        this.fatalError = null;
        this.paint = 0;
        this.id = "1";
        this.clip_id = "";
        this.cmdInProgress = false;
    }
    
    public String appletName() {
        return "SiteProxy RecordStatus 1.1";
    }
    
    void fatalError(final Throwable e, final String where) {
        Debug.report(e, where);
        e.printStackTrace();
        this.println("Exception:" + e + " in " + where);
        this.setCaption(this.fatalError = String.valueOf(where) + " error: " + e.toString());
        if (this.startStopButton != null) {
            this.startStopButton.disable();
        }
    }
    
    void setCaption(final String caption) {
        if (this.imageDesc != null) {
            this.imageDesc.setStatus(caption);
        }
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        ++this.paint;
    }
    
    public void start() {
        this.println("start");
        (this.thread = new Thread(this, "RecordStatus")).start();
    }
    
    public void init() {
        super.init();
        try {
            this.urlStringBase = this.getParameter("url");
            if (this.urlStringBase == null) {
                this.urlStringBase = this.getCodeBase().toString();
                final int slash = this.urlStringBase.indexOf("/", 9);
                if (slash != -1) {
                    this.urlStringBase = this.urlStringBase.substring(0, slash);
                    this.urlStringBase = String.valueOf(this.urlStringBase) + "/";
                    if (this.getParameter("doc") != null) {
                        this.id = this.getParameter("doc");
                    }
                }
            }
            this.println(this.urlStringBase);
            final String statURLString = String.valueOf(this.urlStringBase) + "clip.cgi?cmd=status&id=" + this.id;
            this.println(statURLString);
            this.statusURL = new URL(statURLString);
            this.auth = this.getParameter("auth");
            final int imageWidth = (this.getParameter("width") != null) ? Integer.parseInt(this.getParameter("width")) : 320;
            this.imageDesc = new RecordStatusCanvas(imageWidth, 16);
            this.stopImage = this.loadImage("classes/record_stop.gif");
            this.startImage = this.loadImage("classes/record_start.gif");
            (this.startStopButton = new ImageButtonCanvas(this.startImage, this.stopImage, "start", this)).resize(16, 16);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            this.setLayout(gridBagLayout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 18;
            gridBagLayout.setConstraints(this.startStopButton, gridBagConstraints);
            this.add(this.startStopButton);
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 18;
            gridBagLayout.setConstraints(this.imageDesc, gridBagConstraints);
            this.add(this.imageDesc);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 14;
            this.requestFocus();
            this.setCaption(this.appletName());
        }
        catch (Throwable e) {
            this.fatalError(e, "initialize");
        }
    }
    
    void startRecord() {
        if (this.fatalError != null) {
            this.println("can't execute.. fatal error:" + this.fatalError);
            this.setCaption(this.fatalError);
        }
        else {
            this.setCaption("Starting...");
            if (this.startStopButton != null) {
                this.startStopButton.disable();
            }
            this.doCommand(String.valueOf(this.urlStringBase) + "clip.cgi?cmd=rec_start&id=" + this.id);
        }
    }
    
    void stopRecord() {
        this.setCaption("Stopping...");
        if (super.debug) {
            this.println("Stopping clip:" + this.clip_id);
        }
        if (this.startStopButton != null) {
            this.startStopButton.disable();
        }
        this.doCommand(String.valueOf(this.urlStringBase) + "clip.cgi?cmd=rec_stop&id=" + this.id + "&clip_id=" + this.clip_id);
    }
    
    protected boolean handleMessage(final int id, final Object o) {
        boolean handled = false;
        switch (id) {
            case 1100: {
                if (o instanceof ImageButtonCanvas) {
                    final ImageButtonCanvas b = (ImageButtonCanvas)o;
                    this.println("Command: " + b.getCommand());
                    if ("start".equals(b.getCommand())) {
                        this.startRecord();
                    }
                    else if ("stop".equals(b.getCommand())) {
                        this.stopRecord();
                    }
                }
                handled = true;
                break;
            }
        }
        return handled;
    }
    
    void doCommand(final String u) {
        this.cmdInProgress = true;
        try {
            final URL url = new URL(u);
            this.getStatus(url);
            this.cmdInProgress = false;
        }
        catch (Throwable e) {
            this.fatalError(e, "command failed");
        }
    }
    
    String getStatus(final URL url) throws IOException {
        if (super.debug) {
            this.println(url.toString());
        }
        final URLConnection connection = url.openConnection();
        connection.setDefaultUseCaches(false);
        connection.setUseCaches(false);
        connection.setDoOutput(false);
        connection.setDoInput(true);
        connection.setAllowUserInteraction(false);
        if (this.auth != null) {
            connection.setRequestProperty("Authorization", this.auth);
        }
        final InputStream is = connection.getInputStream();
        String out = "";
        boolean allow_recording = false;
        boolean rec = this.recording;
        int percent = 0;
        int max_seconds = -1;
        final DataInputStream in = new DataInputStream(is);
        String error = null;
        for (int x = 0; x < 100; ++x) {
            final String aLine = in.readLine();
            if (aLine == null) {
                break;
            }
            if (super.debug) {
                this.println("Status: " + aLine);
            }
            final int wh = aLine.indexOf("=");
            if (wh != -1) {
                final String name = aLine.substring(0, wh);
                final String value = aLine.substring(wh + 1);
                if (name.equals("recording")) {
                    rec = (Integer.parseInt(value) != 0);
                }
                if (name.equals("recording_enabled")) {
                    allow_recording = (Integer.parseInt(value) != 0);
                }
                if (name.equals("cur_percent")) {
                    percent = Integer.parseInt(value);
                }
                if (name.equals("clip_id")) {
                    this.clip_id = value;
                }
                if (name.equals("error")) {
                    error = value;
                }
                if (name.equals("max_seconds")) {
                    max_seconds = Integer.parseInt(value);
                }
            }
            out = String.valueOf(out) + aLine;
        }
        if (this.recording != rec) {
            this.recording = rec;
            if (this.recording) {
                this.startStopButton.changeFunction(this.stopImage, null, "stop");
            }
            else {
                this.startStopButton.changeFunction(this.startImage, null, "start");
            }
        }
        if (!allow_recording) {
            this.startStopButton.disable();
            return this.fatalError = "Recording disabled.";
        }
        this.startStopButton.enable();
        if (error != null) {
            return error;
        }
        if (this.recording) {
            return "Recording... " + percent / 100 + "." + percent % 10 + "%";
        }
        String reply = "Ready to record ";
        if (max_seconds > 0) {
            reply = String.valueOf(reply) + max_seconds + " second clip.";
        }
        return reply;
    }
    
    void updateStatus() {
        if (this.cmdInProgress) {
            return;
        }
        try {
            final String status = this.getStatus(this.statusURL);
            this.setCaption(status);
        }
        catch (IOException e) {
            this.fatalError(e, "status failed");
        }
    }
    
    public void run() {
        this.println("RecordStatus Run: " + this.paint);
        this.repaint();
        try {
            Thread.sleep(1000L);
            for (int x = 0; x < 100; ++x) {
                if (this.paint != 0) {
                    break;
                }
                if (this.fatalError != null) {
                    break;
                }
                this.repaint();
                Thread.sleep(100L);
            }
            this.println("run started, painted.. " + this.paint);
            if (this.startStopButton != null) {
                this.startStopButton.repaint();
            }
            while (!this.wasQuit()) {
                if (this.fatalError != null) {
                    break;
                }
                this.updateStatus();
                if (this.recording) {
                    Thread.sleep(1000L);
                }
                else {
                    Thread.sleep(8000L);
                }
            }
        }
        catch (Exception e) {
            this.fatalError(e, "run");
        }
        if (this.fatalError != null) {
            this.setCaption(this.fatalError);
        }
    }
}
