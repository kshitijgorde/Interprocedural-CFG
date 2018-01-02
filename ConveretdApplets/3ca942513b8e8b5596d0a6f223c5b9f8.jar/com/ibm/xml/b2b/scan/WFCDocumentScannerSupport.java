// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.SymbolTable;
import com.ibm.xml.b2b.util.ErrorReporter;

public abstract class WFCDocumentScannerSupport extends DocumentScannerSupport
{
    private static final int USE_HASHTABLE_ATTR_COUNT = 16;
    private int[] fAttrHashtable;
    private int[] fNextAttrInBucket;
    private int fNSDeclNamespaceHandle;
    private ErrorReporter fErrors;
    private String[] fErrorArgs;
    private int fErrorArgsCount;
    
    public WFCDocumentScannerSupport(final SymbolTable symbolTable, final XMLStringBuffer xmlStringBuffer, final ErrorReporter fErrors) {
        super(symbolTable, xmlStringBuffer);
        this.fNSDeclNamespaceHandle = super.fSymbolTable.addSymbol("http://www.w3.org/2000/xmlns/");
        this.fErrors = fErrors;
        this.fErrorArgs = new String[8];
    }
    
    public void reset(final boolean b) {
        super.reset(b);
        this.fNSDeclNamespaceHandle = super.fSymbolTable.addSymbol("http://www.w3.org/2000/xmlns/");
        this.fErrorArgsCount = 0;
    }
    
    protected void entityNotDeclared(final XMLName xmlName) {
        this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 47, new String[] { xmlName.toString() });
    }
    
    public void setParameter(final int n, final XMLString xmlString) {
        this.fErrorArgs[n] = xmlString.toString();
        if (n >= this.fErrorArgsCount) {
            this.fErrorArgsCount = n + 1;
        }
    }
    
    public void setInvalidCharParameter(final int n, final ParsedEntity parsedEntity) {
        this.fErrorArgs[n] = Integer.toHexString(parsedEntity.decodeInvalidCharacter());
        if (n >= this.fErrorArgsCount) {
            this.fErrorArgsCount = n + 1;
        }
    }
    
    public void reportWarning(final String s, final int n) {
        Object[] fErrorArgs;
        if (this.fErrorArgsCount == 0) {
            fErrorArgs = null;
        }
        else {
            fErrorArgs = this.fErrorArgs;
            this.fErrorArgsCount = 0;
        }
        this.fErrors.reportWarning(s, n, fErrorArgs);
    }
    
    public void reportRecoverableError(final String s, final int n) {
        Object[] fErrorArgs;
        if (this.fErrorArgsCount == 0) {
            fErrorArgs = null;
        }
        else {
            fErrorArgs = this.fErrorArgs;
            this.fErrorArgsCount = 0;
        }
        this.fErrors.reportRecoverableError(s, n, fErrorArgs);
    }
    
    public void reportFatalError(final String s, final int n) {
        Object[] fErrorArgs;
        if (this.fErrorArgsCount == 0) {
            fErrorArgs = null;
        }
        else {
            fErrorArgs = this.fErrorArgs;
            this.fErrorArgsCount = 0;
        }
        this.fErrors.reportFatalError(s, n, fErrorArgs);
    }
    
    private void checkDuplicateAttrs() {
        for (int i = super.firstMapping; i < super.lastMapping - 1; ++i) {
            final String s = super.prefixes[i];
            for (int j = i + 1; j < super.lastMapping; ++j) {
                if (super.prefixes[j] == s) {
                    this.duplicateAttribute(super.nsDeclQNames[i]);
                }
            }
        }
        if (super.attrCount < 16) {
            for (int k = 0; k < super.attrCount - 1; ++k) {
                final String localPart = super.attrNames[k].localPart;
                for (int l = k + 1; l < super.attrCount; ++l) {
                    if (super.attrNames[l].localPart == localPart && super.attrNames[l].namespaceURI == super.attrNames[k].namespaceURI) {
                        this.duplicateAttribute(super.attrNames[l].str);
                    }
                }
            }
        }
        else {
            int n;
            int n2;
            for (n = super.attrCount >> 4, n2 = 1024; n2 < n; n2 <<= 1) {}
            if (this.fAttrHashtable == null || n2 > this.fAttrHashtable.length) {
                this.fAttrHashtable = new int[n2];
            }
            for (int n3 = 0; n3 < n2; ++n3) {
                this.fAttrHashtable[n3] = -1;
            }
            --n2;
            final int length = super.attrNames.length;
            if (this.fNextAttrInBucket == null || length > this.fNextAttrInBucket.length) {
                this.fNextAttrInBucket = new int[length];
            }
            for (int n4 = 0; n4 < super.attrCount; ++n4) {
                final int localHandle = super.attrNames[n4].localHandle;
                final int nsHandle = super.attrNames[n4].nsHandle;
                final int n5 = localHandle + nsHandle & n2;
                final int n6 = this.fAttrHashtable[n5];
                if (n6 >= 0) {
                    for (int n7 = n6; n7 >= 0; n7 = this.fNextAttrInBucket[n7]) {
                        if (super.attrNames[n7].localHandle == localHandle && super.attrNames[n7].nsHandle == nsHandle) {
                            this.duplicateAttribute(super.attrNames[n4].str);
                            return;
                        }
                    }
                }
                this.fNextAttrInBucket[n4] = n6;
                this.fAttrHashtable[n5] = n4;
            }
        }
    }
    
    public void resolveNamespaceURIs() {
        super.resolveNamespaceURIs();
        this.checkDuplicateAttrs();
    }
    
    protected void createPrefixMapping(final String s, final int n, final String s2) {
        if (s != XMLString.EMPTY_STRING && n == 0) {
            this.namespaceNameEmpty(s);
        }
        if (s == super.fDefaultPrefix) {
            if (n != super.nsHandles[0]) {
                this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 51, null);
            }
        }
        else if (s == super.nsDeclPrefix) {
            this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 54, null);
        }
        else if (n == super.nsHandles[0] || n == this.fNSDeclNamespaceHandle) {
            this.illegalNamespaceURI(s, n);
        }
        super.createPrefixMapping(s, n, s2);
    }
    
    protected boolean setNamespaceURI(final QName namespaceURI) {
        if (!super.setNamespaceURI(namespaceURI)) {
            this.undeclaredPrefix(namespaceURI.prefix);
        }
        return true;
    }
    
    protected void undeclaredPrefix(final String s) {
        this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 50, new String[] { s });
    }
    
    private void namespaceNameEmpty(final String s) {
        this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 52, new String[] { s });
    }
    
    private void illegalNamespaceURI(final String s, final int n) {
        this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 53, new String[] { s, super.fSymbolTable.toString(n) });
    }
    
    protected void duplicateAttribute(final String s) {
        this.fErrors.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 17, new String[] { super.currentElement.str, s });
    }
}
