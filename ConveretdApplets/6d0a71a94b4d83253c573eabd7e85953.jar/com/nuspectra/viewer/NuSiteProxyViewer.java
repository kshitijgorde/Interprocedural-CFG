// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.net.URL;
import java.util.Date;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Point;
import java.awt.Color;
import java.awt.Image;

public class NuSiteProxyViewer extends NuApplet implements SessionListener
{
    Image background;
    protected ControlSession session;
    Color backColor;
    MJPEGStream mjpeg;
    protected VideoCanvas videoCanvas;
    boolean reconnectVideo;
    int reconnects;
    PassFrame passFrame;
    String sessionKind;
    protected String loadString;
    int sleep;
    int priority;
    protected Image titleImage;
    String lastDoc;
    private Exception e;
    
    public NuSiteProxyViewer() {
        this.background = null;
        this.backColor = new Color(255, 255, 255);
        this.mjpeg = null;
        this.videoCanvas = null;
        this.reconnectVideo = true;
        this.reconnects = 0;
        this.sessionKind = "vid";
        this.sleep = 0;
        this.priority = 5;
        this.lastDoc = null;
        this.e = null;
    }
    
    public String appletName() {
        return "SiteProxyViewer " + Version.getShortVersion();
    }
    
    public void stateChanged(final int state, final int queuePos, final int queueLen) {
        if (state == 7) {
            this.stopStreaming();
        }
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
    }
    
    public void setSession(final ControlSession s) {
        this.session = s;
    }
    
    public void moveRequested(final int p, final int t, final int z, final int backlight) {
    }
    
    protected synchronized void stopStreaming() {
        if (this.session == null && this.mjpeg == null) {
            return;
        }
        Debug.println("stopStreaming start");
        if (this.mjpeg != null) {
            this.mjpeg.quit();
            this.mjpeg = null;
        }
        if (this.session != null) {
            this.session.quit();
        }
        Debug.println("stopStreaming exit");
    }
    
    public String getDoc() {
        return this.getStrParam("DOC", "1");
    }
    
    protected void handlePTZClick(final Point p) {
    }
    
    protected AppletSocket doCommand(final String path) throws IOException {
        final AppletSocket s = this.getConnection(path);
        return s;
    }
    
    protected float getFPS() {
        return 0.0f;
    }
    
    public void paint(final Graphics g) {
        if (!super.vis) {
            super.vis = true;
            super.paint(g);
        }
        if (this.background != null) {
            g.drawImage(this.background, 0, 0, this);
        }
        else {
            super.paint(g);
        }
    }
    
    public String toString() {
        String s = "Applet: mjpeg=";
        if (this.mjpeg == null) {
            s = String.valueOf(s) + "null";
        }
        else {
            s = String.valueOf(s) + this.mjpeg.toString();
        }
        s = String.valueOf(s) + " ctlSession=";
        if (this.session == null) {
            s = String.valueOf(s) + "null";
        }
        else {
            s = String.valueOf(s) + this.session.toString();
        }
        return s;
    }
    
    public String getParameter(final String name) {
        if (this.session != null) {
            synchronized (this.session) {
                final String out = this.session.getCamInfoString(name);
                if (out != null) {
                    // monitorexit(this.session)
                    return out;
                }
            }
            // monitorexit(this.session)
        }
        return super.getParameter(name);
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
    
    protected MJPEGStream createStream() throws Exception {
        Debug.println("MJPEGStream createStream start:" + this.session);
        if (this.session == null || this.session.idStr == null || this.videoCanvas == null) {
            Debug.println("Unable to create video stream:");
            return null;
        }
        this.videoCanvas.invert = this.session.invertVideo;
        try {
            final SiteProxyVideo nvid = new SiteProxyVideo(this, this.videoCanvas, this.session);
            Debug.println("MJPEGStream createStream created " + nvid.toString());
            return nvid;
        }
        catch (Exception e) {
            this.report(e);
            return null;
        }
    }
    
    protected void startStreaming(final boolean start) {
        Debug.println("startStreaming: " + start + " mjpeg=" + this.mjpeg);
        if (this.videoCanvas == null) {
            Debug.report("Unable to startStreaming, videoCanvas=null.");
            return;
        }
        if (this.mjpeg != null) {
            Debug.println("startStreaming, mjpeg != null...");
            this.stopStreaming();
        }
        try {
            this.mjpeg = this.createStream();
            if (this.mjpeg == null) {
                Debug.println("Warning, no video available.");
                return;
            }
            this.mjpeg.setSleep(this.sleep);
            try {
                if (this.priority != 5) {
                    Debug.println("Setting applet priority to " + this.priority);
                    this.mjpeg.setPriority(this.priority);
                }
            }
            catch (Exception e) {
                Debug.report(e, "Unable to set thread priority.");
            }
            if (super.fAuth != null) {
                this.mjpeg.setAuthorization(super.fAuth);
            }
            Debug.println(this.mjpeg.toString());
        }
        catch (Exception e) {
            this.report(e);
            this.mjpeg = null;
        }
        if (start && this.mjpeg != null) {
            this.mjpeg.fDebug = Debug.get();
            this.mjpeg.start();
        }
    }
    
    protected void pack() {
        this.setSize(this.getPreferredSize());
        this.validate();
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        final boolean handled = super.handleMessage(id, arg);
        switch (id) {
            case 1011: {
                this.pack();
                break;
            }
            case 1012: {
                if (this.videoCanvas != null) {
                    this.videoCanvas.setProgressString("Getting user/password...");
                }
                this.showUserPassDialog();
                break;
            }
            case 1013: {
                if (this.videoCanvas != null) {
                    this.videoCanvas.setProgressString("Initializing video stream...");
                }
                this.setUserPass(PassFrame.myUserName, PassFrame.myPassword);
                this.passFrame = null;
                Debug.println("restarted stream");
                break;
            }
            case 1014: {
                Debug.println("User/pass cancel");
                if (this.videoCanvas != null) {
                    this.videoCanvas.setProgressString("Password required!");
                }
                this.passFrame = null;
                super.fAuth = null;
                this.repaint();
            }
            case 1015: {
                if (this.videoCanvas == null || this.wasQuit()) {
                    break;
                }
                boolean recon = false;
                if (this.mjpeg != null) {
                    synchronized (this.mjpeg) {
                        if (this.mjpeg.images > 0L) {
                            recon = true;
                        }
                    }
                    // monitorexit(this.mjpeg)
                    this.mjpeg = null;
                }
                if (recon && this.reconnectVideo) {
                    ++this.reconnects;
                    this.startStreaming(true);
                    break;
                }
                this.stopStreaming();
                this.videoCanvas.setErrorStr((String)arg, null);
                break;
            }
            case 1016: {
                this.handlePTZClick((Point)arg);
                break;
            }
        }
        return handled;
    }
    
    public boolean checkReconnect() throws Exception {
        if (this.session == null && this.lastDoc != null) {
            this.println("Reconnecting...");
            this.switchDocument(this.lastDoc);
            return true;
        }
        return false;
    }
    
    protected void updateSessions() {
        for (int c = this.getComponentCount(), x = 0; x < c; ++x) {
            final Component comp = this.getComponent(x);
            if (comp instanceof SessionListener) {
                ((SessionListener)comp).setSession(this.session);
            }
            comp.setVisible(true);
        }
        this.setVisible(true);
    }
    
    protected Image getParamImage(String param) {
        param = this.getParameter(param);
        if (param != null && param.length() > 0) {
            try {
                return this.loadImage(param);
            }
            catch (Exception e) {
                Debug.report(e);
            }
        }
        return null;
    }
    
    protected void startingSession(final Reply reply) throws Exception {
        if (this.videoCanvas != null) {
            final int w = this.getIntParam("videoWidth", 0);
            final int h = this.getIntParam("videoHeight", 0);
            if (w != 0) {
                this.videoCanvas.setVideoSize(w, h, false);
            }
            this.videoCanvas.clearLogo();
        }
        if (reply.getStatus() != 0) {
            reply.copyTo(this.session.camInfo);
            final Image i = this.getParamImage("error_background");
            if (i != null) {
                if (this.videoCanvas != null) {
                    this.videoCanvas.setErrorStr(reply.getErrorString(), i);
                }
                else {
                    this.background = i;
                }
            }
            String err = reply.getErrorString();
            if (err == null) {
                err = "Error: " + reply.getStatus();
            }
            final Exception e = new Exception(err);
            this.setError(e, err);
            throw e;
        }
        this.background = this.getParamImage("applet_background");
        if (this.background == null) {
            this.setBackground(this.backColor);
        }
        this.titleImage = this.getParamImage("title_image");
        this.sleep = 0;
        this.sleep = this.getIntParam("interframe_delay", this.sleep);
        this.sleep += this.getIntParam("minutes", 0) * 60000;
        this.sleep += this.getIntParam("seconds", 0) * 1000;
        final int iipm = this.getIntParam("ipm", 0);
        if (iipm > 0) {
            final double ipm = 60.0 / iipm;
            this.sleep += (int)(ipm * 1000.0);
        }
        if (this.sleep <= 340) {
            this.priority = 10;
        }
        this.priority = this.getIntParam("priority", this.priority);
    }
    
    protected void newSession(final String doc) throws Exception {
        this.stopStreaming();
        this.println("newSession session.. doc=" + doc);
        this.session = new ControlSession(this, doc, this.sessionKind);
        this.lastDoc = doc;
        try {
            synchronized (this.session) {
                final Reply r = this.session.openSession(this.sessionKind);
                this.println("got session reply: " + this.session + " id:" + this.session.idStr + " state=" + this.session.cameraState());
                this.startingSession(r);
                final String state = this.session.stateID();
                this.session.addListener(this);
                if (this.session == null) {
                    if (!this.hasError()) {
                        this.setErrorString("Unable to open session: " + state);
                    }
                    // monitorexit(this.session)
                    return;
                }
                this.println("called startingSession " + this.session);
                if (this.session.idStr != null) {
                    this.println("Opened " + this.session.cameraState());
                    this.session.start();
                }
                else {
                    if (!this.hasError()) {
                        this.setErrorString(this.session.stateFailureMessage());
                    }
                    if (r.getErrorString() != null) {
                        this.session = null;
                        throw new Exception(r.getErrorString());
                    }
                }
            }
            // monitorexit(this.session)
        }
        catch (Exception e) {
            this.report(e, "Error connecting to server");
            if (!this.hasError()) {
                if (e instanceof IOException) {
                    this.setError(e, "I/O Error - Unable to connect to server.");
                }
                else {
                    this.setError(e, "Unable to connect to server.");
                }
            }
            throw e;
        }
        this.println("newSession complete.. " + this.session.idStr);
    }
    
    public void switchDocument(final String doc) throws Exception {
        if (this.hasError()) {
            this.println("Switch document, clearing error " + this.getErrorString());
            this.clearErrorString();
        }
        this.println("Switch document to " + doc);
        if (this.session != null) {
            this.session.quit();
        }
        if (this.videoCanvas != null) {
            this.videoCanvas.clearError();
            this.videoCanvas.clearLogo();
        }
        this.newSession(doc);
        this.updateSessions();
        this.println("starting stream " + this.session.idStr);
        if (this.session.idStr != null) {
            this.startStreaming(true);
        }
    }
    
    protected Image getSessionImage(final String name) {
        return this.getParamImage(name);
    }
    
    protected VideoCanvas createVideoCanvas() {
        final int w = this.session.getCamInfoInt("videoWidth", 0);
        final int h = this.session.getCamInfoInt("videoHeight", 0);
        final int canvasWidth = this.getIntParam("canvaswidth", w);
        final int canvasHeight = this.getIntParam("canvasheight", h);
        final VideoCanvas v = new VideoCanvas(w, h, canvasWidth, canvasHeight, this.loadString, this.titleImage);
        final String pos = this.getParameter("video_logo_pos");
        if (pos != null && !"OFF".equalsIgnoreCase(pos)) {
            v.setLogo(this.getParamImage("video_logo"), pos);
        }
        v.addListener(this);
        return v;
    }
    
    protected void initParameters() throws Exception {
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        final String cr = this.getParameter("copyright");
        if (cr == null) {
            throw new Exception("unable to find copyright: <param name=\"copyright\" value=\"nuspectra.com\">");
        }
        Debug.println(this.getAppletInfo());
        this.loadString = this.getParameter("load_string");
        if (this.loadString == null) {
            this.loadString = "Initializing SiteProxy...";
        }
    }
    
    protected void doStart() {
        if (this.titleImage != null && !this.hasError()) {
            final int titleDelay = this.getIntParam("title_delay", 0);
            if (titleDelay > 0 && titleDelay < 60000) {
                Debug.println("doStart titleDelay..." + titleDelay);
                try {
                    Thread.sleep(titleDelay);
                }
                catch (Exception ex) {}
            }
        }
        Debug.println("waitUntilStarted... end vis=" + super.vis + " session=" + this.session);
        if (this.session != null && this.session.idStr != null && !this.hasError()) {
            this.startStreaming(true);
        }
        else {
            Debug.println("waitUntilStarted could not start..." + this.session);
        }
    }
    
    protected void waitUntilStarted() {
        this.doStart();
    }
    
    public void start() {
        this.show();
        super.start();
        this.repaint();
        if (this.hasError()) {
            Debug.println("Unable to start due to previous errors " + this.getErrorString());
            return;
        }
        this.waitUntilStarted();
    }
    
    public void stop() {
        this.quit();
        super.stop();
    }
    
    public void quit() {
        if (!this.wasQuit()) {
            try {
                this.stopStreaming();
                if (this.passFrame != null) {
                    this.passFrame.dispose();
                }
                this.passFrame = null;
            }
            catch (Exception e) {
                Debug.report(e);
            }
            super.quit();
        }
    }
    
    protected void createAppletLayout() throws Exception {
        this.videoCanvas = this.createVideoCanvas();
        this.setLayout(new BorderLayout());
        this.add(this.videoCanvas);
    }
    
    public void init() {
        if (this.e != null) {
            Debug.println("Init called twice. 1st here");
            this.e.printStackTrace();
            Debug.println("Init called twice. 2nd here");
            new Exception("init(2)").printStackTrace();
            System.exit(0);
        }
        this.e = new Exception("init(1)");
        super.init();
        try {
            this.initParameters();
            if (this.hasError()) {
                System.err.println(this.getErrorString());
                return;
            }
            int attempts = 0;
            while (true) {
                try {
                    this.clearErrorString();
                    this.newSession(this.getDoc());
                }
                catch (Exception e) {
                    if (e.toString().indexOf("UNAUTHORIZED") == -1) {
                        if (attempts >= 4) {
                            this.println("All " + (attempts + 1) + " attempts to connect to the server have failed...");
                            throw e;
                        }
                        this.println("Attempting to reconnect " + (attempts + 1) + " after open failed. " + e.toString());
                    }
                    else {
                        if (attempts > 10) {
                            throw new Exception("User/Password required. Please reload.");
                        }
                        while (this.passFrame != null) {
                            Thread.sleep(250L);
                        }
                        if (super.fAuth == null) {
                            throw new Exception("User/Password required.");
                        }
                    }
                    ++attempts;
                    continue;
                }
                break;
            }
            this.createAppletLayout();
        }
        catch (Throwable e2) {
            Debug.report(e2, "Unable to initialize");
            this.stopStreaming();
            if (this.videoCanvas != null) {
                this.videoCanvas.setErrorStr("Init failed " + e2.toString(), null);
            }
        }
    }
    
    public void destroy() {
        this.quit();
        super.destroy();
    }
    
    public void setVisible(final boolean b) {
        if (!b) {
            this.stopStreaming();
        }
        super.setVisible(b);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    protected byte[] getLastImageBytes() {
        if (this.mjpeg == null) {
            Debug.println("No video image available.");
            return null;
        }
        synchronized (this.mjpeg) {
            final byte[] lastImageBytes;
            final byte[] b = lastImageBytes = this.mjpeg.getLastImageBytes();
            // monitorexit(this.mjpeg)
            return lastImageBytes;
        }
    }
    
    protected void snapPicture(final boolean invert) throws Exception {
        if (this.mjpeg == null) {
            throw new Exception("Can't snap picture--no video.");
        }
        final URL url = this.session.cmdURL("snap", null);
        this.getAppletContext().showDocument(url, "Live Image " + new Date().toString());
    }
}
