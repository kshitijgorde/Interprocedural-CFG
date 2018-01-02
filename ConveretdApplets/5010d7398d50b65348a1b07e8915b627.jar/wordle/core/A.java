// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.print.PrinterJob;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.AffineTransform;
import wordle.core.a.a.a;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Color;
import wordle.core.a.c;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.print.Printable;

final class A implements Printable
{
    private /* synthetic */ n a;
    
    A(final n a) {
        this.a = a;
    }
    
    public final int print(final Graphics graphics, final PageFormat pageFormat, final int n) {
        if (n > 0) {
            return 1;
        }
        final Graphics2D graphics2D;
        (graphics2D = (Graphics2D)graphics).translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        n.a(this.a, graphics2D, new Dimension((int)pageFormat.getImageableWidth(), (int)pageFormat.getImageableHeight()), true);
        return 0;
    }
}
