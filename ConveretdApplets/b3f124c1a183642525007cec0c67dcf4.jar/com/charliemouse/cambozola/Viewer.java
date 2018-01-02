// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola;

import java.util.Hashtable;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import com.charliemouse.cambozola.shared.ImageChangeEvent;
import java.util.Enumeration;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.Color;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import com.charliemouse.cambozola.watermark.WatermarkCollection;
import com.charliemouse.cambozola.watermark.Watermark;
import java.awt.Image;
import com.charliemouse.cambozola.shared.AppID;
import com.charliemouse.cambozola.shared.CamStream;
import java.util.Vector;
import java.net.URL;
import java.util.Properties;
import com.charliemouse.cambozola.shared.ExceptionReporter;
import com.charliemouse.cambozola.shared.ImageChangeListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class Viewer extends Applet implements MouseListener, MouseMotionListener, KeyListener, ImageChangeListener, ExceptionReporter, ViewerAttributeInterface
{
    private static boolean ms_standalone;
    private Properties m_parameters;
    private URL m_documentBase;
    private URL m_codeBase;
    private URL m_mainURL;
    private Vector m_alternateURLs;
    private CamStream m_imgStream;
    private String m_msg;
    private AppID m_props;
    private boolean m_displayAccessories;
    private PercentArea m_area;
    private Vector m_accessories;
    private Image m_offscreenAccBar;
    private Image m_backingStore;
    private boolean m_readingStream;
    private int m_retryCount;
    private int m_retryDelay;
    private Image m_failureImage;
    private boolean m_loadFailure;
    private Watermark m_wmHit;
    private WatermarkCollection m_wmCollection;
    
    public Viewer() {
        this.m_parameters = null;
        this.m_documentBase = null;
        this.m_codeBase = null;
        this.m_mainURL = null;
        this.m_alternateURLs = null;
        this.m_imgStream = null;
        this.m_msg = null;
        this.m_props = null;
        this.m_displayAccessories = false;
        this.m_area = new PercentArea();
        this.m_accessories = new Vector();
        this.m_offscreenAccBar = null;
        this.m_backingStore = null;
        this.m_readingStream = false;
        this.m_retryCount = 1;
        this.m_retryDelay = 1000;
        this.m_failureImage = null;
        this.m_loadFailure = false;
        this.m_wmHit = null;
        this.m_wmCollection = null;
        this.m_props = AppID.getAppID();
        this.m_alternateURLs = new Vector();
        this.m_parameters = new Properties();
        this.m_wmCollection = new WatermarkCollection();
    }
    
    public void init() {
        if (!Viewer.ms_standalone) {
            this.m_documentBase = this.getDocumentBase();
            this.m_codeBase = this.getCodeBase();
            ((Hashtable<String, String>)this.m_parameters).put("failureimage", this.getHTMLParameterValue("failureimage"));
            ((Hashtable<String, String>)this.m_parameters).put("delay", this.getHTMLParameterValue("delay"));
            ((Hashtable<String, String>)this.m_parameters).put("retries", this.getHTMLParameterValue("retries"));
            ((Hashtable<String, String>)this.m_parameters).put("url", this.getHTMLParameterValue("url"));
            ((Hashtable<String, String>)this.m_parameters).put("accessories", this.getHTMLParameterValue("accessories"));
            ((Hashtable<String, String>)this.m_parameters).put("connectionmethod", this.getHTMLParameterValue("connectionmethod"));
        }
        this.m_props = AppID.getAppID();
        this.m_alternateURLs = new Vector();
        final String message = "www.panolive.com (c) by Gardena.Net";
        this.setMessage(message);
        System.err.println("// " + message);
        System.err.println("// Build date: " + this.m_props.getBuildDate());
        System.err.println("// Available from " + this.m_props.getLocURL());
        final String parameterValue = this.getParameterValue("failureimage");
        if (parameterValue != null && !parameterValue.equals("")) {
            try {
                this.setFailureImageURL(new URL(this.m_documentBase, parameterValue));
            }
            catch (MalformedURLException ex2) {
                System.err.println("Unable to access URL for failure image -" + parameterValue);
            }
        }
        final String parameterValue2 = this.getParameterValue("delay");
        if (parameterValue2 != null && !parameterValue2.equals("")) {
            try {
                this.setRetryDelay(Integer.parseInt(parameterValue2));
            }
            catch (Exception ex3) {
                System.err.println("Unable to set retry delay");
            }
        }
        final String parameterValue3 = this.getParameterValue("retries");
        if (parameterValue3 != null && !parameterValue3.equals("")) {
            try {
                this.setRetryCount(Integer.parseInt(parameterValue3));
            }
            catch (Exception ex4) {
                System.err.println("Unable to set retry count");
            }
        }
        final String parameterValue4 = this.getParameterValue("connectionmethod");
        if (parameterValue4 != null && !parameterValue4.equals("")) {
            try {
                if (parameterValue4.equalsIgnoreCase("socket")) {
                    CamStream.setConnectionMethod(1);
                }
                else if (parameterValue4.equalsIgnoreCase("http")) {
                    CamStream.setConnectionMethod(2);
                }
            }
            catch (Exception ex5) {
                System.err.println("Unable to set connection method");
            }
        }
        final String parameterValue5 = this.getParameterValue("url");
        if (parameterValue5 == null && !parameterValue5.equals("")) {
            throw new IllegalArgumentException("Missing URL");
        }
        this.m_mainURL = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(parameterValue5, "|");
        while (stringTokenizer.hasMoreTokens()) {
            try {
                final URL mainURL = new URL(this.m_codeBase, stringTokenizer.nextToken());
                this.m_alternateURLs.addElement(mainURL);
                if (this.m_mainURL != null) {
                    continue;
                }
                this.m_mainURL = mainURL;
            }
            catch (MalformedURLException ex) {
                this.reportError(ex);
            }
        }
        this.setCurrentURL(this.m_mainURL);
        this.setAlternateURLs(this.m_alternateURLs);
        this.configureAccessories(this.getParameterValue("accessories"));
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    public static void main(final String[] array) {
        Viewer.ms_standalone = true;
        final Frame frame = new Frame(AppID.getAppID().getAppName());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.setSize(352, 320);
        frame.setLayout(new BorderLayout());
        final Viewer viewer = new Viewer();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            if (s.startsWith("-")) {
                if (s.equals("-noaccessories")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("accessories", "none");
                }
                else if (s.startsWith("-accessories=")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("accessories", s.substring(13));
                }
                else if (s.startsWith("-retries=")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("retries", s.substring(9));
                }
                else if (s.startsWith("-connectionmethod=")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("connectionmethod", s.substring(16));
                }
                else if (s.startsWith("-delay=")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("delay", s.substring(7));
                }
                else if (s.startsWith("-failureimage=")) {
                    ((Hashtable<String, String>)viewer.m_parameters).put("failureimage", s.substring(14));
                }
                else {
                    usage();
                    System.exit(0);
                }
            }
            else {
                if (sb.length() != 0) {
                    sb.append("|");
                }
                sb.append(s.trim());
            }
        }
        if (sb.length() == 0) {
            usage();
            System.exit(0);
        }
        ((Hashtable<String, String>)viewer.m_parameters).put("url", sb.toString());
        frame.add("Center", viewer);
        viewer.init();
        frame.setVisible(true);
        viewer.start();
    }
    
    private String getHTMLParameterValue(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return "";
        }
        return parameter;
    }
    
    private String getParameterValue(final String s) {
        return this.m_parameters.getProperty(s, null);
    }
    
    void setFailureImageURL(final URL url) {
        try {
            (this.m_failureImage = this.createImage((ImageProducer)url.getContent())).getWidth(this);
        }
        catch (IOException ex) {
            System.err.println("Unable to access failure image contents - " + ex);
        }
    }
    
    void setRetryCount(final int retryCount) {
        if (retryCount < 1) {
            return;
        }
        this.m_retryCount = retryCount;
    }
    
    void setRetryDelay(final int retryDelay) {
        if (retryDelay < 0) {
            return;
        }
        this.m_retryDelay = retryDelay;
    }
    
    private void configureAccessories(String s) {
        if (s == null || s.equals("") || s.equalsIgnoreCase("default")) {
            s = "Home,ZoomOut,ZoomIn,Pan,ChangeStream,Info,WWWHelp";
        }
        else if (s.equalsIgnoreCase("none")) {
            s = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            try {
                final Accessory accessory = (Accessory)Class.forName("com.charliemouse.cambozola.accessories." + nextToken + "Accessory").newInstance();
                accessory.setViewerAttributes(this);
                if (!accessory.isEnabled()) {
                    continue;
                }
                accessory.getIconImage();
                this.m_accessories.addElement(accessory);
            }
            catch (Exception ex) {
                System.err.println("Unable to load accessory - " + nextToken);
                ex.printStackTrace();
            }
        }
    }
    
    public synchronized void reportError(final Throwable t) {
        this.reportNote(t.getMessage());
        this.stop();
    }
    
    public synchronized void reportFailure(final String s) {
        this.m_loadFailure = true;
        this.reportNote(s);
    }
    
    public synchronized void reportNote(final String message) {
        System.err.println(message);
        this.setMessage(message);
        this.m_readingStream = false;
        this.repaint();
    }
    
    private synchronized void setMessage(final String msg) {
        this.m_msg = msg;
    }
    
    public void start() {
    }
    
    public void stop() {
        if (this.m_imgStream != null) {
            this.m_imgStream.unhook();
            this.m_imgStream = null;
        }
        this.m_readingStream = false;
        final Enumeration<Accessory> elements = this.m_accessories.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().terminate();
        }
    }
    
    public void setCurrentURL(final URL mainURL) {
        this.m_loadFailure = false;
        final String string = this.m_props.getAppNameVersion() + "/Java " + System.getProperty("java.version") + " " + System.getProperty("java.vendor");
        this.m_mainURL = mainURL;
        if (this.m_imgStream != null) {
            this.m_msg = "";
            this.m_imgStream.removeImageChangeListener(this);
            this.m_imgStream.unhook();
        }
        (this.m_imgStream = new CamStream(this.m_mainURL, string, this.m_documentBase, this.m_retryCount, this.m_retryDelay, this)).addImageChangeListener(this);
        this.m_imgStream.start();
    }
    
    public void displayURL(final URL url, final String s) {
        if (Viewer.ms_standalone) {
            return;
        }
        if (s == null) {
            this.getAppletContext().showDocument(url);
        }
        else {
            this.getAppletContext().showDocument(url, s);
        }
    }
    
    public Vector getAlternateURLs() {
        return this.m_alternateURLs;
    }
    
    public void setAlternateURLs(final Vector alternateURLs) {
        this.m_alternateURLs = alternateURLs;
    }
    
    public void imageChanged(final ImageChangeEvent imageChangeEvent) {
        this.update(this.getGraphics());
        this.getToolkit().sync();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.getSize();
        if (this.m_backingStore == null || this.m_backingStore.getWidth(this) != size.width || this.m_backingStore.getHeight(this) != size.height) {
            this.m_backingStore = this.createImage(size.width, size.height);
            this.m_wmCollection.recalculateLocations(size);
        }
        final Graphics graphics2 = this.m_backingStore.getGraphics();
        if (this.m_loadFailure && this.m_failureImage != null) {
            this.paintFrame(graphics2, this.m_failureImage, size, null);
        }
        else if (!this.m_readingStream) {
            graphics2.setPaintMode();
            graphics2.setColor(Color.white);
            graphics2.fillRect(this.m_displayAccessories ? 30 : 0, 0, size.width, size.height);
            final int stringWidth = graphics2.getFontMetrics().stringWidth(this.m_msg);
            graphics2.setColor(Color.black);
            graphics2.drawString(this.m_msg, (size.width - stringWidth) / 2, size.height / 2);
            this.paintAccessories(graphics2);
        }
        if (this.m_imgStream != null) {
            final Image current = this.m_imgStream.getCurrent();
            if (current != null) {
                this.m_loadFailure = false;
                this.m_readingStream = true;
                this.paintFrame(graphics2, current, size, this.m_wmCollection);
            }
        }
        graphics.drawImage(this.m_backingStore, 0, 0, null);
        graphics2.dispose();
    }
    
    public void paintFrame(final Graphics graphics, final Image image, final Dimension dimension, final WatermarkCollection collection) {
        final int n = this.m_displayAccessories ? 30 : 0;
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        if (width == -1 || height == -1) {
            return;
        }
        final Rectangle area = this.m_area.getArea(width, height);
        graphics.drawImage(image, n, 0, dimension.width, dimension.height, area.x, area.y, area.x + area.width, area.y + area.height, this);
        if (collection != null) {
            collection.paint(graphics);
        }
        this.paintAccessories(graphics);
    }
    
    private void paintAccessories(final Graphics graphics) {
        final int n = this.m_displayAccessories ? 30 : 0;
        final Dimension size = this.getSize();
        final int size2 = this.m_accessories.size();
        if (n > 0 && size2 > 0) {
            if (this.m_offscreenAccBar == null) {
                this.m_offscreenAccBar = this.createImage(30, this.m_accessories.size() * 30);
                final Graphics graphics2 = this.m_offscreenAccBar.getGraphics();
                int n2 = 0;
                final Enumeration<Accessory> elements = (Enumeration<Accessory>)this.m_accessories.elements();
                while (elements.hasMoreElements()) {
                    graphics2.setColor(Color.lightGray);
                    final Accessory accessory = elements.nextElement();
                    final int n3 = n2 * 30;
                    graphics2.fill3DRect(0, n3, 30, 30, true);
                    graphics2.drawImage(accessory.getIconImage(), 3, n3 + 3, new ImageObserver() {
                        public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
                            System.err.println(image + "  " + n);
                            return true;
                        }
                    });
                    ++n2;
                }
                graphics2.dispose();
            }
            graphics.drawImage(this.m_offscreenAccBar, 0, 0, null);
            final int n4 = this.m_accessories.size() * 30;
            graphics.setColor(Color.white);
            graphics.fillRect(0, n4, n, size.height);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (!this.m_readingStream) {
            return;
        }
        if (keyEvent.getKeyCode() == 36) {
            this.m_area.reset();
        }
        else if (keyEvent.getKeyCode() == 33) {
            this.m_area.zoomIn();
        }
        else if (keyEvent.getKeyCode() == 34) {
            this.m_area.zoomOut();
        }
        else if (keyEvent.getKeyCode() == 37) {
            this.m_area.panHorizontal(-1);
        }
        else if (keyEvent.getKeyCode() == 39) {
            this.m_area.panHorizontal(1);
        }
        else if (keyEvent.getKeyCode() == 38) {
            this.m_area.panVertical(-1);
        }
        else if (keyEvent.getKeyCode() == 40) {
            this.m_area.panVertical(1);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.m_displayAccessories) {
            this.m_displayAccessories = false;
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!Viewer.ms_standalone && !this.m_displayAccessories && this.m_wmHit != null) {
            this.displayURL(this.m_wmHit.getURL(), null);
            return;
        }
        if (mouseEvent.getX() >= 30) {
            return;
        }
        final int n = mouseEvent.getY() / 30;
        if (n < this.m_accessories.size()) {
            ((Accessory)this.m_accessories.elementAt(n)).actionPerformed(new Point(mouseEvent.getX(), mouseEvent.getY() - n * 30));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        boolean b = false;
        final Point point = mouseEvent.getPoint();
        final boolean displayAccessories = this.m_displayAccessories;
        if (point.x < 30) {
            if (this.m_accessories.size() > 0) {
                this.m_displayAccessories = true;
                final int n = mouseEvent.getY() / 30;
                String s = "";
                if (n < this.m_accessories.size()) {
                    final String description = this.m_accessories.elementAt(n).getDescription();
                    if (description != null) {
                        s = description;
                    }
                }
                this.showStatus(s);
            }
        }
        else {
            this.m_displayAccessories = false;
        }
        if (!this.m_displayAccessories && !Viewer.ms_standalone) {
            final Watermark overClickableWatermark = this.m_wmCollection.isOverClickableWatermark(point);
            if (overClickableWatermark != this.m_wmHit) {
                this.m_wmHit = overClickableWatermark;
                b = true;
                this.setCursor((this.m_wmHit != null) ? Cursor.getPredefinedCursor(12) : Cursor.getDefaultCursor());
            }
        }
        if (this.m_displayAccessories != displayAccessories) {
            b = true;
        }
        if (b) {
            this.repaint();
        }
    }
    
    public void showStatus(final String s) {
        if (!Viewer.ms_standalone) {
            super.showStatus(s);
        }
    }
    
    public PercentArea getViewArea() {
        return this.m_area;
    }
    
    public CamStream getStream() {
        return this.m_imgStream;
    }
    
    public boolean isStandalone() {
        return Viewer.ms_standalone;
    }
    
    public Vector getAccessories() {
        return this.m_accessories;
    }
    
    public static void usage() {
        System.err.println("Usage: WebCamURL [otherURLs] [-accessories=comma separated accessory list]");
        System.err.println("Current set of accessories are:");
        System.err.println(" o ZoomIn       - Zooms in to the image");
        System.err.println(" o ZoomOut      - Zooms out of the image");
        System.err.println(" o Home         - Shows all the image");
        System.err.println(" o Pan          - Pan around a zoomed-in image");
        System.err.println(" o ChangeStream - Swap to a different stream (if > 1 listed)");
        System.err.println(" o Info         - Displays information about the stream");
        System.err.println(" o WWWHelp      - Displays a web page showing help");
        System.err.println("");
        System.err.println(" -noaccessories         Will not display any accessories");
        System.err.println(" -accessories=none      Will not display any accessories");
        System.err.println(" -accessories=default   Will display the default set of accessories");
        System.err.println(" -retries={num}         The number of retries (default = 1)");
        System.err.println(" -delay={num}           The number of milliseconds between retries");
        System.err.println(" -failureimage={url}    Image to display if failure to connect");
        System.err.println(" -connectionmethod=mth  The method of connection [HTTP or Socket]");
    }
    
    static {
        Viewer.ms_standalone = false;
    }
}
