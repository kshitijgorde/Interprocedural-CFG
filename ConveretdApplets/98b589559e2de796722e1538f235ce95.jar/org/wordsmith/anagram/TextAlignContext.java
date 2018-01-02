// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public interface TextAlignContext
{
    int getTotalHeight();
    
    int getTotalWidth();
    
    FontRenderContext getFontRenderContext();
    
    Font getFont();
}
