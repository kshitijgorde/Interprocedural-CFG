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
    
    public WvImageButton(final WvDispatcher wvdispatcher, final Rectangle[] arectangle, final boolean flag) {
        this.isButton = true;
        this.isRaised = true;
        this.wvDispatcher = wvdispatcher;
        this.isButton = flag;
        this.image = new Image[arectangle.length];
        for (int i = 0; i < arectangle.length; ++i) {
            this.image[i] = wvdispatcher.getIconImage(arectangle[i]);
            this.width = Math.max(this.width, arectangle[i].width);
            this.height = Math.max(this.height, arectangle[i].height);
        }
        if (flag) {
            this.width += 4;
            this.height += 4;
        }
        this.size = new Dimension(this.width, this.height);
    }
    
    public WvImageButton(final WvDispatcher wvdispatcher, final Image image1, final boolean flag, final Dimension dimension) {
        this.isButton = true;
        this.isRaised = true;
        this.wvDispatcher = wvdispatcher;
        this.isButton = flag;
        (this.image = new Image[1])[0] = image1;
        if (dimension == null) {
            this.width = image1.getWidth(null);
            this.height = image1.getHeight(null);
        }
        else {
            this.width = dimension.width;
            this.height = dimension.height;
        }
        if (flag) {
            this.width += 4;
            this.height += 4;
        }
        this.size = new Dimension(this.width, this.height);
    }
    
    public void kickOff() {
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
    }
    
    public void paint(final Graphics g) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.width, this.height);
            if (this.backBuffer == null) {
                return;
            }
            this.backGC = this.backBuffer.getGraphics();
        }
        int i = (this.isRaised ? 0 : 1) * 2;
        i += (this.isEnabled() ? 0 : 1);
        if (i >= this.image.length) {
            i -= 2;
            if (i >= this.image.length) {
                i = 0;
            }
        }
        this.backGC.setColor(this.getBackground());
        this.backGC.fillRect(0, 0, this.width, this.height);
        if (this.isButton) {
            if (!this.isRaised) {
                this.backGC.translate(1, 1);
            }
            this.backGC.drawImage(this.image[i], 2, 2, this);
            if (!this.isRaised) {
                this.backGC.translate(-1, -1);
            }
            this.backGC.setColor(Color.lightGray);
            this.backGC.draw3DRect(0, 0, this.width - 1, this.height - 1, this.isRaised);
        }
        else {
            this.backGC.drawImage(this.image[i], 0, 0, this);
        }
        g.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public void disconnect(final int i) {
        this.disable();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void videoConnected(final boolean flag) {
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
    
    public void raised(final boolean flag) {
        this.isRaised = flag;
        this.repaint();
    }
    
    public void connect(final String s) {
        this.repaint();
    }
}
