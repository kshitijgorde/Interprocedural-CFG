// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;

public interface Translet
{
    void transform(final DOM p0, final SerializationHandler p1) throws TransletException;
    
    void transform(final DOM p0, final SerializationHandler[] p1) throws TransletException;
    
    void transform(final DOM p0, final DTMAxisIterator p1, final SerializationHandler p2) throws TransletException;
    
    Object addParameter(final String p0, final Object p1);
    
    void buildKeys(final DOM p0, final DTMAxisIterator p1, final SerializationHandler p2, final int p3) throws TransletException;
    
    void addAuxiliaryClass(final Class p0);
    
    Class getAuxiliaryClass(final String p0);
    
    String[] getNamesArray();
    
    String[] getUrisArray();
    
    int[] getTypesArray();
    
    String[] getNamespaceArray();
}
