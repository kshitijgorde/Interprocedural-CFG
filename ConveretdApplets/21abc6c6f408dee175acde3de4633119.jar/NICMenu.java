import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Insets;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;
import java.awt.Window;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NICMenu extends Applet implements Runnable, ActionListener, MouseListener, MouseMotionListener, WindowListener
{
    private final String home = "http://www.sampo.ru/~kryshen/nicmenu.html";
    public Image bkgr;
    private Image buffer;
    private int hgap;
    private int vgap;
    private int pheight;
    private int pwidth;
    private final boolean DISPLAY_AD = true;
    private boolean adv;
    private final int linset = 15;
    private final Font advFont;
    private final int sAdvHeight = 100;
    private final String sAdv = "NIC MENU";
    private boolean active;
    private int ax1;
    private int ay1;
    private int ax2;
    private int ay2;
    private String prefix;
    private Window window;
    private NICMenu parent;
    private Vector childs;
    private Vector imageDims;
    private Vector images;
    private Vector comps;
    private Thread imageLoader;
    private static boolean aa;
    private NICMenu applet;
    private boolean initialized;
    
    private NICMenu(final String prefix, final Window window, final NICMenu parent, final NICMenu applet) {
        this.bkgr = null;
        this.buffer = null;
        this.pheight = 0;
        this.pwidth = 0;
        this.adv = true;
        this.advFont = new Font("Helvetica", 1, 10);
        this.active = false;
        this.ax1 = 0;
        this.ay1 = 0;
        this.ax2 = 0;
        this.ay2 = 0;
        this.window = null;
        this.parent = null;
        this.childs = new Vector();
        this.imageDims = new Vector();
        this.images = new Vector();
        this.comps = new Vector();
        this.initialized = false;
        this.prefix = prefix;
        this.adv = false;
        this.window = window;
        this.applet = applet;
        this.parent = parent;
        if (parent.window != null) {
            parent.window.addWindowListener(this);
        }
        this.window.addWindowListener(this);
        parent.childs.addElement(this);
        this.init();
    }
    
    public NICMenu() {
        this.bkgr = null;
        this.buffer = null;
        this.pheight = 0;
        this.pwidth = 0;
        this.adv = true;
        this.advFont = new Font("Helvetica", 1, 10);
        this.active = false;
        this.ax1 = 0;
        this.ay1 = 0;
        this.ax2 = 0;
        this.ay2 = 0;
        this.window = null;
        this.parent = null;
        this.childs = new Vector();
        this.imageDims = new Vector();
        this.images = new Vector();
        this.comps = new Vector();
        this.initialized = false;
        this.prefix = "";
        this.applet = this;
    }
    
    public void init() {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        this.setBackground(this.getColorParam(this.prefix + "Background.color", new Color(255, 255, 255)));
        final Image imageParam = this.getImageParam(this.prefix + "Background.image", null);
        if (imageParam != null) {
            this.images.addElement(imageParam);
            this.comps.addElement(this);
            this.imageDims.addElement(new Dimension(-1, -1));
        }
        this.hgap = this.getIntParam(this.prefix + "Gap.horizontal", 4);
        this.vgap = this.getIntParam(this.prefix + "Gap.vertical", 4);
        this.setLayout(new FlowLayout(1, this.hgap, this.vgap));
        this.pheight += this.vgap;
        int n = 1;
        while (true) {
            final String string = this.prefix + "Button" + n;
            final String convert = convert(this.applet.getParameter(string + ".text"));
            final Image imageParam2 = this.getImageParam(string + ".icon", null);
            if (convert == null && imageParam2 == null) {
                break;
            }
            final int intParam = this.getIntParam(string + ".height", 25);
            final int intParam2 = this.getIntParam(string + ".width", 170);
            this.pwidth = Math.max(this.pwidth, intParam2 + this.hgap * 2);
            this.pheight += intParam + this.vgap;
            final NICButton nicButton = new NICButton(convert, intParam2, intParam, this);
            nicButton.setName(string);
            nicButton.backColor = this.getColorParam(string + ".backColor", new Color(240, 240, 240));
            nicButton.textColor = this.getColorParam(string + ".textColor", new Color(0, 0, 255));
            nicButton.borderColor = this.getColorParam(string + ".borderColor", new Color(240, 0, 0));
            nicButton.borderWidth = this.getIntParam(string + ".borderWidth", 1);
            nicButton.setFont(this.getFontParam(string + ".font", "Helvetica", 0, 12));
            if (imageParam2 != null) {
                this.images.addElement(imageParam2);
                this.comps.addElement(nicButton);
                this.imageDims.addElement(new Dimension(this.getIntParam(string + ".iconWidth", -1), this.getIntParam(string + ".iconHeight", -1)));
            }
            nicButton.addMouseListener(this);
            this.add(nicButton);
            ++n;
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        (this.imageLoader = new Thread(this)).start();
    }
    
    public void run() {
        for (int i = 0; i <= this.images.size() - 1; ++i) {
            final Object element = this.comps.elementAt(i);
            Image scaledInstance = this.images.elementAt(i);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(scaledInstance, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {}
            boolean b = true;
            if (mediaTracker.isErrorAny()) {
                mediaTracker.removeImage(scaledInstance);
                scaledInstance.flush();
                mediaTracker.addImage(scaledInstance, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (Exception ex2) {}
                if (mediaTracker.isErrorAny()) {
                    System.err.println("NIC Menu: Error loading image @" + Integer.toHexString(scaledInstance.hashCode()) + " for component @" + Integer.toHexString(element.hashCode()) + "!");
                    b = false;
                }
            }
            if (b) {
                final Dimension dimension = this.imageDims.elementAt(i);
                if (dimension != null && (dimension.width > 0 || dimension.height > 0)) {
                    final int width = scaledInstance.getWidth(null);
                    final int height = scaledInstance.getHeight(null);
                    if (dimension.width <= 0) {
                        dimension.width = dimension.height / height * width;
                    }
                    if (dimension.height <= 0) {
                        dimension.height = dimension.width / width * height;
                    }
                    if (dimension.width != width || dimension.height != height) {
                        scaledInstance = scaledInstance.getScaledInstance(dimension.width, dimension.height, 4);
                        mediaTracker.addImage(scaledInstance, 1);
                        try {
                            mediaTracker.waitForID(1);
                        }
                        catch (Exception ex3) {}
                    }
                }
                if (element instanceof NICMenu) {
                    ((NICMenu)element).bkgr = scaledInstance;
                    ((NICMenu)element).repaint();
                }
                else if (element instanceof NICButton) {
                    ((NICButton)element).setIcon(scaledInstance);
                }
            }
        }
        this.imageLoader = null;
    }
    
    public void paint(final Graphics aa) {
        aa.clearRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.bkgr != null) {
            final int width = this.bkgr.getWidth(null);
            final int height = this.bkgr.getHeight(null);
            if (width > 0 & height > 0) {
                for (int i = 0; i < this.getSize().width; i += width) {
                    for (int j = 0; j < this.getSize().height; j += height) {
                        aa.drawImage(this.bkgr, i, j, this);
                    }
                }
            }
        }
        if (NICMenu.aa) {
            try {
                new Object() {
                    void setAA(final Graphics graphics) throws Throwable {
                        ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
                    }
                }.setAA(aa);
            }
            catch (Throwable t) {
                System.err.println("NIC Menu: " + t);
                System.err.println("          Text antialiasing is not supported by your Java VM.");
                NICMenu.aa = false;
            }
        }
        if (this.adv) {
            aa.setFont(this.advFont);
            final FontMetrics fontMetrics = aa.getFontMetrics();
            final int height2 = fontMetrics.getHeight();
            final int n = height2 * "NIC MENU".length();
            if (this.getSize().height > 100) {
                this.ay1 = (this.getSize().height - n) / 2;
                int n3;
                final int n2 = n3 = height2 - fontMetrics.getDescent() + this.ay1;
                for (int k = 0; k <= "NIC MENU".length() - 1; ++k) {
                    final String substring = "NIC MENU".substring(k, k + 1);
                    this.drawStringBorder(aa, substring, (15 + this.hgap - fontMetrics.stringWidth(substring)) / 2, n3);
                    n3 += height2;
                }
                if (this.active) {
                    aa.setColor(Color.yellow);
                }
                else {
                    aa.setColor(Color.white);
                }
                int n4 = n2;
                this.ax1 = this.getSize().width;
                this.ax2 = 0;
                for (int l = 0; l <= "NIC MENU".length() - 1; ++l) {
                    final String substring2 = "NIC MENU".substring(l, l + 1);
                    final int stringWidth = fontMetrics.stringWidth(substring2);
                    final int ax1 = (15 + this.hgap - stringWidth) / 2;
                    if (ax1 < this.ax1) {
                        this.ax1 = ax1;
                    }
                    final int ax2 = stringWidth + ax1;
                    if (ax2 > this.ax2) {
                        this.ax2 = ax2;
                    }
                    aa.drawString(substring2, ax1, n4);
                    n4 += height2;
                }
                this.ay2 = n4 - height2 + fontMetrics.getDescent();
            }
            else {
                this.ay1 = (15 + this.vgap - height2) / 2;
                this.ay2 = this.ay1 + height2;
                final int stringWidth2 = fontMetrics.stringWidth("NIC MENU");
                this.ax1 = (this.getSize().width - stringWidth2) / 2;
                this.ax2 = this.ax1 + stringWidth2;
                this.drawStringBorder(aa, "NIC MENU", this.ax1, this.ay1 - fontMetrics.getDescent() + height2);
                if (this.active) {
                    aa.setColor(Color.yellow);
                }
                else {
                    aa.setColor(Color.white);
                }
                aa.drawString("NIC MENU", this.ax1, this.ay1 - fontMetrics.getDescent() + height2);
            }
        }
        super.paint(aa);
    }
    
    private void drawStringBorder(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.setColor(Color.black);
        for (int i = n - 1; i <= n + 1; ++i) {
            for (int j = n2 - 1; j <= n2 + 1; ++j) {
                graphics.drawString(s, i, j);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.disposeChilds();
        final NICButton nicButton = (NICButton)actionEvent.getSource();
        final String name = nicButton.getName();
        if (this.applet.getParameter(name + ".Button1.text") != null) {
            final Frame frame = new Frame(nicButton.text);
            frame.setLayout(new BorderLayout());
            final NICMenu nicMenu = new NICMenu(name + ".", frame, this, this.applet);
            frame.add(nicMenu, "Center");
            frame.pack();
            final Insets insets = frame.getInsets();
            frame.setSize(this.getIntParam(name + ".popupWidth", nicMenu.pwidth) + insets.left + insets.right, this.getIntParam(name + ".popupHeight", nicMenu.pheight) + insets.top + insets.bottom);
            final Point locationOnScreen;
            final Point point = locationOnScreen = nicButton.getLocationOnScreen();
            locationOnScreen.x += nicButton.getSize().width;
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = frame.getSize();
            if (size.width + point.x > screenSize.width) {
                point.x = screenSize.width - size.width;
            }
            if (size.height + point.y > screenSize.height) {
                point.y = screenSize.height - size.height;
            }
            frame.setLocation(point.x, point.y);
            frame.show();
        }
        else {
            NICMenu parent = this;
            while (parent.parent != null) {
                parent = parent.parent;
                parent.disposeChilds();
            }
        }
        final String parameter = this.applet.getParameter(name + ".url");
        if (parameter != null) {
            String parameter2 = this.applet.getParameter(name + ".target");
            if (parameter2 == null) {
                parameter2 = "_self";
            }
            try {
                final URL url = new URL(this.applet.getDocumentBase(), parameter);
                System.err.println(url);
                this.applet.getAppletContext().showDocument(url, parameter2);
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.buffer == null) {
            this.buffer = this.createImage(this.getSize().width, this.getSize().height);
        }
        if (this.buffer.getWidth(null) != this.getSize().width | this.buffer.getHeight(null) != this.getSize().height) {
            this.buffer = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.setClip(graphics.getClip());
        this.paint(graphics2);
        graphics.drawImage(this.buffer, 0, 0, null);
    }
    
    private Color getColorParam(final String s, final Color color) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            return color;
        }
        return new Color(Integer.valueOf(parameter, 16));
    }
    
    private Image getImageParam(final String s, final Image image) {
        final String parameter = this.applet.getParameter(s);
        if (parameter != null) {
            return this.applet.getImage(this.applet.getDocumentBase(), parameter);
        }
        return image;
    }
    
    private int getIntParam(final String s, final int n) {
        final String parameter = this.applet.getParameter(s);
        if (parameter == null) {
            return n;
        }
        try {
            return new Integer(parameter);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private Font getFontParam(final String s, final String s2, final int n, final int n2) {
        String parameter = this.applet.getParameter(s + "Name");
        if (parameter == null) {
            parameter = s2;
        }
        String s3 = this.applet.getParameter(s + "Size");
        if (s3 == null) {
            s3 = Integer.toString(n2);
        }
        String s4 = this.applet.getParameter(s + "Style");
        if (s4 == null) {
            s4 = Integer.toString(n);
        }
        return new Font(parameter, new Integer(s4), new Integer(s3));
    }
    
    private static String convert(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        int index = 0;
        while (true) {
            final int n = index;
            index = s.indexOf(92, n);
            if (index < 0) {
                index = length;
            }
            if (index - n >= 1) {
                sb.append(s.substring(n, index));
            }
            if (index >= length - 1) {
                break;
            }
            switch (s.charAt(++index)) {
                case '\\': {
                    sb.append('\\');
                    ++index;
                    continue;
                }
                case 't': {
                    sb.append('\t');
                    ++index;
                    continue;
                }
                case 'r': {
                    sb.append('\r');
                    ++index;
                    continue;
                }
                case 'n': {
                    sb.append('\n');
                    ++index;
                    continue;
                }
                case 'f': {
                    sb.append('\f');
                    ++index;
                    continue;
                }
                case 'u': {
                    try {
                        sb.append((char)(int)Integer.valueOf(s.substring(index + 1, index + 5), 16));
                    }
                    catch (Exception ex) {}
                    index += 5;
                    continue;
                }
            }
        }
        return sb.toString();
    }
    
    private void disposeChilds() {
        final Enumeration<NICMenu> elements = this.childs.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().windowClosed(null);
        }
    }
    
    public Insets getInsets() {
        if (this.adv && this.getSize().height > 100) {
            return new Insets(0, 15, 0, 0);
        }
        if (this.adv) {
            return new Insets(15, 0, 0, 0);
        }
        return new Insets(0, 0, 0, 0);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= this.ax1 & x <= this.ax2 & y >= this.ay1 & y <= this.ay2 & !this.active) {
            this.active = true;
            this.repaint();
        }
        else if (!(x >= this.ax1 & x <= this.ax2 & y >= this.ay1 & y <= this.ay2) & this.active) {
            this.active = false;
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.active | mouseEvent.getSource() != this) {
            return;
        }
        this.active = false;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.active | !(mouseEvent.getSource() instanceof NICButton)) {
            return;
        }
        this.active = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            this.disposeChilds();
        }
        if (!this.active) {
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL("http://www.sampo.ru/~kryshen/nicmenu.html"), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        if (windowEvent != null && windowEvent.getSource() != this.parent.window) {
            return;
        }
        if (this.window == null | this.parent == null) {
            return;
        }
        this.parent.childs.removeElement(this);
        this.window.dispose();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this.window) {}
        this.window.dispose();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    static {
        NICMenu.aa = true;
    }
}
