// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.JComponent;
import org.xidget.ifeature.IWidgetCreationFeature;
import java.awt.event.ComponentListener;
import org.xidget.swing.layout.WidgetBoundsListener;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.Border;
import org.xidget.layout.Margins;
import org.xidget.layout.Bounds;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xmodel.log.Log;
import org.xidget.ifeature.IWidgetFeature;

public class SwingWidgetFeature implements IWidgetFeature
{
    public static final Log log;
    protected IXidget xidget;
    protected IModelObject boundsNode;
    protected Bounds defaultBounds;
    protected Bounds computedBounds;
    protected boolean clampBounds;
    protected Margins insideMargins;
    protected Margins outsideMargins;
    protected Border insideBorder;
    protected Border outsideBorder;
    
    static {
        log = Log.getLog(SwingWidgetFeature.class);
    }
    
    public SwingWidgetFeature(final IXidget xidget) {
        this.defaultBounds = new Bounds();
        this.computedBounds = new Bounds();
        this.xidget = xidget;
        this.defaultBounds = new Bounds(0.0f, 0.0f, -1.0f, -1.0f);
        this.computedBounds = new Bounds(0.0f, 0.0f, -1.0f, -1.0f);
    }
    
    @Override
    public void setDefaultBounds(final float x, final float y, final float width, final float height, final boolean clampBounds) {
        this.defaultBounds.x = x;
        this.defaultBounds.y = y;
        this.defaultBounds.width = width;
        this.defaultBounds.height = height;
        this.clampBounds = clampBounds;
    }
    
    @Override
    public Bounds getDefaultBounds() {
        final Dimension preferredSize = this.xidget.getFeature(Component.class).getPreferredSize();
        if (this.defaultBounds.width < 0.0f) {
            this.defaultBounds.width = preferredSize.width;
        }
        if (this.defaultBounds.height < 0.0f) {
            this.defaultBounds.height = preferredSize.height;
        }
        return this.defaultBounds;
    }
    
    @Override
    public void setComputedBounds(final float x, final float y, final float width, final float height) {
        if (x == this.computedBounds.x && y == this.computedBounds.y && width == this.computedBounds.width && height == this.computedBounds.height) {
            return;
        }
        this.computedBounds.x = x;
        this.computedBounds.y = y;
        this.computedBounds.width = width;
        this.computedBounds.height = height;
        if (!this.clampBounds) {
            this.setDefaultBounds(x, y, width, height, false);
        }
        this.xidget.getFeature(Component.class).setBounds(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }
    
    @Override
    public Bounds getComputedBounds() {
        return this.computedBounds;
    }
    
    @Override
    public void setBoundsNode(final IModelObject boundsNode) {
        this.boundsNode = boundsNode;
        if (boundsNode != null) {
            final Component component = this.xidget.getFeature(Component.class);
            if (component != null) {
                component.addComponentListener(new WidgetBoundsListener(this.xidget));
            }
        }
    }
    
    @Override
    public IModelObject getBoundsNode() {
        return this.boundsNode;
    }
    
    @Override
    public void setInsideMargins(final Margins insideMargins) {
        this.insideMargins = insideMargins;
        final Object[] lastWidgets = this.xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
        final JComponent component = (JComponent)lastWidgets[lastWidgets.length - 1];
        if (component != null) {
            Border border = component.getBorder();
            if (border != null && border == this.insideBorder) {
                border = ((border instanceof CompoundBorder) ? ((CompoundBorder)border).getOutsideBorder() : null);
            }
            if (border == null) {
                component.setBorder(this.insideBorder = BorderFactory.createEmptyBorder(insideMargins.y0, insideMargins.x0, insideMargins.y1, insideMargins.x1));
            }
            else {
                this.insideBorder = BorderFactory.createEmptyBorder(insideMargins.y0, insideMargins.x0, insideMargins.y1, insideMargins.x1);
                component.setBorder(BorderFactory.createCompoundBorder(border, this.insideBorder));
            }
        }
    }
    
    @Override
    public Margins getInsideMargins() {
        if (this.insideMargins == null) {
            this.insideMargins = new Margins();
            final JComponent component = this.xidget.getFeature(JComponent.class);
            if (component != null) {
                final Insets insets = component.getInsets();
                this.insideMargins.x0 = insets.left;
                this.insideMargins.y0 = insets.top;
                this.insideMargins.x1 = insets.right;
                this.insideMargins.y1 = insets.bottom;
            }
        }
        return this.insideMargins;
    }
    
    @Override
    public void setOutsideMargins(final Margins outsideMargins) {
        this.outsideMargins = outsideMargins;
        final JComponent component = (JComponent)this.xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets()[0];
        if (component != null) {
            Border border = component.getBorder();
            if (border != null && border == this.outsideBorder) {
                border = ((border instanceof CompoundBorder) ? ((CompoundBorder)border).getInsideBorder() : null);
            }
            if (border == null) {
                component.setBorder(this.outsideBorder = BorderFactory.createEmptyBorder(outsideMargins.y0, outsideMargins.x0, outsideMargins.y1, outsideMargins.x1));
            }
            else {
                this.outsideBorder = BorderFactory.createEmptyBorder(outsideMargins.y0, outsideMargins.x0, outsideMargins.y1, outsideMargins.x1);
                component.setBorder(BorderFactory.createCompoundBorder(this.outsideBorder, border));
            }
        }
    }
    
    @Override
    public Margins getOutsideMargins() {
        if (this.outsideMargins == null) {
            this.outsideMargins = new Margins();
        }
        return this.outsideMargins;
    }
    
    @Override
    public void setVisible(final boolean visible) {
        this.xidget.getFeature(Component.class).setVisible(visible);
    }
    
    @Override
    public boolean getVisible() {
        return this.xidget.getFeature(Component.class).isVisible();
    }
    
    @Override
    public void setEnabled(final boolean enabled) {
        getPrimaryWidget(this.xidget).setEnabled(enabled);
    }
    
    @Override
    public void setTooltip(final String toolTipText) {
        final JComponent primaryWidget = getPrimaryWidget(this.xidget);
        if (primaryWidget != null) {
            primaryWidget.setToolTipText(toolTipText);
        }
    }
    
    @Override
    public void setBackground(final int n) {
        final JComponent primaryWidget = getPrimaryWidget(this.xidget);
        primaryWidget.setOpaque(true);
        primaryWidget.setBackground(new Color(n));
    }
    
    @Override
    public void setForeground(final int n) {
        getPrimaryWidget(this.xidget).setForeground(new Color(n));
    }
    
    private static JComponent getPrimaryWidget(final IXidget xidget) {
        final Object[] lastWidgets = xidget.getFeature(IWidgetCreationFeature.class).getLastWidgets();
        return (JComponent)lastWidgets[lastWidgets.length - 1];
    }
    
    @Override
    public String toString() {
        return this.xidget.toString();
    }
}
