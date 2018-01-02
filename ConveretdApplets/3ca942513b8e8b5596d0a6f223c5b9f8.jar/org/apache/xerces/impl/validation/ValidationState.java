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
    private boolean fNamespaces;
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
        this.fNamespaces = true;
        this.fEntityState = null;
        this.fNamespaceContext = null;
        this.fSymbolTable = null;
        this.fIdTable = new Hashtable();
        this.fIdRefTable = new Hashtable();
    }
    
    public void setExtraChecking(final boolean fExtraChecking) {
        this.fExtraChecking = fExtraChecking;
    }
    
    public void setFacetChecking(final boolean fFacetChecking) {
        this.fFacetChecking = fFacetChecking;
    }
    
    public void setNormalizationRequired(final boolean fNormalize) {
        this.fNormalize = fNormalize;
    }
    
    public void setUsingNamespaces(final boolean fNamespaces) {
        this.fNamespaces = fNamespaces;
    }
    
    public void setEntityState(final EntityState fEntityState) {
        this.fEntityState = fEntityState;
    }
    
    public void setNamespaceSupport(final NamespaceContext fNamespaceContext) {
        this.fNamespaceContext = fNamespaceContext;
    }
    
    public void setSymbolTable(final SymbolTable fSymbolTable) {
        this.fSymbolTable = fSymbolTable;
    }
    
    public String checkIDRefID() {
        final Enumeration<String> keys = this.fIdRefTable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!this.fIdTable.containsKey(s)) {
                return s;
            }
        }
        return null;
    }
    
    public void reset() {
        this.fExtraChecking = true;
        this.fFacetChecking = true;
        this.fNamespaces = true;
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
    
    public boolean useNamespaces() {
        return this.fNamespaces;
    }
    
    public boolean isEntityDeclared(final String s) {
        return this.fEntityState != null && this.fEntityState.isEntityDeclared(this.getSymbol(s));
    }
    
    public boolean isEntityUnparsed(final String s) {
        return this.fEntityState != null && this.fEntityState.isEntityUnparsed(this.getSymbol(s));
    }
    
    public boolean isIdDeclared(final String s) {
        return this.fIdTable.containsKey(s);
    }
    
    public void addId(final String s) {
        this.fIdTable.put(s, ValidationState.fNullValue);
    }
    
    public void addIdRef(final String s) {
        this.fIdRefTable.put(s, ValidationState.fNullValue);
    }
    
    public String getSymbol(final String s) {
        if (this.fSymbolTable != null) {
            return this.fSymbolTable.addSymbol(s);
        }
        return s.intern();
    }
    
    public String getURI(final String s) {
        if (this.fNamespaceContext != null) {
            return this.fNamespaceContext.getURI(s);
        }
        return null;
    }
    
    static {
        fNullValue = new Object();
    }
}
