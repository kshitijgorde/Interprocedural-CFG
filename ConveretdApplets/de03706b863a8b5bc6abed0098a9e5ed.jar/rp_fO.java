import java.awt.geom.RoundRectangle2D;
import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Dimension;
import javax.swing.JButton;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fO extends JButton
{
    private Dimension a;
    private Shape a;
    
    public rp_fO(final String s) {
        this(s, -1, -1);
    }
    
    private rp_fO(final String s, final int n, final int n2) {
        super(s);
        (this.a = new Dimension()).setSize(-1, -1);
        this.setBackground(rp_aJ.o);
        this.setFont(rp_aJ.a);
        this.setContentAreaFilled(false);
    }
    
    public final Dimension getPreferredSize() {
        if (this.getGraphics() == null) {
            return super.getPreferredSize();
        }
        final Rectangle bounds = this.getGraphics().getFontMetrics().getStringBounds(this.getText(), this.getGraphics()).getBounds();
        if (this.a.height == -1) {
            this.a.height = bounds.height + 9;
        }
        if (this.a.width == -1) {
            this.a.width = bounds.width + 14;
        }
        return this.a;
    }
    
    protected final void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.getModel().isArmed()) {
            graphics.setColor(rp_aJ.p);
        }
        else {
            graphics.setColor(this.getBackground());
        }
        graphics.fillRoundRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, 7, 7);
        graphics.setColor(rp_aJ.r);
        if (!this.isEnabled()) {
            graphics.setColor(Color.GRAY);
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final String text = this.getText();
        final float n = this.getSize().width / 2.0f;
        final float n2 = this.getSize().height / 2.0f;
        final float n3 = n;
        final String s = text;
        final Graphics2D graphics2D2 = graphics2D;
        final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        final float n4 = n3 - (float)graphics2D2.getFont().getStringBounds(s, fontRenderContext).getWidth() / 2.0f;
        final LineMetrics lineMetrics = graphics2D2.getFont().getLineMetrics(s, fontRenderContext);
        graphics2D2.drawString(s, n4, n2 - lineMetrics.getHeight() / 2.0f + lineMetrics.getAscent());
    }
    
    protected final void paintBorder(final Graphics graphics) {
        graphics.setColor(rp_aJ.q);
        graphics.drawRoundRect(0, 0, this.getSize().width - 1, this.getSize().height - 1, 7, 7);
    }
    
    public final boolean contains(final int n, final int n2) {
        if (this.a == null || !this.a.getBounds().equals(this.getBounds())) {
            this.a = new RoundRectangle2D.Float(0.0f, 0.0f, this.getWidth(), this.getHeight(), 7.0f, 7.0f);
        }
        return this.a.contains(n, n2);
    }
}
