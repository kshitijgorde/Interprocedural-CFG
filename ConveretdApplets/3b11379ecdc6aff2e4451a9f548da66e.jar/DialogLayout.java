import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
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
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public DialogLayout(final Container container, final int n, final int n2) {
        this.m_map = new Hashtable();
        this.Construct(container, n, n2);
    }
    
    public DialogLayout(final Container container, final Dimension dimension) {
        this.m_map = new Hashtable();
        this.Construct(container, dimension.width, dimension.height);
    }
    
    protected static void unmapRectangle(final Rectangle rectangle, final int n, final int n2) {
        rectangle.x = rectangle.x * 4 / n;
        rectangle.y = rectangle.y * 8 / n2;
        rectangle.width = rectangle.width * 4 / n;
        rectangle.height = rectangle.height * 8 / n2;
    }
    
    public void setShape(final Component component, final int n, final int n2, final int n3, final int n4) {
        this.m_map.put(component, new Rectangle(n, n2, n3, n4));
    }
    
    public void setShape(final Component component, final Rectangle rectangle) {
        this.m_map.put(component, new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height));
    }
    
    public Rectangle getShape(final Component component) {
        final Rectangle rectangle = this.m_map.get(component);
        return new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Dimension getDialogSize() {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    protected static void mapRectangle(final Rectangle rectangle, final int n, final int n2) {
        rectangle.x = rectangle.x * n / 4;
        rectangle.y = rectangle.y * n2 / 8;
        rectangle.width = rectangle.width * n / 4;
        rectangle.height = rectangle.height * n2 / 8;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    protected static int getCharHeight(final Container container) {
        return container.getFontMetrics(container.getFont()).getHeight();
    }
    
    public void layoutContainer(final Container container) {
        final int countComponents = container.countComponents();
        final Rectangle rectangle = new Rectangle();
        final int charHeight = getCharHeight(container);
        final int charWidth = getCharWidth(container);
        final Insets insets = container.insets();
        container.getFontMetrics(container.getFont());
        for (int i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            final Rectangle rectangle2 = this.m_map.get(component);
            if (rectangle2 != null) {
                rectangle.x = rectangle2.x;
                rectangle.y = rectangle2.y;
                rectangle.height = rectangle2.height;
                rectangle.width = rectangle2.width;
                mapRectangle(rectangle, charWidth, charHeight);
                if (component instanceof Label && System.getProperty("java.vendor").indexOf("Microsoft") != -1) {
                    final Rectangle rectangle3 = rectangle;
                    rectangle3.x -= 12;
                    final Rectangle rectangle4 = rectangle;
                    rectangle4.width += 12;
                }
                final Rectangle rectangle5 = rectangle;
                rectangle5.x += insets.left;
                final Rectangle rectangle6 = rectangle;
                rectangle6.y += insets.top;
                component.reshape(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    protected static int getCharWidth(final Container container) {
        final FontMetrics fontMetrics = container.getFontMetrics(container.getFont());
        final String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int n = fontMetrics.stringWidth(s) / s.length();
        if (n <= 0) {
            n = 1;
        }
        return n;
    }
    
    protected void Construct(final Container container, final int n, final int n2) {
        final Rectangle rectangle = new Rectangle(0, 0, n, n2);
        mapRectangle(rectangle, getCharWidth(container), getCharHeight(container));
        this.m_width = rectangle.width;
        this.m_height = rectangle.height;
    }
}
