// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Vector;

public class MultiImgButton extends ImgButton
{
    private Vector images;
    private Vector filteredImages;
    private int curImage;
    private int curFilter;
    private boolean bDoFilter;
    
    public MultiImgButton(final Image image, final Image image2, final Image image3) {
        super(image, image2);
        this.images = new Vector();
        this.filteredImages = new Vector();
        this.curImage = 0;
        this.curFilter = 0;
        this.bDoFilter = false;
        if (image3 != null) {
            this.images.addElement(image3);
            this.resize(image3.getWidth(this), image3.getHeight(this));
        }
        else {
            System.out.println("no Image provided to MultiImgButton constructor");
        }
    }
    
    public MultiImgButton(final Image image, final Image image2, final Vector images) {
        super(image, image2);
        this.images = new Vector();
        this.filteredImages = new Vector();
        this.curImage = 0;
        this.curFilter = 0;
        this.bDoFilter = false;
        this.images = images;
        if (this.images.size() > 0) {
            final Image image3 = this.images.elementAt(0);
            this.resize(image3.getWidth(this), image3.getHeight(this));
        }
    }
    
    public MultiImgButton(final Image image, final Image image2, final Image image3, final int filtered) {
        this(image, image2, image3);
        this.setFiltered(filtered);
    }
    
    public MultiImgButton(final Image image, final Image image2, final Vector vector, final int filtered) {
        this(image, image2, vector);
        this.setFiltered(filtered);
    }
    
    public void setFiltered(final int curFilter) {
        if ((this.filteredImages.isEmpty() && curFilter != 0) || this.curFilter != curFilter) {
            for (int i = 0; i < this.images.size(); ++i) {
                this.filteredImages.insertElementAt(this.filterImage((Image)this.images.elementAt(i), curFilter), i);
            }
            this.curFilter = curFilter;
        }
    }
    
    public int getFiltered() {
        return this.curFilter;
    }
    
    public void doFiltered(final boolean bDoFilter) {
        this.bDoFilter = bDoFilter;
    }
    
    public boolean doFiltered() {
        return this.bDoFilter;
    }
    
    public void setCurImage(final int curImage) {
        if (curImage < this.images.size() && this.curImage != curImage) {
            this.curImage = curImage;
            this.repaint();
        }
    }
    
    public int getCurImage() {
        return this.curImage;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        Image image;
        if (this.bDoFilter && this.curFilter != 0) {
            image = this.filteredImages.elementAt(this.curImage);
        }
        else {
            image = this.images.elementAt(this.curImage);
        }
        if (image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        boolean b = false;
        final int size = this.images.size();
        if (size > 1) {
            this.curImage = (this.curImage + 1) % size;
            b = true;
            this.showToolTip(this.curImage, true);
            this.repaint();
            this.getParent().postEvent(new Event(this, 1001, null));
            super.isArmed = false;
        }
        else {
            super.mouseUp(event, n, n2);
        }
        return b;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.images.size() > 1) {
            super.isOver = true;
            this.showToolTip(this.curImage, true);
            this.repaint();
        }
        else {
            super.mouseEnter(event, n, n2);
        }
        return false;
    }
}
