// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Shape;
import java.awt.Graphics;

public abstract class LayeredHighlighter implements Highlighter
{
    public abstract Object addHighlight(final int p0, final int p1, final HighlightPainter p2) throws BadLocationException;
    
    public abstract void changeHighlight(final Object p0, final int p1, final int p2) throws BadLocationException;
    
    public abstract void deinstall(final JTextComponent p0);
    
    public abstract Highlight[] getHighlights();
    
    public abstract void install(final JTextComponent p0);
    
    public abstract void paint(final Graphics p0);
    
    public abstract void paintLayeredHighlights(final Graphics p0, final int p1, final int p2, final Shape p3, final JTextComponent p4, final View p5);
    
    public abstract void removeAllHighlights();
    
    public abstract void removeHighlight(final Object p0);
    
    public abstract static class LayerPainter implements HighlightPainter
    {
        public abstract void paint(final Graphics p0, final int p1, final int p2, final Shape p3, final JTextComponent p4);
        
        public abstract Shape paintLayer(final Graphics p0, final int p1, final int p2, final Shape p3, final JTextComponent p4, final View p5);
    }
}
