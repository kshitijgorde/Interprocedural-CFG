// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.validators.schema.SubstitutionGroupComparator;
import org.apache.xerces.utils.QName;

public interface XMLContentModel
{
    int validateContent(final QName[] p0, final int p1, final int p2) throws Exception;
    
    int validateContentSpecial(final QName[] p0, final int p1, final int p2) throws Exception;
    
    void setSubstitutionGroupComparator(final SubstitutionGroupComparator p0);
    
    int whatCanGoHere(final boolean p0, final InsertableElementsInfo p1) throws Exception;
    
    ContentLeafNameTypeVector getContentLeafNameTypeVector();
}
