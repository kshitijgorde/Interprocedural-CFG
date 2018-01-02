// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

class CTabFolderLayout extends Layout
{
    protected Point computeSize(final Composite composite, final int n, final int n2, final boolean b) {
        final CTabFolder cTabFolder = (CTabFolder)composite;
        final CTabItem[] items = cTabFolder.items;
        final CTabFolderRenderer renderer = cTabFolder.renderer;
        int max = 0;
        int selectedIndex = cTabFolder.selectedIndex;
        if (selectedIndex == -1) {
            selectedIndex = 0;
        }
        final GC gc = new GC(cTabFolder);
        for (int i = 0; i < items.length; ++i) {
            if (cTabFolder.single) {
                max = Math.max(max, renderer.computeSize(i, 2, gc, -1, -1).x);
            }
            else {
                int n3 = 0;
                if (i == selectedIndex) {
                    n3 |= 0x2;
                }
                max += renderer.computeSize(i, n3, gc, -1, -1).x;
            }
        }
        max += 3;
        if (cTabFolder.showMax) {
            max += renderer.computeSize(-5, 0, gc, -1, -1).x;
        }
        if (cTabFolder.showMin) {
            max += renderer.computeSize(-6, 0, gc, -1, -1).x;
        }
        if (cTabFolder.single) {
            max += renderer.computeSize(-7, 0, gc, -1, -1).x;
        }
        if (cTabFolder.topRight != null) {
            max += 3 + cTabFolder.topRight.computeSize(-1, cTabFolder.tabHeight, b).x;
        }
        gc.dispose();
        int max2 = 0;
        int max3 = 0;
        for (int j = 0; j < items.length; ++j) {
            final Control control = items[j].getControl();
            if (control != null && !control.isDisposed()) {
                final Point computeSize = control.computeSize(n, n2, b);
                max2 = Math.max(max2, computeSize.x);
                max3 = Math.max(max3, computeSize.y);
            }
        }
        int max4 = Math.max(max, max2);
        int n4 = cTabFolder.minimized ? 0 : max3;
        if (max4 == 0) {
            max4 = 64;
        }
        if (n4 == 0) {
            n4 = 64;
        }
        if (n != -1) {
            max4 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        return new Point(max4, n4);
    }
    
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final CTabFolder cTabFolder = (CTabFolder)composite;
        if (cTabFolder.selectedIndex != -1) {
            final Control control = cTabFolder.items[cTabFolder.selectedIndex].getControl();
            if (control != null && !control.isDisposed()) {
                control.setBounds(cTabFolder.getClientArea());
            }
        }
    }
}
