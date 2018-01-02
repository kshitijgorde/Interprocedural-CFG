import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class WBackPanel extends WPanel
{
    private WBackPanel wparent;
    private WImage backImageSrc;
    private int backMode;
    private Dimension lastSize;
    private WImage backImage;
    private Color backColor;
    private boolean newBackground;
    private int imageID;
    private Image offScreen;
    private Graphics offGraph;
    public boolean doubleBuffering;
    public static final boolean JAVA2;
    
    public WBackPanel() {
        this.wparent = null;
        this.lastSize = null;
        this.backColor = null;
        this.newBackground = true;
        this.imageID = 0;
        this.offScreen = null;
        this.offGraph = null;
        this.doubleBuffering = false;
        this.backImage = null;
        this.backImageSrc = null;
        this.backMode = -1;
        this.setBackground(null);
    }
    
    public WBackPanel(final LayoutManager layout) {
        this();
        this.setLayout(layout);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.backImageSrc != null) {
            preferredSize.width = Math.max(preferredSize.width, this.backImageSrc.getWidth());
            preferredSize.height = Math.max(preferredSize.height, this.backImageSrc.getHeight());
        }
        return preferredSize;
    }
    
    public void addNotify() {
        final Container parent = this.getParent();
        if (parent instanceof WBackPanel) {
            this.wparent = (WBackPanel)parent;
        }
        else {
            this.wparent = null;
        }
        super.addNotify();
    }
    
    public final void removeNotify() {
        this.wparent = null;
        this.backImage = null;
        super.removeNotify();
    }
    
    public final Component getComponent(final String s) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (s.equals(components[i].getName())) {
                return components[i];
            }
        }
        return null;
    }
    
    public void paint(final Graphics offGraph) {
        try {
            final WImage backImage = this.getBackImage();
            final Rectangle clipBounds = offGraph.getClipBounds();
            int doubleBufferActived = this.doubleBufferActived() ? 1 : 0;
            Graphics offGraph2;
            if (doubleBufferActived != 0) {
                final Dimension size = this.getSize();
                if (this.offScreen == null) {
                    this.offScreen = this.createImage(size.width, size.height);
                    if (this.offScreen != null) {
                        if (this.offGraph != null) {
                            this.offGraph.dispose();
                        }
                        this.offGraph = this.offScreen.getGraphics();
                    }
                    else {
                        this.offGraph = offGraph;
                        doubleBufferActived = 0;
                    }
                }
                offGraph2 = this.offGraph;
                if (WBackPanel.JAVA2) {
                    offGraph2.setClip(clipBounds);
                }
            }
            else {
                offGraph2 = offGraph;
            }
            if (this.backImageSrc != null || this.wparent != null) {
                offGraph2.drawImage(backImage.toJavaImage(clipBounds), clipBounds.x, clipBounds.y, null);
            }
            else {
                offGraph2.setColor(this.getBackground());
                offGraph2.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            super.paint(offGraph2);
            if (doubleBufferActived != 0) {
                offGraph.drawImage(this.offScreen, 0, 0, null);
            }
            this.newBackground = false;
        }
        catch (InternalError internalError) {}
    }
    
    public final void setBgImage(final WImage backImageSrc, final int backMode) {
        this.backImageSrc = backImageSrc;
        this.backMode = backMode;
        this.lastSize = null;
    }
    
    private WImage getBackImage() {
        WImage backImage;
        if (this.backImageSrc == null && this.wparent != null) {
            this.newBackground = (this.imageID != this.wparent.imageID);
            backImage = this.wparent.getBackImage(this.getBounds());
            this.imageID = this.wparent.imageID;
        }
        else {
            final Dimension size = this.getSize();
            backImage = this.backImage;
            final Color background = this.getBackground();
            if (backImage == null || !size.equals(this.lastSize) || !background.equals(this.backColor)) {
                backImage = WImage.resize(this.backImageSrc, this.getSize(), this.backMode, backImage);
                ++this.imageID;
                this.backColor = background;
                backImage.fill(this.backColor.getRGB(), false, true, backImage);
                this.lastSize = size;
                this.offScreen = null;
                this.backImage = backImage;
                this.newBackground = true;
            }
        }
        return backImage;
    }
    
    public boolean newBackground() {
        return this.newBackground;
    }
    
    public final WImage getBackImage(final Rectangle rectangle) {
        WImage wImage;
        if (this.backImageSrc == null && this.wparent != null) {
            final Point location = this.getLocation();
            rectangle.translate(location.x, location.y);
            wImage = this.wparent.getBackImage(rectangle);
            rectangle.translate(-location.x, -location.y);
        }
        else {
            wImage = this.getBackImage().copy(rectangle, null);
        }
        return wImage;
    }
    
    public final boolean doubleBufferActived() {
        return this.doubleBuffering && (this.wparent == null || !this.wparent.doubleBufferActived());
    }
    
    static {
        boolean java2 = true;
        try {
            if (System.getProperty("java.version").startsWith("1.1")) {
                java2 = false;
            }
        }
        catch (SecurityException ex) {
            System.err.println(ex);
        }
        JAVA2 = java2;
    }
}
