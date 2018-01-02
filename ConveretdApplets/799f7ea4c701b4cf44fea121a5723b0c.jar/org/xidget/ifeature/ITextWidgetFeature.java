// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import java.util.EnumSet;

public interface ITextWidgetFeature
{
    void setHAlign(final HAlign p0);
    
    void setVAlign(final VAlign p0);
    
    void setEditable(final boolean p0);
    
    void setFontFamily(final String p0);
    
    void setFontStyles(final EnumSet<FontStyle> p0);
    
    void setFontSize(final double p0);
    
    public enum FontStyle
    {
        plain("plain", 0), 
        italic("italic", 1), 
        bold("bold", 2);
        
        private FontStyle(final String s, final int n) {
        }
    }
    
    public enum HAlign
    {
        left("left", 0), 
        center("center", 1), 
        right("right", 2);
        
        private HAlign(final String s, final int n) {
        }
    }
    
    public enum VAlign
    {
        top("top", 0), 
        center("center", 1), 
        bottom("bottom", 2);
        
        private VAlign(final String s, final int n) {
        }
    }
}
