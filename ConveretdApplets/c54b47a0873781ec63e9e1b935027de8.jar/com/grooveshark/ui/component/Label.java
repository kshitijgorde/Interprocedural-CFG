// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel
{
    private static final long serialVersionUID = -5601308057933156435L;
    
    public Label(final String text, final ImageIcon icon) {
        super(text, icon, 2);
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.setOpaque(false);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.addMouseListener(new MouseToActionListener(actionListener, ""));
    }
}
