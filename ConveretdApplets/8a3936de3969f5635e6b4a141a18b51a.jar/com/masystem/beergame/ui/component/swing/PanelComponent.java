// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import java.awt.event.MouseAdapter;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import com.masystem.beergame.ui.scene.PaintProperties;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class PanelComponent extends JPanel implements HasPaintProperties
{
    private static final long serialVersionUID = 1L;
    private static MouseListener MOUSE_BLOCKER;
    private PaintProperties paintProperties;
    
    public PanelComponent() {
        this((Color)null);
    }
    
    public PanelComponent(final Color background) {
        this.paintProperties = new PaintProperties();
        this.setBackground(background);
        this.setPreferredSize(new Dimension(0, 0));
    }
    
    @Override
    public void setBackground(final Color background) {
        if (background != null) {
            this.addMouseListener(PanelComponent.MOUSE_BLOCKER);
        }
        else {
            this.setOpaque(false);
        }
        super.setBackground(background);
    }
    
    @Override
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
        super.paint(graphics2);
        graphics2.dispose();
    }
    
    static {
        PanelComponent.MOUSE_BLOCKER = new MouseAdapter() {};
    }
}
