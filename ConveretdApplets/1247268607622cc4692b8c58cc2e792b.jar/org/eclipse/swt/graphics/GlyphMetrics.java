// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;

public final class GlyphMetrics
{
    public int ascent;
    public int descent;
    public int width;
    
    public GlyphMetrics(final int ascent, final int descent, final int width) {
        if (ascent < 0 || descent < 0 || width < 0) {
            SWT.error(5);
        }
        this.ascent = ascent;
        this.descent = descent;
        this.width = width;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GlyphMetrics)) {
            return false;
        }
        final GlyphMetrics glyphMetrics = (GlyphMetrics)o;
        return glyphMetrics.ascent == this.ascent && glyphMetrics.descent == this.descent && glyphMetrics.width == this.width;
    }
    
    public int hashCode() {
        return this.ascent ^ this.descent ^ this.width;
    }
    
    public String toString() {
        return "GlyphMetrics {" + this.ascent + ", " + this.descent + ", " + this.width + "}";
    }
}
