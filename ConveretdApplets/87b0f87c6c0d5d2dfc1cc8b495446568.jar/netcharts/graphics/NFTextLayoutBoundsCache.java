// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.Font;
import java.awt.font.TextLayout;
import java.util.Vector;
import java.util.Hashtable;

public class NFTextLayoutBoundsCache
{
    private static Hashtable a;
    private static Vector b;
    private static int c;
    private static Object d;
    
    public static int getMaxSize() {
        return NFTextLayoutBoundsCache.c;
    }
    
    public static void setMaxSize(final int c) {
        NFTextLayoutBoundsCache.c = c;
    }
    
    public static Rectangle2D getBounds(final TextLayout textLayout, final String s, final Font font, final FontRenderContext fontRenderContext) {
        if (NFTextLayoutBoundsCache.c <= 0) {
            return textLayout.getBounds();
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s);
        sb.append("|");
        sb.append(font.toString());
        sb.append("|");
        sb.append(fontRenderContext.getTransform().toString());
        sb.append("|");
        sb.append(fontRenderContext.isAntiAliased());
        sb.append("|");
        sb.append(fontRenderContext.usesFractionalMetrics());
        final String string = sb.toString();
        synchronized (NFTextLayoutBoundsCache.d) {
            final Rectangle2D rectangle2D = NFTextLayoutBoundsCache.a.get(string);
            if (rectangle2D != null) {
                NFTextLayoutBoundsCache.b.removeElement(string);
                NFTextLayoutBoundsCache.b.addElement(string);
                return rectangle2D;
            }
            a();
            final Rectangle2D bounds = textLayout.getBounds();
            a(string, bounds);
            return bounds;
        }
    }
    
    private static void a() {
        if (NFTextLayoutBoundsCache.b.size() < NFTextLayoutBoundsCache.c) {
            return;
        }
        final String s = NFTextLayoutBoundsCache.b.elementAt(0);
        NFTextLayoutBoundsCache.b.removeElementAt(0);
        NFTextLayoutBoundsCache.a.remove(s);
    }
    
    private static void a(final String s, final Rectangle2D rectangle2D) {
        NFTextLayoutBoundsCache.a.put(s, rectangle2D);
        NFTextLayoutBoundsCache.b.addElement(s);
    }
    
    static {
        NFTextLayoutBoundsCache.a = new Hashtable();
        NFTextLayoutBoundsCache.b = new Vector();
        NFTextLayoutBoundsCache.c = 0;
        NFTextLayoutBoundsCache.d = new Object();
    }
}
