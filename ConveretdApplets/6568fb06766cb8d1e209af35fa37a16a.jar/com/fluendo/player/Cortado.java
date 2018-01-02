// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.jst.Event;
import com.fluendo.jst.Message;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
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
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class Cortado extends Applet implements Runnable, MouseMotionListener, MouseListener, ComponentListener, BusHandler, StatusListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private Cortado cortado;
    private CortadoPipeline pipeline;
    private String urlString;
    private boolean audio;
    private boolean video;
    private int kateIndex;
    private String kateLanguage;
    private String kateCategory;
    private boolean showSpeaker;
    private boolean keepAspect;
    private boolean ignoreAspect;
    private boolean autoPlay;
    private int bufferSize;
    private String userId;
    private String password;
    private int bufferLow;
    private int bufferHigh;
    private int debug;
    private double durationParam;
    private boolean statusRunning;
    private Thread statusThread;
    public Status status;
    private int statusHeight;
    private boolean inStatus;
    private boolean isBuffering;
    private int desiredState;
    private boolean started;
    private boolean isEOS;
    private boolean isError;
    private static final String[] autoBoolVals;
    private static final int BOOL_AUTO = 0;
    private static final int BOOL_TRUE = 1;
    private static final int BOOL_FALSE = 2;
    private int seekable;
    private int live;
    private int showStatus;
    private static final String[] showStatusVals;
    public static final int STATUS_AUTO = 0;
    public static final int STATUS_SHOW = 1;
    public static final int STATUS_HIDE = 2;
    private int hideTimeout;
    private int hideCounter;
    private boolean mayHide;
    public double currentTime;
    private double _currentTime;
    public double duration;
    public boolean paused;
    public String src;
    private PopupMenu menu;
    private Hashtable params;
    private Configure configure;
    private Dimension appletDimension;
    
    public Cortado() {
        this.statusHeight = 20;
        this.started = false;
        this.currentTime = 0.0;
        this.duration = -1.0;
        this.params = new Hashtable();
    }
    
    public String getAppletInfo() {
        return "Title: Fluendo media player \nAuthor: Wim Taymans \nA Java based network multimedia player.";
    }
    
    public String getRevision() {
        return "$Revision$";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "url", "URL", "The media file to play" }, { "seekable", "enum", "Can you seek in this file (auto|true|false) (default auto)" }, { "live", "enum", "Is this a live stream (disabled PAUSE) (auto|true|false) (default auto)" }, { "duration", "float", "Total duration of the file in seconds (default unknown)" }, { "audio", "boolean", "Enable audio playback (default true)" }, { "video", "boolean", "Enable video playback (default true)" }, { "kateIndex", "boolean", "Enable playback of a particular Kate stream (default -1 (none))" }, { "kateLanguage", "string", "Enable playback of a Kate stream from a language (default empty)" }, { "kateCategory", "string", "Enable playback of a Kate stream from a category (default empty)" }, { "statusHeight", "int", "The height of the status area (default 12)" }, { "autoPlay", "boolean", "Automatically start playback (default true)" }, { "showStatus", "enum", "Show status area (auto|show|hide) (default auto)" }, { "hideTimeout", "int", "Timeout in seconds to hide the status area when showStatus is auto (default 0)" }, { "showSpeaker", "boolean", "Show a speaker icon when audio is available (default true)" }, { "keepAspect", "boolean", "Use aspect ratio of video (default true)" }, { "ignoreAspect", "boolean", "Ignore the aspect ratio as signalled by the video, always assume square pixels (default false)" }, { "bufferSize", "int", "The size of the prebuffer in Kbytes (default 100)" }, { "bufferLow", "int", "Percent of empty buffer (default 10)" }, { "bufferHigh", "int", "Percent of full buffer (default 70)" }, { "userId", "string", "userId for basic authentication (default null)" }, { "password", "string", "password for basic authentication (default null)" }, { "debug", "int", "Debug level 0 - 4 (default = 3)" } };
    }
    
    public void setParam(final String s, final String s2) {
        this.params.put(s, s2);
    }
    
    public void restart() {
        this.stop();
        this.init();
        this.start();
    }
    
    public String getParam(final String s, final String s2) {
        String parameter = this.params.get(s);
        if (parameter == null) {
            try {
                parameter = this.getParameter(s);
            }
            catch (Exception ex) {}
        }
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public int getEnumParam(final String s, final String[] array, final String s2) {
        int n = -1;
        final String param = this.getParam(s, s2);
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(param)) {
                n = i;
                break;
            }
        }
        if (n != -1) {
            Debug.info("param \"" + s + "\" has enum value \"" + n + "\" (" + array[n] + ")");
        }
        else {
            Debug.info("param \"" + s + "\" has invalid enum value");
        }
        return n;
    }
    
    public String getStringParam(final String s, final String s2) {
        final String param = this.getParam(s, s2);
        Debug.info("param \"" + s + "\" has string value \"" + param + "\"");
        return param;
    }
    
    public boolean getBoolParam(final String s, final boolean b) {
        final String value = String.valueOf(this.getParam(s, b ? "true" : "false"));
        final boolean b2 = value.equals("true") | value.equals("1");
        Debug.info("param \"" + s + "\" has boolean value \"" + b2 + "\"");
        return b2;
    }
    
    public double getDoubleParam(final String s, final double n) {
        final double doubleValue = Double.valueOf(this.getParam(s, "" + n));
        Debug.info("param \"" + s + "\" has double value \"" + doubleValue + "\"");
        return doubleValue;
    }
    
    public int getIntParam(final String s, final int n) {
        final int intValue = Integer.valueOf(this.getParam(s, "" + n));
        Debug.info("param \"" + s + "\" has int value \"" + intValue + "\"");
        return intValue;
    }
    
    public void shutDown(final Throwable t) {
        Debug.log(3, "shutting down: reason: " + t.getMessage());
        t.printStackTrace();
        this.stop();
    }
    
    public synchronized void init() {
        this.cortado = this;
        Debug.info("init()");
        if (this.pipeline != null) {
            this.stop();
        }
        this.pipeline = new CortadoPipeline(this);
        this.configure = new Configure();
        final String stringParam = this.getStringParam("url", null);
        this.urlString = stringParam;
        this.src = stringParam;
        this.seekable = this.getEnumParam("seekable", Cortado.autoBoolVals, "auto");
        this.live = this.getEnumParam("live", Cortado.autoBoolVals, "auto");
        this.durationParam = this.getDoubleParam("duration", -1.0);
        this.audio = this.getBoolParam("audio", true);
        this.video = this.getBoolParam("video", true);
        this.kateIndex = this.getIntParam("kateIndex", -1);
        this.kateLanguage = this.getStringParam("kateLanguage", "");
        this.kateCategory = this.getStringParam("kateCategory", "");
        this.statusHeight = this.getIntParam("statusHeight", 12);
        this.autoPlay = this.getBoolParam("autoPlay", true);
        this.showStatus = this.getEnumParam("showStatus", Cortado.showStatusVals, "auto");
        this.hideTimeout = this.getIntParam("hideTimeout", 3);
        this.showSpeaker = this.getBoolParam("showSpeaker", true);
        this.keepAspect = this.getBoolParam("keepAspect", true);
        this.ignoreAspect = this.getBoolParam("ignoreAspect", false);
        this.bufferSize = this.getIntParam("bufferSize", 200);
        this.bufferLow = this.getIntParam("bufferLow", 10);
        this.bufferHigh = this.getIntParam("bufferHigh", 70);
        this.debug = this.getIntParam("debug", 3);
        this.userId = this.getStringParam("userId", null);
        this.password = this.getStringParam("password", null);
        if (!this.video) {
            this.hideTimeout = Integer.MAX_VALUE;
        }
        Debug.level = this.debug;
        Debug.log(3, "build info: " + this.configure.buildInfo);
        Debug.log(3, "revision: " + this.getRevision());
        if (System.getProperty("java.vendor").toUpperCase().startsWith("MICROSOFT", 0)) {
            Debug.log(2, "Found MS JVM, disable seeking.");
            this.seekable = 2;
        }
        this.pipeline.setUrl(this.urlString);
        this.pipeline.setUserId(this.userId);
        this.pipeline.setPassword(this.password);
        this.pipeline.enableAudio(this.audio);
        this.pipeline.enableVideo(this.video);
        this.pipeline.setIgnoreAspect(this.ignoreAspect);
        this.pipeline.enableKateStream(this.kateIndex, this.kateLanguage, this.kateCategory);
        this.pipeline.setBufferSize(this.bufferSize);
        this.pipeline.setBufferLow(this.bufferLow);
        this.pipeline.setBufferHigh(this.bufferHigh);
        URL documentBase;
        try {
            documentBase = this.getDocumentBase();
            Debug.log(3, "Document base: " + documentBase);
        }
        catch (Throwable t) {
            documentBase = null;
        }
        this.pipeline.setDocumentBase(documentBase);
        this.pipeline.setComponent(this);
        this.pipeline.getBus().addHandler(this);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        (this.status = new Status(this)).setShowSpeaker(this.showSpeaker);
        this.status.setHaveAudio(this.audio);
        this.status.setHavePercent(true);
        if (this.live == 2) {
            this.status.setLive(false);
        }
        else {
            this.status.setLive(true);
        }
        if (this.seekable == 1) {
            this.status.setSeekable(true);
        }
        else {
            this.status.setSeekable(false);
        }
        if (this.durationParam < 0.0) {
            try {
                final String s = (documentBase != null) ? documentBase.toString().substring(0, documentBase.toString().lastIndexOf("/")) : "";
                final String s2 = (this.urlString.indexOf("://") >= 0) ? this.urlString : (s + "/" + this.urlString);
                Debug.log(3, "trying to determine duration for " + s2);
                final double n = new DurationScanner().getDurationForURL(new URL(s2), this.userId, this.password);
                this.durationParam = n;
                this.duration = n;
                Debug.log(3, "Determined stream duration to be approx. " + this.durationParam);
            }
            catch (Exception ex) {
                Debug.log(2, "Couldn't determine duration for stream.");
            }
        }
        this.status.setDuration(this.durationParam);
        this.inStatus = false;
        this.mayHide = (this.hideTimeout == 0);
        this.hideCounter = 0;
        if (this.showStatus != 2) {
            this.status.setVisible(true);
        }
        else {
            this.status.setVisible(false);
        }
        (this.menu = new PopupMenu()).add("About...");
        this.menu.addActionListener(this);
        this.add(this.menu);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("About...")) {
            new AboutFrame(this.pipeline).d.setVisible(true);
        }
    }
    
    public Graphics getGraphics() {
        final Dimension size = this.getSize();
        final Graphics graphics = super.getGraphics();
        if (this.status != null && this.status.isVisible()) {
            graphics.setClip(0, 0, size.width, size.height - this.statusHeight);
        }
        else {
            graphics.setClip(0, 0, size.width, size.height);
        }
        return graphics;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.appletDimension = super.getSize();
        if (this.pipeline != null) {
            this.pipeline.resize(this.appletDimension);
        }
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.appletDimension = super.getSize();
        Debug.debug("Component shown, size = " + this.appletDimension);
        if (this.pipeline != null) {
            this.pipeline.resize(this.appletDimension);
        }
    }
    
    public Dimension getSize() {
        if (this.appletDimension == null) {
            this.appletDimension = super.getSize();
        }
        return this.appletDimension;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        try {
            this.realRun();
        }
        catch (Throwable t) {
            this.shutDown(t);
        }
    }
    
    private void realRun() {
        Debug.log(3, "entering status thread");
        while (this.statusRunning) {
            try {
                if (this._currentTime != this.currentTime && this.currentTime >= 0.0 && this.duration > this.currentTime) {
                    this.doSeek(this.currentTime / this.duration);
                }
                final double n = this.pipeline.getPosition() / 1000000.0;
                this._currentTime = n;
                this.currentTime = n;
                this.status.setTime(this.pipeline.getPosition() / 1000000L);
                Thread.sleep(1000L);
                if (this.hideCounter > 0) {
                    --this.hideCounter;
                    if (this.hideCounter == 0) {
                        this.mayHide = true;
                        this.setStatusVisible(false, false);
                    }
                }
                this.pipeline.enableKateStream(Integer.valueOf(this.getParam("kateIndex", "-1")), this.getParam("kateLanguage", ""), this.getParam("kateCategory", ""));
            }
            catch (Exception ex) {
                if (!this.statusRunning) {
                    continue;
                }
                Debug.log(1, "Exception in status thread:");
                ex.printStackTrace();
            }
        }
        Debug.log(3, "exit status thread");
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        if (width <= 0 || height < this.statusHeight) {
            this.appletDimension = null;
            Debug.log(2, "paint aborted: appletDimension wrong; dwidth " + width + ", dheight " + height + ", statusHeight " + this.statusHeight);
            return;
        }
        if (this.status != null) {
            this.status.setBounds(0, height - this.statusHeight, width, this.statusHeight);
            this.status.paint(graphics);
        }
    }
    
    private void setStatusVisible(boolean visible, final boolean b) {
        if (this.status.isVisible() == visible) {
            return;
        }
        if (!visible && !this.mayHide) {
            return;
        }
        if (!b) {
            if (this.showStatus == 1 && !visible) {
                return;
            }
            if (this.showStatus == 2 && visible) {
                return;
            }
        }
        if (this.isError && !visible) {
            return;
        }
        if (this.inStatus && !visible) {
            visible = true;
        }
        if (visible != this.status.isVisible()) {
            Debug.log(3, "Status: " + (visible ? "Show" : "Hide"));
        }
        if (visible) {
            this.hideCounter = this.hideTimeout;
        }
        this.status.setVisible(visible);
        this.repaint();
    }
    
    private boolean intersectStatus(final MouseEvent mouseEvent) {
        final int y = mouseEvent.getY();
        final int height = this.getSize().height;
        return this.inStatus = (y > height - this.statusHeight && y < height);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.inStatus = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.intersectStatus(mouseEvent)) {
            mouseEvent.translatePoint(0, -(this.getSize().height - this.statusHeight));
            this.status.mousePressed(mouseEvent);
        }
        else if ((mouseEvent.getModifiers() & 0x4) == 0x4) {
            this.menu.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.intersectStatus(mouseEvent)) {
            mouseEvent.translatePoint(0, -(this.getSize().height - this.statusHeight));
            this.status.mouseReleased(mouseEvent);
        }
        else {
            this.status.cancelMouseOperation();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.intersectStatus(mouseEvent)) {
            final int n = this.getSize().height - this.statusHeight;
            this.setStatusVisible(true, false);
            mouseEvent.translatePoint(0, -n);
            this.status.mouseDragged(mouseEvent);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.intersectStatus(mouseEvent)) {
            final int n = this.getSize().height - this.statusHeight;
            this.setStatusVisible(true, false);
            mouseEvent.translatePoint(0, -n);
            this.status.mouseMoved(mouseEvent);
        }
        this.setStatusVisible(true, false);
    }
    
    public void handleMessage(final Message message) {
        switch (message.getType()) {
            case 4: {
                Debug.info(message.toString());
                break;
            }
            case 2: {
                Debug.info(message.toString());
                if (!this.isError) {
                    this.status.setMessage(message.parseErrorString());
                    this.status.setState(0);
                    this.pipeline.setState(1);
                    this.setStatusVisible(true, true);
                    this.isError = true;
                    break;
                }
                break;
            }
            case 1: {
                Debug.log(3, "EOS: playback ended");
                if (!this.isError) {
                    this.status.setState(0);
                    this.status.setMessage("Playback ended");
                    this.isEOS = true;
                    this.pipeline.setState(1);
                    this.setStatusVisible(true, false);
                    break;
                }
                break;
            }
            case 8192: {
                Debug.info(message.toString());
                break;
            }
            case 524288: {
                if (!this.isError) {
                    this.status.setMessage(message.parseResourceString());
                    this.setStatusVisible(true, false);
                    break;
                }
                break;
            }
            case 262144: {
                final long durationValue = message.parseDurationValue();
                this.status.setByteDuration(durationValue);
                Debug.log(4, "got duration: " + durationValue);
                if (durationValue == -1L) {
                    break;
                }
                if (this.seekable == 0) {
                    this.status.setSeekable(true);
                }
                if (this.live == 0) {
                    this.status.setLive(false);
                    break;
                }
                break;
            }
            case 32: {
                if (this.isError) {
                    break;
                }
                final boolean bufferingBusy = message.parseBufferingBusy();
                final int bufferingPercent = message.parseBufferingPercent();
                if (bufferingBusy) {
                    if (!this.isBuffering) {
                        Debug.log(3, "PAUSE: we are buffering");
                        if (this.desiredState == 3) {
                            this.pipeline.setState(2);
                        }
                        this.setStatusVisible(this.isBuffering = true, false);
                    }
                    this.status.setBufferPercent(bufferingBusy, bufferingPercent);
                    break;
                }
                if (this.isBuffering) {
                    Debug.log(3, "PLAY: we finished buffering");
                    if (this.desiredState == 3) {
                        this.pipeline.setState(3);
                    }
                    this.setStatusVisible(this.isBuffering = false, false);
                }
                this.status.setBufferPercent(bufferingBusy, bufferingPercent);
                break;
            }
            case 64: {
                if (message.getSrc() == this.pipeline) {
                    message.parseStateChangedOld();
                    switch (message.parseStateChangedNext()) {
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
            case 1048576: {
                this.status.setBytePosition(message.parseBytePosition());
                break;
            }
        }
    }
    
    public void doPause() {
        this.isError = false;
        this.isEOS = false;
        this.paused = true;
        this.status.setMessage("Pause");
        this.desiredState = 2;
        this.pipeline.setState(this.desiredState);
    }
    
    public void doPlay() {
        this.isError = false;
        this.isEOS = false;
        this.paused = false;
        this.status.setMessage("Play");
        this.desiredState = 3;
        this.pipeline.setState(this.desiredState);
    }
    
    public void doStop() {
        this.status.setMessage("Stop");
        this.desiredState = 1;
        this.pipeline.setState(this.desiredState);
    }
    
    public void doSeek(final double n) {
        if (!this.pipeline.sendEvent(Event.newSeek(5, (int)(n * 100.0 * 10000.0)))) {
            Debug.log(2, "seek failed");
        }
    }
    
    public double getPlayPosition() {
        return this.pipeline.getPosition() / 1000000.0;
    }
    
    public void newState(final int n) {
        switch (n) {
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
    
    public void newSeek(final double n) {
        this.doSeek(n);
    }
    
    public synchronized void start() {
        Debug.info("Application starting");
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.status.addStatusListener(this);
        if (this.autoPlay) {
            this.desiredState = 3;
        }
        else {
            this.desiredState = 2;
        }
        this.pipeline.setState(this.desiredState);
        if (this.statusThread != null) {
            throw new RuntimeException("invalid state");
        }
        this.statusThread = new Thread(this, "cortado-StatusThread-" + Debug.genId());
        this.statusRunning = true;
        this.statusThread.start();
    }
    
    public synchronized void stop() {
        Debug.info("Application stopping...");
        this.status.removeStatusListener(this);
        this.removeMouseMotionListener(this);
        this.removeMouseListener(this);
        this.removeComponentListener(this);
        this.statusRunning = false;
        this.desiredState = 1;
        if (this.pipeline != null) {
            try {
                this.pipeline.setState(this.desiredState);
            }
            catch (Throwable t) {}
            try {
                this.pipeline.shutDown();
            }
            catch (Throwable t2) {}
            this.pipeline = null;
        }
        if (this.statusThread != null) {
            try {
                this.statusThread.interrupt();
            }
            catch (Throwable t3) {}
            try {
                this.statusThread.join();
            }
            catch (Throwable t4) {}
            this.statusThread = null;
        }
        Debug.info("Application stopped");
    }
    
    public int getStatusHeight() {
        return this.statusHeight;
    }
    
    public int getShowStatus() {
        return this.showStatus;
    }
    
    public synchronized void play() {
        this.doPlay();
    }
    
    public synchronized void pause() {
        this.doPause();
    }
    
    static {
        autoBoolVals = new String[] { "auto", "true", "false" };
        showStatusVals = new String[] { "auto", "show", "hide" };
    }
}
