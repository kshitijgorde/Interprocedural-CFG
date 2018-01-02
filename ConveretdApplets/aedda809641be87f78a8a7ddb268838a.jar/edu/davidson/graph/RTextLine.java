// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

public class RTextLine extends TextLine
{
    protected int angle;
    private double cos;
    private double sin;
    private Component component;
    
    public RTextLine() {
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final String s) {
        super(s);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final Component drawingComponent) {
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
        this.setDrawingComponent(drawingComponent);
    }
    
    public RTextLine(final String s, final Font font) {
        super(s, font);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final String s, final Font font, final Color color, final int n) {
        super(s, font, color, n);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final String s, final Color color) {
        super(s, color);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final Font font, final Color color, final int n, final int rotation) {
        super(font, color, n);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
        this.setRotation(rotation);
    }
    
    public RTextLine(final Font font, final Color color, final int n) {
        super(font, color, n);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public void copyState(final RTextLine rTextLine) {
        if (rTextLine == null) {
            return;
        }
        super.font = rTextLine.getFont();
        super.color = rTextLine.getColor();
        super.justification = rTextLine.getJustification();
        this.setRotation(rTextLine.getRotation(), rTextLine.getComponent());
        if (super.font == null) {
            return;
        }
        super.fontname = super.font.getName();
        super.fontstyle = super.font.getStyle();
        super.fontsize = super.font.getSize();
        super.parse = true;
    }
    
    public void setRotation(final int n) {
        this.angle = n % 360 / 90 * 90;
        this.cos = Math.cos(n * 3.141592653589793 / 180.0);
        this.sin = Math.sin(n * 3.141592653589793 / 180.0);
    }
    
    public void setDrawingComponent(final Component component) {
        this.component = component;
    }
    
    public void setRotation(final int rotation, final Component drawingComponent) {
        this.setRotation(rotation);
        this.setDrawingComponent(drawingComponent);
    }
    
    public int getRotation() {
        return this.angle;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public int getRWidth(final Graphics graphics) {
        this.parseText(graphics);
        return (int)(Math.abs(this.cos * super.width + this.sin * super.height) + 0.5);
    }
    
    public int getRHeight(final Graphics graphics) {
        this.parseText(graphics);
        return (int)(Math.abs(-this.sin * super.width + this.cos * super.height) + 0.5);
    }
    
    public int getLeftEdge(final Graphics graphics) {
        return this.getLeftEdge(graphics, super.justification);
    }
    
    public int getRightEdge(final Graphics graphics) {
        return this.getRightEdge(graphics, super.justification);
    }
    
    public int getTopEdge(final Graphics graphics) {
        return this.getTopEdge(graphics, super.justification);
    }
    
    public int getBottomEdge(final Graphics graphics) {
        return this.getBottomEdge(graphics, super.justification);
    }
    
    public int getLeftEdge(final Graphics graphics, final int n) {
        this.parseText(graphics);
        switch (this.angle) {
            case -270:
            case 90: {
                return -super.ascent;
            }
            case -180:
            case 180: {
                if (n == 0) {
                    return -super.width / 2;
                }
                if (n == 2) {
                    return 0;
                }
                return -super.width;
            }
            case -90:
            case 270: {
                return -super.descent - super.leading;
            }
            default: {
                if (n == 0) {
                    return -super.width / 2;
                }
                if (n == 2) {
                    return -super.width;
                }
                return 0;
            }
        }
    }
    
    public int getRightEdge(final Graphics graphics, final int n) {
        this.parseText(graphics);
        switch (this.angle) {
            case -270:
            case 90: {
                return super.descent + super.leading;
            }
            case -180:
            case 180: {
                if (n == 0) {
                    return super.width / 2;
                }
                if (n == 2) {
                    return super.width;
                }
                return 0;
            }
            case -90:
            case 270: {
                return super.ascent;
            }
            default: {
                if (n == 0) {
                    return super.width / 2;
                }
                if (n == 2) {
                    return 0;
                }
                return super.width;
            }
        }
    }
    
    public int getTopEdge(final Graphics graphics, final int n) {
        this.parseText(graphics);
        switch (this.angle) {
            case -270:
            case 90: {
                if (n == 0) {
                    return super.width / 2;
                }
                if (n == 2) {
                    return 0;
                }
                return super.width;
            }
            case -180:
            case 180: {
                return super.descent + super.leading;
            }
            case -90:
            case 270: {
                if (n == 0) {
                    return super.width / 2;
                }
                if (n == 2) {
                    return super.width;
                }
                return 0;
            }
            default: {
                return super.ascent;
            }
        }
    }
    
    public int getBottomEdge(final Graphics graphics, final int n) {
        this.parseText(graphics);
        switch (this.angle) {
            case -270:
            case 90: {
                if (n == 0) {
                    return -super.width / 2;
                }
                if (n == 2) {
                    return -super.width;
                }
                return 0;
            }
            case -180:
            case 180: {
                return -super.ascent;
            }
            case -90:
            case 270: {
                if (n == 0) {
                    return -super.width / 2;
                }
                if (n == 2) {
                    return 0;
                }
                return -super.width;
            }
            default: {
                return -super.descent - super.leading;
            }
        }
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        if (graphics == null) {
            return;
        }
        if (this.component == null) {
            this.angle = 0;
        }
        if (this.angle == 0) {
            super.draw(graphics, n, n2);
        }
        else {
            this.draw(this.component, graphics, n, n2);
        }
    }
    
    public void draw(final Graphics graphics, final int n, final int n2, final int justification) {
        super.justification = justification;
        if (graphics == null) {
            return;
        }
        if (this.component == null) {
            this.angle = 0;
        }
        if (this.angle == 0) {
            super.draw(graphics, n, n2);
        }
        else {
            this.draw(this.component, graphics, n, n2);
        }
    }
    
    public synchronized void draw(final Component component, final Graphics graphics, final int n, final int n2) {
        if (super.text == null || component == null) {
            return;
        }
        this.parseText(graphics);
        final int n3 = super.maxAscent + super.maxDescent;
        int n4 = 0;
        int n5 = 0;
        switch (this.angle) {
            case -270:
            case 90: {
                n4 = -super.maxAscent;
                if (super.justification == 0) {
                    n5 = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    n5 = 0;
                    break;
                }
                n5 = -super.width;
                break;
            }
            case -180:
            case 180: {
                n5 = -super.maxDescent;
                if (super.justification == 0) {
                    n4 = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    n4 = 0;
                    break;
                }
                n4 = -super.width;
                break;
            }
            case -90:
            case 270: {
                n4 = -super.maxDescent;
                if (super.justification == 0) {
                    n5 = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    n5 = -super.width;
                    break;
                }
                n5 = 0;
                break;
            }
            default: {
                n4 = 0;
                n5 = 0;
                break;
            }
        }
        final Image image = component.createImage(super.width, n3);
        if (image == null) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        if (super.background != null) {
            graphics2.setColor(super.background);
        }
        else {
            graphics2.setColor(component.getBackground());
        }
        graphics2.fillRect(0, 0, super.width, n3);
        graphics2.setFont(graphics.getFont());
        graphics2.setColor(graphics.getColor());
        if (super.font != null) {
            graphics2.setFont(super.font);
        }
        if (super.color != null) {
            graphics2.setColor(super.color);
        }
        for (int i = 0; i < super.list.size(); ++i) {
            final TextState textState = super.list.elementAt(i);
            if (textState.f != null) {
                graphics2.setFont(textState.f);
            }
            if (textState.s != null) {
                graphics2.drawString(textState.toString(), textState.x, textState.y + super.maxAscent);
            }
        }
        graphics2.dispose();
        final Image image2 = component.createImage(new FilteredImageSource(image.getSource(), new RotateTextFilter(this.angle)));
        if (image2 == null) {
            return;
        }
        graphics.drawImage(image2, n + n4, n2 + n5, null);
    }
}
