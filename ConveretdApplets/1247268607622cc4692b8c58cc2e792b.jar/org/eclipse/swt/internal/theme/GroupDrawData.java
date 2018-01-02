// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class GroupDrawData extends DrawData
{
    public int headerWidth;
    public int headerHeight;
    public Rectangle headerArea;
    static final int GROUP_HEADER_X = 9;
    static final int GROUP_HEADER_PAD = 2;
    
    public GroupDrawData() {
        this.state = new int[1];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y + this.headerHeight / 2;
            rect.bottom = rectangle.y + rectangle.height;
            final int x = rectangle.x + 9;
            final int y = rectangle.y;
            final int saveDC = OS.SaveDC(gc.handle);
            OS.ExcludeClipRect(gc.handle, x - 2, y, x + this.headerWidth + 2, y + this.headerHeight);
            final int[] partId = this.getPartId(0);
            OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            OS.RestoreDC(gc.handle, saveDC);
            final Rectangle headerArea = this.headerArea;
            if (headerArea != null) {
                headerArea.x = x;
                headerArea.y = y;
                headerArea.width = this.headerWidth;
                headerArea.height = this.headerHeight;
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
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        final int n3 = 4;
        int n4 = 1;
        if ((n2 & 0x20) != 0x0) {
            n4 = 2;
        }
        return new int[] { n3, n4 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        return rectangle.contains(point) ? 0 : -1;
    }
}
