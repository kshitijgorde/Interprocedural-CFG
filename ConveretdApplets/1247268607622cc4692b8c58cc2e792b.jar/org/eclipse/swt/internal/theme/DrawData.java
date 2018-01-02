// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class DrawData
{
    public int style;
    public int[] state;
    public Rectangle clientArea;
    public static final int SELECTED = 2;
    public static final int FOCUSED = 4;
    public static final int PRESSED = 8;
    public static final int ACTIVE = 16;
    public static final int DISABLED = 32;
    public static final int HOT = 64;
    public static final int DEFAULTED = 128;
    public static final int GRAYED = 256;
    public static final int DRAW_LEFT = 16;
    public static final int DRAW_TOP = 32;
    public static final int DRAW_RIGHT = 64;
    public static final int DRAW_BOTTOM = 128;
    public static final int DRAW_HCENTER = 256;
    public static final int DRAW_VCENTER = 512;
    public static final int WIDGET_NOWHERE = -1;
    public static final int WIDGET_WHOLE = 0;
    public static final int SCROLLBAR_UP_ARROW = 1;
    public static final int SCROLLBAR_DOWN_ARROW = 2;
    public static final int SCROLLBAR_LEFT_ARROW = 1;
    public static final int SCROLLBAR_RIGHT_ARROW = 2;
    public static final int SCROLLBAR_UP_TRACK = 3;
    public static final int SCROLLBAR_DOWN_TRACK = 4;
    public static final int SCROLLBAR_LEFT_TRACK = 3;
    public static final int SCROLLBAR_RIGHT_TRACK = 4;
    public static final int SCROLLBAR_THUMB = 5;
    public static final int SCALE_UP_TRACK = 1;
    public static final int SCALE_LEFT_TRACK = 1;
    public static final int SCALE_DOWN_TRACK = 2;
    public static final int SCALE_RIGHT_TRACK = 2;
    public static final int SCALE_THUMB = 3;
    public static final int TOOLITEM_ARROW = 1;
    public static final int COMBO_ARROW = 1;
    static final char[] EDIT;
    static final char[] COMBOBOX;
    static final char[] BUTTON;
    static final char[] PROGRESS;
    static final char[] SCROLLBAR;
    static final char[] TAB;
    static final char[] TRACKBAR;
    static final char[] TOOLBAR;
    static final char[] TREEVIEW;
    
    static {
        EDIT = new char[] { 'E', 'D', 'I', 'T', '\0' };
        COMBOBOX = new char[] { 'C', 'O', 'M', 'B', 'O', 'B', 'O', 'X', '\0' };
        BUTTON = new char[] { 'B', 'U', 'T', 'T', 'O', 'N', '\0' };
        PROGRESS = new char[] { 'P', 'R', 'O', 'G', 'R', 'E', 'S', 'S', '\0' };
        SCROLLBAR = new char[] { 'S', 'C', 'R', 'O', 'L', 'L', 'B', 'A', 'R', '\0' };
        TAB = new char[] { 'T', 'A', 'B', '\0' };
        TRACKBAR = new char[] { 'T', 'R', 'A', 'C', 'K', 'B', 'A', 'R', '\0' };
        TOOLBAR = new char[] { 'T', 'O', 'O', 'L', 'B', 'A', 'R', '\0' };
        TREEVIEW = new char[] { 'T', 'R', 'E', 'E', 'V', 'I', 'E', 'W', '\0' };
    }
    
    public DrawData() {
        this.state = new int[1];
    }
    
    Rectangle computeTrim(final Theme theme, final GC gc) {
        return new Rectangle(this.clientArea.x, this.clientArea.y, this.clientArea.width, this.clientArea.height);
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
    }
    
    void drawImage(final Theme theme, final Image image, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final Rectangle bounds = image.getBounds();
            gc.drawImage(image, 0, 0, bounds.width, bounds.height, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    void drawText(final Theme theme, final String s, final int n, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final char[] array = new char[s.length()];
            s.getChars(0, array.length, array, 0);
            int n2 = 32;
            if ((n & 0x10) != 0x0) {
                n2 |= 0x0;
            }
            if ((n & 0x100) != 0x0) {
                n2 |= 0x1;
            }
            if ((n & 0x40) != 0x0) {
                n2 |= 0x2;
            }
            if ((n & 0x20) != 0x0) {
                n2 |= 0x0;
            }
            if ((n & 0x80) != 0x0) {
                n2 |= 0x8;
            }
            if ((n & 0x200) != 0x0) {
                n2 |= 0x4;
            }
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rectangle.y + rectangle.height;
            final int[] partId = this.getPartId(0);
            OS.DrawThemeText(openThemeData, gc.handle, partId[0], partId[1], array, array.length, n2, 0, rect);
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return DrawData.BUTTON;
    }
    
    int[] getPartId(final int n) {
        return new int[2];
    }
    
    Rectangle getBounds(final int n, final Rectangle rectangle) {
        return new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        return -1;
    }
    
    Rectangle measureText(final Theme theme, final String s, final int n, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return new Rectangle(0, 0, 0, 0);
        }
        final int openThemeData = OS.OpenThemeData(0, this.getClassId());
        final char[] array = new char[s.length()];
        s.getChars(0, array.length, array, 0);
        int n2 = 0;
        if ((this.style & 0x4000) != 0x0) {
            n2 |= 0x0;
        }
        if ((this.style & 0x1000000) != 0x0) {
            n2 |= 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            n2 |= 0x2;
        }
        final RECT rect = new RECT();
        RECT rect2 = null;
        if (rectangle != null) {
            rect2 = new RECT();
            rect2.left = rectangle.x;
            rect2.right = rectangle.x + rectangle.width;
            rect2.top = rectangle.y;
            rect2.bottom = rectangle.y + rectangle.height;
        }
        final int[] partId = this.getPartId(0);
        OS.GetThemeTextExtent(openThemeData, gc.handle, partId[0], partId[1], array, array.length, n2, rect2, rect);
        OS.CloseThemeData(openThemeData);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
}
