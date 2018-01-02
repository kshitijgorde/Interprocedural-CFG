// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.awt.Graphics;
import powersoft.powerj.event.PaintEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Image;
import powersoft.powerj.event.PaintListener;

public class PictureBox extends PaintCanvas implements PaintListener
{
    static final long serialVersionUID = 8270980350315491978L;
    public static final int DEFAULT = 0;
    public static final int CENTER = 1;
    protected Image _image;
    protected int _imagePosition;
    protected Insets _insets;
    protected boolean _scaleImage;
    protected boolean _autoSize;
    
    public PictureBox() {
        this._image = null;
        this._insets = new Insets(0, 0, 0, 0);
        this.setEraseBackground(true);
        this.addPaintListener(this);
    }
    
    public boolean getAutoSize() {
        return this._autoSize;
    }
    
    public void setAutoSize(final boolean autoSize) {
        if (this._autoSize != autoSize && (this._autoSize = autoSize)) {
            this.autoSize();
        }
    }
    
    public boolean getScaleImage() {
        return this._scaleImage;
    }
    
    public synchronized void setScaleImage(final boolean scale) {
        if (this._scaleImage != scale) {
            this._scaleImage = scale;
            this.repaint();
        }
    }
    
    public Image getImage() {
        return this._image;
    }
    
    public synchronized void setImage(final Image image) {
        this._image = image;
        if (image != null) {
            UiUtil.waitForImage(this, image);
        }
        if (this._autoSize) {
            this.autoSize();
        }
        else {
            this.repaint();
        }
    }
    
    public void setImage(final String filename) {
        final Image image = this.getToolkit().getImage(filename);
        this.setImage(image);
    }
    
    public void setImage(final URL url) {
        final Image image = this.getToolkit().getImage(url);
        this.setImage(image);
    }
    
    public int getImagePosition() {
        return this._imagePosition;
    }
    
    public synchronized void setImagePosition(final int position) {
        if (this._imagePosition != position) {
            this._imagePosition = position;
            this.repaint();
        }
    }
    
    public Insets getInsets() {
        return this._insets;
    }
    
    public synchronized void setInsets(final Insets insets) {
        if (insets == null) {
            this._insets = new Insets(0, 0, 0, 0);
        }
        else {
            this._insets = insets;
        }
        this.repaint();
    }
    
    public synchronized boolean autoSize() {
        boolean ok = false;
        if (this._image != null) {
            this.setSize(this._image.getWidth(this), this._image.getHeight(this));
            ok = true;
        }
        return ok;
    }
    
    public synchronized void paint(final PaintEvent data) {
        if (this._image == null) {
            return;
        }
        final Graphics g = data.getGraphics();
        final int imageWidth = this._image.getWidth(this);
        final int imageHeight = this._image.getHeight(this);
        int x = 0;
        final int y;
        switch (this._imagePosition) {
            case 1: {
                x = this.getBounds().width / 2 - imageWidth / 2;
                y = this.getBounds().height / 2 - imageHeight / 2;
                break;
            }
            default: {
                x = 0;
                break;
            }
        }
        if (this._scaleImage) {
            g.drawImage(this._image, x, y, this.getBounds().width, this.getBounds().height, this);
        }
        else {
            g.drawImage(this._image, x, y, this);
        }
    }
}
