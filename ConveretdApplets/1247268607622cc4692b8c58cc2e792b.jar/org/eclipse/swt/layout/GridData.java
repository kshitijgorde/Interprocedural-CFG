// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

public final class GridData
{
    public int verticalAlignment;
    public int horizontalAlignment;
    public int widthHint;
    public int heightHint;
    public int horizontalIndent;
    public int verticalIndent;
    public int horizontalSpan;
    public int verticalSpan;
    public boolean grabExcessHorizontalSpace;
    public boolean grabExcessVerticalSpace;
    public int minimumWidth;
    public int minimumHeight;
    public boolean exclude;
    public static final int BEGINNING = 1;
    public static final int CENTER = 2;
    public static final int END = 3;
    public static final int FILL = 4;
    public static final int VERTICAL_ALIGN_BEGINNING = 2;
    public static final int VERTICAL_ALIGN_CENTER = 4;
    public static final int VERTICAL_ALIGN_END = 8;
    public static final int VERTICAL_ALIGN_FILL = 16;
    public static final int HORIZONTAL_ALIGN_BEGINNING = 32;
    public static final int HORIZONTAL_ALIGN_CENTER = 64;
    public static final int HORIZONTAL_ALIGN_END = 128;
    public static final int HORIZONTAL_ALIGN_FILL = 256;
    public static final int GRAB_HORIZONTAL = 512;
    public static final int GRAB_VERTICAL = 1024;
    public static final int FILL_VERTICAL = 1040;
    public static final int FILL_HORIZONTAL = 768;
    public static final int FILL_BOTH = 1808;
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
    
    public GridData() {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    public GridData(final int n) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        if ((n & 0x2) != 0x0) {
            this.verticalAlignment = 1;
        }
        if ((n & 0x4) != 0x0) {
            this.verticalAlignment = 2;
        }
        if ((n & 0x10) != 0x0) {
            this.verticalAlignment = 4;
        }
        if ((n & 0x8) != 0x0) {
            this.verticalAlignment = 3;
        }
        if ((n & 0x20) != 0x0) {
            this.horizontalAlignment = 1;
        }
        if ((n & 0x40) != 0x0) {
            this.horizontalAlignment = 2;
        }
        if ((n & 0x100) != 0x0) {
            this.horizontalAlignment = 4;
        }
        if ((n & 0x80) != 0x0) {
            this.horizontalAlignment = 3;
        }
        this.grabExcessHorizontalSpace = ((n & 0x200) != 0x0);
        this.grabExcessVerticalSpace = ((n & 0x400) != 0x0);
    }
    
    public GridData(final int n, final int n2, final boolean b, final boolean b2) {
        this(n, n2, b, b2, 1, 1);
    }
    
    public GridData(final int horizontalAlignment, final int verticalAlignment, final boolean grabExcessHorizontalSpace, final boolean grabExcessVerticalSpace, final int horizontalSpan, final int verticalSpan) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
        this.grabExcessVerticalSpace = grabExcessVerticalSpace;
        this.horizontalSpan = horizontalSpan;
        this.verticalSpan = verticalSpan;
    }
    
    public GridData(final int widthHint, final int heightHint) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.widthHint = widthHint;
        this.heightHint = heightHint;
    }
    
    void computeSize(final Control control, final int n, final int n2, final boolean b) {
        if (this.cacheWidth != -1 && this.cacheHeight != -1) {
            return;
        }
        if (n == this.widthHint && n2 == this.heightHint) {
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
        this.defaultHeight = n2;
        this.defaultWidth = n2;
        final int n3 = -1;
        this.currentHeight = n3;
        this.currentWidth = n3;
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        String string = null;
        switch (this.horizontalAlignment) {
            case 4: {
                string = "SWT.FILL";
                break;
            }
            case 1: {
                string = "SWT.BEGINNING";
                break;
            }
            case 16384: {
                string = "SWT.LEFT";
                break;
            }
            case 16777224: {
                string = "SWT.END";
                break;
            }
            case 3: {
                string = "GridData.END";
                break;
            }
            case 131072: {
                string = "SWT.RIGHT";
                break;
            }
            case 16777216: {
                string = "SWT.CENTER";
                break;
            }
            case 2: {
                string = "GridData.CENTER";
                break;
            }
            default: {
                string = "Undefined " + this.horizontalAlignment;
                break;
            }
        }
        String string2 = null;
        switch (this.verticalAlignment) {
            case 4: {
                string2 = "SWT.FILL";
                break;
            }
            case 1: {
                string2 = "SWT.BEGINNING";
                break;
            }
            case 128: {
                string2 = "SWT.TOP";
                break;
            }
            case 16777224: {
                string2 = "SWT.END";
                break;
            }
            case 3: {
                string2 = "GridData.END";
                break;
            }
            case 1024: {
                string2 = "SWT.BOTTOM";
                break;
            }
            case 16777216: {
                string2 = "SWT.CENTER";
                break;
            }
            case 2: {
                string2 = "GridData.CENTER";
                break;
            }
            default: {
                string2 = "Undefined " + this.verticalAlignment;
                break;
            }
        }
        String s = String.valueOf(new StringBuffer(String.valueOf(this.getName())).append(" {").toString()) + "horizontalAlignment=" + string + " ";
        if (this.horizontalIndent != 0) {
            s = String.valueOf(s) + "horizontalIndent=" + this.horizontalIndent + " ";
        }
        if (this.horizontalSpan != 1) {
            s = String.valueOf(s) + "horizontalSpan=" + this.horizontalSpan + " ";
        }
        if (this.grabExcessHorizontalSpace) {
            s = String.valueOf(s) + "grabExcessHorizontalSpace=" + this.grabExcessHorizontalSpace + " ";
        }
        if (this.widthHint != -1) {
            s = String.valueOf(s) + "widthHint=" + this.widthHint + " ";
        }
        if (this.minimumWidth != 0) {
            s = String.valueOf(s) + "minimumWidth=" + this.minimumWidth + " ";
        }
        String s2 = String.valueOf(s) + "verticalAlignment=" + string2 + " ";
        if (this.verticalIndent != 0) {
            s2 = String.valueOf(s2) + "verticalIndent=" + this.verticalIndent + " ";
        }
        if (this.verticalSpan != 1) {
            s2 = String.valueOf(s2) + "verticalSpan=" + this.verticalSpan + " ";
        }
        if (this.grabExcessVerticalSpace) {
            s2 = String.valueOf(s2) + "grabExcessVerticalSpace=" + this.grabExcessVerticalSpace + " ";
        }
        if (this.heightHint != -1) {
            s2 = String.valueOf(s2) + "heightHint=" + this.heightHint + " ";
        }
        if (this.minimumHeight != 0) {
            s2 = String.valueOf(s2) + "minimumHeight=" + this.minimumHeight + " ";
        }
        if (this.exclude) {
            s2 = String.valueOf(s2) + "exclude=" + this.exclude + " ";
        }
        return String.valueOf(s2.trim()) + "}";
    }
}
