// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.Rectangle;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import jmaster.util.log.B;
import jmaster.util.log.A;
import javax.swing.Icon;

public class LinkedIcon implements Icon
{
    public static final String ANCHOR_NORTH = "N";
    public static final String ANCHOR_EAST = "E";
    public static final String ANCHOR_SOUTH = "S";
    public static final String ANCHOR_WEST = "W";
    public static final String ANCHOR_NORTH_EAST = "NE";
    public static final String ANCHOR_SOUTH_EAST = "SE";
    public static final String ANCHOR_SOUTH_WEST = "SW";
    public static final String ANCHOR_NORTH_WEST = "NW";
    public static final String ANCHOR_CENTER = "C";
    protected A B;
    private Icon F;
    private String C;
    private int E;
    private int D;
    private float A;
    
    public LinkedIcon() {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.A = 1.0f;
    }
    
    public LinkedIcon(final Icon delegate) {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.A = 1.0f;
        this.setDelegate(delegate);
    }
    
    public Icon getDelegate() {
        return this.F;
    }
    
    public void setDelegate(final Icon f) {
        this.F = f;
    }
    
    public int getHorizontalSpace() {
        return this.D;
    }
    
    public void setHorizontalSpace(final int d) {
        this.D = d;
    }
    
    public int getVerticalSpace() {
        return this.E;
    }
    
    public void setVerticalSpace(final int e) {
        this.E = e;
    }
    
    public float getAlpha() {
        return this.A;
    }
    
    public void setAlpha(final float a) {
        this.A = a;
    }
    
    public String getAnchor() {
        return this.C;
    }
    
    public void setAnchor(final String c) {
        this.C = c;
    }
    
    public int getIconHeight() {
        return (this.F == null) ? 0 : this.F.getIconHeight();
    }
    
    public int getIconWidth() {
        return (this.F == null) ? 0 : this.F.getIconWidth();
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        if (this.F != null) {
            final int width = component.getWidth();
            final int height = component.getHeight();
            final int iconWidth = this.getIconWidth();
            final int iconHeight = this.getIconHeight();
            int n3 = 0;
            int n4 = 0;
            if ("N".equals(this.C)) {
                n3 = (width - iconWidth) / 2;
                n4 = this.E;
            }
            else if ("E".equals(this.C)) {
                n3 = width - this.D - iconWidth;
                n4 = (height - iconHeight) / 2;
            }
            else if ("S".equals(this.C)) {
                n3 = (width - iconWidth) / 2;
                n4 = height - this.E - iconHeight;
            }
            else if ("W".equals(this.C)) {
                n3 = this.D;
                n4 = (height - iconHeight) / 2;
            }
            else if ("NE".equals(this.C)) {
                n3 = width - this.D - iconWidth;
                n4 = this.E;
            }
            else if ("SE".equals(this.C)) {
                n3 = width - this.D - iconWidth;
                n4 = height - this.E - iconHeight;
            }
            else if ("SW".equals(this.C)) {
                n3 = this.D;
                n4 = height - this.E - iconHeight;
            }
            else if ("NW".equals(this.C)) {
                n3 = this.D;
                n4 = this.E;
            }
            else if ("C".equals(this.C)) {
                n3 = (width - iconWidth) / 2;
                n4 = (height - iconHeight) / 2;
            }
            if (this.A < 1.0f) {
                final Graphics2D graphics2D = (Graphics2D)graphics;
                final Composite composite = graphics2D.getComposite();
                try {
                    graphics2D.setComposite(AlphaComposite.getInstance(3, this.A));
                    this.F.paintIcon(component, graphics, n + n3, n2 + n4);
                }
                finally {
                    graphics2D.setComposite(composite);
                }
            }
            else {
                this.F.paintIcon(component, graphics, n + n3, n2 + n4);
            }
        }
    }
    
    public void paintIcon(final Component component, final Graphics graphics) {
        this.paintIcon(component, graphics, 0, 0);
    }
    
    public Rectangle getIconRect(final Component component, final Rectangle rectangle) {
        return this.getIconRect(component.getWidth(), component.getHeight(), rectangle);
    }
    
    public Rectangle getIconRect(final int n, final int n2, Rectangle rectangle) {
        if (rectangle == null) {
            rectangle = new Rectangle();
        }
        final int iconWidth = this.getIconWidth();
        final int iconHeight = this.getIconHeight();
        int x = 0;
        int y = 0;
        if ("N".equals(this.C)) {
            x = (n - iconWidth) / 2;
            y = this.E;
        }
        else if ("E".equals(this.C)) {
            x = n - this.D - iconWidth;
            y = (n2 - iconHeight) / 2;
        }
        else if ("S".equals(this.C)) {
            x = (n - iconWidth) / 2;
            y = n2 - this.E - iconHeight;
        }
        else if ("W".equals(this.C)) {
            x = this.D;
            y = (n2 - iconHeight) / 2;
        }
        else if ("NE".equals(this.C)) {
            x = n - this.D - iconWidth;
            y = this.E;
        }
        else if ("SE".equals(this.C)) {
            x = n - this.D - iconWidth;
            y = n2 - this.E - iconHeight;
        }
        else if ("SW".equals(this.C)) {
            x = this.D;
            y = n2 - this.E - iconHeight;
        }
        else if ("NW".equals(this.C)) {
            x = this.D;
            y = this.E;
        }
        else if ("C".equals(this.C)) {
            x = n - this.D - iconWidth;
            y = n2 - this.E - iconHeight;
        }
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = iconWidth;
        rectangle.height = iconHeight;
        return rectangle;
    }
}
