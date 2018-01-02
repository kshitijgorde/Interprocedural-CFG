// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

public interface Element
{
    AttributeSet getAttributes();
    
    Document getDocument();
    
    Element getElement(final int p0);
    
    int getElementCount();
    
    int getElementIndex(final int p0);
    
    int getEndOffset();
    
    String getName();
    
    Element getParentElement();
    
    int getStartOffset();
    
    boolean isLeaf();
}
