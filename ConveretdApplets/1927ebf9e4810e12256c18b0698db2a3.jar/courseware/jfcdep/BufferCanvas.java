// 
// Decompiled by Procyon v0.5.30
// 

package courseware.jfcdep;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

public class BufferCanvas extends Component
{
    protected Image offscreen;
    protected Graphics offg;
    protected Dimension prefSize;
    private Dimension amSize;
    public static final float DEFAULT_CIRCLE_THICKNESS = 1.8f;
    public static final float DEFAULT_LINE_THICKNESS = 1.8f;
    
    public BufferCanvas(final int n, final int n2) {
        this.prefSize = new Dimension();
        this.amSize = new Dimension();
        this.prefSize = new Dimension(n, n2);
        this.amSize = new Dimension(-1, -1);
    }
    
    public BufferCanvas(final Dimension dimension) {
        this.prefSize = new Dimension();
        this.amSize = new Dimension();
        this.prefSize = new Dimension(dimension.width, dimension.height);
        this.amSize = new Dimension(-1, -1);
    }
    
    public BufferCanvas() {
        this(1, 1);
    }
    
    public void reset() {
        if (this.offscreen != null) {
            this.offscreen.flush();
            this.offscreen = null;
        }
        this.amSize.width = -1;
    }
    
    public synchronized Graphics getOffscreen() {
        final Dimension size = this.getSize();
        if (size.width < 1) {
            size.width = 1;
        }
        if (size.height < 1) {
            size.height = 1;
        }
        if (size.width != this.amSize.width || size.height != this.amSize.height) {
            if (this.offscreen != null) {
                this.offscreen.flush();
            }
            this.offscreen = this.createImage(size.width, size.height);
            if (this.offscreen != null) {
                this.amSize.width = size.width;
                this.amSize.height = size.height;
                this.offg = this.offscreen.getGraphics();
            }
        }
        return this.offg;
    }
    
    protected Graphics newOffscreen() {
        final Dimension size = this.getSize();
        if (this.offscreen != null) {
            this.offscreen.flush();
        }
        this.amSize.width = size.width;
        this.amSize.height = size.height;
        this.offscreen = this.createImage(this.amSize.width, this.amSize.height);
        return this.offg = this.offscreen.getGraphics();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.getOffscreen();
        if (this.offscreen != null) {
            graphics.drawImage(this.offscreen, 0, 0, this);
        }
    }
    
    public void onscreen() {
        if (this.offscreen != null) {
            this.getGraphics().drawImage(this.offscreen, 0, 0, this);
        }
    }
    
    public synchronized void setSize(final int n, final int n2) {
        this.setSize(new Dimension(n, n2));
    }
    
    public synchronized void setSize(final Dimension prefSize) {
        super.setSize(this.prefSize = prefSize);
        this.getOffscreen();
    }
    
    public synchronized void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.prefSize = new Dimension(n3, n4);
        super.setBounds(n, n2, n3, n4);
        this.getOffscreen();
    }
    
    public Dimension getPreferredSize() {
        return this.prefSize;
    }
    
    public void setPreferredSize(final Dimension prefSize) {
        this.prefSize = prefSize;
        this.amSize = new Dimension(-1, -1);
    }
    
    public Dimension getMinimumSize() {
        return this.prefSize;
    }
    
    public Dimension getCurrentSize() {
        return this.amSize;
    }
    
    public void finalize() {
        if (this.offscreen != null) {
            this.offscreen.flush();
        }
        this.offscreen = null;
        try {
            super.finalize();
        }
        catch (Throwable t) {}
    }
    
    public void drawCircle(final int n, final int n2, final int n3, final Color color) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        offscreen.drawOval(n, n2, n3, n3);
        offscreen.drawOval(n - 1, n2 - 1, n3 + 2, n3 + 2);
    }
    
    public void drawCircle(final float n, final float n2, final float n3, final float n4, final Color color) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        final int round = Math.round(n);
        final int round2 = Math.round(n2);
        final int round3 = Math.round(n3);
        offscreen.drawOval(round, round2, round3, round3);
        offscreen.drawOval(round - 1, round2 - 1, round3 + 2, round3 + 2);
    }
    
    public void fillCircle(final float n, final float n2, final float n3, final Color color) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        final int round = Math.round(n);
        final int round2 = Math.round(n2);
        final int round3 = Math.round(n3);
        offscreen.fillOval(round, round2, round3, round3);
    }
    
    public void fillCircle(final int n, final int n2, final int n3, final Color color) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        offscreen.fillOval(n, n2, n3, n3);
    }
    
    public void drawString(final Color color, final String s, final int n, final int n2, final boolean b) {
        this.drawString(color, s, n, n2);
    }
    
    public void drawString(final Color color, final String s, final int n, final int n2) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        offscreen.drawString(s, n, n2);
    }
    
    public void drawLine(final Color color, final int n, final int n2, final int n3, final int n4, final float n5) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        offscreen.drawLine(n, n2, n3, n4);
    }
    
    public void drawLine(final Color color, final int[] array, final int[] array2, final int n, final float n2) {
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(color);
        offscreen.drawPolyline(array, array2, n);
    }
}
