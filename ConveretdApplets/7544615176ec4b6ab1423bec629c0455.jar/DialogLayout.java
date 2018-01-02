import java.awt.Insets;
import java.awt.Label;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Container;
import java.util.Hashtable;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class DialogLayout implements LayoutManager
{
    protected Hashtable m_map;
    protected int m_width;
    protected int m_height;
    
    protected void Construct(final Container parent, final int width, final int height) {
        final Rectangle rect = new Rectangle(0, 0, width, height);
        this.mapRectangle(rect, this.getCharWidth(parent), this.getCharHeight(parent));
        this.m_width = rect.width;
        this.m_height = rect.height;
    }
    
    public Dimension preferredLayoutSize(final Container parent) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public void setShape(final Component comp, final int x, final int y, final int width, final int height) {
        this.m_map.put(comp, new Rectangle(x, y, width, height));
    }
    
    public void setShape(final Component comp, final Rectangle rect) {
        this.m_map.put(comp, new Rectangle(rect.x, rect.y, rect.width, rect.height));
    }
    
    public Rectangle getShape(final Component comp) {
        final Rectangle rect = this.m_map.get(comp);
        return new Rectangle(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void addLayoutComponent(final String name, final Component comp) {
    }
    
    public DialogLayout(final Container parent, final int width, final int height) {
        this.m_map = new Hashtable();
        this.Construct(parent, width, height);
    }
    
    public DialogLayout(final Container parent, final Dimension d) {
        this.m_map = new Hashtable();
        this.Construct(parent, d.width, d.height);
    }
    
    protected int getCharHeight(final Container parent) {
        final FontMetrics m = parent.getFontMetrics(parent.getFont());
        final int height = m.getHeight();
        return height;
    }
    
    public Dimension getDialogSize() {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public void removeLayoutComponent(final Component comp) {
    }
    
    public Dimension minimumLayoutSize(final Container parent) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public void layoutContainer(final Container parent) {
        final int count = parent.countComponents();
        final Rectangle rect = new Rectangle();
        final int charHeight = this.getCharHeight(parent);
        final int charWidth = this.getCharWidth(parent);
        final Insets insets = parent.insets();
        final FontMetrics m = parent.getFontMetrics(parent.getFont());
        for (int i = 0; i < count; ++i) {
            final Component c = parent.getComponent(i);
            final Rectangle r = this.m_map.get(c);
            if (r != null) {
                rect.x = r.x;
                rect.y = r.y;
                rect.height = r.height;
                rect.width = r.width;
                this.mapRectangle(rect, charWidth, charHeight);
                if (c instanceof Label) {
                    final Rectangle rectangle = rect;
                    rectangle.x -= 12;
                    final Rectangle rectangle2 = rect;
                    rectangle2.width += 12;
                }
                final Rectangle rectangle3 = rect;
                rectangle3.x += insets.left;
                final Rectangle rectangle4 = rect;
                rectangle4.y += insets.top;
                c.reshape(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
    
    protected int getCharWidth(final Container parent) {
        final FontMetrics m = parent.getFontMetrics(parent.getFont());
        final String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int width = m.stringWidth(s) / s.length();
        if (width <= 0) {
            width = 1;
        }
        return width;
    }
    
    protected void mapRectangle(final Rectangle rect, final int charWidth, final int charHeight) {
        rect.x = rect.x * charWidth / 4;
        rect.y = rect.y * charHeight / 8;
        rect.width = rect.width * charWidth / 4;
        rect.height = rect.height * charHeight / 8;
    }
}
