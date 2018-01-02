// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;

public class ControlSession extends Thread
{
    public String idStr;
    String urlBaseStr;
    NuApplet applet;
    String doc;
    final int kUnknown = 999999;
    protected boolean isRunnable;
    protected boolean isRunning;
    long noticeCount;
    int prevState;
    boolean invertVideo;
    public long imagesReceived;
    int queuePos;
    int queueLength;
    protected volatile String querying;
    int backlight;
    int pan;
    int tilt;
    int zoom;
    int minPan;
    int minTilt;
    int minZoom;
    int maxPan;
    int maxTilt;
    int maxZoom;
    int homeHeading;
    int compassDisplay;
    protected static String applet_str;
    boolean trial;
    Preset[] presetList;
    public static final int CONNECTING = 0;
    public static final int WATCHING = 1;
    public static final int IN_QUEUE = 2;
    public static final int IN_CONTROL = 3;
    public static final int CAMERA_OFFLINE = 4;
    public static final int CAMERA_BUSY = 5;
    public static final int NO_CONTROLS = 6;
    public static final int DISCONNECT = 7;
    public static final int UNINITIALIZED = 8;
    protected static final boolean debug = false;
    private int cameraState;
    String cameraName;
    Hashtable camInfo;
    Vector mListeners;
    volatile String postedCommand;
    private int seq;
    long lastStateChange;
    long stateExpiration;
    boolean cameraOperated;
    SessionStatusThread noticeSession;
    boolean mjpeg;
    boolean snapMenu;
    String baseURL;
    String cmds1;
    String cmds2;
    boolean quitting;
    
    static {
        ControlSession.applet_str = null;
    }
    
    public ControlSession(final NuApplet applet, final String doc, final String sessionKind) throws Exception {
        super("ControlSession:" + sessionKind);
        this.idStr = null;
        this.isRunnable = true;
        this.isRunning = false;
        this.noticeCount = 0L;
        this.prevState = 8;
        this.invertVideo = false;
        this.imagesReceived = 0L;
        this.queuePos = -1;
        this.queueLength = 0;
        this.querying = null;
        this.backlight = 0;
        this.pan = 999999;
        this.tilt = 999999;
        this.zoom = 999999;
        this.homeHeading = -1;
        this.compassDisplay = 0;
        this.trial = false;
        this.presetList = null;
        this.cameraState = 8;
        this.cameraName = "";
        this.camInfo = new Hashtable();
        this.mListeners = new Vector();
        this.postedCommand = null;
        this.seq = 1;
        this.lastStateChange = 0L;
        this.stateExpiration = 0L;
        this.cameraOperated = false;
        this.mjpeg = false;
        this.snapMenu = false;
        this.baseURL = null;
        this.cmds1 = "";
        this.cmds2 = "";
        this.quitting = false;
        this.doc = doc;
        this.applet = applet;
        if (applet != null) {
            this.baseURL = applet.getCodeBaseServer();
        }
        if ("ctl".equals(sessionKind)) {
            this.baseURL = String.valueOf(this.baseURL) + "ptz.cgi?doc=";
        }
        else {
            if (!"vid".equals(sessionKind)) {
                throw new Exception("Unknown:" + sessionKind);
            }
            this.baseURL = String.valueOf(this.baseURL) + "ses.cgi?doc=";
        }
        this.baseURL = String.valueOf(this.baseURL) + doc;
    }
    
    protected boolean ok() {
        return this.isRunnable && this.isRunning;
    }
    
    int getCamInfoInt(final String n, final int nf) {
        final String s = this.getCamInfoString(n);
        if (s != null) {
            return Integer.parseInt(s);
        }
        return nf;
    }
    
    protected String cameraName() {
        return this.cameraName;
    }
    
    protected boolean querying() {
        return this.querying != null;
    }
    
    int getCamInfoInt(final String n) {
        return this.getCamInfoInt(n, 0);
    }
    
    String getCamInfoString(final String n) {
        return this.camInfo.get(n);
    }
    
    String getCamInfoString(final String n, final String def) {
        final String s = this.camInfo.get(n);
        if (s != null) {
            return s;
        }
        return def;
    }
    
    int getCamBacklight() {
        final String s = this.getCamInfoString("back_light", "OFF");
        if (s.indexOf("OFF") != 1) {
            return 0;
        }
        return 1;
    }
    
    protected int cameraState() {
        return this.cameraState;
    }
    
    protected void println(final String string) {
    }
    
    protected void parseStaticInfo(final Reply reply) {
        this.parsePresetList(reply);
        this.invertVideo = reply.getBoolean("invert", this.invertVideo);
        this.minPan = reply.getValue("minPan");
        this.minTilt = reply.getValue("minTilt");
        this.minZoom = reply.getValue("minZoom");
        this.maxPan = reply.getValue("maxPan");
        this.maxTilt = reply.getValue("maxTilt");
        this.maxZoom = reply.getValue("maxZoom");
        this.pan = reply.getValue("pan");
        this.tilt = reply.getValue("tilt");
        this.zoom = reply.getValue("zoom");
        this.backlight = reply.getValue("backlight", 0);
        final int debugOverride = reply.getValue("debug", 0);
        if (debugOverride > 0) {
            Debug.set(true);
        }
        this.cameraName = reply.getString("name");
        this.trial = reply.getBoolean("trial", this.trial);
        this.homeHeading = reply.getValue("heading", -1);
        this.compassDisplay = reply.getValue("compass_type", 0);
        this.mjpeg = reply.getBoolean("stream", this.mjpeg);
        this.snapMenu = reply.getBoolean("snap_menu", this.snapMenu);
        reply.copyTo(this.camInfo);
        this.println("parseStaticInfo start: " + this.camInfo.toString());
    }
    
    public synchronized Reply openSession(final String sessionKind) throws Exception {
        final long start = System.currentTimeMillis();
        if (this.cameraState() == 0) {
            throw new Exception("already connecting");
        }
        this.setState(0);
        try {
            String params = "copyright=nuspectra&version=" + Version.getShortVersion();
            if (this.applet.expires != null) {
                params = String.valueOf(params) + "&expires=" + this.applet.expires;
            }
            params = String.valueOf(params) + "&kind=" + sessionKind;
            if (ControlSession.applet_str == null) {
                ControlSession.applet_str = this.applet.getClass().getName();
            }
            params = String.valueOf(params) + "&applet=" + ControlSession.applet_str;
            final String profile = this.applet.getParameter("profile");
            if (profile != null) {
                params = String.valueOf(params) + "&profile=" + profile;
            }
            final Reply reply = this.doCommand("open", params);
            if (reply.getStatus() != 0) {
                Debug.println("open error...." + reply.toString());
            }
            this.idStr = reply.getString("id");
            if (this.idStr != null) {
                this.parseStaticInfo(reply);
            }
            return reply;
        }
        catch (Exception e) {
            final long time = System.currentTimeMillis() - start;
            this.setState(7);
            this.idStr = null;
            this.applet.report("openSession failed [" + time + " ms]");
            throw e;
        }
    }
    
    protected void sessionStatusDied(final String why) {
        if (this.noticeSession != null) {
            this.noticeSession = null;
            this.println("Notice died! " + why);
            this.setDisconnected(why);
        }
    }
    
    protected Reply queryCameraState() throws Exception {
        final Reply reply = this.doCommand("dinfo", "timeout=1800&seq=" + this.seq++);
        final boolean cert = reply.getBoolean("certified", false);
        ++this.noticeCount;
        if (cert) {
            final int p = reply.getValue("pan");
            final int t = reply.getValue("tilt");
            final int z = reply.getValue("zoom");
            final int bl = reply.getValue("backlight", 0);
            this.postCameraChanged(p, t, z, bl);
        }
        return reply;
    }
    
    protected boolean getCameraControl(final long timeOutSeconds) throws Exception {
        this.println("getCameraControl starting with state: " + this.stateID());
        switch (this.cameraState) {
            case 0: {
                return false;
            }
            case 2: {
                break;
            }
            case 3: {
                return true;
            }
            case 4:
            case 5:
            case 7: {
                return false;
            }
            case 6: {
                return false;
            }
            default: {
                final Reply reply = this.doCommand("start");
                this.println(reply.toString());
                break;
            }
        }
        if (this.noticeSession == null) {
            throw new Exception("noticeSession null");
        }
        if (timeOutSeconds > 0L) {
            final long stop = System.currentTimeMillis() + timeOutSeconds * 1000L;
            while (this.cameraState == 1 || this.cameraState == 2) {
                Thread.sleep(10L);
                if (System.currentTimeMillis() > stop) {
                    break;
                }
            }
        }
        this.println("getCameraControl returning state " + this.stateID());
        return this.inControl();
    }
    
    protected int getPresetCount() {
        if (this.presetList == null) {
            return 0;
        }
        return this.presetList.length;
    }
    
    protected Preset getPreset(final int zIndex) {
        if (zIndex < this.getPresetCount()) {
            return this.presetList[zIndex];
        }
        return null;
    }
    
    protected void parsePresetList(final Reply presetReply) {
        try {
            final int count = presetReply.getValue("preset_count", 0);
            if (count <= 0) {
                this.presetList = null;
                return;
            }
            final Preset[] tempList = new Preset[count];
            for (int x = 0; x < count; ++x) {
                final String p = "preset_" + (1 + x) + "_";
                final String name = presetReply.getString(String.valueOf(p) + "name");
                final int id = presetReply.getValue(String.valueOf(p) + "id");
                tempList[x] = new Preset(id, name);
            }
            this.presetList = tempList;
        }
        catch (Exception e) {
            this.applet.report(e);
            this.presetList = null;
        }
    }
    
    protected Hashtable getCameraInfo() {
        try {
            return this.camInfo;
        }
        catch (Exception e) {
            this.applet.report(e, "getCameraInfo():");
            return null;
        }
    }
    
    public String stateID() {
        return this.stateID(this.cameraState);
    }
    
    public String stateID(final int v) {
        switch (v) {
            case 0: {
                return new String("CONNECTING");
            }
            case 1: {
                return new String("WATCHING");
            }
            case 2: {
                return new String("IN_QUEUE");
            }
            case 3: {
                return new String("IN_CONTROL");
            }
            case 4: {
                return new String("CAMERA_OFFLINE");
            }
            case 5: {
                return new String("CAMERA_BUSY");
            }
            case 7: {
                return new String("DISCONNECT");
            }
            case 8: {
                return new String("STARTUP");
            }
            case 6: {
                return new String("CONTROLS_UNAVAILABLE");
            }
            default: {
                return new String("?!");
            }
        }
    }
    
    protected String stateFailureMessage() {
        switch (this.cameraState) {
            case 4: {
                return new String("Camera is currently offline or unavailable");
            }
            case 5: {
                return new String("Camera busy or in use by administrator");
            }
            case 7: {
                return new String("Disconnected");
            }
            case 8: {
                return new String("UNINITIALIZED");
            }
            default: {
                return new String("Unknown camera state: " + this.cameraState);
            }
        }
    }
    
    public String toString() {
        return " run: " + (this.isRunnable ? "Y" : "N") + (this.isRunning ? "Y" : "N") + "  [" + this.pan + "," + this.tilt + "," + this.zoom + "] count=" + this.noticeCount % 100L + " " + this.stateID();
    }
    
    private synchronized void setState(final int v) {
        if (!this.isRunnable && v != 7) {
            Debug.println("setState disconnected:" + v);
        }
        this.setState(v, this.queuePos, this.queueLength);
    }
    
    private void setState(final int v, final int qp, final int ql) {
        boolean notify = false;
        if (qp != this.queuePos) {
            notify = true;
            this.queuePos = qp;
        }
        if (ql != this.queueLength) {
            notify = true;
            this.queueLength = ql;
        }
        if (this.cameraState != v) {
            notify = true;
            this.prevState = this.cameraState;
            this.cameraState = v;
            this.println("**** CameraState from " + this.stateID(this.prevState) + " to " + this.stateID(this.cameraState));
            this.lastStateChange = System.currentTimeMillis();
            switch (v) {
                case 0: {
                    this.stateExpiration = 0L;
                }
                case 7: {
                    this.stateExpiration = 0L;
                    if (this.isRunnable) {
                        this.isRunnable = false;
                        break;
                    }
                    break;
                }
            }
        }
        if (notify) {
            this.postStateChange();
        }
    }
    
    protected long msRemaining() {
        if (this.stateExpiration != 0L) {
            return this.stateExpiration - System.currentTimeMillis();
        }
        return -1L;
    }
    
    protected boolean inControl() {
        return this.cameraState == 3 || (this.cameraState == 1 && this.queueLength == 0);
    }
    
    protected boolean inQueue() {
        return this.cameraState == 2;
    }
    
    protected boolean disconnected() {
        return this.cameraState == 7;
    }
    
    public void finalize() {
        if (this.isRunnable) {
            this.println("finalize closeSession");
            this.closeSession();
        }
    }
    
    private void closeSession() {
        if (!this.isRunnable) {
            Debug.report("closeSesion called twice?");
            return;
        }
        if (this.noticeSession != null) {
            Debug.println("quit noticeSession");
            this.noticeSession.quit();
            this.noticeSession = null;
        }
        else {
            Debug.println("noticeSession closed");
        }
        try {
            if (this.idStr != null) {
                if (!this.disconnected()) {
                    this.doCommand("close");
                }
                this.idStr = null;
            }
        }
        catch (Exception e) {
            Debug.report(e, "closeSession");
        }
        this.setDisconnected("closeSession called");
        this.isRunnable = false;
        synchronized (this) {
            this.notifyAll();
        }
        try {
            for (int x = 0; x < 10 && this.isRunning; ++x) {
                Debug.println("wait quit control session:" + this.idStr);
                synchronized (this) {
                    this.notifyAll();
                }
                Thread.sleep(1000L);
            }
            if (this.isAlive()) {
                Debug.println("Stopping control session:" + this.inspect("\r"));
                this.stop();
            }
        }
        catch (Exception ex) {}
    }
    
    private void setDisconnected(final String why) {
        this.println("session disconnected: " + why);
        this.setState(7);
        this.isRunnable = false;
    }
    
    protected void parseReply(final Reply r) throws Exception {
        int i = r.getValue("state", 999999);
        if (i == 999999) {
            i = 7;
            this.applet.report("video session state not set:" + r.inspect(";"));
        }
        if (i == 3 || i == 2) {
            final int remain = r.getValue("timer", 0);
            if (remain >= 0) {
                this.stateExpiration = System.currentTimeMillis() + remain * 1000;
            }
            else {
                this.applet.println("no timer value? " + remain);
            }
        }
        else {
            this.stateExpiration = 0L;
        }
        this.queuePos = r.getValue("qpos");
        this.queueLength = r.getValue("qlen");
        this.setState(i);
    }
    
    protected int readShort(final byte[] header, final int from) {
        short r = (short)(header[from] << 8 & 0xFF00);
        r |= (short)(header[from + 1] & 0xFF);
        return r;
    }
    
    protected int readInt(final byte[] header, final int from) {
        final int b1 = header[from + 0] & 0xFF;
        final int b2 = header[from + 1] & 0xFF;
        final int b3 = header[from + 2] & 0xFF;
        final int b4 = header[from + 3] & 0xFF;
        final int result = b1 << 24 | b2 << 16 | b3 << 8 | (b4 & 0xFF);
        return result;
    }
    
    protected int takeHeader(final byte[] header) throws Exception {
        final int zb = header[0];
        if (zb == 20) {
            final int i = header[1];
            int len = this.readInt(header, 2);
            final int qp = this.readShort(header, 6);
            final int ql = this.readShort(header, 8);
            final int p = this.readShort(header, 10);
            final int t = this.readShort(header, 12);
            final int z = this.readShort(header, 14);
            final int flags = this.readShort(header, 16);
            final boolean q = (flags & 0x1) != 0x0;
            if (!q) {
                this.postCameraChanged(p, t, z, this.backlight);
            }
            if (i != 3 && i != 2) {
                this.stateExpiration = 0L;
            }
            if (this.isRunnable) {
                this.setState(i, qp, ql);
            }
            else {
                len = -1;
            }
            return len;
        }
        final String temp = new String(header);
        Debug.report("invalid header? " + temp);
        this.setState(7, 0, 0);
        return 0;
    }
    
    private Reply executeCommand(final URL url) throws Exception {
        try {
            if (!this.isRunnable) {
                throw new Exception("ControlSession: No Connecton");
            }
            final String cmdURL = url.toString();
            if (this.querying() && cmdURL.indexOf("cmd=close") == -1) {
                this.println("Command In Progress " + this.querying + " wait for " + cmdURL);
                while (this.querying() && this.isRunnable) {
                    Thread.sleep(100L);
                }
                if (!this.isRunnable) {
                    throw new Exception("session terminated");
                }
            }
            this.querying = cmdURL;
            final AppletSocket u = this.applet.getConnection(url);
            if (u == null) {
                this.querying = null;
                throw new Exception("UNAUTHORIZED");
            }
            final Reply reply = new Reply(u.getDataInputStream());
            this.cmds2 = this.cmds1;
            this.cmds1 = url.getFile();
            int ch = this.cmds1.indexOf("cmd=");
            if (ch != -1) {
                this.cmds1 = this.cmds1.substring(ch + 4, this.cmds1.length());
                ch = this.cmds1.indexOf("&");
                if (ch != -1) {
                    this.cmds1 = this.cmds1.substring(0, ch);
                }
            }
            this.querying = null;
            this.parseReply(reply);
            return reply;
        }
        catch (Exception e) {
            this.querying = null;
            final String cmd = url.toString();
            Debug.report(e, "executeCommand terminated: " + cmd);
            throw e;
        }
        finally {
            this.querying = null;
        }
    }
    
    protected URL cmdURL(final String cmd, final String arguments) throws Exception {
        String urlString = this.baseURL;
        if (this.idStr != null) {
            urlString = String.valueOf(urlString) + "&id=" + this.idStr;
        }
        urlString = String.valueOf(urlString) + "&cmd=" + cmd;
        if (arguments != null) {
            urlString = String.valueOf(urlString) + "&" + arguments;
        }
        final URL url = new URL(urlString);
        return url;
    }
    
    protected Reply doCommand(final String cmd, final String arguments) throws Exception {
        if (this.cameraState == 7) {
            throw new Exception("Disconnected.");
        }
        return this.executeCommand(this.cmdURL(cmd, arguments));
    }
    
    protected Reply doCommand(final String cmd) throws Exception {
        return this.doCommand(cmd, null);
    }
    
    protected String inspect(final String br) {
        return "";
    }
    
    protected void startNoticeThread() {
        if (this.noticeSession != null && this.noticeSession.isAlive()) {
            return;
        }
        (this.noticeSession = new SessionStatusThread(this)).start();
    }
    
    protected synchronized void postCommand(final String cmd) {
        this.postedCommand = cmd;
        try {
            this.notifyAll();
        }
        catch (Exception ex) {}
    }
    
    protected synchronized String getCommand() {
        try {
            while (this.isRunnable && !this.commandPosted()) {
                this.wait(500L);
            }
            if (this.isRunnable && this.commandPosted()) {
                final String out = this.postedCommand;
                this.postedCommand = null;
                this.notifyAll();
                return out;
            }
        }
        catch (Exception ex) {}
        Debug.println("ControlSession getCommand exiting!");
        return null;
    }
    
    public void run() {
        Debug.println("ControlSession run start.");
        this.isRunnable = true;
        this.isRunning = true;
        try {
            if (this.idStr == null) {
                this.isRunnable = false;
                Debug.println("session not open!");
            }
            else {
                Debug.println("start notice thread ");
                this.startNoticeThread();
                Debug.println("notice thread started");
            }
            this.applet.sendMessage(1020);
            while (this.isRunnable) {
                try {
                    final String cmd = this.getCommand();
                    if (this.isRunnable && cmd != null) {
                        final Reply reply = this.doCommand(cmd, null);
                        this.println(reply.toString());
                    }
                    else {
                        this.println("skip command, not in control: " + cmd);
                    }
                    "close".equals(cmd);
                }
                catch (Exception e) {
                    Debug.report(e, "ControlSession run exception");
                    break;
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        this.isRunning = false;
        this.setDisconnected("run exited");
        this.postSetSession();
    }
    
    protected void quit() {
        Debug.println("ControlSession Quit start: " + (this.quitting ? " in progress" : ""));
        if (!this.quitting) {
            this.quitting = true;
            if (this.isRunnable && this.isAlive()) {
                try {
                    this.closeSession();
                    for (int x = 0; x < 100; ++x) {
                        if (!this.isAlive()) {
                            break;
                        }
                        Thread.sleep(250L);
                        Debug.println("waiting for ControlSession quit...");
                        synchronized (this) {
                            this.notifyAll();
                        }
                    }
                }
                catch (Exception e) {
                    Debug.report(e);
                }
            }
            this.println("ControlSession exit");
        }
    }
    
    protected boolean commandPosted() {
        return this.postedCommand != null;
    }
    
    protected void postOperateCamera(final String arg) {
        this.postCommand("setpos&" + arg);
    }
    
    protected void standardMove(final String i) {
        this.postCommand("move&std=" + i);
    }
    
    protected void mapClick(final int x, final int y) {
        this.postCommand("map&x=" + x + "&y=" + y);
    }
    
    protected void setPan(final int v) throws Exception {
        this.postOperateCamera("p=" + v);
        this.postMoveRequested(v, this.tilt, this.zoom, this.backlight);
    }
    
    protected void setTilt(final int v) throws Exception {
        this.postOperateCamera("t=" + v);
        this.postMoveRequested(this.pan, v, this.zoom, this.backlight);
    }
    
    protected void setZoom(final int v) throws Exception {
        this.postOperateCamera("z=" + v);
        this.postMoveRequested(this.pan, this.tilt, v, this.backlight);
    }
    
    protected void setPosition(final int p, final int t, final int z) throws Exception {
        if (this.querying == null) {
            this.postCameraChanged(p, t, z, this.backlight);
        }
        this.postMoveRequested(p, t, z, this.backlight);
        this.postOperateCamera("p=" + p + "&t=" + t + "&z=" + z);
    }
    
    protected void postGoPreset(final Preset preset) {
        this.postCommand("gopreset&num=" + preset.id);
    }
    
    protected void goToPreset(final int id) {
        this.postCommand("gopreset&num=" + id);
    }
    
    protected void snapPicture() {
        this.postCommand("snap");
    }
    
    protected int getPan() {
        return this.pan;
    }
    
    protected int getTilt() {
        return this.tilt;
    }
    
    protected int getZoom() {
        return this.zoom;
    }
    
    protected boolean connected() {
        return this.cameraState != 0 && this.cameraState != 7;
    }
    
    public void addListener(final SessionListener inListener) {
        if (!this.mListeners.contains(inListener)) {
            this.mListeners.addElement(inListener);
        }
        inListener.stateChanged(this.cameraState, this.queuePos, this.queueLength);
        inListener.cameraChanged(this.pan, this.tilt, this.zoom, this.getCamBacklight());
    }
    
    void postStateChange() {
        for (int x = 0; this.mListeners != null && x < this.mListeners.size(); ++x) {
            final SessionListener l = this.mListeners.elementAt(x);
            l.stateChanged(this.cameraState, this.queuePos, this.queueLength);
        }
    }
    
    public void postMoveRequested(final int p, final int t, final int z, final int backlight) {
        for (int x = 0; this.mListeners != null && x < this.mListeners.size(); ++x) {
            final SessionListener l = this.mListeners.elementAt(x);
            l.moveRequested(p, t, z, backlight);
        }
    }
    
    void postSetSession() {
        for (int x = 0; this.mListeners != null && x < this.mListeners.size(); ++x) {
            final SessionListener l = this.mListeners.elementAt(x);
            if (!this.isRunnable) {
                l.setSession(null);
            }
            else {
                l.setSession(this);
            }
        }
    }
    
    protected synchronized void postCameraChanged(final int p, final int t, final int z, final int bl) {
        final boolean changed = p != this.pan || t != this.tilt || z != this.zoom || bl != this.backlight;
        if (this.querying != null) {
            if (changed) {
                this.println("Ignore postCameraChange, query in progress " + this.querying);
            }
            return;
        }
        if (changed) {
            this.pan = p;
            this.tilt = t;
            this.zoom = z;
            this.backlight = bl;
        }
        if (changed) {
            final boolean a = this.commandPosted();
            final boolean b = this.querying();
            if (!a && !b) {
                for (int x = 0; x < this.mListeners.size(); ++x) {
                    final SessionListener l = this.mListeners.elementAt(x);
                    l.cameraChanged(p, t, z, this.backlight);
                }
            }
        }
    }
    
    boolean hasHeading() {
        return this.homeHeading != -1;
    }
    
    double getCameraHeading() {
        double t = this.homeHeading + this.pan / 100.0;
        if (t < 0.0) {
            t += 360.0;
        }
        return t;
    }
    
    protected static String getAlphaHeadingString(double hdg) {
        if (hdg < 0.0) {
            hdg += 360.0;
        }
        final String[] rose = { "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N" };
        int cp = (int)Math.round(hdg);
        if (cp < 0) {
            cp += 360;
        }
        cp %= 360;
        final int v = (int)Math.round(cp / 22.5);
        if (v < 0 || v > 16) {
            return "???";
        }
        return rose[v];
    }
    
    static String getNumericHeadingString(final double h) {
        final int hdg = (int)Math.round(h);
        if (hdg < 0) {
            return "";
        }
        if (hdg < 10) {
            return "00" + hdg;
        }
        if (hdg < 100) {
            return "0" + hdg;
        }
        return new StringBuffer().append(hdg).toString();
    }
    
    String compassString() {
        switch (this.compassDisplay) {
            case 0: {
                return "";
            }
            case 1: {
                return String.valueOf(getNumericHeadingString(this.getCameraHeading())) + "°";
            }
            case 2: {
                return getAlphaHeadingString(this.getCameraHeading());
            }
            default: {
                return "???";
            }
        }
    }
    
    boolean controlsEnabled() {
        return this.connected();
    }
}
