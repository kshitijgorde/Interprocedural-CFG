// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public final class FillLayout extends Layout
{
    public int type;
    public int marginWidth;
    public int marginHeight;
    public int spacing;
    
    public FillLayout() {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 0;
    }
    
    public FillLayout(final int type) {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 0;
        this.type = type;
    }
    
    protected Point computeSize(final Composite composite, final int n, final int n2, final boolean b) {
        final Control[] children = composite.getChildren();
        final int length = children.length;
        int max = 0;
        int max2 = 0;
        for (final Control control : children) {
            int max3 = n;
            int max4 = n2;
            if (length > 0) {
                if (this.type == 256 && n != -1) {
                    max3 = Math.max(0, (n - (length - 1) * this.spacing) / length);
                }
                if (this.type == 512 && n2 != -1) {
                    max4 = Math.max(0, (n2 - (length - 1) * this.spacing) / length);
                }
            }
            final Point computeChildSize = this.computeChildSize(control, max3, max4, b);
            max = Math.max(max, computeChildSize.x);
            max2 = Math.max(max2, computeChildSize.y);
        }
        int n3;
        int n4;
        if (this.type == 256) {
            n3 = length * max;
            if (length != 0) {
                n3 += (length - 1) * this.spacing;
            }
            n4 = max2;
        }
        else {
            n3 = max;
            n4 = length * max2;
            if (length != 0) {
                n4 += (length - 1) * this.spacing;
            }
        }
        int n5 = n3 + this.marginWidth * 2;
        int n6 = n4 + this.marginHeight * 2;
        if (n != -1) {
            n5 = n;
        }
        if (n2 != -1) {
            n6 = n2;
        }
        return new Point(n5, n6);
    }
    
    Point computeChildSize(final Control control, final int n, final int n2, final boolean b) {
        FillData layoutData = (FillData)control.getLayoutData();
        if (layoutData == null) {
            layoutData = new FillData();
            control.setLayoutData(layoutData);
        }
        Point point;
        if (n == -1 && n2 == -1) {
            point = layoutData.computeSize(control, n, n2, b);
        }
        else {
            if (control instanceof Scrollable) {
                final Rectangle computeTrim = ((Scrollable)control).computeTrim(0, 0, 0, 0);
                final int width = computeTrim.width;
                final int height = computeTrim.height;
            }
            else {
                final int width;
                final int height = width = control.getBorderWidth() * 2;
            }
            int width;
            int height;
            point = layoutData.computeSize(control, (n == -1) ? n : Math.max(0, n - width), (n2 == -1) ? n2 : Math.max(0, n2 - height), b);
        }
        return point;
    }
    
    protected boolean flushCache(final Control control) {
        final Object layoutData = control.getLayoutData();
        if (layoutData != null) {
            ((FillData)layoutData).flushCache();
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
    
    protected void layout(final Composite composite, final boolean b) {
        final Rectangle clientArea = composite.getClientArea();
        final Control[] children = composite.getChildren();
        final int length = children.length;
        if (length == 0) {
            return;
        }
        final int n = clientArea.width - this.marginWidth * 2;
        final int n2 = clientArea.height - this.marginHeight * 2;
        if (this.type == 256) {
            final int n3 = n - (length - 1) * this.spacing;
            int n4 = clientArea.x + this.marginWidth;
            final int n5 = n3 % length;
            final int n6 = clientArea.y + this.marginHeight;
            final int n7 = n3 / length;
            for (int i = 0; i < length; ++i) {
                final Control control = children[i];
                int n8 = n7;
                if (i == 0) {
                    n8 += n5 / 2;
                }
                else if (i == length - 1) {
                    n8 += (n5 + 1) / 2;
                }
                control.setBounds(n4, n6, n8, n2);
                n4 += n8 + this.spacing;
            }
        }
        else {
            final int n9 = n2 - (length - 1) * this.spacing;
            final int n10 = clientArea.x + this.marginWidth;
            final int n11 = n9 / length;
            int n12 = clientArea.y + this.marginHeight;
            final int n13 = n9 % length;
            for (int j = 0; j < length; ++j) {
                final Control control2 = children[j];
                int n14 = n11;
                if (j == 0) {
                    n14 += n13 / 2;
                }
                else if (j == length - 1) {
                    n14 += (n13 + 1) / 2;
                }
                control2.setBounds(n10, n12, n, n14);
                n12 += n14 + this.spacing;
            }
        }
    }
    
    public String toString() {
        String s = String.valueOf(new StringBuffer(String.valueOf(this.getName())).append(" {").toString()) + "type=" + ((this.type == 512) ? "SWT.VERTICAL" : "SWT.HORIZONTAL") + " ";
        if (this.marginWidth != 0) {
            s = String.valueOf(s) + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            s = String.valueOf(s) + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.spacing != 0) {
            s = String.valueOf(s) + "spacing=" + this.spacing + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
