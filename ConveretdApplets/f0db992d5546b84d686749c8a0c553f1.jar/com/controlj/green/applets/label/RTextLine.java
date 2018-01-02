// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.image.ImageObserver;
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
    
    public RTextLine(final Component c) {
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
        this.setDrawingComponent(c);
    }
    
    public RTextLine(final String s, final Font f) {
        super(s, f);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final String s, final Font f, final Color c, final int j) {
        super(s, f, c, j);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final String s, final Color c) {
        super(s, c);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public RTextLine(final Font f, final Color c, final int j, final int a) {
        super(f, c, j);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
        this.setRotation(a);
    }
    
    public RTextLine(final Font f, final Color c, final int j) {
        super(f, c, j);
        this.angle = 0;
        this.cos = 1.0;
        this.sin = 0.0;
        this.component = null;
    }
    
    public void copyState(final RTextLine t) {
        if (t == null) {
            return;
        }
        super.font = t.getFont();
        super.color = t.getColor();
        super.justification = t.getJustification();
        this.setRotation(t.getRotation(), t.getComponent());
        if (super.font == null) {
            return;
        }
        super.fontname = super.font.getName();
        super.fontstyle = super.font.getStyle();
        super.fontsize = super.font.getSize();
        super.parse = true;
    }
    
    public void setRotation(final int angle) {
        this.angle = angle % 360 / 90 * 90;
        this.cos = Math.cos(angle * 3.141592653589793 / 180.0);
        this.sin = Math.sin(angle * 3.141592653589793 / 180.0);
    }
    
    public void setDrawingComponent(final Component c) {
        this.component = c;
    }
    
    public void setRotation(final int angle, final Component c) {
        this.setRotation(angle);
        this.setDrawingComponent(c);
    }
    
    public int getRotation() {
        return this.angle;
    }
    
    public Component getComponent() {
        return this.component;
    }
    
    public int getRWidth(final Graphics g) {
        this.parseText(g);
        return (int)(Math.abs(this.cos * super.width + this.sin * super.height) + 0.5);
    }
    
    public int getRHeight(final Graphics g) {
        this.parseText(g);
        return (int)(Math.abs(-this.sin * super.width + this.cos * super.height) + 0.5);
    }
    
    public int getLeftEdge(final Graphics g) {
        return this.getLeftEdge(g, super.justification);
    }
    
    public int getRightEdge(final Graphics g) {
        return this.getRightEdge(g, super.justification);
    }
    
    public int getTopEdge(final Graphics g) {
        return this.getTopEdge(g, super.justification);
    }
    
    public int getBottomEdge(final Graphics g) {
        return this.getBottomEdge(g, super.justification);
    }
    
    public int getLeftEdge(final Graphics g, final int j) {
        this.parseText(g);
        switch (this.angle) {
            case -270:
            case 90: {
                return -super.ascent;
            }
            case -180:
            case 180: {
                if (j == 0) {
                    return -super.width / 2;
                }
                if (j == 2) {
                    return 0;
                }
                return -super.width;
            }
            case -90:
            case 270: {
                return -super.descent - super.leading;
            }
            default: {
                if (j == 0) {
                    return -super.width / 2;
                }
                if (j == 2) {
                    return -super.width;
                }
                return 0;
            }
        }
    }
    
    public int getRightEdge(final Graphics g, final int j) {
        this.parseText(g);
        switch (this.angle) {
            case -270:
            case 90: {
                return super.descent + super.leading;
            }
            case -180:
            case 180: {
                if (j == 0) {
                    return super.width / 2;
                }
                if (j == 2) {
                    return super.width;
                }
                return 0;
            }
            case -90:
            case 270: {
                return super.ascent;
            }
            default: {
                if (j == 0) {
                    return super.width / 2;
                }
                if (j == 2) {
                    return 0;
                }
                return super.width;
            }
        }
    }
    
    public int getTopEdge(final Graphics g, final int j) {
        this.parseText(g);
        switch (this.angle) {
            case -270:
            case 90: {
                if (j == 0) {
                    return super.width / 2;
                }
                if (j == 2) {
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
                if (j == 0) {
                    return super.width / 2;
                }
                if (j == 2) {
                    return super.width;
                }
                return 0;
            }
            default: {
                return super.ascent;
            }
        }
    }
    
    public int getBottomEdge(final Graphics g, final int j) {
        this.parseText(g);
        switch (this.angle) {
            case -270:
            case 90: {
                if (j == 0) {
                    return -super.width / 2;
                }
                if (j == 2) {
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
                if (j == 0) {
                    return -super.width / 2;
                }
                if (j == 2) {
                    return 0;
                }
                return -super.width;
            }
            default: {
                return -super.descent - super.leading;
            }
        }
    }
    
    public void draw(final Graphics g, final int x, final int y) {
        if (g == null) {
            return;
        }
        if (this.component == null) {
            this.angle = 0;
        }
        if (this.angle == 0) {
            super.draw(g, x, y);
        }
        else {
            this.draw(this.component, g, x, y);
        }
    }
    
    public void draw(final Graphics g, final int x, final int y, final int j) {
        super.justification = j;
        if (g == null) {
            return;
        }
        if (this.component == null) {
            this.angle = 0;
        }
        if (this.angle == 0) {
            super.draw(g, x, y);
        }
        else {
            this.draw(this.component, g, x, y);
        }
    }
    
    public synchronized void draw(final Component comp, final Graphics g, final int x, final int y) {
        int xoffset = 0;
        int yoffset = 0;
        Image offsI = null;
        Graphics offsG = null;
        Image rotatedImage = null;
        int maxHeight = 0;
        if (super.text == null || comp == null) {
            return;
        }
        this.parseText(g);
        maxHeight = super.maxAscent + super.maxDescent;
        switch (this.angle) {
            case -270:
            case 90: {
                xoffset = -super.maxAscent;
                if (super.justification == 0) {
                    yoffset = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    yoffset = 0;
                    break;
                }
                yoffset = -super.width;
                break;
            }
            case -180:
            case 180: {
                yoffset = -super.maxDescent;
                if (super.justification == 0) {
                    xoffset = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    xoffset = 0;
                    break;
                }
                xoffset = -super.width;
                break;
            }
            case -90:
            case 270: {
                xoffset = -super.maxDescent;
                if (super.justification == 0) {
                    yoffset = -super.width / 2;
                    break;
                }
                if (super.justification == 2) {
                    yoffset = -super.width;
                    break;
                }
                yoffset = 0;
                break;
            }
            default: {
                xoffset = 0;
                yoffset = 0;
                break;
            }
        }
        offsI = comp.createImage(super.width, maxHeight);
        offsG = offsI.getGraphics();
        if (super.background != null) {
            offsG.setColor(super.background);
        }
        else {
            offsG.setColor(comp.getBackground());
        }
        offsG.fillRect(0, 0, super.width, maxHeight);
        offsG.setFont(g.getFont());
        offsG.setColor(g.getColor());
        if (super.font != null) {
            offsG.setFont(super.font);
        }
        if (super.color != null) {
            offsG.setColor(super.color);
        }
        for (int i = 0; i < super.list.size(); ++i) {
            final TextState ts = super.list.elementAt(i);
            if (ts.f != null) {
                offsG.setFont(ts.f);
            }
            if (ts.s != null) {
                offsG.drawString(ts.toString(), ts.x, ts.y + super.maxAscent);
            }
        }
        final RotateFilter f = new RotateFilter(this.angle * 3.141592653589793 / 180.0);
        final ImageProducer producer = new FilteredImageSource(offsI.getSource(), f);
        rotatedImage = comp.createImage(producer);
        g.drawImage(rotatedImage, x + xoffset, y + yoffset, null);
    }
}
