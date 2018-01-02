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

public class ToolItemDrawData extends DrawData
{
    public ToolBarDrawData parent;
    static final int INSET = 1;
    
    public ToolItemDrawData() {
        this.state = new int[2];
    }
    
    Rectangle computeTrim(final Theme theme, final GC gc) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = this.clientArea.x;
            rect.right = this.clientArea.x + this.clientArea.width;
            rect.top = this.clientArea.y;
            rect.bottom = this.clientArea.y + this.clientArea.height;
            final RECT rect2 = new RECT();
            final int[] partId = this.getPartId(0);
            OS.GetThemeBackgroundExtent(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
            OS.CloseThemeData(openThemeData);
            if ((this.style & 0x4) != 0x0) {
                final SIZE size = new SIZE();
                final int[] partId2 = this.getPartId(1);
                OS.GetThemePartSize(openThemeData, 0, partId2[0], partId2[1], null, 1, size);
                rect2.right = Math.max(rect2.left, rect2.right + size.cx);
            }
            else {
                final RECT rect3 = rect2;
                --rect3.left;
                final RECT rect4 = rect2;
                --rect4.top;
                final RECT rect5 = rect2;
                ++rect5.right;
                final RECT rect6 = rect2;
                ++rect6.bottom;
            }
            return new Rectangle(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rectangle.y + rectangle.height;
            SIZE size = null;
            int[] partId = null;
            if ((this.style & 0x4) != 0x0) {
                size = new SIZE();
                partId = this.getPartId(1);
                OS.GetThemePartSize(openThemeData, gc.handle, partId[0], partId[1], rect, 1, size);
                final RECT rect2 = rect;
                rect2.right -= size.cx;
                if (rect.right < rect.left) {
                    rect.right = rect.left;
                }
            }
            final int[] partId2 = this.getPartId(0);
            OS.DrawThemeBackground(openThemeData, gc.handle, partId2[0], partId2[1], rect, null);
            final Rectangle clientArea = this.clientArea;
            if (clientArea != null) {
                final RECT rect3 = new RECT();
                OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId2[0], partId2[1], rect, rect3);
                clientArea.x = rect3.left;
                clientArea.y = rect3.top;
                clientArea.width = rect3.right - rect3.left;
                clientArea.height = rect3.bottom - rect3.top;
            }
            if ((this.style & 0x4) != 0x0) {
                rect.left = rect.right;
                rect.right = rect.left + size.cx;
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            }
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return ToolItemDrawData.TOOLBAR;
    }
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 0: {
                if ((this.style & 0x38) != 0x0) {
                    n3 = 1;
                }
                else if ((this.style & 0x4) != 0x0) {
                    n3 = 3;
                }
                else if ((this.style & 0x2) != 0x0) {
                    if ((this.parent.style & 0x200) != 0x0) {
                        n3 = 6;
                    }
                    else {
                        n3 = 5;
                    }
                }
                if ((this.style & 0x2) != 0x0) {
                    break;
                }
                if ((n2 & 0x40) != 0x0) {
                    if ((this.style & 0x30) != 0x0 && (n2 & 0x2) != 0x0) {
                        n4 = 6;
                    }
                    else {
                        n4 = 2;
                    }
                }
                if ((this.style & 0x30) != 0x0 && (n2 & 0x2) != 0x0) {
                    n4 = 5;
                }
                if ((n2 & 0x8) != 0x0) {
                    n4 = 3;
                }
                if ((n2 & 0x20) != 0x0) {
                    n4 = 4;
                    break;
                }
                break;
            }
            case 1: {
                n3 = 4;
                if ((n2 & 0x40) != 0x0) {
                    n4 = 2;
                }
                if ((n2 & 0x8) != 0x0) {
                    n4 = 3;
                }
                if ((n2 & 0x20) != 0x0) {
                    n4 = 4;
                    break;
                }
                break;
            }
        }
        return new int[] { n3, n4 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return -1;
        }
        if (!rectangle.contains(point)) {
            return -1;
        }
        final int openThemeData = OS.OpenThemeData(0, this.getClassId());
        try {
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
            if (array[0] == 0) {
                return -1;
            }
            if ((this.style & 0x4) != 0x0) {
                final SIZE size = new SIZE();
                final int[] partId2 = this.getPartId(1);
                OS.GetThemePartSize(openThemeData, 0, partId2[0], partId2[1], rect, 1, size);
                rect.left = Math.max(rect.left, rect.right - size.cx);
                OS.HitTestThemeBackground(openThemeData, 0, partId2[0], partId2[1], 0, rect, 0, point2, array);
                if (array[0] != 0) {
                    return 1;
                }
            }
        }
        finally {
            OS.CloseThemeData(openThemeData);
        }
        OS.CloseThemeData(openThemeData);
        return 0;
    }
}
