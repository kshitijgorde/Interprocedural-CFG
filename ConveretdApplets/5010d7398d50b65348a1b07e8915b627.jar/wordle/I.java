// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import wordle.core.b.a.e;
import javax.swing.Action;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;

public final class I extends JRadioButtonMenuItem
{
    private Color[] a;
    private Color b;
    private final int c;
    
    public I(final Action action, final e e, final int c) {
        super(action);
        this.a(e);
        this.c = c;
    }
    
    public final void a(final e e) {
        this.a = e.a();
        this.b = e.b();
    }
    
    public final Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        return new Dimension(32 + preferredSize.width + 16 + 16 * this.a.length, Math.max(16, preferredSize.height));
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Math.max(16, super.getPreferredSize().height));
    }
    
    protected final void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(this.getModel().isArmed() ? new Color(128) : this.getBackground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(this.getModel().isArmed() ? Color.WHITE : this.getForeground());
        graphics.setFont(this.getFont());
        graphics.drawString(this.getText(), 24, this.getHeight() - this.getInsets().bottom - graphics.getFontMetrics().getDescent() - 2);
        if (this.isSelected()) {
            graphics.fillOval(8, this.getHeight() / 2 - 4, 8, 8);
        }
        int n = this.c + 48;
        final int n2 = this.getHeight() / 2 - 6;
        graphics.setColor(this.b);
        graphics.fillRect(n - 3, n2 - 3, this.a.length * 15 + 5, 18);
        Color[] a;
        for (int length = (a = this.a).length, i = 0; i < length; ++i) {
            graphics.setColor(a[i]);
            graphics.fillRoundRect(n, n2, 12, 12, 5, 5);
            n += 15;
        }
    }
}
