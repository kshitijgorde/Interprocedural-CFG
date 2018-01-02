// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

final class o implements ListCellRenderer
{
    o(final n n) {
    }
    
    public final Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final JLabel label;
        (label = new JLabel()).setText(o.toString());
        label.setOpaque(true);
        if (n == 0) {
            label.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray), BorderFactory.createEmptyBorder(1, 10, 1, 0)));
        }
        else {
            label.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 0));
        }
        if (list.getSelectedIndex() == n) {
            label.setOpaque(true);
            label.setBackground(Color.blue);
            label.setForeground(Color.white);
        }
        return label;
    }
}
