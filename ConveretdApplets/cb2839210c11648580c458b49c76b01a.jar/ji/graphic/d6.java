// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.Rectangle;
import ji.util.i;
import java.awt.Polygon;
import ji.image.cy;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public class d6
{
    static Color a;
    
    public static final void a(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n, n2);
    }
    
    public static final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawRect(n, n2, n3, n4);
    }
    
    public static final void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.fillRect(n, n2, n3, n4);
    }
    
    public static final void a(final Graphics graphics, final Image image, final int n, final int n2, final ImageObserver imageObserver) {
        graphics.drawImage(image, n, n2, imageObserver);
    }
    
    public static final void a(final Graphics graphics, final Color a) {
        graphics.setXORMode(d6.a = a);
    }
    
    public static final void a(final Graphics graphics) {
        d6.a = null;
        graphics.setPaintMode();
    }
    
    public static final void a(final cy cy, final Graphics graphics, final Polygon polygon) {
        if (d6.a != null && i.c(282)) {
            graphics.setPaintMode();
            final Rectangle rectangle = new Rectangle(polygon.getBoundingBox());
            rectangle.height = Math.max(rectangle.height, 1);
            rectangle.width = Math.max(rectangle.width, 1);
            final Image image = cy.createImage(rectangle.width, rectangle.height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(graphics.getColor());
            final Rectangle ee = cy.ee();
            graphics2.drawImage(cy.n(), -rectangle.x + ee.x, -rectangle.y + ee.y, null);
            graphics2.setXORMode(d6.a);
            polygon.translate(-rectangle.x, -rectangle.y);
            graphics2.fillPolygon(polygon);
            polygon.translate(rectangle.x, rectangle.y);
            graphics.drawImage(image, rectangle.x, rectangle.y, null);
            image.flush();
            graphics.setXORMode(d6.a);
        }
        else {
            graphics.fillPolygon(polygon);
        }
    }
    
    public static final void a(final Graphics graphics, final int[] array, final int[] array2, final int n) {
        graphics.fillPolygon(array, array2, n);
    }
    
    public static final void a(final Graphics graphics, final Polygon polygon) {
        graphics.drawPolygon(polygon);
    }
    
    public static final void b(final Graphics graphics, final int[] array, final int[] array2, final int n) {
        graphics.drawPolyline(array, array2, n);
    }
    
    public static final void a(final cy cy, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (d6.a != null && i.c(282)) {
            graphics.setPaintMode();
            final Polygon polygon = new Polygon();
            polygon.addPoint(n, n2);
            polygon.addPoint(n3, n4);
            final Rectangle boundingBox = polygon.getBoundingBox();
            boundingBox.height = Math.max(boundingBox.height, 1);
            boundingBox.width = Math.max(boundingBox.width, 1);
            final Image image = cy.createImage(boundingBox.width, boundingBox.height);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(graphics.getColor());
            final Rectangle ee = cy.ee();
            graphics2.drawImage(cy.n(), -boundingBox.x + ee.x, -boundingBox.y + ee.y, null);
            graphics2.setXORMode(d6.a);
            graphics2.fillRect(0, 0, boundingBox.width, boundingBox.height);
            graphics.drawImage(image, boundingBox.x, boundingBox.y, null);
            image.flush();
            graphics.setXORMode(d6.a);
        }
        else {
            graphics.drawLine(n, n2, n3, n4);
        }
    }
    
    static {
        d6.a = null;
    }
}
