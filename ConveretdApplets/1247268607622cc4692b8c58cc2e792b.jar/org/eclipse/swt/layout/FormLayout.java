// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public final class FormLayout extends Layout
{
    public int marginWidth;
    public int marginHeight;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    public int spacing;
    
    public FormLayout() {
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.spacing = 0;
    }
    
    int computeHeight(final Control control, final FormData formData, final boolean b) {
        final FormAttachment topAttachment = formData.getTopAttachment(control, this.spacing, b);
        final FormAttachment bottomAttachment = formData.getBottomAttachment(control, this.spacing, b);
        final FormAttachment minus = bottomAttachment.minus(topAttachment);
        if (minus.numerator != 0) {
            return minus.solveY(formData.getHeight(control, b));
        }
        if (bottomAttachment.numerator == 0) {
            return bottomAttachment.offset;
        }
        if (bottomAttachment.numerator == bottomAttachment.denominator) {
            return -topAttachment.offset;
        }
        if (bottomAttachment.offset <= 0) {
            return -topAttachment.offset * topAttachment.denominator / bottomAttachment.numerator;
        }
        return bottomAttachment.denominator * bottomAttachment.offset / (bottomAttachment.denominator - bottomAttachment.numerator);
    }
    
    protected Point computeSize(final Composite composite, final int x, final int y, final boolean b) {
        final Point layout = this.layout(composite, false, 0, 0, x, y, b);
        if (x != -1) {
            layout.x = x;
        }
        if (y != -1) {
            layout.y = y;
        }
        return layout;
    }
    
    protected boolean flushCache(final Control control) {
        final Object layoutData = control.getLayoutData();
        if (layoutData != null) {
            ((FormData)layoutData).flushCache();
        }
        return true;
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    int computeWidth(final Control control, final FormData formData, final boolean b) {
        final FormAttachment leftAttachment = formData.getLeftAttachment(control, this.spacing, b);
        final FormAttachment rightAttachment = formData.getRightAttachment(control, this.spacing, b);
        final FormAttachment minus = rightAttachment.minus(leftAttachment);
        if (minus.numerator != 0) {
            return minus.solveY(formData.getWidth(control, b));
        }
        if (rightAttachment.numerator == 0) {
            return rightAttachment.offset;
        }
        if (rightAttachment.numerator == rightAttachment.denominator) {
            return -leftAttachment.offset;
        }
        if (rightAttachment.offset <= 0) {
            return -leftAttachment.offset * leftAttachment.denominator / leftAttachment.numerator;
        }
        return rightAttachment.denominator * rightAttachment.offset / (rightAttachment.denominator - rightAttachment.numerator);
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final Rectangle clientArea = composite.getClientArea();
        this.layout(composite, true, clientArea.x + this.marginLeft + this.marginWidth, clientArea.y + this.marginTop + this.marginHeight, Math.max(0, clientArea.width - this.marginLeft - 2 * this.marginWidth - this.marginRight), Math.max(0, clientArea.height - this.marginTop - 2 * this.marginHeight - this.marginBottom), b);
    }
    
    Point layout(final Composite composite, final boolean b, final int n, final int n2, final int n3, final int n4, final boolean b2) {
        final Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; ++i) {
            final Control control = children[i];
            FormData formData = (FormData)control.getLayoutData();
            if (formData == null) {
                control.setLayoutData(formData = new FormData());
            }
            if (b2) {
                formData.flushCache();
            }
            final FormData formData2 = formData;
            final FormData formData3 = formData;
            final FormData formData4 = formData;
            final FormData formData5 = formData;
            final FormAttachment formAttachment = null;
            formData5.cacheBottom = formAttachment;
            formData4.cacheTop = formAttachment;
            formData3.cacheRight = formAttachment;
            formData2.cacheLeft = formAttachment;
        }
        boolean[] array = null;
        Rectangle[] array2 = null;
        int n5 = 0;
        int n6 = 0;
        for (int j = 0; j < children.length; ++j) {
            final Control control2 = children[j];
            final FormData formData6 = (FormData)control2.getLayoutData();
            if (n3 != -1) {
                formData6.needed = false;
                final FormAttachment leftAttachment = formData6.getLeftAttachment(control2, this.spacing, b2);
                final FormAttachment rightAttachment = formData6.getRightAttachment(control2, this.spacing, b2);
                final int solveX = leftAttachment.solveX(n3);
                final int solveX2 = rightAttachment.solveX(n3);
                if (formData6.height == -1 && !formData6.needed) {
                    int width;
                    if (control2 instanceof Scrollable) {
                        width = ((Scrollable)control2).computeTrim(0, 0, 0, 0).width;
                    }
                    else {
                        width = control2.getBorderWidth() * 2;
                    }
                    final FormData formData7 = formData6;
                    final FormData formData8 = formData6;
                    final int n7 = -1;
                    formData8.cacheHeight = n7;
                    formData7.cacheWidth = n7;
                    formData6.computeSize(control2, Math.max(0, solveX2 - solveX - width), formData6.height, b2);
                    if (array == null) {
                        array = new boolean[children.length];
                    }
                    array[j] = true;
                }
                n5 = Math.max(solveX2, n5);
                if (b) {
                    if (array2 == null) {
                        array2 = new Rectangle[children.length];
                    }
                    array2[j] = new Rectangle(0, 0, 0, 0);
                    array2[j].x = n + solveX;
                    array2[j].width = solveX2 - solveX;
                }
            }
            else {
                n5 = Math.max(this.computeWidth(control2, formData6, b2), n5);
            }
        }
        for (int k = 0; k < children.length; ++k) {
            final Control control3 = children[k];
            final FormData formData9 = (FormData)control3.getLayoutData();
            if (n4 != -1) {
                final int solveX3 = formData9.getTopAttachment(control3, this.spacing, b2).solveX(n4);
                final int solveX4 = formData9.getBottomAttachment(control3, this.spacing, b2).solveX(n4);
                n6 = Math.max(solveX4, n6);
                if (b) {
                    array2[k].y = n2 + solveX3;
                    array2[k].height = solveX4 - solveX3;
                }
            }
            else {
                n6 = Math.max(this.computeHeight(control3, formData9, b2), n6);
            }
        }
        for (int l = 0; l < children.length; ++l) {
            final FormData formData10 = (FormData)children[l].getLayoutData();
            if (array != null && array[l]) {
                final FormData formData11 = formData10;
                final FormData formData12 = formData10;
                final int n8 = -1;
                formData12.cacheHeight = n8;
                formData11.cacheWidth = n8;
            }
            final FormData formData13 = formData10;
            final FormData formData14 = formData10;
            final FormData formData15 = formData10;
            final FormData formData16 = formData10;
            final FormAttachment formAttachment2 = null;
            formData16.cacheBottom = formAttachment2;
            formData15.cacheTop = formAttachment2;
            formData14.cacheRight = formAttachment2;
            formData13.cacheLeft = formAttachment2;
        }
        if (b) {
            for (int n9 = 0; n9 < children.length; ++n9) {
                children[n9].setBounds(array2[n9]);
            }
        }
        return new Point(n5 + (this.marginLeft + this.marginWidth * 2 + this.marginRight), n6 + (this.marginTop + this.marginHeight * 2 + this.marginBottom));
    }
    
    public String toString() {
        String s = String.valueOf(this.getName()) + " {";
        if (this.marginWidth != 0) {
            s = String.valueOf(s) + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            s = String.valueOf(s) + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            s = String.valueOf(s) + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginRight != 0) {
            s = String.valueOf(s) + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginTop != 0) {
            s = String.valueOf(s) + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginBottom != 0) {
            s = String.valueOf(s) + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.spacing != 0) {
            s = String.valueOf(s) + "spacing=" + this.spacing + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
