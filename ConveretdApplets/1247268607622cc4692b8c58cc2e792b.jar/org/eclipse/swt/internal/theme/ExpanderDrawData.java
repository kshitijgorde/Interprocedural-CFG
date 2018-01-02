// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ExpanderDrawData extends DrawData
{
    public ExpanderDrawData() {
        this.state = new int[1];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            int n = 1;
            if ((this.style & 0x400) != 0x0) {
                n = 2;
            }
            final SIZE size = new SIZE();
            OS.GetThemePartSize(openThemeData, gc.handle, 2, n, null, 1, size);
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rect.left + size.cx;
            rect.top = rectangle.y;
            rect.bottom = rect.top + size.cy;
            OS.DrawThemeBackground(openThemeData, gc.handle, 2, n, rect, null);
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return ExpanderDrawData.TREEVIEW;
    }
    
    int[] getPartId(final int n) {
        final int n2 = 2;
        int n3 = 1;
        if ((this.style & 0x400) != 0x0) {
            n3 = 2;
        }
        return new int[] { n2, n3 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return -1;
        }
        if (!rectangle.contains(point)) {
            return -1;
        }
        final int openThemeData = OS.OpenThemeData(0, this.getClassId());
        final SIZE size = new SIZE();
        final int[] partId = this.getPartId(0);
        OS.GetThemePartSize(openThemeData, 0, partId[0], partId[1], null, 1, size);
        OS.CloseThemeData(openThemeData);
        if (new Rectangle(rectangle.x, rectangle.y, size.cx, size.cy).contains(point)) {
            return 0;
        }
        return -1;
    }
}
