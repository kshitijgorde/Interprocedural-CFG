// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.impl.XML11DTDScannerImpl;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.impl.XML11DocumentScannerImpl;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.impl.XML11EntityManager;
import org.apache.xerces.impl.XMLEntityManager;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;

public class XML11Configuration extends StandardParserConfiguration
{
    public XML11Configuration() {
        this(null, null, null);
    }
    
    public XML11Configuration(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public XML11Configuration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }
    
    public XML11Configuration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
    }
    
    protected XMLEntityManager createEntityManager() {
        return new XML11EntityManager();
    }
    
    protected XMLDocumentScanner createDocumentScanner() {
        return new XML11DocumentScannerImpl();
    }
    
    protected XMLDTDScanner createDTDScanner() {
        return new XML11DTDScannerImpl();
    }
}
