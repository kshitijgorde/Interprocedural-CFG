// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSAnnotation extends XSObject
{
    public static final short W3C_DOM_ELEMENT = 1;
    public static final short SAX_CONTENTHANDLER = 2;
    public static final short W3C_DOM_DOCUMENT = 3;
    
    boolean writeAnnotation(final Object p0, final short p1);
    
    String getAnnotationString();
}
