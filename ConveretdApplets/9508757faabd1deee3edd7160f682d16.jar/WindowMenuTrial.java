import java.awt.FontMetrics;
import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WindowMenuTrial extends Applet
{
    static final int BORDER_WIDTH = 5;
    static final int BORDER_HEIGHT = 5;
    private IButtonTrial[] button;
    private String target;
    private URL[] url;
    private int nIcon;
    private int titleBarHeight;
    
    public void init() {
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        this.target = this.getParameter("target");
        final boolean borderRaised = this.isBorderRaised();
        this.setTopWindow(borderRaised);
        final Rectangle bounds = this.bounds();
        this.setBorder(new String("West"), 0, 5, bounds.height, borderRaised);
        this.setBorder(new String("East"), 2, 5, bounds.height, borderRaised);
        this.setBorder(new String("South"), 0, bounds.width, 5, borderRaised);
        this.nIcon = Integer.parseInt(this.getParameter("nIcon"));
        this.setButtons(bounds);
    }
    
    private boolean isBorderRaised() {
        final String parameter = this.getParameter("borderRaised");
        return parameter == null || parameter.compareTo("false") != 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.nIcon; ++i) {
            if (event.target == this.button[i]) {
                if (this.target != null) {
                    this.getAppletContext().showDocument(this.url[i], this.target);
                }
                else {
                    this.getAppletContext().showDocument(this.url[i]);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Free applets at www.consultcom.com");
        return true;
    }
    
    private void setBorder(final String s, final int n, final int n2, final int n3, final boolean b) {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(n, 0, 0));
        panel.add(new BorderTrial(n2, n3, b));
        this.add(s, panel);
    }
    
    private void setButtons(final Rectangle rectangle) {
        this.button = new IButtonTrial[this.nIcon];
        this.url = new URL[this.nIcon];
        final int int1 = Integer.parseInt(this.getParameter("row"));
        final int int2 = Integer.parseInt(this.getParameter("col"));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(int1, int2, 0, 0));
        final String parameter = this.getParameter("titleBar");
        int n;
        int n2;
        if (parameter != null && parameter.compareTo("false") == 0) {
            n = (rectangle.width - 10) / int2;
            n2 = (rectangle.height - 10) / int1;
        }
        else {
            n = (rectangle.width - 10) / int2;
            n2 = (rectangle.height - (this.titleBarHeight + 5)) / int1;
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[this.nIcon];
        for (int i = 0; i < this.nIcon; ++i) {
            try {
                this.url[i] = new URL(this.getCodeBase(), this.getParameter("link" + i));
            }
            catch (MalformedURLException ex) {
                System.out.println("MalformedURLException: " + ex.getMessage());
            }
            array[i] = this.getImage(this.getCodeBase(), this.getParameter("image" + i));
            final Font setIconFont = this.setIconFont();
            if (array[i] != null) {
                mediaTracker.addImage(array[i], i);
                this.button[i] = new IButtonTrial(this.getParameter("label" + i), setIconFont, array[i], n, n2);
            }
            else {
                this.button[i] = new IButtonTrial(this.getParameter("label" + i), setIconFont, n, n2);
            }
            panel.add(this.button[i]);
        }
        this.add("Center", panel);
        if (array != null) {
            for (int j = 0; j < this.nIcon; ++j) {
                this.showStatus("Please wait. Loading images..." + j + " of " + this.nIcon);
                try {
                    mediaTracker.waitForID(j);
                }
                catch (InterruptedException ex2) {
                    System.out.println("InterruptedException: " + ex2.getMessage());
                }
            }
        }
    }
    
    private Font setIconFont() {
        int int1;
        if (this.getParameter("iconFontSize") == null) {
            int1 = 13;
        }
        else {
            int1 = Integer.parseInt(this.getParameter("iconFontSize"));
        }
        return new Font("SansSerif", 0, int1);
    }
    
    private Font setTitleFont() {
        int int1;
        if (this.getParameter("titleFontSize") == null) {
            int1 = 13;
        }
        else {
            int1 = Integer.parseInt(this.getParameter("titleFontSize"));
        }
        return new Font("Dialog", 1, int1);
    }
    
    private void setTopWindow(final boolean b) {
        final Rectangle bounds = this.bounds();
        final Panel panel = new Panel();
        final String parameter = this.getParameter("titleBar");
        if (parameter != null && parameter.compareTo("false") == 0) {
            panel.setLayout(new FlowLayout(0, 0, 0));
            panel.add(new BorderTrial(bounds.width, 5, b));
        }
        else {
            panel.setLayout(new FlowLayout(0, 0, 0));
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Font setTitleFont = this.setTitleFont();
            final FontMetrics fontMetrics = this.getFontMetrics(setTitleFont);
            this.titleBarHeight = fontMetrics.getHeight() + fontMetrics.getAscent() + 1;
            final Image image = this.getImage(this.getCodeBase(), this.getParameter("logo"));
            if (image != null) {
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {
                    System.out.println("InterruptedException: " + ex.getMessage());
                }
                panel.add(new TitleBarTrial(this.getParameter("title"), setTitleFont, image, bounds.width, this.titleBarHeight));
            }
            else {
                panel.add(new TitleBarTrial(this.getParameter("title"), setTitleFont, bounds.width, this.titleBarHeight));
            }
        }
        this.add("North", panel);
    }
}
