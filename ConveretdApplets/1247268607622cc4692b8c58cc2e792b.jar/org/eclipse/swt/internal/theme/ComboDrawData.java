// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ComboDrawData extends DrawData
{
    public ComboDrawData() {
        this.state = new int[2];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, ComboDrawData.EDIT);
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rectangle.y + rectangle.height;
            final int[] partId = this.getPartId(0);
            OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            final RECT rect2 = new RECT();
            OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
            final Rectangle clientArea = this.clientArea;
            if (clientArea != null) {
                clientArea.x = rect2.left;
                clientArea.y = rect2.top;
                clientArea.width = rect2.right - rect2.left;
                clientArea.height = rect2.bottom - rect2.top;
            }
            OS.CloseThemeData(openThemeData);
            final int openThemeData2 = OS.OpenThemeData(0, this.getClassId());
            final int getThemeSysSize = OS.GetThemeSysSize(openThemeData2, 2);
            rect.left = rect2.right - getThemeSysSize;
            rect.top = rect2.top;
            rect.right = rect2.right;
            rect.bottom = rect2.bottom;
            final int[] partId2 = this.getPartId(1);
            OS.DrawThemeBackground(openThemeData2, gc.handle, partId2[0], partId2[1], rect, null);
            OS.CloseThemeData(openThemeData2);
            if (clientArea != null) {
                final Rectangle rectangle2 = clientArea;
                rectangle2.width -= getThemeSysSize;
            }
        }
    }
    
    char[] getClassId() {
        return ComboDrawData.COMBOBOX;
    }
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        boolean b = false;
        int n3 = 0;
        switch (n) {
            case 0: {
                b = true;
                n3 = 1;
                if ((n2 & 0x20) != 0x0) {
                    n3 = 4;
                    break;
                }
                break;
            }
            case 1: {
                b = true;
                n3 = 1;
                if ((n2 & 0x20) != 0x0) {
                    n3 = 4;
                }
                if ((n2 & 0x40) != 0x0) {
                    n3 = 2;
                }
                if ((n2 & 0x8) != 0x0) {
                    n3 = 3;
                    break;
                }
                break;
            }
        }
        return new int[] { b, n3 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return -1;
        }
        if (!rectangle.contains(point)) {
            return -1;
        }
        final int openThemeData = OS.OpenThemeData(0, ComboDrawData.EDIT);
        final int[] partId = this.getPartId(0);
        final int n = partId[0];
        final int n2 = partId[1];
        final RECT rect = new RECT();
        rect.left = rectangle.x;
        rect.right = rectangle.x + rectangle.width;
        rect.top = rectangle.y;
        rect.bottom = rectangle.y + rectangle.height;
        final RECT rect2 = new RECT();
        OS.GetThemeBackgroundContentRect(openThemeData, 0, n, n2, rect, rect2);
        OS.CloseThemeData(openThemeData);
        final int openThemeData2 = OS.OpenThemeData(0, this.getClassId());
        final int getThemeSysSize = OS.GetThemeSysSize(openThemeData2, 2);
        OS.CloseThemeData(openThemeData2);
        if (new Rectangle(rect2.right - getThemeSysSize, rect2.top, rect2.bottom - rect2.top, getThemeSysSize).contains(point)) {
            return 1;
        }
        return 0;
    }
}
