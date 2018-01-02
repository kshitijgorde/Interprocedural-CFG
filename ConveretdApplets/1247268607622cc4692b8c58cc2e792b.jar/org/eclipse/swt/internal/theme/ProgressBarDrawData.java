// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ProgressBarDrawData extends RangeDrawData
{
    public ProgressBarDrawData() {
        this.state = new int[1];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rect.left + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rect.top + rectangle.height;
            final int[] array = { 0 };
            OS.GetThemeInt(openThemeData, 0, 0, 2411, array);
            final int n = array[0];
            OS.GetThemeInt(openThemeData, 0, 0, 2412, array);
            final int n2 = array[0];
            final RECT rect2 = new RECT();
            final int[] partId = this.getPartId(0);
            if ((this.style & 0x200) != 0x0) {
                OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
                final int n3 = rect2.bottom - (rect2.bottom - rect2.top) * (this.selection - this.minimum) / (this.maximum - this.minimum);
                rect2.top = rect2.bottom - n;
                while (rect2.top >= n3) {
                    OS.DrawThemeBackground(openThemeData, gc.handle, 4, 0, rect2, null);
                    final RECT rect3 = rect2;
                    rect3.bottom -= n + n2;
                    rect2.top = rect2.bottom - n;
                }
                if (this.selection != 0) {
                    OS.DrawThemeBackground(openThemeData, gc.handle, 4, 0, rect2, null);
                }
            }
            else {
                OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
                final int n4 = rect2.left + (rect2.right - rect2.left) * (this.selection - this.minimum) / (this.maximum - this.minimum);
                rect2.right = rect2.left + n;
                while (rect2.right <= n4) {
                    OS.DrawThemeBackground(openThemeData, gc.handle, 3, 0, rect2, null);
                    final RECT rect4 = rect2;
                    rect4.left += n + n2;
                    rect2.right = rect2.left + n;
                }
                if (this.selection != 0) {
                    OS.DrawThemeBackground(openThemeData, gc.handle, 3, 0, rect2, null);
                }
            }
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return ProgressBarDrawData.PROGRESS;
    }
    
    int[] getPartId(final int n) {
        final boolean b = false;
        int n2;
        if ((this.style & 0x200) != 0x0) {
            n2 = 2;
        }
        else {
            n2 = 1;
        }
        return new int[] { n2, b };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        return rectangle.contains(point) ? 0 : -1;
    }
}
