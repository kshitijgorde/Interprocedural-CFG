// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public final class RowLayout extends Layout
{
    public int type;
    public int marginWidth;
    public int marginHeight;
    public int spacing;
    public boolean wrap;
    public boolean pack;
    public boolean fill;
    public boolean center;
    public boolean justify;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    
    public RowLayout() {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 3;
        this.wrap = true;
        this.pack = true;
        this.fill = false;
        this.center = false;
        this.justify = false;
        this.marginLeft = 3;
        this.marginTop = 3;
        this.marginRight = 3;
        this.marginBottom = 3;
    }
    
    public RowLayout(final int type) {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 3;
        this.wrap = true;
        this.pack = true;
        this.fill = false;
        this.center = false;
        this.justify = false;
        this.marginLeft = 3;
        this.marginTop = 3;
        this.marginRight = 3;
        this.marginBottom = 3;
        this.type = type;
    }
    
    protected Point computeSize(final Composite composite, final int x, final int y, final boolean b) {
        Point point;
        if (this.type == 256) {
            point = this.layoutHorizontal(composite, false, x != -1 && this.wrap, x, b);
        }
        else {
            point = this.layoutVertical(composite, false, y != -1 && this.wrap, y, b);
        }
        if (x != -1) {
            point.x = x;
        }
        if (y != -1) {
            point.y = y;
        }
        return point;
    }
    
    Point computeSize(final Control control, final boolean b) {
        int width = -1;
        int height = -1;
        final RowData rowData = (RowData)control.getLayoutData();
        if (rowData != null) {
            width = rowData.width;
            height = rowData.height;
        }
        return control.computeSize(width, height, b);
    }
    
    protected boolean flushCache(final Control control) {
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
        if (this.type == 256) {
            this.layoutHorizontal(composite, true, this.wrap, clientArea.width, b);
        }
        else {
            this.layoutVertical(composite, true, this.wrap, clientArea.height, b);
        }
    }
    
    Point layoutHorizontal(final Composite composite, final boolean b, final boolean b2, final int n, final boolean b3) {
        final Control[] children = composite.getChildren();
        int n2 = 0;
        for (int i = 0; i < children.length; ++i) {
            final RowData rowData = (RowData)children[i].getLayoutData();
            if (rowData == null || !rowData.exclude) {
                children[n2++] = children[i];
            }
        }
        if (n2 == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        int n3 = 0;
        int n4 = 0;
        int max = 0;
        if (!this.pack) {
            for (int j = 0; j < n2; ++j) {
                final Point computeSize = this.computeSize(children[j], b3);
                n3 = Math.max(n3, computeSize.x);
                n4 = Math.max(n4, computeSize.y);
            }
            max = n4;
        }
        int x = 0;
        int y = 0;
        if (b) {
            final Rectangle clientArea = composite.getClientArea();
            x = clientArea.x;
            y = clientArea.y;
        }
        int[] array = null;
        boolean b4 = false;
        Rectangle[] array2 = null;
        if (b && (this.justify || this.fill || this.center)) {
            array2 = new Rectangle[n2];
            array = new int[n2];
        }
        int max2 = 0;
        int n5 = this.marginLeft + this.marginWidth;
        int n6 = this.marginTop + this.marginHeight;
        for (int k = 0; k < n2; ++k) {
            final Control control = children[k];
            if (this.pack) {
                final Point computeSize2 = this.computeSize(control, b3);
                n3 = computeSize2.x;
                n4 = computeSize2.y;
            }
            if (b2 && k != 0 && n5 + n3 > n) {
                b4 = true;
                if (b && (this.justify || this.fill || this.center)) {
                    array[k - 1] = max;
                }
                n5 = this.marginLeft + this.marginWidth;
                n6 += this.spacing + max;
                if (this.pack) {
                    max = 0;
                }
            }
            if (this.pack || this.fill || this.center) {
                max = Math.max(max, n4);
            }
            if (b) {
                final int n7 = n5 + x;
                final int n8 = n6 + y;
                if (this.justify || this.fill || this.center) {
                    array2[k] = new Rectangle(n7, n8, n3, n4);
                }
                else {
                    control.setBounds(n7, n8, n3, n4);
                }
            }
            n5 += this.spacing + n3;
            max2 = Math.max(max2, n5);
        }
        int max3 = Math.max(x + this.marginLeft + this.marginWidth, max2 - this.spacing);
        if (!b4) {
            max3 += this.marginRight + this.marginWidth;
        }
        if (b && (this.justify || this.fill || this.center)) {
            int n9 = 0;
            int n10 = 0;
            if (!b4) {
                n9 = Math.max(0, (n - max3) / (n2 + 1));
                n10 = Math.max(0, (n - max3) % (n2 + 1) / 2);
            }
            else if (this.fill || this.justify || this.center) {
                int n11 = 0;
                if (n2 > 0) {
                    array[n2 - 1] = max;
                }
                for (int l = 0; l < n2; ++l) {
                    if (array[l] != 0) {
                        final int n12 = l - n11 + 1;
                        if (this.justify) {
                            int n13 = 0;
                            for (int n14 = n11; n14 <= l; ++n14) {
                                n13 += array2[n14].width + this.spacing;
                            }
                            n9 = Math.max(0, (n - n13) / (n12 + 1));
                            n10 = Math.max(0, (n - n13) % (n12 + 1) / 2);
                        }
                        for (int n15 = n11; n15 <= l; ++n15) {
                            if (this.justify) {
                                final Rectangle rectangle = array2[n15];
                                rectangle.x += n9 * (n15 - n11 + 1) + n10;
                            }
                            if (this.fill) {
                                array2[n15].height = array[l];
                            }
                            else if (this.center) {
                                final Rectangle rectangle2 = array2[n15];
                                rectangle2.y += Math.max(0, (array[l] - array2[n15].height) / 2);
                            }
                        }
                        n11 = l + 1;
                    }
                }
            }
            for (int n16 = 0; n16 < n2; ++n16) {
                if (!b4) {
                    if (this.justify) {
                        final Rectangle rectangle3 = array2[n16];
                        rectangle3.x += n9 * (n16 + 1) + n10;
                    }
                    if (this.fill) {
                        array2[n16].height = max;
                    }
                    else if (this.center) {
                        final Rectangle rectangle4 = array2[n16];
                        rectangle4.y += Math.max(0, (max - array2[n16].height) / 2);
                    }
                }
                children[n16].setBounds(array2[n16]);
            }
        }
        return new Point(max3, n6 + max + this.marginBottom + this.marginHeight);
    }
    
    Point layoutVertical(final Composite composite, final boolean b, final boolean b2, final int n, final boolean b3) {
        final Control[] children = composite.getChildren();
        int n2 = 0;
        for (int i = 0; i < children.length; ++i) {
            final RowData rowData = (RowData)children[i].getLayoutData();
            if (rowData == null || !rowData.exclude) {
                children[n2++] = children[i];
            }
        }
        if (n2 == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        int n3 = 0;
        int n4 = 0;
        int max = 0;
        if (!this.pack) {
            for (int j = 0; j < n2; ++j) {
                final Point computeSize = this.computeSize(children[j], b3);
                n3 = Math.max(n3, computeSize.x);
                n4 = Math.max(n4, computeSize.y);
            }
            max = n3;
        }
        int x = 0;
        int y = 0;
        if (b) {
            final Rectangle clientArea = composite.getClientArea();
            x = clientArea.x;
            y = clientArea.y;
        }
        int[] array = null;
        boolean b4 = false;
        Rectangle[] array2 = null;
        if (b && (this.justify || this.fill || this.center)) {
            array2 = new Rectangle[n2];
            array = new int[n2];
        }
        int max2 = 0;
        int n5 = this.marginLeft + this.marginWidth;
        int n6 = this.marginTop + this.marginHeight;
        for (int k = 0; k < n2; ++k) {
            final Control control = children[k];
            if (this.pack) {
                final Point computeSize2 = this.computeSize(control, b3);
                n3 = computeSize2.x;
                n4 = computeSize2.y;
            }
            if (b2 && k != 0 && n6 + n4 > n) {
                b4 = true;
                if (b && (this.justify || this.fill || this.center)) {
                    array[k - 1] = max;
                }
                n5 += this.spacing + max;
                n6 = this.marginTop + this.marginHeight;
                if (this.pack) {
                    max = 0;
                }
            }
            if (this.pack || this.fill || this.center) {
                max = Math.max(max, n3);
            }
            if (b) {
                final int n7 = n5 + x;
                final int n8 = n6 + y;
                if (this.justify || this.fill || this.center) {
                    array2[k] = new Rectangle(n7, n8, n3, n4);
                }
                else {
                    control.setBounds(n7, n8, n3, n4);
                }
            }
            n6 += this.spacing + n4;
            max2 = Math.max(max2, n6);
        }
        int max3 = Math.max(y + this.marginTop + this.marginHeight, max2 - this.spacing);
        if (!b4) {
            max3 += this.marginBottom + this.marginHeight;
        }
        if (b && (this.justify || this.fill || this.center)) {
            int n9 = 0;
            int n10 = 0;
            if (!b4) {
                n9 = Math.max(0, (n - max3) / (n2 + 1));
                n10 = Math.max(0, (n - max3) % (n2 + 1) / 2);
            }
            else if (this.fill || this.justify || this.center) {
                int n11 = 0;
                if (n2 > 0) {
                    array[n2 - 1] = max;
                }
                for (int l = 0; l < n2; ++l) {
                    if (array[l] != 0) {
                        final int n12 = l - n11 + 1;
                        if (this.justify) {
                            int n13 = 0;
                            for (int n14 = n11; n14 <= l; ++n14) {
                                n13 += array2[n14].height + this.spacing;
                            }
                            n9 = Math.max(0, (n - n13) / (n12 + 1));
                            n10 = Math.max(0, (n - n13) % (n12 + 1) / 2);
                        }
                        for (int n15 = n11; n15 <= l; ++n15) {
                            if (this.justify) {
                                final Rectangle rectangle = array2[n15];
                                rectangle.y += n9 * (n15 - n11 + 1) + n10;
                            }
                            if (this.fill) {
                                array2[n15].width = array[l];
                            }
                            else if (this.center) {
                                final Rectangle rectangle2 = array2[n15];
                                rectangle2.x += Math.max(0, (array[l] - array2[n15].width) / 2);
                            }
                        }
                        n11 = l + 1;
                    }
                }
            }
            for (int n16 = 0; n16 < n2; ++n16) {
                if (!b4) {
                    if (this.justify) {
                        final Rectangle rectangle3 = array2[n16];
                        rectangle3.y += n9 * (n16 + 1) + n10;
                    }
                    if (this.fill) {
                        array2[n16].width = max;
                    }
                    else if (this.center) {
                        final Rectangle rectangle4 = array2[n16];
                        rectangle4.x += Math.max(0, (max - array2[n16].width) / 2);
                    }
                }
                children[n16].setBounds(array2[n16]);
            }
        }
        return new Point(n5 + max + this.marginRight + this.marginWidth, max3);
    }
    
    public String toString() {
        String s = String.valueOf(new StringBuffer(String.valueOf(this.getName())).append(" {").toString()) + "type=" + ((this.type != 256) ? "SWT.VERTICAL" : "SWT.HORIZONTAL") + " ";
        if (this.marginWidth != 0) {
            s = String.valueOf(s) + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            s = String.valueOf(s) + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            s = String.valueOf(s) + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginTop != 0) {
            s = String.valueOf(s) + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginRight != 0) {
            s = String.valueOf(s) + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginBottom != 0) {
            s = String.valueOf(s) + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.spacing != 0) {
            s = String.valueOf(s) + "spacing=" + this.spacing + " ";
        }
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s)).append("wrap=").append(this.wrap).append(" ").toString())).append("pack=").append(this.pack).append(" ").toString())).append("fill=").append(this.fill).append(" ").toString())).append("justify=").append(this.justify).append(" ").toString().trim()) + "}";
    }
}
