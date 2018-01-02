// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.main;

import java.awt.Color;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import jmaster.jumploader.model.api.C.C;
import jmaster.jumploader.model.api.file.IFile;
import java.awt.event.ComponentEvent;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import jmaster.util.swing.GUIHelper;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import jmaster.jumploader.model.api.C.A;
import javax.swing.JLabel;
import jmaster.util.swing.icon.ProgressIcon;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.C.B;
import java.awt.event.ComponentListener;
import jmaster.jumploader.view.impl.GenericView;

public class ThumbnailManagerProgressView extends GenericView implements ComponentListener, B
{
    private static final long \u00cc = -6680564599454481448L;
    jmaster.jumploader.model.api.B F;
    IMainView \u00d5;
    ProgressIcon \u00cd;
    JLabel \u00ce;
    JLabel \u00d0;
    boolean \u00cf;
    A \u00d2;
    int width;
    int height;
    JLayeredPane \u00d4;
    boolean \u00d1;
    private float \u00d3;
    private Rectangle \u00cb;
    
    public ThumbnailManagerProgressView(final jmaster.jumploader.model.api.B f, final IMainView \u00f5) {
        super(f);
        this.\u00cd = new ProgressIcon();
        this.\u00ce = new JLabel();
        this.\u00d0 = new JLabel();
        this.width = 168;
        this.height = 36;
        this.\u00d1 = true;
        this.\u00d3 = 0.5f;
        this.F = f;
        this.\u00d5 = \u00f5;
        GUIHelper.getInstance().injectProperties(this, "thumbnailManagerProgressView");
        GUIHelper.getInstance().injectProperties(this.\u00cd, "thumbnailManagerProgressIcon");
        GUIHelper.getInstance().injectProperties(this.\u00d0, "thumbnailManagerProgressTextLabel");
        this.\u00ce.setIcon(this.\u00cd);
        final GridBagConstraints gbc = this.I.newGBC();
        this.setLayout(new GridBagLayout());
        this.add(this.\u00d0, this.A(gbc, 0, 0, 1, 1, 1, 1, 2, 10, GUIHelper.INSETS2));
        this.add(this.\u00ce, this.A(gbc, 0, 1, 1, 1, 1, 1, 2, 10, GUIHelper.INSETS2));
        this.setOpaque(false);
    }
    
    public boolean isInitialized() {
        return this.\u00cf;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.\u00d1 = true;
        this.updateView();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void thumbnailManagerThumbnailChanged(final A a, final IFile file, final C c) {
        this.updateView();
    }
    
    public void thumbnailManagerStatusChanged(final A a) {
        this.updateView();
    }
    
    public void init() {
        try {
            if (this.F.H().isThumbnailManagerProgressViewEnabled()) {
                this.\u00d2 = this.F.A();
                this.\u00d4 = this.F();
                if (this.\u00d4 != null) {
                    this.\u00d4.addComponentListener(this);
                    this.\u00d4.add(this, JLayeredPane.POPUP_LAYER);
                    this.\u00d4.validate();
                    this.updateView();
                    this.F.A().A(this);
                }
            }
        }
        finally {
            this.\u00cf = true;
        }
    }
    
    private JLayeredPane F() {
        JLayeredPane layeredPane = null;
        for (Component parent = (Component)this.\u00d5.getProgramView(); layeredPane == null && parent != null; parent = parent.getParent()) {
            if (parent instanceof JLayeredPane) {
                layeredPane = (JLayeredPane)parent;
            }
        }
        return layeredPane;
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        if (this.\u00d1) {
            this.setBounds(this.\u00d4.getBounds().width - this.width - this.F.H().getThumbnailManagerProgressViewHorizontalMargin(), this.F.H().getThumbnailManagerProgressViewVerticalMargin(), this.width, this.height);
            this.\u00cb = this.getBounds();
            this.\u00d1 = false;
        }
        this.\u00cd.setCompleted(this.\u00d2.D());
        this.repaint();
        this.setVisible(this.\u00d2.B());
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Composite composite = graphics2D.getComposite();
        graphics2D.setComposite(AlphaComposite.getInstance(3, this.\u00d3));
        if (this.\u00cb != null) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, this.\u00cb.width, this.\u00cb.height);
        }
        super.paint(graphics);
        graphics2D.setComposite(composite);
    }
}
