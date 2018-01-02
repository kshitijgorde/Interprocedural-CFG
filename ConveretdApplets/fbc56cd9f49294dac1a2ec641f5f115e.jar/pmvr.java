import java.awt.Font;
import java.awt.Container;
import java.awt.Frame;
import netscape.javascript.JSObject;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.DataInputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pmvr extends Applet implements Runnable
{
    private Image m_image;
    private Image m_logo;
    private String m_tip;
    private boolean m_b360;
    private int m_auto;
    private int[] m_pixdeg;
    private Color m_cBack;
    private static Color cY;
    private String m_c;
    private String m_v;
    private boolean m_bStarted;
    private Thread m_thread;
    private int m_left;
    private int m_top;
    private Point m_move;
    private Point m_down;
    private Point m_drag;
    private boolean m_bInDrag;
    private int m_nSmooth;
    private long m_lt;
    private long m_any;
    private String m_cp;
    private String m_vcp;
    private FloorPlan m_fp;
    private String m_fpp;
    private char m_key;
    private String sDebug;
    private Object[] m_links;
    private int m_hot;
    private int iw;
    private int ih;
    private int sw;
    private int sh;
    private boolean m_bPainted;
    private long m_lPaintOn;
    
    public void stop() {
        this.m_bStarted = false;
    }
    
    private int hotHitTest(final int n, final int n2) {
        if (n2 < this.sh - 15 && !this.m_bInDrag) {
            return this.linkHitTest((n - this.m_left) % ((this.iw > 0) ? this.iw : 10000), n2 - this.m_top);
        }
        return -1;
    }
    
    private int linkHitTest(final int n, final int n2) {
        try {
            for (int i = 0; i < this.m_links.length; ++i) {
                final Object[] array = (Object[])this.m_links[i];
                if (array != null) {
                    final int[] array2 = (int[])array[0];
                    final int length = array2.length;
                    if (length == 2) {
                        if (n >= array2[0] && n < array2[1]) {
                            return i;
                        }
                    }
                    else if (length == 3) {
                        final int n3 = n - array2[0];
                        final int n4 = n2 - array2[1];
                        if (n3 * n3 + n4 * n4 <= array2[2] * array2[2]) {
                            return i;
                        }
                    }
                    else if (length == 4) {
                        if (n >= array2[0] && n <= array2[2] && n2 >= array2[1] && n2 <= array2[3]) {
                            return i;
                        }
                    }
                    else if (length > 4) {
                        final int[] array3 = new int[length / 2];
                        final int[] array4 = new int[length / 2];
                        for (int j = 0; j < array3.length; ++j) {
                            array3[j] = array2[j * 2];
                            array4[j] = array2[j * 2 + 1];
                        }
                        if (new Polygon(array3, array4, array3.length).inside(n, n2)) {
                            return i;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return -1;
    }
    
    private void doPaint(final Graphics graphics) {
        final Image image = this.m_image;
        this.sw = this.size().width;
        this.sh = this.size().height;
        this.iw = ((image != null) ? image.getWidth(this) : -1);
        this.ih = ((image != null) ? image.getHeight(this) : -1);
        final boolean b = image != null && this.prepareImage(image, this);
        final int height = graphics.getFontMetrics().getHeight();
        if (this.m_cp != null && this.iw > 0) {
            this.setLeft((this.sw - this.iw) / 2, false);
            try {
                this.setLeft(this.sw / 2 - Integer.parseInt(this.m_cp), false);
            }
            catch (Exception ex) {}
            this.m_cp = null;
        }
        if (this.m_vcp != null && this.iw > 0) {
            this.setTop(0, false);
            try {
                this.setTop(this.sh / 2 - Integer.parseInt(this.m_vcp), false);
            }
            catch (Exception ex2) {}
            this.m_vcp = null;
        }
        if (!b && this.m_logo == null) {
            this.drawString(graphics, (image == null) ? "No image" : ((this.iw == -1) ? "Loading..." : this.m_tip), (this.sh + height) / 2, null);
        }
        final int left = this.m_left;
        final int top = this.m_top;
        if (this.iw > 0) {
            graphics.drawImage(image, left, top, this);
            if (this.m_b360 && left + this.iw < this.sw) {
                graphics.drawImage(image, left + this.iw, top, this);
            }
        }
        int n = 0;
        if (this.m_logo != null) {
            if (this.m_lt == 0L && this.prepareImage(this.m_logo, this)) {
                this.m_lt = System.currentTimeMillis();
            }
            if (this.m_lt == 0L || System.currentTimeMillis() - this.m_lt < 5000L) {
                final int width = this.m_logo.getWidth(this);
                final int height2 = this.m_logo.getHeight(this);
                if ((n = ((width > 0 && height2 > 0) ? 1 : 0)) != 0) {
                    graphics.drawImage(this.m_logo, (this.sw - width) / 2, (this.sh - height2) / 2, this);
                }
            }
        }
        if (n == 0 && b) {
            final String host = this.getDocumentBase().getHost();
            if (host.indexOf("duckware") == -1 && host.indexOf("idyll") == -1) {
                this.drawString(graphics, "Unregistered Version", (this.sh + height) / 2, pmvr.cY);
            }
        }
        if (!b) {
            this.drawString(graphics, "Patent Pending", this.sh - 2 - height, null);
            this.drawString(graphics, this.m_c, this.sh - 2, null);
        }
        final int n2 = Math.abs(left) % this.iw;
        final int n3 = n2 + this.sw;
        final int n4 = (n3 != this.iw) ? (n3 % this.iw) : n3;
        this.sDebug = "Left:" + n2 + "  Right:" + n4;
        if (this.m_pixdeg != null) {
            if (this.m_fp == null) {
                try {
                    final Applet applet = this.getAppletContext().getApplet((this.m_fpp != null) ? this.m_fpp : "FloorPlan");
                    try {
                        this.m_fp = ((applet != null) ? ((FloorPlan)applet) : null);
                    }
                    catch (Exception ex3) {
                        this.m_pixdeg = null;
                    }
                }
                catch (Exception ex4) {}
            }
            final FloorPlan fp = this.m_fp;
            if (fp != null && this.iw > 0) {
                final int pix2deg = this.pix2deg(n2);
                final int pix2deg2 = this.pix2deg(n4);
                this.sDebug = "Left:" + n2 + "=" + pix2deg + "°  Right:" + n4 + "=" + pix2deg2 + "°";
                fp.setView(this, pix2deg, pix2deg2);
            }
        }
        this.drawStatusLine(graphics, (this.m_hot >= 0) ? this.getLinkDesc(this.m_hot) : null);
        if (this.iw > this.sw) {
            final int[] array = { 10, 2, 10, 10 };
            final int[] array2 = { this.sw - 10, this.sw - 2, this.sw - 10, this.sw - 10 };
            final int[] array3 = { this.sh - 11, this.sh - 6, this.sh - 1, this.sh - 11 };
            final boolean b2 = this.m_b360 || left < 0;
            graphics.setColor((this.m_b360 || left > this.sw - this.iw) ? Color.yellow : Color.lightGray);
            graphics.fillPolygon(array2, array3, 4);
            graphics.setColor(b2 ? Color.yellow : Color.lightGray);
            graphics.fillPolygon(array, array3, 4);
            graphics.setColor(Color.black);
            graphics.drawPolygon(array2, array3, 4);
            graphics.drawPolygon(array, array3, 4);
        }
    }
    
    private void drawStatusLine(final Graphics graphics, String s) {
        if (this.m_key == 'd') {
            s = this.sDebug;
        }
        else if (this.m_key == 'v' || this.m_key == 'c') {
            s = this.getAppletInfo();
        }
        else if (this.m_key == 'm') {
            final Runtime runtime = Runtime.getRuntime();
            s = "used=" + (runtime.totalMemory() - runtime.freeMemory()) / 1024L + "k";
        }
        else if (this.m_key == 'x') {
            s = ((this.iw > 0) ? ((this.m_move.x - this.m_left) % this.iw + "," + (this.m_move.y - this.m_top)) : null);
        }
        if (s != null) {
            this.drawString(graphics, s, this.sh - 2, pmvr.cY);
        }
    }
    
    private void setHotLink(final int hot) {
        if (hot != this.m_hot) {
            this.showStatus((hot >= 0) ? this.getLink(hot)[0] : this.m_tip);
            this.m_hot = hot;
            this.repaint();
        }
    }
    
    private void addLink(final String s) throws MalformedURLException {
        final Object[] link = parseLink(s);
        if (link != null) {
            final int length = this.m_links.length;
            final Object[] links = new Object[length + 1];
            System.arraycopy(this.m_links, 0, links, 0, length);
            (this.m_links = links)[length] = link;
        }
    }
    
    private void drawString(final Graphics graphics, final String s, final int n, final Color color) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        final int n2 = (this.sw - stringWidth) / 2;
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(n2 - 2, n - height, stringWidth + 2 + 2, height);
            graphics.setColor(Color.black);
            graphics.drawRect(n2 - 2, n - height, stringWidth + 2 + 2, height);
        }
        graphics.setColor(Color.black);
        graphics.drawString(s, n2, n - fontMetrics.getDescent());
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        this.m_bStarted = true;
    }
    
    public String getAppletInfo() {
        return this.m_v + " - " + this.m_c;
    }
    
    private boolean setCursor(final int n, final int n2) {
        final int hotHitTest = this.hotHitTest(n, n2);
        this.setHotLink(hotHitTest);
        final int n3 = this.m_bInDrag ? this.inCorners(this.m_down.x, this.m_down.y) : this.inCorners(n, n2);
        this.setCursor((this.m_auto != 0 && !this.m_bInDrag && this.iw > this.sw && System.currentTimeMillis() - this.m_any > 30000L) ? 0 : ((this.m_key == 'x') ? 1 : ((n3 != 0) ? 12 : ((hotHitTest != -1) ? 12 : ((this.ih > this.sh) ? 13 : ((this.iw > this.sw) ? 11 : 0))))));
        return true;
    }
    
    private void setCursor(final int cursor) {
        try {
            this.getFrame().setCursor(cursor);
        }
        catch (Exception ex) {}
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.m_any = System.currentTimeMillis();
        final int n2 = event.shiftDown() ? 10 : 1;
        if (n == 1007) {
            this.setLeft(this.m_left - n2, true);
        }
        else if (n == 1006) {
            this.setLeft(this.m_left + n2, true);
        }
        else if (n == 1004) {
            this.setTop(this.m_top + n2, true);
        }
        else if (n == 1005) {
            this.setTop(this.m_top - n2, true);
        }
        else if (n == 1000) {
            this.setLeft(0, true);
        }
        else {
            this.m_key = ((n == this.m_key) ? '\0' : ((char)n));
            this.repaint();
        }
        return true;
    }
    
    public synchronized void set(final String s, final String vcp) {
        try {
            if (s.equals("image")) {
                this.m_image = this.getImage(this.getDocumentBase(), vcp);
                this.m_cp = "0";
                this.m_vcp = "0";
            }
            else if (s.equals("logo")) {
                (this.m_logo = this.getImage(this.getDocumentBase(), vcp)).getWidth(this);
            }
            else if (s.equals("auto")) {
                this.m_auto = Integer.parseInt(vcp);
            }
            else if (s.equals("background")) {
                this.m_cBack = new Color(Integer.parseInt(vcp, 16));
            }
            else if (s.equals("tip")) {
                if (vcp != null) {
                    this.m_tip = vcp;
                }
            }
            else if (s.equals("view")) {
                this.m_b360 = "360".equals(vcp);
            }
            else if (s.equals("center")) {
                this.m_cp = vcp;
            }
            else if (s.equals("vcenter")) {
                this.m_vcp = vcp;
            }
            else if (s.equals("floorplan")) {
                this.m_fpp = this.getParameter("floorplan");
                this.m_fp = null;
            }
            else if (s.equals("pixdeg")) {
                if (vcp != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(vcp, ",=");
                    this.m_pixdeg = new int[stringTokenizer.countTokens() / 2 * 2];
                    for (int i = 0; i < this.m_pixdeg.length; ++i) {
                        try {
                            this.m_pixdeg[i] = Integer.parseInt(stringTokenizer.nextToken());
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            else if (s.equals("deg")) {
                this.m_any = System.currentTimeMillis();
                final int deg2pix = this.deg2pix(Integer.parseInt(vcp));
                if (deg2pix >= 0) {
                    this.setLeft(this.sw / 2 - deg2pix, true);
                }
            }
            else if (s.equals("links")) {
                this.m_links = new Object[0];
                if (vcp != null) {
                    final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), vcp).openStream());
                    String line;
                    for (int n = 0; (line = dataInputStream.readLine()) != null && n < 1000; ++n) {
                        this.addLink(line);
                    }
                    dataInputStream.close();
                }
            }
            else if (s.startsWith("link")) {
                if (s.equals("link0")) {
                    this.m_links = new Object[0];
                }
                this.addLink(vcp);
            }
        }
        catch (Exception ex2) {}
        this.repaint(50L);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setHotLink(-1);
        return true;
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.m_bPainted = true;
        try {
            final Dimension size = this.size();
            final Rectangle intersection = graphics.getClipRect().intersection(new Rectangle(0, 0, size.width, size.height));
            final Image image = this.createImage(intersection.width, intersection.height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.translate(-intersection.x, -intersection.y);
            graphics2.clipRect(intersection.x, intersection.y, intersection.width, intersection.height);
            graphics2.setColor(this.m_cBack);
            graphics2.fillRect(0, 0, size.width, size.height);
            graphics2.setColor(this.getForeground());
            this.doPaint(graphics2);
            graphics.drawImage(image, intersection.x, intersection.y, intersection.width, intersection.height, null);
            graphics2.dispose();
            this.m_nSmooth += intersection.width * intersection.height;
            if (this.m_nSmooth > 2000000) {
                this.m_nSmooth = 0;
                System.gc();
            }
        }
        catch (Exception ex) {
            graphics.setColor(Color.black);
            graphics.drawString("Exception: " + ex, 0, 20);
            ex.printStackTrace();
        }
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        final long lPaintOn = System.currentTimeMillis() + n;
        if (this.m_bPainted || lPaintOn < this.m_lPaintOn) {
            this.m_lPaintOn = lPaintOn;
            this.m_bPainted = false;
            super.repaint(n, n2, n3, n4, n5);
        }
    }
    
    public pmvr() {
        this.m_tip = "Drag within this image to pan left/right";
        this.m_c = "© 1999 duckware.com";
        this.m_v = "PMVR 2.2d";
        this.m_move = new Point(0, 0);
        this.m_down = new Point(0, 0);
        this.m_drag = new Point(0, 0);
        this.m_links = new Object[0];
        this.m_hot = -2;
        this.iw = -1;
        this.ih = -1;
        this.sw = 1;
        this.sh = 1;
        this.m_bPainted = true;
        this.m_lPaintOn = 0L;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.m_any = System.currentTimeMillis();
        final int hotHitTest = this.hotHitTest(n, n2);
        this.m_bInDrag = false;
        if (hotHitTest != -1) {
            try {
                final String[] link = this.getLink(hotHitTest);
                if (link[0].startsWith("javascript:")) {
                    JSObject.getWindow((Applet)this).eval(link[0].substring(11));
                }
                else {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), link[0]), link[1]);
                }
            }
            catch (Exception ex) {}
        }
        return this.setCursor(n, n2);
    }
    
    private int pix2deg(final int n) {
        for (int n2 = this.m_pixdeg.length / 2 - 1, i = 0; i < n2; ++i) {
            final int n3 = i * 2;
            final int n4 = this.m_pixdeg[n3];
            final int n5 = this.m_pixdeg[n3 + 2];
            if (n >= n4 && n <= n5) {
                final int n6 = this.m_pixdeg[n3 + 1];
                final int n7 = this.m_pixdeg[n3 + 3];
                final int n8 = n6 + ((n6 <= n7) ? 360 : 0);
                final int n9 = n5 - n4;
                return (n7 + ((n8 - n7) * (n5 - n) + n9 / 2) / n9) % 360;
            }
        }
        return 0;
    }
    
    public synchronized void destroy() {
        try {
            this.m_thread.stop();
        }
        catch (Exception ex) {}
        try {
            this.m_image.flush();
        }
        catch (Exception ex2) {}
        this.m_thread = null;
        this.m_image = null;
    }
    
    private void setTop(final int n, final boolean b) {
        this.m_top = ((this.ih > 0 && this.ih < this.sh) ? ((this.sh - this.ih) / 2) : Math.max(this.sh - this.ih, Math.min(0, n)));
        if (b) {
            this.repaint();
        }
    }
    
    private String getLinkDesc(final int n) {
        try {
            return (String)((Object[])this.m_links[n])[2];
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Frame getFrame() {
        for (Container parent = this; parent != null; parent = parent.getParent()) {
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
        }
        return null;
    }
    
    private int inCorners(final int n, final int n2) {
        if (n2 <= this.sh - 10) {
            return 0;
        }
        if (n < 10) {
            return -1;
        }
        if (this.sw - n < 10) {
            return 1;
        }
        return 0;
    }
    
    private static Object[] parseLink(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            final int[] array = new int[stringTokenizer.countTokens() - 2];
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            return new Object[] { array, stringTokenizer.nextToken(), stringTokenizer.nextToken() };
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        pmvr.cY = new Color(255, 255, 192);
    }
    
    private int deg2pix(final int n) {
        final int n2 = this.m_pixdeg.length / 2 - 1;
        int i = 0;
        while (i < n2) {
            final int n3 = i * 2;
            final int n4 = this.m_pixdeg[n3 + 1];
            final int n5 = this.m_pixdeg[n3 + 3];
            final int n6 = n4 + ((n4 <= n5) ? 360 : 0);
            final int n7 = n + ((n < n5) ? 360 : 0);
            if (n7 >= n5 && n7 <= n6) {
                final int n8 = this.m_pixdeg[n3];
                final int n9 = this.m_pixdeg[n3 + 2];
                final int n10 = n6 - n5;
                final int n11 = n9 - n8;
                if (n11 > 0) {
                    return n9 - (n11 * (n7 - n5) + n10 / 2) / n10;
                }
                return -1;
            }
            else {
                ++i;
            }
        }
        return -1;
    }
    
    private boolean setLeft(int i, final boolean b) {
        if (this.iw > 0) {
            if (this.iw < this.sw) {
                i = (this.sw - this.iw) / 2;
            }
            else if (this.m_b360) {
                while (i > 0) {
                    i -= this.iw;
                }
                while (i < -this.iw) {
                    i += this.iw;
                }
            }
            else {
                i = Math.max(this.sw - this.iw, Math.min(0, i));
            }
        }
        final boolean b2 = this.m_left != i;
        this.m_left = i;
        if (b && b2) {
            this.repaint();
        }
        return b2;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.m_any = System.currentTimeMillis();
        this.m_down = new Point(n, n2);
        this.m_bInDrag |= (this.inCorners(n, n2) != 0);
        return this.mouseDrag(event, n, n2);
    }
    
    public void run() {
        try {
            while (true) {
                long n = this.m_bStarted ? 100 : 500;
                if (this.m_bStarted && this.m_auto != 0 && !this.m_bInDrag && this.iw > this.sw && System.currentTimeMillis() - this.m_any > 30000L) {
                    final boolean setLeft = this.setLeft(this.m_left - ((this.m_auto > 0) ? 1 : -1), false);
                    if (!this.m_b360 && !setLeft) {
                        this.m_auto = -this.m_auto;
                    }
                    this.repaint();
                    n = Math.max(10, Math.abs(this.m_auto));
                }
                else if (this.m_bStarted && this.m_bInDrag && this.iw > 0) {
                    final int inCorners = this.inCorners(this.m_down.x, this.m_down.y);
                    final int inCorners2 = this.inCorners(this.m_drag.x, this.m_drag.y);
                    final int n2 = (inCorners != 0) ? ((inCorners2 == 0) ? 0 : ((inCorners < 0) ? 8 : -8)) : (-(this.m_drag.x - this.m_down.x) / 15);
                    final int n3 = (inCorners != 0) ? 0 : (-(this.m_drag.y - this.m_down.y) / 15);
                    if (n2 != 0) {
                        this.setLeft(this.m_left + n2, false);
                        n = 20L;
                    }
                    if (n3 != 0) {
                        this.setTop(this.m_top + n3, false);
                        n = 20L;
                    }
                    if (n2 != 0 || n3 != 0) {
                        this.repaint();
                    }
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void init() {
        this.setFont(new Font("Helvitica", 0, 14));
        this.m_cBack = new Color(12632256);
        if (this.m_thread == null) {
            try {
                this.getDocumentBase().openConnection().setDefaultUseCaches(true);
            }
            catch (Exception ex) {}
            final String[] array = { "image", "logo", "auto", "background", "tip", "view", "center", "vcenter", "pixdeg", "links", "floorplan" };
            for (int i = 0; i < array.length; ++i) {
                this.set(array[i], this.getParameter(array[i]));
            }
            String parameter;
            for (int n = 0; (parameter = this.getParameter("link" + n)) != null && n < 1000; ++n) {
                this.set("link" + n, parameter);
            }
            (this.m_thread = new Thread(this, this.m_v)).start();
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.m_drag = new Point(n, n2);
        this.m_bInDrag |= (Math.abs(n - this.m_down.x) > 5 || Math.abs(n2 - this.m_down.y) > 5);
        return this.setCursor(n, n2);
    }
    
    private String[] getLink(final int n) {
        return this.getLink((Object[])this.m_links[n]);
    }
    
    private String[] getLink(final Object[] array) {
        final String string = array[1].toString();
        final String s = " in ";
        final int index = string.indexOf(s);
        return new String[] { (index > 0) ? string.substring(0, index) : string, (index > 0) ? string.substring(index + s.length()) : "_self" };
    }
    
    public boolean isViewable(final int n) {
        return this.deg2pix(n) >= 0;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.m_move = new Point(n, n2);
        if (this.m_key == 'x') {
            final int n3 = this.getFontMetrics(this.getFont()).getHeight() + 5;
            this.repaint(0, this.sh - n3, this.sw, n3);
        }
        return this.setCursor(n, n2);
    }
}
