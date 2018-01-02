// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.TEXTMETRIC;

public final class FontMetrics
{
    public TEXTMETRIC handle;
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FontMetrics)) {
            return false;
        }
        final TEXTMETRIC handle = ((FontMetrics)o).handle;
        return this.handle.tmHeight == handle.tmHeight && this.handle.tmAscent == handle.tmAscent && this.handle.tmDescent == handle.tmDescent && this.handle.tmInternalLeading == handle.tmInternalLeading && this.handle.tmExternalLeading == handle.tmExternalLeading && this.handle.tmAveCharWidth == handle.tmAveCharWidth && this.handle.tmMaxCharWidth == handle.tmMaxCharWidth && this.handle.tmWeight == handle.tmWeight && this.handle.tmOverhang == handle.tmOverhang && this.handle.tmDigitizedAspectX == handle.tmDigitizedAspectX && this.handle.tmDigitizedAspectY == handle.tmDigitizedAspectY && this.handle.tmItalic == handle.tmItalic && this.handle.tmUnderlined == handle.tmUnderlined && this.handle.tmStruckOut == handle.tmStruckOut && this.handle.tmPitchAndFamily == handle.tmPitchAndFamily && this.handle.tmCharSet == handle.tmCharSet;
    }
    
    public int getAscent() {
        return this.handle.tmAscent - this.handle.tmInternalLeading;
    }
    
    public int getAverageCharWidth() {
        return this.handle.tmAveCharWidth;
    }
    
    public int getDescent() {
        return this.handle.tmDescent;
    }
    
    public int getHeight() {
        return this.handle.tmHeight;
    }
    
    public int getLeading() {
        return this.handle.tmInternalLeading;
    }
    
    public int hashCode() {
        return this.handle.tmHeight ^ this.handle.tmAscent ^ this.handle.tmDescent ^ this.handle.tmInternalLeading ^ this.handle.tmExternalLeading ^ this.handle.tmAveCharWidth ^ this.handle.tmMaxCharWidth ^ this.handle.tmWeight ^ this.handle.tmOverhang ^ this.handle.tmDigitizedAspectX ^ this.handle.tmDigitizedAspectY ^ this.handle.tmItalic ^ this.handle.tmUnderlined ^ this.handle.tmStruckOut ^ this.handle.tmPitchAndFamily ^ this.handle.tmCharSet;
    }
    
    public static FontMetrics win32_new(final TEXTMETRIC handle) {
        final FontMetrics fontMetrics = new FontMetrics();
        fontMetrics.handle = handle;
        return fontMetrics;
    }
}
