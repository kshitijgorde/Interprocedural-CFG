// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;
import java.util.Locale;
import org.w3c.dom.Document;

public interface DocumentEx extends Document, XmlWritable, ElementFactory
{
    String getSystemId();
    
    void setElementFactory(final ElementFactory p0);
    
    ElementFactory getElementFactory();
    
    ElementEx getElementExById(final String p0);
    
    Locale getLocale();
    
    void setLocale(final Locale p0);
    
    Locale chooseLocale(final String[] p0);
    
    void changeNodeOwner(final Node p0);
}
