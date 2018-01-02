// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;

public class CTabItem extends Item
{
    CTabFolder parent;
    int x;
    int y;
    int width;
    int height;
    Control control;
    String toolTipText;
    String shortenedText;
    int shortenedTextWidth;
    Font font;
    Image disabledImage;
    Rectangle closeRect;
    int closeImageState;
    int state;
    boolean showClose;
    boolean showing;
    
    public CTabItem(final CTabFolder cTabFolder, final int n) {
        this(cTabFolder, n, cTabFolder.getItemCount());
    }
    
    public CTabItem(final CTabFolder cTabFolder, final int n, final int n2) {
        super(cTabFolder, n);
        this.height = 0;
        this.closeRect = new Rectangle(0, 0, 0, 0);
        this.closeImageState = 8;
        this.state = 0;
        this.showClose = false;
        this.showing = false;
        this.showClose = ((n & 0x40) != 0x0);
        cTabFolder.createItem(this, n2);
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        this.parent.destroyItem(this);
        super.dispose();
        this.parent = null;
        this.control = null;
        this.toolTipText = null;
        this.shortenedText = null;
        this.font = null;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public Image getDisabledImage() {
        this.checkWidget();
        return this.disabledImage;
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font != null) {
            return this.font;
        }
        return this.parent.getFont();
    }
    
    public CTabFolder getParent() {
        return this.parent;
    }
    
    public boolean getShowClose() {
        this.checkWidget();
        return this.showClose;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        if (this.toolTipText == null && this.shortenedText != null) {
            final String text = this.getText();
            if (!this.shortenedText.equals(text)) {
                return text;
            }
        }
        return this.toolTipText;
    }
    
    public boolean isShowing() {
        this.checkWidget();
        return this.showing;
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                SWT.error(5);
            }
            if (control.getParent() != this.parent) {
                SWT.error(32);
            }
        }
        if (this.control != null && !this.control.isDisposed()) {
            this.control.setVisible(false);
        }
        this.control = control;
        if (this.control != null) {
            if (this.parent.indexOf(this) == this.parent.getSelectionIndex()) {
                this.control.setBounds(this.parent.getClientArea());
                this.control.setVisible(true);
            }
            else {
                final int selectionIndex = this.parent.getSelectionIndex();
                Control control2 = null;
                if (selectionIndex != -1) {
                    control2 = this.parent.getItem(selectionIndex).getControl();
                }
                if (this.control != control2) {
                    this.control.setVisible(false);
                }
            }
        }
    }
    
    public void setDisabledImage(final Image disabledImage) {
        this.checkWidget();
        if (disabledImage != null && disabledImage.isDisposed()) {
            SWT.error(5);
        }
        this.disabledImage = disabledImage;
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        if (font == null && this.font == null) {
            return;
        }
        if (font != null && font.equals(this.font)) {
            return;
        }
        this.font = font;
        if (!this.parent.updateTabHeight(false)) {
            this.parent.updateItems();
            this.parent.redrawTabs();
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            SWT.error(5);
        }
        final Image image2 = this.getImage();
        if (image == null && image2 == null) {
            return;
        }
        if (image != null && image.equals(image2)) {
            return;
        }
        super.setImage(image);
        if (!this.parent.updateTabHeight(false)) {
            if (image2 != null && image != null) {
                final Rectangle bounds = image2.getBounds();
                final Rectangle bounds2 = image.getBounds();
                if (bounds2.width == bounds.width && bounds2.height == bounds.height) {
                    if (this.showing) {
                        final int index = this.parent.indexOf(this);
                        final boolean b = index == this.parent.selectedIndex;
                        if (b || this.parent.showUnselectedImage) {
                            final CTabFolderRenderer renderer = this.parent.renderer;
                            final Rectangle computeTrim = renderer.computeTrim(index, 0, 0, 0, 0, 0);
                            int n = this.x - computeTrim.x;
                            int n2;
                            if (b) {
                                final GC gc = new GC(this.parent);
                                if (this.parent.single && (this.parent.showClose || this.showClose)) {
                                    n += renderer.computeSize(-8, 0, gc, -1, -1).x;
                                }
                                final int min = Math.min(this.x + this.width, this.parent.getRightItemEdge(gc));
                                gc.dispose();
                                n2 = min - n - (computeTrim.width + computeTrim.x);
                                if (!this.parent.single && this.closeRect.width > 0) {
                                    n2 -= this.closeRect.width + 4;
                                }
                            }
                            else {
                                n2 = this.x + this.width - n - (computeTrim.width + computeTrim.x);
                                if (this.parent.showUnselectedClose && (this.parent.showClose || this.showClose)) {
                                    n2 -= this.closeRect.width + 4;
                                }
                            }
                            if (bounds2.width < n2) {
                                this.parent.redraw(n, this.y + (this.height - bounds2.height) / 2 + (this.parent.onBottom ? -1 : 1), bounds2.width, bounds2.height, false);
                            }
                        }
                    }
                    return;
                }
            }
            this.parent.updateItems();
            this.parent.redrawTabs();
        }
    }
    
    public void setShowClose(final boolean showClose) {
        this.checkWidget();
        if (this.showClose == showClose) {
            return;
        }
        this.showClose = showClose;
        this.parent.updateItems();
        this.parent.redrawTabs();
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            SWT.error(4);
        }
        if (text.equals(this.getText())) {
            return;
        }
        super.setText(text);
        this.shortenedText = null;
        this.shortenedTextWidth = 0;
        if (!this.parent.updateTabHeight(false)) {
            this.parent.updateItems();
            this.parent.redrawTabs();
        }
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
    }
}
