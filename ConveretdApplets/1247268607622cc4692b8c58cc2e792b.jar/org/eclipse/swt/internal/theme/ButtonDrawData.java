// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ButtonDrawData extends DrawData
{
    public ButtonDrawData() {
        this.state = new int[1];
    }
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        final int style = this.style;
        int n3 = 0;
        int n4 = 0;
        if ((style & 0x8) != 0x0) {
            n3 = 1;
            n4 = 1;
            if ((n2 & 0x80) != 0x0 && (n2 & 0x10) != 0x0) {
                n4 = 5;
            }
            if ((n2 & 0x40) != 0x0) {
                n4 = 2;
            }
            if ((n2 & 0x8) != 0x0) {
                n4 = 3;
            }
            if ((n2 & 0x20) != 0x0) {
                n4 = 4;
            }
        }
        if ((style & 0x10) != 0x0) {
            n3 = 2;
        }
        if ((style & 0x20) != 0x0) {
            n3 = 3;
        }
        if ((style & 0x30) != 0x0) {
            if ((n2 & 0x2) != 0x0) {
                n4 = 5;
                if ((n2 & 0x40) != 0x0) {
                    n4 = 6;
                }
                if ((n2 & 0x8) != 0x0) {
                    n4 = 7;
                }
                if ((n2 & 0x20) != 0x0) {
                    n4 = 8;
                }
            }
            else {
                n4 = 1;
                if ((n2 & 0x40) != 0x0) {
                    n4 = 2;
                }
                if ((n2 & 0x8) != 0x0) {
                    n4 = 3;
                }
                if ((n2 & 0x20) != 0x0) {
                    n4 = 4;
                }
            }
        }
        return new int[] { n3, n4 };
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rectangle.y + rectangle.height;
            final int[] partId = this.getPartId(0);
            if ((this.style & 0x30) != 0x0) {
                final SIZE size = new SIZE();
                OS.GetThemePartSize(openThemeData, gc.handle, partId[0], partId[1], rect, 2, size);
                rect.right = rect.left + size.cx;
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
                rect.left = rect.right + 3;
                rect.right = rect.left + rectangle.width - size.cx - 3;
            }
            else {
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            }
            final Rectangle clientArea = this.clientArea;
            if (clientArea != null) {
                final RECT rect2 = new RECT();
                OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
                clientArea.x = rect2.left;
                clientArea.y = rect2.top;
                clientArea.width = rect2.right - rect2.left;
                clientArea.height = rect2.bottom - rect2.top;
            }
            OS.CloseThemeData(openThemeData);
        }
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return -1;
        }
        if (!rectangle.contains(point)) {
            return -1;
        }
        final int openThemeData = OS.OpenThemeData(0, this.getClassId());
        final RECT rect = new RECT();
        rect.left = rectangle.x;
        rect.right = rectangle.x + rectangle.width;
        rect.top = rectangle.y;
        rect.bottom = rectangle.y + rectangle.height;
        final POINT point2 = new POINT();
        point2.x = point.x;
        point2.y = point.y;
        final short[] array = { 0 };
        final int[] partId = this.getPartId(0);
        OS.HitTestThemeBackground(openThemeData, 0, partId[0], partId[1], 0, rect, 0, point2, array);
        OS.CloseThemeData(openThemeData);
        return (array[0] == 0) ? -1 : 0;
    }
}
