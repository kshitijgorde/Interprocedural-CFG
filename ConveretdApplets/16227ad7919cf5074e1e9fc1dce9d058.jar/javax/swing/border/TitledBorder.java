// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;

public class TitledBorder extends AbstractBorder
{
    protected String title;
    protected Border border;
    protected int titlePosition;
    protected int titleJustification;
    protected Font titleFont;
    protected Color titleColor;
    private Point textLoc;
    public static final int DEFAULT_POSITION = 0;
    public static final int ABOVE_TOP = 1;
    public static final int TOP = 2;
    public static final int BELOW_TOP = 3;
    public static final int ABOVE_BOTTOM = 4;
    public static final int BOTTOM = 5;
    public static final int BELOW_BOTTOM = 6;
    public static final int DEFAULT_JUSTIFICATION = 0;
    public static final int LEFT = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    protected static final int EDGE_SPACING = 2;
    protected static final int TEXT_SPACING = 2;
    protected static final int TEXT_INSET_H = 5;
    
    public TitledBorder(final String s) {
        this(null, s, 1, 2, null, null);
    }
    
    public TitledBorder(final Border border) {
        this(border, "", 1, 2, null, null);
    }
    
    public TitledBorder(final Border border, final String s) {
        this(border, s, 1, 2, null, null);
    }
    
    public TitledBorder(final Border border, final String s, final int n, final int n2) {
        this(border, s, n, n2, null, null);
    }
    
    public TitledBorder(final Border border, final String s, final int n, final int n2, final Font font) {
        this(border, s, n, n2, font, null);
    }
    
    public TitledBorder(final Border border, final String title, final int titleJustification, final int titlePosition, final Font titleFont, final Color titleColor) {
        this.textLoc = new Point();
        this.title = title;
        this.border = border;
        this.titleFont = titleFont;
        this.titleColor = titleColor;
        this.setTitleJustification(titleJustification);
        this.setTitlePosition(titlePosition);
    }
    
    private static boolean computeIntersection(final Rectangle rectangle, final int n, final int n2, final int n3, final int n4) {
        final int max = Math.max(n, rectangle.x);
        final int min = Math.min(n + n3, rectangle.x + rectangle.width);
        final int max2 = Math.max(n2, rectangle.y);
        final int min2 = Math.min(n2 + n4, rectangle.y + rectangle.height);
        rectangle.x = max;
        rectangle.y = max2;
        rectangle.width = min - max;
        rectangle.height = min2 - max2;
        return rectangle.width > 0 && rectangle.height > 0;
    }
    
    public Border getBorder() {
        Border border = this.border;
        if (border == null) {
            border = UIManager.getBorder("TitledBorder.border");
        }
        return border;
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets(component, new Insets(0, 0, 0, 0));
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        int descent = 0;
        int ascent = 16;
        final Border border = this.getBorder();
        if (border != null) {
            if (border instanceof AbstractBorder) {
                ((AbstractBorder)border).getBorderInsets(component, insets);
            }
            else {
                final Insets borderInsets = border.getBorderInsets(component);
                insets.top = borderInsets.top;
                insets.right = borderInsets.right;
                insets.bottom = borderInsets.bottom;
                insets.left = borderInsets.left;
            }
        }
        else {
            final boolean b = false;
            insets.bottom = (b ? 1 : 0);
            insets.right = (b ? 1 : 0);
            insets.top = (b ? 1 : 0);
            insets.left = (b ? 1 : 0);
        }
        insets.left += 4;
        insets.right += 4;
        insets.top += 4;
        insets.bottom += 4;
        if (component == null || this.getTitle() == null || this.getTitle().equals("")) {
            return insets;
        }
        final FontMetrics fontMetrics = component.getFontMetrics(this.getFont(component));
        if (fontMetrics != null) {
            descent = fontMetrics.getDescent();
            ascent = fontMetrics.getAscent();
        }
        switch (this.getTitlePosition()) {
            case 1: {
                insets.top += ascent + descent + (Math.max(2, 4) - 2);
                break;
            }
            case 0:
            case 2: {
                insets.top += ascent + descent;
                break;
            }
            case 3: {
                insets.top += ascent + descent + 2;
                break;
            }
            case 4: {
                insets.bottom += ascent + descent + 2;
                break;
            }
            case 5: {
                insets.bottom += ascent + descent;
                break;
            }
            case 6: {
                insets.bottom += ascent + 2;
                break;
            }
        }
        return insets;
    }
    
    protected Font getFont(final Component component) {
        final Font titleFont;
        if ((titleFont = this.getTitleFont()) != null) {
            return titleFont;
        }
        final Font font;
        if (component != null && (font = component.getFont()) != null) {
            return font;
        }
        return new Font("Dialog", 0, 12);
    }
    
    public Dimension getMinimumSize(final Component component) {
        final Insets borderInsets = this.getBorderInsets(component);
        final Dimension dimension = new Dimension(borderInsets.right + borderInsets.left, borderInsets.top + borderInsets.bottom);
        final FontMetrics fontMetrics = component.getFontMetrics(this.getFont(component));
        switch (this.titlePosition) {
            case 1:
            case 6: {
                dimension.width = Math.max(fontMetrics.stringWidth(this.getTitle()), dimension.width);
                break;
            }
            default: {
                final Dimension dimension2 = dimension;
                dimension2.width += fontMetrics.stringWidth(this.getTitle());
                break;
            }
        }
        return dimension;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Color getTitleColor() {
        Color color = this.titleColor;
        if (color == null) {
            color = UIManager.getColor("TitledBorder.titleColor");
        }
        return color;
    }
    
    public Font getTitleFont() {
        Font font = this.titleFont;
        if (font == null) {
            font = UIManager.getFont("TitledBorder.font");
        }
        return font;
    }
    
    public int getTitleJustification() {
        return this.titleJustification;
    }
    
    public int getTitlePosition() {
        return this.titlePosition;
    }
    
    public boolean isBorderOpaque() {
        return false;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Border border = this.getBorder();
        if (this.getTitle() == null || this.getTitle().equals("")) {
            if (border != null) {
                border.paintBorder(component, graphics, n, n2, n3, n4);
            }
            return;
        }
        final Rectangle rectangle = new Rectangle(n + 2, n2 + 2, n3 - 4, n4 - 4);
        final Font font = graphics.getFont();
        final Color color = graphics.getColor();
        graphics.setFont(this.getFont(component));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int descent = fontMetrics.getDescent();
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth(this.getTitle());
        Insets borderInsets;
        if (border != null) {
            borderInsets = border.getBorderInsets(component);
        }
        else {
            borderInsets = new Insets(0, 0, 0, 0);
        }
        final int titlePosition = this.getTitlePosition();
        switch (titlePosition) {
            case 1: {
                final int n5 = ascent + descent + (Math.max(2, 4) - 2);
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += n5;
                final Rectangle rectangle3 = rectangle;
                rectangle3.height -= n5;
                this.textLoc.y = rectangle.y - (descent + 2);
                break;
            }
            case 0:
            case 2: {
                final int max = Math.max(0, ascent / 2 + 2 - 2);
                final Rectangle rectangle4 = rectangle;
                rectangle4.y += max;
                final Rectangle rectangle5 = rectangle;
                rectangle5.height -= max;
                this.textLoc.y = rectangle.y - descent + (borderInsets.top + ascent + descent) / 2;
                break;
            }
            case 3: {
                this.textLoc.y = rectangle.y + borderInsets.top + ascent + 2;
                break;
            }
            case 4: {
                this.textLoc.y = rectangle.y + rectangle.height - (borderInsets.bottom + descent + 2);
                break;
            }
            case 5: {
                final Rectangle rectangle6 = rectangle;
                rectangle6.height -= height / 2;
                this.textLoc.y = rectangle.y + rectangle.height - descent + (ascent + descent - borderInsets.bottom) / 2;
                break;
            }
            case 6: {
                final Rectangle rectangle7 = rectangle;
                rectangle7.height -= height;
                this.textLoc.y = rectangle.y + rectangle.height + ascent + 2;
                break;
            }
        }
        switch (this.getTitleJustification()) {
            case 0:
            case 1: {
                this.textLoc.x = rectangle.x + 5 + borderInsets.left;
                break;
            }
            case 3: {
                this.textLoc.x = rectangle.x + rectangle.width - (stringWidth + 5 + borderInsets.right);
                break;
            }
            case 2: {
                this.textLoc.x = rectangle.x + (rectangle.width - stringWidth) / 2;
                break;
            }
        }
        if (border != null) {
            if (titlePosition == 2 || titlePosition == 5) {
                final Rectangle rectangle8 = new Rectangle();
                final Rectangle clipBounds = graphics.getClipBounds();
                rectangle8.setBounds(clipBounds);
                if (computeIntersection(rectangle8, n, n2, this.textLoc.x, n4)) {
                    graphics.setClip(rectangle8);
                    border.paintBorder(component, graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
                rectangle8.setBounds(clipBounds);
                if (computeIntersection(rectangle8, this.textLoc.x + stringWidth, 0, n3 - stringWidth - this.textLoc.x, n4)) {
                    graphics.setClip(rectangle8);
                    border.paintBorder(component, graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
                rectangle8.setBounds(clipBounds);
                if (titlePosition == 2) {
                    if (computeIntersection(rectangle8, this.textLoc.x, rectangle.y + borderInsets.top, stringWidth, n4 - rectangle.y - borderInsets.top)) {
                        graphics.setClip(rectangle8);
                        border.paintBorder(component, graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                    }
                }
                else if (computeIntersection(rectangle8, this.textLoc.x, n2, stringWidth, n4 - borderInsets.bottom - (n4 - rectangle.height - rectangle.y))) {
                    graphics.setClip(rectangle8);
                    border.paintBorder(component, graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
                graphics.setClip(clipBounds);
            }
            else {
                border.paintBorder(component, graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        graphics.setColor(this.getTitleColor());
        graphics.drawString(this.getTitle(), this.textLoc.x, this.textLoc.y);
        graphics.setFont(font);
        graphics.setColor(color);
    }
    
    public void setBorder(final Border border) {
        this.border = border;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setTitleColor(final Color titleColor) {
        this.titleColor = titleColor;
    }
    
    public void setTitleFont(final Font titleFont) {
        this.titleFont = titleFont;
    }
    
    public void setTitleJustification(final int titleJustification) {
        switch (titleJustification) {
            case 0:
            case 1:
            case 2:
            case 3: {
                this.titleJustification = titleJustification;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(titleJustification) + " is not a valid title justification.");
            }
        }
    }
    
    public void setTitlePosition(final int titlePosition) {
        switch (titlePosition) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                this.titlePosition = titlePosition;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(titlePosition) + " is not a valid title position.");
            }
        }
    }
}
