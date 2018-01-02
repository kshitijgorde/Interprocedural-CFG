// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ScaleDrawData extends RangeDrawData
{
    public int increment;
    public int pageIncrement;
    static final int TICS_MARGIN = 10;
    
    public ScaleDrawData() {
        this.state = new int[4];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int style = this.style;
            final int minimum = this.minimum;
            final int maximum = this.maximum;
            final int selection = this.selection;
            final int pageIncrement = this.pageIncrement;
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rect.left + rectangle.width;
            rect.top = rectangle.y;
            rect.bottom = rect.top + rectangle.height;
            final SIZE size = new SIZE();
            if ((style & 0x200) != 0x0) {
                OS.GetThemePartSize(openThemeData, gc.handle, 2, 0, null, 1, size);
                final int n = size.cx - 1;
                OS.GetThemePartSize(openThemeData, gc.handle, 6, 0, null, 1, size);
                final int cx = size.cx;
                final int cy = size.cy;
                OS.GetThemePartSize(openThemeData, gc.handle, 9, 0, rect, 1, size);
                final int cx2 = size.cx;
                final int n3;
                int n2 = n3 = (cx - n) / 2;
                n2 += 10;
                final RECT rect2 = rect;
                rect2.left += n2;
                final RECT rect3 = rect;
                rect3.top += n3;
                rect.right = rect.left + n;
                final RECT rect4 = rect;
                rect4.bottom -= n3;
                final int n4 = rect.bottom - rect.top;
                OS.DrawThemeBackground(openThemeData, gc.handle, 2, 0, rect, null);
                final RECT rect5 = rect;
                rect5.top += (n4 - cy) * (selection - minimum) / Math.max(1, maximum - minimum);
                final RECT rect6 = rect;
                rect6.left -= (cx - n) / 2;
                rect.right = rect.left + cx;
                rect.bottom = rect.top + cy;
                OS.DrawThemeBackground(openThemeData, gc.handle, 6, 0, rect, null);
                rect.top = rectangle.y + n3 + cy / 2;
                rect.bottom = rect.top + 1;
                for (int i = minimum; i <= maximum; i += pageIncrement) {
                    rect.left = rectangle.x + 5;
                    rect.right = rect.left + cx2;
                    if (i != minimum && i != maximum) {
                        final RECT rect7 = rect;
                        ++rect7.left;
                    }
                    rect.top = rectangle.y + n3 + cy / 2;
                    final RECT rect8 = rect;
                    rect8.top += (n4 - cy) * (i - minimum) / Math.max(1, maximum - minimum);
                    rect.bottom = rect.top + 1;
                    OS.DrawThemeBackground(openThemeData, gc.handle, 10, 1, rect, null);
                    gc.drawLine(rect.left, rect.top, rect.right, rect.top);
                    rect.left = rectangle.x + 10 + cx + 1;
                    rect.right = rect.left + cx2;
                    if (i != minimum && i != maximum) {
                        final RECT rect9 = rect;
                        --rect9.right;
                    }
                    OS.DrawThemeBackground(openThemeData, gc.handle, 10, 1, rect, null);
                    gc.drawLine(rect.left, rect.top, rect.right, rect.top);
                }
            }
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return ScaleDrawData.TRACKBAR;
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        return rectangle.contains(point) ? 0 : -1;
    }
}
