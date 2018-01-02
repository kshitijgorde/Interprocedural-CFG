// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.Cursor;
import javax.swing.JTable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class p extends MouseMotionAdapter
{
    public final void mouseMoved(final MouseEvent mouseEvent) {
        final JTable table;
        final int columnAtPoint;
        if ((columnAtPoint = (table = (JTable)mouseEvent.getSource()).columnAtPoint(mouseEvent.getPoint())) == 3 || columnAtPoint == 6) {
            table.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        table.setCursor(Cursor.getDefaultCursor());
    }
}
