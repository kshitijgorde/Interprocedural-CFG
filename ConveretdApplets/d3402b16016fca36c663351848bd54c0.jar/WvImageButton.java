import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvImageButton extends Canvas implements WvEventListener
{
    private int width;
    private int height;
    private Dimension size;
    private boolean isButton;
    private Graphics backGC;
    private Image backBuffer;
    private Image[] image;
    protected WvDispatcher wvDispatcher;
    protected boolean isRaised;
    
    public WvImageButton(final WvDispatcher wvDispatcher, final Rectangle[] array, final boolean isButton) {
        this.isButton = true;
        this.isRaised = true;
        this.wvDispatcher = wvDispatcher;
        this.isButton = isButton;
        this.image = new Image[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.image[i] = wvDispatcher.getIconImage(array[i]);
            this.width = Math.max(this.width, array[i].width);
            this.height = Math.max(this.height, array[i].height);
        }
        if (isButton) {
            this.width += 4;
            this.height += 4;
        }
        this.size = new Dimension(this.width, this.height);
    }
    
    public WvImageButton(final WvDispatcher wvDispatcher, final Image image, final boolean isButton, final Dimension dimension) {
        this.isButton = true;
        this.isRaised = true;
        this.wvDispatcher = wvDispatcher;
        this.isButton = isButton;
        (this.image = new Image[1])[0] = image;
        if (dimension == null) {
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }
        else {
            this.width = dimension.width;
            this.height = dimension.height;
        }
        if (isButton) {
            this.width += 4;
            this.height += 4;
        }
        this.size = new Dimension(this.width, this.height);
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void cameraConnected(final boolean b) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.width, this.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        int n = (this.isRaised ? 0 : 1) * 2 + (this.isEnabled() ? 0 : 1);
        if (n >= this.image.length) {
            n -= 2;
            if (n >= this.image.length) {
                n = 0;
            }
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        if (this.isButton) {
            if (!this.isRaised) {
                this.backGC.translate(1, 1);
            }
            this.backGC.drawImage(this.image[n], 2, 2, this);
            if (!this.isRaised) {
                this.backGC.translate(-1, -1);
            }
            this.backGC.setColor(Color.lightGray);
            this.backGC.draw3DRect(0, 0, this.width - 1, this.height - 1, this.isRaised);
        }
        else {
            this.backGC.drawImage(this.image[n], 0, 0, this);
        }
        graphics.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public void disconnect(final int n) {
        this.disable();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void videoConnected(final boolean b) {
    }
    
    public void enable() {
        super.enable();
        this.repaint();
    }
    
    public void disable() {
        super.disable();
        this.repaint();
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    public void raised(final boolean isRaised) {
        this.isRaised = isRaised;
        this.repaint();
    }
    
    public void connect(final String s) {
        this.repaint();
    }
}
