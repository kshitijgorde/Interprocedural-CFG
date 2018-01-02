// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

public interface XMLContentModel
{
    int validateContent(final int p0, final int[] p1) throws Exception;
    
    int whatCanGoHere(final boolean p0, final InsertableElementsInfo p1) throws Exception;
}
