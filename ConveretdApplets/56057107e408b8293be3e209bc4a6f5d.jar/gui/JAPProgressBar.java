// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Dimension;
import javax.swing.JComponent;
import java.awt.Graphics;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.plaf.ProgressBarUI;
import java.awt.Color;
import javax.swing.JProgressBar;

public class JAPProgressBar extends JProgressBar
{
    private static final long serialVersionUID = 1L;
    private final MyProgressBarUI m_ui;
    
    public JAPProgressBar() {
        (this.m_ui = new MyProgressBarUI(true)).setFilledBarColor(Color.blue);
        super.setUI(this.m_ui);
    }
    
    public void setUI(final ProgressBarUI progressBarUI) {
    }
    
    public ProgressBarUI getUI() {
        return this.m_ui;
    }
    
    public void setFilledBarColor(final Color filledBarColor) {
        this.m_ui.setFilledBarColor(filledBarColor);
    }
    
    public Color getFilledbarColor() {
        return this.m_ui.getFilledBarColor();
    }
    
    private final class MyProgressBarUI extends BasicProgressBarUI
    {
        static final int ms_dx = 13;
        static final int ms_width = 9;
        private boolean m_bOneBarPerValue;
        private Color m_colFilledBar;
        
        public MyProgressBarUI(final boolean bOneBarPerValue) {
            this.m_bOneBarPerValue = false;
            this.m_bOneBarPerValue = bOneBarPerValue;
            this.m_colFilledBar = null;
        }
        
        public void paint(final Graphics graphics, final JComponent component) {
            final JProgressBar progressBar = (JProgressBar)component;
            final int maximum = progressBar.getMaximum();
            final int n = progressBar.getWidth() / 13;
            final int n2 = progressBar.getValue() * n / maximum;
            int n3 = 0;
            final int n4 = 0;
            final int n5 = component.getHeight() - 1;
            final Color color = graphics.getColor();
            if (this.m_colFilledBar != null) {
                graphics.setColor(this.m_colFilledBar);
            }
            for (int i = 0; i < n2; ++i) {
                graphics.fill3DRect(n3, n4, 9, n5 + 1, false);
                n3 += 13;
            }
            graphics.setColor(color);
            for (int j = n2; j < n; ++j) {
                graphics.draw3DRect(n3, n4, 9, n5, false);
                n3 += 13;
            }
        }
        
        public void setFilledBarColor(final Color colFilledBar) {
            this.m_colFilledBar = colFilledBar;
        }
        
        public Color getFilledBarColor() {
            return this.m_colFilledBar;
        }
        
        public Dimension getPreferredSize(final JComponent component) {
            if (!this.m_bOneBarPerValue) {
                return super.getPreferredSize(component);
            }
            return new Dimension(13 * ((JProgressBar)component).getMaximum(), 12);
        }
        
        public Dimension getMinimumSize(final JComponent component) {
            if (!this.m_bOneBarPerValue) {
                return super.getMinimumSize(component);
            }
            return this.getPreferredSize(component);
        }
        
        public Dimension getMaximumSize(final JComponent component) {
            if (!this.m_bOneBarPerValue) {
                return super.getMaximumSize(component);
            }
            return this.getPreferredSize(component);
        }
    }
}
