// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;

public class ScrollBarDrawData extends RangeDrawData
{
    public int thumb;
    public int increment;
    public int pageIncrement;
    
    public ScrollBarDrawData() {
        this.state = new int[6];
    }
    
    void draw(final Theme theme, final GC gc, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            final RECT rect = new RECT();
            if ((this.style & 0x200) != 0x0) {
                final int getThemeSysSize = OS.GetThemeSysSize(openThemeData, 2);
                rect.left = rectangle.x;
                rect.right = rect.left + rectangle.width;
                rect.top = rectangle.y;
                rect.bottom = rect.top + getThemeSysSize;
                final int[] partId = this.getPartId(1);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId[0], partId[1], rect, null);
                rect.bottom = rectangle.y + rectangle.height;
                rect.top = rect.bottom - getThemeSysSize;
                final int[] partId2 = this.getPartId(2);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId2[0], partId2[1], rect, null);
                final int n = rectangle.height - 2 * getThemeSysSize;
                final int max = Math.max(getThemeSysSize / 2, n * this.thumb / Math.max(1, this.maximum - this.minimum));
                final int bottom = rectangle.y + getThemeSysSize + Math.max(0, n * this.selection / Math.max(1, this.maximum - this.minimum));
                rect.top = rectangle.y + getThemeSysSize;
                rect.bottom = bottom;
                final int[] partId3 = this.getPartId(3);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId3[0], partId3[1], rect, null);
                rect.top = rect.bottom;
                rect.bottom = rect.top + max;
                final int[] partId4 = this.getPartId(5);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId4[0], partId4[1], rect, null);
                OS.DrawThemeBackground(openThemeData, gc.handle, 9, partId4[1], rect, null);
                rect.top = rect.bottom;
                rect.bottom = rectangle.y + rectangle.height - getThemeSysSize;
                final int[] partId5 = this.getPartId(4);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId5[0], partId5[1], rect, null);
            }
            else {
                final int getThemeSysSize2 = OS.GetThemeSysSize(openThemeData, 2);
                rect.top = rectangle.y;
                rect.bottom = rect.top + rectangle.height;
                rect.left = rectangle.x;
                rect.right = rect.left + getThemeSysSize2;
                final int[] partId6 = this.getPartId(1);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId6[0], partId6[1], rect, null);
                rect.right = rectangle.x + rectangle.width;
                rect.left = rect.right - getThemeSysSize2;
                final int[] partId7 = this.getPartId(2);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId7[0], partId7[1], rect, null);
                final int n2 = rectangle.width - 2 * getThemeSysSize2;
                final int max2 = Math.max(getThemeSysSize2 / 2, n2 * this.thumb / (this.maximum - this.minimum));
                final int right = rectangle.x + getThemeSysSize2 + Math.max(0, n2 * this.selection / Math.max(1, this.maximum - this.minimum));
                rect.left = rectangle.x + getThemeSysSize2;
                rect.right = right;
                final int[] partId8 = this.getPartId(3);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId8[0], partId8[1], rect, null);
                rect.left = rect.right;
                rect.right = rect.left + max2;
                final int[] partId9 = this.getPartId(5);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId9[0], partId9[1], rect, null);
                OS.DrawThemeBackground(openThemeData, gc.handle, 8, partId9[1], rect, null);
                rect.left = rect.right;
                rect.right = rectangle.x + rectangle.width - getThemeSysSize2;
                final int[] partId10 = this.getPartId(4);
                OS.DrawThemeBackground(openThemeData, gc.handle, partId10[0], partId10[1], rect, null);
            }
            OS.CloseThemeData(openThemeData);
        }
    }
    
    char[] getClassId() {
        return ScrollBarDrawData.SCROLLBAR;
    }
    
    int[] getPartId(final int n) {
        int n2 = 0;
        int n3 = 0;
        final int n4 = this.state[n];
        switch (n) {
            case 1: {
                n2 = 1;
                if ((this.style & 0x200) != 0x0) {
                    n3 = 1;
                    if ((n4 & 0x40) != 0x0) {
                        n3 = 2;
                    }
                    if ((n4 & 0x8) != 0x0) {
                        n3 = 3;
                    }
                    if ((n4 & 0x20) != 0x0) {
                        n3 = 4;
                        break;
                    }
                    break;
                }
                else {
                    n3 = 9;
                    if ((n4 & 0x40) != 0x0) {
                        n3 = 10;
                    }
                    if ((n4 & 0x8) != 0x0) {
                        n3 = 11;
                    }
                    if ((n4 & 0x20) != 0x0) {
                        n3 = 12;
                        break;
                    }
                    break;
                }
                break;
            }
            case 2: {
                n2 = 1;
                if ((this.style & 0x200) != 0x0) {
                    n3 = 5;
                    if ((n4 & 0x40) != 0x0) {
                        n3 = 6;
                    }
                    if ((n4 & 0x8) != 0x0) {
                        n3 = 7;
                    }
                    if ((n4 & 0x20) != 0x0) {
                        n3 = 8;
                        break;
                    }
                    break;
                }
                else {
                    n3 = 13;
                    if ((n4 & 0x40) != 0x0) {
                        n3 = 14;
                    }
                    if ((n4 & 0x8) != 0x0) {
                        n3 = 15;
                    }
                    if ((n4 & 0x20) != 0x0) {
                        n3 = 16;
                        break;
                    }
                    break;
                }
                break;
            }
            case 0:
            case 5: {
                if ((this.style & 0x200) != 0x0) {
                    n2 = 3;
                    break;
                }
                n2 = 2;
                break;
            }
            case 3: {
                if ((this.style & 0x200) != 0x0) {
                    n2 = 7;
                    break;
                }
                n2 = 5;
                break;
            }
            case 4: {
                if ((this.style & 0x200) != 0x0) {
                    n2 = 6;
                    break;
                }
                n2 = 4;
                break;
            }
        }
        if (n != 2 && n != 1) {
            n3 = 1;
            if ((n4 & 0x40) != 0x0) {
                n3 = 2;
            }
            if ((n4 & 0x8) != 0x0) {
                n3 = 3;
            }
            if ((n4 & 0x20) != 0x0) {
                n3 = 4;
            }
        }
        return new int[] { n2, n3 };
    }
    
    Rectangle getBounds(final int n, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            if ((this.style & 0x200) != 0x0) {
                final int getThemeSysSize = OS.GetThemeSysSize(openThemeData, 2);
                final int n2 = rectangle.height - 2 * getThemeSysSize;
                final int max = Math.max(getThemeSysSize / 2, n2 * this.thumb / Math.max(1, this.maximum - this.minimum));
                final int n3 = rectangle.y + getThemeSysSize + Math.max(0, n2 * this.selection / Math.max(1, this.maximum - this.minimum));
                switch (n) {
                    case 2: {
                        return new Rectangle(rectangle.x, rectangle.y + rectangle.height - getThemeSysSize, rectangle.width, getThemeSysSize);
                    }
                    case 1: {
                        return new Rectangle(rectangle.x, rectangle.y, rectangle.width, getThemeSysSize);
                    }
                    case 3: {
                        return new Rectangle(rectangle.x, rectangle.y + getThemeSysSize, rectangle.width, n3 - rectangle.y - getThemeSysSize);
                    }
                    case 5: {
                        return new Rectangle(rectangle.x, n3, rectangle.width, max);
                    }
                    case 4: {
                        return new Rectangle(rectangle.x, n3 + max, rectangle.width, rectangle.y + rectangle.height - getThemeSysSize - n3 - max);
                    }
                }
            }
            OS.CloseThemeData(openThemeData);
        }
        return super.getBounds(n, rectangle);
    }
    
    int getSelection(final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int openThemeData = OS.OpenThemeData(0, this.getClassId());
            if ((this.style & 0x200) != 0x0) {
                final int getThemeSysSize = OS.GetThemeSysSize(openThemeData, 2);
                final int n = rectangle.height - 2 * getThemeSysSize;
                return Math.max(0, Math.min((rectangle.y + getThemeSysSize + Math.max(0, n * this.selection / Math.max(1, this.maximum - this.minimum)) + point.y - rectangle.y - getThemeSysSize) * (this.maximum - this.minimum) / n, this.maximum - this.thumb));
            }
            OS.CloseThemeData(openThemeData);
        }
        return 0;
    }
    
    int hit(final Theme theme, final Point point, final Rectangle rectangle) {
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            return -1;
        }
        final int openThemeData = OS.OpenThemeData(0, this.getClassId());
        final int n = 0;
        final RECT rect = new RECT();
        final POINT point2 = new POINT();
        point2.x = point.x;
        point2.y = point.y;
        final short[] array = { 0 };
        Label_1009: {
            try {
                if ((this.style & 0x200) != 0x0) {
                    final int getThemeSysSize = OS.GetThemeSysSize(openThemeData, 2);
                    rect.left = rectangle.x;
                    rect.right = rect.left + rectangle.width;
                    rect.top = rectangle.y;
                    rect.bottom = rect.top + getThemeSysSize;
                    final int[] partId = this.getPartId(1);
                    OS.HitTestThemeBackground(openThemeData, n, partId[0], partId[1], 0, rect, 0, point2, array);
                    if (array[0] == 0) {
                        rect.bottom = rectangle.y + rectangle.height;
                        rect.top = rect.bottom - getThemeSysSize;
                        final int[] partId2 = this.getPartId(2);
                        OS.HitTestThemeBackground(openThemeData, n, partId2[0], partId2[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 2;
                        }
                        final int n2 = rectangle.height - 2 * getThemeSysSize;
                        final int max = Math.max(getThemeSysSize / 2, n2 * this.thumb / Math.max(1, this.maximum - this.minimum));
                        final int bottom = rectangle.y + getThemeSysSize + Math.max(0, n2 * this.selection / Math.max(1, this.maximum - this.minimum));
                        rect.top = rectangle.y + getThemeSysSize;
                        rect.bottom = bottom;
                        final int[] partId3 = this.getPartId(5);
                        OS.HitTestThemeBackground(openThemeData, n, partId3[0], partId3[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 3;
                        }
                        rect.top = rect.bottom;
                        rect.bottom = rect.top + max;
                        final int[] partId4 = this.getPartId(3);
                        OS.HitTestThemeBackground(openThemeData, n, partId4[0], partId4[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 5;
                        }
                        rect.top = rect.bottom;
                        rect.bottom = rectangle.y + rectangle.height - getThemeSysSize;
                        final int[] partId5 = this.getPartId(4);
                        OS.HitTestThemeBackground(openThemeData, n, partId5[0], partId5[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 4;
                        }
                        break Label_1009;
                    }
                }
                else {
                    final int getThemeSysSize2 = OS.GetThemeSysSize(openThemeData, 2);
                    rect.top = rectangle.y;
                    rect.bottom = rect.top + rectangle.height;
                    rect.left = rectangle.x;
                    rect.right = rect.left + getThemeSysSize2;
                    final int[] partId6 = this.getPartId(1);
                    OS.HitTestThemeBackground(openThemeData, n, partId6[0], partId6[1], 0, rect, 0, point2, array);
                    if (array[0] == 0) {
                        rect.right = rectangle.x + rectangle.width;
                        rect.left = rect.right - getThemeSysSize2;
                        final int[] partId7 = this.getPartId(2);
                        OS.HitTestThemeBackground(openThemeData, n, partId7[0], partId7[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 2;
                        }
                        final int n3 = rectangle.width - 2 * getThemeSysSize2;
                        final int max2 = Math.max(getThemeSysSize2 / 2, n3 * this.thumb / (this.maximum - this.minimum));
                        final int right = rectangle.x + getThemeSysSize2 + Math.max(0, n3 * this.selection / Math.max(1, this.maximum - this.minimum));
                        rect.left = rectangle.x + getThemeSysSize2;
                        rect.right = right;
                        final int[] partId8 = this.getPartId(3);
                        OS.HitTestThemeBackground(openThemeData, n, partId8[0], partId8[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 3;
                        }
                        rect.left = rect.right;
                        rect.right = rect.left + max2;
                        final int[] partId9 = this.getPartId(5);
                        OS.HitTestThemeBackground(openThemeData, n, partId9[0], partId9[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 5;
                        }
                        rect.left = rect.right;
                        rect.right = rectangle.x + rectangle.width - getThemeSysSize2;
                        final int[] partId10 = this.getPartId(4);
                        OS.HitTestThemeBackground(openThemeData, n, partId10[0], partId10[1], 0, rect, 0, point2, array);
                        if (array[0] != 0) {
                            return 4;
                        }
                        break Label_1009;
                    }
                }
                return 1;
            }
            finally {
                OS.CloseThemeData(openThemeData);
            }
        }
        OS.CloseThemeData(openThemeData);
        return -1;
    }
}
