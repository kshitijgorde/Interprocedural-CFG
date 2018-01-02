// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import java.util.Enumeration;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactory;
import org.apache.xerces.validators.datatype.DatatypeValidatorFactoryImpl;
import java.util.Hashtable;

public class GrammarResolverImpl implements GrammarResolver
{
    private Hashtable fGrammarRegistry;
    private DatatypeValidatorFactoryImpl fDataTypeReg;
    
    public GrammarResolverImpl() {
        this.fGrammarRegistry = new Hashtable();
    }
    
    public Grammar getGrammar(final String s) {
        return this.fGrammarRegistry.get(s);
    }
    
    public DatatypeValidatorFactory getDatatypeRegistry() {
        if (this.fDataTypeReg == null) {
            this.fDataTypeReg = new DatatypeValidatorFactoryImpl();
        }
        return this.fDataTypeReg;
    }
    
    public String[] getNSKeysInPool() {
        final int size = this.fGrammarRegistry.size();
        final String[] array = new String[size];
        final Enumeration nameSpaceKeys = this.nameSpaceKeys();
        for (int i = 0; i < size; ++i) {
            array[i] = nameSpaceKeys.nextElement();
        }
        return array;
    }
    
    public void putGrammar(final String s, final Grammar grammar) {
        this.fGrammarRegistry.put(s, grammar);
    }
    
    public int size() {
        return this.fGrammarRegistry.size();
    }
    
    public Enumeration nameSpaceKeys() {
        return this.fGrammarRegistry.keys();
    }
    
    public Grammar removeGrammar(final String s) {
        if (this.containsNameSpace(s)) {
            this.fGrammarRegistry.remove(s);
        }
        return null;
    }
    
    public boolean contains(final Grammar grammar) {
        return this.fGrammarRegistry.contains(grammar);
    }
    
    public boolean containsNameSpace(final String s) {
        return this.fGrammarRegistry.containsKey(s);
    }
    
    public void clearGrammarResolver() {
        this.fGrammarRegistry.clear();
        if (this.fDataTypeReg != null) {
            this.fDataTypeReg.resetRegistry();
        }
    }
}
