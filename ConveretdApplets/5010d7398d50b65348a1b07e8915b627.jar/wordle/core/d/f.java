// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.awt.event.FocusListener;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.JComponent;

final class f extends JComponent
{
    private /* synthetic */ g a;
    
    private f(final g a, final byte b) {
        this.a = a;
        this.setPreferredSize(new Dimension(100, 100));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createBevelBorder(0));
    }
    
    protected final void paintComponent(final Graphics graphics) {
        graphics.setColor((this.a.c == null) ? this.a.getBackground() : this.a.c);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
