// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

public final class FormData
{
    public int width;
    public int height;
    public FormAttachment left;
    public FormAttachment right;
    public FormAttachment top;
    public FormAttachment bottom;
    int cacheWidth;
    int cacheHeight;
    int defaultWhint;
    int defaultHhint;
    int defaultWidth;
    int defaultHeight;
    int currentWhint;
    int currentHhint;
    int currentWidth;
    int currentHeight;
    FormAttachment cacheLeft;
    FormAttachment cacheRight;
    FormAttachment cacheTop;
    FormAttachment cacheBottom;
    boolean isVisited;
    boolean needed;
    
    public FormData() {
        this.width = -1;
        this.height = -1;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    public FormData(final int width, final int height) {
        this.width = -1;
        this.height = -1;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.width = width;
        this.height = height;
    }
    
    void computeSize(final Control control, final int n, final int n2, final boolean b) {
        if (this.cacheWidth != -1 && this.cacheHeight != -1) {
            return;
        }
        if (n == this.width && n2 == this.height) {
            if (this.defaultWidth == -1 || this.defaultHeight == -1 || n != this.defaultWhint || n2 != this.defaultHhint) {
                final Point computeSize = control.computeSize(n, n2, b);
                this.defaultWhint = n;
                this.defaultHhint = n2;
                this.defaultWidth = computeSize.x;
                this.defaultHeight = computeSize.y;
            }
            this.cacheWidth = this.defaultWidth;
            this.cacheHeight = this.defaultHeight;
            return;
        }
        if (this.currentWidth == -1 || this.currentHeight == -1 || n != this.currentWhint || n2 != this.currentHhint) {
            final Point computeSize2 = control.computeSize(n, n2, b);
            this.currentWhint = n;
            this.currentHhint = n2;
            this.currentWidth = computeSize2.x;
            this.currentHeight = computeSize2.y;
        }
        this.cacheWidth = this.currentWidth;
        this.cacheHeight = this.currentHeight;
    }
    
    void flushCache() {
        final int n = -1;
        this.cacheHeight = n;
        this.cacheWidth = n;
        final int n2 = -1;
        this.defaultWidth = n2;
        this.defaultHeight = n2;
        final int n3 = -1;
        this.currentWidth = n3;
        this.currentHeight = n3;
    }
    
    int getWidth(final Control control, final boolean b) {
        this.needed = true;
        this.computeSize(control, this.width, this.height, b);
        return this.cacheWidth;
    }
    
    int getHeight(final Control control, final boolean b) {
        this.computeSize(control, this.width, this.height, b);
        return this.cacheHeight;
    }
    
    FormAttachment getBottomAttachment(final Control control, final int n, final boolean b) {
        if (this.cacheBottom != null) {
            return this.cacheBottom;
        }
        if (this.isVisited) {
            return this.cacheBottom = new FormAttachment(0, this.getHeight(control, b));
        }
        if (this.bottom == null) {
            if (this.top == null) {
                return this.cacheBottom = new FormAttachment(0, this.getHeight(control, b));
            }
            return this.cacheBottom = this.getTopAttachment(control, n, b).plus(this.getHeight(control, b));
        }
        else {
            Control control2 = this.bottom.control;
            if (control2 != null) {
                if (control2.isDisposed()) {
                    control2 = (this.bottom.control = null);
                }
                else if (control2.getParent() != control.getParent()) {
                    control2 = null;
                }
            }
            if (control2 == null) {
                return this.cacheBottom = this.bottom;
            }
            this.isVisited = true;
            final FormData formData = (FormData)control2.getLayoutData();
            final FormAttachment bottomAttachment = formData.getBottomAttachment(control2, n, b);
            switch (this.bottom.alignment) {
                case 1024: {
                    this.cacheBottom = bottomAttachment.plus(this.bottom.offset);
                    break;
                }
                case 16777216: {
                    this.cacheBottom = bottomAttachment.minus(bottomAttachment.minus(formData.getTopAttachment(control2, n, b)).minus(this.getHeight(control, b)).divide(2));
                    break;
                }
                default: {
                    this.cacheBottom = formData.getTopAttachment(control2, n, b).plus(this.bottom.offset - n);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheBottom;
        }
    }
    
    FormAttachment getLeftAttachment(final Control control, final int n, final boolean b) {
        if (this.cacheLeft != null) {
            return this.cacheLeft;
        }
        if (this.isVisited) {
            return this.cacheLeft = new FormAttachment(0, 0);
        }
        if (this.left == null) {
            if (this.right == null) {
                return this.cacheLeft = new FormAttachment(0, 0);
            }
            return this.cacheLeft = this.getRightAttachment(control, n, b).minus(this.getWidth(control, b));
        }
        else {
            Control control2 = this.left.control;
            if (control2 != null) {
                if (control2.isDisposed()) {
                    control2 = (this.left.control = null);
                }
                else if (control2.getParent() != control.getParent()) {
                    control2 = null;
                }
            }
            if (control2 == null) {
                return this.cacheLeft = this.left;
            }
            this.isVisited = true;
            final FormData formData = (FormData)control2.getLayoutData();
            final FormAttachment leftAttachment = formData.getLeftAttachment(control2, n, b);
            switch (this.left.alignment) {
                case 16384: {
                    this.cacheLeft = leftAttachment.plus(this.left.offset);
                    break;
                }
                case 16777216: {
                    this.cacheLeft = leftAttachment.plus(formData.getRightAttachment(control2, n, b).minus(leftAttachment).minus(this.getWidth(control, b)).divide(2));
                    break;
                }
                default: {
                    this.cacheLeft = formData.getRightAttachment(control2, n, b).plus(this.left.offset + n);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheLeft;
        }
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    FormAttachment getRightAttachment(final Control control, final int n, final boolean b) {
        if (this.cacheRight != null) {
            return this.cacheRight;
        }
        if (this.isVisited) {
            return this.cacheRight = new FormAttachment(0, this.getWidth(control, b));
        }
        if (this.right == null) {
            if (this.left == null) {
                return this.cacheRight = new FormAttachment(0, this.getWidth(control, b));
            }
            return this.cacheRight = this.getLeftAttachment(control, n, b).plus(this.getWidth(control, b));
        }
        else {
            Control control2 = this.right.control;
            if (control2 != null) {
                if (control2.isDisposed()) {
                    control2 = (this.right.control = null);
                }
                else if (control2.getParent() != control.getParent()) {
                    control2 = null;
                }
            }
            if (control2 == null) {
                return this.cacheRight = this.right;
            }
            this.isVisited = true;
            final FormData formData = (FormData)control2.getLayoutData();
            final FormAttachment rightAttachment = formData.getRightAttachment(control2, n, b);
            switch (this.right.alignment) {
                case 131072: {
                    this.cacheRight = rightAttachment.plus(this.right.offset);
                    break;
                }
                case 16777216: {
                    this.cacheRight = rightAttachment.minus(rightAttachment.minus(formData.getLeftAttachment(control2, n, b)).minus(this.getWidth(control, b)).divide(2));
                    break;
                }
                default: {
                    this.cacheRight = formData.getLeftAttachment(control2, n, b).plus(this.right.offset - n);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheRight;
        }
    }
    
    FormAttachment getTopAttachment(final Control control, final int n, final boolean b) {
        if (this.cacheTop != null) {
            return this.cacheTop;
        }
        if (this.isVisited) {
            return this.cacheTop = new FormAttachment(0, 0);
        }
        if (this.top == null) {
            if (this.bottom == null) {
                return this.cacheTop = new FormAttachment(0, 0);
            }
            return this.cacheTop = this.getBottomAttachment(control, n, b).minus(this.getHeight(control, b));
        }
        else {
            Control control2 = this.top.control;
            if (control2 != null) {
                if (control2.isDisposed()) {
                    control2 = (this.top.control = null);
                }
                else if (control2.getParent() != control.getParent()) {
                    control2 = null;
                }
            }
            if (control2 == null) {
                return this.cacheTop = this.top;
            }
            this.isVisited = true;
            final FormData formData = (FormData)control2.getLayoutData();
            final FormAttachment topAttachment = formData.getTopAttachment(control2, n, b);
            switch (this.top.alignment) {
                case 128: {
                    this.cacheTop = topAttachment.plus(this.top.offset);
                    break;
                }
                case 16777216: {
                    this.cacheTop = topAttachment.plus(formData.getBottomAttachment(control2, n, b).minus(topAttachment).minus(this.getHeight(control, b)).divide(2));
                    break;
                }
                default: {
                    this.cacheTop = formData.getBottomAttachment(control2, n, b).plus(this.top.offset + n);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheTop;
        }
    }
    
    public String toString() {
        String s = String.valueOf(this.getName()) + " {";
        if (this.width != -1) {
            s = String.valueOf(s) + "width=" + this.width + " ";
        }
        if (this.height != -1) {
            s = String.valueOf(s) + "height=" + this.height + " ";
        }
        if (this.left != null) {
            s = String.valueOf(s) + "left=" + this.left + " ";
        }
        if (this.right != null) {
            s = String.valueOf(s) + "right=" + this.right + " ";
        }
        if (this.top != null) {
            s = String.valueOf(s) + "top=" + this.top + " ";
        }
        if (this.bottom != null) {
            s = String.valueOf(s) + "bottom=" + this.bottom + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
