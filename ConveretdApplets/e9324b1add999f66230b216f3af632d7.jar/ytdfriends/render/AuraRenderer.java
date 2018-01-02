// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.render;

import java.awt.geom.Rectangle2D;
import ytdfriends.DecoratorItem;
import java.awt.Shape;
import edu.berkeley.guir.prefuse.VisualItem;
import java.awt.geom.RoundRectangle2D;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.render.ShapeRenderer;

public class AuraRenderer extends ShapeRenderer
{
    protected FriendsPanel fPanel;
    protected RoundRectangle2D m_aura;
    protected int m_corner;
    protected int m_margin;
    
    public AuraRenderer(final FriendsPanel fPanel) {
        this.m_aura = new RoundRectangle2D.Float();
        this.m_corner = 10;
        this.m_margin = 2;
        this.fPanel = fPanel;
        (this.m_aura = new RoundRectangle2D.Float()).setRoundRect(0.0, 0.0, 10.0, 10.0, this.m_corner, this.m_corner);
    }
    
    protected Shape getRawShape(final VisualItem item) {
        if (!item.getItemClass().equals("aura")) {
            throw new IllegalArgumentException("VisualItem must be of item class aura");
        }
        final DecoratorItem ditem = (DecoratorItem)item;
        final int margin = this.getMargin(ditem.getDecorated());
        final Rectangle2D b = ditem.getDecorated().getBounds();
        this.m_aura.setFrame(b.getMinX() - margin, b.getMinY() - margin, b.getWidth() + 2 * margin, b.getHeight() + 2 * margin);
        return this.m_aura;
    }
    
    protected int getMargin(final VisualItem item) {
        if (this.fPanel.isXRayMode() || item.isHighlighted() || item.getDOI() == 0.0) {
            return this.m_margin + 2;
        }
        return this.m_margin;
    }
}
