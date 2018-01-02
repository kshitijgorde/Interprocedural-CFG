// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.main;

import javax.swing.JApplet;
import javax.swing.JFrame;
import jmaster.util.swing.SwingUtil;
import java.awt.Rectangle;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jmaster.jumploader.model.api.B;
import javax.swing.JLabel;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.view.impl.GenericView;

public class GlassView extends GenericView
{
    private static final long O = -6431897208624300272L;
    private static final String N = "glassView";
    protected IMainView K;
    protected JLabel M;
    private float L;
    
    public GlassView(final B b, final IMainView k) {
        super(b);
        this.M = new JLabel();
        this.L = 0.5f;
        this.K = k;
        this.I.injectProperties(this, "glassView");
        this.I.injectProperties(this.M, "glassView", "label");
        this.setLayout(new BorderLayout());
        this.add(this.M, "Center");
        this.M.setHorizontalAlignment(0);
        this.setOpaque(false);
    }
    
    public Icon getIcon() {
        return this.M.getIcon();
    }
    
    public String getText() {
        return this.M.getText();
    }
    
    public void setIcon(final Icon icon) {
        this.M.setIcon(icon);
    }
    
    public void setText(final String text) {
        this.M.setText(text);
    }
    
    public float getOpacity() {
        return this.L;
    }
    
    public void setOpacity(final float l) {
        this.L = l;
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
        final Composite composite = graphics2D.getComposite();
        graphics2D.setComposite(AlphaComposite.getInstance(3, this.L));
        final Rectangle bounds = this.getBounds();
        graphics2D.fillRect(0, 0, (int)bounds.getWidth(), (int)bounds.getHeight());
        graphics2D.setComposite(composite);
        super.paint(graphics);
    }
    
    public void setVisible(final boolean visible) {
        final JFrame parentJFrame = SwingUtil.getParentJFrame((Component)this.K);
        if (visible && parentJFrame != null) {
            parentJFrame.setGlassPane(this);
        }
        final JApplet parentJApplet = SwingUtil.getParentJApplet((Component)this.K);
        if (visible && parentJApplet != null) {
            parentJApplet.setGlassPane(this);
        }
        super.setVisible(visible);
    }
}
