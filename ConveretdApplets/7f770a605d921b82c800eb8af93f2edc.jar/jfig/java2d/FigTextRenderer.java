// 
// Decompiled by Procyon v0.5.30
// 

package jfig.java2d;

import java.awt.geom.Rectangle2D;
import java.awt.Font;
import jfig.objects.FigAttribs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import jfig.objects.FigCompound;
import jfig.utils.LP2;
import jfig.utils.SetupManager;
import jfig.canvas.FigTrafo2D;
import java.awt.Graphics;
import java.awt.FontMetrics;
import jfig.gui.FontCache;
import java.awt.geom.AffineTransform;
import java.awt.BasicStroke;
import java.awt.Shape;
import jfig.objects.FigText;
import jfig.objects.FigRenderer;

public class FigTextRenderer implements FigRenderer
{
    FigText textObject;
    boolean showBoundingBoxes;
    Shape rect2D;
    BasicStroke stroke;
    AffineTransform cachedAffineTransform;
    FontCache fontCache;
    FontMetrics fm;
    
    public void rebuild() {
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.textObject.getTrafo());
    }
    
    public void paint(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        if (!this.textObject.isVisible()) {
            return;
        }
        try {
            final Point position = this.textObject.getPosition();
            final FigAttribs attributes = this.textObject.getAttributes();
            String s = this.textObject.getText();
            if (s == null) {
                return;
            }
            if (attributes.fig_font == 32) {
                s = FigText.symbol_recode(s);
            }
            final int n = (int)(attributes.fontSize * figTrafo2D.getZoom());
            final Font font = this.fontCache.getFont(attributes.fig_font, n);
            final FontMetrics fontMetrics = this.fontCache.getFontMetrics(attributes.fig_font, n);
            if ((attributes.fig_font_flags & 0x2) != 0x0 && SetupManager.getBoolean("jfig.showTeXStrings", false)) {
                final LP2 lp2 = new LP2();
                lp2.setFontIndex(attributes.fig_font);
                lp2.setFontPtSize(attributes.fontSize);
                lp2.setColorIndex(attributes.fig_line_color);
                lp2.setEnableDisplayBoxes(SetupManager.getBoolean("jfig.utils.SimpleLatexParser.displayBoxes", false));
                lp2.setEnableDumpTokens(SetupManager.getBoolean("jfig.utils.SimpleLatexParser.dumpTokens", false));
                lp2.setUseWords(SetupManager.getBoolean("jfig.utils.SimpleLatexParser.useWords", true));
                lp2.parse(s);
                final FigCompound figCompound = new FigCompound();
                figCompound.setTrafo(this.textObject.getTrafo());
                lp2.convertToFig(figCompound);
                figCompound.update_bbox();
                figCompound.move(this.textObject.getPosition().x, this.textObject.getPosition().y);
                final int n2 = figCompound.getBbox().getXr() - figCompound.getBbox().getXl();
                if (attributes.textAlign == 3) {
                    figCompound.move(-n2, 0);
                }
                else if (attributes.textAlign == 2) {
                    figCompound.move(-n2 / 2, 0);
                }
                figCompound.rotate(new Point(this.textObject.getPosition().x, this.textObject.getPosition().y), -attributes.fig_angle);
                figCompound.paint(graphics, figTrafo2D);
            }
            else {
                final Graphics2D graphics2D = (Graphics2D)graphics;
                final AffineTransform transform = graphics2D.getTransform();
                graphics2D.getStroke();
                final Rectangle2D stringBounds = fontMetrics.getStringBounds(s, graphics2D);
                int n3 = 0;
                if (attributes.textAlign == 3) {
                    n3 = (int)stringBounds.getWidth();
                }
                else if (attributes.textAlign == 2) {
                    n3 = (int)(stringBounds.getWidth() / 2.0);
                }
                final AffineTransform transform2 = new AffineTransform();
                transform2.translate(figTrafo2D.wc_to_screen_x(position.x), figTrafo2D.wc_to_screen_y(position.y));
                transform2.rotate(-attributes.fig_angle);
                transform2.translate(-n3, 0.0);
                transform2.preConcatenate(transform);
                graphics2D.setTransform(transform2);
                if (this.showBoundingBoxes) {
                    graphics2D.setColor(Color.yellow);
                    graphics2D.draw(stringBounds);
                    graphics2D.drawLine(0, 0, (int)stringBounds.getWidth(), 0);
                }
                graphics2D.setColor(attributes.lineColor);
                graphics2D.setFont(font);
                if (s.length() > 0) {
                    graphics2D.drawString(s, 0, 0);
                }
                if (s != null && this.textObject.isShowCursor()) {
                    final String substring = s.substring(0, this.textObject.getTextCursorIndex());
                    final int stringWidth = fontMetrics.stringWidth(substring);
                    final int ascent = fontMetrics.getAscent();
                    if (this.showBoundingBoxes) {
                        final Rectangle2D stringBounds2 = fontMetrics.getStringBounds(substring, graphics2D);
                        graphics2D.setColor(Color.red);
                        graphics2D.draw(stringBounds2);
                    }
                    graphics2D.setColor(Color.blue);
                    graphics2D.drawLine(stringWidth, 0, stringWidth, -ascent);
                }
                if (this.textObject.isShowPoints()) {
                    final int ascent2 = fontMetrics.getAscent();
                    this.showOnePoint(graphics, n3, 0, false);
                    this.showOnePoint(graphics, n3, -ascent2, false);
                }
                if (this.textObject.isSelected()) {
                    final int ascent3 = fontMetrics.getAscent();
                    this.showOnePoint(graphics, n3, 0, true);
                    this.showOnePoint(graphics, n3, -ascent3, true);
                }
                graphics2D.setTransform(transform);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private void showOnePoint(final Graphics graphics, final int n, final int n2, final boolean b) {
        final Color color = b ? Color.white : Color.black;
        final Color color2 = b ? Color.black : Color.white;
        graphics.setColor(color);
        graphics.drawRect(n - 2, n2 - 2, 4, 4);
        graphics.setColor(color2);
        graphics.fillRect(n - 1, n2 - 1, 3, 3);
    }
    
    public FigTextRenderer(final FigText textObject) {
        this.textObject = textObject;
        this.fontCache = FontCache.getFontCache();
        this.showBoundingBoxes = SetupManager.getBoolean("jfig.objects.FigText.showBoundingBoxes", false);
        this.rebuild();
    }
}
