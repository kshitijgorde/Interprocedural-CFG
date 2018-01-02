// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class VImage extends VObject
{
    protected String name;
    protected URL url;
    protected byte[] imagedata;
    protected transient Image image;
    
    public void finalize() {
        this.url = null;
        this.imagedata = null;
        if (this.image != null) {
            this.image.flush();
        }
        this.image = null;
    }
    
    public void paint(final Graphics graphics, final Point point) {
        if (this.image == null) {
            return;
        }
        graphics.drawImage(this.image, point.x, point.y, null);
    }
    
    public void paint(final Graphics graphics, final Point point, final Dimension dimension) {
        if (this.image == null) {
            return;
        }
        graphics.drawImage(this.image, point.x, point.y, dimension.width, dimension.height, null);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(super.width, super.height);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getSize() {
        return new Dimension(super.width, super.height);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public String getName() {
        return this.name;
    }
    
    public URL getURL() {
        return this.url;
    }
    
    public byte[] getImageData() {
        return this.imagedata;
    }
    
    protected void loadImage(final Image image) {
        this.loadImage(image, true);
    }
    
    protected void loadImage(final Image image, final boolean b) {
        if (image == null) {
            throw new NullPointerException("LoadImage(): null image");
        }
        this.image = image;
        if (b) {
            this.waitForImage();
        }
        else {
            super.width = this.image.getWidth(null);
            super.height = this.image.getHeight(null);
        }
    }
    
    protected void waitForImage() {
        if (this == null) {
            throw null;
        }
        final MediaTracker mediaTracker = new MediaTracker(new ComponentDummy());
        mediaTracker.addImage(this.image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0)) {
            final boolean b = true;
            super.height = (b ? 1 : 0);
            super.width = (b ? 1 : 0);
        }
        else {
            super.width = this.image.getWidth(null);
            super.height = this.image.getHeight(null);
        }
    }
    
    public VImage(final Image image) {
        this(image, true);
    }
    
    public VImage(final Image image, final boolean b) {
        super(1, 1);
        this.loadImage(image, b);
    }
    
    public VImage(final Image image, final URL url) {
        this(image);
        if (image != null) {
            this.url = url;
        }
    }
    
    public VImage(final String name) {
        this(Toolkit.getDefaultToolkit().getImage(name));
        if (this.image != null) {
            this.name = name;
        }
    }
    
    public VImage(final URL url) {
        this(Toolkit.getDefaultToolkit().getImage(url), url);
    }
    
    public VImage(final byte[] imagedata) {
        this(Toolkit.getDefaultToolkit().createImage(imagedata));
        if (this.image != null) {
            this.imagedata = imagedata;
        }
    }
    
    class ComponentDummy extends Component
    {
    }
}
