// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import java.util.Enumeration;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactory;

public interface GrammarResolver
{
    Grammar getGrammar(final String p0);
    
    DatatypeValidatorFactory getDatatypeRegistry();
    
    Enumeration nameSpaceKeys();
    
    void putGrammar(final String p0, final Grammar p1);
    
    Grammar removeGrammar(final String p0);
    
    boolean contains(final Grammar p0);
    
    boolean containsNameSpace(final String p0);
    
    void clearGrammarResolver();
    
    int size();
}
