// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;

public final class JAPMultilineLabel extends JPanel
{
    private Font m_font;
    
    public JAPMultilineLabel(final String s) {
        this(s, new JLabel().getFont());
    }
    
    public JAPMultilineLabel(final String s, final Color color) {
        this(s, new JLabel().getFont(), color);
    }
    
    public JAPMultilineLabel(final Font font) {
        this("", font);
    }
    
    public JAPMultilineLabel(final String s, final Font font) {
        this(s, font, null);
    }
    
    public JAPMultilineLabel(final String s, final Font font, final Color color) {
        this.m_font = font;
        this.setText(s, color);
    }
    
    public void setText(final String s, final Color foreground) {
        this.removeAll();
        this.setLayout(new GridLayout(0, 1, 0, 0));
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        while (stringTokenizer.hasMoreElements()) {
            final JLabel label = new JLabel(stringTokenizer.nextToken());
            if (this.m_font != null) {
                label.setFont(this.m_font);
            }
            if (foreground != null) {
                label.setForeground(foreground);
            }
            this.add(label);
        }
    }
    
    public void setText(final String s) {
        this.setText(s, null);
    }
}
