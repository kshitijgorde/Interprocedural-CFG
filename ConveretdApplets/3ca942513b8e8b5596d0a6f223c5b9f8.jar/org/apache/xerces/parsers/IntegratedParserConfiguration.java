// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.apache.xerces.impl.dtd.XMLNSDTDValidator;
import org.apache.xerces.xni.parser.XMLDocumentScanner;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.impl.xs.XSMessageFormatter;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
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
    
    public IntegratedParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        this(symbolTable, xmlGrammarPool, null);
    }
    
    public IntegratedParserConfiguration(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool, final XMLComponentManager xmlComponentManager) {
        super(symbolTable, xmlGrammarPool, xmlComponentManager);
        this.fNonNSScanner = new XMLDocumentScannerImpl();
        this.fNonNSDTDValidator = new XMLDTDValidator();
        this.addComponent(this.fNonNSScanner);
        this.addComponent(this.fNonNSDTDValidator);
    }
    
    protected void configurePipeline() {
        this.setProperty("http://apache.org/xml/properties/internal/datatype-validator-factory", super.fDatatypeValidatorFactory);
        this.configureDTDPipeline();
        if (super.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
            super.fProperties.put("http://apache.org/xml/properties/internal/namespace-binder", super.fNamespaceBinder);
            super.fScanner = this.fNamespaceScanner;
            super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNamespaceScanner);
            if (super.fDTDValidator != null) {
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", super.fDTDValidator);
                this.fNamespaceScanner.setDTDValidator(super.fDTDValidator);
                this.fNamespaceScanner.setDocumentHandler(super.fDTDValidator);
                super.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
                super.fDTDValidator.setDocumentHandler(super.fDocumentHandler);
                if (super.fDocumentHandler != null) {
                    super.fDocumentHandler.setDocumentSource(super.fDTDValidator);
                }
                super.fLastComponent = super.fDTDValidator;
            }
            else {
                this.fNamespaceScanner.setDocumentHandler(super.fDocumentHandler);
                this.fNamespaceScanner.setDTDValidator(null);
                if (super.fDocumentHandler != null) {
                    super.fDocumentHandler.setDocumentSource(this.fNamespaceScanner);
                }
                super.fLastComponent = this.fNamespaceScanner;
            }
        }
        else {
            super.fScanner = this.fNonNSScanner;
            super.fProperties.put("http://apache.org/xml/properties/internal/document-scanner", this.fNonNSScanner);
            if (this.fNonNSDTDValidator != null) {
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/dtd", this.fNonNSDTDValidator);
                this.fNonNSScanner.setDocumentHandler(this.fNonNSDTDValidator);
                this.fNonNSDTDValidator.setDocumentSource(this.fNonNSScanner);
                this.fNonNSDTDValidator.setDocumentHandler(super.fDocumentHandler);
                if (super.fDocumentHandler != null) {
                    super.fDocumentHandler.setDocumentSource(this.fNonNSDTDValidator);
                }
                super.fLastComponent = this.fNonNSDTDValidator;
            }
            else {
                super.fScanner.setDocumentHandler(super.fDocumentHandler);
                if (super.fDocumentHandler != null) {
                    super.fDocumentHandler.setDocumentSource(super.fScanner);
                }
                super.fLastComponent = super.fScanner;
            }
        }
        if (super.fFeatures.get("http://apache.org/xml/features/validation/schema") == Boolean.TRUE) {
            if (super.fSchemaValidator == null) {
                super.fSchemaValidator = new XMLSchemaValidator();
                super.fProperties.put("http://apache.org/xml/properties/internal/validator/schema", super.fSchemaValidator);
                this.addComponent(super.fSchemaValidator);
                if (super.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/xml-schema-1") == null) {
                    super.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/xml-schema-1", new XSMessageFormatter());
                }
            }
            super.fLastComponent.setDocumentHandler(super.fSchemaValidator);
            super.fSchemaValidator.setDocumentSource(super.fLastComponent);
            super.fSchemaValidator.setDocumentHandler(super.fDocumentHandler);
            if (super.fDocumentHandler != null) {
                super.fDocumentHandler.setDocumentSource(super.fSchemaValidator);
            }
            super.fLastComponent = super.fSchemaValidator;
        }
    }
    
    protected XMLDocumentScanner createDocumentScanner() {
        return this.fNamespaceScanner = new XMLNSDocumentScannerImpl();
    }
    
    protected XMLDTDValidator createDTDValidator() {
        return new XMLNSDTDValidator();
    }
}
