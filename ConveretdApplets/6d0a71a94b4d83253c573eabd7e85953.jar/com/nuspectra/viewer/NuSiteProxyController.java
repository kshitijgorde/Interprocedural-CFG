// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionListener;

public class NuSiteProxyController extends NuSiteProxyViewer implements ActionListener
{
    QueueStatusCanvas statusCanvas;
    Font smBoldFont;
    Font smFont;
    Font lgFont;
    String infoString;
    String versString;
    VBPTZBar pan;
    VBPTZBar tilt;
    VBPTZBar zoom;
    int statusHeight;
    PresetChoice presetChoice;
    boolean useKeyboard;
    Rectangle zoomInfoRect;
    Polygon zoomPoly;
    String maxZoomX;
    final int ZWIDTH = 60;
    final int SCROLLWIDTH = 16;
    Color boarderColor;
    protected boolean zoomOnRight;
    boolean useTopStatus;
    
    public NuSiteProxyController() {
        this.statusCanvas = null;
        this.smBoldFont = new Font("SansSerif", 1, 12);
        this.smFont = new Font("SansSerif", 0, 12);
        this.lgFont = new Font("SansSerif", 1, 18);
        this.infoString = null;
        this.versString = this.appletName();
        this.statusHeight = 28;
        this.useKeyboard = true;
        this.zoomPoly = null;
        this.maxZoomX = "16";
        this.boarderColor = Color.gray;
        this.zoomOnRight = false;
        this.useTopStatus = true;
        System.out.println("NuSiteProxy Controller: Build Jan 30, 2007- 1656");
        if (this.isMicrosoft() && !this.getBoolParam("ALLOW_MS", false)) {
            this.setErrorString("This not compatible with your java. Visit java.com for a newer Java Runtime.");
        }
        super.sessionKind = "ctl";
    }
    
    public String appletName() {
        return "SiteProxy " + Version.getShortVersion();
    }
    
    protected Canvas addPano() {
        return null;
    }
    
    protected void createAppletLayout() throws Exception {
        if (this.hasError()) {
            return;
        }
        this.useTopStatus = this.getBoolParam("USE_TOP_STATUS", true);
        if (super.session != null && super.session.idStr != null) {
            synchronized (super.session) {
                this.setLayout(null);
                this.infoString = super.session.getCamInfoString("info");
                if (this.versString != null && super.debug) {
                    this.infoString = String.valueOf(this.versString) + " " + this.infoString;
                }
                final boolean usePano = super.session.getCamInfoInt("panorama", 0) != 0;
                this.zoomOnRight = usePano;
                final Color c = this.parseColor(super.session.getCamInfoString("boarderColor"));
                if (c != null) {
                    this.boarderColor = c;
                }
                (super.videoCanvas = this.createVideoCanvas()).setVisible(false);
                super.videoCanvas.invert = super.session.invertVideo;
                int margin = 16;
                if (this.zoomOnRight) {
                    margin = 8;
                }
                int x = margin;
                int y = margin + 20;
                if (!this.useTopStatus) {
                    y = 1;
                }
                if (!this.zoomOnRight) {
                    x += 76;
                }
                this.setLocation(super.videoCanvas, x, y);
                super.videoCanvas.addListener(this);
                this.add(super.videoCanvas);
                super.videoCanvas.setVisible(true);
                final int videoWidth = this.cwidth(super.videoCanvas);
                final int videoHeight = this.cheight(super.videoCanvas);
                this.pan = new VBPTZBar(1);
                this.tilt = new VBPTZBar(2);
                this.zoom = new VBPTZBar(3);
                final Rectangle vid = this.getBounds(super.videoCanvas);
                int panWidth = videoWidth + 16;
                if (!this.zoomOnRight) {
                    panWidth += 16;
                }
                int px = vid.x;
                if (!this.zoomOnRight) {
                    px -= 16;
                }
                this.pan.setBounds(px, vid.y + videoHeight, panWidth, 16);
                this.tilt.setBounds(vid.x + videoWidth, vid.y, 16, videoHeight);
                this.zoom.setBounds(vid.x - 16, vid.y, 16, videoHeight);
                this.zoomInfoRect = new Rectangle(vid.x - this.cwidth(this.zoom) - 60, vid.y, 60, videoHeight);
                if (this.zoomOnRight) {
                    this.zoomInfoRect.x = vid.x + videoWidth + 16 + 1;
                    this.zoom.setBounds(this.zoomInfoRect.x + this.zoomInfoRect.width, vid.y, 16, videoHeight);
                }
                this.add(this.pan);
                this.add(this.tilt);
                this.add(this.zoom);
                this.pan.setVisible(true);
                this.tilt.setVisible(true);
                this.zoom.setVisible(true);
                x = this.zoomInfoRect.x - 1;
                y = vid.y + videoHeight + 16 + 4;
                if (videoWidth > 320 || this.zoomOnRight) {
                    x = vid.x;
                }
                (this.statusCanvas = new QueueStatusCanvas(this)).setBounds(x, y, 220, this.statusHeight);
                this.statusCanvas.setBackground(super.background);
                this.add(this.statusCanvas);
                this.statusCanvas.setVisible(true);
                this.statusCanvas.repaint();
                Rectangle r = this.getBounds(this.statusCanvas);
                this.setLocation(this.presetChoice = new PresetChoice(this), r.x + r.width + 10, r.y + 3);
                r = this.getBounds(this.presetChoice);
                r.width = 120;
                if (r.height < 10) {
                    r.height = 16;
                }
                this.presetChoice.setBounds(r);
                this.add(this.presetChoice);
                if (this.getBoolParam("SHOW_SNAP", true)) {
                    x = r.x + r.width + 4;
                    y = r.y + 1;
                    final Button b = new Button("Snap");
                    b.setName("Snap");
                    b.setBounds(x, y, 60, 20);
                    b.addActionListener(this);
                    this.add(b);
                }
                this.getBoolParam("CAM_NAME_ON_BOTTOM", false);
                this.maxZoomX = super.session.getCamInfoString("zoomXRating", "?");
                this.maxZoomX = String.valueOf(this.maxZoomX) + "x";
                Canvas pc = null;
                if (usePano) {
                    pc = new PanoramaCanvas(this);
                }
                if (pc != null) {
                    x = vid.x;
                    y += 28;
                    final Rectangle pcb = this.getBounds(pc);
                    pc.setBounds(x, y, pcb.width, pcb.height);
                    this.add(pc);
                }
                this.updateSessions();
                this.pack();
            }
            // monitorexit(super.session)
        }
    }
    
    String getScrollInfo() {
        try {
            if (this.pan == null) {
                return "";
            }
            final String s = String.valueOf(this.pan.getValue()) + "," + this.tilt.getValue() + "," + this.zoom.getValue();
            return s;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public boolean keyDown(final Event evt, final int key) {
        if (this.useKeyboard && super.session != null) {
            String s = null;
            switch (key) {
                case 49: {
                    s = "1";
                    break;
                }
                case 50: {
                    s = "2";
                    break;
                }
                case 51: {
                    s = "3";
                    break;
                }
                case 52: {
                    s = "4";
                    break;
                }
                case 53: {
                    s = "5";
                    break;
                }
                case 54: {
                    s = "6";
                    break;
                }
                case 55: {
                    s = "7";
                    break;
                }
                case 56: {
                    s = "8";
                    break;
                }
                case 57: {
                    s = "9";
                    break;
                }
                case 45: {
                    s = "o";
                    break;
                }
                case 91: {
                    s = "o";
                    break;
                }
                case 123: {
                    s = "o";
                    break;
                }
                case 43: {
                    s = "i";
                    break;
                }
                case 61: {
                    s = "i";
                    break;
                }
                case 93: {
                    s = "i";
                    break;
                }
                case 125: {
                    s = "i";
                }
                case 1003: {
                    s = "z0";
                    break;
                }
                case 1002: {
                    s = "z8";
                    break;
                }
                case 1007: {
                    s = "r";
                    break;
                }
                case 1006: {
                    s = "l";
                    break;
                }
                case 1004: {
                    s = "u";
                    break;
                }
                case 1005: {
                    s = "d";
                    break;
                }
            }
            if (s != null) {
                Debug.println("keyDown :: {" + key + ") -> " + s);
                super.session.standardMove(s);
                return true;
            }
            if (key >= 1008 && key <= 1019) {
                final int fkey = 1008 - key + 1;
                if (fkey <= super.session.getPresetCount()) {
                    super.session.goToPreset(fkey);
                    return true;
                }
            }
            else {
                this.println("keyDown :: {" + key + ")");
            }
        }
        return super.keyDown(evt, key);
    }
    
    protected void HandleMapClick(int x, int y) {
        final double halfX = super.videoCanvas.getBounds().width / 2.0;
        final double halfY = super.videoCanvas.getBounds().height / 2.0;
        final double xDelta = x - halfX;
        final double yDelta = y - halfY;
        final double xf = xDelta / halfX;
        final double yf = yDelta / halfY;
        if (super.debug) {
            System.out.println("Click:: {" + x + ", " + y + "} . " + xf + "/" + yf);
        }
        x = (int)(xf * 100.0);
        y = (int)(yf * 100.0);
        super.session.mapClick(x, y);
    }
    
    public void initParameters() throws Exception {
        super.initParameters();
        try {
            this.useKeyboard = this.getBoolParam("KEY_EQUIVALENTS", true);
        }
        catch (Exception e) {
            this.report(e, "Init");
        }
    }
    
    protected boolean handleMessage(final int id, final Object arg) {
        boolean handled = false;
        Label_0203: {
            try {
                switch (id) {
                    case 1017: {
                        super.session.standardMove((String)arg);
                        handled = true;
                        break;
                    }
                    case 1019: {
                        super.session.postGoPreset((Preset)arg);
                        handled = true;
                        break;
                    }
                    case 300: {
                        if (super.session != null) {
                            this.snapPicture(super.session.invertVideo);
                            handled = true;
                            break;
                        }
                        break;
                    }
                    case 1099: {
                        final int i = (int)arg;
                        switch (i) {
                            case 1: {
                                this.sendMessage(300, null);
                                break Label_0203;
                            }
                            case 2: {
                                this.switchDocument("2");
                                break Label_0203;
                            }
                            case 3: {
                                this.switchDocument("1");
                                break Label_0203;
                            }
                            case 4: {
                                break Label_0203;
                            }
                            default: {
                                break Label_0203;
                            }
                        }
                        break;
                    }
                    case 1100: {
                        this.appletEvent(arg);
                        break;
                    }
                }
            }
            catch (Throwable e) {
                this.report(e);
                handled = true;
            }
        }
        if (!handled) {
            handled = super.handleMessage(id, arg);
        }
        return handled;
    }
    
    protected void handlePTZClick(final Point p) {
        if (super.session != null) {
            super.session.mapClick(p.x, p.y);
        }
    }
    
    private void frame(final Graphics g, final Component c, final Color color) {
        final Rectangle r = c.getBounds();
        g.setColor(color);
        g.drawRect(r.x - 1, r.y - 1, r.width + 2, r.height + 2);
    }
    
    private void frameVideo(final Graphics g, final Color color) {
        Rectangle r = new Rectangle(this.zoomInfoRect);
        g.setColor(color);
        if (this.zoomOnRight) {
            final Rectangle rectangle = r;
            rectangle.width += 16;
            g.drawRect(r.x - 1, r.y - 1, r.width + 1, r.height + 1);
            final Rectangle bounds;
            r = (bounds = super.videoCanvas.getBounds());
            bounds.width += 15;
            final Rectangle rectangle2 = r;
            rectangle2.height += 15;
        }
        else {
            final int sw = this.tilt.getBounds().width;
            final int sh = this.pan.getBounds().height;
            final Rectangle rectangle3 = r;
            rectangle3.width += super.videoCanvas.getBounds().width + sw + sw;
            final Rectangle rectangle4 = r;
            rectangle4.height += sh;
        }
        g.drawRect(r.x - 1, r.y - 1, r.width + 1, r.height + 1);
    }
    
    protected void paintZoomInfo(final Graphics g, final Rectangle r) {
        final int w = r.width;
        final int h = r.height;
        int x = r.x + 10;
        final int charHeight = 14;
        int y = r.y + charHeight + 2;
        g.setFont(this.smBoldFont);
        g.setColor(Color.black);
        g.drawString(this.maxZoomX, x, y);
        y += charHeight;
        g.drawString("Tele", x, y);
        y = r.y + h - 8;
        g.drawString("Wide", x, y);
        y -= charHeight;
        g.drawString("1x", x, y);
        y = r.y + h / 2 - 20;
        x = r.x + 10;
        y = r.y + (h / 2 - charHeight / 2);
        g.drawString("Zoom", x, y);
        final int t = 20;
        final int ph = r.height - 1;
        x = r.x + w - t;
        y = r.y;
        if (this.zoomPoly == null) {
            (this.zoomPoly = new Polygon()).addPoint(x + t, y + ph);
            this.zoomPoly.addPoint(x, y + ph);
            this.zoomPoly.addPoint(x + t - 4, y);
            this.zoomPoly.addPoint(x + t, y);
            this.zoomPoly.addPoint(x + t, y + ph);
        }
        g.setColor(Color.gray);
        g.fillPolygon(this.zoomPoly);
        g.setColor(Color.gray);
        g.drawPolygon(this.zoomPoly);
    }
    
    protected void erase(final Graphics g, final Rectangle r) {
        if (r == null) {
            return;
        }
        if (super.background != null) {
            g.drawImage(super.background, r.x, r.y, r.x + r.width, r.y + r.height, r.x, r.y, r.x + r.width, r.y + r.height, null);
        }
        else {
            g.setColor(super.backColor);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }
    
    protected void paintCompass(final Graphics g) {
        if (this.zoomInfoRect == null || g == null || this.wasQuit()) {
            return;
        }
        final Rectangle r = new Rectangle(this.zoomInfoRect.x, this.zoomInfoRect.y + this.zoomInfoRect.height, this.zoomInfoRect.width - 1, 15);
        if (this.zoomOnRight) {
            final Rectangle rectangle = r;
            --rectangle.x;
            final Rectangle rectangle2 = r;
            rectangle2.width += 18;
        }
        this.erase(g, r);
        g.setColor(Color.gray);
        if (this.zoomOnRight) {
            g.drawRect(r.x, r.y, r.width, r.height);
        }
        else {
            g.drawLine(r.x, r.y - 1, r.x + r.width, r.y - 1);
        }
        if (super.session != null) {
            final String comp = super.session.compassString();
            if (comp.length() == 0) {
                return;
            }
            final int x = r.x + r.width - 52;
            final int y = r.y + 12;
            g.setFont(this.smFont);
            g.setColor(Color.black);
            g.drawString(comp, x, y);
        }
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        if (g == null) {
            return;
        }
        if (super.session != null) {
            g.setFont(this.lgFont);
            g.setColor(Color.gray);
            int x;
            int y;
            if (this.useTopStatus) {
                x = 16;
                y = 23;
            }
            else {
                final int w = g.getFontMetrics().stringWidth(super.session.cameraName());
                x = super.videoCanvas.getX() + super.videoCanvas.getWidth() - (w + 10);
                y = this.statusCanvas.getY() + 20;
            }
            g.drawString(super.session.cameraName(), x, y);
            g.setColor(Color.black);
            g.drawString(super.session.cameraName(), x - 1, y - 1);
        }
        if (this.useTopStatus) {
            int x = 200;
            int y = 15;
            g.setFont(this.smFont);
            g.setColor(Color.black);
            if (this.infoString != null) {
                x = this.appletWidth() - 10;
                x -= g.getFontMetrics().stringWidth(this.infoString);
                g.drawString(this.infoString, x, y);
                y += 15;
            }
        }
        final String err = this.getErrorString();
        if (err != null && super.videoCanvas == null) {
            final int y = 15;
            final int x = 10;
            g.setColor(Color.black);
            g.drawString(this.getErrorString(), x, y);
        }
        if (this.zoomInfoRect != null) {
            this.paintZoomInfo(g, this.zoomInfoRect);
            this.frameVideo(g, Color.gray);
            final Insets s = this.getInsets();
            final Rectangle r = this.appletBounds();
            g.setColor(this.boarderColor);
            g.drawRect(s.left, s.top, r.width - 1, r.height - 1);
            this.paintCompass(g);
        }
    }
    
    public void appletEvent(final Object o) {
        if (o instanceof ImageButtonCanvas) {
            final ImageButtonCanvas b = (ImageButtonCanvas)o;
            this.println("Command: " + b.getCommand());
        }
        else if (o instanceof Button) {
            final Button b2 = (Button)o;
            if ("Snap".equals(b2.getName())) {
                this.sendMessage(300, null);
                return;
            }
            this.println("b=" + b2.getName() + " ... " + b2.toString());
        }
    }
    
    public void actionPerformed(final ActionEvent ae) {
        final Object o = ae.getSource();
        if (o instanceof ImageButtonCanvas) {
            final ImageButtonCanvas b = (ImageButtonCanvas)o;
            if ("mousePressed".equals(ae.getActionCommand())) {
                this.println("Command: " + b.getCommand());
            }
            else {
                "mouseReleased".equals(ae.getActionCommand());
            }
        }
        else if (o instanceof Button) {
            final Button b2 = (Button)o;
            if ("Snap".equals(b2.getName())) {
                this.sendMessage(300, null);
                return;
            }
            this.println("b=" + b2.getName() + " ... " + b2.toString());
        }
    }
    
    protected void startingSession(final Reply reply) throws Exception {
        super.startingSession(reply);
        if (this.statusCanvas != null) {
            this.statusCanvas.setBackground(super.background);
            this.statusCanvas.repaint();
        }
    }
    
    public void setSession(final ControlSession s) {
        super.setSession(s);
        if (s == null) {
            this.infoString = "";
        }
        this.repaint();
    }
    
    public void cameraChanged(final int p, final int t, final int z, final int backlight) {
        super.cameraChanged(p, t, z, backlight);
        this.paintCompass(this.getGraphics());
    }
    
    public void stateChanged(final int state, final int queuePos, final int queueLen) {
        super.stateChanged(state, queuePos, queueLen);
        if (this.zoomInfoRect != null && state != 7) {
            this.paintCompass(this.getGraphics());
        }
    }
    
    public void setInfoStr(final String s) {
        this.infoString = s;
        this.repaint();
    }
}
