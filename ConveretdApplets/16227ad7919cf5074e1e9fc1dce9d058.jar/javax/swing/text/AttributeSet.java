// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.util.Enumeration;

public interface AttributeSet
{
    public static final Object NameAttribute = StyleConstants.NameAttribute;
    public static final Object ResolveAttribute = StyleConstants.ResolveAttribute;
    
    boolean containsAttribute(final Object p0, final Object p1);
    
    boolean containsAttributes(final AttributeSet p0);
    
    AttributeSet copyAttributes();
    
    Object getAttribute(final Object p0);
    
    int getAttributeCount();
    
    Enumeration getAttributeNames();
    
    AttributeSet getResolveParent();
    
    boolean isDefined(final Object p0);
    
    boolean isEqual(final AttributeSet p0);
    
    public interface FontAttribute
    {
    }
    
    public interface ColorAttribute
    {
    }
    
    public interface CharacterAttribute
    {
    }
    
    public interface ParagraphAttribute
    {
    }
}
