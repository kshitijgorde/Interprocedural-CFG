// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class TabItemDrawData extends DrawData
{
    public TabFolderDrawData parent;
    public int position;
    static final int TABITEM_INSET = 2;
    static final int TABITEM_INSET2 = 6;
    
    public TabItemDrawData() {
        this.state = new int[1];
    }
    
    Rectangle computeTrim(final Theme theme, final GC gc) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            int x = this.clientArea.x;
            int y = this.clientArea.y;
            int width = this.clientArea.width;
            int height = this.clientArea.height;
            if ((this.style & 0x4000) != 0x0) {
                x -= 2;
                width += 2;
            }
            y -= 2;
            height += 2;
            final RECT rect = new RECT();
            rect.left = x;
            rect.right = x + width;
            rect.top = y;
            rect.bottom = y + height;
            final RECT rect2 = new RECT();
            final int[] partId = this.getPartId(0);
            OS.GetThemeBackgroundExtent(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
            final RECT rect3 = rect2;
            rect3.left -= 6;
            final RECT rect4 = rect2;
            rect4.top -= 6;
            final RECT rect5 = rect2;
            rect5.right += 6;
            OS.CloseThemeData(openThemeData);
            return new Rectangle(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int n = this.state[0];
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            int x = rectangle.x;
            int y = rectangle.y;
            int width = rectangle.width;
            int height = rectangle.height;
            if ((this.position & 0x4000) != 0x0) {
                x += 2;
                width -= 2;
            }
            y += 2;
            height -= 2;
            if ((n & 0x2) != 0x0) {
                x -= 2;
                y -= 2;
                width += 4;
                height += 4;
            }
            final RECT rect = new RECT();
            rect.left = x;
            rect.right = x + width;
            rect.top = y;
            rect.bottom = y + height;
            final int[] partId = this.getPartId(0);
            OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            OS.CloseThemeData(openThemeData);
            final Rectangle clientArea = this.clientArea;
            if (clientArea != null) {
                final RECT rect2 = new RECT();
                OS.GetThemeBackgroundContentRect(openThemeData, gc.handle, partId[0], partId[1], rect, rect2);
                clientArea.x = rect2.left;
                clientArea.y = rect2.top;
                clientArea.width = rect2.right - rect2.left;
                clientArea.height = rect2.bottom - rect2.top;
            }
        }
    }
    
    char[] getClassId() {
        return TabItemDrawData.TAB;
    }
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        int n3 = 1;
        int n4 = 1;
        if ((this.style & 0x4000) != 0x0 && (this.style & 0x20000) != 0x0) {
            n3 = 2;
        }
        else if ((this.style & 0x4000) != 0x0) {
            n3 = 2;
        }
        if ((n2 & 0x40) != 0x0) {
            n4 = 2;
        }
        if ((n2 & 0x4) != 0x0) {
            n4 = 5;
        }
        if ((n2 & 0x2) != 0x0) {
            n4 = 3;
        }
        if ((n2 & 0x20) != 0x0) {
            n4 = 4;
        }
        return new int[] { n3, n4 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (!rectangle.contains(point)) {
            return -1;
        }
        final int style = this.style;
        int x = rectangle.x;
        int y = rectangle.y;
        int width = rectangle.width;
        int height = rectangle.height;
        if ((style & 0x4000) != 0x0) {
            x += 2;
            width -= 2;
        }
        y += 2;
        height -= 2;
        if (!new Rectangle(x, y, width, height).contains(point)) {
            return -1;
        }
        return 0;
    }
}
