// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Color;
import borland.jbcl.model.ItemPaintSite;
import borland.jbcl.model.ItemPainter;

public class SelectableItemPainter implements ItemPainter, ItemPaintSite
{
    protected ItemPaintSite site;
    protected int state;
    protected ItemPainter painter;
    protected boolean paintBackground;
    protected Color selectedFg;
    protected Color selectedBg;
    protected Color inactiveSelectedFg;
    protected Color inactiveSelectedBg;
    
    public SelectableItemPainter() {
        this.paintBackground = false;
        this.selectedFg = SystemColor.textHighlightText;
        this.selectedBg = SystemColor.textHighlight;
        this.inactiveSelectedFg = SystemColor.menuText;
        this.inactiveSelectedBg = SystemColor.menu;
    }
    
    public SelectableItemPainter(final ItemPainter painter) {
        this.paintBackground = false;
        this.selectedFg = SystemColor.textHighlightText;
        this.selectedBg = SystemColor.textHighlight;
        this.inactiveSelectedFg = SystemColor.menuText;
        this.inactiveSelectedBg = SystemColor.menu;
        this.painter = painter;
    }
    
    public SelectableItemPainter(final ItemPainter painter, final boolean paintBackground) {
        this.paintBackground = false;
        this.selectedFg = SystemColor.textHighlightText;
        this.selectedBg = SystemColor.textHighlight;
        this.inactiveSelectedFg = SystemColor.menuText;
        this.inactiveSelectedBg = SystemColor.menu;
        this.painter = painter;
        this.paintBackground = paintBackground;
    }
    
    public void setPainter(final ItemPainter painter) {
        this.painter = painter;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setPaintBackground(final boolean paintBackground) {
        this.paintBackground = paintBackground;
    }
    
    public boolean isPaintBackground() {
        return this.paintBackground;
    }
    
    public void setSelectedBackground(final Color c) {
        this.selectedBg = c;
    }
    
    public Color getSelectedBackground() {
        return this.selectedBg;
    }
    
    public void setSelectedForeground(final Color c) {
        this.selectedFg = c;
    }
    
    public Color getSelectedForeground() {
        return this.selectedFg;
    }
    
    public void setInactiveSelectedBackground(final Color c) {
        this.inactiveSelectedBg = c;
    }
    
    public Color getInactiveSelectedBackground() {
        return this.inactiveSelectedBg;
    }
    
    public void setInactiveSelectedForeground(final Color c) {
        this.inactiveSelectedFg = c;
    }
    
    public Color getInactiveSelectedForeground() {
        return this.inactiveSelectedFg;
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        this.site = site;
        this.state = state;
        if (this.painter != null) {
            return this.painter.getPreferredSize(data, g, state, this);
        }
        return new Dimension();
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle rect, final int state, final ItemPaintSite site) {
        this.site = site;
        this.state = state;
        if (this.paintBackground) {
            final Color oc = g.getColor();
            g.setColor(this.getBackground());
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            g.setColor(oc);
        }
        if (this.painter != null) {
            this.painter.paint(data, g, rect, state, this);
        }
    }
    
    public Color getBackground() {
        return ((this.state & 0x4) != 0x0) ? (((this.state & 0x20) != 0x0) ? this.inactiveSelectedBg : this.selectedBg) : ((this.site != null) ? this.site.getBackground() : null);
    }
    
    public Color getForeground() {
        return ((this.state & 0x4) != 0x0) ? (((this.state & 0x20) != 0x0) ? this.inactiveSelectedFg : this.selectedFg) : ((this.site != null) ? this.site.getForeground() : null);
    }
    
    public Font getFont() {
        return (this.site != null) ? this.site.getFont() : null;
    }
    
    public int getAlignment() {
        return (this.site != null) ? this.site.getAlignment() : 0;
    }
    
    public Insets getItemMargins() {
        return (this.site != null) ? this.site.getItemMargins() : null;
    }
    
    public Component getSiteComponent() {
        return (this.site != null) ? this.site.getSiteComponent() : null;
    }
}
