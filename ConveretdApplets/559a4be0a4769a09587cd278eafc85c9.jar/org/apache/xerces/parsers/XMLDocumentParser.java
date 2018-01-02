// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public class XMLDocumentParser extends AbstractXMLDocumentParser
{
    public XMLDocumentParser() {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.StandardParserConfiguration"));
    }
    
    public XMLDocumentParser(final XMLParserConfiguration config) {
        super(config);
    }
    
    public XMLDocumentParser(final SymbolTable symbolTable) {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.StandardParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }
    
    public XMLDocumentParser(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.StandardParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
    }
}
