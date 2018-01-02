// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Image;

public abstract class Item extends Widget
{
    String text;
    Image image;
    
    public Item(final Widget widget, final int n) {
        super(widget, n);
        this.text = "";
    }
    
    public Item(final Widget widget, final int n, final int n2) {
        this(widget, n);
    }
    
    protected void checkSubclass() {
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
        this.image = null;
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.image = image;
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        this.text = text;
    }
}
