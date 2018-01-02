// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Panel;
import java.applet.Applet;
import java.awt.Window;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Canvas;

public class TTComponent extends Canvas
{
    public static final String PK_TOOLTIP_TEXT = "tooltipText";
    protected static Toolkit DefaultToolkit;
    protected boolean doubleBuffered;
    protected Image offScreenBuffer;
    Hashtable clientProperties;
    Rectangle _bounds;
    protected Insets insets;
    private boolean isFilteredInstance;
    
    public final Insets getInsets() {
        return this.insets;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this._bounds.setBounds(n, n2, n3, n4);
    }
    
    public void setBounds(final Rectangle rectangle) {
        super.setBounds(rectangle);
        this._bounds.setBounds(rectangle);
    }
    
    public void setLocation(final int n, final int n2) {
        super.setLocation(n, n2);
        this._bounds.setLocation(n, n2);
    }
    
    public void setBounds(final Point point) {
        super.setLocation(point);
        this._bounds.setLocation(point);
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this._bounds.setSize(n, n2);
    }
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        this._bounds.setSize(dimension);
    }
    
    public final void setFiltered(final boolean isFilteredInstance) {
        this.isFilteredInstance = isFilteredInstance;
    }
    
    public final boolean isFiltered() {
        return this.isFilteredInstance;
    }
    
    public Container getTopLevelAncestor() {
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Window || container instanceof Applet) {
                return container;
            }
        }
        return null;
    }
    
    public Panel getTopLevelPanel() {
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Panel) {
                return (Panel)container;
            }
        }
        return null;
    }
    
    public Frame getTopLevelFrame() {
        for (Container container = this.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Frame) {
                return (Frame)container;
            }
        }
        return new Frame();
    }
    
    public int getWidth() {
        return this._bounds.width;
    }
    
    public int getHeight() {
        return this._bounds.height;
    }
    
    public int getX() {
        return this._bounds.x;
    }
    
    public int getY() {
        return this._bounds.y;
    }
    
    protected Object getClientProperty(final String s) {
        return this.clientProperties.get(s);
    }
    
    protected void putClientProperty(final String s, final Object o) {
        this.clientProperties.put(s, o);
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public boolean isDoubleBuffered() {
        return this.doubleBuffered;
    }
    
    public void setDoubleBuffered(final boolean doubleBuffered) {
        if (!(this.doubleBuffered = doubleBuffered)) {
            this.offScreenBuffer = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isShowing()) {
            return;
        }
        final Graphics preferredGraphics = this.getPreferredGraphics();
        if (preferredGraphics == null) {
            return;
        }
        this.paintOn(preferredGraphics);
        if (this.isFilteredInstance) {
            this.filterOffscreenSource();
        }
        if (preferredGraphics != graphics) {
            preferredGraphics.dispose();
        }
        this.syncGraphics(graphics);
    }
    
    protected synchronized void paintOn(final Graphics graphics) {
    }
    
    public void repaintNow() {
        if (!this.isShowing()) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        try {
            this.paint(graphics);
        }
        finally {
            graphics.dispose();
        }
    }
    
    public void syncGraphics(final Graphics graphics) {
        if (this.offScreenBuffer == null) {
            return;
        }
        graphics.drawImage(this.offScreenBuffer, 0, 0, null);
        TTComponent.DefaultToolkit.sync();
    }
    
    protected Graphics getPreferredGraphics() {
        return this.getPreferredGraphics(null);
    }
    
    protected Graphics getPreferredGraphics(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (size.width <= 0 || size.height <= 0) {
            return null;
        }
        if (!this.doubleBuffered) {
            Graphics graphics2;
            if (graphics != null) {
                graphics2 = graphics;
            }
            else {
                graphics2 = this.getGraphics();
            }
            graphics2.clipRect(0, 0, size.width, size.height);
            return graphics2;
        }
        Graphics graphics3 = null;
        Label_0238: {
            if (this.offScreenBuffer != null && this.offScreenBuffer.getWidth(null) == size.width) {
                if (this.offScreenBuffer.getHeight(null) == size.height) {
                    try {
                        graphics3 = this.offScreenBuffer.getGraphics();
                    }
                    catch (IllegalAccessError illegalAccessError) {
                        this.offScreenBuffer.flush();
                        this.offScreenBuffer = this.createImage(size.width, size.height);
                        graphics3 = this.offScreenBuffer.getGraphics();
                        graphics3.setColor(this.getBackground());
                        graphics3.fillRect(0, 0, size.width, size.height);
                    }
                    break Label_0238;
                }
            }
            try {
                this.offScreenBuffer = this.createImage(size.width, size.height);
            }
            catch (IllegalArgumentException ex) {
                this.offScreenBuffer = this.createImage(1, 1);
            }
            graphics3 = this.offScreenBuffer.getGraphics();
            graphics3.setColor(this.getBackground());
            graphics3.fillRect(0, 0, size.width, size.height);
        }
        Rectangle clipBounds = null;
        if (graphics != null) {
            clipBounds = graphics.getClipBounds();
        }
        if (clipBounds != null) {
            if (clipBounds.x < 0) {
                clipBounds.x = 0;
            }
            if (clipBounds.y < 0) {
                clipBounds.y = 0;
            }
            if (clipBounds.width > size.width) {
                clipBounds.width = size.width;
            }
            if (clipBounds.height > size.height) {
                clipBounds.height = size.height;
            }
            graphics3.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        else {
            graphics3.setClip(0, 0, size.width, size.height);
        }
        graphics3.setFont(this.getFont());
        graphics3.setColor(this.getForeground());
        return graphics3;
    }
    
    protected void filterOffscreenSource() {
    }
    
    public TTComponent() {
        this(new Insets(0, 0, 0, 0));
    }
    
    public TTComponent(final Insets insets) {
        this.doubleBuffered = true;
        this.offScreenBuffer = null;
        this.clientProperties = new Hashtable();
        this._bounds = new Rectangle();
        this.isFilteredInstance = false;
        this.insets = insets;
    }
    
    static {
        TTComponent.DefaultToolkit = Toolkit.getDefaultToolkit();
    }
}
