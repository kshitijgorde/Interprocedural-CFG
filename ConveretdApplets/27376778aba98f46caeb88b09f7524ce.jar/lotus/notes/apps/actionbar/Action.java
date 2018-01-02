// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.actionbar;

import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.PopupMenu;
import java.awt.Image;

public class Action
{
    String text;
    Image image;
    Image bgImage;
    boolean isImageWell;
    boolean iconOnRight;
    boolean rightAligned;
    int imagesHigh;
    int imagesWide;
    int bgImagesHigh;
    int bgImagesWide;
    int index;
    int xorigin;
    int width;
    float imageScaleFactor;
    boolean hasSubactions;
    boolean readingRTL;
    PopupMenu menu;
    Rectangle buttonRect;
    Component parent;
    
    public Action(final Component parent, final String text, final Image image, final Image bgImage, final int bgImagesHigh, final int bgImagesWide, final int index, final boolean iconOnRight, final boolean rightAligned, final int n, final int n2, final boolean readingRTL) {
        this.text = null;
        this.image = null;
        this.bgImage = null;
        this.isImageWell = false;
        this.iconOnRight = false;
        this.rightAligned = false;
        this.imagesHigh = 1;
        this.imagesWide = 1;
        this.bgImagesHigh = 1;
        this.bgImagesWide = 1;
        this.index = -1;
        this.xorigin = 0;
        this.width = 0;
        this.imageScaleFactor = 1.0f;
        this.hasSubactions = false;
        this.readingRTL = false;
        this.menu = null;
        this.buttonRect = null;
        this.parent = null;
        this.parent = parent;
        this.text = text;
        this.image = image;
        this.bgImage = bgImage;
        this.bgImagesHigh = bgImagesHigh;
        this.bgImagesWide = bgImagesWide;
        this.index = index;
        this.iconOnRight = iconOnRight;
        this.rightAligned = rightAligned;
        this.imagesHigh = ((n == 0) ? 1 : n);
        this.imagesWide = ((n2 == 0) ? 1 : n2);
        this.readingRTL = readingRTL;
        if (this.imagesWide > 1 || this.imagesHigh > 1) {
            this.isImageWell = true;
        }
        this.buttonRect = new Rectangle();
    }
    
    public int getImageHeight() {
        return (this.image != null) ? ((this.image.getHeight(this.parent) - (this.imagesHigh - 1)) / this.imagesHigh) : 0;
    }
    
    public int getImageWidth() {
        return (this.image != null) ? ((this.image.getWidth(this.parent) - (this.imagesWide - 1)) / this.imagesWide) : 0;
    }
    
    public int getBGImageWidth() {
        return (this.bgImage != null) ? ((this.bgImage.getWidth(this.parent) - (this.bgImagesWide - 1)) / this.bgImagesWide) : 0;
    }
    
    public int getBGImageHeight() {
        return (this.bgImage != null) ? ((this.bgImage.getHeight(this.parent) - (this.bgImagesHigh - 1)) / this.bgImagesHigh) : 0;
    }
    
    public String getText() {
        return this.text;
    }
}
