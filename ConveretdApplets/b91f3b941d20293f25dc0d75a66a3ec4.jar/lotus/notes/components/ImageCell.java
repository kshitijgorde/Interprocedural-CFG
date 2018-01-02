// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import lotus.notes.util.Bidi;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Point;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;

public class ImageCell extends CellEntry
{
    private Image State1Image;
    private Image State2Image;
    private Image[] imageList;
    boolean bToggle;
    public boolean isCustomIcon;
    public boolean isCustomTwistie;
    public int twistChunkWidth;
    private Vector listeners;
    static int DEFAULT_IMAGE_WIDTH;
    static int DEFAULT_IMAGE_HEIGHT;
    
    public ImageCell() {
        this.isCustomIcon = false;
        this.isCustomTwistie = false;
        this.twistChunkWidth = 0;
        this.listeners = new Vector();
        this.State1Image = null;
        this.State2Image = null;
        this.bToggle = true;
        super.bExtentsValid = false;
    }
    
    public ImageCell(final Image state1Image) {
        this.isCustomIcon = false;
        this.isCustomTwistie = false;
        this.twistChunkWidth = 0;
        this.listeners = new Vector();
        this.State1Image = state1Image;
        this.State2Image = null;
        this.bToggle = true;
        super.bExtentsValid = false;
    }
    
    public ImageCell(final Image[] imageList) {
        this.isCustomIcon = false;
        this.isCustomTwistie = false;
        this.twistChunkWidth = 0;
        this.listeners = new Vector();
        this.imageList = imageList;
        this.State1Image = imageList[0];
        this.State2Image = null;
        this.bToggle = true;
        super.bExtentsValid = false;
    }
    
    public ImageCell(final Image state1Image, final Image state2Image, final boolean bToggle) {
        this.isCustomIcon = false;
        this.isCustomTwistie = false;
        this.twistChunkWidth = 0;
        this.listeners = new Vector();
        this.State1Image = state1Image;
        this.State2Image = state2Image;
        this.bToggle = bToggle;
        super.bExtentsValid = false;
    }
    
    public void HideImage() {
        this.bToggle = false;
        this.NotifyListeners();
    }
    
    public void ShowImage() {
        this.bToggle = true;
        this.NotifyListeners();
    }
    
    public void Paint(final Graphics graphics, final Point point, final Component component) {
        Image image = null;
        if (this.bToggle && this.State1Image != null) {
            image = this.State1Image;
        }
        else if (!this.bToggle && this.State2Image != null) {
            image = this.State2Image;
        }
        int height = 0;
        final Dimension getLineBounds = super.Line.GetLineBounds();
        if (image != null) {
            height = image.getHeight(component);
        }
        final int n = point.x + super.ColInfo.GetxPos() + super.padding;
        int getyOffset = super.ColInfo.GetyOffset();
        final int vAlignment = super.ColInfo.vAlignment;
        final ColDesc colInfo = super.ColInfo;
        if (vAlignment == 4 && height < getLineBounds.height) {
            getyOffset += getLineBounds.height - height;
        }
        final int vAlignment2 = super.ColInfo.vAlignment;
        final ColDesc colInfo2 = super.ColInfo;
        if (vAlignment2 == 2 && height < getLineBounds.height) {
            getyOffset = (getLineBounds.height - height + 1) / 2;
        }
        if (super.vAlignment == 2 && height < getLineBounds.height) {
            getyOffset = height / 2 - getLineBounds.height / 2;
        }
        int n2 = CellEntry.handleHAlignment(super.ColInfo, n, super.ClipExtents.width, component.size().width);
        if (super.direction == 1) {
            n2 = Bidi.toggleHorzPos(n2, super.ClipExtents.width, graphics.getClipRect().width);
        }
        final Rectangle rectangle = new Rectangle(n2, point.y, super.ClipExtents.width, getLineBounds.height);
        if (super.bgColor != null) {
            final Color color = graphics.getColor();
            graphics.setColor(super.bgColor);
            int n3 = CellEntry.handleHAlignment(super.ColInfo, n, super.ClipExtents.width, graphics.getClipRect().width);
            if (super.direction == 1) {
                n3 = Bidi.toggleHorzPos(n3, super.ClipExtents.width - 1, graphics.getClipRect().width);
            }
            graphics.fillRect(n3, point.y, super.ClipExtents.width - 1, getLineBounds.height);
            graphics.setColor(color);
            if (image == null) {
                return;
            }
        }
        Graphics graphics2;
        if (super.ColInfo.bClip) {
            graphics2 = graphics.create(rectangle.x, rectangle.y, super.ClipExtents.width, rectangle.height);
        }
        else {
            graphics2 = graphics.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if (image != null && null != graphics2) {
            final int n4 = (super.direction == 1) ? Bidi.toggleHorzPos(0, image.getWidth(component), rectangle.width) : 0;
            if (this.isCustomTwistie && this.twistChunkWidth > 0) {
                this.drawCustTwist(graphics2, image, n4, getyOffset, component);
            }
            else if (height <= getLineBounds.height || this.isCustomIcon) {
                this.drawIcon(graphics2, image, n4, getyOffset, component);
            }
            else {
                graphics2.drawImage(image, n4, getyOffset, -1, getLineBounds.height, component);
            }
        }
        graphics2.dispose();
    }
    
    private void drawCustTwist(final Graphics graphics, final Image image, final int n, final int n2, final Component component) {
        final int height = image.getHeight(component);
        if (!this.bToggle) {
            graphics.drawImage(image, n, n2, this.twistChunkWidth, n2 + height, 0, 0, this.twistChunkWidth, height, component);
        }
        else {
            graphics.drawImage(image, n, n2, this.twistChunkWidth, n2 + height, this.twistChunkWidth + 1, 0, this.twistChunkWidth * 2 + 1, height, component);
        }
    }
    
    private void drawIcon(final Graphics graphics, final Image image, final int n, final int n2, final Component component) {
        if (this.imageList != null) {
            int n3 = n;
            for (int i = 0; i <= this.imageList.length - 1; ++i) {
                final int width = this.imageList[i].getWidth(component);
                graphics.drawImage(this.imageList[i], n3, 0, null);
                n3 += width;
            }
        }
        else {
            graphics.drawImage(image, n, n2, null);
        }
    }
    
    protected void UpdateExtents(final Graphics graphics, final Component component) {
        Image image = null;
        if (this.bToggle && this.State1Image != null) {
            image = this.State1Image;
        }
        else if (!this.bToggle && this.State2Image != null) {
            image = this.State2Image;
        }
        if (image != null && !this.isCustomIcon) {
            super.MaxExtents.width = image.getWidth(component);
            super.MaxExtents.height = image.getHeight(component);
        }
        else {
            super.MaxExtents.width = ImageCell.DEFAULT_IMAGE_WIDTH;
            super.MaxExtents.height = ImageCell.DEFAULT_IMAGE_HEIGHT;
        }
        if (super.ColInfo.bClip) {
            super.ClipExtents.width = super.ColInfo.GetWidth();
        }
        else {
            super.ClipExtents.width = super.MaxExtents.width;
        }
        super.ClipExtents.height = super.MaxExtents.height;
        super.bExtentsValid = true;
    }
    
    public boolean MouseDown(final Event event) {
        if (super.bHandleMouseEvents) {
            this.bToggle = !this.bToggle;
            this.NotifyListeners();
            return true;
        }
        this.NotifyListeners();
        return false;
    }
    
    public boolean IsSelected() {
        return this.bToggle;
    }
    
    public void SetSelected(final boolean bToggle) {
        this.bToggle = bToggle;
    }
    
    private void NotifyListeners() {
        final StateChangeEvent stateChangeEvent = new StateChangeEvent(this);
        final Vector vector;
        synchronized (this) {
            vector = (Vector)this.listeners.clone();
        }
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).stateChanged(stateChangeEvent);
        }
    }
    
    public synchronized void addStateChangeListener(final StateChangeListener stateChangeListener) {
        this.listeners.addElement(stateChangeListener);
    }
    
    public synchronized void removeStateChangeListener(final StateChangeListener stateChangeListener) {
        this.listeners.removeElement(stateChangeListener);
    }
    
    public void setImage(final Image state1Image) {
        this.State1Image = state1Image;
        this.bToggle = true;
    }
    
    public void setImages(final Image state1Image, final Image state2Image) {
        this.State1Image = state1Image;
        this.State2Image = state2Image;
    }
    
    public Image getImage(final int n) {
        if (n <= 1) {
            return (n == 0) ? this.State1Image : this.State2Image;
        }
        return this.State1Image;
    }
    
    static {
        ImageCell.DEFAULT_IMAGE_WIDTH = 16;
        ImageCell.DEFAULT_IMAGE_HEIGHT = 16;
    }
}
