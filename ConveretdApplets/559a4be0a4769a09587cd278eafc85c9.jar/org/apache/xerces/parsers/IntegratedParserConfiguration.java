// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.impl.dtd.XMLNSDTDValidator;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.parser.XMLComponent;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.dtd.XMLDTDValidator;
import org.apache.xerces.impl.XMLDocumentScannerImpl;
import org.apache.xerces.impl.XMLNSDocumentScannerImpl;

public class IntegratedParserConfiguration extends StandardParserConfiguration
{
    protected XMLNSDocumentScannerImpl fNamespaceScanner;
    protected XMLDocumentScannerImpl fNonNSScanner;
    protected XMLDTDValidator fNonNSDTDValidator;
    
    public IntegratedParserConfiguration() {
        this(null, null, null);
    }
    
    public IntegratedParserConfiguration(final SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }
    
    public IntegratedParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }
    
    public IntegratedParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool grammarPool, final XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        this.fNonNSScanner = new XMLDocumentScannerImpl();
        this.fNonNSDTDValidator = new XMLDTDValidator();
        this.addComponent(this.fNonNSScanner);
        this.addComponent(this.fNonNSDTDValidator);
    }
    
    protected void configurePipeline() {
        if (super.fDTDScanner != null) {
            if (super.fDTDProcessor != null) {
                super.fDTDScanner.setDTDHandler(super.fDTDProcessor);
                super.fDTDProcessor.setDTDHandler(super.fDTDHandler);
                super.fDTDScanner.setDTDContentModelHandler(super.fDTDProcessor);
                super.fDTDProcessor.setDTDContentModelHandler(super.fDTDContentModelHandler);
            }
            else {
                super.fDTDScanner.setDTDHandler(super.fDTDHandler);
                super.fDTDScanner.setDTDContentModelHandler(super.fDTDContentModelHandler);
            }
        }
        if (super.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            if (super.fSchemaValidator == null) {
                super.fSchemaValidator = new XMLSchemaValidator();
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", super.fSchemaValidator);
                this.addComponent(super.fSchemaValidator);
                if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                    final XSMessageFormatter xmft = new XSMessageFormatter();
                    super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", xmft);
                }
            }
            super.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", super.fDTDValidator);
            super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
            super.fScanner = this.fNamespaceScanner;
            this.fNamespaceScanner.setDTDValidator(super.fDTDValidator);
            this.fNamespaceScanner.setDocumentHandler(super.fDTDValidator);
            super.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
            super.fDTDValidator.setDocumentHandler(super.fSchemaValidator);
            super.fSchemaValidator.setDocumentSource(super.fDTDValidator);
            super.fSchemaValidator.setDocumentHandler(super.fDocumentHandler);
            super.fLastComponent = super.fSchemaValidator;
        }
        else if (super.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
            super.fScanner = this.fNamespaceScanner;
            super.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", super.fDTDValidator);
            super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
            this.fNamespaceScanner.setDTDValidator(super.fDTDValidator);
            this.fNamespaceScanner.setDocumentHandler(super.fDTDValidator);
            super.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
            super.fDTDValidator.setDocumentHandler(super.fDocumentHandler);
            super.fDocumentHandler.setDocumentSource(super.fDTDValidator);
            super.fLastComponent = super.fDTDValidator;
        }
        else {
            super.fScanner = this.fNonNSScanner;
            super.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", this.fNonNSDTDValidator);
            super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNonNSScanner);
            this.fNonNSScanner.setDocumentHandler(this.fNonNSDTDValidator);
            this.fNonNSDTDValidator.setDocumentSource(this.fNonNSScanner);
            this.fNonNSDTDValidator.setDocumentHandler(super.fDocumentHandler);
            super.fDocumentHandler.setDocumentSource(this.fNonNSDTDValidator);
            super.fLastComponent = this.fNonNSDTDValidator;
        }
    }
    
    protected XMLDocumentScanner createDocumentScanner() {
        return this.fNamespaceScanner = new XMLNSDocumentScannerImpl();
    }
    
    protected XMLDTDValidator createDTDValidator() {
        return new XMLNSDTDValidator();
    }
}
