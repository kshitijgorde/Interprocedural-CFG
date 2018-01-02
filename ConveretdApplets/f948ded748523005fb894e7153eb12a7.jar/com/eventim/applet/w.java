// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.Cursor;
import javax.swing.JTable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class w extends MouseAdapter
{
    private final EventimApplet a;
    
    w(final EventimApplet a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final JTable table;
        if ((table = (JTable)mouseEvent.getSource()).columnAtPoint(mouseEvent.getPoint()) == 6) {
            this.a.b(table.rowAtPoint(mouseEvent.getPoint()));
            return;
        }
        if (table.columnAtPoint(mouseEvent.getPoint()) == 3) {
            this.a.setCursor(Cursor.getPredefinedCursor(3));
            this.a.a(EventimApplet.c(this.a).a(table.rowAtPoint(mouseEvent.getPoint())));
            this.a.setCursor(Cursor.getDefaultCursor());
        }
    }
}
