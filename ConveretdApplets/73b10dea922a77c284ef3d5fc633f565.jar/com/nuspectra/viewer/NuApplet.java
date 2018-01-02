// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.applet.Applet;

public abstract class NuApplet extends Applet
{
    private String errString;
    protected String fAuth;
    protected static final int SNAP_PICTURE = 300;
    protected static final int PACK_APPLET = 1011;
    protected static final int GETUSERPASS = 1012;
    protected static final int PASS_OK = 1013;
    protected static final int PASS_CANCEL = 1014;
    protected static final int VIDEO_FAIL_MSG = 1015;
    protected static final int PTZ_CLICK = 1016;
    protected static final int PTZ_MOVE = 1017;
    protected static final int PTZ_START = 1018;
    protected static final int PTZ_PRESET = 1019;
    protected static final int START_VIDEO = 1020;
    protected static final int PTZ_TEST = 1099;
    protected static final int BUTTON_PRESSED = 1100;
    protected static final int DESTROYED = 400;
    String expires;
    protected boolean debug;
    private boolean quit;
    volatile boolean vis;
    String base;
    
    public NuApplet() {
        this.errString = null;
        this.fAuth = null;
        this.debug = false;
        this.quit = false;
        this.vis = false;
        this.base = null;
    }
    
    public boolean wasQuit() {
        return this.quit;
    }
    
    public void quit() {
        this.quit = true;
    }
    
    public void init() {
        Debug.set(this);
        System.out.println("Starting " + this.getAppletInfo());
        this.setAuthorization(this.getParameter("AUTH"));
        final String user = this.getParameter("USER");
        final String pass = this.getParameter("PASS");
        this.expires = this.getParameter("expires");
        final String lc = this.getParameter("copyright");
        if (!"nuspectra.com".equalsIgnoreCase(lc)) {
            System.out.println(String.valueOf(this.getAppletInfo()) + " Missing 'copyright' param; value must equal 'nuspectra.com' (now set to '" + lc + "')");
        }
        if (user != null && pass != null) {
            this.setUserPass(this.getParameter("USER"), this.getParameter("PASS"));
        }
        this.show();
    }
    
    protected String getErrorString() {
        return this.errString;
    }
    
    protected void clearErrorString() {
        this.setErrorString(null);
    }
    
    protected boolean hasError() {
        return this.errString != null;
    }
    
    public String getAppletInfo() {
        return "NuSpectra " + this.appletName() + " Applet. Copyright 1997-2007 all rights reserved.";
    }
    
    protected abstract String appletName();
    
    protected boolean getBoolParam(final String paramName, final boolean defaultValue) {
        String param = this.getParameter(paramName);
        if (param != null) {
            param = param.toUpperCase();
            if (param.equals("ON")) {
                return true;
            }
            if (param.equals("OFF")) {
                return false;
            }
            if (param.equals("YES")) {
                return true;
            }
            if (param.equals("NO")) {
                return false;
            }
            if (param.equals("TRUE")) {
                return true;
            }
            if (param.equals("FALSE")) {
                return false;
            }
            if (param.equals("T")) {
                return true;
            }
            if (param.equals("F")) {
                return false;
            }
            if (param.equals("1")) {
                return true;
            }
            if (param.equals("0")) {
                return false;
            }
        }
        return defaultValue;
    }
    
    boolean isMicrosoft() {
        try {
            final String vendor = System.getProperty("java.vendor");
            if (vendor != null && vendor.toLowerCase().indexOf("microsoft") != -1) {
                return true;
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    protected int getIntParam(final String paramName, final int defaultValue) {
        int result = defaultValue;
        final String param = this.getParameter(paramName);
        if (param != null) {
            result = Integer.parseInt(param);
        }
        return result;
    }
    
    protected String getStrParam(final String paramName, final String defaultValue) {
        final String param = this.getParameter(paramName);
        if (param != null) {
            return param;
        }
        return defaultValue;
    }
    
    protected float getFloatParam(final String paramName, final float defaultValue) {
        float result = defaultValue;
        final String param = this.getParameter(paramName);
        if (param != null) {
            final Float f = Float.valueOf(param);
            result = f;
        }
        return result;
    }
    
    protected void setStatus(final String s) {
        Debug.println(s);
    }
    
    protected void setErrorString(final String s) {
        boolean needPaint = false;
        if (this.errString != s) {
            needPaint = true;
        }
        this.errString = s;
        if (needPaint) {
            this.repaint();
        }
    }
    
    protected void setError(final Throwable e, final String s) {
        this.setErrorString(s);
        this.report(e, s);
    }
    
    public void destroy() {
        this.quit();
        super.destroy();
    }
    
    protected void report(final Throwable e, final String task) {
        final String err = "SiteProxy: " + task + " " + e.toString();
        System.err.println(err);
        Debug.report(e, err);
    }
    
    protected void report(final Throwable e) {
        System.err.println(e.toString());
        Debug.report(e, "");
    }
    
    protected void println(final String s) {
        Debug.println(s);
    }
    
    protected void report(final String s) {
        System.err.println(s);
        Debug.println(s);
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        final boolean handled = false;
        return handled;
    }
    
    protected void sendMessage(final int id, final Object arg) {
        this.handleMessage(id, arg);
    }
    
    protected void sendMessage(final int id) {
        this.sendMessage(id, null);
    }
    
    protected boolean hasAuth() {
        return this.fAuth != null;
    }
    
    protected String getAuth() {
        return this.fAuth;
    }
    
    protected void setAuthorization(final String auth) {
        if (auth != null && auth.length() > 0) {
            if (auth.startsWith("Basic")) {
                this.fAuth = new String(auth);
            }
            else {
                this.fAuth = new String("Basic " + auth);
            }
        }
        else {
            this.fAuth = null;
        }
    }
    
    protected void setUserPass(final String user, final String pass) {
        if (user != null && pass != null) {
            final String userPass = new String(String.valueOf(user) + ":" + pass);
            final String xyz = new String(AppletSocket.encode(userPass.getBytes()));
            this.setAuthorization("Basic " + xyz);
        }
        else {
            this.setAuthorization(null);
        }
    }
    
    protected AppletSocket getConnection(final URL url) throws IOException {
        final AppletSocket socket = new AppletSocket();
        if (this.hasAuth()) {
            socket.setAuthorization(this.getAuth());
        }
        socket.connect(url);
        if (!socket.authorized) {
            this.sendMessage(1012);
            return null;
        }
        return socket;
    }
    
    protected AppletSocket getConnection(final String path) throws IOException {
        final URL url = new URL(String.valueOf(this.getCodeBaseServer()) + path);
        return this.getConnection(url);
    }
    
    protected String getCodeBaseServer() {
        if (this.base == null) {
            final String s = this.getParameter("BASE");
            if (s != null && s.indexOf("http") != -1) {
                return this.base = s;
            }
            if (this.getCodeBase() != null) {
                final String cb = this.getCodeBase().toString();
                final int c = cb.indexOf("/", 8);
                if (c != -1) {
                    this.base = cb.substring(0, c + 1);
                }
            }
            else {
                this.base = "/";
            }
        }
        return this.base;
    }
    
    protected Image loadImage(final String name) throws Exception {
        if (name == null || name.length() == 0) {
            throw new Exception("bad name?");
        }
        String urlStr = this.getCodeBaseServer();
        if (name.charAt(0) == '/') {
            urlStr = String.valueOf(urlStr) + name.substring(1, name.length());
        }
        else {
            urlStr = String.valueOf(urlStr) + name;
        }
        final URL url = new URL(urlStr);
        Debug.println("loadImage " + url.toString());
        final Image i = this.getImage(url);
        if (i == null) {
            throw new Exception("error getting image " + urlStr);
        }
        final MediaTracker t = new MediaTracker(this);
        t.addImage(i, 1);
        t.waitForID(1);
        if (t.isErrorID(1)) {
            throw new Exception("error getting image " + urlStr);
        }
        return i;
    }
    
    protected static void beep() {
        try {
            final Toolkit tk = Toolkit.getDefaultToolkit();
            tk.beep();
        }
        catch (Throwable e) {
            System.out.print("\u00007");
            System.out.flush();
        }
    }
    
    public void paint(final Graphics g) {
        this.vis = true;
        super.paint(g);
    }
    
    public int getHeight() {
        return this.bounds().height;
    }
    
    public int getWidth() {
        return this.bounds().width;
    }
    
    public int appletHeight() {
        return this.bounds().height;
    }
    
    public int appletWidth() {
        return this.bounds().width;
    }
    
    public Color parseColor(final String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        try {
            final int r = Integer.parseInt(s.substring(1, 3), 16);
            final int g = Integer.parseInt(s.substring(3, 5), 16);
            final int b = Integer.parseInt(s.substring(5, 7), 16);
            return new Color(r, g, b);
        }
        catch (Exception _ex) {
            this.println("Unable to parse color: " + s);
            return null;
        }
    }
    
    public int cwidth(final Component c) {
        return c.bounds().width;
    }
    
    public int cheight(final Component c) {
        return c.bounds().height;
    }
    
    public Rectangle getBounds(final Component c) {
        return c.bounds();
    }
    
    public Rectangle appletBounds(final Component c) {
        return c.bounds();
    }
    
    public Rectangle appletBounds() {
        return this.bounds();
    }
    
    public void setLocation(final Component c, final int x, final int y) {
        try {
            c.setLocation(x, y);
        }
        catch (Throwable t) {
            final Rectangle b = c.bounds();
            b.x = x;
            b.y = y;
            c.setBounds(b);
        }
    }
}
