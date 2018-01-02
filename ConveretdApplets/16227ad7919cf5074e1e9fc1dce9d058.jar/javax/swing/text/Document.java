// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.event.UndoableEditListener;
import javax.swing.event.DocumentListener;

public interface Document
{
    public static final String StreamDescriptionProperty = "stream";
    public static final String TitleProperty = "title";
    
    void addDocumentListener(final DocumentListener p0);
    
    void addUndoableEditListener(final UndoableEditListener p0);
    
    Position createPosition(final int p0) throws BadLocationException;
    
    Element getDefaultRootElement();
    
    Position getEndPosition();
    
    int getLength();
    
    Object getProperty(final Object p0);
    
    Element[] getRootElements();
    
    Position getStartPosition();
    
    String getText(final int p0, final int p1) throws BadLocationException;
    
    void getText(final int p0, final int p1, final Segment p2) throws BadLocationException;
    
    void insertString(final int p0, final String p1, final AttributeSet p2) throws BadLocationException;
    
    void putProperty(final Object p0, final Object p1);
    
    void remove(final int p0, final int p1) throws BadLocationException;
    
    void removeDocumentListener(final DocumentListener p0);
    
    void removeUndoableEditListener(final UndoableEditListener p0);
    
    void render(final Runnable p0);
}
