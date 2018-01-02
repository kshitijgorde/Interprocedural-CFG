// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.validation;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.dv.ValidationContext;

public class ValidationState implements ValidationContext
{
    private boolean fExtraChecking;
    private boolean fFacetChecking;
    private boolean fNormalize;
    private EntityState fEntityState;
    private NamespaceContext fNamespaceContext;
    private SymbolTable fSymbolTable;
    private final Hashtable fIdTable;
    private final Hashtable fIdRefTable;
    private static final Object fNullValue;
    
    public ValidationState() {
        this.fExtraChecking = true;
        this.fFacetChecking = true;
        this.fNormalize = true;
        this.fEntityState = null;
        this.fNamespaceContext = null;
        this.fSymbolTable = null;
        this.fIdTable = new Hashtable();
        this.fIdRefTable = new Hashtable();
    }
    
    public void setExtraChecking(final boolean newValue) {
        this.fExtraChecking = newValue;
    }
    
    public void setFacetChecking(final boolean newValue) {
        this.fFacetChecking = newValue;
    }
    
    public void setNormalizationRequired(final boolean newValue) {
        this.fNormalize = newValue;
    }
    
    public void setEntityState(final EntityState state) {
        this.fEntityState = state;
    }
    
    public void setNamespaceSupport(final NamespaceContext namespace) {
        this.fNamespaceContext = namespace;
    }
    
    public void setSymbolTable(final SymbolTable sTable) {
        this.fSymbolTable = sTable;
    }
    
    public String checkIDRefID() {
        final Enumeration en = this.fIdRefTable.keys();
        while (en.hasMoreElements()) {
            final String key = en.nextElement();
            if (!this.fIdTable.containsKey(key)) {
                return key;
            }
        }
        return null;
    }
    
    public void reset() {
        this.fExtraChecking = true;
        this.fFacetChecking = true;
        this.fIdTable.clear();
        this.fIdRefTable.clear();
        this.fEntityState = null;
        this.fNamespaceContext = null;
        this.fSymbolTable = null;
    }
    
    public void resetIDTables() {
        this.fIdTable.clear();
        this.fIdRefTable.clear();
    }
    
    public boolean needExtraChecking() {
        return this.fExtraChecking;
    }
    
    public boolean needFacetChecking() {
        return this.fFacetChecking;
    }
    
    public boolean needToNormalize() {
        return this.fNormalize;
    }
    
    public boolean isEntityDeclared(final String name) {
        return this.fEntityState != null && this.fEntityState.isEntityDeclared(this.getSymbol(name));
    }
    
    public boolean isEntityUnparsed(final String name) {
        return this.fEntityState != null && this.fEntityState.isEntityUnparsed(this.getSymbol(name));
    }
    
    public boolean isIdDeclared(final String name) {
        return this.fIdTable.containsKey(name);
    }
    
    public void addId(final String name) {
        this.fIdTable.put(name, ValidationState.fNullValue);
    }
    
    public void addIdRef(final String name) {
        this.fIdRefTable.put(name, ValidationState.fNullValue);
    }
    
    public String getSymbol(final String symbol) {
        if (this.fSymbolTable != null) {
            return this.fSymbolTable.addSymbol(symbol);
        }
        return symbol.intern();
    }
    
    public String getURI(final String prefix) {
        if (this.fNamespaceContext != null) {
            return this.fNamespaceContext.getURI(prefix);
        }
        return null;
    }
    
    static {
        fNullValue = new Object();
    }
}
