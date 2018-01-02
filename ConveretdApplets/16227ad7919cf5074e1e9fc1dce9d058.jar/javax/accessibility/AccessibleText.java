// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.text.AttributeSet;

public interface AccessibleText
{
    public static final int CHARACTER = 1;
    public static final int WORD = 2;
    public static final int SENTENCE = 3;
    
    String getAfterIndex(final int p0, final int p1);
    
    String getAtIndex(final int p0, final int p1);
    
    String getBeforeIndex(final int p0, final int p1);
    
    int getCaretPosition();
    
    int getCharCount();
    
    AttributeSet getCharacterAttribute(final int p0);
    
    Rectangle getCharacterBounds(final int p0);
    
    int getIndexAtPoint(final Point p0);
    
    String getSelectedText();
    
    int getSelectionEnd();
    
    int getSelectionStart();
}
