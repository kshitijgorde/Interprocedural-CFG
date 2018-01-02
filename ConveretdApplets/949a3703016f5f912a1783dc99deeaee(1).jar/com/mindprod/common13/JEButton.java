// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common13;

import javax.swing.BorderFactory;
import com.mindprod.common11.FontFactory;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.JButton;

public final class JEButton extends JButton
{
    private static final Border border;
    private static final Font boldFont;
    private static final Font plainFont;
    
    public JEButton(final String text) {
        this(text, true);
    }
    
    public JEButton(final String text, final boolean bold) {
        super(text);
        this.setFocusPainted(false);
        this.setBorder(JEButton.border);
        this.setFont(bold ? JEButton.boldFont : JEButton.plainFont);
    }
    
    static {
        boldFont = FontFactory.build("Arrows", 1, 16);
        plainFont = FontFactory.build("Arrows", 0, 14);
        final Border innerBorder = BorderFactory.createEmptyBorder(2, 5, 2, 5);
        final Border outerBorder = BorderFactory.createRaisedBevelBorder();
        border = BorderFactory.createCompoundBorder(outerBorder, innerBorder);
    }
}
