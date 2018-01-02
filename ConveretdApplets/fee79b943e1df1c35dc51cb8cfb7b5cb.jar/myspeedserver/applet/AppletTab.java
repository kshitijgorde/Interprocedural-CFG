// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import javax.swing.event.EventListenerList;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public abstract class AppletTab extends JPanel implements MouseListener, MouseMotionListener
{
    private Image KU;
    private String LU;
    private myspeed TU;
    private Hashtable GU;
    private Hashtable NU;
    private boolean OU;
    private long QV;
    private AppletPlugin JU;
    private boolean HU;
    private AppletTab$FadeCopyrightThread PU;
    private Hashtable MU;
    private EventListenerList IU;
    static Class US;
    
    public AppletTab(final Applet applet, final AppletPlugin ju, final Image ku, final String lu) {
        this.GU = new Hashtable();
        this.HU = false;
        this.IU = new EventListenerList();
        this.TU = (myspeed)applet;
        this.JU = ju;
        this.KU = ku;
        this.LU = lu;
        this.MU = new Hashtable();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    protected synchronized Image getImage(final String s) {
        Image image = this.MU.get(s);
        if (image == null) {
            image = ((this.JU == null) ? null : this.JU.getImage(s));
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 1);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                if (image.getHeight(null) > 0) {
                    this.MU.put(s, image);
                }
                else {
                    image = null;
                }
            }
        }
        return image;
    }
    
    public abstract void doFirstTimeInit();
    
    public abstract void reset();
    
    protected String getUnregisteredMessage() {
        return this.TU.getUnregisteredMessage();
    }
    
    public Image getImage() {
        return this.KU;
    }
    
    public Image getBackgroundOverlay() {
        return this.TU.getBackgroundOverlay(this.LU);
    }
    
    public String getName() {
        return this.LU;
    }
    
    protected String RC(final String s) {
        return this.TU.RC(s);
    }
    
    protected String RC(final String s, final String s2) {
        return this.TU.RC(s, s2);
    }
    
    protected String RC(final String s, final String[] array) {
        return this.TU.RC(s, array);
    }
    
    protected int iniGetInteger(final String s, final int n) {
        return this.TU.iniGetInteger(s, null, n);
    }
    
    protected long iniGetLong(final String s, final long n) {
        return this.TU.iniGetLong(s, null, n);
    }
    
    protected String iniGetString(final String s) {
        return this.TU.iniGetString(s, null);
    }
    
    protected String iniGetProfessionalString(final String s) {
        return this.TU.iniGetProfessionalString(s);
    }
    
    protected void setFindClosestHitRegion(final boolean hu) {
        this.HU = hu;
    }
    
    protected void addHitRegion(final String s, final Rectangle rectangle) {
        if (this.NU == null) {
            this.NU = new Hashtable();
        }
        this.NU.put(rectangle, s);
    }
    
    private void updateHitRegions() {
        this.GU = ((this.NU == null) ? new Hashtable() : this.NU);
        this.NU = null;
    }
    
    protected Object[] getHitRegionDetail(final int n, final int n2) {
        final Enumeration<Rectangle> keys = (Enumeration<Rectangle>)this.GU.keys();
        Object[] array = null;
        Rectangle rectangle = null;
        double n3 = -1.0;
        while (keys.hasMoreElements()) {
            final Rectangle rectangle2 = keys.nextElement();
            final String s = this.GU.get(rectangle2);
            final double n4 = Math.pow(n - (rectangle2.x + rectangle2.width / 2.0), 2.0) + Math.pow(n2 - (rectangle2.y + rectangle2.height / 2.0), 2.0);
            if (rectangle2.contains(n, n2)) {
                if (!this.HU && (rectangle == null || rectangle.width * rectangle.height > rectangle2.width * rectangle2.height)) {
                    array = new Object[] { s, rectangle2 };
                    rectangle = rectangle2;
                }
                else {
                    if (!this.HU || (n3 != -1.0 && n3 <= n4)) {
                        continue;
                    }
                    array = new Object[] { s, rectangle2 };
                    n3 = n4;
                }
            }
        }
        return array;
    }
    
    protected String getHitRegion(final int n, final int n2) {
        final Object[] hitRegionDetail = this.getHitRegionDetail(n, n2);
        return (hitRegionDetail == null || hitRegionDetail.length < 2) ? null : ((String)hitRegionDetail[0]);
    }
    
    public void setHitMaxTests(final boolean ou) {
        this.OU = ou;
    }
    
    public boolean isHitMaxTests() {
        return this.OU;
    }
    
    protected void doOverlayMessages(final Graphics graphics) {
        this.TU.doOverlayMessages(graphics);
    }
    
    protected void setFadeCopyrightInfo(final boolean b) {
        if (this.PU == null && b) {
            this.PU = new AppletTab$FadeCopyrightThread(this, this);
        }
        else if (this.PU != null && !b) {
            this.PU.stop();
            this.PU = null;
        }
    }
    
    public abstract void panelPaint(final Graphics p0);
    
    protected void paintCopyrightInfo(final Graphics graphics, final int n, int n2) {
        final int height = graphics.getFontMetrics().getHeight();
        final long n3 = 8000L;
        final long time = U.time();
        if (this.QV == 0L) {
            this.QV = time;
        }
        final long n4 = time - this.QV;
        if (n4 < n3) {
            final int n5 = (n4 < n3 / 2L) ? U.max(128, 240 - (int)n4 / 8) : U.max(128, 240 - (int)(n3 - n4) / 8);
            graphics.setColor(new Color(n5, n5, n5));
            graphics.drawString(this.TU.appNameVer(true), n, n2);
            n2 += height;
            graphics.drawString(this.TU.copyrightStuff(), n, n2);
            n2 += height;
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.panelPaint(graphics);
        this.doOverlayMessages(graphics);
        this.updateHitRegions();
    }
    
    protected Rectangle drawWrappedLines(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        return (graphics == null) ? null : this.drawWrappedLines(graphics, s, n, n2, n3, graphics.getFont());
    }
    
    protected Rectangle drawWrappedLines(final Graphics graphics, final String s, final int n, final int n2, final int n3, Font font) {
        int n4 = 0;
        int n5 = 0;
        int stringWidth = 0;
        int max = 0;
        final Font font2 = font;
        final Color color = (graphics == null) ? null : graphics.getColor();
        String s2 = null;
        FontMetrics fontMetrics = getFontMetrics(graphics, font);
        int n6 = fontMetrics.getHeight();
        int n7 = fontMetrics.getAscent();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, TX("\n <>"), true);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(TX("\n"))) {
                n5 += n6;
                n4 = 0;
            }
            else if (nextToken.equals(TX("<"))) {
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                String nextToken2 = null;
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken2 = stringTokenizer.nextToken();
                }
                String nextToken3;
                while (stringTokenizer.hasMoreTokens() && !(nextToken3 = stringTokenizer.nextToken()).equals(TX(">"))) {
                    final int index = nextToken3.indexOf(TX("="));
                    if (index > 0 && index < nextToken3.length() - 1) {
                        hashtable.put(nextToken3.substring(0, index), nextToken3.substring(index + 1));
                    }
                }
                if (nextToken2 != null && nextToken2.equals(TX("b"))) {
                    font = new Font(font2.getName(), font2.getStyle() | 0x1, font2.getSize());
                    if (graphics != null) {
                        graphics.setFont(font);
                    }
                }
                else if (nextToken2 != null && nextToken2.equals(TX("/b"))) {
                    font = new Font(font2.getName(), font2.getStyle() & 0xFFFFFFFE, font2.getSize());
                    if (graphics != null) {
                        graphics.setFont(font);
                    }
                }
                else if (nextToken2 != null && nextToken2.equals(TX("a"))) {
                    if (graphics != null) {
                        graphics.setColor(Color.blue);
                    }
                    s2 = hashtable.get("href");
                }
                else if (nextToken2 != null && nextToken2.equals(TX("/a"))) {
                    if (graphics != null) {
                        graphics.setColor(color);
                    }
                    s2 = null;
                }
                else if (nextToken2 != null && nextToken2.equals(TX("span"))) {
                    try {
                        graphics.setColor(new Color(Integer.parseInt(hashtable.get("bgcolor"), 16)));
                    }
                    catch (Exception ex) {}
                }
                else if (nextToken2 != null && nextToken2.equals(TX("/span"))) {
                    if (graphics != null) {
                        graphics.setColor(color);
                    }
                }
                else if (nextToken2 != null && nextToken2.equals(TX("img"))) {
                    final String s3 = hashtable.get("src");
                    if (s3 != null) {
                        final Image image = this.getImage(s3);
                        final int n8 = (image == null) ? -1 : image.getWidth(null);
                        final int n9 = (image == null) ? -1 : image.getHeight(null);
                        if (n8 > 0 && n9 > 0) {
                            if (n4 + stringWidth > n3) {
                                n5 += n6;
                                n4 = 0;
                            }
                            if (graphics != null) {
                                final int n10 = n2 + n5 + n6 / 2 - n9 / 2;
                                graphics.drawImage(image, n + n4, n10, null);
                                if (s2 != null) {
                                    this.addHitRegion(s2, new Rectangle(n + n4, n10, n8, n9));
                                }
                            }
                            n4 += n8;
                        }
                    }
                }
                fontMetrics = getFontMetrics(graphics, font);
                n7 = fontMetrics.getAscent();
                n6 = fontMetrics.getHeight();
            }
            else {
                stringWidth = fontMetrics.stringWidth(nextToken);
                if (n4 + stringWidth > n3) {
                    n5 += n6;
                    n4 = 0;
                    if (nextToken.equals(TX(" "))) {
                        nextToken = "";
                        stringWidth = 0;
                    }
                }
                if (graphics != null) {
                    graphics.drawString(nextToken, n + n4, n2 + n5 + n7);
                }
                if (graphics != null && s2 != null && stringWidth > 0) {
                    graphics.drawLine(n + n4, n2 + n5 + n7 + 1, n + n4 + stringWidth, n2 + n5 + n7 + 1);
                    this.addHitRegion(s2, new Rectangle(n + n4, n2 + n5, stringWidth, n6));
                }
                n4 += stringWidth;
                max = Math.max(n4, max);
            }
        }
        if (n4 > 0) {
            n5 += n6;
        }
        return new Rectangle(n, n2, max, n5);
    }
    
    protected static FontMetrics getFontMetrics(final Graphics graphics, final Font font) {
        return (graphics == null) ? Toolkit.getDefaultToolkit().getFontMetrics(font) : graphics.getFontMetrics();
    }
    
    protected int drawBars(final Graphics graphics, final FontMetrics fontMetrics, final int n, final int n2, final int n3, int max, final String[] array, final int[] array2, final float n4, final String s) {
        final int height = fontMetrics.getHeight();
        final int n5 = height + 15;
        final int n6 = height + 6;
        int max2 = 1;
        final boolean b = max < 0;
        for (int i = 0; i < array2.length; ++i) {
            max2 = Math.max(max2, array2[i]);
            if (b) {
                max = Math.max(max, fontMetrics.stringWidth(array[i]));
            }
        }
        final int n7 = n3 - 3 - max - 2;
        int n8 = n2;
        if (n7 > 0) {
            for (int j = 0; j < array2.length; ++j) {
                if (graphics != null) {
                    graphics.setColor(new Color(6710886));
                    graphics.drawString(array[j], n, n8 + n5 / 2 - fontMetrics.getDescent() + height / 2);
                    final boolean b2 = array2[j] < 0;
                    final Rectangle rectangle = new Rectangle(n + max + 2, n8 + n5 / 2 - n6 / 2, Math.max(10, b2 ? (n7 / 10) : (n7 * array2[j] / max2)), n6);
                    graphics.setColor(new Color(b2 ? 10485760 : 40960));
                    graphics.fillRect(rectangle.x + 4, rectangle.y + rectangle.height - n6 + 2, rectangle.width - 9, n6 - 4);
                    graphics.setColor(new Color(b2 ? 12582912 : 49152));
                    graphics.drawRect(rectangle.x + 3, rectangle.y + rectangle.height - n6 + 1, rectangle.width - 8, n6 - 3);
                    graphics.setColor(new Color(b2 ? 8388608 : 32768));
                    graphics.drawRect(rectangle.x + 3, rectangle.y + rectangle.height - n6, rectangle.width - 7, n6 - 2);
                    String s2 = s;
                    if (!b2) {
                        s2 = ((n4 > 1.0f) ? new StringBuffer().append(array2[j] / n4).toString() : new StringBuffer().append(array2[j]).toString());
                    }
                    final boolean b3 = fontMetrics.stringWidth(s2) < rectangle.width - 10;
                    final int n9 = b3 ? (rectangle.x + rectangle.width - 6 - fontMetrics.stringWidth(s2)) : (rectangle.x + rectangle.width + 3);
                    graphics.setColor(b3 ? Color.white : new Color(6710886));
                    graphics.drawString(s2, n9, n8 + n5 / 2 - fontMetrics.getDescent() + height / 2 - 1);
                }
                n8 += n5;
            }
        }
        return n8 - n2;
    }
    
    public void setCursor(final Cursor cursor) {
        super.setCursor(cursor);
        this.TU.getGlassPane().setCursor(cursor);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final String hitRegion = this.getHitRegion(mouseEvent.getX(), mouseEvent.getY());
        if (hitRegion != null) {
            this.fireActionEvent(new ActionEvent(this, 1001, hitRegion));
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor((this.getHitRegion(mouseEvent.getX(), mouseEvent.getY()) == null) ? 0 : 12));
    }
    
    public void addActionListener(final ActionListener actionListener) {
        final EventListenerList iu = this.IU;
        Class us;
        if ((us = AppletTab.US) == null) {
            try {
                us = (AppletTab.US = Class.forName("java.awt.event.ActionListener"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        iu.add(us, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        final EventListenerList iu = this.IU;
        Class us;
        if ((us = AppletTab.US) == null) {
            try {
                us = (AppletTab.US = Class.forName("java.awt.event.ActionListener"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        iu.remove(us, actionListener);
    }
    
    protected void fireActionEvent(final ActionEvent actionEvent) {
        final EventListenerList iu = this.IU;
        Class us;
        if ((us = AppletTab.US) == null) {
            try {
                us = (AppletTab.US = Class.forName("java.awt.event.ActionListener"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final ActionListener[] array = iu.getListeners((Class<ActionListener>)us);
        for (int n = 0; array != null && n < array.length; ++n) {
            array[n].actionPerformed(actionEvent);
        }
    }
    
    private static String TX(final String s) {
        return s;
    }
    
    public void addActionListener(final myspeed_TX myspeed_TX) {
    }
}
