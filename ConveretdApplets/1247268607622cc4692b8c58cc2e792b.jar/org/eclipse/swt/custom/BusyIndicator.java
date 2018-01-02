// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class BusyIndicator
{
    static int nextBusyId;
    static final String BUSYID_NAME = "SWT BusyIndicator";
    static final String BUSY_CURSOR = "SWT BusyIndicator Cursor";
    
    static {
        BusyIndicator.nextBusyId = 1;
    }
    
    public static void showWhile(Display current, final Runnable runnable) {
        if (runnable == null) {
            SWT.error(4);
        }
        if (current == null) {
            current = Display.getCurrent();
            if (current == null) {
                runnable.run();
                return;
            }
        }
        final Integer n = new Integer(BusyIndicator.nextBusyId);
        ++BusyIndicator.nextBusyId;
        final Cursor systemCursor = current.getSystemCursor(1);
        final Shell[] shells = current.getShells();
        for (int i = 0; i < shells.length; ++i) {
            if (shells[i].getData("SWT BusyIndicator") == null) {
                shells[i].setCursor(systemCursor);
                shells[i].setData("SWT BusyIndicator", n);
            }
        }
        try {
            runnable.run();
        }
        finally {
            final Shell[] shells2 = current.getShells();
            for (int j = 0; j < shells2.length; ++j) {
                if (shells2[j].getData("SWT BusyIndicator") == n) {
                    shells2[j].setCursor(null);
                    shells2[j].setData("SWT BusyIndicator", null);
                }
            }
        }
        final Shell[] shells3 = current.getShells();
        for (int k = 0; k < shells3.length; ++k) {
            if (shells3[k].getData("SWT BusyIndicator") == n) {
                shells3[k].setCursor(null);
                shells3[k].setData("SWT BusyIndicator", null);
            }
        }
    }
}
