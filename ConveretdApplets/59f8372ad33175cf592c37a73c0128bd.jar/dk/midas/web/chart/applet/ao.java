// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Point;

public class ao extends aj
{
    public static final String[] s;
    public static final Point[] v;
    private int w;
    private int u;
    private Vector t;
    
    public ao(final be be, final int w, final int u) {
        this.t = new Vector();
        this.w = w;
        this.u = u;
        final int u2 = be.null().u();
        final int n = u2 + w;
        final double doubleValue = be.null().b6.elementAt(u2);
        final float n2 = be.bC + (be.char() - be.ek.y - be.byte().bottom) * 1 / be.bI;
        final double doubleValue2 = be.null().b6.elementAt(n);
        final float n3 = n2 + u * be.bJ / be.bI;
        final float n4 = n2 - u * be.bJ / be.bI;
        final aj aj = new aj(doubleValue, n2, doubleValue2, n3, 8);
        final aj aj2 = new aj(doubleValue, n2, doubleValue2, n4, 8);
        this.t.addElement(aj);
        this.t.addElement(aj2);
        for (int i = 1; i < 1000; ++i) {
            final double doubleValue3 = be.null().b6.elementAt(u2);
            final double doubleValue4 = be.null().b6.elementAt(n);
            final float n5 = n2 + i * u * be.bJ / be.bI;
            final aj aj3 = new aj(doubleValue3, n5, doubleValue4, n5 - u * be.bJ / be.bI, 8);
            aj3.a(be);
            if (!aj3.for()) {
                break;
            }
            this.t.addElement(aj3);
        }
        for (int j = -1; j >= -1000; --j) {
            final double doubleValue5 = be.null().b6.elementAt(u2);
            final double doubleValue6 = be.null().b6.elementAt(n);
            final float n6 = n2 + j * u * be.bJ / be.bI;
            final aj aj4 = new aj(doubleValue5, n6, doubleValue6, n6 - u * be.bJ / be.bI, 8);
            aj4.a(be);
            if (!aj4.for()) {
                break;
            }
            this.t.addElement(aj4);
        }
        for (int k = -1; k >= -1000; --k) {
            final double doubleValue7 = be.null().b6.elementAt(u2);
            final double doubleValue8 = be.null().b6.elementAt(n);
            final float n7 = n2 - k * u * be.bJ / be.bI;
            final aj aj5 = new aj(doubleValue7, n7, doubleValue8, n7 + u * be.bJ / be.bI, 8);
            aj5.a(be);
            if (!aj5.for()) {
                break;
            }
            this.t.addElement(aj5);
        }
        for (int l = 1; l < 1000; ++l) {
            final double doubleValue9 = be.null().b6.elementAt(u2);
            final double doubleValue10 = be.null().b6.elementAt(n);
            final float n8 = n2 - l * u * be.bJ / be.bI;
            final aj aj6 = new aj(doubleValue9, n8, doubleValue10, n8 + u * be.bJ / be.bI, 8);
            aj6.a(be);
            if (!aj6.for()) {
                break;
            }
            this.t.addElement(aj6);
        }
    }
    
    public void a(final be be) {
        final Enumeration<aj> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(be);
        }
        this.a(true);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        final Enumeration<aj> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(graphics, n, n2);
        }
    }
    
    public int c() {
        return 8;
    }
    
    public void for(final int n) {
    }
    
    static {
        s = new String[] { "1 X 8", "1 X 4", "1 X 3", "1 X 2", "1 X 1", "2 X 1", "3 X 1", "4 X 1", "8 X 1" };
        v = new Point[] { new Point(1, 8), new Point(1, 4), new Point(1, 3), new Point(1, 2), new Point(1, 1), new Point(2, 1), new Point(3, 1), new Point(4, 1), new Point(8, 1) };
    }
}
