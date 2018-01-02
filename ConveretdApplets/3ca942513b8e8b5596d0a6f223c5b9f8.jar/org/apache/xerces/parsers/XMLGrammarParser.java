// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.dv.DTDDVFactory;

public abstract class XMLGrammarParser extends XMLParser
{
    protected DTDDVFactory fDatatypeValidatorFactory;
    
    protected XMLGrammarParser(final SymbolTable symbolTable) {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }
}
