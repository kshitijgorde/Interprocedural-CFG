// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.awt.font.TextLayout;
import java.awt.Shape;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.util.Map;
import java.awt.font.FontRenderContext;

public final class i
{
    private static final FontRenderContext a;
    private final Map b;
    
    static {
        a = new FontRenderContext(null, true, true);
    }
    
    public i(final int n) {
        this.b = new j(this, 750, 0.75f, true, 750);
    }
    
    public final Shape a(Font deriveFont, final double n, final String s, final double n2) {
        final d d = new d(deriveFont, n, s, n2);
        synchronized (this.b) {
            Shape outline;
            if ((outline = this.b.get(d)) == null) {
                deriveFont = (deriveFont = deriveFont).deriveFont((float)n);
                outline = new TextLayout(h.a(s, deriveFont), deriveFont, i.a).getOutline((n2 == 0.0) ? null : AffineTransform.getRotateInstance(n2));
                this.b.put(d, outline);
            }
            // monitorexit(this.b)
            return outline;
        }
    }
}
