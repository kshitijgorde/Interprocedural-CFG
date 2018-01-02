// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.jst.Event;
import com.fluendo.jst.Message;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import com.fluendo.utils.Debug;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import com.fluendo.jst.BusHandler;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class Cortado extends Applet implements Runnable, MouseMotionListener, MouseListener, BusHandler, StatusListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private static Cortado cortado;
    private static CortadoPipeline pipeline;
    private String urlString;
    private boolean seekable;
    private boolean audio;
    private boolean video;
    private boolean keepAspect;
    private boolean autoPlay;
    private int bufferSize;
    private String userId;
    private String password;
    private int bufferLow;
    private int bufferHigh;
    private int debug;
    private double duration;
    private boolean statusRunning;
    private Thread statusThread;
    private Status status;
    private int statusHeight;
    private boolean inStatus;
    private boolean isBuffering;
    private int desiredState;
    private boolean isEOS;
    private boolean isError;
    private int showStatus;
    private static final String[] showStatusVals;
    private static final int STATUS_AUTO = 0;
    private static final int STATUS_SHOW = 1;
    private static final int STATUS_HIDE = 2;
    private int hideTimeout;
    private int hideCounter;
    private boolean mayHide;
    private PopupMenu menu;
    private Hashtable params;
    private Configure configure;
    private Dimension appletDimension;
    private int rePlayTime;
    
    public Cortado() {
        this.params = new Hashtable();
    }
    
    public String getAppletInfo() {
        return "Title: Fluendo media player \nAuthor: Wim Taymans \nA Java based network multimedia player.";
    }
    
    public String getRevision() {
        return "$Revision: 4059 $";
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "url", "URL", "The media file to play" }, { "seekable", "boolean", "Can you seek in this file (default false)" }, { "duration", "float", "Total duration of the file in seconds (default unknown)" }, { "audio", "boolean", "Enable audio playback (default true)" }, { "video", "boolean", "Enable video playback (default true)" }, { "statusHeight", "int", "The height of the status area (default 12)" }, { "autoPlay", "boolean", "Automatically start playback (default true)" }, { "showStatus", "enum", "Show status area (auto|show|hide) (default auto)" }, { "hideTimeout", "int", "Timeout in seconds to hide the status area when showStatus is auto (default 0)" }, { "keepAspect", "boolean", "Use aspect ratio of video (default true)" }, { "bufferSize", "int", "The size of the prebuffer in Kbytes (default 100)" }, { "bufferLow", "int", "Percent of empty buffer (default 10)" }, { "bufferHigh", "int", "Percent of full buffer (default 70)" }, { "userId", "string", "userId for basic authentication (default null)" }, { "password", "string", "password for basic authentication (default null)" }, { "rePlayTime", "int", "replay Time in s (default 30s)" }, { "debug", "int", "Debug level 0 - 4 (default = 3)" } };
        return info;
    }
    
    public void setParam(final String name, final String value) {
        this.params.put(name, value);
    }
    
    public void restart() {
        this.stop();
        this.init();
        this.start();
    }
    
    public String getParam(final String name, final String def) {
        String result = this.params.get(name);
        if (result == null) {
            try {
                result = this.getParameter(name);
            }
            catch (Exception ex) {}
        }
        if (result == null) {
            result = def;
        }
        return result;
    }
    
    public int getEnum(final String name, final String[] vals, final String def) {
        final String val = this.getParam(name, def);
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i].equals(val)) {
                return i;
            }
        }
        return 0;
    }
    
    public static void shutDown(final Throwable error) {
        Debug.log(3, "shutting down: reason: " + error.getMessage());
        error.printStackTrace();
        Cortado.cortado.stop();
    }
    
    public synchronized void init() {
        Cortado.cortado = this;
        System.out.println("init()");
        if (Cortado.pipeline != null) {
            this.stop();
        }
        Cortado.pipeline = new CortadoPipeline();
        this.configure = new Configure();
        this.urlString = this.getParam("url", null);
        this.seekable = String.valueOf(this.getParam("seekable", "false")).equals("true");
        this.duration = Double.valueOf(this.getParam("duration", "-1.0"));
        this.audio = String.valueOf(this.getParam("audio", "true")).equals("true");
        this.video = String.valueOf(this.getParam("video", "false")).equals("true");
        this.statusHeight = Integer.valueOf(this.getParam("statusHeight", "12"));
        this.autoPlay = String.valueOf(this.getParam("autoPlay", "true")).equals("true");
        this.showStatus = this.getEnum("showStatus", Cortado.showStatusVals, "auto");
        this.hideTimeout = Integer.valueOf(this.getParam("hideTimeout", "0"));
        this.keepAspect = String.valueOf(this.getParam("keepAspect", "true")).equals("true");
        this.bufferSize = Integer.valueOf(this.getParam("bufferSize", "200"));
        this.bufferLow = Integer.valueOf(this.getParam("bufferLow", "10"));
        this.bufferHigh = Integer.valueOf(this.getParam("bufferHigh", "70"));
        this.debug = Integer.valueOf(this.getParam("debug", "3"));
        this.userId = this.getParam("userId", null);
        this.password = this.getParam("password", null);
        this.rePlayTime = Integer.valueOf(this.getParam("rePlayTime", "30"));
        if (System.getProperty("java.vendor").toUpperCase().startsWith("MICROSOFT", 0)) {
            this.seekable = false;
        }
        Debug.level = this.debug;
        Debug.log(3, "build info: " + this.configure.buildInfo);
        Debug.log(3, "revision: " + this.getRevision());
        Cortado.pipeline.setUrl(this.urlString);
        Cortado.pipeline.setUserId(this.userId);
        Cortado.pipeline.setPassword(this.password);
        Cortado.pipeline.enableAudio(this.audio);
        Cortado.pipeline.enableVideo(this.video);
        Cortado.pipeline.setBufferSize(this.bufferSize);
        Cortado.pipeline.setBufferLow(this.bufferLow);
        Cortado.pipeline.setBufferHigh(this.bufferHigh);
        Cortado.pipeline.setRePlayTime(this.rePlayTime);
        URL documentBase;
        try {
            documentBase = this.getDocumentBase();
            Debug.log(3, "Document base: " + documentBase);
        }
        catch (Throwable t) {
            documentBase = null;
        }
        Cortado.pipeline.setDocumentBase(documentBase);
        Cortado.pipeline.setComponent(this);
        Cortado.pipeline.getBus().addHandler(this);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        (this.status = new Status(this, Cortado.pipeline)).setHaveAudio(this.audio);
        this.status.setHavePercent(true);
        this.status.setSeekable(this.seekable);
        this.status.setDuration(this.duration);
        this.inStatus = false;
        this.mayHide = (this.hideTimeout == 0);
        this.hideCounter = 0;
        if (this.showStatus != 2) {
            this.status.setVisible(true);
        }
        else {
            this.status.setVisible(false);
        }
        (this.menu = new PopupMenu()).add("Help");
        this.menu.add("About...");
        this.menu.addActionListener(this);
        this.add(this.menu);
    }
    
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        if (command.equals("Help")) {}
        if (command.equals("About...")) {
            final AboutFrame about = new AboutFrame(Cortado.pipeline);
            about.d.setVisible(true);
        }
    }
    
    public Graphics getGraphics() {
        final Graphics g = super.getGraphics();
        g.setColor(Color.white);
        if (this.status != null && this.status.isVisible()) {
            g.setClip(0, 0, this.getSize().width, this.getSize().height - this.statusHeight);
        }
        else {
            g.setClip(0, 0, this.getSize().width, this.getSize().height);
        }
        return g;
    }
    
    public Dimension getSize() {
        if (this.appletDimension == null) {
            this.appletDimension = super.getSize();
        }
        return this.appletDimension;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void run() {
        try {
            this.realRun();
        }
        catch (Throwable t) {
            shutDown(t);
        }
    }
    
    private void realRun() {
        Debug.log(3, "entering status thread");
        while (this.statusRunning) {
            try {
                final long now = Cortado.pipeline.getPosition() / 1000000L;
                this.status.setTime(now);
                Thread.sleep(1000L);
                if (this.hideCounter <= 0) {
                    continue;
                }
                --this.hideCounter;
                if (this.hideCounter != 0) {
                    continue;
                }
                this.mayHide = true;
                this.setStatusVisible(false, false);
            }
            catch (Exception e) {
                if (!this.statusRunning) {
                    continue;
                }
                e.printStackTrace();
            }
        }
        Debug.log(3, "exit status thread");
    }
    
    public void paint(final Graphics g) {
        final int dwidth = this.getSize().width;
        final int dheight = this.getSize().height;
        if (dwidth <= 0 || dheight <= this.statusHeight) {
            this.appletDimension = null;
            return;
        }
        if (this.status != null && this.status.isVisible()) {
            this.status.setBounds(0, dheight - this.statusHeight, dwidth, this.statusHeight);
            this.status.paint(g);
        }
    }
    
    private void setStatusVisible(final boolean b, final boolean force) {
        if (this.status.isVisible() == b) {
            return;
        }
        if (!b && !this.mayHide) {
            return;
        }
        if (!force) {
            if (this.showStatus == 1 && !b) {
                return;
            }
            if (this.showStatus == 2 && b) {
                return;
            }
        }
        if (this.isError && !b) {
            return;
        }
        if (this.inStatus && !b) {
            return;
        }
        this.status.setVisible(b);
        this.repaint();
    }
    
    private boolean intersectStatus(final MouseEvent e) {
        return this.inStatus = (e.getY() > this.getSize().height - this.statusHeight);
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
        this.setStatusVisible(false, false);
    }
    
    public void mousePressed(final MouseEvent e) {
        if (this.intersectStatus(e)) {
            final int y = this.getSize().height - this.statusHeight;
            e.translatePoint(0, -y);
            this.status.mousePressed(e);
        }
        else if ((e.getModifiers() & 0x4) == 0x4) {
            this.menu.show(this, e.getX(), e.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.intersectStatus(e)) {
            final int y = this.getSize().height - this.statusHeight;
            e.translatePoint(0, -y);
            this.status.mouseReleased(e);
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        if (this.intersectStatus(e)) {
            final int y = this.getSize().height - this.statusHeight;
            this.setStatusVisible(true, false);
            e.translatePoint(0, -y);
            this.status.mouseMoved(e);
        }
        else {
            this.setStatusVisible(false, false);
        }
    }
    
    public void handleMessage(final Message msg) {
        System.out.print("handleMessage");
        switch (msg.getType()) {
            case 2:
            case 4: {
                System.out.println(msg.toString());
                if (!this.isError) {
                    this.status.setMessage(msg.parseErrorString());
                    this.status.setState(0);
                    Cortado.pipeline.setState(1);
                    this.setStatusVisible(true, true);
                    this.isError = true;
                    break;
                }
                break;
            }
            case 1: {
                Debug.log(3, "EOS: playback ended");
                if (!this.isError) {
                    break;
                }
                break;
            }
            case 8192: {
                System.out.println(msg.toString());
                break;
            }
            case 524288: {
                if (!this.isError) {
                    this.status.setMessage(msg.parseResourceString());
                    this.setStatusVisible(true, false);
                    break;
                }
                break;
            }
            case 32: {
                if (this.isError) {
                    break;
                }
                final boolean busy = msg.parseBufferingBusy();
                final int percent = msg.parseBufferingPercent();
                if (busy) {
                    if (!this.isBuffering) {
                        Debug.log(3, "PAUSE: we are buffering");
                        if (this.desiredState == 3) {
                            Cortado.pipeline.setState(2);
                        }
                        this.setStatusVisible(this.isBuffering = true, false);
                    }
                    this.status.setBufferPercent(busy, percent);
                    break;
                }
                if (this.isBuffering) {
                    Debug.log(3, "PLAY: we finished buffering");
                    if (this.desiredState == 3) {
                        Cortado.pipeline.setState(3);
                    }
                    this.setStatusVisible(this.isBuffering = false, false);
                }
                this.status.setBufferPercent(busy, percent);
                break;
            }
            case 64: {
                if (msg.getSrc() == Cortado.pipeline) {
                    final int old = msg.parseStateChangedOld();
                    final int next = msg.parseStateChangedNext();
                    switch (next) {
                        case 2: {
                            if (!this.isError && !this.isEOS) {
                                this.status.setMessage("Paused");
                            }
                            this.status.setState(1);
                            break;
                        }
                        case 3: {
                            if (!this.isError && !this.isEOS) {
                                this.status.setMessage("Playing");
                                this.setStatusVisible(false, false);
                                if (!this.mayHide) {
                                    this.hideCounter = this.hideTimeout;
                                }
                            }
                            this.status.setState(2);
                            break;
                        }
                        case 1: {
                            if (!this.isError && !this.isEOS) {
                                this.status.setMessage("Stopped");
                                this.setStatusVisible(true, false);
                            }
                            this.status.setState(0);
                            break;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public void doPause() {
        this.isError = false;
        this.isEOS = false;
        this.status.setMessage("Pause");
        this.desiredState = 2;
        Cortado.pipeline.setState(this.desiredState);
    }
    
    public void doPlay() {
        this.isError = false;
        this.isEOS = false;
        this.status.setMessage("Play");
        this.desiredState = 3;
        Cortado.pipeline.setState(this.desiredState);
    }
    
    public void doStop() {
        this.status.setMessage("Stop");
        this.desiredState = 1;
        Cortado.pipeline.setState(this.desiredState);
    }
    
    public void doSeek(final double aPos) {
        final Event event = Event.newSeek(5, (int)(aPos * 100.0 * 10000.0));
        final boolean res = Cortado.pipeline.sendEvent(event);
        if (!res) {
            Debug.log(2, "seek failed");
        }
    }
    
    public void newState(final int aState) {
        switch (aState) {
            case 1: {
                this.doPause();
                break;
            }
            case 2: {
                this.doPlay();
                break;
            }
            case 0: {
                this.doStop();
                break;
            }
        }
    }
    
    public void newSeek(final double aPos) {
        this.doSeek(aPos);
    }
    
    public synchronized void start() {
        System.out.println("start()");
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.status.addStatusListener(this);
        if (this.autoPlay) {
            this.desiredState = 3;
        }
        else {
            this.desiredState = 2;
        }
        final int res = Cortado.pipeline.setState(this.desiredState);
        if (this.statusThread != null) {
            throw new RuntimeException("invalid state");
        }
        this.statusThread = new Thread(this, "cortado-StatusThread-" + Debug.genId());
        this.statusRunning = true;
        this.statusThread.start();
    }
    
    public synchronized void stop() {
        System.out.println("stop()");
        this.statusRunning = false;
        this.desiredState = 1;
        if (Cortado.pipeline != null) {
            System.out.println("pipeline stop");
            Cortado.pipeline.setState(this.desiredState);
            System.out.println("pipeline shutdown");
            Cortado.pipeline.shutDown();
            System.out.println("pipeline stopped");
            Cortado.pipeline = null;
        }
        if (this.statusThread != null) {
            try {
                this.statusThread.interrupt();
            }
            catch (Exception ex) {}
            try {
                this.statusThread.join();
            }
            catch (Exception ex2) {}
            this.statusThread = null;
        }
    }
    
    static {
        showStatusVals = new String[] { "auto", "show", "hide" };
    }
}
