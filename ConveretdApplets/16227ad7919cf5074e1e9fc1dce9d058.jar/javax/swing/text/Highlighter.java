// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Shape;
import java.awt.Graphics;

public interface Highlighter
{
    Object addHighlight(final int p0, final int p1, final HighlightPainter p2) throws BadLocationException;
    
    void changeHighlight(final Object p0, final int p1, final int p2) throws BadLocationException;
    
    void deinstall(final JTextComponent p0);
    
    Highlight[] getHighlights();
    
    void install(final JTextComponent p0);
    
    void paint(final Graphics p0);
    
    void removeAllHighlights();
    
    void removeHighlight(final Object p0);
    
    public interface HighlightPainter
    {
        void paint(final Graphics p0, final int p1, final int p2, final Shape p3, final JTextComponent p4);
    }
    
    public interface Highlight
    {
        int getEndOffset();
        
        HighlightPainter getPainter();
        
        int getStartOffset();
    }
}
