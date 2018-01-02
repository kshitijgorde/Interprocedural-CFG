// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;

public class TabFolderDrawData extends DrawData
{
    public int tabsWidth;
    public int tabsHeight;
    public Rectangle tabsArea;
    public int selectedX;
    public int selectedWidth;
    public int spacing;
    
    public TabFolderDrawData() {
        this.state = new int[1];
        if (SWT.getPlatform().equals("gtk")) {
            this.spacing = -2;
        }
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            rect.left = rectangle.x;
            rect.right = rectangle.x + rectangle.width;
            rect.top = rectangle.y;
            if ((this.style & 0x400) != 0x0) {
                rect.bottom = rectangle.y + rectangle.height - this.tabsHeight;
            }
            else {
                final RECT rect2 = rect;
                rect2.top += this.tabsHeight;
                rect.bottom = rectangle.y + rectangle.height;
            }
            final int[] partId = this.getPartId(0);
            OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
            OS.CloseThemeData(openThemeData);
            if (this.tabsArea != null) {
                this.tabsArea.x = rectangle.x;
                this.tabsArea.y = rectangle.y;
                this.tabsArea.width = rectangle.width;
                this.tabsArea.height = this.tabsHeight;
                if ((this.style & 0x400) != 0x0) {
                    final Rectangle tabsArea = this.tabsArea;
                    tabsArea.y += rectangle.height - this.tabsHeight;
                }
            }
        }
    }
    
    char[] getClassId() {
        return TabFolderDrawData.TAB;
    }
    
    int[] getPartId(final int n) {
        final int n2 = this.state[n];
        final int n3 = 9;
        int n4 = 1;
        if ((n2 & 0x20) != 0x0) {
            n4 = 4;
        }
        else {
            if ((n2 & 0x40) != 0x0) {
                n4 = 2;
            }
            if ((n2 & 0x2) != 0x0) {
                n4 = 3;
            }
        }
        return new int[] { n3, n4 };
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (!rectangle.contains(point)) {
            return -1;
        }
        return 0;
    }
}
