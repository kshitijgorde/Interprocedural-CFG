import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import netscape.javascript.JSObject;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FloorPlan extends Applet
{
    private static Color cY;
    private String m_c;
    private String m_v;
    private int m_nSmooth;
    private Image m_i;
    private int m_x;
    private int m_y;
    private int m_l;
    private int m_r;
    private int m_xor;
    private Point m_off;
    private boolean m_bLive;
    private pmvr m_pmvr;
    private Object[] m_links;
    private int m_hot;
    private int m_tx;
    private int m_ty;
    private boolean m_bInDrag;
    private Point m_down;
    private boolean m_bPainted;
    private long m_lPaintOn;
    
    private int hotHitTest(final int n, final int n2) {
        if (this.m_bInDrag) {
            return -1;
        }
        return this.linkHitTest(n - this.m_tx, n2 - this.m_ty);
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
    
    public void set(final String s, final String s2) {
        try {
            if (s.equals("image")) {
                this.m_i = this.getImage(this.getDocumentBase(), s2);
            }
            else if (s.equals("x")) {
                this.m_x = Integer.parseInt(s2);
            }
            else if (s.equals("y")) {
                this.m_y = Integer.parseInt(s2);
            }
            else if (s.equals("left")) {
                this.m_l = Integer.parseInt(s2);
            }
            else if (s.equals("right")) {
                this.m_r = Integer.parseInt(s2);
            }
            else if (s.equals("xor")) {
                this.m_xor = Integer.parseInt(s2, 16);
            }
            else if (s.equals("background")) {
                this.setBackground(new Color(Integer.parseInt(s2, 16)));
            }
            else if (s.equals("links")) {
                this.m_links = new Object[0];
                if (s2 != null) {
                    final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), s2).openStream());
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
                this.addLink(s2);
            }
        }
        catch (Exception ex) {}
        this.repaint(50L);
    }
    
    private void doPaint(final Graphics graphics) {
        final Image i = this.m_i;
        final int width = this.size().width;
        final int height = this.size().height;
        final int n = (i != null) ? i.getWidth(this) : -1;
        final int n2 = (i != null) ? i.getHeight(this) : -1;
        final int n3 = n - width;
        final int n4 = n2 - height;
        final int n5 = (n3 > 0) ? Math.max(width - n, Math.min(0, width / 2 - this.m_x)) : (-n3 / 2);
        final int n6 = (n4 > 0) ? Math.max(height - n2, Math.min(0, height / 2 - this.m_y)) : (-n4 / 2);
        final int n7 = width / 5;
        final int n8 = height / 5;
        final int tx = (n3 > 0) ? ((-n5 < n3 / 2) ? ((-n5 < n7) ? 0 : n5) : ((n3 + n5 < n7) ? (-n3) : n5)) : n5;
        final int ty = (n4 > 0) ? ((-n6 < n4 / 2) ? ((-n6 < n8) ? 0 : n6) : ((n4 + n6 < n8) ? (-n4) : n6)) : n6;
        graphics.translate(this.m_tx = tx, this.m_ty = ty);
        this.m_off = new Point(tx, ty);
        if (i != null) {
            graphics.drawImage(i, 0, 0, this);
        }
        final int x = this.m_x;
        final int y = this.m_y;
        final int l = this.m_l;
        final int r = this.m_r;
        if (x > 0 && y > 0 && n > 0) {
            if (l != 0 || r != 0) {
                final int n9 = (width + height) * 2;
                if (this.getColorModel().getPixelSize() >= 15) {
                    graphics.setColor(Color.black);
                    final Graphics create = graphics.create();
                    create.setXORMode(new Color(this.m_xor));
                    create.fillArc(x - n9 / 2, y - n9 / 2, n9, n9, l, (r - l + 360) % 360);
                    create.dispose();
                }
                graphics.setColor(this.m_bLive ? Color.blue : Color.gray);
                final double n10 = 6.283185307179586 * l / 360.0;
                final double n11 = 6.283185307179586 * r / 360.0;
                graphics.drawLine(x, y, x + (int)(n9 * Math.cos(n10)), y - (int)(n9 * Math.sin(n10)));
                graphics.drawLine(x, y, x + (int)(n9 * Math.cos(n11)), y - (int)(n9 * Math.sin(n11)));
            }
            graphics.setColor(Color.yellow);
            graphics.fillOval(x - 3, y - 3, 6, 6);
            graphics.setColor(Color.blue);
            graphics.drawOval(x - 3, y - 3, 6, 6);
        }
        graphics.translate(-tx, -ty);
        final int hot = this.m_hot;
        if (hot != -1) {
            this.drawStatusLine(graphics, this.getLinkDesc(hot));
        }
    }
    
    private void drawStatusLine(final Graphics graphics, final String s) {
        if (s != null) {
            this.drawString(graphics, s, this.size().height - 2, FloorPlan.cY);
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setHotLink(-1);
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.m_bPainted = true;
        try {
            final Dimension size = this.size();
            final Rectangle intersection = graphics.getClipRect().intersection(new Rectangle(0, 0, size.width, size.height));
            final Image image = this.createImage(intersection.width, intersection.height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.translate(-intersection.x, -intersection.y);
            graphics2.clipRect(intersection.x, intersection.y, intersection.width, intersection.height);
            graphics2.setColor(this.getBackground());
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
    
    private void setHotLink(final int hot) {
        if (hot != this.m_hot) {
            this.showStatus((hot >= 0) ? this.getLink(hot)[0] : null);
            this.m_hot = hot;
            this.repaint();
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
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
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
    
    private int getDegreePos(final int n, final int n2) {
        return ((int)(Math.atan2(this.m_y + this.m_off.y - n2, n - (this.m_x + this.m_off.x)) / 3.141592653589793 * 180.0) + 360) % 360;
    }
    
    private void drawString(final Graphics graphics, final String s, final int n, final Color color) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        final int n2 = (this.size().width - stringWidth) / 2;
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(n2, n - height, stringWidth + 2 + 2, height);
            graphics.setColor(Color.black);
            graphics.drawRect(n2, n - height, stringWidth + 2 + 2, height);
        }
        graphics.setColor(Color.black);
        graphics.drawString(s, n2 + 2, n - fontMetrics.getDescent());
    }
    
    public void destroy() {
        try {
            this.m_i.flush();
        }
        catch (Exception ex) {}
        this.m_i = null;
    }
    
    private void addLink(final String s) throws MalformedURLException {
        final Object[] link = parseLink(s);
        if (link != null && !this.getDocumentBase().equals(new URL(this.getDocumentBase(), this.getLink(link)[0]))) {
            final int length = this.m_links.length;
            final Object[] links = new Object[length + 1];
            System.arraycopy(this.m_links, 0, links, 0, length);
            (this.m_links = links)[length] = link;
        }
    }
    
    public FloorPlan() {
        this.m_c = "© 1999 duckware.com";
        this.m_v = "FloorPlan 2.2d";
        this.m_xor = 1052688;
        this.m_off = new Point(0, 0);
        this.m_hot = -1;
        this.m_down = new Point(0, 0);
        this.m_bPainted = true;
        this.m_lPaintOn = 0L;
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
    
    private boolean doMouse(final int n, final int n2) {
        if (this.m_bInDrag) {
            try {
                this.m_pmvr.set("deg", Integer.toString(this.getDegreePos(n, n2)));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setView(final pmvr pmvr, final int l, final int r) {
        if (this.m_l != l || this.m_r != r) {
            this.m_pmvr = pmvr;
            this.m_bLive = true;
            this.m_l = l;
            this.m_r = r;
            this.repaint(50L);
        }
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
        FloorPlan.cY = new Color(255, 255, 192);
    }
    
    public String getAppletInfo() {
        return this.m_v + " - " + this.m_c;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.m_down = new Point(n, n2);
        this.m_bInDrag = (-1 == this.hotHitTest(n, n2));
        return this.doMouse(n, n2);
    }
    
    public void init() {
        this.setFont(new Font("Helvitica", 0, 14));
        this.setBackground(new Color(15790320));
        if (this.m_i == null) {
            final URL documentBase = this.getDocumentBase();
            try {
                documentBase.openConnection().setDefaultUseCaches(true);
            }
            catch (Exception ex) {}
            final String[] array = { "image", "x", "y", "left", "right", "xor", "background", "links" };
            for (int i = 0; i < array.length; ++i) {
                this.set(array[i], this.getParameter(array[i]));
            }
            String parameter;
            for (int n = 0; (parameter = this.getParameter("link" + n)) != null && n < 1000; ++n) {
                this.set("link" + n, parameter);
            }
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.m_bInDrag |= (Math.abs(n - this.m_down.x) > 3 || Math.abs(n2 - this.m_down.y) > 3);
        this.setCursor(n, n2);
        return this.doMouse(n, n2);
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
    
    private boolean setCursor(final int n, final int n2) {
        boolean viewable = false;
        try {
            viewable = this.m_pmvr.isViewable(this.getDegreePos(n, n2));
        }
        catch (Exception ex) {}
        final int hotHitTest = this.hotHitTest(n, n2);
        this.setHotLink(hotHitTest);
        this.setCursor((hotHitTest != -1) ? 12 : (viewable ? 1 : 0));
        return true;
    }
    
    private void setCursor(final int cursor) {
        try {
            this.getFrame().setCursor(cursor);
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.setCursor(n, n2);
    }
}
