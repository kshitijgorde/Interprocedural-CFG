// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.text.JTextComponent;
import java.awt.Shape;
import java.awt.Graphics;
import javax.swing.text.Highlighter;

public class ErrorHighlightPainter implements HighlightPainter
{
    @Override
    public void paint(final Graphics graphics, final int n, final int n2, final Shape shape, final JTextComponent textComponent) {
        try {
            final Rectangle union = textComponent.modelToView(n).union(textComponent.modelToView(n2));
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final Color color = graphics2D.getColor();
            graphics2D.setColor(Color.pink);
            graphics2D.fillRect(union.x, union.y, union.width, union.height);
            graphics2D.setColor(color);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
